/*
 * TmsActividadesSesionTblFacade.java
 *
 * Created on 23 de octubre de 2007, 08:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.ActividadNoEncontradoException;
import TmsRecaudacion.entidad.TmsActividadesSesionTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsActividadesSesionTblFacade implements TmsActividadesSesionTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsActividadesSesionTblFacade */
    public TmsActividadesSesionTblFacade() {
    }

    public void create(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.persist(tmsActividadesSesionTbl);
    }

    public void edit(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.merge(tmsActividadesSesionTbl);
    }

    public void destroy(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.merge(tmsActividadesSesionTbl);
        em.remove(tmsActividadesSesionTbl);
    }

    public TmsActividadesSesionTbl find(Object pk) {
        return (TmsActividadesSesionTbl) em.find(TmsActividadesSesionTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsActividadesSesionTbl as o").getResultList();
    }
    
    public  TmsActividadesSesionTbl buscarActidadPorClave(String clave){
        return (TmsActividadesSesionTbl) em.createNamedQuery("TmsActividadesSesionTbl.findByClaveActividad").setParameter("claveActividad",clave).getSingleResult();
    }
    
    public TmsActividadesSesionTbl getActividadPorClave(String claveActividad) throws ActividadNoEncontradoException{
        TmsActividadesSesionTbl actividad;
        try{
            actividad = (TmsActividadesSesionTbl)em.createNamedQuery("TmsActividadesSesionTbl.findByClaveActividad").setParameter("claveActividad",claveActividad).getSingleResult();
        }catch(NoResultException ex){
            throw new ActividadNoEncontradoException("No se encontro la actividad");
        }
        return actividad;
    }    
}
