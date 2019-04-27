/**
 * 26/1/2019
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.payment.zarinpal;

import ir.aliloc.api.config.GlobalConstant;

import javax.xml.ws.Holder;
import java.rmi.RemoteException;

public class TestPayment {

    public static void main(String[] args) throws RemoteException {
        System.setProperty("socksProxyHost","127.0.0.1");
        System.setProperty("socksProxyPort","45");
        Holder<String> authority = new Holder<>();
        Holder<Long> refnum = new Holder<>();
        Holder<Integer> status = new Holder<>();
        PaymentGatewayImplementationService paymentGatewayImplementationService = new PaymentGatewayImplementationService();
        PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort = paymentGatewayImplementationService.getPaymentGatewayImplementationServicePort();
        paymentStep1(paymentGatewayImplementationServicePort, GlobalConstant.MERID, 1000, "alikarimipout157@gmail.com", "alikarimipout157@gmail.com", "989367384133", "www.google.com", status, authority);
        paymentStep2(refnum, status, paymentGatewayImplementationServicePort, "577bb2ae-fa83-11e7-8291-000c295eb8fc", "000000000000000000000000000100976047", 1000);

        System.out.println();
    }


    private static void paymentStep1(PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort, String merchantID,
                                     int amount,
                                     String description,
                                     String email,
                                     String mobile,
                                     String callbackURL,
                                     Holder<Integer> status,
                                     Holder<String> authority) {
        //test payment step 1
        paymentGatewayImplementationServicePort.paymentRequest(merchantID,amount,description,email,mobile,callbackURL,status,authority);
        System.out.println(status.value);
        System.out.println(authority.value);
        System.out.println("salala");
    }

    private static void paymentStep2(Holder<Long> refnum,
                                     Holder<Integer> status,
                                     PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort,
                                     String merchantID,
                                     String authority,
                                     int amount) {
        //test payment step 2
        paymentGatewayImplementationServicePort.paymentVerification(merchantID, authority, amount, status, refnum);

        System.out.println(status.value);
        System.out.println(refnum.value);
    }


}
