/*
 * Jdlg_vistaPreviaTarjetaViaje.java
 *
* Created on 10 de octubre de 2007, 06:18 PM
 */

package tmspuertas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_encriptacion.EncriptarMD5;
//import tmscodigobarras.JIF_ValidarCodigoBarras;
import tmspuertas.entidad.TmsCorridasTbl;
import tmspuertas.entidad.TmsCorridasVentaTbl;
import tmspuertas.entidad.TmsEstadosCorridaTbl;
import tmspuertas.entidad.TmsTarjetasViajeTbl;
import tmspuertas.solicitud.UsuarioNoEncontradoException;

/**
 *
 * @author  vgonzalez
 */
public class Jdlg_vistaPreviaTarjetaViaje extends javax.swing.JDialog {

    private String puerto;
    private PrintService impresora;
    private String DBLinkCentral;
    private long edotar;
    private Vector vEdosAcc;
    private String ipAS;
    private int portAS;
    private int numAsientos;
    private Vector datosIniciales;
    private String autorizado2 = "";
    private jdlgDatosSupervisor dlgSupervisor;
    
    /** Creates new form Jdlg_vistaPreviaTarjetaViaje */
    public Jdlg_vistaPreviaTarjetaViaje(int pnumAsientos,Vector oc, Vector tip, Vector dt, TmsPuertasManagedBean pbusquedas,Vector ocm,Object[][] psinOcupacion,int p,boolean pautorizacion,boolean pautorizacion2,long pntarpen, boolean premoto, String pDBLink, String ppuerto, PrintService pimpresora, String pDBLinkCentral, long pedotar, Vector pvEdosAcc, String pIpSA, int pPortSA,Vector pdatosIniciales) {
        this.setTitle("Vista previa de la tarjeta de viaje");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        this.ocupacion = oc;
        this.tipos = tip;
        this.datosTarjeta = dt;
        this.puerto = ppuerto;
        this.edotar = pedotar;
        this.impresora = pimpresora;
        this.busquedas = pbusquedas;
        this.proviene = p;
        this.ocupacionModificada = ocm;
        this.sinOcupacion = psinOcupacion;
        this.autorizacion= pautorizacion;
        this.autorizacion2= pautorizacion2;
        this.ntarpen = pntarpen;
        this.remoto = premoto;
        this.DBLink = pDBLink;
        this.DBLinkCentral = pDBLinkCentral;
        this.vEdosAcc = pvEdosAcc;
        this.ipAS = pIpSA;
        this.portAS= pPortSA;
        this.numAsientos = pnumAsientos;
        this.datosIniciales = pdatosIniciales;
        
        initComponents();
        this.setLayout(null);
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        jtbl_detalleAbordo.setModel(modeloDetalleAbordo);
        jtbl_boletosDetalle.setModel(modeloBoletos);
        String[] rowDetalle ={"ADULTOS", "6","239.00"};
        modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);
        modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);
            String[] row = {"  "," "," "," ","  "," "," "," ","  "," "," "," ","  "," "," "," "};
            //String[] row = {"","STANA","E","TT0657730","","CAPU","P","TT0657731","","TAPO","M","TT0657732","","SMARC","S","TT0657733"};
        modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);
        modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);
        int nc = 0;
        int nr = 0;
        for(int i=1; i<60; i++)
        {
             if(i>numAsientos)break;
             if(i==16){nc=4;nr=0;}
             if(i==31){nc=8;nr=0;}
             if(i==46){nc=12;nr=0;}
             if(i<=9)
               jtbl_boletosDetalle.setValueAt(" "+i,nr,nc);
             else
               jtbl_boletosDetalle.setValueAt(""+i,nr,nc);
             nr++;
        }
        resizeColumnasBoletos();
        resizeColumnasAbordo();
        this.setSize(795,560);
        jtbl_boletosDetalle.setShowHorizontalLines(false);
        //jtbl_detalleAbordo.setShowHorizontalLines(false);
        jtbl_detalleAbordo.setShowGrid(false);
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
        //jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si, Despachar Tarjeta                 <font color=FF0000>             ESC: </font> No     <font color=FF0000>F9: </font> Checar Abordaje</html>");
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si, Despachar Tarjeta                 <font color=FF0000>             ESC: </font> No   </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        jlbl_nombreOperador.setHorizontalAlignment( JTextField.CENTER );
        jlbl_barraEstado.setFocusTraversalKeysEnabled(false);
        System.out.println("this.proviene = "+this.proviene);
       if(proviene==3)
       {
        llenarOcupacion3();
       }
       else
       {
        if(proviene==2)
        {
          llenarOcupacion2();
        }
        else
            {
                llenarOcupacion1();
            }
         }
        String clave = datosTarjeta.get(0).toString();
        jlbl_claveCorrida.setText(clave);
        jlbl_servicio.setText(datosTarjeta.get(1).toString());
        jlbl_origen.setText(datosTarjeta.get(2).toString());
        jlbl_origen2.setText(datosTarjeta.get(2).toString());
        jlbl_destino2.setText(datosTarjeta.get(3).toString());
        jlbl_claveOperador.setText(datosTarjeta.get(4).toString());
        jlbl_nombreOperador.setText(datosTarjeta.get(5).toString());
        jlbl_autobus.setText(datosTarjeta.get(6).toString());
        jlbl_fecha.setText(datosTarjeta.get(7).toString());
        jlbl_hora.setText(datosTarjeta.get(8).toString());
        jlbl_ruta.setText(datosTarjeta.get(9).toString());
        jlbl_descipcionViaje.setText(datosTarjeta.get(10).toString());
        Vector vnfol = new Vector();
          if(!abreSocketAS())
          {
            new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
            nfol  = -1;
          }
          else    
          {
            String nombresquema = clave.substring(0,5);
            if(remoto)
                vnfol =  (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaValorActualTarjetaViajeRemoto(DBLink); 
            else
                vnfol =  (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaValorActualTarjetaViaje(); 
    
            nfol = (Long.valueOf(vnfol.get(0).toString())) + 1;
          }
        
        jlbl_folio.setText(clave.substring(0,10)+""+nfol);
        String sfolio = ""+nfol;
        String sviaje = sfolio;
        if(sfolio.length()>=4)
         sviaje = sfolio.substring(3);
        jlbl_viaje.setText(sviaje);//""+nfol);
        jtxt_empresaTitulo.setText(datosTarjeta.get(15).toString().toUpperCase());
        //jlbl_barraEstado.requestFocus();
        jtxt_TicketSalida.requestFocus();
    }
    
    private void llenarOcupacion1(){
            System.out.println("Ocupacion antes de insertarla:");
            System.out.println(""+ocupacion);
            Object[][] ocupacionSistema = new Object[tipos.size()][4];
            Vector vec =  new Vector();
            vencabezadoDetalle = new Vector();
            vencabezadoDetalle.add("TIPO PAS");
            vencabezadoDetalle.add("# BOL");
            vencabezadoDetalle.add("TOTAL");

            for(int i=0; i<tipos.size();i++){
                Vector vtipo = (Vector) tipos.get(i);
                String letraTipo = vtipo.get(1).toString();
                int conteo = 0;
                float total = 0;
                for(int j=0; j<ocupacion.size(); j++){
                    Vector vocupa = (Vector) ocupacion.get(j);
                    if(letraTipo.equals(vocupa.get(4).toString()))
                    {
                       conteo++;
                       total = total + Float.valueOf(vocupa.get(5).toString());
                    }
                }
                //VAGL 31102011
                /*
                  ocupacionSistema[i][0]=vtipo.get(0).toString();
                  ocupacionSistema[i][1]=""+conteo;
                  ocupacionSistema[i][2]=""+total;
                 */
                
                if(conteo>0 || vtipo.get(0).toString().equals("ESTUDIANTE") || vtipo.get(0).toString().equals("ADULTO") || vtipo.get(0).toString().equals("MENOR") || vtipo.get(0).toString().equals("SENECTUD") || vtipo.get(0).toString().equals("PROFESOR") )
                {
                  System.out.println("vtipo.get(0).toString(): "+vtipo.get(0).toString()+"    ==> "+conteo);
                  Vector vocupacionSistema = new Vector();
                  vocupacionSistema.add(vtipo.get(0).toString());
                  vocupacionSistema.add(conteo);
                  vocupacionSistema.add(total);
                  vec.add(vocupacionSistema);
                }
             }
            System.out.println("vencabezadoDetalle: "+vencabezadoDetalle);
            System.out.println("vec: "+vec);
               //modeloDetalleAbordo.setDataVector(ocupacionSistema,encabezadoDetalle);
                modeloDetalleAbordo.setDataVector(vec,vencabezadoDetalle);
                String[] nrow = {"","",""};
                int cbol = 0;
                float mbol = 0;
                for(int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
                {
                    cbol = cbol + Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                    mbol = mbol + Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString());
                }
                modeloDetalleAbordo.addRow(nrow);
                nrow[0]="TOTALES";
                nrow[1] = ""+cbol;
                nrow[2] = ""+mbol;
                modeloDetalleAbordo.addRow(nrow);
                resizeColumnasAbordo();
                
                ///LLena acupacion
                Vector vOcupados=new Vector();
                if(ocupacion.size()>0){
                for(int j=0; j<ocupacion.size(); j++){
                    Vector vocupa = (Vector) ocupacion.get(j);
                    for(int k=0;k<jtbl_boletosDetalle.getRowCount();k++)
                    {   
                        String na = jtbl_boletosDetalle.getValueAt(k,0).toString().trim();
                        //System.out.println("na: "+na+" vocupa.get(1): "+vocupa.get(1).toString().trim());
                        if(na.equals(vocupa.get(1).toString().trim()))
                        {
                            if(!jtbl_boletosDetalle.getValueAt(k,3).toString().trim().equals(""))
                            {
                                vOcupados.add(vocupa);
                                break;
                            }                            
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,1);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,2);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,3);
                                break;
                        }
                        na = jtbl_boletosDetalle.getValueAt(k,4).toString().trim();
                        if(na.equals(vocupa.get(1).toString().trim()))
                        {
                            if(!jtbl_boletosDetalle.getValueAt(k,7).toString().trim().equals(""))
                            {
                                vOcupados.add(vocupa);
                                break;
                            }                            
                            jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,5);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,6);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,7);
                            break;
                        }
                        na = jtbl_boletosDetalle.getValueAt(k,8).toString().trim();
                        if(na.equals(vocupa.get(1).toString().trim()))
                        {
                            if(!jtbl_boletosDetalle.getValueAt(k,11).toString().trim().equals(""))
                            {
                                vOcupados.add(vocupa);
                                break;
                            }                            
                            jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,9);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,10);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,11);
                            break;
                        }
                        na = jtbl_boletosDetalle.getValueAt(k,12).toString().trim();
                        if(na.equals(vocupa.get(1).toString().trim()))
                        {
                            if(!jtbl_boletosDetalle.getValueAt(k,15).toString().trim().equals(""))
                            {
                                vOcupados.add(vocupa);
                                break;
                            }                            
                            jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,13);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,14);
                            jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,15);
                            break;
                        }
                  }
                 }
                }//if(ocupacion)
             if(vOcupados.size()>0)   
             {
                for(int j=0; j<vOcupados.size(); j++){
                    Vector vocupa = (Vector) vOcupados.get(j);
                    for(int k=0;k<jtbl_boletosDetalle.getRowCount();k++)
                    {   
                            if(jtbl_boletosDetalle.getValueAt(k,3).toString().trim().equals(""))
                            {
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,1);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,2);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,3);
                                break;
                            }                            
                            if(jtbl_boletosDetalle.getValueAt(k,7).toString().trim().equals(""))
                            {
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,5);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,6);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,7);
                                break;
                            }                            
                            if(jtbl_boletosDetalle.getValueAt(k,11).toString().trim().equals(""))
                            {
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,9);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,10);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,11);
                                break;
                            }                            
                            if(jtbl_boletosDetalle.getValueAt(k,15).toString().trim().equals(""))
                            {
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,13);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,14);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(3).toString(),k,15);
                                break;
                            }                            
                  }
                 }                        
             }//if(vOcupados>0)        
                
    }
    
        private void llenarOcupacion2(){
            vencabezadoDetalle = new Vector();
            vencabezadoDetalle.add("TIPO PAS");
            vencabezadoDetalle.add("# BOL");
            vencabezadoDetalle.add("TOTAL");
            String[] nrow = {"","",""};
            modeloDetalleAbordo.setDataVector(ocupacionModificada,vencabezadoDetalle);
            int cbol = 0;
            float mbol = 0;
            for(int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
            {
                cbol = cbol + Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                mbol = mbol + Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString());
            }
            modeloDetalleAbordo.addRow(nrow);
            nrow[0]="TOTALES";
            nrow[1] = ""+cbol;
            nrow[2] = ""+mbol;
            modeloDetalleAbordo.addRow(nrow);
            resizeColumnasAbordo();        
    }

    private void llenarOcupacion3(){
            String[] nrow = {"","",""};
            modeloDetalleAbordo.setDataVector(sinOcupacion,encabezadoDetalle);
            int cbol = 0;
            float mbol = 0;
            for(int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
            {
                cbol = cbol + Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                mbol = mbol + Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString());
            }
            modeloDetalleAbordo.addRow(nrow);
            nrow[0]="TOTALES";
            nrow[1] = ""+cbol;
            nrow[2] = ""+mbol;
            modeloDetalleAbordo.addRow(nrow);
            resizeColumnasAbordo();        
    }

    
    private void resizeColumnasBoletos(){
    TableColumn columinv = jtbl_boletosDetalle.getColumnModel().getColumn(0); columinv.setMinWidth( 15 );columinv.setMaxWidth( 15 );columinv.setPreferredWidth(15);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(4); columinv.setMinWidth( 15 );columinv.setMaxWidth( 15 );columinv.setPreferredWidth(15);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(8); columinv.setMinWidth( 15 );columinv.setMaxWidth( 15 );columinv.setPreferredWidth(15);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(12); columinv.setMinWidth( 15 );columinv.setMaxWidth( 15 );columinv.setPreferredWidth(15);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(1); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(5); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(9); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(13); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(2); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(6); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(10); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(14); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(3); columinv.setMinWidth( 55 );columinv.setMaxWidth( 55 );columinv.setPreferredWidth(55);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(7); columinv.setMinWidth( 55 );columinv.setMaxWidth( 55 );columinv.setPreferredWidth(55);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(11); columinv.setMinWidth( 55 );columinv.setMaxWidth( 55 );columinv.setPreferredWidth(55);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(15); columinv.setMinWidth( 55 );columinv.setMaxWidth( 55 );columinv.setPreferredWidth(55);          
    }
    
    private void resizeColumnasAbordo(){
        TableColumn columinv = jtbl_detalleAbordo.getColumnModel().getColumn(0); columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);                  
                    columinv = jtbl_detalleAbordo.getColumnModel().getColumn(1); columinv.setMinWidth( 50 );columinv.setMaxWidth( 50 );columinv.setPreferredWidth(50);                  
                    columinv = jtbl_detalleAbordo.getColumnModel().getColumn(2); columinv.setMinWidth( 80 );columinv.setMaxWidth( 80 );columinv.setPreferredWidth(80);                  
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbl_barraEstado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_boletosDetalle = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlbl_descipcionViaje = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlbl_folio = new javax.swing.JLabel();
        jlbl_claveOperador = new javax.swing.JLabel();
        jlbl_fecha = new javax.swing.JLabel();
        jlbl_hora = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jlbl_nombreOperador = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlbl_origen2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jlbl_destino2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jlbl_ruta = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jlbl_claveCorrida = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_detalleAbordo = new javax.swing.JTable();
        jtxt_empresaTitulo = new javax.swing.JLabel();
        jlbl_origen = new javax.swing.JLabel();
        jlbl_servicio = new javax.swing.JLabel();
        jlbl_autobus = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlbl_viaje = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jlbl_tipoCorrida = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtxt_TicketSalida = new tms_TextFields.JTextTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_barraEstadoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setText("¿Los datos de la tarjeta de viaje son correctos?");

        jtbl_boletosDetalle.setBackground(new java.awt.Color(238, 238, 238));
        jtbl_boletosDetalle.setFont(new java.awt.Font("Tahoma", 0, 9));
        jtbl_boletosDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_boletosDetalle.setEnabled(false);
        jtbl_boletosDetalle.setFocusable(false);
        jScrollPane1.setViewportView(jtbl_boletosDetalle);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("TARJETA DE VIAJE");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setText("ORIGEN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("SERVICIO");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText("AUTOBUS");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel11.setText("DESCRIPCION DEL VIAJE:");

        jlbl_descipcionViaje.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_descipcionViaje.setText("INTERMEDIO CAPU-TAPO");
        jlbl_descipcionViaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel13.setText("FOLIO");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel14.setText("OPERADOR");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel15.setText("FECHA");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel16.setText("HORA");

        jlbl_folio.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_folio.setText("TAPOI2350N123456");

        jlbl_claveOperador.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_claveOperador.setForeground(new java.awt.Color(0, 102, 255));
        jlbl_claveOperador.setText("1039204");

        jlbl_fecha.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_fecha.setText("15/10/2007");

        jlbl_hora.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_hora.setText("23:50");

        jlbl_nombreOperador.setFont(new java.awt.Font("Tahoma", 1, 13));
        jlbl_nombreOperador.setForeground(new java.awt.Color(0, 102, 255));
        jlbl_nombreOperador.setText("LUIS FLORES EMMANUEL");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel21.setText("RESUMEN DEL VIAJE");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel22.setText("ORIGEN:");

        jlbl_origen2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_origen2.setText("SANMARTIN");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel24.setText("DESTINO:");

        jlbl_destino2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_destino2.setText("AEROMEXICO");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel26.setText("RUTA:");

        jlbl_ruta.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_ruta.setText("120");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel28.setText("CORRIDA:");

        jlbl_claveCorrida.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_claveCorrida.setText("TAPOI2350123456789");

        jScrollPane2.setFocusable(false);

        jtbl_detalleAbordo.setBackground(new java.awt.Color(238, 238, 238));
        jtbl_detalleAbordo.setFont(new java.awt.Font("Tahoma", 0, 10));
        jtbl_detalleAbordo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jtbl_detalleAbordo.setEnabled(false);
        jScrollPane2.setViewportView(jtbl_detalleAbordo);

        jtxt_empresaTitulo.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_empresaTitulo.setText("AUTOBUSES MEXPUE ESTRELLA ROJA");

        jlbl_origen.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_origen.setText("jLabel8");

        jlbl_servicio.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_servicio.setText("jLabel9");

        jlbl_autobus.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_autobus.setForeground(new java.awt.Color(0, 102, 255));
        jlbl_autobus.setText("jLabel10");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel8.setText("DIGITE:");

        jlbl_viaje.setText("0000000000");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel23.setText("TIPO CORRIDA:");

        jlbl_tipoCorrida.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_tipoCorrida.setText("  ");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel25.setText("TICKET SALIDA:");

        jtxt_TicketSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_TicketSalidaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 507, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel25)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jtxt_TicketSalida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabel23))
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel22)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_origen2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel24)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_destino2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel26)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_ruta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel28)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jlbl_tipoCorrida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                    .add(jlbl_claveCorrida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jlbl_viaje))
                            .add(layout.createSequentialGroup()
                                .add(68, 68, 68)
                                .add(jLabel21))
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jlbl_folio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(layout.createSequentialGroup()
                                                    .add(21, 21, 21)
                                                    .add(jLabel13)))
                                            .add(layout.createSequentialGroup()
                                                .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(27, 27, 27)))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(jSeparator5)
                                                .add(jLabel14))
                                            .add(jlbl_claveOperador))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                    .add(jSeparator6)
                                                    .add(jLabel15))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(jSeparator7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel16)))
                                            .add(layout.createSequentialGroup()
                                                .add(jlbl_fecha)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jlbl_hora))))
                                    .add(jlbl_nombreOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(layout.createSequentialGroup()
                        .add(214, 214, 214)
                        .add(jLabel2)
                        .add(24, 24, 24)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 359, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 786, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(536, 536, 536)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel5)
                                    .add(jlbl_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(6, 6, 6)
                                                .add(jlbl_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(layout.createSequentialGroup()
                                                .add(15, 15, 15)
                                                .add(jLabel6)))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(18, 18, 18)
                                                .add(jLabel7))
                                            .add(layout.createSequentialGroup()
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jlbl_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(layout.createSequentialGroup()
                                        .add(27, 27, 27)
                                        .add(jLabel4))))
                            .add(jtxt_empresaTitulo)
                            .add(jlbl_descipcionViaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11))))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jtxt_empresaTitulo)
                        .add(8, 8, 8)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(25, 25, 25)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(49, 49, 49)
                                        .add(jLabel11)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_descipcionViaje)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(layout.createSequentialGroup()
                                                        .add(jLabel13)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                    .add(layout.createSequentialGroup()
                                                        .add(jLabel14)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                                    .add(jlbl_folio)
                                                    .add(jlbl_claveOperador)))
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                    .add(layout.createSequentialGroup()
                                                        .add(jLabel16)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jSeparator7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                    .add(layout.createSequentialGroup()
                                                        .add(jLabel15)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jSeparator6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                                    .add(jlbl_hora)
                                                    .add(jlbl_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                        .add(16, 16, 16)
                                        .add(jlbl_nombreOperador)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel21)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 169, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                            .add(jLabel8)
                                            .add(jlbl_viaje)))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                            .add(jLabel23)
                                            .add(jlbl_tipoCorrida)
                                            .add(jLabel25)
                                            .add(jtxt_TicketSalida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                            .add(jLabel22)
                                            .add(jlbl_origen2)
                                            .add(jLabel24)
                                            .add(jlbl_destino2)
                                            .add(jLabel26)
                                            .add(jLabel28)
                                            .add(jlbl_claveCorrida)
                                            .add(jlbl_ruta))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 265, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(layout.createSequentialGroup()
                                .add(19, 19, 19)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel5)
                                    .add(jLabel7)
                                    .add(jLabel6))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jlbl_origen)
                                    .add(jlbl_servicio)
                                    .add(jlbl_autobus))))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(16, 16, 16)
                                .add(jLabel3))
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)))
                        .add(9, 9, 9))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jLabel4)
                        .add(460, 460, 460)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlbl_barraEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_barraEstadoKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
        /*
        if(evt.getKeyCode() == evt.VK_F9)
        {
            this.setAlwaysOnTop(false);
            JIF_ValidarCodigoBarras f = new JIF_ValidarCodigoBarras(jlbl_claveCorrida.getText(), numAsientos, datosTarjeta.get(12).toString());
            f.setSize(990,720);
            //f.setAlwaysOnTop(true);
            f.setVisible(true);
            this.setAlwaysOnTop(true);
        }
         *
         */

        if(evt.getKeyCode() == evt.VK_ENTER)
            despacharTarjeta();

    }//GEN-LAST:event_jlbl_barraEstadoKeyPressed

    private void jtxt_TicketSalidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_TicketSalidaKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();

        /*
        if(evt.getKeyCode() == evt.VK_F9)
        {
            this.setAlwaysOnTop(false);
            JIF_ValidarCodigoBarras f = new JIF_ValidarCodigoBarras(jlbl_claveCorrida.getText(), numAsientos, datosTarjeta.get(12).toString());
            f.setSize(990,720);
            //f.setAlwaysOnTop(true);
            f.setVisible(true);
            this.setAlwaysOnTop(true);
        }
         *
         */

        if(evt.getKeyCode() == evt.VK_ENTER)
            despacharTarjeta();
    }//GEN-LAST:event_jtxt_TicketSalidaKeyPressed
    
