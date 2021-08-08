package com.restapi.football.repository;

import com.restapi.football.model.Coach;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    
}