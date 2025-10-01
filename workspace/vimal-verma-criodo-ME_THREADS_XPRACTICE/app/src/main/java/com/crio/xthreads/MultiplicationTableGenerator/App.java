package com.crio.xthreads.MultiplicationTableGenerator;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Input: n (can be hardcoded or taken from user input)
        int n = 5;  // Example input, you can replace it with a scanner input

        // TODO:
        // 1. Loop through each number from 1 to n:
        //    - Use a for-loop to iterate from 1 to n.

        // 2. For each number in the loop:
        //    - Create a new `Thread` instance that executes the `TableCreator` class.
        //    - Pass the current number `i` to the `TableCreator` constructor.

        // 3. Start the thread:
        //    - Call `start()` on the newly created thread to begin execution.

        // 4. Wait for the current thread to finish before starting the next one:
        //    - Call `join()` on the thread to ensure the main thread waits until the current thread completes execution.

        for(int i=1;i<=n;i++){
            TableCreator tableCreator = new TableCreator(i);
            Thread thread = new Thread(tableCreator);
            thread.start();
            thread.join();
        }
    }
}
