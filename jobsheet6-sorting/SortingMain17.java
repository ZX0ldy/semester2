public class SortingMain23 {
    public static void main(String[] args) {
        int[] b = {30, 20, 2, 8, 14};   
        selectionSort23 sorting2 = new selectionSort23(b);

        System.out.println("Original Array:");
        sorting2.print();

        sorting2.selectionSort();   

        System.out.println("Sorted Array (Selection Sort):");
        sorting2.print();
    }
}
