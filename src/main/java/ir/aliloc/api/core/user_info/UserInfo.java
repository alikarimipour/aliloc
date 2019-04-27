/**
 * 10/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_info;


import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userInfo")
class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "fbToken")
    private String fbToken;

    @Column(name = "lastFbTokenUpdate")
    private long lastFbTokenUpdate;

    @Column(name = "lastAppVersionFbTokenRefresh")
    private String lastAppVersionFbTokenRefresh;

    @Column(name = "lastMetadataVersionFbTokenRefresh")
    private String lastMetadataVersionFbTokenRefresh;

    @Column(name = "uniqueIdentifier",unique = true)
    private String uniqueIdentifier;

}
