package br.com.springboot.server.rh.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/rh/pessoas/{id}")
  public String changePerson(@PathVariable("id") Long id, Model model){
    Optional<Person> person = personRepository.findById(id);
    if(person.isEmpty()) {
       throw new IllegalArgumentException("invalid person...");
    }

    model.addAttribute("person", person.get());

    return "rh/people/form";
  }

  @GetMapping("/rh/pessoas/excluir/{id}")
  public String deletePerson(@PathVariable("id") Long id, Model model) {
   Optional<Person> person = personRepository.findById(id);
   if(person.isEmpty()){
      throw new IllegalArgumentException("invalid person.");
   }

   personRepository.delete(person.get());

   return"redirect:/rh/pessoas";
   }

  @PostMapping("/rh/pessoas/salvar")
  public String savePerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      return "rh/people/form";
    }

    personRepository.save(person);

    return "redirect:/rh/pessoas";
  }
}
