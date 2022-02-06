/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xer_emv_dll;

/**
 *
 * @author imunoz
 */
public class xeremvcls {

    // Función nativa que llama a la dll puente
    public static native String[] validarEMV(String dbgSetUrl,String Tx_Amount,String Bs_User,String Bs_Pwd,String Bs_UsrTransaction,String Bs_Company,String Bs_Branch,String Bs_Country,String Cc_Type,String Cc_Name,String Cc_Number,
            String Cc_ExpMonth,String Cc_ExpYear,String Cc_CvvCsc,String Tx_Merchant,String Tx_Reference,String Tx_OperationType,String Tx_Currency,String Tx_Room,String Tx_Waiter,String Tx_Shifts,
            String Tx_Tip,String Tx_OperationNumber,String Tx_Auth,String Tx_Date,String Avs_Adress,String Avs_District,String Avs_Municipality,String Avs_City,String Avs_State,String Avs_zip,String Cc_AMEXCvCsc, String pModo);    
    static
    {
        System.loadLibrary("xeremvwraper");
    }
    
    public JClsPinPadTBResponse validarEMVDll(JClsPinPadTBRequest x) {
        JClsPinPadTBResponse z = new JClsPinPadTBResponse();
        System.out.println("Datos en validarEMVDll: "+x.getDbgSetUrl()+" -- "+x.getTx_Amount()+" -- "+x.getBs_User()+" -- "+x.getBs_Pwd()+" -- "+x.getBs_UsrTransaction()+" -- "+x.getBs_Company()+" -- "+x.getBs_Branch()+" -- "+x.getBs_Country()+" -- "+x.getCc_Type()+" -- "+x.getCc_Name()+" -- "+
                          x.getCc_Number()+" -- "+x.getCc_ExpMonth()+" -- "+x.getCc_ExpYear()+" -- "+x.getCc_CvvCsc()+" -- "+x.getTx_Merchant()+" -- "+x.getTx_Reference()+" -- "+x.getTx_OperationType()+" -- "+x.getTx_Currency()+" -- "+x.getTx_Room()+" -- "+x.getTx_Waiter()+" -- "+x.getTx_Shifts()+" -- "+
                          x.getTx_Tip()+" -- "+x.getTx_OperationNumber()+" -- "+x.getTx_Auth()+" -- "+x.getTx_Date()+" -- "+x.getAvs_Adress()+" -- "+x.getAvs_District()+" -- "+x.getAvs_Municipality()+" -- "+x.getAvs_City()+" -- "+x.getAvs_State()+" -- "+x.getAvs_zip()+" -- "+x.getCc_AMEXCvCsc()+" -- "+x.getPModo());
        // Llamar a la funcion de la DLL
        z.setDataArray(validarEMV(x.getDbgSetUrl(),x.getTx_Amount(),x.getBs_User(),x.getBs_Pwd(),x.getBs_UsrTransaction(),x.getBs_Company(),x.getBs_Branch(),x.getBs_Country(),x.getCc_Type(),x.getCc_Name(),
                          x.getCc_Number(),x.getCc_ExpMonth(),x.getCc_ExpYear(),x.getCc_CvvCsc(),x.getTx_Merchant(),x.getTx_Reference(),x.getTx_OperationType(),x.getTx_Currency(),x.getTx_Room(),x.getTx_Waiter(),x.getTx_Shifts(),
                          x.getTx_Tip(),x.getTx_OperationNumber(),x.getTx_Auth(),x.getTx_Date(),x.getAvs_Adress(),x.getAvs_District(),x.getAvs_Municipality(),x.getAvs_City(),x.getAvs_State(),x.getAvs_zip(),x.getCc_AMEXCvCsc(),x.getPModo()));
        return z;
    }
}
