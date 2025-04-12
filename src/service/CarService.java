package service;

import model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarService {
    private final List<Car> cars = new ArrayList<>();
    private int nextId = 1;


    public void updateCar(int id, String newBrand, String newModel) {
        Car existingCar = getCar(id);
        if (existingCar != null) {
            existingCar.setBrand(newBrand);
            existingCar.setModel(newModel);
            saveCars();
        }
    }


    public void addCar(String brand, String model) {
        Car newCar = new Car(nextId++, brand, model, true);
        cars.add(newCar);
        saveCars();
    }

    public Car getCar(int id) {
        Optional<Car> foundCar = cars.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return foundCar.orElse(null);
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    public List<Car> getAvailableCars() {
        return cars.stream()
                .filter(Car::isAvailable)
                .toList();
    }

    public void deleteCar(int id) {
        cars.removeIf(c -> c.getId() == id);
        saveCars();
    }

    private void saveCars() {
        try {
            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"id","brand","model","available","bookedUntil"});

            for (Car car : cars) {
                data.add(new String[]{
                        String.valueOf(car.getId()),
                        car.getBrand(),
                        car.getModel(),
                        String.valueOf(car.isAvailable()),
                        car.getBookedUntil() != null ? car.getBookedUntil().toString() : ""
                });
            }
            FileService.saveToCsv(data, "cars.csv");
        } catch (Exception e) {
            System.err.println("Error saving cars: " + e.getMessage());
            e.printStackTrace();
        }
    }
}