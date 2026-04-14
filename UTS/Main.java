import java.util.Scanner;

public class Main {

    // ─── Dynamic arrays (grown via helper) ───────────────────────────────────────
    static Student[] students = new Student[0];
    static Book[]    books    = new Book[0];
    static Loan[]    loans    = new Loan[0];

    static Scanner sc = new Scanner(System.in);

    // ═══════════════════════════════════════════════════════════════════════════════
    //  ARRAY HELPERS
    // ═══════════════════════════════════════════════════════════════════════════════

    static Student[] appendStudent(Student[] arr, Student s) {
        Student[] n = new Student[arr.length + 1];
        for (int i = 0; i < arr.length; i++) n[i] = arr[i];
        n[arr.length] = s;
        return n;
    }

    static Book[] appendBook(Book[] arr, Book b) {
        Book[] n = new Book[arr.length + 1];
        for (int i = 0; i < arr.length; i++) n[i] = arr[i];
        n[arr.length] = b;
        return n;
    }

    static Loan[] appendLoan(Loan[] arr, Loan l) {
        Loan[] n = new Loan[arr.length + 1];
        for (int i = 0; i < arr.length; i++) n[i] = arr[i];
        n[arr.length] = l;
        return n;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  LOOKUP HELPERS
    // ═══════════════════════════════════════════════════════════════════════════════

    static Student findStudentById(String id) {
        for (Student s : students) if (s.getId().equals(id)) return s;
        return null;
    }

    static Book findBookByCode(String code) {
        for (Book b : books) if (b.getCode().equalsIgnoreCase(code)) return b;
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  DATA ENTRY
    // ═══════════════════════════════════════════════════════════════════════════════

    static void inputStudents() {
        System.out.println("\n========== ADD STUDENT DATA ==========");
        System.out.print("How many students to add? ");
        int count = readInt();
        for (int i = 0; i < count; i++) {
            System.out.println("\n  -- Student " + (i + 1) + " --");
            System.out.print("  ID           : ");
            String id = sc.nextLine().trim();
            if (findStudentById(id) != null) { System.out.println("  >> ID already exists, skipping."); continue; }
            System.out.print("  Name         : ");
            String name = sc.nextLine().trim();
            System.out.print("  Study Program: ");
            String prog = sc.nextLine().trim();
            students = appendStudent(students, new Student(id, name, prog));
            System.out.println("  >> Student added.");
        }
    }

    static void inputBooks() {
        System.out.println("\n========== ADD BOOK DATA ==========");
        System.out.print("How many books to add? ");
        int count = readInt();
        for (int i = 0; i < count; i++) {
            System.out.println("\n  -- Book " + (i + 1) + " --");
            System.out.print("  Code : ");
            String code = sc.nextLine().trim();
            if (findBookByCode(code) != null) { System.out.println("  >> Code already exists, skipping."); continue; }
            System.out.print("  Title: ");
            String title = sc.nextLine().trim();
            System.out.print("  Year : ");
            int year = readInt();
            books = appendBook(books, new Book(code, title, year));
            System.out.println("  >> Book added.");
        }
    }

    static void inputLoans() {
        System.out.println("\n========== ADD LOAN DATA ==========");
        if (students.length == 0 || books.length == 0) {
            System.out.println(">> Please add Student and Book data first!");
            return;
        }
        System.out.println("Available Students:");
        printStudentTable(students);
        System.out.println("Available Books:");
        printBookTable(books);
        System.out.print("\nHow many loan records to add? ");
        int count = readInt();
        for (int i = 0; i < count; i++) {
            System.out.println("\n  -- Loan " + (i + 1) + " --");
            System.out.print("  Student ID            : ");
            String sid = sc.nextLine().trim();
            Student student = findStudentById(sid);
            if (student == null) { System.out.println("  >> Student not found, skipping."); continue; }
            System.out.print("  Book Code             : ");
            String bcode = sc.nextLine().trim();
            Book book = findBookByCode(bcode);
            if (book == null) { System.out.println("  >> Book not found, skipping."); continue; }
            System.out.print("  Loan Duration (days)  : ");
            int dur = readInt();
            if (dur <= 0) { System.out.println("  >> Invalid duration, skipping."); continue; }
            loans = appendLoan(loans, new Loan(student, book, dur));
            System.out.println("  >> Loan record added.");
        }
    }

    static void manageData() {
        boolean back = false;
        while (!back) {
            System.out.println("\n+-----------------------------------------+");
            System.out.println("|           MANAGE DATA                   |");
            System.out.println("+-----------------------------------------+");
            System.out.println("|  1. Add Student(s)                      |");
            System.out.println("|  2. Add Book(s)                         |");
            System.out.println("|  3. Add Loan Record(s)                  |");
            System.out.println("|  4. View Student Data                   |");
            System.out.println("|  5. View Book Data                      |");
            System.out.println("|  6. View Loan Data                      |");
            System.out.println("|  0. Back to Main Menu                   |");
            System.out.println("+-----------------------------------------+");
            System.out.print("Choose: ");
            switch (sc.nextLine().trim()) {
                case "1": inputStudents(); break;
                case "2": inputBooks();    break;
                case "3": inputLoans();    break;
                case "4":
                    if (students.length == 0) System.out.println(">> No student data yet.");
                    else printStudentTable(students); break;
                case "5":
                    if (books.length == 0) System.out.println(">> No book data yet.");
                    else printBookTable(books); break;
                case "6":
                    if (loans.length == 0) System.out.println(">> No loan data yet.");
                    else printLoanTable(loans); break;
                case "0": back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  SORTING ALGORITHMS  (all descending by fine)
    // ═══════════════════════════════════════════════════════════════════════════════

    /** 1. Insertion Sort */
    static Loan[] insertionSort(Loan[] arr) {
        Loan[] a = arr.clone();
        for (int i = 1; i < a.length; i++) {
            Loan key = a[i]; int j = i - 1;
            while (j >= 0 && a[j].getFine() < key.getFine()) { a[j+1] = a[j]; j--; }
            a[j+1] = key;
        }
        return a;
    }

    /** 2. Selection Sort */
    static Loan[] selectionSort(Loan[] arr) {
        Loan[] a = arr.clone();
        for (int i = 0; i < a.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j].getFine() > a[maxIdx].getFine()) maxIdx = j;
            Loan tmp = a[i]; a[i] = a[maxIdx]; a[maxIdx] = tmp;
        }
        return a;
    }

    /** 3. Bubble Sort */
    static Loan[] bubbleSort(Loan[] arr) {
        Loan[] a = arr.clone();
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j].getFine() < a[j+1].getFine()) {
                    Loan tmp = a[j]; a[j] = a[j+1]; a[j+1] = tmp;
                }
        return a;
    }

    /** 4. Merge Sort */
    static Loan[] mergeSort(Loan[] arr) {
        if (arr.length <= 1) return arr.clone();
        int mid = arr.length / 2;
        Loan[] L = new Loan[mid];
        Loan[] R = new Loan[arr.length - mid];
        for (int i = 0; i < mid; i++)            L[i]       = arr[i];
        for (int i = mid; i < arr.length; i++)   R[i - mid] = arr[i];
        L = mergeSort(L); R = mergeSort(R);
        return mergeParts(L, R);
    }

    static Loan[] mergeParts(Loan[] L, Loan[] R) {
        Loan[] res = new Loan[L.length + R.length];
        int i = 0, j = 0, k = 0;
        while (i < L.length && j < R.length)
            res[k++] = (L[i].getFine() >= R[j].getFine()) ? L[i++] : R[j++];
        while (i < L.length) res[k++] = L[i++];
        while (j < R.length) res[k++] = R[j++];
        return res;
    }

    /** 5. Quick Sort */
    static Loan[] quickSort(Loan[] arr) {
        Loan[] a = arr.clone();
        qsort(a, 0, a.length - 1);
        return a;
    }

    static void qsort(Loan[] a, int lo, int hi) {
        if (lo < hi) { int p = partition(a, lo, hi); qsort(a, lo, p-1); qsort(a, p+1, hi); }
    }

    static int partition(Loan[] a, int lo, int hi) {
        int pivot = a[hi].getFine(); int i = lo - 1;
        for (int j = lo; j < hi; j++)
            if (a[j].getFine() >= pivot) { i++; Loan t=a[i]; a[i]=a[j]; a[j]=t; }
        Loan t = a[i+1]; a[i+1] = a[hi]; a[hi] = t;
        return i + 1;
    }

    static void sortMenu() {
        if (loans.length == 0) { System.out.println(">> No loan data to sort."); return; }
        System.out.println("\n========== SORT LOAN DATA BY LARGEST FINE ==========");
        System.out.println("Select sorting algorithm:");
        System.out.println("  1. Insertion Sort  [O(n^2)     – best O(n)]");
        System.out.println("  2. Selection Sort  [O(n^2)]");
        System.out.println("  3. Bubble Sort     [O(n^2)     – best O(n)]");
        System.out.println("  4. Merge Sort      [O(n log n) – stable]");
        System.out.println("  5. Quick Sort      [O(n log n) – in-place]");
        System.out.print("Choose algorithm: ");
        String c = sc.nextLine().trim();

        Loan[] sorted; String name;
        switch (c) {
            case "1": sorted = insertionSort(loans); name = "Insertion Sort"; break;
            case "2": sorted = selectionSort(loans); name = "Selection Sort"; break;
            case "3": sorted = bubbleSort(loans);    name = "Bubble Sort";    break;
            case "4": sorted = mergeSort(loans);     name = "Merge Sort";     break;
            case "5": sorted = quickSort(loans);     name = "Quick Sort";     break;
            default:  System.out.println("Invalid choice."); return;
        }
        System.out.println("\n--- SORTED BY LARGEST FINE (" + name + ") ---");
        printLoanTable(sorted);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  SEARCHING ALGORITHMS
    // ═══════════════════════════════════════════════════════════════════════════════

    /** Sort-by-ID helper used by binary and jump search */
    static Loan[] sortById(Loan[] arr) {
        Loan[] a = arr.clone();
        for (int i = 1; i < a.length; i++) {
            Loan key = a[i]; int j = i - 1;
            while (j >= 0 && a[j].getStudent().getId().compareTo(key.getStudent().getId()) > 0) {
                a[j+1] = a[j]; j--;
            }
            a[j+1] = key;
        }
        return a;
    }

    /** Expand from a found index to collect all records with the same ID */
    static Loan[] collectMatches(Loan[] sorted, int foundIdx, String id) {
        int s = foundIdx, e = foundIdx;
        while (s > 0 && sorted[s-1].getStudent().getId().equals(id)) s--;
        while (e < sorted.length-1 && sorted[e+1].getStudent().getId().equals(id)) e++;
        Loan[] res = new Loan[e - s + 1];
        for (int i = 0; i < res.length; i++) res[i] = sorted[s + i];
        return res;
    }

    /** 1. Sequential (Linear) Search – O(n) */
    static Loan[] sequentialSearch(Loan[] arr, String targetId) {
        Loan[] tmp = new Loan[arr.length]; int cnt = 0;
        for (Loan l : arr) if (l.getStudent().getId().equals(targetId)) tmp[cnt++] = l;
        Loan[] res = new Loan[cnt];
        for (int i = 0; i < cnt; i++) res[i] = tmp[i];
        return res;
    }

    /** 2. Binary Search – O(log n) */
    static Loan[] binarySearch(Loan[] arr, String targetId) {
        Loan[] sorted = sortById(arr);
        int lo = 0, hi = sorted.length - 1, found = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = sorted[mid].getStudent().getId().compareTo(targetId);
            if      (cmp == 0) { found = mid; break; }
            else if (cmp  < 0) lo = mid + 1;
            else               hi = mid - 1;
        }
        if (found == -1) return new Loan[0];
        return collectMatches(sorted, found, targetId);
    }

    /** 3. Jump Search – O(sqrt n) */
    static Loan[] jumpSearch(Loan[] arr, String targetId) {
        Loan[] sorted = sortById(arr);
        int n = sorted.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (prev < n &&
               sorted[Math.min(step, n) - 1].getStudent().getId().compareTo(targetId) < 0) {
            prev  = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return new Loan[0];
        }
        while (prev < Math.min(step, n)) {
            if (sorted[prev].getStudent().getId().equals(targetId))
                return collectMatches(sorted, prev, targetId);
            prev++;
        }
        return new Loan[0];
    }

    static void searchMenu() {
        if (loans.length == 0) { System.out.println(">> No loan data to search."); return; }
        System.out.println("\n========== SEARCH LOAN DATA BY STUDENT ID ==========");
        System.out.println("Select searching algorithm:");
        System.out.println("  1. Sequential Search  [O(n)       – no pre-sort needed]");
        System.out.println("  2. Binary Search      [O(log n)   – uses sorted copy]");
        System.out.println("  3. Jump Search        [O(sqrt n)  – uses sorted copy]");
        System.out.print("Choose algorithm: ");
        String c = sc.nextLine().trim();

        System.out.print("Enter Student ID to search: ");
        String targetId = sc.nextLine().trim();

        Loan[] result; String name;
        switch (c) {
            case "1": result = sequentialSearch(loans, targetId); name = "Sequential Search"; break;
            case "2": result = binarySearch(loans, targetId);     name = "Binary Search";     break;
            case "3": result = jumpSearch(loans, targetId);       name = "Jump Search";       break;
            default:  System.out.println("Invalid choice."); return;
        }

        if (result.length == 0) {
            System.out.println(">> [" + name + "] No loan found for Student ID: " + targetId);
        } else {
            System.out.println("\n--- SEARCH RESULT (" + name + ") ---");
            System.out.println("Student ID    : " + targetId);
            System.out.println("Records found : " + result.length);
            printLoanTable(result);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  DISPLAY HELPERS
    // ═══════════════════════════════════════════════════════════════════════════════

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

    // ═══════════════════════════════════════════════════════════════════════════════
    //  UTILITY
    // ═══════════════════════════════════════════════════════════════════════════════

    static int readInt() {
        while (true) {
            String line = sc.nextLine().trim();
            try { return Integer.parseInt(line); }
            catch (NumberFormatException e) { System.out.print("  >> Please enter a valid integer: "); }
        }
    }

    static void loadSampleData() {
        students = appendStudent(students, new Student("22001", "Abdul",   "Informatics Engineering"));
        students = appendStudent(students, new Student("22002", "Bestari", "Informatics Engineering"));
        students = appendStudent(students, new Student("22003", "Gandi",   "Business Information System"));

        books = appendBook(books, new Book("B001", "Algorithm",   2020));
        books = appendBook(books, new Book("B002", "Database",    2019));
        books = appendBook(books, new Book("B003", "Programming", 2021));
        books = appendBook(books, new Book("B004", "Physics",     2024));

        loans = appendLoan(loans, new Loan(findStudentById("22001"), findBookByCode("B001"), 7));
        loans = appendLoan(loans, new Loan(findStudentById("22002"), findBookByCode("B002"), 3));
        loans = appendLoan(loans, new Loan(findStudentById("22003"), findBookByCode("B003"), 10));
        loans = appendLoan(loans, new Loan(findStudentById("22003"), findBookByCode("B004"), 6));
        loans = appendLoan(loans, new Loan(findStudentById("22001"), findBookByCode("B002"), 4));
        System.out.println(">> Sample data loaded.");
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    //  MAIN
    // ═══════════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   JTI READING ROOM  -  BOOK LOAN SYSTEM      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Load sample data? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) loadSampleData();

        boolean running = true;
        while (running) {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║   JTI READING ROOM  -  BOOK LOAN SYSTEM      ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Manage Data (Add / View)                 ║");
            System.out.println("║  2. Display Book Loan Data                   ║");
            System.out.println("║  3. Calculate Overdue Days & Fine            ║");
            System.out.println("║  4. Sort Loan Data by Largest Fine           ║");
            System.out.println("║  5. Search Loan Data by Student ID           ║");
            System.out.println("║  0. Exit                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Select menu: ");

            switch (sc.nextLine().trim()) {
                case "1": manageData(); break;

                case "2":
                    if (loans.length == 0) System.out.println(">> No loan data yet.");
                    else { System.out.println("\n--- BOOK LOAN DATA ---"); printLoanTable(loans); }
                    break;

                case "3":
                    if (loans.length == 0) { System.out.println(">> No loan data yet."); break; }
                    System.out.println("\n--- OVERDUE DAYS & FINE CALCULATION ---");
                    System.out.printf("%-6s | %-10s | %-20s | %-8s | %-10s | %-12s%n",
                            "ID","Name","Book","Duration","Late Days","Fine");
                    System.out.println("-".repeat(80));
                    for (Loan l : loans)
                        System.out.printf("%-6s | %-10s | %-20s | %-8d | %-10d | Rp %,d%n",
                                l.getStudent().getId(), l.getStudent().getName(),
                                l.getBook().getTitle(), l.getLoanDuration(),
                                l.getLate(), l.getFine());
                    System.out.println("-".repeat(80));
                    System.out.println("Loan limit : 5 days  |  Fine rate: Rp 2,000 / day overdue");
                    break;

                case "4": sortMenu();   break;
                case "5": searchMenu(); break;

                case "0":
                    System.out.println("\nThank you for using JTI Reading Room System. Goodbye!");
                    running = false;
                    break;

                default: System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
