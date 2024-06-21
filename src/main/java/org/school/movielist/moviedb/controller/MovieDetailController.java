package org.school.movielist.moviedb.controller;

import java.sql.Date;
import java.sql.SQLException;

import org.school.movielist.moviedb.Main;
import org.school.movielist.moviedb.model.Actor;
import org.school.movielist.moviedb.model.Genre;
import org.school.movielist.moviedb.model.Movie;
import org.school.movielist.moviedb.model.MovieDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MovieDetailController {
    public Main root_main;
    public ScrollPane detail_scroll_pane;
    public GridPane detail_grid_pane;

    @FXML
    public void initialize (int id) throws SQLException, ClassNotFoundException {
        Button backButton = new Button("Zurück");
        backButton.setOnAction(event -> {
            root_main.showMoviesView();
        });
        detail_grid_pane.add(backButton,0,0);
        getMoviebyId(id);
    }

    private void getMoviebyId(int id) {
        try {

            Movie movie = MovieDAO.getMovieById(id);

            ImageView poster_view = new ImageView();
            assert movie != null;
            Image poster = new Image(movie.getPoster_url());
            poster_view.setImage(poster);
            poster_view.setFitHeight(600);
            poster_view.setFitWidth(400);
            poster_view.getStyleClass().add("movie__poster");
            poster_view.setId(String.valueOf(movie.getMovie_id()));

            detail_grid_pane.add(poster_view,1,1);

            GridPane movie_grid_pane = new GridPane();
            movie_grid_pane.setHgap(10);
            movie_grid_pane.setVgap(10);
            movie_grid_pane.setPadding(new javafx.geometry.Insets(10,10,10,10));
            movie_grid_pane.getStyleClass().add("movie__details");
            Text title = new Text(movie.getTitle());
            title.getStyleClass().add("movie__title");
            movie_grid_pane.add(title,0,0);

            Date release_date = movie.getRelease_date();
            Text release_date_string = new Text(release_date.toString());
            release_date_string.getStyleClass().add("movie__release_date");
            movie_grid_pane.add(release_date_string,0,1);

            Text description = new Text(movie.getDescription());
            description.setWrappingWidth(590);
            description.getStyleClass().add("movie__description");
            movie_grid_pane.add(description,0,2);

            // Genre List
            Text genre_title = new Text("Genres:");
            genre_title.getStyleClass().add("movie__genre_title");
            movie_grid_pane.add(genre_title,0,3);
            StringBuilder genres = new StringBuilder();
            Integer genre_count = 0;
            for (Genre genre : movie.getGenres()) {

                genres.append(genre.getName());
                if (genre_count < movie.getGenres().size() - 1) {
                    genres.append(", ");
                }
                genre_count++;
            }
            Text genre_list = new Text(genres.toString());
            genre_list.getStyleClass().add("movie__genre_list");
            movie_grid_pane.add(genre_list,0,4);

            //Actor List
            Text actor_title = new Text("Actors:");
            actor_title.getStyleClass().add("movie__actor_title");
            movie_grid_pane.add(actor_title,0,5);
            StringBuilder actors = new StringBuilder();
            Integer actor_count = 0;
            for (Actor actor : movie.getActors()) {
                actors.append(actor.getFirstname()).append(" ").append(actor.getLastname());
                if (actor_count < movie.getActors().size() - 1) {
                    actors.append(", ");
                }
                actor_count++;
            }
            Text actor_list = new Text(actors.toString());
            actor_list.setWrappingWidth(590);
            actor_list.getStyleClass().add("movie__actor_list");
            movie_grid_pane.add(actor_list,0,6);
            


            detail_grid_pane.add(movie_grid_pane, 2,1);


        }catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void setMain(Main main) {
        root_main = main;
    }
}
