package ir.aliloc.api.core.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.core.models.init.VerifyCodeModel;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotAcceptableException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 6/17/2018
 * mostafa.mashayekhi9@gmail.com
 */
@Service
public class GlobalService {

    public static final Map<Integer, String> userRoles = createMap();
    private static Map<Integer, String> createMap() {
        Map<Integer, String> userRoles = new HashMap<Integer, String>();
        userRoles.put(0, GlobalConstant.ALL);
        userRoles.put(1, GlobalConstant.USER);
        userRoles.put(2, GlobalConstant.ADMIN);
        return Collections.unmodifiableMap(userRoles);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(String.valueOf(username))
                .setExpiration(new Date(System.currentTimeMillis() + GlobalConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, GlobalConstant.SECRET_KEY.getBytes())
                .compact();
    }

    public String generatePassword(long time, long userId) throws NoSuchAlgorithmException {
        String uuid = UUID.randomUUID().toString();
        String str = String.valueOf(time) + String.valueOf(userId) + uuid;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());
        return bytesToHex(md.digest());
    }

    public String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }


    public boolean checkMobileFormat(String mobile) throws Exception {
        String regexStr = "^[0-9]*$";
        if (mobile.matches(regexStr) && mobile.length() == 14) {
            return true;
        } else {
            throw new NotAcceptableException("error.mobile.format");
        }
    }

    public VerifyCodeModel generateVerificationCode() {
        Random random = new Random();
        String randNum = random.nextInt(90000) + 10000 + "";
        String tmp = "";
        String str = "";
        for (int i = 0; i < randNum.length(); i++) {
            str += randNum.charAt(i);
            tmp += "%DB%B" + randNum.charAt(i);
        }
        long oneHour = 60 * 60 * 1000;
        VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
        verifyCodeModel.setCodeInt(Integer.parseInt(str));
        verifyCodeModel.setCodeStr(tmp);
        return verifyCodeModel;
    }

    public String stringDateChecker(String date) {
        if (date != null && !date.isEmpty()) {
            String[] strChar = date.split("-");
            if (strChar.length == 3) {
                StringBuilder builder = new StringBuilder();
                builder.append(String.format(Locale.getDefault(), "%04d", Integer.parseInt(strChar[0])));
                builder.append("-");
                builder.append(String.format(Locale.getDefault(), "%02d", Integer.parseInt(strChar[1])));
                builder.append("-");
                builder.append(String.format(Locale.getDefault(), "%02d", Integer.parseInt(strChar[2])));
                return builder.toString();
            }
        }
        return date;
    }

    public String[] splitString(String text){
        return text.split("[ ]+");
    }

}
