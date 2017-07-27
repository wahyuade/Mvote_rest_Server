package mvote.rest.controller;

import mvote.rest.database_service.CalonService;
import mvote.rest.database_service.SuaraService;
import mvote.rest.database_service.UserService;
import mvote.rest.library.BCrypt;
import mvote.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by wahyuade on 16/07/17.
 */
@RestController
public class PilihController {
    @Autowired
    private CalonService calonService;

    @Autowired
    private UserService userService;

    @Autowired
    private SuaraService suaraService;

    @GetMapping("/list_calon")
    public ArrayList<CalonModel> getListCalon(@RequestParam String value_n){
        if(userService.findUserModelByN(value_n) != null){
            ArrayList<CalonModel> data_calon = new ArrayList<>();
            Iterator<CalonModel> iterator = calonService.findAll().iterator();
            while (iterator.hasNext()){
                data_calon.add(iterator.next());
            }
            return data_calon;
        }else {
            return null;
        }
    }

    @PostMapping("/check_m")
    public ChallangeModel postPilihCalon(@RequestParam String value_n, @RequestParam String x, @RequestPart String h){
        boolean challange = false;
        String message = "Invalid hash String";
        UserModel data_login = userService.findUserModelByN(value_n);
        for(int i = 1 ; i<=calonService.count(); i++){
            if(BCrypt.checkpw(Integer.toString(i), h)){
                challange = true;
                data_login.setX(x);
                userService.save(data_login);

                message = "Success Verified hash String";
            }
        }
        if(challange){
            return new ChallangeModel(challange, message, Math.round(Math.random()));
        }else{
            return new ChallangeModel(challange, message, null);
        }

    }

    @PostMapping("/vote_validate")
    public DefaultModel postVoteValidate(@RequestPart String nrp, @RequestPart String y_value, @RequestPart String h_value, @RequestPart String c_value){
        BigInteger v = new BigInteger(userService.findUserModelByNrp(nrp).getV());
        BigInteger n = new BigInteger(userService.findUserModelByNrp(nrp).getN());
        BigInteger x = new BigInteger(userService.findUserModelByNrp(nrp).getX());
        BigInteger y = new BigInteger(y_value);
        Integer c = new Integer(c_value);

        boolean challange = false;
        String id_calon = new String();

        Iterator<CalonModel> iterator = calonService.findAll().iterator();

        for(int i = 1 ; i<=calonService.count(); i++){
            if(BCrypt.checkpw(Integer.toString(i), h_value)){
                id_calon = Integer.toString(i);
                challange = true;
            }
        }
        System.out.println("value of x :"+x);
        System.out.println("value of y :"+y);
        System.out.println("value of n :"+n);
        System.out.println("value of v :"+v);
        System.out.println("value of c :"+c);

        if(((x.multiply(v.pow(c))).mod(n).equals((y.multiply(y)).mod(n))) && challange){

            UserModel data_user = userService.findUserModelByNrp(nrp);
            Long end_time = data_user.getWaktuLogin() - 180000;
            data_user.setWaktuLogin(end_time);
            userService.save(data_user);

            String uuid = UUID.randomUUID().toString();

            SuaraModel suara = new SuaraModel(h_value, uuid);
            suaraService.save(suara);

            return new DefaultModel(true,"Selamat Anda berhasil memilih");
        }else{
            return new DefaultModel(false, "Mohon maaf terjadi kesalahan, Vote di tolak !");
        }
    }
}
