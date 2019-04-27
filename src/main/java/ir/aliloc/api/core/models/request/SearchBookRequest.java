/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;

import ir.aliloc.api.core.enums.ESearchDomain;
import ir.aliloc.api.core.enums.ESearchFilter;
import lombok.Data;

@Data
public class SearchBookRequest extends OffsetSizeRequest {

    private String text;
    private ESearchDomain searchDomain;
    private ESearchFilter searchFilter;

}
