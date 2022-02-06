/*
 * ClaseTiposPasajeConvenio.java
 *
 * Created on 1 de noviembre de 2010, 04:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

/**
 *
 * @author vgonzalez
 */
public class ClaseTiposPasajeConvenio {
    private String tipoPasaje;
    private long disponibles;
    private float tarifaSencillo;
    private float tarifaRedondo;
    
    /** Creates a new instance of ClaseTiposPasajeConvenio */
    public ClaseTiposPasajeConvenio() {
    }

    public String getTipoPasaje() {
        return tipoPasaje;
    }

    public void setTipoPasaje(String tipoPasaje) {
        this.tipoPasaje = tipoPasaje;
    }

    public float getTarifaSencillo() {
        return tarifaSencillo;
    }

    public void setTarifaSencillo(float tarifaSencillo) {
        this.tarifaSencillo = tarifaSencillo;
    }

    public float getTarifaRedondo() {
        return tarifaRedondo;
    }

    public void setTarifaRedondo(float tarifaRedondo) {
        this.tarifaRedondo = tarifaRedondo;
    }

    public long getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(long disponibles) {
        this.disponibles = disponibles;
    }

    public String toStiring() {
        return tipoPasaje;
    }
    
}
