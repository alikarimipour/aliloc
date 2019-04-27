/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_course;

import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.payment.Payment;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "purchase_course")
public class PurchaseCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "time")
    private long time;

}
