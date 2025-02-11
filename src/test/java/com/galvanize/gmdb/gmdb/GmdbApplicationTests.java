package com.galvanize.gmdb.gmdb;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.gmdb.controller.MovieController;
import com.galvanize.gmdb.gmdb.controller.ReviewerController;
import com.galvanize.gmdb.gmdb.model.Movie;
import com.galvanize.gmdb.gmdb.model.Review;
import com.galvanize.gmdb.gmdb.model.Reviewer;
import com.galvanize.gmdb.gmdb.repositories.MovieRepository;
import com.galvanize.gmdb.gmdb.repositories.ReviewerRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class GmdbApplicationTests {

    private MockMvc mvc;
    private MockMvc mvc2;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ReviewerRepository reviewerRepository;
    // @Mock
    // private Rev;

    @InjectMocks
    private MovieController movieController;

    @InjectMocks
    private ReviewerController reviewerController;

    private JacksonTester<Movie> jsonMovie;
    private JacksonTester<Reviewer> jsonReviewer;

    private JacksonTester<List<Movie>> jsonMovies;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(movieController).build();
        mvc2 = MockMvcBuilders.standaloneSetup(reviewerController).build();

    }
    // Stories for this project are shown below in order of value, with the highest
    // value listed first.
    // This microservice will contain the CRUD operations required to interact with
    // the GMDB movie database.
    // Other functionality (e.g. user authentication) is hosted in other
    // microservices.
    //
    // 1. As a user
    // I can GET a list of movies from GMDB that includes Movie ID | Movie Title |
    // Year Released | Genre | Runtime
    // so that I can see the list of available movies.

    @Test
    public void canGetAllMovies() throws Exception {
        Movie movie1 = new Movie(1, "movie 1", 2002, "action", 120);
        Movie movie2 = new Movie(2, "movie 2", 1999, "thriller", 160);
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(movie1);
        movieList.add(movie2);
        when(movieRepository.findAll()).thenReturn(movieList);
        mvc.perform(get("/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMovies.write(movieList).getJson()));
    }

    //
    // 2. As a user
    // I can provide a movie ID and get back the record shown in story 1, plus a
    // list of reviews that contains Review ID | Movie ID | Reviewer ID | Review
    // Text | DateTime last modified
    // so that I can read the reviews for a movie.
    @Test
    public void canGetMovieWithReviews() throws Exception{
        Movie movie = new Movie(1, "movie 1", 2002, "action", 120);
        Reviewer reviewer = new Reviewer(1, "mehr",Date.valueOf(LocalDate.now()) , 3);
        Review review1 = new Review(1, "shfdlsdjf", Date.valueOf(LocalDate.now()), movie, reviewer);
        Review review2 = new Review(2, "shfdlsdjffsdfs", Date.valueOf(LocalDate.now()), movie, reviewer);

        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        movie.setReviews(reviews);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        mvc.perform(get("/movies/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonMovie.write(movie).getJson()));

    }

    //
    // 3. As a user
    // I can provide a Reviewer ID and get back a record that contains Reivewer ID |
    // Username | Date Joined | Number of Reviews
    // so that I can see details about a particular reviewer.
    //
    @Test
    public void canGetReviewerRecord() throws Exception {
        Reviewer reviewer1 = new Reviewer(1, "mehr", Date.valueOf(LocalDate.now()), 10);
        when(reviewerRepository.findById(1)).thenReturn(Optional.of(reviewer1));
        mvc2.perform(get("/reviewers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonReviewer.write(reviewer1).getJson()));
    }

    // 4. As a user
    // I can register as a reviewer by providing my Username. (Reviewer ID should be
    // autogenerated)
    // So that I can start reviewing movies.

    @Test
    public void canAddAReviewer() throws Exception{
        Reviewer reviewer = new Reviewer("mehr");
        mvc2.perform(post("/reviewers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonReviewer.write(reviewer).getJson()))
        .andExpect(status().isOk());
    }

    //
    // 5. As a reviewer
    // I can post a review by providing my reviewer ID, a movie ID and my review
    // text. (Review ID should be autogenerated)
    // So that I can share my opinions with others.
    //
    // 6. As a reviewer
    // I can delete a review by providing my reviewer ID and a review ID
    // So that I can remove reviews I no longer wish to share.


    //
    // 7. As a reviewer
    // I can update a review by providing my reviewer ID, a movie ID and my review
    // text.
    // So that I can modify the opinion I'm sharing with others.
    //
    // 8. As an Admin
    // I can add a new movie to the database by providing the data listed in story 1
    // (Movie ID should be autogenerated)
    // so that I can share new movies with the users.

    @Test
    public void canAddANewMovie() throws Exception {
        Movie movie = new Movie("new movie", 2002, "horror", 130);
        mvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMovie.write(movie).getJson()))
                .andExpect(status().isOk());
    }
    // 9. As an Admin
    // I can add update the entry for a movie by providing the data listed in Story
    // 1.
    // so that I can correct errors in previously uploaded movie entries.
    //

    @Test
    public void canUpdateAMovie() throws Exception {
        Movie movie = new Movie("new movie", 2002, "horror", 130);
        mvc.perform(put("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMovie.write(movie).getJson()))
                .andExpect(status().isOk());
    }

    // 10. As an admin
    // I can delete a movie by providing a movie ID
    // so that I can remove movies I no longer wish to share.

    @Test
    public void canDeleteMovie() throws Exception {
        mvc.perform(delete("/movies/1"))
                .andExpect(status().isOk());
    }
    //
    // 11. As an admin
    // I can impersonate a reviewer and do any of the things they can do
    // so that I can help confused reviewers.

    @Test
    public void contextLoads() {
    }

}