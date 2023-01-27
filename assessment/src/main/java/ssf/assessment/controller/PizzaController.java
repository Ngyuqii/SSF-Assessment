package ssf.assessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ssf.assessment.model.Order;

@Controller
public class PizzaController {

    //Integrate RedisService into the controller
    //@Autowired
    //private RedisService pizzaRedis;

    //Show View 0 for user input
    @GetMapping(path="/")
    public String showViewZero(Model model){
        model.addAttribute("order", new Order());
        return "index";
    }
    
}