private void despacharTarjeta()
{
    if(jtxt_TicketSalida.getText().trim().length() > 1 && jtxt_TicketSalida.getText().trim().length()<6)
    {
       new jdlg_error("","Debe ingresar un Numero de Ticket Valido","¡Ticket Inválido!").setVisible(true);
       return;
    }
            if(nfol==-1)
            {
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               this.dispose();
               return;
            }
              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                this.dispose();
                return;
                }
                Vector vestado= (Vector)busquedas.facadeGeneralPuertasRemote.buscarEstadoSesion(Long.valueOf(datosTarjeta.get(13).toString()));
                 String estadoses = vestado.get(0).toString();
                 if(estadoses.equals("CERRADA")){
                     new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
                     System.exit(0);
                 } 
                Vector vasientos = new Vector();
                long nasientos =0;
                if(remoto)
                       vasientos = (Vector)busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponiblesRemoto(Long.valueOf(datosTarjeta.get(11).toString()),DBLink);
                else
                 nasientos = busquedas.facadeGeneralPuertasRemote.numeroAsientosOcupadosNoDisponibles(Long.valueOf(datosTarjeta.get(11).toString()));
                if(nasientos>0)
                {
                             this.setAlwaysOnTop(false);
                              jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Corrida Bloqueada", "La corrida esta bloqueada, espere unos segundos o ¿Desea autorizar la tarjeta?");
                              psn2.setVisible(true);
                              if(respuestaSN)
                              {
                                String respuesta = busquedas.facadeGeneralPuertasRemote.buscaFuncion(datosIniciales.get(1).toString(),"6015"); 
                                long usuarioId = Long.valueOf(datosTarjeta.get(12).toString());
                                if(respuesta.equals("encontrado"))
                                {
                                    this.setAlwaysOnTop(true);
                                    autorizado2 = ""+usuarioId;
                                }
                                else
                                {
                                  if(!abreSocketAS()){
                                   new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
                                   this.setAlwaysOnTop(true);
                                   return;
                                    }
                                  if(!validarDatosSupervisor2("6015"))
                                  {
                                      this.setAlwaysOnTop(true);
                                      return;
                                  }  
                                  else
                                  {
                                      this.setAlwaysOnTop(true);
                                      autorizacion2 = true;
                                  }
                                  this.setAlwaysOnTop(true);
                                }
                              }
                              else
                              {
                                  this.setAlwaysOnTop(true);
                                  return;
                              }
                    
                }
                 
            Vector  NESP = new Vector();
             String nombreEsquema="";
             terminal = "";
             NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreTerminal();
             terminal = NESP.get(0).toString();
             if(remoto)
             {
                 NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquemaRemoto(DBLink);
                 nombreEsquema = NESP.get(0).toString();
              }
             else
             {
                 NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreEsquema();
                 nombreEsquema = NESP.get(0).toString();
             }          
                 
            Vector TER = new Vector();
                if(remoto)
                {
                    TER = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaIdTerminalRemota(DBLink);
                    idTerminal = TER.get(0).toString();
                }   
                else
                {
                    TER = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaIdTerminal();
                    idTerminal = TER.get(0).toString();
                }
            BigInteger terCompnente2bus = BigInteger.valueOf(Long.valueOf(TER.get(0).toString()));
            String te = idTerminal;
            if(idTerminal.length()<3)
            {
               for(int i=idTerminal.length(); i<3;i++)
                   te = te+"0";
            }
            if(idTerminal.length()>=3)
             te = idTerminal.substring(0,3);
                
            idTerminal = te;
            Vector x = (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
            System.out.println("datosTarjeta: "+datosTarjeta);
            TmsCorridasVentaTbl corrida = null;
            if(remoto)
            {
                //corrida = busquedas.corridasVentaTblFacadeRemote.find((BigDecimal)datosTarjeta.get(11));
                corrida = busquedas.facadeGeneralPuertasRemote.buscaCorridaRemota(Long.valueOf(datosTarjeta.get(11).toString()),DBLink);
                if(corrida==null)
                {
                    new jdlg_error("¡La corrida no se encontro en la Base de Datos! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                    return;
                }
            }
            else
                corrida = busquedas.facadeGeneralPuertasRemote.findCorridaVenta(BigDecimal.valueOf(Long.valueOf(datosTarjeta.get(11).toString())));
            
            System.out.println("Operador nuevo: "+corrida.getOperador()+"  Operador Original: "+jlbl_claveOperador.getText());
            
            if(!corrida.getOperador().equals(jlbl_claveOperador.getText()))
            {
                  jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Cambio en corrida", "El operador de la corrida cambio por el "+corrida.getOperador()+", ¿Desea despachar la tarjeta con este cambio?");
                  psn.setVisible(true);
                  if(respuestaSN)
                  {
                      jlbl_claveOperador.setText(corrida.getOperador());
                        Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(corrida.getOperador());
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
//                            if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)                                        
//                              nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
//                            else
//                              nombreoperador = nombre.get(0)+ " " + nombre.get(2);
                            nombreoperador = nombre.get(0).toString();
                        }                
                        jlbl_nombreOperador.setText(nombreoperador);
                  }
                  else
                  {
                      this.dispose();
                      return;
                  }
            }

            System.out.println("Autobus nuevo: "+corrida.getAutobus()+"  Autobus Original: "+jlbl_autobus.getText());
            
            if(!corrida.getAutobus().equals(jlbl_autobus.getText()))
            {
                  jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Cambio en corrida", "El autobus de la corrida cambio por el "+corrida.getAutobus()+", ¿Desea despachar la tarjeta con este cambio?");
                  psn.setVisible(true);
                  if(respuestaSN)
                    jlbl_autobus.setText(corrida.getAutobus());
                  else
                  {
                      this.dispose();
                      return;
                  }
            }
            if(corrida.getEstadoCorrida().equals("D"))
            {
                //new jdlg_error("¡La corrida ya ha sido despachada!","","Corrida despachada").setVisible(true);
                  jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Cambio en corrida", "¡La corrida ya ha sido despachada!, ¿Desea reimprimir la tarjeta?");
                  psn.setVisible(true);
                  if(respuestaSN)
                  {
                    TmsTarjetasViajeTbl  tarDesp = null;                      
                    if(remoto)
                        tarDesp = busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistenteVistaPreviaRemoto(corrida.getCorridaId().longValue(), DBLink);
                    else 
                        tarDesp = busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistenteVistaPrevia(corrida.getCorridaId().longValue());
                    
                    String clave = datosTarjeta.get(0).toString();
                    if(remoto)
                    {
                         Vector vvTarjeta = busquedas.facadeGeneralPuertasRemote.buscaTarjetaCreadaRemoto(tarDesp.getCorridaId().longValue(),DBLink);
                         if(vvTarjeta.size()>0)
                         {
                             Vector vTarjeta = (Vector)vvTarjeta.get(0);
                             jlbl_folio.setText(clave.substring(0,10)+""+vTarjeta.get(0).toString());
                             String sfolio = vTarjeta.get(0).toString();
                             String sviaje = sfolio.substring(3);
                             jlbl_viaje.setText(sviaje);//
                         }
                             boolean valor = busquedas.facadeGeneralPuertasRemote.ActualizarFolioTarjetaRemoto(DBLink,tarDesp.getTarjetaViajeId().longValue(),jlbl_folio.getText());
                             if(!valor)
                             {
                                 new jdlg_error("¡No fue posible actualizar la tarjeta con folio "+jlbl_folio.getText()+"! ","Favor de hacer una reimpresiond e esta tarjeta","Error de datos").setVisible(true);
                                 return;
                             }                         //ActualizarFolioTarjeta
                    }
                    else
                    {
                         jlbl_folio.setText(clave.substring(0,10)+""+tarDesp.getTarjetaViajeId());
                         String sfolio = ""+tarDesp.getTarjetaViajeId();
                         String sviaje = sfolio.substring(3);
                         jlbl_viaje.setText(sviaje);//tar.getTarjetaViajeId());
                          System.out.println("nfoldesp = "+nfol+"     Folio Nuevdesp:"+jlbl_folio.getText());
                              tarDesp.setFolioTarjeta(jlbl_folio.getText());
                              busquedas.facadeGeneralPuertasRemote.ActualizarFolioTarjeta(tarDesp.getTarjetaViajeId().longValue(),jlbl_folio.getText() );
                    }
                jlbl_claveOperador.setText(tarDesp.getOperador());
                Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(tarDesp.getOperador());
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
                    nombreoperador = nombre.get(0).toString();
                }                
                jlbl_nombreOperador.setText(nombreoperador);
                jlbl_autobus.setText(tarDesp.getAutobus());
                    
                      copia = 1;            
                       if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
                        imprimirTarjetaLPT();
                       if(puerto.equals("RED") || puerto.equals("USB"))
                          imprimir_tarjeta_windows();
                       this.dispose();
                       return;
                  }
                  else
                  {
                      this.dispose();
                      return;     
                  }
                 
            }
            if(corrida.getEstadoCorrida().equals("E"))
            {
                new jdlg_error("¡La corrida ha sido cerrada!","","Corrida cerrada").setVisible(true);
                      this.dispose();
                      return;
            }
            if(corrida.getEstadoCorrida().equals("C"))
            {
                new jdlg_error("¡La corrida ha sido cancelada!","","Corrida cancelada").setVisible(true);
                      this.dispose();
                      return;
            }
            
            corrida.setEstadoCorrida("D");
            corrida.setAdicional4("PSD");
            corrida.setReplicacionOrigen(nombreEsquema);
            TmsTarjetasViajeTbl  tarExist = null;
            if(remoto)
                tarExist = busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistenteVistaPreviaRemoto(corrida.getCorridaId().longValue(), DBLink);
            else 
                tarExist = busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistenteVistaPrevia(corrida.getCorridaId().longValue());
            BigDecimal nc = BigDecimal.valueOf(Long.valueOf(datosTarjeta.get(11).toString()));
            List<TmsEstadosCorridaTbl> listaestado = busquedas.facadeGeneralPuertasRemote.buscarPorLetra("D");
            if(listaestado.size()==0)
            {
                new jdlg_advertencia("¡No hay estados datos de alta!"," Favor de contactar al Adiministrador del Sistema","").setVisible(true);
                return;
            }
            //busca estado de la tarjeta abierta
            //Vector vedotar = (Vector)busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje();
            //Vector edotar = (Vector)vedotar.get(0);
            TmsEstadosCorridaTbl estado =  listaestado.get(0);
            TmsCorridasTbl corridan = null;
            if(remoto)
            {
               corridan = busquedas.facadeGeneralPuertasRemote.buscaCorridaSolaRemota(Long.valueOf(datosTarjeta.get(11).toString()),DBLink);
               if(corridan==null){
                    new jdlg_error("¡La corrida no se encontro en la Base de Datos! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                    return;
               }
            }
            else
            {
                corridan = busquedas.facadeGeneralPuertasRemote.findCorrida(BigDecimal.valueOf(Long.valueOf(datosTarjeta.get(11).toString())));
               if(corridan==null){
                    new jdlg_error("¡La corrida no se encontro en la Base de Datos! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                    return;
               }
            }
            System.out.println("Clave Corrida: "+corridan.getCorridaId()+"-"+corridan.getClaveCorrida());
            System.out.println("Tipo Corrida: "+corridan.getTipoCorrida());
            if(corridan.getTipoCorrida().equals("N"))
                jlbl_tipoCorrida.setText("ROL");
            else
                jlbl_tipoCorrida.setText("EXTRA");
            System.out.println("busca la corrida: "+BigDecimal.valueOf(Long.valueOf(datosTarjeta.get(11).toString())));
            corridan.setEstadoCorridaId(estado);
            corridan.setAdicional4("PSD");
            corridan.setReplicacionOrigen(nombreEsquema);
            corridan.setUltimaActualizacionPor(BigInteger.valueOf(Long.valueOf(datosIniciales.get(0).toString())));
            corridan.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
            //TmsEstadosTarjetaViajeTbl estado = busquedas.estadosTarjetaViajeTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf("0")));
            System.out.println("Corrida: "+corrida.getClaveCorrida());
           if(tarExist!=null)
           {
                System.out.println("Entra a if(tarExist!=null).... osea que ya existe una tarjeta de viaje y esta corrida ya fue despachada anteriormente");
                Vector vvedotarCent;
                 if(remoto)
                        vvedotarCent = (Vector)busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistenteRemoto(DBLink,tarExist.getFolioTarjeta());
                 else
                        vvedotarCent = (Vector)busquedas.facadeGeneralPuertasRemote.buscaTarjetaViajeExistente(tarExist.getFolioTarjeta());
                if(vvedotarCent.size()>0)
                {
                    Vector vedotarCent = (Vector)vvedotarCent.get(0);
                    if(vedotarCent.get(0).toString().equals("RECAUDADA"))
                    {
                        new jdlg_error("¡La tarjeta ya ha sido recaudada, ya no puede ser despachada ! ","Favor de contactar al Administrador del Sistema ","Tarjeta Recaudada").setVisible(true);
                        return;
                    }
                }
                
                if(remoto)
                {
                 int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorrida(corridan.getCorridaId().longValue(),estado.getEstadoCorridaId().longValue(),corridan.getAdicional4(),nombreEsquema,DBLink);
                 if(valor == 1)
                 {
                     new jdlg_error("¡La corrida "+corridan.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                     return;
                 }
                 int valorv = busquedas.facadeGeneralPuertasRemote.ActualizarCorridaVenta(corrida.getCorridaId().longValue(),"D",nombreEsquema,corrida.getAdicional4(),DBLink);
                 if(valorv == 1)
                 {
                     new jdlg_error("¡La corrida "+corrida.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                     return;
                 }
                }
                else
                {    
//                 busquedas.facadeGeneralPuertasRemote.editCorridaVenta(corrida);
//                 busquedas.facadeGeneralPuertasRemote.editCorrida(corridan);
                     int valor = busquedas.facadeGeneralPuertasRemote.editCorridaVenta(corrida);
                     if(valor==1)
                     {
                         new jdlg_error("¡La corrida "+corrida.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                     int valor2 = busquedas.facadeGeneralPuertasRemote.editCorrida(corridan);
                     if(valor==1)
                     {
                         new jdlg_error("¡La corrida "+corridan.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                    
                }
                tarExist.setEstadoTarjetaId(BigInteger.valueOf(edotar));
                tarExist.setReplicacionOrigen(nombreEsquema);
                new jdlg_advertencia("¡Esta corrida ya ha sido despachada con anterioridad con el operador "+tarExist.getOperador()+"!","Se imprimira una copia de la tarjeta despachada con los datos originales","Tarjeta Existente").setVisible(true);
                jlbl_claveOperador.setText(tarExist.getOperador());
                Vector vnombre = (Vector) busquedas.facadeGeneralPuertasRemote.buscarNombreOperador(tarExist.getOperador());
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
                jlbl_nombreOperador.setText(nombreoperador);
                jlbl_autobus.setText(tarExist.getAutobus());
                String clave = datosTarjeta.get(0).toString();
                jlbl_folio.setText(clave.substring(0,10)+""+tarExist.getTarjetaViajeId());
                String sfolio = ""+tarExist.getTarjetaViajeId();
                String sviaje = sfolio.substring(3);
                jlbl_viaje.setText(sviaje);//""+tarExist.getTarjetaViajeId());
                long   noAdulTarexist = 0;
                double mtoAdulTarexist = Float.valueOf("0.0").doubleValue();
                long   noMenTarexist = 0;
                double mtoMenTarexist = Float.valueOf("0.0").doubleValue();
                long   noSenTarexist = 0;
                double mtoSenTarexist = Float.valueOf("0.0").doubleValue();
                long   noEstudTarexist = 0;
                double mtoEstudTarexist = Float.valueOf("0.0").doubleValue();
                long   noProfTarexist = 0;
                double mtoProfTarexist = Float.valueOf("0.0").doubleValue();
                long   noEspTarexist = 0;
                double mtoEspTarexist = Float.valueOf("0.0").doubleValue();
                
                   for (int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
                   {
                       if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ADULTO"))
                       {
                           tarExist.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                            noAdulTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                            mtoAdulTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();           
                       }
                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("MENOR"))               
                        {
                           tarExist.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noMenTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoMenTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();       
                        }

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("SENECTUD"))               
                        {
                           tarExist.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noSenTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoMenTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESTUDIANTE"))               
                        {
                           tarExist.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noEstudTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoEstudTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }               

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("PROFESOR"))               
                        {
                           tarExist.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noProfTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoProfTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }    

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESPECIAL"))               
                        {
                           tarExist.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarExist.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noEspTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoEspTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }    
                   }
                   long nus = Long.valueOf(datosTarjeta.get(12).toString());
                   BigInteger usr= BigInteger.valueOf(nus);
                   tarExist.setUltimaActualizacionPor(usr);
                   tarExist.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                   
                   //long   edotarexist = edotar;//Long.valueOf(edotar.get(0).toString());
                   boolean valor;
                   if(remoto)
                     valor = busquedas.facadeGeneralPuertasRemote.ActualizarTarjetaExistenteRemota(noAdulTarexist,mtoAdulTarexist,noMenTarexist,mtoMenTarexist,noProfTarexist,mtoProfTarexist,noSenTarexist,mtoSenTarexist,noEstudTarexist,mtoEstudTarexist,noEspTarexist,mtoEspTarexist,edotar,nombreEsquema,DBLink,tarExist.getTarjetaViajeId().longValue(),nus);
                   else
                     valor = busquedas.facadeGeneralPuertasRemote.ActualizarTarjetaExistente(noAdulTarexist,mtoAdulTarexist,noMenTarexist,mtoMenTarexist,noProfTarexist,mtoProfTarexist,noSenTarexist,mtoSenTarexist,noEstudTarexist,mtoEstudTarexist,noEspTarexist,mtoEspTarexist,edotar,nombreEsquema,tarExist.getTarjetaViajeId().longValue(),nus);
                    //busquedas.tarjetasViajeTblFacadeRemote.edit(tarExist);
                     if(!valor)
                     {
                         new jdlg_error("¡No fue posible actualizar la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                         return;
                     }
                       
                ////////////////////////////////// Actualiza Estados de Operadpr y Autobus //////////////////////////////////                    
//                    TmsAutobusesTbl autobus = busquedas.facadeGeneralPuertasRemote.buscaBusPorNumero(jlbl_autobus.getText());
//                    TmsOperadoresTbl operador = busquedas.facadeGeneralPuertasRemote.buscaOperadorPorNombre(jlbl_claveOperador.getText());
//                    TmsEstadosTbl edoComp1= busquedas.facadeGeneralPuertasRemote.buscaEstadoPorNombre("ENROLADO");
//                    TmsEstadosTbl edoComp3= busquedas.facadeGeneralPuertasRemote.buscaEstadoPorNombre("CORRIDA");
//                    TmsAccionesTbl accComp1 = busquedas.facadeGeneralPuertasRemote.buscaAccionPorNombre("ENROLADO");
//                    TmsAccionesTbl accComp3 = busquedas.facadeGeneralPuertasRemote.buscaAccionPorNombre("CORRIDA");
//                    Vector vvComp3 = (Vector) busquedas.facadeGeneralPuertasRemote.queryBuscaTerminalOperador();
//                        /////Bus
//                        //ENROLADO
//                        //terCompnente2bus
//                        //CORRIDA
//                    if(edoComp1!=null)
//                     autobus.setComponente1Id(edoComp1.getEstadoId().toBigInteger());
//                    else
//                     new jdlg_error("¡El estado ENROLADO del Autobus esta mal configurado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    autobus.setComponente2Id(terCompnente2bus);
//                    if(edoComp3!=null)
//                     autobus.setComponente3Id(edoComp3.getEstadoId().toBigInteger());
//                    else
//                     new jdlg_error("¡La Actividad CORRIDA del Autobus esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                        /////Operador
//                        //ENROLADO
//                        //Vector vvComp3 = (Vector) busquedas.variosFacadeRemote.queryBuscaTerminalOperador();
//                        //CORRIDA
//                     
//                    if(edoComp1!=null)
//                     operador.setAccion1Id(accComp1);
//                    else
//                     new jdlg_error("¡La Accion ENROLADO del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(vvComp3.size()>0)
//                    {
//                        Vector vComp3 = (Vector)vvComp3.get(0);
//                        BigDecimal comp3 = (BigDecimal)vComp3.get(0);
//                        TmsAccionesTbl accComp2 = busquedas.facadeGeneralPuertasRemote.findAccion(BigDecimal.valueOf(Long.valueOf(comp3.toString())));
//                        operador.setAccion2Id(accComp2);
//                    }
//                    else
//                     new jdlg_error("¡La terminal de Acciones del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(edoComp3!=null)
//                     operador.setAccion3Id(accComp3);
//                    else
//                     new jdlg_error("¡La Accion CORRIDA del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    busquedas.facadeGeneralPuertasRemote.editOperador(operador);
//                    busquedas.facadeGeneralPuertasRemote.editAutobus(autobus);
                    if(remoto)
                        busquedas.facadeGeneralPuertasRemote.actualizarEstadosOperBus(jlbl_claveOperador.getText(),jlbl_autobus.getText(),DBLink, (long)1, datosTarjeta.get(15).toString(), nombreEsquema );
                    else
                        busquedas.facadeGeneralPuertasRemote.actualizarEstadosOperBus(jlbl_claveOperador.getText(),jlbl_autobus.getText(),"", (long)0, datosTarjeta.get(15).toString(), nombreEsquema );
                 /////////////////////////////////////////////////////////////////////////////////////////////////////////
                   
           }
           else
           {
//                   busquedas.corridasVentaTblFacadeRemote.edit(corrida);
//                   busquedas.corridasTblFacadeRemote.edit(corridan);
                System.out.println("entra al otro caso que no es if(tarExist!=null)... osea que la tarjeta no existe!"); 
                    if(remoto)
                    {
                     int valor = busquedas.facadeGeneralPuertasRemote.ActualizarCorrida(corridan.getCorridaId().longValue(),estado.getEstadoCorridaId().longValue(),nombreEsquema,corridan.getAdicional4(),DBLink);
                     System.out.println("Valor = "+valor);
                     if(valor == 1)
                     {
                         new jdlg_error("¡La corrida "+corridan.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                     int valorv = busquedas.facadeGeneralPuertasRemote.ActualizarCorridaVenta(corrida.getCorridaId().longValue(),"D",nombreEsquema,corrida.getAdicional4(),DBLink);
                     System.out.println("Valov = "+valorv);
                     if(valorv==1)
                     {
                         new jdlg_error("¡La corrida "+corrida.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                    }
                    else
                    {    
                     int valor = busquedas.facadeGeneralPuertasRemote.editCorridaVenta(corrida);
                     if(valor==1)
                     {
                         new jdlg_error("¡La corrida "+corrida.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                     int valor2 = busquedas.facadeGeneralPuertasRemote.editCorrida(corridan);
                     if(valor==1)
                     {
                         new jdlg_error("¡La corrida "+corridan.getClaveCorrida()+" esta bloqueada! ","Favor de intentar nuevamente","Corrida ocupada").setVisible(true);
                         return;
                     }
                    }
                
                   TmsTarjetasViajeTbl tarjeta = new TmsTarjetasViajeTbl();
                   tarjeta.setFolioTarjeta(jlbl_folio.getText());
                   tarjeta.setCorridaId(BigInteger.valueOf(nc.longValue()));//corridan);
                   tarjeta.setAutobus(jlbl_autobus.getText());
                   tarjeta.setOperador(jlbl_claveOperador.getText());
                   tarjeta.setEstadoTarjetaId(BigInteger.valueOf(edotar));
                   tarjeta.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));

                   tarjeta.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));

                   tarjeta.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));

                   tarjeta.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));

                   tarjeta.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));

                   tarjeta.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
                   tarjeta.setNumeroImpresion(BigInteger.valueOf(Long.valueOf("0")));
                   tarjeta.setReplicacionOrigen(nombreEsquema);
                long   noAdultarjeta = 0;
                double mtoAdultarjeta = Float.valueOf("0.0").doubleValue();
                long   noMentarjeta = 0;
                double mtoMentarjeta = Float.valueOf("0.0").doubleValue();
                long   noSentarjeta = 0;
                double mtoSentarjeta = Float.valueOf("0.0").doubleValue();
                long   noEstudtarjeta = 0;
                double mtoEstudtarjeta = Float.valueOf("0.0").doubleValue();
                long   noProftarjeta = 0;
                double mtoProftarjeta = Float.valueOf("0.0").doubleValue();
                long   noEsptarjeta = 0;
                double mtoEsptarjeta = Float.valueOf("0.0").doubleValue();
                
                   for (int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
                   {
                       if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ADULTO"))
                       {
                           tarjeta.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                            noAdultarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                            mtoAdultarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();           
                       }
                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("MENOR"))               
                        {
                           tarjeta.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noMentarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoMentarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();       
                        }

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("SENECTUD"))               
                        {
                           tarjeta.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noSentarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoMentarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESTUDIANTE"))               
                        {
                           tarjeta.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noEstudtarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoEstudtarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }               

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("PROFESOR"))               
                        {
                           tarjeta.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noProftarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoProftarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }    

                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESPECIAL"))               
                        {
                           tarjeta.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
                           tarjeta.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
                           noEsptarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                           mtoEsptarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
                        }    
                   }

                   long nus = Long.valueOf(datosTarjeta.get(12).toString());
                   BigInteger usr= BigInteger.valueOf(nus);
                   tarjeta.setCreadoPor(usr);
                   tarjeta.setUltimaActualizacionPor(usr);
                   tarjeta.setFechaCreacion(new Date(fecha_servidor.getTime()));
                   tarjeta.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                   tarjeta.setAdicional3(jtxt_TicketSalida.getText());
                   if(autorizacion)
                   {
                        Vector vnumerousr= (Vector)busquedas.facadeGeneralPuertasRemote.buscaNumeroUsuario(nus);
                        tarjeta.setAdicional1("El usuario "+vnumerousr.get(0).toString()+" autorizo despachar la tarjeta aun cuando el operador tenia "+ntarpen+" tarjetas pendientes por recaudar a las "+formathc.format(fecha_servidor.getTime())+" del "+formatfc.format(fecha_servidor.getTime()));
                   }
                   if(autorizacion2)
                   {
                        long nus2 = Long.valueOf(datosTarjeta.get(14).toString());
                        BigInteger usr2= BigInteger.valueOf(nus2);
                        Vector vnumerousr= (Vector)busquedas.facadeGeneralPuertasRemote.buscaNumeroUsuario(nus2);
                        tarjeta.setAdicional2("El usuario "+usr2+" autorizo despachar la tarjeta aun cuando la tarjeta estaba bloqueda, a las "+formathc.format(fecha_servidor.getTime())+" del "+formatfc.format(fecha_servidor.getTime()));
                       tarjeta.setUltimaActualizacionPor(usr2);
                   }
                   TmsTarjetasViajeTbl tar = null; 
                    if(remoto)
                    {
                      //boolean valor = busquedas.variosRemotoFacadeRemote.insertarTarjeta(tarjeta,idTerminal,DBLink);
                      System.out.println("Entra a despachar tarjeta remota: "+tarjeta.getFolioTarjeta());
                       tar = busquedas.facadeGeneralPuertasRemote.insertarTarjetaRemoto(tarjeta,idTerminal,DBLink);
                      //if(!valor)
                      if(tar==null)
                      {
                         new jdlg_error("¡No fue posible despachar la tarjeta de la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                         return;
                      }
                    }
                    else
                      tar = busquedas.facadeGeneralPuertasRemote.createTarjetaViaje(tarjeta, idTerminal);

                  ////// Cambia de estado al operador y al Autobus /////
//                    TmsAutobusesTbl autobus = busquedas.facadeGeneralPuertasRemote.buscaBusPorNumero(jlbl_autobus.getText());
//                    TmsOperadoresTbl operador = busquedas.facadeGeneralPuertasRemote.buscaOperadorPorNombre(jlbl_claveOperador.getText());
//                    TmsEstadosTbl edoComp1  = busquedas.facadeGeneralPuertasRemote.buscaEstadoPorNombre("ENROLADO");
//                    TmsEstadosTbl edoComp3  = busquedas.facadeGeneralPuertasRemote.buscaEstadoPorNombre("CORRIDA");
//                    TmsAccionesTbl accComp1 = busquedas.facadeGeneralPuertasRemote.buscaAccionPorNombre("ENROLADO");
//                    TmsAccionesTbl accComp3 = busquedas.facadeGeneralPuertasRemote.buscaAccionPorNombre("CORRIDA");
//                    
//                    Vector vvComp3 = (Vector) busquedas.facadeGeneralPuertasRemote.queryBuscaTerminalOperador();
//                        /////Bus
//                        //ENROLADO
//                        //terCompnente2bus
//                        //CORRIDA
//                    if(edoComp1!=null)
//                     autobus.setComponente1Id(edoComp1.getEstadoId().toBigInteger());
//                    else
//                     new jdlg_error("¡El estado ENROLADO del Autobus esta mal configurado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    autobus.setComponente2Id(terCompnente2bus);
//                    if(edoComp3!=null)
//                     autobus.setComponente3Id(edoComp3.getEstadoId().toBigInteger());
//                    else
//                     new jdlg_error("¡La Actividad CORRIDA del Autobus esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                        /////Operador
//                        //ENROLADO
//                        //Vector vvComp3 = (Vector) busquedas.variosFacadeRemote.queryBuscaTerminalOperador();
//                        //CORRIDA
//                     
//                    if(edoComp1!=null)
//                     operador.setAccion1Id(accComp1);
//                    else
//                     new jdlg_error("¡La Accion ENROLADO del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(vvComp3.size()>0)
//                    {
//                        Vector vComp3 = (Vector)vvComp3.get(0);
//                        BigDecimal comp3 = (BigDecimal)vComp3.get(0);
//                        TmsAccionesTbl accComp2 = busquedas.facadeGeneralPuertasRemote.findAccion(BigDecimal.valueOf(Long.valueOf(comp3.toString())));
//                        operador.setAccion2Id(accComp2);
//                    }
//                    else
//                     new jdlg_error("¡La terminal de Acciones del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(edoComp3!=null)
//                     operador.setAccion3Id(accComp3);
//                    else
//                     new jdlg_error("¡La Accion CORRIDA del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    busquedas.facadeGeneralPuertasRemote.editOperador(operador);
//                    busquedas.facadeGeneralPuertasRemote.editAutobus(autobus);
                    if(remoto)
                        busquedas.facadeGeneralPuertasRemote.actualizarEstadosOperBus(jlbl_claveOperador.getText(),jlbl_autobus.getText(),DBLink, (long)1, datosTarjeta.get(15).toString(), nombreEsquema);
                    else
                        busquedas.facadeGeneralPuertasRemote.actualizarEstadosOperBus(jlbl_claveOperador.getText(),jlbl_autobus.getText(),"", (long)0, datosTarjeta.get(15).toString(), nombreEsquema);
                  /////////////////////////////////////////////////
                    
                    String clave = datosTarjeta.get(0).toString();
                    if(remoto)
                    {
                         Vector vvTarjeta = busquedas.facadeGeneralPuertasRemote.buscaTarjetaCreadaRemoto(tar.getCorridaId().longValue(),DBLink);
                         if(vvTarjeta.size()>0)
                         {
                             Vector vTarjeta = (Vector)vvTarjeta.get(0);
                             jlbl_folio.setText(clave.substring(0,10)+""+vTarjeta.get(0).toString());
                             String sfolio = vTarjeta.get(0).toString();
                             String sviaje = sfolio.substring(3);
                             jlbl_viaje.setText(sviaje);//
                             //jlbl_viaje.setText(vTarjeta.get(0).toString());
                         }
//                         if(nfol==1)
//                         {
                             boolean valor = busquedas.facadeGeneralPuertasRemote.ActualizarFolioTarjetaRemoto(DBLink,tar.getTarjetaViajeId().longValue(),jlbl_folio.getText());
                             if(!valor)
                             {
                                 new jdlg_error("¡No fue posible actualizar la tarjeta con folio "+jlbl_folio.getText()+"! ","Favor de hacer una reimpresion de esta tarjeta","Error de datos").setVisible(true);
                                 return;
                             }                         //ActualizarFolioTarjeta
//                         }
                    }
                    else
                    {
                         jlbl_folio.setText(clave.substring(0,10)+""+tar.getTarjetaViajeId());
                         String sfolio = ""+tar.getTarjetaViajeId();
                         String sviaje = sfolio.substring(3);
                         jlbl_viaje.setText(sviaje);//tar.getTarjetaViajeId());
                          System.out.println("nfol = "+nfol+"     Folio Nuevo:"+jlbl_folio.getText());
//                          if(nfol==1)
//                          {
                              tar.setFolioTarjeta(jlbl_folio.getText());
                              //busquedas.facadeGeneralPuertasRemote.ActualizarFolioTarjeta(tar.getTarjetaViajeId().longValue(),jlbl_folio.getText() );
//                        }
                    }
            
           }
       if(proviene!=2)
       {
        modeloDetalleAbordo.setDataVector(null,encabezadoDetalle);
        modeloBoletos.setDataVector(null,encabezadoBoletos);
            String[] row = {"  "," "," "," ","  "," "," "," ","  "," "," "," ","  "," "," "," "};
        modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);
        modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);modeloBoletos.addRow(row);
        int nc2 = 0;
        int nr2 = 0;
        for(int i=1; i<60; i++)
        {
             if(i>numAsientos)break;
             if(i==16){nc2=4;nr2=0;}
             if(i==31){nc2=8;nr2=0;}
             if(i==46){nc2=12;nr2=0;}
             if(i<=9)
               jtbl_boletosDetalle.setValueAt(" "+i,nr2,nc2);
             else
               jtbl_boletosDetalle.setValueAt(""+i,nr2,nc2);
             nr2++;
        }
        
        String[] rowDetalle ={"ADULTOS", "6","239.00"};
        modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);
        modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);modeloDetalleAbordo.addRow(rowDetalle);
                Vector ocupacionSistema = new Vector();
                    if(remoto)
                       ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistemaRemoto(jlbl_claveCorrida.getText(),DBLink);
                    else
                        ocupacionSistema  = (Vector)busquedas.facadeGeneralPuertasRemote.buscaDatosOcupacionPorSistema(jlbl_claveCorrida.getText());
                   ocupacion = ocupacionSistema;
                   if(proviene==3)
                   {
                       System.out.println("entra aproviene==3...");
                        Object[][] ocupacionS = new Object[tipos.size()][4];
                        Vector vec =  new Vector();
                        for(int i=0; i<tipos.size();i++){
                            Vector vtipo = (Vector) tipos.get(i);
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
//                             ocupacionS[i][0]=vtipo.get(0).toString();
//                             ocupacionS[i][1]=""+conteo;
//                             ocupacionS[i][2]=""+total;

                            if(conteo>0 || vtipo.get(0).toString().equals("ESTUDIANTE") || vtipo.get(0).toString().equals("ADULTO") || vtipo.get(0).toString().equals("MENOR") || vtipo.get(0).toString().equals("SENECTUD") || vtipo.get(0).toString().equals("PROFESOR") )
                            {
                              Vector vocupacionSistema = new Vector();
                              vocupacionSistema.add(vtipo.get(0).toString());
                              vocupacionSistema.add(conteo);
                              vocupacionSistema.add(total);
                              vec.add(ocupacionS);

                            }
                        }
                    System.out.println("vencabezadoDetalle: "+vencabezadoDetalle);
                    System.out.println("vec: "+vec);
                    sinOcupacion = new Object[vec.size()][4];
                    for(int k=0; k<vec.size(); k++)
                    {
                        Vector v= (Vector)vec.get(k);
                        sinOcupacion[k][0]=v.get(0);
                        sinOcupacion[k][1]=v.get(1);
                        sinOcupacion[k][2]=v.get(2);
                    }
                    //sinOcupacion = ocupacionS;
                    llenarOcupacion3();
                   }
                   else
                   {
                        if(proviene==1)
                        {
                          llenarOcupacion1();
                        }
                     }
       }
           copia = 0;            
           if(puerto.equals("LPT1") || puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
              imprimirTarjetaLPT();
           if(puerto.equals("RED") || puerto.equals("USB"))
              imprimir_tarjeta_windows();
           //new jdlg_informacion("¡La tarjeta fue despachada correctamente!","","Tarjeta despachada").setVisible(true);
           this.dispose();    
}


    public void imprimir_tarjeta_windows(){
        imprimir_recibo_tarjeta imp = new imprimir_recibo_tarjeta();
            imp.ImprimeDatos();
        Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de tarjeta ",""+copia);
        p.setVisible(true);
        if(reimpresion)
        {
            copia++;
            imprimir_tarjeta_windows();
        }
    }    
    
