package net.glowstone.webservices.controller;

import lombok.extern.slf4j.Slf4j;
import net.glowstone.webservices.dto.OnlinePlayers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/detail")
public class DetailController {

    @RequestMapping(value = "/onlinePlayers", method = RequestMethod.GET)
    public OnlinePlayers getOnlinePlayers() {
        return new OnlinePlayers();
    }

}
