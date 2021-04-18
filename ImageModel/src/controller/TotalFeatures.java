package controller;

import java.util.List;

public interface TotalFeatures {
    void setView();

    void loadImage();

    void blurImage();

    void sharpenImage();

    void mosaicImage();

    void pixelateImage();

    void sepiaImage();

    void greyscaleImage();

    void saveImage();

    void generatePattern();

    void ditherImage();

    void runBatchView();


    void update();

    void loadDmc();

    void handleColorClick(String selectedValue);

    void replaceColor(String color);

    void removeColorFromImage(String selectedValue);

    void handleMultipleSelection(List<String> selectedValuesList);
}
