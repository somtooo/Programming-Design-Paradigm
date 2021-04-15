package imagepractice;

import imagepractice.controller.Controller;
import imagepractice.controller.TotalFeatures;

import java.awt.image.BufferedImage;

public interface UnifiedViewInterface {

    void setFeatures(TotalFeatures controller);

    String getImage();

    public void setImagePanel(BufferedImage image);

    void start();

    void setSliderListenerToBlur(TotalFeatures controller);


}
