// InsertionSortMain23.java
public class InsertionSortMain23 {
    public static void main(String[] args) {
        int[] c = {40, 10, 4, 9, 3};   // sample array from jobsheet
        InsertionSort23 sorting3 = new InsertionSort23(c);

        System.out.println("Original array:");
        sorting3.print();

        sorting3.insertionSort();

        System.out.println("Sorted array (Insertion Sort):");
        sorting3.print();
    }
}