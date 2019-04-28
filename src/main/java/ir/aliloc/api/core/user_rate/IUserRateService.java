/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user_rate;

import ir.aliloc.api.core.models.request.OffsetSizeRequest;

import java.util.List;

public interface IUserRateService {

    List<UserRateDTO> getHistoryRateOfUser(OffsetSizeRequest offsetSizeRequest) throws Exception;

    void invitationHandler(String invitationCode,long invitationUserId) throws Exception;

    void addRegistrationUserRate(long userId) throws Exception;

    void addRate(long userId,RateType rateType,String description) throws Exception;
}
