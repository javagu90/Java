/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.solicitud;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_excepciones_incidencias.exceptions.ControlException;
import tms_excepciones_incidencias.exceptions.ExisteExcepcionIncidenciaException;
import tms_incidencias.entidad.TmsActAdicionalesTbl;
import tms_incidencias.entidad.TmsExcepcionIncidenciaTbl;

/**
 *
 * @author Osvaldo Sanchez
 */
@Stateless
public class TmsExcepcionIncidenciaFacadeBean implements TmsExcepcionIncidenciaFacadeRemote {

    @PersistenceContext(unitName = "TMS_Incidencias-ejbPU")
    private EntityManager em;

    //@Override
    public Vector getAllExcepcionesIncidencias()throws ControlException {
        String consulta =
                " SELECT ei.EXCEPCION_INCIDENCIA_ID,"
                + "(SELECT ac.ACCION FROM TMS_ACCIONES_TBL ac,TMS_ACT_ADICIONALES_TBL aa WHERE aa.ACCION_ID=ac.ACCION_ID AND AA.TIPO_ACTIVIDAD_ADICIONAL_ID=ei.TIPO_ACTIVIDAD_ADICIONAL1_ID),"
                + "(SELECT ac.ACCION FROM TMS_ACCIONES_TBL ac,TMS_ACT_ADICIONALES_TBL aa WHERE aa.ACCION_ID=ac.ACCION_ID AND AA.TIPO_ACTIVIDAD_ADICIONAL_ID=ei.TIPO_ACTIVIDAD_ADICIONAL2_ID),"
                + "us.USUARIO_NOMBRE,"
                + "TO_CHAR(EI.FECHA_CREACION,'dd/MM/yyyy')"
                + "FROM TMS_EXCEPCIONES_INC_TBL ei ,TMS_USUARIOS_TBL us  "
                + "WHERE   ei.CREADO_POR=us.USUARIO_ID   ";
        Vector resultado = null;       
            resultado = (Vector) em.createNativeQuery(consulta).getResultList();
            if (resultado == null || resultado.size() == 0) {
                return null;
        }
        return resultado;
    }

    //@Override
    public Vector getActAdicionales() {
        String consulta =
                "SELECT  aa.TIPO_ACTIVIDAD_ADICIONAL_ID,aa.ACTIVIDAD_CLAVE,ac.ACCION "
                + "FROM TMS_ACT_ADICIONALES_TBL aa, TMS_ACCIONES_TBL ac "
                + "WHERE AA.ACCION_ID=AC.ACCION_ID "
                + "ORDER BY ac.ACCION ASC";
        Vector resultado = null;
        try {
            resultado = (Vector) em.createNativeQuery(consulta).getResultList();
            if (resultado == null || resultado.size() == 0) {
                return null;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return resultado;
    }

    //@Override
    public void guardarExcepcionIncidencia(TmsExcepcionIncidenciaTbl excepcionIncidencia) throws ControlException,ExisteExcepcionIncidenciaException {
        TmsActAdicionalesTbl act1 = em.find(TmsActAdicionalesTbl.class, excepcionIncidencia.getTipoActividadAdicional1().getTipoActividadAdicionalId());
        TmsActAdicionalesTbl act2 = em.find(TmsActAdicionalesTbl.class, excepcionIncidencia.getTipoActividadAdicional2().getTipoActividadAdicionalId());
        excepcionIncidencia.setTipoActividadAdicional1(act1);
        excepcionIncidencia.setTipoActividadAdicional2(act2);       
         em.persist(excepcionIncidencia);
         em.flush();      
       
    }

   // @Override
    public void eliminarExcepcionIncidencia(Short idExcepcionIncidencia){
         TmsExcepcionIncidenciaTbl ex=em.find(TmsExcepcionIncidenciaTbl.class,idExcepcionIncidencia);
         em.remove(ex);
         em.flush();
    }

     //@Override
     public boolean validaExcepciones(String claveOperador, String fecha1, String fecha2,Long idIncidencia){
        Vector vVector = new Vector();
        String Consulta="SELECT 1 FROM TMS_EXCEPCIONES_INC_TBL ex "+
                        "WHERE EX.TIPO_ACTIVIDAD_ADICIONAL1_ID IN("+
                        "SELECT op.INCIDENCIA_ID "+
                        "FROM TMS_OPERADOR_INCIDENCIAS_TBL op,TMS_OPERADORES_TBL o "+
                        "WHERE "+
                        "op.OPERADOR_ID = o.OPERADOR_ID AND "+
                        "("+
                        "TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<=TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss') AND TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss')<=TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') OR "+
                        "TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<=TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss') AND TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss')<=TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') OR "+
                        "TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss') <TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') AND TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss')) AND "+
                        "o.CLAVE_OPERADOR LIKE NVL('"+claveOperador+"', o.CLAVE_OPERADOR) AND "+
                        "op.INCIDENCIA_AUTORIZADA IN ('S','N')) AND "+
                        "EX.TIPO_ACTIVIDAD_ADICIONAL2_ID="+idIncidencia;
        try{
            //System.out.println("Buscar::: "+Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return false;
            Vector z = (Vector) vVector.get(0);
            int x = Integer.valueOf(z.get(0).toString());
            if(x==1) return true;
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return true;
        }
    }

     //@Override
     public boolean tienePermisosUsuario(Long idUsuario,String numeroFuncion){
          String consulta =
                "select f.FUNCION_ID from TMS_USUARIOS_TBL u left join TMS_USUARIO_PERFILES_TBL up on u.USUARIO_ID=UP.USUARIO_ID "+
                "left join TMS_PERFILES_TBL pt on pt.PERFIL_ID=up.perfil_id "+
                "left join TMS_MENUS_ENCABEZADO_TBL menc on menc.MENU_ID=pt.perfil_id "+
                "left join TMS_MENUS_LINEAS_TBL ml on ml.MENU_ID=menc.MENU_ID "+
                "left join TMS_FUNCIONES_TBL f on f.FUNCION_ID=ml.FUNCION_ID "+
                "where u.USUARIO_ID="+idUsuario+" AND f.FUNCION_NUMERO='"+numeroFuncion+"'";
        Vector resultado = null;
        try {
            resultado = (Vector) em.createNativeQuery(consulta).getResultList();
            if (resultado == null || resultado.size() == 0) {
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
        return true;
     }

}
