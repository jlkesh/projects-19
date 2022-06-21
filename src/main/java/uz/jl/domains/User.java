package uz.jl.domains;

import lombok.*;
import uz.jl.enums.Language;
import uz.jl.enums.Role;

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
