package GUI;

import JDBC.DBConnection;

import javax.swing.*;

public abstract class MyPanel {
    private String title;
    private DBConnection connection;

    public MyPanel(String title, DBConnection connection) {
        this.title = title;
        this.connection = connection;
    }

    public MyPanel() {}

    public abstract JPanel getMainPanel();

    protected String getTitle() {
        return title;
    }

    protected DBConnection getConnection() {
        return connection;
    }

    protected void connect() {
        connection = new DBConnection();
    }
}
