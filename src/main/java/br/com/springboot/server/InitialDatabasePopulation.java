package br.com.springboot.server;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.springboot.server.rh.domain.Person;
import br.com.springboot.server.rh.domain.PersonRepository;

@Component
@Transactional
public class InitialDatabasePopulation implements CommandLineRunner{

  @Autowired
  private PersonRepository personRepository;

  @Override
  public void run(String... args) throws Exception {
    
    Person p1 = new Person("Brendson");
    p1.setDataNascimento(LocalDate.of(2003, 04, 23));
    p1.setEmail("brendson.example@gmail.com");
    p1.setTelefone("(81)9 8861-5976");
    p1.setCpf("40359660029");

    Person p2 = new Person("Victor");
    p2.setDataNascimento(LocalDate.of(2002, 03, 26));
    p2.setEmail("victor.example@gmail.com");
    p2.setTelefone("(81)9 8861-5976");
    p2.setCpf("11455961000");

    personRepository.save(p1);
    personRepository.save(p2);
  }
  
}
