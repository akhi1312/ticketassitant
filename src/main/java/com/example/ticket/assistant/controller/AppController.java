package com.example.ticket.assistant.controller;


import com.example.ticket.assistant.service.ApiCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    ApiCall apiCall;

    @GetMapping("/")
    public String home(){
       return  "Welcome to Ticket Assistant";
    }

   @PostMapping("/search")
        public ResponseEntity search(@RequestBody String query)
        {

             return ResponseEntity.ok(apiCall.search(query));


        }



}
