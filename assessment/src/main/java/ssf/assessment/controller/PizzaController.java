package ssf.assessment.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showViewOne(@Valid Order order, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "index";
        }
        model.addAttribute("order", new Order());
        return "custdetails";
    }

    //Method to process customerdetails form and perform validation
    //Returns view 2 - orderdetails
    @PostMapping(path="/pizza/order")
    public String showViewTwo(@Valid Order order, BindingResult result, @ModelAttribute Order o, Model model, HttpServletResponse response) throws IOException {
        if (result.hasErrors()){
            return "custdetails";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        calculateCost(model, o.getPizza(), o.getSize(), o.getQuantity());
        model.addAttribute("order", order);
        return "orderdetails";
    }

    //Method to calculate pizza cost
    private void calculateCost(Model model, String pizza, String size, Integer quantity){
        
        //Set pizza price
        double pizzaPrice = 0f;
        if (pizza == "bella" || pizza == "marinara" || pizza == "spianatacalabrese" ) {
            pizzaPrice = 30.00; 
        }
        else if (pizza == "margherita") {
            pizzaPrice = 22.00;
        }
        else if (pizza == "trioformaggio"){
            pizzaPrice = 25.00;
        }
        System.out.println("Price is " + pizzaPrice);

        //Set size multiplier
        double sizeMulti = 0f;
        if (size == "sm") {
            sizeMulti = 1; 
        }
        else if (size == "md") {
            sizeMulti = 1.2; 
        }
        else if (size == "lg") {
            sizeMulti = 1.5; 
        }
        System.out.println("Size multiplier is " + sizeMulti);

        //Total price
        double total = pizzaPrice * sizeMulti * quantity;
        System.out.println("Total price is $" + total);
        model.addAttribute("total", total);

    }

}