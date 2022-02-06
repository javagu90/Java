/*
 * TmsCortesTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsCortesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCortesTblFacadeRemote {
    void create(TmsCortesTbl tmsCortesTbl);

    void edit(TmsCortesTbl tmsCortesTbl);

    void destroy(TmsCortesTbl tmsCortesTbl);

    TmsCortesTbl find(Object pk);

    List findAll();

    /**
     * <code>select o from TmsFechahoraV o</code>
     */
    xertmsCorteReca.entidad.TmsFechahoraV queryTmsFechahoraVFindAll();

    java.util.List buscarCortes(String fecha, boolean pendiente, String ts);

    java.util.Vector buscaDatosServidorReportes();

    java.lang.Object fechaServidor();

    java.lang.Object buscaFolioCorte(String numUsuario);

    java.lang.Object buscaURL();

    java.util.List buscarTodosCortes();

    java.lang.Object queryBuscaNumeroPaqueteActual();

    
}
