/**
 * 6/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.util;


import io.minio.MinioClient;
import io.minio.errors.*;
import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.core.models.response.UploadTokenResponse;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MinioClientGenerator {

    private static MinioClientGenerator instance;
    private static MinioClient sMinioClient;
    private String bucketName;

    private String policy = "{\n" +
            "  \"Statement\": [  \n" +
            "    {\n" +
            "      \"Action\": \"s3:GetObject\",\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Principal\": \"*\",\n" +
            "      \"Resource\": \"arn:aws:s3:::motahari-profile/*\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"Version\": \"2012-10-17\"\n" +
            "}";

    public static MinioClientGenerator getInstance() throws Exception {
        if (instance == null) {
            instance = new MinioClientGenerator();
        }
        return instance;
    }

    private MinioClientGenerator() throws Exception {
        sMinioClient = new MinioClient(GlobalConstant.UPLOAD_URL,
                GlobalConstant.UPLOAD_ACCESS_KEY,
                GlobalConstant.UPLOAD_SECRET_KEY);
    }

    public void setPolicy() throws Exception {
        boolean isExist = getMinioClient().bucketExists(GlobalConstant.UPLOAD_PROFILE_BUCKET);
        if (!isExist) {
            getMinioClient().makeBucket(GlobalConstant.UPLOAD_PROFILE_BUCKET);
        }
        getMinioClient().setBucketPolicy(GlobalConstant.UPLOAD_PROFILE_BUCKET,policy);
    }

    public MinioClient getMinioClient() {
        return sMinioClient;
    }

    public UploadTokenResponse getMinioPreSigned() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidExpiresRangeException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        String uuidFileName = UUID.randomUUID().toString().replace("-", "");
        UploadTokenResponse uploadTokenResponse = new UploadTokenResponse();
        uploadTokenResponse.setUploadToken(sMinioClient.presignedPutObject(GlobalConstant.UPLOAD_PROFILE_BUCKET, uuidFileName, GlobalConstant.UPLOAD_EXPIRE_TIME));
        uploadTokenResponse.setFileName(uuidFileName);
        return uploadTokenResponse;
    }
}
