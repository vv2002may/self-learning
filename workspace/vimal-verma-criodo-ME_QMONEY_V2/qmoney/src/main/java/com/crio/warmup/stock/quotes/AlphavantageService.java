package com.crio.warmup.stock.quotes;

import com.crio.warmup.stock.dto.AlphavantageCandle;
import com.crio.warmup.stock.dto.AlphavantageDailyResponse;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
// import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;



public class AlphavantageService implements StockQuotesService {

  private RestTemplate restTemplate;
  private ObjectMapper objectMapper = getObjectMapper();

  
  public AlphavantageService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to) throws 
      Exception {
    List<Candle> candles = new ArrayList<>();
    String uri = buildUri(symbol);
    String result = restTemplate.getForObject(uri,String.class);
//     String res = restTemplate.getForObject(buildUri(symbol), String.class);
// System.out.println(res);
    AlphavantageDailyResponse response = null;
    try {
      response = objectMapper.readValue(result, new TypeReference<AlphavantageDailyResponse>() {
      });

      for (Entry<String, AlphavantageCandle> entry : response.getCandles().entrySet()) {
        LocalDate date = LocalDate.parse(entry.getKey());
        if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
          entry.getValue().setDate(date);
          candles.add(entry.getValue());
        }

      }
    } catch (JsonProcessingException e) {
      throw new StockQuoteServiceException("Error from AlphavantageService : cannot process json");
    } catch (NullPointerException e) {
      throw new StockQuoteServiceException("Error from AlphavantageService : Null values received");
    } catch (Exception e) {
      throw new RuntimeException();
    }

    return candles;

    // other way
    
    // ObjectMapper om=new ObjectMapper();
    //     om.registerModule(new JavaTimeModule());
    //     String url= buildUri(symbol);
    //  List<Candle> lst=new ArrayList<>();
    //   // System.out.println("Generated URL: " + url); 
    //   //  return Arrays.asList(restTemplate.getForObject(url, TiingoCandle[].class));
    //   try{
    //   String apiMapResponse = restTemplate.getForObject(url, String.class);

    //   Map<LocalDate, AlphavantageCandle> responseMap= om.readValue(apiMapResponse, AlphavantageDailyResponse.class).getCandles();

    //   for (LocalDate date =from; !date.isAfter(to); date = date.plusDays(1)) {
    //      AlphavantageCandle candle = responseMap.get(date);

    //      if(candle !=null){
    //       candle.setDate(date);
    //       lst.add(candle);
    //      }
    //     }
    //   }catch(NullPointerException e){
    //     throw new StockQuoteServiceException("Alphavantage returned invalid response",e);
    //   }

    //   return lst;
  }


  //  ./gradlew test --tests AlphavantageServiceTest
  //CHECKSTYLE:OFF

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }
  
  
  protected String buildUri(String symbol) {
    String uriTemplate = "https://www.alphavantage.co/" 
        + "query?function=TIME_SERIES_DAILY&apikey=GRZKHQQAODJHTIG3&outputsize=full&symbol=" 
        + symbol;
    return uriTemplate;  
  }

  // TODO: CRIO_TASK_MODULE_EXCEPTIONS
  //  Update the method signature to match the signature change in the interface.
  //  Start throwing new StockQuoteServiceException when you get some invalid response from
  //  Alphavangate, or you encounter a runtime exception during Json parsing.
  //  Make sure that the exception propagates all the way from PortfolioManager,
  //  so that the external user's of our API are able to explicitly handle this exception upfront.
  //CHECKSTYLE:OFF

}
