package view;

import controller.TotalFeatures;

import java.awt.image.BufferedImage;

public interface ViewInterface {
    public void setImagePanel(BufferedImage image);

    String getImage();

    void start();

    void setFeatures(TotalFeatures controller);

    void setSliderListenerToBlur(TotalFeatures controller);

    int getSliderValue();

    public void throwError(String error, String errorType);

}
