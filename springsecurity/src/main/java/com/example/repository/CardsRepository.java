package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{
	List<Cards> findByCustomerId(int customerId);
}
