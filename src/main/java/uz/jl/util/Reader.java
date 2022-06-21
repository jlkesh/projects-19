package uz.jl.util;

import java.util.Scanner;

/**
 * @author "Elmurodov Javohir"
 * @since 14/06/22/15:52 (Tuesday)
 * untitled/IntelliJ IDEA
 */
public class Reader {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static String readLine(String placeHolder) {
        return readLine(placeHolder, Color.BLUE);
    }

    public static String readLine(String placeHolder, String color) {
        Writer.print(color + placeHolder + Color.RESET);
        return readLine();
    }

}

