/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes;

import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacade;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author EKS Victor
 */
public class Main {

  private static TmsTarjetasViajeFacadeRemote tarjetasViaje;

//paqReportesTblFacade = lookupReportesTblFacadeRemote();
  protected  static TmsTarjetasViajeFacadeRemote lookupTmsTarjetasViajeFacade() {

    try {

      Context c = new InitialContext();

      return (TmsTarjetasViajeFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote");

    } catch (NamingException ne) {

      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);

      throw new RuntimeException(ne);

    }

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
//    tarjetasViaje = lookupTmsTarjetasViajeFacade();
//    List<TmsTarjetasViaje> tarjetas = tarjetasViaje.findByFolio("SRSMM1617N700187872");
//    for (TmsTarjetasViaje tarjeta : tarjetas) {
//      System.out.println("******************************************************");
//      System.out.println("Id: "+tarjeta.getTarjetaViajeId());
//      System.out.println("Folio: "+tarjeta.getFolioTarjeta());
//      System.out.println("Corrida: "+tarjeta.getClaveCorrida());
//      System.out.println("Estado: "+tarjeta.getEstadoTarjeta());
//    }
  }
}
