package model.filehandling;

import model.category.ExpenseCategory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryFileHandler implements FileHandler<ExpenseCategory> {
    private static final String EXPENSE_CATEGORIES_FILE_PATH = "src/saved/expenseCategories.txt";

    @Override
    public void save(List<ExpenseCategory> categories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSE_CATEGORIES_FILE_PATH))) {
            for (ExpenseCategory category : categories) {
                String line = category.getName() + "/" + category.getBudgetLimit();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExpenseCategory> load() {
        List<ExpenseCategory> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EXPENSE_CATEGORIES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/");
                String categoryName = parts[0].trim();
                int budget = Integer.parseInt(parts[1].trim());
                ExpenseCategory category = new ExpenseCategory(categoryName, budget);
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

