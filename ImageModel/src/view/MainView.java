package view;

import controller.TotalFeatures;
import images.Factory;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MainView extends JFrame implements ViewInterface{
    private final JPanel info;
    private final JScrollPane pane;
    private Scrollable imagePanel;
    private MenuInterface menuBar;
    private JSlider slider;
    JPanel bottom;
    private ChangeListener sliderListener;
    private SecondaryViewInterface colorPicker;
    private SecondaryViewInterface batchView;
    private Factory factory;

    protected final DefaultListModel<String> modelForList =  new DefaultListModel<String>();


    public MainView() {
        super("Practice View");
        try {
            loadSplashScreen();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        centreWindow(this,1800,900);
        this.setSize(1800,900);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        factory = new Factory();
        colorPicker = factory.createColorPickerView();
        batchView = factory.createBashView();
        imagePanel = new Scrollable();
        imagePanel.setBackground(new Color(34,40,44));
        imagePanel.setOpaque(true);
//        this.add(imagePanel,BorderLayout.CENTER);
        info  = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));


        info.setBackground(new Color(21,25,28));
//        info.setOpaque(true);


        pane = new JScrollPane(info,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension(300, 500));
//        JScrollPane pane = new JScrollPane(info);
//        pane.setBackground(new Color(44,52,58));
        pane.setVisible(false);

//        this.add(info, .EAST);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(30);
        JPanel bottom1 = new JPanel(layout);
        bottom1.setBackground(new Color(34,40,44));
        bottom1.add(imagePanel);
        bottom1.add(pane);
        this.add(bottom1, BorderLayout.CENTER);
        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(new Color(34,40,44));
        slider = new JSlider(JSlider.HORIZONTAL, 1,10,1);
        slider.setBackground(new Color(34,40,44));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        sliderListener = e -> {};
        slider.addChangeListener(sliderListener);
        slider.setVisible(true);
        bottom.add(slider);
        JPanel root = new JPanel();
        root.setBackground(new Color(34,40,44));
        this.add(bottom, BorderLayout.SOUTH);
        menuBar = new MenuView();
        this.setJMenuBar((JMenuBar) menuBar);


    }

    private void loadSplashScreen() throws MalformedURLException {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon(new URL("https://i.stack.imgur.com/hzk6C.gif")), SwingConstants.CENTER));
        centreWindow(window,287, 141);
        window.setSize(287,141);
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
        update(controller, min, max);
        sliderListener = e -> controller.blurImage();
        updateFrame();
    }

    @Override
    public void addToInfo(String text, Color colorOfInfo) {
        JLabel addToInfo = new JLabel(text);
        addToInfo.setForeground(colorOfInfo);
        addToInfo.setBackground(colorOfInfo.darker());
        addToInfo.setFont(new Font("Roboto Th",Font.PLAIN, 28));
        info.add(addToInfo);
        pane.setVisible(true);
    }

    @Override
    public void initiateBatchView() {
      batchView.start();
    }


    @Override
    public String getInputDialog(String message, String command) {
        return JOptionPane.showInputDialog(this,message,command, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void setSliderListenerToSharpen(TotalFeatures controller, int min, int max) {
        update(controller, min, max);
        sliderListener = e -> controller.sharpenImage();
        updateFrame();
    }

    @Override
    public void setSliderListenerToPixelate(TotalFeatures controller, int min, int max) {
        update(controller, min, max);
        sliderListener = e -> controller.pixelateImage();
        updateFrame();

    }

    @Override
    public void setSliderListenerToMosaic(TotalFeatures controller, int min, int max) {
        update(controller, min, max);
        sliderListener = e -> controller.mosaicImage();
        updateFrame();

    }

    @Override
    public void setSliderListenerToReduce(TotalFeatures controller, int min, int max) {
        update(controller, min, max);
        sliderListener = e -> controller.ditherImage();
        updateFrame();
    }

    private void updateFrame() {
        slider.addChangeListener(sliderListener);
        bottom.add(slider);
        revalidate();
        repaint();
    }

    private void update(TotalFeatures controller, int min, int max) {
        bottom.remove(slider);
        slider = new JSlider(JSlider.HORIZONTAL, min, max, min);
        slider.setBackground(new Color(21,25,28));
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
        } else return "";

    }

    @Override
    public void throwError(String error, String errorType) {
        JOptionPane.showMessageDialog(this,error,errorType,JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public  void throwSuccess(String success, String successType) {
        JOptionPane.showMessageDialog(this, success, successType,JOptionPane.INFORMATION_MESSAGE);
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
