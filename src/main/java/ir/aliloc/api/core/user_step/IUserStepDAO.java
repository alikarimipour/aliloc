/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_step;

import java.util.List;

interface IUserStepDAO {

    void addUserStep(UserStep userStep) throws Exception;

    UserStep getUserStep(long userId, long stepId) throws Exception;

    List<Long> getUserStepIds(long userId) throws Exception;
}
