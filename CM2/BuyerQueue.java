public class BuyerQueue {
    BuyerNode head;
    BuyerNode tail;
    int queueCounter = 1;

    public void addQueue(String name, String phone) {
        Buyer buyer = new Buyer(queueCounter++, name, phone);
        BuyerNode newNode = new BuyerNode(buyer);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println("Buyer added to queue!");
    }

    public void printQueue() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }

        BuyerNode current = head;

        System.out.println("\n=== BUYER QUEUE ===");
        while (current != null) {
            System.out.println(
                "Queue : " + current.data.queueNumber +
                " | Name : " + current.data.name +
                " | Phone : " + current.data.phoneNumber
            );

            current = current.next;
        }
    }

    public Buyer serveBuyer() {
        if (head == null) {
            return null;
        }

        Buyer served = head.data;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        return served;
    }
}