private void imprimirTarjetaLPT(){
            
            
            char rc = 13;
            char spc= 10;
            int conteo = 0;
            int nrows = (jtbl_detalleAbordo.getRowCount() -1);
            String cp = "";
            if(copia != 0)
                cp = "Copia No "+copia;
            else
                cp = "         ";
            String ticket = jtxt_TicketSalida.getText().trim();
            if(!ticket.equals(""))
                ticket = "TICKET SALIDA: "+ticket;
            for(int i=ticket.length(); i<=30; i++)
                ticket = ticket+" ";
            String CodigoImpresora =""+
"                                                                                     "+datosTarjeta.get(15).toString().toUpperCase()+"\n"+rc+//AUTOBUSES MEXPUE ESTRELLA ROJA\n"+rc+
"                                                                                                    TARJETA DE VIAJE\n"+rc+      
"                                                                                            ORIGEN       SERVICIO       AUTOBUS   \n"+rc+  
"                                                                                            ---------  -------------     -------    \n"+rc+
" "+cp+"                                                                                  "+jlbl_origen.getText()+"     "+jlbl_servicio.getText()+"    "+jlbl_autobus.getText()+"    \n"+rc+
"                                                                                            DESCRIPCION DEL VIAJE       \n"+rc+
"                                                                                            "+jlbl_descipcionViaje.getText()+"          \n"+rc+
"                                                                                          FOLIO             OPERADOR    FECHA      HORA\n"+rc+  
"                                                                                          ----------------  --------  ----------  -----\n"+rc+
" "+ticket+"                                                      "+jlbl_folio.getText()+"     "+jlbl_claveOperador.getText()+"    "+jlbl_fecha.getText()+"  "+jlbl_hora.getText()+"\n"+rc+
"                                                                                              "+jlbl_nombreOperador.getText()+"        \n"+rc+
" ORIGEN : "+jlbl_origen2.getText()+"   DESTINO : "+jlbl_destino2.getText()+"   RUTA : "+jlbl_ruta.getText()+"  CORRIDA : "+jlbl_claveCorrida.getText()+"  TIPO CORRIDA : "+jlbl_tipoCorrida.getText()+"\n"+rc+
" -------------------------------------------------------------------------------------------                  RESUMEN DE VIAJE         \n"+rc+
"|# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |        TIPO PAS   # BOL    TOTAL\n"+rc+
"|--|-----|-|-----------|--|-----|-|-----------|--|-----|-|-----------|--|-----|-|-----------|        ---------  -----  ---------\n";
 String num = jtbl_boletosDetalle.getValueAt(conteo,0).toString(); String cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString(); String t = jtbl_boletosDetalle.getValueAt(conteo,2).toString(); String boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 int tamañoCd = cd.length(); int tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
 String num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString(); String cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString(); String t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString(); String boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
 int tamañoCd2 = cd2.length(); int tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
 String num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString(); String cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString(); String t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString(); String boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
 int tamañoCd3 = cd3.length(); int tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
 String num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString(); String cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString(); String t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString(); String boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
 int tamañoCd4 = cd4.length(); int tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(0,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(0,1).toString();String total = jtbl_detalleAbordo.getValueAt(0,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++)tipo=tipo+" ";} else tipo = tipo.substring(0,11); 
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8)+" ";
 CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|                                             "+"\n"+rc;
}
 
