package org.example;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {
    public enum Color {
        RED,BLUE,BLACK, WHITE;
    }
    private final String registrationNo;

    private final Color color;

    public Car(String registrationNo, Color color) {
        if (!isValidRegistrationNo(registrationNo)) {
            throw new RuntimeException();
        }
        this.registrationNo = registrationNo;
        this.color = color;
    }

    private boolean isValidRegistrationNo(String registrationNo) {
        String regex = "^[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(registrationNo);
        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;

        return Objects.equals(registrationNo, car.registrationNo) && color == car.color;
    }

    public Color color() {
        return this.color;
    }

}
