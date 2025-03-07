import java.util.ArrayList;
import java.util.Arrays;

import controller.CarController;
import model.CarFactory;
import model.CarModel;
import model.Garage;
// import model.GarageImagePair;
import model.Volvo240;
import view.CarView;
import view.DrawPanel;
import view.ImageFactory;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class CarGame {
        public static void main(String[] args) {

                CarModel carModel = new CarModel();

                carModel.addCars(
                        new ArrayList<>(Arrays.asList(CarFactory.createVolvo240(new Point(0, 0)),
                                CarFactory.createSaab95(new Point(0, 100)),
                                CarFactory.createScania(new Point(0, 200)))));

                BufferedImage garageImage = ImageFactory.createImage("/pics/VolvoBrand.jpg");

                Garage<Volvo240> volvoGarage = new Garage<>(10, new Point(300, 300));
                carModel.setGarage(volvoGarage);


                DrawPanel drawPanel = new DrawPanel(800, 560, carModel, garageImage);

                drawPanel.addImages(new ArrayList<>(Arrays.asList(
                        ImageFactory.createVolvoImage(),
                        ImageFactory.createSaabImage(),
                        ImageFactory.createScaniaImage())));

                CarView frame = new CarView("CarSim 1.0", drawPanel);

                CarController carController = new CarController(carModel, frame);

                carModel.start();
        }
}