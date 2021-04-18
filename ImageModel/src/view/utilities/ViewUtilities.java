package view.utilities;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

/** Represents methods that can be used by all views for various purposes. */
public class ViewUtilities {

  /**
   * Centres a component on a screen when the view component is launched.
   *
   * @param frame the view component.
   * @param width the size of the view component.
   * @param height the height of the view component.
   */
  public static void centreWindow(Component frame, int width, int height) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

    // calculate perfect center
    int perf_x = x - width / 2;
    int perf_y = y - height / 2;

    frame.setLocation(perf_x, perf_y);
  }
}
