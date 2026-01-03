package com.restapi.swagger.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/bukankahIniMy")
    public String swaggerRedirect() {
        return "BUKAN, BUKAN PUNYA LU, TAPI PUNYA PADUKA YUDIS";
    }
}
