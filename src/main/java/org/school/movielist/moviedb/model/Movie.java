package org.school.movielist.moviedb.model;

import java.sql.Date;
import java.util.List;

public class Movie {
    private int movie_id;
    private String title;
    private String poster_url;
    private String description;
    private Date release_date;

    private List<Genre> genres = null;

    private List<Actor> actors = null;

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
        return this.actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public int getMovie_id() {
        return this.movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_url() {
        return this.poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return this.release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return this.title + " ("+release_date.toLocalDate().getYear()+")";
    }
}
