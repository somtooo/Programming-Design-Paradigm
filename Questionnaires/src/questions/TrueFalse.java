package questions;

public class TrueFalse extends AbstractQuestion {
  private final String answer;

  public TrueFalse(String question, String answer) throws IllegalArgumentException {
    super(question);
//    if (!answer.toLowerCase().trim().equals("true")
//        | !answer.toLowerCase().trim().equals("false")) {
//      throw new IllegalArgumentException(" Answer must be True or False");
//    }

    this.answer = answer;
  }

  @Override
  public String answer(String theAnswer) {
    if (theAnswer.toLowerCase().trim().equals(answer.toLowerCase().trim())) {
      return "Correct";
    }
    return "Incorrect";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion aQuestion = (AbstractQuestion) o;
      return aQuestion.compareToTrueFalse(this);
    }
    return 1;
  }

  @Override
  public int compareToTrueFalse(Question o) {
    System.out.println(this.getText());
    System.out.println(o.getText());
    System.out.println();
    return o.getText().compareTo(this.getText());
  }

  @Override
  public int compareToMultipleChoice(Question o) {
    return 1;
  }

  @Override
  public int compareToMultipleSelect(Question o) {
    return 1;
  }
}
