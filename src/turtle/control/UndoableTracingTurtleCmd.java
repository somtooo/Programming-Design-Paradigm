package turtle.control;

import turtle.tracingmodel.TracingTurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public interface UndoableTracingTurtleCmd extends TracingTurtleCommand {
  /**
   * Undo command for the tracing turtle.
   * 
   * @param m the model to use
   */
  void undo(TracingTurtleModel m);
}
