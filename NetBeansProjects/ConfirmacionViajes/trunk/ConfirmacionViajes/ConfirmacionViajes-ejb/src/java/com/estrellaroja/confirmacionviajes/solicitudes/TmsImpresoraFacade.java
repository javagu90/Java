/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsImpresora;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author EKS Victor
 */
@Stateless
public class TmsImpresoraFacade extends AbstractFacade<TmsImpresora> implements TmsImpresoraFacadeRemote {
  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsImpresoraFacade() {
    super(TmsImpresora.class);
  }

  public List<TmsImpresora> findByCaja(String nombre_caja) {
    try {
      Query query = em.createNamedQuery("Impresora.findByPrefijo");
      query.setParameter("NOMBRE_CAJA", nombre_caja);
      List<TmsImpresora> result =  query.getResultList();
      return result;
    } catch (Exception e) {
      System.out.println("Exception: "+e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

}
