package uz.jl.dto.user;

import lombok.*;
import uz.jl.enums.Language;

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
