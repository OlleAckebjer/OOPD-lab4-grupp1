package model;

public class InGarageState implements ICarState {
    @Override
    public void handleState(Cars car) {
        System.out.println("Car is in garage.");
    }

}
