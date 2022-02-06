/*
 * JIFPuertas.java
 *
 * Created on 8 de octubre de 2007, 04:37 PM
 */

package tmsconsultaocupacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import tms_ocupacion.entidad.TmsAutobusPlantLineasTbl;
import tms_ocupacion.entidad.TmsAutobusPlantillasEncTbl;
import tms_ocupacion.entidad.TmsBaseDatosConfigTbl;
import tms_ocupacion.entidad.TmsComponenteBusTbl;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;
import tms_ocupacion.entidad.TmsEmpresasTbl;
import tms_ocupacion.entidad.TmsServiciosTbl;

/**
 *
 * @author  vgonzalez
 */
public class JIFConsultaOcupacion extends javax.swing.JInternalFrame {//javax.swing.JFrame {//     
    JFileChooser jFileChooser = new JFileChooser();
    long time;
    /** Creates new form JIFPuertas */
    public JIFConsultaOcupacion(Vector pDatosIniciales) {
        this.datosIniciales = pDatosIniciales;
        System.out.println("Vector de Datos iniciales es: "+datosIniciales);
        this.setUsuarioId(datosIniciales.get(0).toString());
        this.setSesionId(datosIniciales.get(3).toString());
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Plantillas();
        initComponents();
        this.setTitle("Consulta de Ocupacion  Rev30.10.12");
        jcmb_servicio.removeAllItems();
        jcmbx_origen.removeAllItems();
        jcmb_destino.removeAllItems();
        jcmb_empresa.removeAllItems();
        llenarCombosBox();
        JDialog di = new JDialog();
        di.setDefaultLookAndFeelDecorated(true);
        di.dispose();
        listaServicios   = (List<TmsServiciosTbl>)busquedas.serviciosTblFacadeRemote.findAll();
        //jcmb_servicio.addItem("Todos");
        jtbl_corridas.setModel(modeloCorridas);
        for(int i=0; i<listaServicios.size(); i++)
            jcmb_servicio.addItem(listaServicios.get(i).getServicioNombre());
        jtbl_corridas.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none");
        jtbl_corridas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtbl_corridas.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        jtbl_corridas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        jtbl_corridas.setRowHeight(25);  
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jcmb_servicio.setFocusTraversalKeysEnabled(false);
        /*JfileChooser*/
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.addChoosableFileFilter(new FileExtensions("Txt","txt"));
        jFileChooser.addChoosableFileFilter(new FileExtensions("MS Excel","xls"));
        jFileChooser.setAcceptAllFileFilterUsed(false);       
        /*Fin JfileChooser*/
        cargaDatosIniciales();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        //this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
          //Runnable nuevoHiloConsultas = new actualizaCorridasThread(); 
          //hilo = new Thread(nuevoHiloConsultas);
          //hilo.start();  
        //jcmbx_terminales.setVisible(false);
        //jlbl_terminal.setVisible(false);
      //jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Termina | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000></font> Campo Siguiente | <font color=FF0000>ARRIBA</font> Campo Anterior  | <font color=FF0000>F11</font> Reimprimir Tarjeta </html>");        
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
        //buscarCorridas("%","%","%");
         ff = new SimpleDateFormat("dd/MM/yyyy");
         fh = new SimpleDateFormat("HH:mm");
        Vector x=  (Vector) busquedas.variosFacadeRemote.fechaServidor();  
        //fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
        if(x != null) {
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
            time = fecha_servidor.getTime();
        }
        else {
            Calendar cal = Calendar.getInstance();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH24:MM:SS");
            //fecha_servidor = fecha_servidor.valueOf(sdf.format(cal.getTime()).toString());
            time = cal.getTimeInMillis();
        }
        
        //jtxt_hora_desde.setText(fh.format((fecha_servidor.getTime())));
        //jtxt_fecha.setText(ff.format(fecha_servidor.getTime()));
        jtxt_fecha.setText(ff.format(time));
        //jlbl_reloj2 = new RelojVisual(new RelojModeloSwing(fecha_servidor.getTime()));
        //jLabel9.setVisible(false);
        //jtxt_fecha_hasta.setVisible(false);
        jlbl_reloj2 = new RelojVisual(new RelojModeloSwing(time));
        jcmb_empresa.setSelectedItem("Estrella Roja");
         claveActual    = "%";
         autobusActual  = "%";
         servicioActual = "%";
    }

     public void Guardar(File nombre) throws FileNotFoundException {
        String ext = "";
        if(jFileChooser.getFileFilter().getDescription().equals("MS Excel(*.xls)")){
            ext = ".xls";
        }else if (jFileChooser.getFileFilter().getDescription().equals("Txt(*.txt)")){
            ext = ".txt";
        }
        String contenidoArchivoExcel = "";
        for(int i=1; i<encTabla.size(); i++)
           contenidoArchivoExcel = contenidoArchivoExcel + encTabla.get(i).toString()+"\t";
        contenidoArchivoExcel = contenidoArchivoExcel +"\n";
        for(int j=0; j<jtbl_corridas.getRowCount();j++)
        {
            for(int k=1; k<jtbl_corridas.getColumnCount();k++)
            {
                if(jtbl_corridas.getValueAt(j,k)==null)
                  contenidoArchivoExcel =  contenidoArchivoExcel + " \t";
                else    
                  contenidoArchivoExcel =  contenidoArchivoExcel + jtbl_corridas.getValueAt(j,k).toString()+"\t";
            }
            contenidoArchivoExcel = contenidoArchivoExcel +"\n";
           jProgressBar1.setValue(j);
           jProgressBar1.setStringPainted(true);
           jProgressBar1.paint(jProgressBar1.getGraphics());
        }

        FileOutputStream guardo = new FileOutputStream(nombre+ext);
        PrintStream ps = new PrintStream(guardo);
        ps.print(contenidoArchivoExcel);
        ps.flush();
        ps.close();
        Abrir(nombre, ext);
    }
     
