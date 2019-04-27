/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;

import lombok.Data;

@Data
public class LectureSubjectIdRequest extends OffsetSizeRequest {
    private long subjectId;
}
