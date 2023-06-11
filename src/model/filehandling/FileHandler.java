package model.filehandling;

import java.util.ArrayList;
import java.util.List;

public interface FileHandler<T> {
    void save(List<T> items);
    List<T> load();

    // Overloaded methods for handling individual items
    default void save(T item) {
        List<T> items = new ArrayList<>();
        items.add(item);
        save(items);
    }

    default T loadSingle() {
        List<T> items = load();
        if (items != null && !items.isEmpty()) {
            return items.get(0);
        }
        return null;
    }
}