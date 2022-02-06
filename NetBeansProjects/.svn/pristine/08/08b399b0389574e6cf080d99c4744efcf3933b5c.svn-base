/*
 * JDlgCalendario.java
 *
 * Created on 17 de julio de 2007, 08:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package Dialogos;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Formulario que se utiliza para la cancelacion de boletos, el cual pide el tipo
 * Corrida y Asiento en otro caso pide el numero del folio del boleto.
 * indica con mensajes de cancelacion valida.
 * @author Ivan E. Morales 
 * @version 1.01    27/02/2007
 */

public class JDlgCalendario extends JDialog{
    private JComboBox jCboMes;
    private JComboBox jCboAnho;
    private Object[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
                              "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private Object[] anhos = new Object[5];
    private Object[] dias = {"Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"};
    private Object[][] datos = new Object[6][7];
    private int[] diasPorMes = {
                                31, 28, 31, 30, 31, 30,
                                31, 31, 30, 31, 30, 31
                            };
    private int xM, xA;
    private int xMz, xAz;
    private DefaultTableModel xTabla = new DefaultTableModel(null,dias){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
    private JScrollPane jscpDias = new JScrollPane();
    private JTable jtblDias;
    
    public JDlgCalendario() {
        try {
            jbInit();
            this.setResizable(false);
            centrarJDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setModal(true);
        this.setBounds(new Rectangle(10, 10, 300, 195));
        this.setTitle("Calendario");
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        jtblDias = new JTable(xTabla);
        Date xDate = new Date();
        xM = Integer.valueOf(new SimpleDateFormat("M").format(xDate));
        xA = Integer.valueOf(new SimpleDateFormat("yyyy").format(xDate));
        for(int i=-1; i<4; i++)
            anhos[i+1] = xA+i;
        jCboMes = new JComboBox(meses);
        jCboAnho = new JComboBox(anhos);
        jCboMes.setSelectedIndex(xM-1);
        jCboAnho.setSelectedItem(xA);
        Calendario(xM-1, xA);
        xMz=xM; xAz=xA;
        jCboMes.setBounds(new Rectangle(5, 5, 95, 22));
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jCboMes.requestFocusInWindow();
            }
        });
        jCboMes.setFont(new Font("Dialog", 1, 11));
        jCboMes.setNextFocusableComponent(jCboAnho);
                jCboMes.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jCboMes_keyPressed(e);
                    }
                });
                
                jCboAnho.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jCboAnho_keyPressed(e);
                    }
                });
        jCboAnho.setBounds(new Rectangle(110, 5, 95, 22));
        jCboAnho.setFont(new Font("Dialog", 1, 11));
        jscpDias.setBounds(new Rectangle(5, 35, 280, 130));
        jscpDias.setFocusable(false);
        jtblDias.setFocusable(false);
        jtblDias.setRowSelectionAllowed(false);
        jtblDias.setColumnSelectionAllowed(false);
        jtblDias.getTableHeader().setReorderingAllowed(false);
        jtblDias.getTableHeader().setResizingAllowed(false);
        jtblDias.setFont(new Font("Dialog", 1, 11));
        jscpDias.getViewport().add(jtblDias, null);
        this.getContentPane().add(jCboMes, null);
        this.getContentPane().add(jCboAnho, null);
        this.getContentPane().add(jscpDias, null);
        //this.jlblMensajes.set_mensajeCancelar(2);
    }

    private void centrarJDialog(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
    }

    private void jCboMes_keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                if(jCboMes.getSelectedIndex()<jCboMes.getItemCount()-1)
                    jCboMes.setSelectedIndex(jCboMes.getSelectedIndex()+1);
                else
                    jCboMes.setSelectedIndex(0);
                break;
            case KeyEvent.VK_LEFT:
                if(jCboMes.getSelectedIndex()>0)
                    jCboMes.setSelectedIndex(jCboMes.getSelectedIndex()-1);
                else
                    jCboMes.setSelectedIndex(jCboMes.getItemCount()-1);
                break;
            case KeyEvent.VK_ENTER:
                xM=jCboMes.getSelectedIndex();
                xA=Integer.valueOf(jCboAnho.getSelectedItem().toString());
                if(xMz!=xM){
                    Calendario(xM, xA);
                    xMz=xM;
                }
                jCboAnho.requestFocus();
                break;
            case KeyEvent.VK_ESCAPE: this.dispose();
        }
    }
        
    private void jCboAnho_keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                if(jCboAnho.getSelectedIndex()<jCboAnho.getItemCount()-1)
                    jCboAnho.setSelectedIndex(jCboAnho.getSelectedIndex()+1);
                else
                    jCboAnho.setSelectedIndex(0);
                break;
            case KeyEvent.VK_LEFT:
                if(jCboAnho.getSelectedIndex()>0)
                    jCboAnho.setSelectedIndex(jCboAnho.getSelectedIndex()-1);
                else
                    jCboAnho.setSelectedIndex(jCboAnho.getItemCount()-1);
                break;
            case KeyEvent.VK_ENTER:
                xM=jCboMes.getSelectedIndex();
                xA=Integer.valueOf(jCboAnho.getSelectedItem().toString());
                if(xAz!=xA){
                    Calendario(xM, xA);
                    xAz=xA;
                }
                jCboMes.requestFocus();
            break;
            case KeyEvent.VK_ESCAPE: this.dispose();
        }
    }

    private void AnchoColumnas(JTable jtblTabla, int ancho) {
        int anchoContenedor = ancho;
        TableColumn column;
        for (int i = 0; i < jtblTabla.getColumnCount(); i++) {
            column = jtblTabla.getColumnModel().getColumn(i);
            switch (i) {
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.145)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.1425)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.1425)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.1425)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.1425)); break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.145)); break;
            }
        }
    }
    
/******************************* CALENDARIO ***********************************/
    private void Calendario(int pMes, int pAnho){
        datos = new Object[6][7];
        int hoy = 0;
        int diasDeMes = 0;
        int celVacias = 0;
        
        GregorianCalendar actCal = (GregorianCalendar) Calendar.getInstance();
        GregorianCalendar Cal = new GregorianCalendar(pAnho, pMes, 1);
        
        if(actCal.get(Calendar.MONTH) == Cal.get(Calendar.MONTH) &&
           actCal.get(Calendar.YEAR) == Cal.get(Calendar.YEAR)) hoy = Cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("HOY ES DIA: "+hoy);
        diasDeMes = diasPorMes[pMes];
        
        if(pMes == Calendar.FEBRUARY && Cal.isLeapYear(pAnho)) diasDeMes++;
        
        celVacias = Cal.get(Calendar.DAY_OF_WEEK)-1;
        
        int fila = 0;
        int i=1,j;
        while(i <= diasDeMes){
            for(j=celVacias; j<7; j++){
                if(i == hoy) datos[fila][j]="HOY-"+i;
                else datos[fila][j]=i;
                i++;
                if(i > diasDeMes) break;
            }
            fila++;
            celVacias=0;
        }
        xTabla.setDataVector(datos,dias);
        AnchoColumnas(jtblDias,280);
    }
}