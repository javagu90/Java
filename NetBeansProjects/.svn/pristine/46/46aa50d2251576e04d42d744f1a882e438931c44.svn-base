/*
 * TmsUsuariosTblFacade.java
 *
 * Created on 9 de mayo de 2008, 03:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsReportesRecauda.solicitud;

import java.util.List;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsReportesRecauda.TmsSesionActividadesTbl;
import tmsReportesRecauda.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsUsuariosTblFacade implements TmsUsuariosTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsUsuariosTblFacade */
    public TmsUsuariosTblFacade() {
    }

    public void create(TmsUsuariosTbl tmsUsuariosTbl) {
        em.persist(tmsUsuariosTbl);
    }

    public void edit(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
    }

    public void destroy(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
        em.remove(tmsUsuariosTbl);
    }

    public TmsUsuariosTbl find(Object pk) {
        return (TmsUsuariosTbl) em.find(TmsUsuariosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsUsuariosTbl as o").getResultList();
    }
    
    public int buscaCortesPendientes(String fechai, String fechaf){
        int nc =0;
        //Vector vnc = (Vector)em.createNativeQuery("select count(cor.CORTE_ID) from tms_cortes_tbl cor where cor.ESTADO_CORTE = 'P' and cor.NOMBRE_SESION = 'R_AMPERSA' and to_char(cor.FECHA_CREACION,'DD/MM/RRRR') = '"+fecha+"'").getSingleResult();
        Vector vnc = (Vector)em.createNativeQuery("select count(cor.CORTE_ID) from tms_cortes_tbl cor where cor.ESTADO_CORTE = 'P' and cor.NOMBRE_SESION = 'R_AMPERSA' and  cor.FECHA_CREACION between to_date('"+fechai+" 13:00','DD/MM/RRRR HH24:MI') and to_date('"+fechaf+" 12:59','DD/MM/RRRR HH24:MI')").getSingleResult();
        nc = Integer.valueOf(vnc.get(0).toString());
        return nc;
    }


  public Vector buscaServicios() {
    return (Vector)this.em.createNativeQuery("select s.SERVICIO_NOMBRE from tms_servicios_tbl s order by s.SERVICIO_NOMBRE asc").getResultList();
  }

  public Vector buscaEmpresas() {
    return (Vector)this.em.createNativeQuery("select e.EMPRESA_NOMBRE from tms_empresas_tbl e order by e.EMPRESA_NOMBRE asc").getResultList();
  }

    public String _ObtieneFechaHoraBD(){
        return ((Vector)em.createNativeQuery("select to_char(SYSDATE,'RRRR-MM-DD HH24:MI:SS') from dual").getSingleResult()).get(0).toString();
    }

    public Vector buscaDatosTerminal() {
    return (Vector)this.em.createNativeQuery("select terminal_id, nombre_terminal from tms_base_datos_config_tbl where esquema_propio = 'S'").getSingleResult();
  }


    public boolean corteDummyFinDia(long usuarioId){
        Vector v =(Vector)em.createNativeQuery("SELECT CORTE_ID FROM TMS_CORTES_TBL WHERE CORTE_ID = 1").getResultList();
        if(v.size()>0)
            return true;
        else
        {
            try{
            em.createNativeQuery("INSERT INTO TMS_CORTES_TBL (CORTE_ID,NOMBRE_SESION,USUARIO_ID,ESTADO_CORTE,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) values (1,'FIN DE DIA',"+usuarioId+",'R',"+usuarioId+","+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE,'R',0,'XERTMS')").executeUpdate();
            return true;
            }
            catch(EJBException e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public Object queryActividadSesionCorteFinDiaGetId(){
        String Consulta = "SELECT "+
                          "ACT.ACTIVIDAD_SESION_ID "+
                          "FROM "+
                          "TMS_ACTIVIDADES_SESION_TBL ACT "+
                          "WHERE "+
                          "ACT.CLAVE_ACTIVIDAD = 'CORTE_FIN_DIA'";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }

    public long addSesionCorteFinDia(String PrefijoTerminal, TmsSesionActividadesTbl tmsSesionActividadesTbl, String fecha){
        try{
            System.out.println("busca: select SESION_ACTIVIDAD_ID from TMS_SESION_ACTIVIDADES_TBL where VALOR_ACTIVIDAD = '"+tmsSesionActividadesTbl.getValorActividad()+"'");
            Vector v = (Vector)em.createNativeQuery("select SESION_ACTIVIDAD_ID from TMS_SESION_ACTIVIDADES_TBL where VALOR_ACTIVIDAD = '"+tmsSesionActividadesTbl.getValorActividad()+"'").getResultList();
            if(v.size()==0)
            {
                em.persist(tmsSesionActividadesTbl);
                tmsSesionActividadesTbl.setSesionActividadId(Long.valueOf(PrefijoTerminal+tmsSesionActividadesTbl.getSesionActividadId()));
                     //Busca los montos por terminales
                     String qry = " select e.estado_nombre,  sum(tabla.imp_abordo) from	   ( "
                             + "SELECT ciudad_recaudacion_id sucursal_id,NVL(SUM(bol.IMPORTE_BOLETO),0)  imp_abordo "
                             + "FROM   TMS_boletos_boletera_tbl    bol  "
                             + ",TMS_tarjetas_viaje_tbl tar  "
                             + ", TMS_corridas_venta_tbl cv  "
                             + "WHERE  bol.CORTE_ID in ( "
                             + "select tmsco.corte_id "
                             + "FROM TMS_CORTES_TBL              tmsco "
                             + ",TMS_SESION_ACTIVIDADES_TBL tmssa "
                             + ",TMS_ACTIVIDADES_SESION_TBL tmsact "
                             + "WHERE 1=1 "
                             + "AND TMSCO.FECHA_CREACION BETWEEN "
                             + "( SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS') "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "
                             + ") "
                             + "AND "
                             + "( "
                             + "SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440)+1 "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID       "
                             + ") "
                             + "AND tmsco.NOMBRE_SESION = 'R_AMPERSA' "
                             + "AND tmssa.CORTE_ID = tmsco.CORTE_ID "
                             + "AND tmsco.ESTADO_CORTE = 'R'	"
                             + "and tmsact.clave_actividad = 'CORTE'	"
                             + "and tmssa.actividad_sesion_id = tmsact.actividad_sesion_id "
                             + ") "
                             + "and   tar.folio_tarjeta = bol.folio_tarjeta  "
                             + "and   cv.corrida_id = tar.corrida_id  "
                             + "group by ciudad_recaudacion_id  "
                             + "union all "
                             + "SELECT  rec.ciudad_recaudacion_id sucursal_id,  NVL(SUM(rec.IMPORTE_VENTA_ABORDO),0) imp_abordo FROM  "
                             + "TMS_recaudacion_tbl    rec  "
                             + ",TMS_tarjetas_viaje_tbl tar  "
                             + ", TMS_corridas_venta_tbl cv  "
                             + "WHERE rec.ESTADO_RECAUDACION = 'R'  "
                             + "AND   rec.CORTE_ID in ( "
                             + "select tmsco.corte_id "
                             + "FROM TMS_CORTES_TBL              tmsco "
                             + ",TMS_SESION_ACTIVIDADES_TBL tmssa "
                             + ",TMS_ACTIVIDADES_SESION_TBL tmsact "
                             + "WHERE 1=1 "
                             + "AND TMSCO.FECHA_CREACION BETWEEN "
                             + "( SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS') "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "
                             + ") "
                             + "AND "
                             + "( "
                             + "SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440)+1 "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "   AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID       "
                             + ") "
                             + "AND tmsco.NOMBRE_SESION = 'R_AMPERSA' "
                             + "AND tmssa.CORTE_ID = tmsco.CORTE_ID "
                             + "AND tmsco.ESTADO_CORTE = 'R'	 "
                             + "and tmsact.clave_actividad = 'CORTE'	 "
                             + "and tmssa.actividad_sesion_id = tmsact.actividad_sesion_id "
                             + ")  "
                             + "AND   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID  "
                             + "and   cv.corrida_id = tar.corrida_id  "
                             + "and   rec.importe_venta_abordo > 0  "
                             + "and   tar.folio_tarjeta  not in(select distinct(folio_tarjeta) from tms_boletos_boletera_tbl)  "
                             + "group by rec.ciudad_recaudacion_id "
                             + "union all "
                             + "SELECT bol.ciudad_recaudacion_id sucursal_id, NVL(SUM(bol.IMPORTE_BOLETO),0)  imp_abordo "
                             + "FROM   TMS_boletos_boletera_tbl    bol  "
                             + "WHERE  bol.CORTE_ID in ( "
                             + "select tmsco.corte_id "
                             + "FROM TMS_CORTES_TBL              tmsco "
                             + ",TMS_SESION_ACTIVIDADES_TBL tmssa "
                             + ",TMS_ACTIVIDADES_SESION_TBL tmsact "
                             + "WHERE 1=1 "
                             + "AND TMSCO.FECHA_CREACION BETWEEN "
                             + "( SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS') "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "
                             + ") "
                             + "AND "
                             + "( "
                             + "SELECT TO_DATE('"+fecha+"'|| ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440)+1 "
                             + "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "
                             + ",TMS_GLOBAL_PARAMETROS_TBL                  PARAMGLOBAL "
                             + "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "
                             + "AND PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID       "
                             + ") "
                             + "AND tmsco.NOMBRE_SESION = 'R_AMPERSA' "
                             + "AND tmssa.CORTE_ID = tmsco.CORTE_ID "
                             + "AND tmsco.ESTADO_CORTE = 'R'	 "
                             + "and tmsact.clave_actividad = 'CORTE'	 "
                             + "and tmssa.actividad_sesion_id = tmsact.actividad_sesion_id "
                             + ")  "
                             + "and    bol.folio_tarjeta = 'PENDIENTE' group by bol.ciudad_recaudacion_id     "
                             + ") tabla left join  tms_estados_tbl e on e.estado_id = tabla.sucursal_id "
                             + "group by e.estado_nombre ";

                     Vector res = (Vector)em.createNativeQuery(qry).getResultList();
                     System.out.println("Resultado de los datos de las Sucursales: "+res);
                     for(int i=0; i<res.size(); i++)
                     {
                         Vector row = (Vector)res.get(i);
                            String ref = fecha.replace("/", "")+row.get(0);
                            System.out.println("ref: "+ref);
                            String rt = fecha.replace("/", "")+row.get(0)+"RT"+calculaDV_Alg35(ref+"RT");

                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'RECAUDAT_"+rt+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                     }
                return tmsSesionActividadesTbl.getSesionActividadId().longValue();
            }
            else
                return Long.valueOf(v.get(0).toString());

         }catch(Exception ejbex){
            //ejbex.printStackTrace();
           return -1;
        }
    }


 private int calculaDV_Alg35(String nr){
  Vector<Integer> inicial= new Vector<Integer>();
  Vector<Integer> segundo= new Vector<Integer>();
  Vector<String> letras= new Vector<String>();
  Vector<String> numeros= new Vector<String>();

  int n = 0;
  //int A = 1, B = 2,C = 3,D = 4,E = 5,F= 6, G = 7,H = 8,I = 9,J = 10,K = 11, L = 12, M = 13,N = 14, O = 15, P = 16, Q = 17, R=18,S = 19,T = 20, U = 21, V = 22, W = 23,X = 24, Y = 25,Z = 26;
  letras.add("");
  letras.add("A");letras.add("B");letras.add("C");letras.add("D");letras.add("E");letras.add("F");letras.add("G");letras.add("H");
  letras.add("I");letras.add("J");letras.add("K");letras.add("L");letras.add("M");letras.add("N");letras.add("O");letras.add("P");
  letras.add("Q");letras.add("R");letras.add("S");letras.add("T");letras.add("U");letras.add("V");letras.add("W");letras.add("X");
  letras.add("Z");letras.add("Z");
  numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
  char[] car = nr.toCharArray();
  for(int i=0; i<car.length; i++)
  {
      char c = car[i];
      int index = letras.indexOf(""+c);
      if(index>=0)
          inicial.add(index);
      else
          inicial.add(numeros.indexOf(""+c));
  }
 //System.out.println("Paso1: "+inicial);
  for(int i = 0; i<inicial.size(); i++){
      for(int j = 1; j<=3; j++)
      {
          //System.out.println("i = "+i);
          if(i > (inicial.size() - 1))
              break;
          if(j==1)segundo.add(regresaUno(""+(((int)inicial.get(i))*4)));
          if(j==2)segundo.add(regresaUno(""+(((int)inicial.get(i))*3)));
          if(j==3)segundo.add(regresaUno(""+(((int)inicial.get(i))*8)));
          i++;
      }
      i--;
  }
  //System.out.println("Paso2: "+segundo);
  int sum = 0;
  for(int i=0; i<segundo.size(); i++)
      sum = sum + ((int) segundo.get(i));
  //System.out.println("sumaPaso2: "+sum);
  int prox = regresaProximaDecena(""+sum);
  //System.out.println("Resta: "+prox +" - "+sum);
  n = prox - sum;
  return n;
  }

 private int regresaUno(String num){
    //System.out.println("llamo regresaUno con: "+num);
     int unNum = 0;
     if(num.length()==1)
         return Integer.valueOf(num);
     else
     {

         while(num.length()>1)
         {
             String n = ""+num;
             char[] cn = n.toCharArray();
             int nn = 0;
             for(int i=0; i<cn.length; i++)
              nn = nn+ Integer.valueOf(""+cn[i]);
             num = ""+nn;
           // System.out.println("  "+num);
         }
         unNum = Integer.valueOf(num);
     }
     return unNum;
 }

 private int regresaProximaDecena(String num){
    //System.out.println("Decena: "+ num);
     //int decena = 0;
     //char[] cd = num.toCharArray();
     char[] cdp = num.toCharArray();
     char numDecena = cdp[cdp.length -2];
     String s = "";
     //System.out.println("cdp[cdp.length -1] = "+cdp[cdp.length -1]);
     if((""+cdp[cdp.length -1]).equals("0") )
        s = ""+ numDecena;
     else
        s = ""+(Integer.valueOf(""+numDecena) + 1);
     cdp[cdp.length -2] = (s.toCharArray())[0];
     cdp[cdp.length -1] = '0';
     //int decenaProxima = 0;
     String dp = "";
     for(int i=0; i<cdp.length; i++)
         dp = dp +""+cdp[i];
     //System.out.println("Decena Proxima: "+ dp);
     return Integer.valueOf(dp);
 }

}
