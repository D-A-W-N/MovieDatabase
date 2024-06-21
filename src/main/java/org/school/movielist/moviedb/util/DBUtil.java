package org.school.movielist.moviedb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
public class DBUtil {
    //Connection
    public static Connection connection = null;
    //Connection String
    private static final String connStr = "jdbc:mariadb://localhost:3306/movielist";
    //DB User
    private static final String dbUser = "root";
    //DB Password
    private static final String dbPassword = "BilzeBub";


    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Establish the Connection using Connection String
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(connStr,dbUser,dbPassword);
            //System.out.println("Mysql Connection Success!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try {
            //Connect to DB (Establish Connection)
            dbConnect();
            //System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = connection.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
