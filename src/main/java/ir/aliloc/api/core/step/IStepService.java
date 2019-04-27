/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import java.util.List;

public interface IStepService {

    List<StepDTO> getStepListOfCourse(long courseId) throws Exception;

    StepDTO getStepById(long stepId) throws Exception;

}
