package net.glowstone.webservices.controller;

import net.glowstone.webservices.dto.WhiteListPlayer;
import net.glowstone.webservices.repositories.WhiteListRepository;
import net.glowstone.webservices.repositories.criteria.WhiteListPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static net.glowstone.webservices.repositories.specification.WhiteListPlayerSpecification.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
@RequestMapping("/whitelist")
public class WhiteListController {

    private final WhiteListRepository whiteListRepository;

    @Autowired
    public WhiteListController(WhiteListRepository whiteListRepository) {
        this.whiteListRepository = whiteListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<WhiteListPlayer> findWithCriteria(@RequestParam(name = "page", required = false, defaultValue = "0")int page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "15")int size,
                                                  @RequestParam(name = "id", required = false)Long id,
                                                  @RequestParam(name = "name", required = false)String name,
                                                  @RequestParam(name = "uuid", required = false)String uuid) {

        WhiteListPlayerCriteria criteria = new WhiteListPlayerCriteria(id, name, uuid);

        if(criteria.isEmpty())
            return whiteListRepository.findAll(new PageRequest(page, size));

        return whiteListRepository.findAll(where(withId(criteria.getId()))
                .and(withName(criteria.getName()))
                .and(withUuid(criteria.getUuid())), new PageRequest(page, size));

    }

}
