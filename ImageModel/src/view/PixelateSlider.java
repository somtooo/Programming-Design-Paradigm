package view;

import controller.TotalFeatures;

public class PixelateSlider extends AbstractSlider {

    public PixelateSlider() {
        super();
    }
    @Override
    public void setFeatures(TotalFeatures controller) {
        slider.addChangeListener(e -> controller.pixelateImage());
    }

    @Override
    public void setValue(int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);
    }
}
