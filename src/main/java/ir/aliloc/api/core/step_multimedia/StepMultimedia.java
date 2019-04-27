/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step_multimedia;

import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.step.Step;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "step_multimedia")
public class StepMultimedia {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "step_id")
    private Step step;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "multimedia_id")
    private MultiMedia multimedia;


}
