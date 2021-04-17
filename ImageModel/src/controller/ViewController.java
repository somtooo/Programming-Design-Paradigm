package controller;

import imagemodel.ImageModelInterface;
import view.ViewInterface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewController implements TotalFeatures {
    private final ImageModelInterface model;
    private ViewInterface view;
    private boolean setToMosaic;
    private  boolean setToBlur;
    private  boolean setToSharpen;
    private boolean setToPixelate;
    private  boolean setToDither;

    public ViewController(ImageModelInterface model) {
        this.model = model;
        this.setToMosaic = false;
        this.setToBlur = false;
        this.setToSharpen = false;
        this.setToPixelate = false;
        this.setToDither = false;
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
        setCommands(false,false,false,false,false);
    }

    @Override
    public void blurImage() {
        if (!setToBlur) {
            int input = getInput("Enter Blur Value","Blur");
            view.setSliderListenerToBlur(this,input,input+ 20);
            setCommands(false,true,false,false,false);
        }
        try{
            model.blur(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Blur Error");
        }
        updateImage();
    }

    private int getInput(String message, String command) {
        String input = view.getInputDialog(message, command);
        return Integer.parseInt(input);
    }

    private void updateImage() {
        view.setImagePanel(model.getBufferedImage());
    }

    private void setCommands(boolean isSharpen, boolean isBlur, boolean isDither, boolean isPixelate, boolean isMosaic) {
        setToSharpen = isSharpen;
        setToBlur = isBlur;
        setToDither = isDither;
        setToPixelate = isPixelate;
        setToMosaic = isMosaic;
    }

    @Override
    public void sharpenImage() {
        if (!setToSharpen) {
            int input = getInput("Enter Sharpen Value","Sharpen");
            view.setSliderListenerToSharpen(this,input,input+ 20);
            setCommands(true,false,false,false,false);
        }
        try{
            model.sharpen(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sharpen Error");
        }
       view.setImagePanel(model.getBufferedImage());
    }

    @Override
    public void mosaicImage() {

        if (!setToMosaic) {
            int input = getInput("Enter Number of Seeds", "Mosaic");
            view.setSliderListenerToMosaic(this, input, input+20);
            setCommands(false,false,false,false,true);
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
        if (!setToPixelate) {
            int input = getInput("Enter Number of Squares", "Pixelate");
            view.setSliderListenerToPixelate(this, input, input+20);
            setCommands(false,false,false,true,false);
        }
        try{
            model.pixelate(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Pixelate Error");
        }
        updateImage();
    }

    @Override
    public void sepiaImage() {
        try{
            view.hideSlider();
            model.sepia();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sepia Error");
        }
        setCommands(false,false,false,false,false);
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
        setCommands(false,false,false,false,false);
        updateImage();

    }

    @Override
    public void saveImage() {
        try {
      model.saveImage(view.getImageSaveName());
        } catch (IOException  | IllegalStateException e) {
            view.throwError(e.getMessage(), "Save Error");
        }
        setCommands(false,false,false,false,false);
        view.throwSuccess("Saved Image", "Save");
    }

    @Override
    public void generatePattern() {
        try{
            view.hideSlider();
            model.crossStitch();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(), "CrossStitch Error");
        }
        setCommands(false,false,false,false,false);
        updateImage();

    }

    @Override
    public void ditherImage() {
        if (!setToDither) {
            int input = getInput("Enter Number of Colors", "Reduce Color");
            view.setSliderListenerToReduce(this, input, input+20);
            setCommands(false,false,true,false,false);
        }
        try{
            model.reduceColor(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Color Reduction Error");
        }
        updateImage();

    }

    @Override
    public void runBatchView() {
        view.initiateBatchView();

    }
}
