/**
 * 12/2/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user.models;

import lombok.Data;

@Data
public class UserRateDTO extends UserDTO {
    private int totalRate;
    private int monthRate;
}
