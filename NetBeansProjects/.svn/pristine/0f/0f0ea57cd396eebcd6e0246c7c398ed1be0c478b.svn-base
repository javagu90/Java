/*
 * TmsFormatosBoletoTblFacadeRemote.java
 *
 * Created on 3 de septiembre de 2007, 09:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsFormatosBoletoTbl;
import tmsactividadesadicionales.excepciones.formatoNoEncontradoException;


/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsFormatosBoletoTblFacadeRemote {
    void create(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    void edit(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    void destroy(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    List findAll();

    tmsactividadesadicionales.entidad.TmsFormatosBoletoTbl find(Object pk) throws formatoNoEncontradoException;
    
}
