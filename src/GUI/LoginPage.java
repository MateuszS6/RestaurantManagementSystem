package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * The LoginPage class represents the login panel of the Lancaster's Restaurant Management GUI.
 * It allows users to input their username and password to access the system.
 * The class implements ActionListener, FocusListener, DocumentListener, and KeyListener interfaces
 * to handle user interactions and events.
 */
public class LoginPage extends MyPanel implements ActionListener, FocusListener, DocumentListener, KeyListener {
    /**
     * The main application instance.
     */
    private final Main main;

    /**
     * The echo character used for masking password input.
     */
    private final char echoChar;

    /**
     * The main JPanel associated with the login panel.
     */
    private JPanel mainPanel;

    /**
     * The text field for entering the username.
     */
    private JTextField usernameField;

    /**
     * The password field for entering the password.
     */
    private JPasswordField passwordField;

    /**
     * The button to clear the username and password fields.
     */
    private JButton clearButton;

    /**
     * The button to sign in to the system.
     */
    private JButton signInButton;

    /**
     * The label to display error messages.
     */
    private JLabel errorLabel;

    /**
     * The label to display powered by information.
     */
    private JLabel poweredByLabel;

    /**
     * Constructs a new LoginPage object with the specified main application instance.
     *
     * @param m the main application instance
     */
    public LoginPage(Main m) {
        main = m;
        echoChar = passwordField.getEchoChar();
        passwordField.setEchoChar((char) 0);
        errorLabel.setText(" ");
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        // To display default text hint in fields when empty
        usernameField.addFocusListener(this);
        passwordField.addFocusListener(this);

        // To enable/disable buttons based on field input being empty/not
        usernameField.getDocument().addDocumentListener(this);
        passwordField.getDocument().addDocumentListener(this);

        // To remove focus or sign in with keyboard
        usernameField.addKeyListener(this);
        passwordField.addKeyListener(this);

        // Buttons
        signInButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            usernameField.setForeground(Main.PRIMARY_TEXT_COLOUR);
            usernameField.setText("Username");
            passwordField.setForeground(Main.PRIMARY_TEXT_COLOUR);
            passwordField.setEchoChar((char) 0);
            passwordField.setText("Password");
            mainPanel.requestFocus();
            errorLabel.setText(" ");
        } else if (e.getSource() == signInButton) {
            signIn();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == usernameField && usernameField.getText().equals("Username")) {
            usernameField.setForeground(Color.BLACK);
            usernameField.setText("");
        }
        if (e.getSource() == passwordField && passwordField.getText().equals("Password")) {
            passwordField.setForeground(Color.BLACK);
            passwordField.setEchoChar(echoChar);
            passwordField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == usernameField && usernameField.getText().isEmpty()) {
            usernameField.setForeground(Main.PRIMARY_TEXT_COLOUR);
            usernameField.setText("Username");
        }
        if (e.getSource() == passwordField && passwordField.getText().isEmpty()) {
            passwordField.setForeground(Main.PRIMARY_TEXT_COLOUR);
            passwordField.setEchoChar((char) 0);
            passwordField.setText("Password");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateButtonState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateButtonState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateButtonState();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) signIn();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) mainPanel.requestFocus();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Updates the state of the clear and sign-in buttons based on the content of the username and password fields.
     * Enables the buttons if both fields are not empty and have black foreground color.
     * Otherwise, disables the buttons.
     */
    private void updateButtonState() {
        if (!usernameField.getText().isEmpty() && usernameField.getForeground() == Color.BLACK
                || !passwordField.getText().isEmpty() && passwordField.getForeground() == Color.BLACK) {
            clearButton.setEnabled(true);
            signInButton.setEnabled(true);
        } else {
            clearButton.setEnabled(false);
            signInButton.setEnabled(false);
        }
    }

    /**
     * Handles the sign-in action by validating the username and password entered by the user.
     * If the username is "Linda" and the password is "[p, a, s, s]", switches to the main interface panel.
     * Otherwise, displays an error message indicating incorrect username or password.
     */
    private void signIn() {
        String username = usernameField.getText();
        String password = Arrays.toString(passwordField.getPassword());
        if (username.equals("Linda") && password.equals("[p, a, s, s]"))
            main.switchPanel(new MainInterface(main, username));
        else errorLabel.setText("Incorrect username or password.");
    }
}
