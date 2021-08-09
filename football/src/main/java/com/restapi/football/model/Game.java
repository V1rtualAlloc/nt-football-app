package com.restapi.football.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeTeamId")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeamId")
    private Team awayTeam;

    @ManyToMany
    @JoinTable(name="Home_Game_Player",
               joinColumns = @JoinColumn(name="GameId", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name="PlayerId", referencedColumnName = "id"))
    private List<Player> homePlayers;

    @ManyToMany
    @JoinTable(name="Away_Game_Player",
               joinColumns = @JoinColumn(name="GameId", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name="PlayerId", referencedColumnName = "id"))
    private List<Player> awayPlayers;
    
    @ManyToOne
    @JoinColumn(name = "homeCoachId")
    private Coach homeCoach;

    @ManyToOne
    @JoinColumn(name = "awayCoachId")
    private Coach awayCoach;

    private Integer homeTeamRank;
    private Integer awayTeamRank;
    private Date playedDate;
    private String competition;
    private String stage;
    private Integer attendance;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Team getHomeTeam() {
        return homeTeam;
    }
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }
    public Team getAwayTeam() {
        return awayTeam;
    }
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
    public List<Player> getHomePlayers() {
        return homePlayers;
    }
    public void setHomePlayers(List<Player> homePlayers) {
        this.homePlayers = homePlayers;
    }
    public List<Player> getAwayPlayers() {
        return awayPlayers;
    }
    public void setAwayPlayers(List<Player> awayPlayers) {
        this.awayPlayers = awayPlayers;
    }
    public Integer getHomeTeamRank() {
        return homeTeamRank;
    }
    public void setHomeTeamRank(Integer homeTeamRank) {
        this.homeTeamRank = homeTeamRank;
    }
    public Integer getAwayTeamRank() {
        return awayTeamRank;
    }
    public void setAwayTeamRank(Integer awayTeamRank) {
        this.awayTeamRank = awayTeamRank;
    }
    public Date getPlayedDate() {
        return playedDate;
    }
    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }
    public String getCompetition() {
        return competition;
    }
    public void setCompetition(String competition) {
        this.competition = competition;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public Integer getAttendance() {
        return attendance;
    }
    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }
    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }
    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }
    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
    public Coach getHomeCoach() {
        return homeCoach;
    }
    public void setHomeCoach(Coach homeCoach) {
        this.homeCoach = homeCoach;
    }
    public Coach getAwayCoach() {
        return awayCoach;
    }
    public void setAwayCoach(Coach awayCoach) {
        this.awayCoach = awayCoach;
    }

    public Game() { }

    public Game(Team homeTeam, Team awayTeam, List<Player> homePlayers, 
            List<Player> awayPlayers, Date playedDate, Integer homeTeamGoals, 
            Integer awayTeamGoals, Integer homeTeamRank, Integer awayTeamRank,
            Coach homeCoach, Coach awayCoach, String stage,
            Integer attendance, String competition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
        this.playedDate = playedDate;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeamRank = homeTeamRank;
        this.awayTeamRank = awayTeamRank;
        this.homeCoach = homeCoach;
        this.awayCoach = awayCoach;
        this.stage = stage;
        this.attendance = attendance;
        this.competition = competition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof Game)) {
            return false;
        }

        Game game = (Game) o;
        return Objects.equals(this.id, game.id) && Objects.equals(this.homeTeamRank, game.homeTeamRank) && Objects.equals(this.homeTeam, game.homeTeam) 
            && Objects.equals(this.awayTeamRank, game.awayTeamRank) && Objects.equals(this.awayTeam, game.awayTeam) 
            && Objects.equals(this.homePlayers, game.homePlayers) && Objects.equals(this.awayPlayers, game.awayPlayers) 
            && Objects.equals(this.homeCoach, game.homeCoach) && Objects.equals(this.awayCoach, game.awayCoach) 
            && Objects.equals(this.playedDate, game.playedDate) && Objects.equals(this.competition, game.competition) 
            && Objects.equals(this.stage, game.stage) && Objects.equals(this.attendance, game.attendance) 
            && Objects.equals(this.homeTeamGoals, game.homeTeamGoals) && Objects.equals(this.awayTeamGoals, game.awayTeamGoals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.homeTeamRank, this.homeTeam, this.awayTeamRank, 
            this.awayTeam, this.homePlayers, this.awayPlayers, this.homeCoach, 
            this.awayCoach, this.playedDate, this.competition, this.stage,  
            this.attendance, this.homeTeamGoals, this.awayTeamGoals);
    }

    @Override
    public String toString() {
        return "Game{" + 
            "id=" + this.id +
            ", homeTeamRank=" + this.homeTeamRank +
            ", homeTeam=" + this.homeTeam + 
            ", awayTeamRank=" + this.awayTeamRank +
            ", awayTeam=" + this.awayTeam + 
            ", homePlayers=" + this.homePlayers +
            ", awayPlayers=" + this.awayPlayers +
            ", homeCoach=" + this.homeCoach +
            ", awayCoach=" + this.awayCoach +
            ", playedDate=" + this.playedDate + 
            ", competition='" + this.competition + "\'" +
            ", stage='" + this.stage + "\'" +
            ", attendance=" + this.attendance +
            ", homeTeamGoals=" + this.homeTeamGoals + 
            ", awayTeamGoals=" + this.awayTeamGoals +
            "}";
    }
}
