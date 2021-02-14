import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;


/**
 * Class to test the Question Interface for correctness.
 */
public class QuestionTest {
  Question likert;
  Question multipleChoice;
  Question multipleSelect;
  Question trueFalse;
  ArrayList<Question> questions;

  /**
   * Setup object to be used for testing in other functions.
   */
  @Before
  public void setUp() {
    questions = new ArrayList<Question>();
    likert =
        new Likert(
            "Which one of these options accurately describes your experience in Assignment 2");
    multipleSelect = new MultipleSelect("types of pimples", "1", "1", "2", "3");
    multipleChoice = new MultipleChoice("are dogs bad", "2", "1", "3", "4", "5");
    trueFalse = new TrueFalse("The color of the sky is blue", "true");
    multipleChoice = new MultipleChoice("are dogs bad like bad", "2", "1", "2", "3");
  }

  @Test
  public void testLikert() {
    assertEquals(
        "Question: Which one of these options accurately "
                + "describes your experience in Assignment 2 ",
        likert.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    new Likert("");
  }

  @Test
  public void testMultipleChoice() {
    assertEquals(
        "Question: are dogs bad like bad, Answer: 2, Options: [1, 2, 3]",
        multipleChoice.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceMoreThanEight() {
    new MultipleChoice("are dogs bad", "2", "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceLessThanThree() {
    new MultipleChoice("are dogs bad", "2", "1", "3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultipleChoiceNotInOption() {
    new MultipleChoice("types of pimples", "4", "1", "2", "2");
  }

  @Test
  public void testMultipleSelect() {
    assertEquals(
        "Question: types of pimples, Answer: 1, Options: [1, 2, 3]", multipleSelect.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultipleSelectLessThanThree() {
    new MultipleSelect("types of pimples", "1", "1", "2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultipleSelectMoreThanEight() {
    new MultipleSelect("types of pimples", "1", "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultipleSelectNotInOption() {
    new MultipleSelect("types of pimples", "0", "1", "2", "3", "4", "5", "6", "7", "8");
  }

  @Test
  public void testTrueFalse() {
    assertEquals("Question: The color of the sky is blue, Answer: true ", trueFalse.toString());
  }

  @Test
  public void testCompareToTrueFalse() {

    assertEquals(-1, trueFalse.compareTo(likert));
    assertEquals(-1, trueFalse.compareTo(multipleChoice));
    assertEquals(-1, trueFalse.compareTo(multipleSelect));
    Question testTrueFalse = new TrueFalse("Goats are black", "false");
    assertEquals(13, trueFalse.compareTo(testTrueFalse));
  }

  @Test
  public void testCompareToLikert() {
    assertEquals(1, likert.compareTo(trueFalse));
    assertEquals(1, likert.compareTo(multipleChoice));
    assertEquals(1, likert.compareTo(multipleSelect));
    Question testLikert = new Likert("The professor is not that good");
    assertEquals(3, likert.compareTo(testLikert));
  }

  @Test
  public void testCompareToMultipleChoice() {
    assertEquals(1, multipleChoice.compareTo(trueFalse));
    assertEquals(-1, multipleChoice.compareTo(likert));
    assertEquals(-1, multipleChoice.compareTo(multipleSelect));
    Question testMultipleChoice =
            new MultipleChoice("How many shoes are there", "2", "1", "2", "3");
    assertEquals(25, multipleChoice.compareTo(testMultipleChoice));
  }

  @Test
  public void testCompareToMultipleSelect() {
    assertEquals(1, multipleSelect.compareTo(trueFalse));
    assertEquals(-1, multipleSelect.compareTo(likert));
    assertEquals(1, multipleSelect.compareTo(multipleChoice));
    Question testMultipleSelect =
            new MultipleSelect("How many shoes are there", "2", "1", "2", "3");
    assertEquals(44, multipleSelect.compareTo(testMultipleSelect));
  }

  @Test
  public void testArraySort() {
    questions.add(multipleChoice);
    questions.add(likert);
    questions.add(trueFalse);
    questions.add(multipleSelect);
    Collections.sort(questions);

    assertEquals(
        "[Question: The color of the sky is blue, Answer: true , "
            + "Question: are dogs bad like bad, "
            + "Answer: 2, Options: [1, 2, 3], Question: types of pimples, "
                + "Answer: 1, Options: [1, 2, 3], "
            + "Question: Which one of these options accurately describes your "
            + "experience in Assignment 2 ]",
        questions.toString());
  }

  @Test
  public void getText() {
    assertEquals(
        "Which one of these options accurately describes your experience in Assignment 2",
        likert.getText());
    assertEquals("types of pimples", multipleSelect.getText());
    assertEquals("are dogs bad like bad", multipleChoice.getText());
    assertEquals("The color of the sky is blue", trueFalse.getText());
  }

  @Test
  public void answerTrue() {
    assertEquals("Correct", likert.answer("1"));
    assertEquals("Correct", multipleSelect.answer("1"));
    assertEquals("Correct", multipleChoice.answer("2"));
    assertEquals("Correct", trueFalse.answer("True"));
  }

  @Test
  public void answerFalse() {
    assertEquals("Incorrect", likert.answer("6"));
    assertEquals("Incorrect", multipleSelect.answer("2"));
    assertEquals("Incorrect", multipleChoice.answer("1"));
    assertEquals("Incorrect", trueFalse.answer("False"));
  }
}
