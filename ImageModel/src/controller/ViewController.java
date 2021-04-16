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
    private boolean setToMosaic;
    private  boolean setToBlur;

    public ViewController(ImageModelInterface model) {
        this.model = model;
        this.setToMosaic = false;
        this.setToBlur = false;
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

        if (!setToBlur) {
            int input = getInput();
            view.setSliderListenerToBlur(this,input,input+ 20);
            setToBlur = true;
            setToMosaic = false;
        }
        try{
            System.out.println(view.getSliderValue());
            model.blur(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Blur Error");
        }
        updateImage();
    }

    private int getInput() {
        String input = view.getInputDialog("Enter blur value", "Blur");
        return Integer.parseInt(input);
    }

    private void updateImage() {
        view.setImagePanel(model.getBufferedImage());
    }

    @Override
    public void sharpenImage() {
        try{
            view.setSliderValue(1, 10);
            model.sharpen(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sharpen Error");
        }
       view.setImagePanel(model.getBufferedImage());
       view.setSliderListenerToSharpen(this);
    }

    @Override
    public void mosaicImage() {

        if (!setToMosaic) {
            int input = getInput();
            view.setSliderListenerToMosaic(this, input, input+20);
            setToMosaic = true;
            setToBlur = false;
        }
        try{
            model.toMosaic(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Mosaic Error");
        }
        updateImage();
    }

    @Override
    public void pixelateImage() {
        try{
            view.setSliderValue(100,150);
            System.out.println(view.getSliderValue());
            model.pixelate(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Pixelate Error");
        }
        updateImage();
        view.setSliderListenerToPixelate(this);

    }

    @Override
    public void sepiaImage() {
        try{
            view.hideSlider();
            model.sepia();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sepia Error");
        }
        updateImage();
    }

    @Override
    public void greyscaleImage() {
        try{
            view.hideSlider();
            model.greyScale();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"GreyScale Error");
        }
        updateImage();

    }

    @Override
    public void saveImage() {

    }

    @Override
    public void generatePattern() {

    }
}
