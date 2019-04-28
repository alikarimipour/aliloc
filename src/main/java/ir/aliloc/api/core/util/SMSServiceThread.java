/**
 * 8/25/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.util;


import ir.aliloc.api.core.models.init.VerifyCodeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SMSServiceThread implements Runnable {

    private String mobile;
    private VerifyCodeModel mVerifyCodeModel;

    public void setVerifyCodeModel(VerifyCodeModel verifyCodeModel) {
        mVerifyCodeModel = verifyCodeModel;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Autowired
    private SMSService mSMSVoiceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SMSServiceThread.class);

    @Override
    public void run() {
        try {
            LOGGER.info("called from thread");
            if (mobile != null && mVerifyCodeModel != null) {
                mSMSVoiceService.sendAuthenticationSMS(mobile, mVerifyCodeModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
