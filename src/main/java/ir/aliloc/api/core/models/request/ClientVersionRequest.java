/**
 * 11/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;


import ir.aliloc.api.core.enums.EOS;
import lombok.Data;

@Data
public class ClientVersionRequest {

    private EOS os;
    private int versionCode;
}
