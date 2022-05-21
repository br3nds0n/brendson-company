package br.com.springboot.server.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.springboot.server.rh.domain.PersonRepository;

@Controller
public class PersonController {

  @Autowired
  private PersonRepository personRepository;
  
  @GetMapping("/rh/pessoas")
  public String people(Model model) {
    model.addAttribute("listaPessoas", personRepository.findAll());

    return "rh/people/index";
  }
}
