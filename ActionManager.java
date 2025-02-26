import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionManager implements IObjectsArrayList, ICarPoints {
    private final Timer timer;
    private Garage<Volvo240> garage;
    private CarController cc = new CarController();

    private CarView frame;

    public ActionManager() {
        int delay = 50;
        this.timer = new Timer(delay, new TimerListener());
        frame = new CarView("CarSim 1.0", cc);
        garage = new Garage<Volvo240>(10);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
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
                    int x2 = 300;
                    int y2 = 300;
                    double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
                    if (distance < 25) {
                        loadCarToWorkshop(car);
                        break; // break out of the loop
                    }
                }

                car.move();

                int index = cars.indexOf(car);
                frame.drawPanel.movePoints(index, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }

        }
    }

    private boolean isCarOutOfBounds(int x, int y) {
        return x < 0 || x > 700 || y < 0 || y > 500;
    }

    void loadCarToWorkshop(Cars car) {
        car.stopEngine();
        garage.addCar((Volvo240) car);
        cars.remove(car);
    }

    public static void main(String[] args) {
        // CarModel model = new CarModel();

        ActionManager actionManager = new ActionManager();

        IObjectsArrayList.cars.add(CarFactory.createVolvo240());
        IObjectsArrayList.cars.add(CarFactory.createSaab95());
        IObjectsArrayList.cars.add(CarFactory.createScania());

        actionManager.start();
        // Start the timer
        // cc.timer.start();
    }

}