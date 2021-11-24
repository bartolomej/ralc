package main;

public class Utils {

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
}
