package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public interface ICarsArrayList {
    ArrayList<Cars> cars = new ArrayList<>();

    default void addCars(ArrayList<Cars> cars) {
        this.cars.addAll(cars);
    }

}