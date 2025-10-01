package pl_java.interface_.exercise_1.payment;

interface Payable{
  void pay();
}

public class Payment{
  public void pay(int paymentType){
    Payable payment = PaymentCreator.getPaymentProvider(paymentType);
    payment.pay();
  }
    /*
    .
    .
    Rest of the payment methods
    .
    .
    */
    // public void PayWithUPI(){
    //   UPI upiPay = new UPI();
    //   upiPay.payWithUPI();
    // }
    // public void PayWithDebitCard(){
    //   DebitCard debitCardPay = new DebitCard();
    //   debitCardPay.payWithDebitCard();
    // }
    // public void PayWithNetBanking(){
    //   NetBanking netBankingPay = new NetBanking();
    //   netBankingPay.payWithNetBanking();
    // }
    // public void PayWithPaytmWallet(){
    //   Paytm paytmPay = new Paytm();
    //   paytmPay.payWithPaytmWallet();
    // }
  }
