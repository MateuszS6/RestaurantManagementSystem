import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginPage implements ActionListener, FocusListener {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton clearButton;
    private JButton loginButton;
    private final Color defaultTextColour = new Color(0x9B9B9B);

    public LoginPage() {
        usernameField.addFocusListener(this);
        passwordField.addFocusListener(this);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            usernameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == loginButton) {
            System.out.println("Login");
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
}
