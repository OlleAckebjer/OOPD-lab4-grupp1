package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public interface ICarImages {
    ArrayList<BufferedImage> carImages = new ArrayList<>();

    default void addImages(List<BufferedImage> images) {
        carImages.addAll(images);
    }
    default void addImage(BufferedImage image, int width, int height) {
        carImages.add(image);
    }
}
