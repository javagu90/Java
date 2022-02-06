/*
 * tmscargaparamglobrecauda.java
 *
 * Created on 30 de agosto de 2007, 01:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsparamgloblalrecaudacion;

import TmsRecaudacion.entidad.TmsCajaParametrosTbl;
import TmsRecaudacion.entidad.TmsRutaParametrosTbl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import TmsRecaudacion.entidad.TmsEmpresaParametrosTbl;
import TmsRecaudacion.entidad.TmsGlobalParametrosTbl;
import TmsRecaudacion.entidad.TmsParametrosConfigTbl;
import TmsRecaudacion.entidad.TmsServicioParametrosTbl;
import TmsRecaudacion.entidad.TmsTerminalParametrosTbl;
import tmsrecaudacion.jdlg_advertencia;
import tmsrecaudacion.jdlg_error;

/**
 *
 * @author vgonzalez
 */
public class tmscargaparamglobrecauda {
    private Vector codigos = new Vector();
    private Vector valores = new Vector();
    Vector noaplicables = new Vector();
    //String[] arraynoapl = {"P_R_RECLIM","P_R_RECLIM","P_R_PORRET","P_R_PAGPAQ","P_R_PAGCAP","P_R_ANTPEN","P_R_PAGGUA","P_R_MAXTR","P_R_PGOTAR","P_R_PGOPEA","P_R_PGOSLD","P_R_VLRPRTAPR","P_TMPPRITUR","P_TMPSEGTUR","P_TMPTERTUR","P_VLRPRTAPV","P_LIMINTFAL"};
    String[] arraynoapl = {"P_TMPPRITUR","P_TMPSEGTUR","P_TMPTERTUR","P_VLRPRTAPV","P_LIMINTFAL","P_TMPCANRES","P_TMPPRITUR","P_TMPDIABAB","P_TMPINIVAC","P_TMPFINVAC","P_LIMMINBOL","P_VLRPRTAPV","P_LIMINTFAL","P_VLREMPPRI","P_VLRFOLIOIN","P_LIMMENCOR","P_LIMMENCOR","P_LIMSENCOR","P_LIMPROCOR","P_LIMCORCOR","P_LIMOSRCOR","P_LIMESTCOR","P_LIMTARNRE","P_LIMTARNRE","P_VLRVENNAS","P_LIMEFEREC","P_LIMEFENVT","P_LIMFONMAX","P_TMPDESCAN","P_MTOMINTJT"};
    TmsPGRManagedBean busquedas = new TmsPGRManagedBean();
    SimpleDateFormat ffecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fhora  = new SimpleDateFormat("HH:mm");
    SimpleDateFormat fdia = new SimpleDateFormat ("EEEE");
    String dia = "";
    String turno = "";
    BigDecimal idterminal;

    /**
     * Creates a new instance of tmscargaparamglobrecauda
     */
    public tmscargaparamglobrecauda() {
        for(int i=0; i<arraynoapl.length; i++)
            noaplicables.add(arraynoapl[i]);
        turno = busquedas.getTurno();
        System.out.println("Turno actual: "+turno);
        idterminal = BigDecimal.valueOf(Long.valueOf(busquedas.getTerminalId()));
        System.out.println("La terminal:  "+busquedas.getTerminalId());
        Date ddia = busquedas.FechaServidor();
        dia = fdia.format(ddia).toLowerCase();
        System.out.println("Hoy es: "+dia);
        List<TmsParametrosConfigTbl> todosparametros = (List<TmsParametrosConfigTbl>)busquedas.parametrosConfigTblFacadeRemote.findAll();
        System.out.println("el numero de parametros globales es de: "+todosparametros.size());
        for(int i=0; i<todosparametros.size(); i++)
        {
            if(noaplicables.indexOf(todosparametros.get(i).getParametroCodigo())==-1)
            {
                System.out.println("Collection "+todosparametros.get(i).getParametroCodigo()+": "+todosparametros.get(i).getTmsCajaParametrosTblCollection().size());
                String tipo_parametro = todosparametros.get(i).getParametroTipo();
                if(tipo_parametro.equals("GLOBAL"))param_globales(todosparametros.get(i));
                if(tipo_parametro.equals("EMPRESA"))param_empresa(todosparametros.get(i));
                if(tipo_parametro.equals("SERVICIO"))param_servicio(todosparametros.get(i));
                if(tipo_parametro.equals("TERMINAL"))param_terminal(todosparametros.get(i));
                //if(tipo_parametro.equals("CAJA"))param_caja(todosparametros.get(i));
                if(tipo_parametro.equals("RUTA"))param_ruta(todosparametros.get(i));
            }
        }
        
        System.out.println("El tamaño de vector de Codigo es es de :"+getCodigos().size()+" ==> "+getCodigos());
        System.out.println("El tamaño de vector de Valores es es de :"+getValores().size()+" ==> "+getValores());
    }
    
