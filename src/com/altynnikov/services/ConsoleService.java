package com.altynnikov.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleService {
    private static final Logger log = Logger.getLogger(ConsoleService.class.getName());
    private static final String RESET = "\033[0m";  // Text Reset
    private static final String RED = "\033[0;31m";     // RED

    public static String readConsoleInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readed = "";
        try {
            readed = reader.readLine();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return readed;
    }

    public static void showErrorMessage(String message) {
        System.out.println(RED + message + RESET);
    }
}
