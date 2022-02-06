/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCentralFacade implements TmsCentralFacadeRemote {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext(unitName="TMSConsultaOcupaciones_Central")
    private EntityManager em;

    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsCentralFacade() {
    }

  public Object buscarDatos(String autobus, String operador, String servicio, String empresa, String origen, String destino, String fi, String ff, String hi, String tabla, String rutas, boolean extras){
        //System.out.println("La busqueda es en central con la tabla: "+tabla);

        String consulta="  select con.*,'CENTRAL' conn from " +
               tabla+" con " +
               "where   con.SERVICIO = '"+servicio+"' ";
               if(!autobus.equals("%"))
                //   consulta  = consulta +"and   con.AUTOBUS  like '"+autobus+"' or con.AUTOBUS is null  ";
               //else
                   consulta  = consulta +"and   con.AUTOBUS  like '"+autobus+"'  ";
               if(!operador.equals("%"))
                   //consulta  = consulta+ "and   con.OPERADOR like '"+operador+"' or con.OPERADOR is null  ";
               //else
                   consulta  = consulta+ "and   con.OPERADOR like '"+operador+"' ";
               consulta  = consulta+"and   con.ORIGEN   = '"+origen+"' " +
               "and   con.DESTINO   like '"+destino+"' " +
               //"and   con.EMPRESA   like '"+empresa+"' " +
               //"and   con.RUTA_ID in (53,49)" +
               "and ("+ rutas+")"+
               "and   to_date(con.FECHA_CORRIDA,'DD/MM/RRRR') between to_date('"+fi+" 00:00','DD/MM/RRRR HH24:MI') and to_date('"+ff+" 23:59','DD/MM/RRRR HH24:MI') " +
               "and   con.HORA_CORRIDA  between   '"+hi+"' and '23:59' " ;
               if(extras)
                   consulta  = consulta+ "and con.TIPO_CORRIDA = 'E' ";
               consulta  = consulta+ "order by con.SERVICIO asc " +
               ",to_date(con.FECHA_CORRIDA,'DD/MM/RRRR') " +
               ",con.HORA_CORRIDA asc";
       System.out.println("Conuslta(buscarDatos): "+consulta);
       return em.createNativeQuery(consulta).getResultList();
   }

    public TmsCorridasVentaTbl find(Object pk) {
        //System.out.println("Busqueda en central de la corrida: "+pk);
        TmsCorridasVentaTbl corrida = (TmsCorridasVentaTbl) em.find(TmsCorridasVentaTbl.class, pk);
        em.refresh(corrida);
        return corrida;
    }

    public Object buscaDatosOcupacionPorSistema(String corrida){
//     String consulta ="select bol.BOLETO_ID, bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO from tms_boletos_venta_tbl bol "+
//                    " where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
//                    " and bol.CLAVE_CORRIDA = '"+corrida+"' " +
//                    " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";
     //System.out.println("buscaDatosOcupacionPorSistema en central de la corrida: "+corrida);
     String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO||'  '||bol.NOMBRE_PASAJERO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
            // "NVL((select bolrel.importe_boleto from tms_boletos_venta_tbl bolrel where bolrel.boleto_id = bol.BOLETO_RELACIONADO_ID), bol.IMPORTE_BOLETO) importe_boleto  " +
             "from tms_boletos_venta_tbl bol " +
             ",tms_corridas_tbl cor  " +
             ",tms_corridas_tbl correla  " +
             "where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC')  " +
             "and cor.CLAVE_CORRIDA= '"+corrida+"' " +
             "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " +
             "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " +
             "and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";


        return em.createNativeQuery(consulta).getResultList();
    }

}
