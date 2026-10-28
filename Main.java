

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student name: ");
                    String name = input.nextLine();

                    System.out.print("Enter roll number: ");
                    int rollNumber;

                    try {
                        rollNumber = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid roll number.");
                        break;
                    }

                    System.out.print("Enter course: ");
                    String course = input.nextLine();

                    students.add(new Student(name, rollNumber, course));
                    System.out.println("Student added successfully!");
                    break;

                case "2":
                    if (students.isEmpty()) {
                        System.out.println("No students added yet.");
                    } else {
                        System.out.println("\n=== ALL STUDENTS ===");

                        for (Student student : students) {
                            student.displayDetails();
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case "3":
                    System.out.println("Thank you for using the system!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid option. Choose 1, 2, or 3.");
            }
        }
    }
}
