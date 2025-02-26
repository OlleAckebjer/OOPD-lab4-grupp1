import java.awt.Point;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and respond in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements ICarsArrayList {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    //private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    //private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    //ArrayList<Cars> cars = new ArrayList<>();

    // methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        ICarsArrayList.cars.add(new Volvo240());

        Saab95 saab = new Saab95();
        saab.setPosition(new Point(0, 100));
        ICarsArrayList.cars.add(saab);

        Scania scania = new Scania();
        scania.setPosition(new Point(0, 200));
        ICarsArrayList.cars.add(scania);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        //cc.timer.start();
    }

    /*
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */


//    private class TimerListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//
//            for (Cars car : cars) {
//
//                int x = (int) Math.round(car.getPosition().getX());
//                int y = (int) Math.round(car.getPosition().getY());
//
//                if (isCarOutOfBounds(x, y)) {
//                    car.turnLeft();
//                    car.turnLeft();
//                }
//
//                if (car instanceof Volvo240) {
//                    int x2 = 300;
//                    int y2 = 300;
//                    double distance = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
//                    if (distance < 25) {
//                        car.stopEngine();
//                        frame.drawPanel.loadCarToWorkshop();
//                        cars.remove(car);
//                        break; // break out of the loop
//                    }
//                }
//
//                car.move();
//
//                int index = cars.indexOf(car);
//                frame.drawPanel.moveit(index, x, y);
//                // repaint() calls the paintComponent method of the panel
//                frame.drawPanel.repaint();
//            }
//
//        }
//    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;

        for (Cars car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Cars car : cars) {
            car.brake(brake);
        }
    }

    void startCars() {
        for (Cars car : cars) {
            try {
                car.startEngine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void stopCars() {
        for (Cars car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseRamp();
            }
        }
    }

    void lowerBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerRamp();
            }
        }
    }

    void turnRight() {
        for (Cars car : cars) {
            car.turnRight();
        }
    }

    void turnLeft() {
        for (Cars car : cars) {
            car.turnLeft();
        }
    }

//    private boolean isCarOutOfBounds(int x, int y) {
//        return x < 0 || x > 700 || y < 0 || y > 500;
//    }

}