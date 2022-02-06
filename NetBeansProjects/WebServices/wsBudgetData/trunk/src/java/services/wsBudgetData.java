/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import entities.budgetDataPeriodResponse;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import solicitud.BudgetDataSessionRemote;

/**
 *
 * @author vgonzalez
 */
@WebService()
public class wsBudgetData {
    @EJB
    private BudgetDataSessionRemote budgetDataSession;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getBudgetDataPeriod")
    public budgetDataPeriodResponse getBudgetDataPeriod(@WebParam(name = "pBudgetName") String pBudgetName, @WebParam(name = "pGerenciaName") String pGerenciaName, @WebParam(name = "pPeriodName") String pPeriodName, @WebParam(name = "pAmountType") String pAmountType) {
        budgetDataPeriodResponse respuesta =  new budgetDataPeriodResponse();
        respuesta.setBudgetDataList(budgetDataSession.getPresuDataPorPeriodo(pBudgetName, pGerenciaName, pPeriodName, pAmountType));
        return respuesta;
    }

}
