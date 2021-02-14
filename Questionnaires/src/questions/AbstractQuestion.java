package questions;

/**
 * Represents an abstraction of the question class which has the questions of all types and how they
 * are compared.
 */
public abstract class AbstractQuestion implements Question {
  protected final String question;

  /**
   * Initializes the AbstractQuestion type with the required parameters.
   *
   * @param question the type of question.
   * @throws IllegalArgumentException if te question string is empty.
   */
  protected AbstractQuestion(String question) throws IllegalArgumentException {
    if (question.trim().equals("")) {
      throw new IllegalArgumentException("Question cant be empty");
    }
    this.question = question;
  }

  @Override
  public String getText() {
    return question;
  }

  /**
   * Compares to trueFalse to determine correct ordering.
   *
   * @param object the object that compared to true false.
   * @return an int that says trueFalse is always bigger.
   */
  public int compareToTrueFalse(Question object) {
    return -1;
  }

  /**
   * Compares to Likert to determine correct ordering.
   *
   * @param object the object that compared to Likert.
   * @return an int that says Likert is always smaller.
   */
  public int compareToLikert(Question object) {
    return 1;
  }

  /**
   * Compares to Multiple Choice to determine correct ordering.
   *
   * @param object the object that compared to Multiple Choice.
   * @return an int that determines the order.
   */
  public int compareToMultipleChoice(Question object) {
    return -1;
  }

  /**
   * Compares to Multiple Select to determine correct ordering.
   *
   * @param object the object that compared to Multiple Select.
   * @return an int that determines the order.
   */
  public int compareToMultipleSelect(Question object) {
    return -1;
  }
}
