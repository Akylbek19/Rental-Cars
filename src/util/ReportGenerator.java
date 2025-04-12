package util;

import model.Car;
import model.Client;
import model.Booking;
import java.util.List;

public class ReportGenerator {

    public static void generateCarsReport(List<Car> cars) {
        System.out.println("\n=== Car Report ===");
        System.out.printf("Total cars: %d\n", cars.size());
        cars.forEach(car -> System.out.println(
                "ID: " + car.getId() +
                        " | " + car.getBrand() +
                        " " + car.getModel() +
                        " | Status: " + (car.isAvailable() ? "Free" : "Busy")
        ));
    }

    public static void generateBookingsReport(List<Booking> bookings,
                                              List<Car> cars,
                                              List<Client> clients) {
        System.out.println("\n=== Booking Report ===");
        bookings.forEach(b -> {
            Car car = cars.stream()
                    .filter(c -> c.getId() == b.getCarId())
                    .findFirst()
                    .orElse(null);

            Client client = clients.stream()
                    .filter(c -> c.getId() == b.getClientId())
                    .findFirst()
                    .orElse(null);

            System.out.printf(
                    "ID: %d | Car: %s | Client: %s | Period: %s - %s\n",
                    b.getId(),
                    car != null ? car.getBrand() : "N/A",
                    client != null ? client.getName() : "N/A",
                    b.getStartDate(),
                    b.getEndDate()
            );
        });
    }

    public static void generateClientsReport(List<Client> clients) {
        System.out.println("\n=== Client Report ===");
        clients.forEach(client -> System.out.println(
                "ID: " + client.getId() +
                        " | Name: " + client.getName() +
                        " | Email: " + client.getEmail()
        ));
    }
}