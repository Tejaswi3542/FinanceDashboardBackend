package com.project.FinanceDashboard.Record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RecordService {
	@Autowired
	private RecordRepository recordRepo;
	
	// Get entries
	public List<Record> getAllEntries(){
		return recordRepo.findAll();
	}
	
	// Get entries by date and time
	public List<Record> getEntriesByDate(LocalDate date){
		LocalDateTime startTime=date.atStartOfDay();
		LocalDateTime endTime=date.atTime(23, 59, 59);
		return recordRepo.findByDateBetween(startTime, endTime);
	}
	
	// Get entries by category
	public List<Record> getEntriesByCategory(String category){
		return recordRepo.findByCategory(category);
	}
	
	// Get entries by type
	public List<Record> getEntriesByType(String type){
		return recordRepo.findByType(type);
	}
	//Get entries by amount
	public List<Record> getEntriesLessThan(double amount){
		return recordRepo.findByAmountLessThan(amount);
	}

	
	// Add entries
	public Record addEntry(Record newEntry) {
		return recordRepo.save(newEntry);
	}
	
	// Update entries
	public void updateEntryById(Long id, Record newEntry) {
	    Record savedRecord = recordRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
	    savedRecord.setDescription(newEntry.getDescription());
	    savedRecord.setAmount(newEntry.getAmount());
	    savedRecord.setType(newEntry.getType());
	    savedRecord.setCategory(newEntry.getCategory());
	    recordRepo.save(savedRecord);
	}
	
	// Delete entries by type
	@Transactional
	public void deleteEntryByType(String type) {
		recordRepo.deleteByType(type);
	}
}
