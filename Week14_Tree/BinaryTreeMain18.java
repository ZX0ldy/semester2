public class BinaryTreeMain18 {
    public static void main(String[] args) {
        BinaryTree18 bst = new BinaryTree18();
        
        bst.add(new Student18("244107020138", "Devin", "TI-1I", 3.57));
        bst.add(new Student18("244107021823", "Dewi", "TI-1I", 3.85));
        bst.add(new Student18("244107020225", "Wahyu", "TI-1I", 3.21));
        bst.add(new Student18("244107021876", "Angelina", "TI-1I", 3.54));

        System.out.println("Student list (in-order traversal)");
        bst.traverseInOrder(bst.root);

        System.out.println("\nSearch data");
        System.out.print("Search a student with IPK: 3.54: ");
        String result = bst.find(3.54) ? "Found" : "Not Found";
        System.out.println(result);

        System.out.print("Search a student with IPK: 3.22: ");
        result = bst.find(3.22) ? "Found" : "Not Found";
        System.out.println(result);

        bst.add(new Student18("244107020223", "Andhika", "TI-1I", 3.72));
        bst.add(new Student18("244107020226", "Bima", "TI-1I", 3.37));
        bst.add(new Student18("244107020181", "Eiyu", "TI-1I", 3.46));

        System.out.println("\nStudent list:");
        System.out.println("In-order traversal:");
        bst.traverseInOrder(bst.root);

        System.out.println("\nPre-order traversal:");
        bst.traversePreOrder(bst.root);

        System.out.println("\nPost-order traversal:");
        bst.traversePostOrder(bst.root);

        System.out.println("\nData deletion");
        bst.delete(3.57);

        System.out.println("\nStudent list after deletion:");
        bst.traverseInOrder(bst.root);
    }
}