     public void Abrir(File nombre, String ext) {
        try{
            int result = JOptionPane.showOptionDialog(this, "¿Desea abrir el archivo "+nombre.getName()+"?",
                            "TMS-Lista de Pasajeros", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (result == JOptionPane.YES_OPTION){
                
                Process p = null;
                if(ext.equals(".xls"))
                    p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+nombre.getAbsolutePath()+".xls"); 
                else
                    p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+nombre.getAbsolutePath()+".txt"); 
            }
                
        }catch(Exception exc) {
            System.out.println(exc);
        }
    }
    
      private void resizeColumnasCorridas(){
        TableColumn columinv = jtbl_corridas.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        columinv = jtbl_corridas.getColumnModel().getColumn(1); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        int  numCol = encTabla.size();
        for(int i=2; i<numCol; i++)
            columinv = jtbl_corridas.getColumnModel().getColumn(i);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
      }    
    
    public void cargaDatosIniciales(){
       ltiposPasajero = (Vector)busquedas.variosFacadeRemote.buscarTiposPasajero();
        if(ltiposPasajero.size()==0)
            new jdlg_error("¡No estan configurados los tipos de pasajeros en la base de datos! "," Favor de contactar al administrador del sistema","Tipos dePasajeros mal configurados").setVisible(true);
        else
        {
          for(int i=0; i<ltiposPasajero.size(); i++)
          {
             Vector tipo = (Vector)ltiposPasajero.get(i);
             tiposPasajero.add(tipo.get(0).toString());
             letraTiposPasajero.add(tipo.get(1).toString());
          }
        }
       System.out.println("Tipos Pasajero : "+tiposPasajero);
       System.out.println("Letras Pasajero: "+letraTiposPasajero);
       
       Vector vparamServicios = (Vector)busquedas.variosFacadeRemote.buscaParametrosPorServicio();       
        if(vparamServicios.size()>0)
        {
            for(int i=0; i<vparamServicios.size(); i++)
            {
                Vector ps = (Vector)vparamServicios.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
       Vector vparamRutas = (Vector)busquedas.variosFacadeRemote.buscaParametrosPorRuta();       
        if(vparamRutas.size()>0)
        {
            for(int i=0; i<vparamRutas.size(); i++)
            {
                Vector ps = (Vector)vparamRutas.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
        System.out.println("Parametros: "+codigos);
        System.out.println("Valores:    "+valores);
        Vector vEmpresas = (Vector) busquedas.variosFacadeRemote.parametrosEmpresas();
        for(int i=0; i<vEmpresas.size(); i++)
        {
            Vector emp = (Vector) vEmpresas.get(i);
            empresas.add(emp.get(0).toString());
            String[] paem = new String[3];
            paem[0] =   emp.get(1).toString();
            paem[1] =   emp.get(2).toString();
            paramEmpresas.add(paem);
        } 
        
       String rper = busquedas.variosFacadeRemote.buscaPermisoIngreso(datosIniciales.get(1).toString(),"6022");//22");
       if(rper.equals("si"))
           permiso = true;
       else
           permiso = false;
       //System.out.println("manda a buscaRutasPerfil...");
       //rutas = busquedas.variosFacadeRemote.buscaRutasPerfil(this.usuarioId);
       VectorRutas = busquedas.variosFacadeRemote.buscaRutasPerfil(this.usuarioId);
       //if(rutas.equals("-1") || empresas_ruta.equals("-1"))
       if(VectorRutas.size()==0)
            new jdlg_error("¡El usuario no tiene configurada ninguna ruta en su perfil!","","Configuracion de usuario").setVisible(true);
       else
       {
           for(int i=0; i<VectorRutas.size();i++)
           {
             Vector v= (Vector)VectorRutas.get(i);
             boolean existe = false;
               for(int j=0; j<VectorEmpresas.size();j++)
                   if(VectorEmpresas.get(j).toString().equals(v.get(2).toString()))
                    existe = true;
              if(!existe)
               VectorEmpresas.add(v.get(2));
           }

        }
    }

    private String getRutas(){
        Vector completo = new Vector();
        Vector rut = new Vector();
        for(int i=0; i<VectorRutas.size();i++)
        {
            Vector v = (Vector)VectorRutas.get(i);
            boolean existe = false;
            for(int j=0; j<completo.size();j++)
            {
                Vector vc=(Vector)completo.get(j);
                if(v.get(2).toString().equals(vc.get(0).toString()))
                {
                    ((Vector) vc.get(1)).add(v.get(0).toString());
                    existe = true;
                    break;
                }
            }
            if(!existe)
            {
                Vector nv = new Vector();
                Vector nvr = new Vector();
                nvr.add(v.get(0).toString());
                nv.add(v.get(2).toString());
                nv.add(nvr);
                completo.add(nv);
            }

        }
        //System.out.println("Vector Rutas "+rutas);
        String rutas2 = "";

        for(int k=0; k<completo.size();k++)
        {
            String r="";
            Vector vc=(Vector)completo.get(k);
            Vector vr=(Vector)vc.get(1);
            for(int m=0; m<vr.size();m++)
            {
                if (m==0)
                    r = r +vr.get(m).toString();
                else
                    r = r + ","+vr.get(m).toString();
            }
            if(jcmb_empresa.getSelectedItem().toString().equals("Todas"))
            {
                if(k==0)
                    rutas2="(con.empresa = '"+vc.get(0).toString()+"' and  con.ruta_id in ("+r+")) \n";
                else
                    rutas2=rutas2+" or (con.empresa = '"+vc.get(0).toString()+"' and  con.ruta_id in ("+r+")) \n";
            }
            else
            {
                if(jcmb_empresa.getSelectedItem().toString().equals(vc.get(0).toString()))
                    rutas2="con.empresa = '"+vc.get(0).toString()+"' and  con.ruta_id in ("+r+")";
            }
        }
        System.out.println("Rutas2 "+rutas2);

        return rutas2;
    }

    private void buscarCorridas(){//String clavOp, String bus, String servic){
        String clavOp = "%";
        String bus = "%";
        String servic = "%";
        String empresa = "%";
        String origen = "%";
        String destino = "%";
        String horaDesde= "00:00";
        String ffinal = "";
        String finicial = "";
         if(jtxt_fecha.getText().length()<10)
          {
            new jdlg_error("¡Debes introducir una fecha valida!","","Error en formato de fecha").setVisible(true);
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
            return;
          }
        finicial = jtxt_fecha.getText();
        
        if(jtxt_fecha_hasta.getText().equals(""))
          ffinal =  jtxt_fecha.getText();
        else
        {
         if(jtxt_fecha_hasta.getText().length()<10)
          { 
            new jdlg_error("¡Debes introducir una fecha valida!","","Error en formato de fecha").setVisible(true);
            jtxt_fecha_hasta.selectAll();
            jtxt_fecha_hasta.requestFocus();
            return;
          }
         else
             ffinal = jtxt_fecha_hasta.getText();
        }
        Timestamp fecha_servidor_hoy =null;
        String fhoy = "";
        String fayer = "";
        long timeHoy =0;
                    Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor2();
                     System.out.println("fechaServidor2(): "+x);
                    if(x != null)
                    {
                      String[] array = x.get(0).toString().split(",");
                      fecha_servidor_hoy = fecha_servidor_hoy.valueOf(array[1]+" 00:00:00");
                      timeHoy = fecha_servidor_hoy.getTime();
                      fayer = array[0];
                      fhoy = ff.format(fecha_servidor_hoy);
                    }
        boolean combinado = false;
        boolean soloCentral = false;
        long fhi = fechaMilisegundos(finicial);
        long fhf = fechaMilisegundos(ffinal);
        if(fhi>fhf)
          {
            new jdlg_error("¡la Fecha desde debe ser menor a la Fecha hasta!","","Error en formato de fechas").setVisible(true);
            return;
          }
        System.out.println("fhoy: "+fhoy);
        System.out.println("fayer: "+fayer);
        System.out.println("fecha Hoy: "+timeHoy);
        System.out.println("fhi: "+fhi);
        System.out.println("fhf: "+fhf);
        //System.out.println("fhf+2: "+(fhi+172800000));
        System.out.println("fhf+7: "+(fhi+604800000));

        if(timeHoy>fhi && timeHoy>fhf)
            soloCentral = true;
        else
        {
            if(fhi<timeHoy)
                combinado = true;
        }
        if((fhi+604800000)<fhf && !soloCentral)
          {
            new jdlg_error("¡El rango de las fechas no pueder ser mayor a siete dias!","","Error en formato de fechas").setVisible(true);
            return;
          }
             
        

        if(!jtxt_hora_desde.getText().equals(""))
        {
             if(jtxt_hora_desde.getText().length()<5)
              {
                new jdlg_error("¡Debes introducir horas validas!","","Error en formato de hora").setVisible(true);
                return;
              }
             else
               horaDesde = jtxt_hora_desde.getText();
        }

        if(!jcmb_servicio.getSelectedItem().toString().equals("Todos"))
            servic = jcmb_servicio.getSelectedItem().toString();
        
        if(!jcmb_empresa.getSelectedItem().toString().equals("Todas"))
            empresa = jcmb_empresa.getSelectedItem().toString();
        
         //if(!jcmbx_origen.getSelectedItem().toString().equals("Todos"))
         TmsBaseDatosConfigTbl bds = lisEsquemasTerminales.get(jcmbx_origen.getSelectedIndex());
         System.out.println("La terminal seleccionada es: "+bds.getNombreTerminal());
         System.out.println("La terminal con esquema: "+bds.getEsquemaPropio());
        if(bds.getEsquemaPropio().equals("S"))
            esquema = "local";
        else
            esquema = bds.getNombreDblink();
        if(!jcmbx_origen.getSelectedItem().toString().equals("Todos"))
         origen = jcmbx_origen.getSelectedItem().toString();      
        
         if(!jcmb_destino.getSelectedItem().toString().equals("Todos"))
            destino = jcmb_destino.getSelectedItem().toString();      

        
        if(!jtxt_clave.getText().equals(""))
            clavOp = jtxt_clave.getText();
        
        if(!jtxt_autobus.getText().equals(""))
            bus = jtxt_autobus.getText();
        
        if(servic.equals("%") && empresa.equals("%") && origen.equals("%") && destino.equals("%") && clavOp.equals("%") && bus.equals("%"))
        {
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Consulta sin filtros", "La consulta sin filtros podria tomar varios minutos. ¿Desea continuar?");
          psn.setVisible(true);
          if(!respuestaSN)
              return;
        }
        String tabla="TMS_MONITOR_CORRIDAS_A_V";
        if(permiso)
            tabla = "TMS_MONITOR_CORRIDAS_B_V"; 
        String tablaEnc = tabla;
        String tablaCentral = tabla;
        if(!esquema.equals("local"))
            tabla = tabla+"@"+esquema;
        encTabla = new Vector();
        Vector vencTabla = new Vector();
               vencTabla = (Vector) busquedas.variosFacadeRemote.buscaEncabezados(tablaEnc);
//         System.out.println("Me regresa los encabezados: "+vencTabla);   
        for(int i=0; i<vencTabla.size(); i++)
        {
            Vector paso = (Vector) vencTabla.get(i);
            encTabla.add(paso.get(0).toString());
        }
         encTabla.add("CONNECION");
        boolean empresaPermitida = false;
        for(int y =0; y<VectorEmpresas.size(); y++)
            if(VectorEmpresas.get(y).toString().equals(jcmb_empresa.getSelectedItem().toString()))
                empresaPermitida = true;
        if(!empresaPermitida)
        {
            new jdlg_advertencia("¡No hay corridas que coincidan con los criterios de búsqueda!  ","","No se encontraron corridas").setVisible(true);
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
            return;
        }


        //System.out.println("Encabezados: "+encTabla);d
//        datosTabla = (Vector)busquedas.variosFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,finicial,ffinal, horaDesde, tabla, rutas, jchbx_extras.isSelected());
        System.out.println("SoloCentral: "+soloCentral);
        System.out.println("combinado: "+combinado);

        //datosTabla = (Vector)busquedas.variosFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,finicial,ffinal, horaDesde, tabla, getRutas(), jchbx_extras.isSelected());
        if(soloCentral)
            datosTabla = (Vector)busquedas.centralFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,finicial,ffinal, horaDesde, tablaCentral, getRutas(), jchbx_extras.isSelected());
        //System.out.println("Datos: "+datosTabla);
        else
        {
            if(combinado)
            {
                //central de fechas anteriores
                 datosTabla = (Vector)busquedas.centralFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,finicial,fayer, horaDesde, tablaCentral, getRutas(), jchbx_extras.isSelected());
                 Vector datosTablaLocal = new Vector();
                 //local de fechas actuales
                 datosTablaLocal = (Vector)busquedas.variosFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,fhoy,ffinal, horaDesde, tabla, getRutas(), jchbx_extras.isSelected());
                 datosTabla.addAll(datosTablaLocal);
            }
            else
                datosTabla = (Vector)busquedas.variosFacadeRemote.buscarDatos(bus,clavOp,servic,empresa,origen,destino,finicial,ffinal, horaDesde, tabla, getRutas(), jchbx_extras.isSelected());
        }


        if(datosTabla.size()==0)
        {
            new jdlg_advertencia("¡No hay corridas que coincidan con los criterios de búsqueda!  ","","No se encontraron corridas").setVisible(true);
//            jcmb_servicio.setSelectedIndex(0);
//            jcmb_empresa.setSelectedIndex(0);
//            jcmb_destino.setSelectedIndex(0);  
//            jcmbx_origen.setSelectedIndex(0);  
//            jtxt_fecha.setText("");
//            jtxt_fecha_hasta.setText("");
//            jtxt_autobus.setText("");
//            jtxt_clave.setText("");
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
            return;
        }
        else
        {
                modeloCorridas.setDataVector(datosTabla,encTabla);
                //resizeColumnasCorridas();
               jtxt_fecha.setEnabled(false);
               jtxt_fecha_hasta.setEnabled(false);
               jtxt_hora_desde.setEnabled(false);
               jtxt_autobus.setEnabled(false);
               jtxt_clave.setEnabled(false);
               jcmb_servicio.setEnabled(false);
               jcmb_empresa.setEnabled(false);
               jcmbx_origen.setEnabled(false);
               jcmb_destino.setEnabled(false);
               jtbl_corridas.setEnabled(true);
               jtbl_corridas.setRowSelectionInterval(0,0);
               jtbl_corridas.setColumnSelectionInterval(0,0);
               jtbl_corridas.requestFocus();
               if(soloCentral || combinado)
                corridaSelececcionada = busquedas.centralFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));
               else
               {
                   if(esquema.equals("local"))
                        corridaSelececcionada = busquedas.corridasVentaTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));//lcorridas.get(jtbl_corridas.getSelectedRow());
                   else
                   {
                       TmsCorridasVentaTbl corRemota = new TmsCorridasVentaTbl();
                       Vector vec = (Vector)busquedas.corridasVentaTblFacadeRemote.findRemota(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString()), esquema);
                       if(vec.size()==0)
                       {
                           new jdlg_error("La corrida no se encontro en la base de datos","","Error de datos").setVisible(true);
                           jtbl_corridas.requestFocus();
                           return;
                       }
                       else
                       {
                           Vector v = (Vector)vec.get(0);
                           corRemota.setValoresIniciales(v);
                           corridaSelececcionada = corRemota;
                       }
                   }
                }
                plantillaId = corridaSelececcionada.getPlantillaId().longValue();
                ConsultaOcupacion();
               jtbl_corridas.requestFocus();

        }
