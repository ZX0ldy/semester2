import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BuyerQueue queue = new BuyerQueue();
        OrderList orders = new OrderList();

        int choice;

        do {
            System.out.println("\n=== ROYAL DELISH RESTAURANT ===");
            System.out.println("1. Add Queue");
            System.out.println("2. Print Queue");
            System.out.println("3. Serve Buyer & Input Order");
            System.out.println("4. Order Report");
            System.out.println("5. Total Revenue");
            System.out.println("0. Exit");
            System.out.print("Choose Menu : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Input Buyer Name : ");
                    String name = sc.nextLine();

                    System.out.print("Input Phone Number : ");
                    String phone = sc.nextLine();

                    queue.addQueue(name, phone);
                    break;

                case 2:
                    queue.printQueue();
                    break;

                case 3:

                    Buyer served = queue.serveBuyer();

                    if (served == null) {
                        System.out.println("Queue is empty.");
                    } else {

                        System.out.println("\nBuyer Served : " + served.name);

                        System.out.print("Input Order Code : ");
                        int code = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Input Order Name : ");
                        String orderName = sc.nextLine();

                        System.out.print("Input Price : ");
                        int price = sc.nextInt();

                        Order order = new Order(
                                code,
                                orderName,
                                price,
                                served.name
                        );

                        orders.addOrder(order);

                        System.out.println("Order saved!");
                    }

                    break;

                case 4:
                    orders.printOrders();
                    break;

                case 5:
                    System.out.println(
                        "Total Revenue : " +
                        orders.calculateTotalRevenue()
                    );
                    break;

                case 0:
                    System.out.println("Program Finished.");
                    break;

                default:
                    System.out.println("Invalid menu.");
            }

        } while (choice != 0);
    }
}