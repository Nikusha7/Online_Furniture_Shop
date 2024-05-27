package ge.nika.onlinefurnitureshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/home")
    public String adminPage() {
        System.out.println("admin mainpage was called");
        return "html/admin/admin_panel";
    }


}