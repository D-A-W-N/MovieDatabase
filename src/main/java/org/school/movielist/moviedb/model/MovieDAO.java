package org.school.movielist.moviedb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.school.movielist.moviedb.util.DBUtil;

public class MovieDAO {
    public static List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setPoster_url(rs.getString("poster_url"));
                movie.setDescription(rs.getString("description"));
                movie.setRelease_date(rs.getDate("release_date"));
                movie.setGenres(getGenres(rs.getInt("movie_id")));
                movie.setActors(getActors(rs.getInt("movie_id")));
                movies.add(movie);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public static Movie getMovieById(int id) throws SQLException {
        String query = "SELECT * FROM movies WHERE movie_id = "+id;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            if (rs.next()) {
                Movie movie = new Movie();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setPoster_url(rs.getString("poster_url"));
                movie.setDescription(rs.getString("description"));
                movie.setRelease_date(rs.getDate("release_date"));
                movie.setGenres(getGenres(rs.getInt("movie_id")));
                movie.setActors(getActors(rs.getInt("movie_id")));
                return movie;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void addMovie(Movie movie) throws SQLException {
        //Declare a INSERT statement
        String insertStmt = "INSERT INTO movies\n" +
                        "(title, poster_url, description, release_date)\n" +
                        "VALUES\n" +
                        "('"+movie.getTitle()+"', '"+movie.getPoster_url()+"','"+movie.getDescription()+"', '"+movie.getRelease_date()+"');\n";
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMovie(Movie movie) throws SQLException {
        //Declare a INSERT statement
        String updateStmt =
                "BEGIN\n" +
                        "UPDATE movies\n" +
                        "(title, poster_url, description, release_date)\n" +
                        "SET\n" +
                        "(title = '"+movie.getTitle()+"', poster_url = '"+movie.getPoster_url()+"', description = '"+movie.getDescription()+"', release_date = '"+movie.getRelease_date()+"');\n" +
                        "END;";
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMovie(int id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM movies\n" +
                        "         WHERE movie_id ="+ id +";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }

    }

    public static void addGenre(int genre_id, int movie_id) {
        String query = "INSERT INTO movie_genres (genre_id, movie_id) VALUES ("+genre_id+", "+movie_id+")";

        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Genre> getGenres(int movie_id) {
        String query = "SELECT * FROM movie_genres WHERE movie_id = "+movie_id;
        List<Genre> genres = new ArrayList<>();
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Genre genre = GenreDAO.getGenreById(rs.getInt("genre_id"));

                genres.add(genre);
            }

            return genres;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addActor(int actor_id, int movie_id) {
        String query = "INSERT INTO movie_actors (actor_id, movie_id) VALUES ("+actor_id+", "+movie_id+")";

        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Actor> getActors(int movie_id) {
        String query = "SELECT * FROM movie_actors WHERE movie_id = "+movie_id;
        List<Actor> actors = new ArrayList<>();
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Actor actor = ActorDAO.getActorById(rs.getInt("actor_id"));
                actors.add(actor);
            }

            return actors;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Movie> getMoviesByGenre(int genre_id) throws ClassNotFoundException {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movie_genres WHERE genre_id = "+genre_id;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Movie movie = getMovieById(rs.getInt("movie_id"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }
}
