/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_step;

import java.util.List;

public interface IUserStepService {

    void addUserStep(long stepId,long userId) throws Exception;

    boolean checkUserStep(long stepId,long userId) throws Exception;

    List<Long> getUserStepIds(long userId) throws Exception;
}
