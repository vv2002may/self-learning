import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage implements Message {
    String name;
    String message;

    @Override
    public String sendMessage() {
        return "Message sent: '"+message+"'";
    }

    @Override
    public String displayMessage() {
        return name + ": "+message;
    }
}
