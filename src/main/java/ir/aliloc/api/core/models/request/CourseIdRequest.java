/**
 * 1/1/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;

import lombok.Data;

@Data
public class CourseIdRequest extends OffsetSizeRequest {

    private long courseId;
}
