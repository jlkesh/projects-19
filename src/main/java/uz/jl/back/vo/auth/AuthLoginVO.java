package uz.jl.back.vo.auth;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginVO {
    private String login;
    private String password;
}
