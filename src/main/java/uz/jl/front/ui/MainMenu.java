package uz.jl.front.ui;

import uz.jl.front.utils.Color;
import uz.jl.front.utils.Reader;
import uz.jl.front.utils.Util;
import uz.jl.front.utils.Writer;

public class MainMenu {

    static AuthUI authUI = new AuthUI();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Writer.println("login -> 1");
        Writer.println("Quit  -> q");
        String choice = Reader.readLine("👆 ?");
        if (choice.equals("1")) {
            authUI.login();
        } else if (choice.startsWith("q")) {
            Writer.println("Bye", Color.GREEN);
            System.exit(0);
        } else {
            Writer.println("Wrong choice", Color.RED);
        }
        run();
    }
}
