/*
 * JIFrameActividadesAdicionales.java
 *
 * Created on 1 de octubre de 2007, 07:23 PM
 */

package tmsactividadesadicionales;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import tms_encriptacion.EncriptarMD5;
import tmsactividadesadicionales.entidad.TmsActAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsActDatosAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsAuditoriaTbl;
import tmsactividadesadicionales.entidad.TmsEmpresasTbl;
import tmsactividadesadicionales.entidad.TmsOperadoresTbl;
import tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsServiciosTbl;
import tmsactividadesadicionales.excepciones.UsuarioNoEncontradoException;

/**
 *
 * @author  vgonzalez
 */ 
public class JIFrameActividadesAdicionales extends javax.swing.JInternalFrame{//javax.swing.JFrame {//
    private KeyEvent eventoTeclado;
    
    /** Creates new form JIFrameActividadesAdicionales */
    public JIFrameActividadesAdicionales(Vector pDatosIniciales) {
        this.datosIniciales = pDatosIniciales;
        System.out.println("Vector el de Datos iniciales es: "+datosIniciales);
        this.setMenuId(datosIniciales.get(4).toString());
        this.setUsuarioId(datosIniciales.get(0).toString());
        this.nombre_recaudador = datosIniciales.get(2).toString();
        this.setSesionId(datosIniciales.get(3).toString());
        this.cargarValores();
        initComponents();
        f10_Enable();
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jcmb_actividad.setFocusTraversalKeysEnabled(false);
        jcmb_servicio.setFocusTraversalKeysEnabled(false);
        jtxt_monto.setFocusTraversalKeysEnabled(false);
        jtbl_datosAdicionales.setFocusTraversalKeysEnabled(false);
        jtbl_datosAdicionales.setSurrendersFocusOnKeystroke(true);
        
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        formatos   = cargaimpresoras.getFormatos();
        impresoras = cargaimpresoras.getImpresoras();
        nombre_equipo = cargaimpresoras.getNombre_equipo();
        puertos = cargaimpresoras.getPuertos();
        Vector vparamEmpresas = (Vector)busquedas.variosFacadeRemote.buscaParametrosPorEmpresa();
        Vector vparamServicios = (Vector)busquedas.variosFacadeRemote.buscaParametrosPorServicio();
        Vector TER = (Vector)busquedas.variosFacadeRemote.queryBuscaTerminalId();
         setIdterminal(Long.valueOf(TER.get(0).toString()));
         this.setTitle("TMSRecauda Rev29.08.08");
        codigos = new Vector();
        valores = new Vector();
        if(vparamEmpresas.size()>0)
        {
            for(int i=0; i<vparamEmpresas.size(); i++)
            {
                Vector pe = (Vector)vparamEmpresas.get(i);
                String cod = pe.get(0).toString()+"-"+pe.get(1).toString();
                String val = pe.get(2).toString();
                codigos.add(cod);
                valores.add(val);
            }
        }
        if(vparamServicios.size()>0)
        {
            for(int i=0; i<vparamServicios.size(); i++)
            {
                Vector ps = (Vector)vparamServicios.get(i);
                codigos.add(ps.get(0).toString()+"-"+ps.get(1).toString());
                valores.add(ps.get(2).toString());
            }
        }
        System.out.println("Parametros: "+codigos);
        System.out.println("Valores:    "+valores);
        jtbl_datosAdicionales.setModel(modeloDatAdi);
        listaServicios   = (List<TmsServiciosTbl>)busquedas.serviciosTblFacadeRemote.findAll();
        listaActividades = (List<TmsActAdicionalesTbl>)busquedas.actAdicionalesTblFacadeRemote.findAll();
        System.out.println("Listado de Actividades :    "+listaActividades);
        //jtxt_codigoBarras.setFocusTraversalKeysEnabled(false);
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jcmb_actividad.setFocusTraversalKeysEnabled(false);
        jcmb_servicio.setFocusTraversalKeysEnabled(false);
        jtbl_datosAdicionales.setFocusTraversalKeysEnabled(false);
        jtxt_monto.setFocusTraversalKeysEnabled(false);
        TableColumn columinv = jtbl_datosAdicionales.getColumnModel().getColumn(2); columinv.setMinWidth( 0);columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);          
        jtbl_datosAdicionales.setCellSelectionEnabled(true);
        jtbl_datosAdicionales.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0),"none");
        jtbl_datosAdicionales.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0),"none");
        jtbl_datosAdicionales.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none");
        //this.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10,0),"none");
        jpnl_actividadesAdicionales2.setVisible(true);
        jpnl_actividadesAdicionales.setVisible(false);
        jtxt_clave.requestFocus();
        // jtxt_codigoBarras.requestFocus();
     
        
    }
    
    
    private void f10_Enable() {
        jtxt_clave.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jcmb_actividad.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jcmb_servicio.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jtxt_monto.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jtbl_datosAdicionales.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jlbl_folio.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
    }    
     
    private void actividadesAdicionales(){
         Vector vestado= (Vector)busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
         String estado = vestado.get(0).toString();
         if(estado.equals("CERRADA")){
             new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
             System.exit(0);
         }

        if(jtxt_clave.getText().equals(""))
        {
            new jdlg_error("¡Debes ingresar la clave del operador!  ",""," Campos vacios").setVisible(true);
            jtxt_clave.requestFocus();
        }
        else
        {
          List<TmsOperadoresTbl> listaOperador  = busquedas.operadoresTblFacadeRemote.BuscarOperadorPorClave(jtxt_clave.getText());
           if(listaOperador.size()==0)
           {
              new jdlg_error("¡El operador con clave "+jtxt_clave.getText()+" no esta registrado en la Base de Datos!  ",""," El operador no existe").setVisible(true);
              jtxt_clave.setText("");
              jtxt_clave.requestFocus();
           }
           else
           {
              jpnl_actividadesAdicionales2.setVisible(false);
              jpnl_actividadesAdicionales.setVisible(true);
              TmsOperadoresTbl operador = listaOperador.get(0);
              if(operador.getOperadorNombreMedio()!=null && operador.getOperadorNombreMedio().indexOf("OPERADOR")==-1)
                nombreoperador =  operador.getOperadorNombre() + " "+operador.getOperadorNombreMedio() +" " + operador.getOperadorApellido();
              else
                nombreoperador = operador.getOperadorNombre() + " " + operador.getOperadorApellido();
             aplicaretencion = operador.getAplicaRetencion();
             setIdoperador(operador.getOperadorId().longValue());
             if(operador.getEmpresaId()==null)
             {
              new jdlg_error("¡El operador con clave "+jtxt_clave.getText()+" no tiene una empres asociada!  ",""," Error de configuracion del operador").setVisible(true);
              jtxt_clave.selectAll();
              jtxt_clave.requestFocus();
              return;
             }
             TmsEmpresasTbl empre = busquedas.empresasTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(""+operador.getEmpresaId())));
             empresaNombreCorto = empre.getEmpresaNombreCorto();
             Vector vretencion = (Vector)busquedas.variosFacadeRemote.buscarPorcentajeRetencion(empre.getEmpresaNombreCorto());
             System.out.println("Retencion de "+empre.getEmpresaNombreCorto()+" es del: "+vretencion+"%");
             if(vretencion.size()>0)
                 pretencion = vretencion.get(0).toString();
             jtxt_nombre.setText(nombreoperador);
             jtxt_clave.setEnabled(false);
             //jtxt_codigoBarras.setEnabled(false);
             List<TmsPagosActAdicionalesTbl> listaPreingreesados = busquedas.pagosActAdicionalesTblFacadeRemote.buscaActividaesPreingresadas(operador.getOperadorId().toBigInteger());
             
             if(listaPreingreesados.size()>0)
             {
                hayPrepagados = true;
                jcmb_actividad.setEnabled(false);
                jcmb_servicio.setEnabled(false);
                jtxt_monto.setEnabled(false);
                jtxt_retencion.setEnabled(false);
                jtbl_datosAdicionales.setEnabled(false);
                
                prepagados = new Vector();
                for(int i=0; i<listaPreingreesados.size(); i++)
                {
                    TmsPagosActAdicionalesTbl pago = listaPreingreesados.get(i);
                    System.out.println("El numero de datos adicionales del pago de "+pago.getTipoActividadAdicionalId().getAccionId().getAccion()+" es de "+pago.getTipoActividadAdicionalId().getTmsActDatosAdicionalesTblCollection().size());
                    prepagados.add(listaPreingreesados.get(i));
                }

                 numprepagado = 0;
                 mostrarPagoPrepagado();
             }
             else
                 nuevoPago();
           } 
        }   
    }
   
    private void mostrarPagoPrepagado(){
                jcmb_actividad.setEnabled(false);
                jcmb_servicio.setEnabled(false);
                jtxt_monto.setEnabled(false);
                jtxt_retencion.setEnabled(false);
                jtbl_datosAdicionales.setEnabled(false);
                 TmsPagosActAdicionalesTbl prepagado = (TmsPagosActAdicionalesTbl) prepagados.get(numprepagado);
                 jlbl_folio.setText(prepagado.getReferenciaPagoActAdicional().toString());
                 jcmb_actividad.removeAllItems();
                 jcmb_actividad.addItem(prepagado.getTipoActividadAdicionalId().getAccionId().getAccion());
                 jcmb_servicio.removeAllItems();
                 jcmb_servicio.addItem(prepagado.getServicioId().getServicioNombre());
                 jtxt_monto.setText(prepagado.getMontoPago().toString());
                 jtxt_retencion.setText(prepagado.getRetencion().toString());
                 modeloDatAdi.setDataVector(null,encabezadodatosAdicionales);
                 TableColumn columinv = jtbl_datosAdicionales.getColumnModel().getColumn(2); columinv.setMinWidth( 0);columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
                   Collection<TmsActDatosAdicionalesTbl> coleccion = prepagado.getTipoActividadAdicionalId().getTmsActDatosAdicionalesTblCollection();
                   Iterator ipre = coleccion.iterator();
                   while(ipre.hasNext()){
                       TmsActDatosAdicionalesTbl adicional = (TmsActDatosAdicionalesTbl)ipre.next();
                       if(adicional.getHabilitado().equals("S"))
                       {
                           String[] row = new String[3];
                           row[0] = adicional.getDatoAdicionalId().getDatoNombre();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL1"))row[1] = prepagado.getAdicional1();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL2"))row[1] = prepagado.getAdicional2();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL3"))row[1] = prepagado.getAdicional3();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL4"))row[1] = prepagado.getAdicional4();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL5"))row[1] = prepagado.getAdicional5();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL6"))row[1] = prepagado.getAdicional6();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL7"))row[1] = prepagado.getAdicional7();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL8"))row[1] = prepagado.getAdicional8();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL9"))row[1] = prepagado.getAdicional9();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL10"))row[1] = prepagado.getAdicional10();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL11"))row[1] = prepagado.getAdicional11();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL12"))row[1] = prepagado.getAdicional12();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL13"))row[1] = prepagado.getAdicional13();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL14"))row[1] = prepagado.getAdicional14();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL15"))row[1] = prepagado.getAdicional15();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL16"))row[1] = prepagado.getAdicional16();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL17"))row[1] = prepagado.getAdicional17();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL18"))row[1] = prepagado.getAdicional18();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL19"))row[1] = prepagado.getAdicional19();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL20"))row[1] = prepagado.getAdicional20();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL21"))row[1] = prepagado.getAdicional21();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL22"))row[1] = prepagado.getAdicional22();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL23"))row[1] = prepagado.getAdicional23();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL24"))row[1] = prepagado.getAdicional24();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL25"))row[1] = prepagado.getAdicional25();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL26"))row[1] = prepagado.getAdicional26();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL27"))row[1] = prepagado.getAdicional27();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL28"))row[1] = prepagado.getAdicional28();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL29"))row[1] = prepagado.getAdicional29();
                           row[2] = "";
                           modeloDatAdi.addRow(row);
                        }
                      }
