package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingStationTest {

    @Test
    public void TestShouldThrowExceptionWithNegativeLot() {
        assertThrows(RuntimeException.class, () -> {
            new ParkingStation(-1, 1);
        });
    }
    @Test
    public void TestShouldThrowExceptionWithNegativeSlot() {
        assertThrows(RuntimeException.class, () -> {
            new ParkingStation(2, -1);
        });
    }

    @Test
    public void TestShouldHaveOnlyOneActiveParkingLot() {
        ParkingStation parkingStation = new ParkingStation(3, 2);
        assertNotNull(parkingStation.activeLot());
    }

    @Test
    public void TestActiveParkingLotShouldNotBeFull() {
        ParkingStation parkingStation = new ParkingStation(3, 2);
        assertFalse(parkingStation.activeLot().isSlotFull());
    }

    @Test
    public void TestActiveRemainSameIfNotFilled() {
        ParkingStation parkingStation = new ParkingStation(3, 2);

        Car car = new Car("JH17A2246", Car.Color.BLUE);
        Car car1 = new Car("JH17A1242", Car.Color.RED);

        ParkingLot currentActiveLot = parkingStation.activeLot();
        currentActiveLot.park(car);

        assertEquals(currentActiveLot, parkingStation.activeLot());
    }


    @Test
    public void TestActiveParkingChangesAfterBeingFilled() {
        ParkingStation parkingStation = new ParkingStation(3, 2);

        Car car = new Car("JH17A2246", Car.Color.BLUE);
        Car car1 = new Car("JH17A1242", Car.Color.RED);

        ParkingLot currentActiveLot = parkingStation.activeLot();
        parkingStation.park(car);
        parkingStation.park(car1);

        assertNotEquals(currentActiveLot, parkingStation.activeLot());
    }
    @Test
    public void TestIfStationISFull() {
        ParkingStation parkingStation = new ParkingStation(3, 2);

        Car car = new Car("JH17A2246", Car.Color.BLUE);
        Car car1 = new Car("JH17A1242", Car.Color.RED);
        Car car2 = new Car("JH17A3246", Car.Color.BLACK);
        Car car3 = new Car("JH17A5245", Car.Color.WHITE);
        Car car4 = new Car("JH17A1246", Car.Color.BLACK);
        Car car5 = new Car("JH17A5445", Car.Color.WHITE);

        parkingStation.park(car);
        parkingStation.park(car1);
        parkingStation.park(car2);
        parkingStation.park(car3);
        parkingStation.park(car4);
        parkingStation.park(car5);


        assertTrue(parkingStation.isParkingStationFull());
    }

    @Test
    public void TestIfStationISNotFull() {
        ParkingStation parkingStation = new ParkingStation(3, 2);

        Car car = new Car("JH17A2246", Car.Color.BLUE);
        Car car1 = new Car("JH17A1242", Car.Color.RED);
        Car car2 = new Car("JH17A3246", Car.Color.BLACK);
        Car car3 = new Car("JH17A5245", Car.Color.WHITE);

        parkingStation.park(car);
        parkingStation.park(car1);
        parkingStation.park(car2);
        parkingStation.park(car3);


        assertFalse(parkingStation.isParkingStationFull());
    }

    @Test
    public void TestShouldThrowExceptionIfParkingToFilledStation() {
        ParkingStation parkingStation = new ParkingStation(4, 1);

        Car car = new Car("JH17A2246", Car.Color.BLUE);
        Car car1 = new Car("JH17A1242", Car.Color.RED);
        Car car2 = new Car("JH17A3246", Car.Color.BLACK);
        Car car3 = new Car("JH17A5245", Car.Color.WHITE);
        Car car4 = new Car("JH17A1246", Car.Color.BLACK);

        parkingStation.park(car);
        parkingStation.park(car1);
        parkingStation.park(car2);
        parkingStation.park(car3);


        assertThrows(RuntimeException.class, () -> {
            parkingStation.park(car4);
        });
    }
}