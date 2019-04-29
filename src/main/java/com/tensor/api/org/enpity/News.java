package com.tensor.api.org.enpity;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liaochuntao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private long id;
    private String newTitle;
    private String newType;
    private String author;
    private String text;
    private String hashCode;
    private String publish_date;
    private String download_date;
    private String url;
    private String source;


    public void set(Object data){
        this.newType=data.getClass().getTypeName();
    }

}
