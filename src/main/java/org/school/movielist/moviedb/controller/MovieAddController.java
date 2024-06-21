package org.school.movielist.moviedb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.school.movielist.moviedb.Main;
import org.school.movielist.moviedb.model.Movie;
import org.school.movielist.moviedb.model.MovieDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MovieAddController {

    public Main root_main;
    public ScrollPane movie_add_scroll_pane;
    public GridPane movie_add_grid_pane;
    public TextField result_area;

    public TextField title;
    public DatePicker release_date;
    public TextField poster_url;
    public TextArea description;
    public Button submit_button;

    @FXML
    private void handleSubmit(ActionEvent actionEvent) throws SQLException {
        try {
            Movie movie = new Movie();
            String poster;
            if (!Objects.equals(poster_url.getText(), "")) {
                poster = poster_url.getText();
            }else {
                poster = "/no-poster.jpg";
            }

            movie.setTitle(title.getText());
            movie.setRelease_date(Date.valueOf(release_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            movie.setPoster_url(poster);
            movie.setDescription(description.getText());

            MovieDAO.addMovie(movie);
        } catch (SQLException e) {
            result_area.setText(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setMain(Main main) {
        root_main = main;
    }
}
