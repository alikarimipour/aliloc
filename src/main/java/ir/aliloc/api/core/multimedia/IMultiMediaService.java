/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import org.springframework.web.multipart.MultipartFile;

public interface IMultiMediaService {

    MultiMediaDTO addMultiMedia(MultipartFile file) throws Exception;

    MultiMedia getMainMultiMediaById(long id) throws Exception;
}
