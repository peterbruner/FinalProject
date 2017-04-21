package com.novauc.services;


import com.novauc.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer>{
}
