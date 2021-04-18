package view;

import controller.TotalFeatures;
import images.Factory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import view.utilities.ViewUtilities;


/** The main view represents the first view a user interacts with when the GUI is launched. */
public class MainView extends JFrame implements ViewInterface {
  private final JPanel info;
  private final JScrollPane pane;
  private final Scrollable imagePanel;
  private final MenuInterface menuBar;
  private final JPanel bottom;
  private final SecondaryViewInterface colorPicker;
  private final SecondaryViewInterface batchView;
  private JSlider slider;
  private ChangeListener sliderListener;

  /** Default constructor builds the view and sets all its components. */
  public MainView() {
    super("Practice View");
    try {
      loadSplashScreen();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    ViewUtilities.centreWindow(this, 1800, 900);
    this.setSize(1800, 900);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    Factory factory = new Factory();
    colorPicker = factory.createColorPickerView();
    batchView = factory.createBashView();
    imagePanel = new Scrollable();
    imagePanel.setBackground(new Color(34, 40, 44));
    imagePanel.setOpaque(true);
    info = new JPanel();
    info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

    info.setBackground(new Color(21, 25, 28));
    pane =
        new JScrollPane(
            info,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setPreferredSize(new Dimension(300, 500));
    pane.setVisible(false);

    FlowLayout layout = new FlowLayout();
    layout.setHgap(30);
    JPanel bottom1 = new JPanel(layout);
    bottom1.setBackground(new Color(34, 40, 44));
    bottom1.add(imagePanel);
    bottom1.add(pane);
    this.add(bottom1, BorderLayout.CENTER);
    bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottom.setBackground(new Color(34, 40, 44));
    slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
    slider.setBackground(new Color(34, 40, 44));
    slider.setMajorTickSpacing(2);
    slider.setPaintTicks(true);
    sliderListener = e -> {};
    slider.addChangeListener(sliderListener);
    slider.setVisible(true);
    bottom.add(slider);
    JPanel root = new JPanel();
    root.setBackground(new Color(34, 40, 44));
    this.add(bottom, BorderLayout.SOUTH);
    menuBar = new MenuView();
    this.setJMenuBar((JMenuBar) menuBar);
  }

  /**
   * Loads a splash screen before application starts.
   *
   * @throws MalformedURLException if the url isn't correct.
   */
  private void loadSplashScreen() throws MalformedURLException {
    JWindow window = new JWindow();
    window
        .getContentPane()
        .add(
            new JLabel(
                "",
                new ImageIcon(new URL("https://i.stack.imgur.com/hzk6C.gif")),
                SwingConstants.CENTER));
    ViewUtilities.centreWindow(window, 287, 141);
    window.setSize(287, 141);
    window.setVisible(true);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    window.setVisible(false);
  }

  @Override
  public void start() {
    this.setVisible(true);
  }

  @Override
  public void setFeatures(TotalFeatures controller) {
    menuBar.setFeatures(controller);
    slider.addChangeListener(sliderListener);
  }

  @Override
  public void setSliderListenerToBlur(TotalFeatures controller, int min, int max) {
    update(min, max);
    sliderListener = e -> controller.blurImage();
    updateFrame();
  }

  @Override
  public void addToInfo(String text, Color colorOfInfo) {
    JLabel addToInfo = new JLabel(text);
    addToInfo.setForeground(colorOfInfo);
    addToInfo.setBackground(colorOfInfo.darker());
    addToInfo.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    info.add(addToInfo);
    pane.setVisible(true);
    repaint();
    revalidate();
  }

  @Override
  public void initiateBatchView() {
    batchView.start();
  }

  @Override
  public String getInputDialog(String message, String command) {
    return JOptionPane.showInputDialog(this, message, command, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void setSliderListenerToSharpen(TotalFeatures controller, int min, int max) {
    update(min, max);
    sliderListener = e -> controller.sharpenImage();
    updateFrame();
  }

  @Override
  public void setSliderListenerToPixelate(TotalFeatures controller, int min, int max) {
    update(min, max);
    sliderListener = e -> controller.pixelateImage();
    updateFrame();
  }

  @Override
  public void setSliderListenerToMosaic(TotalFeatures controller, int min, int max) {
    update(min, max);
    sliderListener = e -> controller.mosaicImage();
    updateFrame();
  }

  @Override
  public void setSliderListenerToReduce(TotalFeatures controller, int min, int max) {
    update(min, max);
    sliderListener = e -> controller.ditherImage();
    updateFrame();
  }

  /** Updates the JFrame when the slider has been updated. */
  private void updateFrame() {
    slider.addChangeListener(sliderListener);
    bottom.add(slider);
    revalidate();
    repaint();
  }

  /**
   * Updates the slides based on controller command.
   *
   * @param min the min value of the slider.
   * @param max the max value of the slider.
   */
  private void update(int min, int max) {
    bottom.remove(slider);
    slider = new JSlider(JSlider.HORIZONTAL, min, max, min);
    slider.setBackground(new Color(21, 25, 28));
    slider.setMajorTickSpacing(2);
    slider.setPaintTicks(true);
    slider.setVisible(true);
  }

  @Override
  public int getSliderValue() {
    return slider.getValue();
  }

  @Override
  public void hideSlider() {
    slider.setVisible(false);
    slider.removeChangeListener(sliderListener);
    sliderListener = e -> {};
    slider.addChangeListener(sliderListener);
  }

  @Override
  public void setImagePanel(BufferedImage image) {
    imagePanel.setPicture(new ImageIcon(image));
  }

  @Override
  public String getImage() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return "";
    }
  }

  @Override
  public void throwError(String error, String errorType) {
    JOptionPane.showMessageDialog(null, error, errorType, JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void throwSuccess(String success, String successType) {
    JOptionPane.showMessageDialog(null, success, successType, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public String getImageSaveName() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      return fileToSave.getAbsolutePath();
    }
    return "";
  }

  @Override
  public void showDmcDialog() {
    colorPicker.start();
  }

  @Override
  public void clearInfo() {
    info.removeAll();
    repaint();
    revalidate();
  }
}
