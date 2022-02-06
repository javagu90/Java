/*
 * TmsTarjetasViajeTblFacadeRemote.java
 *
 * Created on 10 de diciembre de 2007, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsmodiftarviaje.solicitud;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;
import tmsmodiftarviaje.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsTarjetasViajeTblFacadeRemote {
    void create(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    void destroy(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    TmsTarjetasViajeTbl find(Object pk);

    List findAll();

    java.lang.Object buscaCorrida(long idCor);

    java.lang.Object buscaEstadoTajeta(String edo);

    tmsmodiftarviaje.entidad.TmsTarjetasViajeTbl buscaTarjetaPorFolio(String folio, BigInteger edo);

    java.lang.Object queryBuscaNombreEsquema();

    java.lang.Object fechaServidor();

    java.lang.Object buscaNombreEstadoTajeta(long id);

    java.lang.String buscaFuncion(String usuarioNumero, String funcion);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);
    
}
