package questions;


/**
 * This class represents the a Likert question type builder that can be answered on a fixed, 5-point
 * scale.
 */
public class Likert extends AbstractQuestion {

  /**
   * Initializes a Likert Object with the provided parameters.
   *
   * @param question the Likert type question.
   */
  public Likert(String question) {
    super(question);
  }


  @Override
  public String answer(String answer) {
    if (answer.trim().matches("[1-5]")) {
      return CORRECT;
    }
    return INCORRECT;
  }

  /**
   * Compares other object of type AbstractQuestion to determine the right ordering.
   *
   * @param object the object to compare too.
   * @return an int value that determines the ordering.
   */
  @Override
  public int compareTo(Question object) {
    if (object instanceof AbstractQuestion) {
      AbstractQuestion question = (AbstractQuestion) object;
      return question.compareToLikert(this);
    }
    throw new IllegalArgumentException("Not abstract question class");
  }

  /**
   * Compares same likert object and orders lexicographically based on their questions.
   *
   * @param object likert object to compare to.
   * @return an int determining which is greater or smaller.
   */
  @Override
  public int compareToLikert(Question object) {
    return object.getText().compareTo(this.getText());
  }

  /**
   * String representation of the objects properties.
   *
   * @return string that describes object
   */
  @Override
  public String toString() {
    return String.format("Question: %s ", question);
  }
}
