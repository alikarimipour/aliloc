/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

import ir.aliloc.api.core.audio_book.AudioBook;
import ir.aliloc.api.core.payment.Payment;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase_audio")
public class PurchaseAudio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "audioBook_id")
    private AudioBook audioBook;

    @OneToOne(cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "time")
    private long time;

}
