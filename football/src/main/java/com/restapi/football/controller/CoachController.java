package com.restapi.football.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.restapi.football.assembler.CoachModelAssembler;
import com.restapi.football.exception.CoachNotFoundException;
import com.restapi.football.model.Coach;
import com.restapi.football.repository.CoachRepository;

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
public class CoachController {
    
    private final CoachRepository repository;

    private final CoachModelAssembler assembler;

    public CoachController(CoachRepository repository, CoachModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/coaches")
    public CollectionModel<EntityModel<Coach>> all() {
        
        List<EntityModel<Coach>> coach = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(coach,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoachController.class).all()).withSelfRel());
    }
    
    @PostMapping("/coaches")
    public ResponseEntity<?> newCoach(@RequestBody Coach newCoach) {
        
        EntityModel<Coach> entityModel = assembler.toModel(repository.save(newCoach));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("coaches/{id}")
    public EntityModel<Coach> one(@PathVariable Long id) {

        Coach coach = repository.findById(id) 
            .orElseThrow(() -> new CoachNotFoundException(id));

        return assembler.toModel(coach);
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<?> replacePlayer(@RequestBody Coach newCoach, @PathVariable Long id) {
        
        Coach updatedCoach = repository.findById(id)
            .map(coach -> {
                coach.setName(newCoach.getName());
                coach.setName(newCoach.getName());
                return repository.save(coach);
            })
            .orElseGet(() -> {
                newCoach.setId(id);
                return repository.save(newCoach);
            });

        EntityModel<Coach> entityModel = assembler.toModel(updatedCoach);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
