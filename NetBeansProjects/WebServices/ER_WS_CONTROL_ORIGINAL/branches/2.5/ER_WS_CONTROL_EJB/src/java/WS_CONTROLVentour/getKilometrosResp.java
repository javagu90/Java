/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import WS_CONTROL.Ventour.entidades.TramoRuta;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getKilometrosResp  implements  Serializable {

   private List<TramoRuta> tramosRuta ;
   private double TotalKmts =0;
   private boolean OperacionExitosa;
   private String ErrorCode;
   private String ErrorMsg;

  

    /**
     * @return the OperacionExitosa
     */
    public boolean isOperacionExitosa() {
        return OperacionExitosa;
    }
  
    /**
     * @param OperacionExitosa the OperacionExitosa to set
     */
    public void setOperacionExitosa(boolean OperacionExitosa) {
        this.OperacionExitosa = OperacionExitosa;
    }

    /**
     * @return the ErrorCode
     */
    public String getErrorCode() {
        return ErrorCode;
    }

    /**
     * @param ErrorCode the ErrorCode to set
     */
    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * @return the ErrorMsg
     */
    public String getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * @param ErrorMsg the ErrorMsg to set
     */
    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    /**
     * @return the tramosRuta
     */
    public List<TramoRuta> getTramosRuta() {
        return tramosRuta;
    }

    /**
     * @param tramosRuta the tramosRuta to set
     */
    public void setTramosRuta(List<TramoRuta> tramosRuta) {
        this.tramosRuta = tramosRuta;
    }

    /**
     * @return the TotalKmts
     */
    public double getTotalKmts() {
        return TotalKmts;
    }

    /**
     * @param TotalKmts the TotalKmts to set
     */
    public void setTotalKmts(double TotalKmts) {
        this.TotalKmts = TotalKmts;
    }

}
