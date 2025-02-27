import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionManager implements ICarsArrayList, ICarPoints {
    private final Timer timer;

    CarView frame;

    public ActionManager(CarController controller) {
        int delay = 50;
        this.timer = new Timer(delay, new TimerListener());
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Cars car : cars) {

                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (isCarOutOfBounds(x, y)) {
                    car.turnLeft();
                    car.turnLeft();
                }

                if (car instanceof Volvo240) {
                    int x2 = 300;
                    int y2 = 300;
                    double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
                    if (distance < 25) {
                        car.stopEngine();
                        loadCarToWorkshop();
                        cars.remove(car);
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

    void loadCarToWorkshop() {
        //carImages.removeFirst();
        carPoints.removeFirst();
    }

}