import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

public class LoginPage implements ActionListener, FocusListener, DocumentListener {
    private Main main;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton clearButton;
    private JButton loginButton;
    private final Color defaultTextColour = new Color(0xFF777777, true);

    public LoginPage(Main main) {
        this.main = main;

        usernameField.addFocusListener(this);
        passwordField.addFocusListener(this);

        usernameField.getDocument().addDocumentListener(this);
        passwordField.getDocument().addDocumentListener(this);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateButtonState() {
        if (!usernameField.getText().isEmpty() && usernameField.getForeground() == Color.BLACK
                || !passwordField.getText().isEmpty() && passwordField.getForeground() == Color.BLACK) {
            clearButton.setEnabled(true);
            loginButton.setEnabled(true);
        } else {
            clearButton.setEnabled(false);
            loginButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            usernameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = Arrays.toString(passwordField.getPassword());
            if (username.equals("mateusz") && password.equals("[m, a, t, z]")) {
                System.out.println("Login successful");
                main.switchPanel(new HomePage().getMainPanel());
            } else {
                System.out.println("Login unsuccessful");
            }
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
            passwordField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == usernameField && usernameField.getText().isEmpty()) {
            usernameField.setForeground(defaultTextColour);
            usernameField.setText("Username");
        }
        if (e.getSource() == passwordField && passwordField.getText().isEmpty()) {
            passwordField.setForeground(defaultTextColour);
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
