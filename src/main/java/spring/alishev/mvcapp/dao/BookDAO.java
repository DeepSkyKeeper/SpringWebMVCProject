package spring.alishev.mvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.alishev.mvcapp.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public Optional<Book> show(String title) {
        return jdbcTemplate.query("select * from book where title = ?", new Object[]{title},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title,author,year) VALUES (?,?,?)",
                book.getTitle(),book.getYear());
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title=?,author=?,year=? WHERE id=?",
                updatedBook.getTitle(),updatedBook.getAuthor(),updatedBook.getYear(),id);
    }
    public void update(int id, int user_id) {
        jdbcTemplate.update("UPDATE book SET user_id=? WHERE id=?",
               user_id,id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }
}
