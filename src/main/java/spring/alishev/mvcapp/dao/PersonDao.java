package spring.alishev.mvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.alishev.mvcapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Optional<Person> show(String name){
        return jdbcTemplate.query("select * from person where name=?",new Object[] {name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name,int year){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=? AND year=?",new Object[] {name,year},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name,year) VALUES (?,?)",
                person.getName(),person.getYear());
    }
    public void update(int id, Person updatedPerson) {
jdbcTemplate.update("UPDATE person SET name=?,year=? WHERE id=?",
        updatedPerson.getName(),updatedPerson.getYear(),id);
    }
    public void delete(int id) {
     jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }

    /**
    Тестируем производительность пакетной вставки
     */
    public void testMultipleUpdate(){
        List <Person> people=create1000People();
        long before =System.currentTimeMillis();
        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO person VALUES (?,?)", person.getId(),person.getName(), person.getYear());
        }
        long after =System.currentTimeMillis();
        System.out.println("Ordinary update time"+(after-before)+"ms");
    }
    public void testBatchUpdate(){
        List<Person> people=create1000People();
        long before =System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1,people.get(i).getId());
                        ps.setString(2,people.get(i).getName());
                        ps.setInt(3,people.get(i).getYear());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });
        long after =System.currentTimeMillis();
        System.out.println("Batch update time"+(after-before)+"ms");
    }

    private List<Person> create1000People() {
        List<Person> people=new ArrayList<>();
        for(int i=1;i<=1000;i++){
            people.add(new Person(i,"name"+i,30));
        }
        return people;
    }
}
