/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface generalFacadeRemote {

    public java.util.List<com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje> findByFolio(java.lang.String folio, java.math.BigInteger origen);

    public java.util.List<com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje> findByOperador(java.lang.String claveOperador, java.math.BigInteger origen);

    public java.util.TimeZone getTimeZone();
    
}