//                TmsActAdicionalesTbl act = prepagado.getTipoActividadAdicionalId();
//                if(act.getActividadEditable().equals("S"))
//                {
//                    jtxt_monto.setEnabled(true);
//                    jtxt_retencion.setEnabled(true);
//                    jtbl_datosAdicionales.setEnabled(true);
//                    jtxt_monto.requestFocus();
//                }
//                else
                  jlbl_folio.requestFocus();  
                //jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>RePag</font> Pago Siguiente | <font color=FF0000>AvPag</font> Pago Anterior | <font color=FF0000>F9</font> Pago Nuevo | <font color=FF0000>F10</font> Pagar </html>");
                pagoPrepagado = true;        
    }
    
    private void siguientePrepagado(){
        if(hayPrepagados)
        {
            if(numprepagado==(prepagados.size() - 1))
                numprepagado= 0;
            else
                numprepagado++;
           mostrarPagoPrepagado();
            
        }
    }
    
    private void anteriorPrepagado(){
        if(hayPrepagados)
        {
            if(numprepagado==0)
                numprepagado= prepagados.size() - 1;
            else
                numprepagado--;
            mostrarPagoPrepagado();
        }
        
    }
    
    private void nuevoPago(){
        if(listaActividades.size()==0)
        {
            new jdlg_error("TMSERRORCNF001.- No hay Actividades configuradas ","Favor de contactar al administrador del sistema","Error de Configuracion").setVisible(true);
            this.dispose();
        }
        if(listaServicios.size()==0)
        {
            new jdlg_error("TMSERRORCNF001.- No hay Servicios configuradas ","Favor de contactar al administrador del sistema","Error de Configuracion").setVisible(true);
            this.dispose();
        }
        jcmb_actividad.setEnabled(true);
        jcmb_servicio.setEnabled(true);
        jtxt_monto.setEnabled(true);
        jtxt_retencion.setEnabled(true);
        jtbl_datosAdicionales.setEnabled(true);
        jcmb_actividad.removeAllItems();
        jcmb_servicio.removeAllItems();
        jtxt_monto.setText("");
        jtxt_retencion.setText("");
        Vector vnfol = (Vector)busquedas.variosFacadeRemote.queryBuscaValorActualPagoActividadesAdicionales(); 
        int nfol = Integer.valueOf(vnfol.get(0).toString());
        jlbl_folio.setText(""+(nfol + 1));
        modeloDatAdi.setDataVector(null,encabezadodatosAdicionales);
        TableColumn columinv = jtbl_datosAdicionales.getColumnModel().getColumn(2); columinv.setMinWidth( 0);columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);          
        for(int i=0; i<listaServicios.size(); i++)
          jcmb_servicio.addItem(listaServicios.get(i).getServicioNombre());
        for(int i=0; i<listaActividades.size(); i++)
          jcmb_actividad.addItem(listaActividades.get(i).getAccionId().getAccion());
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F10</font> Pagar</html>");
        pagoPrepagado = false;
        hayPrepagados = false;
        jcmb_actividad.requestFocus();
    }
    
    public void cargarValores(){
        Vector vFunciones = (Vector)busquedas.variosFacadeRemote.buscarFuncionesPorMenuId(this.getIdmenu());
        System.out.println("vFunciones= "+vFunciones);
        funciones= new Vector();
        fauditables= new Vector();
        for(int i=0; i<vFunciones.size(); i++)
        {
            Vector fun = (Vector)vFunciones.get(i);
            funciones.add(fun.get(0).toString());
            fauditables.add(fun.get(1).toString());
        }
        Vector vFuncionesTodas = (Vector)busquedas.variosFacadeRemote.buscarTodasFunciones();
        System.out.println("vFuncionesTodas= "+vFuncionesTodas);
        funcionesTodas= new Vector();
        fauditablesTodas= new Vector();
        for(int i=0; i<vFuncionesTodas.size(); i++)
        {
            Vector fun = (Vector)vFuncionesTodas.get(i);
            funcionesTodas.add(fun.get(0).toString());
            fauditablesTodas.add(fun.get(1).toString());
        }
    }
    
    private void pagar(){
         Vector vestado= (Vector)busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
         String estado = vestado.get(0).toString();
         if(estado.equals("CERRADA")){
             new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
             System.exit(0);
         }
        if(jtxt_monto.getText().equals(""))
                new jdlg_error("¡Debes ingresar el monto del pago!  ",""," Campos vacios").setVisible(true);
            else
            {
            Vector TER = (Vector)busquedas.variosFacadeRemote.queryBuscaIdTerminal();
            idTerminalsub = TER.get(0).toString();
            String te =  idTerminalsub;
            if( idTerminalsub.length()<3)
            {
               for(int i= idTerminalsub.length(); i<3;i++)
                   te = te+"0";
            }
            if( idTerminalsub.length()>=3)
             te =  idTerminalsub.substring(0,2);
                
             idTerminalsub = te;             
                try{ 
                 if(aplicaretencion.equals("S") || aplicaretencion.equals("s"))
                  {
                    double cantidad = Double.valueOf(""+jtxt_monto.getText());
                    double retencion = roundNum(cantidad*(Double.valueOf("."+pretencion)),1);
                    jtxt_retencion.setText(""+retencion);
                  }
                  else
                   jtxt_retencion.setText("0");
                  } catch (Exception e1) {e1.printStackTrace(); }   
                Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
                fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                vista_pago_impresion v = new vista_pago_impresion();
                v.setVisible(true);
                if(hayPrepagados)
                  actividadesAdicionales();
                else
                  nuevoPago();  
            }
    }
    
    private void salirPago(){
        jpnl_actividadesAdicionales2.setVisible(true);
        jpnl_actividadesAdicionales.setVisible(false);
        jcmb_actividad.removeAllItems();
        jcmb_servicio.removeAllItems();
        jtxt_monto.setText("");
        jtxt_retencion.setText("");
        jtxt_clave.setText("");
        //jtxt_codigoBarras.setText("");
        jtxt_nombre.setText("");
        jtxt_clave.setEnabled(true);
        //jtxt_codigoBarras.setEnabled(true);
        jtxt_nombre.setEnabled(true);
        modeloDatAdi.setDataVector(null,encabezadodatosAdicionales);
        TableColumn columinv = jtbl_datosAdicionales.getColumnModel().getColumn(2); columinv.setMinWidth( 0);columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);          
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Pagos | <font color=FF0000>ENTER</font> Busca Operador | <font color=FF0000>F11</font> Cancelar Pago  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
        jtxt_clave.requestFocus();
    }
    
    private void salirAplicacion(){
       jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir de Pagos de Actividades Adicionales?");
          psn.setVisible(true);
          if(respuestaSN)
              this.dispose();
          else
            return;  

        this.dispose();
        
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
                respuesta = busquedas.usuariosTblFacadeRemote.esUsuarioSupervisor(dlgSupervisor.getNumeroUsuario(),pwdEnc.encriptar(dlgSupervisor.getContrasenaUsuario()),nfuncion);
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
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jtxt_clave = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxt_nombre = new javax.swing.JTextField();
        jpnl_actividadesAdicionales = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlbl_folio = new javax.swing.JLabel();
        jlbl_folio.setFocusTraversalKeysEnabled(false);
        jLabel6 = new javax.swing.JLabel();
        jcmb_actividad = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jcmb_servicio = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jtxt_monto = new tms_TextFields.JCuantityTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxt_retencion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_datosAdicionales = new javax.swing.JTable();
        jtbl_datosAdicionales = new JTable()
        {
            public Component prepareEditor(
                TableCellEditor editor, int row, int column)
            {

                Component c = super.prepareEditor(editor, row, column);
                if (c instanceof JTextComponent)
                {
                    ((JTextField)c).selectAll();
                }
                return c;
            }
        };
        jlbl_barraEstado = new javax.swing.JLabel();
        jpnl_actividadesAdicionales2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Operador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Clave:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Nombre:");

        jtxt_nombre.setEditable(false);
        jtxt_nombre.setFont(new java.awt.Font("Tahoma", 0, 9));
        jtxt_nombre.setFocusable(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_clave, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .add(34, 34, 34)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(19, 19, 19))
        );

        jpnl_actividadesAdicionales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actividades Adicionales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Folio:");

        jlbl_folio.setText("000001");
        jlbl_folio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_folioKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Actividad:");

        jcmb_actividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Actividades" }));
        jcmb_actividad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_actividadFocusGained(evt);
            }
        });
        jcmb_actividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_actividadKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Servicio:");

        jcmb_servicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Servicios" }));
        jcmb_servicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_servicioFocusGained(evt);
            }
        });
        jcmb_servicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_servicioKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Monto:");

        jtxt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_montoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_montoFocusLost(evt);
            }
        });
        jtxt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_montoKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Retención:");

        jtxt_retencion.setEditable(false);
        jtxt_retencion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_retencion.setFocusable(false);

        jtbl_datosAdicionales.setBackground(new java.awt.Color(238, 238, 238));
        jtbl_datosAdicionales.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtbl_datosAdicionales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_datosAdicionales.setGridColor(new java.awt.Color(238, 238, 238));
        jtbl_datosAdicionales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_datosAdicionalesFocusGained(evt);
            }
        });
        jtbl_datosAdicionales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_datosAdicionalesKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_datosAdicionales);

        org.jdesktop.layout.GroupLayout jpnl_actividadesAdicionalesLayout = new org.jdesktop.layout.GroupLayout(jpnl_actividadesAdicionales);
        jpnl_actividadesAdicionales.setLayout(jpnl_actividadesAdicionalesLayout);
        jpnl_actividadesAdicionalesLayout.setHorizontalGroup(
            jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_actividadesAdicionalesLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel7)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel8)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel6)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel9)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel4))
                .add(18, 18, 18)
                .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jtxt_retencion)
                        .add(jcmb_servicio, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jtxt_monto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jcmb_actividad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jlbl_folio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 64, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 397, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnl_actividadesAdicionalesLayout.setVerticalGroup(
            jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_actividadesAdicionalesLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jpnl_actividadesAdicionalesLayout.createSequentialGroup()
                        .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(jlbl_folio))
                        .add(14, 14, 14)
                        .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(jcmb_actividad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(16, 16, 16)
                        .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(24, 24, 24)
                        .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(jtxt_monto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20)
                        .add(jpnl_actividadesAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(jtxt_retencion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_barraEstado.setText("jLabel10");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout jpnl_actividadesAdicionales2Layout = new org.jdesktop.layout.GroupLayout(jpnl_actividadesAdicionales2);
        jpnl_actividadesAdicionales2.setLayout(jpnl_actividadesAdicionales2Layout);
        jpnl_actividadesAdicionales2Layout.setHorizontalGroup(
            jpnl_actividadesAdicionales2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 731, Short.MAX_VALUE)
        );
        jpnl_actividadesAdicionales2Layout.setVerticalGroup(
            jpnl_actividadesAdicionales2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 124, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Rev28.08.08");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jpnl_actividadesAdicionales, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_actividadesAdicionales2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(671, Short.MAX_VALUE)
                .add(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_actividadesAdicionales, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_actividadesAdicionales2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(22, 22, 22)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmb_servicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_servicioFocusGained
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F10</font> Pagar</html>");
    }//GEN-LAST:event_jcmb_servicioFocusGained

    private void jcmb_actividadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_actividadFocusGained
            jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F10</font> Pagar</html>");
    }//GEN-LAST:event_jcmb_actividadFocusGained

    private void jtbl_datosAdicionalesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_datosAdicionalesFocusGained
         jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ABAJO</font> Campo Siguiente | <font color=FF0000>ARRIBA</font> Campo Anterior | <font color=FF0000>F10</font> Pagar</html>");

    }//GEN-LAST:event_jtbl_datosAdicionalesFocusGained

    private void jtxt_montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_montoFocusLost
            jtxt_monto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_jtxt_montoFocusLost

    private void jtxt_claveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusLost
            jtxt_clave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_jtxt_claveFocusLost

    private void jtxt_montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_montoFocusGained
        jtxt_monto.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 88, 236)));
    }//GEN-LAST:event_jtxt_montoFocusGained

    private void jtxt_claveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusGained
        jtxt_clave.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 88, 236)));
           jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Pagos | <font color=FF0000>ENTER</font> Busca Operador | <font color=FF0000>F11</font> Cancelar Pago  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");

    }//GEN-LAST:event_jtxt_claveFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
      jtxt_clave.requestFocusInWindow();
      //jtxt_codigoBarras
    }//GEN-LAST:event_formComponentShown

    private void jtbl_datosAdicionalesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_datosAdicionalesKeyPressed
         if(evt.getKeyCode() == evt.VK_ESCAPE)
             salirPago();
         if(evt.getKeyCode() == evt.VK_DOWN)
         {
             if(jtbl_datosAdicionales.getSelectedRow()== (jtbl_datosAdicionales.getRowCount() - 1))
             {
                 if(jcmb_actividad.isEnabled())
                   jcmb_actividad.requestFocus();
                 else
                 {
                     if(jcmb_servicio.isEnabled())
                       jcmb_servicio.requestFocus();
                     else
                     {
                         if(jtxt_monto.isEnabled())
                             jtxt_monto.requestFocus();
                         else
                         jtbl_datosAdicionales.requestFocus();
                     }
                 }
                 
             }
         }
             
        if(evt.getKeyCode() == evt.VK_F10)
            pagar();
        
        if(evt.getKeyCode() == evt.VK_PAGE_DOWN) 
            siguientePrepagado();
         
        if(evt.getKeyCode() == evt.VK_PAGE_UP) 
            anteriorPrepagado();
         

        

    }//GEN-LAST:event_jtbl_datosAdicionalesKeyPressed

    private void jtxt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_montoKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
             salirPago();
       
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
            if(jcmb_servicio.isEnabled())  
              jcmb_servicio.requestFocus();
            else
            {
              if(jcmb_actividad.isEnabled())  
                jcmb_actividad.requestFocus();
              else
                jtxt_monto.requestFocus();  
            }
        }
            
        if(evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_RIGHT)
        {
            if(jtxt_monto.getText().equals(""))
                ;
            else
            {
                try{ 
                 if(aplicaretencion.equals("S") || aplicaretencion.equals("s"))
                  {
                    double cantidad = Double.valueOf(""+jtxt_monto.getText());
                    double retencion = roundNum(cantidad*(Double.valueOf("."+pretencion)),1);
                    //double total = cantidad-retencion;
                    //double mrop = Double.valueOf(jlbl_retencion.getText());
                    jtxt_retencion.setText(""+retencion);
                  }
                  else
                   jtxt_retencion.setText("0");
                  } catch (Exception e1) {e1.printStackTrace(); }   
            }
            if(jtbl_datosAdicionales.getRowCount()>0 && jtbl_datosAdicionales.isEnabled())
             {
                 jtbl_datosAdicionales.setRowSelectionInterval(0,0);
                 jtbl_datosAdicionales.setColumnSelectionInterval(1,1);
                 jtbl_datosAdicionales.requestFocus();
             }
             else
             {
                if(jcmb_actividad.isEnabled())
                    jcmb_actividad.requestFocus();
                else
                {
                    if(jcmb_servicio.isEnabled())
                        jcmb_servicio.requestFocus();
                    else
                    jtxt_monto.requestFocus();
                }
             }   
        }

        if(evt.getKeyCode() == evt.VK_F10)
            pagar();

        if(evt.getKeyCode() == evt.VK_PAGE_DOWN) 
            siguientePrepagado();
         
        if(evt.getKeyCode() == evt.VK_PAGE_UP) 
            anteriorPrepagado();
        
    }//GEN-LAST:event_jtxt_montoKeyPressed

    private void jcmb_servicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_servicioKeyPressed
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
           if(jcmb_actividad.isEnabled()) 
            jcmb_actividad.requestFocus();
           else
           {
               if(jtxt_monto.isEnabled())
                 jtxt_monto.requestFocus();
               else
                 jcmb_servicio.requestFocus();
           }
        }
        
        if(evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_RIGHT)
        {
            if(jtxt_monto.isEnabled())
              jtxt_monto.requestFocus();
            else
            {
                if(jcmb_actividad.isEnabled())
                  jcmb_actividad.requestFocus();
                else
                {
                    if(jtbl_datosAdicionales.getRowCount()>0 && jtbl_datosAdicionales.isEnabled())
                    {
                        jtbl_datosAdicionales.requestFocus();
                        jtbl_datosAdicionales.setRowSelectionInterval(0,0);
                        jtbl_datosAdicionales.setColumnSelectionInterval(1,1);
                    }
                    else
                    jcmb_servicio.requestFocus();
                }  
            }
        }
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
             salirPago();
    
        if(evt.getKeyCode() == evt.VK_F10)
            pagar();

        if(evt.getKeyCode() == evt.VK_PAGE_DOWN) 
            siguientePrepagado();
         
        if(evt.getKeyCode() == evt.VK_PAGE_UP) 
            anteriorPrepagado();
        
    }//GEN-LAST:event_jcmb_servicioKeyPressed

    private void jcmb_actividadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_actividadKeyPressed
        if(evt.getKeyCode() == evt.VK_LEFT)
        {
           if(jtxt_monto.isEnabled())
             jtxt_monto.requestFocus();
           else
           {
               if(jcmb_servicio.isEnabled())
                 jcmb_servicio.requestFocus();
               else
                 jcmb_actividad.requestFocus();     
           }
        }
        
        if(evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_RIGHT)
        { 
           modeloDatAdi.setDataVector(null,encabezadodatosAdicionales);
           TableColumn columinv = jtbl_datosAdicionales.getColumnModel().getColumn(2); columinv.setMinWidth( 0);columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);          
           int indice = jcmb_actividad.getSelectedIndex();
           System.out.println("El indice de lo seleccionado es: "+indice);
           TmsActAdicionalesTbl actividad = listaActividades.get(indice);
           Collection<TmsActDatosAdicionalesTbl> coleccion = actividad.getTmsActDatosAdicionalesTblCollection();
           Iterator ipre = coleccion.iterator();
           while(ipre.hasNext()){
               TmsActDatosAdicionalesTbl relacion = (TmsActDatosAdicionalesTbl)ipre.next();
               if(relacion.getHabilitado().toUpperCase().equals("S"))
               {
                   String[] row = new String[3];
                   row[0] = relacion.getDatoAdicionalId().getDatoNombre();
                   row[1] = "";
                   row[2] = relacion.getGuardarValorEn();   
                   modeloDatAdi.addRow(row);
               }
              }
           boolean param = false; 
           if(actividad.getCodigoParametro()!=null)
            {
               int index = codigos.indexOf(actividad.getCodigoParametro()+"-"+empresaNombreCorto);
               if(index>=0)
               {    
                    jtxt_monto.setText(valores.get(index).toString());
                    param = true;
                    try{ 
                     if(aplicaretencion.equals("S") || aplicaretencion.equals("s"))
                      {
                        double cantidad = Double.valueOf(""+jtxt_monto.getText());
                        double retencion = roundNum(cantidad*(Double.valueOf("."+pretencion)),1);
                        jtxt_retencion.setText(""+retencion);
                      }
                      else
                       jtxt_retencion.setText("0");
                      } catch (Exception e1) {e1.printStackTrace(); }   
               }    
               else
               {
                   index = codigos.indexOf(actividad.getCodigoParametro()+"-"+jcmb_servicio.getSelectedItem().toString());
                   if(index>=0)
                   {    
                        jtxt_monto.setText(valores.get(index).toString());
                        param = true;
                        try{ 
                         if(aplicaretencion.equals("S") || aplicaretencion.equals("s"))
                          {
                            double cantidad = Double.valueOf(""+jtxt_monto.getText());
                            double retencion = roundNum(cantidad*(Double.valueOf("."+pretencion)),1);
                            jtxt_retencion.setText(""+retencion);
                          }
                          else
                           jtxt_retencion.setText("0");
                          } catch (Exception e1) {e1.printStackTrace(); }   
                   }
                   else
                   {
                       new jdlg_advertencia("¡El parametro "+actividad.getCodigoParametro()+" esta mal configurado  "," Favor de contactar al Administrador del sistema!","Error de configuracioon de Parametro").setVisible(true);
                       jtxt_monto.setEnabled(true);
                   }
               }    
            }
