import static org.junit.Assert.assertEquals;

import document.BasicStringVisitor;
import document.Document;
import document.HtmlStringVisitor;
import document.MarkdownStringVisitor;
import document.TextElementVisitor;
import document.WordCountVisitor;
import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;
import org.junit.Before;
import org.junit.Test;

/** Tests the document class for correctness. */
public class DocumentTest {

  TextElement basic;
  BasicText bold;
  TextElement url;
  Paragraph paragraph;
  Document richTextDocument;
  TextElement heading;
  TextElement italic;
  TextElementVisitor<Integer> count;
  TextElementVisitor<String> stringVisitor;
  TextElementVisitor<String> htmlVisitor;
  TextElementVisitor<String> markdownVisitor;

  /** Sets up objects to be used in test functions. */
  @Before
  public void setUp() {
    basic = new BasicText("how are you ");
    bold = new BoldText("i am fine ");
    url = new HyperText("Google", "www.google.com");
    heading = new Heading("The Goat", 1);
    italic = new ItalicText("i am italic");
    paragraph = new Paragraph();
    richTextDocument = new Document();
    count = new WordCountVisitor();
    stringVisitor = new BasicStringVisitor();
    htmlVisitor = new HtmlStringVisitor();
    markdownVisitor = new MarkdownStringVisitor();
  }

  @Test
  public void countWords() {
    assertEquals(0, richTextDocument.countWords());

    richTextDocument.add(basic);
    assertEquals(3, richTextDocument.countWords());

    richTextDocument.add(new BasicText(""));
    assertEquals(3, richTextDocument.countWords());

    richTextDocument.add(bold);
    assertEquals(6, richTextDocument.countWords());

    richTextDocument.add(new BoldText(""));
    assertEquals(6, richTextDocument.countWords());

    richTextDocument.add(url);
    assertEquals(7, richTextDocument.countWords());

    richTextDocument.add(new HyperText("", ""));
    assertEquals(7, richTextDocument.countWords());

    richTextDocument.add(heading);
    assertEquals(9, richTextDocument.countWords());

    richTextDocument.add(new Heading("", 1));
    assertEquals(9, richTextDocument.countWords());

    richTextDocument.add(italic);
    assertEquals(12, richTextDocument.countWords());

    richTextDocument.add(new ItalicText(""));
    assertEquals(12, richTextDocument.countWords());

    paragraph.add(new BasicText("When a goat came"));
    richTextDocument.add(paragraph);
    assertEquals(16, richTextDocument.countWords());

    Paragraph paragraph1 = new Paragraph();
    richTextDocument.add(paragraph1);
    assertEquals(16, richTextDocument.countWords());
  }

  @Test
  public void toText() {
    assertEquals("", richTextDocument.toText(stringVisitor));
    assertEquals("", richTextDocument.toText(markdownVisitor));
    assertEquals("", richTextDocument.toText(htmlVisitor));

    richTextDocument.add(basic);
    assertEquals("how are you", richTextDocument.toText(stringVisitor));
    assertEquals("how are you", richTextDocument.toText(markdownVisitor));
    assertEquals("how are you", richTextDocument.toText(htmlVisitor));

    richTextDocument.add(bold);
    assertEquals("how are you i am fine", richTextDocument.toText(stringVisitor));
    assertEquals("how are you \n" + "**i am fine **", richTextDocument.toText(markdownVisitor));
    assertEquals("how are you \n" + "<b>i am fine </b>", richTextDocument.toText(htmlVisitor));

    richTextDocument.add(heading);
    assertEquals("how are you i am fine The Goat", richTextDocument.toText(stringVisitor));
    assertEquals(
        "how are you \n" + "**i am fine **\n" + "# The Goat",
        richTextDocument.toText(markdownVisitor));
    assertEquals(
        "how are you \n" + "<b>i am fine </b>\n" + "<h1>The Goat</h1>",
        richTextDocument.toText(htmlVisitor));

    richTextDocument.add(url);
    assertEquals("how are you i am fine The Goat Google", richTextDocument.toText(stringVisitor));
    assertEquals(
        "how are you \n" + "**i am fine **\n" + "# The Goat\n" + "[Google](www.google.com)",
        richTextDocument.toText(markdownVisitor));
    assertEquals(
        "how are you \n"
            + "<b>i am fine </b>\n"
            + "<h1>The Goat</h1>\n"
            + "<a href=\"www.google.com\">Google</a>",
        richTextDocument.toText(htmlVisitor));

    richTextDocument.add(italic);
    assertEquals(
        "how are you i am fine The Goat Google i am italic",
        richTextDocument.toText(stringVisitor));
    assertEquals(
        "how are you \n"
            + "**i am fine **\n"
            + "# The Goat\n"
            + "[Google](www.google.com)\n"
            + "*i am italic*",
        richTextDocument.toText(markdownVisitor));
    assertEquals(
        "how are you \n"
            + "<b>i am fine </b>\n"
            + "<h1>The Goat</h1>\n"
            + "<a href=\"www.google.com\">Google</a>\n"
            + "<i>i am italic</i>",
        richTextDocument.toText(htmlVisitor));

    BasicText italic = new ItalicText("its good");
    BasicText italic2 = new ItalicText("its good fine");
    BasicText italic3 = new ItalicText("its good rat");
    paragraph.add(italic);
    paragraph.add(italic2);
    richTextDocument.add(paragraph);
    assertEquals(
        "how are you \n"
            + "**i am fine **\n"
            + "# The Goat\n"
            + "[Google](www.google.com)\n"
            + "*i am italic*\n"
            + "\n"
            + "*its good*\n"
            + "*its good fine*",
        richTextDocument.toText(markdownVisitor));

    assertEquals(
        "how are you i am fine The Goat Google i am italic its good its good fine",
        richTextDocument.toText(stringVisitor));
    assertEquals(
        "how are you \n"
            + "<b>i am fine </b>\n"
            + "<h1>The Goat</h1>\n"
            + "<a href=\"www.google.com\">Google</a>\n"
            + "<i>i am italic</i>\n"
            + "<p><i>its good</i>\n"
            + "<i>its good fine</i>\n"
            + "</p>",
        richTextDocument.toText(htmlVisitor));
  }
}
