package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
/**
 * Person Controller route /Person.
 */

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
    public Person createdFertilizer(@RequestBody Person person) {
    return personService.create(person);
  }
}