import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements ICarPoints {

    // TODO: Refactor Images into Interface Array for ActionManager and DrawPanel
    List<BufferedImage> carImages;

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

//    void moveit(int index, int x, int y) {
//
//        carPoints.get(index).x = x;
//        carPoints.get(index).y = y;
//
//    }

//    void loadCarToWorkshop() {
//        carImages.remove(0);
//        carPoints.remove(0);
//    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to
            // pics.
            // if you are starting in IntelliJ.

            BufferedImage volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            BufferedImage saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            BufferedImage scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));

            carImages = new java.util.ArrayList<>(java.util.Arrays.asList(volvoImage, saabImage, scaniaImage));
            /*
             * carPoints.add(new Point(0, 0));
             * carPoints.add(new Point(0, 100));
             * carPoints.add(new Point(0, 200));
             */

            volvoWorkshopImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < carImages.size(); i++) {
            g.drawImage(carImages.get(i), carPoints.get(i).x, carPoints.get(i).y, null);
        }

        // more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}