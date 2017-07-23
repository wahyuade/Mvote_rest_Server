package mvote.rest.controller;

import mvote.rest.database_service.UserService;
import mvote.rest.model.LoginModel;
import mvote.rest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by wahyuade on 15/07/17.
 */

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginModel dopost(@RequestPart String nrp, @RequestPart String token){
        int before_time = 23;
        int after_time = 07;
        int now = LocalDateTime.now().getHour();
        if(now > after_time && now < before_time){
            UserModel dataLogin = userService.findUserModelByNrp(nrp);
            if(dataLogin == null){
                return new LoginModel(false, "nrp atau token tidak cocok");
            }else{
                if(dataLogin.getToken().equals(token)){
                    if(dataLogin.getWaktuLogin() == null){
                        UserModel update_status_saat_login = userService.findUserModelByNrp(nrp);
                        update_status_saat_login.setWaktuLogin(new Date().getTime());
                        userService.save(update_status_saat_login);
                        return new LoginModel(true, "Berhasil login");
                    }else{
                        //apabila token telah melebihi 3 menit maka login ditolak
                        if((new Date().getTime() - dataLogin.getWaktuLogin())>= 180000){
                            return new LoginModel(false, "Token telah kadaluarsa");
                        }else{
                            return new LoginModel(true, "Berhasil login");
                        }
                    }
                }
                else{
                    return new LoginModel(false, "nrp atau token tidak cocok");
                }
            }
        }else{
            return new LoginModel(false, "Vote belum dimulai");
        }
    }
}
