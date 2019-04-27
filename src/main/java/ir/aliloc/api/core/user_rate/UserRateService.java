/**
 * 12/2/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_rate;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.rate_model.IRateModelService;
import ir.aliloc.api.core.rate_model.RateModel;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.models.request.OffsetSizeRequest;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotAcceptableException;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserRateService implements IUserRateService {

    @Autowired
    private IUserRateDAO mIUserRateDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IRateModelService mIRateModelService;


    @Override
    public List<UserRateDTO> getHistoryRateOfUser(OffsetSizeRequest offsetSizeRequest) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<UserRate> userRateList = mIUserRateDAO.getHistoryOfRateByUser(userId, offsetSizeRequest.getOffset(), offsetSizeRequest.getSize());
        return mCustomMapperService.rateListToRateDTOList(userRateList);
    }

    @Override
    public void invitationHandler(String invitationCode, long invitationUserId) throws Exception {
        long invitedUserId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        if (mIUserRateDAO.checkInvitationUserRate(invitationCode, RateType.INVITED, invitedUserId)) {
            throw new NotAcceptableException(MessageConstant.ERROR_MORE_THAN_ONE_INVITE_CODE_SUBMIT);
        } else {
            mIUserRateDAO.addUserRate(generateModel(invitedUserId, RateType.INVITED, invitationCode));
            mIUserRateDAO.addUserRate(generateModel(invitationUserId, RateType.INVITE_OTHER, null));
        }
    }

    @Override
    public void addRegistrationUserRate(long userId) throws Exception {
        if (!mIUserRateDAO.checkRegistrationUserRate(userId)) {
            mIUserRateDAO.addUserRate(generateModel(userId, RateType.REGISTER, null));
        }
    }

    @Override
    public void addRate(long userId, RateType rateType, String description) throws Exception {
        mIUserRateDAO.addUserRate(generateModel(userId, rateType, description));
    }


    private UserRate generateModel(long userId, RateType rateType, String invitationCode) throws Exception {
        RateModel rateModel = mIRateModelService.getMainObjectRateModelByType(rateType);
        User user = new User();
        user.setId(userId);
        UserRate userRate = new UserRate();
        userRate.setRateModel(rateModel);
        userRate.setUser(user);
        userRate.setTime(Calendar.getInstance().getTimeInMillis());
        userRate.setDescription(invitationCode);
        userRate.setRate(rateModel.getRate());
        return userRate;
    }

}
