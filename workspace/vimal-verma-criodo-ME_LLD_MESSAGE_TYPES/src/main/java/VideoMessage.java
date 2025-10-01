

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoMessage implements Message{
    String name;
    String message;
    String format;

    public VideoMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    private boolean isCorrectFormat() {
        if (message == null || !message.contains(".")) {
            return false;
        }
        String[] parts = message.split("\\.");
        format = parts[parts.length - 1]; 
        return format.equalsIgnoreCase("mp4"); 
    }

    @Override
    public String sendMessage() {
        if (isCorrectFormat()) {
            return "Video message sent: '" + message + "'";
        }
        return "Cannot send message with content type '"+format+"'.";
    }

    @Override
    public String displayMessage() {
        if (isCorrectFormat()) {
            return name + " sent a video: " + message;
        }
        return "Invalid content type for video message.";
    }
}
