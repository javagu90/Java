/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author imunoz
 */
public class budgetDataPeriodResponse {
    
    private ArrayList<BudgetDataTbl> budgetDataList;

    public ArrayList<BudgetDataTbl> getBudgetDataList() {
        return budgetDataList;
    }

    public void setBudgetDataList(ArrayList<BudgetDataTbl> budgetDataList) {
        this.budgetDataList = budgetDataList;
    }
    
}
