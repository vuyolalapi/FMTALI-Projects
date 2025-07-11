package com.cars;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\n                                                                 Good day. Welcome to the Vehicle Registration App!\n");

        while (true) {
            displayMenu();
            int menuOption = getIntInput("Enter your choice: ", 1, 3);

            switch (menuOption) {
                case 1:
                    registerVehicle();
                    break;
                case 2:
                    displayVehicleReport();
                    break;
                case 3:
                    System.out.println("\n                                                    Thank you for using the Vehicle Registration App. Goodbye!");
                    input.close();
                    return;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Register a new vehicle");
        System.out.println("2. View vehicle report");
        System.out.println("3. Exit the application");
    }

    private static void registerVehicle() {
        Car car = new Car();

        System.out.println("\n--- Register New Vehicle ---\n");

        // Get make
        System.out.print("Enter vehicle make (e.g., BMW): ");
        car.setMake(input.nextLine());

        // Get model
        System.out.print("Enter vehicle model (e.g., M4): ");
        car.setModel(input.nextLine());

        // Get VIN with validation
        String vin;
        while (true) {
            System.out.print("Enter VIN (17 characters): ");
            vin = input.nextLine();
            if (vin.length() == 17) {
                car.setVin(vin);
                break;
            }
            System.out.println("VIN must be exactly 17 characters long.");
        }

        // Get license plate with format validation
        System.out.println("Select license plate format:");
        System.out.println("1. Old format (e.g., ABC123GP)");
        System.out.println("2. New format (e.g., AB12CDGP)");
        int plateChoice = getIntInput("Enter your choice: ", 1, 2);

        String plateNumber;
        while (true) {
            String formatPrompt = (plateChoice == 1) ?
                    "Enter old format plate number (e.g., ABC123GP): " :
                    "Enter new format plate number (e.g., AB12CDGP): ";
            System.out.print(formatPrompt);

            plateNumber = input.nextLine().toUpperCase();

            String pattern = (plateChoice == 1) ?
                    "^[A-Z]{3}[0-9]{3}[A-Z]{2}$" :
                    "^[A-Z]{2}[0-9]{2}[A-Z]{2}[A-Z]{2}$";

            if (Pattern.matches(pattern, plateNumber)) {
                break;
            }
            System.out.println("Invalid plate format. Please try again.");
        }
        car.setPlateNumber(plateNumber);

        // Get mileage
        car.setMileage(getIntInput("Enter vehicle mileage: ", 0, 1000000));

        // Get year
        car.setYear(getIntInput("Enter year of manufacture (e.g., 2020): ", 1900, 2025));

        cars.add(car);
        System.out.println("\nVehicle registered successfully!");
    }

    private static void displayVehicleReport() {
        System.out.println("""
                            ***************************************
                                      VEHICLE REPORT
                            ***************************************""");

        if (cars.isEmpty()) {
            System.out.println("No vehicles have been registered yet.");
            return;
        }

        for (Car car : cars) {
            System.out.println("---");
            System.out.println("Make: " + car.getMake());
            System.out.println("Model: " + car.getModel());
            System.out.println("VIN: " + car.getVin());
            System.out.println("Plate Number: " + car.getPlateNumber());
            System.out.println("Mileage: " + car.getMileage());
            System.out.println("Year: " + car.getYear());
        }
    }

    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(input.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a value between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

