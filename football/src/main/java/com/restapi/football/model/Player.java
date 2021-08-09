package com.restapi.football.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name="teamId")
    private Team team;

    @ManyToMany(mappedBy="homePlayers")
    private List<Game> homeGames;

    @ManyToMany(mappedBy="awayPlayers")
    private List<Game> awayGames;
    
    private Integer factor;

    public Player() { }

    public Player(String firstName, String lastName, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        if (this.firstName.isEmpty()) {
            return this.lastName;
        }
        
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // public List<Game> getHomeGames() {
    //     return homeGames;
    // }

    // public void setHomeGames(List<Game> homeGames) {
    //     this.homeGames = homeGames;
    // }

    // public List<Game> getAwayGames() {
    //     return awayGames;
    // }

    // public void setAwayGames(List<Game> awayGames) {
    //     this.awayGames = awayGames;
    // }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
    return this.lastName;
    }
    
    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof Player)) {
            return false;
        }

        Player player = (Player) o;
        return Objects.equals(this.id, player.id) && Objects.equals(this.firstName, player.firstName) && Objects.equals(this.lastName, player.lastName) && Objects.equals(this.team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.team);
    }

    @Override
    public String toString() {
        return "Player{" + 
            "id=" + this.id +
            ", firstName='" + this.firstName + "\'" +
            ", lastName='" + this.lastName + "\'" +
            ", team=" + this.team +
            // ", homeGames=" + this.homeGames + 
            // ", awayGames=" + this.awayGames + 
            "}";
    }
}

