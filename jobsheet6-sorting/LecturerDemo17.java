public class LecturerDemo23 {
    public static void main(String[] args) {
        LecturerData23 data = new LecturerData23(5);

        data.add(new Lecturer23("L001", "Dr. Andi", true, 45));
        data.add(new Lecturer23("L002", "Prof. Budi", true, 50));
        data.add(new Lecturer23("L003", "Dr. Clara", false, 38));
        data.add(new Lecturer23("L004", "Dr. Deni", true, 42));
        data.add(new Lecturer23("L005", "Prof. Eka", false, 55));

        System.out.println("Original Lecturer Data:");
        data.print();

        data.sortingASC();
        System.out.println("\nSorted ASC (youngest → oldest):");
        data.print();

        data.sortingDSC();
        System.out.println("\nSorted DSC (oldest → youngest):");
        data.print();
    }
}
