package util;

import model.Booking;
import model.Car;
import model.Client;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {
    public static void generateCarsReport(List<Car> cars) {
        System.out.println("\n=== Отчет по автомобилям ===");
        System.out.printf("Всего автомобилей: %d\n", cars.size());

        Map<String, Long> brandCount = cars.stream()
                .collect(Collectors.groupingBy(Car::getBrand, Collectors.counting()));

        System.out.println("\nКоличество по маркам:");
        brandCount.forEach((brand, count) ->
                System.out.printf("- %s: %d\n", brand, count));
    }

    public static void generateBookingsReport(List<Booking> bookings,
                                              List<Car> cars,
                                              List<Client> clients) {
        System.out.println("\n=== Отчет по бронированиям ===");
        System.out.printf("Всего бронирований: %d\n", bookings.size());

        System.out.println("\nПоследние 5 бронирований:");
        bookings.stream()
                .limit(5)
                .forEach(b -> {
                    Car car = cars.stream().filter(c -> c.getId() == b.getCarId()).findFirst().orElse(null);
                    Client client = clients.stream().filter(c -> c.getId() == b.getClientId()).findFirst().orElse(null);
                    System.out.printf("%s | Авто: %s | Клиент: %s\n",
                            b, car != null ? car.getBrand() + " " + car.getModel() : "N/A",
                            client != null ? client.getName() : "N/A");
                });
    }

    public static void generateDailyReport(LocalDate date, List<Booking> bookings) {
        long count = bookings.stream()
                .filter(b -> b.getStartDate().equals(date))
                .count();
        System.out.printf("\nБронирований на %s: %d\n", date, count);
    }
}