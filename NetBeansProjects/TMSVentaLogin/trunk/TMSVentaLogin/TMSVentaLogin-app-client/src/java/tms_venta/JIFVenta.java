/*
 * JIFVenta.java
 *
 * Created on 30 de agosto de 2007, 05:21 PM
 */
 
package tms_venta;
 
import AsignacionVenta.jDlgAutorizaSupervisor;
import AsignacionVenta.jDlgRefoliar;
import DialogosX.JDLGBoletosReferenciados;
import DialogosX.JDlgAceptar;
import DialogosX.JDlgAcumulaEstrellasHO;
import DialogosX.JDlgCHorarioMultiBoleto;
import AsignacionVenta.JDlgMotivoRefol;
import DialogosX.JDlgCalendario;
import DialogosX.JDlgCancelaMultiBoleto;
import DialogosX.JDlgCanjeBAMultiBoleto;
import DialogosX.JDlgCanjeBolRef;
import DialogosX.JDlgSiNo;
import DialogosX.JDlgSiNoCancelar;
import DialogosX.JDlgCobro;
import DialogosX.JDlgControlRvn;
import DialogosX.jDlgCtdPasTipoAsiento;
import DialogosX.jDlgVentaBA;
import TMSVtaProductosER.entidad.ProductoCarrito;
import impresion.ImprimeBoletos;
import impresion.imprimir_ticket_canjeBA_ref;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import subProcesos.CriteriosBusqueda;
import subProcesos.JCuantityTextFieldMaxDig;
import subProcesos.JTextTextFieldMaxCharAlfa;
import subProcesos.JThdConsultaCorrida;
import subProcesos.JTxtFieldAsientos;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import subProcesos.Mensajes;
import subProcesos.ConsultaCorridaException;
import subProcesos.RelojModeloSwingVta;
import subProcesos.RelojVisualVta;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_venta.entidad.TmsBoletosVentaTbl;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import tms_vta_productos_er.JdlgVentaProductosER;
import util.Promocion;
import xer_emv_dll.JClsImprimeVoucher;
import xer_emv_dll.JClsPinPadTBRequest;
import xer_emv_dll.JClsPinPadTBResponse;

/**
 *
 * @author  ocruz
 */
public class JIFVenta extends javax.swing.JInternalFrame {
    
     JDesktopPane jdp =  new JDesktopPane();
     Vector datosIniciales;
    /** Creates new form JIFVenta */
    public JIFVenta(Vector pdatosIniciales) {
        String MENU_NOMBRE;
        long MENU_ID;
        long USUARIO_ID;
        String USUARIO_NUMERO;
        String USUARIO_NOMBRE;
        long SESION_ID;
        
        DialogoAceptar = new DialogosX.JDlgAceptar();
        inicioGral=false;
        this.datosIniciales = pdatosIniciales;
        USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        USUARIO_NUMERO = datosIniciales.get(1).toString();
        USUARIO_NOMBRE = datosIniciales.get(2).toString();
        SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        MENU_NOMBRE =datosIniciales.get(7).toString();
        System.out.println("+++++++++ Inicia"+new Date()+" JCls_Sesion_Usuario +++++++++");
        JCls_Sesion_Usuario SesionUsuario = new JCls_Sesion_Usuario(USUARIO_ID, USUARIO_NUMERO, USUARIO_NOMBRE, SESION_ID, MENU_NOMBRE);
        System.out.println("--------- Termina"+new Date()+" JCls_Sesion_Usuario ---------");
        SesionUsuario.setIpAS(datosIniciales.get(5).toString());
        SesionUsuario.setPortAS(datosIniciales.get(6).toString());
        System.out.println("+++++++++ Inicia"+new Date()+" SesionVenta +++++++++");
        sesionVenta = new SesionVenta(SesionUsuario);
        System.out.println("--------- Termina"+new Date()+" SesionVenta ---------");
        sesionVenta.setDatosInicales(datosIniciales);
        if(sesionVenta.SISTEMA_SALIDA_INMEDIATA()){// simula dispose
            return;
        }
        
        System.out.println("+++++++++ Inicia"+new Date()+" proceso +++++++++");
        int error=sesionVenta.proceso(MENU_ID);
        System.out.println("--------- Termina"+new Date()+" proceso ---------");
        if(error == -69 || error == 666){
            return; // simula dispose
        }
        if(error != 0){
            switch(error){
                case -21: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!", "<html>Intentelo mas tarde.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                case 1: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de Funciones de Usuario.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 2: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Carga de parametros iniciales incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 3: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>No se pudo obtener numero de corte.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 4: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Corte en proceso.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 5: DialogoAceptar.mostrarDialogo("¡Configuracion de DBLink Central incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
                case 6: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!", "<html>No se obtuvieron ids de actividades sesion.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                case 7: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!", "<html>Caja invalida.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                case 8: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!", "<html>No se pudo obtener folio del sistema.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                case 9: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!", "<html>No se obtuvo la fecha de la BD.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                case 10: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de esquema propio.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
                case 11: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Ubicación de caja incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
                case 12: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>MAC de caja incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
                case 13: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Complementación de parametros incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 14: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuración de impresion de boletos incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 15: DialogoAceptar.mostrarDialogo("¡Error de configuracion de impresoras!","<html>Esta caja no tiene impresoras<br>configuradas para boletos</html>", Color.RED); break;
                case 16: DialogoAceptar.mostrarDialogo("¡Sesion cancelada!","Presione ENTER para continuar...", Color.RED); break;
                case 17: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Carga de tarifas incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 18: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de terminales.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 19: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de rutas.<br>Contacte al administrador del sistema.</html>",Color.RED); break;
                case 20: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de plantillas.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
                case 21: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de tipos de pago.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
                case 22: DialogoAceptar.mostrarDialogo("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta de tipos de pasajero.<br>Contacte al administrador del sistema.</html>",Color.RED);break;
            }
            return;
        }
        
        if(sesionVenta.getUserCon().getEmpresasOfertantes().length==1){
            objEmp = new Object[1];
            objEmp[0] = sesionVenta.getUserCon().getEmpresasOfertantes()[0][1];
        }
        else{
            objEmp = new Object[sesionVenta.getUserCon().getEmpresasOfertantes().length];
            for(int io=0; io<objEmp.length; io++)
                objEmp[io] = sesionVenta.getUserCon().getEmpresasOfertantes()[io][1];
        }
        cbmEmpresas = new DefaultComboBoxModel(objEmp);
        this.COAnt = sesionVenta.getUserCon().getTerminalNombre();
        this.sesionVenta.cargarOrigenesDestinosServicios(sesionVenta.getUserCon().getTerminalNombre(), true);
        cbmServicio = new DefaultComboBoxModel(sesionVenta.getServicios());
        cbmOrigen = new DefaultComboBoxModel(sesionVenta.getOrigenes());
        cbmDestino = new DefaultComboBoxModel(sesionVenta.getDestinos());
        this.ServicioAnterior = sesionVenta.getServicios().get(0).toString();
        nombreEmpresa=sesionVenta.getUserCon().getEmpresaPrincipal();
        //System.out.println("sesionVenta.getUserCon().getTerminalNombre() "+sesionVenta.getUserCon().getTerminalNombre());
        sesionVenta.getDBLinkClaveOrigen(sesionVenta.getUserCon().getTerminalNombre());
        //System.out.println("VALOR DBLINK "+sesionVenta.getDBLink());
        this.ServicioId = sesionVenta.getServicioId(this.ServicioAnterior);
        this.transaccion=this.txVENTA;
        this.transaccionAux=this.txVENTA;
        sesionVenta.getUserCon().setDigitosCupon(10);
        this.ubiCompo=ucEjecCons;
        //
        initComponents();
        ResumenDefault( );
        if(this.sesionVenta.getUserCon().getAplicacionVenta()) this.setTitle("TMS-Venta");
        else this.setTitle("Call Center");
        
        sesionVenta.setEtiqueta(jLblMultiCast);
        
        // CHECA DE UNA VEZ LA FECHA-HORA
        Date fechainicial =null;
        if(sesionVenta.fechaHoraBD())
            fechainicial = sesionVenta.getFechaHoraSistemaVenta();
        else
            fechainicial = new Date();
        
        jlbl_reloj = new RelojVisualVta(new RelojModeloSwingVta(fechainicial.getTime(), sesionVenta), 200, 20, jLabel3);
        sesionVenta.setFechaHoraBD(jlbl_reloj);
        
        interfazColor();
        
        this.jTblCorridas.setRowHeight(25);
        if(sesionVenta.getUserCon().getAplicacionVenta()) this.jLblTx.setText("*VENTA*");
        else  this.jLblTx.setText("*CALL CENTER*");
        jCboOrigen.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        hoyEs = formatoDate.format(new Date());
        jTxtFecha.setText(hoyEs);
        MesAnho=hoyEs.substring(3);
        Anho=hoyEs.substring(6);
        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
        inicioGral=true;
        jLblNombreUsuario.setText("<html>Usuario: <font color="+ColoresInterfaz.cHTML1+">"+(USUARIO_NOMBRE.length()>15?USUARIO_NOMBRE.substring(0,15):USUARIO_NOMBRE)+"</font></html>");
        jLblNombreEmpresa.setText("<html>Empresa: <font color="+ColoresInterfaz.cHTML1+">"+(nombreEmpresa.length()>16?nombreEmpresa.subSequence(0,15):nombreEmpresa)+"</font></html>");
        inhabilitarF10();
        threadPlantilla = new ThreadPlantilla();
        threadPlantilla.start(); 
        jLblEmpresa.setVisible(false); jCboEmpresas.setVisible(false);
        this.ubiCrit = uCtServicio;
        coloresEmpresas();
        System.out.println("Estado: "+sesionVenta.getEstadoId("CAPU"));
        System.out.println("ServicioId: "+sesionVenta.getServicioId("EBUS"));
        System.out.println("TmsTiposPasajeroId: "+sesionVenta.getTmsTiposPasajeroId("A"));

        jtxt_personaAutorizada.setFocusTraversalKeysEnabled(false);
        jtxt_personaAutorizada.setEnabled(false);
        jtxt_personaAutorizada.setEditable(false);
        vengoDeCobro=false;
        vengoDePantallaCobro=false;
        jCboServicio.requestFocusInWindow();
    }
    /*********************** INICIALIZAR SESION VENTA ************************/    
    private void finalizarSesionVenta(){
        if(!sesionVenta.finalizarVenta()){
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡La sesion no se cerro correctamente!", "Contacte al administrador del sistema.", Color.RED);
        }
        if(jThdConsultaCorrida!=null){
            jThdConsultaCorrida.moreQuotes=false;
            jThdConsultaCorrida = null;
            sesionVenta.b_decode_data=false;
        }
        if(jlbl_reloj!=null){
            jlbl_reloj.finaliza();
            jlbl_reloj=null;
        }
        if(threadPlantilla!=null){
            threadPlantilla.noSalir=false;
            threadPlantilla = null;
        }
        this.dispose();
        return;
    }
    
    private boolean PeriodoVacacional(){
        String FechaHoraCorrida = listaCorridas[fila][1].toString()+" "+listaCorridas[fila][2].toString();
        Date FHCor=null;
        try {
            FHCor = formatoDateServer.parse(FechaHoraCorrida);
        } catch (ParseException ex) {
            ex.printStackTrace();
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡No es posible vender de esta corrida!", "La fecha de la corrida <<"+listaCorridas[fila][0]+">> es incorrecta.", Color.RED);
            return false;
        }
        // PERIODO VACACIONAL TRUE-FALSE
        Date FechaHoraServer = new Date(jlbl_reloj.getFecha().getTime());
        StringTokenizer srtToken;
        try{          
            if(sesionVenta.getUserCon().getIniVac().getTime()<=FHCor.getTime() &&
                    FHCor.getTime()<=sesionVenta.getUserCon().getFinVac().getTime()){
                sesionVenta.getUserCon().setPerVac(true);
                if (corridaActual[62] == null || corridaActual[62].toString().equals("")){
                    sesionVenta.getUserCon().setSenCor(Integer.valueOf(corridaActual[1].toString()));
                    sesionVenta.getUserCon().setEstCor(Integer.valueOf(corridaActual[2].toString()));
                    sesionVenta.getUserCon().setProCor(Integer.valueOf(corridaActual[3].toString()));
                    sesionVenta.getUserCon().setCorCor(Integer.valueOf(corridaActual[4].toString()));
                }else{
                    srtToken = new StringTokenizer(corridaActual[62].toString(),"-");
                    //Senectud
                    sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                    //Profesor
                    sesionVenta.getUserCon().setProCor(Integer.valueOf(srtToken.nextToken()));
                    //Estudiante
                    sesionVenta.getUserCon().setEstCor(Integer.valueOf(srtToken.nextToken()));
                    //Especial
                    sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
                }
            }
            else{
                sesionVenta.getUserCon().setPerVac(false);
                if (corridaActual[62] == null || corridaActual[62].toString().equals("")){
                    sesionVenta.getUserCon().setSenCor(Integer.valueOf(corridaActual[1].toString()));
                    sesionVenta.getUserCon().setCorCor(Integer.valueOf(corridaActual[4].toString()));
                }else{
                    srtToken = new StringTokenizer(corridaActual[62].toString(),"-");
                    //Senectud
                    sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                }
                sesionVenta.getUserCon().setEstCor(0);
                sesionVenta.getUserCon().setProCor(0);
            }
        }catch(NullPointerException npex){
            npex.printStackTrace();
            sesionVenta.getUserCon().setPerVac(false);
            if (corridaActual[62] == null || corridaActual[62].toString().equals("")){
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(corridaActual[1].toString()));
            }else{
                srtToken = new StringTokenizer(corridaActual[62].toString(),"-");
                //Senectud
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                //Profesor
                Integer.valueOf(srtToken.nextToken());
                //Estudiante
                Integer.valueOf(srtToken.nextToken());
                //Especial
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
            }
            sesionVenta.getUserCon().setEstCor(0);
            sesionVenta.getUserCon().setProCor(0);
        }
        return true;
    }
    
    /*************************************************************************/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblFolioActual = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLblNombreEmpresa = new javax.swing.JLabel();
        jLblNombreUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JPnlAutobus = new javax.swing.JPanel();
        JPnlbus = new JPnl_BusX(sesionVenta.getComponentes(), sesionVenta.getEncabezado(sesionVenta.getPLANTILLA_DEFAULT()), sesionVenta.getLineas(sesionVenta.getPLANTILLA_DEFAULT()),
            ColoresInterfaz.cFondoVentana, ColoresInterfaz.cNumeroAsientos);
        JPnlbus.setVisibleOcupado(false);
        JPnlbus.setVisibleReservado(true);
        JPnlbus.setVisibleReservadoNC(true);
        JPnlAutobus.add(JPnlbus,
            new GridBagConstraints(0, 0, 0, 0, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTxtAsientos = new javax.swing.JTextField();
        jTxtAsientos = new JTxtFieldAsientos();
        jLabel7 = new javax.swing.JLabel();
        jTxtTipoPasaje = new paquete.JTxtFieldTipoPasaje();
        jTxtTipoPasaje.setTipos(sesionVenta.CastTiposPasaje());
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jCboOrigen = new javax.swing.JComboBox();
        jCboOrigen = new JComboBox(cbmOrigen);
        jCboOrigen.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
        jLabel9 = new javax.swing.JLabel();
        jCboDestino = new javax.swing.JComboBox();
        jCboDestino= new JComboBox(cbmDestino);
        jLabel10 = new javax.swing.JLabel();
        jCboServicio = new javax.swing.JComboBox();
        jCboServicio = new JComboBox(cbmServicio);
        jLabel11 = new javax.swing.JLabel();
        jTxtFecha = new javax.swing.JTextField();
        jTxtFecha = new JDateTextField();
        jTxtHora = new javax.swing.JTextField();
        jTxtHora = new JHourTextField();
        jLblCiudadVenta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCboCiudadVenta = new javax.swing.JComboBox();
        jCboCiudadVenta = new JComboBox(sesionVenta.getOrigenesDBLink());
        jCboCiudadVenta.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
        jLblEmpresa = new javax.swing.JLabel();
        jCboEmpresas = new javax.swing.JComboBox();
        jCboEmpresas = new JComboBox(cbmEmpresas);
        jPanel6 = new javax.swing.JPanel();
        jScpCorridas = new javax.swing.JScrollPane();
        jTblCorridas = new javax.swing.JTable();
        jPnlVtaActual = new javax.swing.JPanel();
        jScpVtaActual = new javax.swing.JScrollPane();
        jTblVtaActual = new javax.swing.JTable();
        jLblTotalVtaActual = new javax.swing.JLabel();
        jPnlCHorario = new javax.swing.JPanel();
        jScpCH = new javax.swing.JScrollPane();
        jTblCH = new javax.swing.JTable();
        jScpBO = new javax.swing.JScrollPane();
        jTblBO = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jtxt_personaAutorizada = new tms_TextFields.JTextTextField();
        jPanel2 = new javax.swing.JPanel();
        jLblBarraEstado = new javax.swing.JLabel();
        jPnlRecoleccion = new javax.swing.JPanel();
        jLblMonto = new javax.swing.JLabel();
        jTxtMonto = new javax.swing.JTextField();
        jTxtMonto = new JCuantityTextFieldMaxDig(10);
        jTxtMonto.setFocusTraversalKeysEnabled(false);
        jPanel5 = new javax.swing.JPanel();
        jScpResumenVtaCorrida = new javax.swing.JScrollPane();
        jTblResumenVtaCorrida = new javax.swing.JTable();
        jLblTarifaAdulto = new javax.swing.JLabel();
        jLblMultiCast = new javax.swing.JLabel();
        jLblTx = new javax.swing.JLabel();

        setBackground(java.awt.Color.black);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setFocusable(false);

        jLblFolioActual.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLblFolioActual.setForeground(new java.awt.Color(255, 255, 255));
        jLblFolioActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblFolioActual.setText("<html>Folio Actual: <font color=\"#ff0000\">0000000000</font></html>");
        jLblFolioActual.setFocusable(false);
        jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">0000000000</font></html>");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 12));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Rev 14.10.15");

        jLblNombreEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLblNombreEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        jLblNombreEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblNombreEmpresa.setText("EMPRESA");

        jLblNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLblNombreUsuario.setText("USUARIO");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha Hora");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLblNombreUsuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 237, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblFolioActual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblNombreEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLblNombreUsuario)
                .add(jLblFolioActual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLblNombreEmpresa)
                .add(jLabel2)
                .add(jLabel3))
        );

        JPnlAutobus.setBackground(java.awt.Color.black);
        JPnlAutobus.setFocusable(false);
        JPnlAutobus.setMinimumSize(new java.awt.Dimension(190, 458));

        org.jdesktop.layout.GroupLayout JPnlAutobusLayout = new org.jdesktop.layout.GroupLayout(JPnlAutobus);
        JPnlAutobus.setLayout(JPnlAutobusLayout);
        JPnlAutobusLayout.setHorizontalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 199, Short.MAX_VALUE)
        );
        JPnlAutobusLayout.setVerticalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 770, Short.MAX_VALUE)
        );

        jPanel3.setBackground(java.awt.Color.black);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 15));
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Asientos");

        jTxtAsientos.setBackground(new java.awt.Color(238, 238, 238));
        jTxtAsientos.setFont(new java.awt.Font("Calibri", 1, 15));
        jTxtAsientos.setEnabled(false);
        jTxtAsientos.setName("jtxtasientos"); // NOI18N
        jTxtAsientos.setFocusTraversalKeysEnabled(false);
        jTxtAsientos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtAsientosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtAsientosFocusLost(evt);
            }
        });
        jTxtAsientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtAsientosKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 15));
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Tipo Pasaje");

        jTxtTipoPasaje.setBackground(new java.awt.Color(238, 238, 238));
        jTxtTipoPasaje.setEnabled(false);
        jTxtTipoPasaje.setFont(new java.awt.Font("Calibri", 1, 15));
        jTxtTipoPasaje.setName("jtxttipopasaje"); // NOI18N
        jTxtTipoPasaje.setFocusTraversalKeysEnabled(false);
        jTxtTipoPasaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtTipoPasajeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtTipoPasajeFocusLost(evt);
            }
        });
        jTxtTipoPasaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtTipoPasajeKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel7)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTxtTipoPasaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .add(jTxtAsientos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtAsientos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtTipoPasaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBackground(java.awt.Color.black);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar Corrida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), java.awt.Color.white)); // NOI18N
        jPanel4.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("Origen");

        jCboOrigen.setFont(new java.awt.Font("Tahoma", 1, 14));
        jCboOrigen.setFocusTraversalKeysEnabled(false);
        jCboOrigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboOrigenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboOrigenFocusLost(evt);
            }
        });
        jCboOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboOrigenKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("Destino");

        jCboDestino.setFont(new java.awt.Font("Tahoma", 1, 14));
        jCboDestino.setFocusTraversalKeysEnabled(false);
        jCboDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboDestinoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboDestinoFocusLost(evt);
            }
        });
        jCboDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboDestinoKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("Servicio");

        jCboServicio.setFont(new java.awt.Font("Tahoma", 1, 14));
        jCboServicio.setFocusTraversalKeysEnabled(false);
        jCboServicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboServicioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboServicioFocusLost(evt);
            }
        });
        jCboServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboServicioKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("Fecha");

        jTxtFecha.setBackground(new java.awt.Color(238, 238, 238));
        jTxtFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFecha.setText("31/08/2007");
        jTxtFecha.setFocusTraversalKeysEnabled(false);
        jTxtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtFechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtFechaFocusLost(evt);
            }
        });
        jTxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFechaKeyPressed(evt);
            }
        });

        jTxtHora.setBackground(new java.awt.Color(238, 238, 238));
        jTxtHora.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTxtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHora.setFocusTraversalKeysEnabled(false);
        jTxtHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtHoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtHoraFocusLost(evt);
            }
        });
        jTxtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraKeyPressed(evt);
            }
        });

        jLblCiudadVenta.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLblCiudadVenta.setForeground(java.awt.Color.white);
        jLblCiudadVenta.setText("Ciudad Origen");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Hora");

        jCboCiudadVenta.setFont(new java.awt.Font("Tahoma", 1, 14));
        jCboCiudadVenta.setFocusTraversalKeysEnabled(false);
        jCboCiudadVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboCiudadVentaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboCiudadVentaFocusLost(evt);
            }
        });
        jCboCiudadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboCiudadVentaKeyPressed(evt);
            }
        });

        jLblEmpresa.setFont(new java.awt.Font("Arial Black", 1, 11));
        jLblEmpresa.setForeground(java.awt.Color.white);
        jLblEmpresa.setText("Empresa");

        jCboEmpresas.setFont(new java.awt.Font("Tahoma", 1, 14));
        jCboEmpresas.setFocusTraversalKeysEnabled(false);
        jCboEmpresas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboEmpresasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboEmpresasFocusLost(evt);
            }
        });
        jCboEmpresas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboEmpresasKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel10)
                    .add(jLabel11)
                    .add(jLabel8)
                    .add(jLblCiudadVenta))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jCboCiudadVenta, 0, 107, Short.MAX_VALUE)
                            .add(jCboOrigen, 0, 107, Short.MAX_VALUE)
                            .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(4, 4, 4)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel9)
                            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel4Layout.createSequentialGroup()
                                    .add(31, 31, 31)
                                    .add(jLabel1))
                                .add(jLblEmpresa)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jCboDestino, 0, 101, Short.MAX_VALUE)
                            .add(jCboEmpresas, 0, 101, Short.MAX_VALUE)
                            .add(jTxtHora, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                    .add(jCboServicio, 0, 279, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblCiudadVenta)
                    .add(jCboCiudadVenta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblEmpresa)
                    .add(jCboEmpresas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(jCboOrigen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel9)
                        .add(jCboDestino, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel11)
                        .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(java.awt.Color.black);
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de Corridas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), java.awt.Color.white)); // NOI18N
        jPanel6.setFocusable(false);

        jScpCorridas.setBackground(java.awt.Color.black);
        jScpCorridas.setFont(new java.awt.Font("Tahoma", 0, 10));

        jTblCorridas.setBackground(java.awt.Color.black);
        jTblCorridas.setFont(new java.awt.Font("Tahoma", 1, 15));
        jTblCorridas.setForeground(java.awt.Color.white);
        jTblCorridas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblCorridas.setModel(xCorridas);
        jTblCorridas.setName("jtblListadoCorridas"); // NOI18N
        jTblCorridas.setRowMargin(10);
        jTblCorridas.setShowVerticalLines(false);
        jTblCorridas.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"none");
        jTblCorridas.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"none");
        jTblCorridas.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),"none");
        jTblCorridas.getTableHeader().setReorderingAllowed(false);
        jTblCorridas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTblCorridas.setFocusTraversalKeysEnabled(false);
        jTblCorridas.setDefaultRenderer(Object.class, new colorCorridasPorEmpresaRenderer());
        jTblCorridas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTblCorridasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTblCorridasFocusLost(evt);
            }
        });
        jTblCorridas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblCorridasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblCorridasKeyReleased(evt);
            }
        });
        jScpCorridas.setViewportView(jTblCorridas);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpCorridas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScpCorridas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );

        jPnlVtaActual.setBackground(java.awt.Color.black);
        jPnlVtaActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta Actual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), java.awt.Color.white)); // NOI18N
        jPnlVtaActual.setFocusable(false);

        jScpVtaActual.setBackground(java.awt.Color.black);
        jScpVtaActual.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jScpVtaActualComponentShown(evt);
            }
        });

        jTblVtaActual.setBackground(java.awt.Color.black);
        jTblVtaActual.setFont(new java.awt.Font("Tahoma", 1, 15));
        jTblVtaActual.setForeground(java.awt.Color.white);
        jTblVtaActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblVtaActual.setModel(xVtaActual);
        jTblVtaActual.setFocusTraversalKeysEnabled(false);
        jTblVtaActual.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"none");
        jTblVtaActual.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"none");
        jTblVtaActual.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        //jTblVtaActual.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),"none");
        //jTblVtaActual.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"none");
        jTblVtaActual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTblVtaActual.setRowSelectionAllowed(false);
        jTblVtaActual.setColumnSelectionAllowed(false);
        jTblVtaActual.setCellSelectionEnabled(true);
        jTblVtaActual.getTableHeader().setReorderingAllowed(false);
        jTblVtaActual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTblVtaActualFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTblVtaActualFocusLost(evt);
            }
        });
        jTblVtaActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblVtaActualKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblVtaActualKeyReleased(evt);
            }
        });
        jScpVtaActual.setViewportView(jTblVtaActual);

        jLblTotalVtaActual.setFont(new java.awt.Font("Arial Black", 1, 14));
        jLblTotalVtaActual.setForeground(java.awt.Color.green);
        jLblTotalVtaActual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLblTotalVtaActual.setText("Total: $0.00");
        jLblTotalVtaActual.setFocusable(false);

        jScpCH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambio Horario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N
        jScpCH.setFocusable(false);

        jTblCH.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTblCH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblCH.setModel(xCH);
        jTblCH.setFocusable(false);
        jScpCH.setViewportView(jTblCH);

        jScpBO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Boleto Original", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N
        jScpBO.setFocusable(false);

        jTblBO.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTblBO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblBO.setModel(xBO);
        jTblBO.setFocusable(false);
        jScpBO.setViewportView(jTblBO);

        org.jdesktop.layout.GroupLayout jPnlCHorarioLayout = new org.jdesktop.layout.GroupLayout(jPnlCHorario);
        jPnlCHorario.setLayout(jPnlCHorarioLayout);
        jPnlCHorarioLayout.setHorizontalGroup(
            jPnlCHorarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpCH, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
            .add(jScpBO, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        jPnlCHorarioLayout.setVerticalGroup(
            jPnlCHorarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlCHorarioLayout.createSequentialGroup()
                .add(jScpBO, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpCH, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de persona autorizada para canjear venta referenciada:");

        jtxt_personaAutorizada.setFont(new java.awt.Font("Tahoma", 1, 11));
        jtxt_personaAutorizada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_personaAutorizadaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_personaAutorizadaFocusLost(evt);
            }
        });
        jtxt_personaAutorizada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_personaAutorizadaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_personaAutorizadaKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPnlVtaActualLayout = new org.jdesktop.layout.GroupLayout(jPnlVtaActual);
        jPnlVtaActual.setLayout(jPnlVtaActualLayout);
        jPnlVtaActualLayout.setHorizontalGroup(
            jPnlVtaActualLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlVtaActualLayout.createSequentialGroup()
                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 398, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_personaAutorizada, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .add(14, 14, 14)
                .add(jLblTotalVtaActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPnlCHorario, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jScpVtaActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        jPnlVtaActualLayout.setVerticalGroup(
            jPnlVtaActualLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlVtaActualLayout.createSequentialGroup()
                .add(jPnlCHorario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpVtaActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlVtaActualLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jtxt_personaAutorizada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblTotalVtaActual)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setFocusable(false);

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLblBarraEstado.setForeground(new java.awt.Color(255, 255, 255));
        jLblBarraEstado.setText("<html> ok <br> no</html>");
        jLblBarraEstado.setFocusable(false);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                .add(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado)
        );

        jPnlRecoleccion.setBackground(java.awt.Color.black);
        jPnlRecoleccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recoleccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), java.awt.Color.white)); // NOI18N
        jPnlRecoleccion.setOpaque(false);

        jLblMonto.setFont(new java.awt.Font("Calibri", 1, 24));
        jLblMonto.setForeground(java.awt.Color.white);
        jLblMonto.setText("Monto:");

        jTxtMonto.setFont(new java.awt.Font("Calibri", 1, 18));
        jTxtMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtMonto.setText("1234567890");
        jTxtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtMontoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPnlRecoleccionLayout = new org.jdesktop.layout.GroupLayout(jPnlRecoleccion);
        jPnlRecoleccion.setLayout(jPnlRecoleccionLayout);
        jPnlRecoleccionLayout.setHorizontalGroup(
            jPnlRecoleccionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlRecoleccionLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLblMonto)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtMonto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPnlRecoleccionLayout.setVerticalGroup(
            jPnlRecoleccionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlRecoleccionLayout.createSequentialGroup()
                .add(33, 33, 33)
                .add(jPnlRecoleccionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTxtMonto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblMonto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jPanel5.setBackground(java.awt.Color.black);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen Venta Corrida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), java.awt.Color.white)); // NOI18N
        jPanel5.setFocusable(false);

        jScpResumenVtaCorrida.setFocusable(false);

        jTblResumenVtaCorrida.setBackground(java.awt.Color.black);
        jTblResumenVtaCorrida.setFont(new java.awt.Font("Tahoma", 1, 15));
        jTblResumenVtaCorrida.setForeground(java.awt.Color.white);
        jTblResumenVtaCorrida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblResumenVtaCorrida.setModel(xResumenVtaCorrida);
        jTblResumenVtaCorrida.setFocusable(false);
        jTblResumenVtaCorrida.setFocusTraversalKeysEnabled(false);
        jTblResumenVtaCorrida.getTableHeader().setReorderingAllowed(false);
        jTblResumenVtaCorrida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScpResumenVtaCorrida.setViewportView(jTblResumenVtaCorrida);

        jLblTarifaAdulto.setFont(new java.awt.Font("Arial Black", 1, 10));
        jLblTarifaAdulto.setForeground(java.awt.Color.white);
        jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color=ffff00>$0.00</font></html>");
        jLblTarifaAdulto.setFocusable(false);
        jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$0.00</font></html>");

        jLblMultiCast.setBackground(java.awt.Color.green);
        jLblMultiCast.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLblMultiCast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblMultiCast.setOpaque(true);

        jLblTx.setFont(new java.awt.Font("Arial Black", 1, 14));
        jLblTx.setForeground(java.awt.Color.green);
        jLblTx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblTx.setText("jLabel3");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jLblTarifaAdulto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblTx, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .add(1, 1, 1)
                .add(jLblMultiCast, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScpResumenVtaCorrida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .add(jScpResumenVtaCorrida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 113, Short.MAX_VALUE)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLblTarifaAdulto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLblTx)
                        .add(jLblMultiCast, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPnlRecoleccion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPnlVtaActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(15, 15, 15))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, 0))
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPnlRecoleccion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPnlVtaActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScpVtaActualComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScpVtaActualComponentShown
        AnchoColumnasVtaActual2();
    }//GEN-LAST:event_jScpVtaActualComponentShown

    private void jTxtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtMontoKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            System.out.println("ENTER Antes de recolectar");
            Recolectar();
            System.out.println("Saliendo de recoleccion (ENTER)");
        } else{
            if(evt.getKeyCode()==evt.VK_ESCAPE){
                if(this.getResultado()==1) ;
                else if(this.getResultado()==2){
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Recoleccion no realizada!", "Contacte al administrador del sistema.", Color.RED);
                    finalizarSesionVenta();
                }
                jPnlRecoleccion.setVisible(false);
                jPanel4.setVisible(true);
                jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                if(sesionVenta.getUserCon().getAplicacionVenta()) this.jLblTx.setText("*VENTA*");
                else  this.jLblTx.setText("*CALL CENTER*");
            }
        }
    }//GEN-LAST:event_jTxtMontoKeyPressed

    private void jtxt_personaAutorizadaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_personaAutorizadaFocusLost
        jtxt_personaAutorizada.setBackground( Color.WHITE);
        jLblBarraEstado.setText(textoBarraF);
    }//GEN-LAST:event_jtxt_personaAutorizadaFocusLost

    private void jtxt_personaAutorizadaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_personaAutorizadaFocusGained
        jtxt_personaAutorizada.setBackground(new Color(184,207,229));
        this.textoBarraF = jLblBarraEstado.getText();
        jLblBarraEstado.setText(mensajes.getMensajeVta(23));
    }//GEN-LAST:event_jtxt_personaAutorizadaFocusGained

    
    private boolean validaNombre(String nombre){
       String sCadenaSinBlancos = "";;
        for (int x=0; x < nombre.length(); x++) {
          if (nombre.charAt(x) != ' ')
            sCadenaSinBlancos += nombre.charAt(x);
        }
        boolean signos = false;
       for (int x=0; x < sCadenaSinBlancos.length(); x++) {
          if(!Character.isLetter(sCadenaSinBlancos.charAt(x)))
          {
              signos = true;
              break;
          }
        }
        
        if(signos)
            return false;

        if(sCadenaSinBlancos.length()<4)
            return false;
        else
            return true;
    }
    
    private void jtxt_personaAutorizadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_personaAutorizadaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
                if(!validaNombre(jtxt_personaAutorizada.getText()))
                {
                    DialogoAceptar.mostrarDialogo("Faltan datos.", "<html>Debes escribir un nombre autorizado valido para las ventas referenciadas.</html>", Color.RED);
                    return;
                }
                xVtaActual.setValueAt(jtxt_personaAutorizada.getText(), jTblVtaActual.getSelectedRow(), 30);
                Boletos[jTblVtaActual.getSelectedRow()][29]="F";
                Boletos[jTblVtaActual.getSelectedRow()][30]=jtxt_personaAutorizada.getText();
                jtxt_personaAutorizada.setEnabled(false);
                jtxt_personaAutorizada.setEditable(false);
                nombreAutorizado = true;
                jTblVtaActual.setEnabled(true);
                jTblVtaActual.setColumnSelectionInterval(jTblVtaActual.getSelectedRow(),3);
                jTblVtaActual.editCellAt(jTblVtaActual.getSelectedRow(),3);
                jTblVtaActual.requestFocus();
        }

        if(evt.getKeyCode() == evt.VK_ESCAPE)
        {   
                        jTblVtaActual.setEnabled(true);
                        xVtaActual.setValueAt("N", jTblVtaActual.getSelectedRow(), 29);
                        Boletos[jTblVtaActual.getSelectedRow()][29]="N";
                        Boletos[jTblVtaActual.getSelectedRow()][30]="";
                        xVtaActual.setValueAt("",jTblVtaActual.getSelectedRow(), 30);
                        boolean hayF = false;
                        for(int i =0; i<jTblVtaActual.getRowCount();i++)
                        {
                            if(xVtaActual.getValueAt(i, 29).toString().equals("F"))
                            {
                                hayF = true;
                                break;
                            }
                        }
                        if(!hayF)
                        {
                            jtxt_personaAutorizada.setText("");
                            jtxt_personaAutorizada.setEnabled(false);
                            jtxt_personaAutorizada.setEditable(false);
                            nombreAutorizado = false;
                        }
                    jTblVtaActual.setColumnSelectionInterval(jTblVtaActual.getSelectedRow(),3);
                    jTblVtaActual.editCellAt(jTblVtaActual.getSelectedRow(),3);
                    jTblVtaActual.requestFocus();
                        
        }
    }//GEN-LAST:event_jtxt_personaAutorizadaKeyPressed

    private void jtxt_personaAutorizadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_personaAutorizadaKeyReleased

    }//GEN-LAST:event_jtxt_personaAutorizadaKeyReleased

    private void jTblCorridasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTblCorridasFocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTblCorridasFocusGained

    private void jTblCorridasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTblCorridasFocusLost
// TODO add your handling code here:
        if(jTblCorridas.getRowCount()>0 && vengoDePantallaCobro && jTblCorridas.isEnabled()){ 
            vengoDePantallaCobro=false;
            jTblCorridas.requestFocusInWindow(); 
        }
        else{
            if(!jTblCorridas.isEnabled()) vengoDePantallaCobro=false;
        }
    }//GEN-LAST:event_jTblCorridasFocusLost

    private void jTxtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFechaFocusLost
// TODO add your handling code here:
        jTxtFecha.setBackground(ARENA);
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jTxtFechaFocusLost

    private void jCboEmpresasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboEmpresasFocusLost
// TODO add your handling code here:
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboEmpresasFocusLost

    private void jCboCiudadVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboCiudadVentaFocusLost
// TODO add your handling code here:
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboCiudadVentaFocusLost

    private void jCboDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboDestinoFocusLost
// TODO add your handling code here:
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboDestinoFocusLost

    private void jCboOrigenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboOrigenFocusLost
// TODO add your handling code here:
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboOrigenFocusLost

    private void jTxtHoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtHoraFocusLost
// TODO add your handling code here:
        jTxtHora.setBackground(ARENA);
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jTxtHoraFocusLost

    private void jCboServicioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboServicioFocusLost
// TODO add your handling code here:
        if(vengoDeCobro){ 
            vengoDeCobro=false;
            jCboServicio.requestFocusInWindow(); 
        }
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboServicioFocusLost

    private void jTblVtaActualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTblVtaActualFocusLost
// TODO add your handling code here:
        excluyeTeclasVtaActual=false;
    }//GEN-LAST:event_jTblVtaActualFocusLost

    private void jTblVtaActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblVtaActualKeyReleased
// TODO add your handling code here:
        int rowsel=jTblVtaActual.getSelectedRow();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ENTER:
                    jTblVtaActual.setColumnSelectionInterval(3,3);
                break;
            case KeyEvent.VK_RIGHT:
                 if(jTblVtaActual.getSelectedColumn()>=6 && jTblVtaActual.getSelectedColumn()<=28)
                     jTblVtaActual.setColumnSelectionInterval(29,29);
                break;
            case KeyEvent.VK_LEFT:
                 if(jTblVtaActual.getSelectedColumn()>=6 && jTblVtaActual.getSelectedColumn()<=28)
                     jTblVtaActual.setColumnSelectionInterval(5,5);
                break;
        }
    }//GEN-LAST:event_jTblVtaActualKeyReleased

    private void jTxtHoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtHoraFocusGained
// TODO add your handling code here:
        jTxtHora.setBackground(xorColorFondo);
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jTxtHoraFocusGained

    private void jTxtFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFechaFocusGained
// TODO add your handling code here:
        jTxtFecha.setBackground(xorColorFondo);
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jTxtFechaFocusGained

    private void jCboDestinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboDestinoFocusGained
// TODO add your handling code here:
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboDestinoFocusGained

    private void jCboOrigenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboOrigenFocusGained
// TODO add your handling code here:
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboOrigenFocusGained

    private void jCboEmpresasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboEmpresasFocusGained
// TODO add your handling code here:
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboEmpresasFocusGained

    private void jCboCiudadVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboCiudadVentaFocusGained
// TODO add your handling code here:
        controlMouseCriterio();
        excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboCiudadVentaFocusGained

    private void jCboServicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboServicioFocusGained
// TODO add your handling code here:
       controlMouseCriterio();
       excluyeTeclasCriterios=false;
    }//GEN-LAST:event_jCboServicioFocusGained

    private void jTblVtaActualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTblVtaActualFocusGained
// TODO add your handling code here:
        excluyeTeclasVtaActual = false;
    }//GEN-LAST:event_jTblVtaActualFocusGained

    private void jTxtTipoPasajeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtTipoPasajeFocusLost
// TODO add your handling code here:
        jTxtTipoPasaje.setBackground(ARENA);
    }//GEN-LAST:event_jTxtTipoPasajeFocusLost

    private void jTxtTipoPasajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtTipoPasajeFocusGained
// TODO add your handling code here:
        jTxtTipoPasaje.setBackground(xorColorFondo);
    }//GEN-LAST:event_jTxtTipoPasajeFocusGained

    private void jTxtAsientosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtAsientosFocusLost
// TODO add your handling code here:
        jTxtAsientos.setBackground(ARENA);
    }//GEN-LAST:event_jTxtAsientosFocusLost

    private void jTxtAsientosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtAsientosFocusGained
// TODO add your handling code here:
        jTxtAsientos.setBackground(xorColorFondo);
        jTxtAsientos.selectAll();
    }//GEN-LAST:event_jTxtAsientosFocusGained

    private void jCboEmpresasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboEmpresasKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                   case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }

                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        this.transaccionAux=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_F4: 
                CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1:
                controlVentana(evt); break;
            case KeyEvent.VK_RIGHT: 
                    ubiCrit = uCtOrigen;
                    jCboOrigen.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                    ubiCrit = uCtCiudadOrigen;
                    jCboCiudadVenta.requestFocusInWindow();
                break;
            case KeyEvent.VK_ENTER:
                    if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                        sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                        filtroInicial();
                        this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                    }
                    if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                        EjecutaConsulta();
                    break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                agregarPanelRecoleccion(); break;
            case KeyEvent.VK_F6: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;//VK_F9
            case KeyEvent.VK_F11: if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;VenderReservacion(); break;
            case KeyEvent.VK_F12:// if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;CambiarHorarioBoleto(); break;
           // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;


        }
    }//GEN-LAST:event_jCboEmpresasKeyPressed

    private void jCboCiudadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboCiudadVentaKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    else ventaProductor();
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        this.transaccionAux=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1: controlVentana(evt); break;
            case KeyEvent.VK_RIGHT: 
                    if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                        sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                        filtroInicial();
                        this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                    }
                    if(jCboEmpresas.isVisible()){
                        ubiCrit = uCtEmpresa;
                        jCboEmpresas.requestFocusInWindow();
                    }
                    else{
                        ubiCrit = uCtOrigen;
                        jCboOrigen.requestFocusInWindow();
                    }
                break;
            case KeyEvent.VK_LEFT:
                    if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                        sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                        filtroInicial();
                        this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                    }
                    ubiCrit = uCtServicio;
                    jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_ENTER:
                    if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                        sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                        filtroInicial();
                        this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                    }
                    if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                        EjecutaConsulta();
                    break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;agregarPanelRecoleccion(); break;
            case KeyEvent.VK_F6: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11:if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderReservacion(); break;
            case KeyEvent.VK_F12: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;CambiarHorarioBoleto(); break;
                // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jCboCiudadVentaKeyPressed

    private void SeleccionaServicio(String letra){
        int i=indiceServicio;
        do{
            if(jCboServicio.getItemAt(indiceServicio).toString().substring(0,1).equals(letra)){
                jCboServicio.setSelectedIndex(indiceServicio);
                indiceServicio=(indiceServicio+1)%jCboServicio.getItemCount();
                return;
            }
            indiceServicio=(indiceServicio+1)%jCboServicio.getItemCount();
        }while(indiceServicio!=i);
    }
    
    private void jCboDestinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboDestinoKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                 case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }

                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        this.transaccionAux=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_RIGHT: 
                if(jTxtFecha.isEnabled()){
                    ubiCrit = uCtFecha;
                    jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow();
                }
                else{
                    ubiCrit = uCtServicio;
                    jCboServicio.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_LEFT:
                ubiCrit = uCtOrigen; jCboOrigen.requestFocusInWindow(); break;
            case KeyEvent.VK_ENTER:
                if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                    EjecutaConsulta();
                break;
            case KeyEvent.VK_F4:
                    CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1:
                controlVentana(evt); break;
            case KeyEvent.VK_F5: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;agregarPanelRecoleccion(); break; // ojo
            case KeyEvent.VK_F6: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11:if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderReservacion(); break;
            case KeyEvent.VK_F12: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;CambiarHorarioBoleto(); break;
                    // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jCboDestinoKeyPressed

    private void jTxtTipoPasajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtTipoPasajeKeyPressed
    // TODO add your handling code here:
        if(!ConsultaOcupacionX(true, false, false, false)) return;
        int noAsiento, i;
        String resultado="";
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                int rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E");
                if(rSP!=0){
                    this.getToolkit().beep();
                    if(rSP!=-1){
                        DialogoAceptar.mostrarDialogo("TMS Venta","Asiento "+rSP+" bloqueado", Color.RED);
                    }
                    else{
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.", Color.RED);
                        return;
                    }
                }
                jTxtAsientos.setText("");
                jTxtTipoPasaje.setText("");
                jLblBarraEstado.setText(mensajes.getMensajeVta(3));
                BoletoAVender=null;
                TiposAsientos=null;
                this.ubiCompo = ucAsientos;
                abledComponents(jTxtAsientos);
                jTxtAsientos.requestFocusInWindow();
                enabledComponents(jTxtTipoPasaje);
            break;
            case KeyEvent.VK_F1:
                if(sesionVenta.isBolRedCer())
                {
                     //corridaIdRedCerrIda= this.listaCorridas[fila][6].toString();
                     //desloquea los asientos de ida
                     rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, null, "E",sesionVenta.getDblinkRedIda());
                     //desbloquea los asientos de regreso
                     if(rSP==0)
                       rSP=sesionVenta._OcuparAsientosSPReCer(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E",dblinkRedRegreso);
                }
                else
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E");
                if(rSP!=0){
                    this.getToolkit().beep();
                    if(rSP!=-1){
                        DialogoAceptar.mostrarDialogo("TMS Venta","Asiento "+rSP+" bloqueado.", Color.RED);
                    }
                    else{
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.",Color.RED);
                        return;
                    }
                }
                jTxtAsientos.setText("");
                jTxtTipoPasaje.setText("");
                BoletoAVender = null;
                TiposAsientos = null;
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                jCboServicio.requestFocusInWindow();
            break;
            case KeyEvent.VK_ENTER: 
                if(!ValidaSesionVenta()) return;
                if(ValidaTipoPasajero()){
                    // UTILIZAMOS SP PARA OCUPAR TIPOS DE PASAJE
                    sesionVenta.armaCadenaTiposPasaje(TiposAsientos);
                    // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "A");
                    System.out.println("SP_TiposPasaje "+rSP+" - "+sesionVenta.getValorTipo());
                    if(rSP!=0){
                        this.getToolkit().beep();
                        if(rSP!=-1)
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No hay suficientes lugares disponibles<br>para tipo de pasajero <font color=ff0000>"+sesionVenta.getValorTipo()+"</font>.</html>", Color.RED);
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.",Color.RED);
                        jTxtTipoPasaje.selectAll();
                        return;
                    }
                                        
                    this.ubiCompo=ucVtaActual;
                    ConstruyeBoleto("S");
                    //CalculaResumen(0, false);
                    if(sesionVenta.getUserCon().getAplicacionVenta())jLblBarraEstado.setText(mensajes.getMensajeVta(5));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(5));
                    jTblVtaActual.setColumnSelectionInterval(3,3);
                    jTblVtaActual.setRowSelectionInterval(0,0);
                    jTblVtaActual.requestFocusInWindow();
                    enabledComponents(jTxtTipoPasaje);
                }
            break;
            case KeyEvent.VK_F12:
                if(sesionVenta.isBolRedCer()) return;
                //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!ValidaSesionVenta()) return;
                if(!existeCorridaRegreso()) return;
                if(ValidaTipoPasajero()){
                    // UTILIZAMOS SP PARA OCUPAR TIPOS DE PASAJE
                    sesionVenta.armaCadenaTiposPasaje(TiposAsientos);
                    // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "A");
                    System.out.println("SP_TiposPasaje "+rSP+" - "+sesionVenta.getValorTipo());
                    if(rSP!=0){
                        this.getToolkit().beep();
                        if(rSP!=-1)
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>"+rSP+" tipo(s)  de pasajero "+sesionVenta.getValorTipo()+"<br>no disponibles.</html>", Color.RED);
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado",Color.RED);
                        jTxtTipoPasaje.selectAll();
                        return;
                    }
                    this.ubiCompo=ucVtaActual;
                    ConstruyeBoleto("R");
                    //CalculaResumen(0, false);
                    this.ambienteBoletosRedondos = true;
                    jLblBarraEstado.setText(mensajes.getMensajeVta(51));
                    jTblVtaActual.setColumnSelectionInterval(3,3);
                    jTblVtaActual.setRowSelectionInterval(0,0);
                    jTblVtaActual.requestFocusInWindow();
                    enabledComponents(jTxtTipoPasaje);
                    sesionVenta.setBolRedCer(false);
                    if(evt.isControlDown()){
                        sesionVenta.setBolRedCer(true);
                       ciudadOrigenRedCerr = jCboCiudadVenta.getSelectedItem().toString();
                       origenRedCerr=((jCboOrigen.getSelectedItem().toString().equals("TODOS"))?this.listaCorridas[fila][4].toString():jCboOrigen.getSelectedItem().toString());//jCboOrigen.getSelectedItem().toString();
                       destinoRedCerr=((jCboDestino.getSelectedItem().toString().equals("TODOS"))?this.listaCorridas[fila][5].toString():jCboDestino.getSelectedItem().toString());
                       serviocioRedCerr = this.listaCorridas[fila][3].toString();
                       asientosRedCerrIda = sesionVenta.getCadenaAsientos();
                       corridaIdRedCerrIda= this.listaCorridas[fila][6].toString();
                       rutaIdRedondoIda= Long.valueOf(this.listaCorridas[fila][8].toString());
                       sesionVenta.setDblinkRedIda(sesionVenta.getDBLink());
                       sesionVenta.setOrigenCorridaRedIda(jCboCiudadVenta.getSelectedItem().toString());
                       sesionVenta.setOrigenRedIda(jCboOrigen.getSelectedItem().toString());
                        abledComponents(jCboCiudadVenta);
                        abledComponents(jTxtFecha);
                        abledComponents(jTxtHora);
                        abledComponents(jTxtAsientos);
                        abledComponents(jTblCorridas);
                        jCboCiudadVenta.setSelectedItem(destinoRedCerr);
                        if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                            sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                            filtroInicial();
                            this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                        }
                        jCboOrigen.setSelectedItem(destinoRedCerr);
                        jCboDestino.setSelectedItem(origenRedCerr);
                        //System.out.println("serviocioRedCerr: "+serviocioRedCerr);
                        //System.out.println("getServicioLetra: "+sesionVenta.getServicioLetra(serviocioRedCerr));
                        //System.out.println("completo: "+sesionVenta.getServicioLetra(serviocioRedCerr)+"-"+serviocioRedCerr);
                        jCboServicio.setSelectedItem(sesionVenta.getServicioLetra(serviocioRedCerr));
                        jTxtFecha.requestFocusInWindow();
                    }

                }
            break;
                // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jTxtTipoPasajeKeyPressed

    private void jTblCorridasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblCorridasKeyPressed
// TODO add your handling code here:
        try{
        fila  = jTblCorridas.getSelectedRow();
        if(fila<0 && jTblCorridas.getRowCount()>0){
            System.out.println("CASO UNO");
            fila=0;
            jTblCorridas.setRowSelectionInterval(fila, fila);
            jTblCorridas.requestFocusInWindow();
        }
        else{
            if(jTblCorridas.getRowCount()<=0){
                System.out.println("CASO DOS");
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                //PlantillaResumenDefault();
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                jCboServicio.requestFocusInWindow();
                return;
            }
        }
        time_now = new Date().getTime();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                int rSP=0;
                if(sesionVenta.isBolRedCer())
                {
                    System.out.println("******************* Desbloquea Asientos IDA ********************: ");
                    System.out.println("DBLinkIda: "+sesionVenta.getDblinkRedIda());
                    System.out.println("DBLinkRegreso: "+dblinkRedRegreso);
                     //desloquea los asientos de ida
                     rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, sesionVenta.getCadenaTiposPasaje(), "P",sesionVenta.getDblinkRedIda());
                     if(rSP==0)
                            rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, null, "E",sesionVenta.getDblinkRedIda());

                }
                if(this.transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)){
                    abledComponentsCriterios();
                    abledComponents(jTblCorridas);
                    desHabilitarCriterios(2);
                    jTxtFecha.selectAll();
                    if(transaccion.equals(txCANJE_BA)) jLblBarraEstado.setText(mensajes.getMensajeVta(19));
                    jTxtFecha.requestFocusInWindow();
                    return;
                }
                filtroInicialX();
                jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_ENTER:
                if(!ValidaSesionVenta()) return;
                if(sesionVenta.getUserCon().getAplicacionVenta())
                if(!sesionVenta.getUserCon().getFolioUnico() && sesionVenta.getUserCon().getAplicacionVenta() &&  !sesionVenta.isVtaBolBco(this.nombreEmpresa))
                {
                    long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                    if(f1>f2){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Boletos agotados para venta!", "Realice refoliado de boletos para la empresa "+this.nombreEmpresa+".", Color.RED);
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        filtroInicialX();
                        if(sesionVenta.getUserCon().getAplicacionVenta())
                            setLabelFolioX("-",this.nombreEmpresa);
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        jCboServicio.requestFocusInWindow();
                        return;
                    }else{
                        if(f1>f2-sesionVenta.getUserCon().getBolNvtImp())
                            DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>Solo restan "+ ((f2-f1)+1)+ " boletos<br>para la empresa "+this.nombreEmpresa+".</html>", Color.RED);
                    }
                }
                
                if(fila<0){
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Importante(1)!","La corrida ya no esta abierta para venta.", Color.RED);
                    xCorridas.setDataVector(null, encCorridas);
                    AnchoColumnasCorridas();
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    jCboServicio.requestFocusInWindow();
                    return;
                }
                
                //compoTarifa = listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";
                //VAGL&&RAI 09/02/2009
                compoTarifa = listaCorridas[fila][8].toString()+ listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";
                System.out.println("compoTarifa(Enter): "+compoTarifa);
                TarifaServicio=sesionVenta.getTarifaTramo(compoTarifa,0);
                //System.out.println("TarifaServicio: "+TarifaServicio);
                if(TarifaServicio==0) return;
                sesionVenta.getUserCon().setServicioTarifaSencillo(TarifaServicio);
                
                //compoTarifa = listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"RED";
                compoTarifa = listaCorridas[fila][8].toString() + listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"RED";
                //VAGL&&RAI 09/02/2009
                TarifaServicio=sesionVenta.getTarifaTramo(compoTarifa,-1);
                if(TarifaServicio==0) sesionVenta.getUserCon().setServicioTarifaRedondo(sesionVenta.getUserCon().getServicioTarifaSencillo()*2);
                else sesionVenta.getUserCon().setServicioTarifaRedondo(TarifaServicio);
                
                // ASEGURA QUE LA PLANTILLA ES CORRECTA CON LA CORRIDA
                int xa=0;
                strCorridaActualId = this.listaCorridas[fila][6].toString();
                jThdConsultaCorrida.setCorridaActualId(strCorridaActualId);
                int countWhile = 0;
                while(!this.listaCorridas[fila][6].toString().equals(this.corridaActual[63].toString())){
                    countWhile++;
                    if (countWhile >= 1000){
                        return;
                    }
               }
               System.out.println("La ruta es: "+this.listaCorridas[fila][8]);
               sesionVenta.setTiposPasajeLealtad(Integer.valueOf(this.listaCorridas[fila][8].toString()),jCboOrigen.getSelectedItem().toString());
               
                //if(( this.listaCorridas[fila][3].toString().equals("DIRECTO ECONOMICO") || this.listaCorridas[fila][3].toString().equals("PULLMAN PRIMERA CLASE")) && (this.transaccion.equals(txCANJE_BA) || this.transaccion.equals(txCHORARIO)) )                
                String prom = this.listaCorridas[fila][3]+"-"+this.listaCorridas[fila][4]+"-"+this.listaCorridas[fila][5];
                // if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218") || this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226")) && (this.transaccion.equals(txCANJE_BA) || this.transaccion.equals(txCHORARIO)) && sesionVenta.getTmsVtaFacade().getPromocionVigente() )                
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")  || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")  )  && (this.transaccion.equals(txCANJE_BA) || this.transaccion.equals(txCHORARIO)) && sesionVenta.getTmsVtaFacade().getPromocionVigente() )
                {
                    System.out.println("el costo original es: "+montoPromocionVA);
                    System.out.println("el costo seleccionado es: (Sencillo)"+sesionVenta.getUserCon().getServicioTarifaSencillo());
                    System.out.println("el costo seleccionado es: (Redondo)"+sesionVenta.getUserCon().getServicioTarifaRedondo());
                    System.out.println("el costo promocion seleccionador seria: )"+(sesionVenta.getUserCon().getServicioTarifaRedondo()- sesionVenta.getUserCon().getServicioTarifaSencillo()));
                    //"Los boletos de promoción solo pueden ser canjeados los Miercoles y Jueves de Junio";
//                    if((sesionVenta.getUserCon().getServicioTarifaRedondo()/2)<=montoPromocionVA)
                        long tipoId = jTxtTipoPasaje.getTipopasajeid(tipoPasajeroVA,Long.valueOf(this.listaCorridas[fila][8].toString()));   
                        double tDescuento = jTxtTipoPasaje.getDescuento(tipoId);
                        System.out.println("Descueno: "+tDescuento);
                        //montoVA,TarifaRedondo,descuento
                        //23_julio_2009 ya se termino la promocion y subieron tarifas por eso se deshabilita
                    String dia="";
                    try {
                       dia = formatoDia.format(formatoFecha.parse(this.listaCorridas[fila][1].toString())).toUpperCase();
                                        } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Dia de la Corrida: "+dia);
                    if(sesionVenta.getAplicaPromocion(montoPromocionVA,sesionVenta.getUserCon().getServicioTarifaRedondo(),tDescuento,sesionVenta.getUserCon().getServicioTarifaSencillo(),dia))
                    //if(montoPromocionVA >= (sesionVenta.getUserCon().getServicioTarifaRedondo()/2))
                        System.out.println("SI deja canjear el boleto");
                    else    
                    {
                        System.out.println("NO deja canjear el boleto");
                        if(this.transaccion.equals(txCANJE_BA))
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>Los boletos de promoción solo pueden ser canjeados<br> los dias Martes 26 de Febrero y 5 Y 12 de Marzo.</html>", Color.RED);
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>Los boletos de promoción solo se pueden cambiar de <br> horario los dias Martes 26 de Febrero y 5 Y 12 de Marzo.</html>", Color.RED);
//                        DialogoAceptar.mostrarDialogo("TMS Venta.", "Los boletos de promoción no se pueden cambiar de horario en esta corrida.", Color.RED);   
                        return;
                    }
                }
                //DialogoAceptar.mostrarDialogo("TMS Venta.", "Tipo de pasajero(s) no disponible.", Color.RED); 
                
                // EN CASO DE CANJE O CAMBIO DE HORARIO, DISPONE DE TIPO PASAJE
                if(this.transaccion.equals(txCANJE_BA) || this.transaccion.equals(txCHORARIO)){
                    int r, i; vTP=new Vector();
                    for(i=0; i<sesionVenta.getVariosTmsBoletosVentaTbl().size(); i++)
                        vTP.add(sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero());
                    r=sesionVenta.procesaTipoPasaje(this.corridaId, vTP, 1); // decrementa signo=1
                    if(r!=0){
                        this.getToolkit().beep();
                        if(r==1)
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Tipo de pasajero(s) no disponible.", Color.RED);
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.", Color.RED);
                        return;
                    }
                    sesionVenta.armaCadenaTiposPasaje(vTP);
                }
                //
                
               sesionVenta.strDestinoX = (jCboDestino.getSelectedItem().toString().equals("TODOS")? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString());
               SnAsiento = corridaActual[61].toString();
               sesionVenta.getUserCon().setSnAsiento(SnAsiento.toUpperCase().equals("N") ? false : true); 
               
               // CHECA SI EL BOLETO ES SIN NUMERO DE ASIENTO
                if(sesionVenta.getUserCon().getSnAsiento()){
                    jLblBarraEstado.setText("...");
                    CalculaResumen();
                    BoletoSinNumerodeAsiento();
                    return;
                }
                // CONTINUA BOLETO COMPLETO
                this.ubiCompo=ucAsientos;
                jLblBarraEstado.setText(mensajes.getMensajeVta(3));
                
                if(this.transaccion.equals(txCANJE_BA)) jLblBarraEstado.setText(mensajes.getMensajeVta(20));
                abledComponents(jTxtAsientos);
                jTxtAsientos.requestFocusInWindow();
                enabledComponents(jTblCorridas);
            break;
        }
        
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }//GEN-LAST:event_jTblCorridasKeyPressed

    private void jTblCorridasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblCorridasKeyReleased
// TODO add your handling code here:
        try{
        fila  = jTblCorridas.getSelectedRow();
        if(fila<0 && jTblCorridas.getRowCount()>0){
            System.out.println("CASO UNO");
            fila=0;
            jTblCorridas.setRowSelectionInterval(fila, fila);
            jTblCorridas.requestFocusInWindow();
        }
        else{
            if(jTblCorridas.getRowCount()<=0){
                System.out.println("CASO DOS");
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                //PlantillaResumenDefault();
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                jCboServicio.requestFocusInWindow();
                return;
            }
        }
        rndFila = fila;
        strCorridaActualId = this.listaCorridas[fila][6].toString();
        jThdConsultaCorrida.setCorridaActualId(strCorridaActualId);
        switch(evt.getKeyCode()){
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_DOWN: case KeyEvent.VK_PAGE_DOWN:
                    if(!ConsultaOcupacionX(true, false, true, false)) return;
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
                    if(!ConsultaOcupacionX(true, false, true, false)) return;
                break;
        }
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }//GEN-LAST:event_jTblCorridasKeyReleased
    
    private void jTblVtaActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblVtaActualKeyPressed
// TODO add your handling code here:
        String resultado;
        int rowsel = jTblVtaActual.getSelectedRow();
        if(rowsel>-1){
            switch(evt.getKeyCode()){
                case KeyEvent.VK_ENTER:
                    System.out.println("ENTRE A ENTER");
                    if(rowsel>0 && jTblVtaActual.getSelectedColumn()==3)
                        if(xVtaActual.getValueAt(rowsel-1,3)!=null && !xVtaActual.getValueAt(rowsel-1,3).toString().trim().equals(""))
                            xVtaActual.setValueAt(xVtaActual.getValueAt(rowsel-1,3),rowsel,3);
                break;
                case KeyEvent.VK_F8:
                    //System.out.println("Entra al F11..");
                    //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    ////if(transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    if(transaccion.equals(txCANJE_BA)) return;
//                    System.out.println("xVtaActual.getValueAt(rowsel, 7).toString() => "+xVtaActual.getValueAt(rowsel, 7).toString());
                     if(xVtaActual.getValueAt(rowsel, 29).toString().equals("N"))
                     {
                        xVtaActual.setValueAt("F", rowsel, 29);
                        Boletos[rowsel][29]="F";
                       if(!nombreAutorizado)
                       {
                            jTblVtaActual.setColumnSelectionInterval(rowsel,5);
                            jTblVtaActual.editCellAt(rowsel,5);
                            jTblVtaActual.setEnabled(false);
                            jtxt_personaAutorizada.setEnabled(true);
                            jtxt_personaAutorizada.setEditable(true);
                            jtxt_personaAutorizada.requestFocus();
                       }
                       else
                       {
                            for(int i =0; i<jTblVtaActual.getRowCount();i++)
                                if(xVtaActual.getValueAt(i, 29).toString().equals("F"))
                                {
                                    xVtaActual.setValueAt(jtxt_personaAutorizada.getText(),i, 30);
                                    Boletos[i][30]=jtxt_personaAutorizada.getText();
                                }
                       }
                     }
                     else
                     {
                        xVtaActual.setValueAt("N", rowsel, 29);
                        Boletos[rowsel][29]="N";
                        Boletos[rowsel][30]="";
                        xVtaActual.setValueAt("",rowsel, 30);
                        boolean hayF = false;
                        for(int i =0; i<jTblVtaActual.getRowCount();i++)
                        {
                            if(xVtaActual.getValueAt(i, 29).toString().equals("F"))
                            {
                                hayF = true;
                                break;
                            }
                        }
                        if(!hayF)
                        {
                            jtxt_personaAutorizada.setText("");
                            jtxt_personaAutorizada.setEnabled(false);
                            jtxt_personaAutorizada.setEditable(false);
                            nombreAutorizado = false;
                        }
                     } 
                    break;
                case KeyEvent.VK_F12: 
                    //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    if(transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    if(!ambienteBoletosRedondos) return;
                    double impBol=Double.parseDouble(jLblTotalVtaActual.getText().replace("Total: $"," ").replace(",",""));
                    double tarifaNueva, tarifaAnterior;
                    if(xVtaActual.getValueAt(rowsel, 6).toString().equals("R")){
                        System.out.println("Entra cuando es R");
                        xVtaActual.setValueAt("S", rowsel, 6);
                        System.out.println("jugandoImporteVenta["+rowsel+"]: "+jugandoImporteVenta[rowsel]);
                        if(jugandoImporteVentaPermiso[rowsel].equals("S"))
                            tarifaNueva=Math.ceil(sesionVenta.getUserCon().getServicioTarifaSencillo()-(jugandoImporteVenta[rowsel]*sesionVenta.getUserCon().getServicioTarifaSencillo()));
                        else
                        {
                            tarifaNueva=sesionVenta.getUserCon().getServicioTarifaSencillo()-(jugandoImporteVenta[rowsel]*sesionVenta.getUserCon().getServicioTarifaSencillo());
                            double d = (tarifaNueva - (Double.valueOf(""+tarifaNueva).longValue()) );
                            if(d > 0 && d != .50)                                
                                tarifaNueva =    Math.ceil(tarifaNueva);
                        }
                        
                        xVtaActual.setValueAt(tarifaNueva, rowsel, 5);
                        
                        double TTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo();
                        System.out.println("this.listaCorridas[fila][8]: "+this.listaCorridas[fila][8].toString());
                        System.out.println("xVtaActual.getValueAt(rowsel, 2): "+xVtaActual.getValueAt(rowsel, 2).toString());
                        
//ruta origina 
//		217
//
//ruta pruebas 
//		19
//        
                        if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218")  ||  this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226") || this.listaCorridas[fila][8].toString().equals("241") || this.listaCorridas[fila][8].toString().equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && xVtaActual.getValueAt(rowsel, 2).toString().equals("A") )
                             TTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.2);
                        System.out.println("TTarifa: "+TTarifa);
                        if(jugandoImporteVentaPermiso[rowsel].equals("S"))
                            tarifaAnterior=Math.ceil(TTarifa-(jugandoImporteVenta[rowsel]*TTarifa));
                        else
                        {
                            tarifaAnterior=TTarifa-(jugandoImporteVenta[rowsel]*TTarifa);
                            double d = (tarifaAnterior - (Double.valueOf(""+tarifaAnterior).longValue()) ) ;
                            if(d > 0 && d != .50)                                
                                tarifaAnterior =    Math.ceil(tarifaAnterior);
                        }
                         System.out.println("tarifaAnterior: "+tarifaAnterior);
                        if(sesionVenta.isRutaPromocionRedondoTTP(this.listaCorridas[fila][8].toString()) && !(""+Boletos[rowsel][2]).equals("A"))
                        {
                             System.out.println("Si entra a cambiar la tarifa de redondo TTP(F120)...");
                             System.out.println("getServicioTarifaSencillo(): "+sesionVenta.getUserCon().getServicioTarifaSencillo());
                             System.out.println("descuento: "+jugandoImporteVenta[rowsel]);
                             System.out.println("TipoPAsajero["+rowsel+"]: "+Boletos[rowsel][2]);
                            tarifaNueva = ((sesionVenta.getUserCon().getServicioTarifaSencillo() - (jugandoImporteVenta[rowsel] * sesionVenta.getUserCon().getServicioTarifaSencillo())));
                            Boletos[rowsel][5]=tarifaNueva;
                            Boletos[rowsel][6]="S";
                            Boletos[rowsel][31]="S";
                            System.out.println("impBol: "+impBol);
                            impBol = 0;
                            for(int j=0; j<Boletos.length;j++)
                                impBol += Double.valueOf(Boletos[j][5].toString());
                            System.out.println("impBol: "+impBol);
                        }
                        else
                        {
                            Boletos[rowsel][5]=tarifaNueva;
                            Boletos[rowsel][6]="S";
                            Boletos[rowsel][31]="S";
                            System.out.println("impBol: "+impBol);
                            System.out.println(""+impBol+"-"+tarifaAnterior+"+"+tarifaNueva+" = "+impBol);
                            impBol = impBol-tarifaAnterior+tarifaNueva;
                            System.out.println("impBol: "+impBol);
                        }
                    }
                    else{
                        System.out.println("Entra cuando es S");
                        xVtaActual.setValueAt("R", rowsel, 6);
                        double TTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo();
                        System.out.println("this.listaCorridas[fila][8]: "+this.listaCorridas[fila][8].toString());
                        System.out.println("xVtaActual.getValueAt(rowsel, 2): "+xVtaActual.getValueAt(rowsel, 2).toString());
                        
                        if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218")  ||  this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226") || this.listaCorridas[fila][8].toString().equals("241") || this.listaCorridas[fila][8].toString().equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && xVtaActual.getValueAt(rowsel, 2).toString().equals("A") )
                             TTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.2);
                        System.out.println("jugandoImporteVenta["+rowsel+"]= "+jugandoImporteVenta[rowsel]); 
                        System.out.println("TTarifa: "+TTarifa);    
                        if(jugandoImporteVentaPermiso[rowsel].equals("N"))
                            tarifaNueva=Math.ceil(TTarifa-(jugandoImporteVenta[rowsel]*TTarifa));
                        else
                        {
                            tarifaNueva=TTarifa-(jugandoImporteVenta[rowsel]*TTarifa);
                            double d = (tarifaNueva - (Double.valueOf(""+tarifaNueva).longValue()) ) ;
                            if(d > 0 && d != .50)                                
                                tarifaNueva =    Math.ceil(tarifaNueva);
                        }
                        System.out.println("tarifaNueva = "+tarifaNueva);
                        xVtaActual.setValueAt(tarifaNueva, rowsel, 5);
                        if(jugandoImporteVentaPermiso[rowsel].equals("S"))
                            tarifaAnterior=Math.ceil(sesionVenta.getUserCon().getServicioTarifaSencillo()-(jugandoImporteVenta[rowsel]*sesionVenta.getUserCon().getServicioTarifaSencillo()));
                        else
                        {
                            tarifaAnterior=sesionVenta.getUserCon().getServicioTarifaSencillo()-(jugandoImporteVenta[rowsel]*sesionVenta.getUserCon().getServicioTarifaSencillo());
                            double d = (tarifaAnterior - (Double.valueOf(""+tarifaAnterior).longValue()) ) ;
                            if(d > 0 && d != .50)                                
                                tarifaAnterior =    Math.ceil(tarifaAnterior);
                        }
                        if(sesionVenta.isRutaPromocionRedondoTTP(this.listaCorridas[fila][8].toString()) && !(""+Boletos[rowsel][2]).equals("A"))
                        {
                             System.out.println("Si entra a cambiar la tarifa de redondo TTP(F12)...");
                             System.out.println("getServicioTarifaSencillo(): "+sesionVenta.getUserCon().getServicioTarifaSencillo());
                             System.out.println("descuento: "+jugandoImporteVenta[rowsel]);
                             System.out.println("TipoPAsajero["+rowsel+"]: "+Boletos[rowsel][2]);
                            tarifaNueva = ((sesionVenta.getUserCon().getServicioTarifaSencillo() - (jugandoImporteVenta[rowsel] * sesionVenta.getUserCon().getServicioTarifaSencillo())) * 2);
                            System.out.println("tarifaNueva = "+tarifaNueva);
                            xVtaActual.setValueAt(tarifaNueva, rowsel, 5);
                            Boletos[rowsel][5]=tarifaNueva;
                            Boletos[rowsel][6]="R";
                            Boletos[rowsel][31]="R";
                            System.out.println("impBol: "+impBol);
                            impBol = 0;
                            for(int j=0; j<Boletos.length;j++)
                                impBol += Double.valueOf(Boletos[j][5].toString());
                            System.out.println("impBol: "+impBol);
                        }
                        else
                        {
                            Boletos[rowsel][5]=tarifaNueva;
                            Boletos[rowsel][6]="R";
                            Boletos[rowsel][31]="R";
                            System.out.println("impBol: "+impBol);
                            System.out.println(""+impBol+"-"+tarifaAnterior+"+"+tarifaNueva);
                            impBol = impBol-tarifaAnterior+tarifaNueva;
                            System.out.println("impBol: "+impBol);
                        }
                    }   
                    sesionVenta.setImporteVenta(impBol);
                    jLblTotalVtaActual.setText("Total: $"+sesionVenta.customFormat("##,##0.00",impBol));
                    break;
                case KeyEvent.VK_F10:
                   //sesionVenta.reiniciarLookup();
                   if(!this.sesionVenta.getUserCon().getAplicacionVenta())
                   {
                        if(!sesionVenta.getUserCon().getSysCobroBancario())
                        {
                            DialogoAceptar.mostrarDialogo("¡Configuración incorrecta!","<html>!La configuracion del cobro con tarjeta es incorrecta! </html>" , Color.RED); 
                            return;
                        }
                    for(int i=0; i<jTblVtaActual.getRowCount();i++)
                    {
                                
                        if(jTblVtaActual.getValueAt(i,29).toString().equals("N"))// && jTblVtaActual.getValueAt(i,30).toString().length()<4)
                        {
                            DialogoAceptar.mostrarDialogo("¡Datos incorrectos!","<html>!Solo puede vender boletos referenciados! </html>" , Color.RED); 
                            return;
                        }
                        if(jTblVtaActual.getValueAt(i,29).toString().equals("S") && jTblVtaActual.getValueAt(i,30).toString().length()<4)
                        {
                            DialogoAceptar.mostrarDialogo("¡Faltan datos!","<html>Debes introducir el documento de identificación <br> " +
                                    "con el que canjearon el boleto </html>", Color.RED); 
                            return;
                        }
                    }
                   }
                   else
                   {
                        for(int i=0; i<jTblVtaActual.getRowCount();i++)
                        {

                            if(jTblVtaActual.getValueAt(i,29).toString().equals("F") && !validaNombre(jTblVtaActual.getValueAt(i,30).toString()))
                            {
                                DialogoAceptar.mostrarDialogo("Faltan datos.", "<html>Debes escribir un nombre autorizado valido para las ventas referenciadas.</html>", Color.RED);
                                return;
                            }
                        }
                       
                   }
                    sesionVenta.setNombreAutorizado(jtxt_personaAutorizada.getText());
//                    nombreAutorizado = false;
//                    jtxt_personaAutorizada.setText("");
                   if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                   // if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    if(!ValidaSesionVenta()) return;
                    for(int i=0; i<jTblVtaActual.getRowCount(); i++){
                        if(xVtaActual.getValueAt(i,3)!=null && !xVtaActual.getValueAt(i,3).toString().equals(""))
                            Boletos[i][3] = xVtaActual.getValueAt(i,3).toString().toUpperCase();
                    }
                    if(!transaccion.equals(txCANJE_BA)) llenaNombres();
                    this.BoletosRedondos = null;
                    ChecaConstruyeRedondos();
                    if(BoletosRedondos!=null){
                      if(sesionVenta.getUserCon().getAplicacionVenta() && !sesionVenta.isVtaBolBco(this.nombreEmpresa))
                      {
                            long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                            long f3=(f2-f1)+1;
                            if(BoletosRedondos.length>f3){
                            this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>No tiene boletos suficientes para vender boleto redondo.<br>" +
                                    f3+" folio(s) disponible(s).</html>", Color.RED);
                                return;
                            }
                      }
                    }
                    if(sesionVenta.isBolRedCer())
                    {
                        sesionVenta.setOrigenCorridaRedReg(jCboCiudadVenta.getSelectedItem().toString());
                        sesionVenta.setOrigenRedReg(jCboOrigen.getSelectedItem().toString());
                    }
                    if(transaccion.equals(txCANJE_BA)){
                        if(!CanjeBA()) return; 
                    }
                    else{
                        if(!sesionVenta.ValidaFuncionUsuario("5004")){
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5004", "Vender Boleto");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()) return;
                        }
                        EjecutaCobro();
                    }
                break;
                case KeyEvent.VK_ESCAPE:
                    nombreAutorizado = false;
                    jtxt_personaAutorizada.setText("");                    
                    if(transaccion.equals(txVENTA_BA)) return;
                    if(this.transaccion.equals(txCANJE_BA) && sesionVenta.getUserCon().getSnAsiento()) return;
                    System.out.println("Presione ESC - desocupar (_OcuparAsientosSP)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
                    System.out.println("DBLink: "+sesionVenta.getDBLink());
                    int rSP=0;
                    if(!sesionVenta.getUserCon().getSnAsiento() && !transaccion.equals(txCANJE_BA))
                    {
                        if(sesionVenta.isBolRedCer())
                        {
                            System.out.println("DBLinkIda: "+sesionVenta.getDblinkRedIda());
                            System.out.println("DBLinkRegreso: "+dblinkRedRegreso);
                            System.out.println("******************* IDA ********************: ");
                             //desloquea los asientos de ida
                             rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, sesionVenta.getCadenaTiposPasaje(), "P",sesionVenta.getDblinkRedIda());
                             if(rSP==0)
                                    rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, null, "E",sesionVenta.getDblinkRedIda());
                                    //desbloquea los asientos de regreso
                                    System.out.println("******************* REGRESO ********************: ");
                                   rSP=sesionVenta._OcuparAsientosSPReCer(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "P",dblinkRedRegreso);
                                 if(rSP==0)
                                    rSP=sesionVenta._OcuparAsientosSPReCer(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E",dblinkRedRegreso);
                        }
                        else
                            rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "P");
                     }
                    else{
                        if(!sesionVenta.getUserCon().getSnAsiento() && transaccion.equals(txCANJE_BA))
                             rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E");
                        else rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                    }
                    if(rSP!=0){
                        this.getToolkit().beep();
                        if(rSP!=-1){
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
                            jLblTotalVtaActual.setText("Total: $0.00");
                            Boletos=null;
                            ambienteBoletosRedondos=false;
                            ambienteVtaActualnormal();
                            jTxtTipoPasaje.setText("");
                            jTxtAsientos.setText("");
                            xCorridas.setDataVector(null, encCorridas);
                            AnchoColumnasCorridas();
                            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                            jTxtFecha.setText(hoyEs);
                            jTxtHora.setText("");
                            filtroInicialX();
                            jCboServicio.requestFocusInWindow();
                            return;
                        }
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.",Color.RED);
                        return;
                    }
                    //
                    jLblTotalVtaActual.setText("Total: $0.00");
                    Boletos=null;
                    ambienteBoletosRedondos=false;
                    if(sesionVenta.isBolRedCer())
                    {
                        Boletos=null;
                        jLblTotalVtaActual.setText("Total: $0.00");
                        ambienteBoletosRedondos=false;
                        ambienteVtaActualnormal();
                        jTxtTipoPasaje.setText("");
                        jTxtAsientos.setText("");
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        xVtaActual.setDataVector(null, encVtaActual);
                        AnchoColumnasVtaActual();
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        filtroInicialX();
                        jCboServicio.requestFocusInWindow();
                    }
                    sesionVenta.setBolRedCer(false);
                    //sesionVenta.setDBLink("");
                    if(transaccion.equals(txCANJE_BA)){
                        jTxtAsientos.setText("");
                        jTxtTipoPasaje.setText("");
                        jLblBarraEstado.setText(mensajes.getMensajeVta(3));
                        BoletoAVender=null;
                        TiposAsientos=null;
                        ambienteVtaActualnormal();
                        jLblBarraEstado.setText(mensajes.getMensajeVta(20));
                        abledComponents(jTxtAsientos);
                        jTxtAsientos.requestFocusInWindow();
                        return;
                    }
                        
                    if(sesionVenta.getUserCon().getSnAsiento()){
                        BoletoAVender=null;
                        TiposAsientos=null;
                        ambienteVtaActualnormal();
                        jLblBarraEstado.setText("...");
                        CalculaResumen();
                        BoletoSinNumerodeAsiento();
                        return;
                    }
                    
                    ambienteVtaActualnormal();
                    CalculaResumen();
                    if(sesionVenta.getUserCon().getAplicacionVenta())
                        MensajeAuxiliar="<html>" +
                               "Ingresar tipo pasajero: "+getTiposPasajeVenta()+" | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Boleto Redondo | " +
                               "<font color="+ColoresInterfaz.cHTML1+">Ctrl+F12</font> Boleto Redondo Cerrado" +
                               "</html>";
                    else
                        MensajeAuxiliar="<html>" +
                               "Ingresar tipo pasajero: "+getTiposPasajeVenta()+" | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Boleto Redondo | " +
                               "<font color="+ColoresInterfaz.cHTML1+">Ctrl+F12</font> Boleto Redondo Cerrado" +
                               "</html>";
                    jLblBarraEstado.setText(MensajeAuxiliar);
                    //this.ubiCompo=ucTipoPasaje;
                    TiposAsientos=null;
                    if(!ConsultaOcupacionX(true, false, false, false)) return;
                    jTxtTipoPasaje.setText("");
                    abledComponents(jTxtTipoPasaje);
                    jTxtTipoPasaje.requestFocusInWindow();
                    if(sesionVenta.isBolRedCer())
                    {
                        abledComponents(jTxtAsientos);
                        jTxtAsientos.requestFocusInWindow();
                    }

                break;
                case KeyEvent.VK_F1: 
                    if(transaccion.equals(txVENTA_BA)){
                        Boletos=null;
                        jLblTotalVtaActual.setText("Total: $0.00");
                        this.transaccion=this.txVENTA;
                        //PlantillaResumenDefault();
                        jTxtTipoPasaje.setText("");
                        jTxtAsientos.setText("");
                        xVtaActual.setDataVector(null, encVtaActual);
                        AnchoColumnasVtaActual();
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        filtroInicialX();
                        desHabilitarCriterios(1);
                        jCboServicio.requestFocusInWindow();
                        return;
                    }
                    if(sesionVenta.isBolRedCer())
                    {
                         //corridaIdRedCerrIda= this.listaCorridas[fila][6].toString();
                         //desloquea los asientos de ida
                         rSP=sesionVenta._OcuparAsientosSPReCer(Long.valueOf(corridaIdRedCerrIda), asientosRedCerrIda, sesionVenta.getCadenaTiposPasaje(), "E",sesionVenta.getDblinkRedIda());
                         //desbloquea los asientos de regreso
                         if(rSP==0)
                           rSP=sesionVenta._OcuparAsientosSPReCer(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E",dblinkRedRegreso);
                    }
                    else
                         rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                    if(rSP==-1){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.",Color.RED);
                        return;
                    }
                    else{
                        if(rSP!=0)
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
                    }
                    
                    if(transaccion.equals(txCANJE_BA)){
                        Boletos=null;
                        jLblTotalVtaActual.setText("Total: $0.00");
                        jTxtTipoPasaje.setText("");
                        jTxtAsientos.setText("");
                        xVtaActual.setDataVector(null, encVtaActual);
                        AnchoColumnasVtaActual();
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        jLblBarraEstado.setText(mensajes.getMensajeVta(19));
                        abledComponentsCriterios();
                        abledComponents(jTblCorridas);
                        desHabilitarCriterios(2);
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        jTxtFecha.selectAll();
                        jTxtFecha.requestFocusInWindow();
                        return;
                    }
                    
                    Boletos=null;
                    jLblTotalVtaActual.setText("Total: $0.00");
                    ambienteBoletosRedondos=false;
                    ambienteVtaActualnormal();
                    jTxtTipoPasaje.setText("");
                    jTxtAsientos.setText("");
                    xCorridas.setDataVector(null, encCorridas);
                    AnchoColumnasCorridas();
                    xVtaActual.setDataVector(null, encVtaActual);
                    AnchoColumnasVtaActual();
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    jCboServicio.requestFocusInWindow();
                break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_DOWN: case KeyEvent.VK_PAGE_DOWN:
                case KeyEvent.VK_LEFT: case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
                    break;
                case KeyEvent.VK_F9:
                    if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                    if(!ValidaSesionVenta()) return;
                    if(transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    if(ambienteBoletosRedondos) return;
                    if(!validaDisponibilidadRvn()){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Imposible reservar!","<html>Fecha hora de corrida supera <br> el tiempo limite de reservacion.</html>", Color.RED);
                        jTblVtaActual.setColumnSelectionInterval(3,3);
                        jTblVtaActual.setRowSelectionInterval(0,0);
                        jTblVtaActual.requestFocusInWindow();
                        return;
                    }
                    if(!sesionVenta.ValidaFuncionUsuario("5009")){
                        jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5009", "Reservar Boleto");
                        dlg.setVisible(true);
                        if(!dlg.getRespuesta()) return;
                    }
                    if(xVtaActual.getValueAt(0,3)==null || xVtaActual.getValueAt(0,3).toString().equals("")){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Reservacion de Boletos!","Ingrese el Nombre del Responsable de la Reservacion.", Color.RED);
                        jTblVtaActual.setColumnSelectionInterval(3,3);
                        jTblVtaActual.setRowSelectionInterval(0,0);
                        jTblVtaActual.requestFocusInWindow();
                        return;
                    }
                    for(int i=0; i<jTblVtaActual.getRowCount(); i++)
                        if(xVtaActual.getValueAt(i,3)!=null && !xVtaActual.getValueAt(i,3).toString().equals(""))
                            Boletos[i][3] = xVtaActual.getValueAt(i,3).toString().toUpperCase();
                    this.transaccion=this.txRESERVACION;
                    jLblBarraEstado.setText("...");
                    if(EjecutaReservacion()==-1){ // si esta ocupado me espero
                        jTblVtaActual.setColumnSelectionInterval(3,3);
                        jTblVtaActual.setRowSelectionInterval(0,0);
                        jTblVtaActual.requestFocusInWindow();
                        return;
                    }
                    jLblTotalVtaActual.setText("Total: $0.00");
                    this.transaccion=this.txVENTA;
                    this.transaccionAux=this.txVENTA;
                    ambienteVtaActualnormal();
                    jtxt_personaAutorizada.setText("");
                    jtxt_personaAutorizada.setEnabled(false);
                    jtxt_personaAutorizada.setEditable(false);
                    nombreAutorizado = false;
                    jTxtTipoPasaje.setText("");
                    jTxtAsientos.setText("");
                    xCorridas.setDataVector(null, encCorridas);
                    AnchoColumnasCorridas();
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    //PlantillaResumenDefault();
                    jCboServicio.requestFocusInWindow();
                break;
                    // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
            }
        }
    }//GEN-LAST:event_jTblVtaActualKeyPressed
        
    private void jTxtAsientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtAsientosKeyPressed
    // TODO add your handling code here:d
        try{
        if(!ConsultaOcupacionX(true, false, false, false)) return;
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                if(this.transaccion.equals(txCANJE_BA) || this.transaccion.equals(txCHORARIO)){
                    int r=-1;
                    while(r==-1){
                        r=sesionVenta.procesaTipoPasaje(this.corridaId, vTP, -1); // incrementa signo=-1
                        if(r==-1){
                            sesionVenta.iTempos++;
                            if(sesionVenta.iTempos>sesionVenta.tTempos){
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("TMS Venta.", "Asiento bloqueado.",Color.RED);
                                break;
                            }
                        }
                    }
                    if(r!=0 && r!=-1)
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "No fue posible liberar tipo(s) de pasajero.",Color.RED);
                }
                jTxtAsientos.setText("");
                vTP=null;
                jLblBarraEstado.setText(mensajes.getMensajeVta(2));
                jTblCorridas.setColumnSelectionInterval(0,0);
                jTblCorridas.setRowSelectionInterval(fila,fila);
                abledComponents(jTblCorridas);
                jTblCorridas.requestFocusInWindow();
                enabledComponents(jTxtAsientos);
                break;
            case KeyEvent.VK_F1:
                if(transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                jTxtAsientos.setText("");
                if(this.transaccion.equals(txCHORARIO)){
                    int r=-1;
                    while(r==-1){
                        r=sesionVenta.procesaTipoPasaje(this.corridaId, vTP, -1); // incrementa signo=-1
                        if(r==-1){
                            sesionVenta.iTempos++;
                            if(sesionVenta.iTempos>sesionVenta.tTempos){
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("TMS Venta.", "Asiento bloqueado.",Color.RED);
                                break;
                            }
                        }
                    }
                    if(r!=0 && r!=-1)
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "No fue posible liberar tipo(s) de pasajero.",Color.RED);
                    jScpVtaActual.setVisible(true);
                    jLblTotalVtaActual.setVisible(true);
                    jPnlCHorario.setVisible(false);
                    this.desHabilitarCriterios(1);
                }
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                this.transaccion=txVENTA;
                this.transaccionAux=txVENTA;
                jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_ENTER:
                 this.sesionVenta.setTipoTDC("");
                if(!ValidaSesionVenta()) return;
                if(!ValAsientos(sesionVenta.getCapacidadPlantilla(plantillaId)) || BoletoAVender.size()==0){
                    jTxtAsientos.setText("");
                    this.getToolkit().beep();
                }else{
                    // EXISTE DISPONIBILIDAD DE BOLETOS PARA VENTA
                    if(sesionVenta.getUserCon().getAplicacionVenta() && !sesionVenta.isVtaBolBco(this.nombreEmpresa)){
                        long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                        long f3=(f2-f1)+1;
                        if(BoletoAVender.size()>f3){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("Limite de folios.",
                                "<html>No tiene boletos suficientes para vender los asientos.<br>" +
                                f3+" folio(s) disponible(s).</html>", Color.RED);
                            jTxtAsientos.setText("");
                            return;
                        }
                    }
                    // MOD: OCUPACION DE ASIENTOS
                    sesionVenta.armaCadenaAsientos(BoletoAVender);
                    int ctlAsientos=5, i, noAsiento;
                    for(i = 0; i < BoletoAVender.size(); i++){
                        noAsiento=Integer.valueOf(BoletoAVender.get(i).toString());
                        if(!this.corridaActual[ctlAsientos+noAsiento].toString().equals("D")){ // NO ESTAN DISPONIBLES
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("TMS Venta.","Asiento "+noAsiento+" ocupado.", Color.RED);
                            ConsultaOcupacionX(true, false, false, false);
                            return;
                        }
                    }
                    // SI ESTAN DISPONIBLES, UTILIZAMOS SP PARA OCUPARLOS
                    int rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "I");
                    System.out.println("SP_Ocup "+rSP);
                     if(sesionVenta.isBolRedCer() && rSP==0)
                     {
                        rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "A");
                        System.out.println("SP_TiposPasaje(RedondoCerrado) "+rSP+" - "+sesionVenta.getValorTipo());
                     }
                    if(rSP!=0){
                        this.getToolkit().beep();
                        if(rSP!=-1){
                            DialogoAceptar.mostrarDialogo("¡Asiento "+rSP+" ocupado!","Presione Enter Para Continuar...", Color.RED);
                            ConsultaOcupacionX(true, false, false, false);
                        }
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado",Color.RED);
                        return;
                    }
                    //
                    this.ubiCompo=ucTipoPasaje;
                    ConsultaOcupacionX(true, false, false, false);
                    if(this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA)){
                        for(i=0; i<sesionVenta.getVariosTmsBoletosVentaTbl().size(); i++)
                            sesionVenta.actualizaTipoAsiento(this.corridaId, sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero(),Integer.valueOf(BoletoAVender.get(i).toString()));
                        if(this.transaccion.equals(txCANJE_BA)) {
                            ConstruyeBoletoCHCBA();
                            return;
                        }
                        else{
                            ConstruyeBoletoCHCBA();
                            sesionVenta.isCambHorRer();
                            if(!CambioHorario()) return;
                        }
                        this.tipoTransaccion=txVENTA;
                        sesionVenta.setCambHorRer(false);
                        sesionVenta.setReferenciaOriginal("");
                        sesionVenta.setReferenciadoId("");
                        //AnchoColumnasVtaActual();                        
                        jTxtAsientos.setText("");
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        
                        jLblTotalVtaActual.setVisible(true);
                        jPnlCHorario.setVisible(false);
                        jScpVtaActual.setVisible(true);
                        //PlantillaResumenDefault();
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        this.desHabilitarCriterios(1);
                        filtroInicialX();
                        return;
                    }
                    CalculaResumen();
                    if(sesionVenta.getUserCon().getAplicacionVenta())
                        MensajeAuxiliar="<html>" +
                               "Ingresar tipo pasajero: "+getTiposPasajeVenta()+" | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Boleto Redondo | " +
                               "<font color="+ColoresInterfaz.cHTML1+">Ctrl+F12</font> Boleto Redondo Cerrado" +
                               "</html>";
                    else
                        MensajeAuxiliar="<html>" +
                               "Ingresar tipo pasajero: "+getTiposPasajeVenta()+" | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Boleto Redondo | " +
                               "<font color="+ColoresInterfaz.cHTML1+">Ctrl+F12</font> Boleto Redondo Cerrado" +
                               "</html>";
                    jLblBarraEstado.setText(MensajeAuxiliar);
                    jTxtTipoPasaje.setTipos(sesionVenta.getCastTiposPasaje());
                    //System.out.println("MiLista : "+jTxtTipoPasaje.getTam());
                    if(sesionVenta.isBolRedCer())
                    {
                        corridaRedCerr = this.listaCorridas[fila][0].toString().substring(0,10)+"-"+this.listaCorridas[fila][1];
                        asientosRedCerrRegreso = sesionVenta.getCadenaAsientos();
                        dblinkRedRegreso = sesionVenta.getDBLink();
                       /*jCboCiudadVenta.setSelectedItem(ciudadOrigenRedCerr);
                        if(!this.COAnt.equals(jCboCiudadVenta.getSelectedItem().toString())){
                            sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
                            filtroInicial();
                            this.COAnt=jCboCiudadVenta.getSelectedItem().toString();
                        }
                        jCboOrigen.setSelectedItem(origenRedCerr);
                        jCboDestino.setSelectedItem(destinoRedCerr);
                        *
                        */
                        jLblBarraEstado.setText(mensajes.getMensajeVta(51));
                        jTblVtaActual.setColumnSelectionInterval(3,3);
                        jTblVtaActual.setRowSelectionInterval(0,0);
                        jTblVtaActual.requestFocusInWindow();
                        System.out.println("corridaRedCerr: "+corridaRedCerr);
                        System.out.println("asientosRedCerr: "+asientosRedCerrRegreso);
                        System.out.println("ciudadOrigenRedCerr: "+ciudadOrigenRedCerr);
                        System.out.println("origenRedCerr: "+origenRedCerr);
                        System.out.println("destinoRedCerr: "+destinoRedCerr);
                        System.out.println("dblinkRedIda: "+sesionVenta.getDblinkRedIda());
                        System.out.println("dblinkRedRegreso: "+dblinkRedRegreso);

                    }
                    else
                    {
                        abledComponents(jTxtTipoPasaje);
                        jTxtTipoPasaje.requestFocusInWindow();
                    }
                    enabledComponents(jTxtAsientos);
                }
            break;
        }
        
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }//GEN-LAST:event_jTxtAsientosKeyPressed
        
    private void jTxtHoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtHoraKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                 case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                   if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE: 
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        this.transaccionAux=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_F4:
                CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1:
                controlVentana(evt); break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_DOWN:
                ubiCrit = uCtServicio;
                if(sesionVenta.isBolRedCer())
                {
                  jTxtFecha.selectAll();
                  jTxtFecha.requestFocusInWindow();
                }
                else
                    jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_UP:
                ubiCrit = uCtFecha;
                jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
            case KeyEvent.VK_ENTER:
                if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA)) EjecutaConsulta();
                break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;agregarPanelRecoleccion(); break;
            case KeyEvent.VK_F6:// if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11:if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                 VenderReservacion(); break;
            case KeyEvent.VK_F12: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; CambiarHorarioBoleto(); break;
               // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jTxtHoraKeyPressed
    
    private void jTxtFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFechaKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_F4:
                CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1:
                controlVentana(evt); break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_DOWN: //System.out.println("VALOR DBLINK "+sesionVenta.getDBLink());
                if(!AutoCompletaFecha()) return; ubiCrit = uCtHora; jTxtHora.selectAll();  jTxtHora.requestFocusInWindow(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_UP: //System.out.println("VALOR DBLINK "+sesionVenta.getDBLink());
                if(!AutoCompletaFecha()) return;
                ubiCrit = uCtDestino;
                if(sesionVenta.isBolRedCer()) 
                {
                    jTxtHora.selectAll();  
                    jTxtHora.requestFocusInWindow();
                }
                else
                 jCboDestino.requestFocusInWindow();
                break;
            case KeyEvent.VK_ENTER: //System.out.println("VALOR DBLINK "+sesionVenta.getDBLink());
                if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                    EjecutaConsulta();
                break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;agregarPanelRecoleccion(); break;
            case KeyEvent.VK_F6: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11: if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;VenderReservacion(); break;
            case KeyEvent.VK_F12: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;CambiarHorarioBoleto(); break;
                    // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jTxtFechaKeyPressed
    
    private boolean AutoCompletaFecha(){
        if(jTxtFecha.getText().length()==3) jTxtFecha.setText(jTxtFecha.getText()+MesAnho);
        if(jTxtFecha.getText().length()==6) jTxtFecha.setText(jTxtFecha.getText()+Anho);
        
        if(jTxtFecha.getText().length()>0 && jTxtFecha.getText().length()<10){
            jTxtFecha.selectAll();
            jTxtFecha.requestFocusInWindow();
            return false;
        }
        return true;
    }
    
    private void jCboServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboServicioKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_RIGHT:
                ubiCrit = uCtCiudadOrigen;
                jCboCiudadVenta.requestFocusInWindow(); break;
            case KeyEvent.VK_LEFT:
                if(jTxtHora.isEnabled()){
                    ubiCrit = uCtHora;
                    jTxtHora.selectAll(); jTxtHora.requestFocusInWindow();
                }
                else{
                    ubiCrit = uCtDestino;
                    jCboDestino.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_ENTER:
                if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                    EjecutaConsulta();
                break;
            case KeyEvent.VK_F4: 
                CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1: 
                controlVentana(evt); break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; 
                agregarPanelRecoleccion();
                break;
            case KeyEvent.VK_F6: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11: if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;
                VenderReservacion(); break;
            case KeyEvent.VK_F12: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; CambiarHorarioBoleto(); break;
                    // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jCboServicioKeyPressed
        
    private void jCboOrigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboOrigenKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                 case KeyEvent.VK_F2:
                    if(evt.isControlDown()){
                        JDLGBoletosReferenciados dr = new JDLGBoletosReferenciados(sesionVenta.getTmsVtaFacade());
                        break;
                    }
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                    if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
                    this.refoliado();
                    filtroInicialX();
                    this.setLabelFolioX(sesionVenta.obtenerFolioActual(this.nombreEmpresa),this.nombreEmpresa); // Empresa default
                    break;
            case KeyEvent.VK_F1:
                    if(evt.isControlDown()){
                        JDlgCalendario uCalendario = new JDlgCalendario();
                        uCalendario.setVisible(true);
                    }
                    break;
            case KeyEvent.VK_ESCAPE:
                    if(!this.transaccion.equals(txVENTA)){
                        if(this.transaccion.equals(txCHORARIO)){
                            jScpVtaActual.setVisible(true);
                            jLblTotalVtaActual.setVisible(true);
                            jPnlCHorario.setVisible(false);
                        }
                        this.transaccion=txVENTA;
                        desHabilitarCriterios(1);
                        filtroInicialX();
                    }
                break;
            case KeyEvent.VK_F4: 
                CerrarVentana(); break;
            case KeyEvent.VK_2: case KeyEvent.VK_1: 
                controlVentana(evt); break;
            case KeyEvent.VK_RIGHT:
                ubiCrit = uCtDestino; jCboDestino.requestFocusInWindow(); break;
            case KeyEvent.VK_LEFT:
                    if(jCboEmpresas.isVisible()){
                        ubiCrit = uCtEmpresa;
                        jCboEmpresas.requestFocusInWindow();
                    }
                    else{
                        ubiCrit = uCtCiudadOrigen;
                        jCboCiudadVenta.requestFocusInWindow();
                    }
                break;
            case KeyEvent.VK_ENTER: 
                    if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA))
                        EjecutaConsulta();
                    break;
            case KeyEvent.VK_F5: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; agregarPanelRecoleccion(); break;
            case KeyEvent.VK_F6: //if(!sesionVenta.getUserCon().getAplicacionVenta()) return; 
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; VenderBoletoAbierto(); break;
            case KeyEvent.VK_F7: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; CanjeBoletoAbierto(); break;
            case KeyEvent.VK_F8: if(!sesionVenta.getUserCon().getAplicacionVenta()) return; CancelarBoleto(); break;
            case KeyEvent.VK_F9: 
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    CanjeBoletoReferenciado(); 
                    break;
            case KeyEvent.VK_F11: if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return;VenderReservacion(); break;
            case KeyEvent.VK_F12:// if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                if(!excluyeTeclasCriterios) excluyeTeclasCriterios=true; else return; CambiarHorarioBoleto(); break;
                // BRA 01/09/2010
            case KeyEvent.VK_F3: 
                if(evt.isControlDown()) ventaProductor(); else agregarTiempoAire(); break;
        }
    }//GEN-LAST:event_jCboOrigenKeyPressed
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        jPnlRecoleccion.setVisible(false);
        jPnlCHorario.setVisible(false);
        xCorridas.setDataVector(null, encCorridas);
        AnchoColumnasCorridas();
        xVtaActual.setDataVector(null, encVtaActual);
        AnchoColumnasVtaActual();
        long empresaDefaultId = sesionVenta.getEmpresaId(sesionVenta.getUserCon().getEmpresaPrincipal());
        if(sesionVenta.getUserCon().getAplicacionVenta())
            setLabelFolio(sesionVenta.obtenerFolioActual(sesionVenta.getUserCon().getEmpresaPrincipal()), sesionVenta.getUserCon().getEmpresaPrincipal());
        setFoco();
    }//GEN-LAST:event_formComponentShown
    public void setFoco(){
        if(jTblCorridas.getRowCount()>0) jTblCorridas.requestFocusInWindow();
        else jCboServicio.requestFocusInWindow();
    }
    /****************************** FUNCIONES CUSTOM***************************/
    private void AnchoColumnasResumenVtaCorrida(){
        try{
        TableColumn column = null;
        float tpo = (float)0.08;
        float Tot = (float)0.22;
        int anchoContenedor = jScpResumenVtaCorrida.getWidth();
        for (int i = 0; i < jTblResumenVtaCorrida.getColumnCount(); i++) {
            column = jTblResumenVtaCorrida.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.30)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
                case 7: column.setPreferredWidth(Math.round(anchoContenedor*(float)(i==jTblResumenVtaCorrida.getColumnCount()-1 ? Tot : tpo))); break;
            }
            column.setResizable(false);
        }
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }
    
    private void AnchoColumnasCorridas(){
        TableColumn column = null;
        int anchoContenedor = jScpCorridas.getWidth();
        for (int i = 0; i < 6; i++) {
            column = jTblCorridas.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.24)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.07)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.26)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void AnchoColumnasVtaActual(){
        TableColumn column = null;
        int anchoContenedor = jScpVtaActual.getWidth();
        for (int i = 0; i < 31; i++) {
            column = jTblVtaActual.getColumnModel().getColumn(i);
            switch(i){
//                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.29)); break;
//                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
//                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
//                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.36)); break;
//                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.18)); break;
//                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); break;
                
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.20));column.setMinWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.04));column.setMinWidth(Math.round(anchoContenedor*(float)0.04));  break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.04));column.setMinWidth(Math.round(anchoContenedor*(float)0.04));  break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.22)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.22));column.setMinWidth(Math.round(anchoContenedor*(float)0.22));  break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.15));column.setMinWidth(Math.round(anchoContenedor*(float)0.15));  break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.09));column.setMinWidth(Math.round(anchoContenedor*(float)0.09));  break;
                case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: 
                case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24:
                case 25: case 26: case 27:
                case 28: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.0)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.0));column.setMinWidth(Math.round(anchoContenedor*(float)0.0));  break;
                case 29: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.04));column.setMinWidth(Math.round(anchoContenedor*(float)0.04));  break;
                case 30: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.22)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.22));column.setMinWidth(Math.round(anchoContenedor*(float)0.22));  break;

            }
            column.setResizable(false);
        }
        jTblVtaActual.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jtxtNombre));
        jTblVtaActual.getColumnModel().getColumn(30).setCellEditor(new DefaultCellEditor(jtxtNombre));
        
    }
    
    private void AnchoColumnasVtaActual2(){
        TableColumn column = null;
        int anchoContenedor = jScpVtaActual.getWidth();
        for (int i = 0; i < 31; i++) {
            column = jTblVtaActual.getColumnModel().getColumn(i);
            switch(i){
//                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.23)); break;
//                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
//                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
//                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.30)); break;
//                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.22)); break;
//                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
//                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.07)); break;
                
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.19)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.19));column.setMinWidth(Math.round(anchoContenedor*(float)0.19)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.03));column.setMinWidth(Math.round(anchoContenedor*(float)0.03));  break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.03));column.setMinWidth(Math.round(anchoContenedor*(float)0.03));  break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.21));column.setMinWidth(Math.round(anchoContenedor*(float)0.21));  break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.14));column.setMinWidth(Math.round(anchoContenedor*(float)0.14));  break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.09));column.setMinWidth(Math.round(anchoContenedor*(float)0.09));  break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.06));column.setMinWidth(Math.round(anchoContenedor*(float)0.06));  break;
                case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: 
                case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24:
                case 25: case 26: case 27:
                case 28: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.0)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.0));column.setMinWidth(Math.round(anchoContenedor*(float)0.0));  break;
                case 29: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.04));column.setMinWidth(Math.round(anchoContenedor*(float)0.04));  break;
                case 30: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); column.setMaxWidth(Math.round(anchoContenedor*(float)0.21));column.setMinWidth(Math.round(anchoContenedor*(float)0.21));  break;
                
                
            }
            column.setResizable(false);
        }
        jTblVtaActual.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jtxtNombre));
    }
    
    private void AnchoColumnasBO(){
        TableColumn column = null;
        int anchoContenedor = jScpBO.getWidth();
        for (int i = 0; i < 8; i++) {
            column = jTblBO.getColumnModel().getColumn(i);
            switch(i){
//                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.17)); break;
//                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); break;
//                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
//                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
//                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.22)); break;
//                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
//                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
//                case 7: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); break;
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 7: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); break;
            }
            column.setResizable(false);
        }
        jScpVtaActual.setBackground(java.awt.Color.black);
        jTblBO.setBackground(java.awt.Color.black);
        jTblBO.setFont(new java.awt.Font("Tahoma", 1, 15));
        jTblBO.setForeground(java.awt.Color.white);
        
    }
    
    private void AnchoColumnasCH(){
        TableColumn column = null;
        int anchoContenedor = jScpCH.getWidth();
        for (int i = 0; i < 8; i++) {
            column = jTblCH.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 7: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); break;
            }
            column.setResizable(false);
        }
        jScpVtaActual.setBackground(java.awt.Color.black);
        jTblCH.setBackground(java.awt.Color.black);
        jTblCH.setFont(new java.awt.Font("Tahoma", 1, 15));
        jTblCH.setForeground(java.awt.Color.white);
        
    }
    
    private void EjecutaConsulta(){
     try {
        this.ubiCompo=ucEjecCons;
        if(jThdConsultaCorrida!=null){
            jThdConsultaCorrida.moreQuotes=false;
            jThdConsultaCorrida = null;
            sesionVenta.b_decode_data=false;
        }
        if(!ValidaSesionVenta()) return;
        
        if(jCboOrigen.getSelectedItem()==null || jCboCiudadVenta.getSelectedItem()==null ||
           jCboDestino.getSelectedItem()==null  || jCboServicio.getSelectedItem()==null){
            DialogoAceptar.mostrarDialogo("TMS Venta.","Criterios de busqueda invalidos.", Color.RED);
            return;
        }
        
        hoyEs = formatoDate.format(new Date());
        long f1=0, f2=0; boolean soloAvisoBoletos=false;
        ambienteBoletosRedondos=false;
        if(!VerificaRecoleccion()){
            jTxtFecha.setText(hoyEs); jTxtHora.setText(""); filtroInicialX(); jCboServicio.requestFocusInWindow(); return;
        }
        if(sesionVenta.getUserCon().getAplicacionVenta()  && !sesionVenta.isVtaBolBco(this.nombreEmpresa))
        if(sesionVenta.getUserCon().getFolioUnico()  && !sesionVenta.isVtaBolBco(this.nombreEmpresa))
        {
            f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa));
            f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
            if(f1>f2){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("¡Boletos agotados para venta!", "Realice refoliado de boletos para la empresa "+this.nombreEmpresa+".", Color.RED);
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                jCboOrigen.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
                jCboCiudadVenta.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
                filtroInicialX();
                if(sesionVenta.getUserCon().getAplicacionVenta())
                    setLabelFolioX("-",this.nombreEmpresa);
                //PlantillaResumenDefault();
                jCboServicio.requestFocusInWindow();
                return;
            } else{
                if(f1>f2-sesionVenta.getUserCon().getBolNvtImp()) //soloAvisoBoletos = true;
                    DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>Solo restan "+ ((f2-f1)+1)+ " boletos<br>para la empresa "+this.nombreEmpresa+".</html>", Color.RED);
            }
        }
        
        if(!AutoCompletaFecha()) return;
        
        if(jTxtHora.getText().length()>0 && jTxtHora.getText().length()<5){
            jTxtHora.selectAll();
            jTxtHora.requestFocusInWindow();
            return;
        }
        String empresaEspecial = "TODOS";
        
        Date xDate=null;
        if(jlbl_reloj.getFecha()==null) xDate = new Date();
        else xDate = new Date(jlbl_reloj.getFecha().getTime());
        
        criterio = new CriteriosBusqueda(xDate, jCboOrigen.getSelectedItem().toString(),
                sesionVenta.getVectorServiciosReal(jCboServicio.getSelectedItem().toString()),
                jCboDestino.getSelectedItem().toString(),
                jTxtFecha.getText(),
                jTxtHora.getText(),
                empresaEspecial,
                sesionVenta);
        if((xDate.getTime()-unaHora)>criterio.getHoy().getTime()){
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Fecha invalida!","Ingrese otros valores.", Color.RED);
            jTxtFecha.requestFocusInWindow();
            return;
        }
        
        sesionVenta.getDBLinkClaveOrigen(jCboCiudadVenta.getSelectedItem().toString());
        System.out.println("DBLinkClaveOrigen: "+sesionVenta.getDBLink());
        if(sesionVenta.getDBLink()==null){
            sesionVenta.setDBLink("");
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡No existe una conexion a este origen.!","Contacte al administrador del sistema.", Color.RED);
            jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            jCboServicio.requestFocusInWindow();
            return;
        }
        
        if(jCboCiudadVenta.getSelectedItem().equals(sesionVenta.getUserCon().getTerminalNombre()))
            sesionVenta.setTipoTransaccion("L");
        else
            sesionVenta.setTipoTransaccion("R");
        
        consultaListadoCorridas();
        
        jLblBarraEstado.setText("Consulta en proceso, ¡espere por favor!...");
        
        if(this.listaCorridas == null){
            xCorridas.setDataVector(null,encCorridas);
            AnchoColumnasCorridas();
            sesionVenta.setDBLink("");
            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Importante!","No existen corridas para el criterio de busqueda introducido.", Color.RED);
            this.jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            if(!this.transaccion.equals(txVENTA)){
                if(this.transaccion.equals(txCHORARIO)){
                    jScpVtaActual.setVisible(true);
                    jLblTotalVtaActual.setVisible(true);
                    jPnlCHorario.setVisible(false);
                }
                this.transaccion=txVENTA;
                desHabilitarCriterios(1);
            }
            jCboServicio.requestFocusInWindow();
            return;
        }
        this.fila = 0;
        rndFila = 0;
        xCorridas.setDataVector(this.listaCorridas,encCorridas);
        AnchoColumnasCorridas();
        //colorEmpresa();
        ConsultaOcupacionX(true, true, true, true);
        if(this.transaccion.equals(txCHORARIO)||this.transaccion.equals(txCANJE_BA)){
            System.out.println("tarifa comparar "+this.transaccion+"-"+sesionVenta.getUserCon().getServicioTarifaSencillo()+" vs "+tarifaAdultoCHBA);
            if(sesionVenta.getUserCon().getServicioTarifaSencillo()!=tarifaAdultoCHBA){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("Cambio de horario o Canje de Boleto.","<html>No es posible cambiar el boleto para<br>este destino y servicio.</html>", Color.RED);
                xCorridas.setDataVector(null,encCorridas);
                AnchoColumnasCorridas();
//                if(this.transaccion.equals(txCHORARIO)){
//                    jScpVtaActual.setVisible(true);
//                    jLblTotalVtaActual.setVisible(true);
//                    jPnlCHorario.setVisible(false);
//                }
//                jCboDestino.requestFocusInWindow();
                return;
            }
        }
        
        jLblBarraEstado.setText(mensajes.getMensajeVta(2));
        jTblCorridas.setRowSelectionInterval(0,0);
        jTblCorridas.setColumnSelectionInterval(0,0);
        this.ubiCompo=ucListCorridas;
        System.out.println("DBLinkClaveOrigen(2): "+sesionVenta.getDBLink());
        jTblCorridas.requestFocusInWindow();
        enabledComponentsCriterios();
        } catch (ConsultaCorridaException ex) {            
            xCorridas.setDataVector(null,encCorridas);
            AnchoColumnasCorridas();
            sesionVenta.setDBLink("");
            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Importante!","No existen corridas para el criterio de busqueda introducido.", Color.RED);
            this.jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            if(!this.transaccion.equals(txVENTA)){
                if(this.transaccion.equals(txCHORARIO)){
                    jScpVtaActual.setVisible(true);
                    jLblTotalVtaActual.setVisible(true);
                    jPnlCHorario.setVisible(false);
                }
                this.transaccion=txVENTA;
                desHabilitarCriterios(1);
            }
            jCboServicio.requestFocusInWindow();
            return;
        }
    }
    
    public boolean refrescaListaCorridas(boolean primerCaso, boolean calcTarifa, boolean primeraVezConsulta){
        try{
        if(!primeraVezConsulta){
                try {
                    if(!consultarCorridaActual(false)){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Importante(2)!","La corrida ya no esta abierta para venta.", Color.RED);
                        deshacer();
                        return false;
                    }
                } catch (ConsultaCorridaException ex) {
                    //ex.printStackTrace();
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Importante(3)!","La corrida ya no esta abierta para venta.", Color.RED);
                    deshacer();
                    return false;
                }
        }
        
        if(fila<0 || (jTblCorridas!=null && fila>jTblCorridas.getRowCount()-1)) return false;
        
        corridaId=Long.valueOf(this.listaCorridas[fila][6].toString());
        if(calcTarifa){
//            compoTarifa = toString()+listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";
            compoTarifa = listaCorridas[fila][8].toString()+listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";
            //VAGL&&RAI 09/02/2009
           //System.out.println("compoTarifa2: "+compoTarifa);
            TarifaServicio=sesionVenta.getTarifaTramo(compoTarifa,0);
            sesionVenta.getUserCon().setServicioTarifaSencillo(TarifaServicio);
//            compoTarifa = listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"RED";
            compoTarifa = listaCorridas[fila][8].toString()+listaCorridas[fila][3].toString()+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"RED";
            //System.out.println("compoTarifa3(RED): "+compoTarifa);
            //VAGL&&RAI 09/02/2009
            TarifaServicio=sesionVenta.getTarifaTramo(compoTarifa,-1);
            if(TarifaServicio==0) sesionVenta.getUserCon().setServicioTarifaRedondo(sesionVenta.getUserCon().getServicioTarifaSencillo()*2);
            else sesionVenta.getUserCon().setServicioTarifaRedondo(TarifaServicio);
            jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getUserCon().getServicioTarifaSencillo())+"</font></html>");
        }
        
        this.nombreEmpresa=listaCorridas[fila][7].toString();
            if(sesionVenta.getUserCon().getAplicacionVenta())
                setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
        
        this.ServicioId = sesionVenta.getServicioId(this.listaCorridas[fila][3].toString());
        plantillaId=Long.valueOf(this.corridaActual[5].toString());
        capacidadAutobus=sesionVenta.getCapacidadPlantilla(plantillaId);
        PeriodoVacacional();
        return true;
        }catch(Exception aex){
            //aex.printStackTrace();
            return false;
        }
    }
    
    public boolean ConsultaOcupacionX(boolean Calc_RC, boolean primerCaso, boolean calcTarifa, boolean primeraVezConsulta){
        if(!refrescaListaCorridas(primerCaso, calcTarifa, primeraVezConsulta)) return false;
        return true;
    }
    
    private void CalculaResumen(){
        sesionVenta.TiposPasajeParaVenta(); // POR CORRIDA OBTENGO QUE PUEDO VENDER
    }

    private boolean ValAsientos(long CupoMax) {
        try{
            CupoMax=CupoMax+1;
            if(jTxtAsientos.getText().equals("") || jTxtAsientos.getText().equals("0")) return false;
            if(BoletoAVender != null) BoletoAVender=null;
            BoletoAVender = new Vector();
            String aux = this.jTxtAsientos.getText();
            String Salida="";
            boolean AsientoOk = true;
            boolean NoAsientoInv = false;
            if (aux.length() != 0) {
                long asiento, ainicio, afinal;
                String[] Strasiento;
                String[] rango;
                Strasiento = aux.split(" ");
                String StrCorridaId;
                int h, bs;
                boolean yaExiste = true;
                for (int i = 0; i < Strasiento.length; i++) {
                    if (Strasiento[i].contains("-")) { //evaluo que sea un rango
                        rango = Strasiento[i].split("-"); //divido los dos elementos
                        if (rango.length == 2) { //aseguro que sean dos numeros enteros.
                            ainicio = Integer.parseInt(rango[0]);
                            afinal = Integer.parseInt(rango[1]);
                            if(ainicio==0 || afinal==0){ NoAsientoInv=true; AsientoOk = false; break; }
                            //reviso que sea un rango valido
                            if (ainicio < afinal) {
                                for ( asiento = ainicio; asiento <= afinal; asiento++) { //agrego los valores
                                    //aseguro que no se repita el asiento seleccionado
                                    if(!BoletoAVender.contains(asiento) && asiento<CupoMax) {
                                        BoletoAVender.addElement(asiento);
                                    } else {
                                        Salida += asiento + " ";
                                        AsientoOk = false;
                                    }
                                    if(asiento>=CupoMax) NoAsientoInv=true;
                                }
                            }
                        }
                    } else { //no es un ciclo
                        asiento = Integer.parseInt(Strasiento[i]);
                        if(asiento==0){ NoAsientoInv=true; AsientoOk = false; break; }
                        else{
                            //aseguro que no se repita el asiento seleccionado
                            if(!BoletoAVender.contains(asiento) && asiento < CupoMax){
                                BoletoAVender.addElement(asiento);
                            } else {
                                Salida += asiento + " ";
                                AsientoOk = false;
                            }
                        }
                    }
                }
            }
            if (AsientoOk) {
                if(this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA)){
                    if(BoletoAVender.size()!=sesionVenta.getVariosTmsBoletosVentaTbl().size()) AsientoOk = false;
                }
            } else {
                if(!NoAsientoInv){
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Error!","Asiento invalido: "+Salida, Color.RED);
                }else{
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Error!","Asiento invalido: "+Salida, Color.RED);
                }
            }
            return AsientoOk;
        } catch ( NullPointerException npex ) {
            return false;
        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
            return false;
        }catch(java.util.NoSuchElementException ex){
            //ex.printStackTrace();
            return false;
        }catch(Exception x){
            //x.printStackTrace();
            return false;
        }
    }
    
    // VALIDA INGRESO DE TIPOS DE PASAJERO
    private boolean ValidaTipoPasajero() {
        if (jTblCorridas.getRowCount()>0 && jTblCorridas.getSelectedRow() == -1) {
            jTblCorridas.setRowSelectionInterval(0,0);
            jTblCorridas.requestFocusInWindow();
            enabledComponents(jTxtTipoPasaje);
            return false;
        }
        
        if (!obtenerTipos()){
            this.jTxtTipoPasaje.requestFocusInWindow();
            return false;
        }
        
        return true;
    }
    
    private boolean obtenerTipos() {
        TiposAsientos = new Vector();
        String aux = this.jTxtTipoPasaje.getText();
        boolean salida = true;
        long [] ctdAsientos;
        int iTotalast = 0, i, n;
        char[] cTipo;
        if(aux.length() != 0) {
            String[] strTipoAsiento;
            strTipoAsiento = aux.split(" ");
            ctdAsientos = new long[strTipoAsiento.length];
            cTipo = new char[strTipoAsiento.length];
            for (i = 0; i < strTipoAsiento.length; i++) {
                cTipo[i] = strTipoAsiento[i].charAt(strTipoAsiento[i].length() - 1);
                if(strTipoAsiento[i].substring(0, strTipoAsiento[i].length() - 1).equals("")) ctdAsientos[i]=1;
                else ctdAsientos[i] = Long.parseLong(strTipoAsiento[i].substring(0, strTipoAsiento[i].length() - 1));
                iTotalast += (int)ctdAsientos[i];
            }
            if (iTotalast > BoletoAVender.size()) {
                salida = false;
            } else {
                for (n = 0; n < ctdAsientos.length; n++) {
                    for (int t = 0; t < ctdAsientos[n]; t++){
                        TiposAsientos.addElement(cTipo[n]);
                    }
                }
            }
            if(TiposAsientos.size()<1){
                this.jTxtTipoPasaje.setText("");
                return false;
            }
            for(n=0; n<TiposAsientos.size(); n++)
                if(Character.isDigit(TiposAsientos.get(n).toString().charAt(0))){
                this.jTxtTipoPasaje.setText("");
                return false;
                }
        } // if
        
        if (iTotalast < BoletoAVender.size()){
            char defaultt = 'A';//this.jTxtTipoPasaje.getDefault();
            for (i = iTotalast; i < BoletoAVender.size(); i++)
                TiposAsientos.addElement(defaultt);
        }
        
        if(!salida) this.jTxtTipoPasaje.setText("");
        return salida;
    }
    // FIN VALIDA INGRESO TIPO PASAJERO
    
    // BOLETOS POSIBLES A VENDERSE
    private void ConstruyeBoleto(String normal) {
        if(Boletos!=null) Boletos=null;
//        Boletos = new Object[BoletoAVender.size()][31];
        Boletos = new Object[BoletoAVender.size()][36];//[32]//VAGL 02082013[35]
        System.out.println("Construir "+Boletos.length+" boletos para los siguientes \n asientos: "+BoletoAVender.toString()+"y tipos pasaje "+TiposAsientos.toString());
        double Tarifa, tDescuento = 0, TTarifa=0, suma=0;
        if(normal.equals("S")) Tarifa= sesionVenta.getUserCon().getServicioTarifaSencillo();
        else Tarifa= sesionVenta.getUserCon().getServicioTarifaRedondo();
        double TarifaTmp = Tarifa;
        double TarifaMenor = Tarifa;
        long tipoId;
        char tipoPasajero;
        int i;
        jugandoImporteVenta = new double[BoletoAVender.size()];
        jugandoImporteVentaPermiso = new String[BoletoAVender.size()];
        try {sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Boletos a vender: \n");} catch (IOException ex) {ex.printStackTrace();}        
        int cadaNBoletos = 0;
        int nBoletosCeros = 0;
        int nAdultos =0;
        sesionVenta.setRutaConPromocion(false);
        Promocion promo = null;
        int numAdultos = 0;
        int numNoAdultos = 0;
        int numNoAdultosPro = 0;
        int numAdultosSobrantes = 0;
        int numNoAdultosSobrantes = 0;
        int parejas = 0;
        int parejasNoAdultos = 0;
        String folioPromocion="";
        String folioPromocionTmp="";
        int folioPromocionUso=2;
        int tmp =0;
        int count=0;
        Vector vectorPromos = new Vector();
        Vector vectorFoliosPromos = new Vector();
        int numeroFolios=0;
        for(Promocion p : sesionVenta.getRutasConPromocionVigente())
        {
            if(p.getRutaId() == Long.valueOf(this.listaCorridas[fila][8].toString()))
            {
                promo = p;
                sesionVenta.setRutaConPromocion(true);
                for (int y= 0;y<TiposAsientos.size();y++)
                {
                    if(TiposAsientos.get(y).toString().equals("A"))
                        numAdultos++;
                    else
                    {
                        numNoAdultos++;
                        numNoAdultosPro++;
                    }
                }
                //folioPromocionUso = ((int)TiposAsientos.size()/2)*2;
                /*
                numAdultosSobrantes =  numAdultos%2;
                System.out.println("Para la promocion hay "+numAdultos+" y sobran "+numAdultosSobrantes+" para armar otras parejas");
                numNoAdultos = numNoAdultos - numAdultosSobrantes;
                numNoAdultosSobrantes = numNoAdultos%2;
                parejasNoAdultos = (int)numNoAdultos/2;
                parejas=((int)(numAdultos-numAdultosSobrantes)/2)+parejasNoAdultos;
                System.out.println("***********************************************************");
                System.out.println("numAdultos:"+numAdultos);
                System.out.println("numAdultosSobrantes:"+numAdultosSobrantes);
                System.out.println("numNoAdultos:"+numNoAdultos);
                System.out.println("numNoAdultosSobrantes:"+numNoAdultosSobrantes);
                System.out.println("parejasNoAdultos:"+parejasNoAdultos);
                System.out.println("parejas:"+parejas);

                System.out.println("***********************************************************");
                 * 
                 */
                cadaNBoletos = promo.getCadaNBoletos();
                numAdultosSobrantes =  numAdultos%promo.getCadaNBoletos();
                System.out.println("Para la promocion hay "+numAdultos+" y sobran "+numAdultosSobrantes+" para armar otras parejas");
                numNoAdultos = numNoAdultos - numAdultosSobrantes;
                numNoAdultosSobrantes = numNoAdultos%promo.getCadaNBoletos();
                parejasNoAdultos = (int)numNoAdultos/promo.getCadaNBoletos();
                parejas=((int)(numAdultos-numAdultosSobrantes)/promo.getCadaNBoletos())+parejasNoAdultos;
                System.out.println("promo.getCadaNBoletos():"+promo.getCadaNBoletos());
                System.out.println("promo.getxBolPorCadaNBoletos:"+promo.getxBolPorCadaNBoletos());

                System.out.println("************************** NUMERADOS *********************************");
                System.out.println("numAdultos:"+numAdultos);
                System.out.println("numAdultosSobrantes:"+numAdultosSobrantes);
                System.out.println("numNoAdultos:"+numNoAdultos);
                System.out.println("numNoAdultosSobrantes:"+numNoAdultosSobrantes);
                System.out.println("parejasNoAdultos:"+parejasNoAdultos);
                System.out.println("parejas:"+parejas);
                System.out.println("***********************************************************");

                break;
            }
        }
        if(sesionVenta.isRutaConPromocion())
        {
            numeroFolios = ((int) TiposAsientos.size()/promo.getCadaNBoletos())*promo.getCadaNBoletos();
            tmp = numeroFolios;
            int totalPromo = numAdultos;
            if(!promo.isSoloAdultos())
                totalPromo = totalPromo+numNoAdultosPro;
            vectorPromos.add("_");
            vectorFoliosPromos.add("");
            int indic = 0;
            String  folioProm = "";
            for(int p=1; p<= totalPromo;p++)
            {

                indic++;
                if(indic==cadaNBoletos)
                {
                    folioProm = obtenerFolioPromocion()+(count++);
                    vectorPromos.add("N");
                    for(int np = promo.getxBolPorCadaNBoletos()-1; np>=1; np--)
                        vectorPromos.set(p-np,"N");
                    vectorFoliosPromos.add(folioProm);
                    for(int np = promo.getCadaNBoletos()-1; np>=1; np--)
                     vectorFoliosPromos.set((p-np),folioProm);
                    indic=0;
                }
                else
                {
                    vectorPromos.add("S");
                    vectorFoliosPromos.add("");
                }
            }
            System.out.println("El vector para las promos es:"+vectorPromos);
            System.out.println("El vector de folio para las promos es:"+vectorFoliosPromos);
        }

        for (i = 0; i < BoletoAVender.size(); i++) {
        //System.out.println("Tamaño: "+listaCorridas.length);
        
        String cadena = "Listacorridas["+fila+"][n] = "
                +this.listaCorridas[fila][0].toString()+","
                +this.listaCorridas[fila][1].toString()+","
                +this.listaCorridas[fila][2].toString()+","
                +this.listaCorridas[fila][3].toString()+","
                +this.listaCorridas[fila][4].toString()+","
                +this.listaCorridas[fila][5].toString()+","
                +this.listaCorridas[fila][6].toString()+","
                +this.listaCorridas[fila][7].toString()+","
                +this.listaCorridas[fila][8].toString()+",";
//                +this.listaCorridas[fila][9].toString()+","
//                +this.listaCorridas[fila][10].toString()+","
//                +this.listaCorridas[fila][11].toString()+","
//                +this.listaCorridas[fila][12].toString()+","
//                +this.listaCorridas[fila][13].toString()+","
//                +this.listaCorridas[fila][14].toString()+","
//                +this.listaCorridas[fila][15].toString()+","
//                +this.listaCorridas[fila][16].toString()+","
//                +this.listaCorridas[fila][17].toString()+","
//                +this.listaCorridas[fila][18].toString()+","
//                +this.listaCorridas[fila][19].toString()+","
                System.out.println(cadena);
            try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" "+cadena+"\n");} catch (IOException ex) {ex.printStackTrace();}
                String[] array = this.listaCorridas[fila][1].toString().split("/");
                Timestamp fechaCorrida= null;
                fechaCorrida = fechaCorrida.valueOf(array[2]+"-"+array[1]+"-"+array[0]+" 00:00:00");
            
            tipoPasajero = TiposAsientos.elementAt(i).toString().charAt(0);
//////////////Inicia Promocion

        Tarifa = TarifaTmp ;
                if(sesionVenta.isRutaConPromocion())
                {
                    System.out.println("Inicia Promocion");
                    System.out.println("numeroFolios: "+numeroFolios);
                    System.out.println("cadaNBoletos:"+cadaNBoletos);
                    System.out.println("BoletoAVender:"+BoletoAVender.size());
                    
////////////////
                    //cadaNBoletos = 1;
                    if(promo.getFechaLimite().getTime()>=fechaCorrida.getTime() )
                    {
                        boolean pasa=false;
                         if(!promo.isSoloAdultos())
                         {
                             nAdultos++;
                             pasa= true;
                         }
                         else
                         {
                             if((""+tipoPasajero).equals("A"))// && promo.isSoloAdultos())
                                 {
                                     nAdultos++;
                                     pasa= true;
                                 }
                         }

                          folioPromocion = "";
                         if(pasa && vectorPromos.get(nAdultos).toString().equals("N"))
                         {
                             Tarifa = 0.0;
                             //folioPromocion = obtenerFolioPromocion()+(count++);

                         }
                         folioPromocion = vectorFoliosPromos.get(nAdultos).toString();

                      /*
                      folioPromocion = folioPromocionTmp;
                        if( (numeroFolios>0 && folioPromocionUso==0) || numeroFolios==tmp )
                        {
                            folioPromocion = obtenerFolioPromocion()+(count++);
                            folioPromocionTmp = folioPromocion;
                            //folioPromocionUso =2;
                            folioPromocionUso =promo.getxBolPorCadaNBoletos();
                        }
                       if((""+tipoPasajero).equals("A"))
                           nAdultos++;

                        System.out.println("if ("+cadaNBoletos+"=="+nAdultos+")");
                        if(cadaNBoletos==nAdultos)
                        {
                          if((""+tipoPasajero).equals("A"))
                          {
                              Tarifa = 0.0;
                              if(folioPromocionUso>0) folioPromocionUso--;
                              if(numeroFolios>0) numeroFolios--;
                          }
                          else
                          {
                               if(numAdultosSobrantes>0)
                               {
                                   Tarifa = 0.0;
                                   if(folioPromocionUso>0) folioPromocionUso--;
                                   if(numeroFolios>0) numeroFolios--;
                              }
                          }
                          nAdultos=0;
                        }
                         else
                        {
                             if((""+tipoPasajero).equals("A"))
                             {
                                  //nAdultos++;
                                  if(folioPromocionUso>0) folioPromocionUso--;
                                  if(numeroFolios>0) numeroFolios--;
                             }
                        }

                        if(!promo.isSoloAdultos())
                        {
                            if(!(""+tipoPasajero).equals("A"))
                            {
                                if(numAdultosSobrantes>0)
                                {
                                    Tarifa= 0.0;
                                    numAdultosSobrantes--;
                                    if(folioPromocionUso>0) folioPromocionUso--;
                                    if(numeroFolios>0) numeroFolios--;
                                }
                                else
                                {
                                    if(folioPromocionUso==0)
                                        //folioPromocionUso =2;
                                        folioPromocionUso =promo.getxBolPorCadaNBoletos();
                                    folioPromocionTmp = folioPromocion;
                                    folioPromocion = "";

                                }
                            }
                        }
                      */
                    }
                }
                else
                    folioPromocion = "";

//////////////Termina Promocion
            tipoId = jTxtTipoPasaje.getTipopasajeid(tipoPasajero,Long.valueOf(this.listaCorridas[fila][8].toString()));
            System.out.println("busca tipoId: "+tipoPasajero+","+Long.valueOf(this.listaCorridas[fila][8].toString()));
            //System.out.println("tipoId: "+tipoId);
            Boletos[i][0] = this.listaCorridas[fila][0].toString().substring(0,10)+"-"+this.listaCorridas[fila][1];  // corrida-hora
            Boletos[i][1] = BoletoAVender.get(i).toString();                        // asiento
            Boletos[i][2] = tipoPasajero;                                           // tipo pasajero
            Boletos[i][3] = "";                                                     // nombre pasajero
            Boletos[i][4] = jCboOrigen.getSelectedItem().toString()+"-"+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? this.listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString());  // origen-destino
            System.out.println("tipoId: "+tipoId);
            tDescuento = jTxtTipoPasaje.getDescuento(tipoId);
            String redondeo =jTxtTipoPasaje.getRedondeo(tipoId);
            System.out.println("Descueno: "+tDescuento);
            System.out.println("checa si procede el redondeo(ConstruyeBoleto): "+redondeo);
            //if(( this.listaCorridas[fila][3].toString().equals("DIRECTO ECONOMICO") || this.listaCorridas[fila][3].toString().equals("PULLMAN PRIMERA CLASE")) && normal.equals("R") )
            System.out.println("Promo: "+ sesionVenta.getTmsVtaFacade().getPromocionVigente());
            if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218")  ||  this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226") || this.listaCorridas[fila][8].toString().equals("241") || this.listaCorridas[fila][8].toString().equals("242")) && normal.equals("R") && sesionVenta.getTmsVtaFacade().getPromocionVigente() && (""+tipoPasajero).equals("A") )
            {
                if(redondeo.equals("S"))
                    TTarifa = Math.ceil(Tarifa-(Tarifa*.2));
                else
                {
                    TTarifa = Tarifa-(Tarifa*.2);
                    double d = (TTarifa - (Double.valueOf(""+TTarifa).longValue()) );
                    if(d > 0 && d != .50)                                
                        TTarifa =    Math.ceil(TTarifa);                    
                }
            }
            else
            {
//               if(sesionVenta.getRutasConPromocionvigente().indexOf(","+this.listaCorridas[fila][8].toString()+",")>=0 && sesionVenta.isExistePromocionvigente())
//               {
//                   System.out.println("Entra a obtener la tarifa para la promocion vigente.... ");
//
//               }
//              else
//               {
                    if(redondeo.equals("S"))
                        TTarifa = Math.ceil(Tarifa - (tDescuento * Tarifa));
                    else
                    {
                        TTarifa = Tarifa - (tDescuento * Tarifa);
                        System.out.println("checa si procede ceil: "+TTarifa+" - "+ (Double.valueOf(""+TTarifa).longValue()) + " = "+(TTarifa - (Double.valueOf(""+TTarifa).longValue()) ));
                        double d = (TTarifa - (Double.valueOf(""+TTarifa).longValue()) );
                        if(d > 0 && d != .50)
                            TTarifa =    Math.ceil(TTarifa);
                    }
//                }
            }
            //
            if(sesionVenta.isRutaPromocionRedondoTTP(this.listaCorridas[fila][8].toString()) && !(""+tipoPasajero).equals("A") && !normal.equals("S"))
            {
                 System.out.println("Si entra a cambiar la tarifa de redondo TTP(ConstruyeBoleto)...");
                TTarifa = (sesionVenta.getUserCon().getServicioTarifaSencillo() - (tDescuento * sesionVenta.getUserCon().getServicioTarifaSencillo())) * 2;
            }
            suma = suma+TTarifa;
            if(sesionVenta.isRutaConPromocion() && (""+tipoPasajero).equals("M"))
                TarifaMenor = TTarifa;
            Boletos[i][5] = TTarifa;                                                // importe boleto
            Boletos[i][6] = normal;                                                 // BOLETO NORMAL + BOLETO ABIERTO
            Boletos[i][7] = this.listaCorridas[fila][7];                          // empresa
            Boletos[i][8] = this.listaCorridas[fila][3];                          // servicio
            Boletos[i][9] = sesionVenta.getUserCon().getCaja();                     // caja
            Boletos[i][10] = sesionVenta.getUserCon().getCorteId();                 // corte id
            Boletos[i][11] = this.listaCorridas[fila][0];                         // clave corrida
            Boletos[i][12] = this.ClienteId;                                        // cliente id
            Boletos[i][13] = null;                                                  // tipo pago
            Boletos[i][14] = null;                                                  // referencia pago
            Boletos[i][15] = this.transaccion;                                      // tipo operacion
            Boletos[i][16] = null;                                                  // reservacion id
            Boletos[i][17] = null;                                                  // boleto relacionado id
            Boletos[i][18] = null;                                                  // dias validez boleto abierto
            Boletos[i][19] = null;                                                  // folio preimpreso
            Boletos[i][20] = sesionVenta.getUserCon().getTerminalNombre();          // ciudad Venta
            Boletos[i][21] = jCboOrigen.getSelectedItem();//this.listadoCorridas[fila][4]; // origen
            Boletos[i][22] = (jCboDestino.getSelectedItem().toString().equals("TODOS") ? this.listaCorridas[fila][5] : jCboDestino.getSelectedItem()); // destino
            Boletos[i][23] = "L";                                                   // tipo Transaccion
            Boletos[i][24] = sesionVenta.getUserCon().getUsuarioNum();              // cajero
            Boletos[i][25] = this.listaCorridas[fila][6];                         // corridaId
            Boletos[i][26] = this.listaCorridas[fila][1];                         // fecha
            Boletos[i][27] = this.listaCorridas[fila][2];                         // hora
            Boletos[i][28] = sesionVenta.getUserCon().getSnAsiento();               // sin numero de asiento
            Boletos[i][29] = "N";                                                    //Tipo de Venta (N = Normal, F = Referenciada)
            Boletos[i][30] = "";                                                    //Nombre del cliente autorizado 
            Boletos[i][35] = "MOS";                                                    //Tipo de Venta MOS=Contado, CRE=Credito
            System.out.println("El tipo de boleto que asigna es: "+normal);
            Boletos[i][31] = normal+(folioPromocion.equals("")?"":"-")+folioPromocion;                                                    //Identifica al boleto de la parte sencillo del redondo
            //Boletos[i][31] = "S";
            Boletos[i][35] = "MOS";                                                    //Tipo de Venta MOS=Contado, CRE=Credito
            jugandoImporteVenta[i]=tDescuento; // DESCUENTO
            jugandoImporteVentaPermiso[i]=redondeo;//REDONDEO
            
        }
        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}
        sesionVenta.setImporteVenta(suma);
        if(normal.equals("S")){
            xVtaActual.setDataVector(Boletos,encVtaActual);
            AnchoColumnasVtaActual();
        }
        else{
//            encVtaActual = new Object[31];
            encVtaActual = new Object[36];//[32]//VAGL 02082013[35]
            encVtaActual[0]="Corrida-Hora";    encVtaActual[1]="#";              encVtaActual[2] ="T";
            encVtaActual[3]="Nombre Pasajero"; encVtaActual[4]="Origen-Destino"; encVtaActual[5]="Costo";
            encVtaActual[6]="Viaje";
            encVtaActual[7]="7";encVtaActual[8]="8";encVtaActual[9]="9";encVtaActual[10]="10";encVtaActual[11]="";encVtaActual[12]="12";
            encVtaActual[13]="13";encVtaActual[14]="14";encVtaActual[15]="15";encVtaActual[16]="16";encVtaActual[17]="17";encVtaActual[18]="18";
            encVtaActual[19]="19";encVtaActual[20]="20";encVtaActual[21]="21";encVtaActual[22]="22";encVtaActual[23]="23";encVtaActual[24]="24";
            encVtaActual[25]="25";encVtaActual[26]="26";encVtaActual[27]="27";encVtaActual[28]="28";
            encVtaActual[29]="T. Vta";encVtaActual[30]="Nombre Autorizado";
            xVtaActual.setDataVector(Boletos, encVtaActual);
            AnchoColumnasVtaActual2();
        }   
        jLblTotalVtaActual.setText("Total: $"+sesionVenta.customFormat("##,##0.00",suma));
        /* //Se deshabilita por que solo forzaba a la venta cuando era 2X1 y era para orientar a los Taquilleros 14/11/2013 
        if(sesionVenta.isRutaConPromocion() && parejasNoAdultos>0)
        {
            if(numNoAdultosSobrantes>0)
                DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!","<html><Font size=5> Por promoción, si usted solicita "+((int)(TiposAsientos.size()/promo.getCadaNBoletos()))*promo.getCadaNBoletos()+" Adultos y "+numNoAdultosSobrantes+" Menor <br>Usted pagaria: </Font><Font size=5> $"+((((int)(TiposAsientos.size()/promo.getCadaNBoletos()))*TarifaTmp)+(numNoAdultosSobrantes*TarifaMenor))+" Pesos. </Font></html>",Color.RED);
                //DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!","<html><Font size=5> Por promoción, si usted solicita "+((int)(TiposAsientos.size()/2))*2+" Adultos y "+numNoAdultosSobrantes+" Menor <br>Usted pagaria: </Font><Font size=5> $"+((((int)(TiposAsientos.size()/2))*TarifaTmp)+(numNoAdultosSobrantes*TarifaMenor))+" Pesos. </Font></html>",Color.RED);
            else
                //DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!","<html><Font size=5> Por promoción, si usted solicita "+((int)(TiposAsientos.size()/2))*2+" Adultos <br>Usted pagaria: </Font><Font size=5> $"+(((int)(TiposAsientos.size()/2))*TarifaTmp)+" Pesos.</Font></html>",Color.RED);
                DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!","<html><Font size=5> Por promoción, si usted solicita "+((int)(TiposAsientos.size()/promo.getCadaNBoletos()))*promo.getCadaNBoletos()+" Adultos <br>Usted pagaria: </Font><Font size=5> $"+(((int)(TiposAsientos.size()/promo.getCadaNBoletos()))*TarifaTmp)+" Pesos.</Font></html>",Color.RED);
        }
         * 
         */
    }

    private void ConstruyeBoletosReferenciados(String normal) {
        if(Boletos!=null) Boletos=null;
//        Boletos = new Object[sesionVenta.getVariosTmsBoletosVentaTbl().size()][31];
        Boletos = new Object[sesionVenta.getVariosTmsBoletosVentaTbl().size()][36];//[32]//VAGL 02082013[35]
        System.out.println("Construir "+Boletos.length+" boletos referenciados");
        for (int i = 0; i < sesionVenta.getVariosTmsBoletosVentaTbl().size(); i++) {
            TmsBoletosVentaTbl bol = sesionVenta.getVariosTmsBoletosVentaTbl().get(i);
            if(bol.getTipoOperacion().equals("VA"))
                //Boletos[i][0] = formatoFecha.format(bol.getFecha())+"-"+bol.getHora()+"----"; // corrida-hora
                Boletos[i][0] = "------"; // corrida-hora
            else
                Boletos[i][0] = formatoFecha.format(bol.getFecha())+"-"+bol.getHora()+"-"+bol.getClaveCorrida().substring(0,10); // corrida-hora
            Boletos[i][1] = bol.getNoAsiento();                        // asiento
            Boletos[i][2] = bol.getTipoPasajero();                                   // tipo pasajero
            Boletos[i][3] = bol.getNombrePasajero();                                 // nombre pasajero
            Boletos[i][4] = bol.getOrigen()+"-"+bol.getDestino();                    // origen-destino
            Boletos[i][5] = bol.getImporteBoleto();                                  // importe boleto
            Boletos[i][6] = normal;                                                 // BOLETO NORMAL + BOLETO ABIERTO
            Boletos[i][7] = bol.getEmpresa();                                       // empresa
            Boletos[i][8] = bol.getServicio();                                      // servicio
            Boletos[i][9] = sesionVenta.getUserCon().getCaja();                     // caja
            Boletos[i][10] = sesionVenta.getUserCon().getCorteId();                 // corte id
            Boletos[i][11] =  bol.getClaveCorrida();                                // clave corrida
            Boletos[i][12] = this.ClienteId;                                        // cliente id
            Boletos[i][13] = "BRF";                                                 // tipo pago
            Boletos[i][14] = null;                                                  // referencia pago
            if(bol.getTipoOperacion().equals("VA"))
                Boletos[i][15] = "VA";                                              // tipo operacion
            else
                Boletos[i][15] = txVENTA_RF; 
            Boletos[i][16] = null;                                                  // reservacion id
            Boletos[i][17] = bol.getBoletoId();                                     // boleto relacionado id
            if(bol.getTipoOperacion().equals("VA"))
                Boletos[i][18] = bol.getDiasValidezBoletoAbierto();                 // dias validez boleto abierto
            else
                Boletos[i][18] = null; 
            Boletos[i][19] = null;                                                  // folio preimpreso
            Boletos[i][20] = sesionVenta.getUserCon().getTerminalNombre();          // ciudad Venta
            Boletos[i][21] = bol.getOrigen();                                       // origen
            Boletos[i][22] = bol.getDestino();                                      // destino
            Boletos[i][23] = "L";                                                   // tipo Transaccion
            Boletos[i][24] = sesionVenta.getUserCon().getUsuarioNum();              // cajero
            Boletos[i][25] = bol.getCorridaId()      ;                              // corridaId
            if(bol.getTipoOperacion().equals("VA"))
            {
                Boletos[i][26] = null;                                               // fecha
                Boletos[i][27] = null;                                                 // hora
            }
            else
            {
                Boletos[i][26] = formatoFecha.format(bol.getFecha());                  // fecha
                Boletos[i][27] = bol.getHora();                                         // hora
            }
            
            Boletos[i][28] = sesionVenta.getUserCon().getSnAsiento();               // sin numero de asiento
            Boletos[i][29] = "N";                                                   //Tipo de Venta (N = Normal, F = Referenciada)
            Boletos[i][30] = "";                                                    //Nombre del cliente autorizado 
            if(bol.getTipoOperacion().equals("VA"))
                Boletos[i][31] = "R";
            else
                Boletos[i][31] = "S";
            Boletos[i][32] = sesionVenta.getReferenciadoId();
            Boletos[i][35] = "MOS";                                                    //Tipo de Venta MOS=Contado, CRE=Credito
        }
    }

    //Gener numero de folio de la Promocion vigente
    private String obtenerFolioPromocion(){
        String terminal = ""+sesionVenta.getUserCon().getTerminalId();
        if(terminal.length()<4)
        {
            for(int i=terminal.length(); i<4; i++)
                terminal = terminal+"0";
        }
              return  terminal+""+new Date().getTime();
    }

    // BOLETO CAMBIO DE HORARIO - BOL ABIERTO
    private void ConstruyeBoletoCHCBA() {
        if(Boletos!=null) Boletos=null;
//        Boletos = new Object[sesionVenta.getVariosTmsBoletosVentaTbl().size()][31];
        Boletos = new Object[sesionVenta.getVariosTmsBoletosVentaTbl().size()][36];//[32]//VAGL 02082013[35]
        double suma=0;
        for(int i=0; i<sesionVenta.getVariosTmsBoletosVentaTbl().size(); i++){
            Boletos[i][0] = this.listaCorridas[fila][0].toString().substring(0,10)+"-"+this.listaCorridas[fila][1]+" "+this.listaCorridas[fila][2];  // corrida-hora
            Boletos[i][1] = BoletoAVender.get(i).toString();                           // asiento
            Boletos[i][2] = encBO[i][3];                                               // tipo pasajero
            if(this.transaccionAux.equals(txCANJE_BA_RF) || this.transaccionAux.equals(txCHORARIO_RF))
                Boletos[i][3] = encBO[i][7];  
            else                                                                       // nombre pasajero
                Boletos[i][3] = encBO[i][4];                                              
            Boletos[i][4] = jCboOrigen.getSelectedItem().toString()+"-"+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? this.listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString());  // origen-destino
            Boletos[i][5] = sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();

            System.out.println("Entra a calcular el cargo para el cobro...");
            System.out.println("sesionVenta.isCobroCargo(): "+sesionVenta.isCobroCargo());
            System.out.println(""+sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto()+"-"+sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getAdicional15());

            if(sesionVenta.isCobroCargo())
                suma+=Double.valueOf(sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getAdicional15());
            else
                suma+=sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            Boletos[i][6] = "S";                                                    // BOLETO NORMAL + BOLETO ABIERTO
            Boletos[i][7] = this.listaCorridas[fila][7];                               // empresa
            Boletos[i][8] = this.listaCorridas[fila][3];                               // servicio
            Boletos[i][9] = sesionVenta.getUserCon().getCaja();                     // caja
            Boletos[i][10] = sesionVenta.getUserCon().getCorteId();                 // corte id
            Boletos[i][11] = this.listaCorridas[fila][0];                           // clave corrida
            Boletos[i][12] = this.ClienteId;                                        // cliente id
            if(this.transaccion.equals(txCANJE_BA))
            {  
                if(this.transaccionAux.equals(txCANJE_BA_RF))
                    Boletos[i][13] = "BAF";
                else
                   Boletos[i][13] = "BAB";                                             // tipo pago
            }
            else
            {    
                if(this.transaccionAux.equals(txCHORARIO_RF))
                   Boletos[i][13] = encBO[i][12];                                    // tipo pago
                else
                   Boletos[i][13] = encBO[i][9];                                     
            }
            if(this.transaccionAux.equals(txCHORARIO_RF))
                Boletos[i][14] = encBO[i][11];                                            
            else
                Boletos[i][14] = encBO[i][8];                                            // referencia pago
            if(this.transaccionAux.equals(txCANJE_BA_RF) || this.transaccionAux.equals(txCHORARIO_RF))
                Boletos[i][15] = this.transaccionAux;                                // tipo operacion
            else
               Boletos[i][15] = this.transaccion;  
            Boletos[i][16] = null;                                                  // reservacion id
            if(this.transaccionAux.equals(txCANJE_BA_RF) || this.transaccionAux.equals(txCHORARIO_RF))
                Boletos[i][17] = sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getBoletoId(); // boleto relacionado id
            else
                Boletos[i][17] = null; 
            Boletos[i][18] = null;                                                  // dias validez boleto abierto
            Boletos[i][19] = null;                                                  // folio preimpreso
            Boletos[i][20] = sesionVenta.getUserCon().getTerminalNombre();          // ciudad Venta
            Boletos[i][21] = jCboOrigen.getSelectedItem();                          // origen
            Boletos[i][22] = (jCboDestino.getSelectedItem().toString().equals("TODOS") ? this.listaCorridas[fila][5] : jCboDestino.getSelectedItem());//this.listadoCorridas[fila][5];                         // destino
            Boletos[i][23] = "L";                                                   // tipo Transaccion
            Boletos[i][24] = sesionVenta.getUserCon().getUsuarioNum();              // cajero
            Boletos[i][25] = this.listaCorridas[fila][6];                         // corridaId
            Boletos[i][26] = this.listaCorridas[fila][1];                         // fecha
            Boletos[i][27] = this.listaCorridas[fila][2];                         // hora
            Boletos[i][28] = sesionVenta.getUserCon().getSnAsiento();               // sin numero de asiento
            if(this.transaccionAux.equals(txCHORARIO_RF))
            {
                Boletos[i][29] = "F";                                                   //Tipo de Venta (N = Normal, F = Referenciada)
                Boletos[i][30] = sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getNombreAutorizado(); 
                Boletos[i][32] = sesionVenta.getReferenciadoId();                   //Id del primer boleto referenciado
            }
            else
            {
                Boletos[i][29] = "N"; 
                Boletos[i][30] = "";                                                    //Nombre del cliente autorizado 
            }
            Boletos[i][31] = "S";  
            Boletos[i][35] = "MOS";                                                    //Tipo de Venta MOS=Contado, CRE=Credito            
        }
        
        sesionVenta.setImporteVenta(suma);
            
        if(this.transaccion.equals(txCHORARIO)){
            String FechaHoraCorrida = listaCorridas[fila][1].toString()+" "+listaCorridas[fila][2].toString();
            encCH = new Object[Boletos.length][8];
            for(int i=0; i<Boletos.length; i++){
                encCH[i][0] = Boletos[i][11];
                encCH[i][1] = FechaHoraCorrida;
                encCH[i][2] = Boletos[i][1];
                encCH[i][3] = encBO[i][3];
                if(this.transaccionAux.equals(txCHORARIO_RF))
                    encCH[i][4] = encBO[i][7];
                else
                    encCH[i][4] = encBO[i][4];
                encCH[i][5] = Boletos[i][21];
                encCH[i][6] = Boletos[i][22];
                encCH[i][7] = Boletos[i][5];
            }   
            xCH.setDataVector(encCH, encCH2);
            AnchoColumnasCH();
        }
        else{
            jLblBarraEstado.setText(mensajes.getMensajeVta(18));
            xVtaActual.setDataVector(Boletos,encVtaActual);
            AnchoColumnasVtaActual();
            jLblTotalVtaActual.setText("Total: $"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()));
            jTblVtaActual.setRowSelectionInterval(0,0);
            jTblVtaActual.setColumnSelectionInterval(3,3);
            jTblVtaActual.requestFocusInWindow();
            enabledComponents(jTxtAsientos);
        }
    }
    // FIN BOLETO CAMBIO DE HORARIO

    private void llenaNombres(){
        String NombreAux;
        String NombreAuxX;
        if(jTblVtaActual.getRowCount()>0){
            NombreAux=Boletos[0][3].toString();
            if(!NombreAux.equals("")){
                Boletos[0][3]=NombreAux;
                for (int i=1;i<jTblVtaActual.getRowCount();i++){
                    NombreAuxX = Boletos[i][3].toString().trim();
                    if (NombreAuxX.equals(""))
                        Boletos[i][3] = NombreAux;
                    else
                        Boletos[i][3] = Boletos[i][3].toString();
                }
            }
            else{
                NombreAux= "VALIDO AL PORTADOR";
                for (int i=0;i<jTblVtaActual.getRowCount();i++)
                    if (Boletos[i][3].toString().equals(""))
                        Boletos[i][3] = NombreAux;
            }
        }
    }
    
    /*************************************************************************/
    private int EjecutaReservacion(){
        llenaNombres();
        
        DialogoSiNoCancelar = new JDlgSiNoCancelar("¡Reservacion de Boletos!","¿La Reservación es Cancelable?");
        int  r = DialogoSiNoCancelar.getResultado();
        if(r==3){
            int rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
            if(rSP==-1){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado.",Color.RED);
                return -1;
            }
            else{
                if(rSP!=0){
                    DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
                    return 0;
                }
            }
            return 0;
        }
        String Cancelable="C";
        if(r==2) Cancelable="R";
        
        String cRvn=sesionVenta.TerminarReservacionLocal(Boletos, corridaId, Cancelable,
                                                         sesionVenta.getCadenaAsientos(),
                                                         sesionVenta.getCadenaTiposPasaje());
        if(cRvn!=null & !cRvn.equals("-1")){
            DialogoAceptar.mostrarDialogo("¡Importante!","Anote su clave de confirmacion: <<"+cRvn+">>",colorDialogoActivo);
            return 0;
        }
        else{
            if(cRvn==null){
                DialogoAceptar.mostrarDialogo("¡La reservacion no se realizo correctamente!","Contacte al Administrador del sistema.",Color.RED);
                int rSP=-1;
                sesionVenta.iTempos=0;
                while(rSP==-1){
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                    if(rSP==-1){
                        sesionVenta.iTempos++;
                        if(sesionVenta.iTempos>sesionVenta.tTempos) break;
                    }
                }
                return -2;
            }
            else{
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("Reservacion.", "Registro ocupado.",Color.RED);
                return -1;
            }
        }
    }
    
    private void EjecutaCobro(){
        if(!ValidaSesionVenta()) return;
        if(!validaImporteBoletosEnCero()) return;
        if(sesionVenta.isBolRedCer())
            sesionVenta.strCiudadOrigen = ciudadOrigenRedCerr;
        else
            sesionVenta.strCiudadOrigen = jCboCiudadVenta.getSelectedItem().toString();
        System.out.println("Ciudad Origen Original: "+sesionVenta.strCiudadOrigen);
        ubiCompo = this.ucCobro;
        jDlgCobro = new JDlgCobro(Boletos, sesionVenta, "", this.transaccion, this.nombreEmpresa, this.BoletosRedondos, jlbl_reloj);
        if(!jDlgCobro.isCerrar())jDlgCobro.setVisible(true);
        switch(jDlgCobro.getVentaOk()){
            case -1:
                // REANUDO HILO
                System.out.println("Entra a cerrar el cobro...");
                ubiCompo = ucVtaActual;
                this.jTblVtaActual.setRowSelectionInterval(0,0);
                this.jTblVtaActual.setColumnSelectionInterval(3,3);
                this.jTblVtaActual.requestFocusInWindow();
                break;
            case 1:
                Boletos=null;
                    nombreAutorizado = false;
                    jtxt_personaAutorizada.setText("");
                if(this.transaccion.equals(this.txVENTA) || this.transaccion.equals(txVENTA_BR) || this.transaccion.equals(txCANJE_BA)){
                    System.out.println("Desocupo asientos por venta cancelada: "+sesionVenta.getCadenaAsientos());
                    sesionVenta.iTempos=0;
                    int rSP=-1;
                    while(rSP==-1){
                        rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                        if(rSP==-1){
                            sesionVenta.iTempos++;
                            if(sesionVenta.iTempos>sesionVenta.tTempos){
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("TMS Venta.", "Asiento bloqueado.",Color.RED);
                                break;
                            }
                        }
                    }
                    if(rSP!=0 && rSP!=-1)
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
                }
                this.transaccion = txVENTA;
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("¡Venta Cancelada!", "Presione enter para continuar...", Color.RED);
                break;
            case 11: // extension de venta cancelada, pero el motivo es sesion invalida al momento de cobrar
                    nombreAutorizado = false;
                    jtxt_personaAutorizada.setText("");
                if(jThdConsultaCorrida!=null){
                    jThdConsultaCorrida.moreQuotes=false;
                    jThdConsultaCorrida = null;
                    sesionVenta.b_decode_data=false;
                }
                if(jlbl_reloj!=null){
                    jlbl_reloj.finaliza();
                    jlbl_reloj=null;
                }
                if(threadPlantilla!=null){
                    threadPlantilla.noSalir=false;
                    threadPlantilla = null;
                }
                if(this.transaccion.equals(this.txVENTA) || this.transaccion.equals(txVENTA_BR) || this.transaccion.equals(txCANJE_BA)){
                    int rSP=-1;
                    sesionVenta.iTempos=1;
                    while(rSP==-1){
                        rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                        if(rSP==-1){
                            sesionVenta.iTempos++;
                            if(sesionVenta.iTempos>sesionVenta.tTempos){
                                System.out.println("Asiento bloqueado.");
                                break;
                            }
                        }
                    }
                }
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("¡La sesion de venta ya no es vigente!", "<html>La aplicacion sera cerrada.<br>Contacte al administrador del sistema.</html>", Color.RED);
                if(!sesionVenta.finalizarVenta())
                    DialogoAceptar.mostrarDialogo("¡La sesion no se cerro correctamente!", "Contacte al administrador del sistema.", Color.RED);
                BoletoAVender = null;
                Boletos = null;
                BoletosRedondos = null;
                TiposAsientos = null;
                this.dispose();
                return;
            case 13: // extension de venta cancelada, pero el motivo es cnx cerrada
                    nombreAutorizado = false;
                    jtxt_personaAutorizada.setText("");
                if(jThdConsultaCorrida!=null){
                    jThdConsultaCorrida.moreQuotes=false;
                    jThdConsultaCorrida = null;
                    sesionVenta.b_decode_data=false;
                }
                if(jlbl_reloj!=null){
                    jlbl_reloj.finaliza();
                    jlbl_reloj=null;
                }
                if(threadPlantilla!=null){
                    threadPlantilla.noSalir=false;
                    threadPlantilla = null;
                }
                if(!sesionVenta.finalizarVenta())
                    DialogoAceptar.mostrarDialogo("¡La sesion no se cerro correctamente!", "Contacte al administrador del sistema.", Color.RED);
                BoletoAVender = null;
                Boletos = null;
                BoletosRedondos = null;
                TiposAsientos = null;
                this.dispose();
                return;
            case 2:
                // REANUDO HILO
                ubiCompo = ucVtaActual;
                this.jTblVtaActual.setRowSelectionInterval(0,0);
                this.jTblVtaActual.setColumnSelectionInterval(3,3);
                this.jTblVtaActual.requestFocusInWindow();
                return;
            case 3: // ERROR EN TJT CRED
                nombreAutorizado = false;
                jtxt_personaAutorizada.setText("");
                if(jThdConsultaCorrida != null){
                    jThdConsultaCorrida.moreQuotes = false;
                    jThdConsultaCorrida = null;
                    sesionVenta.b_decode_data=false;
                }
                Boletos=null;
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No fue posible realizar la venta.</html>",Color.RED);
                if(this.transaccion.equals(txVENTA)){
                    sesionVenta.iTempos=0;
                    int rSP=-1;
                    while(rSP==-1){
                        rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                        if(rSP==-1){
                            sesionVenta.iTempos++;
                            if(sesionVenta.iTempos>sesionVenta.tTempos){
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("TMS Venta.", "Asiento bloqueado.",Color.RED);
                                break;
                            }
                        }
                    }
                    if(rSP!=0 && rSP!=-1)
                        DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
                }
                this.transaccion = txVENTA;
                break;
            default:
                if(this.sesionVenta.getUserCon().getAplicacionVenta() && !sesionVenta.isVtaBolBco(""))
                {
                    nombreAutorizado = false;
                    jtxt_personaAutorizada.setText("");
                    if(jThdConsultaCorrida != null){
                        jThdConsultaCorrida.moreQuotes = false;
                        jThdConsultaCorrida = null;
                        sesionVenta.b_decode_data=false;
                    }
                    Boletos=null;
                    if(!sesionVenta.isVtaBolBco(this.nombreEmpresa))
                    {
                        String folioActual=sesionVenta.obtenerFolioActual(this.nombreEmpresa);
                        long f1=Long.valueOf(folioActual), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                        if(f1>f2){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Boletos agotados para venta!", "<html>Realice refoliado de boletos para la empresa<br>"+this.nombreEmpresa+".</html>", Color.RED);
                            filtroInicialX();
                            if(sesionVenta.getUserCon().getAplicacionVenta())
                                setLabelFolioX("-",this.nombreEmpresa);
                        }
                        else{
                            if(sesionVenta.getUserCon().getAplicacionVenta())
                                setLabelFolio(folioActual,this.nombreEmpresa);
                            if(f1>f2-sesionVenta.getUserCon().getBolNvtImp())
                                DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>Solo restan "+ ((f2-f1)+1)+ " boletos<br>para la empresa "+this.nombreEmpresa+".</html>", Color.RED);
                        }
                    }
                    /*if(this.transaccion.equals(txVENTA) || this.transaccion.equals(txVENTA_RESERVACION) || this.transaccion.equals(txVENTA_BA)){
                        if(sesionVenta.EFECTIVO_CAJA>=sesionVenta.getUserCon().getLimVenta()) Recoleccion_Libre(true);
                    }*/
                    this.transaccion = txVENTA;
                }
        }
        if(jDlgCobro.getVentaOk()==-1)
            return;
        this.nombreEmpresa = sesionVenta.getUserCon().getEmpresaPrincipal(); // EMPRESA DEFAULT
        filtroInicialX();
        // FINALIZO HILO
        xCorridas.setDataVector(null,encCorridas);
        AnchoColumnasCorridas();
        xVtaActual.setDataVector(null,encVtaActual);
        AnchoColumnasVtaActual();
        jTxtTipoPasaje.setText("");
        jTxtAsientos.setText("");
        nombreAutorizado = false;
        jtxt_personaAutorizada.setText("");
        jLblTotalVtaActual.setText("Total: $0.00");
        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
        vengoDeCobro=true;
        vengoDePantallaCobro=true;
        if(this.transaccion.equals(txVENTA_BA)){this.transaccion=this.txVENTA; this.desHabilitarCriterios(1); return; }
        this.transaccion=this.txVENTA;
        this.desHabilitarCriterios(1);
        jCboServicio.requestFocusInWindow();
    }
 
    public void setLabelFolio(String strFolio, String Empresa) {
      if(sesionVenta.getUserCon().getAplicacionVenta())  
      {
            long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
            if(f1>f2){
                //if(!jLblFolioActual.getText().equals("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">-</font></html>"))
                    jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">-</font></html>");
            }
            else{
                if(strFolio.equals("0")) jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">"+(sesionVenta.isVtaBolBco("")?"0000000000":"-")+"</font></html>");
                else jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">"+strFolio+"</font></html>");
            }
            jLblNombreEmpresa.setText("<html>Empresa: <font color="+ColoresInterfaz.cHTML1+">"+(Empresa.length()>16?Empresa.substring(0,15):Empresa)+"</font></html>");
      }
    }
    
    public void setLabelFolioX(String strFolio, String Empresa) {
      if(sesionVenta.getUserCon().getAplicacionVenta())  
      {

            long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
            if(f1>f2){
                    jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">-</font></html>");
            }
            else{
                if(strFolio.equals("0")) jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">"+(sesionVenta.isVtaBolBco("")?"0000000000":"-")+"</font></html>");
                else jLblFolioActual.setText("<html>Folio Actual: <font color="+ColoresInterfaz.cHTML1+">"+strFolio+"</font></html>");
            }
            jLblNombreEmpresa.setText("<html>Empresa: <font color="+ColoresInterfaz.cHTML1+">"+(Empresa.length()>16?Empresa.subSequence(0,15):Empresa)+"</font></html>");
      }
    }
    
    /*private String NombreEmpresaActual(long EmpresaId, boolean trunco){
        String paraEmpresa="";
        int i, j;
        for(i=0; i<this.sesionVenta.getUserCon().getEmpresasOfertantes().length; i++)
            if(Long.valueOf(this.sesionVenta.getUserCon().getEmpresasOfertantes()[i][0].toString())==EmpresaId){
            if(trunco){
                j=this.sesionVenta.getUserCon().getEmpresasOfertantes()[i][1].toString().length()-1;
                if(j>30)
                    paraEmpresa=this.sesionVenta.getUserCon().getEmpresasOfertantes()[i][1].toString().substring(0,30)+"...";
                else
                    paraEmpresa=this.sesionVenta.getUserCon().getEmpresasOfertantes()[i][1].toString().substring(0,j)+"...";
            } else paraEmpresa=this.sesionVenta.getUserCon().getEmpresasOfertantes()[i][1].toString();
            break;
            }
        return paraEmpresa;
    }*/
    
    /*************************************************************************/
    private void CancelarBoleto(){
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txVENTA_BA)) return;
        if(jTblVtaActual.getRowCount()>0) return;
        
        if(sesionVenta.EFECTIVO_CAJA==0){
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Imposible realizar cancelacion!", "No cuenta con efectivo en caja.", Color.RED);
            return;
        }
        if(this.transaccion.equals(txVENTA)){
            if(!sesionVenta.ValidaFuncionUsuario("5005")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5005", "Cancelar Boleto");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return;
            }
            this.jLblTx.setText("*CANCELACION*");
            jIFCancelar = new JDlgCancelaMultiBoleto(sesionVenta, sesionVenta.getOrigenesDBLink(), objEmp, jlbl_reloj);
            jIFCancelar.setFocusTraversalKeysEnabled(false);
            jIFCancelar.setVisible(true);

            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            transaccion = txVENTA;
            jCboServicio.requestFocusInWindow();
        }
    }
    
    private void CambiarHorarioBoleto(){
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txVENTA_BA)) return;
        if(jTblVtaActual.getRowCount()>0) return;
        if(this.transaccion.equals(txVENTA)){
            if(!sesionVenta.ValidaFuncionUsuario("5006")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5006", "Cambiar Horario Boleto");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return;
            }
            this.jLblTx.setText("*CAMBIO HORARIO*");
            montoPromocionVA = 0;
            tipoPasajeroVA = 'A';
            jIFCHorario = new JDlgCHorarioMultiBoleto(sesionVenta, sesionVenta.getOrigenesDBLink(), objEmp, jlbl_reloj.getFecha());
            jIFCHorario.setFocusTraversalKeysEnabled(false);
            jIFCHorario.setVisible(true);
            if(!jIFCHorario.chOk){
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                jCboServicio.requestFocusInWindow();
                return;
            }
            this.exiteLelatadHO = jIFCHorario.isExisteLealtad();
            System.out.println("jIFCHorario.isRedencionSOC(): "+jIFCHorario.isRedencionSOC());
            this.redencionSOC = jIFCHorario.isRedencionSOC();
            System.out.println("exiteLelatadHO: "+exiteLelatadHO);
            DialogoAceptar.mostrarDialogo("¡El boleto es valido!", "Proceda a cambiarlo de horario...", colorDialogoActivo);
           //VAGL&&RAI 09/02/2009
            System.out.println("sesionVenta.getTmsBoletosVentaTbl().getCorridaId() = "+sesionVenta.getTmsBoletosVentaTbl().getClaveCorrida());
            String ruta_id = sesionVenta.getTmsVtaFacade().getRutaId_corrida(sesionVenta.getTmsBoletosVentaTbl().getClaveCorrida(),sesionVenta.getDBLink());
             System.out.println("ruta_id: "+ruta_id);
            tarifaAdultoCHBA=  sesionVenta.getTarifaTramo(ruta_id+sesionVenta.getTmsBoletosVentaTbl().getServicio()+
                            sesionVenta.getTmsBoletosVentaTbl().getOrigen()+
                            sesionVenta.getTmsBoletosVentaTbl().getDestino()+
                            "SEN",0);
                        
            if(sesionVenta.getUserCon().getAplicacionVenta())
            {
                long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                long f3=(f2-f1)+1;
                if(1>f3){
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("Limite de folios.", "Boletos agotados para realizar cambio de horario.", Color.RED);
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    if(sesionVenta.getUserCon().getAplicacionVenta())
                        setLabelFolioX("-",this.nombreEmpresa);
                    jCboServicio.requestFocusInWindow();
                    return;
                }
                setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
            }
            montoPromocionVA = jIFCHorario.importeBolOriginalAdulto;
            System.out.println("tipoPasajeroVA en CambiarHorarioBoleto: "+jIFCHorario.tipoPasajero);
            char[] tpc = jIFCHorario.tipoPasajero.toCharArray();
            tipoPasajeroVA = tpc[0];
            System.out.println("tipoPasajeroVA = "+tipoPasajeroVA);
            this.transaccionAux=this.txCHORARIO;
            sesionVenta.setCambHorRer(false);
            sesionVenta.setReferenciaOriginal("");
            sesionVenta.setReferenciadoId("");
            sesionVenta.setReferenciadoId("");
            if(jIFCHorario.isReferenciado())
            {
                this.transaccionAux=this.txCHORARIO_RF;
                sesionVenta.setCambHorRer(true);
                sesionVenta.setReferenciaOriginal(jIFCHorario.getReferencia());
                sesionVenta.setReferenciadoId(jIFCHorario.getBoletoReferenciadoId());
            }
            encBO=jIFCHorario.boletos;
            this.sesionVenta.setNumeroTarjetaSocio(jIFCHorario.getNumTarLeal());
            //
            int i,j;
            encBOx = new Object[encBO.length][8];
            System.out.println("transaccionAux = "+this.transaccionAux);
            if(this.transaccionAux.equals(txCHORARIO_RF))
            {    
                for(i=0; i<encBO.length; i++)
                {
                  encBOx[i][0] = encBO[i][0];encBOx[i][1] = encBO[i][1];
                  encBOx[i][2] = encBO[i][2];encBOx[i][3] = encBO[i][3];
                  encBOx[i][4] = encBO[i][7];encBOx[i][5] = encBO[i][4];
                  encBOx[i][6] = encBO[i][5];encBOx[i][7] = encBO[i][6];
                }
            }
            else
            {    
                for(i=0; i<encBO.length; i++)
                    for(j=0; j<8; j++)
                        encBOx[i][j] = encBO[i][j];
            }
            
            //
            xBO.setDataVector(encBOx, encBO2);
            AnchoColumnasBO();
            Object[] xEnc = null;
            xCH.setDataVector(null, xEnc);
            jScpVtaActual.setVisible(false);
            jLblTotalVtaActual.setVisible(false);
            jPnlCHorario.setVisible(true);

            this.transaccion=this.txCHORARIO;
            //
            jCboCiudadVenta.setSelectedItem(jIFCHorario.CiudadCorridaCH);
            //
            sesionVenta.cargarOrigenesDestinosServicios(jCboCiudadVenta.getSelectedItem().toString(), false);
            cbmServicio = new DefaultComboBoxModel(sesionVenta.getServicios());
            cbmOrigen = new DefaultComboBoxModel(sesionVenta.getOrigenes());
            cbmDestino = new DefaultComboBoxModel(sesionVenta.getDestinos());
            jCboOrigen.setModel(cbmOrigen);
            jCboDestino.setModel(cbmDestino);
            jCboServicio.setModel(cbmServicio);
            //xcv
//            System.out.println("Datos para verificar encBO[0][4]: "+encBO[0][4].toString());
//            System.out.println("Datos para verificar encBO[0][5]: "+encBO[0][5].toString());
//            System.out.println("Datos para verificar encBO[0][6]: "+encBO[0][6].toString());
            if(this.transaccionAux.equals(txCHORARIO_RF))
            {
                jCboOrigen.setSelectedItem(encBO[0][4].toString());
                jCboDestino.setSelectedItem(encBO[0][5].toString());
            }
            else
            {
                jCboOrigen.setSelectedItem(encBO[0][5].toString());
                jCboDestino.setSelectedItem(encBO[0][6].toString());
            }
            jCboServicio.setSelectedItem(sesionVenta.getServicioLetra(jIFCHorario.ServicioCH));
            this.desHabilitarCriterios(2);
        }
    }
    
    private void VenderReservacion(){
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txVENTA_BA)) return;
        if(jTblVtaActual.getRowCount()>0) return;
        if(this.transaccion.equals(txVENTA)){
            this.jLblTx.setText("*RESERVACIONES*");
            jIFControlRvn = new JDlgControlRvn(sesionVenta, this.nombreEmpresa, sesionVenta.getOrigenesDBLink(), jLblNombreEmpresa, jLblFolioActual, jlbl_reloj);
            jIFControlRvn.setFocusTraversalKeysEnabled(false);
            this.ubiCompo = this.ucReservacion;
            jIFControlRvn.setVisible(true);
            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            jCboServicio.requestFocusInWindow();
        }
    }
    
    private String PeriodoVacacionalX(String tipoPasaje){
        if(!tipoPasaje.equals("S") && !tipoPasaje.equals("E") &&
                !tipoPasaje.equals("P") && !tipoPasaje.equals("C") && !tipoPasaje.equals("M")) return "";
        if(tipoPasaje.equals("M")) return "(-1)";
        return "1";
    }
    
    public boolean CambioHorario(){
        System.out.println("nombreEmpresa: "+this.nombreEmpresa);
        System.out.println("redencionSOC :"+this.redencionSOC);
        System.out.println("isEmpresaRedencion: "+sesionVenta.isEmpresaRedencion(this.nombreEmpresa));
        if(this.redencionSOC && !sesionVenta.isEmpresaRedencion(this.nombreEmpresa))
        {
                DialogoAceptar.mostrarDialogo("¡Boleto de redención!","<html>No es posible relizar el cambio de horario para esta corrida<br>La empresa no permite redención</html>", colorDialogoActivo);                    
            System.out.println("CHO - desocupar (_OcuparAsientosSP)(POR REDENCION)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
            sesionVenta.iTempos=0;
            int rSP=-1;
            while(rSP==-1){
                rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                if(rSP==-1){
                    sesionVenta.iTempos++;
                    if(sesionVenta.iTempos>sesionVenta.tTempos){
                        break;
                    }
                }
            }
            if(rSP!=0 && rSP!=-1){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
            }
            return false;
        }
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Esta seguro de realizar el Cambio de Horario?",true);
        boolean res = DialogoSiNo.getResultado();
        if(res){
            //Se manda a cobrar el cargo por cambio de horario
            if (sesionVenta.isCobroCargo())
            {
            //      EjecutaCobro();
                jDlgCobro = new JDlgCobro(Boletos, sesionVenta, "", this.transaccion, this.nombreEmpresa, this.BoletosRedondos, jlbl_reloj);
                if(!jDlgCobro.isCerrar())jDlgCobro.setVisible(true);
                if(jDlgCobro.getVentaOk()!=0)
                    return false;
                System.out.println("(HO)sesionVenta.getTipoPagoCargo(): "+sesionVenta.getTipoPagoCargo());
                System.out.println("(HO)sesionVenta.getAprobacionCargo(): "+sesionVenta.getAprobacionCargo());

                for(int j=0; j<Boletos.length; j++)
                {
                    Boletos[j][13] = sesionVenta.getTipoPagoCargo();
                    Boletos[j][14] =  sesionVenta.getAprobacionCargo();
                }

           }


            int res1;
            sesionVenta.strCiudadOrigen = jCboCiudadVenta.getSelectedItem().toString();
            System.out.println("Ciudad Origen en cambio de horario: "+sesionVenta.strCiudadOrigen);
            System.out.println("****** Antes de _RegistroVentaSP ...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
//            res1=sesionVenta._RegistroVentaSP(Boletos);
//                    for(int k=0; k<sesionVenta.getFoliosBoletos().size(); k++)
//                    {
//                        Vector v = (Vector)sesionVenta.getFoliosBoletos().get(k);
//                        //private Object[][] Boletos;
//                        for(int l=0; l<Boletos.length; l++)
//                        {
//                            if(v.get(0).toString().equals(Boletos[l][1]))
//                            {
//                                Boletos[l][19]=v.get(1).toString();
//                                break;
//                            }
//                        }
//                    }            
//            System.out.println("****** Despues de _RegistroVentaSP ...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());

////////////////////////////////////////////
                   List<TmsTiposPasajeroTbl> listado = sesionVenta.getTiposPasajeLealtad();
                    System.out.println("SOCIO INTIMO(HO)... ");
                    boolean pideTarjetaLealtad = false;
                   if(sesionVenta.isEmpresaLealtad(Boletos[0][7].toString()))
                   {
                        for(int m=0; m<listado.size(); m++)
                        {
                            if(listado.get(m).getAplicaLealtad()!=null)
                            {
                                if(listado.get(m).getAplicaLealtad().equals("S"))
                                    pideTarjetaLealtad = true;
                            }
                            System.out.println("Lealtad(HO)("+listado.get(m).getRutaId()+")"+listado.get(m).getLealtad() +" Lunes: "+listado.get(m).getL_LUNES() +" Tipo: "+listado.get(m).getAplicaTipoLealtad()+" Aplica ("+listado.get(m).getLetraTipo()+") = "+listado.get(m).getAplicaLealtad());
                        }
                    }
                    System.out.println("pideTarjetaLealtad(HO) : "+pideTarjetaLealtad);
                    System.out.println("exiteLelatadHO: "+this.exiteLelatadHO);
                    boolean insertaLealtad = false;
                    Vector insertados = null;
                    boolean muestraEstrellas = this.sesionVenta.getNumeroTarjetaSocio().equals("") ?true :false;
                    if(pideTarjetaLealtad && exiteLelatadHO) 
                    {
                        System.out.println("Entra a pedir tarjeta de lealtad(HO)...");
                        System.out.println("Numero Tarjeta Socio: "+this.sesionVenta.getNumeroTarjetaSocio());
                        JDlgAcumulaEstrellasHO acumula = new  JDlgAcumulaEstrellasHO(new javax.swing.JDialog(), true, this.Boletos, this.sesionVenta,listado,(this.sesionVenta.getNumeroTarjetaSocio().equals("") ?true :false),sesionVenta.getNumeroTarjetaSocio() );
                        if(acumula.isCompletado()){
                             this.Boletos =  acumula.getBoletos(); 
                             insertaLealtad = true;
                             insertados = acumula.getRegistros();
                        }
                        else
                            acumula = null;
                    }//if(pideTarjetaLealtad)                  
///////////////////////////////////////////  
                    System.out.println("Boletos[0][13]: "+Boletos[0][13]);
                    System.out.println("Boletos[0][14]: "+Boletos[0][14]);
                    System.out.println("Boletos[0][33]: "+Boletos[0][33]);
                    System.out.println("Boletos[0][34]: "+Boletos[0][34]);
            res1=sesionVenta._RegistroVentaSP(Boletos);
                    for(int k=0; k<sesionVenta.getFoliosBoletos().size(); k++)
                    {
                        Vector v = (Vector)sesionVenta.getFoliosBoletos().get(k);
                        //private Object[][] Boletos;
                        for(int l=0; l<Boletos.length; l++)
                        {
                            if(v.get(0).toString().equals(Boletos[l][1]))
                            {
                                Boletos[l][19]=v.get(1).toString();
                                break;
                            }
                        }
                    }            
            System.out.println("****** Despues de _RegistroVentaSP ...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
            if(res1==0){
            //Manda a cancelar el boleto original del cambio de horario                
                    sesionVenta.cancelaLealtad();
///////////////////////////
                    
                    if(insertaLealtad && res1==0){
                        System.out.println("Entra a acumular estrellas en HO..." );
                        java.lang.String numeroOperacion = "";
                        for(int k=0;k<insertados.size(); k++)
                        {//String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento
                            Vector v =(Vector) insertados.get(k);
                            //System.out.println("Vector("+k+"):  "+v);
                            numeroOperacion = v.get(3).toString();
                            sesionVenta.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()),v.get(1).toString(),v.get(2).toString(),v.get(3).toString(),v.get(4).toString(),"A");
                        } 
                        
                        System.out.println("Acumula Estrellas...");
                        if(!sesionVenta.isUsarWSLelatad())
                        {
                            try{
                              String respuestaLealtad = sesionVenta.getTmsVtaFacade().Registra_Transaccion_Lealtad(numeroOperacion,"");
                              String[] arrayrsp = respuestaLealtad.split(",");
                               System.out.println("Respueesta(HO0): "+arrayrsp[0]);
                                //if(Boolean.parseBoolean(arrayrsp[0)))
                                    System.out.println("Respueesta(HO1): "+arrayrsp[1]);
                                    System.out.println("Respueesta(HO2): "+arrayrsp[2]);
                                    System.out.println("muestraEstrellas(HO): "+muestraEstrellas);
                                    if(muestraEstrellas)
                                    {
                                        if(arrayrsp[0].equals("@error"))
                                            DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                                        else
                                            DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+arrayrsp[1].replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                                        if(Boolean.parseBoolean(arrayrsp[0]) && Boolean.parseBoolean(arrayrsp[2]))
                                            DialogoAceptar.mostrarDialogo("¡Estrellas de Tarjeta!","<html><Font size=6> ¡ Sus Estrellas estan proximas a vencer!</Font></html>", colorDialogoActivo);              
                                    }              

                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion(A)");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                            }
                        }
                        else
                        {
                            try { // Call Web Service Operations
                                wsLealtad.OperacionesResponse result = sesionVenta.getWSPort().getOperacion(numeroOperacion);
                                //result.getStatus().isSuccess();
                                System.out.println("(WS)Result(getOperacion(A)(HO)("+numeroOperacion+")) = "+result.getStatus().isSuccess());
                                if(result.getStatus().isSuccess()){
                                   System.out.println("Puntos Otorgados: "+result.getPuntos().getPuntosOtorgados()); 
                                   System.out.println("           Saldo: "+result.getPuntos().getSaldoPuntos()); 
                                   if(this.sesionVenta.getNumeroTarjetaSocio().equals(""))
                                        DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas exitosa!","<html>Se acumularon:  <Font size=16>"+result.getPuntos().getPuntosOtorgados()+"</font> Estrellas  <br>        Saldo Actual:  <Font size=16>"+result.getPuntos().getSaldoPuntos()+"</font> Estrellas</html>", colorDialogoActivo);                    
                                }else
                                    DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>"+result.getStatus().getMessage()+"</html>", Color.RED);
                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion del WS (Tarjeta Credito)");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);

                            }
                        }
                    }                      
                    
//////////////////////
                
                sesionVenta.iTempos=0;
                int rSP=-1;
                while(rSP==-1){
                    System.out.println("****** Antes de _liberaAsientoCancelado ...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
                    rSP=sesionVenta._liberaAsientoCancelado(sesionVenta.getTmsBoletosVentaTbl().getClaveCorrida());
                    System.out.println("****** Despues de _liberaAsientoCancelado ...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
                    if(rSP==-1){
                        sesionVenta.iTempos++;
                        if(sesionVenta.iTempos>sesionVenta.tTempos){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Asiento bloqueado.",Color.RED);
                            break;
                        }
                    }
                }
                if(rSP!=0 && rSP!=-1){
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No fue posible liberar el asiento o<br>tipo de pasajero del boleto original.</html>", Color.RED);
                }
                vTP=null;
                if(sesionVenta.getUserCon().getAplicacionVenta()) 
                    setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
                //DialogoAceptar.mostrarDialogo("¡PAUSA!","Antes de checar el cambio de horario....", Color.RED);
                System.out.println("Hora antes de checar el cambio de horario...."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
                if(transaccionAux.equals(txCHORARIO_RF))       
                {
                       Vector ids = new Vector();
                       for(int bf = 0; bf<sesionVenta.getVariosTmsBoletosVentaTbl().size(); bf++)
                       {
                            Vector id = new Vector();
                            TmsBoletosVentaTbl bolRef = sesionVenta.getVariosTmsBoletosVentaTbl().get(bf);
                            id.add(bolRef.getBolReferenciadoId());
                            id.add(bolRef.getDocumento());
                            ids.add(id);
                       }
                       //DialogoAceptar.mostrarDialogo("¡PAUSA!","Antes de liberaReferenciado....", Color.RED);
                        System.out.println("Antes de liberaReferenciado....."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
                       if(!sesionVenta.liberaReferenciado(ids))
                           DialogoAceptar.mostrarDialogo("¡Error!","Los boletos referenciados no pudieron ser actualizados.", Color.RED);
                }
//                transaccion = txVENTA;
//                transaccionAux = txVENTA;
                else
                {
                    ImprimeBoletos x = new ImprimeBoletos();
                    if(!x.ImprimeDatos(sesionVenta.getTmsBoletosVentaTbl().getTipoPago(), sesionVenta.getUserCon().getPrefijo(),
                                       Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), Boletos,
                                       this.transaccion, sesionVenta.getUserCon().getDiasValBab(),
                                       sesionVenta.getUserCon().getUsuarioNom(), sesionVenta, jlbl_reloj.getFecha(),sesionVenta.getTmsVtaFacade().getPromocionVigente(),"") ) {
                        Boletos=null;
                        if(sesionVenta.getVariosTmsBoletosVentaTbl().size()==1) DialogoAceptar.mostrarDialogo("Cambio de horario.","Ahora marque el boleto <<"+sesionVenta.getTmsBoletosVentaTbl().getFolioPreimpreso()+">> como cambio de horario...",colorDialogoActivo);
                        else DialogoAceptar.mostrarDialogo("Cambio de horario.", "Ahora marque los boletos originales como cambio de horario...", colorDialogoActivo);
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("Cambio de horario.","<html>¡Cambio de horario registrado!<br>Boleto no impreso por falla de impresion.</html>", Color.RED);
                        transaccion = txVENTA;
                        transaccionAux = txVENTA;
                        sesionVenta.setReferenciaOriginal("");
                        sesionVenta.setReferenciadoId("");
                        sesionVenta.setCambHorRer(false);
                        jLblTotalVtaActual.setText("Total: $0.00");
                        return true;
                    }
                }
                 System.out.println("Despues de liberaReferenciado o no....."+sesionVenta.getTmsVtaFacade()._ObtieneFechaHoraBD2());
                Boletos=null;
                jLblTotalVtaActual.setText("Total: $0.00");
                if(transaccionAux.equals(txCHORARIO_RF))
                    DialogoAceptar.mostrarDialogo("¡Cambio de Horario Correcto!","<html>El boleto referenciado fue cambiado correctamente con <br>la misma referencia <font size=16>"+sesionVenta.getReferenciaOriginal()+"</font>.</html>.", colorDialogoActivo);
                else
                {                    
                    if(sesionVenta.getVariosTmsBoletosVentaTbl().size()==1) DialogoAceptar.mostrarDialogo("Cambio de horario.","Ahora marque el boleto <<"+sesionVenta.getTmsBoletosVentaTbl().getFolioPreimpreso()+">> como cambio de horario...",colorDialogoActivo);
                    else DialogoAceptar.mostrarDialogo("Cambio de horario.", "Ahora marque los boletos originales como cambio de horario...", colorDialogoActivo);
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Cambio de Horario Correcto!","Tome el Nuevo Boleto de la impresora.", colorDialogoActivo);
                }
                transaccion = txVENTA;
                transaccionAux = txVENTA;
                sesionVenta.setReferenciaOriginal("");
                sesionVenta.setReferenciadoId("");
                sesionVenta.setCambHorRer(false);
                if(sesionVenta.getUserCon().getAplicacionVenta() && !sesionVenta.isVtaBolBco(this.nombreEmpresa))
                {
                    String folioActual=sesionVenta.obtenerFolioActual(this.nombreEmpresa);
                    long f1=Long.valueOf(folioActual), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                    if(f1>f2){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Boletos agotados para venta!", "<html>Realice refoliado de boletos para la empresa<br>"+this.nombreEmpresa+".</html>", Color.RED);
                        if(sesionVenta.getUserCon().getAplicacionVenta())
                            setLabelFolioX("-",this.nombreEmpresa);
                    }
                    else{
                        if(sesionVenta.getUserCon().getAplicacionVenta())
                            setLabelFolio(folioActual,this.nombreEmpresa);
                        if(f1>f2-sesionVenta.getUserCon().getBolNvtImp())
                            DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>Solo restan "+ ((f2-f1)+1)+ " boletos<br>para la empresa "+this.nombreEmpresa+".</html>", Color.RED);
                    }
                }
            }
            else{
                System.out.println("CHO - desocupar (_OcuparAsientosSP)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
                sesionVenta.iTempos=0;
                int rSP=-1;
                while(rSP==-1){
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                    if(rSP==-1){
                        sesionVenta.iTempos++;
                        if(sesionVenta.iTempos>sesionVenta.tTempos){
                            break;
                        }
                    }
                }
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("¡Aviso!","<html>No fue posible realizar el cambio de horario.<br>Contacte al Administrador del sistema.</html>", Color.RED);
            }
        }
        else{
            System.out.println("CHO - desocupar (_OcuparAsientosSP)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
            sesionVenta.iTempos=0;
            int rSP=-1;
            while(rSP==-1){
                rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                if(rSP==-1){
                    sesionVenta.iTempos++;
                    if(sesionVenta.iTempos>sesionVenta.tTempos){
                        break;
                    }
                }
            }
            if(rSP!=0 && rSP!=-1){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
            }
        //    filtroInicialX();
            //xVtaActual.setDataVector(null, encVtaActual);
            //AnchoColumnasVtaActual();
            //return false;
        }
        System.out.println("Manda a rehacer la tabla de Venta Actual....");
//        xVtaActual.setDataVector(null, encVtaActual);
//        AnchoColumnasVtaActual2();
      //  filtroInicialX();
        jTblVtaActual.repaint();
        vTP=null;
        Boletos=null;
        jLblTotalVtaActual.setText("Total: $0.00");
        sesionVenta.setCobroCargo(false);
        sesionVenta.setTipoPagoCargo("");
        sesionVenta.setAprobacionCargo("");
        this.transaccion = this.txVENTA;
        return true;
    }    
    
    public boolean CanjeBA(){
        if(!ValidaSesionVenta()) return true;
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Esta seguro de realizar el canje del boleto abierto?",true);
        boolean res = DialogoSiNo.getResultado();
        if(res){
            salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
            if(salidaImpresionTVRef==null){
                System.out.println("No encontro impresora de voucher");
                return true;
            }
            sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(this.nombreEmpresa));
            sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta. getNombreImpresoraVoucherBlanco(this.nombreEmpresa));
            sesionVenta.strCiudadOrigen = jCboCiudadVenta.getSelectedItem().toString();
            System.out.println("Ciudad Origen en canje de boleto abierto: "+sesionVenta.strCiudadOrigen);
            int res1=sesionVenta._RegistroVentaSP(Boletos);
            boolean vtaMoto = false;
            if(res1==0){
                 Vector ids = new Vector();
                if(this.transaccionAux.equals(txCANJE_BA_RF))
                {
                   for(int bf = 0; bf<sesionVenta.getVariosTmsBoletosVentaTbl().size(); bf++)
                   {
                        Vector id = new Vector();
                        TmsBoletosVentaTbl bolRef = sesionVenta.getVariosTmsBoletosVentaTbl().get(bf);
                        if(bolRef.getAdicional10().equals("CALL_CENTER"))
                            vtaMoto = true;
                        id.add(bolRef.getBolReferenciadoId());
                        id.add(bolRef.getDocumento());
                        ids.add(id);
                   }
                   if(!sesionVenta.liberaReferenciado(ids))
                       DialogoAceptar.mostrarDialogo("¡Error!","Los boletos referenciados no pudieron ser actualizados.", Color.RED);
                }
                 
               ImprimeBoletos x = new ImprimeBoletos();
                boolean result = false;
                if(this.transaccionAux.equals(txCANJE_BA_RF))
                {
                    result =x.ImprimeDatos("BAF", sesionVenta.getUserCon().getPrefijo(),
                                   Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), Boletos,
                                   this.transaccion, sesionVenta.getUserCon().getDiasValBab(),
                                  sesionVenta.getUserCon().getUsuarioNom(), sesionVenta, jlbl_reloj.getFecha(), sesionVenta.getTmsVtaFacade().getPromocionVigente(),"") ;
                }
                else
                {
                    result =x.ImprimeDatos("BAB", sesionVenta.getUserCon().getPrefijo(),
                                   Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), Boletos,
                                   this.transaccion, sesionVenta.getUserCon().getDiasValBab(),
                                  sesionVenta.getUserCon().getUsuarioNom(), sesionVenta, jlbl_reloj.getFecha(),sesionVenta.getTmsVtaFacade().getPromocionVigente(),"") ;
                }
                if(!result){
                    Boletos=null;
                    jLblTotalVtaActual.setText("Total: $0.00");
                    if(this.transaccionAux.equals(txCANJE_BA_RF))
                        DialogoAceptar.mostrarDialogo("Boletos canjeados.", "Los boletos referenciados han sido canjeados...", colorDialogoActivo);
                    else
                    {
                        if(sesionVenta.getVariosTmsBoletosVentaTbl().size()==1) DialogoAceptar.mostrarDialogo("¡Boleto original canjeado!", "Ahora marque el boleto <<"+sesionVenta.getTmsBoletosVentaTbl().getFolioPreimpreso()+">> como canjeado...", colorDialogoActivo);
                        else DialogoAceptar.mostrarDialogo("Boletos canjeados.", "Ahora marque los boletos originales como canjeados...", colorDialogoActivo);
                    }
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Canje de boleto abierto registrado!","Boleto no impreso por falla de impresion.", Color.RED);
                    transaccion = txVENTA;
                }
                else{
                    //Imprime comprobante de Canje de Boleto Abierto Referenciado
                    imprimir_ticket_canjeBA_ref ticket = null;
                    if(this.transaccionAux.equals(txCANJE_BA_RF))
                    {
                         ticket = new imprimir_ticket_canjeBA_ref(sesionVenta.getNombreAutorizado(), sesionVenta.getReferenciaCanjeBA(), "",sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom(),Boletos, ids,"Boleto Abierto",this.sesionVenta);
                         ticket.ImprimeDatos(salidaImpresionTVRef,sesionVenta.isVoucherBlanco(),sesionVenta.getImpresoraNombreVoucherBlanco());
                       //VAGL 06092011 Reimpresion de Voucher cuando un boleto referencia se pago con TDC
                        if(!sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2().equals(""))
                        {
                            
                            JClsPinPadTBRequest jClsPinPadTBRequest = new JClsPinPadTBRequest();
                            JClsPinPadTBResponse jClsPinPadTBResponse = jClsPinPadTBRequest.getReimpresionVoucher(sesionVenta.getUserCon().getDbgSetUrl(),String.valueOf(sesionVenta.getImporteVenta()),sesionVenta.getUserCon().getBs_User(),
                                                                                                        sesionVenta.getUserCon().getBs_Pwd(),sesionVenta.getUserCon().getUsuarioNum(),sesionVenta.getUserCon().getBs_Company(),
                                                                                                        sesionVenta.getUserCon().getBs_Branch(),sesionVenta.getUserCon().getBs_Country(),sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2());
                            String msg = "<html>";
                            if(jClsPinPadTBResponse.getRspDsResponse().equals("approved") || !sesionVenta.isActivoEMVFull())
                            {
                                String vou = "";
                                if(sesionVenta.isActivoEMVFull())
                                {
                                    try{
                                        if(sesionVenta.isImpVouComercio())
                                            vou = jClsPinPadTBResponse.getRspVoucherComercio()+"\n@br  \n@br  "+jClsPinPadTBResponse.getRspVoucherCliente();
                                        else
                                            vou = jClsPinPadTBResponse.getRspVoucherCliente()+"\n@br  \n@br  ";
                                    } catch (Exception e)
                                    {e.printStackTrace(); DialogoAceptar.mostrarDialogo("¡Configuracion EVM Full!", "¡La DLL del EMV Full no esta configurada!\n Contacte al Administrador del Sistema", Color.RED);}
                                }
                                else
                                    vou = jClsPinPadTBResponse.getRspQryRePrint();


                                    //vou = jClsPinPadTBResponse.getRspVoucher();
                                JClsImprimeVoucher jClsImprimeVoucher = new JClsImprimeVoucher();
                                //VAGL 14092011 Se manda a llamar dos metodos en lugar de unos solo para imprimir el voucher
                                //jClsImprimeVoucher.ImprimeDatos(jClsPinPadTBResponse.getRspVoucher(), salidaImpresionTVRef, 0,sesionVenta.getReferenciaCanjeBA(),"");
                                System.out.println("Reimpresion de Voucer con(BA): ");
                                System.out.println("ImpresoraNombreVoucherBlanco: "+sesionVenta.getImpresoraNombreVoucherBlanco());
                                System.out.println("sesionVenta.isVoucherBlanco(): "+sesionVenta.isVoucherBlanco());
                                jClsImprimeVoucher.ReimprimeDatos(vou, salidaImpresionTVRef, 0,sesionVenta.getReferenciaCanjeBA(),"",(vtaMoto?"MO/TO":""),Boletos, ids, sesionVenta.getImpresoraNombreVoucherBlanco(), sesionVenta.isVoucherBlanco());
                           }


                          DialogoAceptar.mostrarDialogo("Boletos canjeados.", "<html>Los boletos referenciados han sido canjeados<br> Tome el comprobante del canje <br> y solicite la firma del voucher para el banco</html>", colorDialogoActivo);

                        }
                    }
                    Boletos=null;
                    jLblTotalVtaActual.setText("Total: $0.00");
                    if(this.transaccionAux.equals(txCANJE_BA_RF) && sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2().equals(""))
                        DialogoAceptar.mostrarDialogo("Boletos canjeados.", "<html>Los boletos referenciados han sido canjeados<br> Tome el comprobante del canje</html>", colorDialogoActivo);
                    else
                    {
                        if(sesionVenta.getVariosTmsBoletosVentaTbl().size()==1) DialogoAceptar.mostrarDialogo("¡Boleto original canjeado!", "Ahora marque el boleto <<"+sesionVenta.getTmsBoletosVentaTbl().getFolioPreimpreso()+">> como canjeado...", colorDialogoActivo);
                        else DialogoAceptar.mostrarDialogo("Boletos canjeados.", "Ahora marque los boletos originales como canjeados...", colorDialogoActivo);
                    }
                    this.getToolkit().beep();
                    DialogoAceptar.mostrarDialogo("¡Canje de boleto abierto correcto!","Tome el nuevo boleto de la impresora.", colorDialogoActivo);
                    transaccion = txVENTA;
                }
                if(!sesionVenta.isVtaBolBco(this.nombreEmpresa))
                {
                    String folioActual=sesionVenta.obtenerFolioActual(this.nombreEmpresa);
                    long f1=Long.valueOf(folioActual), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                    if(f1>f2){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("¡Boletos agotados para venta!", "<html>Realice refoliado de boletos para la empresa<br>"+this.nombreEmpresa+".</html>", Color.RED);
                        if(sesionVenta.getUserCon().getAplicacionVenta() || sesionVenta.isVtaBolBco(this.nombreEmpresa))
                            setLabelFolioX("-",this.nombreEmpresa);
                    }
                    else{
                        if(sesionVenta.getUserCon().getAplicacionVenta() || sesionVenta.isVtaBolBco(this.nombreEmpresa))
                            setLabelFolio(folioActual,this.nombreEmpresa);
                        if(f1>f2-sesionVenta.getUserCon().getBolNvtImp())
                            DialogoAceptar.mostrarDialogo("Limite de folios.", "<html>Solo restan "+ ((f2-f1)+1)+ " boletos<br>para la empresa "+this.nombreEmpresa+".</html>", Color.RED);
                    }
                } 
            }
            else{
                System.out.println("CHO - desocupar (_OcuparAsientosSP)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
                sesionVenta.iTempos=0;
                int rSP=-1;
                while(rSP==-1){
                    rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                    if(rSP==-1){
                        sesionVenta.iTempos++;
                        if(sesionVenta.iTempos>sesionVenta.tTempos){
                            break;
                        }
                    }
                }
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.", "No fue posible realizar el canje de boleto.",Color.RED);
            }
        }
        else{
            System.out.println("AC - desocupar (_OcuparAsientosSP)"+this.corridaId+" - "+sesionVenta.getCadenaAsientos()+" - "+sesionVenta.getCadenaTiposPasaje());
            sesionVenta.iTempos=0;
            int rSP=-1;
            while(rSP==-1){
                rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                if(rSP==-1){
                    sesionVenta.iTempos++;
                    if(sesionVenta.iTempos>sesionVenta.tTempos){
                        break;
                    }
                }
            }
            if(rSP!=0 && rSP!=-1){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>No fue posible liberar asiento o<br>tipo de pasajero.</html>",Color.RED);
            }
        }
        filtroInicialX();
        Boletos=null;
        this.transaccion = this.txVENTA;
        jTxtAsientos.setText("");
        xVtaActual.setDataVector(null, encVtaActual);
        AnchoColumnasVtaActual();
        xCorridas.setDataVector(null, encCorridas);
        AnchoColumnasCorridas();
        this.transaccion = this.txVENTA;
        this.desHabilitarCriterios(1);
        return true;
    }
    
    private void VenderBoletoAbierto(){
        if(!ValidaSesionVenta()) return;
        if(this.transaccion.equals(txVENTA_BA)){
            if(!sesionVenta.ValidaFuncionUsuario("5007")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5007", "Vender Boleto Abierto");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return;
            }
            //jLblBarraEstado.setText(mensajes.getMensajeVta(16));
            if(jCboServicio.getSelectedItem().toString().equals("TODOS")){
                DialogoAceptar.mostrarDialogo("Especifique un Servicio","Presione ENTER para continuar...",colorDialogoActivo);
                jCboServicio.setSelectedIndex(1);
                jCboServicio.requestFocusInWindow();
                return;
            }

            if(jCboOrigen.getSelectedItem().toString().equals("TODOS")){
                DialogoAceptar.mostrarDialogo("Especifique un Origen","Presione ENTER para continuar...",colorDialogoActivo);
                jCboOrigen.setSelectedIndex(1);
                jCboOrigen.requestFocusInWindow();
                return;
            }

            if(jCboDestino.getSelectedItem().toString().equals("TODOS")){
                DialogoAceptar.mostrarDialogo("Especifique un Destino","Presione ENTER para continuar...",colorDialogoActivo);
                jCboDestino.setSelectedIndex(1);
                jCboDestino.requestFocusInWindow();
                return;
            }
            //compoTarifa = sesionVenta.getServicioNombre(jCboServicio.getSelectedItem().toString())+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";            
            //VAGL&&RAI 09/02/2009
            compoTarifa = sesionVenta.getServicioNombre(jCboServicio.getSelectedItem().toString())+jCboOrigen.getSelectedItem().toString()+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString())+"SEN";
            System.out.println("busca rutaIdVA: "+jCboServicio.getSelectedItem().toString()+","+jCboOrigen.getSelectedItem().toString()+","+(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString()));
            int rutaIdVA = sesionVenta.getRutaIdVA(jCboServicio.getSelectedItem().toString(),jCboOrigen.getSelectedItem().toString(),(jCboDestino.getSelectedItem().toString().equals("TODOS") ? listaCorridas[fila][5].toString() : jCboDestino.getSelectedItem().toString()) );
            sesionVenta.setTiposPasajeLealtad(rutaIdVA,jCboOrigen.getSelectedItem().toString());
            System.out.println("rutaIdVA: "+ rutaIdVA);
            System.out.println("compoTarifa(F6): "+compoTarifa);
            TarifaServicio=sesionVenta.getTarifaTramo1(compoTarifa,0);
            if(TarifaServicio==0){
                jLblBarraEstado.setText(mensajes.getMensajeVta(15));
                jCboOrigen.requestFocusInWindow();
                return;
            }
            sesionVenta.getUserCon().setServicioTarifaSencillo(TarifaServicio);
            jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getUserCon().getServicioTarifaSencillo())+"</font></html>");

            if(jCboCiudadVenta.getSelectedItem().equals(sesionVenta.getUserCon().getTerminalNombre())) sesionVenta.setTipoTransaccion("L");
            else sesionVenta.setTipoTransaccion("R");
            criterio = new CriteriosBusqueda(jlbl_reloj.getFecha(), jCboOrigen.getSelectedItem().toString(),
                                            sesionVenta.getVectorServiciosReal(jCboServicio.getSelectedItem().toString()),
                                            jCboDestino.getSelectedItem().toString(),
                                            jTxtFecha.getText(),
                                            jTxtHora.getText(),
                                            jCboEmpresas.getSelectedItem().toString(),
                                            sesionVenta);

            boolean zz = sesionVenta.busqCorridaVentaBA(jCboCiudadVenta.getSelectedItem().toString(), criterio.getOrigen(),
                    criterio.getDestino(), criterio.getHoy(), criterio.getFecha(),
                    criterio.getServicio(), criterio.getEmpresa());
            if(!zz){
                this.transaccion=this.txVENTA;
                DialogoAceptar.mostrarDialogo("¡Aviso!","Este servicio no dispone de boleto abierto.",Color.RED);
                jLblEmpresa.setVisible(false); jCboEmpresas.setVisible(false);
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                this.desHabilitarCriterios(1);
                this.jCboServicio.requestFocusInWindow();
                return;
            }
            //
            sesionVenta.strDestinoX=criterio.getDestino();
            // MOD: AGREGUE DOS LINEAS PARA SEGUIR TRABAJANDO CON LA EMPRESA ACTUAL
            this.nombreEmpresa=jCboEmpresas.getSelectedItem().toString();
            if(sesionVenta.getUserCon().getAplicacionVenta())
                setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
            //
            Object[] listadoC= new Object[4];
            listadoC[0]=jCboOrigen.getSelectedItem();
            listadoC[1]=jCboDestino.getSelectedItem();
            // MOD: PARA TRABAJAR CON EMPRESA CORRECTA listadoC[2]=sesionVenta.getUserCon().getEmpresaPrincipal();
            listadoC[2]=this.nombreEmpresa;
            listadoC[3]=sesionVenta.getVectorServiciosReal(jCboServicio.getSelectedItem().toString());
            
            // CONSULTAR OCUPACION
            
            //
            if(listaCorridas == null)
                try {
                    consultaListadoCorridas();
                } catch (ConsultaCorridaException ex) {
                    ex.printStackTrace();
                }
            System.out.println("sesionVenta = "+sesionVenta);
            System.out.println("listadoC = "+listadoC);
            System.out.println("this.listaCorridas[fila][8] = "+this.listaCorridas[fila][8]);
            System.out.println("this.listaCorridas[fila][8].toString() = "+this.listaCorridas[fila][8].toString());
            System.out.println("Long = "+Long.valueOf(this.listaCorridas[fila][8].toString()));
            jDlgVentaBA dlgBA = new jDlgVentaBA(sesionVenta, listadoC,Long.valueOf(this.listaCorridas[fila][8].toString()));
            if(!dlgBA.getContinuar()){
                Boletos = null;
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("No es posible vender boleto abierto.","Configuracion incorrecta para tipos de pasajeros.", Color.RED);
                jLblBarraEstado.setText(mensajes.getMensajeVta(15));
                jCboServicio.setSelectedIndex(1);
                jCboServicio.requestFocusInWindow();
                return;
            }
            dlgBA.setVisible(true);
            switch(dlgBA.getResultado()){
                case 0:
                    Boletos=dlgBA.getBoletos(); 
                    //AGREGUE LAS SIGUIENTES LINEAS PARA VALIDAR LA DISPOSICION DE BOLETOS
                  if(sesionVenta.getUserCon().getAplicacionVenta() && !sesionVenta.isVtaBolBco(this.nombreEmpresa))
                  {
                    long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
                    long f3=(f2-f1)+1;
                    if(Boletos.length>f3){
                        Boletos = null;
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("Limite de folios.",
                                "<html>No tiene boletos suficientes para vender boleto abierto.<br>" +
                                f3+" folio(s) disponible(s).</html>", Color.RED);
                        jLblBarraEstado.setText(mensajes.getMensajeVta(15));
                        jCboServicio.setSelectedIndex(1);
                        jCboServicio.requestFocusInWindow();
                        return;
                    }// FIN MODIFICACION
                  }                    
                    xVtaActual.setDataVector(Boletos,encVtaActual);
                    AnchoColumnasVtaActual();
                    jLblTotalVtaActual.setText("Total: $"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()));
                    jLblBarraEstado.setText(mensajes.getMensajeVta(17));
                    jTblVtaActual.setRowSelectionInterval(0,0);
                    jTblVtaActual.setColumnSelectionInterval(3,3);
                    jTblVtaActual.requestFocusInWindow();
                    enabledComponentsCriterios();
                    break;
                case 1:
                    jLblBarraEstado.setText(mensajes.getMensajeVta(15));
                    jCboServicio.setSelectedIndex(1);
                    jCboServicio.requestFocusInWindow();
                    break;
                case 2:
                    jLblEmpresa.setVisible(false); jCboEmpresas.setVisible(false);
                    xVtaActual.setDataVector(null,encVtaActual);
                    AnchoColumnasVtaActual();
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    this.transaccion=this.txVENTA;
                    this.desHabilitarCriterios(1);
                    break;
            }
        }else{
            if(this.transaccion.equals(txVENTA)){
                this.jLblTx.setText("*VENTA BOL ABIERTO*");
                this.transaccion=this.txVENTA_BA;
                jLblEmpresa.setVisible(true); jCboEmpresas.setVisible(true);
                this.jCboEmpresas.setSelectedIndex(0);
                this.jCboDestino.setSelectedIndex(1);
                this.jCboServicio.setSelectedIndex(1);
                this.desHabilitarCriterios(3);
                jLblBarraEstado.setText(mensajes.getMensajeVta(15));
            }
        }
    }
    
    private void CanjeBoletoAbierto(){ 
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txVENTA_BA)) return;
        if(jTblVtaActual.getRowCount()>0) return;
        if(this.transaccion.equals(txVENTA)){
            if(!sesionVenta.ValidaFuncionUsuario("5008")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5008", "Canjear Boleto Abierto");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return;
            }
            this.jLblTx.setText("*CANJE BOL ABIERTO*");
            Date cDate = null;
            if(jlbl_reloj.getFecha()==null) cDate = new Date();
            else cDate = new Date(jlbl_reloj.getFecha().getTime());
            montoPromocionVA = 0;
            tipoPasajeroVA = '_';
            
            jIFCanjeBA = new JDlgCanjeBAMultiBoleto(sesionVenta, sesionVenta.getOrigenesDBLink(), jCboCiudadVenta.getSelectedObjects(), objEmp, cDate.getTime());
            jIFCanjeBA.setFocusTraversalKeysEnabled(false);
            jIFCanjeBA.setVisible(true);
            if(!jIFCanjeBA.chOk){
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                jCboServicio.requestFocusInWindow();
                return;
            }
            // COMENTARIO: this.nombreEmpresa=jIFCanjeBA.EmpresaCBA;
            long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)), f2=sesionVenta.obtenerFolioFinal(this.nombreEmpresa);
            long f3=(f2-f1)+1;
            if(1>f3){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("Limite de folios.", "Boletos agotados para realizar cambio de horario.", Color.RED);
                filtroInicialX();
                if(sesionVenta.getUserCon().getAplicacionVenta())
                    setLabelFolioX("-",this.nombreEmpresa);
                jCboServicio.requestFocusInWindow();
                return;
            }
            if(sesionVenta.getUserCon().getAplicacionVenta())
                setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
            //
            jCboCiudadVenta.setSelectedItem(jIFCanjeBA.CiudadCorridaCBA);
            //
            sesionVenta.cargarOrigenesDestinosServicios(jCboCiudadVenta.getSelectedItem().toString(), false);
            cbmServicio = new DefaultComboBoxModel(sesionVenta.getServicios());
            cbmOrigen = new DefaultComboBoxModel(sesionVenta.getOrigenes());
            cbmDestino = new DefaultComboBoxModel(sesionVenta.getDestinos());
            jCboOrigen.setModel(cbmOrigen);
            jCboDestino.setModel(cbmDestino);
            jCboServicio.setModel(cbmServicio);
            //
            jCboOrigen.setSelectedItem(jIFCanjeBA.OrigenCBA);
            jCboDestino.setSelectedItem(jIFCanjeBA.DestinoCBA);
            jCboServicio.setSelectedItem(jIFCanjeBA.ServicioCBA);
            //VAGL&&RAI 09/02/2009
            DialogoAceptar.mostrarDialogo("¡El boleto abierto es valido!", "Proceda a canjearlo...", colorDialogoActivo);
            tarifaAdultoCHBA=sesionVenta.getTarifaTramo1(sesionVenta.getTmsBoletosVentaTbl().getServicio()+
                            sesionVenta.getTmsBoletosVentaTbl().getOrigen()+
                            sesionVenta.getTmsBoletosVentaTbl().getDestino()+
                            "SEN",0);
            jLblBarraEstado.setText(mensajes.getMensajeVta(19));
            montoPromocionVA = jIFCanjeBA.importeBolOriginalAdulto;
            System.out.println("tipoPasajeroVA en CambiarHorarioBoleto: "+jIFCanjeBA.tipoPasajero);
            char[] tpc = jIFCanjeBA.tipoPasajero.toCharArray();
            tipoPasajeroVA = tpc[0];
            System.out.println("tipoPasajeroVA = "+tipoPasajeroVA);
            encBO=jIFCanjeBA.boletos;
            this.transaccionAux=this.txCANJE_BA;
            if(jIFCanjeBA.isReferenciado())
                this.transaccionAux=this.txCANJE_BA_RF;
            this.transaccion=this.txCANJE_BA;
            desHabilitarCriterios(2);
            jCboServicio.setSelectedItem(sesionVenta.getServicioLetra(jIFCanjeBA.ServicioCBA));
        }
    }
    
    private void CanjeBoletoReferenciado(){
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txVENTA_BA)) return;
        if(jTblVtaActual.getRowCount()>0) return;
                salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
                if(salidaImpresionTVRef==null){
                    System.out.println("No encontro impresora de voucher");
                    return;
                }
        //sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(this.nombreEmpresa));
        //sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(this.nombreEmpresa));

        if(this.transaccion.equals(txVENTA)){
//            if(!sesionVenta.ValidaFuncionUsuario("5008")){
//                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5008", "Canjear Boleto Abierto");
//                dlg.setVisible(true);
//                if(!dlg.getRespuesta()) return;
//            }
            this.jLblTx.setText("*CANJE BOL REFERENCIADO*");
            Date cDate = null;
            if(jlbl_reloj.getFecha()==null) cDate = new Date();
            else cDate = new Date(jlbl_reloj.getFecha().getTime());
            jIFCanjeBF = new JDlgCanjeBolRef(sesionVenta, sesionVenta.getOrigenesDBLink(), jCboCiudadVenta.getSelectedObjects(), objEmp, cDate.getTime());
            jIFCanjeBF.setFocusTraversalKeysEnabled(false);
            jIFCanjeBF.setVisible(true);
            //System.out.println("chOk = "+jIFCanjeBF.chOk);
            if(!jIFCanjeBF.chOk){
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                this.jLblTx.setText("*VENTA*");
                jCboServicio.requestFocusInWindow();
                return;
            }
            // COMENTARIO: this.nombreEmpresa=jIFCanjeBF.EmpresaCBA;
            System.out.println("VariosTmsBoletosVentaTbl = "+ sesionVenta.getVariosTmsBoletosVentaTbl().size() +" Empresa = "+sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getEmpresa());
            this.nombreEmpresa=sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getEmpresa();
                salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
                if(salidaImpresionTVRef==null){
                    System.out.println("No encontro impresora de voucher");
                    return;
                }
            sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(this.nombreEmpresa));
            sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(this.nombreEmpresa));
            sesionVenta.setReferenciadoId(sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getBolReferenciadoId());
            long f1=Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa));
            long f2=f1+sesionVenta.getVariosTmsBoletosVentaTbl().size();
            long f3=(f2-f1)+1;
            if(1>f3){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("Limite de folios.", "Boletos agotados para realizar cambio de horario.", Color.RED);
               // filtroInicialX();
                if(sesionVenta.getUserCon().getAplicacionVenta())
                    setLabelFolioX("-",this.nombreEmpresa);
                jCboServicio.requestFocusInWindow();
                return;
            }
            if(sesionVenta.getUserCon().getAplicacionVenta())
                setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
            //encBF=jIFCanjeBF.boletos;
            this.transaccion=this.txCANJE_BA_RF;
            ConstruyeBoletosReferenciados("S");              

                    nombreAutorizado = false;
                    sesionVenta.setNombreAutorizado(jtxt_personaAutorizada.getText());
                    jtxt_personaAutorizada.setText("");
                    if(!sesionVenta.getUserCon().getAplicacionVenta()) return;
                    if(!ValidaSesionVenta()) return;
                    this.BoletosRedondos = null;
//                    if(!sesionVenta.ValidaFuncionUsuario("5004")){
//                        jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5004", "Vender Boleto");
//                        dlg.setVisible(true);
//                        if(!dlg.getRespuesta()) return;
//                    }
                   // EjecutaCobro();
                    int res1=sesionVenta._RegistroVentaSP(Boletos);   
                    boolean vtaMoto = false;
                    if(res1 == 0)
                    {
                       Vector ids = new Vector();
                       for(int bf = 0; bf<sesionVenta.getVariosTmsBoletosVentaTbl().size(); bf++)
                       {
                            Vector id = new Vector();
                            TmsBoletosVentaTbl bolRef = sesionVenta.getVariosTmsBoletosVentaTbl().get(bf);
                            if(bolRef.getAdicional10().equals("CALL_CENTER"))
                                vtaMoto = true;
                            id.add(bolRef.getBolReferenciadoId());
                            id.add(bolRef.getDocumento());
                            ids.add(id);
                       }
                       if(!sesionVenta.liberaReferenciado(ids))
                               DialogoAceptar.mostrarDialogo("¡Error!","Los boletos referenciados no pudieron ser actualizados.", Color.RED);
                       System.out.println("Se imprimiran: "+Boletos.length+", forma de pago: Boletos Referenciados");
                        String tr = this.transaccion;
                        if(sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getTipoOperacion().equals("VA"))
                            tr = "VA";
                       ImprimeBoletos x = new ImprimeBoletos();
                       System.out.println("Folio Actual: "+Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)));
                            if(!x.ImprimeDatos("BRF",sesionVenta.getUserCon().getPrefijo(),Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)),
                                Boletos,tr, sesionVenta.getUserCon().getDiasValBab(),sesionVenta.getUserCon().getUsuarioNom(),
                                sesionVenta, jlbl_reloj.getFecha(),sesionVenta.getTmsVtaFacade().getPromocionVigente(),"")){
                                //this.setVentaOk(0);
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("¡Canje Realizado!","Boleto no impreso por falla de impresion.", Color.RED);
                                this.dispose();
                                //DialogoAceptar.mostrarDialogo("¡Venta registrada!","Boleto no impreso por falla de impresion.", Color.RED);
                                return;
                            }
                       String tpoCnkg = "Boleto";
                       if(sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getTipoOperacion().equals("VA"))
                           tpoCnkg = "Boleto Abierto";
                       imprimir_ticket_canjeBA_ref ticket = null;
                       if(!vtaMoto)
                       {
                           //if(sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2().equals(""))
                           System.out.println("Si Imprime comprobante por que es venta Taquilla y no es MOTO (EFE/BBV)");
                           ticket = new imprimir_ticket_canjeBA_ref(sesionVenta.getNombreAutorizado(), sesionVenta.getReferenciaCanjeBA(), "", sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom(), Boletos, ids, tpoCnkg,this.sesionVenta);
                           ticket.ImprimeDatos(salidaImpresionTVRef,sesionVenta.isVoucherBlanco(),sesionVenta.getImpresoraNombreVoucherBlanco());
                       }
                        else
                            System.out.println("No Imprime comprobante por que es venta MOTO y se fusiona con el Voucher..  ");

                       //VAGL 06092011 Reimpresion de Voucher cuando un boleto referencia se pago con TDC
                        if(!sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2().equals(""))
                        {
                            
                            JClsPinPadTBRequest jClsPinPadTBRequest = new JClsPinPadTBRequest();
                            JClsPinPadTBResponse jClsPinPadTBResponse = jClsPinPadTBRequest.getReimpresionVoucher(sesionVenta.getUserCon().getDbgSetUrl(),String.valueOf(sesionVenta.getImporteVenta()),sesionVenta.getUserCon().getBs_User(),
                                                                                                        sesionVenta.getUserCon().getBs_Pwd(),sesionVenta.getUserCon().getUsuarioNum(),sesionVenta.getUserCon().getBs_Company(),
                                                                                                        sesionVenta.getUserCon().getBs_Branch(),sesionVenta.getUserCon().getBs_Country(),sesionVenta.getVariosTmsBoletosVentaTbl().get(0).getAdicional2());
                            String msg = "<html>";
                            System.out.println("Respuesta de la Reimpresion: "+jClsPinPadTBResponse.getRspDsResponse());
                            System.out.println("Reimrpesion de Vouvher: "+jClsPinPadTBResponse.getRspVoucher());
                            System.out.println("Reimrpesion de Vouvher: "+jClsPinPadTBResponse.getRspQryRePrint());
                            System.out.println("Se termina Reimrpesion de Vouvher***********************************");
                            if(jClsPinPadTBResponse.getRspDsResponse().equals("approved") || !sesionVenta.isActivoEMVFull())
                            {
                                String vou = "";
                                if(sesionVenta.isActivoEMVFull())
                                {
                                    System.out.println("Entra a isActivoEMVFull()..");
                                    try{
                                        if(sesionVenta.isImpVouComercio())
                                            vou = jClsPinPadTBResponse.getRspVoucherComercio()+"\n@br  \n@br  "+jClsPinPadTBResponse.getRspVoucherCliente();
                                        else
                                            vou = jClsPinPadTBResponse.getRspVoucherCliente()+"\n@br  \n@br  ";
                                    } catch (Exception e)
                                    {e.printStackTrace(); DialogoAceptar.mostrarDialogo("¡Configuracion EVM Full!", "¡La DLL del EMV Full no esta configurada!\n Contacte al Administrador del Sistema", Color.RED);}
                                }
                                else
                                {
                                    System.out.println("No entra a isActivoEMVFull()..");
                                    //vou = jClsPinPadTBResponse.getRspVoucher();
                                    vou = jClsPinPadTBResponse.getRspQryRePrint();
                                    System.out.println("y manda aimprimir: \n"+jClsPinPadTBResponse.getRspVoucher());
                                }
                                JClsImprimeVoucher jClsImprimeVoucher = new JClsImprimeVoucher();
                                //VAGL 14092011 Se manda a llamar dos metodos en lugar de unos solo para imprimir el voucher
                                //jClsImprimeVoucher.ImprimeDatos(jClsPinPadTBResponse.getRspVoucher(), salidaImpresionTVRef, 0,sesionVenta.getReferenciaCanjeBA(),"");
                                System.out.println("Manda a Reimprimir el Voucher de Canje de Boleto Ref con:");
                                System.out.println("ImpresoraNombreVoucherBlanco: "+sesionVenta.getImpresoraNombreVoucherBlanco());
                                System.out.println("sesionVenta.isVoucherBlanco(): "+sesionVenta.isVoucherBlanco());
                                System.out.println("salidaImpresionTVRef: "+salidaImpresionTVRef);
                                jClsImprimeVoucher.ReimprimeDatos(vou, salidaImpresionTVRef, 0,sesionVenta.getReferenciaCanjeBA(),"",(vtaMoto?"MO/TO":""),Boletos, ids, sesionVenta.getImpresoraNombreVoucherBlanco(),sesionVenta.isVoucherBlanco() );
                           }
                          DialogoAceptar.mostrarDialogo("Boletos canjeados.", "<html>Los boletos referenciados han sido canjeados<br> Tome el comprobante del canje <br> y solicite la firma del voucher para el banco</html>", colorDialogoActivo);
                            
                            /*
                            String vou = "@cnn VENTA "
                                    + "@br "
                                    + "@cnn AUTOBUSES MEXICO PUEBLA ESTRELLA ROJA  "
                                    + "@cnn (020) AUTOB MEX PUE EST ROJA 4 PONIENTE "
                                    + "@cnn    "
                                    + "@cnn   "
                                    + "@br "
                                    + "@cnn 7273370 AUTOB MEX PUE EST ROJA "
                                    + "@cnn 0670SAUS1 "
                                    + "@br "
                                    + "@lnn No.Tarjeta: xxxxxxxxxxxx1292 "
                                    + "@lnn Vence:03/18 "
                                    + "@br "
                                    + "@lnn CREDITO "
                                    + "@br "
                                    + "@cnb -C-O-M-E-R-C-I-O- "
                                    + "@br "
                                    + "@lnn APROBADA "
                                    + "@lnn IMPORTE"
                                    + "@cnb $ 1,560.00 MXN   "
                                    + "@br "
                                    + "@lnn Oper.:     189980873 "
                                    + "@lnn Ref.:      94120206141119@lnn 07"
                                    + "@lnn ARQC:      BCC61E0B54D4BB16"
                                    + "@lnn AID:       07A0000000041010 "
                                    + "@lnn Aut.:      002621 "
                                    + "@lnn   "
                                    + "@br                                     "
                                    + "@lnn Fecha: 02/06/2014 11:19:29 "
                                    + "@br "
                                    + "@br "
                                    + "@br "
                                    + "@cnn ______________________________ "
                                    + "@cnn STONE AGUILAR/RICARDO      "
                                    + "@br "
                                    + "@br "
                                    + "@br "
                                    + "@br "
                                    + "@cnn CP-D 7.1.1                "
                                    + "@br "
                                    + "@lsn POR ESTE PAGARE ME OBLIGO INCONDI "
                                    + "@lsn CIONALMENTE A PAGAR A LA ORDEN DEL "
                                    + "@lsn BANCO EMISOR EL IMPORTE DE ESTE  "
                                    + "@lsn TITULO EN LOS TERMINOS DEL CONTRA "
                                    + "@lsn TO SUSCRITO PARA EL USO DE ESTA  "
                                    + "@lsn TARJETA DE CREDITO EN EL CASO DE  "
                                    + "@lsn OPERACIONES CON TARJETA DE DEBITO, "
                                    + "@lsn EXPRESAMENTE RECONOZCO Y ACEPTO  "
                                    + "@lsn ESTE RECIBO ES EL COMPROBANTE DE  "
                                    + "@lsn LA OPERACION REALIZADA, MISMA QUE "
                                    + "@lsn SE CONSIGNA  EN EL ANVERSO Y TEN "
                                    + "@lsn DRA PLENO VALOR PROBATORIO Y FUER "
                                    + "@lsn ZA LEGAL, EN VIRTUD DE QUE LO FIR "
                                    + "@lsn ME PERSONALMENTE Y/O DIGITE MI NU "
                                    + "@lsn MERO DE IDENTIFICACION PERSONAL  "
                                    + "@lsn COMO FIRMA ELECTRONICA EL CUAL ES  "
                                    + "@lsn EXCLUSIVO DE MI RESPONSABILIDAD "
                                    + "@lsn MANIFESTANDO PLENA CONFORMIDAD  "
                                    + "@lsn AL RESPECTO. "
                                    + "</voucher_comercio><voucher_cliente>@cnb Santander "
                                    + "@cnn VENTA "
                                    + "@br "
                                    + "@cnn AUTOBUSES MEXICO PUEBLA ESTRELLA ROJA "
                                    + "@cnn (020) AUTOB MEX PUE EST ROJA 4 PONIENTE "
                                    + "@cnn    "
                                    + "@cnn   "
                                    + "@br "
                                    + "@cnn 7273370 AUTOB MEX PUE EST ROJA "
                                    + " "
                                    + "@br "
                                    + "@lnn No.Tarjeta: xxxxxxxxxxxx1292 "
                                    + "@br "
                                    + "@lnn CREDITO "
                                    + "@br "
                                    + "@cnb -C-L-I-E-N-T-E- "
                                    + "@br "
                                    + "@lnn APROBADA "
                                    + "@lnn IMPORTE"
                                    + "@cnb $ 1,560.00 MXN   "
                                    + "@br "
                                    + "@lnn Oper.:     189980873 "
                                    + "@lnn Ref.:      94120206141119@lnn 07"
                                    + "@lnn ARQC:      BCC61E0B54D4BB16"
                                    + "@lnn AID:       07A0000000041010 "
                                    + "@lnn Aut.:      002621  "
                                    + "@lnn   "
                                    + "@br "
                                    + "@lnn Fecha: 02/06/2014 11:19:29 "
                                    + " @br "
                                    + "@br "
                                    + "@br "
                                    + "@br "
                                    + "@cnn CP-D 7.1.1                ";
                                JClsImprimeVoucher jClsImprimeVoucher = new JClsImprimeVoucher();
                                System.out.println("vtaMoto: "+vtaMoto);
                                jClsImprimeVoucher.ReimprimeDatos(vou, salidaImpresionTVRef, 0,sesionVenta.getReferenciaCanjeBA(),"",(vtaMoto?"MO/TO":""),Boletos, ids);
                             *
                             */

                        }
                        else
                          DialogoAceptar.mostrarDialogo("Boletos canjeados.", "<html>Los boletos referenciados han sido canjeados<br> Tome el comprobante del canje</html>", colorDialogoActivo);


                       DialogoAceptar.mostrarDialogo("¡Canje exitoso!","Tome el boleto de la impresora", colorDialogoActivo);

                       this.jLblTx.setText("*VENTA*");
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        
                        abledComponentsCriterios();
                        abledComponents(jTblCorridas);
                        desHabilitarCriterios(1);
                        jTxtFecha.selectAll();
                        jTxtFecha.requestFocusInWindow();
                        this.transaccion = txVENTA;
                        filtroInicialX();
                        jCboServicio.requestFocusInWindow();
                    }
                    else
                    {
                        if(res1==-1)
                           DialogoAceptar.mostrarDialogo("Canje de boleto.","No es posible realizar el canje del boleto.",Color.RED);
                        if(res1==-2)
                           DialogoAceptar.mostrarDialogo("Error de canje de boleto.","La corrida ha sido despachada.",Color.RED);
                        this.transaccion = txVENTA;
                        this.transaccionAux = txVENTA;
                        filtroInicialX();
                    }
                    
        }
    }    
    /*************************************************************************/        
    private void desHabilitarCriterios(int iCaso){
        switch(iCaso){
            case 0:
                //this.jCboEmpresas.setEnabled(false);
                this.jCboCiudadVenta.setEnabled(false);
                this.jCboOrigen.setEnabled(false);
                this.jCboDestino.setEnabled(false);
                this.jTxtFecha.setEnabled(false);
                this.jTxtHora.setEnabled(false);
                //this.jCboEmpresas.setFocusable(false);
                this.jCboCiudadVenta.setFocusable(false);
                this.jCboOrigen.setFocusable(false);
                this.jCboDestino.setFocusable(false);
                this.jTxtFecha.setFocusable(false);
                this.jTxtHora.setFocusable(false);
                this.jCboServicio.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
                this.jCboServicio.requestFocusInWindow();
                break;
            case 1:
                //this.jCboEmpresas.setFocusable(true);
                this.jCboCiudadVenta.setFocusable(true);
                this.jCboOrigen.setFocusable(true);
                this.jCboDestino.setFocusable(true);
                this.jCboServicio.setFocusable(true);
                this.jTxtFecha.setFocusable(true);
                this.jTxtHora.setFocusable(true);
                //this.jCboEmpresas.setEnabled(true);
                this.jCboCiudadVenta.setEnabled(true);
                this.jCboOrigen.setEnabled(true);
                this.jCboDestino.setEnabled(true);
                this.jCboServicio.setEnabled(true);
                this.jTxtFecha.setEnabled(true);
                this.jTxtHora.setEnabled(true);
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                sesionVenta.setCambHorRer(false);
                sesionVenta.setReferenciaOriginal("");
                sesionVenta.setReferenciadoId("");
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                this.jCboServicio.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
                this.jCboServicio.requestFocusInWindow();
                break;
            case 2:
                //this.jCboEmpresas.setFocusable(false);
                this.jCboCiudadVenta.setFocusable(false);
                this.jCboOrigen.setFocusable(false);
                this.jCboDestino.setFocusable(true);
                this.jCboServicio.setFocusable(true);
                //this.jCboEmpresas.setEnabled(false);
                this.jCboCiudadVenta.setEnabled(false);
                this.jCboOrigen.setEnabled(false);
                this.jCboDestino.setEnabled(true);
                this.jCboServicio.setEnabled(true);
                this.jTxtFecha.selectAll();
                this.jTxtFecha.requestFocusInWindow();
                break;
            case 3:
                //this.jCboEmpresas.setEnabled(true);
                this.jCboCiudadVenta.setEnabled(true);
                this.jCboOrigen.setEnabled(true);
                this.jCboDestino.setEnabled(true);
                this.jTxtFecha.setEnabled(false);
                this.jTxtHora.setEnabled(false);
                //this.jCboEmpresas.setFocusable(true);
                this.jCboCiudadVenta.setFocusable(true);
                this.jCboOrigen.setFocusable(true);
                this.jCboDestino.setFocusable(true);
                this.jTxtFecha.setFocusable(false);
                this.jTxtHora.setFocusable(false);
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                this.jCboServicio.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
                this.jCboServicio.requestFocusInWindow();
                break;
        }
    }
    
    private void CerrarVentana(){    
        if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Desea cerrar la Aplicación TMS Venta?",false);
        boolean r = DialogoSiNo.getResultado();
        if(!r){
            jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            jCboServicio.requestFocusInWindow();
            return;
        }
        if(jTblVtaActual.getRowCount()>0){
            int rSP=-1;
            sesionVenta.iTempos=1;
            while(rSP==-1){
                rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                if(rSP==-1){
                    sesionVenta.iTempos++;
                    if(sesionVenta.iTempos>sesionVenta.tTempos){
                        System.out.println("Asiento bloqueado");
                        break;
                    }
                }
            }
        }
        
        if(jThdConsultaCorrida != null){
            jThdConsultaCorrida.moreQuotes = false;
            jThdConsultaCorrida = null;
            sesionVenta.b_decode_data=false;
        }
        
        if(jlbl_reloj!=null){
            jlbl_reloj.finaliza();
            jlbl_reloj=null;
        }
                
        if(threadPlantilla!=null){
            threadPlantilla.noSalir=false;
            threadPlantilla = null;
        }
        finalizarSesionVenta();
    }
    
    private void BoletoSinNumerodeAsiento(){
        this.ubiCompo=ucListCorridas;
        if(!ConsultaOcupacionX(true, false, false, false)) return;
        
        //ES CAMBIO DE HORARIO O CANJE DE BOLETO ABIERTO
        if(this.transaccion.equals(txCHORARIO) || this.transaccion.equals(txCANJE_BA)){
            //AQUI ASIGNO ASIENTO EN AUTOMATICO
                int r=eligeAsientoInsertaTipo();
            if(r==0){
                if(this.transaccion.equals(txCANJE_BA)) {
                    for(int i=0; i<sesionVenta.getVariosTmsBoletosVentaTbl().size(); i++)
                            sesionVenta.actualizaTipoAsiento(this.corridaId, sesionVenta.getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero(),Integer.valueOf(BoletoAVender.get(i).toString()));
                    ConstruyeBoletoCHCBA();
                    jLblBarraEstado.setText(mensajes.getMensajeVta(21));
                    return;
                }
                else{
                    ConstruyeBoletoCHCBA();
                    if(!CambioHorario()) return;
                }
            }
            else if(r==-1) return;
            
            xCorridas.setDataVector(null, encCorridas);
            AnchoColumnasCorridas();
            jScpVtaActual.setVisible(true);
            jLblTotalVtaActual.setVisible(true);
            jPnlCHorario.setVisible(false);
            this.tipoTransaccion=txVENTA;
            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            this.desHabilitarCriterios(1);
            filtroInicialX();
            return;
        }
        jDlgCtdPasTipoAsiento bolSinAsiento = new jDlgCtdPasTipoAsiento(sesionVenta, this.nombreEmpresa,
                                                                                this.corridaId, this.capacidadAutobus,
                                                                                jCboOrigen.getSelectedItem().toString(),
                                                                                jCboDestino.getSelectedItem().toString(),
                                                                                jlbl_reloj.getFecha().getTime(),
                                                                                listaCorridas[fila], jThdConsultaCorrida,
                                                                                Long.valueOf(this.listaCorridas[fila][8].toString()));
        bolSinAsiento.setVisible(true);
        switch(bolSinAsiento.getResultado()){
            case 0: BoletoAVender = bolSinAsiento.getBoletoAVender();
                    TiposAsientos = bolSinAsiento.getTiposAsientos();
                    Boletos = bolSinAsiento.getBoletos();
                    xVtaActual.setDataVector(Boletos,encVtaActual);
                    AnchoColumnasVtaActual();
                    jLblTotalVtaActual.setText("Total: $"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()));
                    /* //Se deshabilita por que solo forzaba a la venta cuando era 2X1 y era para orientar a los Taquilleros 14/11/2013
                    if(bolSinAsiento.getSesionVenta().isRutaConPromocion() && bolSinAsiento.getParejasNoAdultos()>0)
                    {
                        if(bolSinAsiento.getNumNoAdultosSobrantes()>0)
                            DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!",bolSinAsiento.getMensajePromo1(),Color.RED);
                        else
                            DialogoAceptar.mostrarDialogo("¡Promocióon Vigente!",bolSinAsiento.getMensajePromo2(),Color.RED);
                    }
                     */
                    if(sesionVenta.getUserCon().getAplicacionVenta())jLblBarraEstado.setText(mensajes.getMensajeVta(5));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(5));
                    jTblVtaActual.setColumnSelectionInterval(3,3);
                    jTblVtaActual.setRowSelectionInterval(0,0);
                    this.ubiCompo=ucVtaActual;
                    //
                    int i, noAsiento;
                    for(i=0; i<BoletoAVender.size(); i++){
                        noAsiento=Integer.valueOf(BoletoAVender.get(i).toString());
                    }
                    if(!ConsultaOcupacionX(true, false, false, false)) return;
                    //
                    jTblVtaActual.requestFocusInWindow();
                    enabledComponents(jTblCorridas);
                return;
            case 1: 
                    try{
                        jTblCorridas.setRowSelectionInterval(fila,fila);
                    }catch(Exception ex){
                        //ex.printStackTrace();
                        jTblCorridas.setRowSelectionInterval(0,0);
                    }
                    jLblBarraEstado.setText(mensajes.getMensajeVta(2));
                    jTblCorridas.setColumnSelectionInterval(0,0);
                    abledComponents(jTblCorridas);
                    jTblCorridas.requestFocusInWindow();
                return;
            case 2:
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    xVtaActual.setDataVector(null, encVtaActual);
                    AnchoColumnasVtaActual();
                    jTxtTipoPasaje.setText("");
                    jTxtAsientos.setText("");
                    xCorridas.setDataVector(null, encCorridas);
                    AnchoColumnasCorridas();
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    //PlantillaResumenDefault();  
                    jCboServicio.requestFocusInWindow();
                return;
        }
    }
    
    private boolean VerificaRecoleccion(){
        if(sesionVenta.EFECTIVO_CAJA>=sesionVenta.getUserCon().getAvisoRecoleccion() &&
           sesionVenta.EFECTIVO_CAJA<sesionVenta.getUserCon().getLimVenta()){
            DialogoAceptar.mostrarDialogo("¡Aviso de Recoleccion!", "Solicite al supervisor una recoleccion.", Color.RED);
            return true;
        } else{
            if(sesionVenta.EFECTIVO_CAJA>=sesionVenta.getUserCon().getLimVenta()){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("¡Venta limite!", "<html>A partir de este momento no puede vender mas...<br>Solicite al supervisor una recoleccion.</html>", Color.RED);
                return false;
            }
        }
        return true;
    }
    
    private boolean refoliado(){
        if(transaccion.equals(txVENTA_BA)) return false;
        this.jLblTx.setText("*REFOLIADO*");
        if(!sesionVenta.ValidaFuncionUsuario("5003")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5003", "Refoliar");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return false;
        }
        Vector empRefol;
        if(sesionVenta.getUserCon().getFolioUnico()){
            empRefol=new Vector();
            empRefol.add(sesionVenta.getUserCon().getEmpresaPrincipal());
        }
        else empRefol = queEmpresasRefolian();
        if(empRefol==null) return false;
        //System.out.println("EMPRESAS PARA REFOLIO "+empRefol.get(0).toString());
        jDlgRefoliar dlgDatosIni = new jDlgRefoliar(false,false,sesionVenta.getUserCon().getFondoMax(), sesionVenta, empRefol, false);
        //dlgDatosIni.setDirecto(true);
        dlgDatosIni.centrarDialogo();
        dlgDatosIni.inicializarEmpresasNombre(sesionVenta.getUserCon().getFolioUnico());
        sesionVenta.cargarFoliosSesion();
        if(sesionVenta.SISTEMA_SALIDA_INMEDIATA()){
            this.dispose();
            return false;
        }
        /*for(int i=0; i<sesionVenta.getFoliosSesion().length; i++){
            System.out.println("refoliado folios "+sesionVenta.getFoliosSesion()[i][0]+" "+sesionVenta.getFoliosSesion()[i][1]+" "+sesionVenta.getFoliosSesion()[i][2]);
        }*/
        dlgDatosIni.mostrarFoliosSesion();
        dlgDatosIni.setVisible(true);
        if(dlgDatosIni.getAccesoVenta()==0){
            sesionVenta.cargarFoliosSesion(); // MODIFICADO PARA REFOLIAJE
            if(sesionVenta.SISTEMA_SALIDA_INMEDIATA()){
                this.dispose();
                return false;
            }
            sesionVenta.getUserCon().setFolioPreimpreso(sesionVenta.getFoliosSesion());
            sesionVenta.FoliosActuales();
            if(!sesionVenta.AuditarFuncion()){
                DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Refoliar!", "La aplicacion será cerrada.\nContacte al administrador del sistema.",Color.RED);
                //System.exit(0);
                this.dispose();
                return false;
            }
            DialogoAceptar.mostrarDialogo("¡Refoliado correcto!", "Puede continuar la venta...",Color.RED);
            return true;
        }
        return false;
    }
    
    private class colorCorridasPorEmpresaRenderer extends DefaultTableCellRenderer{
        String p_valor=null;
        private Font fuenteItalica = new Font("Tahoma", 3, 15);
        private Font fuenteNormal = new Font("Tahoma", 1, 15);
        private Color bg=Color.BLACK;
        private Color fg=Color.WHITE;
        private Color bgXOR=fg;
        private Color fgXOR=bg;
        private Color bg1=Color.BLACK;
        private Color fg1=Color.WHITE;
        private Color bg2=Color.BLACK;
        private Color fg2=Color.WHITE;
        
        private int ic;
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
            boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            for(ic=0; ic<coloresEmpresasOfertantes.length; ic++)
                if(coloresEmpresasOfertantes[ic][0].toString().equals(listaCorridas[row][7].toString())){
                    bg = (Color) coloresEmpresasOfertantes[ic][1];
                    fg = (Color) coloresEmpresasOfertantes[ic][2];
                    break;
                }
            
            //jLblNombreEmpresa.setText("<html>Empresa: <font color="+ColoresInterfaz.cHTML1+">"+(listaCorridas[row][7].toString().length()>16?listaCorridas[row][7].toString().substring(0,15):listaCorridas[row][7].toString())+"</font></html>");
            
            if (selected == true){
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            else{
                setBackground(bg);
                setForeground(fg);
            }
            
            return this;
        }
    }
    
    public boolean getInicioGral(){ return this.inicioGral; }

    private void inhabilitarF10() {
        this.jCboOrigen.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboDestino.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFecha.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHora.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboServicio.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblCorridas.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtAsientos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtTipoPasaje.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblVtaActual.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    
   }
    
    private void inhabilitarMouse() {
        this.jCboCiudadVenta.setRequestFocusEnabled(false);
        this.jCboEmpresas.setRequestFocusEnabled(false);
        this.jCboOrigen.setRequestFocusEnabled(false);
        this.jCboDestino.setRequestFocusEnabled(false);
        this.jTxtFecha.setRequestFocusEnabled(false);
        this.jTxtHora.setRequestFocusEnabled(false);
        this.jCboServicio.setRequestFocusEnabled(false);
        this.jTblCorridas.setRequestFocusEnabled(false);
        this.jTxtAsientos.setRequestFocusEnabled(false);
        this.jTxtTipoPasaje.setRequestFocusEnabled(false);
        this.jTblVtaActual.setRequestFocusEnabled(false);
        this.jTblBO.setRequestFocusEnabled(false);
        this.jTblCH.setRequestFocusEnabled(false);
        this.jTblResumenVtaCorrida.setRequestFocusEnabled(false);
        this.JPnlAutobus.setRequestFocusEnabled(false);
    }

    private String getTiposPasajeVenta() {
        String cadena="";
        Vector cx = null;
        for(int i=0; i<sesionVenta.getCastTiposPasaje().size(); i++){
            cx = (Vector) sesionVenta.getCastTiposPasaje().get(i);
            if(cadena.indexOf(cx.get(1).toString())==-1)
                cadena = cadena + "<font color="+ColoresInterfaz.cHTML1+">"+cx.get(2).toString()+"</font> "+cx.get(1).toString()+" ";
        }
        return cadena;
    }
    
    private void PlantillaResumenDefault(){
        this.JPnlbus.setVND(null);
        this.PlantillaDefault();
        this.ResumenDefault();
    }
    
    private void PlantillaDefault(){
        JPnlbus.getPlantillaDefault();
    }
    
    private void ResumenDefault(){
        try{
        encResumenVtaCorrida = new Object[8];
        encResumenVtaCorrida[0] = "Boleto";
        encResumenVtaCorrida[1] = "A";
        encResumenVtaCorrida[2] = "M";
        encResumenVtaCorrida[3] = "S";
        encResumenVtaCorrida[4] = "E";
        encResumenVtaCorrida[5] = "P";
        encResumenVtaCorrida[6] = "C";
        encResumenVtaCorrida[7] = "Ocup";
        objResumenVtaCorrida = new Object[2][8];
        objResumenVtaCorrida[0][0]="Disp.";  
        objResumenVtaCorrida[1][0]="Vend.";
        int i, j;
        for(i=0; i<2; i++)
            for(j=1; j<8; j++)
                objResumenVtaCorrida[i][j]=null;
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                xResumenVtaCorrida.setDataVector(objResumenVtaCorrida, encResumenVtaCorrida);
                AnchoColumnasResumenVtaCorrida();
            }
        });
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }
    
    private int eligeAsientoInsertaTipo(){
        try {
            if(!consultarCorridaActual(false)) return 1;
        } catch (ConsultaCorridaException ex) {
            return 1;
        }
        int ctlAsientos=5, i, maxA;
        BoletoAVender=new Vector();
        TiposAsientos=new Vector();
        String tipoPasaje;
        String resultado="";
        
        for(i=0; i<vTP.size(); i++)
            TiposAsientos.add(vTP.get(i).toString());
        
        maxA=0;
        System.out.println("Necesito: "+sesionVenta.getVariosTmsBoletosVentaTbl().size());
        for(i = 1; i < this.capacidadAutobus+1; i++){
            if(this.corridaActual[ctlAsientos+i].equals("D")){
                BoletoAVender.addElement(i);
                maxA++;
            }
            if(maxA==sesionVenta.getVariosTmsBoletosVentaTbl().size()) break;
        }
        System.out.println("Obtuve: "+maxA);
        if(maxA!=sesionVenta.getVariosTmsBoletosVentaTbl().size()){
            BoletoAVender = null; TiposAsientos=null;
            DialogoAceptar.mostrarDialogo("TMS Venta.","Asiento(s) ocupado(s).", Color.RED);
            return 1;
        }
        
        sesionVenta.armaCadenaAsientos(BoletoAVender);
        sesionVenta.armaCadenaTiposPasaje(TiposAsientos);
        
        // SI ESTAN DISPONIBLES, UTILIZAMOS SP PARA OCUPARLOS
        System.out.println("Ocupa Auto: "+this.corridaId+" - "+sesionVenta.getCadenaAsientos());
        int rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "I");
        System.out.println("SP_Ocup "+rSP);
        if(rSP!=0){
            this.getToolkit().beep();
            if(rSP!=-1){
                DialogoAceptar.mostrarDialogo("TMS Venta","Se han ocupado asientos que usted eligio.", Color.RED);
                return -1;
            }
            else
                DialogoAceptar.mostrarDialogo("TMS Venta.", "Registro ocupado",Color.RED);
            return -1;
        }
        return 0;
    }
    
    private boolean consultarCorridaActual(boolean esPrimeraVez) throws ConsultaCorridaException{
        try{
            if(esPrimeraVez){
                Date fi=new Date();
                while(new Date().getTime()-fi.getTime() < 150);
            }
            else{
                long timex = new Date().getTime(); // USUARIO PERMANANECE MAS DE 20MS SOBRE LA CORRIDA SELECCIONADA
                if((timex-time_now)<20) return true;
            }
            Object[] v = jThdConsultaCorrida.getCorridaActual(false);
            if(v == null){
                if(sesionVenta.isCorridaCerrada()) return false;
                return true;
            }
            this.corridaActual = new Object[64];
            for(int h=0; h<v.length; h++)
                this.corridaActual[h]=v[h].toString();
            return true;
        }catch(Exception ex){
            //ex.printStackTrace();
            //throw new ConsultaCorridaException("Consulta Corrida Exception::: "+ex.getMessage());
            return true;
        }
    }
    
    private void ambienteVtaActualnormal() {
//        encVtaActual = new Object[31];
        encVtaActual = new Object[36];//[32]//VAGL 02082013[35]
        encVtaActual[0]="Corrida-Hora"; encVtaActual[1]="#"; encVtaActual[2] ="T";
        encVtaActual[3]="Nombre Pasajero"; encVtaActual[4]="Origen-Destino"; encVtaActual[5]="Costo";
        encVtaActual[6]="6";encVtaActual[7]="7";encVtaActual[8]="8";encVtaActual[9]="9";encVtaActual[10]="10";encVtaActual[11]="11";
        encVtaActual[12]="12";encVtaActual[13]="13";encVtaActual[14]="14";encVtaActual[15]="15";encVtaActual[16]="16";encVtaActual[17]="17";
        encVtaActual[18]="18";encVtaActual[19]="19";encVtaActual[20]="20";encVtaActual[21]="21";encVtaActual[22]="22";encVtaActual[23]="23";
        encVtaActual[24]="24";encVtaActual[25]="25";encVtaActual[26]="26";encVtaActual[27]="27";encVtaActual[28]="28";
        encVtaActual[29]="T. Vta";encVtaActual[30]="Nombre Autorizado";
        xVtaActual.setDataVector(null, encVtaActual);
        AnchoColumnasVtaActual();
    }

    private void ChecaConstruyeRedondos() {
        if(!transaccion.equals(txVENTA)) return;
        boolean huboRedondos=false;
        int pasa;
        String prutaIdRedondoIda ="";
        if(sesionVenta.isBolRedCer())
            prutaIdRedondoIda = ""+rutaIdRedondoIda;
        else
            prutaIdRedondoIda = this.listaCorridas[fila][8].toString();
        for(pasa=0; pasa<Boletos.length; pasa++)
        {
            System.out.println("("+Boletos.length+") Tipo de boleto en ChecaConstruyeRedondos: (Tipo)"+Boletos[pasa][6].toString()+"   (Servicio)"+Boletos[pasa][8].toString());
            if(Boletos[pasa][6].toString().equals("R")){
                huboRedondos=true;
                break;
            }
        }
        System.out.println("huboRedondos = "+huboRedondos);
        BoletosRedondos=null;
        if(!huboRedondos) return;
        
        String[] strSplit;
        int contRedondos=0;
        //Object[][] nuevo = new Object[Boletos.length*2][29];
//        Object[][] nuevo = new Object[Boletos.length*2][31];
        Object[][] nuevo = new Object[Boletos.length*2][36];//[32]//VAGL 02082013[35]
        int i;
        double calcTarifa, tDescuento;
        char tipoPasajero;
        long tipoId;
        String[] arrayAsientosRed=null;
        int indexbr = 1;
        if(sesionVenta.isBolRedCer())
            arrayAsientosRed = asientosRedCerrRegreso.split(",");
        for(i=0; i<Boletos.length; i++){
            tipoPasajero = Boletos[i][2].toString().charAt(0);
            //tipoId = jTxtTipoPasaje.getTipopasajeid(tipoPasajero,Long.valueOf(this.listaCorridas[fila][8].toString()));
            tipoId = jTxtTipoPasaje.getTipopasajeid(tipoPasajero,Long.valueOf(prutaIdRedondoIda));

            tDescuento = jTxtTipoPasaje.getDescuento(tipoId);
            String redondeo =jTxtTipoPasaje.getRedondeo(tipoId);
            //for(pasa=0; pasa<29; pasa++){
            for(pasa=0; pasa<35; pasa++){//32
                if(pasa==6)
                    nuevo[contRedondos][6]= "S";
                else{
                    if(pasa==5)
                    {
                        //if(Boletos[i][8].toString().equals("DIRECTO ECONOMICO") || Boletos[i][8].toString().equals("PULLMAN PRIMERA CLASE") )
                        //String prom = Boletos[i][8]+"-"+Boletos[i][21]+"-"+Boletos[i][22];
                        //System.out.println("ChecaConstruyeRedondos: "+prom);
                        //System.out.println("ChecaConstruyeRedondos: (RutaId) "+this.listaCorridas[fila][8].toString());
                        System.out.println("ChecaConstruyeRedondos: (RutaId) "+prutaIdRedondoIda);
                        System.out.println("checa redondeo(ChecaConstruyeRedondos): "+redondeo);
                        //if(Boletos[i][8].toString().equals("DIRECTO ECONOMICO") || Boletos[i][8].toString().equals("PULLMAN PRIMERA CLASE") )
                        //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() )
                        //if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218") || this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226")  || this.listaCorridas[fila][8].toString().equals("241") || this.listaCorridas[fila][8].toString().equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && Boletos[i][6].toString().equals("R") && (""+tipoPasajero).equals("A"))
                        if(( prutaIdRedondoIda.equals("217") || prutaIdRedondoIda.equals("218") || prutaIdRedondoIda.equals("225") || prutaIdRedondoIda.equals("226")  || prutaIdRedondoIda.equals("241") || prutaIdRedondoIda.equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && Boletos[i][6].toString().equals("R") && (""+tipoPasajero).equals("A"))
                        {
                            if(redondeo.equals("S"))
                                //nuevo[contRedondos][5]= Math.ceil(sesionVenta.getUserCon().getServicioTarifaSencillo());
                                //nuevo[contRedondos][5]= Math.ceil(sesionVenta.getUserCon().getServicioTarifaRedondo()/2);
                                nuevo[contRedondos][5]= Math.ceil(sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.2));
                            else
                            {
                                //nuevo[contRedondos][5]= sesionVenta.getUserCon().getServicioTarifaSencillo();
                                //nuevo[contRedondos][5]= sesionVenta.getUserCon().getServicioTarifaRedondo()/2;
                                double tar = (sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.4));
                                if(redondeo.equals("S"))
                                    tar= Math.ceil(tar);
                                else
                                {
                                    double d = (Double.valueOf(""+tar) - (Double.valueOf(""+ Double.valueOf(""+tar)).longValue()) );
                                    if(d > 0 && (d != .50 || d != .20 || d != .40))                                
                                         tar =    Math.ceil( Double.valueOf(""+tar));                            
                                }
                                
                                nuevo[contRedondos][5]= tar;//sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.4);
                                System.out.println("valor para ver el ceil: "+(Double.valueOf(""+nuevo[contRedondos][5])));
                                double d = (Double.valueOf(""+nuevo[contRedondos][5]) - (Double.valueOf(""+ Double.valueOf(""+nuevo[contRedondos][5])).longValue()) );
                                System.out.println("Resultado para ver el ceil: "+d);
                                if(d > 0 && (d != .50 || d != .20 || d != .40))                                
                                     nuevo[contRedondos][5] =    Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));                            
                            }
                        }
                        else
                           {
                                if(redondeo.equals("S"))
                                     nuevo[contRedondos][5]= Math.ceil(sesionVenta.getUserCon().getServicioTarifaSencillo()-(tDescuento*sesionVenta.getUserCon().getServicioTarifaSencillo()));
                                else
                                {
                                     nuevo[contRedondos][5]= sesionVenta.getUserCon().getServicioTarifaSencillo()-(tDescuento*sesionVenta.getUserCon().getServicioTarifaSencillo());
                                     double d = (Double.valueOf(""+nuevo[contRedondos][5]) - (Double.valueOf(""+ Double.valueOf(""+nuevo[contRedondos][5])).longValue()) ) ;
                                     if(d > 0 && d != .50)                                
                                         nuevo[contRedondos][5] =    Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));                            
                                }
                            }
                        }
                    else
                        nuevo[contRedondos][pasa]= Boletos[i][pasa]; // boleto normal
                }
            }
            contRedondos++;
            if(Boletos[i][6].toString().equals("R")){ // construyo boleto redondo
                //Boletos[i][31] = "R"; //Se marca como redondo la parte sencilla del boleto redondo
                //System.out.println("Boleto["+i+"]= "+Boletos[i][1]);
                //System.out.println("checa referenciado Boleto["+i+"]= "+Boletos[i][31]);
                //Boletos[i][31] = "R";
                System.out.println("entra a construye redondo... ");
                System.out.println("bolRedCer: "+sesionVenta.isBolRedCer());
                if (sesionVenta.isBolRedCer())
                    nuevo[contRedondos][0] = this.listaCorridas[fila][0].toString().substring(0,10)+"-"+this.listaCorridas[fila][1];                     // corrida-hora
                else
                    nuevo[contRedondos][0] = null;                               // corrida-hora
                if (sesionVenta.isBolRedCer())
                {
                    nuevo[contRedondos][1] = arrayAsientosRed[indexbr];          // asiento
                    indexbr++;
                }
                else
                    nuevo[contRedondos][1] = null;                               // asiento
                nuevo[contRedondos][2] = Boletos[i][2];                          // tipo pasajero
                nuevo[contRedondos][3] = Boletos[i][3];                          // nombre pasajero
                strSplit = Boletos[i][4].toString().split("-");
                if(strSplit.length>1)
                    nuevo[contRedondos][4] = strSplit[1]+"-"+strSplit[0];        // Boletos[i][4]; origen-destino invertido
                else
                    nuevo[contRedondos][4] = Boletos[i][4];                      // origen-destino
                calcTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo()/2;
//                calcTarifa = sesionVenta.getUserCon().getServicioTarifaRedondo()-sesionVenta.getUserCon().getServicioTarifaSencillo();
                String prom = Boletos[i][8]+"-"+Boletos[i][21]+"-"+Boletos[i][22];
//                if(Boletos[i][8].toString().equals("DIRECTO ECONOMICO") || Boletos[i][8].toString().equals("PULLMAN PRIMERA CLASE"))
                   //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && sesionVenta.getTmsVtaFacade().getPromocionVigente())
                //if(( this.listaCorridas[fila][8].toString().equals("217") || this.listaCorridas[fila][8].toString().equals("218") || this.listaCorridas[fila][8].toString().equals("225") || this.listaCorridas[fila][8].toString().equals("226")  || this.listaCorridas[fila][8].toString().equals("241") || this.listaCorridas[fila][8].toString().equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && Boletos[i][2].toString().equals("A"))
                if(( prutaIdRedondoIda.equals("217") || prutaIdRedondoIda.equals("218") || prutaIdRedondoIda.equals("225") || prutaIdRedondoIda.equals("226")  || prutaIdRedondoIda.equals("241") || prutaIdRedondoIda.equals("242")) && sesionVenta.getTmsVtaFacade().getPromocionVigente() && Boletos[i][2].toString().equals("A"))
                {
//                    nuevo[contRedondos][5] = Math.ceil(sesionVenta.getUserCon().getServicioTarifaRedondo()/2);    // importe boleto
                    //nuevo[contRedondos][5] = sesionVenta.getUserCon().getServicioTarifaRedondo() - sesionVenta.getUserCon().getServicioTarifaSencillo() ;    // importe boleto
//                    nuevo[contRedondos][5] = sesionVenta.getUserCon().getServicioTarifaRedondo()/2;    // importe boleto
//                    nuevo[contRedondos-1][5] = sesionVenta.getUserCon().getServicioTarifaRedondo()/2;
                    double tar = (sesionVenta.getUserCon().getServicioTarifaRedondo()-(sesionVenta.getUserCon().getServicioTarifaRedondo()*.2));
                    if(redondeo.equals("S"))
                        tar= Math.ceil(tar);
                    else
                    {
                        double d = (Double.valueOf(""+tar) - (Double.valueOf(""+ Double.valueOf(""+tar)).longValue()) );
                        if(d > 0 && (d != .50 || d != .20 || d != .40))                                
                             tar =    Math.ceil( Double.valueOf(""+tar));                            
                    }
                    
                    nuevo[contRedondos][5] =   tar/2;    // importe boleto
                    nuevo[contRedondos-1][5] = tar/2;
                    System.out.println(" antes: nuevo[contRedondos][5]"+nuevo[contRedondos][5]);
                    System.out.println(" antes: nuevo[contRedondos][5]"+nuevo[contRedondos-1][5]);
                    double d = (Double.valueOf(""+nuevo[contRedondos][5]) - (Double.valueOf(""+ Double.valueOf(""+nuevo[contRedondos][5])).longValue()) );
                    System.out.println("valor para ver el ceil(R): "+(Double.valueOf(""+nuevo[contRedondos][5])));
                    System.out.println("Resultado para ver el ceil(R): "+d);
                    if(redondeo.equals("S"))
                    {
                        nuevo[contRedondos][5] =    Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));  
                        nuevo[contRedondos-1][5] = Math.ceil( Double.valueOf(""+nuevo[contRedondos][5])); 
                    }
                    else
                    {
                        if(d > 0 && (d == .50 || d == .20 || d == .40))                                 
                            System.out.println("No entra a ceil...");
                        else
                        {
                            System.out.println("Si entra a ceil por que no es .50o .20 o .40...");
                            nuevo[contRedondos][5] =    Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));  
                            nuevo[contRedondos-1][5] = Math.ceil( Double.valueOf(""+nuevo[contRedondos][5])); 
                        }
                        System.out.println(" despues: nuevo[contRedondos][5]"+nuevo[contRedondos][5]);
                        System.out.println(" despues: nuevo[contRedondos][5]"+nuevo[contRedondos-1][5]);
                    }
                }
                else
                {

                    if(sesionVenta.isRutaPromocionRedondoTTP(this.listaCorridas[fila][8].toString()) && !(""+tipoPasajero).equals("A"))
                    {
                         System.out.println("Si entra a cambiar la tarifa de redondo TTP(ChecaConstruyeRedondos)...");
                        nuevo[contRedondos][5] = ((sesionVenta.getUserCon().getServicioTarifaSencillo() - (tDescuento * sesionVenta.getUserCon().getServicioTarifaSencillo())) * 2)/2;
                        nuevo[contRedondos-1][5] = ((sesionVenta.getUserCon().getServicioTarifaSencillo() - (tDescuento * sesionVenta.getUserCon().getServicioTarifaSencillo())) * 2)/2;
                    }
                    else
                    {
    //                    nuevo[contRedondos][5] = Math.ceil(calcTarifa -(tDescuento*calcTarifa));    // importe boleto
                        nuevo[contRedondos][5] = calcTarifa -(tDescuento*calcTarifa);    // importe boleto
                        nuevo[contRedondos-1][5] = calcTarifa -(tDescuento*calcTarifa);
                        double d = (Double.valueOf(""+nuevo[contRedondos][5]) - (Double.valueOf(""+ Double.valueOf(""+nuevo[contRedondos][5])).longValue()) );
                        if(d > 0 && d != .50)
                        {
                            nuevo[contRedondos][5] =    Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));
                             nuevo[contRedondos-1][5] =  Math.ceil( Double.valueOf(""+nuevo[contRedondos][5]));
                        }
                    }
                }
//                System.out.println("Boletos["+i+"][5](importe boleto): "+Boletos[i][5]);
//                System.out.println(" nuevo["+contRedondos+"-1][5](importe boleto): "+Boletos[i][5]);
                
                nuevo[contRedondos][6] = "R";                                    // BOLETO NORMAL + BOLETO ABIERTO
                nuevo[contRedondos][7] = Boletos[i][7];                          // empresa
                nuevo[contRedondos][8] = Boletos[i][8];                          // servicio
                nuevo[contRedondos][9] = Boletos[i][9];                          // caja
                nuevo[contRedondos][10] = Boletos[i][10];                        // corte id
                if(sesionVenta.isBolRedCer())
                    nuevo[contRedondos][11] = this.listaCorridas[fila][0];       // clave corrida
                else
                    nuevo[contRedondos][11] = null;                              // clave corrida
                nuevo[contRedondos][12] = null;                                  // cliente id
                nuevo[contRedondos][13] = null;                                  // tipo pago
                nuevo[contRedondos][14] = null;                                  // referencia pago
                if (sesionVenta.isBolRedCer())
                    nuevo[contRedondos][15] = this.transaccion;                  // tipo operacion
                else
                    nuevo[contRedondos][15] = "VA";                              // tipo operacion
                nuevo[contRedondos][16] = null;                                  // reservacion id
                nuevo[contRedondos][17] = null;                                  // boleto relacionado id
                nuevo[contRedondos][18] = null;                                  // dias validez boleto abierto
                nuevo[contRedondos][19] = null;                                  // folio preimpreso
                nuevo[contRedondos][20] = Boletos[i][20];                        // ciudad Venta
                nuevo[contRedondos][21] = Boletos[i][22];                        // origen invertido
                nuevo[contRedondos][22] = Boletos[i][21];                        // destino invertido
                nuevo[contRedondos][23] = Boletos[i][23];                        // tipo Transaccion
                nuevo[contRedondos][24] = Boletos[i][24];                        // cajero
                if (sesionVenta.isBolRedCer())
                { 
                   nuevo[contRedondos][25] = this.listaCorridas[fila][6];                // corridaId
                   nuevo[contRedondos][26] = this.listaCorridas[fila][1];                // fecha
                   nuevo[contRedondos][27] = this.listaCorridas[fila][2];                // hora}
                 }
                else
                {
                    nuevo[contRedondos][25] = Boletos[i][25];                    // corridaId
                    nuevo[contRedondos][26] = Boletos[i][26];                    // fecha
                    nuevo[contRedondos][27] = Boletos[i][27];                    // hora
                }
                nuevo[contRedondos][28] = Boletos[i][28];                        // sin numero de asiento
                nuevo[contRedondos][29] = Boletos[i][29];                        //Tipo de Venta (N = Normal, F = Referenciada)
                nuevo[contRedondos][30] = Boletos[i][30];                        //Nombre del cliente autorizado 
                nuevo[contRedondos][31] = "R";                                  //Identifica al Boleto de la parte Sencillo del Redondo 
                nuevo[contRedondos][35] = "MOS";                                //Tipo de Venta MOS=Contado, CRE=Credito
                contRedondos++;
            }
        }
        // actualiza tarifas correspondientes
//        BoletosRedondos = new Object[contRedondos][29];
//        BoletosRedondos = new Object[contRedondos][31];
        System.out.println("contRedondos: "+contRedondos);
        BoletosRedondos = new Object[contRedondos][36];//[32]//VAGL 02082013[35]
        //double suma = 0; 
        for(i=0; i<contRedondos; i++){
            //System.out.println("\n");
            //for(pasa=0; pasa<29; pasa++){
            for(pasa=0; pasa<35; pasa++){//32
                //System.out.println("BoletosRedondos["+i+"]["+pasa+"]= "+nuevo[i][pasa]);    
                BoletosRedondos[i][pasa]= nuevo[i][pasa];
                //if(pasa==5) suma = suma+Double.valueOf(BoletosRedondos[i][pasa].toString());
            }
        }
        nuevo=null;
    }
    
    private boolean ValidaSesionVenta(){
        int edoConsulta;
        if((edoConsulta=sesionVenta.getValidaSesionVenta())!=0){
            if(edoConsulta==-21){
                this.getToolkit().beep();
                DialogoSiNo = new JDlgSiNo("En este momento no es posible realizar la operacion.","<html>Por favor intentelo mas tarde o,<br>¿desea salir de TMS Venta?.</html>",false);
                if(!DialogoSiNo.getResultado()) return false;
            }
            deshacer();
            if(jThdConsultaCorrida!=null){
                jThdConsultaCorrida.moreQuotes=false;
                jThdConsultaCorrida = null;
                sesionVenta.b_decode_data=false;
            }
            if(jlbl_reloj!=null){
                jlbl_reloj.finaliza();
                jlbl_reloj=null;
            }
            if(threadPlantilla!=null){
                threadPlantilla.noSalir=false;
                threadPlantilla = null;
            }
            if(!sesionVenta.finalizarVenta())
                DialogoAceptar.mostrarDialogo("¡La sesion no se cerro correctamente!", "Contacte al administrador del sistema.", Color.RED);
            this.dispose();
            return false;
        }
        return true;
    }
    
    private void deshacer(){
        int rSP=-1;
        if(this.ubiCompo==ucVtaActual){
            ambienteBoletosRedondos=false;
            sesionVenta.iTempos=1;
            rSP=-1;
            while(rSP==-1){
                rSP=sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), sesionVenta.getCadenaTiposPasaje(), "E");
                if(rSP==-1){
                    sesionVenta.iTempos++;
                    if(sesionVenta.iTempos>sesionVenta.tTempos){
                        System.out.println("Asiento bloqueado");
                        break;
                    }
                }
            }
            ambienteVtaActualnormal();
            jLblTotalVtaActual.setText("Total: $0.00");
            jTxtTipoPasaje.setText("");
            jTxtAsientos.setText("");
            xCorridas.setDataVector(null, encCorridas);
            AnchoColumnasCorridas();
            xVtaActual.setDataVector(null, encVtaActual);
            AnchoColumnasVtaActual();
            if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
            else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
            jTxtFecha.setText(hoyEs);
            jTxtHora.setText("");
            filtroInicialX();
            jCboServicio.requestFocusInWindow();
        }
        else{
            if(this.ubiCompo==ucTipoPasaje){
                jTxtAsientos.setText("");
                jTxtTipoPasaje.setText("");
                sesionVenta.iTempos=1;
                rSP=-1;
                while(rSP==-1){
                    sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E");
                    if(rSP==-1){
                        sesionVenta.iTempos++;
                        if(sesionVenta.iTempos>sesionVenta.tTempos){
                            System.out.println("Asiento bloqueado");
                            break;
                        }
                    }
                }
                if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                xCorridas.setDataVector(null, encCorridas);
                AnchoColumnasCorridas();
                jTxtFecha.setText(hoyEs);
                jTxtHora.setText("");
                filtroInicialX();
                jCboServicio.requestFocusInWindow();
            }
            else{
                if(this.ubiCompo==ucAsientos){
                    //sesionVenta._OcuparAsientosSP(this.corridaId, sesionVenta.getCadenaAsientos(), null, "E");
                    if(this.transaccion.equals(txCHORARIO)){
                        jScpVtaActual.setVisible(true);
                        jLblTotalVtaActual.setVisible(true);
                        jPnlCHorario.setVisible(false);
                        this.desHabilitarCriterios(1);
                    }
                    if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                    else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                    xCorridas.setDataVector(null, encCorridas);
                    AnchoColumnasCorridas();
                    jTxtFecha.setText(hoyEs);
                    jTxtHora.setText("");
                    filtroInicialX();
                    jCboServicio.requestFocusInWindow();
                }
                else{
                    if(this.ubiCompo==ucListCorridas){
                        xCorridas.setDataVector(null, encCorridas);
                        AnchoColumnasCorridas();
                        if(this.transaccion.equals(txCHORARIO)){
                            abledComponentsCriterios();
                            abledComponents(jTblCorridas);
                            desHabilitarCriterios(2);
                            jTxtFecha.requestFocusInWindow();
                            this.ubiCompo=ucEjecCons;
                            return;
                        }
                        if(sesionVenta.getUserCon().getAplicacionVenta()) jLblBarraEstado.setText(mensajes.getMensajeVta(1));
                        else jLblBarraEstado.setText(mensajes.getMensajeCallCenter(1));
                        jTxtFecha.setText(hoyEs);
                        jTxtHora.setText("");
                        filtroInicialX();
                        jCboServicio.requestFocusInWindow();
                    }
                }
            }
        }
        jTxtTipoPasaje.setText("");
        this.ubiCompo=ucEjecCons;
    }
    
    private boolean validaDisponibilidadRvn() {
        String fechaHoraCorrida;
        long dtRvn = jlbl_reloj.getFecha().getTime()+(sesionVenta.getUserCon().getMinAntRv()*60000);
        long lFechaHoraCorrida;
        for(int i=0; i<jTblVtaActual.getRowCount(); i++){
            fechaHoraCorrida = Boletos[i][26] + " " + Boletos[i][27];
            try {
                lFechaHoraCorrida = formatoDateServer.parse(fechaHoraCorrida).getTime();
            } catch (ParseException ex) {
                lFechaHoraCorrida = dtRvn;
            }
            if(dtRvn>=lFechaHoraCorrida) return false;
        }
        return true;
    }

    private Vector queEmpresasRefolian() {
        Vector xEmp = new Vector();
        if(sesionVenta.getUserCon().getFolioUnico()){
            xEmp.add(sesionVenta.getUserCon().getEmpresaPrincipal());
            return xEmp;
        }
        JDlgMotivoRefol jDlgMotivoRefol = new JDlgMotivoRefol(sesionVenta);
        jDlgMotivoRefol.setVisible(true);
        // caso 1 error de impresion
        xEmp=jDlgMotivoRefol.getEmpresas();
        if(xEmp==null) return null;
        long f1, f2;
        if(xEmp.size()!=0){
            // que tal si no tiene boletos para ajustar
            f1=Long.valueOf(sesionVenta.obtenerFolioActual(xEmp.get(0).toString()));
            f2=Long.valueOf(sesionVenta.obtenerFolioFinal(xEmp.get(0).toString()));
            if(f1>f2){
                this.getToolkit().beep();
                DialogoAceptar.mostrarDialogo("Boletos agotados.","<html>No es posible realizar ajuste.<br>" +
                        "Realice una nueva dotacion.</html>", Color.RED);
                return null;
            }
            // ajuste realmente con boletos
            return xEmp;
        }
        
        // caso 2 a quien se le acabo el boleto
        for(int i=0; i<sesionVenta.getUserCon().getFolioPreimpreso().length; i++){
            f1=Long.valueOf(sesionVenta.obtenerFolioActual(sesionVenta.getUserCon().getFolioPreimpreso()[i][2]));
            f2=Long.valueOf(sesionVenta.obtenerFolioFinal(sesionVenta.getUserCon().getFolioPreimpreso()[i][2]));
            if(f1>f2) xEmp.add(sesionVenta.getUserCon().getFolioPreimpreso()[i][2]);
        }
        if(xEmp.size()!=0) return xEmp;
        this.getToolkit().beep();
        DialogoAceptar.mostrarDialogo("¡Aun no debe refoliar!","Todas las empresas tienen boletos disponibles.", Color.RED);
        return null;
    }

    private void filtroInicial() {
        sesionVenta.cargarOrigenesDestinosServicios(jCboCiudadVenta.getSelectedItem().toString(), false);
        cbmServicio = new DefaultComboBoxModel(sesionVenta.getServicios());
        cbmOrigen = new DefaultComboBoxModel(sesionVenta.getOrigenes());
        cbmDestino = new DefaultComboBoxModel(sesionVenta.getDestinos());
        jCboOrigen.setModel(cbmOrigen);
        jCboDestino.setModel(cbmDestino);
        jCboServicio.setModel(cbmServicio);
        if(jCboCiudadVenta.getSelectedItem().toString().equals(sesionVenta.getUserCon().getTerminalNombre()))
            jCboOrigen.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
    }
    
    private void filtroInicialX() {
        System.out.println("Filtro inicial X...");
        if(jThdConsultaCorrida != null){
            jThdConsultaCorrida.moreQuotes = false;
            jThdConsultaCorrida = null;
            sesionVenta.b_decode_data=false;
        }
        BoletoAVender=null;
        TiposAsientos=null;
        Boletos=null;
        BoletosRedondos=null;
        jTxtFecha.setText(hoyEs);
        jTxtHora.setText("");
        jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$0.00</font></html>");
        sesionVenta.cargarOrigenesDestinosServicios(sesionVenta.getUserCon().getTerminalNombre(), false);
        cbmServicio = new DefaultComboBoxModel(sesionVenta.getServicios());
        cbmOrigen = new DefaultComboBoxModel(sesionVenta.getOrigenes());
        cbmDestino = new DefaultComboBoxModel(sesionVenta.getDestinos());
        jCboOrigen.setModel(cbmOrigen);
        jCboDestino.setModel(cbmDestino);
        jCboServicio.setModel(cbmServicio);
        jCboOrigen.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
        jCboCiudadVenta.setSelectedItem(sesionVenta.getUserCon().getTerminalNombre());
        ///Limpia las variables del los productos
        List<ProductoCarrito>productos = new ArrayList<ProductoCarrito>();
        this.sesionVenta.setProductosCarrito(productos);
        sesionVenta.setCobroCargo(false);
        sesionVenta.setTipoPagoCargo("");
        sesionVenta.setAprobacionCargo("");

        this.COAnt = sesionVenta.getUserCon().getTerminalNombre();
        this.nombreEmpresa = sesionVenta.getUserCon().getEmpresaPrincipal();
        if(sesionVenta.getUserCon().getAplicacionVenta())
            this.setLabelFolio(sesionVenta.obtenerFolioActual(this.nombreEmpresa), this.nombreEmpresa);
        this.ubiCompo=ucEjecCons;
        this.ubiCrit=uCtServicio;
        if(sesionVenta.getUserCon().getAplicacionVenta()) this.jLblTx.setText("*VENTA*");
        else  this.jLblTx.setText("*CALL CENTER*");
        jLblEmpresa.setVisible(false); jCboEmpresas.setVisible(false); // MOD: QUITO EMPRESA
        excluyeTeclasCriterios = false;
        sesionVenta.setBolRedCer(false);
        sesionVenta.setDBLink("");
        this.PlantillaResumenDefault();
        ambienteVtaActualnormal();
        abledComponentsCriterios();
        abledComponents(jTblCorridas);
        enabledComponents(jTxtAsientos);
        enabledComponents(jTxtTipoPasaje);
        abledComponents(jTblVtaActual);
    }
    
    private boolean existeCorridaRegreso(){
                String o, d, s;
        d = jCboOrigen.getSelectedItem().toString(); // origen invertido
        o = this.listaCorridas[fila][5].toString(); // destino invertido
        s = this.listaCorridas[fila][3].toString(); // servicio
        sesionVenta.getDBLinkClaveOrigenX(o);
        System.out.println(" o "+o+" d "+d+" s "+s);
        int zz = sesionVenta.busqExisteCorrida(o, o, d, criterio.getHoy(), s, this.nombreEmpresa);
        switch(zz){
            case -1: DialogoAceptar.mostrarDialogo("TMS Venta.","Este servicio no dispone de boleto redondo.",Color.RED); return false;
            case -2: DialogoAceptar.mostrarDialogo("TMS Venta.","La ruta de regreso no permite venta.",Color.RED); return false;
        }
        return true;
    }
    
    private void CalculaResumenZ(Object[] vInfo, long CapBus){
        System.out.println("vInfo: "+vInfo[0]+","+vInfo[1]+","+vInfo[2]+","+vInfo[3]+","+vInfo[4]);
        try{
        Object[][] nDatos = new Object[3][9];
        for(int i1=0; i1<3; i1++)
            for(int i2=0; i2<nDatos.length; i2++)
                nDatos[i1][i2] = null;
        
        int tiposPresentes=0;
        StringTokenizer srtToken;
        
        nDatos[0][1] = sesionVenta.getTmsTiposPasajeroLetra("MENOR"); // TIPO
        nDatos[2][1] = "0"; // VENDIDOS
        nDatos[1][1] =  ""; //DISPONIBLES
        tiposPresentes++;
        
        if(sesionVenta.getUserCon().getPerVac()){ // VACACIONES
            if (vInfo[62] == null || vInfo[62].toString().equals("")){
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(vInfo[1].toString()));
                sesionVenta.getUserCon().setEstCor(Integer.valueOf(vInfo[2].toString()));
                sesionVenta.getUserCon().setProCor(Integer.valueOf(vInfo[3].toString()));
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(vInfo[4].toString()));
            }else{
                srtToken = new StringTokenizer(vInfo[62].toString(),"-");
                //Senectud
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                //Profesor
                sesionVenta.getUserCon().setProCor(Integer.valueOf(srtToken.nextToken()));
                //Estudiante
                sesionVenta.getUserCon().setEstCor(Integer.valueOf(srtToken.nextToken()));
                //Especial
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
            }
        }
        else{ // NO VACACIONES
            if (vInfo[62] == null || vInfo[62].toString().equals("")){
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(vInfo[1].toString()));
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(vInfo[4].toString()));
            }else{
                srtToken = new StringTokenizer(vInfo[62].toString(),"-");
                //Senectud
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                //Profesor
                Integer.valueOf(srtToken.nextToken());
                //Estudiante
                Integer.valueOf(srtToken.nextToken());
                //Especial
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
            }
            sesionVenta.getUserCon().setEstCor(0);
            sesionVenta.getUserCon().setProCor(0);
        }
        
        if(sesionVenta.getUserCon().getSenCor()!=0){
            nDatos[0][2] = sesionVenta.getTmsTiposPasajeroLetra("SENECTUD"); // TIPO
            sesionVenta.getUserCon().setDispSenCor(Integer.valueOf(vInfo[1].toString()));
            nDatos[1][2] = sesionVenta.getUserCon().getDispSenCor(); //DISPONIBLES
            nDatos[2][2] = "0"; // VENDIDOS
            tiposPresentes++;
        }
        else{
            sesionVenta.getUserCon().setDispSenCor(0);
        }
        if(sesionVenta.getUserCon().getEstCor()!=0){
            nDatos[0][3] = sesionVenta.getTmsTiposPasajeroLetra("ESTUDIANTE"); // TIPO
            sesionVenta.getUserCon().setDispEstCor(Integer.valueOf(vInfo[2].toString()));
            nDatos[1][3] = sesionVenta.getUserCon().getDispEstCor(); //DISPONIBLES
            nDatos[2][3] = "0"; // VENDIDOS
            tiposPresentes++;
        }
        else{
            sesionVenta.getUserCon().setDispEstCor(0);
        }
        if(sesionVenta.getUserCon().getProCor()!=0){
            nDatos[0][4] = sesionVenta.getTmsTiposPasajeroLetra("PROFESOR"); // TIPO
            sesionVenta.getUserCon().setDispProCor(Integer.valueOf(vInfo[3].toString()));
            nDatos[1][4] = sesionVenta.getUserCon().getDispProCor(); //DISPONIBLES
            nDatos[2][4] = "0"; // VENDIDOS
            tiposPresentes++;
        }
        else{
            sesionVenta.getUserCon().setDispProCor(0);
        }
        if(sesionVenta.getUserCon().getCorCor()!=0){
            nDatos[0][5] = sesionVenta.getTmsTiposPasajeroLetra("ESPECIAL"); // TIPO
            sesionVenta.getUserCon().setDispCorCor(Integer.valueOf(vInfo[4].toString()));
            nDatos[1][5] = sesionVenta.getUserCon().getDispCorCor(); //DISPONIBLES
            nDatos[2][5] = "0"; // VENDIDOS
            tiposPresentes++;
        }
        else{
            sesionVenta.getUserCon().setDispCorCor(0);
        }
        
        nDatos[0][0] = sesionVenta.getTmsTiposPasajeroLetra("ADULTO"); // TIPO
        nDatos[2][0] = "0"; // VENDIDOS
        nDatos[1][0] =  ""; //DISPONIBLES
        tiposPresentes++;
        
        // TABLA DINAMICA DE RESUMEN
        encResumenVtaCorrida = new Object[tiposPresentes+2];
        objResumenVtaCorrida = new Object[2][tiposPresentes+2];
        encResumenVtaCorrida[0]=""; objResumenVtaCorrida[0][0]="Disp.";  objResumenVtaCorrida[1][0]="Vend.";
        int i, h=0;
        boolean noSalir;
        for(i=1; i<=tiposPresentes; i++){
            noSalir = true;
            while(noSalir){
                if(nDatos[0][h]!=null){
                    encResumenVtaCorrida[i]=nDatos[0][h];
                    objResumenVtaCorrida[0][i]=nDatos[1][h];
                    objResumenVtaCorrida[1][i]=nDatos[2][h];
                    noSalir=false;
                }
                h++;
            }       
        }
        encResumenVtaCorrida[i]="Ocup"; objResumenVtaCorrida[0][i]="";  objResumenVtaCorrida[1][i]="0/"+CapBus;
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                xResumenVtaCorrida.setDataVector(objResumenVtaCorrida, encResumenVtaCorrida);
                AnchoColumnasResumenVtaCorrida();
            }
        });
        //
        jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getUserCon().getServicioTarifaSencillo())+"</font></html>");
        //if(ingresoTP) sesionVenta.TiposPasajeParaVenta(vInfo.get(5).toString()); // POR CORRIDA OBTENGO QUE PUEDO VENDER
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }
    private void CalculaResumenX(Object[] vInfo, int bolVta, int opcion, boolean ingresoTP, boolean noSeRefrescoPlantilla, long CapBus){
        try{
        Object[][] nDatos = new Object[3][9];
        for(int i1=0; i1<3; i1++)
            for(int i2=0; i2<nDatos.length; i2++)
                nDatos[i1][i2] = null;
        
        int tiposPresentes=0;
        int vendidos=0;
        int adultos=0;
        StringTokenizer srtToken;
        
        Vector xActual = new Vector();
        
        nDatos[0][1] = sesionVenta.getTmsTiposPasajeroLetra("MENOR"); // TIPO
        nDatos[2][1] = Integer.valueOf(vInfo[0].toString()); // VENDIDOS
        nDatos[1][1] =  ""; //DISPONIBLES
        tiposPresentes++;
        vendidos+=Integer.valueOf(nDatos[2][1].toString());
        
        xActual.add(nDatos[2][1].toString());

        if(sesionVenta.getUserCon().getPerVac()){ // VACACIONES
            if (vInfo[62] == null || vInfo[62].toString().equals("")){
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(vInfo[1].toString()));
                sesionVenta.getUserCon().setEstCor(Integer.valueOf(vInfo[2].toString()));
                sesionVenta.getUserCon().setProCor(Integer.valueOf(vInfo[3].toString()));
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(vInfo[4].toString()));
            }else{
                srtToken = new StringTokenizer(vInfo[62].toString(),"-");
                //Senectud
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                //Profesor
                sesionVenta.getUserCon().setProCor(Integer.valueOf(srtToken.nextToken()));
                //Estudiante
                sesionVenta.getUserCon().setEstCor(Integer.valueOf(srtToken.nextToken()));
                //Especial
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
            }
        }
        else{ // NO VACACIONES
            if (vInfo[62] == null || vInfo[62].toString().equals("")){
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(vInfo[1].toString()));
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(vInfo[4].toString()));
            }else{
                srtToken = new StringTokenizer(vInfo[62].toString(),"-");
                //Senectud
                sesionVenta.getUserCon().setSenCor(Integer.valueOf(srtToken.nextToken()));
                //Profesor
                Integer.valueOf(srtToken.nextToken());
                //Estudiante
                Integer.valueOf(srtToken.nextToken());
                //Especial
                sesionVenta.getUserCon().setCorCor(Integer.valueOf(srtToken.nextToken()));
            }
            sesionVenta.getUserCon().setEstCor(0);
            sesionVenta.getUserCon().setProCor(0);
        }
        
        if(sesionVenta.getUserCon().getSenCor()!=0){
            nDatos[0][2] = sesionVenta.getTmsTiposPasajeroLetra("SENECTUD"); // TIPO
            sesionVenta.getUserCon().setDispSenCor(Integer.valueOf(vInfo[1].toString()));
            nDatos[1][2] = sesionVenta.getUserCon().getDispSenCor(); //DISPONIBLES
            nDatos[2][2] = sesionVenta.getUserCon().getSenCor() - Integer.valueOf(nDatos[1][2].toString()); // VENDIDOS
            tiposPresentes++;
            vendidos+=Integer.valueOf(nDatos[2][2].toString());
            
            xActual.add(nDatos[2][2].toString());
        }
        else{
            sesionVenta.getUserCon().setDispSenCor(0);
        }
        if(sesionVenta.getUserCon().getEstCor()!=0){
            nDatos[0][3] = sesionVenta.getTmsTiposPasajeroLetra("ESTUDIANTE"); // TIPO
            sesionVenta.getUserCon().setDispEstCor(Integer.valueOf(vInfo[2].toString()));
            nDatos[1][3] = sesionVenta.getUserCon().getDispEstCor(); //DISPONIBLES
            nDatos[2][3] = sesionVenta.getUserCon().getEstCor() - Integer.valueOf(nDatos[1][3].toString()); // VENDIDOS
            tiposPresentes++;
            vendidos+=Integer.valueOf(nDatos[2][3].toString());
            
            xActual.add(nDatos[2][3].toString());
        }
        else{
            sesionVenta.getUserCon().setDispEstCor(0);
        }        
        if(sesionVenta.getUserCon().getProCor()!=0){
            nDatos[0][4] = sesionVenta.getTmsTiposPasajeroLetra("PROFESOR"); // TIPO
            sesionVenta.getUserCon().setDispProCor(Integer.valueOf(vInfo[3].toString()));
            nDatos[1][4] = sesionVenta.getUserCon().getDispProCor(); //DISPONIBLES
            nDatos[2][4] = sesionVenta.getUserCon().getProCor() - Integer.valueOf(nDatos[1][4].toString()); // VENDIDOS
            tiposPresentes++;
            vendidos+=Integer.valueOf(nDatos[2][4].toString());
            
            xActual.add(nDatos[2][4].toString());
        }
        else{
            sesionVenta.getUserCon().setDispProCor(0);
        }
        if(sesionVenta.getUserCon().getCorCor()!=0){
            nDatos[0][5] = sesionVenta.getTmsTiposPasajeroLetra("ESPECIAL"); // TIPO
            sesionVenta.getUserCon().setDispCorCor(Integer.valueOf(vInfo[4].toString()));
            nDatos[1][5] = sesionVenta.getUserCon().getDispCorCor(); //DISPONIBLES
            nDatos[2][5] = sesionVenta.getUserCon().getCorCor() - Integer.valueOf(nDatos[1][5].toString()); // VENDIDOS
            tiposPresentes++;
            vendidos+=Integer.valueOf(nDatos[2][5].toString());
            
            xActual.add(nDatos[2][5].toString());
        }
        else{
            sesionVenta.getUserCon().setDispCorCor(0);
        }

        opcion=bolVta;
        if(opcion==0) adultos = 0;
        else adultos=(opcion>vendidos ? opcion-vendidos : 0);
        
        nDatos[0][0] = sesionVenta.getTmsTiposPasajeroLetra("ADULTO"); // TIPO
        nDatos[2][0] = adultos; // VENDIDOS
        nDatos[1][0] =  ""; //DISPONIBLES
        tiposPresentes++;
        vendidos+=adultos;
        
        xActual.add(nDatos[2][0].toString());
        
        if(esMismoVectorR(xActual, xAnterior) && noSeRefrescoPlantilla) return;
        xAnterior = copiaVector(xActual);
        // TABLA DINAMICA DE RESUMEN
        encResumenVtaCorrida = new Object[tiposPresentes+2];
        objResumenVtaCorrida = new Object[2][tiposPresentes+2];
        encResumenVtaCorrida[0]=""; objResumenVtaCorrida[0][0]="Disp.";  objResumenVtaCorrida[1][0]="Vend.";
        int i, h=0;
        boolean noSalir;
        for(i=1; i<=tiposPresentes; i++){
            noSalir = true;
            while(noSalir){
                if(nDatos[0][h]!=null){
                    encResumenVtaCorrida[i]=nDatos[0][h];
                    objResumenVtaCorrida[0][i]=nDatos[1][h];
                    objResumenVtaCorrida[1][i]=nDatos[2][h];
                    noSalir=false;
                }
                h++;
            }       
        }
        encResumenVtaCorrida[i]="Ocup"; objResumenVtaCorrida[0][i]="";  objResumenVtaCorrida[1][i]=vendidos+"/"+CapBus;
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                xResumenVtaCorrida.setDataVector(objResumenVtaCorrida, encResumenVtaCorrida);
                AnchoColumnasResumenVtaCorrida();
            }
        });
        //
        jLblTarifaAdulto.setText("<html>Precio: <font size=+1 color="+ColoresInterfaz.cHTML2+">$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getUserCon().getServicioTarifaSencillo())+"</font></html>");
        }catch(java.lang.ArrayIndexOutOfBoundsException aex){
            ;//aex.printStackTrace();
        }
    }

    private boolean consultaListadoCorridas() throws ConsultaCorridaException{
        inicializaCorrida();
        Object[][] dc = sesionVenta.obtenerCorridasVenta(jCboCiudadVenta.getSelectedItem().toString(),
                criterio.getOrigen(), criterio.getDestino(),
                formatoDateServer.format(criterio.getHoy()), criterio.getServicio(), criterio.getEmpresa());
        
        System.out.println("CVta "+jCboCiudadVenta.getSelectedItem().toString()+" - Origen "+
                criterio.getOrigen()+" - Destino "+criterio.getDestino()+" - FechaHora "+
                formatoDateServer.format(criterio.getHoy())+" - Servicio "+criterio.getServicio()+" - Empresa "+criterio.getEmpresa());
        if(dc==null){
            this.listaCorridas = null;
            return false;
        }
        this.listaCorridas = dc;
        this.strCorridaActualId=this.listaCorridas[0][6].toString();
        jThdConsultaCorrida = new JThdConsultaCorrida(sesionVenta, this.strCorridaActualId);
        jThdConsultaCorrida.start();
        consultarCorridaActual(true);
        return true;
    }
    
        private class ThreadPlantilla extends Thread{
    //private class ThreadPlantilla{
        boolean noSalir = true;
        int cont, asiento=1, fin;
        Vector vectorAnterior=null;
        Object[] vAsientosX=null;
        Object[] vAsientos=null;
        Vector bolVta=new Vector();
        Vector BolOcupados=new Vector();
        Vector BolReservados=new Vector();
        Vector BolReservadosNC=new Vector();
        boolean noSeRefrescoPlantilla;
        private String sa;
        private String sh;
        private long tiempoInactividad;
        private long planId;
        //private long cp;
        
        public ThreadPlantilla(){
            xAnterior = null;
            planId=-1;
            //pa = -1;
            sa = "";
            sh = "";
            //cp=0;
        }

        public void run(){
            while(noSalir){
                try{
                    if((ubiCompo == ucAsientos || ubiCompo == ucListCorridas ||
                        ubiCompo == ucTipoPasaje || ubiCompo == ucVtaActual)
                        && jTblCorridas.getRowCount()>0 && jTblCorridas.getSelectedRow()>-1){
                        try{
                            vAsientosX = null;
                            vAsientosX = jThdConsultaCorrida.getCorridaActual(false);
                            vAsientos = new Object[vAsientosX.length];
                            if(vAsientosX!=null){
                                corridaActual = vAsientosX;
                                for(fin=0; fin<vAsientosX.length; fin++)
                                    vAsientos[fin]=vAsientosX[fin].toString();
                                planId = Long.valueOf(vAsientos[5].toString());
                                bolVta.clear();
                                BolOcupados.clear();
                                BolReservados.clear();
                                BolReservadosNC.clear();

                                String edoAsiento="";
                                cont = 0;
                                asiento=1;
                                fin=sesionVenta.getCapacidadPlantilla(planId)+5;
                                for(cont=6; cont<=fin; cont++){
                                    edoAsiento=vAsientos[cont].toString();
                                    if(!edoAsiento.equals("D")){
                                        if(edoAsiento.equals("N") || edoAsiento.equals("V")) BolOcupados.addElement(asiento);
                                        if(edoAsiento.equals("R")) BolReservados.addElement(asiento);
                                        if(edoAsiento.equals("C")) BolReservadosNC.addElement(asiento);
                                        bolVta.addElement(asiento);
                                    }
                                    asiento++;
                                }

                                sh = vAsientos[0].toString();
                                if (bolVta.size() > 0){
                                    noSeRefrescoPlantilla=esMismoVector(bolVta, vectorAnterior);
                                    if(noSeRefrescoPlantilla)
                                        if(!sh.equals(sa)) noSeRefrescoPlantilla = false;

                                    if(!noSeRefrescoPlantilla){                                        
                                        JPnlbus.setPlantilla(sesionVenta.getComponentes(),
                                                         sesionVenta.getEncabezado(planId),
                                                         sesionVenta.getLineas(planId));
                                        JPnlbus.setVND(bolVta);
                                        JPnlbus.pintaPlantilla(planId);
                                        if(BolOcupados.size()!=0){ JPnlbus.setOcupados(BolOcupados); }
                                        if(BolReservados.size()!=0){ JPnlbus.setReservados(BolReservados); }
                                        if(BolReservadosNC.size()!=0){ JPnlbus.setReservadNC(BolReservadosNC); }
                                        totalRvnes=BolReservadosNC.size()+BolReservados.size();
                                        vectorAnterior=copiaVector(bolVta);
                                    }
                                    CalculaResumenX(vAsientos, bolVta.size(), 0, false, noSeRefrescoPlantilla, sesionVenta.getCapacidadPlantilla(planId));
                                }
                                else{
                                    //if(!sh.equals(sa)){
                                        JPnlbus.setPlantilla(sesionVenta.getComponentes(),
                                                             sesionVenta.getEncabezado(planId),
                                                             sesionVenta.getLineas(planId));
                                        JPnlbus.setVND(null);
                                        JPnlbus.pintaPlantilla(planId);
                                        //ResumenDefault();
                                        CalculaResumenZ(vAsientos, sesionVenta.getCapacidadPlantilla(planId));
                                        vectorAnterior=null;
                                        xAnterior = null;
                                    //}
                                }
                                sa=sh;
                            }
                        } catch ( NullPointerException npex ) {
                            vectorAnterior=null;
                            xAnterior = null;
                            //pa = -1;
                            sa = "";
                            sh = "";
                        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
                            vectorAnterior=null;
                            xAnterior = null;
                            //pa = -1;
                            sa = "";
                            sh = "";
                        }catch(java.util.NoSuchElementException ex){
                            vectorAnterior=null;
                            xAnterior = null;
                            //pa = -1;
                            sa = "";
                            sh = "";
                        }catch(Exception x){
                            vectorAnterior=null;
                            xAnterior = null;
                            //pa = -1;
                            sa = "";
                            sh = "";
                        }
                    }
                    if(sesionVenta.getTipoTransaccion().equals("L")) tiempoInactividad=249;
                    else tiempoInactividad=349;
                    Thread.sleep(tiempoInactividad);
                }catch(java.lang.ArrayIndexOutOfBoundsException aex1){
                    ;
                } catch ( InterruptedException iex ) {
                    ;
                } catch ( IllegalThreadStateException itsex ) {
                    ;
                }
            }
            System.out.println("hilo PLANTILLA finalizado.");
        }
    } // FIN THREAD PLANTILLA
    
    private boolean esMismoVector(Vector v1, Vector v2) {
        if(v2==null) return false;
        if(v1.size()!=v2.size()) return false;
        for(int i=0; i<v1.size(); i++)
            if(!v2.contains(v1.get(i).toString())) return false;

        return true;
    }

    private Vector copiaVector(Vector v1) {
        Vector v=new Vector();
        for(int i=0; i<v1.size(); i++)
            v.add(v1.get(i).toString());

        return v;
    }
    
    private boolean esMismoVectorR(Vector v1, Vector v2) {
        if(v2==null) return false;
        if(v1.size()!=v2.size()) return false;
        
        if(!v1.equals(v2)) return false;

        return true;
    }
   
    private void interfazColor(){
        setBackground(ColoresInterfaz.cFondoVentana);
        JPnlAutobus.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel3.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel4.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel5.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel6.setBackground(ColoresInterfaz.cFondoVentana);
        jScpCorridas.setBackground(ColoresInterfaz.cFondoVentana);
        jTblCorridas.setBackground(ColoresInterfaz.cFondoVentana);
        jPnlVtaActual.setBackground(ColoresInterfaz.cFondoVentana);
        jScpVtaActual.setBackground(ColoresInterfaz.cFondoVentana);
        jTblVtaActual.setBackground(ColoresInterfaz.cFondoVentana);
        jTblResumenVtaCorrida.setBackground(ColoresInterfaz.cFondoVentana);
        jLabel3.setBackground(ColoresInterfaz.cFondoVentana);
        
        jLblTotalVtaActual.setForeground(ColoresInterfaz.cTexto2);
        jLblTx.setForeground(ColoresInterfaz.cTexto2);
        jLblTarifaAdulto.setForeground(ColoresInterfaz.cTexto2);
        
        jPanel1.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        jLblBarraEstado.setForeground(ColoresInterfaz.cTexto1);
        jPanel2.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        
        jLblNombreUsuario.setForeground(ColoresInterfaz.cTexto1);
        jLblFolioActual.setForeground(ColoresInterfaz.cTexto1);
        jLblNombreEmpresa.setForeground(ColoresInterfaz.cTexto1);
        jLabel3.setForeground(ColoresInterfaz.cTexto2);
        jLblCiudadVenta.setForeground(ColoresInterfaz.cTexto1);
        jLblEmpresa.setForeground(ColoresInterfaz.cTexto1);
        jLabel8.setForeground(ColoresInterfaz.cTexto1);
        jLabel9.setForeground(ColoresInterfaz.cTexto1);
        jLabel11.setForeground(ColoresInterfaz.cTexto1);
        jLabel1.setForeground(ColoresInterfaz.cTexto1);
        jLabel10.setForeground(ColoresInterfaz.cTexto1);
        jLblMonto.setForeground(ColoresInterfaz.cTexto1);
        jLblMonto.setFont(ColoresInterfaz.fuente4);
        jTxtMonto.setFont(ColoresInterfaz.fuente4);
        
        jLblNombreUsuario.setFont(ColoresInterfaz.fuente6);
        jLblFolioActual.setFont(ColoresInterfaz.fuente6);
        jLblNombreEmpresa.setFont(ColoresInterfaz.fuente6);
        jLabel3.setFont(ColoresInterfaz.fuente6);
        jLblCiudadVenta.setFont(ColoresInterfaz.fuente1);
        jCboCiudadVenta.setFont(ColoresInterfaz.fuente1);
        jLblEmpresa.setFont(ColoresInterfaz.fuente1);
        jCboEmpresas.setFont(ColoresInterfaz.fuente1);
        jLabel8.setFont(ColoresInterfaz.fuente1);
        jCboOrigen.setFont(ColoresInterfaz.fuente1);
        jLabel9.setFont(ColoresInterfaz.fuente1);
        jCboDestino.setFont(ColoresInterfaz.fuente1);
        jLabel11.setFont(ColoresInterfaz.fuente1);
        jTxtFecha.setFont(ColoresInterfaz.fuente1);
        jLabel1.setFont(ColoresInterfaz.fuente1);
        jTxtHora.setFont(ColoresInterfaz.fuente1);
        jLabel10.setFont(ColoresInterfaz.fuente1);
        jCboServicio.setFont(ColoresInterfaz.fuente1);
        jTblResumenVtaCorrida.setFont(ColoresInterfaz.fuente1);
        jLblTarifaAdulto.setFont(ColoresInterfaz.fuente2);
        jLblTx.setFont(ColoresInterfaz.fuente2);
        jTblCorridas.setFont(ColoresInterfaz.fuente5);
        
        jLabel6.setFont(ColoresInterfaz.fuente2);
        jTxtAsientos.setFont(ColoresInterfaz.fuente2);
        jLabel7.setFont(ColoresInterfaz.fuente2);
        jTxtTipoPasaje.setFont(ColoresInterfaz.fuente2);
        
        jTblBO.setFont(ColoresInterfaz.fuente1);
        jTblCH.setFont(ColoresInterfaz.fuente1);
        jTblVtaActual.setFont(ColoresInterfaz.fuente2);
        jLblTotalVtaActual.setFont(ColoresInterfaz.fuente2);
        jLblBarraEstado.setFont(ColoresInterfaz.fuente2);
    }
    
    private void controlVentana(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } 
                                break;
        }
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
    
    void enabledComponents(JComponent jc){
        jc.setEnabled(false);
        
        if(jc.getName()!=null && jc.getName().equals("jtblListadoCorridas")){
            jc.setForeground(Color.WHITE);
            jc.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        }
        else{
            jc.setForeground(Color.BLACK);
            jc.setBackground(ARENA);
        }
    }
    
    void abledComponents(JComponent jc){
        jc.setEnabled(true);
        
        if(jc.getName()!=null && jc.getName().equals("jtblListadoCorridas")){
            jc.setForeground(Color.WHITE);
            jc.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        }
        else{
            jc.setForeground(Color.BLACK);
            jc.setBackground(ARENA);
        }
    }

    void enabledComponentsCriterios() {
        enabledComponents(jCboServicio);
        enabledComponents(jCboCiudadVenta);
        enabledComponents(jCboOrigen);
        enabledComponents(jCboDestino);
        enabledComponents(jCboEmpresas);
        enabledComponents(jTxtFecha);
        enabledComponents(jTxtHora);
    }
    
    void abledComponentsCriterios() {
        abledComponents(jCboServicio);
        abledComponents(jCboCiudadVenta);
        abledComponents(jCboOrigen);
        abledComponents(jCboDestino);
        abledComponents(jCboEmpresas);
        abledComponents(jTxtFecha);
        abledComponents(jTxtHora);
    }
    
    void controlMouseCriterio(){
        /*switch(this.ubiCrit){
            case uCtCiudadOrigen: jCboCiudadVenta.requestFocusInWindow(); break;
            case uCtEmpresa: jCboEmpresas.requestFocusInWindow(); break;
            case uCtOrigen: jCboOrigen.requestFocusInWindow(); break;
            case uCtDestino: jCboDestino.requestFocusInWindow(); break;
            case uCtFecha: jTxtFecha.requestFocusInWindow(); break;
            case uCtHora: jTxtHora.requestFocusInWindow(); break;
            case uCtServicio: jCboServicio.requestFocusInWindow(); break;
        }*/
    }

    private void inicializaCorrida() {
        corridaActual = new Object[64];
        corridaActual[0]="0";
        corridaActual[1]="0";
        corridaActual[2]="0";
        corridaActual[3]="0";
        corridaActual[4]="0";
        corridaActual[5]="0";
        for(int h=6; h<61; h++)
           this.corridaActual[h]="N";
        corridaActual[61]="S";
        corridaActual[62]="0-0-0-0";
        corridaActual[63]="";
    }

    private boolean validaImporteBoletosEnCero() {
        boolean r=true;
        int i;
        if(BoletosRedondos!=null){
           /* for(i=0; i<BoletosRedondos.length; i++)
                if(Double.valueOf(BoletosRedondos[i][5].toString())<=0){
                    r=false;
                    break;
                }*/
        }
        else{
            for(i=0; i<Boletos.length; i++)
                
                if(Double.valueOf(Boletos[i][5].toString())<=0 && !sesionVenta.isRutaConPromocion()){
                    r=false;
                    break;
                }
        }
        
        if(!r)
            DialogoAceptar.mostrarDialogo("Boleto con importe cero",
                "<html>Posibles causas: Tarifa no configurada o boleto redondo<br>con misma tarifa de boleto sencillo.</html>", Color.RED);
        return r;
    }

    private void coloresEmpresas() {
        String p_valor;
        coloresEmpresasOfertantes = new Object[sesionVenta.getUserCon().getEmpresasOfertantes().length][3];
        for(int i=0; i<sesionVenta.getUserCon().getEmpresasOfertantes().length; i++){
            coloresEmpresasOfertantes[i][0] = sesionVenta.getUserCon().getEmpresasOfertantes()[i][1];
            try{
                p_valor=sesionVenta.getValorParametro("P_COLORFONDOCORRIDA"+sesionVenta.getUserCon().getEmpresasOfertantes()[i][0],-1);
                coloresEmpresasOfertantes[i][1] = (p_valor==null ? Color.BLACK : Color.decode(p_valor));
                p_valor=sesionVenta.getValorParametro("P_COLORTEXTOCORRIDA"+sesionVenta.getUserCon().getEmpresasOfertantes()[i][0],-1);
                coloresEmpresasOfertantes[i][2] = (p_valor==null ? Color.WHITE : Color.decode(p_valor));
               }catch(NumberFormatException nfex){
                coloresEmpresasOfertantes[i][1]=JClsColoresInterfaz.cFondoVentana;
                coloresEmpresasOfertantes[i][2]=Color.WHITE;
            }
        }
    }
    
    private void agregarPanelRecoleccion() {
        if(!ValidaSesionVenta()) return;
        if(transaccion.equals(txCHORARIO) || transaccion.equals(txVENTA_BA) || transaccion.equals(txCANJE_BA)) return;
        if(sesionVenta.EFECTIVO_CAJA==0){
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Aun no existe venta por recolectar!", "Intente mas tarde.", Color.RED);
            return;
        }
        if(!sesionVenta.ValidaFuncionUsuario("5002")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5002", "Recolectar Efectivo");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return;
        }
        
        jPanel4.setVisible(false);
        jPnlRecoleccion.setVisible(true);
        jLblBarraEstado.setText(mensajes.getMensajeVta(22));
        bRecol = true;
        jTxtMonto.setText("");
        jTxtMonto.requestFocusInWindow();
        
        this.jLblTx.setText("*RECOLECCION*");
        int tipoRecol=2;
        if(sesionVenta.EFECTIVO_CAJA<this.sesionVenta.getUserCon().getAvisoRecoleccion()) tipoRecol=1;
        bRecol=true;
        System.out.println("Antes de recoleccion");
        this.setCtdDispCaja(sesionVenta.EFECTIVO_CAJA);
        this.setMontoReco(sesionVenta.getUserCon().getAvisoRecoleccion());
        if(this.validarPrimeraRecoleccionVTA()){
            this.HabilitarPRECOL();
        }
        else{
            if(tipoRecol==1){
                this.desHabilitarRLIBRE();
            }
            else{
                this.insertaRecoleccionAuto();
                this.desHabilitarRAUTO();
            }
        }
        txTipoRecol = tipoRecol;
    }

    // metodos de recolectar
    public void setCtdDispCaja(double pActCtdRecol){ this.ctdDispCaja=pActCtdRecol; }
    public void setMontoReco(double pMontoReco){
        this.dblMontoReco=pMontoReco;
    }
    public boolean validarPrimeraRecoleccionVTA() {
        datosRecoleccion = new Object[3];
        if(sesionVenta.esPrimeraRecoleccion()){
            datosRecoleccion[0] = "EFE";
            datosRecoleccion[1] = 1;
            datosRecoleccion[2] = sesionVenta.getUserCon().getFondoMax();
            jTxtMonto.setText(datosRecoleccion[2].toString());
            primeraReco = true;
        }else
            primeraReco = false;
        System.out.println("primera recoleccion: "+(primeraReco?"SI":"NO"));
        return primeraReco;
    }
    public void HabilitarPRECOL(){
        this.jTxtMonto.setEditable(false);
        jTxtMonto.requestFocusInWindow();
    }

    public void desHabilitarRLIBRE(){
        this.jTxtMonto.setEditable(true);
        this.jTxtMonto.requestFocusInWindow();
    }
    public void insertaRecoleccionAuto(){
        datosRecoleccion = new Object[3];
        datosRecoleccion[0] = "EFE";
        datosRecoleccion[1] = 1;
        datosRecoleccion[2] = this.dblMontoReco;
        jTxtMonto.setText(datosRecoleccion[2].toString());
        jTxtMonto.requestFocusInWindow();
    }
    public void desHabilitarRAUTO(){
        this.jTxtMonto.setEditable(false);
        jTxtMonto.requestFocusInWindow();
    }
    public int getResultado(){ return this.iResultado; }
   
    private void Recolectar(){
        if(jTxtMonto.getText().equals("") || jTxtMonto.getText().equals("Monto a Recolectar") || (Double.valueOf(jTxtMonto.getText())<=0 && jTxtMonto.isEditable()) || Double.valueOf(jTxtMonto.getText())>sesionVenta.EFECTIVO_CAJA){
            /*if((!jTxtMonto.getText().equals("") && !jTxtMonto.getText().equals("Monto a Recolectar")) && ((txTipoRecol!=1 && Double.valueOf(jTxtMonto.getText())>sesionVenta.EFECTIVO_CAJA) || !bRecol)){
                System.out.println("Doble ENTER detenido en Recoleccion Auto.");
                return;
            }
            else{
                if(primeraReco && !bRecol){
                    System.out.println("Doble ENTER detenido en Primera Recoleccion.");
                    return;
                }
            }*/
            DialogoAceptar.mostrarDialogo("¡Cantidad de recoleccion invalida!","Ingrese cantidad correcta.",Color.RED);
            this.jTxtMonto.selectAll();
            this.jTxtMonto.requestFocusInWindow();
            bRecol=true;
            return;
        }
        if(txTipoRecol==1){
            if(!bRecol){
                System.out.println("Doble ENTER detenido en Recoleccion personalizada");
                bRecol=true;
                return;
            }
            System.out.println("Recoleccion personalizada "+jTxtMonto.getText()+" --- "+sesionVenta.EFECTIVO_CAJA);
            double mtoR = Double.valueOf(jTxtMonto.getText());
            if(mtoR>sesionVenta.EFECTIVO_CAJA){
                DialogoAceptar.mostrarDialogo("¡Cantidad de recoleccion invalida!","Ingrese cantidad correcta.",Color.RED);
                this.jTxtMonto.selectAll();
                this.jTxtMonto.requestFocusInWindow();
                bRecol=true;
                return;
            }
            datosRecoleccion[0] = "EFE";
            datosRecoleccion[1] = 1;
            datosRecoleccion[2] = jTxtMonto.getText();
        }
        montoReco=Float.valueOf(datosRecoleccion[2].toString());
        datosTablaReco = new Object[1][3];
        datosTablaReco[0][0]=datosRecoleccion[0];
        datosTablaReco[0][1]=datosRecoleccion[1];
        datosTablaReco[0][2]=datosRecoleccion[2];

        long usuarioId;
        if(sesionVenta.getAplicaDatosAuditoria()!=null)
            usuarioId = sesionVenta.getAplicaDatosAuditoria().getUsuarioId();
        else usuarioId = sesionVenta.getUserCon().getUsuarioId();
        
        System.out.println("Antes de insertar Recoleccion en BD: --->"+bRecol);
        bRecol = false;
        //if(sesionVenta.insertarDatosRecoleccion(datosTablaReco,1,usuarioId)){
        if(sesionVenta.insertarDatosRecoleccion(datosTablaReco,1,sesionVenta.getUserCon().getAutorizadoPorIdUsuario())){
            sesionVenta.EFECTIVO_CAJA-=montoReco;
            System.out.println("despues de insertar Recoleccion en BD y decrementar EFECTIVO_CAJA");
            System.out.println("Antes de mostrar dialogo: recoleccion correcta");
            DialogoAceptar.mostrarDialogo("TMS Venta.","Recolección realizada correctamente.", Color.BLUE);
            bRecol = true;
            System.out.println("Despues de mostrar dialogo: recoleccion correcta: --->"+bRecol);

            if(!sesionVenta.AuditarFuncion())
                DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Recolectar Efectivo!","Presione ENTER para continuar.",Color.RED);
            
            limpiarResReco();
            iResultado=1;
            if(primeraReco){
                bRecol = true;
                System.out.println("Primera Recoleccion");
                if(txTipoRecol==2){
                    this.primeraReco=false;
                    System.out.println("Recoleccion auto EFE: "+sesionVenta.EFECTIVO_CAJA);
                    jTxtMonto.setEditable(false); // auto
                    this.insertaRecoleccionAuto();
                    if(Double.valueOf(jTxtMonto.getText())>sesionVenta.EFECTIVO_CAJA){
                        txTipoRecol=1;
                        jTxtMonto.setEditable(true);
                        jTxtMonto.setText("");
                        jTxtMonto.requestFocusInWindow();
                        bRecol=true;
                        return;
                    }
                }
                if(txTipoRecol==1){
                    this.primeraReco=false;
                    jTxtMonto.setEditable(true);
                    jTxtMonto.setText("Monto a Recolectar");
                    jTxtMonto.selectAll();
                    jTxtMonto.requestFocusInWindow();
                    bRecol=true;
                    return;
                }
            }
            else{
                System.out.println("Se cierra recoleccion.");
                iResultado=1;
                txTipoRecol=1;
                jTxtMonto.setEditable(true);
                jTxtMonto.setText("");
                jTxtMonto.requestFocusInWindow();
                bRecol=true;
                return;
            }
        }else{
            DialogoAceptar.mostrarDialogo("¡La recolección no pudo guardarse!","Contacte al administrador del sistema.",Color.RED);
            limpiarResReco();
            iResultado=2;
        }
    }
    private void limpiarDatosReco(){
        jTxtMonto.setText("");
        montoReco = 0;
        jTxtMonto.requestFocusInWindow();
    }
    
    private void limpiarResReco(){
        limpiarDatosReco();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPnlAutobus;
    private javax.swing.JComboBox jCboCiudadVenta;
    private javax.swing.JComboBox jCboDestino;
    private javax.swing.JComboBox jCboEmpresas;
    private javax.swing.JComboBox jCboOrigen;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblCiudadVenta;
    private javax.swing.JLabel jLblEmpresa;
    private javax.swing.JLabel jLblFolioActual;
    private javax.swing.JLabel jLblMonto;
    private javax.swing.JLabel jLblMultiCast;
    private javax.swing.JLabel jLblNombreEmpresa;
    private javax.swing.JLabel jLblNombreUsuario;
    private javax.swing.JLabel jLblTarifaAdulto;
    private javax.swing.JLabel jLblTotalVtaActual;
    private javax.swing.JLabel jLblTx;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPnlCHorario;
    private javax.swing.JPanel jPnlRecoleccion;
    private javax.swing.JPanel jPnlVtaActual;
    private javax.swing.JScrollPane jScpBO;
    private javax.swing.JScrollPane jScpCH;
    private javax.swing.JScrollPane jScpCorridas;
    private javax.swing.JScrollPane jScpResumenVtaCorrida;
    private javax.swing.JScrollPane jScpVtaActual;
    private javax.swing.JTable jTblBO;
    private javax.swing.JTable jTblCH;
    private javax.swing.JTable jTblCorridas;
    private javax.swing.JTable jTblResumenVtaCorrida;
    private javax.swing.JTable jTblVtaActual;
    private javax.swing.JTextField jTxtAsientos;
    private javax.swing.JTextField jTxtFecha;
    private javax.swing.JTextField jTxtHora;
    private javax.swing.JTextField jTxtMonto;
    private paquete.JTxtFieldTipoPasaje jTxtTipoPasaje;
    private tms_TextFields.JTextTextField jtxt_personaAutorizada;
    // End of variables declaration//GEN-END:variables
    private final String txVENTA="VT";
    private final String txCHORARIO="HO";
    private final String txRESERVACION="RV";
    private final String txVENTA_RESERVACION="VR";
    private final String txVENTA_BA="VA";
    private final String txCANJE_BA="AC";
    private final String txVENTA_BR="Vr";
    private final String txVENTA_RF="FT";
    private final String txCHORARIO_RF="FO";
    private final String txCANJE_BA_RF="FC";
    
    private final int ucEjecCons=0;
    private final int ucListCorridas=1;
    private final int ucAsientos=2;
    private final int ucTipoPasaje=3;
    private final int ucVtaActual=4;
    private final int ucCobro=5;
    private final int ucReservacion=5;
    
    private int fila=0;
    private Object[] encCorridas = {"Corrida", "Fecha", "Hora", "Servicio", "Origen", "Destino"};
    private DefaultTableModel xCorridas = new DefaultTableModel(null, encCorridas){
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    private Object[] encResumenVtaCorrida = {"Boleto", "A", "M", "S", "E", "P", "C", "Ocup"};
    private DefaultTableModel xResumenVtaCorrida = new DefaultTableModel(null, encResumenVtaCorrida){
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    private Object[] encVtaActual = {"Corrida-Hora", "#", "T", "Nombre Pasajero", "Origen-Destino", "Costo","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","T. Vta","Nombre Autorizado"};
    private DefaultTableModel xVtaActual = new DefaultTableModel(null, encVtaActual){
        public boolean isCellEditable(int row, int col) {
            if(col==3) return true;    
            return false;
        }
    };
    private SesionVenta sesionVenta = null;   
    private Object[][] objResumenVtaCorrida;
    private Object[][] listaCorridas;
    private Object[] corridaActual;
    private Object[][] Boletos;
    private Object[][] BoletosRedondos;
    private SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoDateServer = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoDateServer2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String hoyEs;
    private String MesAnho;
    private String Anho;
    
    public String transaccion=txVENTA;
    public String transaccionAux=txVENTA;
    private long ServicioId;
    private JPnl_BusX JPnlbus;
    private Vector BoletoAVender=null;
    private Vector TiposAsientos=null;
    private Vector xAnterior = null;
    private int totalRvnes=0;
    private CriteriosBusqueda criterio=null;
    private long corridaId;
    private long plantillaId;
    private double TarifaServicio;
    private String compoTarifa;
    private String tipoTransaccion = "L";
    private Object ClienteId = null;
    private JDlgCobro jDlgCobro = null;
    private JDlgCancelaMultiBoleto jIFCancelar = null;
    private JDlgCHorarioMultiBoleto jIFCHorario = null;
    private JDlgCanjeBAMultiBoleto jIFCanjeBA = null;
    private JDlgCanjeBolRef jIFCanjeBF = null;
    private JDlgControlRvn jIFControlRvn = null;
    private Object[] encBO2 = {"Corrida", "Fecha", "#", "Tipo", "Nombre", "Origen", "Destino", "Importe"};
    private Object[] encCH2 = {"Corrida", "Fecha", "#", "Tipo", "Nombre", "Origen", "Destino", "Importe"};
    private Object[][] encBO = null;
    private Object[][] encBF = null;
    private Object[][] encBOx = null;
    private Object[][] encCH = null;
    private DefaultTableModel xBO = new DefaultTableModel(null,encBO2){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
    private DefaultTableModel xCH = new DefaultTableModel(null,encCH2){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    private JDlgSiNoCancelar DialogoSiNoCancelar;
    private Color colorDialogoActivo = new Color(0,83,255);
    private JTextTextFieldMaxCharAlfa jtxtNombre = new JTextTextFieldMaxCharAlfa(30);
    private Mensajes mensajes = new Mensajes();
    private String MensajeAuxiliar="";
    private DefaultComboBoxModel cbmEmpresas;
    private DefaultComboBoxModel cbmServicio;
    private DefaultComboBoxModel cbmOrigen;
    private DefaultComboBoxModel cbmDestino;
    private int indiceServicio=0;
    private String ServicioAnterior;
    private String COAnt;
    private int capacidadAutobus;
    private String nombreEmpresa;
    private boolean inicioGral;
    private boolean ambienteBoletosRedondos=false;
    private double dblTarifaSencillo = 0;
    private double dblTarifaRedondo = 0;
    private String SnAsiento;
    private int ubiCompo=0; // 0-criterios consulta 1-listado corridas 2-asientos 3-tipo pasaje 4-vta actual
    private double[] jugandoImporteVenta;
    private String[] jugandoImporteVentaPermiso;
    private Object[] objEmp;
    private Color ARENA = new Color(238, 238, 238);
    private Color xorColorFondo = new Color(156, 179, 201);
    private static final long unaHora = 28800000;
    private ThreadPlantilla threadPlantilla=null;
    private JThdConsultaCorrida jThdConsultaCorrida=null;
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    private KeyEvent eventoTeclado;
    private int ubiCrit;
    private static final int uCtCiudadOrigen = 0;
    private static final int uCtEmpresa = 1;
    private static final int uCtOrigen = 2;
    private static final int uCtDestino = 3;
    private static final int uCtFecha = 4;
    private static final int uCtHora = 5;
    private static final int uCtServicio = 6;
    private String strCorridaActualId;   
    private long time_now=0;
    private double tarifaAdultoCHBA;
    private Object[][] coloresEmpresasOfertantes;
    private int rndFila;
    private boolean excluyeTeclasVtaActual;
    private boolean excluyeTeclasCriterios;
    private RelojVisualVta jlbl_reloj=null;
    private Vector vTP=null;
    private boolean vengoDeCobro=false;
    private boolean vengoDePantallaCobro=false;
    
    // campos de recolectar
    private double ctdDispCaja=0;
    private double dblMontoReco=0;
    private Object[] datosRecoleccion;
    private boolean primeraReco;
    private int iResultado=0;
    private int txTipoRecol=0; // 0-RECOLECCION ANTES DE VENTA, 1-RECOLECCIONES EN VENTA (LIBRE), 2-(RECOL. AUTO)
    private boolean bRecol;
    private float montoReco;
    private Object[][] datosTablaReco;
    private boolean nombreAutorizado = false;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");   
    private SimpleDateFormat formatoDia = new SimpleDateFormat("EEEEE");
    private String textoBarraF = "";

    private String salidaImpresionTVRef;
    private double montoPromocionVA;
    private char tipoPasajeroVA='_';

    private boolean exiteLelatadHO = false;
    private boolean redencionSOC = false;
    //private boolean bolRedCer = false;
    private String ciudadOrigenRedCerr;
    private String origenRedCerr;
    private String destinoRedCerr;
    private String serviocioRedCerr;
    private String corridaRedCerr;
    private String asientosRedCerrIda;
    private String asientosRedCerrRegreso;
    private String corridaIdRedCerrIda;
    //private String dblinkRedIda;
    private String dblinkRedRegreso;
    private long rutaIdRedondoIda;

 
    /*
     private void agregarTiempoAire(){
         System.out.println("Agrega Tiempo Aire");
         if(!ValidaSesionVenta()) return;
         
         Vector vdatos= new Vector();
//         vdatos.addElement( sesionVenta.getUserCon().getUsuarioNum()+"");  // noUsuario
//         vdatos.addElement( sesionVenta.getUserCon().getUsuarioNom()+"");  // nombreUsuario
//         vdatos.addElement( sesionVenta.getUserCon().getUsuarioId()+"");  //usuarioId
//         vdatos.addElement( sesionVenta.getUserCon().getCajaId()+"");      // caja
//         vdatos.addElement( sesionVenta.getUserCon().getCorteId()+"");   // corteId 
//         vdatos.addElement( sesionVenta.getUserCon().getTerminalNombre()+""); // ciudadVenta   
//         vdatos.addElement("TMS Móvil");   //canalVenta
//         
        String taeCompany = "";  //8002";
        String taeUsuario = "";  //EstrellaRoja";
        String taePass    = ""; //123456";
        String impreconfirm = "N";
        String impresora ="";
         
        System.out.println(" Buscando datos de configuracion para la caja "+sesionVenta.getUserCon().getCajaId());
        
        Vector VPara_TAE=sesionVenta.GetPar_TAE(sesionVenta.getUserCon().getCajaId()+"", 
                                                sesionVenta.getUserCon().getTerminalId()+"");
         System.out.println("VPara_TAE ------------->     "+VPara_TAE);
        if (VPara_TAE != null && VPara_TAE.size() > 0)
        {
           // taeCompany = ((Vector)VPara_TAE.elementAt(0)).elementAt(0).toString();
           // taePass    = ((Vector)VPara_TAE.elementAt(2)).elementAt(0).toString();
           // taeUsuario = ((Vector)VPara_TAE.elementAt(1)).elementAt(0).toString();
           
           taeCompany = VPara_TAE.elementAt(0).toString();
           taePass    = VPara_TAE.elementAt(1).toString();
           taeUsuario = VPara_TAE.elementAt(2).toString();
           
           impreconfirm = ( VPara_TAE.elementAt(3) != null?  VPara_TAE.elementAt(3).toString() : "N");
           impresora   =  ( VPara_TAE.elementAt(4) != null?  VPara_TAE.elementAt(4).toString() :  "");
           
            System.out.println(" taeCompany  "+ taeCompany);
            System.out.println(" taeUsuario  "+ taeUsuario);
            System.out.println(" taePass  "+ taePass);
           
            System.out.println(" impreconfirm  "+ impreconfirm);
            System.out.println(" impresora  "+ impresora);
      
           
        }
        else
        {
           DialogoAceptar.mostrarDialogo("TMS-TAE","No se encuentran los parámetros de tiempo Aire \n para la caja  "+sesionVenta.getUserCon().getCaja()+" \nContacte al administrador del sistema.",Color.RED);
        
           return;
        }  
        String noUsuario      = sesionVenta.getUserCon().getUsuarioNum()+"";  // noUsuario
        String nombreUsuario  = sesionVenta.getUserCon().getUsuarioNom()+"";  // nombreUsuario;
        String usuarioId      = sesionVenta.getUserCon().getUsuarioId()+"";  //usuarioId
        String caja           = sesionVenta.getUserCon().getCaja();      // caja
        String corteId        = sesionVenta.getUserCon().getCorteId()+"";   // corteId 
        String ciudadVenta    = sesionVenta.getUserCon().getTerminalNombre()+""; // ciudadVenta   
        String canalVenta     = "TMS Movil";
        String TipoPago        = "EFE";
        System.out.println("agregarTiempoAire Puerto a imprimir ---  >   "+ sesionVenta.getImpresoraVoucher(this.nombreEmpresa));
        
        
        System.out.println("taeCompany  "+taeCompany);
        System.out.println("taeUsuario  "+taeUsuario);
        System.out.println("taePass  "+taePass);
        
        System.out.println("noUsuario  "+noUsuario);
        System.out.println("nombreUsuario  "+nombreUsuario);
        System.out.println("usuarioId  "+usuarioId);
        System.out.println("caja  "+caja);
        System.out.println("corteId  "+corteId);
        System.out.println("ciudadVenta  "+ciudadVenta);
        System.out.println("canalVenta  "+canalVenta);
        
        
          ventatae.VentaTaePrincipal jifVentaTae = new ventatae.VentaTaePrincipal(
                                                       taeUsuario,taeCompany,  taePass,
                                                       noUsuario, nombreUsuario, usuarioId,
                                                       caja, corteId, ciudadVenta, canalVenta,
                                                       impresora, //sesionVenta.getImpresoraVoucher(this.nombreEmpresa),
                                                       TipoPago,
                                                       impreconfirm);
        
        // JDlgAgregaTiempoAire dlg = new JDlgAgregaTiempoAire(this, true , vdatos );
        // dlg.setVisible(true); 
         
     }
*/     


    private void agregarTiempoAire(){
         System.out.println("Agrega Tiempo Aire");
         if(!ValidaSesionVenta()) return;
         
         Vector vdatos= new Vector();
         /*vdatos.addElement( sesionVenta.getUserCon().getUsuarioNum()+"");  // noUsuario
         vdatos.addElement( sesionVenta.getUserCon().getUsuarioNom()+"");  // nombreUsuario
         vdatos.addElement( sesionVenta.getUserCon().getUsuarioId()+"");  //usuarioId
         vdatos.addElement( sesionVenta.getUserCon().getCajaId()+"");      // caja
         vdatos.addElement( sesionVenta.getUserCon().getCorteId()+"");   // corteId 
         vdatos.addElement( sesionVenta.getUserCon().getTerminalNombre()+""); // ciudadVenta   
         vdatos.addElement("TMS Móvil");   //canalVenta
          */
        String taeCompany = "";  //8002";
        String taeUsuario = "";  //EstrellaRoja";
        String taePass    = ""; //123456";
        String impreconfirm = "N";
        String impresora ="";
         
        System.out.println(" Buscando datos de configuracion para TAE:  ");//+sesionVenta.getUserCon().getTerminalId());//.getCajaId());
        System.out.println("EMPRESA_ID:  -1");
        System.out.println("SERVICIO_ID:  -1");
        System.out.println("TERMINAL_ID:  "+sesionVenta.getUserCon().getTerminalId());
        System.out.println("RUTA_ID:  -1");   
        System.out.println("CAJA_ID:  "+sesionVenta.getUserCon().getCajaId());
        
        //EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID
        Vector VPara_TAE=sesionVenta.GetPar_TAE(-1,-1 
                                                ,sesionVenta.getUserCon().getTerminalId()
                                                ,-1
                                                ,sesionVenta.getUserCon().getCajaId());
         System.out.println("VPara_TAE ------------->     "+VPara_TAE);
        if (VPara_TAE != null && VPara_TAE.size() > 0)
        {
           // taeCompany = ((Vector)VPara_TAE.elementAt(0)).elementAt(0).toString();
           // taePass    = ((Vector)VPara_TAE.elementAt(2)).elementAt(0).toString();
           // taeUsuario = ((Vector)VPara_TAE.elementAt(1)).elementAt(0).toString();
           
           taeCompany = VPara_TAE.elementAt(0).toString().trim();
           taePass    = VPara_TAE.elementAt(1).toString().trim();
           taeUsuario = VPara_TAE.elementAt(2).toString().trim();
           
           impreconfirm = ( VPara_TAE.elementAt(3) != null?  VPara_TAE.elementAt(3).toString().trim() : "N");
           impresora   =  ( VPara_TAE.elementAt(4) != null?  VPara_TAE.elementAt(4).toString().trim() :  "");
           
            System.out.println(" taeCompany: "+ taeCompany);
            System.out.println(" taeUsuario: "+ taeUsuario);
            System.out.println(" taePass: "+ taePass);
           
            System.out.println(" impreconfirm  "+ impreconfirm);
            System.out.println(" impresora  "+ impresora);
      
           
        }
        else
        {
           DialogoAceptar.mostrarDialogo("TMS-TAE","<HTML>No se encuentran configurados los parámetros de tiempo Aire <br> Contacte al administrador del sistema.</HTML>",Color.RED);
        
           return;
        }  
        String noUsuario      = sesionVenta.getUserCon().getUsuarioNum()+"";  // noUsuario
        String nombreUsuario  = sesionVenta.getUserCon().getUsuarioNom()+"";  // nombreUsuario;
        String usuarioId      = sesionVenta.getUserCon().getUsuarioId()+"";  //usuarioId
        String caja           = sesionVenta.getUserCon().getCaja();      // caja
        String corteId        = sesionVenta.getUserCon().getCorteId()+"";   // corteId 
        String ciudadVenta    = sesionVenta.getUserCon().getTerminalNombre()+""; // ciudadVenta   
        String canalVenta     = "TMS Movil";
        String TipoPago        = "EFE";
        System.out.println("agregarTiempoAire Puerto a imprimir ---  >   "+ sesionVenta.getImpresoraVoucher(this.nombreEmpresa));
        
        
        System.out.println("taeCompany  "+taeCompany);
        System.out.println("taeUsuario  "+taeUsuario);
        System.out.println("taePass  "+taePass);
        
        System.out.println("noUsuario  "+noUsuario);
        System.out.println("nombreUsuario  "+nombreUsuario);
        System.out.println("usuarioId  "+usuarioId);
        System.out.println("caja  "+caja);
        System.out.println("corteId  "+corteId);
        System.out.println("ciudadVenta  "+ciudadVenta);
        System.out.println("canalVenta  "+canalVenta);
        
        
          ventatae.VentaTaePrincipal jifVentaTae = new ventatae.VentaTaePrincipal(
                                                       taeUsuario,taeCompany,  taePass,
                                                       noUsuario, nombreUsuario, usuarioId,
                                                       caja, corteId, ciudadVenta, canalVenta,
                                                       impresora, //sesionVenta.getImpresoraVoucher(this.nombreEmpresa),
                                                       TipoPago,
                                                       impreconfirm);
        
        // JDlgAgregaTiempoAire dlg = new JDlgAgregaTiempoAire(this, true , vdatos );
        // dlg.setVisible(true); 
         
     }
     
        
     private void ventaProductor()
    {
        JdlgVentaProductosER dialog = new JdlgVentaProductosER(new javax.swing.JFrame(), true,datosIniciales,sesionVenta.getUserCon().getTerminalNombre(),sesionVenta.getUserCon().getCaja(),sesionVenta.getUserCon().getTerminalId(),sesionVenta.getUserCon().getCorteId());
        dialog.centrarDialogo();
        dialog.setVisible(true);
     }
}

