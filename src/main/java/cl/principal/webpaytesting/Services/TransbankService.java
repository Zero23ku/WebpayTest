package cl.principal.webpaytesting.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cl.transbank.webpay.Webpay;
import cl.transbank.webpay.WebpayNormal;
import cl.transbank.webpay.configuration.Configuration;
import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;

@Service
public class TransbankService {

    public Map<String,String> testService(){
        Map<String,String> test = new HashMap<>();
        test.put("value","hola mundo");
        return test;
    }

    public void testTransbank() throws Exception {
        Configuration configuration = Configuration.forTestingWebpayPlusNormal();
        WebpayNormal transaction = new Webpay(configuration).getNormalTransaction();

        double amount = 1000;
        String sessionId = "mi-id-de-sesion";
        String buyOrder = String.valueOf(Math.abs(new Random().nextLong()));
        String returnURL = "https://callback/resultado/de/transaccion";
        String finalURL = "https://callback/final/post/comprobante/webpay";
        WsInitTransactionOutput initResult = transaction.initTransaction(
                amount,sessionId,buyOrder,returnURL,finalURL
        );

        String formAction = initResult.getUrl();
        String tokenWs = initResult.getToken();
    }
}
