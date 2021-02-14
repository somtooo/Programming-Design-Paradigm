package questions;

import java.util.Arrays;

/**
 * This class represents the MultipleSelect question type builder that has more than one answer per
 * multiple options.
 */
public class MultipleSelect extends AbstractQuestion {

  private final String correctAnswer;
  private final String[] options;

  /**
   * Initializes the MultipleSelect object with the required parameters.
   *
   * @param question the multiple select type question
   * @param correctAnswer the right answer to the question.
   * @param options the options that the correct answer will be taken from
   * @throws IllegalArgumentException if the number of options isn't between 3 or 8.
   */
  public MultipleSelect(String question, String correctAnswer, String... options)
      throws IllegalArgumentException {
    super(question);
    if (options.length < 3 | options.length > 8) {
      throw new IllegalArgumentException("Only three to eight options allowed");
    }

    String trimmed = correctAnswer.replaceAll("\\s+", "");
    char[] c = trimmed.toCharArray();
    try {
      for (char value : c) {
        Arrays.asList(options).contains(options[Character.getNumericValue(value) - 1]);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Answer not in option");
    }

    this.correctAnswer = correctAnswer;
    this.options = options;
  }

  @Override
  public String answer(String answer) {
    if (answer.replaceAll("\\s+", "").equals(correctAnswer.replaceAll("\\s+", ""))) {
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
      return question.compareToMultipleSelect(this);
    }
    throw new IllegalArgumentException("Not an abstract question class");
  }

  /**
   * Compares same MultipleSelect object and orders lexicographically based on their questions.
   *
   * @param object MultipleChoice object to compare to.
   * @return an int determining which is greater or smaller.
   */
  @Override
  public int compareToMultipleSelect(Question object) {
    return object.getText().compareTo(this.getText());
  }

  /**
   * String representation of the objects properties.
   *
   * @return string that describes object
   */
  @Override
  public String toString() {
    return String.format(
        "Question: %s, Answer: %s, Options: %s", question, correctAnswer, Arrays.toString(options));
  }
}
