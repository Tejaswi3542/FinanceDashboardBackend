package com.project.FinanceDashboard.Record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	@Autowired private RecordRepository recordRepo;
	
	// get total expenses
	public double getTotalExpenses() {
		Double expense=recordRepo.sumByType("expense");
		if(expense==null) expense=(double) 0;
		return expense;
	}
	
	// Get net amount
	public double getNetAmount() {
		Double income=recordRepo.sumByType("income");
		Double expense=recordRepo.sumByType("expense");
		if(income==null) income=(double) 0;
		if(expense==null) expense=(double) 0;
		return income-expense;
		
	}
	
	// Get category wise total
	public double getCategoryWiseTotal(String category) {
		Double total=recordRepo.sumByCategory(category);
		if(total==null) total=(double) 0;
		return total;
	}
	
	// get recent activity
	public List<Record> getRecentActivity(int n) {
		Pageable pageable=PageRequest.of(0, n);
		return recordRepo.findAllByOrderByDateDesc(pageable);
		
	}
	
	// get total income
	public double getTotalIncome() {
		Double income=recordRepo.sumByType("income");
		if(income==null) income=(double) 0;
		return income;
	}

	
}
