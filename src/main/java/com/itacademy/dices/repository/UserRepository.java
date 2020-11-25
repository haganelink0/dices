package com.itacademy.dices.repository;

import com.itacademy.dices.dto.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserResponse, Integer> {
}
