/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
class MultiMediaService implements IMultiMediaService{

    @Autowired
    private IMultiMediaDAO mIMultiMediaDAO;

    @Autowired
    private ModelMapper mModelMapper;

    @Override
    public MultiMediaDTO addMultiMedia(MultipartFile file) throws Exception {
        MultiMedia multiMedia = new MultiMedia();
        multiMedia.setMime(MimeType.IMAGE);
        multiMedia.setFile(file.getBytes());
        return mModelMapper.map(mIMultiMediaDAO.addMultimedia(multiMedia), MultiMediaDTO.class);
    }

    @Override
    public MultiMedia getMainMultiMediaById(long id) throws Exception {
        return mIMultiMediaDAO.getMultimediabyId(id);
    }
}
