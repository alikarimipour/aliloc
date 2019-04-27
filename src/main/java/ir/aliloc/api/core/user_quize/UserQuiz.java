/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.quize.Quiz;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_quiz")
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "time")
    private long time;

    @Column(name = "EQuizResult")
    private EQuizResult eType;

    @Column(name = "point")
    private float point;

}
