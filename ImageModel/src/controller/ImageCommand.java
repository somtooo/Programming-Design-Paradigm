package controller;

import imagemodel.ImageModelInterface;

import java.io.IOException;

public interface ImageCommand {
    void run(ImageModelInterface model) throws IOException;
}
