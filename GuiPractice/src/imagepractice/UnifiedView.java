package imagepractice;


import imagepractice.controller.TotalFeatures;

import java.awt.image.BufferedImage;

public class UnifiedView implements UnifiedViewInterface{
    private PracticeViewInterface practiceView;
    private MenuInterface menuView;

    public UnifiedView () {
        practiceView = new PracticeView();
    }

    @Override
    public void setFeatures(TotalFeatures controller) {
        practiceView.setFeatures(controller);

    }

    @Override
    public void start() {
        practiceView.start();
    }

    @Override
    public void setSliderListenerToBlur(TotalFeatures controller) {
        practiceView.setSliderListenerToBlur(controller);
    }

    @Override
    public String getImage() {
        return practiceView.getImage();
    }

    @Override
    public void setImagePanel(BufferedImage image) {
        practiceView.setImagePanel(image);

    }

}
