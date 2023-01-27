package ssf.assessment.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import ssf.assessment.model.Order;

@Controller
public class PizzaController {

    //Integrate RedisService into the controller
    //@Autowired
    //private RedisService pizzaRedis;

    //Show View 0 - index for user input
    @GetMapping(path="/")
    public String showViewZero(Model model){
        model.addAttribute("order", new Order());
        return "index";
    }
    
    //Method to process pizza form and perform validation
    //Returns view 1 - custdetails
    @PostMapping(path="/pizza")
    public String showViewOne(@Valid Order order, BindingResult bindingResult, Model model) {
        // if(bindingResult.hasErrors()){
        //     return "index";
        // }
        model.addAttribute("order", new Order());
        return "custdetails";
    }

    //Method to process customerdetails form and perform validation
    //Returns view 2 - orderdetails
    @PostMapping(path="/pizza/order")
    public String showViewOne(@Valid Order order, BindingResult bindingResult, Model model, HttpServletResponse response) throws IOException {
        // if (bindingResult.hasErrors()){
        //     return "custdetails";
        // }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "orderdetails";


    }

}
