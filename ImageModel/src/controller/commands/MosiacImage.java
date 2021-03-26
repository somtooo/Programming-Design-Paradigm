package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class MosiacImage implements ImageCommand {
    private int seeds;
    public MosiacImage(int seeds) {
        this.seeds = seeds;
    }


    @Override
    public void run(ImageModelInterface model) {
        model.toMosaic(seeds);

    }
}





