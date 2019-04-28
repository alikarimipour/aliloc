/**
 * 10/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "multimedia")
public class MultiMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "mime")
    private MimeType mime;

    @Column(name = "quality")
    private String quality;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "date")
    private long date;

    @Column(name = "size")
    private double size;

    @Lob
    @Column(name="file")
    private byte[] file;


}
