public class OrderList {
    OrderNode head;
    OrderNode tail;

    public void addOrder(Order order) {
        OrderNode newNode = new OrderNode(order);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void sortOrders() {
        if (head == null) {
            return;
        }

        boolean swapped;

        do {
            swapped = false;
            OrderNode current = head;

            while (current.next != null) {

                if (current.data.orderName.compareToIgnoreCase(
                        current.next.data.orderName) > 0) {

                    Order temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;

                    swapped = true;
                }

                current = current.next;
            }

        } while (swapped);
    }

    public void printOrders() {
        if (head == null) {
            System.out.println("No orders available.");
            return;
        }

        sortOrders();

        OrderNode current = head;

        System.out.println("\n=== ORDER REPORT ===");

        while (current != null) {
            System.out.println(
                "Code : " + current.data.orderCode +
                " | Order : " + current.data.orderName +
                " | Price : " + current.data.price +
                " | Buyer : " + current.data.buyerName
            );

            current = current.next;
        }
    }

    public int calculateTotalRevenue() {
        int total = 0;

        OrderNode current = head;

        while (current != null) {
            total += current.data.price;
            current = current.next;
        }

        return total;
    }
}