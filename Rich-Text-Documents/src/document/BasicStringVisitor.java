package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/** This visitor accumulates a basic string representation of its text elements it visits. */
public class BasicStringVisitor implements TextElementVisitor<String> {
  private String representation;

  /** Default constructor. */
  public BasicStringVisitor() {
    representation = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    return getString(object.getText());
  }

  @Override
  public String visitBoldText(BoldText object) {
    return getString(object.getText());
  }

  @Override
  public String visitHeading(Heading object) {
    return getString(object.getText());
  }

  @Override
  public String visitHyperText(HyperText object) {
    return getString(object.getText());
  }

  @Override
  public String visitItalicText(ItalicText object) {
    return getString(object.getText());
  }

  @Override
  public String visitParagraphText(Paragraph object) {
    return getString(object.getText());
  }

  @Override
  public String toString() {
    return representation;
  }

  /**
   * Gets the string representation of elements.
   *
   * @param text the text to accumulate.
   * @return the accumulated representation.
   */
  private String getString(String text) {
    representation = representation + text.trim() + " ";
    return representation;
  }
}
