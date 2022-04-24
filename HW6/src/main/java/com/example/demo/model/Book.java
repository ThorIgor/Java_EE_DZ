package com.example.demo.model;

import lombok.*;

import javax.persistence.*;


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

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;
}
