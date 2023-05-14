package com.galvanize.gmdb.gmdb.model;





import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Table(name="reviewers")
@Entity
public class Reviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private Date dateJoined;
    private int numberOfReviews;

    public Reviewer() {
    }

    public Reviewer(int id, String username, Date dateJoined, int numberOfReviews) {
        this.id = id;
        this.username = username;
        this.dateJoined = dateJoined;
        this.numberOfReviews = numberOfReviews;
    }

     //constructor for username

     public Reviewer(String username){
        this.username = username;
     }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date joiningDate) {
        this.dateJoined = joiningDate;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
}
