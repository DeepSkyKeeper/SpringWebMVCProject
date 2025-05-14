package spring.alishev.mvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.alishev.mvcapp.models.Book;
import spring.alishev.mvcapp.models.Person;

@Component
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void update(int id, int userId) {
        jdbcTemplate.update("UPDATE book SET user_id=? where id=?", userId, id);
    }
    public void update(int id) {
        jdbcTemplate.update("UPDATE book SET user_id=null WHERE id=?", id);
    }
    public void show(int id) {
        jdbcTemplate.query("SELECT person.name FROM person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
