/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Timestamp;

/**
 *
 * @author vgonzalez
 */
public class Promocion {
    private long rutaId;
    private int cadaNBoletos = 1;
    private int xBolPorCadaNBoletos = 1;
    private boolean soloAdultos = false;
    private Timestamp fechaLimite = null;

    public Promocion(){

    }


    /**
     * @return the cadaNBoletos
     */
    public int getCadaNBoletos() {
        return cadaNBoletos;
    }

    /**
     * @param cadaNBoletos the cadaNBoletos to set
     */
    public void setCadaNBoletos(int cadaNBoletos) {
        this.cadaNBoletos = cadaNBoletos;
    }

    /**
     * @return the xBolPorCadaNBoletos
     */
    public int getxBolPorCadaNBoletos() {
        return xBolPorCadaNBoletos;
    }

    /**
     * @param xBolPorCadaNBoletos the xBolPorCadaNBoletos to set
     */
    public void setxBolPorCadaNBoletos(int xBolPorCadaNBoletos) {
        this.xBolPorCadaNBoletos = xBolPorCadaNBoletos;
    }

    /**
     * @return the soloAdultos
     */
    public boolean isSoloAdultos() {
        return soloAdultos;
    }

    /**
     * @param soloAdultos the soloAdultos to set
     */
    public void setSoloAdultos(boolean soloAdultos) {
        this.soloAdultos = soloAdultos;
    }

    /**
     * @return the fechaLimite
     */
    public Timestamp getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite the fechaLimite to set
     */
    public void setFechaLimite(Timestamp fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return the rutaId
     */
    public long getRutaId() {
        return rutaId;
    }

    /**
     * @param rutaId the rutaId to set
     */
    public void setRutaId(long rutaId) {
        this.rutaId = rutaId;
    }

    public void setValor(String parametro,String valor)
    {
            if(parametro.equals("P_VTA_NUM_BOL_PROM"))
                this.setCadaNBoletos(Integer.valueOf(valor));
            if(parametro.equals("P_VTA_NUM_BOL_X_PROM"))
                this.setxBolPorCadaNBoletos(Integer.valueOf(valor));
            if(parametro.equals("P_VTA_PROM_SOLO_ADULT"))
                this.setSoloAdultos(valor.equals("S"));
            if(parametro.equals("P_VTA_FECHA_LIM_PROM"))
            {
                String[] array = valor.split("/");
                Timestamp t= null;
                t = t.valueOf(array[2]+"-"+array[1]+"-"+array[0]+" 00:00:00");
                this.setFechaLimite(t);

            }

    }

}
