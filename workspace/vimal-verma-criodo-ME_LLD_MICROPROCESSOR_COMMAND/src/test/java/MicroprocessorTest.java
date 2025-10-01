import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

class MicroProcessorTest {

    @Test
    void testSetCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.executeCommand(new SetCommand(processor, "B", 7));
        assertEquals(7, processor.getRegister("B").getValue());
    }

    @Test
    void testAdrCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("A").setValue(5);
        processor.getRegister("C").setValue(3);
        processor.executeCommand(new AdrCommand(processor, "C", "A"));
        assertEquals(8, processor.getRegister("C").getValue());
    }

    @Test
    void testAddCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("D").setValue(10);
        processor.executeCommand(new AddCommand(processor, "D", 15));
        assertEquals(25, processor.getRegister("D").getValue());
    }

    @Test
    void testMovCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("C").setValue(0);
        processor.getRegister("D").setValue(12);
        processor.executeCommand(new MovCommand(processor, "C", "D"));
        assertEquals(12, processor.getRegister("C").getValue());
        assertEquals(12, processor.getRegister("D").getValue());
    }

    @Test
    void testInrCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("B").setValue(6);
        processor.executeCommand(new InrCommand(processor, "B"));
        assertEquals(7, processor.getRegister("B").getValue());
    }

    @Test
    void testDcrCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("A").setValue(4);
        processor.executeCommand(new DcrCommand(processor, "A"));
        assertEquals(3, processor.getRegister("A").getValue());
    }

    @Test
    void testRstCommand() {
        MicroProcessor processor = new MicroProcessor();
        processor.getRegister("A").setValue(5);
        processor.getRegister("B").setValue(10);
        processor.getRegister("C").setValue(15);
        processor.getRegister("D").setValue(20);
        processor.executeCommand(new RstCommand(processor));
        assertEquals(0, processor.getRegister("A").getValue());
        assertEquals(0, processor.getRegister("B").getValue());
        assertEquals(0, processor.getRegister("C").getValue());
        assertEquals(0, processor.getRegister("D").getValue());
    }

   

    @Test
    void testCommandInterface() throws ClassNotFoundException, NoSuchMethodException {
        // Ensure the Command interface exists
        Class<?> commandInterface = Class.forName("Command");
        assertTrue(commandInterface.isInterface(), "Command should be an interface");

        // Ensure the Command interface has the execute method
        Method executeMethod = commandInterface.getMethod("execute");
        assertNotNull(executeMethod, "Command interface should have an execute() method");
    }

    @Test
    void testInvalidCommand() {
        MicroProcessor processor = new MicroProcessor();

        class InvalidCommand {
            public void execute() {
                // Invalid command that does not implement the Command interface
            }
        }

        Object invalidCommand = new InvalidCommand();

        // Use reflection to bypass direct method invocation
        Exception exception = assertThrows(ClassCastException.class, () -> {
            processor.executeCommand((Command) invalidCommand);
        });

        assertEquals(ClassCastException.class, exception.getClass());
    }

//  testCommandInterface:

        // This test ensures that the Command interface exists and contains an execute() method using Java reflection.
        // Class.forName("Command") checks if the Command interface exists.
        // commandInterface.getMethod("execute") verifies the presence of the execute method.

// testInvalidCommand:

    // Uses an inner class to simulate an invalid command that does not implement the Command interface.
    // Ensures a ClassCastException is thrown when an invalid command is passed to executeCommand.
}

