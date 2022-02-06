/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tarjetasNoPermitidas;

import java.util.Vector;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class generalFacadeBean implements generalFacadeBeanRemote {
    
    @PersistenceContext
    private EntityManager em;

    public generalFacadeBean(){

    }

    public Vector buscarTarjetas() {
        return (Vector)em.createNativeQuery("SELECT t.TARJETA_NO_PERMITIDA_ID, t.numero_tarjeta FROM TMS_TARJETAS_NO_PERMITIDAS_TBL t where ESTADO_TARJETA = 'A'").getResultList();
    }


    public boolean agregarTarjeta(String NoTarjeta,long usuarioId)
    {
        String query="insert into TMS_TARJETAS_NO_PERMITIDAS_TBL (TARJETA_NO_PERMITIDA_ID,NUMERO_TARJETA,ESTADO_TARJETA,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                + "values (TMS_TARJETAS_NO_PERMITIDAS_SEQ.nextval,'"+NoTarjeta+"','A','','','','','',"+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE) ";
       try
       {
         em.createNativeQuery(query).executeUpdate();
       }
       catch(EJBException ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    public boolean removerTarjeta(long idTarjeta,long usuarioId)
    {
        String query="update TMS_TARJETAS_NO_PERMITIDAS_TBL SET ESTADO_TARJETA = 'I',ULTIMA_ACTUALIZACION_POR = "+usuarioId+" , ULTIMA_FECHA_ACTUALIZACION = SYSDATE "
                + "where  TARJETA_NO_PERMITIDA_ID = "+idTarjeta ;
       try
       {
         em.createNativeQuery(query).executeUpdate();
       }
       catch(EJBException ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

}
