package com.userservice.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="user_microservice")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient
    private List<Rating> rating =new ArrayList<>();
}
