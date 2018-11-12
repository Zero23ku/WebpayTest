package cl.principal.webpaytesting.Controllers;

import cl.principal.webpaytesting.Services.TransbankService;


import com.transbank.webpay.wswebpay.service.TransactionResultOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String,String> transactionTest() throws Exception {
       return transbankService.testTransbank();
    }

    @PostMapping("/transbankResponse")
    public void transbankResponse(@RequestParam("token_ws") String tokenWs) throws  Exception{
        transbankService.transbankCallback(tokenWs);
    }

    @PostMapping("/transbankFinal")
    public String transbankFinal(@RequestParam("token_ws") String tokenWS){
        return tokenWS;
    }
}
