package view;

import controller.TotalFeatures;

public class SharpenSlider extends AbstractSlider implements  SliderInterface{

    public SharpenSlider() {
        super();
    }

    @Override
    public void setFeatures(TotalFeatures controller) {
        slider.addChangeListener(e -> controller.sharpenImage());

    }

    @Override
    public void setValue(int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);
    }
}
