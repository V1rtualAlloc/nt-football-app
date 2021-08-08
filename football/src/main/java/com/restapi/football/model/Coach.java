package com.restapi.football.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coach {
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private Integer factor;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getFactor() {
        return factor;
    }
    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public Coach() { }

    public Coach(String name, Integer factor) {
        this.name = name;
        this.factor = factor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof Coach)) {
            return false;
        }

        Coach coach = (Coach) o;
        return Objects.equals(this.id, coach.id) && Objects.equals(this.name, coach.name) && Objects.equals(this.factor, coach.factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.factor);
    }

    @Override
    public String toString() {
        return "Coach{" + 
            "id=" + this.id +
            ", name='" + this.name + "\'" +
            ", factor=" + this.factor + 
            "}";
    }
}
