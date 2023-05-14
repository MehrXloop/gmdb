package com.galvanize.gmdb.gmdb.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.gmdb.gmdb.model.Reviewer;
import com.galvanize.gmdb.gmdb.repositories.ReviewerRepository;

@RestController
@RequestMapping("/reviewers")
public class ReviewerController {
    @Autowired
    private ReviewerRepository reviewerRepository;

    @GetMapping("/{id}")
    public Optional<Reviewer> getMovieById(@PathVariable int id) {
        return reviewerRepository.findById(id);
    }

    @PostMapping("")
    public void addReviewer(@RequestBody Reviewer reviewer){
        reviewer.setNumberOfReviews(0);
        reviewer.setDateJoined(Date.valueOf(LocalDate.now()));
           reviewerRepository.save(reviewer);
    }
}
