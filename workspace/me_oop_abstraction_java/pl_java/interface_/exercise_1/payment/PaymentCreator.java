package pl_java.interface_.exercise_1.payment;

public class PaymentCreator {
    public static Payable getPaymentProvider(int paymentType){
        switch(paymentType){
            case 1: //"DEBIT_CARD"
              return new DebitCard();     
            case 2: //"NET_BANKING"
              return new NetBanking();   
            case 3: //"UPI"
              return new UPI();  
            case 4: //"PAYTM_WALLET"
              return new Paytm();
            default:
              return null;
          }
    }
}
