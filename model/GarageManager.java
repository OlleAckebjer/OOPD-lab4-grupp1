package model;

public class GarageManager {
    private Garage<Volvo240> volvoGarage;

    public void setGarage(Garage<Volvo240> volvoGarage) {
        this.volvoGarage = volvoGarage;
    }

    public Garage<Volvo240> getGarage() {
        return volvoGarage;
    }

    public void loadCarToWorkshop(Volvo240 car) {
        if (!(car.getState() instanceof InGarageState)) {
            car.stopEngine();
            volvoGarage.addCar(car);
            car.setState(new InGarageState());
        }
    }
}
