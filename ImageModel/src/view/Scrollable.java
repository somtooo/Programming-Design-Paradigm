package view;

import controller.TotalFeatures;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** Represents a class that manages a scrollable picture. */
public class Scrollable extends JPanel implements ItemListener {
  private final JScrollPane pictureScrollPane;
  private ScrollablePicture picture;

  /** Default constructor. */
  public Scrollable() {
    setLayout(new FlowLayout());
    picture = new ScrollablePicture(new ImageIcon(), 10);
    pictureScrollPane = new JScrollPane(null);
    add(pictureScrollPane);
    pictureScrollPane.setPreferredSize(new Dimension(950, 700));
    pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
    setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
  }

  /**
   * Sets the image on the scrollPane.
   *
   * @param i the image to be set.
   */
  public void setPicture(ImageIcon i) {
    picture = new ScrollablePicture(i, 10);
    pictureScrollPane.setViewportView(picture);
    if (picture.getMouseListeners().length > 0) {
      picture.removeMouseListener(picture.getMouseListeners()[0]);
    }
  }

  /**
   * Gives control to the controller to handle its elements listeners.
   *
   * @param controller the controller to give control too.
   */
  public void setFeatures(TotalFeatures controller) {
    picture.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
            controller.showDmcDialog(event.getX(), event.getY());
          }
        });
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    picture.setMaxUnitIncrement(10);
  }
}
