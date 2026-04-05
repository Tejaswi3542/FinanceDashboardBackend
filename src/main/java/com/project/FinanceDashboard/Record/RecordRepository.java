package com.project.FinanceDashboard.Record;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, Long>{
	// find entries by date and time.
	List<Record> findByDateBetween(LocalDateTime start, LocalDateTime end);
	
	List<Record> findByCategory(String category);
	
	List<Record> findByType(String type);

	List<Record> findByAmountLessThan(double amount);

	@Modifying
	@Query("DELETE FROM Record r WHERE r.type = :type")
	void deleteByType(String type);
	
	@Query("SELECT SUM(r.amount) FROM Record r WHERE r.type = :type")
	Double sumByType(@Param("type") String type);
	
	@Query("SELECT SUM(r.amount) FROM Record r WHERE r.category = :category")
	Double sumByCategory(@Param("category") String category);
	
	List<Record> findAllByOrderByDateDesc(Pageable pageable);
}
