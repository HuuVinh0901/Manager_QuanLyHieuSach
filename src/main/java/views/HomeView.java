package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeView extends JPanel {
    private JLabel lblBackground;

    public HomeView() {
        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/homeView.jpg"));
        lblBackground = new JLabel(imageIcon);
        add(lblBackground, BorderLayout.CENTER);
    }
}
