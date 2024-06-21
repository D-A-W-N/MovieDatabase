package org.school.movielist.moviedb.controller;

import java.sql.SQLException;
import java.util.List;

import org.school.movielist.moviedb.Main;
import org.school.movielist.moviedb.model.Movie;
import org.school.movielist.moviedb.model.MovieDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class MovieController {
    public Main root_main;
    public FlowPane poster_flow_pane;
    public FlowPane genre_flow_pane;
    public ScrollPane poster_scroll_pane;

    @FXML
    public void initialize () throws Exception, SQLException, ClassNotFoundException {
        getAllMovies();
    }


    @FXML
    private void getAllMovies() throws Exception, SQLException, ClassNotFoundException {
        try {
            List<Movie> movies = MovieDAO.getAllMovies();
            populateMovies(movies);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @FXML
    private void populateMovies(List<Movie> movies) throws Exception, SQLException, ClassNotFoundException , NullPointerException {
        try {
            movies.forEach(movie -> {
                StackPane poster_stack_pane = new StackPane();
                poster_stack_pane.getStyleClass().add("movielist__poster__stackpane");

                Label title_label = new Label(movie.getTitle());
                title_label.setWrapText(true);
                title_label.setMaxWidth(200);
                title_label.setAlignment(javafx.geometry.Pos.CENTER);
                title_label.getStyleClass().add("movielist__title");
                StackPane.setAlignment(title_label, javafx.geometry.Pos.BOTTOM_CENTER);
                

                ImageView poster_view = new ImageView();
                Image poster_image = new Image(movie.getPoster_url(), true);
                poster_view.setImage(poster_image);
                poster_view.setCache(true);
                poster_view.setFitHeight(300);
                poster_view.setFitWidth(200);
                poster_view.getStyleClass().add("movielist__poster");
                poster_view.setId(String.valueOf(movie.getMovie_id()));
                poster_view.setOnMouseClicked((event -> {
                    root_main.showMovieDetailView(movie.getMovie_id());
                }));

                poster_stack_pane.getChildren().addAll(poster_view, title_label);
                
                poster_flow_pane.getChildren().add(poster_stack_pane);
            });
            poster_flow_pane.setAlignment(javafx.geometry.Pos.CENTER);
            poster_scroll_pane.setContent(poster_flow_pane);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void setMain(Main main) {
        root_main = main;
    }
}
