package turtle.control.commands;

import turtle.control.TracingTurtleCommand;
import turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public class Turn implements TracingTurtleCommand {
  private double d;

  /**
   * Constructor.
   * 
   * @param d the angle to turn.
   */
  public Turn(Double d) {
    this.d = d;
  }

  @Override
  public void go(TracingTurtleModel m) {
    m.turn(this.d);
  }

  // @Override
  // public void undo(TracingTurtleModel m) {
  // m.turn(-this.d);
  // }
}
