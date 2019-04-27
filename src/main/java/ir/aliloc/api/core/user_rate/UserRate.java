/**
 * 7/22/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_rate;


import ir.aliloc.api.core.rate_model.RateModel;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_rate")
public class UserRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id")
    private RateModel rateModel;

    @Column(name = "rate")
    private int rate;

    @Column(name = "time")
    private long time;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

}
