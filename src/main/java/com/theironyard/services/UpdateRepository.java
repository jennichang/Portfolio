package com.theironyard.services;

import com.theironyard.entities.Update;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UpdateRepository extends CrudRepository<Update, Integer> {

}