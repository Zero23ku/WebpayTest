package cl.principal.webpaytesting.Controllers;

import cl.principal.webpaytesting.Services.TransbankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class PetitionController {

    @Autowired
    TransbankService transbankService;


    @PostMapping("/transactionTest")
    public Map<String,String> transactionTest(@RequestBody Map<String,String> request) throws Exception {
       return transbankService.testTransbank(Double.parseDouble(request.get("amount")));
    }

    @PostMapping("/transbankResponse")
    public ModelAndView transbankResponse(@RequestParam("token_ws") String tokenWs) throws  Exception {
        return transbankService.transbankCallback(tokenWs);
    }

}