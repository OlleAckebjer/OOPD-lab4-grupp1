package controller;

import model.*;
import view.CarView;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements IActionListener {
    private final CarManager carManager;

    public CarController(CarManager carManager, CarView carView) {
        this.carManager = carManager;
        carView.setIActionListener(this);
    }

    @Override
    public void onGas(int amount) {
        carManager.gas(amount);
    }

    @Override
    public void onBrake(int amount) {
        carManager.brake(amount);
    }

    @Override
    public void onStart() {
        carManager.startCars();
    }

    @Override
    public void onStop() {
        carManager.stopCars();
    }

    @Override
    public void onTurboOn() {
        carManager.turboOn();
    }

    @Override
    public void onTurboOff() {
        carManager.turboOff();
    }

    @Override
    public void onLiftBed() {
        carManager.liftBed();
    }

    @Override
    public void onLowerBed() {
        carManager.lowerBed();
    }

    @Override
    public void onTurnRight() {
        carManager.turnRight();
    }

    @Override
    public void onTurnLeft() {
        carManager.turnLeft();
    }

    @Override
    public void onAddCar() {
        carManager.addCar();
    }

    @Override
    public void onRemoveCar() {
        carManager.removeCar();
    }
}