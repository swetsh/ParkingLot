package org.example;

import java.util.*;

public class ParkingLot {

    private final Map<Car.Color, Integer> mapColor;
    private final Map<Integer, Car> cars;
    private final PriorityQueue<Integer> slots;

    private final int totalSlots;

    public ParkingLot(int totalSlots) {
        if (totalSlots < 0) {
            throw new RuntimeException();
        }

        this.totalSlots = totalSlots;

        this.slots = new PriorityQueue<>();

        for (int i=0; i<totalSlots; i++) {
            slots.add(i);
        }

        this.mapColor = new HashMap<>();
        this.cars = new HashMap<>();
    }

    public boolean isSlotFull() {
        return slots.isEmpty();
    }

    public int park(Car car) {
        if (isSlotFull()) {
            throw new RuntimeException();
        }
        if (cars.containsValue(car)) {
            throw new RuntimeException();
        }
        mapColor.put(car.color(), mapColor.getOrDefault(car.color(), 0) + 1);

        int closestSlot = slots.poll();
        cars.put(closestSlot, car);
        return closestSlot;
    }

    public Car unPark(int slotNo) {
        if (slotNo >= totalSlots || slotNo < 0) {
            throw new RuntimeException();
        }

        if (slots.contains(slotNo)) {
            throw new RuntimeException();
        }

        slots.add(slotNo);
        return cars.get(slotNo);
    }

    public int countByColor(Car.Color color) {
        return mapColor.getOrDefault(color, 0);
    }

}
