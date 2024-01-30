package org.example;

import java.util.*;

public class ParkingStation implements Parkable {

    private ParkingLot activeLot;
    private boolean isStationFull;

    private final LinkedList<ParkingLot> emptyLots;
    private final LinkedList<ParkingLot> filledLots;
    private Map<ParkingLot, Integer> lotId;

    public ParkingStation(int totalLot, int slotPerLot) {
        if (totalLot <= 0) {
            throw new RuntimeException();
        }


        this.emptyLots = new LinkedList<>();
        this.filledLots = new LinkedList<>();
        this.lotId = new HashMap<>();

        for (int i=0; i<totalLot; i++) {
            ParkingLot parkingLot = new ParkingLot(slotPerLot);
            this.emptyLots.addLast(parkingLot);
            lotId.put(parkingLot, i);
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

    @Override
    public ParkId park(Car car) {
        int slot = this.activeLot.park(car).slotNumber()    ;
        int lot = lotId.get(this.activeLot);
        this.changeActiveLot();
        return new ParkId(lot, slot);
    }

    @Override
    public void addParkable(Parkable parkable) {
        if (parkable instanceof ParkingStation) {
            throw new RuntimeException();
        }
        parkable.addParkable(this);
    }

}
