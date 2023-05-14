package com.galvanize.gmdb.gmdb.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Table(name="reviews")
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reviewText;
    private Date dateTimeLastModified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="movie_id",referencedColumnName="id",nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="reviewer_id",referencedColumnName="id",nullable = false)
    private Reviewer reviewer;

    // empty constructor
    public Review() {

    }

    // constructor
    public Review(int id, String reviewText, Date dateTimeLastModified, Movie movie, Reviewer reviewer) {
        this.id = id;
        this.reviewText = reviewText;
        this.dateTimeLastModified = dateTimeLastModified;
        this.movie = movie;
        this.reviewer = reviewer;
    }

   


    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getDateTimeLastModified() {
        return dateTimeLastModified;
    }

    public void setDateTimeLastModified(Date dateTimeLastModified) {
        this.dateTimeLastModified = dateTimeLastModified;
    }

}
