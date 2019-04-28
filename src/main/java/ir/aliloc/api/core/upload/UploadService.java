/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.upload;


import ir.aliloc.api.core.models.response.UploadTokenResponse;
import ir.aliloc.api.core.util.MinioClientGenerator;
import org.springframework.stereotype.Service;

@Service
class UploadService implements IUploadService {

    @Override
    public UploadTokenResponse getUploadToken() throws Exception {
        MinioClientGenerator minioClientGenerator = MinioClientGenerator.getInstance();
        minioClientGenerator.setPolicy();
        return minioClientGenerator.getMinioPreSigned();
    }
}
