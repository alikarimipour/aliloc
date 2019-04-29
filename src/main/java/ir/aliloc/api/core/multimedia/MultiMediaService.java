/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import ir.aliloc.api.security.IAuthenticationFaced;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
class MultiMediaService implements IMultiMediaService {

    @Autowired
    private IMultiMediaDAO mIMultiMediaDAO;

    @Autowired
    private ModelMapper mModelMapper;

    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;

    @Override
    public MultiMediaDTO addMultiMedia(MultipartFile file) throws Exception {
        MultiMedia multiMedia = new MultiMedia();
        multiMedia.setMime(MimeType.IMAGE);
        multiMedia.setFile(file.getBytes());
        multiMedia.setName(file.getOriginalFilename());


        String pathFolder = "C://locTemp//" + Long.parseLong(mIAuthenticationFaced.getAuthentication().getName()) + "//";
        new File(pathFolder).mkdirs();

        Path path = Paths.get(pathFolder + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);
        return mModelMapper.map(mIMultiMediaDAO.addMultimedia(multiMedia), MultiMediaDTO.class);
    }

    @Override
    public MultiMedia getMainMultiMediaById(long id) throws Exception {
        return mIMultiMediaDAO.getMultimediabyId(id);
    }
}