package spring.alishev.mvcapp.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.alishev.mvcapp.dao.BookDAO;
import spring.alishev.mvcapp.dao.PersonDAO;
import spring.alishev.mvcapp.dao.UserDAO;
import spring.alishev.mvcapp.models.Book;
import spring.alishev.mvcapp.models.Person;
import spring.alishev.mvcapp.util.BookValidator;

import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;
    private final UserDAO userDAO;

    public BookController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDao, UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDao;
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,@ModelAttribute ("person") Person person) {
        Book book= bookDAO.show(id);
       Optional<Integer> userId;
        model.addAttribute("book",book);
        userId=book.getUser_id();
        if (userId.isPresent()){
        model.addAttribute("person",userDAO.show(userId.get()));}
        else model.addAttribute("people", personDAO.index());
        System.out.println("отправка в шаблон books/show из bookcontroller");
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return ("books/new");
        bookDAO.save(book);
        return "redirect:/books" + book.getId();
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return ("books/edit");
        }
        bookDAO.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/del/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}
