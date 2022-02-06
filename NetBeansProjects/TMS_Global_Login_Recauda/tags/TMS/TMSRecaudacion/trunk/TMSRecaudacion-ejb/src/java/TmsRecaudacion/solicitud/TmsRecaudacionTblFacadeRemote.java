/*
 * TmsRecaudacionTblFacadeRemote.java
 *
 * Created on 3 de noviembre de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecaudacionTbl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRecaudacionTblFacadeRemote {

    void edit(TmsRecaudacionTbl tmsRecaudacionTbl);

    void destroy(TmsRecaudacionTbl tmsRecaudacionTbl);

    TmsRecaudacionTbl find(Object pk);

    List findAll();

    java.util.List<TmsRecaudacion.entidad.TmsRecaudacionTbl> busquedaPagosPorTarjetaId(BigInteger idTar);

    TmsRecaudacion.entidad.TmsRecaudacionTbl create(TmsRecaudacionTbl tmsRecaudacionTbl, String ter, boolean imprimeDiesel) throws EJBException;

   
    
}
