package view;

import controller.TotalFeatures;
import java.awt.Color;
import java.util.Objects;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Represents the menu for the main view. It has the elements that need to be activated to perform
 * most of the GUI functionality.
 */
public class MenuView extends JMenuBar implements MenuInterface {

  private final JMenuItem runSepia;
  private final JMenuItem runGreyScale;
  private final JMenuItem runPixelate;
  private final JMenuItem runMosaic;
  private final JMenuItem runCross;
  private final JMenuItem batch;
  private final JMenuItem save;
  private final JMenuItem runDither;
  private final JMenuItem load;
  private final JMenuItem runBlur;
  private final JMenuItem runSharpen;
  private final JMenuItem exit;

  /** Default constructor builds the menu so the main view can use it. */
  public MenuView() {
    super();
    setBackground(new Color(21, 25, 28));

    JMenu file = new JMenu("File");
    file.setForeground(Color.white);
    this.add(file);
    load = new JMenuItem("Load Image");
    save = new JMenuItem("Save Image As");
    file.add(load);
    file.add(save);
    exit = new JMenuItem("Exit");
    file.add(exit);

    JMenu blur = new JMenu("Blur");
    blur.setForeground(Color.white);
    this.add(blur);
    runBlur = new JMenuItem("Apply Default Blur");
    blur.add(runBlur);

    JMenu sharpen = new JMenu("Sharpen");
    sharpen.setForeground(Color.white);
    this.add(sharpen);
    runSharpen = new JMenuItem("Apply Default Sharpen");
    sharpen.add(runSharpen);

    JMenu sepia = new JMenu("Sepia");
    sepia.setForeground(Color.white);
    this.add(sepia);
    runSepia = new JMenuItem("Apply Default Sepia");
    sepia.add(runSepia);

    JMenu grey = new JMenu("GreyScale");
    grey.setForeground(Color.white);
    this.add(grey);
    runGreyScale = new JMenuItem("Apply Default GreyScale");
    grey.add(runGreyScale);

    JMenu dither = new JMenu("Reduce Color");
    dither.setForeground(Color.white);
    this.add(dither);
    runDither = new JMenuItem("Apply Dither Color Reduction");
    dither.add(runDither);

    JMenu pixelate = new JMenu("Pixelate");
    pixelate.setForeground(Color.white);
    this.add(pixelate);
    runPixelate = new JMenuItem("Apply Default Pixelate");
    pixelate.add(runPixelate);

    JMenu mosaic = new JMenu("Mosaic");
    mosaic.setForeground(Color.white);
    this.add(mosaic);
    runMosaic = new JMenuItem("Apply Default Mosaic");
    mosaic.add(runMosaic);

    JMenu stitch = new JMenu("Embroidery Pattern");
    stitch.setForeground(Color.white);
    this.add(stitch);
    runCross = new JMenuItem("Generate CrossStitch Pattern");
    stitch.add(runCross);

    JMenu settings = new JMenu("Settings");
    settings.setForeground(Color.white);
    this.add(settings);
    batch = new JMenuItem("Switch to Batch Processing");
    settings.add(batch);
  }

  /**
   * Gives the controller over its elements listeners.
   *
   * @param controller the controller to give control too.
   */
  @Override
  public void setFeatures(TotalFeatures controller) {
    Objects.requireNonNull(controller);
    load.addActionListener(e -> controller.loadImage());
    runBlur.addActionListener(e -> controller.blurImage());
    runSharpen.addActionListener(e -> controller.sharpenImage());
    runGreyScale.addActionListener(e -> controller.greyscaleImage());
    runSepia.addActionListener(e -> controller.sepiaImage());
    runMosaic.addActionListener(e -> controller.mosaicImage());
    runPixelate.addActionListener(e -> controller.pixelateImage());
    runCross.addActionListener(e -> controller.generatePattern());
    runDither.addActionListener(e -> controller.ditherImage());
    save.addActionListener(e -> controller.saveImage());
    batch.addActionListener(e -> controller.runBatchView());
    runCross.addActionListener(e -> controller.generatePattern());
    exit.addActionListener(e -> System.exit(0));
  }
}
