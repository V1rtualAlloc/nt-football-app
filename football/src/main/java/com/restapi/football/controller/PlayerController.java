package com.restapi.football.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.restapi.football.assembler.PlayerModelAssembler;
import com.restapi.football.exception.PlayerNotFoundException;
import com.restapi.football.model.Player;
import com.restapi.football.repository.PlayerRepository;

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
public class PlayerController {
    
    private final PlayerRepository repository;

    private final PlayerModelAssembler assembler;

    public PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/players")
    public CollectionModel<EntityModel<Player>> all() {
        
        List<EntityModel<Player>> players = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(players,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlayerController.class).all()).withSelfRel());
    }
    
    @PostMapping("/players")
    public ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) {
        
        EntityModel<Player> entityModel = assembler.toModel(repository.save(newPlayer));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("players/{id}")
    public EntityModel<Player> one(@PathVariable Long id) {

        Player player = repository.findById(id) 
            .orElseThrow(() -> new PlayerNotFoundException(id));

        return assembler.toModel(player);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<?> replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
        
        Player updatedPlayer = repository.findById(id)
            .map(player -> {
                player.setName(newPlayer.getName());
                player.setTeam(newPlayer.getTeam());
                // player.setHomeGames(newPlayer.getHomeGames());
                // player.setAwayGames(newPlayer.getAwayGames());
                return repository.save(player);
            })
            .orElseGet(() -> {
                newPlayer.setId(id);
                return repository.save(newPlayer);
            });

        EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
