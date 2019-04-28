/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class MultiMediaService implements IMultiMediaService{

    @Autowired
    private IMultiMediaDAO mIMultiMediaDAO;

    @Override
    public MultiMedia addProfilePic(String url) throws Exception {
        MultiMedia multiMedia = new MultiMedia();
        multiMedia.setMime(MimeType.IMAGE);
        multiMedia.setUrl(url);
        return mIMultiMediaDAO.addProfile(multiMedia);
    }
}
