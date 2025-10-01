public class IdleState implements State {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int coin) {
        machine.amount += coin;
        machine.setState(new HasMoneyState(machine));
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("Please insert a coin");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please insert a coin");
    }
}