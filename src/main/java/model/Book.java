package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {
    private int id;
    private String author;
    private String description;
    private String isbn;
    private Date release_date;
    private String title;
}
