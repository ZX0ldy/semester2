public class Buyer {
    String name;
    String phoneNumber;
    int queueNumber;

    public Buyer(int queueNumber, String name, String phoneNumber) {
        this.queueNumber = queueNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}