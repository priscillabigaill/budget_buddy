package model.filehandling;

import model.category.Category;
import model.category.IncomeCategory;
import model.category.ExpenseCategory;
import model.Transaction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileHandler implements FileHandler<Transaction> {
    private static final String TRANSACTIONS_FILE_PATH = "src/saved/transactions.txt";

    // Saves the list of transactions to the transactions file
    @Override
    public void save(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE_PATH))) {
            for (Transaction transaction : transactions) {
                String line = transaction.getType() + "," +
                        transaction.getCategory().getName() + "," +
                        transaction.getAmount() + "," +
                        transaction.getDescription() + "," +
                        transaction.getDate();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the list of transactions from the transactions file
    @Override
    public List<Transaction> load() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(TRANSACTIONS_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String type = parts[0];
                    String categoryName = parts[1];
                    int amount = Integer.parseInt(parts[2]);
                    String description = parts[3];
                    String dateString = parts[4];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    Category category = findCategoryByName(categoryName);
                    if (category != null) {
                        Transaction transaction = new Transaction(type, category, amount, description, date);
                        transactions.add(transaction);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Finds a category by its name
    public Category findCategoryByName(String categoryName) {
        List<IncomeCategory> incomeCategories = new IncomeCategoryFileHandler().load();
        List<ExpenseCategory> expenseCategories = new ExpenseCategoryFileHandler().load();

        // Combine the income and expense categories into a single list
        List<Category> allCategories = new ArrayList<>();
        allCategories.addAll(incomeCategories);
        allCategories.addAll(expenseCategories);

        for (Category category : allCategories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }
}
