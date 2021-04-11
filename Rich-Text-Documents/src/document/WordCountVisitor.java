package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/** This visitor computes the number of words in a text element. */
public class WordCountVisitor implements TextElementVisitor<Integer> {

  @Override
  public Integer visitBasicText(BasicText object) {
    return calculateNumberOfWords(object.getText());
  }

  @Override
  public Integer visitBoldText(BoldText object) {
    return calculateNumberOfWords(object.getText());
  }

  @Override
  public Integer visitHeading(Heading object) {
    return calculateNumberOfWords(object.getText());
  }

  @Override
  public Integer visitHyperText(HyperText object) {
    return calculateNumberOfWords(object.getText());
  }

  @Override
  public Integer visitItalicText(ItalicText object) {
    return calculateNumberOfWords(object.getText());
  }

  @Override
  public Integer visitParagraphText(Paragraph object) {
    return calculateNumberOfWords(object.getText());
  }

  /**
   * Calculates the number of words in a given text.
   *
   * @param text the text to get the number of words from.
   * @return the number of words.
   */
  private Integer calculateNumberOfWords(String text) {
    String[] words = text.split("\\s+");
    if (words[0].equals("")) {
      return 0;
    } else {
      return words.length;

    }
  }

}
