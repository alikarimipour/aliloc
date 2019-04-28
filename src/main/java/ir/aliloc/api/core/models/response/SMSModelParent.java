/**
 * Created by alikarimipour157@gmail.com on 9/16/2017.
 */
package ir.aliloc.api.core.models.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SMSModelParent {

    @SerializedName("return")
    private SMSModel returnSMS;

}
