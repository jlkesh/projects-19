package uz.jl.front.ui;

import uz.jl.back.config.UserSession;
import uz.jl.back.enums.Role;
import uz.jl.back.service.UserService;
import uz.jl.back.vo.auth.AuthUserVO;
import uz.jl.front.utils.Color;
import uz.jl.front.utils.Reader;
import uz.jl.front.utils.Writer;

import java.util.Objects;

public class MainMenu {

    static AuthPage authUI =  new AuthPage();
    static UserPage userPage =  new UserPage();
    static AdminPage adminPage =  new AdminPage();
    static AuthUserVO session = UserSession.getInstance().getUserVO();
    static UserService userService = UserService.getInstance();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        if (Objects.isNull(session)) {
            Writer.println("login -> 1");
            Writer.println("register -> 2");
        } else {
            Writer.println("logout -> 3");
            if (session.getRole().equals(Role.ADMIN)) adminPage.menu();
            else userPage.menu();
        }
        Writer.println("Quit  -> q");
        String choice = Reader.readLine("👆 ?");
        switch (choice) {
            case "1" -> authUI.login();
            case "2" -> authUI.register();
            case "3" -> authUI.logout();
            case "q" -> {
                Writer.println("Bye", Color.GREEN);
                userService.persist();
                System.exit(0);
            }
            default -> Writer.println("Wrong choice", Color.RED);
        }
        run();
    }
}
