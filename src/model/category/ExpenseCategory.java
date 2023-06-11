package model.category;

public class ExpenseCategory extends Category {
    
    private int budgetLimit;

    public ExpenseCategory(String name, int budgetLimit) {
        super(name);
        this.budgetLimit = budgetLimit;
    }
    
    public int getBudgetLimit() {
        return budgetLimit;
    }
    
}
