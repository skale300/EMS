package com.swapapp.microservices.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapapp.microservices.ems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}