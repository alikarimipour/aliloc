/**
 * 10/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_info;

import lombok.Data;

@Data
public class UserInfoDTO {

    private long id;
    private String fbToken;
    private long lastFbTokenUpdate;
    private String lastAppVersionFbTokenRefresh;
    private String lastMetadataVersionFbTokenRefresh;
    private String uniqueIdentifier;
}
