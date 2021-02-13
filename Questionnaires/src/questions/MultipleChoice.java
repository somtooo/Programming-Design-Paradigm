package questions;

import java.util.Arrays;

public class MultipleChoice extends AbstractQuestion {
  private final String correctAnswer;
//  private final String op1;
//  private  String op2;
//  private  String op3;
//  private  String op4;
  private String options;


  public MultipleChoice(String question, String correctAnswer, String ...optionList)
          throws IllegalArgumentException {
      super(question);
      options = "";
      for (String value: optionList) {
        if (! (value.replaceAll("\\s+", "").length() > 8 | value.replaceAll("\\s+", "").length() < 3)){
          throw new
                  IllegalArgumentException("gg");
        }
      }

//    if (options.replaceAll("\\s+", "").matches("[1-8]+")) {
//      throw new IllegalArgumentException(" numbers from 1 - 8 needed");
//    }

//    else if (options.replaceAll("\\s+", "").length() < 3 | options.trim().length() > 8) {
//      throw new IllegalArgumentException("3 to 8 options needed");
//    } else if (!correctAnswer.trim().matches("[1-6]")) {
//      throw new IllegalArgumentException("Single Number from 1-8");
//    } else if (correctAnswer.trim().length() != 1) {
//      throw new IllegalArgumentException("Only choose one option");
//    } else if (!options.replaceAll("\\s+", "").contains(correctAnswer.trim())) {
//      throw new IllegalArgumentException("Answer must be in option");
//    }
    this.correctAnswer = correctAnswer;

  }

//  public MultipleChoice(String question, String correctAnswer, String op1, String op2, String op3)
//          throws IllegalArgumentException {
//    super(question);
//
//    options = op1 + op2 + op3;
//    if (!options.replaceAll("\\s+", "").matches("[1-6]+")) {
//      throw new IllegalArgumentException(" numbers from 1 - 8 needed");
//    } else if (options.replaceAll("\\s+", "").length() < 3 | options.trim().length() > 8) {
//      throw new IllegalArgumentException("3 to 8 options needed");
//    } else if (!correctAnswer.trim().matches("[1-6]")) {
//      throw new IllegalArgumentException("Single Number from 1-8");
//    } else if (correctAnswer.trim().length() != 1) {
//      throw new IllegalArgumentException("Only choose one option");
//    } else if (!options.replaceAll("\\s+", "").contains(correctAnswer.trim())) {
//      throw new IllegalArgumentException("Answer must be in option");
//    }
//    this.correctAnswer = correctAnswer;
//    this.op1 = op1;
//    this.op2 = op2;
//    this.op3 = op3;
//  }
//
//  public MultipleChoice(String question, String correctAnswer, String op1, String op2, String op3, String op4)
//          throws IllegalArgumentException {
//    super(question);
//
//    options = op1 + op2 + op3 + op4;
////    if (!options.replaceAll("\\s+", "").matches("[1-8]+")) {
////      throw new IllegalArgumentException(" numbers from 1 - 8 needed");
////    } else if (options.replaceAll("\\s+", "").length() < 3 | options.trim().length() > 8) {
////      throw new IllegalArgumentException("3 to 8 options needed");
////    } else if (!correctAnswer.trim().matches("[1-8]")) {
////      throw new IllegalArgumentException("Single Number from 1-8");
////    } else if (correctAnswer.trim().length() != 1) {
////      throw new IllegalArgumentException("Only choose one option");
////    } else if (!options.replaceAll("\\s+", "").contains(correctAnswer.trim())) {
////      throw new IllegalArgumentException("Answer must be in option");
////    }
//    this.correctAnswer = correctAnswer;
//    this.op1 = op1;
//    this.op2 = op2;
//    this.op3 = op3;
//    this.op4 = op4;
//  }

  @Override
  public String answer(String answer) {
//    if (!(options.replaceAll("\\s+", "").contains(answer.replaceAll("\\s+", "")))) {
//      return "Incorrect";
//    }
    if (answer.replaceAll("\\s+", "").equals(correctAnswer.replaceAll("\\s+", ""))) {
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
    return o.getText().compareTo(this.getText());
  }

  @Override
  public int compareToMultipleSelect(Question o) {
    return 1;
  }
}
