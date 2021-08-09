package com.restapi.football.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.restapi.football.assembler.GameModelAssembler;
import com.restapi.football.exception.GameNotFoundException;
import com.restapi.football.model.Game;
import com.restapi.football.repository.GameRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

@RestController
public class GameController {
    
    private final GameRepository repository;

    private final GameModelAssembler assembler;

    public GameController(GameRepository repository, GameModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/games")
    public CollectionModel<EntityModel<Game>> all() {
        
        List<EntityModel<Game>> game = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(game,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).all()).withSelfRel());
    }
    
    @PostMapping("/games")
    public ResponseEntity<?> newCoach(@RequestBody Game newGame) {
        
        EntityModel<Game> entityModel = assembler.toModel(repository.save(newGame));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("games/{id}")
    public EntityModel<Game> one(@PathVariable Long id) {

        Game game = repository.findById(id) 
            .orElseThrow(() -> new GameNotFoundException(id));

        return assembler.toModel(game);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<?> replaceGame(@RequestBody Game newGame, @PathVariable Long id) {
        
        Game updatedGame = repository.findById(id)
            .map(game -> {
                game.setHomeTeam(newGame.getHomeTeam());
                game.setAwayTeam(newGame.getAwayTeam());
                game.setHomePlayers(newGame.getHomePlayers());
                game.setAwayPlayers(newGame.getAwayPlayers());
                game.setPlayedDate(newGame.getPlayedDate());
                game.setHomeTeamGoals(newGame.getHomeTeamGoals());
                game.setAwayTeamGoals(newGame.getAwayTeamGoals());
                game.setHomeTeamRank(newGame.getHomeTeamRank());
                game.setAwayTeamRank(newGame.getAwayTeamRank());
                game.setHomeCoach(newGame.getHomeCoach());
                game.setAwayCoach(newGame.getAwayCoach());
                return repository.save(game);
            })
            .orElseGet(() -> {
                newGame.setId(id);
                return repository.save(newGame);
            });

        EntityModel<Game> entityModel = assembler.toModel(updatedGame);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
