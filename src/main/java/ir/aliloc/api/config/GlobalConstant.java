/**
 * 3/10/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.config;

public class GlobalConstant {

    public static final String APP_VERSION = "0.0.1";
    public static final String SECRET_KEY = "DhW5C=sbNzQQSEt5TaU+dSBucSRYehvJ6V#ehVx=J#yM2Wc5UbVC63!L8$^jxBy6LVfF5RsX-yW&GN%j_6QmfRxY7?3cmZKK^*g$yUexHvYxrNU!KU6xe$ny";
    public static final String SMS_VERIFICATION_CODE="به جمع کاربران اپلیکیشن استاد مطهری خوش آمدید. کد فعال سازی شما :";


    public static final int AUTH_ID_NUMBER = 1500;//generate auth Id with (* or /)
//    public static final long EXPIRATION_TIME = 86400000; // one day
    public static final long EXPIRATION_TIME = 315360000000L; // one day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "token";
    public static final long EXPIRATION_VERIFY_CODE = 900000L;
    public static final String SMS_URL = "https://api.kavenegar.com/v1/4256452B7A4F7179727A7578546C6D764546576B30594A316865716E7A676D7A/sms/send.json";

    public static final String UPLOAD_URL = "https://box.amnmoj.ir";
    public static final String UPLOAD_ACCESS_KEY = "XAS8PG1BHSATZE09C25C";
    public static final String UPLOAD_SECRET_KEY = "k-6kjgUz7PvpTZ_MdWOkZXw7pIk_mZCsY1wxzuLC";
    public static final String UPLOAD_PROFILE_BUCKET = "motahari-profile";
    public static final int UPLOAD_EXPIRE_TIME = 60 * 60 * 24;

    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String ALL = "ROLE_ALL";

    public static final String FLIGHT_MAIN_URL = "http://apitech.ir/gdsApi/";
    public static final String FLIGHT_SEARCH_URL = "SearchFlightApi";
    public static final String FLIGHT_USERNAME = "";
    public static final String FLIGHT_KEY = "";

    public static final String WEATHER_API = "e26a073ab1cc06de6d30b8f3d467ef2c";

    public static final String PUSH_NOTIFICATION_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String PUSH_NOTIFICATION_TOKEN = "AAAABb-Bjuc:APA91bHNO5oJV0L-s-aCJnFpQ0WbNKnVTifhxWJkmYUdG0mnxhlTnPJ13bmSfeyvh5frqlvo1RMNP1e54-yrwd2XN6bv_eRVLJs9Sp7WzXJ_PQ6lcqWUFklgehF2u2tpZNf4qr74_z5O";
    public static final String NOTIFICATION_TO_ALL = "/topics/default";

    public static final String NOTIFICATION_MAJOR_SETTING = "SETTING";
    public static final String NOTIFICATION_MINOR_TYPE_NEW_VERSION = "NEW_VERSION";
    public static final String NOTIFICATION_MINOR_CHECK_VERSION ="CHECK_VERSION";

    public static final int VOUCHER_LENGTH = 8;

    private static final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiItMTAwMCIsImV4cCI6MTg1NzkwMjE3Mn0.A7iW9-MZaOClb5u1ANN93UGyNS_ucC5Io9i2Q1BIsvw1fY6Wictt_D3_BGsgm44Jkws1CtH3PjDxYXNIgO84GA";
    public static final String MERID = "577bb2ae-fa83-11e7-8291-000c295eb8fc";
    public static final String PAY_URL = "https://www.zarinpal.com/pg/StartPay/";
    public static final String ZARINPAL_URL_CALL_BACK = "http://srv1.motahari.ir:8081/motahari/payment/call-back";
}

