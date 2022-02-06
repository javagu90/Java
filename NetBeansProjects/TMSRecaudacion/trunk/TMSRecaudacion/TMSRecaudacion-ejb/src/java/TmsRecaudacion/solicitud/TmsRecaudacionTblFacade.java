/*
 * TmsRecaudacionTblFacade.java
 *
 * Created on 3 de noviembre de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecaudacionTbl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRecaudacionTblFacade implements TmsRecaudacionTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsRecaudacionTblFacade */
    public TmsRecaudacionTblFacade() {
    }

    public TmsRecaudacionTbl create(TmsRecaudacionTbl tmsRecaudacionTbl, String ter, boolean imprimeDiesel) throws EJBException{
        em.persist(tmsRecaudacionTbl);
        tmsRecaudacionTbl.setRecaudacionReferencia(tmsRecaudacionTbl.getRecaudacionId().toBigInteger());
        System.out.println("tmsRecaudacionTbl.getRecaudacionId(setRecaudacionReferencia): "+tmsRecaudacionTbl.getRecaudacionId());
        String ids = ""+tmsRecaudacionTbl.getRecaudacionId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsRecaudacionTbl.setRecaudacionId(BigDecimal.valueOf(idn));
        System.out.println("nuevoId(): "+idn);
        /*try
        {
            edit(tmsRecaudacionTbl);
        }catch(EJBException e){
            System.out.println("*************** EJBException Cachada *****************");
            e.printStackTrace();
        }*/
       edit(tmsRecaudacionTbl);
       String qry =  "update TMS_RECAUDACION_TBL R set R.ADICIONAL3 = TMS_RECAUDACION_DIESEL_SEQ.nextval where  RECAUDACION_ID = "+tmsRecaudacionTbl.getRecaudacionId();
       if(imprimeDiesel)
       { 
           em.createNativeQuery(qry).executeUpdate();
           em.refresh(tmsRecaudacionTbl);
       }
       return tmsRecaudacionTbl;
    }

    public void edit(TmsRecaudacionTbl tmsRecaudacionTbl) {
        em.merge(tmsRecaudacionTbl);
    }

    public void destroy(TmsRecaudacionTbl tmsRecaudacionTbl) {
        em.merge(tmsRecaudacionTbl);
        em.remove(tmsRecaudacionTbl);
    }

    public TmsRecaudacionTbl find(Object pk) {
        TmsRecaudacionTbl rec = (TmsRecaudacionTbl) em.find(TmsRecaudacionTbl.class, pk);
        em.refresh(rec);
        return  rec;
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRecaudacionTbl as o").getResultList();
    }
 
    public List<TmsRecaudacionTbl> busquedaPagosPorTarjetaId(BigInteger idTar){
        return em.createNamedQuery("TmsRecaudacionTbl.findByTarjetaViajeId").setParameter("tarjetaViajeId",idTar).getResultList();
    }
}
