package main;

import java.io.*;
import static main.util.Utils.*;

public class Help {

    static public void printHelp() {
        try {
            String commandDocumentation = readFile("./commands.md");
            printFormatted(commandDocumentation);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void printCommandHelp(String commandName) {
        try {
            String content = readFile("./commands.md");
            String[] lines = content.split("\n");
            for (String line : lines) {
                if (line.startsWith("-")) {
                    String[] parts = parseCommandLine(line);
                    if (parts[0].contains(commandName)) {
                        printCommandLine(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private void printFormatted(String content) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            try {
                printLine(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printLine(String line) throws Exception {
        if (line.startsWith("##")) {
            printTitleLine(line);
        }
        else if (line.startsWith("-")){
            printCommandLine(line);
        }
        else if (line.equals("")) {
            System.out.println();
        }
    }

    private static void printCommandLine(String line) {
        String[] parts = parseCommandLine(line);
        System.out.format("%-20s%s\n", formatWithColor(parts[0], ANSI_BLUE), parts[1]);
    }

    private static void printTitleLine(String line) {
        String formatted = line.replaceAll("#", "").trim();
        System.out.println(formatWithColor(formatted, ANSI_GREEN));
    }

    private static String[] parseCommandLine(String line) {
        String[] parts = line.replaceFirst("-", "").split(" - ");
        String command = parts[0].replaceAll("[` ]", "");
        String description = parts[1].trim();
        return new String[]{command, description};
    }
}
