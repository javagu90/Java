/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitud;

import entities.BudgetDataTbl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

/**
 *
 * @author imunoz
 */
@Stateless
public class BudgetDataSession implements BudgetDataSessionRemote {
    @Resource(name = "SUPROD_DB")
    private DataSource SUPROD_DB;
    OracleCallableStatement stmt;
 
    @Override
    public ArrayList<BudgetDataTbl> getPresuDataPorPeriodo(String pBudgetName, String pGerenciaName, String pPeriodName, String pAmountType) {
        Connection cnx=null;
        boolean bResultado=false;
        int i=0;
        ARRAY array;
        STRUCT obj;
        BudgetDataTbl[] budgetCollectionResp;
        BudgetDataTbl budgetTypeResp;
        ArrayList<BudgetDataTbl> budgetCollection = new ArrayList<BudgetDataTbl>();
        System.out.println("=> ****** Ejecutando con pBudgetName="+pBudgetName+", pGerenciaName="+pGerenciaName+", pPeriodName="+pPeriodName+", pAmountType="+pAmountType+",");   
        try {
            System.out.println("=> ****** Antes de  SUPROD_DB.getConnection()....");   
            cnx = SUPROD_DB.getConnection(); 
                   String q1 = 
                    "BEGIN "+
                      "XERREPS_PKG.XER_PRESUPUESTO_CST(?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            System.out.println("=> ****** Antes de  stmt = (OracleCallableStatement) cnx.prepareCall(q1)....");   
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            //ARRAY newArray = new ARRAY(desc, stmt.getConnection(), _strBoletos);
            //System.out.println(this.getCaja()+"=> _strBoletos = "+_strBoletos);
            //((OraclePreparedStatement)stmt).setArray(1, newArray);
            stmt.setString(1,pBudgetName);
            stmt.setString(2,pGerenciaName);
            stmt.setString(3,pPeriodName);
            stmt.setString(4,pAmountType);
            stmt.registerOutParameter(5,OracleTypes.ARRAY, "XER_PRESUP_COLLECTION_TYPE");
            System.out.println("=> ****** Antes de  stmt.execute()....");   
            bResultado=stmt.execute();
            System.out.println("Resultado de execute "+bResultado);
            System.out.println("=> ****** Despues de  stmt.execute()....");   
            //if(bResultado) {
                    array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                    ResultSet rs=array.getResultSet();                  

                    while(rs.next()){
                        obj= (STRUCT)rs.getObject(2);
                        Object[] attrs1=obj.getAttributes();                        
                        budgetTypeResp = new BudgetDataTbl();
                        budgetTypeResp.setBUDGET_NAME(String.valueOf(attrs1[0]));
                        budgetTypeResp.setPERIOD_NAME(String.valueOf(attrs1[1]));
                        budgetTypeResp.setAMOUNT_TYPE(String.valueOf(attrs1[2]));
                        budgetTypeResp.setEMPRESA(String.valueOf(attrs1[3]));
                        budgetTypeResp.setSUBCUENTA(String.valueOf(attrs1[4]));
                        budgetTypeResp.setBUDGET(Float.valueOf(attrs1[5].toString()));
                        budgetTypeResp.setENCUMBRANCE(Float.valueOf(attrs1[6].toString()));
                        budgetTypeResp.setACTUAL(Float.valueOf(attrs1[7].toString()));
                        budgetTypeResp.setFUNDS_AVAILABLE(Float.valueOf(attrs1[8].toString()));
                        budgetTypeResp.setREQ_ENCUMBRANCE_AMOUNT(Float.valueOf(attrs1[9].toString()));
                        budgetTypeResp.setPO_ENCUMBRANCE_AMOUNT(Float.valueOf(attrs1[10].toString()));
                        budgetTypeResp.setOTHER_ENCUMBRANCE_AMOUNT(Float.valueOf(attrs1[11].toString()));
                        budgetCollection.add(budgetTypeResp);
                    }
                    System.out.println("collection: "+budgetCollection.size());
            //}
            stmt.close();
            if(cnx!=null) cnx.close();
            
        } catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        i = budgetCollection.size();
        budgetCollectionResp = new BudgetDataTbl[i];
        for (int j=0;j<i;j++){
            budgetCollectionResp[j] = budgetCollection.get(j);
        }
        
        return budgetCollection;
    }
 
}