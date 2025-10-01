
package com.example.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
public class TradeData {
  private String symbol;
  private int quantity;
  private LocalDate purchaseDate;
} 