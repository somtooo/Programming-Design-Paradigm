package imagedemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageView extends JFrame implements ActionListener {

  /** Generated version id. */ 
  private static final long serialVersionUID = 5453921206754904126L;
  
  private ImagePanel imagePanel;
  
  public ImageView(String title) {
    super(title);
    
    this.setSize(500, 300);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // necessary so it closes correctly
    
    this.setLayout(new BorderLayout());
    
    // button on the bottom right
    JButton load = new JButton("Load Super Cool Images!");
    load.addActionListener(this);
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    bottom.add(load);
    load.setEnabled(true);
    this.add(bottom, BorderLayout.SOUTH);
    
    imagePanel = new ImagePanel();
    //imagePanel.setBackground(Color.DARK_GRAY);
    this.add(imagePanel, BorderLayout.CENTER);
    
    imagePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent event) {
        JOptionPane.showMessageDialog(null,  "Mouse Clicked: (" + event.getX() + ", " + event.getY() + ")");
      }
    });
  }

  public void start() {
    this.setVisible(true);
  }
  
  private void getImage() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //String filename = file.getAbsolutePath();
      try {
        BufferedImage image = ImageIO.read(file);
        // Add this to the GUI
        imagePanel.setImage(image);
        repaint();
      }
      catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "error occurred loading image");
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    getImage();
  }
}
