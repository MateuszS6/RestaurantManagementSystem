package GUI;

import javax.swing.*;

public abstract class MyPanel {
    private String title;

    public abstract JPanel getMainPanel();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
