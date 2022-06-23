package uz.jl.back.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCreateDTO {
    private String username;
    private String password;
    private String language;
}
