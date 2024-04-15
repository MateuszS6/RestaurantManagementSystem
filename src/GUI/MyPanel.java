package GUI;

import JDBC.DBConnection;

import javax.swing.*;

public abstract class MyPanel {
    private String title;
    private DBConnection connection;

    // Used for content panels to set a title to display and the connection to use
    public MyPanel(String title, DBConnection connection) {
        this.title = title;
        this.connection = connection;
    }

    // Used by the login page and main interface that don't need a title or a connection
    public MyPanel() {}

    public abstract JPanel getMainPanel();

    protected String getTitle() {
        return title;
    }

    // All content panels can call this method to return the connection inherited from the main interface
    protected DBConnection getConnection() {
        return connection;
    }

    protected void connect() {
        connection = new DBConnection();
    }
}
