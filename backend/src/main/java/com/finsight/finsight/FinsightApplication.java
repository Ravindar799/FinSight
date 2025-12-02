package com.finsight.finsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinsightApplication.class, args);
	}

}
//User 1 ──> Many Budgets
//Budget 1 ──> Many Categories
//Category 1 ──> Many Expenses
//User
// └── Budget (January)
//      └── Category (Food)
//           └── Expense (#1: Groceries)
//           └── Expense (#2: Starbucks)
//      └── Category (Rent)
//           └── Expense (#3: Rent Payment)