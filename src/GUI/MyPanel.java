package GUI;

import JDBC.DBConnection;

import javax.swing.*;

public abstract class MyPanel {
    private final String title;
    private DBConnection connection;

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

    public void reconnect() {
        connection = new DBConnection();
        System.out.println("Connection refreshed");
    }
}
