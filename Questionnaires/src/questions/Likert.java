package questions;

public class Likert extends AbstractQuestion {

  public Likert(String question) {
    super(question);
  }

  @Override
  public String answer(String answer) {
    if (answer.trim().matches("[1-5]")) {
      return "Correct";
    }
    return "Incorrect";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQuestion = (AbstractQuestion) o;
      return aQuestion.compareToLikert(this);
    }
    return 1;
  }

  @Override
  public int compareToLikert(Question o) {
    return (question.compareTo(o.getText()));
  }
}
