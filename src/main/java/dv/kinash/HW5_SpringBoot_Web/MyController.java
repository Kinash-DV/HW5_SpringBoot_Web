package dv.kinash.HW5_SpringBoot_Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @RequestMapping("/weather")
    public String weather(Model model,
                          @RequestParam(required = false) String degree,
                          @RequestParam(required = false) String color){
        model.addAttribute("degrees", degree);
        model.addAttribute("color", color);
        return "weather.html";
    }
}
