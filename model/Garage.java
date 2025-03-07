package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Garage<T extends Cars> implements IHasPosition, IHasSize {
    private final int maxCapacity;
    private final List<T> cars = new ArrayList<>();
    private Point position;
    private final int X;
    private final int Y;

    public Garage(int size, Point position, int X, int Y) {
        this.maxCapacity = size;
        this.position = new Point(position); // Defensive copying
        this.X = X;
        this.Y = Y;

    }

    public Point getPosition() {
        return new Point(position); // Return a copy of the position - deffensive copying
    }

    public void addCar(T car) {
        if (cars.size() >= maxCapacity) {
            throw new IllegalArgumentException("Garage is full");
        }
        if (cars.contains(car)) {
            throw new IllegalArgumentException("Car already in garage");
        }
        cars.add(car);

    }

    public T getCar(int index) {
        if (cars.isEmpty()) {
            throw new IllegalStateException("Garage is empty");
        }
        if (index < 0 || index >= cars.size()) {
            throw new IllegalArgumentException("Invalid index");
        }
        return cars.remove(index);
    }

    public T getLastCar() {
        return getCar(cars.size() - 1);
    }

    @Override
    public int getWidth() {
        return X;
    }

    @Override
    public int getHeight() {
        return Y;
    }
}