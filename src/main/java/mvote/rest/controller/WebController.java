package mvote.rest.controller;

import mvote.rest.database_service.AdminService;
import mvote.rest.database_service.CalonService;
import mvote.rest.database_service.SuaraService;
import mvote.rest.database_service.UserService;
import mvote.rest.library.BCrypt;
import mvote.rest.model.AdminModel;
import mvote.rest.model.CalonModel;
import mvote.rest.model.HitungSuaraCalonModel;
import mvote.rest.model.SuaraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by wahyuade on 16/07/17.
 */
@Controller
public class WebController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private SuaraService suaraService;

    @Autowired
    private UserService userService;

    @Autowired
    private CalonService calonService;

    @GetMapping("/")
    public String home(){
        return "redirect:/hasil";
    }


    @GetMapping("/hasil")
    public String index(Model model){
        ArrayList<AdminModel> data_admin = adminService.findByStatusLogin("1");
        if(adminService.count() != data_admin.size()){
            model.addAttribute("useryangtelahlogin", data_admin);
            model.addAttribute("adminmodel", new AdminModel());
            return "login";
        }else{

            ArrayList<SuaraModel> data_suara = suaraService.findAll();
            ArrayList<CalonModel> data_calon = calonService.findAll();
            ArrayList<HitungSuaraCalonModel> data_hitung = new ArrayList<>();
            for(int i=0;i<data_calon.size();i++){
                data_hitung.add(new HitungSuaraCalonModel(data_calon.get(i).getId(), data_calon.get(i).getNama(),data_calon.get(i).getFoto()));
            }

            for(int i=0;i<data_suara.size();i++){
                for(int j = 0 ;j< data_calon.size();j++){
                    if(BCrypt.checkpw(data_hitung.get(j).getId(), data_suara.get(i).getIdCalon())){
                        data_hitung.get(j).tambahSuara();
                    }
                }
            }

            model.addAttribute("totalsuara", userService.countByWaktuLoginNotNull());
            model.addAttribute("data_hitung", data_hitung);
            model.addAttribute("suarasah", suaraService.count());
            model.addAttribute("suaraabstain", userService.countByWaktuLoginNotNull() - suaraService.count());

            return "index";
        }
    }

    @PostMapping("/hasil")
    public String postLoginAdmin(@ModelAttribute AdminModel adminmodel){
        AdminModel adminData = adminService.findByUsername(adminmodel.getUsername());
        if(adminData != null){
            if(adminData.getPassword().equals(adminmodel.getPassword())){
                adminData.setStatusLogin("1");
                adminService.save(adminData);
                return "redirect:/hasil";
            }else{
                return "redirect:/hasil";
            }
        }else{
            return "redirect:/hasil";
        }
    }
}
