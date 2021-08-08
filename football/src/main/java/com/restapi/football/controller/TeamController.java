package com.restapi.football.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.restapi.football.assembler.TeamModelAssembler;
import com.restapi.football.exception.TeamNotFoundException;
import com.restapi.football.model.Team;
import com.restapi.football.repository.TeamRepository;

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
public class TeamController {
    
    private final TeamRepository repository;

    private final TeamModelAssembler assembler;

    public TeamController(TeamRepository repository, TeamModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/teams")
    public CollectionModel<EntityModel<Team>> all() {
        
        List<EntityModel<Team>> teams = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(teams,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeamController.class).all()).withSelfRel());
    }
    
    @PostMapping("/teams")
    public ResponseEntity<?> newTeam(@RequestBody Team newTeam) {
        
        EntityModel<Team> entityModel = assembler.toModel(repository.save(newTeam));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("teams/{id}")
    public EntityModel<Team> one(@PathVariable Long id) {

        Team team = repository.findById(id) 
            .orElseThrow(() -> new TeamNotFoundException(id));

        return assembler.toModel(team);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<?> replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {
        
        Team updatedTeam = repository.findById(id)
            .map(team -> {
                team.setName(newTeam.getName());
                return repository.save(team);
            })
            .orElseGet(() -> {
                newTeam.setId(id);
                return repository.save(newTeam);
            });

        EntityModel<Team> entityModel = assembler.toModel(updatedTeam);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
