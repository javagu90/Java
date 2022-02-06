/*
 * TmsOfertasServicioFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.solicitud;   

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsOfertasServicioTbl;
import xertms.entidad.TmsOfertasServicioV;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad2.TmsParametrosConfigTbl;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsOfertasServicioFacade implements TmsOfertasServicioFacadeRemote {
    @PersistenceContext(unitName="TMSDB_OfertasServicios-ejbPU")
    EntityManager em;
    /**
     * Creates a new instance of TmsOfertasServicioFacade
     */
    public TmsOfertasServicioFacade() {
    }
    
    public String[] obtenerTerminal(){
        String Consulta = 
                "SELECT BDC.TERMINAL_ID, BDC.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                "WHERE BDC.ESQUEMA_PROPIO = 'S'";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            String s[] = new String[2];
            s[0] = y.get(0).toString();
            s[1] = y.get(1).toString();
            return s;
        }catch(Exception ex){
            return null;
        }
    }
    
    public boolean validaHora(long servicioId, long rutaId, Timestamp hora){
        String Consulta =
                "SELECT A.OFERTA_SERVICIO_ID "+
                "FROM TMS_OFERTAS_SERVICIO_TBL A "+
                "WHERE A.SERVICIO_ID = "+servicioId+" "+
                "AND   A.RUTA_ID = "+rutaId+" "+
                "AND   TO_CHAR(A.HORA_CORRIDA,'HH24:MI') = " +
                "TO_CHAR(TO_TIMESTAMP('"+hora+"','YYYY-MM-DD HH24:MI:SS.FF'), 'HH24:MI')";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return true;
            return false;
        }catch(NoResultException nrex){
            return true;
        }
    }
    
    public long validaHoraModificada(String nombreOferta, long ofertaServicioId, Timestamp hora, long origenId, long destinoId, long empresaId){
        String Consulta =
                "SELECT A.OFERTA_SERVICIO_ID "+
                "FROM TMS_OFERTAS_SERVICIO_TBL A "+
                "WHERE A.OFERTA_SERVICIO_NOMBRE = '"+nombreOferta+"' "+
                "AND   A.OFERTA_SERVICIO_ID<>"+ofertaServicioId+" "+
                "AND   TO_CHAR(A.HORA_CORRIDA,'HH24:MI') = " +
                "TO_CHAR(TO_TIMESTAMP('"+hora+"','YYYY-MM-DD HH24:MI:SS.FF'), 'HH24:MI') " +
                "AND   A.TRAMO_ORIGEN_ID = "+origenId+" " +
                "AND   A.TRAMO_DESTINO_ID = "+destinoId+" " +
                "AND   A.EMPRESA_ID = "+empresaId;
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(NoResultException nrex){
            return -1;
        }
    }
    
    public List<TmsOfertasServicioV> queryTmsOfertasServicioV(String nombreOferta, String empresaId, String rutaId){
        String Consulta =
          "SELECT * "+
          "FROM TMS_OFERTAS_SERVICIO_V A "+
          "WHERE A.OFERTA_SERVICIO_NOMBRE LIKE '"+nombreOferta+"' "+
          "AND   A.EMPRESA_ID=NVL('"+empresaId+"',A.EMPRESA_ID) "+
          "AND   A.RUTA_ID=NVL('"+rutaId+"',A.RUTA_ID) "+
          "ORDER BY A.OFERTA_SERVICIO_ID";
        try{
            //System.out.println(Consulta);
            List<TmsOfertasServicioV> x = em.createNativeQuery(Consulta, TmsOfertasServicioV.class).getResultList();
            if(x.size()==0) return null;
            for(int i=0; i<x.size(); i++) em.refresh(x.get(i));
            return x;
        }catch(Exception ex){
            return null;
        }
    }
    
    public boolean existeNombreOfertaServicio(String nombreOferta){
        String Consulta =
          //"SELECT COUNT(*) FROM TMS_OFERTAS_SERVICIO_TBL@PCENTRAL_LINK.ESTRELLAROJA.COM.MX OS WHERE OS.OFERTA_SERVICIO_NOMBRE = '"+nombreOferta+"'";
                "SELECT COUNT(*) FROM TMS_OFERTAS_SERVICIO_TBL OS WHERE OS.OFERTA_SERVICIO_NOMBRE = '"+nombreOferta+"'";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return true;
            int valor = Integer.valueOf(x.get(0).toString());
            System.out.println("valor "+valor);
            if(valor>0) return true;
            return false;
        }catch(Exception ex){
            return true;
        }
    }
            
    public List<TmsEmpresasTbl> queryTmsEmpresasTblAll(){
        return (List<TmsEmpresasTbl>)em.createNamedQuery("TmsEmpresasTbl.findAll").getResultList();
    }
    
    public List<TmsServiciosTbl> queryTmsServiciosTblAll(){
        return (List<TmsServiciosTbl>)em.createNamedQuery("TmsServiciosTbl.findAll").getResultList();
    }
    
    public List<TmsRutasV> queryTmsRutasV(){
        return (List<TmsRutasV>)em.createNamedQuery("TmsRutasV.findAll").getResultList();
    }
    
    public List<TmsRutasTbl> queryTmsRutasTblAll(){
        return (List<TmsRutasTbl>)em.createNamedQuery("TmsRutasTbl.findAll").getResultList();
    }
    
    public List<TmsEstadosTbl> queryTmsEstadosTblAll(){
        return (List<TmsEstadosTbl>)em.createNamedQuery("TmsEstadosTbl.findAll").getResultList();
    }
    
    public boolean registrarOferta(String PrefijoTerminalId, TmsOfertasServicioTbl oferta){
        try{
            oferta.setMenoresCorrida((long)0);
            em.persist(oferta);
            oferta.setOfertaServicioId(Long.valueOf(PrefijoTerminalId+oferta.getOfertaServicioId()));
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public boolean eliminarOferta(long ofertaId){
        try{
            TmsOfertasServicioTbl o = em.find(TmsOfertasServicioTbl.class, ofertaId);
            em.remove(o);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public boolean actualizarOferta(TmsOfertasServicioTbl oferta){
        try{
            TmsOfertasServicioTbl ofertaX = em.find(TmsOfertasServicioTbl.class, oferta.getOfertaServicioId());
            if(ofertaX==null) return false;
            em.refresh(ofertaX);
            oferta.setCreadoPor(ofertaX.getCreadoPor());
            oferta.setFechaCreacion(ofertaX.getFechaCreacion());
            em.merge(oferta);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    // anexo funciones parametros
    public List findAll() {
        try{
            List<TmsParametrosConfigTbl> todosparametros = em.createQuery("select object(o) from TmsParametrosConfigTbl as o").getResultList();
            for(int i=0; i<todosparametros.size(); i++)
            {
                System.out.println("SizeColl("+i+")"+todosparametros.get(i).getTmsGlobalParametrosTblCollection().size());
                System.out.println("SizeColl("+i+")"+todosparametros.get(i).getTmsServicioParametrosTblCollection().size());
            }
            return todosparametros;
        }catch(NoResultException nrex){
            return null;
        }
    }
    
    public TmsParametrosConfigTbl busquedaPorCodigo(String codigo){
            TmsParametrosConfigTbl param = (TmsParametrosConfigTbl) em.createNamedQuery("TmsParametrosConfigTbl.findByParametroCodigo").setParameter("parametroCodigo",codigo).getSingleResult();
            System.out.println("Tamaño de la coleccionGlobal: "+param.getTmsGlobalParametrosTblCollection().size());
            System.out.println("Tamaño de la coleccionEmpresas: "+param.getTmsEmpresaParametrosTblCollection().size());
            System.out.println("Tamaño de la coleccionServicios: "+param.getTmsServicioParametrosTblCollection().size());
            System.out.println("Tamaño de la coleccionTerminales: "+param.getTmsTerminalParametrosTblCollection().size());
            System.out.println("Tamaño de la coleccionCajas: "+param.getTmsCajaParametrosTblCollection().size());
            return param;
    }
    
    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }
    
   /*public long[] obtienePlantilla(long servicioId){
        String Consulta = 
                "SELECT PLANTILLA.PLANTILLA_ENC_ID, PLANTILLA.CAPACIDAD_ASIENTOS "+
                "FROM TMS_AUTOBUS_PLANTILLAS_ENC_TBL PLANTILLA "+
                "WHERE PLANTILLA.NOMBRE_CORTO = ( "+
                        "SELECT DISTINCT(PARAMSERVICIO.PARAMETRO_VALOR) "+
                        "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                        ",TMS_SERVICIO_PARAMETROS_TBL   PARAMSERVICIO "+
                        "WHERE PARAM.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' "+
                        "AND   PARAMSERVICIO.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "+
                        "AND   PARAMSERVICIO.SERVICIO_ID = "+servicioId+" "+
                ")";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            long[] z = new long[2];
            if(x==null || x.size()==0){
                z[0]=-1;
                z[1]=-1;
                return z;
            }
            Vector y = (Vector) x.get(0);
            z[0]=Long.valueOf(y.get(0).toString());
            z[1]=Long.valueOf(y.get(1).toString());
            return z;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    */
    
    public Vector PlantillaRutas(){
        String Consulta = 
                "SELECT PLANTILLA.PLANTILLA_ENC_ID, PLANTILLA.CAPACIDAD_ASIENTOS, RUTAS.RUTA_NUMERO "+
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                ",TMS_RUTA_PARAMETROS_TBL   PARAMRUTA "+
                ",TMS_RUTAS_TBL   RUTAS "+
                ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL PLANTILLA "+
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' "+
                "AND   PARAMRUTA.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "+
                "AND   PARAMRUTA.RUTA_ID = RUTAS.RUTA_ID "+
                "AND   PLANTILLA.NOMBRE_CORTO = PARAMRUTA.PARAMETRO_VALOR "+
                "ORDER BY 3";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            return x;
        }catch(Exception ex){
            return null;
        }
    }
    
    public long validaHoraBD(String nombreOferta, long ofertaServicioId, Timestamp hora, long origenId, long destinoId, long empresaId){
        String Consulta =
                "SELECT A.OFERTA_SERVICIO_ID "+
                "FROM TMS_OFERTAS_SERVICIO_TBL A "+
                "WHERE A.OFERTA_SERVICIO_NOMBRE = '"+nombreOferta+"' "+
                "AND   A.OFERTA_SERVICIO_ID<>"+ofertaServicioId+" "+
                "AND   TO_CHAR(A.HORA_CORRIDA,'HH24:MI') = " +
                "TO_CHAR(TO_TIMESTAMP('"+hora+"','YYYY-MM-DD HH24:MI:SS.FF'), 'HH24:MI') " +
                "AND   A.TRAMO_ORIGEN_ID = "+origenId+" " +
                "AND   A.TRAMO_DESTINO_ID = "+destinoId+" " +
                "AND   A.EMPRESA_ID = "+empresaId;
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(NoResultException nrex){
            return -1;
        }
    }
    
    public boolean afectaRolBaseLinea(long ofsId){
        String Consulta =
                "SELECT 1 FROM TMS_OFERTAS_SERVICIO_TBL OFS "+
                "WHERE OFS.OFERTA_SERVICIO_ID = "+ofsId+" "+
                "AND	  ( "+
                         "1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_1_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_2_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_3_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_4_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_5_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_6_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_7_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_8_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_9_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_10_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_11_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_12_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_13_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_14_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_15_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_16_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_17_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_18_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_19_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_20_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_21_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_22_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_23_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_24_ID=OFS.OFERTA_SERVICIO_ID) "+
                "OR	  1 = (SELECT DISTINCT(1) FROM TMS_ROLES_BASE_LINEAS_TBL RBL WHERE RBL.OFERTA_CORRIDA_25_ID=OFS.OFERTA_SERVICIO_ID) "+
                ")";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return true;
            return false;
        }catch(NoResultException nrex){
            return false;
        }
    }
    
    public Vector muestraOfertasGuardadas(){
        String Consulta =
                "SELECT DISTINCT(OFS.OFERTA_SERVICIO_NOMBRE) FROM TMS_OFERTAS_SERVICIO_TBL OFS "+
                "ORDER BY 1";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            return x;
        }catch(NoResultException nrex){
            return null;
        }
    }

     public Vector ServicioEmpresa(){
        String Consulta = 
                "SELECT S.SERVICIO_NOMBRE, R.RUTA_NUMERO, E.EMPRESA_NOMBRE "+
                "FROM TMS_SERVICIOS_EMPRESAS_TBL SE "+
                         ",TMS_SERVICIOS_TBL S "+
                         ",TMS_RUTAS_TBL R "+
                         ",TMS_EMPRESAS_TBL E " +
                "WHERE S.SERVICIO_ID = SE.SERVICIO_ID "+
                "AND  R.RUTA_ID = SE.RUTA_ID "+
                "AND  E.EMPRESA_ID = SE.EMPRESA_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            return x;
        }catch(NoResultException ex){
            return null;
        }
    }
}