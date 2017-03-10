package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Brady on 3/9/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

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
        cheeses.put(cheeseName, cheeseDescription);
        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        ArrayList<String> cheeseNames = new ArrayList<String>();
        model.addAttribute("cheeseNames", cheeseNames);
        model.addAttribute("title", "Remove Cheese");
        for(String entry : cheeses.keySet()){
            cheeseNames.add(entry);
        }
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method= RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam String name){

        for(String key : cheeses.keySet()){
            if(key.equals(name)){
                cheeses.remove(key);
            }
        }

        return "redirect:";
    }


}
