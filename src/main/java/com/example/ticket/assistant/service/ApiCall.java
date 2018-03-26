package com.example.ticket.assistant.service;

import com.example.ticket.assistant.Model.Event;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCall {



    @Autowired
    Gson gson;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${url}")
    private String url;


    public List<Event> search(String string) {

        List<Event> result = new ArrayList<Event>();

        url = url + gson.fromJson(string , JsonObject.class).get("query").getAsString();
        RestTemplate restTemplate = new RestTemplate();

        try{

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer ".concat(accessToken));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            System.out.println(headers);
            System.out.println(url);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            JsonArray events = jsonObject.getAsJsonArray("events");
            for(JsonElement eventElement : events) {
                Event event = new Event();
                event.setName(eventElement.getAsJsonObject().get("name").getAsString());
                event.setAddress(eventElement.getAsJsonObject().get("venue").getAsJsonObject().get("address1").getAsString());
                event.setLink("www.stubhub.com/"+eventElement.getAsJsonObject().get("webURI").getAsString());
                event.setStatus(eventElement.getAsJsonObject().get("status").getAsString());
                result.add(event);
            }



        } catch (Exception e) {
            System.out.println("** Exception: " + e.getMessage());
        }

        return result;

    }

}
