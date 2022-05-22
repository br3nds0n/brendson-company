package br.com.springboot.server.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.springboot.server.rh.domain.Person;
import br.com.springboot.server.rh.domain.PersonRepository;

@Controller
public class PersonController {

  @Autowired
  private PersonRepository personRepository;
  
  @GetMapping("/rh/pessoas")
  public String people(Model model) {
    model.addAttribute("listPeople", personRepository.findAll());

    return "rh/people/index";
  }

  @GetMapping("/rh/pessoas/nova")
  public String newPerson(Model model){

    model.addAttribute("person", new Person(""));

    return "rh/people/form";
  }

  @PostMapping("/rh/pessoas/salvar")
  public String savePerson(@ModelAttribute("person") Person person){
    personRepository.save(person);

    return "redirect:/rh/pessoas";
  }
}
