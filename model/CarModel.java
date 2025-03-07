package model;

import model.Cars.Direction;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Currently responsible for far too many things. Break actions into a class, and timer simulation into another?
public class CarModel implements ICarImages{

    ArrayList<Cars> cars = new ArrayList<>();
    private final CarSimManager carSim;
    private final GarageManager garageManager;
    private final CarEventManager carEventManager;
    private final List<ICarModelListener> listeners = new ArrayList<>();
    private Garage<Volvo240> volvoGarage;

    public CarModel() {
        this.carSim = new CarSimManager(this, 50);
        this.garageManager = new GarageManager();
        this.carEventManager = new CarEventManager();
    }

    public void addCars(ArrayList<Cars> cars) {
        this.cars.addAll(cars);
    }

    public ArrayList<Cars> getCars(){ return cars; }

    public void start() {
        carSim.start();
    }

    public void stop() {
        carSim.stop();
    }

    public void addListener(ICarModelListener listener) {
        carEventManager.addListener(listener);
    }

    public void removeListener(ICarModelListener listener) {
        carEventManager.removeListener(listener);
    }

    public void notifyListeners() {
        carEventManager.notifyListeners();
    }

    public void setGarage(Garage<Volvo240> volvoGarage) {
        garageManager.setGarage(volvoGarage);
    }

    public Garage<Volvo240> getGarage() {
        return garageManager.getGarage();
    }
}