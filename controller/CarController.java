package controller;

import java.awt.Point;
import java.awt.image.BufferedImage;

import model.*;
import view.ImageFactory;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements ICarsArrayList, ICarController {

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;

        for (Cars car : ICarsArrayList.cars) {
            try {

                car.gas(gas);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Cars car : ICarsArrayList.cars) {
            car.brake(brake);
        }
    }

    public void startCars() {
        for (Cars car : ICarsArrayList.cars) {
            try {
                car.startEngine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopCars() {
        for (Cars car : ICarsArrayList.cars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasTurbo) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasTurbo) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasFlatbed) {
                ((Scania) car).raiseRamp();
            }
        }
    }

    public void lowerBed() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasFlatbed) {
                ((Scania) car).lowerRamp();
            }
        }
    }

    public void turnRight() {
        for (Cars car : ICarsArrayList.cars) {
            car.turnRight();
        }
    }

    public void turnLeft() {
        for (Cars car : ICarsArrayList.cars) {
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