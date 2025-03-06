package model;

import java.awt.*;

public class Volvo240 extends Cars {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        this(new Point(0, 0));
    }

    public Volvo240(Point position) {
        super(4, 100, Color.BLACK, "Volvo240", new Point(position));
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}