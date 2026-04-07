import java.util.Scanner;

public class StudentDemo23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TopStudents23 topstudents = new TopStudents23(5);

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Class: ");
            String studentClass = sc.nextLine();

            System.out.print("Enter GPA: ");
            double gpa = sc.nextDouble();
            sc.nextLine();

            topstudents.add(new Student23(id, name, studentClass, gpa));
        }

        System.out.println("\nOriginal student list:");
        topstudents.print();

        topstudents.bubbleSort();

        System.out.println("\nSorted student list (by GPA, descending):");
        topstudents.print();

        sc.close();
    }
}
