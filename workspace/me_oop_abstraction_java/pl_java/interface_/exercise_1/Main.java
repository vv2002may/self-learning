package pl_java.interface_.exercise_1;

import java.util.Scanner;

import pl_java.interface_.exercise_1.payment.Payment;

public class Main {
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Pay for checkout");
    //     System.out.println("Choose your option:");
    //     System.out.println("1)Pay using Debit Card");
    //     System.out.println("2)Pay using Net Banking");
    //     System.out.println("3)Pay using UPI");
    //     System.out.println("4)Pay using Paytm Wallet");
    //     Integer option = sc.nextInt();
    //     Payment c = new Payment();
    //     switch(option){
    //       case 1:
    //         c.PayWithDebitCard();
    //         break;
    //       case 2:
    //         c.PayWithNetBanking();
    //         break;
    //       case 3:
    //         c.PayWithUPI();
    //         break;
    //       case 4:
    //         c.PayWithPaytmWallet();
    //         break;
    //     }
    //   }
      
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pay for checkout");
        System.out.println("Choose your option:");
        System.out.println("1)Pay using Debit Card");
        System.out.println("2)Pay using Net Banking");
        System.out.println("3)Pay using UPI");
        System.out.println("4)Pay using Paytm Wallet");
        Integer paymentType = sc.nextInt();
        Payment payment = new Payment();
        payment.pay(paymentType);
        sc.close();
  }
}
