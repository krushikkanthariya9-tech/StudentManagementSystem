
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== STUDENT MANAGEMENT SYSTEM ===");

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = input.nextInt();
        input.nextLine();

        System.out.print("Enter course: ");
        String course = input.nextLine();

        Student student = new Student(name, rollNumber, course);

        System.out.println("\n=== STUDENT DETAILS ===");
        student.displayDetails();

        input.close();
    }
}

