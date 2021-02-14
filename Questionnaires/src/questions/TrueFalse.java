package questions;

/**
 * This class represents the True/False question type builder that can be answered in either True or
 * False.
 */
public class TrueFalse extends AbstractQuestion {
  private final String answer;

  /**
   * Initializes the TrueFalse object with the required parameters.
   *
   * @param question the True/False type question.
   * @param answer the right answer which is either True or False.
   */
  public TrueFalse(String question, String answer) {
    super(question);
    this.answer = answer;
  }

  @Override
  public String answer(String theAnswer) {
    if (theAnswer.toLowerCase().trim().equals(answer.toLowerCase().trim())) {
      return CORRECT;
    }
    return INCORRECT;
  }

  /**
   * Compares other object of type AbstractQuestion to determine the right ordering.
   *
   * @param object the object to compare too. *
   * @return an int value that determines the ordering.
   */
  @Override
  public int compareTo(Question object) {
    if (object instanceof AbstractQuestion) {
      AbstractQuestion question = (AbstractQuestion) object;
      return question.compareToTrueFalse(this);
    }
    throw new IllegalArgumentException("Not an Abstract class");
  }

  /**
   * Compares same TrueFalse object and orders lexicographically based on their questions.
   *
   * @param object TrueFalse object to compare to.
   * @return an int determining which is greater or smaller.
   */
  @Override
  public int compareToTrueFalse(Question object) {
    return object.getText().compareTo(this.getText());
  }

  /**
   * Compares TrueFalse object with Multiple choice to enforce true false to be greater.
   *
   * @param object Multiple choice object that's to be compared.
   * @return an int determining true false as greater.
   */
  @Override
  public int compareToMultipleChoice(Question object) {
    return 1;
  }

  /**
   * Compares TrueFalse object with Multiple select to enforce true false to be greater.
   *
   * @param object Multiple select object that's to be compared.
   * @return an int determining true false as greater.
   */
  @Override
  public int compareToMultipleSelect(Question object) {
    return 1;
  }

  /**
   * String representation of the objects properties.
   *
   * @return string that describes object
   */
  @Override
  public String toString() {
    return String.format("Question: %s, Answer: %s ", question, answer);
  }
}
