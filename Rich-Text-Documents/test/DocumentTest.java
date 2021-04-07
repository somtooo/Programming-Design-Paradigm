import static org.junit.Assert.assertEquals;

import document.BasicStringVisitor;
import document.Document;
import document.HtmlStringVisitor;
import document.MarkdownStringVisitor;
import document.TextElementVisitor;
import document.WordCountVisitor;
import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;
import org.junit.Before;
import org.junit.Test;




/**
 * Tests the document class for correctness.
 */
public class DocumentTest {

  TextElement basic;
  BasicText bold;
  TextElement url;
  Paragraph paragraph;
  Document richTextDocument;
  TextElementVisitor<Integer> count;

  /** Sets up objects to be used in test functions. */
  @Before
  public void setUp() {
    basic = new BasicText("how are you ");
    bold = new BoldText("i am fine ");
    url = new HyperText("Google", "www.google.com");
    paragraph = new Paragraph();
    richTextDocument = new Document();
    count = new WordCountVisitor();
  }

  @Test
  public void countWords() {
    richTextDocument.add(basic);
    richTextDocument.add(bold);
    assertEquals(6, richTextDocument.countWords());
  }

  @Test
  public void toText() {
    TextElementVisitor<String> stringVisitor = new BasicStringVisitor();
    TextElementVisitor<String> htmlVisitor = new HtmlStringVisitor();
    BasicText italic = new ItalicText("its good");
    BasicText italic2 = new ItalicText("its good fine");
    BasicText italic3 = new ItalicText("its good rat");
    paragraph.add(italic);
    paragraph.add(italic2);
    richTextDocument.add(paragraph);
    TextElementVisitor<String> markdownVisitor = new MarkdownStringVisitor();
    assertEquals("*its good*\n" + "*its good fine*", richTextDocument.toText(markdownVisitor));
  }
}
