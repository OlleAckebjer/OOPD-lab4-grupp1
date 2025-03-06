package model;

import java.awt.Point;

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

}
