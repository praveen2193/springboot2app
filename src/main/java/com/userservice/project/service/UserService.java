package com.userservice.project.service;

import com.userservice.project.dao.UserDao;
import com.userservice.project.entity.Hotel;
import com.userservice.project.entity.Rating;
import com.userservice.project.entity.User;
import com.userservice.project.exception.ResourceNotFoundException;
import com.userservice.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDao {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate template;

    Logger logger= LoggerFactory.getLogger(UserService.class);


    @Override
    public User createUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        User user1 =repository.save(user);
        return user1;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUser(String userId) {
       User user= repository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with userid not found"+userId));
       //http://localhost:8080/api/v1/rating/64896b743c9c536a72d90d85
        Rating[] forObject = template.getForObject("http://RATING-SERVICE/api/v1/ratings/user/"+user.getUserId(), Rating[].class);
        logger.info("{} ",forObject);
        List<Rating> list = Arrays.stream(forObject).toList();

        List<Rating> ratinglist = list.stream().map(rating -> {
           ResponseEntity<Hotel> entity =template.getForEntity("http://HOTEL-SERVICE/api/v1/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = entity.getBody();
            logger.info(" {}",entity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratinglist);
        return user;
    }

    @Override
    public void deleteById(String userId) {
     repository.deleteById(userId);

    }

    @Override
    public void deleteAll() {
    repository.deleteAll();
    }

    @Override
    public User updateUser(User user, String userId) {
        Optional<User> byId = repository.findById(userId);
        if (byId.isPresent()){
            User use=byId.get();
            use.setName(user.getName());
            use.setEmail(user.getEmail());
            use.setAbout(user.getAbout());
         return repository.save(use);
        }else {
            throw new ResourceNotFoundException("resource not found");
        }

    }
}
