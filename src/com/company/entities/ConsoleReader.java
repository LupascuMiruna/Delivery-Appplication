package com.company.entities;

import java.util.Scanner;

public class ConsoleReader {

    private static Scanner scanner;

    private ConsoleReader() {
    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
