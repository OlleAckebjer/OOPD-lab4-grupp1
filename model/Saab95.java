package model;

import java.awt.*;

public class Saab95 extends Cars implements IHasTurbo {
    boolean turboOn = false;

    public Saab95() {
        this(new Point(0, 0));
    }

    public Saab95(Point position) {
        super(2, 125, Color.RED, "Saab95", new Point(position));
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn)
            turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}