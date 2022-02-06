/*
 * tmscargaimpresorasrecauda.java
 *
 * Created on 3 de septiembre de 2007, 02:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales;

import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;
import javax.print.PrintService;
import tmsactividadesadicionales.entidad.TmsCajasImpresorasTbl;
import tmsactividadesadicionales.entidad.TmsCajasTbl;
import tmsactividadesadicionales.entidad.TmsEmpresasTbl;
import tmsactividadesadicionales.entidad.TmsFormatosBoletoTbl;
import tmsactividadesadicionales.entidad.TmsImpresorasTbl;
import tmsactividadesadicionales.excepciones.CajaNoEncontradaException;
import tmsactividadesadicionales.excepciones.formatoNoEncontradoException;

/**
 *
 * @author vgonzalez
 */
public class tmscargaimpresorasrecauda {
    TmsActAdicionalesManagedBean busquedas = new TmsActAdicionalesManagedBean();
    PcInfo pc = new PcInfo();
    private Vector formatos = new Vector();
    private Vector impresoras = new Vector();
    private Vector puertos = new Vector();
    private String nombre_equipo = "";
    private TmsCajasTbl caja;
    private String ubicacion;
    private String puerto;

    /**
     * Creates a new instance of tmscargaimpresorasrecauda
     */
    public tmscargaimpresorasrecauda() {
        try {
            caja = busquedas.cajasTblFacadeRemote.buscarCajaPorEquipo(pc.getHostName().toString().toUpperCase());
        } catch(Exception e) {
            new jdlg_error("¡No esta configurada este equipo como caja! ","Favor de contactar al administrador del sistema","Equipo no configurado").setVisible(true);
            System.exit(0);
        }
        System.out.println("la caja es : "+getCaja().getCajaNombre() +" Id= "+getCaja().getCajaId());
        this.setNombre_equipo(getCaja().getNombreEquipo());
        this.setUbicacion(getCaja().getUbicacionId().toString());
        
        if(getCaja().getTmsCajasImpresorasTblCollection().size()>0)
        {
            System.out.println("La coleccion Cajas_Impresoras : "+getCaja().getTmsCajasImpresorasTblCollection());
            Iterator ipg = getCaja().getTmsCajasImpresorasTblCollection().iterator();
            boolean existeimp = false;
            while(ipg.hasNext()){
              TmsCajasImpresorasTbl cajaimp = (TmsCajasImpresorasTbl)ipg.next();
              TmsEmpresasTbl empre   = cajaimp.getEmpresaId();
              TmsImpresorasTbl impre = cajaimp.getImpresoraId();
              String vpuerto = cajaimp.getSalidaImpresion();
              String vActividad = cajaimp.getActividadImpresora();
              //TmsFormatosBoletoTbl formato  = busquedas.formatosBoletoTblFacadeRemote.find(BigDecimal.valueOf(Double.valueOf(""+cajaimp.getFormatoBoletoId())));
              
              //String empformato = ""+empre.getEmpresaId()+formato.getFormatoNombre();
               // PrintService service[] = PrinterJob.lookupPrintServices();
//                System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
//                boolean existeimp = false;
//                PrintService impresoraEncontrada = null;
//                for(int i=0; i<service.length;i++)
//                {
//                    System.out.println("        "+service[i]. getName());
//                    if(service[i].getName().toUpperCase().equals(impre.getImpresoraNombre().toUpperCase()))
//                    {
//                        existeimp = true;
//                        impresoraEncontrada = service[i];
//                        puerto = vpuerto;
//                    }
//                }
//                if(existeimp)
//                {
//                    System.out.println("La impresora si existe y se llama: "+impre.getImpresoraNombre()+" y el puesrto es: "+puerto);
//                    if(cajaimp.getActividadImpresora().equals("BOLETOS"))
//                      getFormatos().add(empformato);
//                    else
//                      getFormatos().add(cajaimp.getActividadImpresora());  
//                    getImpresoras().add(impresoraEncontrada);//impre.getImpresoraNombre());
//                    getPuertos().add(puerto);
//                    
//                }
//               else
//                //JOptionPane.showMessageDialog(new JOptionPane(), "¡La impresora "+impre.getImpresoraNombre()+" no esta configurada en esta caja, \n favor de contactar al administrador del sistema!","Impresora no configurada", JOptionPane.ERROR_MESSAGE);   
//                new jdlg_error("¡La impresora "+impre.getImpresoraNombre()+" no esta configurada en esta caja, "," Favor de contactar al Administrador del sistema!","Impresora no configurada").setVisible(true);

            if(vActividad.equals("TICKETS"))
                existeimp =true;
            if(existeimp)
            {
                System.out.println("La impresora si existe y se llama: "+impre.getImpresoraNombre()+" y el puesrto es: "+puerto);
                  getFormatos().add(cajaimp.getActividadImpresora());  
                  getImpresoras().add(impre.getImpresoraNombre());//impre.getImpresoraNombre());
                  getPuertos().add(vpuerto);

            }
              existeimp =false;
            
         }//while(ipg.hasNext())
        if(getFormatos().size()==0)
            new jdlg_error("¡No hay impresoras de tiickets configuradas! "," Favor de contactar al Administrador del sistema!","Impresora no configurada").setVisible(true);
            
        }
        else
         //JOptionPane.showMessageDialog(null,"¡Esta caja no tiene impresoras configuradas!","Error de configuracion de impresoras",JOptionPane.WARNING_MESSAGE);   
            new jdlg_advertencia("¡Esta caja no tiene impresoras configuradas!","","Error de configuracion de impresoras");   
      System.out.println("El vector de formatos es de   :"+formatos.size()+" ==> "+formatos);
      System.out.println("El vector de impresoras es de :"+impresoras.size()+" ==> "+impresoras);
      System.out.println("El vector de puesrtos es de :"+puertos.size()+" ==> "+puertos);
    }

    public Vector getFormatos() {
        return formatos;
    }

    public Vector getImpresoras() {
        return impresoras;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public TmsCajasTbl getCaja() {
        return caja;
    }

    private void setUbicacion(String ubi) {
        this.ubicacion  = ubi;
    }
    
    private String getUbicacion(String ubi) {
        return this.ubicacion;
    }

    public Vector getPuertos() {
        return puertos;
    }

    public void setPuertos(Vector puertos) {
        this.puertos = puertos;
    }
    
}
