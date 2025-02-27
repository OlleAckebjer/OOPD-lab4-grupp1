import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ActionManager implements ICarsArrayList, ICarPoints {
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
                        continue;
                    }
                }

                car.move();
                frame.drawPanel.repaint();
            }

        }
    }

    private boolean isCarOutOfBounds(int x, int y) {
        return x < 0 || x > 700 || y < 0 || y > 500;
    }

    void loadCarToWorkshop(Cars car) {
        if (carIsLoaded.get(cars.indexOf(car))) {
            return;
        }
        car.stopEngine();
        garage.addCar((Volvo240) car);

        // cars.remove(car);

        carIsLoaded.set(cars.indexOf(car), true);

    }

    public static void main(String[] args) {
        // CarModel model = new CarModel();

        ActionManager actionManager = new ActionManager();

        actionManager.addCars(
                new ArrayList<>(Arrays.asList(CarFactory.createVolvo240(), CarFactory.createSaab95(),
                        CarFactory.createScania())));

        try {
            BufferedImage volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(
                    "pics/Volvo240.jpg")));
            BufferedImage saabImage = ImageIO
                    .read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            BufferedImage scaniaImage = ImageIO
                    .read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            ICarsArrayList.carImages.addAll(Arrays.asList(volvoImage, saabImage, scaniaImage));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        actionManager.start();
    }
}