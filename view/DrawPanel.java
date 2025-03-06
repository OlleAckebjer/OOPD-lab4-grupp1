package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.CarModel;
import model.ICarModelListener;
import model.ICarsArrayList;

import java.util.Objects;
import model.NotLoadedState;
// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements ICarsArrayList, ICarModelListener {

    private CarModel carModel;
    private BufferedImage garageImage;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarModel carModel, BufferedImage garageImage) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.carModel = carModel;
        carModel.addListener(this);
        this.garageImage = garageImage;

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getState() instanceof NotLoadedState) {

                g.drawImage(carImages.get(i), cars.get(i).getX(), cars.get(i).getY(),
                        null);
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