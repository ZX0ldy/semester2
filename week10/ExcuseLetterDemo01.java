import java.util.Scanner;

public class ExcuseLetterDemo01 {
    public static void main(String[] args) {
        ExcuseLetterStack01 stack = new ExcuseLetterStack01(10);
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Excuse Letter Management ===");
            System.out.println("1. Submit Excuse Letter");
            System.out.println("2. Process Excuse Letter");
            System.out.println("3. View Latest Excuse Letter");
            System.out.println("4. Search for Letter");
            System.out.println("0. Exit");
            System.out.print("Choose a menu: ");
            choice = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("ID        : ");
                    String id = scan.nextLine();
                    System.out.print("Name      : ");
                    String name = scan.nextLine();
                    System.out.print("Class     : ");
                    String className = scan.nextLine();
                    System.out.print("Type (S=Sick / I=Other): ");
                    char type = scan.nextLine().toUpperCase().charAt(0);
                    System.out.print("Duration (days): ");
                    int duration = scan.nextInt();
                    scan.nextLine();

                    ExcuseLetter01 letter = new ExcuseLetter01(id, name, className, type, duration);
                    stack.push(letter);
                    System.out.println(name + "'s excuse letter has been successfully submitted!!");
                    System.out.println();
                    break;

                case 2:
                    ExcuseLetter01 processed = stack.pop();
                    if (processed != null) {
                        System.out.println("Processing excuse letter from: " + processed.name);
                        processed.printInfo();
                        System.out.println("Letter has been processed/validated.");
                    }
                    System.out.println();
                    break;

                case 3:
                    ExcuseLetter01 latest = stack.peek();
                    if (latest != null) {
                        System.out.println("Latest (top) excuse letter:");
                        latest.printInfo();
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Enter student name to search: ");
                    String searchName = scan.nextLine();
                    stack.searchByName(searchName);
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
