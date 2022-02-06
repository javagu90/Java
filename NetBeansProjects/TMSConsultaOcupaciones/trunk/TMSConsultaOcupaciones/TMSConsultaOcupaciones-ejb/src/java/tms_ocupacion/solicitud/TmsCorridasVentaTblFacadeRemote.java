/*
 * TmsCorridasVentaTblFacadeRemote.java
 *
 * Created on 9 de octubre de 2007, 03:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCorridasVentaTblFacadeRemote {
    void create(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    void edit(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    void destroy(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    TmsCorridasVentaTbl find(Object pk);

    List findAll();

    java.util.List buscarCorridasPorServicio(Timestamp feha1, Timestamp fecha2, String servicio);

    java.util.List buscarCorridasPorAutobus(Timestamp feha1, Timestamp fecha2, String autobus);

    java.util.List buscarCorridasPorOperador(Timestamp feha1, Timestamp fecha2, String operador);

    java.util.List buscarCorridasPorFecha(Timestamp feha1, Timestamp fecha2);

    java.util.List buscarCorridasPorOperadorAutobus(Timestamp feha1, Timestamp fecha2, String operador, String bus);

    java.util.List buscarCorridasPorOperadorServicio(Timestamp feha1, Timestamp fecha2, String operador, String servic);

    java.util.List buscarCorridasPorOperadorAutobusServicio(Timestamp feha1, Timestamp fecha2, String operador, String servic, String bus);

    java.util.List buscarCorridasPorAutobusServicio(Timestamp feha1, Timestamp fecha2, String autobus, String servicio);

    tms_ocupacion.entidad.TmsCorridasVentaTbl actualizarCorrida(TmsCorridasVentaTbl corrida);

    java.util.List<tms_ocupacion.entidad.TmsCorridasVentaTbl> buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus);

    java.util.Vector findRemota(long idRemota, String esquema);



    
}
