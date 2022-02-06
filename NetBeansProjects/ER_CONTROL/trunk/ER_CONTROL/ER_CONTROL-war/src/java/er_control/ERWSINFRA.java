/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package er_control;
 
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author vgonzalez
 */
@WebService()
public class ERWSINFRA {

    @PersistenceContext(unitName="ER_CONTROL-ejbPU")
    private EntityManager em;
    
    private SimpleDateFormat ffh = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public getOperacionesPromocionResp getOperacionesPromocion(getOperacionesPromocionReq parametros)
    {
        getOperacionesPromocionResp respuesta = new getOperacionesPromocionResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        if(parametros.getOperacion().equals("A"))
        {
            //Operaciones para insert
            String query = "select ER_PROMOCIONES_SEQ.nextval from dual";
            long id = -1;
            try
            {
             Vector vid = ((Vector)em.createNativeQuery(query).getSingleResult());
                id = Long.valueOf(vid.get(0).toString());
              query = "INSERT INTO er_promociones_tbl (PROMOCION_ID,NOMBRE_PROMOCION,CODIGO_PROMOCION,DESCRIPCION_PROMOCION,NOMBRE_PRODUCTO,SERVICIO_ID,RUTA_ID,TRAMO_ID,EMPRESA_ID,APLICA_SENCILLO,APLICA_REDONDO,VIGENCIA_FECHA_INICIAL,VIGENCIA_FECHA_FINAL,CANAL_VENTA,COMPRA_MINIMA,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                    + "VALUES ("+id+",'"+parametros.getPromocion().getNombrePromocion()+"','"+parametros.getPromocion().getCodigoPromocion()+"','"+parametros.getPromocion().getDescripcionPromocion()+"','"+parametros.getPromocion().getNombreProducto()+"',"+parametros.getPromocion().getServicioId()+","+parametros.getPromocion().getRutaId()+","+parametros.getPromocion().getTramoId()+","+parametros.getPromocion().getEmpresaId()+",'"+parametros.getPromocion().getAplicaSencillo()+"','"+parametros.getPromocion().getAplicaRedondo()+"',to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaInicial())+"','DD/MM/RRRR HH24:MI'),to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaFinal())+"','DD/MM/RRRR HH24:MI'),'"+parametros.getPromocion().getCanalVenta()+"',"+parametros.getPromocion().getCompraMinima()+",'"+parametros.getPromocion().getAdicional1()+"','"+parametros.getPromocion().getAdicional2()+"','"+parametros.getPromocion().getAdicional3()+"','"+parametros.getPromocion().getAdicional4()+"','"+parametros.getPromocion().getAdicional5()+"',1,SYSDATE,1,SYSDATE)";
              System.out.println("query(insert): "+query);
              em.createNativeQuery(query).executeUpdate();
            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-002");
                respuesta.setErrorMsg("La promocion no pudo se agregada: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
            respuesta.setPromocionId(id);
            respuesta.setErrorCode("");
            respuesta.setErrorMsg("la promocion se agrego satisfactoriamente");
            respuesta.setOperacionExitosa(true);

        }
        if(parametros.getOperacion().equals("M"))
        {
            //Operaciones para update

            try
            {
              String query = "update er_promociones_tbl set "
                      + "NOMBRE_PROMOCION = '"+parametros.getPromocion().getNombrePromocion()+"', "
                      + "CODIGO_PROMOCION = '"+parametros.getPromocion().getCodigoPromocion()+"', "
                      + "DESCRIPCION_PROMOCION = '"+parametros.getPromocion().getDescripcionPromocion()+"', "
                      + "SERVICIO_ID = "+parametros.getPromocion().getServicioId()+", "
                      + "RUTA_ID = "+parametros.getPromocion().getRutaId()+", "
                      + "TRAMO_ID = "+parametros.getPromocion().getTramoId()+", "
                      + "EMPRESA_ID = "+parametros.getPromocion().getEmpresaId()+", "
                      + "APLICA_SENCILLO = '"+parametros.getPromocion().getAplicaSencillo()+"', "
                      + "APLICA_REDONDO = '"+parametros.getPromocion().getAplicaRedondo()+"', "
                      + "VIGENCIA_FECHA_INICIAL = to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaInicial())+"','DD/MM/RRRR HH24:MI'), "
                      + "VIGENCIA_FECHA_FINAL = to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaFinal())+"','DD/MM/RRRR HH24:MI'), "
                      + "CANAL_VENTA = '"+parametros.getPromocion().getCanalVenta()+"', "
                      + "COMPRA_MINIMA = "+parametros.getPromocion().getCompraMinima()+", "
                      + "ADICIONAL1 = '"+parametros.getPromocion().getAdicional1()+"', "
                      + "ADICIONAL2 = '"+parametros.getPromocion().getAdicional2()+"', "
                      + "ADICIONAL3 = '"+parametros.getPromocion().getAdicional3()+"', "
                      + "ADICIONAL4 = '"+parametros.getPromocion().getAdicional4()+"', "
                      + "ADICIONAL5 = '"+parametros.getPromocion().getAdicional5()+"', "
                      + "ULTIMA_ACTUALIZACION_POR = 1, "
                      + "ULTIMA_FECHA_ACTUALIZACION = SYSDATE "
                      + "where PROMOCION_ID = "+parametros.getPromocion().getPromocionId();
              System.out.println("query(update): "+query);
              em.createNativeQuery(query).executeUpdate();
            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-002");
                respuesta.setErrorMsg("La promocion no pudo ser modificada: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
            respuesta.setPromocionId(parametros.getPromocion().getServicioId().longValue());
            respuesta.setErrorCode("");
            respuesta.setErrorMsg("la promocion se modifico satisfactoriamente");
            respuesta.setOperacionExitosa(true);        }

        return respuesta;
    }


}
