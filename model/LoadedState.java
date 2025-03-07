package model;

class LoadedState implements ICarState {
    @Override
    public void handleState(Cars car) {
        System.out.println("Car is loaded and ready to drive.");
    }
}
