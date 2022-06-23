package uz.jl.back.vo.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.jl.back.enums.Language;
import uz.jl.back.enums.Role;


@Getter
@Setter
@Builder
public class AuthUserVO {
    private long id;
    private String username;
    private Language language;
    private Role role;
}
