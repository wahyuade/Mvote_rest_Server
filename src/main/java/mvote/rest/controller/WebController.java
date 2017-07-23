package mvote.rest.controller;

import mvote.rest.database_service.AdminService;
import mvote.rest.database_service.SuaraService;
import mvote.rest.database_service.UserService;
import mvote.rest.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/hasil")
    public String index(Model model){
        ArrayList<AdminModel> data_admin = adminService.findByStatusLogin("1");
        if(adminService.count() != data_admin.size()){
            model.addAttribute("useryangtelahlogin", data_admin);
            model.addAttribute("adminmodel", new AdminModel());
            return "login";
        }else{

            model.addAttribute("totalsuara", userService.countByWaktuLoginNotNull());
            model.addAttribute("urut1", suaraService.countByIdCalon("1"));
            model.addAttribute("urut2", suaraService.countByIdCalon("2"));
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
