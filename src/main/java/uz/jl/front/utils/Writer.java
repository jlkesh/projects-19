package uz.jl.front.utils;

import java.io.PrintStream;

public class Writer {
    private static final PrintStream out = null;


    public static void print(Object data) {
        print(data, Color.BLUE);
    }

    public static void print(Object data, String color) {
        System.out.print(color + data + Color.RESET);
    }

    public static void println(Object data) {
        println(data, Color.BLUE);
    }

    public static void println(Object data, String color) {
        System.out.println(color + data + Color.RESET);
    }
}
