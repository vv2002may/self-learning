import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudioMessage implements Message{
    String name;
    String message;
    String format;

    public AudioMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    private boolean isCorrectFormat() {
        if (message == null || !message.contains(".")) {
            return false;
        }
        String[] parts = message.split("\\.");
        format = parts[parts.length - 1]; 
        return format.equalsIgnoreCase("wav"); 
    }

    @Override
    public String sendMessage() {
        if (isCorrectFormat()) {
            return "Audio message sent: '" + message + "'";
        }
        return "Cannot send message with content type '"+format+"'.";
    }

    @Override
    public String displayMessage() {
        if (isCorrectFormat()) {
            return name + " sent an audio: " + message;
        }
        return "Invalid content type for audio message.";
    }
}
