package Phase_00;

/*
How many method calls deep is the crash? 4 methods deep: [
    at Phase_00.StackTraceNavigator$Invoice.generateSummary(StackTraceNavigator.java:24)
	at Phase_00.StackTraceNavigator$InvoiceService.processOrder(StackTraceNavigator.java:32)
	at Phase_00.StackTraceNavigator$OrderController.handleRequest(StackTraceNavigator.java:39)
	at Phase_00.StackTraceNavigator.main(StackTraceNavigator.java:46)
],
What exception was thrown and what does the message say?
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0

 */
public class StackTraceNavigator {

    static class Order {
        String customerName;
        String[] items;

        Order(String customerName, String[] items) {
            this.customerName = customerName;
            this.items = items;
        }
    }

    static class Invoice {
        Order order;

        Invoice(Order order) {
            this.order = order;
        }

        String generateSummary() {
            if (order.items == null || order.items.length == 0) {
                throw new IllegalArgumentException(
                        "Cannot generate invoice: order for '" + order.customerName + "' has no items"
                );
            }
            return "Invoice for: " + order.customerName +
                    "\nFirst item: " + order.items[0];  // potential issues here
        }
    }

    static class InvoiceService {
        static String processOrder(Order order) {
            Invoice invoice = new Invoice(order);
            return invoice.generateSummary();
        }
    }

    static class OrderController {
        static void handleRequest(String customerName, String[] items) {
            Order order = new Order(customerName, items);
            String result = InvoiceService.processOrder(order);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        // This will crash. Your job is to read where and why before touching any code.
        OrderController.handleRequest("Alice", new String[] {"widget"});
    }
}
