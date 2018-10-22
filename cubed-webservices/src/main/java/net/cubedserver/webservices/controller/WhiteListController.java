package net.cubedserver.webservices.controller;

import io.swagger.annotations.ApiOperation;
import net.cubedserver.webservices.dto.OpPlayer;
import net.cubedserver.webservices.dto.WhiteListPlayer;
import net.cubedserver.webservices.exception.InvalidOpPlayerException;
import net.cubedserver.webservices.exception.InvalidWhiteListPlayerException;
import net.cubedserver.webservices.repositories.criteria.WhiteListPlayerCriteria;
import net.cubedserver.webservices.repositories.specification.WhiteListPlayerSpecification;
import net.cubedserver.webservices.repositories.WhiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Represents the /whitelist endpoint
 *
 * @author jdesive
 */
@RestController
@RequestMapping("/api/whitelist")
public class WhiteListController {

    private final WhiteListRepository whiteListRepository;

    @Autowired
    public WhiteListController(WhiteListRepository whiteListRepository) {
        this.whiteListRepository = whiteListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(tags = {"WhiteList"}, value = "Search for WhiteListed Players", nickname = "Search WhiteList", produces = "applications/json")
    public Page<WhiteListPlayer> findWithCriteria(@RequestParam(name = "page", required = false, defaultValue = "0")int page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "15")int size,
                                                  @RequestParam(name = "id", required = false)Long id,
                                                  @RequestParam(name = "name", required = false)String name,
                                                  @RequestParam(name = "uuid", required = false)String uuid) {

        WhiteListPlayerCriteria criteria = new WhiteListPlayerCriteria(id, name, uuid);

        if(criteria.isEmpty())
            return whiteListRepository.findAll(new PageRequest(page, size));

        return whiteListRepository.findAll(where(WhiteListPlayerSpecification.withId(criteria.getId()))
                .and(WhiteListPlayerSpecification.withName(criteria.getName()))
                .and(WhiteListPlayerSpecification.withUuid(criteria.getUuid())), new PageRequest(page, size));

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(tags = {"WhiteList"}, value = "Add a WhiteListed Player", nickname = "Add Whitelisted Player", produces = "applications/json")
    public WhiteListPlayer addOpPlayer(@RequestBody WhiteListPlayer whiteListPlayer) {
        if(!whiteListPlayer.isValid()){
            throw new InvalidWhiteListPlayerException(whiteListPlayer);
        }
        return whiteListRepository.save(whiteListPlayer);
    }

}
