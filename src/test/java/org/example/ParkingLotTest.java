package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotTest {
    @Test
    public void TestHandleNegativeSlots() {
        assertThrows(RuntimeException.class, () -> {
            new ParkingLot(-1);
        });
    }

    @Test
    public void TestIfSlotIsFull() {
        ParkingLot parkingLot = new ParkingLot(0);
        assertTrue(parkingLot.isSlotFull());
    }

    @Test
    public void TestIfSlotIsEmpty() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertFalse(parkingLot.isSlotFull());
    }

    @Test
    public void TestIfSlotGetFullAfterPark() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("JH17A1234", Car.Color.BLUE);
        parkingLot.park(car);
        assertTrue(parkingLot.isSlotFull());
    }

    @Test
    public void TestCarParked() {
        Car car = new Car("JH17A1234", Car.Color.BLUE);
        ParkingLot parkingLot = new ParkingLot(1);

        ParkId parkId = parkingLot.park(car);

        assertEquals(new ParkId(0,0), parkId);
    }

    @Test
    public void Test2CarParkedOnThreeInitialSlots() {
        Car car1 = new Car("JH17A1235", Car.Color.BLACK);
        Car car2 = new Car("JH17A1236", Car.Color.BLUE);
        ParkingLot parkingLot = new ParkingLot(3);

        ParkId parkId1 = parkingLot.park(car1);
        ParkId parkId2 = parkingLot.park(car2);

        assertEquals(new ParkId(0,0), parkId1);
        assertEquals(new ParkId(0,1), parkId2);
    }

    @Test
    public void TestCarCannotBeParkedIfSlotsFull() {
        Car car1 = new Car("JH17A1235", Car.Color.BLACK);
        Car car2 = new Car("JH17A1236", Car.Color.BLUE);
        ParkingLot parkingLot = new ParkingLot(1);

        ParkId parkId1 = parkingLot.park(car1);

        assertThrows(RuntimeException.class, () -> {
            ParkId parkId2 = parkingLot.park(car2);
        });
    }

    @Test
    public void TestCountColorIfNoCarParked() {
        ParkingLot parkingLot = new ParkingLot(10);

        assertEquals(0, parkingLot.countByColor(Car.Color.WHITE));
    }

    @Test
    public void TestCountColorIfOneDifferentColorCarParked() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("JH17A1235", Car.Color.BLACK);
        parkingLot.park(car);

        assertEquals(0, parkingLot.countByColor(Car.Color.WHITE));
    }

    @Test
    public void TestCountColorIfOneSameColorCarParkedExpectOne() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("JH17A1235", Car.Color.BLACK);
        parkingLot.park(car);

        assertEquals(1, parkingLot.countByColor(Car.Color.BLACK));
    }

    @Test
    public void TestCountColorIfThreeSameColorCarParkedExpectOne() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("JH17A1235", Car.Color.BLACK);
        Car car2 = new Car("JH17A1236", Car.Color.BLACK);
        Car car3 = new Car("JH17A1246", Car.Color.BLACK);

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);

        assertEquals(3, parkingLot.countByColor(Car.Color.BLACK));
    }



    @Test
    public void TestShouldFailIfSameCarParkedTwice() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("JH17A1246", Car.Color.BLACK);

        assertThrows(RuntimeException.class, () -> {
            parkingLot.park(car);
            parkingLot.park(car);
        });
    }

    @Test
    public void TestUnParkAtInvalidNegativeSlot() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertThrows(RuntimeException.class, () -> {
            parkingLot.unPark(-1);
        });
    }

    @Test
    public void TestUnParkAtInvalidSPositiveSlot() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertThrows(RuntimeException.class, () -> {
            parkingLot.unPark(2);
        });
    }

    @Test
    public void TestUnParkAtSlotAlreadyEmpty() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertThrows(RuntimeException.class, () -> {
            parkingLot.unPark(0);
        });
    }

    @Test
    public void TestUnParkAtCorrectSlotWithoutException() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("JH17A1246", Car.Color.BLACK);

        ParkId parkId1 = parkingLot.park(car);

        assertNotEquals(new Car("JH17A8888", Car.Color.BLACK), parkingLot.unPark(parkId1.slotNumber()));

    }

    @Test
    public void TestUnParkAtCorrectSlotReturnSameCar() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("JH17A1246", Car.Color.BLACK);

        ParkId parkId = parkingLot.park(car);

        assertEquals(new Car("JH17A1246", Car.Color.BLACK), parkingLot.unPark(parkId.slotNumber()));

    }

    @Test
    public void TestUnParkingShouldEmptyFilledSlot() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("JH17A1246", Car.Color.BLACK);
        Car car2 = new Car("JH17A1241", Car.Color.BLACK);

        ParkId parkId1 = parkingLot.park(car1);
        ParkId parkId2 = parkingLot.park(car2);

        assertTrue(parkingLot.isSlotFull());

        parkingLot.unPark(parkId2.slotNumber());

        assertFalse(parkingLot.isSlotFull());

    }

    @Test
    public void TestAddParkable() {
        ParkingStation parkingStation = mock(ParkingStation.class);

        ParkingLot parkingLot = new ParkingLot(2);

        parkingLot.addParkable(parkingStation);

        // Using Mockito to verify that the addParkable method was called with the correct argument
        verify(parkingStation).addParkable(parkingLot);
    }

    @Test
    public void TestAddingMultipleParkable() {
        ParkingStation parkingStation1 = mock(ParkingStation.class);
        ParkingStation parkingStation2 = mock(ParkingStation.class);
        ParkingStation parkingStation3 = mock(ParkingStation.class);

        ParkingLot parkingLot = new ParkingLot(2);

        parkingLot.addParkable(parkingStation1);
        parkingLot.addParkable(parkingStation2);
        parkingLot.addParkable(parkingStation3);

        verify(parkingStation1).addParkable(parkingLot);
        verify(parkingStation2).addParkable(parkingLot);
        verify(parkingStation3).addParkable(parkingLot);
    }

    @Test
    public void TestParkingLotCanNotbeAdded() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);

        assertThrows(RuntimeException.class, () -> {
            parkingLot.addParkable(parkingLot2);
        });
    }


}