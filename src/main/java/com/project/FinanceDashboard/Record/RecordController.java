package com.project.FinanceDashboard.Record;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/records")
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	@GetMapping("getAll")
	@PreAuthorize("hasAnyRole('VIEWER', 'ANALYST', 'ADMIN')")
	public ResponseEntity<List<Record>> getAllRecord() {
		return ResponseEntity.status(HttpStatus.OK).body(recordService.getAllEntries());
	}
	@GetMapping("get-by-date")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<List<Record>> getEntriesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recordService.getEntriesByDate(date));
	}
	
	@GetMapping("get-by-category/{category}")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<List<Record>> getEntriesByCategory(@PathVariable String category){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recordService.getEntriesByCategory(category));
	}
	
	@GetMapping("get-by-type/{type}")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<List<Record>> getEntriesByType(@PathVariable String type){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recordService.getEntriesByType(type));
	}
	
	@GetMapping("get-amount-less-than/{amount}")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<List<Record>> getEntriesLessThan(@PathVariable double amount){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recordService.getEntriesLessThan(amount));
	}
	
 	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addEntry(@Valid @RequestBody Record newEntry){
		recordService.addEntry(newEntry);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Entry saved successfully..");
	}
	
	@PutMapping("update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateEntryById(@Valid @PathVariable Long id, @RequestBody Record newEntry) {
	    try {
	        recordService.updateEntryById(id, newEntry);
	        return ResponseEntity.status(HttpStatus.OK).body("Entry updated successfully");
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	@DeleteMapping("delete/{type}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteEntryByType(@PathVariable String type){
		recordService.deleteEntryByType(type);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Entry deleted successfully");
	}
	
	

}
