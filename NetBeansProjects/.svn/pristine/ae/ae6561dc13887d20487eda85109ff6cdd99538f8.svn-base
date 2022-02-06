/*
 * jdlg_Reimpresion.java
 *
 * Created on 24 de octubre de 2007, 03:00 AM
 */

package tmspuertas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tmspuertas.entidad.TmsAutobusPlantillasEncTbl;
import tmspuertas.entidad.TmsCorridasVentaTbl;
import tmspuertas.entidad.TmsServiciosTbl;
import tmspuertas.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_Reimpresion extends javax.swing.JDialog {
    
    /** Creates new form jdlg_Reimpresion */
    public jdlg_Reimpresion( TmsPuertasManagedBean bus, Vector cod,Vector val,long pusuarioId,long psesionId,List<TmsAutobusPlantillasEncTbl> pencabezados,Vector pltiposPasajero,boolean premoto, String pDBLink, String ppuerto, PrintService pimpresora, Vector pempresas, Vector pparamempre, String pipAS, int pportAS){
        this.setTitle("Reimpresion de Tarjetas de Viaje");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        initComponents();
        this.busquedas = bus;
        this.codigos = cod;
        this.puerto = ppuerto;
        this.impresora = pimpresora;
        this.empresas = pempresas;
        this.paramEmpresas = pparamempre;
        this.valores = val;
        this.usuarioId = pusuarioId;
        this.sesionId  = pusuarioId;
        this.encabezados = pencabezados;
        this.ltiposPasajero = pltiposPasajero;
        this.remoto = premoto;
        this.DBLink = pDBLink;
        this.ipAS = pipAS;
        this.portAS = pportAS;
        jtbl_tarjetas.setModel(modeloTarjetas);
        modeloTarjetas.setDataVector(null,encabezadoTarjetas);
        resizeColumnasCorridas();
        jtxt_clave.setFocusTraversalKeysEnabled(false);
        jtxt_autobus.setFocusTraversalKeysEnabled(false);
        jcmb_servicios.setFocusTraversalKeysEnabled(false);
        jtxt_fechadesde.setFocusTraversalKeysEnabled(false);
        jtxt_fechahasta.setFocusTraversalKeysEnabled(false);
        jtxt_horadesde.setFocusTraversalKeysEnabled(false);
        jtxt_horahasta.setFocusTraversalKeysEnabled(false);
        jtbl_tarjetas.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none");
        jcmb_servicios.removeAllItems();
        jtbl_tarjetas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        jtbl_tarjetas.setRowHeight(23);  
        Vector listaServicios   = busquedas.facadeGeneralPuertasRemote.buscaNombreServicios();
        jcmb_servicios.addItem("TODOS LOS SERVICIOS");
        for(int i=0; i<listaServicios.size(); i++)
        {
            Vector serv = (Vector)listaServicios.get(i);
            jcmb_servicios.addItem(serv.get(0).toString());
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fh = new SimpleDateFormat("HH:mm");
        //Date hoy = new Date();
        Vector x = new Vector();
          x=  (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();  
        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
        jtxt_fechadesde.setText(ff.format(fecha_servidor.getTime()));
        jtxt_fechahasta.setText(ff.format(fecha_servidor.getTime()));
        jtxt_horadesde.setText(fh.format((fecha_servidor.getTime())- 7200000));
        jtxt_horahasta.setText(fh.format(fecha_servidor.getTime()));
       jtxt_clave.requestFocus();
    }
    
    
      private void resizeColumnasCorridas(){
        TableColumn columinv = jtbl_tarjetas.getColumnModel().getColumn(0); columinv.setMinWidth( 150 );columinv.setMaxWidth( 150 );columinv.setPreferredWidth(150);
        columinv = jtbl_tarjetas.getColumnModel().getColumn(1);columinv.setMinWidth( 160 );columinv.setMaxWidth( 160 );columinv.setPreferredWidth(160);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(2);columinv.setMinWidth( 110 );columinv.setMaxWidth( 110 );columinv.setPreferredWidth(110);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(3);columinv.setMinWidth( 110 );columinv.setMaxWidth( 110 );columinv.setPreferredWidth(110);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(4);columinv.setMinWidth( 70 );columinv.setMaxWidth( 70 );columinv.setPreferredWidth(70);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(5);columinv.setMinWidth( 70 );columinv.setMaxWidth( 70 );columinv.setPreferredWidth(70);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(6);columinv.setMinWidth( 85 );columinv.setMaxWidth( 85 );columinv.setPreferredWidth(85);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(7);columinv.setMinWidth( 70 );columinv.setMaxWidth( 70 );columinv.setPreferredWidth(70);        
        columinv = jtbl_tarjetas.getColumnModel().getColumn(8);columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);        
      }    
    
    
    private void verificarCambiarOcupacion(){
        System.out.println("La ruta de la tarjeta "+jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),0)+" es: "+jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),8));
        int index = codigos.indexOf("P_VLRVENNAS-"+jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),8));
        if(index>=0)
        {
            if(valores.get(index).toString().equals("S"))
              modificarOcupacion = true;
            else
              modificarOcupacion = false;
        }
    }
    
    public int getCapacidadPlantilla(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i).getCapacidadAsientos().intValue();
        return 0;
    }
    
    private void buscarTarjetas(){
          if(!abreSocketAS()){
           new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
           return;
            }
        if(jtxt_fechadesde.getText().equals("") || jtxt_fechahasta.getText().equals(""))
        {
            new jdlg_error("¡Debes introducir la fecha desde y hasta!","","Faltan fechas").setVisible(true);
            jtxt_fechadesde.requestFocus();
            return;
        }  
        else
        {
         if(jtxt_fechadesde.getText().length()<10 || jtxt_fechahasta.getText().length()<10)
          {
            new jdlg_error("¡Debes introducir fechas validas!","","Error en formato de fecha").setVisible(true);
            return;
          }
        }
        
        if(jtxt_horadesde.getText().equals("") || jtxt_horahasta.getText().equals(""))
        {
            new jdlg_error("¡Debes introducir la hora desde y hasta!","","Faltan horas").setVisible(true);
            jtxt_horadesde.requestFocus();
            return;
        }  
        else
        {
         if(jtxt_horadesde.getText().length()<5 || jtxt_horahasta.getText().length()<5)
          {
            new jdlg_error("¡Debes introducir horas validas!","","Error en formato de hora").setVisible(true);
            return;
          }
        }
        
        long hi = horaMinutos(jtxt_horadesde.getText());
        long hf = horaMinutos(jtxt_horahasta.getText());
        if(hi>hf)
          {
            new jdlg_error("¡la Hora desde debe ser menor a la Hora hasta!","","Error en formato de hora").setVisible(true);
            return;
          }

        long fhi = fechaMilisegundos(jtxt_fechadesde.getText());
        long fhf = fechaMilisegundos(jtxt_fechahasta.getText());
        if(fhi>fhf)
          {
            new jdlg_error("¡la Fecha desde debe ser menor a la Fecha hasta!","","Error en formato de fechas").setVisible(true);
            return;
          }
        
        String operador = "%";
        String autobus  = "%";
        String servicio = "%";
        if(!jtxt_clave.getText().equals(""))
            operador = jtxt_clave.getText();
        if(!jtxt_autobus.getText().equals(""))
            autobus = jtxt_autobus.getText();
        if(!jcmb_servicios.getSelectedItem().toString().equals("TODOS LOS SERVICIOS"))
            servicio = jcmb_servicios.getSelectedItem().toString();
        System.out.println("Busca corridas: "+operador+" - "+autobus+" - "+servicio+" - "+jtxt_fechadesde.getText()+" - "+jtxt_fechahasta.getText()+" - "+jtxt_horadesde.getText()+" - "+jtxt_horahasta.getText());
        if(remoto)
        {
         listaCorridas = (List<TmsCorridasVentaTbl>)busquedas.facadeGeneralPuertasRemote.buscarCorridasReimpresionRemoto(autobus,operador,servicio,jtxt_fechadesde.getText(),jtxt_fechahasta.getText(),jtxt_horadesde.getText(),jtxt_horahasta.getText(),DBLink);       
         listaTarjetas = (List<TmsTarjetasViajeTbl>)busquedas.facadeGeneralPuertasRemote.buscarTarjetasReimpresionRemoto(autobus,operador,servicio,jtxt_fechadesde.getText(),jtxt_fechahasta.getText(),jtxt_horadesde.getText(),jtxt_horahasta.getText(),DBLink);
        }
        else
        {
         listaCorridas = (List<TmsCorridasVentaTbl>)busquedas.facadeGeneralPuertasRemote.buscarCorridasReimpresion(autobus,operador,servicio,jtxt_fechadesde.getText(),jtxt_fechahasta.getText(),jtxt_horadesde.getText(),jtxt_horahasta.getText());       
         listaTarjetas = (List<TmsTarjetasViajeTbl>)busquedas.facadeGeneralPuertasRemote.buscarTarjetasReimpresion(autobus,operador,servicio,jtxt_fechadesde.getText(),jtxt_fechahasta.getText(),jtxt_horadesde.getText(),jtxt_horahasta.getText());
        }
        if(listaCorridas.size()==0)
        {
            new jdlg_error("¡No existen tarjetas con estos criterios!","","No hay coincidencias").setVisible(true);
            return;
        }

//        for(int i=0; i<listaTarjetas.size();i++)
//        {
//            TmsTarjetasViajeTbl tarjeta = listaTarjetas.get(i);
//            System.out.println("Tarjeta"+i+"= "+tarjeta.getFolioTarjeta());
//        }
        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fh = new SimpleDateFormat("HH:mm");
        Object[][] datos =  new Object[listaCorridas.size()][9];//{"Folio Tarjeta","Servicio","Origen","Destino","Operador","Autobus","Fecha","Hora"};
        for(int i=0; i<listaCorridas.size();i++)
        {
            TmsCorridasVentaTbl corrida = listaCorridas.get(i);
            TmsTarjetasViajeTbl tarjeta = listaTarjetas.get(i);
            datos[i][0] = tarjeta.getFolioTarjeta();
            datos[i][1] = corrida.getServicio();
            datos[i][2] = corrida.getOrigen();
            datos[i][3] = corrida.getDestino();
            if(corrida.getOperador()==null)
                datos[i][4] = "No Asignado";
            else    
                datos[i][4] = tarjeta.getOperador();
            if(corrida.getAutobus()==null)
             datos[i][5] = "No Asignado";
            else
             datos[i][5] = tarjeta.getAutobus();
            //System.out.println("Fecha corrida: "+corrida.getFechaHoraCorrida());
            datos[i][6] = ff.format(corrida.getFechaHoraCorrida());
            datos[i][7] = fh.format(corrida.getFechaHoraCorrida());
            Vector lruta = new Vector();
                if(remoto)
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRutaRemoto(corrida.getClaveCorrida(),DBLink);
                else
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRuta(corrida.getClaveCorrida());
                if(lruta.size()>0)
                {
                    Vector ruta = (Vector) lruta.get(0);
                    datos[i][8] =  ruta.get(0).toString();
                }
                else
                {
                    new jdlg_advertencia("¡La rua de la corrida no esta configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                    datos[i][7] =  "1";
                }
            
        }
        modeloTarjetas.setDataVector(datos,encabezadoTarjetas);
        resizeColumnasCorridas();
        jtbl_tarjetas.setRowSelectionInterval(0,0);
        jtbl_tarjetas.setColumnSelectionInterval(0,0);
        jtbl_tarjetas.requestFocus();
    }
    
    
    private long horaMinutos(String hora){
       long minutos =0;
       System.out.println("Hora: "+hora);
       StringTokenizer tknz = new  StringTokenizer(hora,":");
       String sh = tknz.nextToken();
       String sm = tknz.nextToken();
       System.out.println("Horas: "+sh);       
       System.out.println("Minutos: "+sm);       
       long h = Long.valueOf(sh);
       long m = Long.valueOf(sm);
       minutos = (h*60) + m;
       return minutos;
    }
    
    private long fechaMilisegundos(String fecha){
        StringTokenizer tknz = new  StringTokenizer(fecha,"/");
        String dia = tknz.nextToken();
        String mes = tknz.nextToken();
        String año = tknz.nextToken();
        Timestamp fechaCompleta = null;
        fechaCompleta = fechaCompleta.valueOf(año+"-"+mes+"-"+dia+" 00:00:00");
        return fechaCompleta.getTime();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_clave = new tms_TextFields.JTextTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxt_autobus = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcmb_servicios = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxt_fechadesde = new tms_TextFields.JDateTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxt_fechahasta = new tms_TextFields.JDateTextField();
        jtxt_horadesde = new tms_TextFields.JHourTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxt_horahasta = new tms_TextFields.JHourTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_tarjetas = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de B\u00fasqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Clave Operador:");

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

        jcmb_servicios.setFont(new java.awt.Font("Tahoma", 1, 12));
        jcmb_servicios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmb_servicios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_serviciosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmb_serviciosFocusLost(evt);
            }
        });
        jcmb_servicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_serviciosKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Fecha Desde: ");

        jtxt_fechadesde.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_fechadesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_fechadesdeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_fechadesdeFocusLost(evt);
            }
        });
        jtxt_fechadesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechadesdeKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("hasta:");

        jtxt_fechahasta.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_fechahasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_fechahastaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_fechahastaFocusLost(evt);
            }
        });
        jtxt_fechahasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechahastaKeyPressed(evt);
            }
        });

        jtxt_horadesde.setText("12:00");
        jtxt_horadesde.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_horadesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_horadesdeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_horadesdeFocusLost(evt);
            }
        });
        jtxt_horadesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_horadesdeKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Hora desde:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("hasta:");

        jtxt_horahasta.setText("12:00");
        jtxt_horahasta.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_horahasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_horahastaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_horahastaFocusLost(evt);
            }
        });
        jtxt_horahasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_horahastaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_fechadesde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_fechahasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 22, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jcmb_servicios, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_horadesde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_horahasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jtxt_clave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcmb_servicios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel7)
                    .add(jLabel4)
                    .add(jtxt_fechadesde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(jtxt_fechahasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtxt_horadesde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtxt_horahasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarjetas Encontradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_tarjetas.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtbl_tarjetas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_tarjetas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_tarjetasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtbl_tarjetasFocusLost(evt);
            }
        });
        jtbl_tarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_tarjetasKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_tarjetas);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Cerrar | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> \u00ab \u00bb </font> Moverse entre Campos </html>");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(15, 15, 15)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_horahastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_horahastaFocusLost
        jtxt_horahasta.setBackground(Color.WHITE); 
    }//GEN-LAST:event_jtxt_horahastaFocusLost

    private void jtxt_horadesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_horadesdeFocusLost
        jtxt_horadesde.setBackground(Color.WHITE);  
    }//GEN-LAST:event_jtxt_horadesdeFocusLost

    private void jtxt_fechahastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechahastaFocusLost
             jtxt_fechahasta.setBackground(Color.WHITE);  
    }//GEN-LAST:event_jtxt_fechahastaFocusLost

    private void jtxt_fechadesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechadesdeFocusLost
        jtxt_fechadesde.setBackground(Color.WHITE);  
    }//GEN-LAST:event_jtxt_fechadesdeFocusLost

    private void jtxt_autobusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_autobusFocusLost
            jtxt_autobus.setBackground(Color.WHITE);  
    }//GEN-LAST:event_jtxt_autobusFocusLost

    private void jtxt_claveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusLost
             jtxt_clave.setBackground(Color.WHITE);  
    }//GEN-LAST:event_jtxt_claveFocusLost

    private void jtxt_horahastaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_horahastaFocusGained
        jtxt_horahasta.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_horahastaFocusGained

    private void jtxt_horadesdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_horadesdeFocusGained
        jtxt_horadesde.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_horadesdeFocusGained

    private void jtxt_fechahastaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechahastaFocusGained
        jtxt_fechahasta.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_fechahastaFocusGained

    private void jtxt_fechadesdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_fechadesdeFocusGained
        jtxt_fechadesde.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_fechadesdeFocusGained

    private void jtxt_autobusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_autobusFocusGained
        jtxt_autobus.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_autobusFocusGained

    private void jtxt_claveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_claveFocusGained
            jtxt_clave.setBackground(new Color(184,207,229));
    }//GEN-LAST:event_jtxt_claveFocusGained

    private void jcmb_serviciosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_serviciosFocusLost
     jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Cerrar | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos </html>");
    }//GEN-LAST:event_jcmb_serviciosFocusLost

    private void jcmb_serviciosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_serviciosFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Cerrar | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000> ABAJO </font> Mostrar Servicios  </html>");
    }//GEN-LAST:event_jcmb_serviciosFocusGained

    private void jtbl_tarjetasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_tarjetasFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Cerrar | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos </html>");
    }//GEN-LAST:event_jtbl_tarjetasFocusLost

    private void jtxt_horahastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_horahastaKeyPressed
       if(evt.getKeyCode() == evt.VK_LEFT)
      {
         jtxt_horadesde.requestFocus();
         jtxt_horadesde.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_clave.requestFocus();
          jtxt_clave.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();

      }        
    }//GEN-LAST:event_jtxt_horahastaKeyPressed

    private void jtxt_horadesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_horadesdeKeyPressed
       if(evt.getKeyCode() == evt.VK_LEFT)
      {
         jtxt_fechahasta.requestFocus();
         jtxt_fechahasta.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_horahasta.requestFocus();
          jtxt_horahasta.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();

      }

    }//GEN-LAST:event_jtxt_horadesdeKeyPressed

    private void jtxt_fechahastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fechahastaKeyPressed
      if(evt.getKeyCode() == evt.VK_LEFT)
      {
         jtxt_fechadesde.requestFocus();
         jtxt_fechadesde.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_horadesde.requestFocus();
          jtxt_horadesde.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();

      }
    }//GEN-LAST:event_jtxt_fechahastaKeyPressed

    private void jtxt_fechadesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fechadesdeKeyPressed
    
      if(evt.getKeyCode() == evt.VK_LEFT)
      {
         jcmb_servicios.requestFocus();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_fechahasta.requestFocus();
          jtxt_fechahasta.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();

      }        
    }//GEN-LAST:event_jtxt_fechadesdeKeyPressed

    private void jcmb_serviciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_serviciosKeyPressed
       if(evt.getKeyCode() == evt.VK_LEFT)
      {
          jtxt_autobus.requestFocus();
          jtxt_autobus.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_fechadesde.requestFocus();
          jtxt_fechadesde.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();
      }     
        
    }//GEN-LAST:event_jcmb_serviciosKeyPressed

    private void jtxt_autobusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_autobusKeyPressed
      if(evt.getKeyCode() == evt.VK_LEFT)
      {
          jtxt_clave.requestFocus();
          jtxt_clave.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jcmb_servicios.requestFocus();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();

      }        
    }//GEN-LAST:event_jtxt_autobusKeyPressed

    private void jtbl_tarjetasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_tarjetasFocusGained
    jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER</font> Imprimir Tarjeta | <font color=FF0000>ABAJO</font> Tarjeta Siguiente | <font color=FF0000>ARRIBA</font> Tarjeta Anterior | <font color=FF0000>ESCAPE</font> Campos de Busqueda </html>");
    }//GEN-LAST:event_jtbl_tarjetasFocusGained

    private void jtbl_tarjetasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_tarjetasKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
        {
            modeloTarjetas.setDataVector(null,encabezadoTarjetas);
            resizeColumnasCorridas();
            jtxt_clave.selectAll();
            jtxt_clave.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_ENTER){
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
                verificarCambiarOcupacion();
   
                TmsCorridasVentaTbl corrida = listaCorridas.get(jtbl_tarjetas.getSelectedRow());
                Vector ocupacionSistema = new Vector();
                if(remoto)
                    ocupacionSistema = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistemaRemoto(corrida.getClaveCorrida(),DBLink);
                else
                    ocupacionSistema = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistema(corrida.getClaveCorrida());
                System.out.println("la calve de la corrida es: "+corrida.getClaveCorrida());
                System.out.println("la ocupacion es: "+ocupacionSistema);
                Vector datosTarjeta =new Vector();
                Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),4).toString());
                Vector nombre;
                String nombreoperador  = "";
                if(vnombre.size()==0)
                {
                 new jdlg_advertencia("¡El operador no se encontro registrado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                 this.dispose();
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
                TmsTarjetasViajeTbl tarjeta = listaTarjetas.get(jtbl_tarjetas.getSelectedRow());
                datosTarjeta.add(corrida.getClaveCorrida());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),1).toString());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),2).toString());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),3).toString());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),4).toString());
                datosTarjeta.add(nombreoperador);
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),5).toString());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),6).toString());
                datosTarjeta.add(jtbl_tarjetas.getValueAt(jtbl_tarjetas.getSelectedRow(),7).toString());
                Vector lruta = new Vector();
                if(remoto)
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRutaRemoto(corrida.getClaveCorrida(),DBLink);
                else
                    lruta = (Vector) busquedas.facadeGeneralPuertasRemote.buscarDatosRuta(corrida.getClaveCorrida());
                String rutaID = "";
                if(lruta.size()>0)
                {
                    Vector ruta = (Vector) lruta.get(0);
                    datosTarjeta.add(ruta.get(0).toString());
                    datosTarjeta.add(ruta.get(1).toString());
                    rutaID = ruta.get(2).toString();
                }
                else
                {
                    new jdlg_advertencia("¡La rua de la corrida no esta configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
                    datosTarjeta.add("00");
                    datosTarjeta.add("RUTA DESCONOCIDA");
                    rutaID = "1";
                }
                 datosTarjeta.add(corrida.getCorridaId());
                 datosTarjeta.add(usuarioId);
                 datosTarjeta.add(sesionId);
                 int index = empresas.indexOf(corrida.getEmpresa()); 
                 if(index<0)
                   datosTarjeta.add("Autobuses México Puebla Estrella Roja S.A. de C.V.");
                 else
                 {
                     String[] parem =(String[]) paramEmpresas.get(index);
                     datosTarjeta.add(parem[0]);
                 }
                 datosTarjeta.add(rutaID);
                 //datosTarjeta(16) Tipo Corrida
                 if(corrida.getTipoCorrida().equals("N"))
                    datosTarjeta.add("ROL");
                 else
                    datosTarjeta.add("EXTRA");
                     
                int capacidad =  capacidad = this.getCapacidadPlantilla(corrida.getPlantillaId().longValue()); 
         if(modificarOcupacion)
            {
                System.out.println("Entra a sacar los valores de la Tarjeta...");
                System.out.println("El Vectyor de Ocupacion es: "+ltiposPasajero);
                Object[][] ocupacionS = new Object[ltiposPasajero.size()][4];
                for(int i=0; i<ltiposPasajero.size();i++){
                    Vector vtipo = (Vector) ltiposPasajero.get(i);
                    ocupacionS[i][0]=vtipo.get(0).toString();   
                    if(vtipo.get(0).toString().equals("ADULTO")){ocupacionS[i][1]=""+tarjeta.getNoAdultosAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoAdultosAbordados();}
                    if(vtipo.get(0).toString().equals("MENOR")){ocupacionS[i][1]=""+tarjeta.getNoMenoresAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoMenoresAbordados();}
                    if(vtipo.get(0).toString().equals("SENECTUD")){ocupacionS[i][1]=""+tarjeta.getNoSenectudAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoSenectudAbordados();}
                    if(vtipo.get(0).toString().equals("ESTUDIANTE")){ocupacionS[i][1]=""+tarjeta.getNoEstudiantesAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoEstudiantesAbordados();}
                    if(vtipo.get(0).toString().equals("PROFESOR")){ocupacionS[i][1]=""+tarjeta.getNoProfesorAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoProfesorAbordados();}
                    if(vtipo.get(0).toString().equals("ESPECIAL")){ocupacionS[i][1]=""+tarjeta.getNoEspecialAbordados(); ocupacionS[i][2]=""+tarjeta.getMontoEspecialAbordados();}
              }
               
                 //ocupacionS[i][1]=""+conteo;   
                 //ocupacionS[i][2]=""+total;   
             if(remoto)
                new  Jdlg_vistaPreviaReimpresionTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),2,tarjeta,ocupacionS,remoto,DBLink,puerto, impresora, this.ipAS, this.portAS).setVisible(true);                 
             else
                new  Jdlg_vistaPreviaReimpresionTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),2,tarjeta,ocupacionS,remoto,"",puerto, impresora, this.ipAS, this.portAS).setVisible(true);                 
           }
            else
            {
                 if(remoto)
                    new  Jdlg_vistaPreviaReimpresionTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),1,tarjeta,new Object[5][5],remoto,DBLink,puerto, impresora, this.ipAS, this.portAS).setVisible(true);
                 else
                     new  Jdlg_vistaPreviaReimpresionTarjetaViaje(capacidad,ocupacionSistema,ltiposPasajero,datosTarjeta,busquedas,new Vector(),1,tarjeta,new Object[5][5],remoto,"",puerto, impresora, this.ipAS, this.portAS).setVisible(true);
            }              
            //buscarCorridas(claveActual,autobusActual,servicioActual);
        }
        
    }//GEN-LAST:event_jtbl_tarjetasKeyPressed

    
    private void jtxt_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_claveKeyPressed
      if(evt.getKeyCode() == evt.VK_LEFT)
      {
          jtxt_horahasta.requestFocus();
          jtxt_horahasta.selectAll();
      }

      if(evt.getKeyCode() == evt.VK_RIGHT)
      {
          jtxt_autobus.requestFocus();
          jtxt_autobus.selectAll();
      }
      
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          this.dispose();
      }
      
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        buscarTarjetas();
      }

    }//GEN-LAST:event_jtxt_claveKeyPressed
    
    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jdlg_Reimpresion(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_servicios;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_tarjetas;
    private javax.swing.JTextField jtxt_autobus;
    private tms_TextFields.JTextTextField jtxt_clave;
    private tms_TextFields.JDateTextField jtxt_fechadesde;
    private tms_TextFields.JDateTextField jtxt_fechahasta;
    private tms_TextFields.JHourTextField jtxt_horadesde;
    private tms_TextFields.JHourTextField jtxt_horahasta;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadoTarjetas = {"Folio Tarjeta","Servicio","Origen","Destino","Operador","Autobus","Fecha","Hora","id_ruta"};
    private DefaultTableModel modeloTarjetas= new DefaultTableModel(null,encabezadoTarjetas){
    public boolean isCellEditable (int row, int column){if (column == 20)return true;return false;}};
    private TmsPuertasManagedBean busquedas;
    private boolean modificarOcupacion = false;
    private List<TmsServiciosTbl> listaServicios;
    private Vector codigos;
    private Vector valores;
    private List<TmsCorridasVentaTbl> listaCorridas;
    private List<TmsTarjetasViajeTbl> listaTarjetas ;        
    private long usuarioId;
    private long sesionId;
    private List<TmsAutobusPlantillasEncTbl> encabezados;
    private Vector ltiposPasajero;
    private Timestamp fecha_servidor;
    private boolean remoto;
    private String DBLink;
    private String puerto;
    private PrintService impresora;
    private Vector paramEmpresas;
    private Vector empresas;

    private int portAS;

    private String ipAS;


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
