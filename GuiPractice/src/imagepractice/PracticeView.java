package imagepractice;

import imagepractice.controller.Controller;
import imagepractice.controller.TotalFeatures;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PracticeView extends JFrame implements PracticeViewInterface{

    private Scrollable imagePanel;
    private MenuInterface menuBar;
    private JSlider slider;
    private ChangeListener sliderListener;
    public PracticeView() {
        super("Practice View");
        this.setSize(1500,900);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        imagePanel = new Scrollable();
        this.add(imagePanel,BorderLayout.CENTER);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        slider = new JSlider(JSlider.HORIZONTAL, 0,20,0);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        sliderListener = e -> {System.out.println("Normal slider");};
        bottom.add(slider);
        this.add(bottom, BorderLayout.SOUTH);
        menuBar = new MenuBar(imagePanel);
        this.setJMenuBar((JMenuBar) menuBar);



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
    public void setSliderListenerToBlur(TotalFeatures controller) {
        slider.removeChangeListener(sliderListener);
        sliderListener = e -> controller.blurImage();
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
//            try {
//                BufferedImage image = ImageIO.read(file);
//                System.out.println(file.getAbsolutePath());
//                // Add this to the GUI
//                imagePanel.setPicture(new ImageIcon(image));
//
//            }
//            catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, "error occurred loading image");
//            }
        } else return "";


    }


    //        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Specify a file to save");
//
//        int userSelection = fileChooser.showSaveDialog(this);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            System.out.println("Save as file: " + fileToSave.getName());
//        }
  public static void main(String[] args) {
      PracticeView unifiedView = new PracticeView();
      Controller controller = new Controller();
      controller.setView(unifiedView);
      unifiedView.start();

      }

}

