/*
 * peticionesFacade.java
 *
 * Created on 22 de septiembre de 2008, 02:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsc.solicitudes;

import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_encriptacion.EncriptarMD5;
import wsc.entidades.Cliente;
import wsc.excepciones.AgenteNoCreadoException;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class peticionesFacade implements peticionesFacadeRemote {
    
    @PersistenceContext(unitName="EJBModWSCteREPCONTROL")
    private EntityManager em;

    private EncriptarMD5 pwdEnc = new EncriptarMD5();
    /**
     * Creates a new instance of peticionesFacade
     */
    public peticionesFacade() {
        
    }

    public Vector buscaCliente(String email){
    String consulta = "select cl.cliente_id from ER_USUARIOS_TBL u left join ER_USUARIOS_CLIENTES_TBL cl on CL.USUARIO_ID = U.USUARIO_ID   where U.CORREO_ELECTRONICO =  '"+email+"'";
       return (Vector) em.createNativeQuery(consulta ).getResultList();
    } 
 
    public int getvalidaCliente(String email, String ppsw){
        String psw = "";
        try {
            psw = getPwdEnc().encriptar(ppsw);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        //System.out.println("select XER_TMS_WS_PKG.getClienteValido('"+email+"','"+psw+"') from dual");
        //Vector vc = (Vector) em.createNativeQuery("select XER_TMS_WS_PKG.getClienteValido('"+email+"','"+psw+"') from dual").getSingleResult();
        String qry = "select count(c.usuario_id) " +
                "from ER_usuarios_TBL c " +
                "where c.CORREO_ELECTRONICO= '"+email+"' " +
                "and c.CONTRASENA_ENCRIPTADA =  '"+psw+"' " +
                "AND (C.FECHA_FINAL IS NULL OR C.FECHA_FINAL > SYSDATE) ";
        System.out.println("Qry de getvalidaCliente: "+qry);
         Vector vc = (Vector) em.createNativeQuery( qry ).getSingleResult();
        return Integer.valueOf(vc.get(0).toString());
    }
    
    public int getvalidaCliente2(String email, String ppsw){
        String psw = "";
        try {
            psw = getPwdEnc().encriptar(ppsw);
        } catch (Exception ex) {    
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        String qry = "select count(c.usuario_id) " +
                "from ER_usuarios_TBL c " +
                "where c.CORREO_ELECTRONICO= '"+email+"' " +
                "and c.CONTRASENA_ENCRIPTADA =  '"+psw+"'";
        System.out.println("Qry de getvalidaCliente2: "+qry);
        Vector vc = (Vector) em.createNativeQuery( qry ).getSingleResult();
         return Integer.valueOf(vc.get(0).toString());
    }    
    
    public Vector getCliente(String email, String ppsw){
        String psw = "";
        try {
            psw = getPwdEnc().encriptar(ppsw);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from tms_clientes_tbl c where c.EMAIL= '"+email+"' and c.CONTRASENIA = '"+psw+"' and (c.FECHA_HASTA is null or c.FECHA_HASTA > sysdate)";
        
        return (Vector) em.createNativeQuery(qry).getResultList();        
    }

    public Vector getCliente2(String email){
        /*String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from tms_clientes_tbl c where c.EMAIL= '"+email+"' and (c.FECHA_HASTA is null or c.FECHA_HASTA > sysdate)";
        */
        String qry = "SELECT "
                + "C.CLIENTE_ID,  C.NOMBRE ,C.APATERNO||' '||C.AMATERNO APELLIDOS,C.CONTACTO,C.CALLE,C.NO_INT,C.NO_EXT,C.COLONIA,C.CODIGO_POSTAL,C.CIUDAD DELEG_MUN,C.CIUDAD,C.ESTADO,C.TELEFONO,C.TELEFONO_OFICINA  "
                + ",'' FECHA_NAC,'' SEXO,'' ESTADO_CIVIL,'' COMPANIA,( CASE TU.NOMBRE_TIPO when 'AGENTE DE VENTAS' THEN 'AGENTE' ELSE TU.NOMBRE_TIPO END) PUESTO,C.EMAIL,'' SALDO_PUNTOS,'' FECHA_ULTIMA_ACUMULACION,'' FECHA_ULTIMO_CANJE,C.TIPO_CLIENTE,TO_CHAR(C.FECHA_CREACION,'DD/MM/RRRR'),TO_CHAR(C.FECHA_BAJA,'DD/MM/RRRR')   "
                + ",C.CREADO_POR,C.FECHA_CREACION,C.ULTIMA_ACTUALIZACION_POR,C.ULTIMA_FECHA_ACTUALIZACION,'P' REPLICACION_ESTADO,'0' REPLICACION_INTENTOS,'' REPLICACION_ORIGEN,U.CONTRASENA_ENCRIPTADA   "
                + ",c.TELEFONO_CELULAR,'' NO_PASAPORTE,c.PAIS,c.TELEFONO_FAX,c.RFC,( CASE TU.NOMBRE_TIPO when 'ADMINISTRADOR' THEN 0 ELSE u.USUARIO_RELACIONADO_ID END) CLIENTE_RELACIONADO_ID  "
                + ",(select ER_CONTROL_PKG.GET_AGENCIA_VIAJES_USR(TU.NOMBRE_TIPO,U.USUARIO_ID) from dual) ADICIONAL1  "
                + ",c.ADICIONAL2,c.ADICIONAL3,c.ADICIONAL4,c.ADICIONAL5 "
                + "FROM ER_USUARIOS_TBL U  "
                + "LEFT JOIN ER_TIPOS_USUARIO_TBL tu on TU.TIPO_USUARIO_ID = U.TIPO_USUARIO_ID  "
                + "LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC ON U.USUARIO_ID = UC.USUARIO_ID   "
                + "LEFT JOIN  ER_CLIENTES_TBL C ON C.CLIENTE_ID = UC.CLIENTE_ID  "
                + "WHERE U.USUARIO_ID IN (SELECT UC2.USUARIO_ID  "
                + "FROM ER_CLIENTES_TBL C2 LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC2 ON UC2.CLIENTE_ID = C2.CLIENTE_ID "
                + "WHERE upper(C2.EMAIL) = upper('"+email+"')) "
                + "and (U.FECHA_FINAL is null or U.FECHA_FINAL > sysdate) ";
                return (Vector) em.createNativeQuery(qry).getResultList();
    }

    public Vector getCliente3(String email, long adminId){
        /*String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from tms_clientes_tbl c where c.EMAIL= '"+email+"'";
        */
        String qry = "SELECT "
                + "C.CLIENTE_ID,  C.NOMBRE ,C.APATERNO||' '||C.AMATERNO APELLIDOS,C.CONTACTO,C.CALLE,C.NO_INT,C.NO_EXT,C.COLONIA,C.CODIGO_POSTAL,C.CIUDAD DELEG_MUN,C.CIUDAD,C.ESTADO,C.TELEFONO,C.TELEFONO_OFICINA  "
                + ",'' FECHA_NAC,'' SEXO,'' ESTADO_CIVIL,'' COMPANIA,( CASE TU.NOMBRE_TIPO when 'AGENTE DE VENTAS' THEN 'AGENTE' ELSE TU.NOMBRE_TIPO END) PUESTO,C.EMAIL,'' SALDO_PUNTOS,'' FECHA_ULTIMA_ACUMULACION,'' FECHA_ULTIMO_CANJE,C.TIPO_CLIENTE,TO_CHAR(C.FECHA_CREACION,'DD/MM/RRRR'),TO_CHAR(C.FECHA_BAJA,'DD/MM/RRRR')   "
                + ",C.CREADO_POR,C.FECHA_CREACION,C.ULTIMA_ACTUALIZACION_POR,C.ULTIMA_FECHA_ACTUALIZACION,'P' REPLICACION_ESTADO,'0' REPLICACION_INTENTOS,'' REPLICACION_ORIGEN,U.CONTRASENA_ENCRIPTADA   "
                + ",c.TELEFONO_CELULAR,'' NO_PASAPORTE,c.PAIS,c.TELEFONO_FAX,c.RFC,( CASE TU.NOMBRE_TIPO when 'ADMINISTRADOR' THEN 0 ELSE u.USUARIO_RELACIONADO_ID END) CLIENTE_RELACIONADO_ID  "
                + ",(select ER_CONTROL_PKG.GET_AGENCIA_VIAJES_USR(TU.NOMBRE_TIPO,U.USUARIO_ID) from dual) ADICIONAL1  "
                + ",c.ADICIONAL2,c.ADICIONAL3,c.ADICIONAL4,c.ADICIONAL5 "
                + "FROM ER_USUARIOS_TBL U  "
                + "LEFT JOIN ER_TIPOS_USUARIO_TBL tu on TU.TIPO_USUARIO_ID = U.TIPO_USUARIO_ID  "
                + "LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC ON U.USUARIO_ID = UC.USUARIO_ID   "
                + "LEFT JOIN  ER_CLIENTES_TBL C ON C.CLIENTE_ID = UC.CLIENTE_ID  "
                + "WHERE U.USUARIO_ID IN (SELECT UC2.USUARIO_ID  "
                + "FROM ER_CLIENTES_TBL C2 LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC2 ON UC2.CLIENTE_ID = C2.CLIENTE_ID "
                + "WHERE upper(C2.EMAIL) = upper('"+email+"')) "
                + "AND U.USUARIO_RELACIONADO_ID = (SELECT UC.USUARIO_ID FROM ER_USUARIOS_CLIENTES_TBL UC WHERE UC.CLIENTE_ID = "+adminId+") ";
        return (Vector) em.createNativeQuery(qry).getResultList();        
    }
    
  public Vector getCliente4(String email, String ppsw){
        String psw = "";
        try {
            psw = getPwdEnc().encriptar(ppsw);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        /*
        String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from tms_clientes_tbl c where c.EMAIL= '"+email+"' and c.CONTRASENIA = '"+psw+"' ";
        */
        /*
        String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,(SELECT AGE.AGENCIA_NOMBRE " +
                  "FROM TMS_AGENCIAS_VIAJE_TBL age " +
                    ",TMS_AGENTES_VENTA_TBL agv " +
                    ",TMS_CLIENTES_TBL cli " +
                 "WHERE AGE.AGENCIA_ID = AGV.AGENCIA_ID " +
                   "AND AGV.ADICIONAL1 = CLI.CLIENTE_ID " +
                   "AND CLI.CLIENTE_ID = c.CLIENTE_ID) ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from tms_clientes_tbl c where c.EMAIL= '"+email+"' and c.CONTRASENIA = '"+psw+"' ";
        */    
        String qry = "SELECT "
                + "C.CLIENTE_ID,  C.NOMBRE ,C.APATERNO||' '||C.AMATERNO APELLIDOS,C.CONTACTO,C.CALLE,C.NO_INT,C.NO_EXT,C.COLONIA,C.CODIGO_POSTAL,C.CIUDAD DELEG_MUN,C.CIUDAD,C.ESTADO,C.TELEFONO,C.TELEFONO_OFICINA "
                + ",'' FECHA_NAC,'' SEXO,'' ESTADO_CIVIL,'' COMPANIA,( CASE TU.NOMBRE_TIPO when 'AGENTE DE VENTAS' THEN 'AGENTE' ELSE TU.NOMBRE_TIPO END) PUESTO,C.EMAIL,'' SALDO_PUNTOS,'' FECHA_ULTIMA_ACUMULACION,'' FECHA_ULTIMO_CANJE,C.TIPO_CLIENTE,TO_CHAR(C.FECHA_CREACION,'DD/MM/RRRR'),TO_CHAR(C.FECHA_BAJA,'DD/MM/RRRR')  "
                + ",C.CREADO_POR,C.FECHA_CREACION,C.ULTIMA_ACTUALIZACION_POR,C.ULTIMA_FECHA_ACTUALIZACION,'P' REPLICACION_ESTADO,'0' REPLICACION_INTENTOS,'' REPLICACION_ORIGEN,U.CONTRASENA_ENCRIPTADA  "
                + ",c.TELEFONO_CELULAR,'' NO_PASAPORTE,c.PAIS,c.TELEFONO_FAX,c.RFC,( CASE TU.NOMBRE_TIPO when 'ADMINISTRADOR' THEN 0 ELSE u.USUARIO_RELACIONADO_ID END) CLIENTE_RELACIONADO_ID "
                + ",(select ER_CONTROL_PKG.GET_AGENCIA_VIAJES_USR(TU.NOMBRE_TIPO,U.USUARIO_ID) from dual) ADICIONAL1 "
                + ",c.ADICIONAL2,c.ADICIONAL3,c.ADICIONAL4,c.ADICIONAL5 "
                + "FROM ER_USUARIOS_TBL U  "
                + "LEFT JOIN ER_TIPOS_USUARIO_TBL tu on TU.TIPO_USUARIO_ID = U.TIPO_USUARIO_ID "
                + "LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC ON U.USUARIO_ID = UC.USUARIO_ID  "
                + "LEFT JOIN  ER_CLIENTES_TBL C ON C.CLIENTE_ID = UC.CLIENTE_ID "
                + "WHERE U.CORREO_ELECTRONICO = '"+email+"' "
                + "AND U.CONTRASENA_ENCRIPTADA = '"+psw+"' ";
        System.out.println("qry(getCliente4): "+qry);
        return (Vector) em.createNativeQuery(qry).getResultList();        
    }
  
    public Vector getValidaAdministradorAutoriza(String email, long adminId){
        //String qry = "select CLIENTE_ID from tms_clientes_tbl c where c.EMAIL= '"+email+"' and c.CLIENTE_RELACIONADO_ID = "+adminId+"";
        String qry = "SELECT U. USUARIO_ID "
                + "FROM ER_USUARIOS_TBL U  "
                + "WHERE U.USUARIO_ID IN (SELECT UC2.USUARIO_ID  "
                + "FROM ER_CLIENTES_TBL C2 LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC2 ON UC2.CLIENTE_ID = C2.CLIENTE_ID "
                + "WHERE C2.EMAIL = '"+email+"') "
                + "AND U.USUARIO_RELACIONADO_ID = (SELECT UC.USUARIO_ID FROM ER_USUARIOS_CLIENTES_TBL UC WHERE UC.CLIENTE_ID = "+adminId+") ";
                return (Vector) em.createNativeQuery(qry).getResultList();
    }    
    
    public Vector getClientesAdministrados(long administradorId){
        /*String qry = "select " +
                "CLIENTE_ID,  NOMBRE ,  APELLIDOS,  CONTACTO,CALLE,NUMERO_INT,NUMERO_EXT,COLONIA,CODIGO_POSTAL,DELEG_MUN,CIUDAD,ESTADO,TELEFONO_CASA,TELEFONO_OFICINA, " +
                "FECHA_NAC,SEXO,ESTADO_CIVIL,COMPANIA,PUESTO,EMAIL,SALDO_PUNTOS,FECHA_ULTIMA_ACUMULACION,FECHA_ULTIMO_CANJE,TIPO_CLIENTE_ID,to_char(FECHA_DESDE,'DD/MM/RRRR'),to_char(FECHA_HASTA,'DD/MM/RRRR'), " +
                "CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN,CONTRASENIA, " +
                "TELEFONO_CELULAR,NO_PASAPORTE,PAIS,TELEFONO_FAX,RFC,CLIENTE_RELACIONADO_ID,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5 " +
                " from TMS_CLIENTES_TBL c where c.CLIENTE_RELACIONADO_ID = "+administradorId;
         *
         */
        String qry = "SELECT "
                + "C.CLIENTE_ID,  C.NOMBRE ,C.APATERNO||' '||C.AMATERNO APELLIDOS,C.CONTACTO,C.CALLE,C.NO_INT,C.NO_EXT,C.COLONIA,C.CODIGO_POSTAL,C.CIUDAD DELEG_MUN,C.CIUDAD,C.ESTADO,C.TELEFONO,C.TELEFONO_OFICINA "
                + ",'' FECHA_NAC,'' SEXO,'' ESTADO_CIVIL,'' COMPANIA,( CASE TU.NOMBRE_TIPO when 'AGENTE DE VENTAS' THEN 'AGENTE' ELSE TU.NOMBRE_TIPO END) PUESTO,C.EMAIL,'' SALDO_PUNTOS,'' FECHA_ULTIMA_ACUMULACION,'' FECHA_ULTIMO_CANJE,C.TIPO_CLIENTE,TO_CHAR(C.FECHA_CREACION,'DD/MM/RRRR'),TO_CHAR(C.FECHA_BAJA,'DD/MM/RRRR')  "
                + ",C.CREADO_POR,C.FECHA_CREACION,C.ULTIMA_ACTUALIZACION_POR,C.ULTIMA_FECHA_ACTUALIZACION,'P' REPLICACION_ESTADO,'0' REPLICACION_INTENTOS,'' REPLICACION_ORIGEN,U.CONTRASENA_ENCRIPTADA  "
                + ",c.TELEFONO_CELULAR,'' NO_PASAPORTE,c.PAIS,c.TELEFONO_FAX,c.RFC,( CASE TU.NOMBRE_TIPO when 'ADMINISTRADOR' THEN 0 ELSE u.USUARIO_RELACIONADO_ID END) CLIENTE_RELACIONADO_ID "
                + ",'' ADICIONAL1 ,c.ADICIONAL2,c.ADICIONAL3,c.ADICIONAL4,c.ADICIONAL5 "
                + "FROM ER_USUARIOS_TBL U  "
                + "LEFT JOIN ER_TIPOS_USUARIO_TBL tu on TU.TIPO_USUARIO_ID = U.TIPO_USUARIO_ID "
                + "LEFT JOIN ER_USUARIOS_CLIENTES_TBL UC ON U.USUARIO_ID = UC.USUARIO_ID  "
                + "LEFT JOIN  ER_CLIENTES_TBL C ON C.CLIENTE_ID = UC.CLIENTE_ID "
                + "WHERE U.USUARIO_RELACIONADO_ID = (select usc.USUARIO_ID from ER_USUARIOS_CLIENTES_TBL usc where usc.cliente_id  = "+administradorId+")";

        return (Vector) em.createNativeQuery(qry).getResultList();        
    }
    
    
    public String getContraseniaNueva(){   
    String[] claves={"A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9","0"};
            String contrasenia = "";
            for(int i=0; i<6;i++)
            {
                int pos = new Double(Math.random() * claves.length).intValue();
                contrasenia = contrasenia + claves[pos];
            }
            return contrasenia;
    }
    


    
    public int agregarCliente(Cliente cliente){
    Vector vvusweb = (Vector)em.createNativeQuery("select us.USUARIO_ID from TMS_USUARIOS_TBL us where us.USUARIO_NOMBRE = 'USUARIO WEB'").getResultList(); 
     if(vvusweb.size()==0) 
         return -1;
     Vector vusweb = (Vector)vvusweb.get(0);
     long usweb = Long.valueOf(vusweb.get(0).toString());
    String psw = "";
     try {
            psw = pwdEnc.encriptar(cliente.getContrasenia());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     try{
            System.out.println(" Cliente.informacion " + cliente.getnombre() +" "+ cliente.getApellidos() +" "+ cliente.getCalle());
            System.out.println("Se ejecuta "+cliente.getInsertar(usweb, psw));
            if(em.createNativeQuery(cliente.getInsertar(usweb, psw)).executeUpdate()==0) return 1;//no inserto
            return 0; // inserto
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            return 1;
        }         
    }
   
    public int modificacionCliente(Cliente cliente){
    Vector vvusweb = (Vector)em.createNativeQuery("select us.USUARIO_ID from TMS_USUARIOS_TBL@TMS_LINK.ESTRELLAROJA.COM.MX us where us.USUARIO_NOMBRE = 'USUARIO WEB'").getResultList();
     if(vvusweb.size()==0) 
         return -1;
     Vector vusweb = (Vector)vvusweb.get(0);
     long usweb = Long.valueOf(vusweb.get(0).toString());
    System.out.println("USUARIO WEB: "+usweb);
    String psw = "";
     try {
            psw = pwdEnc.encriptar(cliente.getContrasenia());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_clientes_tbl "+
                "where CLIENTE_ID = "+cliente.getClienteId()+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    cliente.getModificar(usweb,psw)+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            return 1;
        }        
    }    
    
    public Vector getreservacionesCliente(long clienteId, String fechaInicial, String fechaFinal){
        Vector vreserv = null;
        String qry = "select to_char(c.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') fecha_corrida " +
                ",c.ORIGEN " +
                ",c.DESTINO " +
                ", c.SERVICIO " +
                ", c.EMPRESA " +
                ", r.NO_ASIENTO " +
                ", tp.NOMBRE_TIPO tipo_pasajero " +
                ", (Xer_Tms_Ws_Pkg.Tms_GetTarXTipoXCorrida_Fnc(c.SERVICIO, c.ORIGEN, c.DESTINO, 'SEN', 'MXP', tp.LETRA_TIPO)) importe " +
                ", r.RESPONSABLE_RESERVACION " +
                ", r.CLAVE_RESERVACION " +
                ", case r.CANCELABLE when 'C' THEN 'CANCELABLE' WHEN 'R' THEN 'NO CANCELABLE' end tipo_reservacion " +
                ", r.ESTADO_RESERVACION " +
                ", to_char(r.FECHA_CREACION,'DD/MM/RRRR HH24:MI') fecha_reservacion " +
                "from TMS_reservaciones_TBL r " +
                ",TMS_corridas_venta_TBL c  " +
                ",TMS_TIPOS_PASAJERO_TBL tp " +
                "where c.Corrida_id = r.CORRIDA_ID " +
                "and tp.LETRA_TIPO = r.TIPO_PASAJERO " +
                "and r.CLIENTE_ID = "+clienteId+" " +
                "and trunc(r.FECHA_CREACION) between to_date('"+fechaInicial+"','DD/MM/RRRR') and to_date('"+fechaFinal+"','DD/MM/RRRR') " +
                "order by r.FECHA_CREACION";
        vreserv = (Vector)em.createNativeQuery(qry).getResultList();
        return vreserv;
    }

  public Vector getcomprasCliente(long clienteId, String fechaInicial, String fechaFinal){
        Vector vreserv = null;
        String qry = "select to_char(c.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') fecha_corrida " +
                ", bv.ORIGEN " +
                ", bv.DESTINO " +
                ", bv.SERVICIO " +
                ", bv.EMPRESA " +
                ", bv.NO_ASIENTO " +
                ", tp.NOMBRE_TIPO tipo_pasajero " +
                ", bv.NOMBRE_PASAJERO  " +
                ", bv.IMPORTE_BOLETO " +
                ", bv.FOLIO_PREIMPRESO folio  " +
                ", case bv.TIPO_OPERACION when 'VT' then 'COMPRA' when 'CN' then 'CANCELACION' when 'HO' then 'CAMBIO DE HORARIO' when 'VA' then 'COMPRA BOLETO AVIERTO' when 'AC' then 'CANJE DE BOLETO ABIERTO'  end operacion " +
                ", to_char(bv.FECHA_HORA_VENTA ,'DD/MM/RRRR HH24:MI') fecha_operacion " +
                "from TMS_BOLETOS_VENTA_TBL bv " +
                ",TMS_corridas_TBL c  " +
                ",TMS_TIPOS_PASAJERO_TBL tp " +
                "where c.CLAVE_CORRIDA = bv.CLAVE_CORRIDA " +
                "and tp.LETRA_TIPO = bv.TIPO_PASAJERO		 " +
                "and bv.CLIENTE_ID = "+clienteId+" " +
                "and trunc(bv.FECHA_HORA_VENTA) between to_date('"+fechaInicial+"','DD/MM/RRRR') and to_date('"+fechaFinal+"','DD/MM/RRRR') " +
                "order by bv.FECHA_HORA_VENTA";
        vreserv = (Vector)em.createNativeQuery(qry).getResultList();
        return vreserv;
    }

    public EncriptarMD5 getPwdEnc() {
        return pwdEnc;
    }

    public int agregarAgente(Cliente cliente)// throws AgenteNoCreadoException
    {
        String insertQry = "";
        String centralDBLink = "";
        String vClienteId = "";
        Vector vAgenciaDatos = null;
        String vUsuarioId = em.createNativeQuery("select us.USUARIO_ID from TMS_USUARIOS_TBL us where us.USUARIO_NOMBRE = 'USUARIO WEB'").getSingleResult().toString();
        vUsuarioId = vUsuarioId.replace('[', ' ');
        vUsuarioId = vUsuarioId.replace(']', ' ');
        vUsuarioId = vUsuarioId.trim();
        //if(vvusweb.size()==0)
          //   return -1;
        //Vector vusweb = (Vector)vvusweb.get(0);
        //long usweb = Long.valueOf(vusweb.get(0).toString());
        String psw = "";
         try {
                psw = pwdEnc.encriptar(cliente.getContrasenia());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         try{
                // Seleccionar la Agencia
                //insertQry = "SELECT NOMBRE_DBLINK FROM tms_base_datos_config_tbl WHERE NOMBRE_ESQUEMA = 'XERTMS'";
                //centralDBLink = em.createNativeQuery(insertQry).getSingleResult().toString();
                //centralDBLink = centralDBLink.replace('[', ' ');
                //centralDBLink = centralDBLink.replace(']', ' ');
                //centralDBLink = centralDBLink.trim();
                insertQry = "SELECT AGE.AGENCIA_ID,agv.AGENTE_ID,agv.TIPO_PAGO_ID  " +
                              "FROM TMS_AGENCIAS_VIAJE_TBL age " +
                               ",TMS_AGENTES_VENTA_TBL agv " +
                                ",TMS_CLIENTES_TBL cli " +
                             "WHERE AGE.AGENCIA_ID = AGV.AGENCIA_ID " +
                               "AND AGV.ADICIONAL1 = CLI.CLIENTE_ID " +
                               "AND CLI.CLIENTE_ID = " + String.valueOf(cliente.getClienteRelacionadoId());
                //System.out.println("Datos de Agencia:"+insertQry);
                vAgenciaDatos = (Vector)em.createNativeQuery(insertQry).getSingleResult();
                // Obtener el ClienteID
                insertQry = "SELECT cli.CLIENTE_ID FROM TMS_CLIENTES_TBL cli WHERE EMAIL = '"+cliente.getEmail()+"'";
                vClienteId = em.createNativeQuery(insertQry).getSingleResult().toString();
                vClienteId = vClienteId.replace('[', ' ');
                vClienteId = vClienteId.replace(']', ' ');
                vClienteId = vClienteId.trim();
                // Insertar Agente
                System.out.println(" Cliente.informacion: " + cliente.getnombre() +"-"+ cliente.getApellidos() +"-"+ cliente.getCalle() +"-"+ cliente.getFechaDesde());
                insertQry = "INSERT INTO TMS_AGENTES_VENTA_TBL " +
                "(AGENTE_ID, AGENCIA_ID, AGENTE_USUARIO, AGENTE_CONTRASENIA, AGENTE_NOMBRE, AGENTE_APELLIDO_PATERNO, AGENTE_APELLIDO_MATERNO, AGENTE_DIRECCION1, AGENTE_DIRECCION2, AGENTE_RFC, AGENTE_TELEFONO1, AGENTE_TELEFONO2, AGENTE_FECHA_NACIMIENTO, AGENTE_SEXO, AGENTE_ESTADO_CIVIL, AGENTE_EMAIL, AGENTE_PUESTO, FECHA_ULTIMA_VENTA, FECHA_ULTIMO_CANJE, AGENTE_TIPO, FECHA_EFECTIVA_DESDE, FECHA_EFECTIVA_HASTA, AGENTE_RELACIONADO_ID, TIPO_PAGO_ID, ADICIONAL1, ADICIONAL2, ADICIONAL3, ADICIONAL4, ADICIONAL5, CREADO_POR, FECHA_CREACION, ULTIMA_ACTUALIZACION_POR, ULTIMA_FECHA_ACTUALIZACION, REPLICACION_ESTADO, REPLICACION_INTENTOS, REPLICACION_ORIGEN) "+
                "VALUES (TMS_AGENTES_VENTA_SEQ.NEXTVAL, ";
                insertQry = insertQry + String.valueOf(vAgenciaDatos.get(0))+", ";
                insertQry = insertQry + (cliente.getEmail()==null ?"null," :"'" + cliente.getEmail()) + "', ";
                insertQry = insertQry + "'"+psw+"', ";
                insertQry = insertQry + (cliente.getnombre()==null ?"null," :"'" + cliente.getnombre()) + "', ";
                insertQry = insertQry + (cliente.getApellidos()==null ?"null," :"'" + cliente.getApellidos()) + "', ";
                insertQry = insertQry + (cliente.getApellidos()==null ?"null," :"'" + cliente.getApellidos()) + "', ";
                insertQry = insertQry + "'" +(cliente.getCalle()==null ?"null" :cliente.getCalle()) + "_" + (cliente.getNumeroExt()==null ?"null" : cliente.getNumeroExt()) + "_" + (cliente.getNumeroInt()==null ?"null" :cliente.getNumeroInt()) + "_" + (cliente.getColonia()==null ?"null" : cliente.getColonia()) + "', ";
                insertQry = insertQry + "'" +(cliente.getCodigoPostal()==null ?"null" :cliente.getCodigoPostal()) + "_" + (cliente.getDelegMun()==null ?"null" : cliente.getDelegMun()) + "_" + (cliente.getCiudad()==null ?"null" :cliente.getCiudad()) + "_" + (cliente.getEstado()==null ?"null" : cliente.getEstado()) + "', ";
                insertQry = insertQry + "null, ";
                insertQry = insertQry + (cliente.getTelefonoCasa()==null ?"null," :"'" + cliente.getTelefonoCasa() + "', ");
                insertQry = insertQry + (cliente.getTelefonoCelular()==null ?"null," :"'" + cliente.getTelefonoCelular() + "', ");
                insertQry = insertQry + (cliente.getFechaNac()==null ?"null," :"to_date('"+ cliente.getFechaNac() + "','DD/MM/RRRR'), ");
                insertQry = insertQry + (cliente.getSexo()==null ?"null," :"'" + cliente.getSexo()) + "', ";
                insertQry = insertQry + (cliente.getEstadoCivil()==null ?"null," :"'" + cliente.getEstadoCivil() + "', ");
                insertQry = insertQry + (cliente.getEmail()==null ?"null," :"'" + cliente.getEmail() + "', ");
                insertQry = insertQry + (cliente.getPuesto()==null ?"null," :"'" + cliente.getPuesto()) + "', ";
                insertQry = insertQry + "null, ";
                insertQry = insertQry + "null, ";
                insertQry = insertQry + (cliente.getPuesto()==null ?"null," :"'" + cliente.getPuesto()) + "', ";
                insertQry = insertQry + "trunc(SYSDATE),";
                insertQry = insertQry + (cliente.getFechaHasta()==null ?"null," :"to_date('"+ cliente.getFechaHasta() + "','DD/MM/RRRR'), ");
                insertQry = insertQry + String.valueOf(vAgenciaDatos.get(1)) + ", ";
                insertQry = insertQry + String.valueOf(vAgenciaDatos.get(2)) + ", ";
                insertQry = insertQry + vClienteId + ", ";
                insertQry = insertQry + (cliente.getAdicional2() == null ?"null," :"'" + cliente.getAdicional2() + "', ");
                insertQry = insertQry + (cliente.getAdicional3() == null ?"null," :"'" + cliente.getAdicional3() + "', ");
                insertQry = insertQry + (cliente.getAdicional4() == null ?"null," :"'" + cliente.getAdicional4() + "', ");
                insertQry = insertQry + (cliente.getAdicional5() == null ?"null," :"'" + cliente.getAdicional5() + "', ");
                insertQry = insertQry + vUsuarioId + ",sysdate, ";
                insertQry = insertQry + vUsuarioId + ",sysdate,'P',0,'XERTMS')";

                System.out.println("Se ejecuta "+insertQry);
                if(em.createNativeQuery(insertQry).executeUpdate()==0) return 1;//no inserto
                return 0; // inserto
            }
            catch(Exception nrex){
                //new AgenteNoCreadoException("NO INSERTO AGENTE:\n"+nrex.getMessage());
                nrex.printStackTrace();
                return 1;
            }
    }

    public String getFecha(){
        return em.createNativeQuery("select to_char(SYSDATE,'DD/MM/RRRR HH24:MI') from dual ").getSingleResult().toString();
    }

}
