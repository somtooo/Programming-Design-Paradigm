package observer;

import java.util.List;

/** Represents methods that carry out various checks on a list of grade records. */
public abstract class AbstractObservable implements GradeObserver {

  /**
   * Updates the list based on various conditions.
   *
   * @param record the grade record to update the list with.
   * @param records the list to update.
   */
  protected void performUpdate(GradeRecord record, List<GradeRecord> records) {
    if (noEqual(record, records)) {
      int courseIndex = checkIfRetookCourse(record, records);
      if (courseIndex >= 0) {
        handleCourseRetook(records, record, courseIndex);
      } else if (courseIndex == -1) {
        records.add(record);
      }
    }
  }

  /**
   * Checks to see if theres an equal record in records.
   *
   * @param record the new record that we are using to check for equality.
   * @return boolean representing if theres an equal or not.
   */
  protected boolean noEqual(GradeRecord record, List<GradeRecord> records) {
    for (GradeRecord equalRecord : records) {
      if (equalRecord.equals(record)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks to see if theres a course was retaken, if it was it returns the index of the first grade
   * only if the new grade is higher.
   *
   * @param record the record to check if course was retook.
   * @return the index of the old grade if the new grade is higher, -1 if no matching course was
   *     found, -2 if the old grade is greater than the new grade.
   */
  protected int checkIfRetookCourse(GradeRecord record, List<GradeRecord> records) {
    int index = 0;
    for (GradeRecord duplicate : records) {
      if (duplicate.getCourse().equalsIgnoreCase(record.getCourse())) {
        if (duplicate.getGrade().getGradeValue() < record.getGrade().getGradeValue()) {
          return index;
        } else if (duplicate.getGrade().getGradeValue() > record.getGrade().getGradeValue()) {
          return -2;
        }
      }
      index++;
    }
    return -1;
  }

  /**
   * Updates the totalQualityPoints, totalCreditHours and records appropriately if new course grade
   * is higher than old course grade.
   *
   * @param record new Course grade which is higher.
   * @param duplicateGradeIndex index of the old course grade.
   */
  protected void handleCourseRetook(
      List<GradeRecord> records, GradeRecord record, int duplicateGradeIndex) {
    records.remove(duplicateGradeIndex);
    records.add(record);
  }

  /**
   * Calculates gpa when given a list of record.
   *
   * @param records the list of record to calculate GPA from.
   * @return the gpa
   */
  protected double calculateGpa(List<GradeRecord> records) {
    double totalQualityPoints = getTotalQualityPoints(records);
    double totalCreditHours = getTotalCredits(records);

    return totalQualityPoints / totalCreditHours;
  }

  /**
   * Calculates total credit hours taken when given a list of record.
   *
   * @param records the list of record to total credit hours from.
   * @return the total credit hours.
   */
  protected double getTotalCredits(List<GradeRecord> records) {
    double credits = 0;
    for (GradeRecord record : records) {
      credits = credits + record.getCredits();
    }
    return credits;
  }

  /**
   * Calculates total quality points when given a list of record.
   *
   * @param records the list of record to total quality points from.
   * @return the total quality points.
   */
  private double getTotalQualityPoints(List<GradeRecord> records) {
    double qualityPoints = 0;
    for (GradeRecord record : records) {
      qualityPoints = qualityPoints + (record.getCredits() * record.getGrade().getGradeValue());
    }
    return qualityPoints;
  }
}
