package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Cars.Direction;

public class CarSimManager {
    private final CarModel carModel;
    private final Timer timer;

    public CarSimManager(CarModel carModel, int delay) {
        this.carModel = carModel;
        this.timer = new Timer(delay, new TimerListener());
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : carModel.getCars()) {
                if (isCarOutOfBounds(car)) {
                    car.turnAround();
                }
                if (car instanceof Volvo240 && shouldLoadToGarage(car)) {
                    loadCarToWorkshop(car);
                    continue;
                }
                car.move();
            }
            carModel.notifyListeners();
        }
    }

    private boolean isCarOutOfBounds(Cars car) {
        // Move isCarOutOfBounds from CarModel to here
        int x = car.getX();
        int y = car.getY();
        Direction dir = car.getDirection();

        int carHeight = 60;
        int carWidth = 100;
        return (x < 0 && dir == Direction.WEST) ||
                (x > 800 - carWidth && dir == Direction.EAST) ||
                (y < 0 && dir == Direction.SOUTH) ||
                (y > 560 - carHeight && dir == Direction.NORTH);
    }

    private boolean shouldLoadToGarage(Cars car) {
        int x = (int) Math.round(car.getPosition().getX());
        int y = (int) Math.round(car.getPosition().getY());
        int x2 = carModel.getGarage().getPosition().x;
        int y2 = carModel.getGarage().getPosition().y;

        double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
        return distance < 25;
    }

    private void loadCarToWorkshop(Cars car) {
        if (!(car.getState() instanceof InGarageState)) {
            car.stopEngine();
            carModel.getGarage().addCar((Volvo240) car);
            car.setState(new InGarageState());
        }
    }
}
