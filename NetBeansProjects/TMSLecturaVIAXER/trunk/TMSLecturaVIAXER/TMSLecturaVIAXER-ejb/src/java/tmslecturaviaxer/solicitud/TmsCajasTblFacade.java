/*
 * TmsCajasTblFacade.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmslecturaviaxer.entidad.TmsCajasTbl;


/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCajasTblFacade implements TmsCajasTblFacadeRemote {

    @PersistenceContext(unitName="tmslecturaviaxer-ejbPU")
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
    
    public TmsCajasTbl buscarCajaPorEquipo(String nomEq){
      List<TmsCajasTbl> cajax = (List<TmsCajasTbl>) em.createNamedQuery("TmsCajasTbl.findByNombreEquipo").setParameter("nombreEquipo",nomEq).getResultList();
      if(cajax.size()==0)
          return null;
      TmsCajasTbl caja = cajax.get(0);
      em.refresh(caja);
      //System.out.println("El tama�o de la coleccion de Cajas_Impresoras es de "+caja.getTmsCajasImpresorasTblCollection().size());  
      return caja;
    }
    
 public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }

   public Object queryBuscaTerminalId(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }     
   
 public Object queryBuscaTerminalLocal(){
   return   em.createNativeQuery("select  est.ESTADO_ID from tms_base_datos_config_tbl base ,tms_estados_tbl est where esquema_propio = 'S' and est.ESTADO_NOMBRE = base.nombre_terminal" ).getSingleResult();
  }     
   
public Object queryBuscaImpresoraTikets(long cajaId){
   String consulta = "select impres.IMPRESORA_NOMBRE,  cajasimpres.SALIDA_IMPRESION " +
           "from " +
           "tms_cajas_tbl			cajas " +
           ",tms_impresoras_tbl		impres " +
           ",tms_cajas_impresoras_tbl cajasimpres " +
           "where cajas.CAJA_ID = "+cajaId+" " +
           "and 	cajasimpres.CAJA_ID = cajas.CAJA_ID " +
           "and   impres.IMPRESORA_ID = cajasimpres.IMPRESORA_ID " +
           "and   cajasimpres.ACTIVIDAD_IMPRESORA = 'TICKETS'";
    return   em.createNativeQuery(consulta ).getResultList();
  } 
}
