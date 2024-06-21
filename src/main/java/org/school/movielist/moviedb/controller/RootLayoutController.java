package org.school.movielist.moviedb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.school.movielist.moviedb.Main;
import org.school.movielist.moviedb.model.Movie;
import org.school.movielist.moviedb.model.MovieDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class RootLayoutController {
    private Main mainClass;
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    }
    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleAddMovie(ActionEvent actionEvent) {
        mainClass.showAddMovieView();
    }

    public void setMain(Main main) {
        mainClass = main;
    }

    public void handleShowMovies(ActionEvent actionEvent) {
        mainClass.showMoviesView();
    }

    public void handleShowMovie(ActionEvent actionEvent) throws SQLException {
        List<Movie> movies = MovieDAO.getAllMovies();
        Random rand = new Random();
        Movie movie = movies.get(rand.nextInt(movies.size()));

        mainClass.showMovieDetailView(movie.getMovie_id());
    }
}
