package io.spring.api.responses;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status){
        // menggunakan HashMap untuk custom responsenya
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj){
        // menggunakan HashMap untuk custom responsenya
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);
    }       
}
