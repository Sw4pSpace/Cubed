package net.cubed.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * WebServices entry point for the cubed server.
 *
 * @author jdesive
 */
@EnableAsync
@SpringBootApplication
public class GlowWebServices {

    public static void main(String[] args) {
        SpringApplication.run(GlowWebServices.class, args);
    }

}
