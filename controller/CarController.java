package controller;

import model.*;
import view.CarView;
import view.DrawPanel;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements IActionListener {
    private final CarModel carModel;

    public CarController(CarModel carModel, CarView carView) {
        this.carModel = carModel;
        carView.setIActionListener(this);
    }

    @Override
    public void onGas(int amount) {
        carModel.gas(amount);
    }

    @Override
    public void onBrake(int amount) {
        carModel.brake(amount);
    }

    @Override
    public void onStart() {
        carModel.startCars();
    }

    @Override
    public void onStop() {
        carModel.stopCars();
    }

    @Override
    public void onTurboOn() {
        carModel.turboOn();
    }

    @Override
    public void onTurboOff() {
        carModel.turboOff();
    }

    @Override
    public void onLiftBed() {
        carModel.liftBed();
    }

    @Override
    public void onLowerBed() {
        carModel.lowerBed();
    }

    @Override
    public void onTurnRight() {
        carModel.turnRight();
    }

    @Override
    public void onTurnLeft() {
        carModel.turnLeft();
    }

    @Override
    public void onAddCar() {
        carModel.addCar();
    }

    @Override
    public void onRemoveCar() {
        carModel.removeCar();
    }
}