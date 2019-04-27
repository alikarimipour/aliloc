/**
 * 11/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;

import lombok.Data;

@Data
public class ClientVersionDTO {

    private ClientVersionEnum type;
    private String message;
    private String updateLink;
    private String latestVersionName;


}
