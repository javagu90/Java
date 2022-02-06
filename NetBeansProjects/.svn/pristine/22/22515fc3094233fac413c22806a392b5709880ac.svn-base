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
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tmspuertas.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author  vgonzalez
 */
public class Jdlg_vistaPreviaReimpresionTarjetaViaje extends javax.swing.JDialog {
    
    /** Creates new form Jdlg_vistaPreviaTarjetaViaje */
    public Jdlg_vistaPreviaReimpresionTarjetaViaje(int numAsientos,Vector oc, Vector tip, Vector dt, TmsPuertasManagedBean pbusquedas,Vector ocm,int p,TmsTarjetasViajeTbl ptarjeta,Object[][] psinOcupacion,boolean premoto,String pDBLink, String ppuerto, PrintService pimpresora, String pipAS, int pportAS) {
        this.setTitle("Vista previa de la tarjeta de viaje");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        this.puerto = ppuerto;
        this.impresora = pimpresora;
        this.ipAS =  pipAS;
        this.portAS = pportAS;
        this.ocupacion = oc;
        this.tipos = tip;
        this.datosTarjeta = dt;
        this.busquedas = pbusquedas;
        this.proviene = p;
        this.ocupacionModificada = ocm;
        this.tarjeta = ptarjeta;
        this.sinOcupacion = psinOcupacion;
        this.DBLink = pDBLink;
        this.remoto = premoto;
        initComponents();
        jtxt_motivo.setFocusTraversalKeysEnabled(false);
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
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si  |  <font color=FF0000>             ESC: </font> No  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        jlbl_nombreOperador.setHorizontalAlignment( JTextField.CENTER );
        jlbl_barraEstado.setFocusTraversalKeysEnabled(false);
        Vector acupa = new Vector();
        vencabezadoDetalle = new Vector();
        vencabezadoDetalle.add("TIPO PAS");
        vencabezadoDetalle.add("# BOL");
        vencabezadoDetalle.add("TOTAL");            
        if(proviene==2)
        {
            String[] nrow = {"","",""};
            modeloDetalleAbordo.setDataVector(sinOcupacion,encabezadoDetalle);
            System.out.println("La ocuapcion es: "+modeloDetalleAbordo.getDataVector());
            int cbol = 0;
            float mbol = 0;
            for(int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
            {
                 Vector v = new Vector();
                if(jtbl_detalleAbordo.getValueAt(i,1)==null)
                {
                    jtbl_detalleAbordo.setValueAt("0",i,1);
                    jtbl_detalleAbordo.setValueAt("0",i,2);
                }
                else    
                {
                    cbol = cbol + Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
                    mbol = mbol + Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString());
                }
                if(Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())>0 || jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESTUDIANTE") || jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ADULTO") || jtbl_detalleAbordo.getValueAt(i,0).toString().equals("MENOR") || jtbl_detalleAbordo.getValueAt(i,0).toString().equals("SENECTUD") || jtbl_detalleAbordo.getValueAt(i,0).toString().equals("PROFESOR") )
                {
                     v.add(jtbl_detalleAbordo.getValueAt(i,0).toString());
                     v.add(jtbl_detalleAbordo.getValueAt(i,1).toString());
                     v.add(jtbl_detalleAbordo.getValueAt(i,2).toString());
                     acupa.add(v);
                }
            }
            modeloDetalleAbordo.setDataVector(acupa,vencabezadoDetalle);
            modeloDetalleAbordo.addRow(nrow);
            nrow[0]="TOTALES";
            nrow[1] = ""+cbol;
            nrow[2] = ""+mbol;
            modeloDetalleAbordo.addRow(nrow);
            resizeColumnasAbordo();
            
//            vencabezadoDetalle.add("TIPO PAS");
//            vencabezadoDetalle.add("# BOL");
//            vencabezadoDetalle.add("TOTAL");
//            String[] nrow = {"","",""};
//            modeloDetalleAbordo.setDataVector(ocupacionModificada,vencabezadoDetalle);
//            int cbol = 0;
//            float mbol = 0;
//            for(int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
//            {
////               System.out.println("Valor 1: "+jtbl_detalleAbordo.getValueAt(i,1).toString());
////               System.out.println("Valor 2: "+jtbl_detalleAbordo.getValueAt(i,2).toString());
//                cbol = cbol + Integer.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                mbol = mbol + Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString());
//            }
//            modeloDetalleAbordo.addRow(nrow);
//            nrow[0]="TOTALES";
//            nrow[1] = ""+cbol;
//            nrow[2] = ""+mbol;
//            modeloDetalleAbordo.addRow(nrow);
//            resizeColumnasAbordo();
        }   
        else
            {
            Object[][] ocupacionSistema = new Object[tipos.size()][4];
            for(int i=0; i<tipos.size();i++){
                Vector vtipo = (Vector) tipos.get(i);
                String letraTipo = vtipo.get(1).toString();
//                 System.out.println("Letra : "+letraTipo);
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
                Vector v = new Vector();
              ocupacionSistema[i][0]=vtipo.get(0).toString();   
              ocupacionSistema[i][1]=""+conteo;   
              ocupacionSistema[i][2]=""+total;  
              if(conteo>0 || vtipo.get(0).toString().equals("ESTUDIANTE") || vtipo.get(0).toString().equals("ADULTO") || vtipo.get(0).toString().equals("MENOR") || vtipo.get(0).toString().equals("SENECTUD") || vtipo.get(0).toString().equals("PROFESOR") )
              {
                  v.add(vtipo.get(0).toString());
                  v.add(conteo);
                  v.add(total);
                  acupa.add(v);
              }
              
             }
                //modeloDetalleAbordo.setDataVector(ocupacionSistema,encabezadoDetalle);
                modeloDetalleAbordo.setDataVector(acupa,vencabezadoDetalle);
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
             System.out.println("Ocupacion : "+ocupacion);
                
                Vector vOcupados=new Vector();
                if(ocupacion.size()>0){
                    
                for(int j=0; j<ocupacion.size(); j++){
                    Vector vocupa = (Vector) ocupacion.get(j);
                    for(int k=0;k<jtbl_boletosDetalle.getRowCount();k++)
                    {   
                        String na = jtbl_boletosDetalle.getValueAt(k,0).toString().trim();
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
        String clave = datosTarjeta.get(0).toString();
        jlbl_numreimpresion.setText(""+((tarjeta.getNumeroImpresion().intValue()) + 1));
        jlbl_claveCorrida.setText(clave);
        jlbl_tipoCorrida.setText(datosTarjeta.get(16).toString());
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
        jlbl_folio.setText(tarjeta.getFolioTarjeta());
        jlbl_viaje.setText(""+tarjeta.getFolioTarjeta().substring(13)); //10));
        jtxt_empresaTitulo.setText(datosTarjeta.get(14).toString());
        jtxt_TicketSalida.setText(tarjeta.getAdicional3());
        jtxt_motivo.requestFocus();
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
        jlbl_origen = new javax.swing.JLabel();
        jlbl_servicio = new javax.swing.JLabel();
        jlbl_autobus = new javax.swing.JLabel();
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
        jLabel8 = new javax.swing.JLabel();
        jlbl_numreimpresion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtxt_motivo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jlbl_viaje = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jlbl_tipoCorrida = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtxt_TicketSalida = new tms_TextFields.JTextTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_barraEstadoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setText("¿Desea Reimprimir la Tarjeta de Viaje?");

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

        jlbl_origen.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_origen.setText("TAPO");

        jlbl_servicio.setFont(new java.awt.Font("Tahoma", 0, 9));
        jlbl_servicio.setText("INTERMEDIO");

        jlbl_autobus.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_autobus.setForeground(new java.awt.Color(0, 102, 255));
        jlbl_autobus.setText("1039204");

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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("No de Reimpresion:");

        jlbl_numreimpresion.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_numreimpresion.setText("20");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Motivo:");

        jtxt_motivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_motivoKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel9.setText("DIGITE:");

        jlbl_viaje.setText("0000000000");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel29.setText("TIPO CORRIDA:");

        jlbl_tipoCorrida.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_tipoCorrida.setText("ROL");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel25.setText("TICKET SALIDA:");

        jtxt_TicketSalida.setEditable(false);
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
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 507, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
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
                                                .add(jLabel28))
                                            .add(layout.createSequentialGroup()
                                                .add(jLabel25)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(jtxt_TicketSalida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jLabel29)))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(jlbl_tipoCorrida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                            .add(jlbl_claveCorrida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(jLabel9)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jlbl_viaje))
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
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
                                                        .add(jlbl_nombreOperador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                                    .add(260, 260, 260)))))
                                    .add(layout.createSequentialGroup()
                                        .add(65, 65, 65)
                                        .add(jLabel21))))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_numreimpresion))
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel10)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jtxt_motivo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel5)
                                        .add(31, 31, 31)
                                        .add(jLabel6))
                                    .add(layout.createSequentialGroup()
                                        .add(jlbl_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jlbl_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel7)
                                    .add(jlbl_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(272, 272, 272))))
                    .add(layout.createSequentialGroup()
                        .add(237, 237, 237)
                        .add(jLabel2)
                        .add(26, 26, 26)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 238, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 786, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(468, 468, 468))
            .add(layout.createSequentialGroup()
                .add(603, 603, 603)
                .add(jLabel4)
                .addContainerGap(804, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(535, 535, 535)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel11)
                    .add(jlbl_descipcionViaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(419, 419, 419)
                .add(jtxt_empresaTitulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 364, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(712, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(23, 23, 23)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(45, 45, 45)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jlbl_origen)
                                    .add(jlbl_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jlbl_autobus))
                                .add(21, 21, 21)
                                .add(jLabel11)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jlbl_descipcionViaje)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(19, 19, 19)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel5)
                                    .add(jLabel7)
                                    .add(jLabel6)
                                    .add(jLabel8)
                                    .add(jlbl_numreimpresion))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 22, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel10)
                                    .add(jtxt_motivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(51, 51, 51)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(layout.createSequentialGroup()
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
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel9)
                                        .add(1, 1, 1))
                                    .add(jlbl_viaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel29)
                                    .add(jlbl_tipoCorrida)
                                    .add(jLabel25)
                                    .add(jtxt_TicketSalida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .add(15, 15, 15)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jLabel2))
                        .add(9, 9, 9))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jLabel4)
                        .add(460, 460, 460)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(layout.createSequentialGroup()
                .add(jtxt_empresaTitulo)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_motivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_motivoKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
        
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            if(jtxt_motivo.getText().equals(""))
            {
                new jdlg_error("¡Debes escribir un motivo de reimpresion!","","").setVisible(true);
                return;
            }
            
//            Vector vestado= (Vector)busquedas.variosFacadeRemote.buscarEstadoSesion(Long.valueOf(datosTarjeta.get(13).toString()));
//             String estado = vestado.get(0).toString();
//             if(estado.equals("CERRADA")){
//                 new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
//                 System.exit(0);
//             } 

              if(!abreSocketAS()){
               new  jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.","Intentelo mas tarde.","¡No existe una conexión con la Base de Datos!").setVisible(true);
               return;
                }
            Vector x = new Vector();
             x = (Vector)busquedas.facadeGeneralPuertasRemote.fechaServidor();
            Vector  NESP = new Vector();
            NESP = (Vector)busquedas.facadeGeneralPuertasRemote.queryBuscaNombreTerminal();
            terminal = NESP.get(0).toString();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
            System.out.println("datosTarjeta: "+datosTarjeta);
            //TmsCorridasVentaTbl corrida = busquedas.corridasVentaTblFacadeRemote.find((BigDecimal)datosTarjeta.get(11));
            BigInteger nr = BigInteger.valueOf(Long.valueOf(jlbl_numreimpresion.getText()));
            tarjeta.setNumeroImpresion(nr);
            tarjeta.setMotivoReimpresion(jtxt_motivo.getText());
            long nus = Long.valueOf(datosTarjeta.get(12).toString());
            BigInteger usr= BigInteger.valueOf(nus);
            tarjeta.setUltimaActualizacionPor(usr);
            tarjeta.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
            if(remoto)
            {
              boolean valor = busquedas.facadeGeneralPuertasRemote.modificarTarjetaReimpresion(tarjeta,DBLink);
                 if(!valor)
                 {
                     new jdlg_error("¡No fue posible actualizar la tarjeta "+tarjeta.getFolioTarjeta()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
                     return;
                 }
            }
            else
               busquedas.facadeGeneralPuertasRemote.editTarjetaViaje(tarjeta);

           if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2") || puerto.equals("ARCHIVO")  )
            imprimirTarjetaLPT();
           if(puerto.equals("RED") || puerto.equals("USB"))
              imprimir_tarjeta_windows();
           new jdlg_informacion("¡La tarjeta fue reimpresa correctamente!","","Tarjeta reimpresa").setVisible(true);
           this.dispose();
           
        }
        
    }//GEN-LAST:event_jtxt_motivoKeyPressed

    private void jlbl_barraEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_barraEstadoKeyPressed
        
        
    }//GEN-LAST:event_jlbl_barraEstadoKeyPressed

    private void jtxt_TicketSalidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_TicketSalidaKeyPressed

}//GEN-LAST:event_jtxt_TicketSalidaKeyPressed
    
    
private void imprimirTarjetaLPT(){
            
            
            char rc = 13;
            char spc= 10;
            int conteo = 0;
            int nrows = (jtbl_detalleAbordo.getRowCount() -1);
            String ticket = jtxt_TicketSalida.getText().trim();
            if(!ticket.equals(""))
                ticket = "TICKET SALIDA: "+ticket;
            for(int i=ticket.length(); i<=30; i++)
                ticket = ticket+" ";

String CodigoImpresora ="" +
"                                                                                    "+datosTarjeta.get(14).toString().toUpperCase()+"\n"+rc+//AUTOBUSES MEXPUE ESTRELLA ROJA\n"+rc+
//"                                                                                            AUTOBUSES MEXPUE ESTRELLA ROJA\n"+rc+
"                                                                                                    TARJETA DE VIAJE\n"+rc+      
"                                                                                            ORIGEN        SERVICIO       AUTOBUS   \n"+rc+  
"                                                                                            ---------  -------------     -------    \n"+rc+ 
"                                                                                            "+jlbl_origen.getText()+"    "+jlbl_servicio.getText()+"  "+jlbl_autobus.getText()+"    \n"+rc+
"                                                                                            DESCRIPCION DEL VIAJE       \n"+rc;
String mot = jtxt_motivo.getText();
if(mot.length()<60)
{
    int n = mot.length();
    for(int i=n; i<=60;i++)
        mot = mot +" ";
}   
//"                                                                                            "+jlbl_descipcionViaje.getText()+"          \n"+rc+
CodigoImpresora = CodigoImpresora+ " "+
" No. Reimpresion: "+jlbl_numreimpresion.getText()+"    Motivo: "+mot+""+jlbl_descipcionViaje.getText()+"          \n"+rc+
//" No. Reimpresion: "+jlbl_numreimpresion.getText()+"    Motivo: "+jtxt_motivo.getText()+"\n"+rc+                              
"                                                                                          FOLIO             OPERADOR    FECHA      HORA\n"+rc+  
"                                                                                          ----------------  --------  ----------  -----\n"+rc+
" "+ticket+"                                                      "+jlbl_folio.getText()+"    "+jlbl_claveOperador.getText()+"    "+jlbl_fecha.getText()+"  "+jlbl_hora.getText()+"\n"+rc+
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
 //if(tamañoTipo<11){for(int i=tamañoTipo; i<=11; i++ )tipo=tipo+" ";} else tipo = tipo.substring(0,11) ;     
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
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Fecha Impresion: "+formatfc.format(fecha_servidor.getTime())+" "+formathc.format(fecha_servidor.getTime())+"\n"+rc;
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
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Terminal: "+terminal+"\n"+rc+
" -------------------------------------------------------------------------------------------  * Boletos Vendidos por Internet \n\n"+rc;                       

            
            
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
    
}    
    
    public void imprimir_tarjeta_windows(){
        imprimir_recibo_tarjeta imp = new imprimir_recibo_tarjeta();
        imp.ImprimeDatos();
    }    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel jlbl_numreimpresion;
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
    private javax.swing.JTextField jtxt_motivo;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadoBoletos = {"#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO ","#"," CD "," T " ," BOLETO "};
    private Object[] encabezadoDetalle = {"TIPO PAS","# BOL","TOTAL"};
    private TmsTarjetasViajeTbl tarjeta;
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

    private boolean remoto;

    private String DBLink;

    private String puerto;

    private PrintService impresora;

    private String ipAS;

    private int portAS;
    
    public Object[] getEncabezadoBoletos() {
        return encabezadoBoletos;
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
                                Vector x = (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
                                fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                                int lineaNueva=11, row=1;
                                g.drawString(datosTarjeta.get(14).toString().toUpperCase(),370,lineaNueva);
                                row++;
                                g.drawString("TARJETA DE VIAJE",405,lineaNueva*row);
                                row++;
                                g.drawString("ORIGEN       SERVICIO         AUTOBUS   ",370,lineaNueva*row);
                                row++;
                                g.drawString("-------- ----------------     -------    ",370,lineaNueva*row);
                                row++;
                                g.drawString("No. Reimpresion: "+jlbl_numreimpresion.getText()+"    Motivo: "+jtxt_motivo.getText(),0,lineaNueva*row);
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
g.drawString("|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| Terminal: "+terminal ,0,lineaNueva*row);
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
