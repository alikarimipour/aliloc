/**
 * 10/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;

import ir.aliloc.api.core.enums.EOS;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clientVersion")
public class ClientVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "minVersionCode")
    private int minVersionCode;

    @Column(name = "maxVersionCode")
    private int maxVersionCode;

    @Column(name = "lastVersionName")
    private String lastVersionName;

    @Column(name = "isForce")
    private boolean force;

    @Column(name = "message")
    private String message;

    @Column(name = "updateLink")
    private String updateLink;

    @Column(name = "os")
    private EOS eOS;

}
