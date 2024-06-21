package org.school.movielist.moviedb.model;

import org.school.movielist.moviedb.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
    public ActorDAO() {
    }

    public static void addActor(Actor actor) throws SQLException {
        String query = "INSERT INTO actors (firstname, lastname) VALUES ('"+actor.getFirstname()+"', '"+actor.getLastname()+"')";
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateActor(Actor actor) throws SQLException {
        String query = "UPDATE actors SET firstname = '"+actor.getFirstname()+"', lastname = '"+actor.getLastname()+"' WHERE actor_id = "+actor.getActor_id();
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteActor(int ActorId) throws SQLException {
        String query = "DELETE FROM actors WHERE actor_id = "+ActorId;
        try {
            DBUtil.dbExecuteUpdate(query);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Actor getActorById(int ActorId) throws SQLException {
        String query = "SELECT * FROM actors WHERE actor_id = "+ActorId;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            if (rs.next()) {
                Actor actor = new Actor();
                actor.setActor_id(rs.getInt("actor_id"));
                actor.setFirstname(rs.getString("firstname"));
                actor.setLastname(rs.getString("lastname"));
                return actor;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Actor> getAllActors() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actors";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setActor_id(rs.getInt("actor_id"));
                actor.setFirstname(rs.getString("firstname"));
                actor.setLastname(rs.getString("lastname"));
                actors.add(actor);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return actors;
    }
}

