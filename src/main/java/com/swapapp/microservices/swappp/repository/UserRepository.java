package com.swapapp.microservices.swappp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapapp.microservices.swappp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}