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

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarModel carModel) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        carModel.addListener(this);
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
        for (int i = 0; i < garageImages.size(); i++) {
            g.drawImage(garageImages.get(i), 300, 300, null); // TODO: fix hard coded values
        }

    }

    @Override
    public void onCarModelUpdated() {
        repaint();
    }
}