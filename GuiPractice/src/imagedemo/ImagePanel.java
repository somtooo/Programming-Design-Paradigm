package imagedemo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
  
  /** Generated version id. */
  private static final long serialVersionUID = -7919107801874775685L;
  
  private BufferedImage image;
  
  public ImagePanel() {
    image = null;
  }
  
  public void setImage(BufferedImage image) {
    this.image = image;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }
  }
}
