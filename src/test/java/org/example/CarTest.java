package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void TestHandleInvalidRegistrationNumber() {
        assertThrows(RuntimeException.class, () -> {
            Car car = new Car("-1", Car.Color.BLACK);
        });
    }

    @Test
    public void TestValidRegistrationNumber() {
        Car car = new Car("JH17A8824", Car.Color.BLACK);
    }

    @Test
    public void TestNotEqualsDifferentCar() {
        Car car1 = new Car("JH17A8824", Car.Color.BLACK);
        Car car3 = new Car("JH17A8829", Car.Color.BLACK);

        assertNotEquals(car1, car3);
    }

    @Test
    public void TestEquals() {
        Car car1 = new Car("JH17A8824", Car.Color.BLACK);
        Car car2 = new Car("JH17A8824", Car.Color.BLACK);

        assertEquals(car1, car2);
    }

    @Test
    public void TestCarColor() {
        Car car1 = new Car("JH17A8824", Car.Color.WHITE);

        assertEquals(Car.Color.WHITE, car1.color());
    }
}