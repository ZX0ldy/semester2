import java.util.Scanner;

public class StudentDemo01 {
    public static void main(String[] args) {
        StudentAssignmentStack01 stack = new StudentAssignmentStack01(5);
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Submit Assignment");
            System.out.println("2. Grade Assignment");
            System.out.println("3. View Top Assignment");
            System.out.println("4. View All Assignments");
            System.out.println("5. View First Submitter");      // Q5
            System.out.println("6. Count Assignments in Stack"); // Q6
            System.out.println("0. Exit");
            System.out.print("Choose a menu: ");
            choice = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scan.nextLine();
                    System.out.print("NIM: ");
                    String nim = scan.nextLine();
                    System.out.print("Class Name: ");
                    String className = scan.nextLine();
                    Student01 newStudent = new Student01(nim, name, className);
                    stack.push(newStudent);
                    System.out.println(name + "'s assignment has been successfully submitted!!");
                    System.out.println();
                    break;

                case 2:
                    Student01 graded = stack.pop();
                    if (graded != null) {
                        System.out.println("Grading assignment from " + graded.name);
                        System.out.print("Input grade (0-100): ");
                        int grade = scan.nextInt();
                        graded.grading(grade);
                        System.out.printf("Assignment grade of %s is %d%n", graded.name, grade);
                        // Experiment 2: convert to binary
                        String binary = stack.convertToBinary(grade);
                        System.out.printf("Assignment grade in binary is %s%n", binary);
                    }
                    System.out.println();
                    break;

                case 3:
                    Student01 top = stack.peek();
                    if (top != null) {
                        System.out.println("The last assignment comes from " + top.name);
                    }
                    System.out.println();
                    break;

                case 4:
                    if (!stack.isEmpty()) {
                        System.out.println("Assignment list:");
                        stack.print();
                    } else {
                        System.out.println("No assignments in stack.");
                        System.out.println();
                    }
                    break;

                case 5:
                    // Q5: Show first submitter (bottom of stack)
                    Student01 first = stack.getBottom();
                    if (first != null) {
                        System.out.println("The first student who submitted: " + first.name +
                                " (NIM: " + first.nim + ", Class: " + first.className + ")");
                    }
                    System.out.println();
                    break;

                case 6:
                    // Q6: Count assignments
                    int count = stack.countAssignments();
                    System.out.println("Total assignments currently in stack: " + count);
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }

        } while (choice != 0);

        scan.close();
    }
}
