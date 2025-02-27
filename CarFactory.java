import java.awt.Point;
// import Volvo240; // Replace 'your.package' with the actual package name where Volvo240 is defined

public class CarFactory {

    public static Cars createVolvo240() {
        Cars car = new Volvo240();
        car.setPosition(new Point(0, 0));
        return car;
    }

    public static Cars createSaab95() {
        Cars car = new Saab95();
        car.setPosition(new Point(0, 100));
        return car;
    }

    public static Cars createScania() {
        Cars car = new Scania();
        car.setPosition(new Point(0, 200));
        return car;
    }

}
