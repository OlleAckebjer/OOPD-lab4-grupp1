package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import model.CarModel;
import model.Cars;
import model.ICarModelListener;

import java.util.ArrayList;
import model.NotLoadedState;
import model.Saab95;
import model.Scania;
import model.Volvo240;

public class DrawPanel extends JPanel implements ICarModelListener {

    private CarModel carModel;
    private BufferedImage garageImage;
    private ArrayList<BufferedImage> carImages;

    // Initializes the panel and reads the images
    public DrawPanel(CarModel carModel, BufferedImage garageImage, ArrayList<BufferedImage> carImages) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(carModel.getX(), carModel.getY()));
        this.setBackground(Color.green);
        this.carModel = carModel;
        carModel.addListener(this);
        this.garageImage = garageImage;
        this.carImages = carImages;
    }

    public void setImageToLastCar() {
        if (carModel.getLastCar() instanceof Volvo240) {
            carImages.add(ImageFactory.createVolvoImage());
        } else if (carModel.getLastCar() instanceof Saab95) {
            carImages.add(ImageFactory.createSaabImage());
        } else if (carModel.getLastCar() instanceof Scania) {
            carImages.add(ImageFactory.createScaniaImage());
        }

    }

    public void removeImage() {
        if (carImages.size() > 0) {
            carImages.remove(carImages.size() - 1);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Cars> cars = carModel.getCars();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getState() instanceof NotLoadedState) {
                g.drawImage(carImages.get(i), cars.get(i).getX(), cars.get(i).getY(), null);
            }
        }
        Point garagePosition = carModel.getGarage().getPosition();

        g.drawImage(garageImage, garagePosition.x, garagePosition.y, null);

    }

    @Override
    public void onCarModelUpdated() {
        repaint();
    }
}
