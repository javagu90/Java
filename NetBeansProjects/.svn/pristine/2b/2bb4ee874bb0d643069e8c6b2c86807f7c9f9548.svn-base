/*
 * TmsOperadoresTblFacadeRemote.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsedosoperador.entidad.TmsOperadoresTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsOperadoresTblFacadeRemote {
    void create(TmsOperadoresTbl tmsOperadoresTbl);

    void edit(TmsOperadoresTbl tmsOperadoresTbl);

    void destroy(TmsOperadoresTbl tmsOperadoresTbl);

    TmsOperadoresTbl find(Object pk);

    List findAll();

    java.util.List<tmsedosoperador.entidad.TmsOperadoresTbl> findByClaveOperador(String clave);

    java.lang.Object fechaServidor();

    java.lang.Object queryBuscaNombreEsquema();

    boolean ejecutaReplicacion(String Tabla, String filaId, String esquemaPropioOrigen, String vModo);

}
