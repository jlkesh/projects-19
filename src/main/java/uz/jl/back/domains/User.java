package uz.jl.back.domains;

import lombok.*;
import uz.jl.back.enums.Language;
import uz.jl.back.enums.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private long id;
    private String username;
    private String password;
    private Language language;
    private Role role;
    private boolean active;
}
