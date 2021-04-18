package view.utilities;

import javax.swing.*;
import java.awt.*;

public class ViewUtilities {

    public static void centreWindow(Component frame, int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

        // calculate perfect center
        int perf_x = (int) x - width/2;
        int perf_y = (int) y - height/2;

        frame.setLocation(perf_x, perf_y);
    }


}
