/**
 * 12/24/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class RateModelService implements IRateModelService {

    @Autowired
    private IRateModelDAO mIRateModelDAO;
    @Autowired
    private ModelMapper mModelMapper;

    @Override
    public RateModelDTO getRateModelByType(RateType rateType) throws Exception {
        return mModelMapper.map(mIRateModelDAO.getRateModelByType(rateType), RateModelDTO.class);
    }

    @Override
    public RateModel getMainObjectRateModelByType(RateType rateType) throws Exception {
        return mIRateModelDAO.getRateModelByType(rateType);
    }
}
