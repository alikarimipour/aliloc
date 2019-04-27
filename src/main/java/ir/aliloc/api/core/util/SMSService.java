package ir.aliloc.api.core.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.VerifyCodeModel;
import ir.aliloc.api.core.models.response.SMSModel;
import ir.aliloc.api.core.models.response.SMSModelParent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotAcceptableException;
import java.io.IOException;

@Service
@Transactional
public class SMSService {

    @Autowired
    private GlobalService mGlobalService;


    public SMSModel sendAuthenticationSMS(String mobile, VerifyCodeModel verificationCode) throws Exception {
        return sendRequest(mobile, verificationCode.getCodeInt());
    }

    private SMSModel sendRequest(String mobile, int verificationCode) throws Exception {
        mGlobalService.checkMobileFormat(mobile);
        String message = GlobalConstant.SMS_VERIFICATION_CODE + verificationCode;
        String finalMessage = relaxYaLetters(message);
        return sendMessage(finalMessage, mobile);
    }

    private static String relaxYaLetters(String token) {
        token = token.replace('\u0620', '\u06CC');
        token = token.replace('\u0626', '\u06CC');
        token = token.replace('\u063D', '\u06CC');
        token = token.replace('\u063E', '\u06CC');
        token = token.replace('\u063F', '\u06CC');
        token = token.replace('\u0649', '\u06CC');
        token = token.replace('\u064A', '\u06CC');
        token = token.replace('\u06CD', '\u06CC');
        token = token.replace('\u06CE', '\u06CC');
        token = token.replace('\u06D0', '\u06CC');
        token = token.replace('\u06D1', '\u06CC');
        token = token.replace('\u0775', '\u06CC');
        token = token.replace('\u0776', '\u06CC');
        token = token.replace('\u0777', '\u06CC');
        return token;
    }

    public SMSModel sendMessage(String message, String mobile) throws Exception {
        SMSModel smsModel = new SMSModel();
        if (callServiceSendSMS(message, mobile)) {
            smsModel.setStatus(200);
            return smsModel;
        } else {
            throw new NotAcceptableException(MessageConstant.ERROR_SEND_SMS);
        }
    }

    private boolean callServiceSendSMS(String message, String mobile) throws IOException {
        String url = GlobalConstant.SMS_URL + "?receptor=" + mobile + "&message=" + message;
        OkHttpClient client = HttpHelperService.getInstance().getClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        SMSModelParent smsModelParent = gson.fromJson(res, SMSModelParent.class);
        return smsModelParent.getReturnSMS().getStatus() == 200;
    }
}

