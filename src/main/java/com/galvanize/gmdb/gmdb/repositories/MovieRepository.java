package com.galvanize.gmdb.gmdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galvanize.gmdb.gmdb.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
}
