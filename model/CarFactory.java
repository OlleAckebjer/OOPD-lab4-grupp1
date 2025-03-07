package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
// import Volvo240; // Replace 'your.package' with the actual package name where Volvo240 is defined

public class CarFactory {

    public static Cars createVolvo240(Point position) {
        Cars car = new Volvo240(new Point(position));
        return car;
    }

    public static Cars createSaab95(Point position) {
        Cars car = new Saab95(new Point(position));
        return car;
    }

    public static Cars createScania(Point position) {
        Cars car = new Scania(new Point(position));
        return car;
    }

    public static Cars createRandomCar(int x, int y) {

        Random rand = new Random();
        Point randomPoint = new Point(rand.nextInt(x), rand.nextInt(y));

        ArrayList<Cars> randomCars = new ArrayList<>(
                Arrays.asList(createVolvo240(randomPoint), createSaab95(randomPoint),
                        createScania(randomPoint)));

        return randomCars.get(rand.nextInt(3));
    }
}