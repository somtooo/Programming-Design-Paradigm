import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import observer.CoopEligible;
import observer.GoodStanding;
import observer.Grade;
import observer.GradeObserver;
import observer.GradeRecord;
import observer.GraduationEligible;
import org.junit.Before;
import org.junit.Test;



/** Tests the GoodStanding observer for correctness. */
public class ObserversTest {

  GradeObserver goodStanding;
  GradeObserver coopEligible;
  GradeObserver graduationEligible;

  /**
   * Sets  up the object to be reused by other test functions.
   */
  @Before
  public void setUp() {
    goodStanding = new GoodStanding();
    coopEligible = new CoopEligible();
    graduationEligible = new GraduationEligible();
  }

  @Test
  public void goodStandingIsSatisfied() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.A, 4);
    assertFalse(goodStanding.isSatisfied());

    goodStanding.update(gradeRecord);
    goodStanding.update(secondGradeRecord);
    assertTrue(goodStanding.isSatisfied());

    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.F, 4);
    GradeRecord fourthGradeRecord = new GradeRecord("CS5500", Grade.F, 4);
    goodStanding.update(thirdGradeRecord);
    goodStanding.update(fourthGradeRecord);
    assertFalse(goodStanding.isSatisfied());
  }

  @Test
  public void goodStandingRetookCourseSatisfied() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.A, 4);
    goodStanding.update(gradeRecord);
    goodStanding.update(secondGradeRecord);
    assertTrue(goodStanding.isSatisfied());

    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.F, 4);
    GradeRecord fourthGradeRecord = new GradeRecord("CS5500", Grade.F, 4);
    goodStanding.update(thirdGradeRecord);
    goodStanding.update(fourthGradeRecord);
    assertFalse(goodStanding.isSatisfied());

    GradeRecord retakeGradeRecord = new GradeRecord("CS5600", Grade.A, 4);
    goodStanding.update(retakeGradeRecord);
    assertTrue(goodStanding.isSatisfied());
  }

  @Test
  public void goodStandingEqualCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.D, 4);
    goodStanding.update(gradeRecord);
    goodStanding.update(secondGradeRecord);
    assertFalse(goodStanding.isSatisfied());

    GradeRecord equalRecord = new GradeRecord("CS5010", Grade.A, 4);
    goodStanding.update(equalRecord);
    assertFalse(goodStanding.isSatisfied());
  }

  @Test
  public void testGoodStandingWhenRetookCourseGradeIsLower() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    goodStanding.update(gradeRecord);
    goodStanding.update(secondGradeRecord);
    assertTrue(goodStanding.isSatisfied());

    GradeRecord lowerRecord = new GradeRecord("CS5800", Grade.F, 4);
    goodStanding.update(lowerRecord);
    assertTrue(goodStanding.isSatisfied());
  }

  @Test
  public void testCoopIsSatisfied() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.A, 4);
    assertFalse(coopEligible.isSatisfied());

    coopEligible.update(gradeRecord);
    coopEligible.update(secondGradeRecord);
    assertFalse(coopEligible.isSatisfied());

    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.F, 4);
    GradeRecord fourthGradeRecord = new GradeRecord("CS5500", Grade.F, 4);
    coopEligible.update(thirdGradeRecord);
    coopEligible.update(fourthGradeRecord);
    assertFalse(coopEligible.isSatisfied());
  }

  @Test
  public void testCoopRetookCourseSatisfied() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.A, 4);
    coopEligible.update(gradeRecord);
    coopEligible.update(secondGradeRecord);
    assertFalse(coopEligible.isSatisfied());

    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.F, 4);
    GradeRecord fourthGradeRecord = new GradeRecord("CS5500", Grade.F, 4);
    coopEligible.update(thirdGradeRecord);
    coopEligible.update(fourthGradeRecord);
    assertFalse(coopEligible.isSatisfied());

    GradeRecord retakeGradeRecord = new GradeRecord("CS5600", Grade.A, 4);
    coopEligible.update(retakeGradeRecord);
    assertTrue(coopEligible.isSatisfied());
  }

  @Test
  public void coopEqualCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.D, 4);
    coopEligible.update(gradeRecord);
    coopEligible.update(secondGradeRecord);
    assertFalse(coopEligible.isSatisfied());

    GradeRecord thirdRecord = new GradeRecord("CS5433", Grade.A, 4);
    GradeRecord equalRecord = new GradeRecord("CS5010", Grade.A, 4);
    coopEligible.update(thirdRecord);
    coopEligible.update(equalRecord);
    assertFalse(coopEligible.isSatisfied());
  }

  @Test
  public void testCoopWhenRetookCourseGradeIsLower() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5200", Grade.B, 4);
    GradeRecord fourthRecord = new GradeRecord("CS5300", Grade.B, 4);
    coopEligible.update(gradeRecord);
    coopEligible.update(secondGradeRecord);
    coopEligible.update(thirdGradeRecord);
    coopEligible.update(fourthRecord);
    assertTrue(coopEligible.isSatisfied());

    GradeRecord lowerRecord = new GradeRecord("CS5800", Grade.F, 4);

    coopEligible.update(lowerRecord);
    assertTrue(coopEligible.isSatisfied());
  }

  @Test
  public void testGraduationEligibleMasters() {
    assertFalse(graduationEligible.isSatisfied());
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5500", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertTrue(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleMastersSecondCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertTrue(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleMastersFailCourse() {
    assertFalse(graduationEligible.isSatisfied());
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.C, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5500", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleMastersFailCourseAndRetake() {
    assertFalse(graduationEligible.isSatisfied());
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.C, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5500", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

    GradeRecord retake = new GradeRecord("CS5010", Grade.B, 4);
    graduationEligible.update(retake);
    assertTrue(graduationEligible.isSatisfied());


  }

  @Test
  public void testGraduationEligibleMastersSkipCourse() {
    assertFalse(graduationEligible.isSatisfied());
    GradeRecord gradeRecord = new GradeRecord("CS50110", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5500", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleAlign() {
    GradeRecord gradeRecord = new GradeRecord("CS5004", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5500", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertTrue(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleAlignSecondCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5004", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.B, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertTrue(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleAlignFailCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5004", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.C, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

  }

  @Test
  public void testGraduationEligibleAlignFailCourseAndRetake() {
    GradeRecord gradeRecord = new GradeRecord("CS5004", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5800", Grade.C, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

    GradeRecord retake = new GradeRecord("CS5800", Grade.B, 4);
    graduationEligible.update(retake);
    assertTrue(graduationEligible.isSatisfied());


  }

  @Test
  public void testGraduationEligibleAlignMissingCourse() {
    GradeRecord gradeRecord = new GradeRecord("CS5004", Grade.A, 4);
    GradeRecord secondGradeRecord = new GradeRecord("CS5300", Grade.A, 4);
    GradeRecord thirdGradeRecord = new GradeRecord("CS5600", Grade.B, 4);
    graduationEligible.update(gradeRecord);
    graduationEligible.update(secondGradeRecord);
    graduationEligible.update(thirdGradeRecord);
    assertFalse(graduationEligible.isSatisfied());

  }


}
