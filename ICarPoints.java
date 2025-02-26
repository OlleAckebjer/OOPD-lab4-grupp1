import java.awt.*;
import java.util.List;

public interface ICarPoints {
    List<Point> carPoints = new java.util.ArrayList<>(
            java.util.Arrays.asList(new Point(0, 0), new Point(0, 100), new Point(0, 200)));

    default void movePoints(int index, int x, int y) {

        carPoints.get(index).x = x;
        carPoints.get(index).y = y;

    }
}