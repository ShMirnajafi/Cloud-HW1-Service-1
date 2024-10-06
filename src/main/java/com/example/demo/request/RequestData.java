package com.example.demo.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "request_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestData {

    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    private String status;

    @Column(name = "image_caption")
    private String imageCaption;

    @Column(name = "new_image_url")
    private String newImageUrl;
}
