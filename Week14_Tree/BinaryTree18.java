public class BinaryTree18 {
    Node18 root;

    // Constructor pohon baru kosong
    public BinaryTree18 () {
        root = null;
    }

    // Cek apakah pohon kosong
    public boolean isEmpty () {
        return root == null;
    }

    // Method tambah data (Non-Rekursif)
    public void add (Student18 data) {
        if (isEmpty()) {
            root = new Node18(data);
        } else {
            Node18 current = root;
            while (true) {
                if (data.ipk < current.data.ipk) {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current.left = new Node18(data);
                        break;
                    }
                } else if (data.ipk > current.data.ipk) {
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current.right = new Node18(data);
                        break;
                    }
                } else {
                    // Jika IPK sama, data tidak dimasukkan (asumsi unik untuk BST ini)
                    break;
                }
            }
        }
    }

    // Method cari data berdasarkan IPK
    public boolean find (double ipk) {
        boolean result = false;
        Node18 current = root;
        while (current != null) {
            if (current.data.ipk == ipk) {
                result = true;
                break;
            } else if (ipk < current.data.ipk) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return result;
    }

    // Traversal Pre-Order (Root -> Left -> Right)
    public void traversePreOrder (Node18 node) {
        if (node != null) {
            node.data.print();
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    // Traversal In-Order (Left -> Root -> Right)
    public void traverseInOrder (Node18 node) {
        if (node != null) {
            traverseInOrder(node.left);
            node.data.print();
            traverseInOrder(node.right);
        }
    }

    // Traversal Post-Order (Left -> Right -> Root)
    public void traversePostOrder (Node18 node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            node.data.print();
        }
    }

    // Mencari penerus/suksesor untuk kasus penghapusan dengan 2 anak
    Node18 getSuccessor (Node18 del) {
        Node18 successor = del.right;
        Node18 successorParent = del;
        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }
        if (successor != del.right) {
            successorParent.left = successor.right;
            successor.right = del.right;
        }
        return successor;
    }

    // Method hapus data berdasarkan IPK
    public void delete (double ipk) {
        if (isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }
        Node18 parent = root;
        Node18 current = root;
        boolean isLeftChild = false;

        // Proses pencarian node yang ingin dihapus
        while (current.data.ipk != ipk) {
            parent = current;
            if (ipk < current.data.ipk) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                System.out.println("Couldn't find data!");
                return;
            }
        }

        // KASUS 1: Node yang dihapus tidak memiliki anak (daun)
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } 
        // KASUS 2a: Node yang dihapus hanya punya anak kiri
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } 
        // KASUS 2b: Node yang dihapus hanya punya anak kanan
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } 
        // KASUS 3: Node yang dihapus memiliki 2 anak
        else {
            Node18 successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }
    
    // --- Bagian penambahan kode untuk Assignment/Tugas diletakkan di bawah setelah Eksperimen 2 ---
}