import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = loadStudents();

        System.out.println("Loaded " + students.size() + " student(s).");

        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter 1, 2, or 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = input.nextLine().trim();

                    if (name.isEmpty() || name.contains("|")) {
                        System.out.println("Invalid name.");
                        break;
                    }

                    System.out.print("Enter roll number: ");
                    int rollNumber;

                    try {
                        rollNumber = Integer.parseInt(input.nextLine());
                        if (rollNumber <= 0) {
                            System.out.println("Roll number must be positive.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid roll number.");
                        break;
                    }

                    boolean duplicate = false;
                    for (Student s : students) {
                        if (s.rollNumber == rollNumber) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        System.out.println("That roll number already exists.");
                        break;
                    }

                    System.out.print("Enter course: ");
                    String course = input.nextLine().trim();

                    if (course.isEmpty() || course.contains("|")) {
                        System.out.println("Invalid course.");
                        break;
                    }

                    students.add(new Student(name, rollNumber, course));
                    saveStudents(students);
                    System.out.println("Student saved successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("\n=== ALL STUDENTS ===");
                        for (Student s : students) {
                            s.displayDetails();
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 3:
                    input.close();
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(
                    s.name + "|" + s.rollNumber + "|" + s.course
                );
            }
        } catch (IOException e) {
            System.out.println("Could not save student records.");
        }
    }

    static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length != 3) {
                    continue;
                }

                try {
                    String name = data[0];
                    int rollNumber = Integer.parseInt(data[1]);
                    String course = data[2];

                    students.add(new Student(name, rollNumber, course));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping an invalid record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load student records.");
        }

        return students;
    }
}

