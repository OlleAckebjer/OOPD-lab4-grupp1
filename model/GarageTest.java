package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

public class GarageTest {

    @BeforeEach
    void setup() {
    }

    @Test
    void addCarTest() {
        Garage<Cars> garage = new Garage<>(10,new Point(0,0));
        Cars volvo240 = new Volvo240();
        Cars saab = new Saab95();
        garage.addCar(volvo240);
        garage.addCar(saab);
        assertEquals(saab, garage.getCar(1));
        assertEquals(volvo240, garage.getCar(0));
    }

    @Test
    void addCarTestVolvo() {
        Garage<Volvo240> garage = new Garage<>(10,new Point(0,0));
        Volvo240 volvo240 = new Volvo240();
        garage.addCar(volvo240);
        assertEquals(volvo240, garage.getCar(0));
    }

    @Test
    void addMaxCarsTest() {
        Garage<Cars> garage = new Garage<>(1,new Point(0,0));
        Cars volvo240 = new Volvo240();
        garage.addCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> garage.addCar(new Saab95()));
    }

    @Test
    void addSameCarTwiceTest() {
        Garage<Cars> garage = new Garage<>(10,new Point(0,0));
        Cars volvo240 = new Volvo240();
        garage.addCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> garage.addCar(volvo240));
    }

    @Test
    void getCarTest() {
        Garage<Cars> garage = new Garage<>(10,new Point(0,0));
        Cars volvo240 = new Volvo240();
        garage.addCar(volvo240);
        assertEquals(volvo240, garage.getCar(0));
        assertThrows(IllegalStateException.class, () -> garage.getCar(1));
    }

    @Test
    void getLastCarTest() {
        Garage<Cars> garage = new Garage<>(10,new Point(0,0));
        Cars volvo240 = new Volvo240();
        Cars saab = new Saab95();
        garage.addCar(volvo240);
        garage.addCar(saab);
        assertEquals(saab, garage.getLastCar());
        assertEquals(volvo240, garage.getLastCar());
    }

    @Test
    void getInvalidIndexTest() {
        Garage<Cars> garage = new Garage<>(10,new Point(0,0));
        assertThrows(IllegalStateException.class, () -> garage.getCar(0));
    }

}