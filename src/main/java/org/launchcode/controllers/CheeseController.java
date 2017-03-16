package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Brady on 3/9/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    @RequestMapping(value= "")
    public String index(Model model){

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, String cheeseDescription) {
        Cheese cheese = new Cheese();
        cheese.setName(cheeseName);
        cheese.setDescription(cheeseDescription);
        cheeses.add(cheese);
        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method= RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam("delete") ArrayList<String> cheesesToDelete){
        int count=0;
        for( String cheeseToDelete : cheesesToDelete){
            for(Iterator<Cheese> cheeseKey = cheeses.iterator(); cheeseKey.hasNext(); ){
                if(cheeseToDelete.equalsIgnoreCase(cheeseKey.next().getName())){
                    cheeseKey.remove();
                }
            }
        }

        return "redirect:";
    }


}
