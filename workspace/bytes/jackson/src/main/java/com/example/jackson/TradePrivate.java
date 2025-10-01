
package com.example.jackson;

public class TradePrivate {

  private String symbol;

  private int quantity;

  private String purchaseDate;

  public TradePrivate() {}

  public TradePrivate(String symbol, int quantity, String purchaseDate) {
    this.symbol = symbol;
    this.quantity = quantity;
    this.purchaseDate = purchaseDate;
  }

  public String getSymbol() {
    return symbol;
  }
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getQuantity() {
    return symbol;
  }
  public void setQuantity(String symbol) {
    this.symbol = symbol;
  }
  
  public String getPurchaseDate() {
    return symbol;
  }
  public void setPurchaseDate(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return "TradePrivate [purchaseDate=" + purchaseDate + ", quantity=" + quantity + ", symbol="
        + symbol + "]";
  }
}
