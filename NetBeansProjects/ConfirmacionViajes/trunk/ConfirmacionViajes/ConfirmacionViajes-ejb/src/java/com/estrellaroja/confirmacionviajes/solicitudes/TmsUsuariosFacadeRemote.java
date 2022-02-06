/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsUsuario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author EKS Victor
 */
@Remote
public interface TmsUsuariosFacadeRemote {

  TmsUsuario findUsuario(BigDecimal id);

}
