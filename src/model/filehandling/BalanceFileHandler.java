package model.filehandling;

import java.io.*;
import java.util.List;

public class BalanceFileHandler implements FileHandler<Integer> {
    private static final String BALANCE_FILE_PATH = "src/saved/balance.txt";

    // Saves the balance to the balance file
    @Override
    public void save(Integer balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_FILE_PATH))) {
            writer.write(String.valueOf(balance));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads a single balance from the balance file
    @Override
    public Integer loadSingle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BALANCE_FILE_PATH))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Not implemented, as saving a list of balances is not supported
    @Override
    public void save(List<Integer> items) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Not implemented, as loading a list of balances is not supported
    @Override
    public List<Integer> load() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
