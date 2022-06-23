package uz.jl.back.vo.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.jl.back.enums.Language;

@Getter
@Setter
@Builder
public class AuthUserCreateVO {
    private String username;
    private String password;
    private String language;
}
