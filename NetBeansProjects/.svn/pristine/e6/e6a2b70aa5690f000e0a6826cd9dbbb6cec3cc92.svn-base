/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsOperador;
import java.math.BigInteger;
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
public class TmsOperadorFacade extends AbstractFacade<TmsOperador> implements TmsOperadorFacadeRemote {
  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsOperadorFacade() {
    super(TmsOperador.class);
  }

  public List<TmsOperador> getByEmpresa(BigInteger empresa){
    Query query = em.createNamedQuery("TmsOperador.findByEmpresa");
    query.setParameter("empresaId", empresa);
    List<TmsOperador> result= query.getResultList();
    return result;
  }

}
