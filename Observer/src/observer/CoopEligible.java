package observer;

import java.util.ArrayList;

/**
 * Implements the GradeObserver interface. Represents methods that checks to see if a student is
 * Coop Eligible.
 */
public class CoopEligible extends AbstractObservable  {
  private final ArrayList<GradeRecord> records;

  public CoopEligible() {
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
    return (calculateGpa(records) >= 3.0 && getTotalCredits(records) >= 16);
  }
}
