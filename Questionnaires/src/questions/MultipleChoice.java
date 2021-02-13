package questions;

public class MultipleChoice extends AbstractQuestion {
  private final String correctAnswer;
  private final String options;

  public MultipleChoice(String question, String correctAnswer, String options)
      throws IllegalArgumentException {
    super(question);
    if (!options.replaceAll("\\s+", "").matches("[1-8]+")) {
      throw new IllegalArgumentException(" numbers from 1 - 8 needed");
    } else if (options.replaceAll("\\s+", "").length() < 3 | options.trim().length() > 8) {
      throw new IllegalArgumentException("3 to 8 options needed");
    } else if (!correctAnswer.trim().matches("[1-8]")) {
      throw new IllegalArgumentException("Single Number from 1-8");
    } else if (correctAnswer.trim().length() != 1) {
      throw new IllegalArgumentException("Only choose one option");
    } else if (!options.replaceAll("\\s+", "").contains(correctAnswer.trim())) {
      throw new IllegalArgumentException("Answer must be in option");
    }
    this.correctAnswer = correctAnswer;
    this.options = options;
  }

  @Override
  public String answer(String answer) {
    if (answer.trim().equals(correctAnswer.toLowerCase().trim())
        && options.replaceAll("\\s+", "").contains(answer.trim())) {
      return "Correct";
    }
    return "Incorrect";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQuestion = (AbstractQuestion) o;
      return aQuestion.compareToMultipleChoice(this);
    }
    return 1;
  }

  @Override
  public int compareToMultipleChoice(Question o) {
    return question.compareTo(o.getText());
  }
}
