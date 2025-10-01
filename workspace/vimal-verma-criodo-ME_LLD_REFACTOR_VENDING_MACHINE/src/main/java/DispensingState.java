public class DispensingState implements State {
    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int coin) {
        System.out.println("Please wait for the current transaction to finish");
    }

    @Override
    public void selectProduct(String product) {
        if (!machine.productPriceMap.containsKey(product)) {
            System.out.println("Sorry, we don't have that product.");
            return;
        }

        machine.choice = product;
        int price = machine.productPriceMap.get(product);
        if (machine.amount >= price) {
            System.out.println("Selected " + product);
        } else {
            System.out.println("Please insert more money.");
        }
    }

    @Override
    public void dispenseProduct() {
        if (machine.choice != null) {
            int price = machine.productPriceMap.get(machine.choice);
            if (machine.amount >= price) {
                System.out.println("Dispensing " + machine.choice);
                machine.amount = 0;
                machine.choice = null;
                machine.setState(new IdleState(machine));
            } else {
                System.out.println("Please insert more money.");
                machine.setState(new HasMoneyState(machine));
            }
        } else {
            System.out.println("Please select a product");
            machine.setState(machine.amount == 0 ? new IdleState(machine) : new DispensingState(machine));
        }
    }
}