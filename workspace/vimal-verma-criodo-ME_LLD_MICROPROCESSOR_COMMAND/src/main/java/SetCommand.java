public class SetCommand{

    private String register;
    
    public SetCommand(MicroProcessor processor, String register, int value){
        switch(register){
            case "A":
            MicroProcessor.map.get(register).setValue(value);
                
        }
    }
}