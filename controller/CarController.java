package controller;

import model.*;
import view.CarView;
import view.DrawPanel;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController {

    private CarModel carModel;
    private int gasAmount;
    private CarView carView;
    private DrawPanel drawPanel;

    public CarController(CarModel carModel, CarView carView, DrawPanel drawPanel, int gasAmount) {
        this.carModel = carModel;
        this.carView = carView;
        this.gasAmount = gasAmount;
        this.drawPanel = drawPanel;
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        carView.gasButton.addActionListener(e -> carModel.gas(gasAmount));
        carView.brakeButton.addActionListener(e -> carModel.brake(gasAmount));
        carView.startButton.addActionListener(e -> carModel.startCars());
        carView.stopButton.addActionListener(e -> carModel.stopCars());
        carView.turboOnButton.addActionListener(e -> carModel.turboOn());
        carView.turboOffButton.addActionListener(e -> carModel.turboOff());
        carView.liftBedButton.addActionListener(e -> carModel.liftBed());
        carView.lowerBedButton.addActionListener(e -> carModel.lowerBed());
        carView.turnRightButton.addActionListener(e -> carModel.turnRight());
        carView.turnLeftButton.addActionListener(e -> carModel.turnLeft());
        carView.addCarButton.addActionListener(e -> {
            if (carModel.canCreateMoreCars()) {
                carModel.addRandomCar();

                drawPanel.setImageToLastCar();
            }
        });
        carView.removeCarButton.addActionListener(e -> {
            carModel.removeCar();
            drawPanel.removeImage();
        });
    }

}