conteo++;             
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(1,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(1,1).toString(); String total = jtbl_detalleAbordo.getValueAt(1,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else tipo = tipo.substring(0,11);
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else {
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(2,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(2,1).toString(); String total = jtbl_detalleAbordo.getValueAt(2,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(3,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(3,1).toString();String total = jtbl_detalleAbordo.getValueAt(3,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(4,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(4,1).toString();String total = jtbl_detalleAbordo.getValueAt(4,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(5,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(5,1).toString();String total = jtbl_detalleAbordo.getValueAt(5,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}

conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(6,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(6,1).toString();String total = jtbl_detalleAbordo.getValueAt(6,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;                               
}

conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(7,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(7,1).toString();String total = jtbl_detalleAbordo.getValueAt(7,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(8,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(8,1).toString();String total = jtbl_detalleAbordo.getValueAt(8,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(9,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(9,1).toString();String total = jtbl_detalleAbordo.getValueAt(9,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(10,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(10,1).toString();String total = jtbl_detalleAbordo.getValueAt(10,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(11,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(11,1).toString();String total = jtbl_detalleAbordo.getValueAt(11,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);

CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               "+"\n"+rc;
}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(12,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(12,1).toString();String total = jtbl_detalleAbordo.getValueAt(12,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| FIRMA __________________   DIGITE: "+jlbl_viaje.getText()+"\n"+rc; 
}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(13,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(13,1).toString();String total = jtbl_detalleAbordo.getValueAt(13,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total+"\n"+rc;
}
else
{
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Fecha Impresion: "+formatfc.format(fecha_servidor.getTime())+" "+formathc.format(fecha_servidor.getTime())+" \n"+rc;
}
conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Terminal: "+terminal+" \n"+rc+
" -------------------------------------------------------------------------------------------  * Boletos Vendidos por Internet \n\n"+rc;                       
                    
                    
                    
                    
                    
                    
                    
//" ORIGEN : "+jlbl_origen2.getText()+"           DESTINO : "+jlbl_destino2.getText()+" RUTA : "+jlbl_ruta.getText()+"  CORRIDA : "+jlbl_claveCorrida+"\n"+rc+
//" -----------------------------------------------------------------------------------                         RESUMEN DE VIAJE         \n"+rc+
//"|# | CD  |T| BOLETO  |# | CD  |T| BOLETO  |# | CD  |T| BOLETO  |# | CD  |T| BOLETO  |               TIPO PAS  # BOL ADI       TOTAL"+rc+
//"|--|-----|-|---------|--|-----|-|---------|--|-----|-|---------|--|-----|-|---------|               --------- ----- ----- ---------"+rc+
//"| 1|     |A|         |16|     | |         |31|     | |         |46|     | |         |               "+jtbl_detalleAbordo.getValueAt(0,0).toString()+"       "+jtbl_detalleAbordo.getValueAt(0,1).toString()+"          "+jtbl_detalleAbordo.getValueAt(0,2).toString()+""+rc+
//"| 2|     |M|         |17|     | |         |32|     | |         |47|     | |         |               "+jtbl_detalleAbordo.getValueAt(1,0).toString()+"      "+jtbl_detalleAbordo.getValueAt(1,1).toString()+"            "+jtbl_detalleAbordo.getValueAt(1,2).toString()+""+rc+
//"| 3|     |L|         |18|     | |         |33|     | |         |48|     | |         |               "+jtbl_detalleAbordo.getValueAt(2,0).toString()+"      "+jtbl_detalleAbordo.getValueAt(2,1).toString()+"            "+jtbl_detalleAbordo.getValueAt(2,2).toString()+""+rc+
//"| 4|     |S|         |19|     | |         |34|     | |         |  |     | |         |               "+jtbl_detalleAbordo.getValueAt(3,0).toString()+"       "+jtbl_detalleAbordo.getValueAt(3,1).toString()+"            "+jtbl_detalleAbordo.getValueAt(3,2).toString()+""+rc+
//"| 5|     |E|         |20|     | |         |35|     | |         |  |     | |         |               "+jtbl_detalleAbordo.getValueAt(4,0).toString()+"      "+jtbl_detalleAbordo.getValueAt(4,1).toString()+"            "+jtbl_detalleAbordo.getValueAt(4,2).toString()+""+rc+
//"| 6|     |P|         |21|     | |         |36|     | |         |  |     | |         |               "+jtbl_detalleAbordo.getValueAt(5,0).toString()+"      "+jtbl_detalleAbordo.getValueAt(5,1).toString()+"            "+jtbl_detalleAbordo.getValueAt(5,2).toString()+""+rc+
//"| 7|     |W|         |22|     | |         |37|     | |         |  |     | |         |                                              "+rc+
//"| 8|     | |         |23|     | |         |38|     | |         |  |     | |         |               "+jtbl_detalleAbordo.getValueAt(7,0).toString()+"         "+jtbl_detalleAbordo.getValueAt(7,1).toString()+"           "+jtbl_detalleAbordo.getValueAt(7,2).toString()+""+rc+
//"| 9|     | |         |24|     | |         |39|     | |         |  |     | |         |                                              "+rc+
//"|10|     | |         |25|     | |         |40|     | |         |  |     | |         |                 FIRMA ______________________ "+rc+
//"|11|     | |         |26|     | |         |41|     | |         |  |     | |         |                                              "+rc+
//"|12|     | |         |27|     | |         |42|     | |         |  |     | |         |                                              "+rc+
//"|13|     | |         |28|     | |         |43|     | |         |  |     | |         |                                              "+rc+
//"|14|     | |         |29|     | |         |44|     | |         |  |     | |         |                                              "+rc+
//"|15|     | |         |30|     | |         |45|     | |         |  |     | |         |                                              "+rc+
//" -----------------------------------------------------------------------------------                                               "+rc;         
            
            
            FileOutputStream os = null;
            try {
                String UserHome = System.getProperty("user.home");
                if(puerto.equals("ARCHIVO"))
                 os = new FileOutputStream(UserHome+"\\"+jlbl_folio.getText()+".TXT");
                else
                     os = new FileOutputStream(puerto);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }//SalidaImpresion); // LPT1 / C:\\ARCHIVO.TXT / COM1
            PrintStream ps = new PrintStream(os); 
            ps.print(CodigoImpresora);
            try {
                os.close(); 
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
            
        Jdlg_Pregunta p = new Jdlg_Pregunta("Impresion de copia de Tarjeta de Viaje",""+copia);
        p.setVisible(true);
        if(reimpresion)
        {
            copia++;
            imprimirTarjetaLPT();
        }
    
}    
    



    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jdlg_vistaPreviaTarjetaViaje("Vista previa de la tarjeta de viaje", 47).setVisible(true);
            }
        });
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel jlbl_autobus;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_claveCorrida;
    private javax.swing.JLabel jlbl_claveOperador;
    private javax.swing.JLabel jlbl_descipcionViaje;
    private javax.swing.JLabel jlbl_destino2;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_folio;
    private javax.swing.JLabel jlbl_hora;
    private javax.swing.JLabel jlbl_nombreOperador;
    private javax.swing.JLabel jlbl_origen;
    private javax.swing.JLabel jlbl_origen2;
    private javax.swing.JLabel jlbl_ruta;
    private javax.swing.JLabel jlbl_servicio;
    private javax.swing.JLabel jlbl_tipoCorrida;
    private javax.swing.JLabel jlbl_viaje;
    private javax.swing.JTable jtbl_boletosDetalle;
    private javax.swing.JTable jtbl_detalleAbordo;
    private tms_TextFields.JTextTextField jtxt_TicketSalida;
    private javax.swing.JLabel jtxt_empresaTitulo;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadoBoletos = {"#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO "};
    private Object[] encabezadoDetalle = {"TIPO PAS","# BOL","TOTAL"};
    private Vector vencabezadoDetalle = new Vector();
    private DefaultTableModel modeloBoletos = new DefaultTableModel(null,encabezadoBoletos){
    public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 20)
               return true;
            return false;
        }   
    };
    private DefaultTableModel modeloDetalleAbordo = new DefaultTableModel(null,encabezadoDetalle){
    public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 20)
               return true;
            return false;
        }   
    };

    private int proviene;

    private Vector ocupacion;

    private TmsPuertasManagedBean busquedas;

    private Vector tipos;

    private Vector datosTarjeta;
    private Vector ocupacionModificada;
    private String terminal;
    private Timestamp fecha_servidor= null; 
    private SimpleDateFormat formatfc = new SimpleDateFormat ("dd/MM/yyyy");
    private SimpleDateFormat formathc = new SimpleDateFormat ("HH:mm");
    private Object[][] sinOcupacion;
    private boolean autorizacion;
    private boolean autorizacion2;
    private long ntarpen;
    private String idTerminal;
    private boolean remoto;
    private String DBLink;
    private boolean respuestaSN = true;
    private long nfol;
    private boolean reimpresion = false;

    private int copia;
    
    public Object[] getEncabezadoBoletos() {
        return encabezadoBoletos;
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



 
class Jdlg_Pregunta extends JDialog {
                            private JLabel jLabel1 = new JLabel();
                            private JLabel jLabel2 = new JLabel();
                            private JLabel jLabel3 = new JLabel();
                            private JLabel jlbl_barraEstadoP = new JLabel();
                            private ImageIcon imagen_pregunta = new ImageIcon("C:\\NetBeansProyects\\TMSPuertas\\TMSPuertas-app-client\\src\\java\\tmspuertas\\Images\\pregunta.gif");//Jdlg_Pregunta.class.getResource("pregunta.gif"));
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
                                jLabel1.setText("¿Se imprimio correctamente la tarjeta?");
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
                                if(copia == 0)
                                    jLabel3.setText("");
                                else
                                    jLabel3.setText("Copia No: "+numref);
                                jlbl_barraEstadoP.setFont(new java.awt.Font("Tahoma", 1, 12));
                                jlbl_barraEstadoP.setForeground(new java.awt.Color(0, 0, 0));
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
 

 
 class imprimir_recibo_tarjeta implements Printable{
                            private double PIXELES_POR_PULGADA = 72.0;
                            private double ANCHO = 8.5;//3.625; //DIMENSIONES DEL PAPEL (PULGADAS)
                            private double ALTO = 11;
                            private String[][] datos_imprimir;
                            private int numtarjetas;
                            
                            public imprimir_recibo_tarjeta(){
                            }

                            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                                if (page > 0) {
                                    return NO_SUCH_PAGE;
                                }

                                int conteo = 0;
                                int nrows = (jtbl_detalleAbordo.getRowCount() -1);
                                String cp = "";
                                if(copia != 0)
                                    cp = "Copia No "+copia;
                                else
                                    cp = "         ";
                                 String autobus = "";
                                 double sueldo = 0;
                                 double retencion = 0;
                                 double totaltarjeta = 0;
                                 double vta_manual =0;
                                 double vta_abordo =0;
                                Graphics2D g2d = (Graphics2D)g;
                                g2d.translate(pf.getImageableX(), pf.getImageableY());
                                Font fontVar = new Font("Courier",Font.PLAIN,7);
                                g.setFont(fontVar);
                                //Vector x = (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
                                //fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                                int lineaNueva=11, row=1;
                                g.drawString(datosTarjeta.get(15).toString().toUpperCase(),370,lineaNueva);
                                row++;
                                g.drawString("TARJETA DE VIAJE",405,lineaNueva*row);
                                row++;
                                g.drawString("ORIGEN       SERVICIO         AUTOBUS   ",370,lineaNueva*row);
                                row++;
                                g.drawString("-------- ----------------     -------    ",370,lineaNueva*row);
                                row++;
                                g.drawString(" "+cp,0,lineaNueva*row);
                                g.drawString(jlbl_origen.getText()+"     "+jlbl_servicio.getText()+"    "+jlbl_autobus.getText(),370,lineaNueva*row);
                                row++;
                                g.drawString("DESCRIPCION DEL VIAJE",370,lineaNueva*row);
                                row++;
                                g.drawString(jlbl_descipcionViaje.getText()+"          ",370,lineaNueva*row);
                                row++;
                                g.drawString("FOLIO             OPERADOR    FECHA      HORA",370,lineaNueva*row);
                                row++;
                                g.drawString("----------------  --------  ----------  -----",370,lineaNueva*row);
                                row++;
                                g.drawString(jlbl_folio.getText()+"   "+jlbl_claveOperador.getText()+"    "+jlbl_fecha.getText()+"  "+jlbl_hora.getText(),370,lineaNueva*row);
                                row++;
                                g.drawString(jlbl_nombreOperador.getText(),390,lineaNueva*row);
                                row++;
                                g.drawString(" ORIGEN : "+jlbl_origen2.getText()+"   DESTINO : "+jlbl_destino2.getText()+"   RUTA : "+jlbl_ruta.getText()+"  CORRIDA : "+jlbl_claveCorrida.getText()+"  TIPO CORRIDA : "+jlbl_tipoCorrida.getText(),0,lineaNueva*row);
                                row++;
                                g.drawString(" -------------------------------------------------------------------------------------------",0,lineaNueva*row);
                                g.drawString("RESUMEN DE VIAJE ",465,lineaNueva*row);
                                row++;
                                g.drawString("|# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |# | CD  |T| BOLETO    |",0,lineaNueva*row);
                                g.drawString("TIPO PAS   # BOL    TOTAL",430,lineaNueva*row);
                                row++;
                                g.drawString("|--|-----|-|-----------|--|-----|-|-----------|--|-----|-|-----------|--|-----|-|-----------|",0,lineaNueva*row);
                                g.drawString("---------  -----  ---------",430,lineaNueva*row);
                                row++;
 String num = jtbl_boletosDetalle.getValueAt(conteo,0).toString(); String cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString(); String t = jtbl_boletosDetalle.getValueAt(conteo,2).toString(); String boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 int tamañoCd = cd.length(); int tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
 String num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString(); String cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString(); String t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString(); String boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
 int tamañoCd2 = cd2.length(); int tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
 String num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString(); String cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString(); String t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString(); String boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
 int tamañoCd3 = cd3.length(); int tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
 String num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString(); String cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString(); String t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString(); String boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
 int tamañoCd4 = cd4.length(); int tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(0,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(0,1).toString();String total = jtbl_detalleAbordo.getValueAt(0,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++)tipo=tipo+" ";} else tipo = tipo.substring(0,11); 
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8)+" ";
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|                                             ",0,lineaNueva*row);
row++;
}

conteo++;             
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(1,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(1,1).toString(); String total = jtbl_detalleAbordo.getValueAt(1,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else tipo = tipo.substring(0,11);
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;
}
else {
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;


}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(2,0).toString(); String nbol = jtbl_detalleAbordo.getValueAt(2,1).toString(); String total = jtbl_detalleAbordo.getValueAt(2,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;


}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;
}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(3,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(3,1).toString();String total = jtbl_detalleAbordo.getValueAt(3,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;
}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(4,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(4,1).toString();String total = jtbl_detalleAbordo.getValueAt(4,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;
}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;
}


conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(5,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(5,1).toString();String total = jtbl_detalleAbordo.getValueAt(5,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}

conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(6,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(6,1).toString();String total = jtbl_detalleAbordo.getValueAt(6,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}

conteo++;    
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(7,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(7,1).toString();String total = jtbl_detalleAbordo.getValueAt(7,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(8,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(8,1).toString();String total = jtbl_detalleAbordo.getValueAt(8,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(9,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(9,1).toString();String total = jtbl_detalleAbordo.getValueAt(9,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(10,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(10,1).toString();String total = jtbl_detalleAbordo.getValueAt(10,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(11,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(11,1).toString();String total = jtbl_detalleAbordo.getValueAt(11,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);

g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|               ",0,lineaNueva*row);
row++;

}

conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(12,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(12,1).toString();String total = jtbl_detalleAbordo.getValueAt(12,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
    
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| FIRMA __________________   DIGITE: "+jlbl_viaje.getText(),0,lineaNueva*row);
row++;

}


conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
if(conteo<=nrows)
{
 String tipo = jtbl_detalleAbordo.getValueAt(13,0).toString();String nbol = jtbl_detalleAbordo.getValueAt(13,1).toString();String total = jtbl_detalleAbordo.getValueAt(13,2).toString();
 int tamañoTipo = tipo.length(); int tamañonbol  = nbol.length(); int tamañoTotal = total.length();
 if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else {tipo = tipo.substring(0,11); tipo = tipo+" ";}
 if(tamañonbol<5 ){for(int i=tamañonbol; i<=5 ; i++)nbol=nbol+" ";} else nbol = nbol.substring(0,5);
 if(tamañoTotal<8 ){for(int i=tamañoTotal; i<=8 ; i++)total=" "+total;} else total = total.substring(0,8);
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"|        "+tipo+" "+nbol+""+total,0,lineaNueva*row);
row++;

}
else
{
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Fecha Impresion: "+formatfc.format(fecha_servidor.getTime())+" "+formathc.format(fecha_servidor.getTime()),0,lineaNueva*row);
row++;

}
        
conteo++;   
 num = jtbl_boletosDetalle.getValueAt(conteo,0).toString();cd = jtbl_boletosDetalle.getValueAt(conteo,1).toString();t = jtbl_boletosDetalle.getValueAt(conteo,2).toString();boleto = jtbl_boletosDetalle.getValueAt(conteo,3).toString();
 tamañoCd = cd.length();tamañoBoleto = boleto.length();
 if(tamañoCd<4 ){for(int i=tamañoCd; i<=4 ; i++)cd=cd+" ";} else cd = cd.substring(0,4)+" ";
 if(tamañoBoleto<10 ){for(int i=tamañoBoleto; i<=10 ; i++)boleto=boleto+" ";} else boleto = boleto.substring(0,10)+" "; 
num2 = jtbl_boletosDetalle.getValueAt(conteo,4).toString();cd2 = jtbl_boletosDetalle.getValueAt(conteo,5).toString();t2 = jtbl_boletosDetalle.getValueAt(conteo,6).toString();boleto2 = jtbl_boletosDetalle.getValueAt(conteo,7).toString();
tamañoCd2 = cd2.length();tamañoBoleto2 = boleto2.length();
 if(tamañoCd2<4 ){for(int i=tamañoCd2; i<=4 ; i++)cd2=cd2+" ";} else cd2 = cd2.substring(0,4)+" ";
 if(tamañoBoleto2<10 ){for(int i=tamañoBoleto2; i<=10 ; i++)boleto2=boleto2+" ";} else boleto2 = boleto2.substring(0,10)+" "; 
num3 = jtbl_boletosDetalle.getValueAt(conteo,8).toString();cd3 = jtbl_boletosDetalle.getValueAt(conteo,9).toString();t3 = jtbl_boletosDetalle.getValueAt(conteo,10).toString();boleto3 = jtbl_boletosDetalle.getValueAt(conteo,11).toString();
tamañoCd3 = cd3.length();tamañoBoleto3 = boleto3.length();
 if(tamañoCd3<4 ){for(int i=tamañoCd3; i<=4 ; i++)cd3=cd3+" ";} else cd3 = cd3.substring(0,4)+" ";
 if(tamañoBoleto3<10 ){for(int i=tamañoBoleto3; i<=10 ; i++)boleto3=boleto3+" ";} else boleto3 = boleto3.substring(0,10)+" "; 
num4 = jtbl_boletosDetalle.getValueAt(conteo,12).toString();cd4 = jtbl_boletosDetalle.getValueAt(conteo,13).toString();t4 = jtbl_boletosDetalle.getValueAt(conteo,14).toString();boleto4 = jtbl_boletosDetalle.getValueAt(conteo,15).toString();
tamañoCd4 = cd4.length();tamañoBoleto4 = boleto4.length();
 if(tamañoCd4<4 ){for(int i=tamañoCd4; i<=4 ; i++)cd4=cd4+" ";} else cd4 = cd4.substring(0,4)+" ";
 if(tamañoBoleto4<10 ){for(int i=tamañoBoleto4; i<=10 ; i++)boleto4=boleto4+" ";} else boleto4 = boleto4.substring(0,10)+" "; 
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Terminal: "+terminal,0,lineaNueva*row);
row++;
g.drawString(" -------------------------------------------------------------------------------------------  * Boletos Vendidos por Internet",0,lineaNueva*row);
row++;row++;

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
                                //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService(); 
                                try {
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
 
 private void mostrarDialogoSupervisor2(String fun) {
        dlgSupervisor = new jdlgDatosSupervisor(true,"TMS - Validar Supervisor         Funcion: "+fun);
        dlgSupervisor.centrarDialogo();
        dlgSupervisor.setVisible(true);
    }
 
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
