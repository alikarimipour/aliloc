/**
 * 12/16/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import lombok.Data;

import java.util.List;

@Data
public class
AudioBookDTO {
    private long id;
    private String title;
    private int price;
    private boolean isBuy;
    private MultiMediaDTO demo;
    private List<MultiMediaDTO> multimediaList;
}
