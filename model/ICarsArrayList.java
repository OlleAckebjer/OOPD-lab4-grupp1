package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ICarsArrayList {
    ArrayList<Cars> cars = new ArrayList<>();
    List<BufferedImage> carImages = new ArrayList<>();
    List<BufferedImage> garageImages = new ArrayList<>();

    default void addCars(ArrayList<Cars> cars) {
        this.cars.addAll(cars);
    }

}