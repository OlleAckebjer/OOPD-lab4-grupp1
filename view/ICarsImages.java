package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface ICarsImages {
    ArrayList<BufferedImage> carImages = new ArrayList<>();

    default void addImages(ArrayList<BufferedImage> image) {
        this.carImages.addAll(image);
    }

    default void addImage(BufferedImage image) {
        this.carImages.add(image);
    }
}
