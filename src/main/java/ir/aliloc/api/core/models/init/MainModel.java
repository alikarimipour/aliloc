/**
 * 3/6/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.models.init;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainModel<T> {

    private int statusCode;
    private String message;
    private T result;
}
