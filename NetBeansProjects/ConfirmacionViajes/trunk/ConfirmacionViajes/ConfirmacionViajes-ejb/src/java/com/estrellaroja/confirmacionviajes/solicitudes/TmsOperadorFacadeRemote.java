/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsOperador;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author EKS Victor
 */
@Remote
public interface TmsOperadorFacadeRemote {
  public List<TmsOperador> getByEmpresa(BigInteger empresa);

}
