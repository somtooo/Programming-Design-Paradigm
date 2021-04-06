package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * This is an interface for the visitor on text elements. This interface offers a chance to visit
 * every kind of text element in the element package. Some visitors may want this level of
 * granularity, others may not.
 *
 * @param <R> the type of the return parameter for the visit
 */
public interface TextElementVisitor<R> {
  /**
   * The action to take when visiting a basic text element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitBasicText(BasicText object);
  /**
   * The action to take when visiting a bold text element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitBoldText(BoldText object);
  /**
   * The action to take when visiting a heading element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitHeading(Heading object);
  /**
   * The action to take when visiting a hyper text element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitHyperText(HyperText object);
  /**
   * The action to take when visiting an italic text element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitItalicText(ItalicText object);
  /**
   * The action to take when visiting a paragraph text element.
   *
   * @param object the basic text element.
   * @return the element
   */

  R visitParagraphText(Paragraph object);
}
