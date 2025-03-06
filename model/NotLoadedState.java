package model;

public class NotLoadedState implements ICarState {
    @Override
    public void handleState(Cars car) {
        System.out.println("Car is not loaded yet.");
    }
}
