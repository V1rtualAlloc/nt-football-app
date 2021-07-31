package com.restapi.football.dbo;

import com.restapi.football.model.Player;
import com.restapi.football.repository.PlayerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initPlayerDatabase(PlayerRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Player("Luka", "Modric", "Croatia")));
            log.info("Preloading " + repository.save(new Player("Harry", "Kane", "England")));
        };
    }
}
