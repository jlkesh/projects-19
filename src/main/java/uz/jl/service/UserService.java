package uz.jl.service;

import lombok.NonNull;
import uz.jl.config.PasswordConfigurer;
import uz.jl.dao.UserDao;
import uz.jl.domains.User;
import uz.jl.dto.user.UserCreateDTO;
import uz.jl.enums.Language;

public class UserService {
    private UserDao userDao = new UserDao();

    public long create(@NonNull UserCreateDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(PasswordConfigurer.encode(dto.getPassword()))
                .language(Language.findByName(dto.getLanguage()))
                .build();
        return userDao.save(user);
    }

    public void persist() {
        userDao.persist();
    }
}
