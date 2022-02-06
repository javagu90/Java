/*
 * TmsPagosActAdicionalesTblFacade.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsPagosActAdicionalesTblFacade implements TmsPagosActAdicionalesTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsPagosActAdicionalesTblFacade */
    public TmsPagosActAdicionalesTblFacade() {
    }

    public BigDecimal create(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl, String ter) {
           em.persist(tmsPagosActAdicionalesTbl);
           tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional(tmsPagosActAdicionalesTbl.getPagoActividadAdicionalId().toBigInteger());
           String ids = ""+tmsPagosActAdicionalesTbl.getPagoActividadAdicionalId();
           String idnuevo = ter+""+ids;
           long idn = Long.valueOf(idnuevo);
           tmsPagosActAdicionalesTbl.setPagoActividadAdicionalId(BigDecimal.valueOf(idn));
           edit(tmsPagosActAdicionalesTbl);
           return BigDecimal.valueOf(Long.valueOf(tmsPagosActAdicionalesTbl.getReferenciaPagoActAdicional().toString()));
    }

    public void edit(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl) {
        em.merge(tmsPagosActAdicionalesTbl);
    }

    public void destroy(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl) {
        em.merge(tmsPagosActAdicionalesTbl);
        em.remove(tmsPagosActAdicionalesTbl);
    }

    public TmsPagosActAdicionalesTbl find(Object pk) {
        return (TmsPagosActAdicionalesTbl) em.find(TmsPagosActAdicionalesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsPagosActAdicionalesTbl as o").getResultList();
    }

    public List<TmsPagosActAdicionalesTbl> buscaActividaesPreingresadas(BigInteger Idoperador){
      List<TmsPagosActAdicionalesTbl> listaPreingreesados = em.createNamedQuery("TmsPagosActAdicionalesTbl.findByOperadorId").setParameter("operadorId", Idoperador).getResultList();
            for(int i=0; i<listaPreingreesados.size(); i++)
                {
                    TmsPagosActAdicionalesTbl pago = listaPreingreesados.get(i);
                    em.merge(pago);
                    System.out.println("la coleccion del pago "+pago.getTipoActividadAdicionalId().getAccionId().getAccion()+" es de "+pago.getTipoActividadAdicionalId().getTmsActDatosAdicionalesTblCollection().size());
                }

        return listaPreingreesados;
    }

     public List<TmsPagosActAdicionalesTbl> buscaActividaesPorReferencia(BigInteger ref){
      List<TmsPagosActAdicionalesTbl> listaCancelados = em.createNamedQuery("TmsPagosActAdicionalesTbl.findByReferenciaPagoActAdicional").setParameter("referenciaPagoActAdicional", ref).getResultList();
            for(int i=0; i<listaCancelados.size(); i++)
                {
                    TmsPagosActAdicionalesTbl pago = listaCancelados.get(i);
                    em.merge(pago);
                    System.out.println("la coleccion del pago "+pago.getTipoActividadAdicionalId().getAccionId().getAccion()+" es de "+pago.getTipoActividadAdicionalId().getTmsActDatosAdicionalesTblCollection().size());
                }

        return listaCancelados;
    }
   
    
}
