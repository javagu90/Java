/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitud;

import entities.BudgetDataTbl;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author imunoz
 */
@Remote
public interface BudgetDataSessionRemote {

    ArrayList<BudgetDataTbl> getPresuDataPorPeriodo(String pBudgetName, String pGerenciaName, String pPeriodName, String pAmountType);
    
}
