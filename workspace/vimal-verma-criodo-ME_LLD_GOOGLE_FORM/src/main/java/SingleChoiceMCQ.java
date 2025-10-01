import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleChoiceMCQ implements Question {
    String questionText;
    String[] choices;

    @Override
    public Boolean acceptResponse(String response) {
        return Arrays.asList(choices).contains(response);
    }

}
