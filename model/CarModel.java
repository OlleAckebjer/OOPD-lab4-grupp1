package model;

import javax.swing.*;

import model.Cars.Direction;
import view.ImageFactory;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.ICarsImages.carImages;

// TODO: Currently responsible for far too many things. Break actions into a class, and timer simulation into another?
public class CarModel {
    ArrayList<Cars> cars = new ArrayList<>();
    private final Timer timer;
    private final List<ICarModelListener> listeners = new ArrayList<>();
    private Garage<Volvo240> volvoGarage;
    private final int distanceThreshold = 25;
    private final int carHeight = 60;
    private final int carWidth = 100;

    public void addCars(ArrayList<Cars> cars) {
        this.cars.addAll(cars);
    }

    public ArrayList<Cars> getCars(){ return cars; }

    public CarModel() {
        int delay = 50;
        this.timer = new Timer(delay, new TimerListener());
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

    /*
     * public void setGarageImage(BufferedImage garageImage) {
     * this.garageImage = garageImage;
     * }
     * 
     * public BufferedImage getGarageImage() {
     * return garageImage;
     * }
     */

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

                    double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
                    if (distance < distanceThreshold) {
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
        } else if (x > 800 - carWidth && dir == Direction.EAST) {
            movingOutOfBounds = true;
        } else if (y < 0 && dir == Direction.SOUTH) {
            movingOutOfBounds = true;
        } else if (y > 560 - carHeight && dir == Direction.NORTH) {
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
        for (Cars car : cars) {
            try {

                car.gas(gas);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Cars car : cars) {
            car.brake(brake);
        }
    }

    public void startCars() {
        for (Cars car : cars) {
            try {
                car.startEngine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopCars() {
        for (Cars car : cars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Cars car : cars) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Cars car : cars) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Cars car : cars) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).raiseRamp();
            }
        }
    }

    public void lowerBed() {
        for (Cars car : cars) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).lowerRamp();
            }
        }
    }

    public void turnRight() {
        for (Cars car : cars) {
            car.turnRight();
        }
    }

    public void turnLeft() {
        for (Cars car : cars) {
            car.turnLeft();
        }
    }

    public void addCar() {
        if (cars.size() < 10) {
            Cars newCar = CarFactory.createRandomCar();
            cars.add(newCar);

            if (newCar instanceof Volvo240) {
                carImages.add(ImageFactory.createVolvoImage());
            } else if (newCar instanceof Saab95) {
                carImages.add(ImageFactory.createSaabImage());
            } else if (newCar instanceof Scania) {
                carImages.add(ImageFactory.createScaniaImage());
            }

        } else {
            System.out.println("Can't add more cars to the list");
        }
    }

    public void removeCar() {
        if (!cars.isEmpty()) {
            cars.removeLast();
            carImages.removeLast();
        }
    }
}
