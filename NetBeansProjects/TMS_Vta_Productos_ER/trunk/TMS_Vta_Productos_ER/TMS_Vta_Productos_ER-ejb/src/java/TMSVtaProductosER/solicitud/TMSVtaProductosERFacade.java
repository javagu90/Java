/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TMSVtaProductosER.solicitud;

import TMSVtaProductosER.entidad.Producto;
import TMSVtaProductosER.entidad.Tarifa;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.driver.OracleCallableStatement;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TMSVtaProductosERFacade implements TMSVtaProductosERFacadeRemote {
    @PersistenceContext(unitName="TMS_Vta_Productos_ER-ejbPU")
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public TMSVtaProductosERFacade(){}

    public Vector getTerminales(){
        return (Vector)em.createNativeQuery("SELECT BD.TERMINAL_ID,BD.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL  bd where BD.NOMBRE_TERMINAL <> 'CENTRAL' order by BD.NOMBRE_TERMINAL").getResultList();
    }

    public Vector getEmpresas(){
        return (Vector)em.createNativeQuery("SELECT EMPRESA_ID ,EMPRESA_NOMBRE,DESCRIPCION from tms_empresas_tbl order by EMPRESA_NOMBRE").getResultList();
    }

    public String getTerminalActual(){
        Vector v = (Vector)em.createNativeQuery("SELECT BD.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL  bd where BD.ESQUEMA_PROPIO = 'S'").getResultList();
        Vector res = (Vector)v.get(0);
        return res.get(0).toString();
    }

    public List<Producto> getProductos(String clave, String nombre,Vector terminales,Vector empresas){
        List<Producto> lista= new ArrayList<Producto>();
        Vector res = new Vector();
        boolean existeProducto;
        String qry = "SELECT P.PRODUCTO_ID "
                + ",P.PRODUCTO_CLAVE "
                + ",P.PRODUCTO_NOMBRE "
                + ",P.PRODUCTO_DESCRIPCION "
                + ",P.PRODUCTO_CATEGORIA "
                + ",P.IMAGEN "
                + ",P.PRODUCTO_EXISTENCIA "
                + ",P.EMPRESA_ID "
                + ",P.ELIMINADO "
                + ",T.TARIFA_PRODUCTO_ID "
                + ",T.PRODUCTO_ID "
                + ",T.IMPORTE_TARIFA "
                + ",T.TIPO_MONEDA "
                + ",to_char(T.FECHA_HORA_TARIFA,'DD/MM/RRRR') fecha "
                + ",T.TIPO_TARIFA "
                + ",T.TERMINAL_ID "
                + "FROM TMS_PRODUCTOS_ER_TBL P LEFT JOIN TMS_TARIFAS_PRODUCTOS_TBL T ON T.PRODUCTO_ID = P.PRODUCTO_ID "
                + "WHERE 1=1 ";
                if(!clave.equals(""))
                    qry = qry + "AND P.PRODUCTO_CLAVE like '%"+clave+"%' ";
                if(!nombre.equals(""))
                    qry = qry + "AND P.PRODUCTO_NOMBRE like '%"+nombre+"%'";
                qry = qry + "order by P.PRODUCTO_NOMBRE,T.FECHA_HORA_TARIFA ";
        try{
                System.out.println("getProductos: "+qry);
                res = (Vector)em.createNativeQuery(qry).getResultList();
            } catch (EJBException ex) {
                System.out.println("Error ala buscar Productos...");
                ex.printStackTrace();
            }
       if(res.size()==0)
            return lista;
        else
        {
            for(int i=0; i<res.size();i++)
            {
               //Producto p = lista.get(i);
                Vector p = (Vector) res.get(i);
               existeProducto = false;
               int indexProdExist=-1;
               int cont = 0;
               for(Producto pb : lista)
               {
                   if (Long.valueOf(p.get(0).toString()) == pb.getProductoId())
                   {
                      existeProducto = true;
                      indexProdExist = cont;
                      break;
                   }
                   cont++;
               }
               if(existeProducto && (p.get(9)!=null && !p.get(9).equals("")))
               {
                   Tarifa t = new Tarifa(p,terminales);
                   lista.get(indexProdExist).addTarifa(t);
               }
              else
               {
                 if(!existeProducto)
                 {
                   Producto product = new Producto((Vector)res.get(i),terminales,empresas);
                   lista.add(product);
                 }
               }
            }
            System.out.println("La consulta obtuvo "+lista.size()+" Productos");
            return lista;
        }
    }

    public String agregarProducto(Producto pro,long usuarioId)
    {
        String res = "valido";
        String eliminado = pro.isEliminado()?"S":"N";
        String existencia = (pro.getProductoExistencia()==-1?"null":""+pro.getProductoExistencia());
        String empresa = (pro.getEmpresaId()==-1?"null":""+pro.getEmpresaId());
        String qry = "select TMS_PRODUCTOS_ER_SEQ.nextval from dual";
        Connection cnx=null;
        PreparedStatement pst = null;
        try
        {
         cnx = dataSource.getConnection();
         Vector vres= (Vector)em.createNativeQuery(qry).getSingleResult();
         BigDecimal v = (BigDecimal)vres.get(0);
         qry = "INSERT INTO TMS_PRODUCTOS_ER_TBL (PRODUCTO_ID ,PRODUCTO_CLAVE ,PRODUCTO_NOMBRE ,PRODUCTO_DESCRIPCION ,PRODUCTO_CATEGORIA ,IMAGEN ,PRODUCTO_EXISTENCIA ,EMPRESA_ID ,ELIMINADO,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                + "VALUES ( "
                + v.longValue()+",'"+pro.getProductoClave()+"','"+pro.getProductoNombre()+"','"+pro.getProductoDescipcion()+"','"+pro.getProductoCategoria()+"'"
                + ",?"//+pro.getProductoImagen()
                + ","+existencia
                + ","+empresa
                + ",'"+eliminado+"'"
                + ","+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE )";
        pst = cnx.prepareStatement(qry);
        pst.setBytes(1,pro.getProductoImagen());
        pst.executeUpdate();
         for(Tarifa t : pro.getProductoTarifas())
            res = agregarActualizarTarifa(t,v.longValue(),usuarioId);

        }catch(EJBException ex){ex.printStackTrace(); res = "invalido";}
         catch (SQLException ex){ex.printStackTrace();res = "invalido";}
        return res;
    }

    public String actualizarProducto(Producto pro,long usuarioId,String eliminarTarifas)
    {
        String res = "valido";
        String eliminado = pro.isEliminado()?"S":"N";
        String existencia = (pro.getProductoExistencia()==-1?"null":""+pro.getProductoExistencia());
        String empresa = (pro.getEmpresaId()==-1?"null":""+pro.getEmpresaId());
        if(!eliminarTarifas.equals(""))
        {
            String qryElim = "delete from TMS_TARIFAS_PRODUCTOS_TBL where TARIFA_PRODUCTO_ID  in ("+eliminarTarifas+")";
            em.createNativeQuery(qryElim).executeUpdate();
        }
        Connection cnx=null;
        PreparedStatement pst = null;
        try
        {
         cnx = dataSource.getConnection();
         long productoId = pro.getProductoId();
         String qry = "UPDATE TMS_PRODUCTOS_ER_TBL SET "
                 + "PRODUCTO_CLAVE = '"+pro.getProductoClave()+"' "
                 + ",PRODUCTO_NOMBRE = '"+pro.getProductoNombre()+"' "
                 + ",PRODUCTO_DESCRIPCION = '"+pro.getProductoDescipcion()+"' "
                 + ",PRODUCTO_CATEGORIA = '"+pro.getProductoCategoria()+"' "
                 + ",IMAGEN = ? "
                 + ",PRODUCTO_EXISTENCIA = "+existencia
                 + ",EMPRESA_ID = "+empresa
                 + ",ELIMINADO = '"+eliminado+"'"
                 + ",ULTIMA_ACTUALIZACION_POR = "+usuarioId
                 + ",ULTIMA_FECHA_ACTUALIZACION = SYSDATE "
                 + "WHERE PRODUCTO_ID = "+productoId;
        pst = cnx.prepareStatement(qry);
        pst.setBytes(1,pro.getProductoImagen());
        pst.executeUpdate();
         for(Tarifa t : pro.getProductoTarifas())
            res = agregarActualizarTarifa(t,productoId,usuarioId);

        }catch(EJBException ex){ex.printStackTrace(); res = "invalido";}
         catch (SQLException ex){ex.printStackTrace();res = "invalido";}
        return res;
    }
    
    private String agregarActualizarTarifa(Tarifa t,long productoId,long usuarioId)
    {
            String res = "valido";
            String terminal = (t.getTerminalIdTarifa()==-1?"null":""+t.getTerminalIdTarifa());
            String qry ="";
            if(t.getTarifaId()==-1)
            {
                System.out.println("Se agrego la tarifa nueva");
                    qry ="INSERT INTO TMS_TARIFAS_PRODUCTOS_TBL (TARIFA_PRODUCTO_ID ,PRODUCTO_ID ,IMPORTE_TARIFA ,TIPO_MONEDA ,FECHA_HORA_TARIFA ,TIPO_TARIFA ,TERMINAL_ID,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                    + "VALUES (TMS_TARIFAS_PRODUCTOS_SEQ.nextval"
                    + ","+productoId//v.longValue()
                    + ","+t.getImporteTarifa()
                    + ",'"+t.getTipoMonedaTarifa()+"'"
                    + ",TO_DATE('"+t.getFechaTarifa()+"','DD/MM/RRRR')"
                    + ",'"+t.getTipoTarifa()+"'"
                    + ","+terminal
                    + ","+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE )";
            }
            else
            {
                System.out.println("Se modifico la tarifa "+t.getTarifaId());
                qry ="UPDATE TMS_TARIFAS_PRODUCTOS_TBL SET  "
                        + "PRODUCTO_ID =  "+productoId
                        + ",IMPORTE_TARIFA =  "+t.getImporteTarifa()
                        + ",TIPO_MONEDA = '"+t.getTipoMonedaTarifa()+"' "
                        + ",FECHA_HORA_TARIFA  = TO_DATE('"+t.getFechaTarifa()+"','DD/MM/RRRR') "
                        + ",TIPO_TARIFA = '"+t.getTipoTarifa()+"' "
                        + ",TERMINAL_ID = "+terminal
                        + ",ULTIMA_ACTUALIZACION_POR = "+usuarioId
                        + ",ULTIMA_FECHA_ACTUALIZACION = SYSDATE "
                        + "where TARIFA_PRODUCTO_ID = "+t.getTarifaId();
            }
          try{
                em.createNativeQuery(qry).executeUpdate();
            } catch (EJBException ex) {ex.printStackTrace();res = "invalido";}

            return res;
    }

    public List<Producto> getProductosParaVenta(){
        List<Producto> lista= new ArrayList<Producto>();
        Vector res = new Vector();
        boolean existeProducto;
        String qry = " SELECT P.PRODUCTO_ID ,P.PRODUCTO_CLAVE ,P.PRODUCTO_NOMBRE,P.PRODUCTO_DESCRIPCION,P.PRODUCTO_CATEGORIA,P.IMAGEN ,P.PRODUCTO_EXISTENCIA,T2.TIPO_TARIFA "
                + ",CASE WHEN T3.FECHA_HORA_TARIFA <=SYSDATE THEN T2.IMPORTE_TARIFA ELSE 0 END TARIFA_VIGENTE "
                + ",T2.TIPO_MONEDA "
                + ",to_char(T3.FECHA_HORA_TARIFA,'DD/MM/RRRR') FECHA_TARIFA  "
                + ",(SELECT EMPRESA_NOMBRE from tms_empresas_tbl e where e.EMPRESA_ID = p.EMPRESA_ID) EMPRESA_NOMBRE  "
                + ",nvl(P.ADICIONAL1,'N'),nvl(P.ADICIONAL2,',EFE,'),nvl(P.ADICIONAL3,'N'),nvl(P.ADICIONAL4,'N'),nvl(P.ADICIONAL5,'N') "
                + ",(SELECT DESCRIPCION from tms_empresas_tbl e where e.EMPRESA_ID = p.EMPRESA_ID) EMPRESA_DESCRIPCION  "
                + "FROM  "
                + "( "
                + "SELECT T1.TIPO_TARIFA, MAX(T1.FECHA_HORA_TARIFA) FECHA_HORA_TARIFA,T1.TIPO_MONEDA,T1.PRODUCTO_ID FROM TMS_TARIFAS_PRODUCTOS_TBL T1 "
                + "WHERE 1=1 "
                + "AND (T1.TERMINAL_ID IS NULL OR T1.TERMINAL_ID = (select bd.TERMINAL_ID from TMS_BASE_DATOS_CONFIG_TBL bd where bd.ESQUEMA_PROPIO  = 'S') ) "
                + "GROUP BY T1.TIPO_TARIFA, T1.TIPO_MONEDA, T1.PRODUCTO_ID "
                + ") T3  "
                + ",TMS_TARIFAS_PRODUCTOS_TBL T2,TMS_PRODUCTOS_ER_TBL P  "
                + "WHERE T2.PRODUCTO_ID = T3.PRODUCTO_ID "
                + "AND T2.FECHA_HORA_TARIFA  = T3.FECHA_HORA_TARIFA  "
                + "AND T2.TIPO_TARIFA = T3.TIPO_TARIFA "
                + "AND T2.TIPO_MONEDA = T3.TIPO_MONEDA "
                + "AND P.PRODUCTO_ID = t2.PRODUCTO_ID "
                + "ORDER BY P.PRODUCTO_CLAVE ";
        try{
                System.out.println("getProductos: "+qry);
                res = (Vector)em.createNativeQuery(qry).getResultList();
            } catch (EJBException ex) {
                System.out.println("Error al buscar Productos...");
                ex.printStackTrace();
            }
       if(res.size()==0)
            return lista;
        else
        {
            for(int i=0; i<res.size();i++)
            {
               //Producto p = lista.get(i);
               Vector p = (Vector) res.get(i);
               existeProducto = false;
               int indexProdExist=-1;
               int cont = 0;
               for(Producto pb : lista)
               {
                   if (Long.valueOf(p.get(0).toString()) == pb.getProductoId())
                   {
                      existeProducto = true;
                      indexProdExist = cont;
                      break;
                   }
                   cont++;
               }
               if(existeProducto)
               {
                   Tarifa t = new Tarifa(p);
                   if(p.get(7).toString().equals("CADA UNO"))
                       lista.get(indexProdExist).setImporteTarifaMostrador(Float.valueOf(p.get(8).toString()));
                   lista.get(indexProdExist).addTarifa(t);
               }
              else
               {
                   Producto product = new Producto((Vector)res.get(i));
                   lista.add(product);
               }
            }
            System.out.println("La consulta de getProductosParaVenta obtuvo "+lista.size()+" Productos");
            return lista;
        }
    }

    public List<Producto> getProductosParaVentaCarrito(){
        List<Producto> lista= new ArrayList<Producto>();
        Vector res = new Vector();
        boolean existeProducto;
        String qry = " SELECT P.PRODUCTO_ID ,P.PRODUCTO_CLAVE ,P.PRODUCTO_NOMBRE,P.PRODUCTO_DESCRIPCION,P.PRODUCTO_CATEGORIA,P.IMAGEN ,P.PRODUCTO_EXISTENCIA,T2.TIPO_TARIFA "
                + ",CASE WHEN T3.FECHA_HORA_TARIFA <=SYSDATE THEN T2.IMPORTE_TARIFA ELSE 0 END TARIFA_VIGENTE "
                + ",T2.TIPO_MONEDA "
                + ",to_char(T3.FECHA_HORA_TARIFA,'DD/MM/RRRR') FECHA_TARIFA  "
                + ",(SELECT EMPRESA_NOMBRE from tms_empresas_tbl e where e.EMPRESA_ID = p.EMPRESA_ID) EMPRESA_NOMBRE  "
                + ",nvl(P.ADICIONAL1,'N'),nvl(P.ADICIONAL2,',EFE,'),nvl(P.ADICIONAL3,'N'),nvl(P.ADICIONAL4,'N'),nvl(P.ADICIONAL5,'N') "
                + ",(SELECT descripcion from tms_empresas_tbl e where e.EMPRESA_ID = p.EMPRESA_ID) EMPRESA_descripcion "
                + "FROM  "
                + "( "
                + "SELECT T1.TIPO_TARIFA, MAX(T1.FECHA_HORA_TARIFA) FECHA_HORA_TARIFA,T1.TIPO_MONEDA,T1.PRODUCTO_ID FROM TMS_TARIFAS_PRODUCTOS_TBL T1 "
                + "WHERE 1=1 "
                + "AND (T1.TERMINAL_ID IS NULL OR T1.TERMINAL_ID = (select bd.TERMINAL_ID from TMS_BASE_DATOS_CONFIG_TBL bd where bd.ESQUEMA_PROPIO  = 'S') ) "
                + "GROUP BY T1.TIPO_TARIFA, T1.TIPO_MONEDA, T1.PRODUCTO_ID "
                + ") T3  "
                + ",TMS_TARIFAS_PRODUCTOS_TBL T2,TMS_PRODUCTOS_ER_TBL P  "
                + "WHERE T2.PRODUCTO_ID = T3.PRODUCTO_ID "
                + "AND T2.FECHA_HORA_TARIFA  = T3.FECHA_HORA_TARIFA  "
                + "AND T2.TIPO_TARIFA = T3.TIPO_TARIFA "
                + "AND T2.TIPO_MONEDA = T3.TIPO_MONEDA "
                + "AND P.PRODUCTO_ID = t2.PRODUCTO_ID "
                + "AND UPPER(P.ADICIONAL1) = 'S'"
                + "ORDER BY P.PRODUCTO_CLAVE ";
        try{
                System.out.println("getProductos: "+qry);
                res = (Vector)em.createNativeQuery(qry).getResultList();
            } catch (EJBException ex) {
                System.out.println("Error al buscar Productos...");
                ex.printStackTrace();
            }
       if(res.size()==0)
            return lista;
        else
        {
            for(int i=0; i<res.size();i++)
            {
               //Producto p = lista.get(i);
               Vector p = (Vector) res.get(i);
               existeProducto = false;
               int indexProdExist=-1;
               int cont = 0;
               for(Producto pb : lista)
               {
                   if (Long.valueOf(p.get(0).toString()) == pb.getProductoId())
                   {
                      existeProducto = true;
                      indexProdExist = cont;
                      break;
                   }
                   cont++;
               }
               if(existeProducto)
               {
                   Tarifa t = new Tarifa(p);
                   if(p.get(7).toString().equals("CADA UNO"))
                       lista.get(indexProdExist).setImporteTarifaMostrador(Float.valueOf(p.get(8).toString()));
                   lista.get(indexProdExist).addTarifa(t);
               }
              else
               {
                   Producto product = new Producto((Vector)res.get(i));
                   lista.add(product);
               }
            }
            System.out.println("La consulta de getProductosParaVentaCarrito obtuvo "+lista.size()+" Productos");
            return lista;
        }
    }

    public boolean registrarVentaProducto(
        String empresa,
        String producto,
        long cantidad,
        String folio,
        String clave_producto,
        float precio_unitario,
        float subtotal,
        float iva,
        float total,
        float retencion,
        long corteId,
        String caja,
        long cajero,
        String terminal,
        long terminalId,
        long usuarioId)
    {
        String query = "INSERT INTO XER_VENTAS_MISCELANEAS_TBL (VM_ID,CANAL_VENTA,EMPRESA,PRODUCTO,TIPO_OPERACION,TIPO_PAGO,CANTIDAD,NO_FOLIO,REFERENCIA,VALOR_UNITARIO,SUBTOTAL,IVA,TOTAL,RETENCION,CORTE_ID,CAJA,CLAVE_CAJERO,CIUDAD_VENTA,FECHA_HORA_VENTA,FECHA_EFECTIVA,CREADO_POR,FECHA_CREACION,AUTORIZADO_POR,ULTIMA_ACTUALIZACION)"
                + "values ('"+terminalId+"'||XER_VENTAS_MISCELANEAS_SEQ.nextval,'TMS VENTA','"+empresa+"','"+producto+"','VT','EFE',"+cantidad+",'"+folio+"','"+clave_producto+"',"+precio_unitario+","+subtotal+","+iva+","+total+","+retencion+","+corteId+",'"+caja+"',"+cajero+",'"+terminal+"',SYSDATE,SYSDATE,"+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE)";
       try
       {
          em.createNativeQuery(query).executeUpdate();
           return true;
       }catch(EJBException e){
        e.printStackTrace();
        }
        return false;
    }

    public String getTicketCompra(long terminalId)
    {
        Vector res = (Vector)em.createNativeQuery("select TMS_TICKET_PRODUCTOS_SEQ.nextval from dual").getSingleResult();
        return terminalId+res.get(0).toString();

    }


      public Vector getImpresora(String caja){
          String qry ="SELECT I.IMPRESORA_NOMBRE,CI.SALIDA_IMPRESION, F.FORMATO_NOMBRE "
                  + "FROM TMS_CAJAS_TBL C "
                  + "LEFT JOIN TMS_CAJAS_IMPRESORAS_TBL CI ON CI.CAJA_ID = C.CAJA_ID  "
                  + "LEFT JOIN TMS_FORMATOS_BOLETO_TBL F on F.FORMATO_BOLETO_ID = CI.FORMATO_BOLETO_ID  "
                  + "LEFT JOIN TMS_IMPRESORAS_TBL I ON I.IMPRESORA_ID = CI.IMPRESORA_ID  "
                  + "WHERE UPPER(C.CAJA_NOMBRE) = upper('"+caja+"')  "
                  + "AND F.FORMATO_NOMBRE IN ('TICKET_TERMICO','TICKET') "
                  + "AND CI.ACTIVIDAD_IMPRESORA = 'TICKETS'";

          return (Vector)em.createNativeQuery(qry).getResultList();
      }
}

