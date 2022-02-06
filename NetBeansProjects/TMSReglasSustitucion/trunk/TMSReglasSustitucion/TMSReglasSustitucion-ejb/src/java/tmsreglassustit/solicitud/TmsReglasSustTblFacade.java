/*
 * TmsReglasSustTblFacade.java
 *
 * Created on 21 de octubre de 2007, 07:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsreglassustit.entidad.TmsReglasSustTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsReglasSustTblFacade implements TmsReglasSustTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsReglasSustTblFacade */
    public TmsReglasSustTblFacade() {
    }

    public void create(TmsReglasSustTbl tmsReglasSustTbl) {
        em.persist(tmsReglasSustTbl);
    }

    public void edit(TmsReglasSustTbl tmsReglasSustTbl) {
        em.merge(tmsReglasSustTbl);
    }

    public void destroy(TmsReglasSustTbl tmsReglasSustTbl) {
        //em.merge(tmsReglasSustTbl);
        //em.remove(tmsReglasSustTbl);
        em.createNativeQuery("delete from tms_reglas_sust_tbl rs where rs.REGLA_SUSTITUCION_ID = "+tmsReglasSustTbl.getReglaSustitucionId()).executeUpdate();
    }

    public TmsReglasSustTbl find(Object pk) {
        return (TmsReglasSustTbl) em.find(TmsReglasSustTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsReglasSustTbl as o").getResultList();
    }

  public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }
  
  
  public List<TmsReglasSustTbl> buscaReglas(long idFlotilla){
      List<TmsReglasSustTbl> lista = (List<TmsReglasSustTbl>) em.createNativeQuery("select * from tms_reglas_sust_tbl reg where reg.FLOTILLA_ID = "+idFlotilla+" order by reg.PRIORIDAD ",TmsReglasSustTbl.class ).getResultList();
      if(lista.size()>0)
      {
          for(int i=0; i<lista.size(); i++)
              em.refresh(lista.get(i));
      }
      return lista;
  }

}
