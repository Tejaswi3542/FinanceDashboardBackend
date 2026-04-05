package com.project.FinanceDashboard.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {
	// total expenses
	// total income
	// net amount
	// category wise total
	// recent activity
	
	@Autowired private DashboardService dashboardService;
	
	// Get total expenses
	@GetMapping("totalExpense")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<?> getTotalExpenses(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dashboardService.getTotalExpenses());
	}
	
	// Get total income
		@GetMapping("totalIncome")
		@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
		public ResponseEntity<?> getTotalIncome(){
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dashboardService.getTotalIncome());
		}
	
	//Get net amount
	@GetMapping("netAmount")
	@PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
	public ResponseEntity<?> getNetAmount(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dashboardService.getNetAmount());
	}
	
	//Category wise total
	@GetMapping("total/{category}")
	@PreAuthorize("hasAnyRole('ANALYST','ADMIN')")
	public ResponseEntity<?> getCategoryWiseTotal(@PathVariable String category){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dashboardService.getCategoryWiseTotal(category));
	}
	
	// get recent activity
	@GetMapping("recent/{n}")
	@PreAuthorize("hasAnyRole('ANALYST','ADMIN')")
	public ResponseEntity<?> getRecentActivity(@PathVariable int n){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dashboardService.getRecentActivity(n));
	}
	

}
