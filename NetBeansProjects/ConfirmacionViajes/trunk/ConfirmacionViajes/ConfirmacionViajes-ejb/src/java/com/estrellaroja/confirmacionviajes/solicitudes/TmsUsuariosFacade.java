/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsUsuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author EKS Victor
 */
@Stateless
public class TmsUsuariosFacade extends AbstractFacade<TmsUsuario> implements TmsUsuariosFacadeRemote {
  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsUsuariosFacade() {
    super(TmsUsuario.class);
  }

   public TmsUsuario findUsuario(BigDecimal id) {
    try {
      Query query = em.createNamedQuery("findUsuario");
      query.setParameter("usuario", id);
      TmsUsuario result = (TmsUsuario) query.getSingleResult();
      return result;
    } catch (Exception e) {
      return null;
    }
  }

}
