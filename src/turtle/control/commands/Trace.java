package turtle.control.commands;

import turtle.control.UndoableTracingTurtleCmd;
import turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public class Trace implements UndoableTracingTurtleCmd {
  private double d;

  /**
   * Constructor.
   * 
   * @param d the distance to trace.
   */
  public Trace(Double d) {
    this.d = d;
  }

  @Override
  public void go(TracingTurtleModel m) {
    m.trace(this.d);
  }

  @Override
  public void undo(TracingTurtleModel m) {

  }
}
