package spring.alishev.mvcapp.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.alishev.mvcapp.dao.BookDAO;
import spring.alishev.mvcapp.models.Book;


@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (bookDAO.show(book.getTitle()).isPresent()) {
            errors.rejectValue("title", "Книга с таким названием уже существует");
        }
    }
}
