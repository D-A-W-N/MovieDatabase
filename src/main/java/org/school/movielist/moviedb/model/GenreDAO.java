package org.school.movielist.moviedb.model;

import org.school.movielist.moviedb.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    public GenreDAO() {
    }

    public static void addGenre(Genre genre) throws SQLException {
        String query = "INSERT INTO genres (name) VALUES ('"+genre.getName()+"')";
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateGenre(Genre genre) throws SQLException {
        String query = "UPDATE genres SET name = '"+genre.getName()+"' WHERE genre_id = "+genre.getGenre_id();
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteGenre(int genreId) throws SQLException {
        String query = "DELETE FROM genres WHERE genre_id = "+genreId;
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Genre getGenreById(int genreId) throws SQLException {
        String query = "SELECT * FROM genres WHERE genre_id = "+genreId;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            if (rs.next()) {
                Genre genre = new Genre();
                genre.setGenre_id(rs.getInt("genre_id"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setGenre_id(rs.getInt("genre_id"));
                genre.setName(rs.getString("name"));
                genres.add(genre);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return genres;
    }
}
