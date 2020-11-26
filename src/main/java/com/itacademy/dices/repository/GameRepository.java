package com.itacademy.dices.repository;

import com.itacademy.dices.dto.response.GameResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameResponse, Integer> {

    List<GameResponse> findByUserId(Integer id);


}
