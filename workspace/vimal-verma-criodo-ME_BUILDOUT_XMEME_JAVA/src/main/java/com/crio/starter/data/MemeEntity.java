package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

// models in db
@Data
@NoArgsConstructor
@Document(collection = "memes")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class MemeEntity {
    
    @Id
    private String id;
    private String name;
    private String url;
    private String caption;
    // private LocalTime createdAt;

}
