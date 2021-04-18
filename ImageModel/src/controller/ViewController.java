package controller;

import imagemodel.ImageModelInterface;
import view.ColorPickerInterface;
import view.ViewInterface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewController implements TotalFeatures {
    private final ImageModelInterface model;
    private static ViewInterface view;
    private boolean setToMosaic;
    private  boolean setToBlur;
    private  boolean setToSharpen;
    private boolean setToPixelate;
    private  boolean setToDither;
    private static boolean  setToCrossStitch;
    private  int xCoordinate;
    private int yCoordinate;

    public ViewController(ImageModelInterface model, ViewInterface viewToSet) {
        this.model = model;
        this.setToMosaic = false;
        this.setToBlur = false;
        this.setToSharpen = false;
        this.setToPixelate = false;
        this.setToDither = false;
        setToCrossStitch = false;
        xCoordinate = 0;
        yCoordinate = 0;
        view = viewToSet;

    }

    @Override
    public void setView() {
        view.setFeatures(this);
    }

    @Override
    public void loadImage() {
        try {
            model.loadImage(view.getImage());
        } catch (IOException e) {
            view.throwError(e.getMessage(), "Load Error");
        }
        setCommands(false,false,false,false,false,false);
    }

    @Override
    public void blurImage() {
        if (!setToBlur) {
            int input = getInput("Enter Blur Value","Blur");
            view.setSliderListenerToBlur(this,input,input+ 20);
            setCommands(false,true,false,false,false,false);
        }
        try{
            model.blur(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Blur Error");
        }

    }

    private int getInput(String message, String command) {
        String input = view.getInputDialog(message, command);
        return Integer.parseInt(input);
    }


    private void setCommands(boolean isSharpen, boolean isBlur, boolean isDither, boolean isPixelate, boolean isMosaic, boolean isCrossStitch) {
        setToSharpen = isSharpen;
        setToBlur = isBlur;
        setToDither = isDither;
        setToPixelate = isPixelate;
        setToMosaic = isMosaic;
        setToCrossStitch = isCrossStitch;
    }

    @Override
    public void sharpenImage() {
        if (!setToSharpen) {
            System.out.println("i rannnn");
            int input = getInput("Enter Sharpen Value","Sharpen");
            view.setSliderListenerToSharpen(this,input,input+ 20);
            setCommands(true,false,false,false,false,false);
        }
        try{
            model.sharpen(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sharpen Error");
        }

    }

    @Override
    public void mosaicImage() {

        if (!setToMosaic) {
            int input = getInput("Enter Number of Seeds", "Mosaic");
            view.setSliderListenerToMosaic(this, input, input+20);
            setCommands(false,false,false,false,true,false);
        }
        try{
            model.toMosaic(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Mosaic Error");
        }

    }

    @Override
    public void pixelateImage() {
        if (!setToPixelate) {
            int input = getInput("Enter Number of Squares", "Pixelate");
            view.setSliderListenerToPixelate(this, input, input+20);
            setCommands(false,false,false,true,false,false);
        }
        try{
            model.pixelate(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Pixelate Error");
        }
    }

    @Override
    public void sepiaImage() {
        try{
            view.hideSlider();
            model.sepia();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Sepia Error");
        }
        setCommands(false,false,false,false,false,false);
    }

    @Override
    public void greyscaleImage() {
        try{
            view.hideSlider();
            model.greyScale();
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"GreyScale Error");
        }
        setCommands(false,false,false,false,false,false);

    }

    @Override
    public void saveImage() {
        try {
      model.saveImage(view.getImageSaveName());
        } catch (IOException  | IllegalStateException e) {
            view.throwError(e.getMessage(), "Save Error");
        }
        setCommands(false,false,false,false,false,false);
        view.throwSuccess("Saved Image", "Save");
    }

    @Override
    public void generatePattern() {
        try{
            if (!setToCrossStitch) {
                view.hideSlider();
                model.crossStitch();
                view.setScrollable(this);
            }

        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(), "CrossStitch Error");
        }
        setCommands(false,false,false,false,false,true);



    }

    private void updateLegend() {
        List<String> colorsUsed = model.getDmcColorsUsed();
        view.clearInfo();
        if (colorsUsed.size() > 0) {
            for (String colors : colorsUsed  ) {
                String icon = model.getLegendIcon(colors);
                int[] rgb = model.getDmcRgb(colors);
                view.addToInfo(icon + " - " + colors,new Color(rgb[0],rgb[1],rgb[2]));
            }
        }

    }

    @Override
    public void ditherImage() {
        if (!setToDither) {
            int input = getInput("Enter Number of Colors", "Reduce Color");
            view.setSliderListenerToReduce(this, input, input+20);
            setCommands(false,false,true,false,false,false);
        }
        try{
            model.reduceColor(view.getSliderValue());
        } catch (IllegalStateException e) {
            view.throwError(e.getMessage(),"Color Reduction Error");
        }


    }

    @Override
    public void runBatchView() {
        view.initiateBatchView();

    }

    @Override
    public void update() {
    BufferedImage image = model.getBufferedImage();
    view.setImagePanel(image);
    updateLegend();
    }


    @Override
    public void showDmcDialog(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        view.showDmcDialog(this);
    }

    @Override
    public void loadDmc() {
        view.clearInputString();
        String[] dmcValues = model.getDmcValues();
        for (String dmcValue : dmcValues) {
            view.setAddToList(dmcValue);
        }
    }

    @Override
    public void handleColorClick(String selectedValue) {
        int[] rgb = model.getDmcRgb(selectedValue);
        int[] listRgb = view.getListElementColor();

        if (Arrays.equals(rgb, listRgb)) {
            view.setListElementColor(new Color(255-listRgb[0], 255-listRgb[1],255 - listRgb[2]));
        }
        view.setListColor(rgb[0],rgb[1],rgb[2]);

    }

    @Override
    public void replaceColor(String color) {
        if (!(color == null)) {
            model.updateColorInImage(color, xCoordinate, yCoordinate);

        }

    }

    @Override
    public void removeColorFromImage(String selectedValue) {
        model.removeColorFromImage(yCoordinate,xCoordinate);
    }

    @Override
    public void handleMultipleSelection(List<String> selectedValuesList) {
        model.setDmc(selectedValuesList);
        model.crossStitch();
    }
}
