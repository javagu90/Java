/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.layout.GroupLayout;

/**
 *
 * @author EKS Victor
 */
public class JdlgPreguntaSN extends JDialog {

  private JLabel jLabel1;
  private JLabel jlbl_barraEstado;
  private JLabel jlbl_mensaje;
  private boolean respuestaSN;

  public JdlgPreguntaSN(String titulo, String pregunta) {
    setTitle(titulo);
    setModal(true);
    setDefaultLookAndFeelDecorated(true);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(7);
    setAlwaysOnTop(true);
    initComponents();

    setResizable(false);
    this.jlbl_mensaje.setText(pregunta);
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
    this.jlbl_barraEstado.setHorizontalAlignment(JTextField.LEADING);
    int nletras = pregunta.length();
    setSize(nletras * 6 + 80, 155);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension DilaogSize = getSize();
    if (DilaogSize.height > screenSize.height) {
      DilaogSize.height = screenSize.height;
    }
    if (DilaogSize.width > screenSize.width) {
      DilaogSize.width = screenSize.width;
    }
    setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2);
    setDefaultLookAndFeelDecorated(true);

    getRootPane().setWindowDecorationStyle(7);
    this.jlbl_mensaje.requestFocus();
  }

  private void initComponents() {
    this.jLabel1 = new JLabel();
    this.jlbl_barraEstado = new JLabel();
    this.jlbl_mensaje = new JLabel();
    this.jlbl_mensaje.setFocusTraversalKeysEnabled(false);

    setDefaultCloseOperation(2);
    this.jLabel1.setIcon(new ImageIcon(JdlgPreguntaSN.class.getResource("/com/estrellaroja/confirmacionviajes/forms/images/pregunta.gif")));

    this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 12));
    this.jlbl_barraEstado.setForeground(new Color(0, 0, 0));
    this.jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
    this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

    this.jlbl_mensaje.setFont(new Font("Arial", 1, 12));
    this.jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
    this.jlbl_mensaje.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent evt) {
        jlbl_mensajeKeyPressed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jLabel1).add(14, 14, 14).add(this.jlbl_mensaje, -1, 321, 32767).add(424, 424, 424)).add(this.jlbl_barraEstado, -1, 800, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(21, 21, 21).add(layout.createParallelGroup(3).add(this.jLabel1, -2, 42, -2).add(this.jlbl_mensaje)).addPreferredGap(0, 59, 32767).add(this.jlbl_barraEstado, -2, 27, -2)));

    pack();
  }

  private void jlbl_mensajeKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 27) {
      //JIFRecaudacion.access$9202(JIFRecaudacion.this, false);
      respuestaSN = false;
      dispose();
    }
    if (evt.getKeyCode() == 10) {
      respuestaSN = true;
      dispose();
    }
  }

  public boolean isRespuestaSN() {
    return respuestaSN;
  }
}
