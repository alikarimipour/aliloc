/**
 * 12/4/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import ir.aliloc.api.core.audio_book.AudioBookDTO;
import ir.aliloc.api.core.lecture.LectureDTO;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private long id;
    private String title;
    private String printOrder;
    private String printDate;
    private String description;
    private int price;
    private int pageCount;
    private int startPage;
    private String sellLink;
    private MultiMediaDTO thumbnail;
    private boolean isDownload;
    private List<LectureDTO> lectures;
    private AudioBookDTO audio;
}
