package observer;

import java.util.ArrayList;

/**
 * Implements the GradeObserver interface. Represents methods that checks to see if a student is
 * still in good standing.
 */
public class GoodStanding extends AbstractObservable  {
  protected ArrayList<GradeRecord> records;

  public GoodStanding() {
    records = new ArrayList<>();
  }

  @Override
  public void update(GradeRecord record) {
    performUpdate(record, records);
  }



  @Override
  public boolean isSatisfied() {
    if (records.size() == 0) {
      return false;
    }
    return calculateGpa(records) >= 3.0;
  }
}
