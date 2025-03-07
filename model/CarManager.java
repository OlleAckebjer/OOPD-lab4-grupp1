package model;

import view.ImageFactory;

import java.util.ArrayList;

public class CarManager implements ICarImages{
    private final CarModel carModel;

    public CarManager(CarModel carModel) {
        this.carModel = carModel;
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : carModel.getCars()) {
            try {

                car.gas(gas);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Cars car : carModel.getCars()) {
            car.brake(brake);
        }
    }

    public void startCars() {
        for (Cars car : carModel.getCars()) {
            try {
                car.startEngine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopCars() {
        for (Cars car : carModel.getCars()) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Cars car : carModel.getCars()) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Cars car : carModel.getCars()) {
            if (car instanceof IHasTurbo) {
                ((IHasTurbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Cars car : carModel.getCars()) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).raiseRamp();
            }
        }
    }

    public void lowerBed() {
        for (Cars car : carModel.getCars()) {
            if (car instanceof IHasFlatbed) {
                ((IHasFlatbed) car).lowerRamp();
            }
        }
    }

    public void turnRight() {
        for (Cars car : carModel.getCars()) {
            car.turnRight();
        }
    }

    public void turnLeft() {
        for (Cars car : carModel.getCars()) {
            car.turnLeft();
        }
    }

    public void addCar() {
        if (carModel.getCars().size() < 10) {
            Cars newCar = CarFactory.createRandomCar();
            carModel.getCars().add(newCar);

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
        if (!carModel.getCars().isEmpty()) {
            carModel.getCars().removeLast();
            carImages.removeLast();
        }
    }
}
