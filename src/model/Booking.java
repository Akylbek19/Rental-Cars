package model;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int carId;
    private int clientId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(int id, int carId, int clientId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.carId = carId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Геттеры
    public int getId() { return id; }
    public int getCarId() { return carId; }
    public int getClientId() { return clientId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    @Override
    public String toString() {
        return String.format("Booking #%d: Car=%d, Client=%d, %s - %s",
                id, carId, clientId, startDate, endDate);
    }
}