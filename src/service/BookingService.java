package service;

import model.Booking;
import model.Car;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private int nextId = 1;
    private final CarService carService;

    public BookingService(CarService carService) {
        this.carService = carService;
        loadBookings(); // Загрузка данных при создании сервиса
    }

    public void createBooking(int carId, int clientId,
                              LocalDate startDate, LocalDate endDate) {
        // Проверка дат
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Incorrect booking dates");
        }

        Car car = carService.getCar(carId);
        if (car == null) {
            throw new IllegalArgumentException("The car was not found");
        }

        if (!car.isAvailable()) {
            throw new IllegalStateException("The car is already booked");
        }

        // Обновляем статус авто
        car.setAvailable(false);
        car.setBookedUntil(endDate);
        // Сохраняем изменения через CarService
        carService.updateCar(car.getId(), car.getBrand(), car.getModel());

        // Создаем новое бронирование
        Booking newBooking = new Booking(
                nextId++,
                carId,
                clientId,
                startDate,
                endDate
        );
        bookings.add(newBooking);
        saveBookings();
        System.out.println("Booking #" + newBooking.getId() + " successfully created!");
    }

    // Загрузка бронирований из файла
    private void loadBookings() {
        // Реализация загрузки из CSV
        try {
            List<String[]> data = FileService.loadFromCsv("bookings.csv");
            for (String[] row : data) {
                bookings.add(new Booking(
                        Integer.parseInt(row[0]),
                        Integer.parseInt(row[1]),
                        Integer.parseInt(row[2]),
                        LocalDate.parse(row[3]),
                        LocalDate.parse(row[4])
                ));
                nextId = Math.max(nextId, Integer.parseInt(row[0]) + 1);
            }
        } catch (Exception e) {
            System.out.println("Bookings could not be uploaded: " + e.getMessage());
        }
    }

    // Сохранение бронирований в файл
    private void saveBookings() {
        try {
            List<String[]> data = new ArrayList<>();
            for (Booking booking : bookings) {
                data.add(new String[]{
                        String.valueOf(booking.getId()),
                        String.valueOf(booking.getCarId()),
                        String.valueOf(booking.getClientId()),
                        booking.getStartDate().toString(),
                        booking.getEndDate().toString()
                });
            }
            FileService.saveToCsv(data, "bookings.csv");
        } catch (Exception e) {
            System.err.println("Error saving bookings: " + e.getMessage());
        }
    }

    // Дополнительные методы
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getBookingsForCar(int carId) {
        return bookings.stream()
                .filter(b -> b.getCarId() == carId)
                .toList();
    }

    public boolean cancelBooking(int bookingId) {
        return bookings.removeIf(b -> b.getId() == bookingId);
    }
}