//        try {
//          Thread.sleep (2000);
//         } catch (Exception e) { e.printStackTrace(); }
//         final JTable fTable = jtbl_corridas;
//         SwingUtilities.invokeLater(new Runnable() {
//         public void run() { 
//                     ColumnResizer.adjustColumnPreferredWidths (fTable);                    
//                     fTable.revalidate();
//         } 
//          });

                                  // strategy - get max width for cells in column and
                                   // make that the preferred width
           TableColumnModel columnModel = jtbl_corridas.getColumnModel();
           for (int col=0; col<jtbl_corridas.getColumnCount(); col++) 
           {
                int maxwidth = 0;            
                for (int row=0; row<jtbl_corridas.getRowCount(); row++) {
                TableCellRenderer rend  = jtbl_corridas.getCellRenderer(row, col); 
                           Object value = jtbl_corridas.getValueAt (row, col); 
                            Component comp = rend.getTableCellRendererComponent (jtbl_corridas, value, false, false, row, col);
                           maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
                } // for row
                TableColumn column = columnModel.getColumn (col);
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if (headerRenderer == null)
                            headerRenderer = jtbl_corridas.getTableHeader().getDefaultRenderer();
                Object headerValue = column.getHeaderValue();
                Component headerComp = headerRenderer.getTableCellRendererComponent (jtbl_corridas, headerValue, false, false, 0, col);
                maxwidth = Math.max (maxwidth, headerComp.getPreferredSize().width);
                column.setPreferredWidth (maxwidth + 15);
           } // for col         
        TableColumn columinv = jtbl_corridas.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
         columinv = jtbl_corridas.getColumnModel().getColumn(1); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
         columinv = jtbl_corridas.getColumnModel().getColumn((jtbl_corridas.getColumnCount()-1) ); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
         
    }
    
    
    
    private void salirAplicacion(){
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Salir de Consulta de Ocupacion", "¿Seguro que desea salir de la Aplicacion de Consultas?");
          psn.setVisible(true);
          if(respuestaSN)
          {
              //hilo.interrupt();
              this.dispose();
          }

    }


    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_clave = new tms_TextFields.JTextTextField();
        jLabel3 = new javax.swing.JLabel();
        jcmb_servicio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxt_hora_desde = new tms_TextFields.JHourTextField();
        jlbl_terminal = new javax.swing.JLabel();
        jcmbx_origen = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jtxt_fecha = new tms_TextFields.JDateTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcmb_empresa = new javax.swing.JComboBox();
        jcmb_destino = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jtxt_fecha_hasta = new tms_TextFields.JDateTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxt_autobus = new tms_TextFields.JTextTextField();
        jchbx_extras = new javax.swing.JCheckBox();
        JPnlAutobus = new javax.swing.JPanel();
        JPnlbus = new JPnl_BusX(this.getComponentes(), this.getEncabezado(this.getPLANTILLA_DEFAULT()), this.getLineas(this.getPLANTILLA_DEFAULT()));
        JPnlbus.setVisibleOcupado(false);
        JPnlbus.setVisibleReservado(true);
        JPnlbus.setVisibleReservadoNC(true);
        JPnlAutobus.add(JPnlbus,
            new GridBagConstraints(0, 0, 0, 0, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        JPnlAutobus.setBackground(java.awt.Color.white);
        JPnlAutobus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JPnlAutobus.setFocusable(false);
        jlbl_barraEstado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_corridas = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        jlbl_Reloj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Consulta Ocupacion");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Operador:");

        jtxt_clave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtxt_clave.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_clave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_claveFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_claveFocusLost(evt);
            }
        });
        jtxt_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_claveKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Servicio:");

        jcmb_servicio.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_servicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PULLMAN PRIMERA CLASE" }));
        jcmb_servicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_servicioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmb_servicioFocusLost(evt);
            }
        });
        jcmb_servicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_servicioKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Hora Desde:");

        jtxt_hora_desde.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_hora_desde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_hora_desdeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_hora_desdeFocusLost(evt);
            }
        });
        jtxt_hora_desde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_hora_desdeKeyPressed(evt);
            }
        });

        jlbl_terminal.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_terminal.setText("Origen:");

        jcmbx_origen.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmbx_origen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbx_origen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmbx_origenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbx_origenFocusLost(evt);
            }
        });
        jcmbx_origen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbx_origenKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Fecha desde:");

        jtxt_fecha.setFont(new java.awt.Font("Tahoma", 1, 10));
        jtxt_fecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_fechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_fechaFocusLost(evt);
            }
        });
        jtxt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechaKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Destino:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Empresa:");

        jcmb_empresa.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_empresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autobuses de Oriente" }));
        jcmb_empresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_empresaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmb_empresaFocusLost(evt);
            }
        });
        jcmb_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_empresaKeyPressed(evt);
            }
        });

        jcmb_destino.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_destino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmb_destino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_destinoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmb_destinoFocusLost(evt);
            }
        });
        jcmb_destino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_destinoKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("hasta:");

        jtxt_fecha_hasta.setFont(new java.awt.Font("Tahoma", 1, 10));
        jtxt_fecha_hasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_fecha_hastaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_fecha_hastaFocusLost(evt);
            }
        });
        jtxt_fecha_hasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fecha_hastaKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Autobus:");

        jtxt_autobus.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_autobus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_autobusFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_autobusFocusLost(evt);
            }
        });
        jtxt_autobus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_autobusKeyPressed(evt);
            }
        });

        jchbx_extras.setFont(new java.awt.Font("Tahoma", 1, 12));
        jchbx_extras.setText("Solo corridas Extras");
        jchbx_extras.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jchbx_extras.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jchbx_extras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchbx_extrasMouseClicked(evt);
            }
        });
        jchbx_extras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jchbx_extrasFocusGained(evt);
            }
        });
        jchbx_extras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jchbx_extrasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jchbx_extrasKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtxt_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_fecha_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jlbl_terminal)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jcmbx_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(39, 39, 39)
                        .add(jLabel3)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jcmb_destino, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 189, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(31, 31, 31)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jcmb_empresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jchbx_extras))
                .add(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE, false)
                            .add(jLabel6)
                            .add(jtxt_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel9)
                            .add(jtxt_fecha_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel4)
                            .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3)
                            .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(15, 15, 15)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2)
                            .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jcmbx_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_terminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7)
                            .add(jcmb_destino, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE, false)
                            .add(jLabel8)
                            .add(jcmb_empresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(15, 15, 15)
                        .add(jchbx_extras)))
                .addContainerGap())
        );

        JPnlAutobus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ocupacion de la corrida", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        JPnlAutobus.setMaximumSize(new java.awt.Dimension(185, 431));
        JPnlAutobus.setPreferredSize(new java.awt.Dimension(185, 431));

        org.jdesktop.layout.GroupLayout JPnlAutobusLayout = new org.jdesktop.layout.GroupLayout(JPnlAutobus);
        JPnlAutobus.setLayout(JPnlAutobusLayout);
        JPnlAutobusLayout.setHorizontalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 236, Short.MAX_VALUE)
        );
        JPnlAutobusLayout.setVerticalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 525, Short.MAX_VALUE)
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jlbl_barraEstado.setText("jLabel4");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corridas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        jtbl_corridas.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtbl_corridas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CAPUI1720N20046996", "DIRECTO ECONOMICO", "CAPU", "TAPO", "0341", "3286", "04/10/2007", "15:20"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Clave Corrida", "Servicio", "Origen", "Destino", "Operador", "Autobus", "Fecha", "Hora"
            }
        ));
        jtbl_corridas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_corridasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtbl_corridasFocusLost(evt);
            }
        });
        jtbl_corridas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_corridasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_corridasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtbl_corridas);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        jlbl_Reloj.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_Reloj.setText("10/12/2008 13:00");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 248, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 907, Short.MAX_VALUE)
                        .add(jlbl_Reloj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jlbl_Reloj)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jchbx_extrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchbx_extrasMouseClicked
          if(jchbx_extras.isSelected())
              jchbx_extras.setSelected(false);
          else
             jchbx_extras.setSelected(true);   
    }//GEN-LAST:event_jchbx_extrasMouseClicked

    private void jchbx_extrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jchbx_extrasKeyReleased
      if(evt.getKeyCode() == evt.VK_SPACE)
      {
          if(jchbx_extras.isSelected())
              jchbx_extras.setSelected(false);
          else
             jchbx_extras.setSelected(true);     
      }
    }//GEN-LAST:event_jchbx_extrasKeyReleased

    private void jchbx_extrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jchbx_extrasKeyPressed
     if(evt.getKeyCode() == evt.VK_SPACE)
        return;
     if(evt.getKeyCode() == evt.VK_F2)
     {
         if(jchbx_extras.isSelected())
             jchbx_extras.setSelected(false);
         else
             jchbx_extras.setSelected(true);
     }   
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
            jcmb_destino.requestFocus();

        if(evt.getKeyCode() == evt.VK_ENTER)
             buscarCorridas();               
     
     
    }//GEN-LAST:event_jchbx_extrasKeyPressed

    private void jchbx_extrasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jchbx_extrasFocusGained
    jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F2</font> Seleccionar/Deseleccionar | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");     
    }//GEN-LAST:event_jchbx_extrasFocusGained

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyPressed
// TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1KeyPressed

    private void jcmb_destinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_destinoFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
    }//GEN-LAST:event_jcmb_destinoFocusLost

    private void jcmb_destinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_destinoFocusGained
    jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Destinos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");     
    }//GEN-LAST:event_jcmb_destinoFocusGained

    private void jcmbx_origenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbx_origenFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
    }//GEN-LAST:event_jcmbx_origenFocusLost

    private void jcmbx_origenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbx_origenFocusGained
    jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Origenes | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");     
    }//GEN-LAST:event_jcmbx_origenFocusGained

    private void jcmb_empresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_empresaFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
    }//GEN-LAST:event_jcmb_empresaFocusLost

    private void jcmb_empresaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_empresaFocusGained
    jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Empresas | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");     
    }//GEN-LAST:event_jcmb_empresaFocusGained

    private void jcmb_destinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_destinoKeyPressed

        if(evt.getKeyCode() == evt.VK_RIGHT)
            jchbx_extras.requestFocus();
        
        if(evt.getKeyCode() == evt.VK_LEFT)
            jcmbx_origen.requestFocus();

        if(evt.getKeyCode() == evt.VK_ENTER)
             buscarCorridas();               

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();               
    }//GEN-LAST:event_jcmb_destinoKeyPressed

    private void jcmb_empresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_empresaKeyPressed
        if(evt.getKeyCode() == evt.VK_LEFT)
            jcmb_servicio.requestFocus();
        
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
           jtxt_clave.requestFocus();
           jtxt_clave.selectAll();
         }

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();  

        if(evt.getKeyCode() == evt.VK_ENTER)
              buscarCorridas(); 

    }//GEN-LAST:event_jcmb_empresaKeyPressed

    private void jtxt_fechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechaFocusLost
        //jtxt_fecha.setBackground(Color.WHITE);
        jtxt_fecha.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_fechaFocusLost

    private void jtxt_fechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechaFocusGained
        jtxt_fecha.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_fechaFocusGained

    private void jtxt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fechaKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
              jtxt_fecha_hasta.requestFocus();
              jtxt_fecha_hasta.selectAll();
              //jtxt_hora_desde.requestFocus();
              //jtxt_hora_desde.selectAll();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
            jchbx_extras.requestFocus();

        if(evt.getKeyCode() == evt.VK_ENTER)
             buscarCorridas();
        
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();          
    
        
    }//GEN-LAST:event_jtxt_fechaKeyPressed

    private void jcmbx_origenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbx_origenKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT)
              jcmb_destino.requestFocus();
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtxt_autobus.requestFocus();
            jtxt_autobus.selectAll();
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
             buscarCorridas();

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();          
    }//GEN-LAST:event_jcmbx_origenKeyPressed

    private void jtxt_hora_desdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_hora_desdeFocusGained
        //jtxt_hora_desde.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 88, 236)));
        jtxt_hora_desde.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_hora_desdeFocusGained

    private void jtxt_hora_desdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_hora_desdeFocusLost
        //jtxt_hora_desde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jtxt_hora_desde.setBackground(Color.WHITE);

    }//GEN-LAST:event_jtxt_hora_desdeFocusLost

    private void jtxt_autobusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_autobusFocusLost
     jtxt_autobus.setBackground(Color.WHITE);
    }//GEN-LAST:event_jtxt_autobusFocusLost

    private void jtxt_claveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusLost
         jtxt_clave.setBackground(Color.WHITE);
    }//GEN-LAST:event_jtxt_claveFocusLost

    private void jtxt_hora_desdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_hora_desdeKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT)
             jcmb_servicio.requestFocus();   
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtxt_fecha_hasta.requestFocus();
            jtxt_fecha_hasta.selectAll();
            //jtxt_fecha.requestFocus();
            //jtxt_fecha.selectAll();
        }
        if(evt.getKeyCode() == evt.VK_ENTER)
            buscarCorridas();

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();               
        
    }//GEN-LAST:event_jtxt_hora_desdeKeyPressed

    private void jtxt_autobusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_autobusKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT)
            jcmbx_origen.requestFocus();
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtxt_clave.selectAll();
            jtxt_clave.requestFocus();
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
             buscarCorridas();               

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();               
    }//GEN-LAST:event_jtxt_autobusKeyPressed

    private void jtxt_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_claveKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
            jtxt_autobus.selectAll();
            jtxt_autobus.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
            jcmb_empresa.requestFocus();

        if(evt.getKeyCode() == evt.VK_ENTER)
              buscarCorridas(); 

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();           
    }//GEN-LAST:event_jtxt_claveKeyPressed

    private void jtxt_autobusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_autobusFocusGained
    jtxt_autobus.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_autobusFocusGained

    private void jtxt_claveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusGained
        jtxt_clave.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_claveFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        jtxt_fecha.selectAll();
        jtxt_fecha.requestFocus();
    }
    
    private void jtbl_corridasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_corridasKeyReleased
  

        if(evt.getKeyCode() == evt.VK_UP)
        {
            if(jtbl_corridas.getSelectedRow()>=0)
            {//centralFacadeRemote
                  if(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),(jtbl_corridas.getColumnCount()-1) ).toString().equals("CENTRAL"))
                     corridaSelececcionada = busquedas.centralFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));
                  else
                  {
    //                corridaSelececcionada = busquedas.corridasVentaTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));//lcorridas.get(jtbl_corridas.getSelectedRow());
                       if(esquema.equals("local"))
                            corridaSelececcionada = busquedas.corridasVentaTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));//lcorridas.get(jtbl_corridas.getSelectedRow());
                       else
                       {
                           TmsCorridasVentaTbl corRemota = new TmsCorridasVentaTbl();
                           Vector vec = (Vector)busquedas.corridasVentaTblFacadeRemote.findRemota(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString()), esquema);
                           if(vec.size()==0)
                           {
                               new jdlg_error("La corrida no se encontro en la base de datos","","Error de datos").setVisible(true);
                               jtbl_corridas.requestFocus();
                               return;
                           }
                           else
                           {
                               Vector v = (Vector)vec.get(0);
                               corRemota.setValoresIniciales(v);
                               corridaSelececcionada = corRemota;
                           }
                       }
                    }
                plantillaId = corridaSelececcionada.getPlantillaId().longValue();
                ConsultaOcupacion();
                //verificarCambiarOcupacion();
            } 
        }

        if(evt.getKeyCode() == evt.VK_DOWN)
        {
            if(jtbl_corridas.getSelectedRow()<=(jtbl_corridas.getRowCount()-1))
            {
//                TmsCorridasVentaTbl corrida = lcorridas.get(jtbl_corridas.getSelectedRow());
//                corridaSelececcionada = busquedas.corridasVentaTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));//lcorridas.get(jtbl_corridas.getSelectedRow());

                  if(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),(jtbl_corridas.getColumnCount()-1) ).toString().equals("CENTRAL"))
                     corridaSelececcionada = busquedas.centralFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));
                  else
                  {
                       if(esquema.equals("local"))
                            corridaSelececcionada = busquedas.corridasVentaTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));//lcorridas.get(jtbl_corridas.getSelectedRow());
                       else
                       {
                           TmsCorridasVentaTbl corRemota = new TmsCorridasVentaTbl();
                           Vector vec = (Vector)busquedas.corridasVentaTblFacadeRemote.findRemota(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString()), esquema);
                           if(vec.size()==0)
                           {
                               new jdlg_error("La corrida no se encontro en la base de datos","","Error de datos").setVisible(true);
                               jtbl_corridas.requestFocus();
                               return;
                           }
                           else
                           {
                               Vector v = (Vector)vec.get(0);
                               corRemota.setValoresIniciales(v);
                               corridaSelececcionada = corRemota;
                           }
                       }
                    }

                plantillaId = corridaSelececcionada.getPlantillaId().longValue();
                ConsultaOcupacion();
                //verificarCambiarOcupacion();
            } 
        } 
                 

    }//GEN-LAST:event_jtbl_corridasKeyReleased

    private void jtbl_corridasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_corridasFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
    }//GEN-LAST:event_jtbl_corridasFocusLost

    private void jtbl_corridasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_corridasFocusGained
        jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Detalles de Ocupacion | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior | <font color=FF0000>F9</font> Exportar Tabla a Excel</html>");
    }//GEN-LAST:event_jtbl_corridasFocusGained

    private void jtbl_corridasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_corridasKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN || evt.getKeyCode() == evt.VK_UP)
            modificarOcupacion = false;
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
        {
           modeloCorridas.setDataVector(null,encabezadoCorridas);
           //resizeColumnasCorridas();
           jtxt_hora_desde.setEnabled(true); 
           //jtxt_hora_desde.setText("");
           jtxt_autobus.setEnabled(true);
           //jtxt_autobus.setText("");
           jtxt_clave.setEnabled(true);
           //jtxt_clave.setText("");
           jcmb_servicio.setEnabled(true);
           jcmb_empresa.setEnabled(true);
           jcmbx_origen.setEnabled(true);
           jcmb_destino.setEnabled(true);
           jtxt_fecha.setEnabled(true);
           //jtxt_fecha.setText("");
           jtxt_fecha_hasta.setEnabled(true);
           //jtxt_fecha_hasta.setText("");
           jtxt_hora_desde.setEnabled(true);
           //jtxt_hora_desde.setText("");
           jtxt_fecha.selectAll();
           jtxt_fecha.requestFocus();
         
        }
         
        if(evt.getKeyCode() == evt.VK_F9){
            //Guardar a Archivo
            /*File b = null;
            String nombreArchivoExcel = "";
            jdl_nombreArchivoExcel dnom = new jdl_nombreArchivoExcel(new JDialog(), true);
            dnom.setVisible(true);
            if(dnom.getNombre().equals("salida"))
              return;
            else
             nombreArchivoExcel = dnom.getNombre();
             
             b = new File("C:\\"+nombreArchivoExcel+".xls");
             if(b.exists())
             {
                 new jdlg_error("¡El archivo existe!"," Favor de introducir otro nombre"," Archivo existente").setVisible(true);
                 System.out.println("El archivo existe...");
                 return;
             }
             else
             {*/
             int result = jFileChooser.showSaveDialog(this);
             if (result != JFileChooser.CANCEL_OPTION) {
                File nombre = jFileChooser.getSelectedFile();
                if ( ( nombre == null ) || ( nombre.getName().equals( "" ) ) ) {
                    JOptionPane.showMessageDialog( this, "Nombre de Archivo Invalido",
                            "TMS - Consulta de Ocupación", JOptionPane.ERROR_MESSAGE );
                } else  if(nombre.exists()) {
                        JOptionPane.showMessageDialog( this, "Archivo Existente",
                            "TMS - Consulta de Ocupación", JOptionPane.ERROR_MESSAGE );
                    } else {
                        try {
                            jProgressBar1.setValue( 0 );
                            jProgressBar1.setMinimum( 0 );
                            jProgressBar1.setMaximum(jtbl_corridas.getRowCount() - 1);

                            Guardar(nombre);

                            jProgressBar1.setValue( 0 );
                            jProgressBar1.setStringPainted(true);  
                            jProgressBar1.paint(jProgressBar1.getGraphics());
                        }catch(FileNotFoundException fnfe){
                            System.out.println("File not Found Exception "+fnfe);
                        }
                        
                    }
                }
                        
             /*jProgressBar1.setValue( 0 );
             jProgressBar1.setMinimum( 0 );
             jProgressBar1.setMaximum(jtbl_corridas.getRowCount() - 1);
             //nombreArchivoExcel = dnom.getNombre();
                /*String  contenidoArchivoExcel = "";
              *contenidoArchivoExcel equivale a salida
                for(int i=1; i<encTabla.size(); i++)
                    contenidoArchivoExcel = contenidoArchivoExcel + encTabla.get(i).toString()+"\t";
                contenidoArchivoExcel = contenidoArchivoExcel +"\n";
                for(int j=0; j<jtbl_corridas.getRowCount();j++)
                {
                    for(int k=1; k<jtbl_corridas.getColumnCount();k++)
                    {
                        if(jtbl_corridas.getValueAt(j,k)==null)
                          contenidoArchivoExcel =  contenidoArchivoExcel + " \t";
                        else    
                          contenidoArchivoExcel =  contenidoArchivoExcel + jtbl_corridas.getValueAt(j,k).toString()+"\t";
                    }
                    contenidoArchivoExcel = contenidoArchivoExcel +"\n";
                   jProgressBar1.setValue(j);
                   jProgressBar1.setStringPainted(true);
                   jProgressBar1.paint(jProgressBar1.getGraphics());
                }
                //System.out.println("Archivo a Excle: "+contenidoArchivoExcel);
                FileOutputStream os = null;
                    try {
                         os = new FileOutputStream("C:\\"+nombreArchivoExcel+".xls");
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    PrintStream ps = new PrintStream(os); 
                    ps.print(contenidoArchivoExcel);
                    try {
                        os.close(); 
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } 
              
             
          jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Exportacion completada", "Los datos se exportaron correctamente ¿Desea ver el archivo?");
          psn2.setVisible(true);
           if(respuestaSN)
           {
                try
                   {

                     Process p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL C://"+nombreArchivoExcel+".xls"); //prueba.xls");
                    }
                    catch (Throwable exc4)
                    {
                      System.out.println("No se puede abrir el archivo de Excel.\n");
                      exc4.printStackTrace();
                    }
            }*/   
             jProgressBar1.setValue( 0 );
             jProgressBar1.setStringPainted(true);
             jProgressBar1.paint(jProgressBar1.getGraphics());
             }
             
        //}
        

        if(evt.getKeyCode() == evt.VK_ENTER){
                boolean autorizacion = false;
                boolean autorizacion2 = false;
                int ntarpen = 0;
                autorizado = ""+usuarioId;
                autorizado2 = ""+usuarioId;
                //Vector vedotar = (Vector)busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje();
                //if(vedotar.size()==0) 
                  //new jdlg_error("¡Los estados de las tarjetas estan mal configurados! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
               // TmsCorridasVentaTbl corridaDis = lcorridas.get(jtbl_corridas.getSelectedRow());
                Vector vasientos = new Vector();
                //vasientos = (Vector)busquedas.variosFacadeRemote.numeroAsientosOcupadosNoDisponibles(corridaSelececcionada.getCorridaId().longValue());
                //int nasientos = Integer.valueOf(vasientos.get(0).toString());
//                if(nasientos>0)
//                {
//                              jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Corrida Bloqueada", "La corrida esta bloqueda, espere unos segundos o ¿Desea autorizar la tarjeta?");
//                              psn2.setVisible(true);
//                              if(respuestaSN)
//                              {
//                                String respuesta = busquedas.variosFacadeRemote.buscaFuncion(datosIniciales.get(1).toString(),"6015"); 
//                                if(respuesta.equals("encontrado"))
//                                 autorizado2 = ""+usuarioId;
//                                else
//                                      autorizacion2 = true;
//                              }
//                              else
//                                return;                
//                }
                
                //Vector vparam = (Vector) busquedas.variosFacadeRemote.buscarParametroNumMaxTar(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
//                System.out.println("Ser: "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString()+"  "+"Vector vparam: "+vparam);
//                if(vparam.size()>0)
//                {
//                    Vector param = (Vector)vparam.get(0);
//                    int np = Integer.parseInt(param.get(0).toString());
//                    Vector vntar =  new Vector();
//                    vntar =   (Vector) busquedas.variosFacadeRemote.buscarNumTar(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString(),jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString(),"");
//                    System.out.println("Oper: "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString()+"  "+"Vector vntar: "+vntar);
//                    if(vntar.size()>0)
//                    {
//                        Vector ntar = (Vector)vntar.get(0);
//                        int nt= Integer.parseInt(ntar.get(0).toString());
//                        if(nt>=np)
//                        {
//                          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Tarjetas Pendientes", "El usuario tiene  "+nt+"  tarjetas pendientes, ¿Desea autorizar una mas?");
//                          psn.setVisible(true);
//                          if(respuestaSN)
//                          {
//                            String respuesta = busquedas.variosFacadeRemote.buscaFuncion(datosIniciales.get(1).toString(),"6014"); 
//                            if(respuesta.equals("encontrado"))
//                             autorizado = ""+usuarioId;
//                            else
//                            {
//                                  autorizacion = true;
//                                  ntarpen = nt;
//                            }
//                          }
//                          else
//                            return;   
//                        }
//                    }
//                }
               //boolean
               //if(soloCentral || combinado)
                //corridaSelececcionada = busquedas.centralFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString())));


                Vector ocupacionSistema = new Vector();

                  if(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),(jtbl_corridas.getColumnCount()-1) ).toString().equals("CENTRAL"))
                     ocupacionSistema =  (Vector)busquedas.centralFacadeRemote.buscaDatosOcupacionPorSistema(corridaSelececcionada.getClaveCorrida());
                  else
                  {
                    if(esquema.equals("local"))
                        ocupacionSistema  = (Vector)busquedas.variosFacadeRemote.buscaDatosOcupacionPorSistema(corridaSelececcionada.getClaveCorrida());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                    else
                        ocupacionSistema = (Vector)busquedas.variosFacadeRemote.buscaDatosOcupacionPorSistemaRemoto(corridaSelececcionada.getClaveCorrida(), esquema);
                   }

                System.out.println("la ocupacion es: "+ocupacionSistema);
                if(ocupacionSistema.size()==0)
                {
                    new jdlg_advertencia("¡La corrida aun no tiene ocupacion!","","Corrida sin ocupacion").setVisible(true);
                    return;
                }
                Vector datosTarjeta =new Vector();
                String nombreoperador  = "";
                if(corridaSelececcionada.getOperador()==null)
                    nombreoperador = "No Asignado";
                else
                {
                    Vector vnombre = (Vector) busquedas.variosFacadeRemote.buscarNombreOperador(corridaSelececcionada.getOperador());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString());
                    Vector nombre;

                    if(vnombre.size()==0)
                      nombreoperador = "Operador desconocido";
                    else
                    { 
                        nombre = (Vector) vnombre.get(0);
                        if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)                                        
                          nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
                        else
                          nombreoperador = nombre.get(0)+ " " + nombre.get(2);
                    }                
                }
                datosTarjeta.add(corridaSelececcionada.getClaveCorrida());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                datosTarjeta.add(corridaSelececcionada.getServicio());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
                datosTarjeta.add(corridaSelececcionada.getOrigen());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),2).toString());
                datosTarjeta.add(corridaSelececcionada.getDestino());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),3).toString());
                datosTarjeta.add(corridaSelececcionada.getOperador());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString());
                datosTarjeta.add(nombreoperador);
                if(corridaSelececcionada.getAutobus()==null)
                    datosTarjeta.add("No Asignado");
                else
                    datosTarjeta.add(corridaSelececcionada.getAutobus());//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),5).toString());
                datosTarjeta.add(ff.format(corridaSelececcionada.getFechaHoraCorrida()));//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString());
                datosTarjeta.add(fh.format(corridaSelececcionada.getFechaHoraCorrida()));//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),7).toString());
                datosTarjeta.add("");//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),8).toString());
                datosTarjeta.add("");//jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),9).toString());
                //TmsCorridasVentaTbl corrida = lcorridas.get(jtbl_corridas.getSelectedRow());
                 datosTarjeta.add(corridaSelececcionada.getCorridaId());
                 datosTarjeta.add(autorizado); //usuarioId);
                 datosTarjeta.add(sesionId);
                 datosTarjeta.add(autorizado2);
                 
