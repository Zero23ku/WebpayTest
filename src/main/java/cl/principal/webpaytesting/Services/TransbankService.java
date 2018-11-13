package cl.principal.webpaytesting.Services;

import cl.principal.webpaytesting.config.ApplicationProperties;
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
import org.springframework.web.servlet.ModelAndView;

@Service
public class TransbankService {

    private Configuration configuration;
    private WebpayNormal transaction;
    private ApplicationProperties applicationProperties;



    TransbankService(ApplicationProperties applicationProperties) throws  Exception{
        this.configuration = Configuration.forTestingWebpayPlusNormal();
        this.transaction = new Webpay(configuration).getNormalTransaction();
        this.applicationProperties = applicationProperties;
    }

    public Map<String,String> testTransbank(Double amount) throws Exception {
        String port = applicationProperties.getPort();
        String sessionId = "mi-id-de-sesion";
        String buyOrder = String.valueOf(Math.abs(new Random().nextLong()));
        String returnURL = "http://localhost:" + port + "/transbankResponse";
        String finalURL = "http://localhost:" + port + "/transbankFinal";
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

    public ModelAndView transbankCallback(String tokenWs) throws  Exception{
        ModelAndView mav = new ModelAndView();
        TransactionResultOutput result = transaction.getTransactionResult(tokenWs);
        //transaction.acknowledgeTransaction(tokenWs);
        WsTransactionDetailOutput output = result.getDetailOutput().get(0);
        if(output.getResponseCode() == 0){
            //Acá se puede procesar la transaccion por lado nuestro
            mav.setViewName("inter");
            mav.addObject("token_ws",tokenWs);
            mav.addObject("redirectURL",result.getUrlRedirection());
        }else{
            //En caso de error, RIP
            mav.setViewName("error");
        }
        return mav;
    }

    public ModelAndView finalVoucher(String tokenWs, String tbkToken) throws  Exception{
        ModelAndView mav = new ModelAndView();
        if(tokenWs != null) {
            //Hacer algo aqui, mostrar un voucher o algo asi
            mav.setViewName("final");
        }else if(tbkToken != null){
            try {
                TransactionResultOutput result = transaction.getTransactionResult(tbkToken);
            }catch (Exception e) {
                mav.setViewName("anulado");
            }
        }
        return mav;
    }
}