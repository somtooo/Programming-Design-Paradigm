package imagepractice;

import imagepractice.controller.TotalFeatures;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface PracticeViewInterface {

    public void setImagePanel(BufferedImage image);

    String getImage();

    void start();

    void setFeatures(TotalFeatures controller);

    void setSliderListenerToBlur(TotalFeatures controller);
}
