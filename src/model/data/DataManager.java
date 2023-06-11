package model.data;

import model.Transaction;
import java.time.LocalDate;
import java.util.List;

public class DataManager {

    // Constructor that initializes the DataManager with a list of loaded transactions
    public DataManager(List<Transaction> loadedTransactions) {
        transactions = loadedTransactions; // Assign the loaded transactions to the transactions variable
        dateFilter = new DateFilter();
    }

    private List<Transaction> transactions; // List to store the transactions
    private DateFilter dateFilter; // DateFilter object to perform filtering

    // Returns a list of transactions filtered by the specified start and end dates
    public List<Transaction> getFilteredTransactions(LocalDate startDate, LocalDate endDate) {
        // Call the filterByDate method of the dateFilter object to filter the transactions
        List<Transaction> filteredTransactions = dateFilter.filterByDate(transactions, startDate, endDate);
        return filteredTransactions;
    }
}
