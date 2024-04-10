package GUI;

import javax.swing.*;

public class TestPanel implements MyPanel {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JLabel contentLabel;

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JLabel getContentLabel() {
        return contentLabel;
    }
}
