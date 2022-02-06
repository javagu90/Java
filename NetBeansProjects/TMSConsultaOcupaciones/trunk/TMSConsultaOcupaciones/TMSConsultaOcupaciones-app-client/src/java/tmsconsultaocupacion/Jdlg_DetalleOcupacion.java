/*
 * Jdlg_DetalleOcupacion.java
 *
* Created on 10 de octubre de 2007, 06:18 PM
 */

package tmsconsultaocupacion;

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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import tmspuertas.entidad.TmsAccionesTbl;
//import tmspuertas.entidad.TmsAutobusesTbl;
//import tmspuertas.entidad.TmsCorridasTbl;
//import tmspuertas.entidad.TmsCorridasVentaTbl;
//import tmspuertas.entidad.TmsEstadosCorridaTbl;
//import tmspuertas.entidad.TmsEstadosTbl;
//import tmspuertas.entidad.TmsOperadoresTbl;
//import tmspuertas.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author  vgonzalez
 */
public class Jdlg_DetalleOcupacion extends javax.swing.JDialog {

    /**
     * Creates new form Jdlg_DetalleOcupacion
     */
    public Jdlg_DetalleOcupacion(int numAsientos,Vector oc, Vector tip, Vector dt, TmsConsultasManagedBean pbusquedas,Vector ocm,Object[][] psinOcupacion,int p,boolean pautorizacion,boolean pautorizacion2,int pntarpen, boolean premoto, String pDBLink, boolean ppermiso) {
        this.setTitle("Resumen de Ocupacion de la Corrida");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        this.setAlwaysOnTop(true);
        this.ocupacion = oc;
        this.tipos = tip;
        this.datosTarjeta = dt;
        this.busquedas = pbusquedas;
        this.proviene = p;
        this.ocupacionModificada = ocm;
        this.sinOcupacion = psinOcupacion;
        this.autorizacion= pautorizacion;
        this.autorizacion2= pautorizacion2;
        this.ntarpen = pntarpen;
        this.remoto = premoto;
        this.DBLink = pDBLink;
        this.permiso = ppermiso;
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
        //this.setSize(955,622);
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
        jlbl_barraEstado.setText("<html><font color=FF0000>  ESCAPE </font> Cerrar Pantalla de detalle  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        //jlbl_nombreOperador.setHorizontalAlignment( JTextField.CENTER );
        jlbl_barraEstado.setFocusTraversalKeysEnabled(false);
       if(proviene==3)
       {
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
       else
       {
        if(proviene==2)
        {
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
        else
            {
            //System.out.println("Ocupacion antes de insertarla:");
            //System.out.println(""+ocupacion);
            //Object[][] ocupacionSistema = new Object[tipos.size()][4];
            Vector vocupacionSistema = new Vector();
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

              //ocupacionSistema[i][0]=vtipo.get(0).toString();
             // ocupacionSistema[i][1]=""+conteo;
             // ocupacionSistema[i][2]=""+conteo;
                if(conteo>0 || vtipo.get(0).toString().equals("ESTUDIANTE") || vtipo.get(0).toString().equals("ADULTO") || vtipo.get(0).toString().equals("MENOR") || vtipo.get(0).toString().equals("SENECTUD") || vtipo.get(0).toString().equals("PROFESOR") )
                {
                    Vector vec = new Vector();
                    vec.add(vtipo.get(0).toString());
                    vec.add(""+conteo);
                    vec.add(""+conteo);
                    vocupacionSistema.add(vec);
                }
             }
                vencabezadoDetalle = new Vector();
                vencabezadoDetalle.add("TIPO PAS");
                vencabezadoDetalle.add("# BOL");
                vencabezadoDetalle.add("TOTAL");
                modeloDetalleAbordo.setDataVector(vocupacionSistema,vencabezadoDetalle);
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
                        if(na.equals(vocupa.get(1).toString().trim()))
                        {
                            if(!jtbl_boletosDetalle.getValueAt(k,3).toString().trim().equals(""))
                            {
                                vOcupados.add(vocupa);
                                break;
                            }                            
                                jtbl_boletosDetalle.setValueAt(vocupa.get(2).toString(),k,1);
                                jtbl_boletosDetalle.setValueAt(vocupa.get(4).toString(),k,2);
                                if(vocupa.get(3)==null)
                                 jtbl_boletosDetalle.setValueAt("",k,3);
                                else
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
                            if(vocupa.get(3)==null)
                                jtbl_boletosDetalle.setValueAt("",k,7);
                            else
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
                            if(vocupa.get(3)==null)
                               jtbl_boletosDetalle.setValueAt("",k,11); 
                            else
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
                            if(vocupa.get(3)==null)
                                 jtbl_boletosDetalle.setValueAt("",k,15);
                            else
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
         }
        String clave = datosTarjeta.get(0).toString();
        jlbl_claveCorrida.setText(clave);
        jlbl_servicio.setText(datosTarjeta.get(1).toString());
        //jlbl_origen.setText(datosTarjeta.get(2).toString());
        jlbl_origen2.setText(datosTarjeta.get(2).toString());
        jlbl_destino2.setText(datosTarjeta.get(3).toString());
        //jlbl_claveOperador.setText(datosTarjeta.get(4).toString());
//        jlbl_nombreOperador.setText(datosTarjeta.get(5).toString());
 //       jlbl_autobus.setText(datosTarjeta.get(6).toString());
        jlbl_fecha.setText(datosTarjeta.get(7).toString());
        jlbl_hora.setText(datosTarjeta.get(8).toString());
        //jlbl_ruta.setText(datosTarjeta.get(9).toString());
        //jlbl_descipcionViaje.setText(datosTarjeta.get(10).toString());
        Vector vnfol = new Vector();
        vnfol =  (Vector)busquedas.variosFacadeRemote.queryBuscaValorActualTarjetaViaje(); 
        nfol = (Long.valueOf(vnfol.get(0).toString())) + 1;
        //jlbl_folio.setText("");//clave.substring(0,10)+""+nfol);
        String sfolio = ""+nfol;
        String sviaje = sfolio;
        //if(sfolio.length()>=4)
        // sviaje = sfolio.substring(3);
        //jlbl_viaje.setText(sviaje);//""+nfol);
        if(!permiso)
        {
            jScrollPane2.setVisible(false);
            jtbl_detalleAbordo.setVisible(false);
            jLabel21.setVisible(false);
            System.out.println("Entro a quitar la tabla....");
        }
        jtxt_empresaTitulo.setText(datosTarjeta.get(15).toString().toUpperCase());
        jlbl_barraEstado.requestFocus();
    }
    
    
    private void resizeColumnasBoletos(){
    TableColumn columinv = jtbl_boletosDetalle.getColumnModel().getColumn(0); columinv.setMinWidth( 17 );columinv.setMaxWidth( 17 );columinv.setPreferredWidth(17);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(4); columinv.setMinWidth( 17 );columinv.setMaxWidth( 17 );columinv.setPreferredWidth(17);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(8); columinv.setMinWidth( 17 );columinv.setMaxWidth( 17 );columinv.setPreferredWidth(17);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(12); columinv.setMinWidth( 17 );columinv.setMaxWidth( 17 );columinv.setPreferredWidth(17);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(1); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(5); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(9); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(13); columinv.setMinWidth( 40 );columinv.setMaxWidth( 40 );columinv.setPreferredWidth(40);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(2); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(6); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(10); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(14); columinv.setMinWidth( 16 );columinv.setMaxWidth( 16 );columinv.setPreferredWidth(16);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(3); columinv.setMinWidth( 175 );columinv.setMaxWidth( 175 );columinv.setPreferredWidth(175);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(7); columinv.setMinWidth( 175 );columinv.setMaxWidth( 175 );columinv.setPreferredWidth(175);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(11); columinv.setMinWidth( 175 );columinv.setMaxWidth( 175 );columinv.setPreferredWidth(175);          
                columinv = jtbl_boletosDetalle.getColumnModel().getColumn(15); columinv.setMinWidth( 175 );columinv.setMaxWidth( 175 );columinv.setPreferredWidth(175);          
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jlbl_barraEstado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlbl_fecha = new javax.swing.JLabel();
        jlbl_hora = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jlbl_origen2 = new javax.swing.JLabel();
        jlbl_destino2 = new javax.swing.JLabel();
        jlbl_claveCorrida = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_detalleAbordo = new javax.swing.JTable();
        jtxt_empresaTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbl_servicio = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_boletosDetalle = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_barraEstadoKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel5.setText("Origen:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(" Servicio:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel16.setText("Hora:");

        jlbl_fecha.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_fecha.setText("15/10/2007");

        jlbl_hora.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_hora.setText("23:50");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel21.setText("RESUMEN DEL VIAJE");

        jlbl_origen2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_origen2.setText("SANMARTIN");

        jlbl_destino2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_destino2.setText("AEROMEXICO");

        jlbl_claveCorrida.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_claveCorrida.setText("TAPOI2350123456789");

        jScrollPane2.setFocusable(false);
        jtbl_detalleAbordo.setBackground(new java.awt.Color(238, 238, 238));
        jtbl_detalleAbordo.setFont(new java.awt.Font("Tahoma", 1, 10));
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

        jtxt_empresaTitulo.setFont(new java.awt.Font("Tahoma", 1, 13));
        jtxt_empresaTitulo.setText("AUTOBUSES MEXPUE ESTRELLA ROJA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel1.setText("Corrida:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel3.setText("Fecha:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel2.setText("Destino:");

        jlbl_servicio.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_servicio.setText("MESXIO-AERO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de Ocupacion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13)));
        jtbl_boletosDetalle.setBackground(new java.awt.Color(238, 238, 238));
        jtbl_boletosDetalle.setFont(new java.awt.Font("Tahoma", 1, 9));
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

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(34, 34, 34)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(jLabel1)
                                .add(17, 17, 17)
                                .add(jlbl_claveCorrida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(14, 14, 14)
                                .add(jLabel3)
                                .add(17, 17, 17)
                                .add(jlbl_fecha))
                            .add(layout.createSequentialGroup()
                                .add(19, 19, 19)
                                .add(jLabel16)
                                .add(17, 17, 17)
                                .add(jlbl_hora))
                            .add(layout.createSequentialGroup()
                                .add(8, 8, 8)
                                .add(jLabel5)
                                .add(17, 17, 17)
                                .add(jlbl_origen2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(1, 1, 1)
                                .add(jLabel2)
                                .add(17, 17, 17)
                                .add(jlbl_destino2))
                            .add(layout.createSequentialGroup()
                                .add(jLabel6)
                                .add(14, 14, 14)
                                .add(jlbl_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jtxt_empresaTitulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 416, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 155, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(178, 178, 178))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel21)
                        .add(225, 225, 225))))
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jLabel21)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jtxt_empresaTitulo)
                        .add(21, 21, 21)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jlbl_claveCorrida))
                        .add(15, 15, 15)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jlbl_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(15, 15, 15)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel16)
                            .add(jlbl_hora))
                        .add(16, 16, 16)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(jlbl_origen2))
                        .add(14, 14, 14)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jlbl_destino2))
                        .add(17, 17, 17)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_servicio))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlbl_barraEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_barraEstadoKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
        
