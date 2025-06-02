package spring.alishev.mvcapp.models;

import jakarta.validation.constraints.*;

import java.util.Optional;


public class Book {
    private int id;

    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть длиной от  2 до 100 символов")
    private String title;

    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть длиной от  2 до 100 символов")
    private String author;

    @Min(value=0,message="Год издания не может быть <0")
    @Max(value=2025,message = "Год издания не может быть позже текущего")
    private int year;

    private Optional<Integer> user_id=Optional.empty();

    public Book(int id, String title, String author, int year, Optional<Integer> user_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.user_id = user_id;
    }
    public Book(){}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Optional<Integer> getUser_id() {
        return user_id;
    }

    public void setUser_id() {
        user_id=null;
    }

    public void setUser_id(Optional<Integer> id) {
        this.user_id = id;
    }
}

