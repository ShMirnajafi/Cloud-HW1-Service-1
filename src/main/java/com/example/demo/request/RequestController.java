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

    private RequestService requestService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> getRequest(@ModelAttribute Request request) {
        Request userRequest = new Request(request.getEmail(), request.getImage());

        if (userRequest.getImage() == null || userRequest.getImage().isEmpty()) {
            return new ResponseEntity<>("No image received",HttpStatus.BAD_REQUEST);
        }

        String requestId = requestService.getRequest(request);

        if (!requestId.isEmpty()) {
            return new ResponseEntity<>(requestId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getRequestStatus(@PathVariable String id) {
        if (requestService.getRequestStatusValidation(id)) {
            return new ResponseEntity<>(requestService.getRequestStatus(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
