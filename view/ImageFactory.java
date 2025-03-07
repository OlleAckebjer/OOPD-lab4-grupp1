package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ImageFactory {
    public static BufferedImage createImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageFactory.class.getResourceAsStream(path)));
        } catch (IOException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    public static BufferedImage createVolvoImage() {
        return createImage("/pics/Volvo240.jpg");
    }

    public static BufferedImage createSaabImage() {
        return createImage("/pics/Saab95.jpg");
    }

    public static BufferedImage createScaniaImage() {
        return createImage("/pics/Scania.jpg");
    }

    public static BufferedImage createGarageImage() {
        return createImage("/pics/VolvoBrand.jpg");
    }

}