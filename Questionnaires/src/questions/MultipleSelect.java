package questions;

public class MultipleSelect extends AbstractQuestion {
  private final String correctAnswer;
  private final String options;

  public MultipleSelect(String question, String correctAnswer, String options)
      throws IllegalArgumentException {
    super(question);

    if (!options.replaceAll("\\s+", "").matches("[1-8]+")) {
      throw new IllegalArgumentException(" numbers from 1 - 8 needed");
    } else if (options.replaceAll("\\s+", "").length() < 3 | options.trim().length() > 8) {
      throw new IllegalArgumentException("3 to 8 options needed");
    } else if (!correctAnswer.replaceAll("\\s+", "").matches("[1-8]")) {
      throw new IllegalArgumentException("Numbers Only");
    } else if (correctAnswer.replaceAll("\\s+", "").length() < 2) {
      throw new IllegalArgumentException("More than one answer is required");
    } else if (!options.replaceAll("\\s+", "").contains(correctAnswer.replaceAll("\\s+", ""))) {
      throw new IllegalArgumentException("Answer must be in option");
    }
    this.correctAnswer = correctAnswer;
    this.options = options;
  }

  @Override
  public String answer(String answer) {
    if (answer.trim().equals(correctAnswer.toLowerCase().trim())
        && options.replaceAll("\\s+", "").contains(answer.replaceAll("\\s+", ""))) {
      return "Correct";
    }
    return "Incorrect";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQuestion = (AbstractQuestion) o;
      return aQuestion.compareToMultipleSelect(this);
    }
    return 1;
  }

  @Override
  public int compareToMultipleSelect(Question o) {
    return question.compareTo(o.getText());
  }
}
