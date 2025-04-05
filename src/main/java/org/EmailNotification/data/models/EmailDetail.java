package org.EmailNotification.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetail {
    @Id
    private String id;

    private String subject;

    private String body;

    @DBRef
    private User user;

    private String recipient;
}
