package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;



@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title can't be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "ISBN can't be empty")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN has bad format")
    @Column(name = "isbn")
    private String isbn;

    @NotEmpty(message = "Author can't be empty")
    @Column(name = "author")
    private String author;

    @ManyToMany(mappedBy = "books")
    List<User> users;
}
