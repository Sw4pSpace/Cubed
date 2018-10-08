package net.glowstone.webservices.controller;

import net.glowstone.webservices.dto.OpPlayer;
import net.glowstone.webservices.repositories.OpsRepository;
import net.glowstone.webservices.repositories.criteria.OpPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static net.glowstone.webservices.repositories.specification.OpPlayerSpecification.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
@RequestMapping("/op")
public class OpController {

    private final OpsRepository opsRepository;

    @Autowired
    public OpController(OpsRepository opsRepository) {
        this.opsRepository = opsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<OpPlayer> findWithCriteria(@RequestParam(name = "page", required = false, defaultValue = "0")int page,
                                           @RequestParam(name = "size", required = false, defaultValue = "15")int size,
                                           @RequestParam(name = "id", required = false)Long id,
                                           @RequestParam(name = "name", required = false)String name,
                                           @RequestParam(name = "uuid", required = false)String uuid) {

        OpPlayerCriteria criteria = new OpPlayerCriteria(id, name, uuid);

        if(criteria.isEmpty())
            return opsRepository.findAll(new PageRequest(page, size));

        return opsRepository.findAll(where(withId(criteria.getId()))
                .and(withName(criteria.getName()))
                .and(withUuid(criteria.getUuid())), new PageRequest(page, size));

    }

}
