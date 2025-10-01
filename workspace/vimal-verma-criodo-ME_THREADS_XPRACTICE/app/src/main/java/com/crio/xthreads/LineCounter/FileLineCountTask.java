package com.crio.xthreads.LineCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class FileLineCountTask implements Callable<Integer> {
    private final String fileName;

    public FileLineCountTask(String fileName) {
        this.fileName = fileName;
    }

    // - Define a constructor that takes the file name as a parameter.
    // - Implement the call method to:
    // - Open the file from the resources folder.
    // - Read the file line by line using BufferedReader.
    // - Count the lines and return the count.
    // - Handle any `IOException` that might occur during file operations.

    @Override
    public Integer call() throws IOException {
        int lineCount = 0;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            while (reader.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }
}
