package smapp;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import smapp.SMapp;
import smapp.Student;
import smapp.StudentDatabase;

public class CaptureStudent {

    private static Scanner scanner;
    private static StudentDatabase database;

    public static void captureStudent(Scanner scanner, StudentDatabase database) {
        CaptureStudent.scanner = scanner;
        CaptureStudent.database = database;

        System.out.println("----------------------------------------------");
        System.out.println("Student Registration");
        System.out.println("----------------------------------------------");

        try {
            // enter name with format checking
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim(); // trim spaces
            while (name.isEmpty() || name.matches(".*\\s+.*")) {
                System.out.println("Invalid input. Please enter a name without extra spaces.");
                System.out.print("Enter name: ");
                name = scanner.nextLine().trim();
            }
            name = capitalize(name); // auto capitalize

            System.out.print("Enter surname: ");
            String surname = scanner.nextLine().trim(); // trim spaces
            while (surname.isEmpty() || surname.matches(".*\\s+.*")) {
                System.out.println("Invalid input. Please enter a surname without extra spaces.");
                System.out.print("Enter surname: ");
                surname = scanner.nextLine().trim();
            }
            surname = capitalize(surname); // auto capitalize

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim(); // trim spaces
            while (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
                System.out.print("Enter email: ");
                email = scanner.nextLine().trim();
            }
            email = email.substring(0, 1).toLowerCase() + email.substring(1); // auto small letter the first letter

            System.out.print("Enter student course: ");
            String course = scanner.nextLine().trim(); // trim spaces
            while (course.isEmpty() || course.matches(".*\\s+.*")) {
                System.out.println("Invalid input. Please enter a course without extra spaces.");
                System.out.print("Enter student course: ");
                course = scanner.nextLine().trim();
            }

            int age; // ensure numerical variable is inputted
            while (true) {
                System.out.println("Enter student's age:");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age >= 18) { // ages must be equal or above age limit
                        break;
                    } else {
                        System.out.println("You have entered an incorrect student age!!! Please re-enter age:"); // ensures users corrects their input variables
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid age.");
                    scanner.next(); // Clear invalid input
                }
            }
            scanner.nextLine(); // Consume newline left-over

            if (name.trim().isEmpty() || surname.trim().isEmpty() || email.trim().isEmpty() || course.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid input: name, surname, email, and course are required");
            }

            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid input: email is not valid");
            }

            if (age < 18) {
                throw new IllegalArgumentException("Invalid input: age must be at least 18");
            }

            int studentNumber = (int) (Math.random() * 90000) + 10000; // generate a random 5-digit number

            Student student = new Student(name, surname, age, email, course, studentNumber);
            database.addStudent(student);
            System.out.println("Welcome " + name + " " + surname + studentNumber);
            System.out.println("Please remember to write down or remember your student number (" + studentNumber + ")");
            System.out.println("press (1) to continue");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next(); // clear invalid input
                System.out.println("press (1) to continue");
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (choice == 1) {
                SMapp.menu(); // Call the menu method from SMapp.java
            } else {
                System.out.println("Exiting application. Goodbye!");
                scanner.close();
                System.exit(1); // exit the application
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            scanner.close();
            System.exit(1); // exit the application
        }
    }

    // Method to validate email format
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    // Method to capitalize the first letter of a string
    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    static void saveStudent(Student student, StudentDatabase database) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}