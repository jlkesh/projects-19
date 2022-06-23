package uz.jl.back.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.jl.back.vo.auth.AuthUserVO;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSession {
    private static UserSession instance;

    public static UserSession getInstance() {
        synchronized (UserSession.class) {
            if (instance == null) {
                instance = new UserSession();
            }
        }
        return instance;
    }

    private AuthUserVO userVO;
}
