package uz.jl.mapper.user;

import uz.jl.config.PasswordConfigurer;
import uz.jl.domains.User;
import uz.jl.dto.user.UserCreateDTO;
import uz.jl.enums.Language;

public class UserMapper {


    public User fromCreateDto(UserCreateDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(PasswordConfigurer.encode(dto.getPassword()))
                .language(Language.findByName(dto.getLanguage()))
                .build();
    }

}
