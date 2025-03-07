package model;

import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Cars implements IHasFlatbed {
    private boolean isRampDown = false;
    private final ArrayList<Cars> loadedCars = new ArrayList<>();

    public CarTransport(Point position) {
        super(2, 700, Color.WHITE, "Scania", new Point(position));
    }

    public CarTransport() {
        this(new Point(0, 0));
    }

    // Helper method to check if the car is moving
    private boolean isMoving() {
        return (this.getCurrentSpeed() > 0);
    }

    // Helper method to set the ramp
    private void setRamp(boolean down) {
        if (isMoving()) {
            throw new IllegalAccessError("Can't adjust ramp while moving");
        }
        isRampDown = down;
    }

    public void lowerRamp() {
        setRamp(true);
    }

    public void raiseRamp() {
        setRamp(false);
    }

    public void addCar(Cars car) {
        if (!isRampDown) {
            throw new IllegalArgumentException("Can't add car while the ramp is up");
        }
        int maxDistanceAllowed = 5;
        if (car.getPosition().distance(this.getPosition()) > maxDistanceAllowed) {
            throw new IllegalArgumentException("Can't add car, it's too far away");
        }
        if (car instanceof IHasFlatbed) {
            throw new IllegalArgumentException("Can't add a car with a flatbed");
        }
        int maxSize = 6;
        if (loadedCars.size() >= maxSize) {
            throw new IllegalArgumentException("Can't add more loadedCars, max capacity reached");
        }
        if (loadedCars.contains(car)) {
            throw new IllegalArgumentException("Can't add the same car twice");
        }
        if (!(car.getState() instanceof NotLoadedState)) {
            throw new IllegalArgumentException("Can't add a loaded car");
        }
        if (car.getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Can't add a moving car");
        }
        loadedCars.add(car);
        car.setState(new LoadedState());
    }

    public Cars getLastCar() {
        if (!isRampDown) {
            throw new IllegalAccessError("Can't get the last car while the ramp is up");
        }
        if (loadedCars.isEmpty()) {
            throw new IllegalArgumentException("Can't get the last car of an empty flatbed");
        }

        loadedCars.getLast().setState(new NotLoadedState());

        return loadedCars.removeLast();
    }

    @Override
    public void move() {
        checkRamp();
        super.move();
        for (Cars car : loadedCars) {
            car.setPosition(this.getPosition());
        }
    }

    private void checkRamp() {
        if (isRampDown) {
            throw new IllegalArgumentException("Can't move vehicle while the ramp is down.");
        }
    }

    @Override
    public void startEngine() {
        checkRamp();
        super.startEngine();
    }

    @Override
    protected double speedFactor() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 100;
    }

    @Override
    public int getWidth() {
        return 60;
    }
}
