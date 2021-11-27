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

            // print help for all available commands
            if (input.equals(".help")) {
                Help.printHelp();
                continue;
            }

            // print help for specific command(s)
            if (input.matches("\\.help .*")) {
                String[] parts = input.split(" ");
                Help.printCommandHelp(parts[1]);
                continue;
            }

            try {
                String result = computer.execute(input);
                computer.clearState();
                System.out.print(result);
            } catch (Exception e) {
                System.err.println("Failed to execute: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
