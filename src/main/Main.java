package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to ralc!");
        Scanner sc = new Scanner(System.in);
        Computer computer = new Computer();
        String input = "";
        while (!input.startsWith("exit")) {
            System.out.print("\n> ");
            input = sc.nextLine();
            try {
                String result = computer.execute(input);
                System.out.println(result);
            } catch (Exception e) {
                System.err.println("Failed to execute: " + e.getMessage());
                e.printStackTrace();
            }
            computer.clearState();
        }
    }
}
