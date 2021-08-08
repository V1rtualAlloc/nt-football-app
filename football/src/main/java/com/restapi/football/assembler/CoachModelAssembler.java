package com.restapi.football.assembler;

import com.restapi.football.controller.CoachController;
import com.restapi.football.model.Coach;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CoachModelAssembler implements RepresentationModelAssembler<Coach, EntityModel<Coach>> {
    
    @Override
    public EntityModel<Coach> toModel(Coach coach) {

        return EntityModel.of(coach,
            linkTo(methodOn(CoachController.class).one(coach.getId())).withSelfRel(),
            linkTo(methodOn(CoachController.class).all()).withRel("coach"));
    }
}