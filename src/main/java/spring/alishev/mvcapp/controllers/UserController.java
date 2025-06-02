package spring.alishev.mvcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.alishev.mvcapp.dao.BookDAO;
import spring.alishev.mvcapp.dao.UserDAO;
import spring.alishev.mvcapp.models.Person;

@Controller
@RequestMapping("/books")
public class UserController {
    private final UserDAO userDAO;
    private final BookDAO bookDAO;

    public UserController(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @PatchMapping("/user/del/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.update(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/user/add")
    public String addUser(Model model, @ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.update(id, person.getId());
        return "redirect:/books/{id}";
    }
}
