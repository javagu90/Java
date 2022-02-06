/*
 * TmsFormatosBoletoTblFacade.java
 *
 * Created on 3 de septiembre de 2007, 09:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsFormatosBoletoTbl;
import tmsactividadesadicionales.excepciones.formatoNoEncontradoException;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsFormatosBoletoTblFacade implements TmsFormatosBoletoTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsFormatosBoletoTblFacade */
    public TmsFormatosBoletoTblFacade() {
    }

    public void create(TmsFormatosBoletoTbl tmsFormatosBoletoTbl) {
        em.persist(tmsFormatosBoletoTbl);
    }

    public void edit(TmsFormatosBoletoTbl tmsFormatosBoletoTbl) {
        em.merge(tmsFormatosBoletoTbl);
    }

    public void destroy(TmsFormatosBoletoTbl tmsFormatosBoletoTbl) {
        em.merge(tmsFormatosBoletoTbl);
        em.remove(tmsFormatosBoletoTbl);
    }

    public TmsFormatosBoletoTbl find(Object pk) throws formatoNoEncontradoException{
        TmsFormatosBoletoTbl formato = null;
        try {
            formato = em.find(TmsFormatosBoletoTbl.class, pk);
        }  catch(NoResultException e) {
            throw new formatoNoEncontradoException("Error al buscar formatos de Boletos");
        }
        return formato;
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsFormatosBoletoTbl as o").getResultList();
    }
    
}