//        if(evt.getKeyCode() == evt.VK_ENTER)
//        {
//                Vector vestado= (Vector)busquedas.variosFacadeRemote.buscarEstadoSesion(Long.valueOf(datosTarjeta.get(13).toString()));
//                 String estadoses = vestado.get(0).toString();
//                 if(estadoses.equals("CERRADA")){
//                     new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
//                     System.exit(0);
//                 }
//
//            Vector  NESP = new Vector();
//             String nombreEsquema="";
//                if(remoto)
//                {
//                 NESP = (Vector)busquedas.variosRemotoFacadeRemote.queryBuscaNombreEsquema(DBLink);
//                 nombreEsquema = NESP.get(0).toString();
//                }
//             else
//             {
//                 NESP = (Vector)busquedas.variosFacadeRemote.queryBuscaNombreEsquema();
//                 nombreEsquema = NESP.get(0).toString();
//             }
//
//            Vector TER = new Vector();
//                if(remoto)
//                {
//                    TER = (Vector)busquedas.variosRemotoFacadeRemote.queryBuscaIdTerminal(DBLink);
//                    idTerminal = TER.get(0).toString();
//                }
//                else
//                {
//                    TER = (Vector)busquedas.variosFacadeRemote.queryBuscaIdTerminal();
//                    idTerminal = TER.get(0).toString();
//                }
//            BigInteger terCompnente2bus = BigInteger.valueOf(Long.valueOf(TER.get(0).toString()));
//            String te = idTerminal;
//            if(idTerminal.length()<3)
//            {
//               for(int i=idTerminal.length(); i<3;i++)
//                   te = te+"0";
//            }
//            if(idTerminal.length()>=3)
//             te = idTerminal.substring(0,2);
//
//            idTerminal = te;
//            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
//            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
//            System.out.println("datosTarjeta: "+datosTarjeta);
//            TmsCorridasVentaTbl corrida = null;
//            if(remoto)
//            {
//                //corrida = busquedas.corridasVentaTblFacadeRemote.find((BigDecimal)datosTarjeta.get(11));
//                corrida = busquedas.variosRemotoFacadeRemote.buscaCorridaRemota(Long.valueOf(datosTarjeta.get(11).toString()),DBLink);
//                if(corrida==null)
//                {
//                    new jdlg_error("¡La corrida no se encontro en la Base de Datos! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                    return;
//                }
//            }
//            else
//                corrida = busquedas.corridasVentaTblFacadeRemote.find((BigDecimal)datosTarjeta.get(11));
//
//            System.out.println("Operador nuevo: "+corrida.getOperador()+"  Operador Original: "+jlbl_claveOperador.getText());
//
//            if(!corrida.getOperador().equals(jlbl_claveOperador.getText()))
//            {
//                  jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Cambio en corrida", "El operador de la corrida cambio por el "+corrida.getOperador()+", ¿Desea despachar la tarjeta con este cambio?");
//                  psn.setVisible(true);
//                  if(respuestaSN)
//                  {
//                      jlbl_claveOperador.setText(corrida.getOperador());
//                        Vector vnombre = (Vector) busquedas.variosFacadeRemote.buscarNombreOperador(corrida.getOperador());
//                        Vector nombre;
//                        String nombreoperador  = "";
//                        if(vnombre.size()==0)
//                        {
//                         new jdlg_advertencia("¡El operador no se encontro registrado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                         return;
//                        }
//                        else
//                        {
//                            nombre = (Vector) vnombre.get(0);
//                            if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)
//                              nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
//                            else
//                              nombreoperador = nombre.get(0)+ " " + nombre.get(2);
//                        }
//                        jlbl_nombreOperador.setText(nombreoperador);
//                  }
//                  else
//                  {
//                      this.dispose();
//                      return;
//                  }
//            }
//
//            System.out.println("Autobus nuevo: "+corrida.getAutobus()+"  Autobus Original: "+jlbl_autobus.getText());
//
//            if(!corrida.getAutobus().equals(jlbl_autobus.getText()))
//            {
//                  jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Cambio en corrida", "El autobus de la corrida cambio por el "+corrida.getAutobus()+", ¿Desea despachar la tarjeta con este cambio?");
//                  psn.setVisible(true);
//                  if(respuestaSN)
//                    jlbl_autobus.setText(corrida.getAutobus());
//                  else
//                  {
//                      this.dispose();
//                      return;
//                  }
//            }
//            if(corrida.getEstadoCorrida().equals("D"))
//            {
//                new jdlg_error("¡La corrida ya ha sido despachada!","","Corrida despachada").setVisible(true);
//                      this.dispose();
//                      return;
//            }
//            if(corrida.getEstadoCorrida().equals("E"))
//            {
//                new jdlg_error("¡La corrida ha sido cerrada!","","Corrida cerrada").setVisible(true);
//                      this.dispose();
//                      return;
//            }
//            if(corrida.getEstadoCorrida().equals("C"))
//            {
//                new jdlg_error("¡La corrida ha sido cancelada!","","Corrida cancelada").setVisible(true);
//                      this.dispose();
//                      return;
//            }
//
//            corrida.setEstadoCorrida("D");
//            corrida.setAdicional4("PSD");
//            corrida.setReplicacionOrigen(nombreEsquema);
//            TmsTarjetasViajeTbl  tarExist = null;
//            if(remoto)
//                tarExist = busquedas.variosRemotoFacadeRemote.buscaTarjetaViajeExistente(corrida.getCorridaId().longValue(), DBLink);
//            else
//                tarExist = busquedas.variosFacadeRemote.buscaTarjetaViajeExistente(corrida.getCorridaId().longValue());
//            BigDecimal nc = (BigDecimal)datosTarjeta.get(11);
//            List<TmsEstadosCorridaTbl> listaestado = busquedas.estadosCorridaTblFacadeRemote.buscarPorLetra("D");
//            if(listaestado.size()==0)
//            {
//                new jdlg_advertencia("¡No hay estados datos de alta!"," Favor de contactar al Adiministrador del Sistema","").setVisible(true);
//                return;
//            }
//            //busca estado de la tarjeta abierta
//            Vector vedotar = (Vector)busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje();
//            Vector edotar = (Vector)vedotar.get(0);
//            TmsEstadosCorridaTbl estado =  listaestado.get(0);
//            TmsCorridasTbl corridan = null;
//            if(remoto)
//            {
//               corridan = busquedas.variosRemotoFacadeRemote.buscaCorridaSolaRemota(Long.valueOf(datosTarjeta.get(11).toString()),DBLink);
//               if(corridan==null){
//                    new jdlg_error("¡La corrida no se encontro en la Base de Datos! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                    return;
//               }
//            }
//            else
//                corridan = busquedas.corridasTblFacadeRemote.find((BigDecimal)datosTarjeta.get(11));
//            corridan.setEstadoCorridaId(estado);
//            corridan.setAdicional4("PSD");
//            corridan.setReplicacionOrigen(nombreEsquema);
//            //TmsEstadosTarjetaViajeTbl estado = busquedas.estadosTarjetaViajeTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf("0")));
//            System.out.println("Corrida: "+corrida.getClaveCorrida());
//           if(tarExist!=null)
//           {
//                Vector vvDBLink = (Vector) busquedas.variosFacadeRemote.buscaDBLinkCentral();
//                if(vvDBLink.size()==0)
//                {
//                    new jdlg_error("¡La conexion con la BD Central esta mal configurada! ","Favor de contactar al Administrador del Sistema ","Error de Configuracion").setVisible(true);
//                    return;
//                }
//                Vector vDBLink = (Vector) vvDBLink.get(0);
//                String DBLinkCentral  = vDBLink.get(0).toString();
//                Vector vvedotarCent = (Vector)busquedas.variosFacadeRemote.buscaTarjetaViajeExistenteCentral(DBLinkCentral,tarExist.getFolioTarjeta());
//                if(vvedotarCent.size()>0)
//                {
//                    Vector vedotarCent = (Vector)vvedotarCent.get(0);
//                    if(vedotarCent.get(0).toString().equals("RECAUDADA"))
//                    {
//                        new jdlg_error("¡La tarjeta ya ha sido recaudada, ya no puede ser despachada ! ","Favor de contactar al Administrador del Sistema ","Tarjeta Recaudada").setVisible(true);
//                        return;
//                    }
//                }
//
//                if(remoto)
//                {
//                 boolean valor = busquedas.variosRemotoFacadeRemote.ActualizarCorrida(corridan.getCorridaId().longValue(),estado.getEstadoCorridaId().longValue(),nombreEsquema,DBLink);
//                 if(!valor)
//                 {
//                     new jdlg_error("¡No fue posible actualizar la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                     return;
//                 }
//                 boolean valorv = busquedas.variosRemotoFacadeRemote.ActualizarCorridaVenta(corrida.getCorridaId().longValue(),"D",nombreEsquema,DBLink);
//                 if(!valorv)
//                 {
//                     new jdlg_error("¡No fue posible actualizar la corrida "+corrida.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                     return;
//                 }
//                }
//                else
//                {
//                 busquedas.corridasVentaTblFacadeRemote.edit(corrida);
//                 busquedas.corridasTblFacadeRemote.edit(corridan);
//                }
//
//                tarExist.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(edotar.get(0).toString())));
//                tarExist.setReplicacionOrigen(nombreEsquema);
//                jlbl_claveOperador.setText(tarExist.getOperador());
//                Vector vnombre = (Vector) busquedas.variosFacadeRemote.buscarNombreOperador(tarExist.getOperador());
//                Vector nombre;
//                String nombreoperador  = "";
//                if(vnombre.size()==0)
//                {
//                 new jdlg_advertencia("¡El operador no se encontro registrado! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                 return;
//                }
//                else
//                {
//                    nombre = (Vector) vnombre.get(0);
//                    if(nombre.get(1)!=null && nombre.get(1).toString().indexOf("OPERADOR")==-1)
//                      nombreoperador =  nombre.get(0) + " "+nombre.get(1) +" " + nombre.get(2);
//                    else
//                      nombreoperador = nombre.get(0)+ " " + nombre.get(2);
//                }
//                jlbl_nombreOperador.setText(nombreoperador);
//                jlbl_autobus.setText(tarExist.getAutobus());
//                String clave = datosTarjeta.get(0).toString();
//                jlbl_folio.setText(clave.substring(0,10)+""+tarExist.getTarjetaViajeId());
//                String sfolio = ""+tarExist.getTarjetaViajeId();
//                String sviaje = sfolio.substring(3);
//                jlbl_viaje.setText(sviaje);//""+tarExist.getTarjetaViajeId());
//                long   noAdulTarexist = 0;
//                double mtoAdulTarexist = Float.valueOf("0.0").doubleValue();
//                long   noMenTarexist = 0;
//                double mtoMenTarexist = Float.valueOf("0.0").doubleValue();
//                long   noSenTarexist = 0;
//                double mtoSenTarexist = Float.valueOf("0.0").doubleValue();
//                long   noEstudTarexist = 0;
//                double mtoEstudTarexist = Float.valueOf("0.0").doubleValue();
//                long   noProfTarexist = 0;
//                double mtoProfTarexist = Float.valueOf("0.0").doubleValue();
//                long   noEspTarexist = 0;
//                double mtoEspTarexist = Float.valueOf("0.0").doubleValue();
//
//                   for (int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
//                   {
//                       if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ADULTO"))
//                       {
//                           tarExist.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                            noAdulTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                            mtoAdulTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                       }
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("MENOR"))
//                        {
//                           tarExist.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noMenTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoMenTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("SENECTUD"))
//                        {
//                           tarExist.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noSenTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoMenTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESTUDIANTE"))
//                        {
//                           tarExist.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noEstudTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoEstudTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("PROFESOR"))
//                        {
//                           tarExist.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noProfTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoProfTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESPECIAL"))
//                        {
//                           tarExist.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarExist.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noEspTarexist  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoEspTarexist = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//                   }
//                   long nus = Long.valueOf(datosTarjeta.get(12).toString());
//                   BigInteger usr= BigInteger.valueOf(nus);
//                   tarExist.setUltimaActualizacionPor(usr);
//                   tarExist.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
//
//                   long   edotarexist = Long.valueOf(edotar.get(0).toString());
//                   if(remoto)
//                   {
//                     boolean valor = busquedas.variosRemotoFacadeRemote.ActualizarTarjetaExistente(noAdulTarexist,mtoAdulTarexist,noMenTarexist,mtoMenTarexist,noProfTarexist,mtoProfTarexist,noSenTarexist,mtoSenTarexist,noEstudTarexist,mtoEstudTarexist,noEspTarexist,mtoEspTarexist,edotarexist,nombreEsquema,DBLink,tarExist.getTarjetaViajeId().longValue(),nus);
//                     if(!valor)
//                     {
//                         new jdlg_error("¡No fue posible actualizar la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                         return;
//                     }
//                   }
//                   else
//                     busquedas.tarjetasViajeTblFacadeRemote.edit(tarExist);
//
//                    TmsAutobusesTbl autobus = busquedas.autobusesTblFacadeRemote.buscaBusPorNumero(jlbl_autobus.getText());
//                    TmsOperadoresTbl operador = busquedas.operadoresTblFacadeRemote.buscaOperadorPorNombre(jlbl_claveOperador.getText());
//                    TmsEstadosTbl edoComp1= busquedas.estadosTblFacadeRemote.buscaEstadoPorNombre("ENROLADO");
//                    TmsEstadosTbl edoComp3= busquedas.estadosTblFacadeRemote.buscaEstadoPorNombre("CORRIDA");
//                    TmsAccionesTbl accComp1 = busquedas.accionesTblFacadeRemote.buscaAccionPorNombre("ENROLADO");
//                    TmsAccionesTbl accComp3 = busquedas.accionesTblFacadeRemote.buscaAccionPorNombre("CORRIDA");
//                    Vector vvComp3 = (Vector) busquedas.variosFacadeRemote.queryBuscaTerminalOperador();
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
//                        TmsAccionesTbl accComp2 = busquedas.accionesTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(comp3.toString())));
//                        operador.setAccion2Id(accComp2);
//                    }
//                    else
//                     new jdlg_error("¡La terminal de Acciones del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(edoComp3!=null)
//                     operador.setAccion3Id(accComp3);
//                    else
//                     new jdlg_error("¡La Accion CORRIDA del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    busquedas.operadoresTblFacadeRemote.edit(operador);
//                    busquedas.autobusesTblFacadeRemote.edit(autobus);
//
//
//           }
//           else
//           {
////                   busquedas.corridasVentaTblFacadeRemote.edit(corrida);
////                   busquedas.corridasTblFacadeRemote.edit(corridan);
//                    if(remoto)
//                    {
//                     boolean valor = busquedas.variosRemotoFacadeRemote.ActualizarCorrida(corridan.getCorridaId().longValue(),estado.getEstadoCorridaId().longValue(),nombreEsquema,DBLink);
//                     if(!valor)
//                     {
//                         new jdlg_error("¡No fue posible actualizar la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                         return;
//                     }
//                     boolean valorv = busquedas.variosRemotoFacadeRemote.ActualizarCorridaVenta(corrida.getCorridaId().longValue(),"D",nombreEsquema,DBLink);
//                     if(!valorv)
//                     {
//                         new jdlg_error("¡No fue posible actualizar la corrida "+corrida.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                         return;
//                     }
//                    }
//                    else
//                    {
//                     busquedas.corridasVentaTblFacadeRemote.edit(corrida);
//                     busquedas.corridasTblFacadeRemote.edit(corridan);
//                    }
//
//                   TmsTarjetasViajeTbl tarjeta = new TmsTarjetasViajeTbl();
//                   tarjeta.setFolioTarjeta(jlbl_folio.getText());
//                   tarjeta.setCorridaId(BigInteger.valueOf(nc.longValue()));//corridan);
//                   tarjeta.setAutobus(jlbl_autobus.getText());
//                   tarjeta.setOperador(jlbl_claveOperador.getText());
//                   tarjeta.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(edotar.get(0).toString())));
//                   tarjeta.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//
//                   tarjeta.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//
//                   tarjeta.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//
//                   tarjeta.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//
//                   tarjeta.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//
//                   tarjeta.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf("0.0").doubleValue()));
//                   tarjeta.setNumeroImpresion(BigInteger.valueOf(Long.valueOf("0")));
//                   tarjeta.setReplicacionOrigen(nombreEsquema);
//                long   noAdultarjeta = 0;
//                double mtoAdultarjeta = Float.valueOf("0.0").doubleValue();
//                long   noMentarjeta = 0;
//                double mtoMentarjeta = Float.valueOf("0.0").doubleValue();
//                long   noSentarjeta = 0;
//                double mtoSentarjeta = Float.valueOf("0.0").doubleValue();
//                long   noEstudtarjeta = 0;
//                double mtoEstudtarjeta = Float.valueOf("0.0").doubleValue();
//                long   noProftarjeta = 0;
//                double mtoProftarjeta = Float.valueOf("0.0").doubleValue();
//                long   noEsptarjeta = 0;
//                double mtoEsptarjeta = Float.valueOf("0.0").doubleValue();
//
//                   for (int i=0; i<jtbl_detalleAbordo.getRowCount();i++)
//                   {
//                       if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ADULTO"))
//                       {
//                           tarjeta.setNoAdultosAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoAdultosAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                            noAdultarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                            mtoAdultarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                       }
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("MENOR"))
//                        {
//                           tarjeta.setNoMenoresAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoMenoresAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noMentarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoMentarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("SENECTUD"))
//                        {
//                           tarjeta.setNoSenectudAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoSenectudAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noSentarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoMentarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESTUDIANTE"))
//                        {
//                           tarjeta.setNoEstudiantesAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoEstudiantesAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noEstudtarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoEstudtarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("PROFESOR"))
//                        {
//                           tarjeta.setNoProfesorAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoProfesorAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noProftarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoProftarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//
//                        if(jtbl_detalleAbordo.getValueAt(i,0).toString().equals("ESPECIAL"))
//                        {
//                           tarjeta.setNoEspecialAbordados(BigInteger.valueOf(Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString())));
//                           tarjeta.setMontoEspecialAbordados(BigDecimal.valueOf(Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue()));
//                           noEsptarjeta  = Long.valueOf(jtbl_detalleAbordo.getValueAt(i,1).toString());
//                           mtoEsptarjeta = Float.valueOf(jtbl_detalleAbordo.getValueAt(i,2).toString()).doubleValue();
//                        }
//                   }
//
//                   long nus = Long.valueOf(datosTarjeta.get(12).toString());
//                   BigInteger usr= BigInteger.valueOf(nus);
//                   tarjeta.setCreadoPor(usr);
//                   tarjeta.setUltimaActualizacionPor(usr);
//                   tarjeta.setFechaCreacion(new Date(fecha_servidor.getTime()));
//                   tarjeta.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
//                   if(autorizacion)
//                   {
//                        SimpleDateFormat formatfc = new SimpleDateFormat ("dd/MM/yyyy");
//                        SimpleDateFormat formathc = new SimpleDateFormat ("HH:mm");
//                        Vector vnumerousr= (Vector)busquedas.variosFacadeRemote.buscaNumeroUsuario(nus);
//                        tarjeta.setAdicional1("El usuario "+vnumerousr.get(0).toString()+" autorizo despachar la tarjeta aun cuando el operador tenia "+ntarpen+" tarjetas pendientes por recaudar a las "+formathc.format(fecha_servidor.getTime())+" del "+formatfc.format(fecha_servidor.getTime()));
//                   }
//                   if(autorizacion2)
//                   {
//                        SimpleDateFormat formatfc = new SimpleDateFormat ("dd/MM/yyyy");
//                        SimpleDateFormat formathc = new SimpleDateFormat ("HH:mm");
//                        long nus2 = Long.valueOf(datosTarjeta.get(14).toString());
//                        BigInteger usr2= BigInteger.valueOf(nus2);
//                        Vector vnumerousr= (Vector)busquedas.variosFacadeRemote.buscaNumeroUsuario(nus2);
//                        tarjeta.setAdicional2("El usuario "+usr2+" autorizo despachar la tarjeta aun cuando la tarjeta estaba bloqueda, a las "+formathc.format(fecha_servidor.getTime())+" del "+formatfc.format(fecha_servidor.getTime()));
//                       tarjeta.setUltimaActualizacionPor(usr2);
//                   }
//                   TmsTarjetasViajeTbl tar = null;
//                    if(remoto)
//                    {
//                      //boolean valor = busquedas.variosRemotoFacadeRemote.insertarTarjeta(tarjeta,idTerminal,DBLink);
//                      tar = busquedas.variosRemotoFacadeRemote.insertarTarjeta(tarjeta,idTerminal,DBLink);
//                      //if(!valor)
//                      if(tar==null)
//                      {
//                         new jdlg_error("¡No fue posible despachar la tarjeta de la corrida "+corridan.getClaveCorrida()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                         return;
//                      }
//                    }
//                    else
//                      tar = busquedas.tarjetasViajeTblFacadeRemote.create(tarjeta, idTerminal);
//
//                    TmsAutobusesTbl autobus = busquedas.autobusesTblFacadeRemote.buscaBusPorNumero(jlbl_autobus.getText());
//                    TmsOperadoresTbl operador = busquedas.operadoresTblFacadeRemote.buscaOperadorPorNombre(jlbl_claveOperador.getText());
//                    TmsEstadosTbl edoComp1= busquedas.estadosTblFacadeRemote.buscaEstadoPorNombre("ENROLADO");
//                    TmsEstadosTbl edoComp3= busquedas.estadosTblFacadeRemote.buscaEstadoPorNombre("CORRIDA");
//                    TmsAccionesTbl accComp1 = busquedas.accionesTblFacadeRemote.buscaAccionPorNombre("ENROLADO");
//                    TmsAccionesTbl accComp3 = busquedas.accionesTblFacadeRemote.buscaAccionPorNombre("CORRIDA");
//                    Vector vvComp3 = (Vector) busquedas.variosFacadeRemote.queryBuscaTerminalOperador();
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
//                        TmsAccionesTbl accComp2 = busquedas.accionesTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(comp3.toString())));
//                        operador.setAccion2Id(accComp2);
//                    }
//                    else
//                     new jdlg_error("¡La terminal de Acciones del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    if(edoComp3!=null)
//                     operador.setAccion3Id(accComp3);
//                    else
//                     new jdlg_error("¡La Accion CORRIDA del Operador esta mal configurada! ","Favor de contactar al administrador del sistema","Error de configuracion").setVisible(true);
//                    busquedas.operadoresTblFacadeRemote.edit(operador);
//                    busquedas.autobusesTblFacadeRemote.edit(autobus);
//                    String clave = datosTarjeta.get(0).toString();
//                    if(remoto)
//                    {
//                         Vector vvTarjeta = busquedas.variosRemotoFacadeRemote.buscaTarjetaCreada(tar.getCorridaId().longValue(),DBLink);
//                         if(vvTarjeta.size()>0)
//                         {
//                             Vector vTarjeta = (Vector)vvTarjeta.get(0);
//                             jlbl_folio.setText(clave.substring(0,10)+""+vTarjeta.get(0).toString());
//                             String sfolio = vTarjeta.get(0).toString();
//                             String sviaje = sfolio.substring(3);
//                             jlbl_viaje.setText(sviaje);//
//                             //jlbl_viaje.setText(vTarjeta.get(0).toString());
//                         }
//                         if(nfol==1)
//                         {
//                             boolean valor = busquedas.variosRemotoFacadeRemote.ActualizarFolioTarjeta(DBLink,tar.getTarjetaViajeId().longValue(),jlbl_folio.getText());
//                             if(!valor)
//                             {
//                                 new jdlg_error("¡No fue posible actualizar la tarjeta con folio "+jlbl_folio.getText()+"! ","Favor de contactar al Administrador del Sistema","Error de datos").setVisible(true);
//                                 return;
//                             }                         //ActualizarFolioTarjeta
//                         }
//                    }
//                    else
//                    {
//                         jlbl_folio.setText(clave.substring(0,10)+""+tar.getTarjetaViajeId());
//                         String sfolio = ""+tar.getTarjetaViajeId();
//                         String sviaje = sfolio.substring(3);
//                         jlbl_viaje.setText(sviaje);//tar.getTarjetaViajeId());
//                          System.out.println("nfol = "+nfol+"     Folio Nuevo:"+jlbl_folio.getText());
//                          if(nfol==1)
//
//                          {
//                              tar.setFolioTarjeta(jlbl_folio.getText());
//                              busquedas.tarjetasViajeTblFacadeRemote.edit(tar);
//                          }
//                    }
//
//           }
//           copia = 0;
//           if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
//            imprimirTarjetaLPT();
//           if(puerto.equals("RED") || puerto.equals("USB"))
//              imprimir_tarjeta_windows();
//           //new jdlg_informacion("¡La tarjeta fue despachada correctamente!","","Tarjeta despachada").setVisible(true);
//           this.dispose();
//
//        }
    }//GEN-LAST:event_jlbl_barraEstadoKeyPressed
    

  
    /*
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
            String CodigoImpresora =""+
"                                                                                            "+datosTarjeta.get(15).toString().toUpperCase()+"\n"+rc+//AUTOBUSES MEXPUE ESTRELLA ROJA\n"+rc+
"                                                                                                    TARJETA DE VIAJE\n"+rc+      
"                                                                                            ORIGEN       SERVICIO       AUTOBUS   \n"+rc+  
"                                                                                            ---------  -------------     -------    \n"+rc+
" "+cp+"                                                                                  "+jlbl_origen.getText()+"     "+jlbl_servicio.getText()+"    "+jlbl_autobus.getText()+"    \n"+rc+
"                                                                                            DESCRIPCION DEL VIAJE       \n"+rc+
"                                                                                            "+jlbl_descipcionViaje.getText()+"          \n"+rc+
"                                                                                          FOLIO             OPERADOR    FECHA      HORA\n"+rc+  
"                                                                                          ----------------  --------  ----------  -----\n"+rc+
"                                                                                        "+jlbl_folio.getText()+"        "+jlbl_claveOperador.getText()+"    "+jlbl_fecha.getText()+"  "+jlbl_hora.getText()+"\n"+rc+
"                                                                                              "+jlbl_nombreOperador.getText()+"        \n"+rc+
" ORIGEN : "+jlbl_origen2.getText()+"   DESTINO : "+jlbl_destino2.getText()+"   RUTA : "+jlbl_ruta.getText()+"  CORRIDA : "+jlbl_claveCorrida.getText()+"\n"+rc+
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
CodigoImpresora = CodigoImpresora +
"|"+num+"|"+cd+"|"+t+"|"+boleto+"|"+num2+"|"+cd2+"|"+t2+"|"+boleto2+"|"+num3+"|"+cd3+"|"+t3+"|"+boleto3+"|"+num4+"|"+cd4+"|"+t4+"|"+boleto4+"| FIRMA _____________________   VIAJE: "+jlbl_viaje.getText()+"\n"+rc+
" -------------------------------------------------------------------------------------------                                               \n\n"+rc;                       
                    
                    
                    
                    
                    
                    
                    
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
          //  try {
                //if(puerto.equals("ARCHIVO"))
                // os = new FileOutputStream("C:\\"+jlbl_folio.getText()+".TXT");
                //else
                //     os = new FileOutputStream(puerto);
           // } catch (FileNotFoundException ex) {
          //      ex.printStackTrace();
           // }//SalidaImpresion); // LPT1 / C:\\ARCHIVO.TXT / COM1
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
    
*/


    /**
     * 
     * 
     * @param args the command line arguments
     *     
     *    public static void main(String args[]) {
     *        java.awt.EventQueue.invokeLater(new Runnable() {
     *            public void run() {
     *                new Jdlg_DetalleOcupacion("Vista previa de la tarjeta de viaje", 47).setVisible(true);
     *            }
     *        });
     *    }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_claveCorrida;
    private javax.swing.JLabel jlbl_destino2;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_hora;
    private javax.swing.JLabel jlbl_origen2;
    private javax.swing.JLabel jlbl_servicio;
    private javax.swing.JTable jtbl_boletosDetalle;
    private javax.swing.JTable jtbl_detalleAbordo;
    private javax.swing.JLabel jtxt_empresaTitulo;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadoBoletos = {"#"," CD "," T " ," NOMBRE PASAJERO ","#"," CD "," T " ," NOMBRE PASAJERO ","#"," CD "," T " ," NOMBRE PASAJERO ","#"," CD "," T " ," NOMBRE PASAJERO "};
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

    private TmsConsultasManagedBean busquedas;

    private Vector tipos;

    private Vector datosTarjeta;
    private Vector ocupacionModificada;
    private Timestamp fecha_servidor= null; 
    private Object[][] sinOcupacion;
    private boolean autorizacion;
    private boolean autorizacion2;
    private int ntarpen;
    private String idTerminal;
    private boolean remoto;
    private String DBLink;
    private boolean respuestaSN = true;
    private long nfol;
    private boolean reimpresion = false;
    private int copia;
    private boolean permiso;
            
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
 

 
 
}
