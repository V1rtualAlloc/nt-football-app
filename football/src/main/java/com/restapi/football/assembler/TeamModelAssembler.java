package com.restapi.football.assembler;

import com.restapi.football.controller.TeamController;
import com.restapi.football.model.Team;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TeamModelAssembler implements RepresentationModelAssembler<Team, EntityModel<Team>> {

    @Override
    public EntityModel<Team> toModel(Team player) {

        return EntityModel.of(player,
            linkTo(methodOn(TeamController.class).one(player.getId())).withSelfRel(),
            linkTo(methodOn(TeamController.class).all()).withRel("teams"));
    }
}
