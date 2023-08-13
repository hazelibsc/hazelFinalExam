package com.example.hazelfinalexam.repositories;

import com.example.hazelfinalexam.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SalesRepository extends JpaRepository<Sales, BigInteger> {
}
