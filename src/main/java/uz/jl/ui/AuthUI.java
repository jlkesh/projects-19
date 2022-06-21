package uz.jl.ui;

import uz.jl.enums.Role;
import uz.jl.service.AdminService;
import uz.jl.service.UserService;
import uz.jl.util.Writer;

import java.util.Objects;
import java.util.Scanner;

import static uz.jl.dao.DAO.session;

public class AuthUI {
    final static Scanner scanner = new Scanner(System.in);
    final static UserService service = new UserService();
    final static AdminService adminService = new AdminService();

    public static void main(String[] args) {
        if (Objects.isNull(session)) {
            System.out.println("Register -> 1");
            System.out.println("login -> 2");
        } else if (Objects.nonNull(session) && Role.ADMIN.equals(session.getRole())) {
            adminMenu();
        } else if (Objects.nonNull(session) && Role.USER.equals(session.getRole())) {
            userMenu();
        }
        System.out.println("Quit -> q");
        String choice = scanner.nextLine();
        if (choice.equals("1") && Objects.isNull(session)) {
            service.register();
        } else if (choice.equals("2") && Objects.isNull(session)) {
            service.login();
        } else if (choice.equals("1") && Objects.nonNull(session) && Role.ADMIN.equals(session.getRole())) {
            adminService.userCrud();
        } else if (choice.equals("2") && Objects.nonNull(session) && Role.ADMIN.equals(session.getRole())) {
            adminService.questionCrud();
        } else if (choice.equals("3") && Objects.nonNull(session) && Role.ADMIN.equals(session.getRole())) {
            adminService.subjectCrud();
        }
        else if (choice.equals("1") && Objects.nonNull(session) && Role.USER.equals(session.getRole())) {
            service.startQuiz();
        }
        else if (choice.equals("2") && Objects.nonNull(session) && Role.USER.equals(session.getRole())) {
            service.myQuizzes();
        }
        else if (choice.equals("q")) {
            service.persist();
            return;
        } else System.out.println("Wrong choice");
        main(args);
    }

    private static void userMenu() {
        Writer.println("Start quiz-> 1");
        Writer.println("My quizzes-> 2");
    }

    private static void adminMenu() {
        Writer.println("User crud ->1");
        Writer.println("Question crud ->2");
        Writer.println("Subject  crud ->3");
    }
}
