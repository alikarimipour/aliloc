/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_step;

import ir.aliloc.api.core.step.Step;
import ir.aliloc.api.core.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserStepService implements IUserStepService {

    @Autowired
    private IUserStepDAO mIUserStepDAO;


    @Override
    public void addUserStep(long stepId, long userId) throws Exception {
        User user = new User();
        user.setId(userId);
        Step step = new Step();
        step.setId(stepId);
        UserStep userStep = new UserStep();
        userStep.setStep(step);
        userStep.setUser(user);
        userStep.setTime(Calendar.getInstance().getTimeInMillis());
        mIUserStepDAO.addUserStep(userStep);
    }

    @Override
    public boolean checkUserStep(long stepId, long userId) throws Exception {
        return mIUserStepDAO.getUserStep(userId, stepId) != null;
    }

    @Override
    public List<Long> getUserStepIds(long userId) throws Exception {
        return mIUserStepDAO.getUserStepIds(userId);
    }
}
