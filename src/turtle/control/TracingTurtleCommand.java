package turtle.control;

import turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public interface TracingTurtleCommand {
  /**
   * Starting point for the controller.
   * 
   * @param m the model to use
   */
  void go(TracingTurtleModel m);
}
