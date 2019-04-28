/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user_rate;

import java.util.List;

interface IUserRateDAO {

    List<UserRate> getHistoryOfRateByUser(long userId, int offset, int size) throws Exception;

    void addUserRate(UserRate userRate) throws Exception;

    List<Object> checkUserRateByType(RateType rateType, long userId) throws Exception;

    boolean checkInvitationUserRate(String invitation, RateType rateType, long userId) throws Exception;

    boolean checkRegistrationUserRate(long userId) throws Exception;
}
