package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;


/**
 * This command runs the mosaic image operation.
 */
public class MosaicImage implements ImageCommand {
    private final int seeds;

    /**
     * Default constructor.
     * @param seeds the number of pixels to pick.
     */
    public MosaicImage(int seeds) {
        this.seeds = seeds;
    }


    @Override
    public void run(ImageModelInterface model) throws IllegalStateException {
        model.toMosaic(seeds);

    }
}





