package cl.principal.webpaytesting.Controllers;


import cl.principal.webpaytesting.Services.TransbankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

    TransbankService transbankService;
    WebController(TransbankService transbankService){
        this.transbankService = transbankService;
    }

    @RequestMapping("/")
    public String mainPage(Model model){
        return "index";
    }

    @RequestMapping(value = "/transbankFinal", method = { RequestMethod.GET, RequestMethod.POST})
    public ModelAndView finalPage(@RequestParam(value = "token_ws", required = false) String tokenWs,
                                  @RequestParam(value = "TBK_TOKEN", required = false) String tbkToken,
                                  Model model) throws Exception{
        return transbankService.finalVoucher(tokenWs,tbkToken);
    }
}