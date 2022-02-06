/*
 * TmsActAdicionalesTblFacade.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsActAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsActAdicionalesTblFacade implements TmsActAdicionalesTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsActAdicionalesTblFacade */
    public TmsActAdicionalesTblFacade() {
    }

    public void create(TmsActAdicionalesTbl tmsActAdicionalesTbl) {
        em.persist(tmsActAdicionalesTbl);
    }

    public void edit(TmsActAdicionalesTbl tmsActAdicionalesTbl) {
        em.merge(tmsActAdicionalesTbl);
    }

    public void destroy(TmsActAdicionalesTbl tmsActAdicionalesTbl) {
        em.merge(tmsActAdicionalesTbl);
        em.remove(tmsActAdicionalesTbl);
    }

    public TmsActAdicionalesTbl find(Object pk) {
        TmsActAdicionalesTbl actividad = (TmsActAdicionalesTbl) em.find(TmsActAdicionalesTbl.class, pk);
        System.out.println("La Coleccion de Datos adicionales de la actividad es de: "+actividad.getTmsActDatosAdicionalesTblCollection().size());
        return actividad;
    }

    public List findAll() {
        List listado = em.createQuery("select object(o) from TmsActAdicionalesTbl as o where o.habilitado = 'S'").getResultList();
        for(int i=0; i<listado.size(); i++)
        {
            TmsActAdicionalesTbl actividad = (TmsActAdicionalesTbl) listado.get(i);
            System.out.println("La Coleccion de Datos adicionales de la actividad es de: "+actividad.getTmsActDatosAdicionalesTblCollection().size());
        }
        return listado;
    }
    
}
