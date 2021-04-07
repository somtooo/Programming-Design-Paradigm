package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import java.util.List;

/** This visitor accumulates a HTML string representation of its text elements it visits. */
public class HtmlStringVisitor implements TextElementVisitor<String> {
  private String representation;

  /** Default constructor. */
  public HtmlStringVisitor() {
    representation = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    representation = representation + object.getText() + "\n";
    return representation;
  }

  @Override
  public String visitBoldText(BoldText object) {
    representation = representation + "<b>" + object.getText() + "</b>" + "\n";
    return representation;
  }

  @Override
  public String visitHeading(Heading object) {
    String level = String.valueOf(object.getLevel());
    representation =
        representation + "<h" + level + ">" + object.getText() + "</h" + level + ">" + "\n";
    return representation;
  }

  @Override
  public String visitHyperText(HyperText object) {
    String url = String.valueOf(object.getUrl());
    representation = representation + "<a href=\"" + url + "\">" + object.getText() + "</a>" + "\n";
    return representation;
  }

  @Override
  public String visitItalicText(ItalicText object) {
    representation = representation + "<i>" + object.getText() + "</i>" + "\n";
    return representation;
  }

  @Override
  public String visitParagraphText(Paragraph object) {
    TextElementVisitor<String> html = new HtmlStringVisitor();
    List<BasicText> content = object.getContent();
    for (BasicText text : content) {
      text.accept(html);
    }
    representation = representation + "<p>" + html.toString() + "</p>" + "\n";
    return representation;
  }

  @Override
  public String toString() {
    return representation;
  }
}
