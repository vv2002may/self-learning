public class HasMoneyState implements State {
    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int coin) {
        machine.amount += coin;
    }

    @Override
    public void selectProduct(String product) {
        if (!machine.productPriceMap.containsKey(product)) {
            System.out.println("Sorry, we don't have that product.");
            return;
        }
        int price = machine.productPriceMap.get(product);
        machine.choice = product;

        if (machine.amount >= price) {
            System.out.println("Selected " + product);
            machine.setState(new DispensingState(machine));
        } else {
            System.out.println("Please insert more money.");
        }
    }

    @Override
    public void dispenseProduct() {
        if (machine.choice == null) {
            System.out.println("Please select a product");
            return;
        }

        int price = machine.productPriceMap.get(machine.choice);
        if (machine.amount >= price) {
            System.out.println("Dispensing " + machine.choice);
            machine.amount -= price;
            machine.choice = null;
            machine.setState(machine.amount == 0 ? new IdleState(machine) : new HasMoneyState(machine));
        } else {
            System.out.println("Please insert more money.");
        }
    }
}
