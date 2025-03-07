package controller;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import model.*;
import view.ICarsImages;
import view.ImageFactory;

import javax.swing.*;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController{
    private final CarModel carModel;

    public CarController(CarModel carModel) {
        this.carModel = carModel;
    }

    public ActionListener getGasActionListener(JSpinner gasSpinner) {
        return e -> carModel.gas((int) gasSpinner.getValue());
    }

    public ActionListener getBrakeActionListener(JSpinner brakeSpinner) {
        return e -> carModel.brake((int) brakeSpinner.getValue());
    }

    public ActionListener getStartActionListener() {
        return e -> carModel.startCars();
    }

    public ActionListener getStopActionListener() {
        return e -> carModel.stopCars();
    }

    public ActionListener getTurboOnActionListener() {
        return e -> carModel.turboOn();
    }

    public ActionListener getTurboOffActionListener() {
        return e -> carModel.turboOff();
    }

    public ActionListener getLiftBedActionListener() {
        return e -> carModel.liftBed();
    }

    public ActionListener getLowerBedActionListener() {
        return e -> carModel.lowerBed();
    }

    public ActionListener getTurnRightActionListener() {
        return e -> carModel.turnRight();
    }

    public ActionListener getTurnLeftActionListener() {
        return e -> carModel.turnLeft();
    }

    public ActionListener getAddCarActionListener() {
        return e -> carModel.addCar();
    }

    public ActionListener getRemoveCarActionListener() {
        return e -> carModel.removeCar();
    }
}
