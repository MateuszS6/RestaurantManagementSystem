import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface implements ActionListener {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JPanel displayPanel;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton analyticsButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
