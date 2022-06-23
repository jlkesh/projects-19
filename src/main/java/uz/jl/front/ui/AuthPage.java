package uz.jl.front.ui;

import com.google.gson.Gson;
import uz.jl.back.config.UserSession;
import uz.jl.back.service.UserService;
import uz.jl.back.vo.auth.AuthLoginVO;
import uz.jl.back.vo.auth.AuthUserCreateVO;
import uz.jl.back.vo.auth.AuthUserVO;
import uz.jl.back.vo.response.Data;
import uz.jl.back.vo.response.Response;
import uz.jl.front.utils.Color;
import uz.jl.front.utils.Reader;
import uz.jl.front.utils.Writer;

public class AuthPage {
    final static UserService service = UserService.getInstance();
    final static Gson gson = new Gson();


    public static void menu() {
    }

    public void logout() {
        Writer.println("Successfully logged out");
        UserSession.getInstance().setUserVO(null);
    }

    public void register() {
        AuthUserCreateVO createVO = AuthUserCreateVO.builder()
                .username(Reader.readLine("username : "))
                .password(Reader.readLine("password : "))
                .language(Reader.readLine("language : "))
                .build();
        Response<Data<Long>> dataResponse = service.create(createVO);
        Data<Long> data = dataResponse.getData();
        showResponse(data);
    }

    public void login() {
        AuthLoginVO vo = AuthLoginVO.builder().login(Reader.readLine("Login : ")).password(Reader.readLine("Password : ")).build();
        Response<Data<AuthUserVO>> response = service.login(vo);
        Data<AuthUserVO> responseData = response.getData();
        UserSession.getInstance().setUserVO(responseData.getBody());
        showResponse(responseData);
        menu();
    }

    public static void showResponse(Data<?> data) {
        if (!data.isOk()) Writer.println(gson.toJson(data.getError()), Color.RED);
        else {
            Writer.println(gson.toJson(data.getBody()), Color.GREEN);
        }
    }
}
