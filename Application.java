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

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Application {
        public static void main(String[] args) {
                // CarModel model = new CarModel();

                CarModel carModel = new CarModel();

                carModel.addCars(
                                new ArrayList<>(Arrays.asList(CarFactory.createVolvo240(), CarFactory.createSaab95(),
                                                CarFactory.createScania())));

                try {
                        BufferedImage volvoImage = ImageIO
                                        .read(Objects.requireNonNull(Application.class.getResourceAsStream(
                                                        "/pics/Volvo240.jpg")));
                        BufferedImage saabImage = ImageIO
                                        .read(Objects
                                                        .requireNonNull(Application.class
                                                                        .getResourceAsStream("/pics/Saab95.jpg")));
                        BufferedImage scaniaImage = ImageIO
                                        .read(Objects
                                                        .requireNonNull(Application.class
                                                                        .getResourceAsStream("/pics/Scania.jpg")));
                        BufferedImage garageImageFile = ImageIO
                                        .read(Objects
                                                        .requireNonNull(Application.class
                                                                        .getResourceAsStream("/pics/VolvoBrand.jpg")));

                        ICarsArrayList.carImages.addAll(Arrays.asList(volvoImage, saabImage, scaniaImage));
                        BufferedImage garageImage = garageImageFile;
                        carModel.setGarageImage(garageImage);

                } catch (IOException ex) {
                        ex.printStackTrace();
                }

                Garage<Volvo240> volvoGarage = new Garage<>(10, new Point(300, 300));
                carModel.setGarage(volvoGarage);

                ICarController carController = new CarController();

                CarView frame = new CarView("CarSim 1.0", carController, carModel);

                carModel.start();
        }
}