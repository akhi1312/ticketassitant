package com.example.ticket.assistant.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    private String name;
    private String status ;
    private String link;
    private String address;
    private int minPrice;
    private int maxPrice;
    private int totalTicketAvailabe;

}
