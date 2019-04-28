/**
 * 12/5/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.models.request;

import lombok.Data;

@Data
public class LectureSubjectIdRequest extends OffsetSizeRequest {
    private long subjectId;
}
