/*
 * TmsOperadoresTblFacade.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsedosoperador.entidad.TmsOperadoresTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsOperadoresTblFacade implements TmsOperadoresTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsOperadoresTblFacade */
    public TmsOperadoresTblFacade() {
    }

    public void create(TmsOperadoresTbl tmsOperadoresTbl) {
        em.persist(tmsOperadoresTbl);
    }

    public void edit(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
    }

    public void destroy(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
        em.remove(tmsOperadoresTbl);
    }

    public TmsOperadoresTbl find(Object pk) {
        return (TmsOperadoresTbl) em.find(TmsOperadoresTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsOperadoresTbl as o").getResultList();
    }

   public List<TmsOperadoresTbl> findByClaveOperador(String clave) {
        List<TmsOperadoresTbl> listado = (List<TmsOperadoresTbl>)em.createNamedQuery("TmsOperadoresTbl.findByClaveOperador").setParameter("claveOperador",clave).getResultList();
        for(int i=0; i<listado.size(); i++)
            em.refresh(listado.get(i));
        return listado;
        
    } 

   public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }    
   
   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    

  public boolean ejecutaReplicacion(String Tabla, String filaId, String esquemaPropioOrigen, String vModo){
        String Consulta = 
            "DECLARE "+
            "vResultado VARCHAR2(1); "+
            "BEGIN "+
            "Tms_Replicacion_Pkg.TMS_REPLICAR_CONFIGURACION_PRC('"+Tabla+"','"+filaId+"','XERTMS','"+esquemaPropioOrigen+"','"+vModo+"',vResultado); "+
            "END;";
        try{
            int r = em.createNativeQuery(Consulta).executeUpdate();
            if(r>0) return true;
            return false;
        }catch(Exception ex){
            return false;
        }
    }

   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    

}
