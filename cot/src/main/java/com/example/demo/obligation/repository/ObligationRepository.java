package com.example.demo.obligation.repository;

import com.example.demo.obligation.entity.Obligation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {
}
