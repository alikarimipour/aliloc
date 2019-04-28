/**
 * 10/6/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.util;


import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.rate_model.RateModelDTO;
import ir.aliloc.api.core.user_rate.UserRate;
import ir.aliloc.api.core.user_rate.UserRateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomMapperService {
    @Autowired
    private ModelMapper mModelMapper;

    public List<UserRateDTO> rateListToRateDTOList(List<UserRate> userRateList) {
        List<UserRateDTO> userRateDTOList = new ArrayList<>();
        for (UserRate userRate : userRateList) {
            userRateDTOList.add(rateToRateDTO(userRate));
        }
        return userRateDTOList;
    }

    public UserRateDTO rateToRateDTO(UserRate userRate) {
        UserRateDTO userRateDTO = new UserRateDTO();
        userRateDTO.setId(userRate.getId());
        userRateDTO.setDescription(userRate.getDescription());
        userRateDTO.setRate(userRate.getRate());
        userRateDTO.setTime(userRate.getTime());
        RateModelDTO rateModelDTO = new RateModelDTO();
        rateModelDTO.setId(userRate.getRateModel().getId());
        rateModelDTO.setDescription(userRate.getRateModel().getDescription());
        rateModelDTO.setText(userRate.getRateModel().getText());
        rateModelDTO.setRate(userRate.getRateModel().getRate());
        rateModelDTO.setRateType(userRate.getRateModel().getRateType());
        userRateDTO.setRateModel(rateModelDTO);
        return userRateDTO;
    }

    public MultiMediaDTO multimediaToMultimediaDTO(MultiMedia multiMedia, boolean isShowUrl) {
        MultiMediaDTO multiMediaDTO = new MultiMediaDTO();
        multiMediaDTO.setId(multiMedia.getId());
        multiMediaDTO.setSize(multiMedia.getSize());
        multiMediaDTO.setQuality(multiMedia.getQuality());
        multiMediaDTO.setMime(multiMedia.getMime());
        multiMediaDTO.setDuration(multiMedia.getDuration());
        multiMediaDTO.setDescription(multiMedia.getDescription());
        if (isShowUrl) {
            multiMediaDTO.setUrl(multiMedia.getUrl());
        }
        return multiMediaDTO;
    }

    public List<MultiMediaDTO> multiMediaListToMultiMediaDTOList(List<MultiMedia> multiMediaList, boolean isShowUrl) {
        List<MultiMediaDTO> multiMediaDTOList = new ArrayList<>();
        for (MultiMedia multiMedia : multiMediaList) {
            multiMediaDTOList.add(multimediaToMultimediaDTO(multiMedia, isShowUrl));
        }
        return multiMediaDTOList;
    }

    public List<MultiMediaDTO> multiMediaListToMultiMediaUrlDTOList(Set<MultiMedia> multiMediaList, boolean isShowUrl) {
        List<MultiMediaDTO> multiMediaDTOList = new ArrayList<>();
        for (MultiMedia multiMedia : multiMediaList) {
            multiMediaDTOList.add(multimediaToMultimediaDTO(multiMedia, isShowUrl));
        }
        return multiMediaDTOList;
    }
}

