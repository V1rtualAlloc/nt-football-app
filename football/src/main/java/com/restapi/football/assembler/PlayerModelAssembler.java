package com.restapi.football.assembler;

import com.restapi.football.controller.PlayerController;
import com.restapi.football.model.Player;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PlayerModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {
    
    @Override
    public EntityModel<Player> toModel(Player player) {

        return EntityModel.of(player,
            linkTo(methodOn(PlayerController.class).one(player.getId())).withSelfRel(),
            linkTo(methodOn(PlayerController.class).all()).withRel("players"));
    }
}
