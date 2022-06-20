package com.hackerrank.gevents.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Component
@RestController
public class EventController {

  @Autowired
  private EventRepository repo;

  @GetMapping("/events")
  public List<Event> getAllEvents() {
    List<Event> eve = new ArrayList<Event>();  
    repo.findAll().forEach(event -> eve.add(event));  
    return eve;
  }

  @GetMapping("/events/{eventId}")
  public ResponseEntity<Event> getEvent(@PathVariable (value = "eventId") Integer eventId) {
    Optional<Event> event = repo.findById(eventId);
 
    if(event.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(event.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }
 
  @GetMapping("/repos/{eventId}/events")
  public ResponseEntity<List<Event>> getEventbyRepo(@PathVariable (value = "eventId") Integer eventId) {
	  
      List<Event> event1 = repo.findAllEvent(eventId);
 
    if(!event1.isEmpty()) {
        return ResponseEntity.status(HttpStatus.OK).body(event1);
    } else {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<Event>());
    }
  }

   @PostMapping("/events")
    public ResponseEntity<Event> saveUser(@Validated @RequestBody Event event) {
         Event eve =  repo.save(event);
         return ResponseEntity.status(HttpStatus.CREATED).body(eve);
    }

}
