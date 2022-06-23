package uz.jl.back.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import uz.jl.back.config.PasswordConfigurer;
import uz.jl.back.dao.UserDao;
import uz.jl.back.domains.User;
import uz.jl.back.enums.Language;
import uz.jl.back.enums.Role;
import uz.jl.back.vo.auth.AuthLoginVO;
import uz.jl.back.vo.auth.AuthUserCreateVO;
import uz.jl.back.vo.auth.AuthUserVO;
import uz.jl.back.vo.response.Data;
import uz.jl.back.vo.response.Response;

import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService instance;
    private UserDao userDao = new UserDao();

    public Response<Data<Long>> create(@NonNull AuthUserCreateVO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(PasswordConfigurer.encode(dto.getPassword()))
                .language(Language.findByName(dto.getLanguage()))
                .build();
        user.setActive(true);
        user.setRole(Role.USER);
        return new Response<>(new Data<>(userDao.save(user)));
    }

    public Response<Data<User>> get(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isEmpty())
            return new Response<>(new Data<>(
                    Data.errorBuilder()
                            .friendlyMessage("User not found")
                            .code(404).build()));

        return new Response<>(new Data<>(userOptional.get()));
    }

    public Response<Data<List<User>>> getAll() {
        List<User> users = userDao.getAll();
        return new Response<>(new Data<>(users, users.size()));
    }


    public Response<Data<AuthUserVO>> login(AuthLoginVO vo) {
        Optional<User> userOptional = userDao.findByUsername(vo.getLogin());
        if (userOptional.isEmpty()) {
            log.info("User not found");
            return new Response<>(new Data<>(Data.errorBuilder()
                    .friendlyMessage("Bad credentials")
                    .developerMessage("User not found by username '%s'".formatted(vo.getLogin()))
                    .code(404)
                    .build()
            ));
        }
        User user = userOptional.get();
        if (!PasswordConfigurer.matchPassword(vo.getPassword(), user.getPassword())) {
            log.info("Bad credentials");
            return new Response<>(new Data<>(Data.errorBuilder()
                    .friendlyMessage("Bad credentials")
                    .code(404)
                    .build()));
        }
        if (!user.isActive()) {
            log.info("User not active");
            return new Response<>(new Data<>(Data.errorBuilder()
                    .friendlyMessage("User not active")
                    .code(404)
                    .build()
            ));
        }
        return new Response<>(new Data<>(AuthUserVO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .language(user.getLanguage())
                .role(user.getRole())
                .build()));
    }

    public void persist() {
        userDao.persist();
    }


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
}
