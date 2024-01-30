package org.example;

import java.util.ArrayList;
import java.util.List;

public interface Parkable {
    List<Parkable> parkables = new ArrayList<>();
    ParkId park(Car car);
    void addParkable(Parkable parkable);
}
