package document;

import document.elements.TextElement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a document. It contains a list of the elements of the document in the
 * order that they appear in the document. This class is provided as a starting point for the
 * Visitor lab in CS 5010.
 */
public class Document {

  private final List<TextElement> content;

  /** Default constructor initializes the fields of the class. */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Add an element to the document. Elements are added in order.
   *
   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  /**
   * Counts the number of words in the document.
   *
   * @return the number of words in the document.
   */
  public int countWords() {
    WordCountVisitor countVisitor = new WordCountVisitor();
    int words = 0;
    for (TextElement element : content) {
      words = words + element.accept(countVisitor);
    }
    return words;
  }

  /**
   * Converts document to a text format.
   * @param visitor the text format to convert document to.
   * @return the converted text format.
   */
  public String toText(TextElementVisitor<String> visitor) {
    for (TextElement element : content) {
      element.accept(visitor);
    }
    return visitor.toString().trim();
  }
}
