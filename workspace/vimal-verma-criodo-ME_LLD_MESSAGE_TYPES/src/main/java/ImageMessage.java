import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageMessage implements Message {
    String name;
    String message;
    String format;

    public ImageMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    private boolean isCorrectFormat() {
        if (message == null || !message.contains(".")) {
            return false;
        }
        String[] parts = message.split("\\.");
        format = parts[parts.length - 1]; 
        return format.equalsIgnoreCase("jpg");
    }

    @Override
    public String sendMessage() {
        if (isCorrectFormat()) {
            return "Image message sent: '" + message + "'";
        }
        return "Cannot send message with content type '"+format+"'.";
    }

    @Override
    public String displayMessage() {
        if (isCorrectFormat()) {
            return name + " sent an image: " + message;
        }
        return "Invalid content type for image message.";
    }
}