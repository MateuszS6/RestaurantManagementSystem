package GUI;

import JDBC.DBConnection;

import javax.swing.*;

/**
 * The MyPanel class is an abstract class representing a panel in the GUI application.
 * It provides common functionality and properties shared by different panels.
 */
public abstract class MyPanel {
    /**
     * The title of the panel, used for display purposes.
     */
    private String title;

    /**
     * The database connection associated with the panel.
     */
    private DBConnection connection;

    /**
     * Constructs a new MyPanel object with the specified title and database connection.
     * This constructor is used for content panels to set a title to display and the connection to use.
     *
     * @param title      the title of the panel
     * @param connection the database connection to use
     */
    public MyPanel(String title, DBConnection connection) {
        this.title = title;
        this.connection = connection;
    }

    /**
     * Constructs a new MyPanel object.
     * This constructor is used by the login page and main interface that don't need a title or a connection.
     */
    public MyPanel() {}

    /**
     * Returns the main JPanel associated with the panel.
     * Subclasses must implement this method to provide the main panel content.
     *
     * @return the main JPanel associated with the panel
     */
    public abstract JPanel getMainPanel();

    /**
     * Returns the title of the panel.
     *
     * @return the title of the panel
     */
    protected String getTitle() {
        return title;
    }

    /**
     * Returns the database connection associated with the panel.
     *
     * @return the database connection associated with the panel
     */
    protected DBConnection getConnection() {
        return connection;
    }

    /**
     * Establishes a new database connection.
     * This method is used to connect to the database when needed.
     */
    protected void connect() {
        connection = new DBConnection();
    }
}
