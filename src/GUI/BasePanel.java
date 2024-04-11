package GUI;

import javax.swing.*;

public class BasePanel extends MyPanel {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JLabel contentLabel;

    public BasePanel(MyPanel displayPanel) {
        contentPanel.add(displayPanel.getMainPanel());
        contentLabel.setText(displayPanel.getTitle());
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
