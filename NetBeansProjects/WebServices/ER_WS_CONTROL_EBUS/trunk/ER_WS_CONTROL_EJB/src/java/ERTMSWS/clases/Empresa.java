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
public class Empresa implements Serializable{

   private long EmpresaId;
   private String EmpresaNombre;
   private String EmpresaDescripcion;

   public Empresa(){

   }

    /**
     * @return the EmpresaId
     */
    public long getEmpresaId() {
        return EmpresaId;
    }

    /**
     * @param EmpresaId the EmpresaId to set
     */
    public void setEmpresaId(long EmpresaId) {
        this.EmpresaId = EmpresaId;
    }

    /**
     * @return the EmpresaNombre
     */
    public String getEmpresaNombre() {
        return EmpresaNombre;
    }

    /**
     * @param EmpresaNombre the EmpresaNombre to set
     */
    public void setEmpresaNombre(String EmpresaNombre) {
        this.EmpresaNombre = EmpresaNombre;
    }

    /**
     * @return the EmpresaDescripcion
     */
    public String getEmpresaDescripcion() {
        return EmpresaDescripcion;
    }

    /**
     * @param EmpresaDescripcion the EmpresaDescripcion to set
     */
    public void setEmpresaDescripcion(String EmpresaDescripcion) {
        this.EmpresaDescripcion = EmpresaDescripcion;
    }


}
