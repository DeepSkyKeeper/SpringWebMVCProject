package spring.alishev.mvcapp.models;


import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной от 2 до 30 символов")
    private String name;
    @Min(value = 1900, message = "Укажите год не раньше 1900")
    private int year;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
