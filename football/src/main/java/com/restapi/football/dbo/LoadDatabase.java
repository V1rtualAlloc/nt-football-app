package com.restapi.football.dbo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.restapi.football.model.Coach;
import com.restapi.football.model.Game;
import com.restapi.football.model.Player;
import com.restapi.football.model.Team;
import com.restapi.football.repository.CoachRepository;
import com.restapi.football.repository.GameRepository;
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
    CommandLineRunner initDatabase(GameRepository gameRepository, CoachRepository coachRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {

        // teams
        List<Team> teams = Arrays.asList(
            new Team("Croatia"), 
            new Team("England"));

        for (Team team : teams) {
            teamRepository.save(team);
        }

        // home players
        List<Player> homePlayers = Arrays.asList(
            new Player("Luka", "Modric", teams.get(0)),  
            new Player("Ivan", "Rakitic", teams.get(0)));
        
        for (Player player : homePlayers) {
            playerRepository.save(player);
        }

        // away players
        List<Player> awayPlayers = Arrays.asList(
            new Player("Jack", "Vardy", teams.get(1)),  
            new Player("Harry", "Kane", teams.get(1)));
        
        for (Player player : awayPlayers) {
            playerRepository.save(player);
        }

        // coaches
        List<Coach> coaches = Arrays.asList(
            new Coach("Zlatko Dalic", 3),  
            new Coach("Gareth Southgate", 1));

        for (Coach coach : coaches) {
            coachRepository.save(coach);
        }

        // games
        List<Game> games = Arrays.asList(
            new Game(teams.get(0), teams.get(1), homePlayers, 
            awayPlayers, new Date(), 1, 
            0, 10, 12,
            coaches.get(0), coaches.get(1), "1/2 finals", 
            43532, "Euro 2020")
        );

        for (Game game : games) {
            gameRepository.save(game);
        }

        return args -> {
            // log.info("Preloading " + playerRepository.save(new Player("Luka", "Modric", teamRepository.save(new Team("Croatia")))));
            // log.info("Preloading " + playerRepository.save(new Player("Harry", "Kane", teamRepository.save(new Team("England")))));
            for (Team team : teams) {
                log.info("Preloading " + team);
            }

            for (Player player : homePlayers) {
                log.info("Preloading " + player);
            }

            for (Player player : awayPlayers) {
                log.info("Preloading " + player);
            }

            for (Coach coach : coaches) {
                log.info("Preloading " + coach);
            }

            for (Game game : games) {
                log.info("Preloading " + game);
            }
        };
    }
}
