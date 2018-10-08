package net.glowstone.webservices.controller;

import net.glowstone.webservices.dto.BannedPlayer;
import net.glowstone.webservices.repositories.BannedRepository;
import net.glowstone.webservices.repositories.criteria.BannedPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static net.glowstone.webservices.repositories.specification.BannedPlayerSpecification.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
@RequestMapping("/banned")
public class BannedController {

    private final BannedRepository bannedRepository;

    @Autowired
    public BannedController(BannedRepository bannedRepository) {
        this.bannedRepository = bannedRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<BannedPlayer> findWithCriteria(@RequestParam(name = "page", required = false, defaultValue = "0")int page,
                                               @RequestParam(name = "size", required = false, defaultValue = "15")int size,
                                               @RequestParam(name = "id", required = false)Long id,
                                               @RequestParam(name = "target", required = false)String target,
                                               @RequestParam(name = "created", required = false)Date created,
                                               @RequestParam(name = "expires", required = false)Date expires,
                                               @RequestParam(name = "source", required = false)String source,
                                               @RequestParam(name = "reason", required = false)String reason) {

        BannedPlayerCriteria criteria = new BannedPlayerCriteria(id, target, created, expires, source, reason);

        if(criteria.isEmpty())
            return bannedRepository.findAll(new PageRequest(page, size));

        return  bannedRepository.findAll(where(withId(criteria.getId()))
                .and(withTarget(criteria.getTarget()))
                .and(withCreated(criteria.getCreated()))
                .and(withExpires(criteria.getExpires()))
                .and(withSource(criteria.getSource()))
                .and(withReason(criteria.getReason())), new PageRequest(page, size));

    }

}
