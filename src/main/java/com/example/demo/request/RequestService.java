package com.example.demo.request;

import com.example.demo.objectstorage.ObjectStorageService;
import com.example.demo.rabbitmq.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RequestService {

    private RequestRepository requestRepository;
    private ObjectStorageService objectStorageService;
    private RabbitMQProducer rabbitMQProducer;

    public String getRequest(Request request) {
        String id = UUID.randomUUID().toString();

        // save image in storage service
        try {
            // Convert MultipartFile to a Path and call the upload method
            Path tempFile = Files.createTempFile("upload-", request.getImage().getOriginalFilename());
            request.getImage().transferTo(tempFile.toFile());

            objectStorageService.uploadFile(request.getImage().getOriginalFilename(), tempFile);
        } catch (Exception e) {
            return "";
        }

        RequestData requestData = new RequestData(id, request.getEmail(), "pending", null, null);

        // save request in database
        requestRepository.save(requestData);

        // save id in rabbitmq
        rabbitMQProducer.sendMessage(id);

        return id;
    }

    public boolean getRequestStatusValidation(String id) {
        Optional<RequestData> request = requestRepository.findById(id);
        return request.isPresent();
    }

    public String getRequestStatus(String id) {
        Optional<RequestData> request = requestRepository.findById(id);

        if (!request.isPresent()) {
            throw new RuntimeException("No record found for this id: " + id);
        }

        return request.get().getStatus();
    }
}
