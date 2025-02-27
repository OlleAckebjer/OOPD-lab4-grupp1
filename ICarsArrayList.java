import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ICarsArrayList {
    ArrayList<Cars> cars = new ArrayList<>();
    List<BufferedImage> carImages = new ArrayList<>();
    List<Boolean> carIsLoaded = new ArrayList<>();

    // ArrayList<Garage> garage = new ArrayList<>();

    default void addCars(ArrayList<Cars> cars) {
        this.cars.addAll(cars);

        this.carIsLoaded.add(false);
        this.carIsLoaded.add(false);
        this.carIsLoaded.add(false);
    }

    /*
     * {
     * volvo : {image: ..., isLoaded: false}
     * saab : {image: ..., isLoaded: false}
     * scania: {image: ..., isLoaded: false}
     * }
     */

    default void addCarImages(List<BufferedImage> carImages) {
        this.carImages.addAll(carImages);
    }

}