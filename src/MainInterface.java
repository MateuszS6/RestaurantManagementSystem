import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainInterface implements ActionListener {
    private Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JButton dashboardButton;
    private JButton orderButton;
    private JButton menuButton;
    private JButton tablesButton;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JLabel test;

    public MainInterface(Main main, String user) {
        this.main = main;
        welcomeLabel.setText("Welcome, " + user + '!');
        this.main.MySetIcon(logoLabel, "images/restaurant-logo.jpeg", -1, 150);
        this.main.MySetIcon(poweredByLabel, "images/team-icon.png", -1, 30);

        // Side-panel buttons
        dashboardButton.addActionListener(this);
        orderButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);

//        updateTime();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        while (true) {
            String time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
            test.setText("Dashboard");
        } else if (e.getSource() == orderButton) {
            test.setText("Order");
        } else if (e.getSource() == menuButton) {
            test.setText("Menu");
        } else if (e.getSource() == tablesButton) {
            test.setText("Tables");
        }
    }
}
