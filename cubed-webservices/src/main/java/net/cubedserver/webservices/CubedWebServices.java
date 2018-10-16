package net.cubedserver.webservices;

import net.cubedserver.webservices.dto.Account;
import net.cubedserver.webservices.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * WebServices entry point for the cubed server.
 *
 * @author jdesive
 */
@EnableAsync
@SpringBootApplication
public class CubedWebServices {

    public static void main(String[] args) {
        SpringApplication.run(CubedWebServices.class, args);
    }

    // TODO Remove when we have a proper way to load accounts
    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
        return arg0 -> accountRepository.save(new Account(1L, "cubed", new BCryptPasswordEncoder().encode("cubed"), new Date()));
    }

}
