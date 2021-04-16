package view;

import controller.TotalFeatures;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

public class MainView extends JFrame implements ViewInterface{
    private Scrollable imagePanel;
    private MenuInterface menuBar;
    private JSlider slider;
    JPanel bottom;


    private ChangeListener sliderListener;


    public MainView() throws MalformedURLException {
        super("Practice View");
//        JWindow window = new JWindow();
//        window.getContentPane().add(
//                new JLabel("", new ImageIcon(new URL("https://i.stack.imgur.com/hzk6C.gif")), SwingConstants.CENTER));
//        centreWindow(window,287, 141);
//        window.setSize(287,141);
//        window.setVisible(true);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        window.setVisible(false);

        centreWindow(this,1800,900);
        this.setSize(1800,900);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        imagePanel = new Scrollable();
        imagePanel.setBackground(new Color(21,25,28));
        imagePanel.setOpaque(true);
//        this.add(imagePanel,BorderLayout.CENTER);
        JPanel info  = new JPanel();
        info.setPreferredSize(new Dimension(350,200));
        info.setBackground(new Color(44,52,58));
//        this.add(info, BorderLayout.EAST);
        JPanel bottom1 = new JPanel(new FlowLayout());
        bottom1.setBackground(new Color(21,25,28));
        bottom1.add(imagePanel);
        bottom1.add(info);
        this.add(bottom1, BorderLayout.CENTER);

        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel bottom2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(new Color(21,25,28));
        slider = new JSlider(JSlider.HORIZONTAL, 1,10,1);
        slider.setBackground(new Color(21,25,28));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        sliderListener = e -> {};
        slider.addChangeListener(sliderListener);
        slider.setVisible(true);
        bottom.add(slider);
        JPanel root = new JPanel();
        root.setBackground(new Color(21,25,28));
        this.add(bottom, BorderLayout.SOUTH);
        menuBar = new MenuView();
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
    public void setSliderListenerToBlur(TotalFeatures controller, int min, int max) {
        updateSlider(controller, min, max);
    }

    private void updateSlider(TotalFeatures controller, int min, int max) {
        bottom.remove(slider);
        slider = new JSlider(JSlider.HORIZONTAL,min,max,min);
        slider.setBackground(new Color(21,25,28));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setVisible(true);
        sliderListener = e -> controller.blurImage();
        slider.addChangeListener(sliderListener);
        bottom.add(slider);
        revalidate();
        repaint();
    }

    @Override
    public String getInputDialog(String message, String command) {
        return JOptionPane.showInputDialog(this,message,command, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void setSliderListenerToSharpen(TotalFeatures controller) {
        slider.setVisible(true);
        slider.removeChangeListener(sliderListener);
        sliderListener = e -> controller.sharpenImage();
        slider.addChangeListener(sliderListener);
    }

    @Override
    public void setSliderListenerToPixelate(TotalFeatures controller) {
        slider.setVisible(true);
        slider.removeChangeListener(sliderListener);
        sliderListener = e -> controller.pixelateImage();
        slider.addChangeListener(sliderListener);

    }

    @Override
    public void setSliderListenerToMosaic(TotalFeatures controller, int min, int max) {
        bottom.remove(slider);
        slider = new JSlider(JSlider.HORIZONTAL,min,max,min);
        slider.setBackground(new Color(21,25,28));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setVisible(true);
        sliderListener = e -> controller.mosaicImage();
        slider.addChangeListener(sliderListener);
        bottom.add(slider);
        revalidate();
        repaint();

    }

    @Override
    public void setSliderValue(int min, int max) {
//        slider.setMinimum(min);
//        slider.setMaximum(max);
//        slider.setMajorTickSpacing(max/min);
    }

    @Override
    public int getSliderValue() {
        return slider.getValue();
    }

    @Override
    public void hideSlider() {
        System.out.println(" ia m running");
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
        } else return "";


    }

    @Override
    public void throwError(String error, String errorType) {
        JOptionPane.showMessageDialog(this,error,errorType,JOptionPane.ERROR_MESSAGE);
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

    public static void centreWindow(JWindow frame, int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

        // calculate perfect center
        int perf_x = (int) x - width/2;
        int perf_y = (int) y - height/2;

        frame.setLocation(perf_x, perf_y);
    }

    public static void centreWindow(JFrame frame, int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

        // calculate perfect center
        int perf_x = (int) x - width/2;
        int perf_y = (int) y - height/2;

        frame.setLocation(perf_x, perf_y);
    }

}
