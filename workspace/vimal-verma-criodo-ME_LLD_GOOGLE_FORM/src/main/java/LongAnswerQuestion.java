import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongAnswerQuestion {
    String questionText;
    public Boolean acceptResponse(String response) {
        return true;
    }
}
