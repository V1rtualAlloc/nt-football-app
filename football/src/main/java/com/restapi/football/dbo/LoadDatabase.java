package com.restapi.football.dbo;

import com.restapi.football.model.Player;
import com.restapi.football.model.Team;
import com.restapi.football.repository.PlayerRepository;
import com.restapi.football.repository.TeamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, PlayerRepository playerRepository) {

        return args -> {
            log.info("Preloading " + playerRepository.save(new Player("Luka", "Modric", teamRepository.save(new Team("Croatia")))));
            log.info("Preloading " + playerRepository.save(new Player("Harry", "Kane", teamRepository.save(new Team("England")))));
        };
    }
}
