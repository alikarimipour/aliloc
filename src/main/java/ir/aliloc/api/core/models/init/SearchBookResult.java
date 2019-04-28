/**
 * 12/11/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.models.init;

import lombok.Data;
import org.springframework.util.MimeType;

@Data
public class SearchBookResult {

    private long bookId;
    private String description;
    private int pageCount;
    private int price;
    private String printDate;
    private String printOrder;
    private String title;
    private long thumbnailId;
    private String thumbnailUrl;
    private MimeType thumbnailMime;
    private String thumbnailQuality;
    private String thumbnailDescription;
    private int duration;
    private double size;
    private long bookIndexId;
    private int bookIndexLevel;
    private String bookIndexTitle;
    private int bookIndexPage;
    private long pageId;
    private String pageDetails;
    private String pageFoot;
    private int pageNumber;

}
