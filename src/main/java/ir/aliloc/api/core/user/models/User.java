/**
 * 3/6/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user.models;


import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.user.GenderType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birthDate")
    private long birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile", unique = true)
    private String mobile;

    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "nationalCode")
    private String nationalCode;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "avatar_multimedia_id")
    private MultiMedia avatar;

    @Column(name = "creationDate")
    private long creationDate;

    @Column(name = "invitationCode")
    private String invitationCode;

    @Column(name = "fatherName")
    private String fatherName;

}
