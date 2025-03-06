import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.imageio.ImageIO;

import controller.CarController;
import controller.ICarController;
import model.CarFactory;
import model.CarModel;
import model.Garage;
import model.ICarsArrayList;
// import model.GarageImagePair;
import model.Volvo240;
import view.CarView;
import view.DrawPanel;
import view.ImageFactory;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Application {
        public static void main(String[] args) {

                CarModel carModel = new CarModel();

                carModel.addCars(
                                new ArrayList<>(Arrays.asList(CarFactory.createVolvo240(), CarFactory.createSaab95(),
                                                CarFactory.createScania())));

                ICarsArrayList.carImages.addAll(Arrays.asList(
                                ImageFactory.createVolvoImage(),
                                ImageFactory.createSaabImage(),
                                ImageFactory.createScaniaImage()));

                BufferedImage garageImage = ImageFactory.createImage("/pics/VolvoBrand.jpg");

                Garage<Volvo240> volvoGarage = new Garage<>(10, new Point(300, 300));
                carModel.setGarage(volvoGarage);

                ICarController carController = new CarController();

                DrawPanel drawPanel = new DrawPanel(800, 560, carModel, garageImage);

                CarView frame = new CarView("CarSim 1.0", carController, carModel, drawPanel);

                carModel.start();
        }
}