 import java.util.HashMap;
 import java.util.Map;

//  public class VendingMachine {

//      private enum State {
//          IDLE,
//          HAS_MONEY,
//          DISPENSING
//      }

//      private int amount;
//      private State state; 
//      private String choice;
//      private final Map<String, Integer> productPriceMap;

//      public VendingMachine() {
//          amount = 0;
//          state = State.IDLE;
//          choice = null;
//          productPriceMap = new HashMap<>();
//      }

//      public void addProduct(String productName, int price) {
//          productPriceMap.put(productName, price);
//      }

//      public void insertMoney(int coin) {
//          switch (state) {
//              case IDLE:
//                  amount += coin;
//                  state = State.HAS_MONEY;
//                  break;
//              case HAS_MONEY:
//                  amount += coin;
//                  break;
//              case DISPENSING:
//                  System.out.println("Please wait for the current transaction to finish");
//                  break;
//          }
//      }

//      public void selectProduct(String product) {
//          switch (state) {
//              case IDLE:
//                  System.out.println("Please insert a coin");
//                  break;
//              case HAS_MONEY:
//              case DISPENSING:
//                  if (productPriceMap.containsKey(product)) {
//                      int price = productPriceMap.get(product);
//                      choice = product;
//                      if (amount >= price) {
//                          System.out.println("Selected " + product);
//                          state = State.DISPENSING;
//                      } else {
//                          System.out.println("Please insert more money.");
//                      }
//                  } else {
//                      System.out.println("Sorry, we don't have that product.");
//                  }
//                  break;
//          }
//      }
//    public void dispenseProduct() {
//        switch (state) {
//            case IDLE:
//                System.out.println("Please insert a coin");
//                break;
//            case HAS_MONEY:
//                if (choice == null) {
//                    System.out.println("Please select a product");
//                } else {
//                    int price = productPriceMap.get(choice);
//                    if (amount >= price) {
//                        System.out.println("Dispensing " + choice);
//                        amount -= price;
//                        state = (amount == 0) ? State.IDLE : State.HAS_MONEY;
//                    } else {
//                        System.out.println("Please insert more money.");
//                        state = State.HAS_MONEY;
//                    }
//                }
//                break;
//            case DISPENSING:
//                if (choice != null) {
//                    int price = productPriceMap.get(choice);
//                    if (amount >= price) {
//                        System.out.println("Dispensing " + choice);
//                        amount = 0;
//                        state = State.IDLE;
//                    } else {
//                        System.out.println("Please insert more money.");
//                        state = State.HAS_MONEY;
//                    }
//                } else {
//                    System.out.println("Please select a product");
//                    state = (amount == 0) ? State.IDLE : State.DISPENSING;
//                }
//                break;
//        }
//    }

//  }



public class VendingMachine {

    private State state;
    int amount;
    String choice;
    final Map<String, Integer> productPriceMap;

    public VendingMachine() {
        productPriceMap = new HashMap<>();
        amount = 0;
        choice = null;
        state = new IdleState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addProduct(String productName, int price) {
        productPriceMap.put(productName, price);
    }

    public void insertMoney(int coin) {
        state.insertMoney(coin);
    }

    public void selectProduct(String product) {
        state.selectProduct(product);
    }

    public void dispenseProduct() {
        state.dispenseProduct();
    }
}