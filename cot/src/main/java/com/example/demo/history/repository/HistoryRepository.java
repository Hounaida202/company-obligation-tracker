package com.example.demo.history.repository;

import com.example.demo.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByTypeAndReferenceId(String type, Long referenceId);
    List<History> findByPaymentDateBetween(LocalDate start, LocalDate end);
    List<History> findByPaymentDate(LocalDate date);
}
