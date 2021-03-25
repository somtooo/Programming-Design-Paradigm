package turtle.tracingmodel;

import turtle.model.Position2D;

import java.util.Objects;


/**
 * Created by blerner on 10/10/16.
 */
public final class Line {
  private final Position2D start;
  private final Position2D end;

  /**
   * Constructor.
   * 
   * @param start the position of the start of the line
   * @param end   the position of the end of the line
   */
  public Line(Position2D start, Position2D end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Accessor for the start of the line.
   * 
   * @return the start of this line
   */
  public Position2D getStart() {
    return start;
  }

  /**
   * Accessor for the end of this line.
   * 
   * @return the end of this line
   */
  public Position2D getEnd() {
    return end;
  }

  @Override
  public String toString() {
    return String.format("%s--%s", this.start.toString(), this.end.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Line)) {
      return false;
    }

    Line line = (Line) o;

    return (this.start.equals(line.start) && this.end.equals(line.end))
        || (this.end.equals(line.start) && this.start.equals(line.end));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}
