/**
 * 10/8/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import lombok.Data;

@Data
public class MultiMediaDTO {

    private long id;
    private MimeType mime;
    private String quality;
    private String description;
    private int duration;
    private String url;
    private double size;
}
