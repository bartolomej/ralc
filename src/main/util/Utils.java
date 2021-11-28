package main.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String join(String[] array, String delimiter) {
        String out = "";
        for (int i = 0; i < array.length; i++) {
            out += array[i];
            if (i < array.length - 1) {
                out += delimiter;
            }
        }
        return out;
    }

    public static int random(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static String formatWithColor(String string, String color) {
        return String.format("%s%s%s", color, string, ANSI_RESET);
    }

    public static String readFile(String path) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        InputStreamReader fs = new InputStreamReader(new FileInputStream(path));
        BufferedReader br = new BufferedReader(fs);
        String line;
        while ((line = br.readLine()) != null) {
            resultStringBuilder.append(line).append("\n");
        }
        return resultStringBuilder.toString();
    }
}
