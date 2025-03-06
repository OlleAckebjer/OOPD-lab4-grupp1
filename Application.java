import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.imageio.ImageIO;

import controller.CarController;
import controller.ICarController;
import model.CarFactory;
import model.CarModel;
import model.ICarsArrayList;
import view.CarView;
import view.DrawPanel;

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
                        BufferedImage garageImage = ImageIO
                                        .read(Objects
                                                        .requireNonNull(Application.class
                                                                        .getResourceAsStream("/pics/VolvoBrand.jpg")));

                        ICarsArrayList.carImages.addAll(Arrays.asList(volvoImage, saabImage, scaniaImage));
                        ICarsArrayList.garageImages.addAll(Arrays.asList(garageImage));
                } catch (IOException ex) {
                        ex.printStackTrace();
                }

                ICarController carController = new CarController();

                CarView frame = new CarView("CarSim 1.0", carController, carModel);

                carModel.start();
        }
}