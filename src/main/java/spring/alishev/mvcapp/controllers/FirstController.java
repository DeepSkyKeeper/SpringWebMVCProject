package spring.alishev.mvcapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    // Обращение по адресу без параметров -> будет подставлены значения null
    public String helloPage(HttpServletRequest request){
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        System.out.println("hello "+name+" email:"+email);

return "first/hello";
    }
    @GetMapping("/goodbye")
    //Обращение по адресу без параметров выведет ошибку
    public String goodbyePage(@RequestParam(value="name", required=false) String name,
                              @RequestParam(value="email",required = false) String email, Model model){
//        System.out.println("goodbye "+name+" email:"+email);
        model.addAttribute("message","goodbye "+name+" "+email);
        return "first/goodbye";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam(value="a") int a,
                             @RequestParam(value="b") int b,
                             @RequestParam(value="action") String action,Model model){
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("action",action);
        model.addAttribute("result",calc(a,b,action));
        return "first/calculator";
    }
    private String calc(int a,int b,String action){
        double res=0;
        String ans="";

        switch(action){
            case "add": res=a+b;break;
            case "sub": res=a-b;break;
            case "mul": res=a*b;break;
            case "div": res=a/(double)b;break;
            default: ans="input data error";break;
        }

        ans=(!ans.isBlank())?ans:a+" "+action+" "+b+"="+res;
        return ans;
    }
}
