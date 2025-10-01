package com.crio.xthreads.PrimeNumberChecker;

// PrimeChecker class that implements Runnable
class PrimeChecker implements Runnable {
    private final int number;
    private boolean isPrime;

    public PrimeChecker(int number) {
        this.number = number;
    }

    // TODO: Implement the run method to perform prime number checking
    // 1. Call the checkPrime method to determine if the number is prime.
    // 2. Store the result in the isPrime field.
    @Override
    public void run() {
        isPrime = checkPrime(number);
    }

    // Method to check if a number is prime
    private boolean checkPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Getter for isPrime
    public boolean isPrime() {
        return isPrime;
    }

    public int getNumber() {
        return number;
    }
    
}

