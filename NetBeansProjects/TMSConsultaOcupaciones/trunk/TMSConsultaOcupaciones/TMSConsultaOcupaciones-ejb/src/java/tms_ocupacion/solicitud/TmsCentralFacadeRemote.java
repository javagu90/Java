/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCentralFacadeRemote {

    public java.lang.Object buscarDatos(java.lang.String autobus, java.lang.String operador, java.lang.String servicio, java.lang.String empresa, java.lang.String origen, java.lang.String destino, java.lang.String fi, java.lang.String ff, java.lang.String hi, java.lang.String tabla, java.lang.String rutas, boolean extras);

    public tms_ocupacion.entidad.TmsCorridasVentaTbl find(java.lang.Object pk);

    public java.lang.Object buscaDatosOcupacionPorSistema(java.lang.String corrida);
    
}
