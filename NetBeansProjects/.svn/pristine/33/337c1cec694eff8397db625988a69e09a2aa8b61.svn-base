/*
 * TmsParametrosConfigTblFacade.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import TmsRecaudacion.entidad.TmsParametrosConfigTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsParametrosConfigTblFacade implements TmsParametrosConfigTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsParametrosConfigTblFacade */
    public TmsParametrosConfigTblFacade() {
    }

    public void create(TmsParametrosConfigTbl tmsParametrosConfigTbl) {
        em.persist(tmsParametrosConfigTbl);
    }

    public void edit(TmsParametrosConfigTbl tmsParametrosConfigTbl) {
        em.merge(tmsParametrosConfigTbl);
    }

    public void destroy(TmsParametrosConfigTbl tmsParametrosConfigTbl) {
        em.merge(tmsParametrosConfigTbl);
        em.remove(tmsParametrosConfigTbl);
    }

    public TmsParametrosConfigTbl find(Object pk) {
        return (TmsParametrosConfigTbl) em.find(TmsParametrosConfigTbl.class, pk);
    }

    public List findAll() {
//       List<TmsParametrosConfigTbl> todosparametros = em.createQuery("select object(o) from TmsParametrosConfigTbl as o where o.parametroCodigo in ('P_R_RECNRE','P_R_RECLIM','P_R_MAXTAR','P_R_VTABORDO','P_R_VTASIS','P_R_PGOTAR','P_R_PORRET')").getResultList();
        List<TmsParametrosConfigTbl> todosparametros = em.createNativeQuery("select * from Tms_Parametros_Config_Tbl o where (o.parametro_CODIGO in ('P_R_RECNRE','P_R_RECLIM','P_R_MAXTAR','P_R_VTABORDO','P_R_VTASIS','P_R_PGOTAR','P_R_PORRET','P_R_BUSALACUM') or o.PARAMETRO_CODIGO in (select distinct(codigo_parametro) from Tms_Servicios_Gastos_Tbl where codigo_parametro<>' '))",TmsParametrosConfigTbl.class).getResultList(); 
//        List<TmsParametrosConfigTbl> todosparametros = em.createNativeQuery("select * from Tms_Parametros_Config_Tbl o where o.PARAMETRO_CODIGO in (select distinct(codigo_parametro) from Tms_Servicios_Gastos_Tbl where codigo_parametro<>' ')",TmsParametrosConfigTbl.class).getResultList();       
       for(int i=0; i<todosparametros.size(); i++)
       {
           em.refresh(todosparametros.get(i));
           int num = todosparametros.get(i).getTmsGlobalParametrosTblCollection().size();
           num = todosparametros.get(i).getTmsEmpresaParametrosTblCollection().size();
           num = todosparametros.get(i).getTmsServicioParametrosTblCollection().size();
           num = todosparametros.get(i).getTmsTerminalParametrosTblCollection().size();
           num = todosparametros.get(i).getTmsCajaParametrosTblCollection().size();
           num = todosparametros.get(i).getTmsRutaParametrosTblCollection().size();
//           System.out.println("SizeCollGobal("+i+")"+todosparametros.get(i).getTmsGlobalParametrosTblCollection().size());
//           System.out.println("SizeCollEmpresa("+i+")"+todosparametros.get(i).getTmsEmpresaParametrosTblCollection().size());
//           System.out.println("SizeCollServicio("+i+")"+todosparametros.get(i).getTmsServicioParametrosTblCollection().size());
//           System.out.println("SizeCollTerminal("+i+")"+todosparametros.get(i).getTmsTerminalParametrosTblCollection().size());
//           System.out.println("SizeCollCaja("+i+")"+todosparametros.get(i).getTmsCajaParametrosTblCollection().size());
//           System.out.println("SizeCollRuta("+i+")"+todosparametros.get(i).getTmsRutaParametrosTblCollection().size());
       }
    return todosparametros;
    }
    
    
    public TmsParametrosConfigTbl busquedaPorCodigo(String codigo){
        TmsParametrosConfigTbl param = (TmsParametrosConfigTbl) em.createNamedQuery("TmsParametrosConfigTbl.findByParametroCodigo").setParameter("parametroCodigo",codigo).getSingleResult();
        em.refresh(param);
        int num = param.getTmsGlobalParametrosTblCollection().size();
        num = param.getTmsEmpresaParametrosTblCollection().size();
        num = param.getTmsServicioParametrosTblCollection().size();
        num = param.getTmsTerminalParametrosTblCollection().size();
        num = param.getTmsCajaParametrosTblCollection().size();
        num = param.getTmsRutaParametrosTblCollection().size();
//        System.out.println("Tamaño dela colleccionGlobal: "+param.getTmsGlobalParametrosTblCollection().size());
//        System.out.println("Tamaño dela colleccionEmpresas: "+param.getTmsEmpresaParametrosTblCollection().size());
//        System.out.println("Tamaño dela colleccionServicios: "+param.getTmsServicioParametrosTblCollection().size());
//        System.out.println("Tamaño dela colleccionTerminales: "+param.getTmsTerminalParametrosTblCollection().size());
//        System.out.println("Tamaño dela colleccionCajas: "+param.getTmsCajaParametrosTblCollection().size());
//        System.out.println("Tamaño dela colleccionRutas: "+param.getTmsRutaParametrosTblCollection().size());
        return param;
    }
    
    
   public Object queryBuscaTerminalId(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }     

   public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }     


}
