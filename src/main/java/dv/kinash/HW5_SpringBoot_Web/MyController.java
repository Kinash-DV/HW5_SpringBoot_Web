package dv.kinash.HW5_SpringBoot_Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @GetMapping("/weather/{color}")
    public String weather(Model model,
                          @RequestParam(required = false) String degree,
                          @PathVariable String color){
        model.addAttribute("degrees", degree);
        model.addAttribute("color", color);
        return "weather.html";
    }
}
