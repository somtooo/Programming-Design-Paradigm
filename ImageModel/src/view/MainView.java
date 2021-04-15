package view;

import controller.TotalFeatures;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MainView extends JFrame implements ViewInterface{
    private Scrollable imagePanel;
    private MenuInterface menuBar;
    private JSlider slider;
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

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(new Color(21,25,28));
        slider = new JSlider(JSlider.HORIZONTAL, 1,10,1);
        slider.setBackground(new Color(21,25,28));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        sliderListener = e -> {};
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
    public void setSliderListenerToBlur(TotalFeatures controller) {
        slider.removeChangeListener(sliderListener);
        sliderListener = e -> controller.blurImage();
        slider.addChangeListener(sliderListener);
    }

    @Override
    public int getSliderValue() {
        return slider.getValue();
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
            return file.getName();
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
