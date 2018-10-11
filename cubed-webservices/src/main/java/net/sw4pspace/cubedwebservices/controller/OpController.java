package net.sw4pspace.cubedwebservices.controller;

import io.swagger.annotations.ApiOperation;
import net.sw4pspace.cubedwebservices.repositories.specification.OpPlayerSpecification;
import net.sw4pspace.cubedwebservices.dto.OpPlayer;
import net.sw4pspace.cubedwebservices.repositories.OpsRepository;
import net.sw4pspace.cubedwebservices.repositories.criteria.OpPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Represents the /op endpoint
 *
 * @author jdesive
 */
@RestController
@RequestMapping("/op")
public class OpController {

    private final OpsRepository opsRepository;

    @Autowired
    public OpController(OpsRepository opsRepository) {
        this.opsRepository = opsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(tags = {"Op"}, value = "Search for Server Operators", nickname = "Search Ops", produces = "applications/json")
    public Page<OpPlayer> findWithCriteria(@RequestParam(name = "page", required = false, defaultValue = "0")int page,
                                           @RequestParam(name = "size", required = false, defaultValue = "15")int size,
                                           @RequestParam(name = "id", required = false)Long id,
                                           @RequestParam(name = "name", required = false)String name,
                                           @RequestParam(name = "uuid", required = false)String uuid) {

        OpPlayerCriteria criteria = new OpPlayerCriteria(id, name, uuid);

        if(criteria.isEmpty())
            return opsRepository.findAll(new PageRequest(page, size));

        return opsRepository.findAll(where(OpPlayerSpecification.withId(criteria.getId()))
                .and(OpPlayerSpecification.withName(criteria.getName()))
                .and(OpPlayerSpecification.withUuid(criteria.getUuid())), new PageRequest(page, size));

    }

}
