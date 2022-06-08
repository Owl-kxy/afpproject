package com.afpproject.afpproject.repository;

import com.afpproject.afpproject.model.entity.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<Request, String>
{

}
