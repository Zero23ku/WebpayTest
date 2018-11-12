package cl.principal.webpaytesting.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class WebController {

    @RequestMapping("/")
    public String mainPage(Model model) throws IOException {
        return "index";
    }
}
