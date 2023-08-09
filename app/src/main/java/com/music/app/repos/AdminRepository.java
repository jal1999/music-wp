 package com.music.app.repos;

 import com.music.app.models.Admin;
 import org.springframework.data.mongodb.repository.MongoRepository;

 public interface AdminRepository extends MongoRepository<Admin, String> {
     Admin findByEmail(String email);
 }