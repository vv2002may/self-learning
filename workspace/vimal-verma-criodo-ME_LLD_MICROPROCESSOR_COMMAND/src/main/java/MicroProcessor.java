import java.util.HashMap;
import java.util.Map;

public class MicroProcessor{
    
    public static Map<String, Register> map = new HashMap<>();

    public static void main(String[] args) {

        Register A = new Register(0);
        Register B = new Register(0);
        Register C = new Register(0);
        Register D = new Register(0);

        map.put("A", A);
        map.put("B", B);
        map.put("C", C);
        map.put("D", D);

    }

    public void executeCommand(SetCommand setCommand) {

    }

    public Register getRegister(String name) {
        return map.get(name);
    }

}