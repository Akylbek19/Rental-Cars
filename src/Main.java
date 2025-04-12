import model.Client;
import service.CarService;
import service.ClientService;
import service.BookingService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CarService carService = new CarService();
    private static final ClientService clientService = new ClientService();
    private static final BookingService bookingService =
            new BookingService(carService);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> manageCars();
                case 2 -> bookCarMenu();
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong choice!");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Car rental system ===");
        System.out.println("1. Car management");
        System.out.println("2. Car booking");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mistake! Enter a number.");
            }
        }
    }



    private static void manageCars() {
        while (true) {
            System.out.println("\n=== Car management ===");
            System.out.println("1. Add a car");
            System.out.println("2. Show all cars");
            System.out.println("3. Upgrade your car");
            System.out.println("4. Delete a car");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCar();
                case 2 -> showAllCars();
                case 3 -> updateCar();
                case 4 -> deleteCar();
                case 0 -> { return; }
                default -> System.out.println("Wrong choice!");
            }
        }
    }

    private static void addCar() {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        carService.addCar(brand, model);
        System.out.println("A car has been added!");
    }

    private static void showAllCars() {
        System.out.println("\nList of cars:");
        carService.getAllCars().forEach(System.out::println);
    }

    private static void updateCar() {
        System.out.print("ID the car: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("A new brand: ");
        String newBrand = scanner.nextLine();
        System.out.print("The new model: ");
        String newModel = scanner.nextLine();
        carService.updateCar(id, newBrand, newModel);
        System.out.println("The car has been updated!");
    }

    private static void deleteCar() {
        System.out.print("ID the car: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        carService.deleteCar(id);
        System.out.println("The car was deleted!");
    }

    private static void bookCarMenu() {
        try {
            System.out.println("\n=== Car booking ===");

            // Ввод данных автомобиля
            System.out.print("Enter the car ID: ");
            int carId = Integer.parseInt(scanner.nextLine());

            // Ввод данных клиента
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your phone: ");
            String phone = scanner.nextLine();

            // Ввод дат
            System.out.print("Enter the start date (dd.мм.yyyy): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            System.out.print("Enter the end date (dd.мм.yyyy): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            // Создание бронирования
            bookingService.createBooking(
                    carId,
                    clientService.findOrCreateClient(name, email,phone).getId(),
                    startDate,
                    endDate
            );
            System.out.println("The booking has been successfully created!");
        } catch (Exception e) {
            System.err.println("Mistake: " + e.getMessage());
        }
    }
}