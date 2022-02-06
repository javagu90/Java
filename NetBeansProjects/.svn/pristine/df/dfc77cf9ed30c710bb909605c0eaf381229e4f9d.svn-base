/*
 * JIFPuertas0.java
 *
 * Created on 8 de octubre de 2007, 04:37 PM
 */

package tmspuertas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JDateTextField;
import tms_encriptacion.EncriptarMD5;
import tmspuertas.entidad.TmsAutobusPlantLineasTbl;
import tmspuertas.entidad.TmsAutobusPlantillasEncTbl;
import tmspuertas.entidad.TmsComponenteBusTbl;
import tmspuertas.entidad.TmsCorridasVentaTbl;
import tmspuertas.solicitud.UsuarioNoEncontradoException;
import tmspuertas.util.RelojModeloSwing;
import tmspuertas.util.RelojVisual;
import tmspuertas.util.tmscargaimpresorasPuertas;

/**
 *
 * @author  vgonzalez
 */
public class JIFPuertas extends javax.swing.JInternalFrame {// javax.swing.JFrame {//    
    
    /**
     * Creates new form JIFPuertas
     */
    public JIFPuertas(Vector pDatosIniciales) {
        this.datosIniciales = pDatosIniciales;
        System.out.println("Vector el de Datos iniciales es: "+datosIniciales);
        this.setUsuarioId(datosIniciales.get(0).toString());
        this.setSesionId(datosIniciales.get(3).toString());
        this.ipAS = datosIniciales.get(5).toString();
        this.portAS = Integer.valueOf(datosIniciales.get(6).toString());
          if(!abreSocketAS()){
           new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
           System.exit(0);
            }
        Plantillas();
        Vector x = new Vector();
        x =  (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
        initComponents();
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setIconifiable(true);
        this.setTitle("Puertas   Rev13.08.14");
        jcmb_servicio.removeAllItems();
        JDialog di = new JDialog();
        di.setDefaultLookAndFeelDecorated(true);
        di.dispose();
        Vector listaServicios   = busquedas.facadeGeneralPuertasRemote.buscaNombreServicios();
        Vector vvDBLink = (Vector) busquedas.facadeGeneralPuertasRemote.buscaDBLinkCentral();
        if(vvDBLink.size()==0)
        {
            new jdlg_error("¡La conexion con la BD Central esta mal configurada! ","Favor de contactar al Administrador del Sistema ","Error de Configuracion").setVisible(true);
            return;
        }
            jcmb_servicio.addItem("TODOS LOS SERVICIOS");
        jtbl_corridas.setModel(modeloCorridas);
        for(int i=0; i<listaServicios.size(); i++)
        {
            Vector serv = (Vector)listaServicios.get(i);
            jcmb_servicio.addItem(serv.get(0).toString());
        }
        jtbl_corridas.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none");
        jtbl_corridas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        jtbl_corridas.setRowHeight(28);  
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jcmb_servicio.setFocusTraversalKeysEnabled(false);
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
        jcmbx_terminales.setVisible(false);
        jlbl_terminal.setVisible(false);
      //jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Termina | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000></font> Campo Siguiente | <font color=FF0000>ARRIBA</font> Campo Anterior  | <font color=FF0000>F11</font> Reimprimir Tarjeta </html>");        
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
//        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
//        jtx_fecha.setText(ff.format(fecha_servidor.getTime()));
        Vector vDBLink = (Vector) vvDBLink.get(0);
        DBLinkCentral  = vDBLink.get(0).toString();       
        boolean continuar = true;
        cargaimpresoras = new tmscargaimpresorasPuertas();
        if(!cargaimpresoras.isConfiguarada())
        {
            new jdlg_error("¡No esta configurado este equipo como caja! ","Favor de contactar al administrador del sistema","Equipo no configurado").setVisible(true);
            continuar = false;
        }
        if(!cargaimpresoras.isTiene())
        {
            new jdlg_error("¡Este equipo no tienen impresoras configuradas! ","Favor de contactar al administrador del sistema","Equipo no configurado").setVisible(true);
            continuar = false;
        }
        
        if(!cargaimpresoras.isEncontro())
        {
            new jdlg_error("¡Esta caja no tiene impresora configurada para tarjetas o no esta instalada! "," Favor de contactar al Administrador del Sistema!","Error de configuracion de impresoras").setVisible(true);
            continuar = false;
        }
        setInicioGral(continuar);
        if(continuar)
        {   
            puertos   = cargaimpresoras.getFormatos();
            impresoras = cargaimpresoras.getImpresoras();
            puerto =  puertos.get(0).toString();
            impresora = (PrintService)impresoras.get(0);
            System.out.println("La Impresora de Tarjetas es: "+impresora.getName()+"  en el Puerto: "+puerto);
            buscarCorridas("%","%","%");
             claveActual    = "%";
             autobusActual  = "%";
             servicioActual = "%";
        }
    }

      private void resizeColumnasCorridas(){
        TableColumn columinv = jtbl_corridas.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);//140
        columinv = jtbl_corridas.getColumnModel().getColumn(1);columinv.setMinWidth( 150 );columinv.setMaxWidth( 150 );columinv.setPreferredWidth(150);        
        columinv = jtbl_corridas.getColumnModel().getColumn(2);columinv.setMinWidth( 150 );columinv.setMaxWidth( 150 );columinv.setPreferredWidth(150);        
        columinv = jtbl_corridas.getColumnModel().getColumn(3);columinv.setMinWidth( 63 );columinv.setMaxWidth( 63 );columinv.setPreferredWidth(63);        
        columinv = jtbl_corridas.getColumnModel().getColumn(4);columinv.setMinWidth( 63 );columinv.setMaxWidth( 63 );columinv.setPreferredWidth(63);        
        columinv = jtbl_corridas.getColumnModel().getColumn(5);columinv.setMinWidth( 14 );columinv.setMaxWidth( 14 );columinv.setPreferredWidth(14);        
        columinv = jtbl_corridas.getColumnModel().getColumn(6);columinv.setMinWidth( 75 );columinv.setMaxWidth( 75 );columinv.setPreferredWidth(75);        
        columinv = jtbl_corridas.getColumnModel().getColumn(7);columinv.setMinWidth( 75 );columinv.setMaxWidth( 75 );columinv.setPreferredWidth(75);        
        columinv = jtbl_corridas.getColumnModel().getColumn(8);columinv.setMinWidth( 77 );columinv.setMaxWidth( 77 );columinv.setPreferredWidth(77);        
        columinv = jtbl_corridas.getColumnModel().getColumn(9);columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);        
        columinv = jtbl_corridas.getColumnModel().getColumn(10);columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);        
        columinv = jtbl_corridas.getColumnModel().getColumn(11);columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);        
        columinv = jtbl_corridas.getColumnModel().getColumn(12);columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);        
      }    
    
    public void cargaDatosIniciales(){
          if(!abreSocketAS()){
           new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
           return;
            }
       ltiposPasajero = (Vector)busquedas.facadeGeneralPuertasRemote.buscarTiposPasajero();
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
       
       Vector vparamServicios = (Vector)busquedas.facadeGeneralPuertasRemote.buscaParametrosPorServicio();       
        if(vparamServicios.size()>0)
        {
            for(int i=0; i<vparamServicios.size(); i++)
            {
                Vector ps = (Vector)vparamServicios.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
       Vector vparamRutas = (Vector)busquedas.facadeGeneralPuertasRemote.buscaParametrosPorRuta();       
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
        

        Vector vEmpresas = (Vector) busquedas.facadeGeneralPuertasRemote.parametrosEmpresas();
        for(int i=0; i<vEmpresas.size(); i++)
        {
            Vector emp = (Vector) vEmpresas.get(i);
            empresas.add(emp.get(0).toString());
            String[] paem = new String[3];
            paem[0] =   emp.get(1).toString();
            paem[1] =   emp.get(2).toString();
            paramEmpresas.add(paem);
        }
        Vector vNombreEmpresas = (Vector) busquedas.facadeGeneralPuertasRemote.buscaEmpresas();
        {
            for(int i=0; i<vNombreEmpresas.size(); i++)
            {
                Vector emp = (Vector)vNombreEmpresas.get(i);
                idNombreEmpresas.add(emp.get(0).toString());
                nombreEmpresas.add(emp.get(1).toString());
            }
        } 
        System.out.println("Parametros Empresas:    "+valores);
        
        edotar = busquedas.facadeGeneralPuertasRemote.queryBuscaEstadoTarjetaViaje();
        vEdosAcc = busquedas.facadeGeneralPuertasRemote.buscaEstadosAcciones();
        //vEdosAcc(0)ESTADO_ID => 'ENROLADO', (1)ESTADO_ID => 'CORRIDA', (2)ACCION_ID => 'ENROLADO', (3)ACCION_ID => 'CORRIDA'
    }
     
    private void buscarCorridas(String clavOp, String bus, String servic){
          if(!abreSocketAS()){
           new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
           return;
            }
        codigos = new Vector();
        valores = new Vector();
        modeloCorridas.setDataVector(null,encabezadoCorridas);
           jtxt_hora_desde.setEnabled(true);
           jtx_fecha.setEnabled(true); 
           jtxt_autobus.setEnabled(true);
           jtxt_clave.setEnabled(true);
           jcmb_servicio.setEnabled(true);
        resizeColumnasCorridas();
        Vector vparamServicios = new Vector();
             vparamServicios  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaParametrosPorServicio();       
        if(vparamServicios.size()>0)
        {
            for(int i=0; i<vparamServicios.size(); i++)
            {
                Vector ps = (Vector)vparamServicios.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
       Vector vparamRutas = new Vector();
            vparamRutas =   (Vector)busquedas.facadeGeneralPuertasRemote.buscaParametrosPorRuta();       
        if(vparamRutas.size()>0)
        {
            for(int i=0; i<vparamRutas.size(); i++)
            {
                Vector ps = (Vector)vparamRutas.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
        if(bus.equals(""))
            bus = "%";
        if(clavOp.equals(""))
            clavOp = "%";
        System.out.println("Clave: "+clavOp);
        System.out.println("Atobus: "+bus);
        System.out.println("Servicio: "+servic);
        SimpleDateFormat formatfcn  = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat formatfcom = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatfc   = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formathc   = new SimpleDateFormat ("HH:mm");
        Vector x =  new Vector();
               x =  (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
        Vector x2 =  new Vector();
               x2 =  (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidorManana();
        fecha_servidor_manana = null;
        fecha_servidor_manana = fecha_servidor_manana.valueOf(x2.get(0).toString()+" 00:00:00");
        Timestamp fecha_servidor_manana = null;
        lcorridas = null;
        
         
        if(!jtxt_hora_desde.getText().equals("") && jtx_fecha.getText().equals(""))
        {
             if(jtxt_hora_desde.getText().length()<5)
              {
                new jdlg_error("¡Debes introducir horas validas!","","Error en formato de hora").setVisible(true);
                return;
              }
                fecha_servidor = fecha_servidor.valueOf(formatfcn.format(fecha_servidor.getTime())+" "+jtxt_hora_desde.getText()+":00");
        }       
        
        if(!jtxt_hora_desde.getText().equals("") && !jtx_fecha.getText().equals(""))
        {
             if(jtxt_hora_desde.getText().length()<5)
              {
                new jdlg_error("¡Debes introducir horas validas!","","Error en formato de hora").setVisible(true);
                return;
              }
             if(jtx_fecha.getText().length()<10)
              {
                new jdlg_error("¡Debes introducir una fecha valida!","","Error en formato de fecha").setVisible(true);
                return;
              }

           String sfechabusqueda = jtx_fecha.getText();
           StringTokenizer tokenff = new StringTokenizer(sfechabusqueda,"/");  
           String di= tokenff.nextToken();
           String mi= tokenff.nextToken(); 
           String ai= tokenff.nextToken();        
           Timestamp fcorridasatras = null;
           fcorridasatras = fcorridasatras.valueOf(ai+"-"+mi+"-"+di+" 00:00:00");
           fecha_servidor = fecha_servidor.valueOf(formatfcn.format(fcorridasatras.getTime())+" "+jtxt_hora_desde.getText()+":00");
        }
        

        if(jtxt_hora_desde.getText().equals("") && !jtx_fecha.getText().equals(""))
        {
             if(jtx_fecha.getText().length()<10)
              {
                new jdlg_error("¡Debes introducir una fecha valida!","","Error en formato de fecha").setVisible(true);
                return;
              }

           String sfechabusqueda = jtx_fecha.getText();
           StringTokenizer tokenff = new StringTokenizer(sfechabusqueda,"/");  
           String di= tokenff.nextToken();
           String mi= tokenff.nextToken(); 
           String ai= tokenff.nextToken();        
           Timestamp fcorridasatras = null;
           fcorridasatras = fcorridasatras.valueOf(ai+"-"+mi+"-"+di+" 00:00:00");
           fecha_servidor = fecha_servidor.valueOf(formatfcn.format(fcorridasatras.getTime())+" "+formathc.format(fecha_servidor.getTime())+":00");
           System.out.println("Fecha Servidor: "+fecha_servidor);
           System.out.println("Fecha Servidor Mañana: "+fecha_servidor_manana);
        }

        long tiempoDespues = 21600000;//7200000; //2 horas
        //if(clavOp.equals("") && bus.equals("") && servic.equals("") )     
           //lcorridas =(List<TmsCorridasVentaTbl>) busquedas.corridasVentaTblFacadeRemote.buscarCorridasPorFecha(new Timestamp(fecha_servidor.getTime()), new Timestamp(fecha_servidor.getTime() + tiempoDespues));
        //else
        //{ //900000 ==>15 min 
            String fchi = formatfcom.format(fecha_servidor.getTime() - 900000);
            String fchf = formatfcom.format(fecha_servidor.getTime() + tiempoDespues);
            //if(!clavOp.equals("") && !bus.equals("") && !servic.equals(""))
            if(remoto)
            {
                Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                lcorridas = busquedas.facadeGeneralPuertasRemote.buscarCorridasProximasRemoto(fchi, fchf, clavOp, servic, bus,tersel.get(1).toString());           
            }
            else
               lcorridas = busquedas.facadeGeneralPuertasRemote.buscarCorridasProximas(fchi, fchf, clavOp, servic, bus);
        //}
            
        
        if(lcorridas.size()==0)
        {
            new jdlg_advertencia("¡No hay corridas proximas!  ","","No se encontraron corridas").setVisible(true);
            jcmb_servicio.setSelectedIndex(0);
            jtxt_autobus.setText("");
            jtxt_clave.setText("");
            jtx_fecha.setText("");
            jtxt_hora_desde.setText("");
            jtxt_clave.requestFocus();
        }
        else
        {
            Object[][] datos = new Object[lcorridas.size()][20];
            for(int i=0; i<lcorridas.size(); i++)
            {
                Vector corrida = (Vector)lcorridas.get(i);
                //(0)Corrida_id, (1)Clave_corrida, (2)Servicio, (3)Origen, (4)Destino, (5)Fecha_hora_corrida, (6)Operador, (7)Autobus, (8)Plantilla_id, (9)Empresa
                datos[i][0] = corrida.get(1).toString();
                datos[i][1] = corrida.get(2).toString();
                datos[i][2] = corrida.get(9).toString();
                datos[i][3] = corrida.get(3).toString();
                datos[i][4] = corrida.get(4).toString();
                datos[i][5] = corrida.get(10).toString();
                if( corrida.get(6)==null)
                 datos[i][6] = "No Asignado";
                else
                  datos[i][6] = corrida.get(6).toString();  
                if( corrida.get(7)==null)
                 datos[i][7] = "No Asignado";
                else
                  datos[i][7] = corrida.get(7).toString();  
                Timestamp hfc = null;
                hfc = Timestamp.valueOf(corrida.get(5).toString());
                datos[i][8] = formatfc.format(hfc.getTime());    
                datos[i][9] = formathc.format(hfc.getTime());    
                Vector lruta = new Vector();
                if(remoto) 
                {   
                    Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRutaRemoto(corrida.get(1).toString(),tersel.get(1).toString());
                }
                else
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRuta(corrida.get(1).toString());
                 
                if(lruta.size()>0)
                {
                    Vector ruta = (Vector) lruta.get(0);
                    datos[i][10] = ruta.get(0).toString();
                    datos[i][11] = ruta.get(1).toString();
                    datos[i][12] = ruta.get(2).toString();
                }
                else
                {
                    new jdlg_advertencia("¡La ruta de la corrida "+corrida.get(1).toString()+" no esta configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                    datos[i][10] = "00";
                    datos[i][11] = "RUTA DESCONOCIDA";
                    datos[i][12] = "0";
                }
            }
           modeloCorridas.setDataVector(datos,encabezadoCorridas);
           resizeColumnasCorridas();
           jtxt_hora_desde.setEnabled(false);
           jtx_fecha.setEnabled(false); 
           jtxt_autobus.setEnabled(false);
           jtxt_clave.setEnabled(false);
           jcmb_servicio.setEnabled(false);
           jtbl_corridas.setEnabled(true);
           jtbl_corridas.setRowSelectionInterval(0,0);
           jtbl_corridas.setColumnSelectionInterval(0,0);
           Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
            plantillaId = Long.valueOf(corrida.get(8).toString());
            System.out.println("plantillaId: "+plantillaId);
            ConsultaOcupacion();
            verificarCambiarOcupacion();
           jtbl_corridas.requestFocus();
       }
    }
    
    
    private void verificarCambiarOcupacion(){
        //TmsCorridasVentaTbl corrida = lcorridas.get(jtbl_corridas.getSelectedRow());
        int index = codigos.indexOf("P_VLRVENNAS-"+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),10));
        if(index>=0)
        {
            if(valores.get(index).toString().equals("S"))
            {
             jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Despachar Tarjeta | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior | <font color=FF0000>F2</font> Modificar Ocupacion  | <font color=FF0000>F3</font> Asignar Operador/Autobus </html>");
             modificarOcupacion = true;
            }
            else
                modificarOcupacion = false;
        }
        else
            jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Despachar Tarjeta | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior  | <font color=FF0000>F3</font> Asignar Operador/Autobus </html>");
    }
    
    private void salirAplicacion(){
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Salir de Puertas", "¿Seguro que desea salir de la Aplicacion de Puertas?");
          psn.setVisible(true);
          if(respuestaSN)
          {
              //hilo.interrupt();
              this.dispose();
          }

    }

    private void remprimir(){
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
        String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6013"); 
        if(respuesta.equals("encontrado"))
        {
           if(remoto)
           {
             Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
             new jdlg_Reimpresion(busquedas,codigos,valores,usuarioId,sesionId,encabezados,ltiposPasajero,remoto,tersel.get(1).toString(),puerto,impresora,empresas,paramEmpresas, this.ipAS, this.portAS).setVisible(true);
           }
           else
             new jdlg_Reimpresion(busquedas,codigos,valores,usuarioId,sesionId,encabezados,ltiposPasajero,remoto,"",puerto,impresora,empresas,paramEmpresas, this.ipAS, this.portAS).setVisible(true);
        }   
        else
        {
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
          if(validarDatosSupervisor("6013"))
          {
            if(remoto)
            {
              Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
              new jdlg_Reimpresion(busquedas,codigos,valores,Long.valueOf(autorizado),sesionId,encabezados,ltiposPasajero,remoto,tersel.get(1).toString(),puerto,impresora,empresas,paramEmpresas, this.ipAS, this.portAS).setVisible(true);
            }
            else
                new jdlg_Reimpresion(busquedas,codigos,valores,Long.valueOf(autorizado),sesionId,encabezados,ltiposPasajero,remoto,"",puerto,impresora,empresas,paramEmpresas, this.ipAS, this.portAS).setVisible(true);
          }
          
        }
    }

    
    private void mostrarDialogoSupervisor(String fun) {
        dlgSupervisor = new jdlgDatosSupervisor(true,"TMS - Validar Supervisor         Funcion: "+fun);
        dlgSupervisor.centrarDialogo();
        dlgSupervisor.setVisible(true);
    }

    private boolean validarDatosSupervisor(String nfuncion) {
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        mostrarDialogoSupervisor(nfuncion);
        String numeroUsr = dlgSupervisor.getNumeroUsuario();
        String claveSuper = dlgSupervisor.getContrasenaUsuario();
        boolean valido = false;
        String respuesta = "";
        if(numeroUsr.equals("nada") && claveSuper.equals("nada"))
            valido = false;
        else
        {
            try{
                respuesta = busquedas.facadeGeneralPuertasRemote.esUsuarioSupervisor(dlgSupervisor.getNumeroUsuario(),pwdEnc.encriptar(dlgSupervisor.getContrasenaUsuario()),nfuncion);
            } catch (UsuarioNoEncontradoException exu) {
                System.out.println(exu.getMessage());
                return false;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
     
            if(respuesta.equals("no encontrado"))
            {
                valido = false;
                new jdlg_error("¡Numero de usuario o Contraseña invalidos!   "," Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
            }
            else
            {
               if(respuesta.equals("invalido"))
               {
                    valido = false;
                    new jdlg_error("¡Tu perfil de usuario no te permite realizar esta funcion!   "," Favor de contactar al Administrador del Sistema", "Permiso Denegado").setVisible(true);
               }                
               else
               {
                  valido = true;
                  autorizado = respuesta;
               }
            }
        }
       return valido;
    }    
    
    private void mostrarDialogoSupervisor2(String fun) {
        dlgSupervisor = new jdlgDatosSupervisor(true,"TMS - Validar Supervisor         Funcion: "+fun);
        dlgSupervisor.centrarDialogo();
        dlgSupervisor.setVisible(true);
    }

    private boolean validarDatosSupervisor2(String nfuncion) {
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        mostrarDialogoSupervisor2(nfuncion);
        String numeroUsr = dlgSupervisor.getNumeroUsuario();
        String claveSuper = dlgSupervisor.getContrasenaUsuario();
        boolean valido = false;
        String respuesta = "";
        if(numeroUsr.equals("nada") && claveSuper.equals("nada"))
            valido = false;
        else
        {
            try{
                respuesta = busquedas.facadeGeneralPuertasRemote.esUsuarioSupervisor(dlgSupervisor.getNumeroUsuario(),pwdEnc.encriptar(dlgSupervisor.getContrasenaUsuario()),nfuncion);
            } catch (UsuarioNoEncontradoException exu) {
                System.out.println(exu.getMessage());
                return false;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
     
            if(respuesta.equals("no encontrado"))
            {
                valido = false;
                new jdlg_error("¡Numero de usuario o Contraseña invalidos!   "," Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
            }
            else
            {
               if(respuesta.equals("invalido"))
               {
                    valido = false;
                    new jdlg_error("¡Tu perfil de usuario no te permite realizar esta funcion!   "," Favor de contactar al Administrador del Sistema", "Permiso Denegado").setVisible(true);
               }                
               else
               {
                  valido = true;
                  autorizado2 = respuesta;
               }
            }
        }
       return valido;
    }    
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">
      // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          

  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_clave = new tms_TextFields.JTextTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxt_autobus = new tms_TextFields.JTextTextField();
        jLabel3 = new javax.swing.JLabel();
        jcmb_servicio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxt_hora_desde = new tms_TextFields.JHourTextField();
        jlbl_terminal = new javax.swing.JLabel();
        jcmbx_terminales = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jtx_fecha = new tms_TextFields.JDateTextField();
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
        jLabel5 = new javax.swing.JLabel();
        //jlbl_reloj = new javax.swing.JLabel();
        jlbl_reloj = new RelojVisual(new RelojModeloSwing(fecha_servidor.getTime(),busquedas));
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Clave Operador:");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Servicio:");

        jcmb_servicio.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_servicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        jlbl_terminal.setText("Terminal:");

        jcmbx_terminales.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmbx_terminales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbx_terminales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbx_terminalesKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Fecha:");

        jtx_fecha.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtx_fecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtx_fechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtx_fechaFocusLost(evt);
            }
        });
        jtx_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtx_fechaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .add(13, 13, 13))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel6)
                        .add(21, 21, 21)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtx_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(37, 37, 37)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel4)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jlbl_terminal))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel3)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcmbx_terminales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 165, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jtx_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_terminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcmbx_terminales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPnlAutobus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ocupacion de la corrida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        JPnlAutobus.setMaximumSize(new java.awt.Dimension(185, 431));
        JPnlAutobus.setPreferredSize(new java.awt.Dimension(185, 431));
        org.jdesktop.layout.GroupLayout JPnlAutobusLayout = new org.jdesktop.layout.GroupLayout(JPnlAutobus);
        JPnlAutobus.setLayout(JPnlAutobusLayout);
        JPnlAutobusLayout.setHorizontalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 211, Short.MAX_VALUE)
        );
        JPnlAutobusLayout.setVerticalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 543, Short.MAX_VALUE)
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jlbl_barraEstado.setText("jLabel4");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corridas proximas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_corridas.setFont(new java.awt.Font("Tahoma", 1, 11));
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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("");

        jlbl_reloj.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_reloj.setText("10/12/2008 14:59");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(791, Short.MAX_VALUE)
                .add(jlbl_reloj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34)
                .add(jLabel5)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jlbl_reloj))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>      
    
    /*    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_clave = new tms_TextFields.JTextTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxt_autobus = new tms_TextFields.JTextTextField();
        jLabel3 = new javax.swing.JLabel();
        jcmb_servicio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxt_hora_desde = new tms_TextFields.JHourTextField();
        jlbl_terminal = new javax.swing.JLabel();
        jcmbx_terminales = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jtx_fecha = new tms_TextFields.JDateTextField();
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
        jLabel5 = new javax.swing.JLabel();
        //jlbl_reloj = new javax.swing.JLabel();
        jlbl_reloj = new RelojVisual(new RelojModeloSwing(fecha_servidor.getTime(),busquedas));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Clave Operador:");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Servicio:");

        jcmb_servicio.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_servicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        jlbl_terminal.setText("Terminal:");

        jcmbx_terminales.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmbx_terminales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbx_terminales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbx_terminalesKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Fecha:");

        jtx_fecha.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtx_fecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtx_fechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtx_fechaFocusLost(evt);
            }
        });
        jtx_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtx_fechaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .add(13, 13, 13))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel6)
                        .add(21, 21, 21)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtx_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(37, 37, 37)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel4)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jlbl_terminal))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel3)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcmbx_terminales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 165, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jtx_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_terminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcmbx_terminales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jtxt_hora_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPnlAutobus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ocupacion de la corrida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        JPnlAutobus.setMaximumSize(new java.awt.Dimension(185, 410));
        JPnlAutobus.setPreferredSize(new java.awt.Dimension(185, 410));
        org.jdesktop.layout.GroupLayout JPnlAutobusLayout = new org.jdesktop.layout.GroupLayout(JPnlAutobus);
        JPnlAutobus.setLayout(JPnlAutobusLayout);
        JPnlAutobusLayout.setHorizontalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 211, Short.MAX_VALUE)
        );
        JPnlAutobusLayout.setVerticalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 535, Short.MAX_VALUE)
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jlbl_barraEstado.setText("jLabel4");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corridas proximas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("v1.19");

        jlbl_reloj.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_reloj.setText("10/12/2008 14:59");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(791, Short.MAX_VALUE)
                .add(jlbl_reloj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34)
                .add(jLabel5)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jlbl_reloj))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>                        
*/                   
    

    private void jtx_fechaKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
              jtxt_hora_desde.requestFocus();
              jtxt_hora_desde.selectAll();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
            jcmb_servicio.requestFocus();

        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
           }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
           }   
        }

        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                remoto = false;
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();
        
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
        
    }

    private void jtx_fechaFocusLost(java.awt.event.FocusEvent evt) {
        jtx_fecha.setBackground(Color.WHITE);
    }

    private void jtx_fechaFocusGained(java.awt.event.FocusEvent evt) {
        jtx_fecha.setBackground(new Color(184,207,229));
        if(remoto)
             jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
        else    
              jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    }
    
    
    private void jcmbx_terminalesKeyPressed(java.awt.event.KeyEvent evt) {                                            
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
              jtxt_clave.requestFocus();
              jtxt_clave.selectAll();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtxt_hora_desde.requestFocus();
            jtxt_hora_desde.selectAll();
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
           }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
           }   
        }

        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                remoto = false;
                jtxt_clave.requestFocus();
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();
        
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
    }                                           

    private void jtxt_hora_desdeFocusGained(java.awt.event.FocusEvent evt) {                                            
          jtxt_hora_desde.setBackground(new Color(184,207,229));
        if(remoto)
             jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
        else{    
              jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
    }                                           

    private void jcmbx_terminalesFocusGained(java.awt.event.FocusEvent evt) {
      if(remoto)
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Terminales | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");     
      else
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Terminales | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");               
    }
    
    private void jtxt_hora_desdeFocusLost(java.awt.event.FocusEvent evt) {                                          
        jtxt_hora_desde.setBackground(Color.WHITE);
    }                                         

    private void jtxt_autobusFocusLost(java.awt.event.FocusEvent evt) {                                       
       jtxt_autobus.setBackground(Color.WHITE);
    }                                      

    private void jtxt_claveFocusLost(java.awt.event.FocusEvent evt) {                                     
        jtxt_clave.setBackground(Color.WHITE);
    }                                    

    private void jcmbx_terminalesFocusLost(java.awt.event.FocusEvent evt) {
     jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    }
    
    
    
    private void jtxt_hora_desdeKeyPressed(java.awt.event.KeyEvent evt) {                                           
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
            if(jcmbx_terminales.isVisible())
             jcmbx_terminales.requestFocus();   
            else
            {
              jtxt_clave.requestFocus();
              jtxt_clave.selectAll();
            }
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtx_fecha.requestFocus();
            jtx_fecha.selectAll();
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
           }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
           }   
        }

        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                remoto = false;
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();
        
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
        
    }                                          

    private void jtxt_autobusKeyPressed(java.awt.event.KeyEvent evt) {                                        
        if(evt.getKeyCode() == evt.VK_RIGHT)
            jcmb_servicio.requestFocus();
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            jtxt_clave.selectAll();
            jtxt_clave.requestFocus();
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
           }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
           }   
        }

        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                remoto = false;
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }

        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();
        
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
    }                                       

    private void jtxt_claveKeyPressed(java.awt.event.KeyEvent evt) {                                      
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
            jtxt_autobus.selectAll();
            jtxt_autobus.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            if(jcmbx_terminales.isVisible())
                jcmbx_terminales.requestFocus();   
            else
            {
                jtxt_hora_desde.requestFocus();
                jtxt_hora_desde.selectAll();
            }
        }

        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
       }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
           }   
        }

        
        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                remoto = false;
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();
                
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
    }                                     

    private void jtxt_autobusFocusGained(java.awt.event.FocusEvent evt) {                                         
        jtxt_autobus.setBackground(new Color(184,207,229));
       if(remoto)
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
        else{    
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");                         
          }
    }                                        

    private void jtxt_claveFocusGained(java.awt.event.FocusEvent evt) {                                       
        jtxt_clave.setBackground(new Color(184,207,229)); //102, 255, 0(verde)
        if(remoto)
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
        else{    
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
    }                                      

    private void formComponentShown(java.awt.event.ComponentEvent evt) {                                    
        if(jtbl_corridas.getRowCount()>0)
        {
         jtbl_corridas.setRowSelectionInterval(0,0);
         jtbl_corridas.setColumnSelectionInterval(0,0);
         jtbl_corridas.requestFocus();
        }
        else
        {
           jtxt_hora_desde.setEnabled(true); 
           jtxt_hora_desde.setText("");
           jtx_fecha.setEnabled(true); 
           jtx_fecha.setText("");
           jtxt_autobus.setEnabled(true);
           jtxt_autobus.setText("");
           jtxt_clave.setEnabled(true);
           jcmb_servicio.setEnabled(true);
            jtxt_clave.setText("");
            jtxt_clave.requestFocus();
        }
    }                                   

    public void setFoco(){
        if(jtbl_corridas.getRowCount()>0)
        {
         jtbl_corridas.setRowSelectionInterval(0,0);
         jtbl_corridas.setColumnSelectionInterval(0,0);
         jtbl_corridas.requestFocus();
        }
        else
        {
            jtxt_clave.requestFocus();
        }
    }
    
    private void jtbl_corridasKeyReleased(java.awt.event.KeyEvent evt) {                                          
         if(evt.getKeyCode() == evt.VK_F2){
            if(modificarOcupacion)
            {
                  if(!jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1) .toString().equals("SERVICIO DE TURISMO"))
                   {
                       if(fecha_servidor.getTime()>=fecha_servidor_manana.getTime())
                       { 
                           new jdlg_error("¡Solo puedes despachar corridas de hoy!","","No se despachan corridas de dias posteriores").setVisible(true);
                          return;
                       }
                   }
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
                boolean autorizacion = false;
                boolean autorizacion2 = false;
                long ntarpen = 0;
                autorizado = ""+usuarioId;
                autorizado2 = ""+usuarioId;
                Vector corridaDis = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                Vector vasientos = new Vector();
                long nasientos =0;
                if(remoto){
                       Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                       vasientos = (Vector)busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponiblesRemoto(Long.valueOf(corridaDis.get(0).toString()),tersel.get(1).toString());
                }
                else
                 nasientos = busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponibles(Long.valueOf(corridaDis.get(0).toString()));
                if(nasientos>0)
                {
//                    new jdlg_advertencia("¡La corrida esta bloqueada!"," Espere unos segundos o verifique en taquillas por favor","Corrida Bloqueada").setVisible(true);
//                    return;
                              jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Corrida Bloqueada", "La corrida esta bloqueada, espere unos segundos o ¿Desea autorizar la tarjeta?");
                              psn2.setVisible(true);
                              if(respuestaSN)
                              {
                                String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6015"); 
                                if(respuesta.equals("encontrado"))
                                 autorizado2 = ""+usuarioId;
                                else
                                {
                                  if(!abreSocketAS()){
                                   new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                                   return;
                                    }
                                  if(!validarDatosSupervisor2("6015"))
                                    return;  
                                  else
                                      autorizacion2 = true;
                                }
                              }
                              else
                                return;
                    
                }
             boolean limitar = true;
             System.out.println("modificarOcupacion(1): "+modificarOcupacion);
             System.out.println("empresas: "+empresas);
             System.out.println("corridaDis: "+corridaDis.get(9).toString());
             int indexl = empresas.indexOf(corridaDis.get(9).toString());
             System.out.println("indexl: "+indexl);
             if(indexl<0)
             {
                 limitar = false;
                 modificarOcupacion= false;
             }
             else
             {
                 String[] pareml =(String[]) paramEmpresas.get(indexl);
                 if(pareml[1].equals("N"))
                     limitar = false;
                 if(pareml[1].equals("S"))
                    limitar = true; 
                 modificarOcupacion= false;
             }
             System.out.println("modificarOcupacion(2): "+modificarOcupacion);
              if(limitar)
              {
                        ntarpen = busquedas.facadeGeneralPuertasRemote.buscaPermitirCrearTarjeta(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString(),jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString(), DBLinkCentral);
                        System.out.println("Numero de tarjetas pendientes: "+ntarpen);
                        if(ntarpen>=0)
                       {
                              jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Tarjetas Pendientes", "El operador tiene  "+ntarpen+"  tarjetas pendientes. ¿Desea autorizar una mas?");
                              psn.setVisible(true);
                              if(respuestaSN)
                              {
                                String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6014"); 
                                if(respuesta.equals("encontrado"))
                                 autorizado = ""+usuarioId;
                                else
                                {
                                  if(!abreSocketAS()){
                                   new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                                   return;
                                    }
                                  if(!validarDatosSupervisor("6014"))
                                    return;  
                                  else
                                      autorizacion = true;
                                }
                              }
                              else
                                return;
                            modificarOcupacion= false;  
                        }
                    modificarOcupacion= false;    
            }
                System.out.println("Busca la ocupacion de la corrida: "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
               //Vector ocupacionSistema = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistema(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                Vector ocupacionSistema = new Vector();
                    if(remoto){
                       Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                       ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistemaRemoto(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString(),tersel.get(1).toString());
                    }                
                    else
                        ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistema(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                System.out.println("la ocupacion es: "+ocupacionSistema);
                Vector datosTarjeta =new Vector();
                Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString());
                String nombreoperador  = "";
                if(vnombre.size()==0)
                {
                 new jdlg_advertencia("¡El operador no se encontro registrado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                 jtbl_corridas.requestFocus();
                 return;
                }
                else
                   { 
                       Vector nombre = (Vector) vnombre.get(0);
//                       if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)                                        
//                          nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
//                        else
//                         nombreoperador = nombre.get(0)+ " " + nombre.get(2);
                      nombreoperador = nombre.get(0).toString();
                    
                    }
                nombreoperador= nombreoperador.replace(',', ' ');
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),3).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString());
                datosTarjeta.add(nombreoperador);
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),7).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),8).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),9).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),10).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),11).toString());
                Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                 
                 datosTarjeta.add(corrida.get(0).toString());
                 datosTarjeta.add(autorizado);//usuarioId);
                 datosTarjeta.add(sesionId);
                 datosTarjeta.add(autorizado2);
                 int index = empresas.indexOf(corrida.get(9).toString()); 
                 if(index<0)
                   datosTarjeta.add("Autobuses México Puebla Estrella Roja S.A. de C.V.");
                 else
                 {
                     String[] parem =(String[]) paramEmpresas.get(index);
                     datosTarjeta.add(parem[0]);
                 }
                 if(remoto){
                   Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                    new jdlg_actualizaOcupacion(ltiposPasajero,ocupacionSistema, datosTarjeta,busquedas,capacidad,autorizacion,autorizacion2,ntarpen,remoto, tersel.get(1).toString(), puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);
                 }
                 else
                    new jdlg_actualizaOcupacion(ltiposPasajero,ocupacionSistema, datosTarjeta,busquedas,capacidad,autorizacion,autorizacion2,ntarpen,remoto,"", puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);
                //new jdlg_informacion("¡La informacion de la ocupacion se a actualizado correctamente!","","Ocuapcion Actualizada").setVisible(true);
               buscarCorridas(claveActual,autobusActual,servicioActual);
            modificarOcupacion= false;
            }
        }

        if(evt.getKeyCode() == evt.VK_UP)
        {
            jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Despachar Tarjeta | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior  | <font color=FF0000>F3</font> Asignar Operador/Autobus </html>");
            if(jtbl_corridas.getSelectedRow()>=0)
            {
                Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                plantillaId = Long.valueOf(corrida.get(8).toString());
                ConsultaOcupacion();
                verificarCambiarOcupacion();
            } 
        }

        if(evt.getKeyCode() == evt.VK_DOWN)
        {
            jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Despachar Tarjeta | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior  | <font color=FF0000>F3</font> Asignar Operador/Autobus </html>");
            if(jtbl_corridas.getSelectedRow()<=(jtbl_corridas.getRowCount()-1) && jtbl_corridas.getSelectedRow()!=-1)
            {
                Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                plantillaId = Long.valueOf(corrida.get(8).toString());
                ConsultaOcupacion();
                verificarCambiarOcupacion();
            } 
        } 
                 
        if(evt.getKeyCode() == evt.VK_F3)
        {
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
        String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6023"); 
        if(respuesta.equals("encontrado"))
        {
             String operador = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 6).toString().trim();
             String autobus = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 7).toString().trim();
             System.out.println("Operador y asignad: "+operador +"  Autobus ya asignado: "+autobus);
             String fecha = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),8).toString()+" "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),9).toString();
             jdlg_Asignar_Op_Bus  asap = new jdlg_Asignar_Op_Bus(busquedas, this.ipAS, this.portAS, nombreEmpresas, ocupacionSusutituir,fecha);
             Vector corridaAsig = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
             asap.setverEmpresa(corridaAsig.get(9).toString());
             if(!operador.equals("No Asignado"))
                 asap.setVerOperador(operador,corridaAsig.get(9).toString() );
             if(!autobus.equals("No Asignado"))
                 asap.setVerAutobus(autobus,corridaAsig.get(9).toString());
             asap.setVisible(true);
             System.out.println("Corrida a Asignar: "+corridaAsig);
             if(asap.isAsignado())
             {
                 System.out.println("Operador: "+asap.getOperadorId()+", "+asap.getOperadorCalve());
                 System.out.println("Autobus: "+asap.getBusId()+", "+asap.getBusNumero());
                    Vector  NESP = new Vector();
                     String nombreEsquema="";
                        if(remoto)
                        {
                            Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                            NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquemaRemoto(tersel.get(1).toString());
                            nombreEsquema = NESP.get(0).toString();
                            System.out.println("Compara: "+corridaAsig.get(0).toString()+".equals("+asap.getEmpresa()+")");
                            //Vector vocupfun = (Vector)busquedas.variosFacadeRemote.consultaOcupacionPorCorrida(Long.valueOf(tarjeta.get(17).toString()));
                            //Long.valueOf(vocupfun.get(0).toString());

                            if(corridaAsig.get(9).toString().equals(asap.getEmpresa()))
                            {
                                System.out.println("si es igual la empresa");
                                
                                 int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacionRemoto(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, usuarioId,tersel.get(1).toString(),"",(long)-1);                        
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                            }
                            else
                            {
                                Vector vvServId = (Vector)busquedas.facadeGeneralPuertasRemote.buscaIdServicio(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
                                if(vvServId.size()==0)
                                {
                                    new jdlg_error("¡El servicio "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString()+" no esta configurado! ","Favor de contactar al administrador","").setVisible(true);
                                     return;
                                }
                                Vector vservId = (Vector)vvServId.get(0);
                                long servId = Long.valueOf(vservId.get(0).toString());
                                int index = nombreEmpresas.indexOf(asap.getEmpresa());
                                long idEmp = Long.valueOf(idNombreEmpresas.get(index).toString());
                                long idRuta = Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),12).toString());//Long.valueOf(corridaAsig.get(11).toString());
                                System.out.println("Busca con el Servicio: "+servId+"  Empresa: "+idEmp+"  Ruta"+ idRuta); 
                                Vector vvServEmpre = (Vector)busquedas.facadeGeneralPuertasRemote.buscaServicioEmpresas(servId,idEmp, idRuta);
                                if(vvServEmpre.size()==0)
                                {
                                     new jdlg_error("¡La empresa "+asap.getEmpresa()+" no puede realizar la ruta "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),3).toString()+"-"+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString()+" del servicio "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString()+" ! ","","").setVisible(true);
                                     return;
                                }
                                corridaAsig.set(9,asap.getEmpresa());
                                lcorridas.set(jtbl_corridas.getSelectedRow(),(Vector)corridaAsig);
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacionRemoto(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, usuarioId,tersel.get(1).toString(),asap.getEmpresa(),idEmp);
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                                jtbl_corridas.setValueAt(asap.getEmpresa(),jtbl_corridas.getSelectedRow(),2);
                            }                        
                            jtbl_corridas.setValueAt(asap.getOperadorCalve(),jtbl_corridas.getSelectedRow(), 6);
                            jtbl_corridas.setValueAt(asap.getBusNumero(),jtbl_corridas.getSelectedRow(), 7);
                        }
                       else
                         {
                            NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquema();
                            nombreEsquema = NESP.get(0).toString();
                            System.out.println("Compara: "+corridaAsig.get(0).toString()+".equals("+asap.getEmpresa()+")");
                            if(corridaAsig.get(9).toString().equals(asap.getEmpresa()))
                            {
                                System.out.println("si es igual la empresa");
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacion(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, usuarioId,"",(long)-1 );
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                            }
                            else
                            {
                               System.out.println("no es igual la empresa");
                                Vector vvServId = (Vector)busquedas.facadeGeneralPuertasRemote.buscaIdServicio(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
                                if(vvServId.size()==0)
                                {
                                    new jdlg_error("¡El servicio "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString()+" no esta configurado! ","Favor de contactar al administrador","").setVisible(true);
                                     return;
                                }
                                Vector vservId = (Vector)vvServId.get(0);
                                long servId = Long.valueOf(vservId.get(0).toString());
                                int index = nombreEmpresas.indexOf(asap.getEmpresa());
                                long idEmp = Long.valueOf(idNombreEmpresas.get(index).toString());
                                long idRuta = Long.valueOf(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),12).toString());//Long.valueOf(corridaAsig.get(11).toString());
                                System.out.println("Busca con el Servicio: "+servId+"  Empresa: "+idEmp+"  Ruta: "+ idRuta); 
                                Vector vvServEmpre = (Vector)busquedas.facadeGeneralPuertasRemote.buscaServicioEmpresas(servId,idEmp, idRuta);
                                if(vvServEmpre.size()==0)
                                {
                                     new jdlg_error("¡La empresa "+asap.getEmpresa()+" no puede realizar la ruta "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),3).toString()+"-"+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString()+" del servicio "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString()+" ! ","","").setVisible(true);
                                     return;
                                }
                                corridaAsig.set(9,asap.getEmpresa());
                                lcorridas.set(jtbl_corridas.getSelectedRow(),(Vector)corridaAsig);
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacion(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, usuarioId, asap.getEmpresa(), idEmp );
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                                jtbl_corridas.setValueAt(asap.getEmpresa(),jtbl_corridas.getSelectedRow(),2);
                            }
                            jtbl_corridas.setValueAt(asap.getOperadorCalve(),jtbl_corridas.getSelectedRow(), 6);
                            jtbl_corridas.setValueAt(asap.getBusNumero(),jtbl_corridas.getSelectedRow(), 7);
                        }
             }        
        }//if(encontrado)   
        else
        {
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
          if(validarDatosSupervisor("6023"))
          { 
             String operador = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 6).toString().trim();
             String autobus = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 7).toString().trim();
             String fecha = jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),8).toString()+" "+jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),9).toString();
             jdlg_Asignar_Op_Bus  asap = new jdlg_Asignar_Op_Bus(busquedas, this.ipAS, this.portAS, nombreEmpresas,ocupacionSusutituir,fecha);
             Vector corridaAsig = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
             asap.setverEmpresa(corridaAsig.get(9).toString());
             if(!operador.equals("No Asignado"))
                 asap.setVerOperador(operador, corridaAsig.get(9).toString());
             if(!autobus.equals("No Asignado"))
                 asap.setVerAutobus(autobus, corridaAsig.get(9).toString());
             asap.setVisible(true);
             System.out.println("Corrida a Asignar: "+corridaAsig);
             if(asap.isAsignado()) 
             {
                 System.out.println("Operador: "+asap.getOperadorId()+", "+asap.getOperadorCalve());
                 System.out.println("Autobus: "+asap.getBusId()+", "+asap.getBusNumero());
                    Vector  NESP = new Vector();
                     String nombreEsquema="";
                        if(remoto)
                        {
                            Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                            NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquemaRemoto(tersel.get(1).toString());
                            nombreEsquema = NESP.get(0).toString();
                            System.out.println("Compara: "+corridaAsig.get(0).toString()+".equals("+asap.getEmpresa()+")");
                            if(corridaAsig.get(9).toString().equals(asap.getEmpresa()))
                            {
                                System.out.println("si es igual la empresa");
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacionRemoto(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, Long.valueOf(autorizado),tersel.get(1).toString(),"",(long)-1);                        
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                            }
                            else
                            {
                                int index = nombreEmpresas.indexOf(asap.getEmpresa());
                                long idEmp = Long.valueOf(idNombreEmpresas.get(index).toString());
                                corridaAsig.set(9,asap.getEmpresa());
                                lcorridas.set(jtbl_corridas.getSelectedRow(),(Vector)corridaAsig);
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacionRemoto(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, Long.valueOf(autorizado),tersel.get(1).toString(), asap.getEmpresa(), idEmp);
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                            }                        
                            jtbl_corridas.setValueAt(asap.getOperadorCalve(),jtbl_corridas.getSelectedRow(), 6);
                            jtbl_corridas.setValueAt(asap.getBusNumero(),jtbl_corridas.getSelectedRow(), 7);
                        }
                       else
                         {
                            NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquema();
                            nombreEsquema = NESP.get(0).toString();
                            System.out.println("Compara: "+corridaAsig.get(0).toString()+".equals("+asap.getEmpresa()+")");
                            if(corridaAsig.get(9).toString().equals(asap.getEmpresa()))
                            {
                                System.out.println("si es igual la empresa");
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacion(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, Long.valueOf(autorizado),"",(long)-1 );
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                            }
                            else
                            {
                                int index = nombreEmpresas.indexOf(asap.getEmpresa());
                                long idEmp = Long.valueOf(idNombreEmpresas.get(index).toString());
                                corridaAsig.set(9,asap.getEmpresa());
                                lcorridas.set(jtbl_corridas.getSelectedRow(),(Vector)corridaAsig);
                                int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorridasAsignacion(Long.valueOf(corridaAsig.get(0).toString()),asap.getBusId(), asap.getBusNumero(),asap.getOperadorCalve(),asap.getOperadorId(), nombreEsquema, Long.valueOf(autorizado),asap.getEmpresa(), idEmp );
                                 if(valor==1)
                                 {
                                     new jdlg_error("¡La corrida esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                                     return;
                                 }
                                
                            }
                            jtbl_corridas.setValueAt(asap.getOperadorCalve(),jtbl_corridas.getSelectedRow(), 6);
                            jtbl_corridas.setValueAt(asap.getBusNumero(),jtbl_corridas.getSelectedRow(), 7);
                        }
             }                
          }
          
        }//else(encontrado)

        }
            
    }                                         

    private void jtbl_corridasFocusLost(java.awt.event.FocusEvent evt) {                                        
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    }                                       

    private void jtbl_corridasFocusGained(java.awt.event.FocusEvent evt) {                                          
        jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Campos de Busqueda | <font color=FF0000>ENTER</font> Despachar Tarjeta | <font color=FF0000>ABAJO</font> Corrida Siguiente | <font color=FF0000>ARRIBA</font> Corrida Anterior | <font color=FF0000>F3</font> Asignar Operador/Autobus </html>");
    }                                         

    private void jtbl_corridasKeyPressed(java.awt.event.KeyEvent evt) {                                         
        if(evt.getKeyCode() == evt.VK_DOWN || evt.getKeyCode() == evt.VK_UP)
            modificarOcupacion = false;
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
        {
           modeloCorridas.setDataVector(null,encabezadoCorridas);
           resizeColumnasCorridas();
           jtxt_hora_desde.setEnabled(true); 
           jtxt_hora_desde.setText("");
           jtx_fecha.setEnabled(true); 
           jtx_fecha.setText("");
           jtxt_autobus.setEnabled(true);
           jtxt_autobus.setText("");
           jtxt_clave.setEnabled(true);
           jcmb_servicio.setEnabled(true);
            jtxt_clave.setText("");
            jtxt_clave.requestFocus();
        }
        

        if(evt.getKeyCode() == evt.VK_ENTER){
                  if(!abreSocketAS()){
                   new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                   return;
                    }
                ConsultaOcupacion();
                boolean autorizacion = false;
                boolean autorizacion2 = false;
                long ntarpen = 0;
                autorizado = ""+usuarioId;
                autorizado2 = ""+usuarioId;
                
                if(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 6).equals("No Asignado"))
                {
                    new jdlg_error("¡El Operador no esta asignado! ","Favor de asignar un operador a la corrida","Faltan datos").setVisible(true);                    
                    return;
                }
                
                if(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(), 7).equals("No Asignado"))
                {
                    new jdlg_error("¡El Autobus no esta asignado! ","Favor de asignar un Autobus a la corrida","Faltan datos").setVisible(true);                    
                    return;
                }
                //verifica que la corrida no sea de Turismo
                   //if(!jcmb_servicio.getSelectedItem().toString().equals("SERVICIO DE TURISMO"))
                  if(!jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1) .toString().equals("SERVICIO DE TURISMO"))
                   {
                       if(fecha_servidor.getTime()>=fecha_servidor_manana.getTime())
                       {
                           new jdlg_error("¡Solo puedes despachar corridas de hoy!","","No se despachan corridas de dias posteriores").setVisible(true);
                            return;
                       }
                   }
                
                Vector corridaDis = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                Vector vasientos = new Vector();
                long nasientos =0;
                if(remoto){
                       Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                       vasientos = (Vector)busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponiblesRemoto(Long.valueOf(corridaDis.get(0).toString()),tersel.get(1).toString());
                }
                else
                 nasientos =  busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponibles(Long.valueOf(corridaDis.get(0).toString()));
                if(nasientos>0)
                {
                              jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Corrida Bloqueada", "La corrida esta bloqueda, espere unos segundos o ¿Desea autorizar la tarjeta?");
                              psn2.setVisible(true);
                              if(respuestaSN)
                              {
                                String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6015"); 
                                if(respuesta.equals("encontrado"))
                                 autorizado2 = ""+usuarioId;
                                else
                                {
                                  if(!abreSocketAS()){
                                   new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                                   return;
                                    }
                                      if(!validarDatosSupervisor2("6015"))
                                    return;  
                                  else
                                      autorizacion2 = true;
                                }
                              }
                              else
                                return;                
                }
             boolean limitar = true;
             System.out.println("Busca parametro para la empresa: "+corridaDis.get(9).toString());
             int indexl = empresas.indexOf(corridaDis.get(9).toString()); 
             if(indexl<0)
               limitar = false;
             else
             {
                 String[] pareml =(String[]) paramEmpresas.get(indexl);
                 System.out.println("El parametro es: "+pareml[1]);
                 if(pareml[1].equals("N"))
                     limitar = false;
                 if(pareml[1].equals("S"))
                    limitar = true; 
             }
              if(limitar)
              {
                      if(!abreSocketAS()){
                       new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                       return;
                        }
                        ntarpen = busquedas.facadeGeneralPuertasRemote.buscaPermitirCrearTarjeta(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString(),jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString(), DBLinkCentral);
                        System.out.println("Numero de tarjetas pendientes: "+ntarpen);
                        if(ntarpen>=0)
                        {
                          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Tarjetas Pendientes", "El operador tiene  "+ntarpen+"  tarjetas pendientes, ¿Desea autorizar una mas?");
                          psn.setVisible(true);
                          if(respuestaSN)
                          {
                            String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6014"); 
                            if(respuesta.equals("encontrado"))
                             autorizado = ""+usuarioId;
                            else
                            {
                              if(!abreSocketAS()){
                               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                               return;
                                }
                              if(!validarDatosSupervisor("6014"))
                                return;  
                              else
                                  autorizacion = true;
                            }
                          }
                          else
                            return;   
                        }
              }//limitar
                Vector ocupacionSistema = new Vector();
                    if(remoto){
                       Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                       ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistemaRemoto(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString(),tersel.get(1).toString());
                    }                
                    else
                        ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistema(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                System.out.println("(2)la ocupacion es: "+ocupacionSistema);
                Vector datosTarjeta =new Vector();
                Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString());
                Vector nombre;
                String nombreoperador  = "";
                if(vnombre.size()==0)
                {
                 new jdlg_advertencia("¡El operador no se encontro registrado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                 return;
                }
                else
                { 
                    nombre = (Vector) vnombre.get(0);
//                    if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)                                        
//                      nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
//                    else
//                      nombreoperador = nombre.get(0)+ " " + nombre.get(2);
                      nombreoperador = nombre.get(0).toString();
                    
                }                
                nombreoperador= nombreoperador.replace(',', ' ');
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),0).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),1).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),3).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),4).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),6).toString());
                datosTarjeta.add(nombreoperador);
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),7).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),8).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),9).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),10).toString());
                datosTarjeta.add(jtbl_corridas.getValueAt(jtbl_corridas.getSelectedRow(),11).toString());
                Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
                 datosTarjeta.add(corrida.get(0).toString());
                 datosTarjeta.add(autorizado); //usuarioId);
                 datosTarjeta.add(sesionId);
                 datosTarjeta.add(autorizado2);                 
                 int index = empresas.indexOf(corrida.get(9).toString()); 
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
                System.out.println("entra cuando modificarOcupacion true y aproviene=3...");
                 if(remoto){
                   Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                   new  Jdlg_vistaPreviaTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),ocupacionS,3,autorizacion,autorizacion2,ntarpen,remoto, tersel.get(1).toString(),puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales ).setVisible(true);                               
                 }
                 else
                   new  Jdlg_vistaPreviaTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),ocupacionS,3,autorizacion,autorizacion2,ntarpen,remoto,"", puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);              
               modificarOcupacion= false;
             }
             else    
             {
                 if(remoto){
                   Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
                   new  Jdlg_vistaPreviaTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),new Object[5][5],1,autorizacion,autorizacion2,ntarpen,remoto, tersel.get(1).toString(), puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);
                 }
                 else
                     new  Jdlg_vistaPreviaTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),new Object[5][5],1,autorizacion,autorizacion2,ntarpen,remoto,"", puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);
             }              
           buscarCorridas(claveActual,autobusActual,servicioActual);
        }
        
    }                                        

    private void jcmb_servicioFocusLost(java.awt.event.FocusEvent evt) {                                        
    if(remoto)
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    else
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
    
    }                                       

    private void jcmb_servicioFocusGained(java.awt.event.FocusEvent evt) {                                          
      if(remoto)
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Servicios | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");     
      else
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>ABAJO</font> Mostrar Servicios | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");               
    }                                         

    private void jcmb_servicioKeyPressed(java.awt.event.KeyEvent evt) {                                         
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
           jtxt_autobus.requestFocus();
           jtxt_autobus.selectAll();
         }
        
        if(evt.getKeyCode() == evt.VK_RIGHT)
        {
            jtx_fecha.requestFocus();
            jtx_fecha.selectAll();
        }

        if(evt.getKeyCode() == evt.VK_F5)
          if(!remoto)
            remoto();

        if(evt.getKeyCode() == evt.VK_F6)
        {
            if(remoto)
            {
                remoto = false;
                jlbl_terminal.setVisible(false);
                jcmbx_terminales.setVisible(false);
                jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F5</font> Terminal Remota | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F11)
            remprimir();

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
        {
           if(jcmb_servicio.getSelectedIndex() == 0)
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), "%");
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = "%";               
           }
           else
           {
             buscarCorridas(jtxt_clave.getText(), jtxt_autobus.getText(), jcmb_servicio.getSelectedItem().toString());
             claveActual    = jtxt_clave.getText();
             autobusActual  = jtxt_autobus.getText();
             servicioActual = jcmb_servicio.getSelectedItem().toString();               
               
           }   
        }        
    }                                        
    
    public boolean getInicioGral() {
        return inicioGral;
    }

    public void setInicioGral(boolean inicioGral) {
        this.inicioGral = inicioGral;
    }
    
    
    
    /************************* plantillas ***************************/
    private boolean Plantillas(){
        componentes = new ArrayList<TmsComponenteBusTbl>(busquedas.facadeGeneralPuertasRemote.queryTmsComponenteBusTblFindAll());
        encabezados = new ArrayList<TmsAutobusPlantillasEncTbl>(busquedas.facadeGeneralPuertasRemote.queryTmsAutobusPlantillasEncTblFindAll());
        lineas =  new ArrayList<TmsAutobusPlantLineasTbl>(busquedas.facadeGeneralPuertasRemote.queryTmsAutobusPlantLineasTblFindAll());
        
        if(componentes.size()==0 || encabezados.size()==0 || lineas.size()==0) return false;
        PLANTILLA_DEFAULT=encabezados.get(0).getPlantillaEncId();
        return true;
    }
    
    public List<TmsComponenteBusTbl> getComponentes(){
        return componentes;
    }
    
    public TmsAutobusPlantillasEncTbl getEncabezado(long PlantillaId){
        System.out.println("Busca el Id :"+PlantillaId);
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) {
            System.out.println("Si la encuentra...");
            return encabezados.get(i);
            }
        System.out.println("No la encuentra...");
        return null;
    }
    
    public List<TmsAutobusPlantLineasTbl> getLineas(long PlantillaId){
        Vector vLineas = new Vector();
        System.out.println("Busca las lineas con el Id :"+PlantillaId);
        for(int i=0; i<lineas.size(); i++)
            if(lineas.get(i).getPlantillaEncId()==PlantillaId)
              vLineas.addElement(lineas.get(i));
        
        if(vLineas.size()==0) 
        {
            System.out.println("No las encuentra ...");
            return null;
        }
        System.out.println("Si las encuentra y son: "+vLineas.size());
        return new ArrayList<TmsAutobusPlantLineasTbl>(vLineas);
    }
    
    public int getCapacidadPlantilla(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i).getCapacidadAsientos().intValue();
        return 0;
    }
    
   public void ConsultaOcupacion(){
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
       ocupacionSusutituir = 0;       
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
        Vector corrida = (Vector) lcorridas.get(jtbl_corridas.getSelectedRow());
        TmsCorridasVentaTbl corrida2 = null;
        if(!remoto)
        {  
            //corrida2 = busquedas.corridasVentaTblFacadeRemote.actualizarCorrida(corrida);
            //listaAsientos= corrida2.getAsientos();
            String asientos = busquedas.facadeGeneralPuertasRemote.AsientosPorCorrida(Long.valueOf(corrida.get(0).toString()));
            String[] array = asientos.split(",");
            //System.out.println("Cadena:  "+asientos+" tamaño Array: "+array.length);
            for(int i = 0; i<array.length; i++)
               listaAsientos.add(array[i]); //System.out.println("Asientos"+(i+1)+":"+array[i]);//listaAsientos = new Vector();
            
        }
        else
        {
            Vector tersel = (Vector) lisEsquemasTerminales.get(jcmbx_terminales.getSelectedIndex());
            corrida2 = busquedas.facadeGeneralPuertasRemote.buscaCorridaRemota(Long.valueOf(corrida.get(0).toString()),tersel.get(1).toString());
            listaAsientos= corrida2.getAsientos();
        }

        //System.out.println("El vetor de ocupacion es: "+listaAsientos);
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
        ocupacionSusutituir = boletosVendidos.size();
        if (boletosVendidos.size() > 0){
            if(BoletosOcupados.size()!=0){ JPnlbus.setOcupados(BoletosOcupados); }
            if(BoletosReservados.size()!=0){ JPnlbus.setReservados(BoletosReservados); }
            if(BoletosReservadosNC.size()!=0){ JPnlbus.setReservadNC(BoletosReservadosNC); }
        }
    }    
  
   private void remoto() {
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
        String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6019"); 
        if(respuesta.equals("encontrado"))
        {
            remoto = true;
            jlbl_terminal.setVisible(true);
            jcmbx_terminales.setVisible(true);
            lisEsquemasTerminales =  busquedas.facadeGeneralPuertasRemote.buscaEsquemasTerminales("N");
            jcmbx_terminales.removeAllItems();
            for(int i = 0; i<lisEsquemasTerminales.size(); i++)
            {
              Vector v = (Vector) lisEsquemasTerminales.get(i);
                System.out.println("Terminal"+i+" = "+v.get(0));
//                if(lisEsquemasTerminales.get(i).getNombreTerminal().equals("CENTRAL"))
//                 lisEsquemasTerminales.remove(i);
//                else
                 jcmbx_terminales.addItem(v.get(0).toString());
            }
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
        }   
        else
        {
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
          if(validarDatosSupervisor("6019"))
          {
            remoto = true;
            jlbl_terminal.setVisible(true);
            jcmbx_terminales.setVisible(true);
            lisEsquemasTerminales =  (Vector) busquedas.facadeGeneralPuertasRemote.buscaEsquemasTerminales("N");
            jcmbx_terminales.removeAllItems();
            for(int i = 0; i<lisEsquemasTerminales.size(); i++)
            {
                Vector v = (Vector) lisEsquemasTerminales.get(i);
                System.out.println("Terminal"+i+" = "+v.get(0));
////               if(lisEsquemasTerminales.get(i).getNombreTerminal().equals("CENTRAL"))
////                 lisEsquemasTerminales.remove(i);
//                else
                 jcmbx_terminales.addItem(v.get(0).toString());
            }
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Puertas | <font color=FF0000>   F6</font> Terminal Local | <font color=FF0000>ENTER</font> Busca Corridas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Reimprimir Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");             
          }
          
        }   
       
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
     * 
     * 
     * @param args the command line arguments
     *    
     *    public static void main(String args[]) {
     *        java.awt.EventQueue.invokeLater(new Runnable() {
     *            public void run() {
     *                new JIFPuertas0().setVisible(true);
     *            }
     *        });
     *    }
     */
    // Variables declaration - do not modify                     
    private javax.swing.JPanel JPnlAutobus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_servicio;
    private javax.swing.JComboBox jcmbx_terminales;
    private javax.swing.JLabel jlbl_barraEstado;
    //private javax.swing.JLabel jlbl_reloj;
    private RelojVisual jlbl_reloj;
    private javax.swing.JLabel jlbl_terminal;
    private javax.swing.JTable jtbl_corridas;
    private tms_TextFields.JTextTextField jtxt_autobus;
    private tms_TextFields.JTextTextField jtxt_clave;
    private tms_TextFields.JHourTextField jtxt_hora_desde;
    // End of variables declaration                   
    // plantillas
    private Vector codigos = new Vector();
    private Vector valores = new Vector();
    private Vector ltiposPasajero;
    private Vector tiposPasajero = new Vector();
    private Vector letraTiposPasajero = new Vector();
    private Vector indiceCorridas = new Vector();
    private Vector ocupacionCorridas = new Vector();
    private Vector lcorridas;
    private List<TmsComponenteBusTbl> componentes;
    private List<TmsAutobusPlantillasEncTbl> encabezados;
    private List<TmsAutobusPlantLineasTbl> lineas;
    private JPnl_BusX JPnlbus;
    private Vector boletosVendidos = new Vector();
    Vector BoletosOcupados      = new Vector();
    Vector BoletosReservados    = new Vector();
    Vector BoletosReservadosNC  = new Vector();
    private Object[] encabezadoCorridas = {"Clave Corrida","Servicio","Empresa","Origen","Destino","T","Operador","Autobus","Fecha","Hora","RutaNumero","RutaNombre","RutaId"};
    private DefaultTableModel modeloCorridas= new DefaultTableModel(null,encabezadoCorridas){
    public boolean isCellEditable (int row, int column){if (column == 20)return true;return false;}};
    private TmsPuertasManagedBean busquedas = new TmsPuertasManagedBean();
    private boolean respuestaSN = false;
    private Timestamp fecha_servidor_manana = null;
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
    private Thread hilo;
    private jdlgDatosSupervisor dlgSupervisor;
    private String autorizado = "";
    private String autorizado2 = "";
    private boolean remoto = false;
    private Vector lisEsquemasTerminales; 
    private long PLANTILLA_DEFAULT;
    private JLabel jLabel6;
    private JDateTextField jtx_fecha;
    private String DBLinkCentral;
    private tmscargaimpresorasPuertas cargaimpresoras;
    private Vector puertos = null;
    private Vector impresoras = null;
    private PrintService impresora;
    private String puerto;
    private Vector empresas = new Vector();
    private Vector idNombreEmpresas = new Vector();
    private Vector nombreEmpresas = new Vector();
    private Vector paramEmpresas = new Vector();
    private long edotar = 1;
    private long ocupacionSusutituir;
    private Vector vEdosAcc;
    private String ipAS;
    private int portAS;
    private boolean inicioGral = true;

    
 public class actualizaCorridasThread extends  Thread {
  public void run() {
    while(true) {
     System.out.println ("Actualiza la consulta");        
      buscarCorridas(claveActual,autobusActual,servicioActual);//System.out.print("ping ");
  try {
    Thread.sleep(60000);
   }
   catch (InterruptedException e) {
  }
      
    }
  }
}    
    
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
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
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
        jlbl_barraEstado.setForeground(new java.awt.Color(0, 0, 0));
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
        if(column == 7)
            setHorizontalAlignment(JTextField.CENTER);
        else
            setHorizontalAlignment(JTextField.LEFT);
        return cell;
    }}

    private KeyEvent eventoTeclado;

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }


////// verifica si hay conexion con la BD ///////
public boolean abreSocketAS(){
        Socket s = null;
        try {
            s = new Socket(this.ipAS, this.portAS);
            return true;
        }catch( IOException e ) {
            return false;
        }catch(Exception err){
            return false;
   }
}




 
         
}