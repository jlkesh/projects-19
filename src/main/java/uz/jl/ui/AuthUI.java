package uz.jl.ui;

import uz.jl.dto.user.UserCreateDTO;
import uz.jl.service.UserService;

import java.util.Scanner;

public class AuthUI {
    final static Scanner scanner = new Scanner(System.in);
    final static UserService service = new UserService();

    public static void main(String[] args) {

        System.out.println("register -> 1");
        System.out.println("quit -> q");
        String choice = scanner.nextLine();
        String s = "john";
        if (choice.equals("1")) {
            UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                    .username(scanner.nextLine())
                    .password(scanner.nextLine())
                    .language(scanner.nextLine())
                    .build();
            service.create(userCreateDTO);
        } else if (choice.equals("q")) {
            service.persist();
            return;
        } else System.out.println("Wrong choice");
        main(args);
    }
}
