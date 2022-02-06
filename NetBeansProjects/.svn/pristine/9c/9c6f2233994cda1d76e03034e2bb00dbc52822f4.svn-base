/*
 * TmsCorridasVentaTblFacade.java
 *
 * Created on 9 de octubre de 2007, 03:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCorridasVentaTblFacade implements TmsCorridasVentaTblFacadeRemote {

    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /** Creates a new instance of TmsCorridasVentaTblFacade */
    public TmsCorridasVentaTblFacade() {
    }

    public void create(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.persist(tmsCorridasVentaTbl);
    }

    public void edit(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.merge(tmsCorridasVentaTbl);
    }

    public void destroy(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.merge(tmsCorridasVentaTbl);
        em.remove(tmsCorridasVentaTbl);
    }

    public TmsCorridasVentaTbl find(Object pk) {
        System.out.println("Busqueda en local de la corrida: "+pk);
        TmsCorridasVentaTbl corrida = (TmsCorridasVentaTbl) em.find(TmsCorridasVentaTbl.class, pk);
        em.refresh(corrida);
        return corrida;
    }
    
    public Vector findRemota(long idRemota, String esquema){
        return (Vector)em.createNativeQuery("select * from tms_corridas_venta_tbl@"+esquema+" where corrida_id = "+idRemota).getResultList();
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCorridasVentaTbl as o").getResultList();
    }

    public List buscarCorridasPorFecha(Timestamp feha1, Timestamp fecha2){
      List<TmsCorridasVentaTbl> listado =  (List<TmsCorridasVentaTbl>)em.createNamedQuery("TmsCorridasVentaTbl.findByFechaHoraCorrida").setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;
    }
    
    public List buscarCorridasPorServicio(Timestamp feha1, Timestamp fecha2, String servicio){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByServicio").setParameter("servicio",servicio).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList(); 
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;    
    }

    
    public List buscarCorridasPorOperador(Timestamp feha1, Timestamp fecha2, String operador){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByOperador").setParameter("operador",operador).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;    
        
    }

    public List buscarCorridasPorOperadorAutobus(Timestamp feha1, Timestamp fecha2, String operador, String bus){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByOperadorAutobus").setParameter("operador",operador).setParameter("autobus",bus).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          
    }

    public List buscarCorridasPorOperadorServicio(Timestamp feha1, Timestamp fecha2, String operador, String servic){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByOperadorServicio").setParameter("operador",operador).setParameter("servicio",servic).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          

    }

     public List buscarCorridasPorOperadorAutobusServicio(Timestamp feha1, Timestamp fecha2, String operador, String servic, String bus){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByOperadorAutobusServicio").setParameter("operador",operador).setParameter("servicio",servic).setParameter("autobus",bus).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          
        
    }
   
    
    public List buscarCorridasPorAutobus(Timestamp feha1, Timestamp fecha2, String autobus){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByAutobus").setParameter("autobus",autobus).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          
    
    }
    
    
    public List buscarCorridasPorAutobusServicio(Timestamp feha1, Timestamp fecha2, String autobus, String servicio){
       List<TmsCorridasVentaTbl> listado = em.createNamedQuery("TmsCorridasVentaTbl.findByAutobusServicio").setParameter("autobus",autobus).setParameter("servicio",servicio).setParameter("fechaHoraCorrida1",feha1).setParameter("fechaHoraCorrida2",fecha2).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          
        
    }
    
    public TmsCorridasVentaTbl actualizarCorrida(TmsCorridasVentaTbl corrida){
        TmsCorridasVentaTbl corridaRefrescada = em.find(TmsCorridasVentaTbl.class,corrida.getCorridaId());
        em.refresh(corridaRefrescada);
        return corridaRefrescada;
    }

    public List<TmsCorridasVentaTbl> buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus){
      String consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";
      if(!operador.equals("%") && bus.equals("%"))
      {
              consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"') " +
              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && operador.equals("%"))
      {
             consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && !operador.equals("%"))
      {
             consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and t.operador like '"+operador+"' " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }
      
      List<TmsCorridasVentaTbl> listado = null;
      listado =  (List<TmsCorridasVentaTbl>)em.createNativeQuery(consulta,TmsCorridasVentaTbl.class).getResultList();
      for(int i=0; i<listado.size();i++)
          em.refresh(listado.get(i)); 
      return listado;          
    }   
}
