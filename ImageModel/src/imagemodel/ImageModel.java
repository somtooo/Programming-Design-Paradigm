package imagemodel;

import images.ImageUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

    public ImageModel() {
        this.image = new int[0][][];
        this.pattern = "";
    }

    @Override
    public int[][][] blur(int intensity) throws IllegalStateException {
        checkState();
        Filter blur = new Blur(image);
        return blur.applyFilter(intensity);
    }

    private void checkState() {
        if (image.length == 0) {
            throw new IllegalStateException("Image is empty");
        }
    }


    @Override
    public int[][][] sharpen(int intensity) throws IllegalStateException {
        checkState();
        Filter sharpen = new Sharpen(Objects.requireNonNull(image));
        return sharpen.applyFilter(intensity);
    }

    @Override
    public int[][][] greyScale() throws IllegalStateException {
        checkState();
        Transform greyScale = new GreyScale(Objects.requireNonNull(image));
        return greyScale.applyTransform();
    }

    @Override
    public int[][][] sepia() throws IllegalStateException {
        checkState();
        Transform sepia = new Sepia(Objects.requireNonNull(image));
        return sepia.applyTransform();
    }

    @Override
    public int[][][] reduceColor(int numberOfColors) throws IllegalStateException {
        checkState();
        Reduce colorReduce = new FloydDithering(Objects.requireNonNull(image));
        return colorReduce.reduce(numberOfColors);
    }

    @Override
    public int[][][] toMosaic(int seeds) throws IllegalStateException {
        checkState();
        Chunking mosaic = new Mosaic(Objects.requireNonNull(image));
        return mosaic.apply(seeds);
    }

    @Override
    public int[][][] pixelate(int squares) throws IllegalStateException {
        checkState();
        Chunking pixelate = new Pixelation(Objects.requireNonNull(image));
        return pixelate.apply(squares);
    }

    @Override
    public String crossStitch() throws IllegalStateException {
        checkState();
        Pattern crossStitch = new CrossStitch(Objects.requireNonNull(image));
        this.pattern = crossStitch.generate();
        return pattern;
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
