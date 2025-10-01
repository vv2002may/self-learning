

// public class PaymentProcessor {
// public void processPayment(String paymentMethod, double amount) {
// switch (paymentMethod) {
// case "CreditCard":
// System.out.println("Processing payment of " + amount + " through credit card.");
// break;
// case "PayPal":
// System.out.println("Processing payment of " + amount + " through PayPal.");
// break;
// // Adding new payment methods would require modifying this class
// default:
// throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
// }
// }
// }

public interface PaymentProcessor {
    void processPayment(double amount);
}
