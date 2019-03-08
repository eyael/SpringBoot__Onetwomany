package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ChildrenRepository childrenRepository;
    @Autowired
    MomRepository momRepository;
    @RequestMapping("/")
    public String listChildren(Model model){
        model.addAttribute("children", childrenRepository.findAll());
        model.addAttribute("moms", momRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String childrenForm(Model model){
        model.addAttribute("children", new Children());
        model.addAttribute("moms", momRepository.findAll());
        return "childrenform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Children children, BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("moms", momRepository.findAll());
            return "childrenform";
        }
       childrenRepository.save(children);
        return "redirect:/";
    }
    @GetMapping("/addmom")
    public String momForm(Model model){
        model.addAttribute("mom", new Mom());
        return "mom";
    }
    @PostMapping("/processmom")
    public String processMom(@Valid Mom mom, BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            return "mom";
        }

     momRepository.save(mom);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showChildren(@PathVariable("id") long id, Model model){
        model.addAttribute("children", childrenRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateChildren(@PathVariable("id") long id, Model model){
        model.addAttribute("moms", momRepository.findAll());
        model.addAttribute("children", childrenRepository.findById(id).get());
        return "childrenform";
    }

    @RequestMapping("/delete/{id}")
    public String delchildren(@PathVariable("id") long id){
        childrenRepository.deleteById(id);
        return "redirect:/";
    }

}
