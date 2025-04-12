# 🚗 Car Rental System - Project Documentation
**Student:** Khakimov Akylbek (EEAIR-24)

## 📝 Project Description
Console application for car rental management with CRUD operations, data persistence in CSV files, and report generation.

## 🎯 Objectives
1. Implement car/clients/booking management
2. Ensure data persistence between sessions
3. Provide user-friendly console interface
4. Validate all user inputs
5. Generate operational reports

## ✅ Project Requirements
1. **CRUD Operations** for Cars/Clients/Bookings
2. **Console Menu** with intuitive navigation
3. **Input Validation**:
    - Email format validation
    - Date format checking (dd.MM.yyyy)
    - Phone number validation
4. **Data Storage** in CSV files:
    - cars.csv
    - clients.csv
    - bookings.csv
5. **Modular Architecture**:
    - Separate classes for Model/Service/Util
6. **Reporting**:
    - Available cars list
7. **Error Handling** for all user inputs
8. **Error handling**
    - Protection against incorrect input
    - Logging errors in the `error.log` file
    - Competent messages to the user
9. **Conflict check**
    - Prohibition of double booking of one car
    - Date intersection check

## 🧠 Implementation Details
=== Car rental system ===
1. Car management
2. Car booking
3. Exit
   Choose: 1

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 1
* Brand: BMW
* Model: E34
* A car has been added!

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 1
* Brand: MERSEDEZ-BENZ
* Model: W210
* A car has been added!

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 2

List of cars:
* ID: 1 | BMW E34 | Status: Available
* ID: 2 | MERSEDEZ-BENZ W210 | Status: Available

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 0

=== Car rental system ===
1. Car management
2. Car booking
3. Exit
* Choose: 2

=== Car booking ===
* Enter the car ID: 1
* Enter your name: Akylbek
* Enter your email: Akylbekkk@gmail.com
* Enter your phone: 0706190506
* Enter the start date (dd.мм.yyyy): 10.10.2024
* Enter the end date (dd.мм.yyyy):

**Mistake: Text '' could not be parsed at index 0**

=== Car rental system ===
1. Car management
2. Car booking
3. Exit
    * Choose: 2

=== Car booking ===
* Enter the car ID: 1
* Enter your name: akylbek
* Enter your email: Akylbek@gmail.com
* Enter your phone: 0706190506
* Enter the start date (dd.мм.yyyy): 10.10.2024
* Enter the end date (dd.мм.yyyy): 10.11.2024
* Booking #6 successfully created!
* The booking has been successfully created!

=== Car rental system ===
1. Car management
2. Car booking
3. Exit
    * Choose: 1

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 2

List of cars:
* ID: 1 | BMW E34 | Status: Busy until 10.11.2024
* ID: 2 | MERSEDEZ-BENZ W210 | Status: Available

=== Car management ===
1. Add a car
2. Show all cars
3. Upgrade your car
4. Delete a car
5. Back
* Choose: 0

=== Car rental system ===
1. Car management
2. Car booking
3. Exit
* Choose: 0

Process finished with exit code 0


### Data Structures
- `ArrayList` for collections storage
- `LocalDate` for rental periods
- `DateTimeFormatter` for date parsing

### File Structure
###### /data
* cars.csv
* clients.csv
* bookings.csv
* /src
###### /model
* Car.java
* Client.java
* Booking.java
###### /service
* BookingService
* CarService.java
* ClientService
* FileService
###### /util
* ReportGenerator
* Validator
###### Main.java
###### Readme.md


## 📊 Presentation

The project presentation is available on Canva:  
[![Canva Presentation](https://img.shields.io/badge/View_Presentation-Canva-%2300C4CC?style=for-the-badge&logo=canva)](https://www.canva.com/design/DAGkcFECiDQ/3U9C28YKZ-2d1Ri7LzYqYA/view)