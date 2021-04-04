import static org.junit.Assert.assertEquals;

import observer.Grade;
import observer.GradeObserver;
import observer.GradeRecord;
import observer.GradeSubject;
import observer.GradeSubjectImpl;
import org.junit.Before;
import org.junit.Test;



/** Tests the subject for correctness. */
public class GradeSubjectImplTest {
  GradeSubject subject;

  @Before
  public void setUp() {
    subject = new GradeSubjectImpl();
  }

  @Test
  public void attach() {
    StringBuilder log = new StringBuilder();
    GradeObserver observer = new GoodStandingMock(log, "observer1");
    GradeObserver observerTwo = new GoodStandingMock(log, "observer2");

    subject.attach(observer);
    subject.attach(observerTwo);
    assertEquals("[observer1, observer2] ", subject.toString());

  }

  @Test
  public void detach() {
    StringBuilder log = new StringBuilder();
    GradeObserver observer = new GoodStandingMock(log, "observer1");
    GradeObserver observerTwo = new GoodStandingMock(log, "observer2");

    subject.attach(observer);
    subject.attach(observerTwo);
    assertEquals("[observer1, observer2] ", subject.toString());
    subject.detach(observerTwo);
    assertEquals("[observer1] ", subject.toString());

  }

  @Test
  public void testNotify() {
    StringBuilder log = new StringBuilder();
    GradeObserver observer = new GoodStandingMock(log, "observer1");
    GradeRecord gradeRecord = new GradeRecord("CS5010", Grade.A, 4);
    subject.attach(observer);
    subject.notify(gradeRecord);
    assertEquals("GradeRecord[course=CS5010, grade=A, credits=4]", log.toString());
  }
}
