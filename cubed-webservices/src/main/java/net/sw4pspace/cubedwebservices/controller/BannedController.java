package net.sw4pspace.cubedwebservices.controller;

import io.swagger.annotations.ApiOperation;
import net.sw4pspace.cubedwebservices.repositories.BannedRepository;
import net.sw4pspace.cubedwebservices.repositories.specification.BannedPlayerSpecification;
import net.sw4pspace.cubedwebservices.dto.BannedPlayer;
import net.sw4pspace.cubedwebservices.repositories.criteria.BannedPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Represents the /banned endpoint
 *
 * @author jdesive
 */
@RestController
@RequestMapping("/banned")
public class BannedController {

    private final BannedRepository bannedRepository;

    @Autowired
    public BannedController(BannedRepository bannedRepository) {
        this.bannedRepository = bannedRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(tags = {"Banned"}, value = "Search for Banned Players", nickname = "Search Banned", produces = "applications/json")
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

        return  bannedRepository.findAll(where(BannedPlayerSpecification.withId(criteria.getId()))
                .and(BannedPlayerSpecification.withTarget(criteria.getTarget()))
                .and(BannedPlayerSpecification.withCreated(criteria.getCreated()))
                .and(BannedPlayerSpecification.withExpires(criteria.getExpires()))
                .and(BannedPlayerSpecification.withSource(criteria.getSource()))
                .and(BannedPlayerSpecification.withReason(criteria.getReason())), new PageRequest(page, size));

    }

}
