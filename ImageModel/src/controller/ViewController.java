package controller;

import imagemodel.ImageModelInterface;
import view.ViewInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewController implements TotalFeatures {
    private ImageModelInterface model;
    private ViewInterface view;

    public ViewController(ImageModelInterface model) {
        this.model = model;
    }

    public void setView(ViewInterface viewToSet) {
        view = viewToSet;
        view.setFeatures(this);

    }

    @Override
    public void loadImage() {
        try {
            model.loadImage(view.getImage());
            view.setImagePanel(model.getBufferedImage());
        } catch (IOException e) {
            view.throwError(e.getMessage(), "Load Error");
        }
    }

    @Override
    public void blurImage() {
        model.blur(view.getSliderValue());
        view.setImagePanel(model.getBufferedImage());
        view.setSliderListenerToBlur(this);
    }

    @Override
    public void sharpenImage() {
        try {
            BufferedImage image =
                    ImageIO.read(
                            new File(
                                    "C:\\Users\\chukw\\IdeaProjects\\GuiPractice\\src\\imagepractice\\images\\SnowyTree.png"));
            System.out.println("I Sharpened image");
            view.setImagePanel(image);
            // Add this to the GUI
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
