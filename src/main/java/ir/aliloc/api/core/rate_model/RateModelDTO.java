/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;
import lombok.Data;

@Data
public class RateModelDTO {

    private long id;
    private String text;
    private String description;
    private RateType rateType;
    private int rate;
}
