/*
 * TmsReglasSustTblFacadeRemote.java
 *
 * Created on 21 de octubre de 2007, 07:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsreglassustit.entidad.TmsReglasSustTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsReglasSustTblFacadeRemote {
    void create(TmsReglasSustTbl tmsReglasSustTbl);

    void edit(TmsReglasSustTbl tmsReglasSustTbl);

    void destroy(TmsReglasSustTbl tmsReglasSustTbl);

    TmsReglasSustTbl find(Object pk);

    List findAll();

    java.lang.Object fechaServidor();

    java.util.List<tmsreglassustit.entidad.TmsReglasSustTbl> buscaReglas(long idFlotilla);
    
}
