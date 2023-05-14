package com.galvanize.gmdb.gmdb.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Table(name="movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year_released;
    private String genre;
    private int runtime;

    @OneToMany(mappedBy="movie",targetEntity=Review.class,cascade = CascadeType.ALL)
    private List<Review> reviews;
   
    public List<Review> getReviews() {
        return reviews;
    }


    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    //empty constructor
    public Movie(){

    }
    

    //constructor
    public Movie(int id, String title, int year_released, String genre, int runtime) {
        this.id = id;
        this.title = title;
        this.year_released = year_released;
        this.genre = genre;
        this.runtime = runtime;
    }
    
    //constructor without id
    public Movie(String title, int year_released, String genre, int runtime) {
        this.title = title;
        this.year_released = year_released;
        this.genre = genre;
        this.runtime = runtime;
    }

    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear_released() {
        return year_released;
    }
    public void setYear_released(int year_released) {
        this.year_released = year_released;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


}
