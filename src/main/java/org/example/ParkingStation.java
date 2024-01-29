package org.example;

import java.util.*;

public class ParkingStation {
    private ParkingLot activeLot;
    private boolean isStationFull;

    private final LinkedList<ParkingLot> emptyLots;
    private final LinkedList<ParkingLot> filledLots;

    public ParkingStation(int totalLot, int slotPerLot) {
        if (totalLot <= 0) {
            throw new RuntimeException();
        }


        this.emptyLots = new LinkedList<>();
        this.filledLots = new LinkedList<>();

        for (int i=0; i<totalLot; i++) {
            this.emptyLots.addLast(new ParkingLot(slotPerLot));
        }
        this.activeLot = this.emptyLots.removeLast();

        this.isStationFull = false;
    }

    public ParkingLot activeLot() {
        return this.activeLot;
    }

    public boolean isParkingStationFull() {
        return this.isStationFull;
    }

    private void changeActiveLot() {
        if (this.activeLot.isSlotFull()) {
            this.filledLots.addLast(this.activeLot);
            if (this.emptyLots.isEmpty())
                this.isStationFull = true;
            else
                this.activeLot = emptyLots.removeLast();
        }
    }

    public int park(Car car) {
        int slot = this.activeLot.park(car);
        this.changeActiveLot();
        return slot;
    }

    public int unPark(Car car) {
        return 0;
    }
}
