package model;

import java.awt.*;

public class Scania extends Cars implements IHasFlatbed {
    private int flatbedAngle = 0;

    public Scania(Point position) {
        super(4, 700, Color.WHITE, "Scania", new Point(position));
    }

    public Scania() {
        this(new Point(0, 0));
    }

    // Helper method to adjust the ramp angle by a given change
    private void adjustRamp(int change) {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Can't adjust ramp while the vehicle is moving.");
        }
        flatbedAngle = Math.min(Math.max(flatbedAngle + change, 0), 70);
    }

    public void raiseRamp() {
        adjustRamp(10);
    }

    // Extra method to raise the ramp by a given angle
    public void raiseRamp(int angle) {
        adjustRamp(angle);
    }

    public void lowerRamp() {
        adjustRamp(-10);
    }

    // Extra method to lower the ramp by a given angle
    public void lowerRamp(int angle) {
        adjustRamp(angle);
    }

    // Extra method to set the ramp angle directly
    public void setRampAngle(int angle) {
        if (angle < 0 || angle > 70) {
            throw new IllegalArgumentException("Invalid angle, must be between 0 and 70.");
        }
        flatbedAngle = angle;
    }

    public int getFlatBedAngle() {
        return flatbedAngle;
    }

    @Override
    public void startEngine() {
        if (flatbedAngle > 0) {
            throw new IllegalArgumentException("Can't move vehicle while the flatbed is tilted.");
        }
        super.startEngine();
    }

    @Override
    protected double speedFactor() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 60;
    }
}