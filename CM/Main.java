import java.util.Scanner;

public class Main {

    // ─── Data ────────────────────────────────────────────────────────────────────

    static Student[] students = {
        new Student("22001", "Abdul",   "Informatics Engineering"),
        new Student("22002", "Bestari", "Informatics Engineering"),
        new Student("22003", "Gandi",   "Business Information System")
    };

    static Book[] books = {
        new Book("B001", "Algorithm",   2020),
        new Book("B002", "Database",    2019),
        new Book("B003", "Programming", 2021),
        new Book("B004", "Physics",     2024)
    };

    static Loan[] loans;

    // ─── Helpers ─────────────────────────────────────────────────────────────────

    static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    static Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    // ─── Sorting : Insertion Sort (by fine, descending) ──────────────────────────

    static Loan[] insertionSortByFine(Loan[] arr) {
        Loan[] sorted = arr.clone();
        int n = sorted.length;
        for (int i = 1; i < n; i++) {
            Loan key = sorted[i];
            int j = i - 1;
            // Move elements that are SMALLER than key one position ahead
            while (j >= 0 && sorted[j].getFine() < key.getFine()) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    // ─── Searching : Binary Search by Student ID ─────────────────────────────────
    //   Requires the array to be sorted by ID first (we do that before searching).

    static Loan[] sortForBinarySearch(Loan[] arr) {
        // Insertion sort on a copy, ascending by student ID (string comparison)
        Loan[] sorted = arr.clone();
        int n = sorted.length;
        for (int i = 1; i < n; i++) {
            Loan key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].getStudent().getId().compareTo(key.getStudent().getId()) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    /**
     * Binary Search – returns ALL loans matching the given studentId.
     * Strategy: find ONE matching index, then expand left/right.
     */
    static Loan[] binarySearchByStudentId(Loan[] arr, String targetId) {
        Loan[] sorted = sortForBinarySearch(arr);
        int lo = 0, hi = sorted.length - 1, foundIndex = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = sorted[mid].getStudent().getId().compareTo(targetId);
            if (cmp == 0) {
                foundIndex = mid;
                break;
            } else if (cmp < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (foundIndex == -1) return new Loan[0];   // not found

        // Collect all matches around foundIndex
        int start = foundIndex, end = foundIndex;
        while (start > 0 && sorted[start - 1].getStudent().getId().equals(targetId)) start--;
        while (end < sorted.length - 1 && sorted[end + 1].getStudent().getId().equals(targetId)) end++;

        Loan[] result = new Loan[end - start + 1];
        for (int i = 0; i < result.length; i++) result[i] = sorted[start + i];
        return result;
    }

    // ─── Display Helpers ─────────────────────────────────────────────────────────

    static void printBookTable(Book[] arr) {
        System.out.println();
        System.out.println("+--------+----------------------+------+");
        System.out.println("| Code   | Title                | Year |");
        System.out.println("+--------+----------------------+------+");
        for (Book b : arr) b.showBook();
        System.out.println("+--------+----------------------+------+");
    }

    static void printStudentTable(Student[] arr) {
        System.out.println();
        System.out.println("+--------+------------+--------------------------------+");
        System.out.println("| ID     | Name       | Study Program                  |");
        System.out.println("+--------+------------+--------------------------------+");
        for (Student s : arr) s.showStudent();
        System.out.println("+--------+------------+--------------------------------+");
    }

    static void printLoanTable(Loan[] arr) {
        System.out.println();
        System.out.println("+--------+------------+----------------------+----------------+------------+--------------+");
        System.out.println("| ID     | Name       | Book Title           | Duration (days)| Late (days)|    Fine      |");
        System.out.println("+--------+------------+----------------------+----------------+------------+--------------+");
        for (Loan l : arr) l.showLoan();
        System.out.println("+--------+------------+----------------------+----------------+------------+--------------+");
    }

    // ─── Menu Handlers ───────────────────────────────────────────────────────────

    static void menuDisplayData() {
        System.out.println("\n========== DISPLAY DATA ==========");
        System.out.println("1. Book Data");
        System.out.println("2. Student Data");
        System.out.println("3. Both");
        System.out.print("Choose: ");
    }

    // ─── Main ────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        // Initialise loan data
        loans = new Loan[] {
            new Loan(findStudentById("22001"), findBookByTitle("Algorithm"),   7),
            new Loan(findStudentById("22002"), findBookByTitle("Database"),    3),
            new Loan(findStudentById("22003"), findBookByTitle("Programming"), 10),
            new Loan(findStudentById("22003"), findBookByTitle("Physics"),     6),
            new Loan(findStudentById("22001"), findBookByTitle("Database"),    4)
        };

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║   JTI READING ROOM – BOOK LOAN SYSTEM        ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Display Book & Student Data               ║");
            System.out.println("║  2. Display Book Loan Data                    ║");
            System.out.println("║  3. Calculate Overdue Days & Fine             ║");
            System.out.println("║  4. Sort Loan Data by Largest Fine            ║");
            System.out.println("║  5. Search Loan Data by Student ID            ║");
            System.out.println("║  0. Exit                                      ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Select menu: ");

            String input = sc.nextLine().trim();

            switch (input) {
                // ── 1. Display book & student data ───────────────────────────────
                case "1": {
                    menuDisplayData();
                    String sub = sc.nextLine().trim();
                    switch (sub) {
                        case "1":
                            System.out.println("\n--- BOOK DATA ---");
                            printBookTable(books);
                            break;
                        case "2":
                            System.out.println("\n--- STUDENT DATA ---");
                            printStudentTable(students);
                            break;
                        case "3":
                            System.out.println("\n--- BOOK DATA ---");
                            printBookTable(books);
                            System.out.println("\n--- STUDENT DATA ---");
                            printStudentTable(students);
                            break;
                        default:
                            System.out.println("Invalid sub-menu.");
                    }
                    break;
                }

                // ── 2. Display loan data ──────────────────────────────────────────
                case "2": {
                    System.out.println("\n--- BOOK LOAN DATA ---");
                    printLoanTable(loans);
                    break;
                }

                // ── 3. Calculate overdue days & fine ─────────────────────────────
                case "3": {
                    System.out.println("\n--- OVERDUE DAYS & FINE CALCULATION ---");
                    System.out.printf("%-6s | %-10s | %-20s | %-8s | %-10s | %-12s%n",
                            "ID", "Name", "Book", "Duration", "Late Days", "Fine");
                    System.out.println("-".repeat(80));
                    for (Loan l : loans) {
                        System.out.printf("%-6s | %-10s | %-20s | %-8d | %-10d | Rp %,d%n",
                                l.getStudent().getId(),
                                l.getStudent().getName(),
                                l.getBook().getTitle(),
                                l.getLoanDuration(),
                                l.getLate(),
                                l.getFine());
                    }
                    System.out.println("-".repeat(80));
                    System.out.println("Loan limit  : " + loans[0].getLoanLimit() + " days");
                    System.out.println("Fine rate   : Rp 2,000 / day overdue");
                    break;
                }

                // ── 4. Sort by largest fine (Insertion Sort) ─────────────────────
                case "4": {
                    System.out.println("\n--- LOAN DATA SORTED BY LARGEST FINE (Insertion Sort) ---");
                    Loan[] sorted = insertionSortByFine(loans);
                    printLoanTable(sorted);
                    break;
                }

                // ── 5. Search by student ID (Binary Search) ───────────────────────
                case "5": {
                    System.out.print("\nEnter Student ID to search: ");
                    String searchId = sc.nextLine().trim();
                    Loan[] result = binarySearchByStudentId(loans, searchId);
                    if (result.length == 0) {
                        System.out.println(">> No loan data found for Student ID: " + searchId);
                    } else {
                        System.out.println("\n--- SEARCH RESULT (Binary Search) ---");
                        System.out.println("Student ID  : " + searchId);
                        System.out.println("Records found: " + result.length);
                        printLoanTable(result);
                    }
                    break;
                }

                // ── 0. Exit ───────────────────────────────────────────────────────
                case "0": {
                    System.out.println("\nThank you for using JTI Reading Room System. Goodbye!");
                    running = false;
                    break;
                }

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
