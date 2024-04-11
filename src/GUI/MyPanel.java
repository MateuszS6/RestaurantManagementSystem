package GUI;

import JDBC.DBConnection;

import javax.swing.*;

public abstract class MyPanel {
    private final String title;
    private final DBConnection connection;

    public MyPanel(String title, DBConnection connection) {
        this.title = title;
        this.connection = connection;
    }

    public abstract JPanel getMainPanel();

    public String getTitle() {
        return title;
    }

    public DBConnection getConnection() {
        return connection;
    }
}
