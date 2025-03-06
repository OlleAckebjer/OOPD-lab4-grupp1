package controller;

import model.Cars;
import model.ICarsArrayList;
import model.IHasFlatbed;
import model.IHasTurbo;

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
                ((IHasTurbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).raiseRamp();
            }
        }
    }

    public void lowerBed() {
        for (Cars car : ICarsArrayList.cars) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).lowerRamp();
            }
        }
    }

    public void turnRight() {
        for (Cars car : ICarsArrayList.cars) {
            car.turnRight();
        }
    }

    public void turnLeft() {
        System.out.println("Turning left");
        System.out.println(ICarsArrayList.cars);
        for (Cars car : ICarsArrayList.cars) {
            car.turnLeft();
        }
    }

}