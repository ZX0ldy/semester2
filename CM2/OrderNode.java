public class OrderNode {
    Order data;
    OrderNode prev;
    OrderNode next;

    public OrderNode(Order data) {
        this.data = data;
    }
}