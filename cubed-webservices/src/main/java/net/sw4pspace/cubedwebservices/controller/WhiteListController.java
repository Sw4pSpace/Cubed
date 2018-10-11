package net.sw4pspace.cubedwebservices.controller;

import io.swagger.annotations.ApiOperation;
import net.sw4pspace.cubedwebservices.dto.WhiteListPlayer;
import net.sw4pspace.cubedwebservices.repositories.WhiteListRepository;
import net.sw4pspace.cubedwebservices.repositories.criteria.WhiteListPlayerCriteria;
import net.sw4pspace.cubedwebservices.repositories.specification.WhiteListPlayerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Represents the /whitelist endpoint
 *
 * @author jdesive
 */
@RestController
@RequestMapping("/whitelist")
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

}
