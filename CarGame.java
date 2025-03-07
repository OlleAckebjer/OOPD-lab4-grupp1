import java.util.ArrayList;
import java.util.Arrays;

import controller.CarController;
import model.CarFactory;
import model.CarModel;
import model.Garage;
import model.Volvo240;
import view.CarView;
import view.DrawPanel;
import view.ImageFactory;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class CarGame {
        public static void main(String[] args) {

                int delay = 50;
                int X = 800;
                int Y = 560;
                int maxAmountCars = 10;

                CarModel carModel = new CarModel(X, Y, delay, maxAmountCars);

                carModel.addCars(
                                new ArrayList<>(Arrays.asList(CarFactory.createVolvo240(new Point(0, 0)),
                                                CarFactory.createSaab95(new Point(0, 100)),
                                                CarFactory.createScania(new Point(0, 200)))));

                BufferedImage garageImage = ImageFactory.createImage("/pics/VolvoBrand.jpg");

                Garage<Volvo240> volvoGarage = new Garage<>(10, new Point(300, 300), 100, 100);
                carModel.setGarage(volvoGarage);

                ArrayList<BufferedImage> carImages = new ArrayList<>(Arrays.asList(
                                ImageFactory.createVolvoImage(),
                                ImageFactory.createSaabImage(),
                                ImageFactory.createScaniaImage()));

                DrawPanel drawPanel = new DrawPanel(carModel, garageImage, carImages);

                CarView frame = new CarView("CarSim 1.0", carModel, drawPanel);

                int gasAmount = 100;
                CarController carController = new CarController(carModel, frame, drawPanel, gasAmount);

                carModel.start();
        }
}