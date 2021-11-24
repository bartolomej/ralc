package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to ralc!");
        System.out.println("Type '.help' for more info or '.exit' to exit.");
        Scanner sc = new Scanner(System.in);
        Computer computer = new Computer();
        String input = "";
        while (!input.startsWith(".exit")) {
            System.out.print("\n> ");
            input = sc.nextLine();
            if (input.equals(".help")) {
                showHelp();
                continue;
            }
            try {
                String result = computer.execute(input);
                System.out.print(result);
            } catch (Exception e) {
                System.err.println("Failed to execute: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    static void showHelp() {
        // TODO: parse and display commands.md
        System.out.println("Full list of commands is available here: https://github.com/bartolomej/ralc/blob/main/commands.md");
    }
}
