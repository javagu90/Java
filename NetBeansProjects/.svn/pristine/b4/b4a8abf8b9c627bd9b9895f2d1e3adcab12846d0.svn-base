/*
 * tmscargaparamglobvta.java
 *
 * Created on 30 de agosto de 2007, 01:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ofertasservicios;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import xertms.entidad2.TmsGlobalParametrosTbl;
import xertms.entidad2.TmsParametrosConfigTbl;
import xertms.entidad2.TmsServicioParametrosTbl;

/**
 *
 * @author vgonzalez
 */
public class tmscargaparamglobvta {
    private Vector codigos = new Vector();
    private Vector valores = new Vector();
    Vector noaplicables = new Vector();
    String[] arraynoapl = {"P_R_RECLIM","P_R_RECLIM","P_R_PORRET","P_R_PAGPAQ","P_R_PAGCAP","P_R_ANTPEN","P_R_PAGGUA","P_R_MAXTR","P_R_PGOTAR","P_R_PGOPEA","P_R_PGOSLD","P_R_VLRPRTAPR","P_TMPPRITUR","P_TMPSEGTUR","P_TMPTERTUR","P_VLRPRTAPV","P_LIMINTFAL"};
    SesionVenta busquedas;
    SimpleDateFormat ffecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fhora  = new SimpleDateFormat("HH:mm");
    SimpleDateFormat fdia = new SimpleDateFormat ("EEEE");
    String dia = "";
    String turno = "";

    /** Creates a new instance of tmscargaparamglobvta */
    public tmscargaparamglobvta(SesionVenta pBusquedas) {
        this.busquedas=pBusquedas;
        for(int i=0; i<arraynoapl.length -1; i++)
            noaplicables.add(arraynoapl[i]);
        turno = busquedas.getTurno();
        System.out.println("Turno actual: "+turno);
        Date ddia = new Date();
        dia = fdia.format(ddia).toLowerCase();
        System.out.println("Hoy es: "+dia);
        List<TmsParametrosConfigTbl> todosparametros = (List<TmsParametrosConfigTbl>)busquedas.findAllTodosParametros();
        System.out.println("el numero de parametros globales es de: "+todosparametros.size());
        for(int i=0; i<todosparametros.size(); i++)
        {
            if(noaplicables.indexOf(todosparametros.get(i).getParametroCodigo())==-1)
            {
                String tipo_parametro = todosparametros.get(i).getParametroTipo();
                if(tipo_parametro.equals("GLOBAL"))param_globales(todosparametros.get(i));
                if(tipo_parametro.equals("SERVICIO"))param_servicio(todosparametros.get(i));
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
    
    private void param_servicio(TmsParametrosConfigTbl pservicio){
        System.out.println("Entra a param_servicio");
        if(pservicio.getTmsServicioParametrosTblCollection().size()>0)
        {
            Collection<TmsServicioParametrosTbl> collservicio = pservicio.getTmsServicioParametrosTblCollection();
            Iterator ipg = collservicio.iterator();
            while(ipg.hasNext()){
               String valor = "";
               TmsServicioParametrosTbl servicio = (TmsServicioParametrosTbl)ipg.next();
                 if(servicio.getTurno() ==  null )
                 {
                  getCodigos().add(pservicio.getParametroCodigo()+""+servicio.getServicioId());
                  getValores().add(servicio.getParametroValor());
                  }
                 else
                 {
                  if(servicio.getTurno().equals("T") || servicio.getTurno().equals(turno) )
                    {                 
                        if(dia.equals("lunes"))valor = servicio.getLunesValor();
                        if(dia.equals("martes"))valor = servicio.getMartesValor();
                        if(dia.equals("miércoles"))valor = servicio.getMiercolesValor();
                        if(dia.equals("jueves"))valor = servicio.getJuevesValor();
                        if(dia.equals("viernes"))valor = servicio.getViernesValor();
                        if(dia.equals("sábado"))valor = servicio.getSabadoValor();
                        if(dia.equals("domingo"))valor = servicio.getDomingoValor();
                        getCodigos().add(pservicio.getParametroCodigo()+""+servicio.getServicioId());
                        getValores().add(valor);
                    }
               }
            }
        }
    }

    public Vector getCodigos() {
        return codigos;
    }

    public Vector getValores() {
        return valores;
    }
}