System.out.println("Es editable: "+actividad.getActividadEditable());
System.out.println("Encotro parametro: "+param);
           if(actividad.getActividadEditable().equals("N") &&  param)
           {
               if(jtbl_datosAdicionales.getRowCount()==0)
                 jtbl_datosAdicionales.setEnabled(false); 
               else
                 jtbl_datosAdicionales.setEnabled(true);   
               jtxt_monto.setEnabled(false);
               jtxt_retencion.setEnabled(false);
               jcmb_actividad.setEnabled(false);
               jcmb_servicio.setEnabled(true);
               jcmb_servicio.requestFocus();
           }
           if(jcmb_servicio.isEnabled())
            jcmb_servicio.requestFocus();
           else
           {
               if(jtxt_monto.isEnabled())
                   jtxt_monto.requestFocus();
               else
               {
                if(jtbl_datosAdicionales.isEnabled())
                    jtbl_datosAdicionales.requestFocus();
                else
                    jcmb_actividad.requestFocus();
               }
           
           }
        }       
        if(evt.getKeyCode() == evt.VK_ESCAPE)
             salirPago();
        
        if(evt.getKeyCode() == evt.VK_F10)
            pagar();

        if(evt.getKeyCode() == evt.VK_PAGE_DOWN) 
            siguientePrepagado();
         
        if(evt.getKeyCode() == evt.VK_PAGE_UP) 
            anteriorPrepagado();
        
    }//GEN-LAST:event_jcmb_actividadKeyPressed

    private void jlbl_folioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_folioKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            salirPago();        
        
        if(evt.getKeyCode() == evt.VK_F9)
            nuevoPago();

        if(evt.getKeyCode() == evt.VK_F10)
            pagar();

        if(evt.getKeyCode() == evt.VK_PAGE_DOWN) 
            siguientePrepagado();
         
        if(evt.getKeyCode() == evt.VK_PAGE_UP) 
            anteriorPrepagado();

    }//GEN-LAST:event_jlbl_folioKeyPressed

    private void jtxt_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_claveKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            actividadesAdicionales();

        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();
         
        if(evt.getKeyCode() == evt.VK_F11)
          {
             Vector vestado= (Vector)busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
             String estado = vestado.get(0).toString();
             if(estado.equals("CERRADA")){
                 new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
                 System.exit(0);
             }

           int indexfun = funciones.indexOf("6004");
           if(indexfun>=0)
             new Jdlg_cancelarPago("Cancelar Pago de Activida Adicional", busquedas, funcionesTodas, fauditablesTodas, getUsuarioId(), getNombre_equipo(), getIdterminal(),getSesionId()).setVisible(true);        
           else
           {
               if(validarDatosSupervisor("6004"))
                 new Jdlg_cancelarPago("Cancelar Pago de Activida Adicional", busquedas, funcionesTodas, fauditablesTodas, getUsuarioId(), getNombre_equipo(),Long.valueOf(autorizado),getSesionId()).setVisible(true);        
           }
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

    }//GEN-LAST:event_jtxt_claveKeyPressed
    

 public void imprimir_recibo(String nref){
//        imprimir_recibo_capacitacion imp = new imprimir_recibo_capacitacion(nref);
//        if(formatos.indexOf("TICKETS")>=0)
//            imp.ImprimeDatos();
//        else
//        {
//            new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso"," El numero de referencia del pago es: "+nref,"Impresora no encontrada").setVisible(true);
//            return;
//        }
//        Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante",nref);
//        p.setVisible(true);
//        if(reimpresion)
//            imprimir_recibo(nref);
        int index = formatos.indexOf("TICKETS");
        if(index>=0)
        {
            String puerto = puertos.get(index).toString();
               if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
               {
                 if(!imprimir_recibo_capacitacion_LPT(nref,puerto))
                     new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!","","Error de impresión").setVisible(true);
               }
               if(puerto.equals("RED") || puerto.equals("USB"))
               {
                 int indeximp =  formatos.indexOf("TICKETS");
                 PrintService impresora = (PrintService)impresoras.get(indeximp);
                 {
                     if(!imprimir_recibo_capacitacion_LPT(nref,impresora.getName()))
                         new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!","","Error de impresión").setVisible(true);
                 }
                 //imprimir_recibo_rec(datos,(datos[0]));
               }
        }
        else
        {
            new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no será impreso"," El numero de referencia del pago es: "+nref,"Impresora no encontrada").setVisible(true);
            return;
        }
        Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante",nref);
        p.setVisible(true);
        if(reimpresion)
            imprimir_recibo(nref);     
     
    }    
    
