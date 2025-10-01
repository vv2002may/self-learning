
package com.example.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor@Getter@Setter
public class Trade {
  
  private String symbol;
  private int quantity;
  private LocalDate purchaseDate;

  public String getSymbol(String string, int i, LocalDate localDate) {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

}
