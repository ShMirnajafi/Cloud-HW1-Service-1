package com.example.demo.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RequestService {

    RequestRepository requestRepository;

    public String getRequest(Request request) {
        //TODO: generate id
        String id;

        //TODO: save image in storage service

        //TODO: save request in database

        //TODO: save id in rabbitqm
    }

    public boolean getRequestStatusValidation(String id) {
        boolean isIdValid = false;

        //TODO: check if id is in database
    }

    public RequestStatus getRequestStatus(String id) {
        RequestStatus requestStatus;

        //TODO: get status column from database
    }
}
