/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import java.util.List;

interface IStepDAO {

    List<Step> getStepListByCourseId(long courseId) throws Exception;

    Step getStepById(long stepId) throws Exception;


}
