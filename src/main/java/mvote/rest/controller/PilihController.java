package mvote.rest.controller;

import mvote.rest.database_service.CalonService;
import mvote.rest.database_service.SuaraService;
import mvote.rest.database_service.UserService;
import mvote.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
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
    public ArrayList<CalonModel> getListCalon(@RequestParam String local){
        if(userService.findUserModelByLocal(local) != null){
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

    @PostMapping("/vote")
    public DefaultModel postPilihCalon(@RequestPart String local, @RequestPart String id_calon){
        if(userService.findUserModelByLocal(local) != null){
            if(userService.findUserModelByLocal(local).getStatusPilih().equals("0")){
                // apabila token sudah melebihi 5 menit user tidak bisa memilih
                if((new Date().getTime() - userService.findUserModelByLocal(local).getWaktuLogin())>= 300000){
                    return new DefaultModel(false, "Token telah kadaluarsa");
                }else{
                    //token belum melebihi 5 menit maka user bisa memilih
                    UserModel update_status_pilih_user = userService.findUserModelByLocal(local);
                    update_status_pilih_user.setStatusPilih("1");
                    userService.save(update_status_pilih_user);
                    String uuid = UUID.randomUUID().toString();

                    SuaraModel suara = new SuaraModel(id_calon, uuid);
                    suaraService.save(suara);

                    return new DefaultModel(true, "Berhasil vote");
                }

            }else{
                return new DefaultModel(false, "Token telah kadaluarsa");
            }
        }else{
            return new DefaultModel(false, "Token telah kadaluarsa");
        }
    }
}