//                 datosTarjeta.add(corridaSelececcionada.getEmpresa());
                 int index = empresas.indexOf(corridaSelececcionada.getEmpresa()); 
                 if(index<0)
                   datosTarjeta.add("Autobuses México Puebla Estrella Roja S.A. de C.V.");
                 else
                 {
                     String[] parem =(String[]) paramEmpresas.get(index);
                     datosTarjeta.add(parem[0]);
                 }
                 
             if(modificarOcupacion)
             {
                Object[][] ocupacionS = new Object[ltiposPasajero.size()][4];
                for(int i=0; i<ltiposPasajero.size();i++){
                    Vector vtipo = (Vector) ltiposPasajero.get(i);
                    String letraTipo = vtipo.get(1).toString();
                     System.out.println("Letra : "+letraTipo);
                    int conteo = 0;
                    float total = 0;
                    for(int j=0; j<ocupacionSistema.size(); j++){
                        Vector vocupa = (Vector) ocupacionSistema.get(j);
                        if(letraTipo.equals(vocupa.get(4).toString()))
                        {
                           conteo++;
                           total = total + Float.valueOf(vocupa.get(5).toString());
                        }
                    }

                 ocupacionS[i][0]=vtipo.get(0).toString();   
                 ocupacionS[i][1]=""+conteo;   
                 ocupacionS[i][2]=""+total;   
                }
               new  Jdlg_DetalleOcupacion(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),ocupacionS,3,autorizacion,autorizacion2,ntarpen,false,"",permiso).setVisible(true);              
               modificarOcupacion= false;
             }
             else    
             {
              new  Jdlg_DetalleOcupacion(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),new Object[5][5],1,autorizacion,autorizacion2,ntarpen,false,"",permiso).setVisible(true);
             }              
           //buscarCorridas(claveActual,autobusActual,servicioActual);
        }
        
    }//GEN-LAST:event_jtbl_corridasKeyPressed

    private void jcmb_servicioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_servicioFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");             
    }//GEN-LAST:event_jcmb_servicioFocusLost

    private void jcmb_servicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_servicioFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Consulta Ocupacion | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Servicios | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");     
    }//GEN-LAST:event_jcmb_servicioFocusGained

    private void jcmb_servicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_servicioKeyPressed
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
           jtxt_hora_desde.requestFocus();
           jtxt_hora_desde.selectAll();
         }
        
        if(evt.getKeyCode() == evt.VK_RIGHT)
            jcmb_empresa.requestFocus();

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();  

        if(evt.getKeyCode() == evt.VK_ENTER)
              buscarCorridas(); 

    }//GEN-LAST:event_jcmb_servicioKeyPressed

    private void jtxt_fecha_hastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fecha_hastaKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT) {
            jtxt_hora_desde.requestFocus();
            jtxt_hora_desde.selectAll();
        }

        if(evt.getKeyCode() == evt.VK_LEFT) {
            jtxt_fecha.selectAll();
            jtxt_fecha.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_ENTER)
            buscarCorridas();

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }

        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();
}//GEN-LAST:event_jtxt_fecha_hastaKeyPressed

    private void jtxt_fecha_hastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fecha_hastaFocusLost
        jtxt_fecha_hasta.setBackground(Color.WHITE);
}//GEN-LAST:event_jtxt_fecha_hastaFocusLost

    private void jtxt_fecha_hastaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fecha_hastaFocusGained
        jtxt_fecha_hasta.setBackground(new Color(184,207,229));
}//GEN-LAST:event_jtxt_fecha_hastaFocusGained
    
    private long fechaMilisegundos(String fecha){
        StringTokenizer tknz = new  StringTokenizer(fecha,"/");
        String dia = tknz.nextToken();
        String mes = tknz.nextToken();
        String año = tknz.nextToken();
        Timestamp fechaCompleta = null;
        fechaCompleta = fechaCompleta.valueOf(año+"-"+mes+"-"+dia+" 00:00:00");
        return fechaCompleta.getTime();
    }
        
    
    /************************* plantillas ***************************/
    private boolean Plantillas(){
        componentes = new ArrayList<TmsComponenteBusTbl>(busquedas.variosFacadeRemote.queryTmsComponenteBusTblFindAll());
        encabezados = new ArrayList<TmsAutobusPlantillasEncTbl>(busquedas.variosFacadeRemote.queryTmsAutobusPlantillasEncTblFindAll());
        lineas =  new ArrayList<TmsAutobusPlantLineasTbl>(busquedas.variosFacadeRemote.queryTmsAutobusPlantLineasTblFindAll());
        
        if(componentes.size()==0 || encabezados.size()==0 || lineas.size()==0) return false;
        PLANTILLA_DEFAULT=encabezados.get(0).getPlantillaEncId();
        return true;
    }
    
    public List<TmsComponenteBusTbl> getComponentes(){
        return componentes;
    }
    
    public TmsAutobusPlantillasEncTbl getEncabezado(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i);
        return null;
    }
    
    public List<TmsAutobusPlantLineasTbl> getLineas(long PlantillaId){
        Vector vLineas = new Vector();
        
        for(int i=0; i<lineas.size(); i++)
            if(lineas.get(i).getPlantillaEncId()==PlantillaId)
                vLineas.addElement(lineas.get(i));
        
        if(vLineas.size()==0) return null;
        return new ArrayList<TmsAutobusPlantLineasTbl>(vLineas);
    }
    
    public int getCapacidadPlantilla(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i).getCapacidadAsientos().intValue();
        return 0;
    }
    
   public void ConsultaOcupacion(){
        boletosVendidos.clear();
        int cont=0, asiento=1, fin;
        
        BoletosOcupados.clear();
        BoletosReservados.clear();
        BoletosReservadosNC.clear();
        JPnlbus.setPlantilla(this.getComponentes(),
                             this.getEncabezado(plantillaId),
                             this.getLineas(plantillaId));
        JPnlbus.pintaPlantilla(plantillaId);
        capacidad = this.getCapacidadPlantilla(plantillaId); 
        //fin=capacidad+21;
        String edoAsiento="";
        Vector listaAsientos = new Vector();
         //TmsCorridasVentaTbl corrida = lcorridas.get(jtbl_corridas.getSelectedRow());
         //TmsCorridasVentaTbl corrida2 = busquedas.corridasVentaTblFacadeRemote.actualizarCorrida(corrida);
        listaAsientos= corridaSelececcionada.getAsientos();
//        System.out.println("Corrida Seleccionada : "+corridaSelececcionada.getClaveCorrida());
//        System.out.println("El vector de ocupacion es(listaAsientos): "+listaAsientos);
        for(cont=0; cont<capacidad; cont++){
            edoAsiento=listaAsientos.get(cont).toString();//this.listadoCorridas[fila][cont].toString();//"D";
            if(!edoAsiento.equals("D")){
                if(edoAsiento.equals("N") || edoAsiento.equals("V")) BoletosOcupados.addElement(asiento);
                if(edoAsiento.equals("R")) BoletosReservados.addElement(asiento);
                if(edoAsiento.equals("C")) BoletosReservadosNC.addElement(asiento);
                boletosVendidos.addElement(asiento);
            }
            asiento++;
        }
        if (boletosVendidos.size() > 0){
            if(BoletosOcupados.size()!=0){ JPnlbus.setOcupados(BoletosOcupados); }
            if(BoletosReservados.size()!=0){ JPnlbus.setReservados(BoletosReservados); }
            if(BoletosReservadosNC.size()!=0){ JPnlbus.setReservadNC(BoletosReservadosNC); }
        }
    }    
  
   private void llenarCombosBox() {
            remoto = true;
            jlbl_terminal.setVisible(true);
            lisEsquemasTerminales =  busquedas.baseDatosConfigTblFacadeRemote.buscaEsquemasTerminales("N");
             Vector vdbLC = (Vector)busquedas.variosFacadeRemote.buscaDBLinkCentral();
             Vector dbLC = (Vector)vdbLC.get(0);
             Vector ds = (Vector)busquedas.variosFacadeRemote.buscaDestinos();
            jcmbx_origen.removeAllItems();
            jcmb_destino.removeAllItems();
            jcmb_empresa.removeAllItems();
            //jcmbx_origen.addItem("Todos");
            TmsBaseDatosConfigTbl bdCentral = new TmsBaseDatosConfigTbl();
            bdCentral.setBdconfigId(BigDecimal.valueOf(Long.valueOf("-1")));
            bdCentral.setNombreTerminal("Todos");
            bdCentral.setNombreDblink(dbLC.get(0).toString());
            bdCentral.setEsquemaPropio("N");
            //jcmb_destino.addItem("Todos");
            //lisEsquemasTerminales.add(0,bdCentral);
            //jcmb_empresa.addItem("Todas");
            int local = 0;
            for(int i = 0; i<lisEsquemasTerminales.size(); i++)
            {
                if(lisEsquemasTerminales.get(i).getEsquemaPropio().equals("S"))
                    local = i;
                jcmbx_origen.addItem(lisEsquemasTerminales.get(i).getNombreTerminal());
                //jcmb_destino.addItem(lisEsquemasTerminales.get(i).getNombreTerminal());
            }
            jcmb_destino.addItem("Todos");
            for(int i = 0; i<ds.size(); i++)
            {
                Vector d = (Vector)ds.get(i);
                jcmb_destino.addItem(d.get(0).toString());
            }
                
           jcmbx_origen.setSelectedIndex(local);
           List<TmsEmpresasTbl> listadoEmpresas = busquedas.empresasTblFacadeRemote.findAll();
            for(int i = 0; i<listadoEmpresas.size(); i++)
                jcmb_empresa.addItem(listadoEmpresas.get(i).getEmpresaNombre());
           
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    }


    private void setUsuarioId(String string) {
        this.usuarioId = Long.valueOf(string);
    }

    private void setSesionId(String string) {
        this.sesionId = Long.valueOf(string);
    }
  
      private long getSesionId() {
        return this.sesionId;
    }  
    
    public long getPLANTILLA_DEFAULT(){ return this.PLANTILLA_DEFAULT; }      


    /**
     * @param args the command line arguments
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JIFPuertas().setVisible(true);
            }
        });
    }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPnlAutobus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jchbx_extras;
    private javax.swing.JComboBox jcmb_destino;
    private javax.swing.JComboBox jcmb_empresa;
    private javax.swing.JComboBox jcmb_servicio;
    private javax.swing.JComboBox jcmbx_origen;
    private javax.swing.JLabel jlbl_Reloj;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_terminal;
    private javax.swing.JTable jtbl_corridas;
    private tms_TextFields.JTextTextField jtxt_autobus;
    private tms_TextFields.JTextTextField jtxt_clave;
    private tms_TextFields.JDateTextField jtxt_fecha;
    private tms_TextFields.JDateTextField jtxt_fecha_hasta;
    private tms_TextFields.JHourTextField jtxt_hora_desde;
    // End of variables declaration//GEN-END:variables
    // plantillas
    private Vector codigos = new Vector();
    private Vector valores = new Vector();
    private Vector ltiposPasajero;
    private Vector tiposPasajero = new Vector();
    private Vector letraTiposPasajero = new Vector();
    private Vector indiceCorridas = new Vector();
    private Vector ocupacionCorridas = new Vector();
    private  List<TmsCorridasVentaTbl> lcorridas;
    private List<TmsComponenteBusTbl> componentes;
    private List<TmsAutobusPlantillasEncTbl> encabezados;
    private List<TmsAutobusPlantLineasTbl> lineas;
    private JPnl_BusX JPnlbus;
    private Vector boletosVendidos = new Vector();
    Vector BoletosOcupados      = new Vector();
    Vector BoletosReservados    = new Vector();
    Vector BoletosReservadosNC  = new Vector();
    private Object[] encabezadoCorridas = {"Clave Corrida","Servicio","Origen","Destino","Operador","Autobus","Fecha","Hora","RutaId","RutaNombre"};
    private DefaultTableModel modeloCorridas= new DefaultTableModel(){
    public boolean isCellEditable (int row, int column){if (column == 20)return true;return false;}};
    private TmsConsultasManagedBean busquedas = new TmsConsultasManagedBean();
    private List<TmsServiciosTbl> listaServicios = null;
    private boolean respuestaSN = false;
    private Timestamp fecha_servidor= null; 
    private long plantillaId=1;
    private boolean modificarOcupacion = false;
    private int capacidad;
    private Vector datosIniciales;
    private long usuarioId;
    private long sesionId;
    private String claveActual ="";
    private String autobusActual="";
    private String servicioActual="";
    private String autorizado = "";
    private String autorizado2 = "";
    private boolean remoto = false;
    private List<TmsBaseDatosConfigTbl> lisEsquemasTerminales; 
    private long PLANTILLA_DEFAULT;
    private TmsCorridasVentaTbl corridaSelececcionada;
    private Vector empresas = new Vector();
    private Vector paramEmpresas = new Vector();
    private SimpleDateFormat ff;
    private SimpleDateFormat fh;
    private Vector encTabla = new Vector();
    private Vector datosTabla = new Vector();
    private boolean permiso = false;
    private String esquema = "";
    private RelojVisual jlbl_reloj2;
    private String rutas = "";
    private Vector VectorRutas = null;
    private Vector VectorEmpresas = new Vector();
    //Inicia Modificacion al codigo
    private String empresas_ruta = "";
    //Termina Modificacion al codigo
    //// C L A S E S
class jdlg_pregunta_SN extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_pregunta_SN
     */
    public jdlg_pregunta_SN(String titulo, String pregunta){
        this.setTitle(titulo);
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        this.setResizable(false);
        jlbl_mensaje.setText(pregunta);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        int nletras = pregunta.length();
        this.setSize((nletras * 6) + 80,150);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            //this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            jlbl_mensaje.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jlbl_mensaje = new javax.swing.JLabel();
        jlbl_mensaje.setFocusTraversalKeysEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\pregunta.gif"));

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jlbl_mensaje.setFont(new java.awt.Font("Arial", 1, 12));
        jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
        jlbl_mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_mensajeKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(14, 14, 14)
                .add(jlbl_mensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .add(424, 424, 424))
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_mensaje))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 59, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>                           

    private void jlbl_mensajeKeyPressed(java.awt.event.KeyEvent evt) {                                        
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          respuestaSN = false;
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
          respuestaSN = true;
          this.dispose();
      }
      
    }                                       
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_mensaje;
    
    // End of variables declaration                   
    
}

