package net.cubedserver.webservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/dashboard";
    }

    @GetMapping("/banned")
    public String banned() {
        return "/banned";
    }

    @GetMapping("/whitelist")
    public String whitelist() {
        return "/whitelist";
    }

    @GetMapping("/operators")
    public String operators() {
        return "/operators";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "/error/404";
    }

    @GetMapping("/500")
    public String error500() {
        return "/error/500";
    }

}
