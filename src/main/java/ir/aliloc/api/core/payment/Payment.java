/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

import ir.aliloc.api.core.enums.EPaymentState;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "BookId")
    private long BookId;

    @Column(name = "CourseId")
    private long courseId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "payKey")
    private String payKey;

    @Column(name = "payUrl")
    private String payUrl;

    @Column(name = "ePaymentState")
    private EPaymentState paymentState;

    @Column(name = "time")
    private long time;


}
