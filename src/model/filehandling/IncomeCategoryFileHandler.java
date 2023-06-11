package model.filehandling;

import model.category.IncomeCategory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeCategoryFileHandler implements FileHandler<IncomeCategory> {
    private static final String INCOME_CATEGORIES_FILE_PATH = "src/saved/incomeCategories.txt";

    // Saves the list of income categories to the income categories file
    @Override
    public void save(List<IncomeCategory> categories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INCOME_CATEGORIES_FILE_PATH))) {
            for (IncomeCategory category : categories) {
                String line = category.getName();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the list of income categories from the income categories file
    @Override
    public List<IncomeCategory> load() {
        List<IncomeCategory> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INCOME_CATEGORIES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String categoryName = line.trim();
                IncomeCategory category = new IncomeCategory(categoryName);
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
