/*
 * TmsCajasTblFacade.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsCajasImpresorasTbl;
import tmsactividadesadicionales.entidad.TmsCajasTbl;
import tmsactividadesadicionales.excepciones.CajaNoEncontradaException;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCajasTblFacade implements TmsCajasTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbPU")
    private EntityManager em;
    
    /** Creates a new instance of TmsCajasTblFacade */
    public TmsCajasTblFacade() {
    }

    public void create(TmsCajasTbl tmsCajasTbl) {
        em.persist(tmsCajasTbl);
    }

    public void edit(TmsCajasTbl tmsCajasTbl) {
        em.merge(tmsCajasTbl);
    }

    public void destroy(TmsCajasTbl tmsCajasTbl) {
        em.merge(tmsCajasTbl);
        em.remove(tmsCajasTbl);
    }

    public TmsCajasTbl find(Object pk) {
        return (TmsCajasTbl) em.find(TmsCajasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCajasTbl as o").getResultList();
    }
    
    public TmsCajasTbl buscarCajaPorEquipo(String nomEq) throws CajaNoEncontradaException{
        TmsCajasTbl caja;
        
        try {
            caja = (TmsCajasTbl) em.createNamedQuery("TmsCajasTbl.findByNombreEquipo").setParameter("nombreEquipo", nomEq).getSingleResult();
        } catch(NoResultException e) {
            caja = null;
            throw new CajaNoEncontradaException("Error al buscar cajas");
        }
      em.refresh(caja);
      System.out.println("El tamaño de la coleccion de Cajas_Impresoras es de "+caja.getTmsCajasImpresorasTblCollection().size());  
         if(caja.getTmsCajasImpresorasTblCollection().size()>0)
         {
            Iterator ipg = caja.getTmsCajasImpresorasTblCollection().iterator();
            while(ipg.hasNext()){
              TmsCajasImpresorasTbl cajaimp = (TmsCajasImpresorasTbl)ipg.next();
              em.refresh(cajaimp);
         } 
        }       return caja;
    }
    
   public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }

}
