import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;

    // public ShoppingCart() {
    // this.items = new ArrayList<>();
    // this.paymentProcessor = new PaymentProcessor();
    // this.notificationService = new NotificationService();
    // }

    public ShoppingCart(PaymentProcessor paymentProcessor,
            NotificationService notificationService) {
        this.items = new ArrayList<>();
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }



    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }



    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }


    public NotificationService getNotificationService() {
        return notificationService;
    }



    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    public void addItem(CartItem item) {
        items.add(item);
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(CartItem::getPrice).sum();
    }

    // public void checkout(String paymentMethod, String notificationChannel) {
    // double totalAmount = getTotalAmount();
    // paymentProcessor.processPayment(paymentMethod, totalAmount);
    // notificationService.sendNotification(notificationChannel, "Order placed for amount: " +
    // totalAmount);
    // }

    public void checkout() {
        double totalAmount = getTotalAmount();
        paymentProcessor.processPayment(totalAmount);
        notificationService.sendNotification("Order placed for amount: " + totalAmount);
    }

    public List<CartItem> getItems() {
        return items;
    }
}

