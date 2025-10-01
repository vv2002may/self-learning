package com.crio.warmup.stock;

import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.management.RuntimeErrorException;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {
  private static String token = "f1f0c3563894d72598e398ab2fee37ed9d58c66a";

  public static String getToken() {
    return token;
  }

  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).toURI())
        .toFile();
  }

  public static List<PortfolioTrade> readTradesFromJson(String filename)
      throws IOException, URISyntaxException {
    ObjectMapper objectMapper = getObjectMapper();
    File inputFile = resolveFileFromResources(filename);
    PortfolioTrade[] portfolioTrades = objectMapper.readValue(inputFile, PortfolioTrade[].class);
    return Arrays.asList(portfolioTrades);
  }

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {
    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    List<String> symbolList = new ArrayList<>();
    for (PortfolioTrade i : portfolioTrades) {
      symbolList.add(i.getSymbol());
    }
    return symbolList;
  }

  public static List<String> debugOutputs() {

    String valueOfArgument0 = "trades.json";
    String resultOfResolveFilePathArgs0 =
        "trades.json";
    String toStringOfObjectMapper = "ObjectMapper";
    String functionNameFromTestFileInStackTrace = "mainReadFile";
    String lineNumberFromTestFileInStackTrace = "";


    return Arrays.asList(
        new String[] {valueOfArgument0, resultOfResolveFilePathArgs0, toStringOfObjectMapper,
            functionNameFromTestFileInStackTrace, lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {

    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    List<TotalReturnsDto> totalReturnsDtos = new ArrayList<>();
    for (PortfolioTrade i : portfolioTrades) {
      List<Candle> candles = fetchCandles(i, LocalDate.parse(args[1]), token);
      totalReturnsDtos.add(new TotalReturnsDto(i.getSymbol(), getClosingPriceOnEndDate(candles)));
    }
    Collections.sort(totalReturnsDtos);
    List<String> symbolList = new ArrayList<>();
    for (TotalReturnsDto dto : totalReturnsDtos) {
      symbolList.add(dto.getSymbol());
    }
    return symbolList;

  }


  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {

    String uri = "https://api.tiingo.com/tiingo/daily/" + trade.getSymbol() + "/prices?startDate="
        + trade.getPurchaseDate().toString() + "&endDate=" + endDate.toString() + "&token=" + token;

    return uri;
  }

  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
    return candles.get(0).getOpen();
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
    return candles.get(candles.size() - 1).getClose();
  }


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    if(endDate.isBefore(trade.getPurchaseDate())){
      throw new RuntimeException();
    }
    Candle[] candles = new RestTemplate().getForObject(prepareUrl(trade, endDate, token), TiingoCandle[].class);
    return Arrays.asList(candles);
  }

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
      List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
      List<AnnualizedReturn> annualizedReturns = new ArrayList<>();
      LocalDate endDate=LocalDate.parse(args[1]);
      for(PortfolioTrade trade:portfolioTrades){
        List<Candle> candles=fetchCandles(trade, endDate, token);
        AnnualizedReturn annualizedReturn=calculateAnnualizedReturns(endDate, trade, getOpeningPriceOnStartDate(candles), getClosingPriceOnEndDate(candles));
        annualizedReturns.add(annualizedReturn);
      }
      Collections.sort(annualizedReturns);
    return annualizedReturns;
  }

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate, PortfolioTrade trade,
      Double buyPrice, Double sellPrice) {
        if(endDate.isBefore(trade.getPurchaseDate())){
          throw new RuntimeException("sell date can not be before purchase date!");
        }
    double buyValue=buyPrice*trade.getQuantity();    
    double sellValue=sellPrice*trade.getQuantity();    
    double totalReturns = (sellValue - buyValue) / buyValue;

    long days = ChronoUnit.DAYS.between(trade.getPurchaseDate(),endDate);
    double years=(double)days/365;
    double annualizedReturn=Math.pow(1+totalReturns, 1/years)-1;
    return new AnnualizedReturn(trade.getSymbol(), annualizedReturn, totalReturns);
  }


  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       LocalDate endDate = LocalDate.parse(args[1]);
       List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
       PortfolioManager portfolioManager=PortfolioManagerFactory.getPortfolioManager(new RestTemplate());
       return portfolioManager.calculateAnnualizedReturn(portfolioTrades, endDate);
  }


  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      throw new IllegalArgumentException("Usage: PortfolioManagerApplication <inputFile> <provider>");
  }
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());

    // printJsonObject(mainReadFile(args));
    // printJsonObject(mainReadQuotes(args));
    // printJsonObject(mainCalculateSingleReturn(args));
    printJsonObject(mainCalculateReturnsAfterRefactor(args));
  }

}