public static double roundNum(double num, int ndec) throws Exception{
    double valor = 0;

    valor = num;

    valor = valor*ndec;
    valor = Math.round(valor);
    valor = valor/ndec;

    return valor;

    }    

    public void setUsuarioId(String id) {
        this.usuarioid = Long.valueOf(id);
        /*Vector vusuario = null;
        try {
            vusuario = (Vector)busquedas.variosFacadeRemote.buscarUsuarioPorId(usuarioid);
        } catch (UsuarioNoEncontradoException ex) {
            ex.printStackTrace();
        }
        if(vusuario==null){
            new jdlg_error("TMSERRORCNF001.- No se encuentra el usuario dado de alta en la Base de datos. ","Favor de contactar al administrador de la aplicación.","Error de configuracion").setVisible(true);
            System.exit(0);

        }*/
    }

    public long getUsuarioId(){
      return   usuarioid;
    }

    public long getIdmenu() {
        return idmenu;
    }

    public void setMenuId(String idmenu) {
        this.idmenu = Long.valueOf(idmenu);
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_actividad;
    private javax.swing.JComboBox jcmb_servicio;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_folio;
    private javax.swing.JPanel jpnl_actividadesAdicionales;
    private javax.swing.JPanel jpnl_actividadesAdicionales2;
    private javax.swing.JTable jtbl_datosAdicionales;
    private javax.swing.JTextField jtxt_clave;
    private tms_TextFields.JCuantityTextField jtxt_monto;
    private javax.swing.JTextField jtxt_nombre;
    private javax.swing.JTextField jtxt_retencion;
    // End of variables declaration//GEN-END:variables
    private Vector datosIniciales;
    private Vector prepagados;
    private int numprepagado;
    private List<TmsServiciosTbl> listaServicios = null;
    private List<TmsActAdicionalesTbl> listaActividades = null;
    private TmsActAdicionalesManagedBean busquedas = new TmsActAdicionalesManagedBean();
    private String nombreoperador = "";
    private String aplicaretencion = "N";
    private String pretencion = "15";
    private Object[] encabezadodatosAdicionales = {"Dato Adicional","Valor","Adicional"};
    private DefaultTableModel modeloDatAdi= new DefaultTableModel(null,encabezadodatosAdicionales){
     public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 1)
               return true;
            return false;
        }   
    };
    private String nombre_recaudador = "";
    private tmscargaimpresorasrecauda cargaimpresoras = new tmscargaimpresorasrecauda();
    private boolean pagoPrepagado = false;
    private boolean hayPrepagados = false;
    private String empresaNombreCorto="";
    private long sesionId = 1;
    private long usuarioid = 999;
    private long idmenu = 0;
    private long idoperador = 0;
    private long idterminal = 0;
    private String idTerminalsub;
    private Vector funciones;
    private Vector funcionesTodas;
    private Vector fauditables;
    private Vector fauditablesTodas;
    private boolean reimpresion = false;
    private String nombre_equipo = "";
    private Vector codigos = null;
    private Vector valores = null;
    private Vector formatos = null;
    private Vector impresoras = null;
    private Timestamp fecha_servidor= null; 
    private Vector puertos = null;
    private jdlgDatosSupervisor dlgSupervisor;
    private boolean respuestaSN;
    private String autorizado = "";
    

   
   class vista_pago_impresion extends JDialog{

        private JTextArea jTextArea1 = new JTextArea();
        private JLabel jlbl_mensajeVPI = new JLabel();
        private JLabel jlbl_barraEstadoVPI = new JLabel();
        private ImageIcon imageHelp = new ImageIcon(vista_pago_impresion.class.getResource("pregunta.gif"));
        private JLabel jLabel2 = new JLabel();
        private int alto = 0;
       
        public vista_pago_impresion() {
            this.setTitle("Vista de Impresion de Ticket");
            this.setDefaultLookAndFeelDecorated(true);
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setModal(true);
            try {
                jbInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.jTextArea1.requestFocus();
        }

        private void jbInit() throws Exception {
            this.setSize(new Dimension(400, 450+alto));
            this.setLayout(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
            this.setResizable(false);
            jlbl_barraEstadoVPI.setFont(new java.awt.Font("Tahoma", 1, 12));
            jlbl_barraEstadoVPI.setForeground(new java.awt.Color(153, 153, 153));
            jlbl_barraEstadoVPI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            jlbl_barraEstadoVPI.setBounds(new Rectangle(0, 395+alto, 400, 25));
            jlbl_barraEstadoVPI.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
            jlbl_barraEstadoVPI.setHorizontalAlignment(JTextField.CENTER);
            jTextArea1.setBounds(new Rectangle(10, 10, 370, 300+alto));
            jTextArea1.setEditable(false);
            //jTextArea1.setFocusable(false);       
            jTextArea1.setFont(new Font("Dialog", 0, 12));
            jTextArea1.setSize(new Dimension(375, 335+alto));
            jlbl_mensajeVPI.setText("¿Seguro que desea pagar la Actividad?");
            jlbl_mensajeVPI.setBounds(new Rectangle(105, 365+alto, 250, 15));
            jlbl_mensajeVPI.setFont(new Font("Arial", 1, 12));
            jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1_KeyPressed(evt);
            }
            });
             jTextArea1.setFocusTraversalKeysEnabled(false);
             jLabel2.setBounds(new Rectangle(60, 350+alto, 35, 45));
             jLabel2.setIcon(imageHelp);
             jTextArea1.append(" REF.: "+jlbl_folio.getText()+ "\n\n");
             jTextArea1.append(" Actividad            :   "+jcmb_actividad.getSelectedItem().toString() + "\n");
             jTextArea1.append(" Servicio             :   "+jcmb_servicio.getSelectedItem().toString() + "\n");
             for(int i=0; i<jtbl_datosAdicionales.getRowCount(); i++)
             {   
                 if(jtbl_datosAdicionales.getValueAt(i,1)!=null)
                  jTextArea1.append(" "+jtbl_datosAdicionales.getValueAt(i,0).toString()+"      :   "+jtbl_datosAdicionales.getValueAt(i,1).toString()+ "\n");
             }
             jTextArea1.append(" Operador           :   "+jtxt_clave.getText()+"-"+jtxt_nombre.getText()+ "\n");
             jTextArea1.append(" Sueldo               :   "+jtxt_monto.getText()+ "\n");
             jTextArea1.append(" Retencion         :   "+jtxt_retencion.getText()+ "\n");
             float total = (Float.valueOf(jtxt_monto.getText()))- (Float.valueOf(jtxt_retencion.getText()));
             long total2 = Float.valueOf(total).longValue();
             jTextArea1.append(" Total                  :   "+total+ "\n");
             jTextArea1.append("              ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos 00/M.N.  )\n");
             jTextArea1.append(" Recaudador      :   "+nombre_recaudador+ "\n");
             SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
             SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
             jTextArea1.append(" Fecha de Recaudacion:  "+formatf.format(fecha_servidor.getTime())  + "\n");
             jTextArea1.append(" Hora de Recaudacion  : "+ formath.format(fecha_servidor.getTime())+ "\n\n");
             jTextArea1.append(" Firma: ________________________ \n");
             jLabel2.setFocusable(false);
             jlbl_mensajeVPI.setFocusable(false);
             this.add(jLabel2, null);
             this.add(jTextArea1, null);
             this.add(jlbl_barraEstadoVPI,null);
             this.add(jlbl_mensajeVPI, null);
             this.jTextArea1.requestFocus();
        }


   
    //--------------------Eventos del teclado --------------//
      private void jTextArea1_KeyPressed(java.awt.event.KeyEvent evt) {                                        
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
           String nref ="";
            if(hayPrepagados)
            {
               TmsPagosActAdicionalesTbl prepagado = (TmsPagosActAdicionalesTbl) prepagados.get(numprepagado);
               prepagado.setEstadoPago("R");
               nref = ""+prepagado.getReferenciaPagoActAdicional().longValue();
               if(prepagado.getTipoActividadAdicionalId().getActividadAfavor().equals("N"))
               {
                   double total = ((prepagado.getMontoPago().doubleValue()) * -1);
                   prepagado.setMontoPago(BigDecimal.valueOf(total));
                   prepagado.setRetencion(BigDecimal.valueOf(Double.valueOf("0")));
               }
               prepagado.setFechaHoraRecaudacion(new Date(fecha_servidor.getTime()));
               prepagado.setUltimaActualizacionPor(BigInteger.valueOf(getUsuarioId()));
               prepagado.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
               busquedas.pagosActAdicionalesTblFacadeRemote.edit(prepagado);
               prepagados.remove(numprepagado);
            }
            else
            {
               TmsPagosActAdicionalesTbl pago = new TmsPagosActAdicionalesTbl();
               int indexs  = jcmb_servicio.getSelectedIndex();
               pago.setServicioId(listaServicios.get(indexs));
               int indexact  = jcmb_actividad.getSelectedIndex();
               TmsActAdicionalesTbl actividad = listaActividades.get(indexact);
               pago.setTipoActividadAdicionalId(actividad);
               pago.setFechaHoraRecaudacion(new Date(fecha_servidor.getTime()));
               //new Date(fecha_servidor.getTime())
               pago.setOperadorId(BigInteger.valueOf(getIdoperador()));
               if(actividad.getActividadAfavor().equals("S"))
                 pago.setMontoPago(BigDecimal.valueOf(Double.valueOf(jtxt_monto.getText())));
               else
                 pago.setMontoPago(BigDecimal.valueOf(((Double.valueOf(jtxt_monto.getText()))* -1)));  
               pago.setRetencion(BigDecimal.valueOf(Double.valueOf(jtxt_retencion.getText())));
               pago.setEstadoPago("R");
               pago.setRecaudadorId(BigInteger.valueOf(getUsuarioId()));
               pago.setCiudadRecaudacionId(BigInteger.valueOf(getIdterminal()));
               pago.setCreadoPor(BigInteger.valueOf(getUsuarioId()));
               pago.setFechaCreacion(new Date(fecha_servidor.getTime()));
               pago.setUltimaActualizacionPor(BigInteger.valueOf(getUsuarioId()));
               pago.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
               for(int i=0; i<jtbl_datosAdicionales.getRowCount(); i++)
               {
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL1")) pago.setAdicional1(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL2")) pago.setAdicional2(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL3")) pago.setAdicional3(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL4")) pago.setAdicional4(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL5")) pago.setAdicional5(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL6")) pago.setAdicional6(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL7")) pago.setAdicional7(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL8")) pago.setAdicional8(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL9")) pago.setAdicional9(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL10")) pago.setAdicional10(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL11")) pago.setAdicional11(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL12")) pago.setAdicional12(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL13")) pago.setAdicional13(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL14")) pago.setAdicional14(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL15")) pago.setAdicional15(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL16")) pago.setAdicional16(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL17")) pago.setAdicional17(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL18")) pago.setAdicional18(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL19")) pago.setAdicional19(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL20")) pago.setAdicional20(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL21")) pago.setAdicional21(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL22")) pago.setAdicional22(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL23")) pago.setAdicional23(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL24")) pago.setAdicional24(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL25")) pago.setAdicional25(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL26")) pago.setAdicional26(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL27")) pago.setAdicional27(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL28")) pago.setAdicional28(jtbl_datosAdicionales.getValueAt(i,1).toString());
                   if(jtbl_datosAdicionales.getValueAt(i,2).toString().equals("ADICIONAL29")) pago.setAdicional29(jtbl_datosAdicionales.getValueAt(i,1).toString());
               }
               nref = busquedas.pagosActAdicionalesTblFacadeRemote.create(pago,idTerminalsub).toString();
            }

           
           //AGREGA EL REGISTRO DE AUDITORIA
           int indexfun = funcionesTodas.indexOf("6003");
           if(indexfun>=0)
           {
            String auditable = fauditablesTodas.get(indexfun).toString().toUpperCase();
             if(auditable.equals("S") || auditable.equals("Y"))
             {
               TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
               auditoria.setUsuarioId(BigInteger.valueOf(getUsuarioId()));
               auditoria.setNombreEquipo(getNombre_equipo());
               auditoria.setFuncionNumero(BigInteger.valueOf((long)6003));
               auditoria.setFecha(fecha_servidor);
               auditoria.setEstadoProceso("EXITO");
               auditoria.setDescripcionProceso("Se Pago la Actividad Adicional de "+jcmb_actividad.getSelectedItem().toString());
               busquedas.auditoriaTblFacadeRemote.create(auditoria,idTerminalsub);
            }
           }
           
        this.dispose();
        imprimir_recibo(nref);
      }
      
    } 
    
}
    
   class imprimir_recibo_capacitacion implements Printable{
        
        private double PIXELES_POR_PULGADA = 72.0;
        private double ANCHO = 3.625; //DIMENSIONES DEL PAPEL (PULGADAS)
        private double ALTO = 12;
        private String ref="";
        
        public imprimir_recibo_capacitacion(String nref){
            ref = nref;
        }

        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            Font fontVar = new Font("Arial",Font.PLAIN,9);
            g.setFont(fontVar);
            int lineaNueva=11, i=1;
            g.drawString("AUTOBUSES MEX-PUE ESTRELLA ROJA",10,lineaNueva);
            i++;
            g.drawString("REF. : ",0,lineaNueva*i);        
            g.drawString(ref,40,lineaNueva*i);        
            i++;i++; 
            g.drawString("ACTIVIDAD",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(jcmb_actividad.getSelectedItem().toString(),85,lineaNueva*i);
            i++; 
            g.drawString("SERVICIO",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(jcmb_servicio.getSelectedItem().toString(),85,lineaNueva*i);
            for(int ni=0; ni<jtbl_datosAdicionales.getRowCount(); ni++)
            {
                if(jtbl_datosAdicionales.getValueAt(ni,1)!=null)
                {    
                    i++; 
                    g.drawString(jtbl_datosAdicionales.getValueAt(ni,0).toString(),0,lineaNueva*i);
                    g.drawString(":",73,lineaNueva*i);
                    g.drawString(jtbl_datosAdicionales.getValueAt(ni,1).toString(),85,lineaNueva*i);
                }
            }            

            i++; 
            g.drawString("OPERADOR",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(jtxt_clave.getText()+"-"+jtxt_nombre.getText(),85,lineaNueva*i);
            i++;
            g.drawString("SUELDO",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(jtxt_monto.getText(),85,lineaNueva*i);
            i++;
            g.drawString("RETENCION",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(jtxt_retencion.getText(),85,lineaNueva*i);
            i++;
             float total = (Float.valueOf(jtxt_monto.getText()))- (Float.valueOf(jtxt_retencion.getText()));
             long total2 = Float.valueOf(total).longValue();
            g.drawString("TOTAL",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(""+total,85,lineaNueva*i);
            i++;i++;
            g.drawString("              ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos 00/M.N.  )",0,lineaNueva*i);
            i++;i++;
            g.drawString("RECAUDADOR",0,lineaNueva*i);
            g.drawString(":",73,lineaNueva*i);
            g.drawString(""+nombre_recaudador,85,lineaNueva*i);
            SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
            SimpleDateFormat formath = new SimpleDateFormat ("H:mm:ss");
            i++;
            g.drawString("FECHA DE RECAUDACION: ",0,lineaNueva*i);
            g.drawString(""+formatf.format(fecha_servidor.getTime()),125,lineaNueva*i);
            i++;
            g.drawString("HORA DE RECAUDACION : ",0,lineaNueva*i);
            g.drawString(""+formath.format(fecha_servidor.getTime()),125,lineaNueva*i);
            i++;i++;i++;
            g.drawString("FIRMA ________________________",0,lineaNueva*i);
            i++;i++;i++;i++;                              
            g.drawString("         .",150,lineaNueva*i);
            return PAGE_EXISTS;
        }

        public boolean ImprimeDatos(){
            
            return this.setPrint();
        }
        
        private boolean setPrint(){    
            PageFormat formatoPagina = new PageFormat();
            double papelAncho  = ANCHO*PIXELES_POR_PULGADA;
            double papelAlto   = ALTO*PIXELES_POR_PULGADA;
            double papelImaginableX      = PIXELES_POR_PULGADA*0.5;
            double papelImaginableY      = PIXELES_POR_PULGADA*0.0625;
            double papelImaginableAncho  = papelAncho  - (PIXELES_POR_PULGADA*0.5);
            double papelImaginableAlto   = papelAlto - PIXELES_POR_PULGADA;

            Paper papel = new Paper();
            papel.setSize(papelAncho,papelAlto);
            papel.setImageableArea(papelImaginableX,papelImaginableY,papelImaginableAncho,papelImaginableAlto);
            formatoPagina.setPaper(papel);
            formatoPagina.setOrientation(PageFormat.PORTRAIT);
            
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this,formatoPagina);

            //PrintService service[] = PrinterJob.lookupPrintServices();
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService(); 
            try {
                  int indeximp =  formatos.indexOf("TICKETS");
                  PrintService impresora = (PrintService)impresoras.get(indeximp);
                 job.setPrintService(impresora);
                job.print();
            } catch (PrinterException ex) {
                return false;
            }
             return true;
        }

        String customFormat(String pattern, float value ) {
              DecimalFormat myFormatter = new DecimalFormat(pattern);
              return myFormatter.format(value);
           }
    }    
    
   private boolean imprimir_recibo_capacitacion_LPT(String ref,String pSalidaImpresion){
            String SalidaImpresion = pSalidaImpresion;
            String sCad="\n";
            sCad = sCad+"    AUTOBUSES MEX-PUE ESTRELLA ROJA";
            sCad =sCad+"\n";
            sCad = sCad+"REF. : "+ref;        
            sCad =sCad+"\n";
            sCad =sCad+"\n"; 
            sCad = sCad+"ACTIVIDAD   :  "+jcmb_actividad.getSelectedItem().toString();
            sCad =sCad+"\n"; 
            sCad = sCad+"SERVICIO    :  "+jcmb_servicio.getSelectedItem().toString();
            for(int ni=0; ni<jtbl_datosAdicionales.getRowCount(); ni++)
            {
                if(jtbl_datosAdicionales.getValueAt(ni,1)!=null)
                {    
                    sCad =sCad+"\n"; 
                    sCad = sCad+jtbl_datosAdicionales.getValueAt(ni,0).toString()+"  :  "+jtbl_datosAdicionales.getValueAt(ni,1).toString();
                }
            }            

            sCad =sCad+"\n"; 
            sCad = sCad+"OPERADOR    :  "+jtxt_clave.getText()+"-"+jtxt_nombre.getText();
            sCad =sCad+"\n";
            sCad = sCad+"SUELDO      :  "+jtxt_monto.getText();
            sCad =sCad+"\n";
            sCad = sCad+"RETENCION   :  "+jtxt_retencion.getText();
            sCad =sCad+"\n";
             float total = (Float.valueOf(jtxt_monto.getText()))- (Float.valueOf(jtxt_retencion.getText()));
             long total2 = Float.valueOf(total).longValue();
            sCad = sCad+"TOTAL       :  "+total;
            sCad =sCad+"\n";
            sCad =sCad+"\n";
            sCad = sCad+"     ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos 00/M.N.  )";
            sCad =sCad+"\n";
            sCad =sCad+"\n";
            sCad = sCad+"RECAUDADOR  :  "+nombre_recaudador;
            SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
            SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
            sCad = sCad+"\n";
            sCad = sCad+"FECHA DE RECAUDACION: "+formatf.format(new Date(fecha_servidor.getTime()));
            sCad = sCad+"\n";
            sCad = sCad+"HORA DE RECAUDACION : "+formath.format(new Date(fecha_servidor.getTime()));
            sCad = sCad+"\n";sCad = sCad+"\n";
            sCad = sCad+"FIRMA ________________________";
            sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";                         
            sCad = sCad+"         .";
          try{
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = "C:\\TICKET_PAGO_ACT_ADICIONAL_"+ref+".TXT";
                
            FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
            PrintStream ps = new PrintStream(os); 
            ps.print(sCad); // CADENA A IMPRIMIR
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            return false;
        }catch(Exception sctex){
            sctex.printStackTrace();
            return false;
        }
      return true;                   

   }

   class Jdlg_Pregunta extends JDialog {
                            private JLabel jLabel1 = new JLabel();
                            private JLabel jLabel2 = new JLabel();
                            private JLabel jLabel3 = new JLabel();
                            private JLabel jlbl_barraEstadoP = new JLabel();
                            private ImageIcon imagen_pregunta = new ImageIcon(vista_pago_impresion.class.getResource("pregunta.gif"));
                            private String numref;
                            
                            public Jdlg_Pregunta(String title, String nref) {
                                this.setTitle(title);
                                this.setModal(true);
                                this.setDefaultLookAndFeelDecorated(true);
                                numref = nref;
                                this.setUndecorated(true);
                                this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
                                this.setAlwaysOnTop(true);
                                try {
                                    jbInit();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                jLabel3.requestFocus();
                                this.addComponentListener(new ComponentAdapter() {
                                            public void componentShown(ComponentEvent ce) {
                                                jLabel3.requestFocusInWindow();
                                            }
                                        });
                            }

                            private void jbInit() throws Exception {
                                this.setSize(new Dimension(333, 128));
                                this.getContentPane().setLayout( null );
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                Dimension DilaogSize = this.getSize();
                                if (DilaogSize.height > screenSize.height) {
                                    DilaogSize.height = screenSize.height;
                                }
                                if (DilaogSize.width > screenSize.width) {
                                    DilaogSize.width = screenSize.width;
                                }
                                this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
                                jLabel1.setText("¿Se imprimio correctamente el comprobante?");
                                jLabel1.setBounds(new Rectangle(45, 35, 265, 15));
                                jLabel1.setFont(new Font("Arial", 1, 12));
                                jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    jLabel1_KeyPressed(evt);
                                }
                                });
                                jLabel3.setFocusTraversalKeysEnabled(false);
                                jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
                                jLabel2.setSize(new Dimension(35, 45));
                                jLabel2.setIcon(imagen_pregunta);
  
                                jLabel3.setBounds(new Rectangle(45, 10, 285, 15));
                                jLabel3.setFont(new Font("Arial", 1, 12));
                                jLabel3.setText("Referencia de Pago: "+numref);
                                jlbl_barraEstadoP.setFont(new java.awt.Font("Tahoma", 1, 12));
                                jlbl_barraEstadoP.setForeground(new java.awt.Color(153, 153, 153));
                                jlbl_barraEstadoP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                                jlbl_barraEstadoP.setBounds(new Rectangle(0, 69, 333, 25));
                                jlbl_barraEstadoP.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
                                jlbl_barraEstadoP.setHorizontalAlignment(JTextField.CENTER);
                                this.add(jLabel3, null);
                                this.add(jLabel2, null);
                                this.add(jLabel1, null);
                                this.add(jlbl_barraEstadoP,null);
                                jLabel1.setFocusable(false);
                                jLabel2.setFocusable(false);
                                jLabel3.setFocusable(true);
                                jLabel3.requestFocus();
                                
                            }

                     private void jLabel1_KeyPressed(java.awt.event.KeyEvent evt) {                                        
                          if(evt.getKeyCode() == evt.VK_ESCAPE)
                          {
                                reimpresion = true;
                                this.dispose();
                          }
                          if(evt.getKeyCode() == evt.VK_ENTER)
                          {
                            reimpresion = false;
                            this.dispose();
                          }
                        }                             
    }    

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
   
    public long getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(long idoperador) {
        this.idoperador = idoperador;
    }

    public long getIdterminal() {
        return idterminal;
    }

    public void setIdterminal(long idterminal) {
        this.idterminal = idterminal;
    }

    public long getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = Long.valueOf(sesionId);
    }

    public void setFoco(){
            jtxt_clave.requestFocus();
    }
    

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
}
