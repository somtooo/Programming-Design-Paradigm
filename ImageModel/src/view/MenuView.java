package view;

import controller.TotalFeatures;


import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MenuView extends JMenuBar implements MenuInterface {

    private JMenuItem load;
    private JMenuItem runBlur;
    private JMenuItem runSharpen;

    public MenuView()  {
        super();
        setBackground(new Color(21,25,28));

        JMenu file = new JMenu("File");
        file.setForeground(Color.white);
        this.add(file);
        JMenuItem exit = new JMenuItem("Exit");
        load = new JMenuItem("Load Image");
        JMenuItem save = new JMenuItem("Save Image As");
        file.add(load);
        file.add(save);
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
        JMenuItem runSepia = new JMenuItem("Apply Default Sepia");
        sepia.add(runSepia);

        JMenu grey = new JMenu("GreyScale");
        grey.setForeground(Color.white);
        this.add(grey);
        JMenuItem runGreyScale = new JMenuItem("Apply Default GreyScale");
        grey.add(runGreyScale);

        JMenu dither = new JMenu("Reduce Color");
        dither.setForeground(Color.white);
        this.add(dither);
        JMenuItem runDither = new JMenuItem("Apply Dither Color Reduction");
        dither.add(runDither);

        JMenu pixelate = new JMenu("Pixelate");
        pixelate.setForeground(Color.white);
        this.add(pixelate);
        JMenuItem runPixelate = new JMenuItem("Apply Default Pixelate");
        pixelate.add(runPixelate);

        JMenu mosaic = new JMenu("Mosaic");
        mosaic.setForeground(Color.white);
        this.add(mosaic);
        JMenuItem runMosaic = new JMenuItem("Apply Default Mosaic");
        mosaic.add(runMosaic);

        JMenu stitch = new JMenu("Embroidery Pattern");
        stitch.setForeground(Color.white);
        this.add(stitch);
        JMenuItem runCross = new JMenuItem("Generate CrossStitch Pattern");
        stitch.add(runCross);

        JMenu settings = new JMenu("Settings");
        settings.setForeground(Color.white);
        this.add(settings);
        JMenuItem batch = new JMenuItem("Switch to Batch Processing");
        settings.add(batch);


    }


    @Override
    public void setFeatures(TotalFeatures controller) {
        load.addActionListener(e -> controller.loadImage());
        runBlur.addActionListener(e -> controller.blurImage());
        runSharpen.addActionListener(e -> controller.sharpenImage());

    }


}



