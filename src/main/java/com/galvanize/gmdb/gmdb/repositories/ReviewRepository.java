package com.galvanize.gmdb.gmdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galvanize.gmdb.gmdb.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
