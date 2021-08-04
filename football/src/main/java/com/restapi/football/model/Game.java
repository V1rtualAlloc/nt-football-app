package com.restapi.football.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "homeTeamId")
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "awayTeamId")
    private Team awayTeam;

    @OneToMany(mappedBy = "team")
    private List<Player> homePlayers;

    @OneToMany(mappedBy = "team")
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

    public Game() { }

    public Game(Team homeTeam, Team awayTeam, List<Player> homePlayers, List<Player> awayPlayers, Coach homeCoach,
            Coach awayCoach, Date playedDate, String competition, String stage, Integer attendance,
            Integer homeTeamGoals, Integer awayTeamGoals, Integer homeTeamRank, Integer awayTeamRank) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
        this.homeCoach = homeCoach;
        this.awayCoach = awayCoach;
        this.playedDate = playedDate;
        this.competition = competition;
        this.stage = stage;
        this.attendance = attendance;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeamRank = homeTeamRank;
        this.awayTeamRank = awayTeamRank;
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
        return Objects.equals(this.id, game.id) && Objects.equals(this.homeTeam, game.homeTeam) && Objects.equals(this.awayTeam, game.awayTeam) && Objects.equals(this.playedDate, game.playedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.homeTeam, this.awayTeam, this.playedDate);
    }

    @Override
    public String toString() {
        return "Game{" + 
            "id=" + this.id +
            ", homeTeamRank=" + this.homeTeamRank +
            ", homeTeam='" + this.homeTeam + "\'" +
            ", awayTeamRank=" + this.awayTeamRank +
            ", awayTeam='" + this.awayTeam + "\'" +
            ", homePlayers=" + this.homePlayers.toString() +
            ", awayPlayers=" + this.awayPlayers.toString() +
            ", homeCoach=" + this.homeCoach +
            ", awayCoach=" + this.awayCoach +
            ", playedDate=" + this.playedDate + 
            ", competition=" + this.competition +
            ", stage=" + this.stage +
            ", attendance=" + this.attendance +
            ", homeTeamGoals=" + this.homeTeamGoals + 
            ", awayTeamGoals=" + this.awayTeamGoals +
            "}";
    }
}
