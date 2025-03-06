package model;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Currently responsible for far too many things. Break actions into a class, and timer simulation into another?
public class CarModel implements ICarsArrayList {
    private final Timer timer;
    private final Garage<Volvo240> garage;
    private final List<ICarModelListener> listeners = new ArrayList<>();

    public CarModel() {
        int delay = 50;
        this.timer = new Timer(delay, new TimerListener());
        this.garage = new Garage<Volvo240>(10, new java.awt.Point(300, 300));
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

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (isCarOutOfBounds(x, y)) {
                    car.turnAround();
                }

                if (car instanceof Volvo240) {
                    int x2 = 300, y2 = 300;
                    double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
                    if (distance < 25) {
                        loadCarToWorkshop(car);
                        continue;
                    }
                }
                car.move();
            }
            notifyListeners();
        }
    }

    private boolean isCarOutOfBounds(int x, int y) {
        return x < 0 || x > 700 || y < 0 || y > 500;
    }

    private void loadCarToWorkshop(Cars car) {
        if (car.getState() instanceof InGarageState) {
            return;
        }
        car.stopEngine();
        garage.addCar((Volvo240) car);
        car.setState(new InGarageState());

        // TODO: Could use methods for adding / removing new cars.
    }

}
