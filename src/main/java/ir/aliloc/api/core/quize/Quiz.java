/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.quize;

import ir.aliloc.api.core.question_bank.QuestionBank;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "passPoint")
    private int passPoint;

    @Column(name = "questionCount")
    private int questionCount;

    @Column(name = "time")
    private long time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "questionBank_id")
    private QuestionBank questionBank;

    @Column(name = "coefficient")
    private float coefficient;


}
