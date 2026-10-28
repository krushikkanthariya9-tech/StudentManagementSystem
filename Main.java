public class Main {
    public static void main(String[] args) {
        Student student = new Student(
            "Student One",
            101,
            "Diploma IT"
        );

        System.out.println("=== STUDENT DETAILS ===");
        student.displayDetails();
    }
}
