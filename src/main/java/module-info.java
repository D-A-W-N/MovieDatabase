module org.school.movielist.moviedb {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires java.sql.rowset;
    requires org.mariadb.jdbc;

    opens org.school.movielist.moviedb to javafx.fxml;
    opens org.school.movielist.moviedb.controller to javafx.fxml;
    opens org.school.movielist.moviedb.model to javafx.fxml;
    opens org.school.movielist.moviedb.util to javafx.fxml;

    exports org.school.movielist.moviedb;
}