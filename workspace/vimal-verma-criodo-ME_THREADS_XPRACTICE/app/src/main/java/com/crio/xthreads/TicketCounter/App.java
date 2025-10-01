package com.crio.xthreads.TicketCounter;

// CustomerThread class simulating a customer trying to book tickets

public class App {

    public static void main(String[] args) throws InterruptedException {

        // TODO: Initialize the TicketCounter and Simulate Concurrent Ticket Booking.
        // 1. Initialize the TicketCounter object with a specific number of available tickets (e.g.,
        // 10 tickets).
        // 2. Create multiple customer threads using the CustomerThread class, each representing a
        // customer
        // attempting to book a certain number of tickets.
        // 3. Start all the customer threads to simulate concurrent ticket booking, allowing them to
        // run in parallel.

         // Initialize the TicketCounter with a specific number of tickets (e.g., 10)
         TicketCounter ticketCounter = new TicketCounter(10);

         // Create multiple customer threads representing different booking attempts
         Thread customer1 = new Thread(new CustomerThread(ticketCounter, "Customer 1", 3));
         Thread customer3 = new Thread(new CustomerThread(ticketCounter, "Customer 3", 2));
         Thread customer4 = new Thread(new CustomerThread(ticketCounter, "Customer 4", 3));
         Thread customer2 = new Thread(new CustomerThread(ticketCounter, "Customer 2", 5));
 

          // Start all the customer threads to simulate concurrent ticket booking

          // Start customer 1, 3, and 4 threads and wait for them to finish
        customer1.start();
        customer1.join();  // Wait for Customer 1 to finish booking

        customer3.start();
        customer3.join();  // Wait for Customer 3 to finish booking

        customer4.start();
        customer4.join();  // Wait for Customer 4 to finish booking

        // Finally, start customer 2 (this thread should fail due to lack of tickets)
        customer2.start();
        customer2.join();  // Wait for Customer 2 to finish
    }
}
