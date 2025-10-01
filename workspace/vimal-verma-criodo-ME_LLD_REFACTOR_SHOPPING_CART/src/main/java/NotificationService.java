

// public class NotificationService {
//     public void sendNotification(String channel, String message) {
//         switch (channel) {
//             case "Email":
//                 System.out.println("Sending email notification: " + message);
//                 break;
//             case "SMS":
//                 System.out.println("Sending SMS notification: " + message);
//                 break;
//             // Adding new notification channels would require modifying this class
//             default:
//                 throw new IllegalArgumentException("Unknown notification channel: " + channel);
//         }
//     }
// }


public interface NotificationService{
    void sendNotification(String message);
}