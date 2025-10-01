
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.addProduct("Coke", 10);
        vendingMachine.addProduct("Pepsi", 15);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testInsertMoneyAndSelectProduct() {
        vendingMachine.insertMoney(10);
        vendingMachine.selectProduct("Coke");
        assertTrue(outContent.toString().contains("Selected Coke"));
    }

    @Test
    void testInsertMoneySelectProductAndDispense() {
        vendingMachine.insertMoney(10);
        vendingMachine.selectProduct("Coke");
        outContent.reset();
        vendingMachine.dispenseProduct();
        assertTrue(outContent.toString().contains("Dispensing Coke"));
    }

    @Test
    void testInsertInsufficientMoneyAndTryToDispense() {
        vendingMachine.insertMoney(5);
        vendingMachine.selectProduct("Pepsi");
        // outContent.reset();
        vendingMachine.dispenseProduct();
        System.err.println(outContent);
        assertTrue(outContent.toString().contains("Please insert more money."));
    }

    @Test
    void testSelectProductWithoutInsertingMoney() {
        vendingMachine.selectProduct("Coke");
        assertTrue(outContent.toString().contains("Please insert a coin"));
    }

    @Test
    void testStateTransitions() throws NoSuchFieldException, IllegalAccessException {
        // Initial state should be IdleState
        assertState(IdleState.class);

        // Insert money, should transition to HasMoneyState
        vendingMachine.insertMoney(10);
        assertState(HasMoneyState.class);

        // Select product, should transition to DispensingState if enough money
        vendingMachine.selectProduct("Coke");
        assertState(DispensingState.class);

        // Dispense product, should transition back to IdleState or HasMoneyState
        vendingMachine.dispenseProduct();
        assertState(IdleState.class);
    }

    @Test
    void testInsufficientMoneyStateTransition() throws NoSuchFieldException, IllegalAccessException {
        // Insert money less than product price, should remain in HasMoneyState
        vendingMachine.insertMoney(5);
        vendingMachine.selectProduct("Coke");
        assertState(HasMoneyState.class);

        // Insert additional money to meet product price, should transition to DispensingState
        vendingMachine.insertMoney(5);
        vendingMachine.selectProduct("Coke");
        assertState(DispensingState.class);
    }

    private void assertState(Class<?> expectedStateClass) throws NoSuchFieldException, IllegalAccessException {
        Field stateField = VendingMachine.class.getDeclaredField("state");
        stateField.setAccessible(true);
        Object state = stateField.get(vendingMachine);
        assertEquals(expectedStateClass, state.getClass(), "Expected state: " + expectedStateClass.getSimpleName());
    }
}


