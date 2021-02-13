package questions;

import java.util.ArrayList;
import java.util.Collections;

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
    return o.getText().compareTo(this.getText());
  }

    public static void main(String[] args) {
      ArrayList<Question> questions = new ArrayList<Question>();
      Likert a = new Likert("Which one of these options accurately describes your experience in Assignment 2");
      MultipleSelect ms = new MultipleSelect("types of pimples","1","1 2","3 4");
      MultipleChoice mc = new MultipleChoice("are dogs bad","2","1 2","3 4");
      TrueFalse tf = new TrueFalse("Anne","true");
      TrueFalse t = new TrueFalse("Tom","false");
      MultipleChoice mc2 = new MultipleChoice("are dogs bad like bad","2","1 2","3 4");



      questions.add(tf);
      questions.add(ms);
      questions.add(mc);

      questions.add(a);
      questions.add(t);

//        System.out.println(t.compareTo(mc));
//      System.out.println(mc.compareTo(t));


      Collections.sort(questions);
      for (Question i : questions){
          System.out.println(i.getText());
      }
      System.out.println(questions);


  }
}
