package imagepractice;

import components.ScrollDemo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar {

    private JMenuItem load;
    private Scrollable imagePanel;
    private  JSlider slider;
    private ChangeListener eventi;

    public MenuBar(Scrollable imagePanel,JSlider slider) {
        super();
        eventi = new event("default");
        slider.addChangeListener(eventi);
        JMenu file = new JMenu("File");
        this.add(file);
        JMenuItem exit = new JMenuItem("Exit");
        load = new JMenuItem("Load Image");
        load.addActionListener(new loadAction());
        JMenuItem save = new JMenuItem("Save Image As");
        file.add(load);
        file.add(save);
        file.add(exit);
        this.slider = slider;

        JMenu blur = new JMenu("Blur");
        this.add(blur);
        JMenuItem runBlur = new JMenuItem("Apply Default Blur");
        blur.add(runBlur);
        runBlur.addActionListener(new blurAction());

        JMenu sharpen = new JMenu("Sharpen");
        this.add(sharpen);
        JMenuItem runSharpen = new JMenuItem("Apply Default Sharpen");
        sharpen.add(runSharpen);
        runSharpen.addActionListener(new sharpenAction());

        JMenu sepia = new JMenu("Sepia");
        this.add(sepia);
        JMenuItem runSepia = new JMenuItem("Apply Default Sepia");
        sepia.add(runSepia);

        JMenu grey = new JMenu("GreyScale");
        this.add(grey);
        JMenuItem runGreyScale = new JMenuItem("Apply Default GreyScale");
        grey.add(runGreyScale);

        JMenu dither = new JMenu("Reduce Color");
        this.add(dither);
        JMenuItem runDither = new JMenuItem("Apply Dither Color Reduction");
        dither.add(runDither);

        JMenu pixelate = new JMenu("Pixelate");
        this.add(pixelate);
        JMenuItem runPixelate = new JMenuItem("Apply Default Pixelate");
        pixelate.add(runPixelate);

        JMenu mosaic = new JMenu("Mosaic");
        this.add(mosaic);
        JMenuItem runMosaic = new JMenuItem("Apply Default Mosaic");
        mosaic.add(runMosaic);

        JMenu stitch = new JMenu("Embroidery Pattern");
        this.add(stitch);
        JMenuItem runCross = new JMenuItem("Generate CrossStitch Pattern");
        stitch.add(runCross);

        JMenu settings = new JMenu("Settings");
        this.add(settings);
        JMenuItem batch = new JMenuItem("Switch to Batch Processing");
        settings.add(batch);

        this.imagePanel = imagePanel;

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
                imagePanel.setPicture(new ImageIcon(image));

            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "error occurred loading image");
            }
        }


    }

    public class loadAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getImage();

        }

    }

    private void simulateCommand(int value, String action) {
        if (!action.equals("default")) {
            java.net.URL imgUrl = ScrollDemo.class.getResource("images/SnowyTree.png");
            if (imgUrl != null) {
                imagePanel.setPicture(new ImageIcon(imgUrl));
                System.out.println(String.format("Simulated %s from %s",value, action));
            } else {
                System.err.println("Couldn't find file: " + "images/SnowyTree.png");
            }
        }

    }
    public class blurAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            simulateCommand(1,"blur action first");
            slider.removeChangeListener(eventi);
            eventi = new event("blur action");
            slider.addChangeListener(eventi);
        }

        }

    public class sharpenAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            simulateCommand(1,"sharpen action first");
            slider.removeChangeListener(eventi);
            eventi = new event("sharpen action");
            slider.addChangeListener(eventi);
        }

    }

        public class event implements ChangeListener {

        private String action;
        public event(String action) {
            this.action = action;
        }
            @Override
            public void stateChanged(ChangeEvent e) {
                slider.setMinimum(1);
                slider.setMaximum(20);
                simulateCommand(slider.getValue(),action);


            }
        }

}



