package observer;

import java.util.ArrayList;

/**
 * Implements the GradeObserver interface. Represents methods that checks to see if a student is
 * Graduation Eligible.
 */
public class GraduationEligible extends AbstractObservable implements GradeObserver {
  private final ArrayList<GradeRecord> records;

  public GraduationEligible() {
    records = new ArrayList<>();
  }

  @Override
  public void update(GradeRecord record) {
    performUpdate(record, records);
  }

  @Override
  public boolean isSatisfied() {
    return checkEligibility();
  }

  /**
   * Checks if a student is eligible to graduate.
   *
   * @return a boolean that specifies if the student is eligible.
   */
  private boolean checkEligibility() {
    boolean cs5010 = false;
    boolean cs5800 = false;
    boolean cs5500 = false;
    boolean cs5600 = false;
    boolean cs5004 = false;

    for (GradeRecord record : records) {
      String courseName = record.getCourse();
      double gradeValue = record.getGrade().getGradeValue();
      if (gradeValue >= 3.0) {
        if (courseName.equalsIgnoreCase("cs5010")) {
          cs5010 = true;
        } else if (courseName.equalsIgnoreCase("cs5800")) {
          cs5800 = true;
        } else if (courseName.equalsIgnoreCase("cs5500")) {
          cs5500 = true;
        } else if (courseName.equalsIgnoreCase("cs5600")) {
          cs5600 = true;
        } else if (courseName.equalsIgnoreCase("cs5004")) {
          cs5004 = true;
        }
      }
    }

    boolean canGraduate = cs5010 && cs5800 && cs5500 || cs5010 && cs5800 && cs5600;
    boolean canGraduateAlign = cs5004 && cs5800 && cs5500 || cs5004 && cs5800 && cs5600;

    if (records.size() == 0) {
      return false;
    }

    return canGraduate || canGraduateAlign;
  }
}
