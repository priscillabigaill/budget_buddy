package model.data;

import model.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DateFilter {

    // Filters the list of transactions based on the specified start and end dates
    public List<Transaction> filterByDate(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        LocalDate fromDate = startDate;
        LocalDate toDate = endDate;

        Predicate<Transaction> dateFilter = isInFilter(fromDate, toDate);

        List<Transaction> filteredTransactions = new ArrayList<>();

        // Iterate through each transaction and check if it satisfies the date filter
        for (Transaction transaction : transactions) {
            if (dateFilter.test(transaction)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    // Returns a predicate that checks if a transaction's date falls within the specified range
    private Predicate<Transaction> isInFilter(LocalDate fromDate, LocalDate toDate) {
        return transaction -> {
            // Assuming transaction.getDate() returns a String representation of a date
            String dateString = String.valueOf(transaction.getDate());
            LocalDate transactionDate = LocalDate.parse(dateString);

            // Check if the transaction date is equal to or after the start date
            // and if it is equal to or before the end date
            return (transactionDate.isEqual(fromDate) || transactionDate.isAfter(fromDate))
                    && (transactionDate.isEqual(toDate) || transactionDate.isBefore(toDate));
        };
    }
}
