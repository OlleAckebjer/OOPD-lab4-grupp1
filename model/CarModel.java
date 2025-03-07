package model;

import javax.swing.*;

import model.Cars.Direction;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Currently responsible for far too many things. Break actions into a class, and timer simulation into another?
public class CarModel {
    private final Timer timer;
    private final List<ICarModelListener> listeners = new ArrayList<>();
    private Garage<Volvo240> volvoGarage;
    private final ArrayList<Cars> cars = new ArrayList<>();
    private final int X;
    private final int Y;
    private final int maxAmountCars;

    public CarModel(int x, int y, int delay, int maxAmountCars) {
        this.X = x;
        this.Y = y;
        this.timer = new Timer(delay, new TimerListener());
        this.maxAmountCars = maxAmountCars;
    }

    public boolean canCreateMoreCars() {
        return cars.size() < maxAmountCars;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void addListener(ICarModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ICarModelListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (ICarModelListener listener : listeners) {
            listener.onCarModelUpdated();
        }
    }

    public void setGarage(Garage<Volvo240> volvoGarage) {
        this.volvoGarage = volvoGarage;
    }

    public Garage<Volvo240> getGarage() {
        return volvoGarage;
    }

    public void addCars(List<Cars> cars) {
        this.cars.addAll(cars);
    }

    public ArrayList<Cars> getCars() {
        return cars;
    }

    public Cars getLastCar() {
        return cars.get(cars.size() - 1);
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (isCarOutOfBounds(car)) {
                    car.turnAround();
                }

                if (car instanceof Volvo240) {
                    int x2 = getGarage().getPosition().x;
                    int y2 = getGarage().getPosition().y;
                    int garageWidth = getGarage().getWidth();
                    int garageHeight = getGarage().getHeight();

                    int carWidth = car.getWidth();
                    int carHeight = car.getHeight();

                    if (x + carWidth >= x2 && x <= x2 + garageWidth &&
                            y + carHeight >= y2 && y <= y2 + garageHeight) {

                        loadCarToWorkshop(car);
                        continue;
                    }

                }
                car.move();
            }
            notifyListeners();
        }
    }

    private boolean isCarOutOfBounds(Cars car) {
        int x = car.getX();
        int y = car.getY();
        Direction dir = car.getDirection();

        boolean movingOutOfBounds = false;
        if (x < 0 && dir == Direction.WEST) {
            movingOutOfBounds = true;
        } else if (x > X - car.getWidth() && dir == Direction.EAST) {
            movingOutOfBounds = true;
        } else if (y < 0 && dir == Direction.SOUTH) {
            movingOutOfBounds = true;
        } else if (y > Y - car.getHeight() && dir == Direction.NORTH) {
            movingOutOfBounds = true;
        }

        return movingOutOfBounds;
    }

    private void loadCarToWorkshop(Cars car) {
        if (car.getState() instanceof InGarageState) {
            return;
        }
        car.stopEngine();
        volvoGarage.addCar((Volvo240) car);
        car.setState(new InGarageState());

    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;

        for (Cars car : getCars()) {
            try {

                car.gas(gas);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Cars car : getCars()) {
            car.brake(brake);
        }
    }

    public void startCars() {
        for (Cars car : getCars()) {
            try {
                car.startEngine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopCars() {
        for (Cars car : getCars()) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Cars car : getCars()) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Cars car : getCars()) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Cars car : getCars()) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).raiseRamp();
            }
        }
    }

    public void lowerBed() {
        for (Cars car : getCars()) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).lowerRamp();
            }
        }
    }

    public void turnRight() {
        for (Cars car : getCars()) {
            car.turnRight();
        }
    }

    public void turnLeft() {
        for (Cars car : getCars()) {
            car.turnLeft();
        }
    }

    public void addCar(Cars car) {
        cars.add(car);
    }

    public void addRandomCar() {
        Cars randomCar = CarFactory.createRandomCar(this.X, this.Y);
        addCar(randomCar);

    }

    public void removeCar() {

        if (!cars.isEmpty()) {
            cars.removeLast();
        }

    }

}
