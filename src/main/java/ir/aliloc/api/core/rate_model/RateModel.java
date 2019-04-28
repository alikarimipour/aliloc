/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rateModel")
public class RateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private RateType rateType;

    @Column(name = "rate")
    private int rate;

    @Column(name = "periodically")
    private int periodically;

    @Column(name = "active")
    private boolean active;

    @Column(name = "maxCount")
    private int maxCount;

}