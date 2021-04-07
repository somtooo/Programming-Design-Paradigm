package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import java.util.List;

/** This visitor accumulates a Markdown string representation of its text elements it visits. */
public class MarkdownStringVisitor implements TextElementVisitor<String> {
  private String representation;

  /** Default constructor. */
  public MarkdownStringVisitor() {
    representation = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    representation = representation + object.getText() + "\n";
    return representation;
  }

  @Override
  public String visitBoldText(BoldText object) {
    representation = representation + "**" + object.getText() + "**" + "\n";
    return representation;
  }

  @Override
  public String visitHeading(Heading object) {
    representation =
        representation + "#".repeat(Math.max(0, object.getLevel())) + " " + object.getText() + "\n";
    return representation;
  }

  @Override
  public String visitHyperText(HyperText object) {
    representation =
        representation + "[" + object.getText() + "]" + "(" + object.getUrl() + ")" + "\n";
    return representation;
  }

  @Override
  public String visitItalicText(ItalicText object) {
    representation = representation + "*" + object.getText() + "*" + "\n";
    return representation;
  }

  @Override
  public String visitParagraphText(Paragraph object) {
    TextElementVisitor<String> markdown = new MarkdownStringVisitor();
    List<BasicText> content = object.getContent();
    for (BasicText text : content) {
      text.accept(markdown);
    }
    representation = representation + "\n" + markdown.toString() + "\n";
    return representation;
  }

  @Override
  public String toString() {
    return representation;
  }
}
