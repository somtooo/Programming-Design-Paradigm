package controller;

import static org.junit.Assert.assertEquals;

import imagemodel.ImageModelInterface;
import imagemodel.MockModel;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;




/** Tests the controller class in isolation for correctness. */
public class ControllerTest {

  @Test
  public void blurCommandFail() throws IOException {
    Readable in = new StringReader("blur 1");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("1 []", log.toString());
    assertEquals(
        "blur command was not carried out successfully make sure image "
                +
                "is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void blurCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\nblur 1");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png 1 [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "blur command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void sharpenCommandFail() throws IOException {
    Readable in = new StringReader("sharpen 1");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("1 []", log.toString());
    assertEquals(
        "sharpen command was not carried out successfully make sure "
                +
                "image is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void sharpenCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\nsharpen 1");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png 1 [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "sharpen command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void greyScaleCommandFail() throws IOException {
    Readable in = new StringReader("greyScale");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("[]", log.toString());
    assertEquals(
        "greyScale command was not carried out successfully make sure image is "
                +
                "loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void greyScaleCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\ngreyScale");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "greyScale command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void sepiaCommandFail() throws IOException {
    Readable in = new StringReader("sepia");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("[]", log.toString());
    assertEquals(
        "sepia command was not carried out successfully make sure "
                +
                "image is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void sepiaCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\nsepia");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "sepia command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void reduceCommandFail() throws IOException {
    Readable in = new StringReader("reduce 8");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("8 []", log.toString());
    assertEquals(
        "reduce command was not carried out successfully make sure "
                +
                "image is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void reduceCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\nreduce 8");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png 8 [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "reduce command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void toMosaicCommandFail() throws IOException {
    Readable in = new StringReader("toMosaic 1100");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("1100 []", log.toString());
    assertEquals(
        "toMosaic command was not carried out successfully make sure image "
                +
                "is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void toMosaicCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\ntoMosaic 1100");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png 1100 [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "toMosaic command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void pixelateCommandFail() throws IOException {
    Readable in = new StringReader("pixelate 100");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("100 []", log.toString());
    assertEquals(
        "pixelate command was not carried out successfully make sure image"
                +
                " is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void pixelateCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\npixelate 100");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png 100 [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "pixelate command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void patternCommandFail() throws IOException {
    Readable in = new StringReader("pattern");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("[]", log.toString());
    assertEquals(
        "pattern command was not carried out successfully make sure image "
                +
                "is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void patternCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png\npattern");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "pattern command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void loadCommandFail() throws IOException {
    Readable in = new StringReader("load fail.jpg");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("fail.jpg ", log.toString());
    assertEquals("load command was not carried out successfully\n", out.toString());
  }

  @Test
  public void loadCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("goat.png ", log.toString());
    assertEquals("load command was carried out successfully\n", out.toString());
  }

  @Test
  public void saveCommandFail() throws IOException {
    Readable in = new StringReader("save goat.png");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("goat.png []", log.toString());
    assertEquals(
        "save command was not carried out successfully make sure "
                +
                "image is loaded before calling image operation\n",
        out.toString());
  }

  @Test
  public void saveCommandFailIo() throws IOException {
    Readable in = new StringReader("load goat.png save fail.jpg");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png fail.jpg [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]",
        log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "save command was not carried out successfully\n",
        out.toString());
  }

  @Test
  public void saveCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png save goat-blur.jpg");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals(
        "goat.png goat-blur.jpg [[[1, -2, 3], [2, 3, 4]], [[-4, -5, 6, 9], [1], [2, 3]]]",
        log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "save command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void savePatternCommandPass() throws IOException {
    Readable in = new StringReader("load goat.png save pattern.txt");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("goat.png pattern.txt ", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "save command was carried out successfully\n",
        out.toString());
  }

  @Test
  public void savePatternFailIo() throws IOException {
    Readable in = new StringReader("load goat.png save fail.txt");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("goat.png fail.txt ", log.toString());
    assertEquals(
        "load command was carried out successfully\n"
            + "save command was not carried out successfully\n",
        out.toString());
  }

  @Test
  public void testUnsupportedCommand() throws IOException {
    Readable in = new StringReader("kill goat.png");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("", log.toString());
    assertEquals(
        "Sorry we do not support the kill command currently\n"
            + "Sorry we do not support the goat.png command currently\n",
        out.toString());
  }

  @Test
  public void testWrongExtraInput() throws IOException {
    Readable in = new StringReader("blur ");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(model);
    assertEquals("", log.toString());
    assertEquals(
        "blur command was not carried out successfully check ReadMe for how to use commands\n",
        out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongModel() throws IOException {
    Readable in = new StringReader("blur ");
    Appendable out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ImageModelInterface model = new MockModel(log);
    IController controller = new Controller(in, out);
    controller.start(null);
    assertEquals("", log.toString());
    assertEquals(
        "blur command was not carried out successfully check ReadMe for how to use commands\n",
        out.toString());
  }


}
