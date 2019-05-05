package ir.aliloc.api.core.models.response;


import lombok.Data;

/**
 * Created by Ali karimipout on 7/21/2016.
 */

@Data
public class Post {
    private int id;
    private String postImageURL;
    private String title;
    private String content;
    private String date;

}
