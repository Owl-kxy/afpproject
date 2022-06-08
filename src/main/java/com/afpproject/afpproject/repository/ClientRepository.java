package com.afpproject.afpproject.repository;

import com.afpproject.afpproject.model.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
