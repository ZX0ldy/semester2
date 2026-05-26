public class Order {
    int orderCode;
    String orderName;
    int price;
    String buyerName;

    public Order(int orderCode, String orderName, int price, String buyerName) {
        this.orderCode = orderCode;
        this.orderName = orderName;
        this.price = price;
        this.buyerName = buyerName;
    }
}