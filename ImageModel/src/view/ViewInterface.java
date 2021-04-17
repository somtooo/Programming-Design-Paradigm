package view;

import controller.TotalFeatures;

import java.awt.image.BufferedImage;

public interface ViewInterface {
    public void setImagePanel(BufferedImage image);

    String getImage();

    void start();

    void setFeatures(TotalFeatures controller);

    void setSliderListenerToBlur(TotalFeatures controller, int min, int max);

    void setSliderListenerToSharpen(TotalFeatures controller, int min, int max);

    void setSliderListenerToPixelate(TotalFeatures controller, int min, int max);

    void setSliderListenerToMosaic(TotalFeatures controller, int min, int max);

    void setSliderListenerToReduce(TotalFeatures controller, int min, int max);


    void setSliderValue(int min, int max);

    int getSliderValue();

    void hideSlider();

    void initiateBatchView();

    String getInputDialog(String message, String command);

    public void throwError(String error, String errorType);

    public String getImageSaveName();

    public  void throwSuccess(String success, String successType);

}