public class CustomTableCellRenderer extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent (JTable table,Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        if (isSelected) {
            cell.setBackground(new Color(0,175,255));
            cell.setForeground(Color.WHITE);
        } 
        else {
            cell.setForeground(Color.BLACK);
            if (row % 2 == 0) {
                cell.setBackground(new Color(217,229,241));
            }
            else {
                cell.setBackground(Color.WHITE);
            }
        }
        //if(column == 7)
        //    setHorizontalAlignment(JTextField.CENTER);
        //else
            setHorizontalAlignment(JTextField.LEFT);
        return cell;
    }
}

    private KeyEvent eventoTeclado;

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }    

//----------------Clases Reloj----------//
 class RelojVisual{
     /**
      * Se pasa un observable de fecha/hora. El Observable debe pasar un
      * Date a esta visual para que la presente.
      */
     public RelojVisual(Observable modelo)
     {
         // La fecha/hora se pinta en el centro de este JLabel
         jlbl_Reloj.setHorizontalAlignment((SwingConstants.CENTER));
         jlbl_Reloj.setVerticalAlignment(SwingConstants.BOTTOM);  
         //this.setForeground(Color.BLUE);
         
         // Suscripción al cambio de fecha/hora en el modelo recibido.
         modelo.addObserver (new Observer ()
         {
             // Método al que el Observable llamará cuando se cambie
             // la fecha/hora. El arg se espera que sea un Date.
             public void update(java.util.Observable o, Object arg) 
             {
                 final Object fecha = arg;
                 
                 // Se actualiza en pantalla la fecha/hora.
                 SwingUtilities.invokeLater (new Runnable()
                 {
                     public void run()
                     {
                         jlbl_Reloj.setText (format.format(fecha));
                     }
                 });
             }
         });
         
         // Se da una dimension al JLabel.
         jlbl_Reloj.setPreferredSize(new Dimension (200, 20));
     }
     
     /**
      * Cambia el formato de presentacion de la fecha/hora en pantalla.
      */
     public void setFormat (SimpleDateFormat unFormato)
     {
         format = unFormato;
     }
     
     /**
      * Clase para mostrar una fecha/hora en formato texto.
      */
      SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
//    SimpleDateFormat format = new SimpleDateFormat ("HH:mm:ss");
    
}


 class RelojModeloSwing extends Observable
 {
     /**
      * Lanza un timer cada segundo, avisando a los observadores de este
      * modelo del cambio. 
      */
     public static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm";

    private long vinicial; 
    private long lahora=0;
     public RelojModeloSwing(long pvinicial)
     {

         //busquedas = pbusquedas;
         vinicial = pvinicial;
         Timer timer = new Timer (1000, new ActionListener ()
         {
             public void actionPerformed(ActionEvent e)
             {
                 long time;
                 setChanged();
                 lahora++;
                 //if(lahora==3600)
                 if(lahora==600)
                 {
                     Timestamp fecha_servidor = null;
                    Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
                    if(x != null) {
                        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                        time = fecha_servidor.getTime();
                    }
                    else{
                        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                        time = fecha_servidor.getTime();
                    }
                    vinicial = time;
                    lahora = 0;
                 }
                     notifyObservers (new Date(vinicial));
                     vinicial = vinicial+1000;
             }
         });
         timer.start();
     }
     
     public Object now() {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            return sdf.format(cal.getTimeInMillis());
     }
     
}
 
 class FileExtensions extends FileFilter{
    
    String desc, ext;
    public FileExtensions(String desc, String ext) {
        this.desc = desc;
        this.ext = ext;
    }
    
    public boolean accept(File file) {
        return file.getName().toLowerCase().endsWith("."+ext);
  }

    public String getDescription() {
        return desc+"(*."+ext+")";
    }
}
}
