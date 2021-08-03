package com.restapi.football.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GamePlayer {
    
    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    private Boolean starter;
    
    public GamePlayer() { }
    
    public GamePlayer(Player player, Boolean starter) {
        this.player = player;
        this.starter = starter;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getStarter() {
        return starter;
    }

    public void setStarter(Boolean starter) {
        this.starter = starter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof GamePlayer)) {
            return false;
        }

        GamePlayer gamePlayer = (GamePlayer) o;
        return Objects.equals(this.id, gamePlayer.id) && Objects.equals(this.player, gamePlayer.player) && Objects.equals(this.starter, gamePlayer.starter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.player, this.starter);
    }

    @Override
    public String toString() {
        return "GamePlayer{" + 
            "id=" + this.id +
            ", player='" + this.player + "\'" +
            ", starter=" + this.starter + 
            "}";
    }
}
