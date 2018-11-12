package cl.principal.webpaytesting.Controllers;

import cl.principal.webpaytesting.Services.TransbankService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;


@RestController
public class PetitionController {

    TransbankService transbankService;

    PetitionController(TransbankService transbankService){
        this.transbankService = transbankService;
    }

    @GetMapping("/test")
    public Map<String,String> getTest(){
        return transbankService.testService();
    }

    @PostMapping("/transactionTest")
    public void transactionTest() throws Exception {
       this.transbankService.testTransbank();
    }
}
