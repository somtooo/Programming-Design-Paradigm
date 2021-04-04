import observer.GradeObserver;
import observer.GradeRecord;

/** Mock of Good standing class used to test the Grade Subject. */
public class GoodStandingMock implements GradeObserver {
  private final StringBuilder log;
  private final String description;

  public GoodStandingMock(StringBuilder log, String description) {
    this.log = log;
    this.description = description;
  }

  @Override
  public void update(GradeRecord record) {
    log.append(record.toString());
  }

  @Override
  public boolean isSatisfied() {
    return false;
  }

  @Override
  public String toString() {
    return description;
  }
}
