/*
 * Jdlg_Pregunta.java
 *
 * Created on 13 de agosto de 2007, 05:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_insprecol.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 *
 * @author ocruz
 */
     public class Jdlg_Pregunta extends JDialog {
             private JLabel jLabel1 = new JLabel();
             private JLabel jLabel2 = new JLabel();
             private JLabel jLabel3 = new JLabel();
             private ImageIcon imagen_pregunta = new ImageIcon("C:\\Documents and Settings\\vgonzalez\\TMS_Catalogos\\TMS_Catalogos-app-client\\src\\java\\tms_catalogos\\Imagenes\\pregunta.GIF" );//Jdlg_Pregunta.class.getResource("pregunta.gif"));
             private JButton jbtn_respuesta_si = new JButton();
             private JButton jbtn_respuesta_no = new JButton();
             
             private int Respuesta;
             private String mensaje;
                          
             public Jdlg_Pregunta(String title, String pMensaje) {
                 this.mensaje = pMensaje;
                 this.setTitle(title);
                 this.setModal(true);
                 this.setDefaultLookAndFeelDecorated(true);
                 this.setUndecorated(true);
                 this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
                 this.setAlwaysOnTop(true);
                 this.Respuesta = 0;
                 try {
                     jbInit();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
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
                 jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
                 jLabel2.setSize(new Dimension(35, 45));
                 jLabel2.setIcon(imagen_pregunta);
                 jbtn_respuesta_si.setText("Si");
                 jbtn_respuesta_si.setBounds(new Rectangle(115, 50, 50, 25));
                 jbtn_respuesta_si.setMnemonic('s');
                 jbtn_respuesta_si.setFont(new Font("Arial", 1, 12));
                 jbtn_respuesta_si.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent e) {
                                 jbtn_respuesta_si_actionPerformed(e);
                             }
                         });
                 jbtn_respuesta_si.addKeyListener(new KeyAdapter() {
                             public void keyPressed(KeyEvent e) {
                                 jbtn_respuesta_si_keyPressed(e);
                             }
                         });
                 jbtn_respuesta_no.setText("No");
                 jbtn_respuesta_no.setBounds(new Rectangle(175, 50, 50, 25));
                 jbtn_respuesta_no.setMnemonic('N');
                 jbtn_respuesta_no.setFont(new Font("Arial", 1, 12));
                 jbtn_respuesta_no.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent e) {
                                 jbtn_respuesta_no_actionPerformed(e);
                             }
                         });
                 jbtn_respuesta_no.addKeyListener(new KeyAdapter() {
                             public void keyPressed(KeyEvent e) {
                                 jbtn_respuesta_no_keyPressed(e);
                             }
                         });
                 jLabel3.setBounds(new Rectangle(45, 15, 285, 15));
                 jLabel3.setFont(new Font("Arial", 1, 12));
                 jLabel3.setText(mensaje);
                 this.getContentPane().add(jLabel3, null);
                 this.add(jbtn_respuesta_no, null);
                 this.add(jbtn_respuesta_si, null);
                 this.add(jLabel2, null);
                 //this.add(jLabel1, null);
             }

             private void jbtn_respuesta_si_actionPerformed(ActionEvent e) {
                 this.Respuesta = 1;
                 this.dispose();
             }

             private void jbtn_respuesta_no_actionPerformed(ActionEvent e) {
                 this.Respuesta = 0;
                 this.dispose();
             }

             private void jbtn_respuesta_si_keyPressed(KeyEvent e) {
                 if(e.getKeyCode() == 37 || e.getKeyCode() == 38)
                     jbtn_respuesta_no.requestFocus();
                 if(e.getKeyCode() == 39 || e.getKeyCode() == 40)
                     jbtn_respuesta_no.requestFocus();
                 if(e.getKeyCode() == 10)
                     jbtn_respuesta_si.doClick();
             }

             private void jbtn_respuesta_no_keyPressed(KeyEvent e) {
                 if(e.getKeyCode() == 37 || e.getKeyCode() == 38)
                     jbtn_respuesta_si.requestFocus();
                 if(e.getKeyCode() == 39 || e.getKeyCode() == 40)
                     jbtn_respuesta_si.requestFocus();
                 if(e.getKeyCode() == 10)
                     jbtn_respuesta_no.doClick();
             }
             
             public int getRespuesta(){
                 return this.Respuesta;
             }
         }