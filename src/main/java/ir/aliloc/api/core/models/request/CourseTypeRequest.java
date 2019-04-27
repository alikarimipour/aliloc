/**
 * 12/23/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;

import ir.aliloc.api.core.enums.ECourseTypeEnum;
import lombok.Data;

@Data
public class CourseTypeRequest extends OffsetSizeRequest {

    private ECourseTypeEnum courseType;
}
