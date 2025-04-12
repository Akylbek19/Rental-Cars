package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Car {
    private int id;
    private String brand;
    private String model;
    private boolean available;
    private LocalDate bookedUntil;

    public Car(int id, String brand, String model, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.available = available;
    }


    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("The brand cannot be empty");
        }
        this.brand = brand;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("The model cannot be empty");
        }
        this.model = model;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBookedUntil(LocalDate bookedUntil) {
        this.bookedUntil = bookedUntil;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public boolean isAvailable() { return available; }
    public LocalDate getBookedUntil() { return bookedUntil; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String status = available ? "Available" :
                "Busy until " + (bookedUntil != null ? bookedUntil.format(formatter) : "N/A");
        return String.format("ID: %d | %s %s | Status: %s",
                id, brand, model, status);
    }
}