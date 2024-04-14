package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesPanel extends MyPanel {
    private JPanel mainPanel;
    private JComboBox bookingsComboBox;
    private JComboBox coversComboBox;
    private JLabel dayCoversLabel;
    private JLabel dayBookingsLabel;
    private JLabel annualBookingsLabel;
    private JLabel annualCoversLabel;

    public SalesPanel(MyPanel parent) {
        super("Sales", parent.getConnection());

        // Drop-down menus
        bookingsComboBox.addActionListener(e -> {
            String average = getConnection().getDayAverageBookings(bookingsComboBox.getSelectedIndex());
            dayBookingsLabel.setText(average);
        });
        coversComboBox.addActionListener(e -> {
            String average = getConnection().getDayAverageCovers(coversComboBox.getSelectedIndex());
            dayCoversLabel.setText(average);
        });

        // Statistic labels
        dayBookingsLabel.setText(getConnection().getDayAverageBookings(0)); // 0 -> on Monday
        dayCoversLabel.setText(getConnection().getDayAverageCovers(0)); // 0 -> Monday
        annualBookingsLabel.setText(getConnection().getAnnualBookings());
        annualCoversLabel.setText(getConnection().getAnnualCovers());
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