    private void param_globales(TmsParametrosConfigTbl pglobales){
         System.out.println("Entra a param_globales");
        if(pglobales.getTmsGlobalParametrosTblCollection().size()>0)
        {
            getCodigos().add(pglobales.getParametroCodigo());
            Collection<TmsGlobalParametrosTbl> collglobales = pglobales.getTmsGlobalParametrosTblCollection();
            Iterator ipg = collglobales.iterator();
            while(ipg.hasNext()){
               TmsGlobalParametrosTbl globales = (TmsGlobalParametrosTbl)ipg.next();
                getValores().add(globales.getParametroValor());
            }
        }
    }       
    
    private void param_empresa(TmsParametrosConfigTbl pempresa){
         System.out.println("Entra a param_empresa");
        if(pempresa.getTmsEmpresaParametrosTblCollection().size()>0)
        {
            Collection<TmsEmpresaParametrosTbl> collempresas = pempresa.getTmsEmpresaParametrosTblCollection();
            Iterator ipg = collempresas.iterator();
            while(ipg.hasNext()){
               TmsEmpresaParametrosTbl empresas = (TmsEmpresaParametrosTbl)ipg.next();
                getCodigos().add(pempresa.getParametroCodigo()+""+empresas.getEmpresaId());
                getValores().add(empresas.getParametroValor());
            }
        }
         
        
    }

