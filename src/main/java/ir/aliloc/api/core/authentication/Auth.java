/**
 * 3/6/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.authentication;


import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token")
    private String token;

    @Column(name = "lastModifyTime")
    private long lastModify;

    @Column(name = "verifyCode")
    private int verifyCode;

}
