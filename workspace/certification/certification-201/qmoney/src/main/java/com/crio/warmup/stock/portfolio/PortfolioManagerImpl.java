
package com.crio.warmup.stock.portfolio;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.io.IOException;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.quotes.StockQuotesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {

  private String token = "8cf1d5316a1b78a8d2758a9e5a05472d27f886a7";
  private RestTemplate restTemplate;

  StockQuotesService stockQuotesService;

  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility

  @Deprecated
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    // this.restTemplate = restTemplate;
  }

  protected PortfolioManagerImpl(StockQuotesService stockQuotesService) {
    this.stockQuotesService=stockQuotesService;
  }

  
  public String getToken() {
    return this.token;
  }

  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException {
    if (to.isBefore(from)) {
      throw new RuntimeException();
    }
    Candle[] candles = restTemplate.getForObject(buildUri(symbol, from, to), TiingoCandle[].class);
    return (candles != null) ? Arrays.asList(candles) : Collections.emptyList();
  }

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
    // String uriTemplate = "https:api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
    // + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

    String uri = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?startDate="
        + startDate.toString() + "&endDate=" + endDate.toString() + "&token=" + token;

    return uri;
  }

  private Double getOpeningPriceOnStartDate(List<Candle> candles) {
    return candles.get(0).getOpen();
  }

  private Double getClosingPriceOnEndDate(List<Candle> candles) {
    return candles.get(candles.size() - 1).getClose();
  }

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate, PortfolioTrade trade,
      Double buyPrice, Double sellPrice) {
    if (endDate.isBefore(trade.getPurchaseDate())) {
      throw new RuntimeException("sell date can not be before purchase date!");
    }
    double buyValue = buyPrice * trade.getQuantity();
    double sellValue = sellPrice * trade.getQuantity();
    double totalReturns = (sellValue - buyValue) / buyValue;

    long days = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate);
    double years = (double) days / 365;
    double annualizedReturn = Math.pow(1 + totalReturns, 1 / years) - 1;
    return new AnnualizedReturn(trade.getSymbol(), annualizedReturn, totalReturns);
  }

  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate) throws Exception {
        List<AnnualizedReturn> annualizedReturns=new ArrayList<>();

        for(PortfolioTrade portfolioTrade: portfolioTrades){
          List<Candle> candles=stockQuotesService.getStockQuote(portfolioTrade.getSymbol(), portfolioTrade.getPurchaseDate(), endDate);
          AnnualizedReturn annualizedReturn=calculateAnnualizedReturns(endDate, portfolioTrade, getOpeningPriceOnStartDate(candles), getClosingPriceOnEndDate(candles));
          annualizedReturns.add(annualizedReturn);
        }
        Collections.sort(annualizedReturns);
        return annualizedReturns;
  }



  // ¶TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Modify the function #getStockQuote and start delegating to calls to
  //  stockQuoteService provided via newly added constructor of the class.
  //  You also have a liberty to completely get rid of that function itself, however, make sure
  //  that you do not delete the #getStockQuote function.

}
