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
public class ServicioVta implements Serializable{
   private long ServicioId;
   private String ServicioNombre;
   private String ServicioDescripcion;

   public ServicioVta(){

   }

    /**
     * @return the ServicioId
     */
    public long getServicioId() {
        return ServicioId;
    }

    /**
     * @param ServicioId the ServicioId to set
     */
    public void setServicioId(long ServicioId) {
        this.ServicioId = ServicioId;
    }

    /**
     * @return the ServicioNombre
     */
    public String getServicioNombre() {
        return ServicioNombre;
    }

    /**
     * @param ServicioNombre the ServicioNombre to set
     */
    public void setServicioNombre(String ServicioNombre) {
        this.ServicioNombre = ServicioNombre;
    }

    /**
     * @return the ServicioDescripcion
     */
    public String getServicioDescripcion() {
        return ServicioDescripcion;
    }

    /**
     * @param ServicioDescripcion the ServicioDescripcion to set
     */
    public void setServicioDescripcion(String ServicioDescripcion) {
        this.ServicioDescripcion = ServicioDescripcion;
    }

}
