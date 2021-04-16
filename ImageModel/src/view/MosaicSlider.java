package view;

import controller.TotalFeatures;

public class MosaicSlider extends AbstractSlider implements SliderInterface {

    public MosaicSlider() {
        super();
    }


    @Override
    public void setFeatures(TotalFeatures controller) {
        slider.addChangeListener(e -> controller.mosaicImage());
    }

    @Override
    public void setValue(int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);
    }
}
