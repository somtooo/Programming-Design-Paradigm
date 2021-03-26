package imagemodel;

import imagemodel.utilities.FileUtilities;
import imagemodel.utilities.ImageUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the Image Model Interface and represents image processing operations that can be done on an image.
 */
public class ImageModel  implements ImageModelInterface {

    private final int[][][] image;
    private String pattern;
    /**
     * Sets fields with the required parameters.
     *
     * @param image the image operations will be applied on.
     */
    public ImageModel(int[][][] image) {
        Objects.requireNonNull(image, "Image must not be null");
        this.image = image;
        this.pattern = "";
    }

    /**
     * Default constructor.
     */
    public ImageModel() {
        this.image = new int[0][][];
        this.pattern = "";
    }

    @Override
    public void blur(int intensity) throws IllegalStateException {
        checkState();
        Filter blur = new Blur(image);
        blur.applyFilter(intensity);
    }

    private void checkState() {
        if (image.length == 0) {
            throw new IllegalStateException("Image is empty");
        }
    }


    @Override
    public void sharpen(int intensity) throws IllegalStateException {
        checkState();
        Filter sharpen = new Sharpen(Objects.requireNonNull(image));
        sharpen.applyFilter(intensity);
    }

    @Override
    public void greyScale() throws IllegalStateException {
        checkState();
        Transform greyScale = new GreyScale(Objects.requireNonNull(image));
        greyScale.applyTransform();
    }

    @Override
    public void sepia() throws IllegalStateException {
        checkState();
        Transform sepia = new Sepia(Objects.requireNonNull(image));
        sepia.applyTransform();
    }

    @Override
    public void reduceColor(int numberOfColors) throws IllegalStateException {
        checkState();
        Reduce colorReduce = new FloydDithering(Objects.requireNonNull(image));
        colorReduce.reduce(numberOfColors);
    }

    @Override
    public void toMosaic(int seeds) throws IllegalStateException {
        checkState();
        Chunking mosaic = new Mosaic(Objects.requireNonNull(image));
        mosaic.apply(seeds);
    }

    @Override
    public void pixelate(int squares) throws IllegalStateException {
        checkState();
        Chunking pixelate = new Pixelation(Objects.requireNonNull(image));
        pixelate.apply(squares);
    }

    @Override
    public void crossStitch() throws IllegalStateException {
        checkState();
        Pattern crossStitch = new CrossStitch(Objects.requireNonNull(image));
        this.pattern = crossStitch.generate();
    }

    @Override
    public int[][][] loadImage(String filename) throws IOException {
        File file = new File("");
        String path = file.getAbsolutePath() + "\\" + filename;
        return ImageUtilities.readImage(path);
    }

    @Override
    public void saveImage(String filename) throws IllegalStateException, IOException  {
        checkState();
        ImageUtilities.writeImage( image, Objects.requireNonNull(image)[0].length, image.length, filename);

    }

    @Override
    public void saveTextToFile(String filename) throws IOException {
        FileUtilities.writeToFile(filename,pattern);
    }
}
