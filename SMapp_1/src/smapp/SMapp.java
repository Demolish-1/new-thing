// Dylan Gorrah ST10398445
package smapp;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class SMapp {
    private static final Scanner scanner = new Scanner(System.in);
    public static StudentDatabase database = new StudentDatabase();
// runs first
    public static void main(String[] args) {
        welcomeMessage();
        LaunchWith1();
    }
// prints welcome
    public static void welcomeMessage() {
    System.out.println("----------------------------------------------");
    System.out.println("STUDENT MANAGEMENT APPLICATION");
    System.out.println("----------------------------------------------");
    System.out.println("Enter (1) to launch menu or press any button to exit.");
}
public static void LaunchWith1() {
    try {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (choice == 1) {
            menu();
        } else {
            System.out.println("Goodbye!");
            scanner.close();
            System.exit(0);
        }
    } catch (InputMismatchException e) {
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }

} // prints menu
public static void menu() {
    System.out.println();
    System.out.println("Please select one of the following menu items:");
    System.out.println("----------------------------------------------");
    System.out.println("(1)  Capture new Student.");
    System.out.println("(2)  Search for a student.  ");
    System.out.println("(3)  Delete a student. ");
    System.out.println("(4)  Print student Report. ");
    System.out.println("(5)  Exit Application. ");
    System.out.println("----------------------------------------------");

    int selection;
    while (true) {
        try {
            selection = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (selection < 1 || selection > 5) {
                System.out.println("Invalid selection. Please try again.");
            } else {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
    }

    switch (selection) {
      case 1 -> CaptureStudent.captureStudent(scanner, database); // Pass the scanner and database objects
        case 2 -> searchStudent(); // serches student by student number
        case 3 -> deleteStudent(); // delete student
        case 4 -> Report(); // Print student Report
        case 5 -> {
            Timer timer = new Timer();
            System.out.println("Exiting application in 4 seconds. Goodbye!");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    scanner.close();
                    System.exit(0); // when ever you pres [ 5 ] the program will always close.
                }
            }, 4000); // 4000 milliseconds = 4 seconds
            }
    }

    }

   
 public static void searchStudent() {
    try {
        System.out.print("Enter student number: ");
        int studentNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Student student = database.searchStudent(studentNumber);

        if (student != null) {
            System.out.println("Student found:");
            System.out.println("Name: " + student.getName());
            System.out.println("Surname: " + student.getSurname());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
        } else {
            System.out.println("Student not found.");
        }

        while (true) {
            System.out.println("Press (1) to continue or any other key to exit.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (choice == 1) {
                menu();
                break;
            } else {
                System.out.println("Invalid input. Please enter 1 to continue.");
            }
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.next(); // Consume invalid input
        searchStudent(); // Call the method again
    }

}


public static void Report() {
    database.printReport();
    System.out.println("press (1) to continue, anything els to return to menu");

    try {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        while (true) {
            if (choice == 1) {
                menu();
            } else {
                System.out.println("Invalid selection. Please try again.");
                Report(); // launch menu
                break;
            }
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.next(); // Consume invalid input
        menu(); // Call the method again
    }
}


public static void deleteStudent() {
    System.out.print("Enter student number: ");
    int studentNumber = scanner.nextInt();
    scanner.nextLine(); // Consume newline left-over

    if (database.getStudents().containsKey(studentNumber)) {
        database.removeStudent(studentNumber);
        System.out.println("Student deleted successfully.");
    } else {
        System.out.println("Student not found.");
    }

    System.out.println("press (1) to continue");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline left-over

    while (true) {
        if (choice == 1) {
            menu();
        } else {
            System.out.println("Exiting application. Goodbye!");
            scanner.close();
            break;
        }
    }
    }

    static void searchStudent(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void searchStudent(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



  
        }
    












