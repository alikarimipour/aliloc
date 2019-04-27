/**
 * 11/14/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.upload;


import ir.aliloc.api.core.models.response.UploadTokenResponse;

public interface IUploadService {

    UploadTokenResponse getUploadToken() throws Exception;
}
