import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortAnswerQuestion implements Question{

    String questionText;

    public Boolean acceptResponse(String response) {
        if(response.length()>30){
            return false;
        }
        return true;
    }

}
