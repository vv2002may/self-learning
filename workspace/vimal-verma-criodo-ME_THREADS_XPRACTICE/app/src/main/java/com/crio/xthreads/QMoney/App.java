/*
 * To implement a multi-threaded stock return calculator, we will use an ExecutorService to process multiple trades concurrently. 
 * The goal is to calculate the total and annualized returns for each stock in the portfolio, leveraging parallel processing for efficiency.
 */

package com.crio.xthreads.QMoney;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.crio.xthreads.QMoney.dto.ReturnSummary;
import com.crio.xthreads.QMoney.stockdata.StockDataServiceType;
import com.crio.xthreads.QMoney.trade.Trade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class App {

    public static void main(String[] args) {
        // Initialize QMoneyCalculator with TIINGO stock data service
        QMoneyCalculator calculator = new QMoneyCalculator(StockDataServiceType.TIINGO);
        
        // Initialize ObjectMapper for JSON parsing
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Load trades from the JSON file
        List<Trade> trades = loadTradesFromFile(objectMapper);

        // Define a fixed end date for calculating returns
        LocalDate endDate = LocalDate.parse("2019-12-12");

        /* 
        TODO: Modify the code to support parallel processing using an ExecutorService.
   
        1. **Create an ExecutorService**:
            - Replace the sequential for loop with an ExecutorService for parallel processing.
            - Use `Executors.newFixedThreadPool()` to create a fixed thread pool.
            - The number of threads can be determined by `Runtime.getRuntime().availableProcessors()` to utilize all available CPU cores.

        2. **Submit Tasks for Parallel Execution**:
            - Instead of processing trades sequentially, submit each trade as a task to the ExecutorService.
            - Use the `submit()` method of the ExecutorService to submit a `Callable<ReturnSummary>` task for each trade.
            - The task should involve calculating the returns for each trade by invoking `calculator.calculateReturns()`.

        3. **Collect Results Using Future**:
            - Store the `Future<ReturnSummary>` objects returned by `submit()` in a `List<Future<ReturnSummary>>`.
            - This allows you to retrieve the results of the tasks after they have completed.

        4. **Handle Exceptions Gracefully**:
            - Use a loop to iterate over the `Future` objects and call `future.get()` to retrieve the `ReturnSummary`.
            - Handle any potential exceptions, such as `InterruptedException` or `ExecutionException`, that may occur during the retrieval process.
            - In case of an error, log the error message and return a default `ReturnSummary` (e.g., with a null symbol and 0.0 returns).

        5. **Print the Results**:
            - After all tasks have completed, iterate over the collected `ReturnSummary` results and print them in the desired format.

        6. **Shutdown the ExecutorService**:
            - After all tasks have been submitted, initiate a graceful shutdown of the ExecutorService using `shutdown()`.
            - Use `awaitTermination()` to wait for all tasks to complete or a timeout to occur.
            - If tasks are still running after the timeout, force shutdown using `shutdownNow()`.
            - Ensure that the shutdown process is robust by handling `InterruptedException`.
        */


         // Create an ExecutorService with a fixed thread pool for parallel processing
         ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

          // List to hold Future objects representing the results of the tasks
        List<Future<ReturnSummary>> futures = new ArrayList<>();



        //  for (Trade trade : trades) {
        //      try {
        //          ReturnSummary result = calculator.calculateReturns(trade.getSymbol(), trade.getPurchaseDate(), endDate);
        //          System.out.printf("Symbol: %s | Total Return: %.2f%% | Annualized Return: %.2f%%\n",
        //                  result.getSymbol(), result.getTotalReturn(), result.getAnnualizedReturn());

        //      } catch (Exception e) {
        //          System.err.println("Error processing trade result: " + e.getMessage());
        //      }
        //  }

         // Submit tasks for each trade to be processed concurrently
         for (Trade trade : trades) {
            Callable<ReturnSummary> task = () -> calculator.calculateReturns(trade.getSymbol(), trade.getPurchaseDate(), endDate);
            Future<ReturnSummary> future = executorService.submit(task);
            futures.add(future);
        }

        // Aggregate and print the results
        for (Future<ReturnSummary> future : futures) {
            try {
                ReturnSummary result = future.get();
                System.out.printf("Symbol: %s | Total Return: %.2f%% | Annualized Return: %.2f%%\n",
                        result.getSymbol(), result.getTotalReturn(), result.getAnnualizedReturn());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error processing trade result: " + e.getMessage());
            }
        }

        // Shutdown the ExecutorService
        executorService.shutdown();
        try {
            // Wait for all tasks to complete or timeout after a specific duration
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Force shutdown if tasks are still running
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt(); // Restore interrupted state
        }
    }

    

    private static List<Trade> loadTradesFromFile(ObjectMapper objectMapper) {
        // Load and parse trades from the JSON file
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("trades.json")) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Trades file not found in resources folder.");
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<Trade>>() {});
        } catch (IOException e) {
            System.err.println("Error loading trades: " + e.getMessage());
            return List.of(); // Return an empty list if an error occurs
        }
    }

}