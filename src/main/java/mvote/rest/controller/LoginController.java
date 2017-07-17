package mvote.rest.controller;

import mvote.rest.database_service.UserService;
import mvote.rest.model.LoginModel;
import mvote.rest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        UserModel dataLogin = userService.findUserModelByNrp(nrp);
        if(dataLogin == null){
            return new LoginModel(false, "nrp atau token tidak cocok", null);
        }else{
            if(dataLogin.getToken().equals(token)){
                if(dataLogin.getStatusToken().equals("1")){
                    // apabila token sudah melebihi 5 menit user tidak bisa memilih
                    if((new Date().getTime() - dataLogin.getWaktuLogin())>= 300000){
                        return new LoginModel(false, "Token telah kadaluarsa", null);
                    }else{
                        // apabila token belum melebihi 5 menit namun user sudah memilih maka user tidak bisa memilih
                        if(dataLogin.getStatusPilih().equals("1")){
                            return new LoginModel(false, "Token telah kadaluarsa", null);
                        }else{
                            return new LoginModel(true, "Berhasil login", dataLogin.getLocal());
                        }
                    }
                }else{
                    UserModel update_status_saat_login = userService.findUserModelByNrp(nrp);
                    update_status_saat_login.setStatusToken("1");
                    update_status_saat_login.setStatusPilih("0");
                    update_status_saat_login.setWaktuLogin(new Date().getTime());
                    userService.save(update_status_saat_login);
                    return new LoginModel(true, "Berhasil login", dataLogin.getLocal());
                }
            }else{
                return new LoginModel(false, "nrp atau token tidak cocok", null);
            }
        }
    }
}
