package uz.jl.service;

import lombok.NonNull;
import uz.jl.config.PasswordConfigurer;
import uz.jl.dao.UserDao;
import uz.jl.domains.User;
import uz.jl.dto.user.UserCreateDTO;
import uz.jl.enums.Role;
import uz.jl.mapper.user.UserMapper;
import uz.jl.util.Color;
import uz.jl.util.Reader;
import uz.jl.util.Writer;

import java.util.Optional;

public class UserService {
    private UserDao userDao = new UserDao();
    private UserMapper mapper = new UserMapper();


    public long create(@NonNull UserCreateDTO dto) {
        User user = mapper.fromCreateDto(dto);
        return userDao.save(user);
    }

    public void persist() {
        userDao.persist();
    }

    public void register() {
        Writer.println("Username: ");
        String username = Reader.readLine();
        Writer.println("Password: ");
        String password = Reader.readLine();
        Writer.println(" Choose your language: UZ/EN/RU");
        String language = Reader.readLine();
        UserCreateDTO dto = new UserCreateDTO();
        dto.setLanguage(language);
        dto.setPassword(password);
        dto.setUsername(username);
        create(dto);
    }

    private boolean checkLanguage(String language) {
        for (Role value : Role.values()) {
            if (value.equals(language))
                return true;
        }
        return false;
    }

    public void login() {
        String username = Reader.readLine("Username: ");
        String password = Reader.readLine("Password: ");
        Optional<User> byUsername =
                userDao.findByUsername(username);
        if (byUsername.isEmpty()) {
            Writer.println(Color.RED, "User not found");
            return;
        }
        User user = byUsername.get();
        boolean matchPassword = PasswordConfigurer.matchPassword(password, user.getPassword());
        Writer.println("Welcome!");
        session = user;

    }

    public void myQuizzes() {
    }

    public void startQuiz() {
    }
}
