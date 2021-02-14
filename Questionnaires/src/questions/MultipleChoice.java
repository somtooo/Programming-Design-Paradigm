package questions;

import java.util.Arrays;

/**
 * This class represents the MultipleChoice question type builder that has an answer per multiple
 * options.
 */
public class MultipleChoice extends AbstractQuestion {
  private final String correctAnswer;
  private final String[] options;

  /**
   * Initializes the MultipleChoice object with the required parameters.
   *
   * @param question the multiple choice type question
   * @param correctAnswer the right answer to the question.
   * @param option the options that the correct answer will be taken from
   * @throws IllegalArgumentException if the number of options isn't between 3 or 8.
   */
  public MultipleChoice(String question, String correctAnswer, String... option)
      throws IllegalArgumentException {

    super(question);
    if (option.length < 3 | option.length > 8) {
      throw new IllegalArgumentException("Only three to eight options allowed");
    }

    try {
      Arrays.asList(option).contains(option[Integer.parseInt(correctAnswer) - 1]);
    } catch (Exception e) {
      throw new IllegalArgumentException("Answer not in option");
    }

    this.correctAnswer = correctAnswer;
    this.options = option;
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
      return question.compareToMultipleChoice(this);
    }
    throw new IllegalArgumentException("not abstract question class");
  }

  /**
   * Compares same MultipleChoice object and orders lexicographically based on their questions.
   *
   * @param object MultipleChoice object to compare to.
   * @return an int determining which is greater or smaller.
   */
  @Override
  public int compareToMultipleChoice(Question object) {
    return object.getText().compareTo(this.getText());
  }

  /**
   * Compares MultipleChoice object with Multiple select to enforce true false to be greater.
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
    return String.format(
        "Question: %s, Answer: %s, Options: %s", question, correctAnswer, Arrays.toString(options));
  }
}
