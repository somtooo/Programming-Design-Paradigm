package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scrollable extends JPanel implements ItemListener {
    JScrollPane pictureScrollPane;
    ScrollablePicture picture;

    public Scrollable() {
        // Get the image to use.
        // Set up the scroll pane.
        setLayout(new FlowLayout());
        picture = null;
        pictureScrollPane = new JScrollPane(null);
        add(pictureScrollPane);
        pictureScrollPane.setPreferredSize(new Dimension(950, 700));
        pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

    }

    public void setPicture(ImageIcon i) {
        picture = new ScrollablePicture(i, 10);
        pictureScrollPane.setViewportView(picture);
        picture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JOptionPane.showMessageDialog(null,  "Mouse Clicked: (" + event.getX() + ", " + event.getY() + ")");
            }
        });

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        picture.setMaxUnitIncrement(10);
    }
}
