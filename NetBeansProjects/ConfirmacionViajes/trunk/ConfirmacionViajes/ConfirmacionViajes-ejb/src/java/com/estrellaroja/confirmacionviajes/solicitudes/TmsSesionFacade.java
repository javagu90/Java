/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsSesion;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author EKS Victor
 */
@Stateless
public class TmsSesionFacade extends AbstractFacade<TmsSesion> implements TmsSesionFacadeRemote {

  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsSesionFacade() {
    super(TmsSesion.class);
  }

  public TmsSesion find(BigDecimal id) {
    try {
      Query query = em.createNamedQuery("findSession");
      query.setParameter("sessionnumber", id);
      TmsSesion result = (TmsSesion) query.getSingleResult();
      return result;
    } catch (Exception e) {
      return null;
    }

  }
}
