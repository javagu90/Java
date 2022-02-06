/*
 * tmscargaimpresorasPuertas.java
 *
 * Created on 3 de septiembre de 2007, 02:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.util;

import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;
import javax.print.PrintService;
import tmspuertas.TmsPuertasManagedBean;
import tmspuertas.entidad.TmsCajasImpresorasTbl;
import tmspuertas.entidad.TmsCajasTbl;
//import tmspuertas.entidad.TmsEmpresasTbl;
import tmspuertas.entidad.TmsFormatosBoletoTbl;
import tmspuertas.entidad.TmsImpresorasTbl;
import tmspuertas.jdlg_advertencia;
import tmspuertas.jdlg_error;

/**
 *
 * @author vgonzalez
 */
public class tmscargaimpresorasPuertas {
    TmsPuertasManagedBean busquedas = new TmsPuertasManagedBean();
    PcInfo pc = new PcInfo();
    private Vector formatos = new Vector();
    private Vector impresoras = new Vector();
    private String nombre_equipo = "";
    private TmsCajasTbl caja;
    private Vector datosCaja = null;
    private boolean tiene = true;
    private boolean encontro = true;
    private boolean configuarada = true;
    
    private String ubicacion;

    /**
     * Creates a new instance of tmscargaimpresorasPuertas
     */
    public tmscargaimpresorasPuertas() {
        System.out.println("Hostanme= "+pc.getHostName().toString().toUpperCase());
        datosCaja = busquedas.facadeGeneralPuertasRemote.buscarDatosCajaPorEquipo(pc.getHostName().toString().toUpperCase());
        System.out.println("El tamaño del vector es: "+datosCaja.size());
        if(datosCaja.size()==0)
        {
           System.out.println("Entra a cambiar configuaracion");
            configuarada = false;
          //new jdlg_error("¡No esta configurado este equipo como caja! ","Favor de contactar al administrador del sistema","Equipo no configurado").setVisible(true);
         //System.exit(0);
        }
      else{
        Vector dc = (Vector)datosCaja.get(0); // (0) idCaja    (1) ubicacionId      
        System.out.println("la caja es : "+pc.getHostName().toString().toUpperCase() +" Id= "+dc.get(0).toString());
        this.setNombre_equipo(pc.getHostName().toString().toUpperCase());
        this.setUbicacion(dc.get(1).toString());
        Vector dimp = busquedas.facadeGeneralPuertasRemote.buscarDatosImpresoraPorCaja(Long.valueOf(dc.get(0).toString()));
        if(dimp.size()==0){
              tiene = false;
            //new jdlg_error("¡Este equipo no tienen impresoras configuradas! ","Favor de contactar al administrador del sistema","Equipo no configurado").setVisible(true);
             //System.exit(0);
        }
            boolean existeimp = false;
            System.out.println("La coleccion Cajas_Impresoras : "+dimp);//getCaja().getTmsCajasImpresorasTblCollection());
            for(int j=0; j<dimp.size(); j++){
             Vector imp = (Vector) dimp.get(j);//(0)ACTIVIDAD_IMPRESORA, (1)SALIDA_IMPRESION, (2)IMPRESORA_NOMBRE
                PrintService service[] = PrinterJob.lookupPrintServices();
                System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
                PrintService impresoraEncontrada = null;
                for(int i=0; i<service.length;i++)
                {
                    System.out.println("        "+service[i]. getName()+ "  ==> "+imp.get(2).toString().toUpperCase().trim()+" ==> "+imp.get(0).toString());
                    
                    if(service[i].getName().toUpperCase().equals(imp.get(2).toString().toUpperCase().trim()) && imp.get(0).toString().equals("TARJETA"))
                    {
                        existeimp = true;
                        impresoraEncontrada = service[i];
                        break;
                    }
                }
                if(existeimp)
                {
                    System.out.println("La impresora de tarjetas si existe y se llama: "+imp.get(2));
                    getFormatos().add(imp.get(1));  
                    getImpresoras().add(impresoraEncontrada);//impre.getImpresoraNombre());
                }
               //else
                //JOptionPane.showMessageDialog(new JOptionPane(), "¡La impresora "+impre.getImpresoraNombre()+" no esta configurada en esta caja, \n favor de contactar al administrador del sistema!","Impresora no configurada", JOptionPane.ERROR_MESSAGE);   
                //new jdlg_error("¡La impresora "+imp.get(2)+" no esta configurada para tarjetas en esta caja, "," Favor de contactar al Administrador del sistema!","Impresora no configurada").setVisible(true);
            }
        if(!existeimp)
        {
            encontro = false;
            //new jdlg_error("¡Esta caja no tiene impresora configurada para tarjetas o no esta instalada! "," Favor de contactar al Administrador del Sistema!","Error de configuracion de impresoras").setVisible(true);
            //System.exit(0);
        }   

        System.out.println("El vector de Puertos es de   :"+formatos.size()+" ==> "+formatos);
        System.out.println("El vector de impresoras es de :"+impresoras.size()+" ==> "+impresoras);
     }
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

    public boolean isTiene() {
        return tiene;
    }

    public boolean isEncontro() {
        return encontro;
    }

    public boolean isConfiguarada() {
        return configuarada;
    }
    

}
