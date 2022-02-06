/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TMSVtaProductosER.entidad;

import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class Tarifa implements java.io.Serializable{
    private long tarifaId;
    private float importeTarifa;
    private String tipoTarifa= "";
    private String tipoMonedaTarifa= "";
    private String fechaTarifa= "";
    private long terminalIdTarifa;
    private String terminalNombreTarifa = "";

    public Tarifa(){

    }

    public Tarifa(Vector t,Vector terminales){
       this.setTarifaId(Long.valueOf(t.get(9).toString()));
       this.setImporteTarifa(Float.valueOf(t.get(11).toString()));
       this.setTipoMonedaTarifa(t.get(12).toString());
       this.setFechaTarifa(t.get(13).toString());
       this.setTipoTarifa(t.get(14).toString());
       this.setTerminalIdTarifa(t.get(15)!=null?Long.valueOf(t.get(15).toString()):-1);
       this.setTerminalNombreTarifa("TODAS");
       if(t.get(15)!=null)
       {
           for(int y=0; y<terminales.size();y++)
           {
               Vector v = (Vector)terminales.get(y);
               if(v.get(0).toString().equals(t.get(15).toString()))
                 this.setTerminalNombreTarifa(v.get(1).toString());
           }
        }

    }

    public Tarifa(Vector t){
       //this.setTarifaId(Long.valueOf(t.get(9).toString()));
       this.setTipoTarifa(t.get(7).toString());
       this.setImporteTarifa(Float.valueOf(t.get(8).toString()));
       this.setTipoMonedaTarifa(t.get(9).toString());
       this.setFechaTarifa(t.get(10).toString());
       //this.setTerminalIdTarifa(t.get(15)!=null?Long.valueOf(t.get(15).toString()):-1);
       //this.setTerminalNombreTarifa("TODAS");
    }
    /**
     * @return the tarifaId
     */
    public long getTarifaId() {
        return tarifaId;
    }

    /**
     * @param tarifaId the tarifaId to set
     */
    public void setTarifaId(long tarifaId) {
        this.tarifaId = tarifaId;
    }

    /**
     * @return the importeTarifa
     */
    public float getImporteTarifa() {
        return importeTarifa;
    }

    /**
     * @param importeTarifa the importeTarifa to set
     */
    public void setImporteTarifa(float importeTarifa) {
        this.importeTarifa = importeTarifa;
    }

    /**
     * @return the tipoTarifa
     */
    public String getTipoTarifa() {
        return tipoTarifa;
    }

    /**
     * @param tipoTarifa the tipoTarifa to set
     */
    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    /**
     * @return the tipoMonedaTarifa
     */
    public String getTipoMonedaTarifa() {
        return tipoMonedaTarifa;
    }

    /**
     * @param tipoMonedaTarifa the tipoMonedaTarifa to set
     */
    public void setTipoMonedaTarifa(String tipoMonedaTarifa) {
        this.tipoMonedaTarifa = tipoMonedaTarifa;
    }

    /**
     * @return the fechaTarifa
     */
    public String getFechaTarifa() {
        return fechaTarifa;
    }

    /**
     * @param fechaTarifa the fechaTarifa to set
     */
    public void setFechaTarifa(String fechaTarifa) {
        this.fechaTarifa = fechaTarifa;
    }

    /**
     * @return the terminalIdTarifa
     */
    public long getTerminalIdTarifa() {
        return terminalIdTarifa;
    }

    /**
     * @param terminalIdTarifa the terminalIdTarifa to set
     */
    public void setTerminalIdTarifa(long terminalIdTarifa) {
        this.terminalIdTarifa = terminalIdTarifa;
    }

    /**
     * @return the terminalNombreTarifa
     */
    public String getTerminalNombreTarifa() {
        return terminalNombreTarifa;
    }

    /**
     * @param terminalNombreTarifa the terminalNombreTarifa to set
     */
    public void setTerminalNombreTarifa(String terminalNombreTarifa) {
        this.terminalNombreTarifa = terminalNombreTarifa;
    }


}
