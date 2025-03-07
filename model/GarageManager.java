package model;

public class GarageManager {
    private Garage<Volvo240> volvoGarage;

    public void setGarage(Garage<Volvo240> volvoGarage) {
        this.volvoGarage = volvoGarage;
    }

    public Garage<Volvo240> getGarage() {
        return volvoGarage;
    }
}
