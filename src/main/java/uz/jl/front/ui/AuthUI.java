package uz.jl.front.ui;

import uz.jl.back.config.UserSession;
import uz.jl.back.service.UserService;
import uz.jl.back.vo.auth.AuthLoginVO;
import uz.jl.back.vo.auth.AuthUserVO;
import uz.jl.front.utils.Reader;
import uz.jl.front.utils.Writer;

import java.util.Scanner;

public class AuthUI {
    final static Scanner scanner = new Scanner(System.in);
    final static UserService service = new UserService();

    public static void menu() {
        UserSession session = UserSession.getInstance();
        String choice = "";
        while (!choice.startsWith("<")) {
            if (session.getUserVO() == null) {
                Writer.println("login    -> 1");
                Writer.println("register -> 2");
            } else {
                Writer.println("logout   -> 3");
            }
            Writer.println("Go Back  -> <<");
            choice = Reader.readLine("?:");
            switch (choice) {
                case "1" -> innerLogin();
                case "2" -> register();
                case "3" -> logout();
                case "<<" -> {
                    return;
                }
            }
        }

    }

    private static void innerLogin() {
        AuthLoginVO vo = AuthLoginVO.builder()
                .login(Reader.readLine("Login : "))
                .password(Reader.readLine("Password : "))
                .build();
        AuthUserVO response = service.login(vo);
    }

    private static void logout() {
        Writer.println("Successfully logged out");
        UserSession.getInstance().setUserVO(null);
    }

    private static void register() {
        System.out.println("Register method called");
    }

    public static void login() {
        AuthLoginVO vo = AuthLoginVO.builder()
                .login(Reader.readLine("Login : "))
                .password(Reader.readLine("Password : "))
                .build();
        AuthUserVO response = service.login(vo);
        UserSession.getInstance().setUserVO(response);
        menu();
    }
}
