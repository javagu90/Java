/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsBaseDatos;
import com.estrellaroja.confirmacionviajes.entidades.TmsSesion;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class TmsBaseDatosFacade extends AbstractFacade<TmsBaseDatos> implements TmsBaseDatosFacadeRemote {
  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsBaseDatosFacade() {
    super(TmsBaseDatos.class);
  }

  public List<TmsBaseDatos> findByPrefijo(String prefijo) {
    try {
      Query query = em.createNamedQuery("findByPrefijo");
      query.setParameter("prefijo", prefijo);
      List<TmsBaseDatos> result =  query.getResultList();
      return result;
    } catch (Exception e) {
      return null;
    }
  }
  @Override
  public List<TmsBaseDatos> findAll() {
    try {
      Query query = em.createNamedQuery("findAll");
      List<TmsBaseDatos> result = query.getResultList();
      return result;
    } catch (Exception e) {
      System.out.println("e = " + e);
      return null;
    }
  }
}
