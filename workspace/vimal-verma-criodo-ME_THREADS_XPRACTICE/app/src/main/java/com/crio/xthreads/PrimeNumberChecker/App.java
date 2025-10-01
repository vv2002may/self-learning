package com.crio.xthreads.PrimeNumberChecker;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Example list of numbers
        List<Integer> numbers = List.of(2, 3, 4, 5, 29, 77);

        // Lists to hold threads and PrimeChecker instances
        List<Thread> threads = new ArrayList<>();
        List<PrimeChecker> checkers = new ArrayList<>();

        // TODO:
        // 1. Create and start threads for each number in the `numbers` list:
        //    a. Iterate over the `numbers` list.
        //    b. For each number, create an instance of `PrimeChecker`.
        //    c. Create a `Thread` for each `PrimeChecker` instance.
        //    d. Start each thread to perform the prime check.
        //    e. Add each thread to the `threads` list.
        //    f. Add each `PrimeChecker` instance to the `checkers` list.

        // 2. Wait for all threads to finish:
        //    a. Iterate over the `threads` list.
        //    b. Call `join()` on each thread to ensure the main thread waits for their completion.

        // 3. Collect and display results:
        //    a. Iterate over the `numbers` list and corresponding `checkers` list.
        //    b. For each number, retrieve the result from the `PrimeChecker` instance.
        //    c. Print whether each number is prime or not based on the result from `isPrime()`.

        for(Integer number : numbers){
            PrimeChecker checker = new PrimeChecker(number);
            Thread thread = new Thread(checker);
            threads.add(thread);
            checkers.add(checker);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        for(PrimeChecker checker: checkers){
            System.out.println(checker.getNumber() + " is " + (checker.isPrime() ? "" : "not ") + "prime");
        }
    }

}
