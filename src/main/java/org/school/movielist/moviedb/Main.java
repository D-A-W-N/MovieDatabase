package org.school.movielist.moviedb;

import java.io.IOException;
import java.sql.SQLException;

import org.school.movielist.moviedb.controller.MovieAddController;
import org.school.movielist.moviedb.controller.MovieController;
import org.school.movielist.moviedb.controller.MovieDetailController;
import org.school.movielist.moviedb.controller.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    //This is our PrimaryStage (It contains everything)
    public Stage primaryStage;
    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;
        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Movielist");
        //2) Initialize RootLayout
        this.initRootLayout();
        //3) Display the Movies View
        this.showMoviesView();
    }
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.
            //Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //Shows the movies operations view inside the root layout.
    public void showMoviesView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MoviesView.fxml"));
            ScrollPane moviesView = loader.load();
            MovieController controller = loader.getController();
            controller.setMain(this);
            moviesView.getStylesheets().add(String.valueOf(getClass().getResource("css/movielist.css")));
            rootLayout.setCenter(moviesView);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showMovieDetailView(int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MovieDetailView.fxml"));
            ScrollPane movieDetailView = loader.load();
            movieDetailView.getStylesheets().add(String.valueOf(Main.class.getResource("css/movie.css")));
            MovieDetailController controller = loader.getController();
            controller.setMain(this);
            controller.initialize(id);
            rootLayout.setCenter(movieDetailView);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Shows the operations view inside the root layout.
    public void showAddMovieView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MovieAddView.fxml"));
            ScrollPane moviesAddView = loader.load();
            moviesAddView.getStylesheets().add(String.valueOf(Main.class.getResource("css/movie-add.css")));
            //Give the controller access to the main.
            MovieAddController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setCenter(moviesAddView);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}