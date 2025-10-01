package com.crio.xthreads.OddEvenPrinter;

import java.util.concurrent.locks.Lock;

public class OddEvenPrinter {
    private final int limit;
    private final Object lock = new Object();
    private boolean isOdd = true;

    public OddEvenPrinter(int limit) {
        this.limit = limit;
    }

    /**
     * TODO: Implement the method to print odd numbers. - Synchronize the method/block to ensure
     * thread-safe access. - Use a flag (`isOdd`) to determine if it is the correct time to print
     * odd numbers. - Use `lock.wait()` to pause the thread if it's not the right turn. - Print the
     * odd number and the thread name. - Update the `isOdd` flag to indicate that the next number to
     * print should be even. - Use `lock.notify()` to wake up the waiting thread (for even numbers).
     */
    public void printOdd() {
        for (int i = 1; i <= limit; i += 2) {
            synchronized (lock) {
                while(!isOdd){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " printed: " + i);
                isOdd=false;
                lock.notify();
            }
        }
    }

    /**
     * TODO: Implement the method to print even numbers. - Synchronize the method/block to ensure
     * thread-safe access. - Use a flag (`isOdd`) to determine if it is the correct time to print
     * even numbers. - Use `lock.wait()` to pause the thread if it's not the right turn. - Print the
     * even number and the thread name. - Update the `isOdd` flag to indicate that the next number
     * to print should be odd. - Use `lock.notify()` to wake up the waiting thread (for odd
     * numbers).
     */
    public void printEven() {
        synchronized(lock){
            for (int i = 2; i <= limit; i += 2) {
                while(isOdd){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " printed: " + i);
                isOdd=true;
                lock.notify();
            }
        }
    }

}
