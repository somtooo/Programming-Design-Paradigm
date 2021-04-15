package imagepractice;

import components.ScrollDemo;
import imagepractice.controller.TotalFeatures;

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

public class MenuBar extends JMenuBar implements MenuInterface {

    private JMenuItem load;
    private JMenuItem runBlur;
    private Scrollable imagePanel;
    private JMenuItem runSharpen;
    private ChangeListener eventi;

    public MenuBar(Scrollable imagePanel) {
        super();
        JMenu file = new JMenu("File");
        this.add(file);
        JMenuItem exit = new JMenuItem("Exit");
        load = new JMenuItem("Load Image");
        JMenuItem save = new JMenuItem("Save Image As");
        file.add(load);
        file.add(save);
        file.add(exit);

        JMenu blur = new JMenu("Blur");
        this.add(blur);
        runBlur = new JMenuItem("Apply Default Blur");
        blur.add(runBlur);

        JMenu sharpen = new JMenu("Sharpen");
        this.add(sharpen);
        runSharpen = new JMenuItem("Apply Default Sharpen");
        sharpen.add(runSharpen);

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



    @Override
    public void setFeatures(TotalFeatures controller) {
        load.addActionListener(e -> controller.loadImage());
        runBlur.addActionListener(e -> controller.blurImage());
        runSharpen.addActionListener(e -> controller.sharpenImage());

    }


}



