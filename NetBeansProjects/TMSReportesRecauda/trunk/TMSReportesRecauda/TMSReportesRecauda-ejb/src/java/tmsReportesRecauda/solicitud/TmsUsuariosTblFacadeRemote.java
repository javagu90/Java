/*
 * TmsUsuariosTblFacadeRemote.java
 *
 * Created on 9 de mayo de 2008, 03:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsReportesRecauda.solicitud;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;
import tmsReportesRecauda.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsUsuariosTblFacadeRemote {
    void create(TmsUsuariosTbl tmsUsuariosTbl);

    void edit(TmsUsuariosTbl tmsUsuariosTbl);

    void destroy(TmsUsuariosTbl tmsUsuariosTbl);

    TmsUsuariosTbl find(Object pk);

    List findAll();

    int buscaCortesPendientes(String fechai, String fechaf);
    
    Vector buscaEmpresas();

    Vector buscaServicios();

    java.util.Vector buscaDatosTerminal();

    java.lang.Object queryActividadSesionCorteFinDiaGetId();

    boolean corteDummyFinDia(long usuarioId);

    java.lang.String _ObtieneFechaHoraBD();

    long addSesionCorteFinDia(java.lang.String PrefijoTerminal, tmsReportesRecauda.TmsSesionActividadesTbl tmsSesionActividadesTbl, java.lang.String fecha);
}
