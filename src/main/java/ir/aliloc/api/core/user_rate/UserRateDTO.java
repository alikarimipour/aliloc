/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user_rate;

import ir.aliloc.api.core.rate_model.RateModelDTO;
import lombok.Data;

@Data
public class UserRateDTO {

    private long id;
    private RateModelDTO rateModel;
    private int rate;
    private long time;
    private String description;
}
