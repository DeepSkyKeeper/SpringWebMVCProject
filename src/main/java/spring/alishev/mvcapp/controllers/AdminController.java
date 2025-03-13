package spring.alishev.mvcapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.alishev.mvcapp.dao.PersonDAO;
import spring.alishev.mvcapp.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
private final PersonDAO personDAO;
@Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String  adminPage(Model model,@ModelAttribute ("person") Person person) {
    model.addAttribute("people", personDAO.index());
    return "people/adminPage";
    }
    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person){
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