    private void param_servicio(TmsParametrosConfigTbl pservicio){
        System.out.println("Entra a param_servicio");
        if(pservicio.getTmsServicioParametrosTblCollection().size()>0)
        {
            Collection<TmsServicioParametrosTbl> collservicio = pservicio.getTmsServicioParametrosTblCollection();
            Iterator ipg = collservicio.iterator();
            while(ipg.hasNext()){
             String valor = "";
             TmsServicioParametrosTbl servicio = (TmsServicioParametrosTbl)ipg.next();
             if(servicio.getTurno()!=null)
              {
                    if(servicio.getTurno().equals(turno) || servicio.getTurno().equals("T"))
                     {
                        if(dia.equals("lunes")){if(servicio.getLunesValor() == null)valor = "0"; else valor = servicio.getLunesValor();}
                        if(dia.equals("martes")){if(servicio.getMartesValor() == null)valor = "0";valor = servicio.getMartesValor();}
                        if(dia.equals("miércoles")){if(servicio.getMiercolesValor() == null)valor = "0";valor = servicio.getMiercolesValor();}
                        if(dia.equals("jueves")){if(servicio.getJuevesValor() == null)valor = "0";valor = servicio.getJuevesValor();}
                        if(dia.equals("viernes")){if(servicio.getViernesValor() == null)valor = "0";valor = servicio.getViernesValor();}
                        if(dia.equals("sábado")){if(servicio.getSabadoValor() == null)valor = "0";valor = servicio.getSabadoValor();}
                        if(dia.equals("domingo")){if(servicio.getDomingoValor() == null)valor = "0";valor = servicio.getDomingoValor();}
                       getCodigos().add(pservicio.getParametroCodigo()+"-S"+servicio.getServicioId());
                       getValores().add(valor);
                        getCodigos().add(pservicio.getParametroCodigo()+"-SL"+servicio.getServicioId());getValores().add(servicio.getLunesValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-SM"+servicio.getServicioId());getValores().add(servicio.getMartesValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-SW"+servicio.getServicioId());getValores().add(servicio.getMiercolesValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-SJ"+servicio.getServicioId());getValores().add(servicio.getJuevesValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-SV"+servicio.getServicioId());getValores().add(servicio.getViernesValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-SS"+servicio.getServicioId());getValores().add(servicio.getSabadoValor());
                        getCodigos().add(pservicio.getParametroCodigo()+"-sD"+servicio.getServicioId());getValores().add(servicio.getDomingoValor());
                    }
             }
                 else
                 {
                       getCodigos().add(pservicio.getParametroCodigo()+"-S"+servicio.getServicioId());
                       getValores().add(servicio.getParametroValor());
                 }  
            }
        }
    }

    /*
    private void param_terminal(TmsParametrosConfigTbl pterminal){
        System.out.println("Entra a param_terminales");
        if(pterminal.getTmsTerminalParametrosTblCollection().size()>0)
        {
            boolean correcto = true;
            String valor = "";
            int numc = 0;
            Collection<TmsTerminalParametrosTbl> collterminal = pterminal.getTmsTerminalParametrosTblCollection();
            Iterator ipg = collterminal.iterator();
            while(ipg.hasNext()){
             TmsTerminalParametrosTbl terminal = (TmsTerminalParametrosTbl)ipg.next();
             if((Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal)))
             { numc++;
                System.out.println(""+terminal.getTerminalId()+"="+idterminal+"==>"+numc);
             }
            }
             System.out.println("El numero de valores para la terminal es de: "+numc);
            ipg = collterminal.iterator();
            while(ipg.hasNext()){
                TmsTerminalParametrosTbl terminal = (TmsTerminalParametrosTbl)ipg.next();
                  System.out.println("El tamaño de la coleccion es: "+collterminal.size()+"  y el turno es: "+terminal.getTurno());
             if(numc>1 && terminal.getTurno().equals("T")) 
               JOptionPane.showMessageDialog(null,"¡El parametro "+pterminal.getParametroCodigo()+" esta mal configurado!","Error de configuracion de parametro global",JOptionPane.WARNING_MESSAGE);
              else
              {
                     if(terminal.getTurno() ==  null &&  (Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal)))
                   {
                     getCodigos().add(pterminal.getParametroCodigo());
                     getValores().add(terminal.getParametroValor());
                   }
                   else
                   {
                    if((terminal.getTurno().equals("T") || terminal.getTurno().equals(turno)) && (Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal)) )
                    {                 
                        if(dia.equals("lunes"))valor = terminal.getLunesValor();
                        if(dia.equals("martes"))valor = terminal.getMartesValor();
                        if(dia.equals("miércoles"))valor = terminal.getMiercolesValor();
                        if(dia.equals("jueves"))valor = terminal.getJuevesValor();
                        if(dia.equals("viernes"))valor = terminal.getViernesValor();
                        if(dia.equals("sábado"))valor = terminal.getSabadoValor();
                        if(dia.equals("domingo"))valor = terminal.getDomingoValor();
                        getCodigos().add(pterminal.getParametroCodigo());
                        getValores().add(valor);
                    }
                }
              }
            }
        }
        
    }
    */
    
    
    private void param_terminal(TmsParametrosConfigTbl pterminal){
        System.out.println("Entra a param_terminales");
        if(pterminal.getTmsTerminalParametrosTblCollection().size()>0)
        {
            boolean correcto = true;
            String valor = "";
            int numc = 0;
            Collection<TmsTerminalParametrosTbl> collterminal = pterminal.getTmsTerminalParametrosTblCollection();
            Iterator ipg = collterminal.iterator();
            while(ipg.hasNext()){
             TmsTerminalParametrosTbl terminal = (TmsTerminalParametrosTbl)ipg.next();
                System.out.println("Verifica: "+terminal.getTerminalId()+"="+idterminal+"==>"+numc);
             if((Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal)))
             { numc++;
                System.out.println(""+terminal.getTerminalId()+"="+idterminal+"==>"+numc);
             }
            }
             System.out.println("El numero de valores para la terminal es de: "+numc);
            ipg = collterminal.iterator();
            while(ipg.hasNext()){
                TmsTerminalParametrosTbl terminal = (TmsTerminalParametrosTbl)ipg.next();
              System.out.println("El tamaño de la coleccion es: "+collterminal.size()+"  y el turno es: "+terminal.getTurno());
//             if(numc>1 && terminal.getTurno().equals("T")) 
//               new jdlg_advertencia("¡El parametro "+pterminal.getParametroCodigo()+" esta mal configurado!","","¡Error de configuracion de parametro global!");
//              else
//              {
                   if(terminal.getTurno() ==  null &&  (Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal)))
                   {
                     getCodigos().add(pterminal.getParametroCodigo());
                     getValores().add(terminal.getParametroValor());
                   }
                   else
                   {
                    if((Integer.parseInt(""+terminal.getTerminalId())) == (Integer.parseInt(""+idterminal))){
                        if((terminal.getTurno().equals("T") || terminal.getTurno().equals(turno)))
                        {                 
                            if(dia.equals("lunes"))valor = terminal.getLunesValor();
                            if(dia.equals("martes"))valor = terminal.getMartesValor();
                            if(dia.equals("miércoles"))valor = terminal.getMiercolesValor();
                            if(dia.equals("jueves"))valor = terminal.getJuevesValor();
                            if(dia.equals("viernes"))valor = terminal.getViernesValor();
                            if(dia.equals("sábado"))valor = terminal.getSabadoValor();
                            if(dia.equals("domingo"))valor = terminal.getDomingoValor();
                            getCodigos().add(pterminal.getParametroCodigo());
                            getValores().add(valor);
                        }
                    }
                }
              //}
              
            }
        }
        
    }    
    
    private void param_ruta(TmsParametrosConfigTbl pruta){
        System.out.println("Entra a param_ruta");
        if(pruta.getTmsRutaParametrosTblCollection().size()>0)
        {
            String valor = "";
            int numc = 0;
            Collection<TmsRutaParametrosTbl> collruta = pruta.getTmsRutaParametrosTblCollection();
            Iterator ipg = collruta.iterator();
            ipg = collruta.iterator();
            String ruta_id = "";
            while(ipg.hasNext()){
              TmsRutaParametrosTbl ruta = (TmsRutaParametrosTbl)ipg.next();
              System.out.println("El tamaño de la coleccion es: "+collruta.size()+"  y el turno es: "+ruta.getTurno());
             if(ruta.getTurno()!=null)
              {
                    if(ruta.getTurno().equals(turno) || ruta.getTurno().equals("T"))
                     {
                        if(dia.equals("lunes")){if(ruta.getLunesValor() == null)valor = "0"; else valor = ruta.getLunesValor();}
                        if(dia.equals("martes")){if(ruta.getMartesValor() == null)valor = "0";valor = ruta.getMartesValor();}
                        if(dia.equals("miércoles")){if(ruta.getMiercolesValor() == null)valor = "0";valor = ruta.getMiercolesValor();}
                        if(dia.equals("jueves")){if(ruta.getJuevesValor() == null)valor = "0";valor = ruta.getJuevesValor();}
                        if(dia.equals("viernes")){if(ruta.getViernesValor() == null)valor = "0";valor = ruta.getViernesValor();}
                        if(dia.equals("sábado")){if(ruta.getSabadoValor() == null)valor = "0";valor = ruta.getSabadoValor();}
                        if(dia.equals("domingo")){if(ruta.getDomingoValor() == null)valor = "0";valor = ruta.getDomingoValor();}
                        getCodigos().add(pruta.getParametroCodigo()+"-R"+ruta.getRutaId().getRutaId());
                        getValores().add(valor);
                        getCodigos().add(pruta.getParametroCodigo()+"-RL"+ruta.getRutaId().getRutaId());getValores().add(ruta.getLunesValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RM"+ruta.getRutaId().getRutaId());getValores().add(ruta.getMartesValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RW"+ruta.getRutaId().getRutaId());getValores().add(ruta.getMiercolesValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RJ"+ruta.getRutaId().getRutaId());getValores().add(ruta.getJuevesValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RV"+ruta.getRutaId().getRutaId());getValores().add(ruta.getViernesValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RS"+ruta.getRutaId().getRutaId());getValores().add(ruta.getSabadoValor());
                        getCodigos().add(pruta.getParametroCodigo()+"-RD"+ruta.getRutaId().getRutaId());getValores().add(ruta.getDomingoValor());
                    }
             }
                 else
                 {
                    getCodigos().add(pruta.getParametroCodigo()+"-R"+ruta.getRutaId().getRutaId());
                    getValores().add(ruta.getParametroValor());
                 }  
            }//While()
        }
    }
/*
    private void param_caja(TmsParametrosConfigTbl pcaja){
        System.out.println("Entra a param_cajas");
        if(pcaja.getTmsEmpresaParametrosTblCollection().size()>0)
        {
            Collection<TmsCajaParametrosTbl> collcajas = pcaja.getTmsCajaParametrosTblCollection();
            Iterator ipg = collcajas.iterator();
            while(ipg.hasNext()){
               String valor = "";
                TmsEmpresaParametrosTbl cajas = (TmsEmpresaParametrosTbl)ipg.next();
                 if(cajas.getTurno() ==  null )
                 {
                   getCodigos().add(pcaja.getParametroCodigo());
                   getValores().add(cajas.getParametroValor());
                 }
                 else
                 {
                  if(cajas.getTurno().equals("T") || cajas.getTurno().equals(turno) )
                    {                 
                        if(dia.equals("lunes"))valor = cajas.getLunesValor();
                        if(dia.equals("martes"))valor = cajas.getMartesValor();
                        if(dia.equals("miércoles"))valor = cajas.getMiercolesValor();
                        if(dia.equals("jueves"))valor = cajas.getJuevesValor();
                        if(dia.equals("viernes"))valor = cajas.getViernesValor();
                        if(dia.equals("sábado"))valor = cajas.getSabadoValor();
                        if(dia.equals("domingo"))valor = cajas.getDomingoValor();
                        getCodigos().add(pcaja.getParametroCodigo());
                        getValores().add(valor);
                    }
               }
            }
        } 
    }   
*/
   
    public Vector getCodigos() {
        return codigos;
    }

    public Vector getValores() {
        return valores;
    }
}
