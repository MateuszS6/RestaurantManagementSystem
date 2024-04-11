package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

public class LoginPage extends MyPanel implements ActionListener, FocusListener, DocumentListener {
    private final Main main;
    private final char echoChar;
    private JPanel mainPanel;
    private JLabel errorLabel;
    private JLabel poweredByLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton clearButton;
    private JButton signInButton;

    public LoginPage(Main m) {
        super(null, null);
        main = m;
        echoChar = passwordField.getEchoChar();
        errorLabel.setText("");
        passwordField.setEchoChar((char) 0);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        // To display default text hint in fields when empty
        usernameField.addFocusListener(this);
        passwordField.addFocusListener(this);

        // To enable/disable buttons based on field input being empty/not
        usernameField.getDocument().addDocumentListener(this);
        passwordField.getDocument().addDocumentListener(this);

        // To clear the fields or attempt log in
        signInButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void updateButtonState() {
        if (!usernameField.getText().isEmpty() && usernameField.getForeground() == Color.BLACK
                || !passwordField.getText().isEmpty() && passwordField.getForeground() == Color.BLACK) {
            clearButton.setEnabled(true);
            signInButton.setEnabled(true);
        } else {
            clearButton.setEnabled(false);
            signInButton.setEnabled(false);
        }
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
            String username = usernameField.getText();
            String password = Arrays.toString(passwordField.getPassword());
            if (!username.equals("Mateusz") && !password.equals("[m, a, t, z]"))
                errorLabel.setText("Incorrect username or password.");
            else main.switchPanel(new MainInterface(main, username));
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
}
