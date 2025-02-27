import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements ICarsArrayList {

    // TODO: Refactor Images into Interface Array for ActionManager and DrawPanel
    // List<BufferedImage> carImages;

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {

            volvoWorkshopImage = ImageIO
                    .read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < cars.size(); i++) {
            if (!carIsLoaded.get(i)) {

                g.drawImage(carImages.get(i), cars.get(i).getX(), cars.get(i).getY(),
                        null);
            }
        }

        // more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}