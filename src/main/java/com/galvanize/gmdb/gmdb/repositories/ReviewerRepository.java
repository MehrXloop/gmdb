package com.galvanize.gmdb.gmdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galvanize.gmdb.gmdb.model.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, Integer>{
    
}
