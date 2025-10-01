import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceMCQ implements Question {
    String questionText;
    String[] choices;

    @Override
public Boolean acceptResponse(String response) {
    List<String> checkResponse = Arrays.asList(response.split(", "));

    // Ensure both lists match exactly
    return Arrays.asList(choices).containsAll(checkResponse);
}

}
