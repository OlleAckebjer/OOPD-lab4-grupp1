package controller;

public interface IActionListener {
    void onGas(int amount);
    void onBrake(int amount);
    void onStart();
    void onStop();
    void onTurboOn();
    void onTurboOff();
    void onLiftBed();
    void onLowerBed();
    void onTurnRight();
    void onTurnLeft();
    void onAddCar();
    void onRemoveCar();
}