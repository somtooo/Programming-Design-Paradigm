package view;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractSlider extends JPanel implements  SliderInterface  {
    protected JSlider slider;
    public AbstractSlider()  {
        setLayout(new FlowLayout());
        setBackground(new Color(21,25,28));
        slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slider.setBackground(new Color(21,25,28));
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        this.add(slider);
    }
}
