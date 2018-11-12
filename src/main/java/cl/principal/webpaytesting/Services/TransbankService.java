package cl.principal.webpaytesting.Services;

import com.transbank.webpay.wswebpay.service.TransactionResultOutput;
import com.transbank.webpay.wswebpay.service.WsTransactionDetailOutput;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cl.transbank.webpay.Webpay;
import cl.transbank.webpay.WebpayNormal;
import cl.transbank.webpay.configuration.Configuration;
import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;

@Service
public class TransbankService {

    Configuration configuration;
    WebpayNormal transaction;

    TransbankService() throws  Exception{
        this.configuration = Configuration.forTestingWebpayPlusNormal();
        this.transaction = new Webpay(configuration).getNormalTransaction();
    }



    public Map<String,String> testService(){
        Map<String,String> test = new HashMap<>();
        test.put("value","hola mundo");
        return test;
    }

    public Map<String,String> testTransbank() throws Exception {

        double amount = 1000;
        String sessionId = "mi-id-de-sesion";
        String buyOrder = String.valueOf(Math.abs(new Random().nextLong()));
        String returnURL = "http://localhost:8080/transbankResponse";
        String finalURL = "http://localhost:8080/transbankFinal";
        WsInitTransactionOutput initResult = transaction.initTransaction(
                amount,sessionId,buyOrder,returnURL,finalURL
        );

        String formAction = initResult.getUrl();
        String tokenWs = initResult.getToken();
        Map<String,String> result = new HashMap<>();
        result.put("formAction",formAction);
        result.put("tokenWS",tokenWs);
        return result;
    }

    public void transbankCallback(String tokenWs) throws  Exception{
        TransactionResultOutput result = transaction.getTransactionResult(tokenWs);
        WsTransactionDetailOutput output = result.getDetailOutput().get(0);
        if(output.getResponseCode() == 0){

        }else{

        }
    }
}
