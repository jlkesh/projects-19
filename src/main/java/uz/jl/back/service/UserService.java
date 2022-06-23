package uz.jl.back.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import uz.jl.back.config.PasswordConfigurer;
import uz.jl.back.dao.UserDao;
import uz.jl.back.domains.User;
import uz.jl.back.enums.Language;
import uz.jl.back.user.UserCreateDTO;
import uz.jl.back.vo.auth.AuthLoginVO;
import uz.jl.back.vo.auth.AuthUserVO;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void persist() {
        userDao.persist();
    }

    public AuthUserVO login(AuthLoginVO vo) {
        // TODO: 6/23/2022 write some logic here
        Optional<User> userOptional = userDao.findByUsername(vo.getLogin());
        if (userOptional.isEmpty()) {
            log.info("User not found");
            return null;
        }
        User user = userOptional.get();
        if (!PasswordConfigurer.matchPassword(vo.getPassword(), user.getPassword())) {
            log.info("Bad credentials");
            return null;
        }
        if (!user.isActive()) {
            log.info("User not active");
            return null;
        }
        return AuthUserVO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .language(user.getLanguage())
                .role(user.getRole())
                .build();
    }
}
