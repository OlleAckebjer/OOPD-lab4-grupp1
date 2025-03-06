package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
// import Volvo240; // Replace 'your.package' with the actual package name where Volvo240 is defined

public class CarFactory {

    public static Cars createVolvo240() {
        Cars car = new Volvo240(new Point(0, 0));
        return car;
    }

    public static Cars createSaab95() {
        Cars car = new Saab95(new Point(0, 100));
        return car;
    }

    public static Cars createScania() {
        Cars car = new Scania(new Point(0, 200));
        return car;
    }

    public static Cars createRandomCar() {

        ArrayList<Cars> randomCars = new ArrayList<>(Arrays.asList(createVolvo240(), createSaab95(), createScania()));
        Random rand = new Random();

        return randomCars.get(rand.nextInt(randomCars.size()));
    }
}