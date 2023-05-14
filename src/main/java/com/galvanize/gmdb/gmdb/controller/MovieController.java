package com.galvanize.gmdb.gmdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.gmdb.gmdb.model.Movie;
import com.galvanize.gmdb.gmdb.repositories.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {
     @Autowired
    private MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Movie> getMovieById(@PathVariable int id){
        return movieRepository.findById(id);
    }
    

    @PostMapping("")
    public void saveMovie(@RequestBody Movie movie)
    {
        movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){
       movieRepository.deleteById(id);
    }

    @PutMapping("")
    public void updateMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
    }

    

}
