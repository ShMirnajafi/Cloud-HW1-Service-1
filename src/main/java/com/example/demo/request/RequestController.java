package com.example.demo.request;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping(path = "api/v1/request")
public class RequestController {

    RequestService requestService;

    @PostMapping
    public ResponseEntity<String> getRequest(@RequestBody Request request) {
        //TODO: receive image in json
        String requestId = requestService.getRequest(request);
        if (!requestId.isEmpty()) {
            return new ResponseEntity<>(requestId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RequestStatus> getRequestStatus(@PathVariable String id) {
        if (requestService.getRequestStatusValidation(id)) {
            return new ResponseEntity<>(requestService.getRequestStatus(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
