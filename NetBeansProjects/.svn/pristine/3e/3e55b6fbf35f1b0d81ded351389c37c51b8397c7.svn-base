/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author EKS Victor
 */
@Remote
public interface TmsTarjetasViajeFacadeRemote {

  List<TmsTarjetasViaje> findByFolio(String folio, BigInteger origen);

  List<TmsTarjetasViaje> findByOperador(String clave_operador, BigInteger origen);

   public String confirmarTarjeta(BigInteger tarjeta_id, Long usuario_id,BigInteger origen,
          String operador,String autobus, String boletos_venta_manual, String importe_venta_manual);

  public ArrayList<String> confirmarTarjetas(ArrayList<BigInteger> tarjetas, Long usuario_id, BigInteger origen,
          String operador,String autobus, String boletos_venta_manual, String importe_venta_manual);

  public Timestamp getFechaServidor();

  public ArrayList getValues(BigInteger tarjeta, String operador);

}
