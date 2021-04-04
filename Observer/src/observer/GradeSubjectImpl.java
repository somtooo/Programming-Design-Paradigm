package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Grade Subject Interface. Represents methods to notify observers when theres a new
 * grade record.
 */
public class GradeSubjectImpl implements GradeSubject {
  private final List<GradeObserver> observers;

  public GradeSubjectImpl() {
    observers = new ArrayList<>();
  }

  @Override
  public void attach(GradeObserver observer) {
    observers.add(observer);
  }

  @Override
  public void detach(GradeObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notify(GradeRecord record) {
    for (GradeObserver observer : observers) {
      observer.update(record);
    }
  }

  @Override
  public String toString() {
    return String.format("%s ", observers);
  }
}
