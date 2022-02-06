/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class Terminal implements Serializable{

   private long TerminalId;
   private String TerminalNombre;
   private String TerminalDescripcion;

   public Terminal(){

   }

    /**
     * @return the TerminalId
     */
    public long getTerminalId() {
        return TerminalId;
    }

    /**
     * @param TerminalId the TerminalId to set
     */
    public void setTerminalId(long TerminalId) {
        this.TerminalId = TerminalId;
    }

    /**
     * @return the TerminalNombre
     */
    public String getTerminalNombre() {
        return TerminalNombre;
    }

    /**
     * @param TerminalNombre the TerminalNombre to set
     */
    public void setTerminalNombre(String TerminalNombre) {
        this.TerminalNombre = TerminalNombre;
    }

    /**
     * @return the TerminalDescripcion
     */
    public String getTerminalDescripcion() {
        return TerminalDescripcion;
    }

    /**
     * @param TerminalDescripcion the TerminalDescripcion to set
     */
    public void setTerminalDescripcion(String TerminalDescripcion) {
        this.TerminalDescripcion = TerminalDescripcion;
    }


}
