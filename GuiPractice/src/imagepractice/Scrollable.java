package imagepractice;

import components.ScrollDemo;

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




        // Put it in this panel.


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

    private static void createAndShowGui() {
        // Create and set up the window.
        JFrame frame = new JFrame("ScrollDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new ScrollDemo();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

//    public void run() {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGui();
//            }
//        });
//    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgUrl = ScrollDemo.class.getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        picture.setMaxUnitIncrement(10);
    }
}
