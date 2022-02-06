/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JIFConfirmacionViajes.java
 *
 * Created on 27/02/2014, 05:02:09 PM
 */
package com.estrellaroja.confirmacionviajes.forms;

import com.estrellaroja.confirmacionviajes.entidades.TmsBaseDatos;
import com.estrellaroja.confirmacionviajes.entidades.TmsSesion;
import com.estrellaroja.confirmacionviajes.util.EntityDataModel;
import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import com.estrellaroja.confirmacionviajes.entidades.TmsUsuario;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsBaseDatosFacadeRemote;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsSesionFacadeRemote;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsUsuariosFacadeRemote;
import com.estrellaroja.confirmacionviajes.solicitudes.generalFacadeRemote;
import com.estrellaroja.confirmacionviajes.util.CustomTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

/**
 *
 * @author EKS Victor
 */
public class JdlgConfirmacionViajes extends javax.swing.JDialog {

  private final Vector datosIniciales;
  private final Long USUARIO_ID;
  private final String USUARIO_NUMERO;
  private final String USUARIO_NOMBRE;
  private final Long SESION_ID;
  private final Long MENU_ID;
  private BigInteger origen_id;
  private TmsUsuario currentUser;
  private TmsUsuariosFacadeRemote tmsUsuariosFacade;
  private TmsBaseDatosFacadeRemote basesDeDatosFacade;
  private KeyEvent eventoTeclado;
  private String folio_tarjeta = "";
  private String terminal = "";
  private final int[] anchoColumnas = {155, 150, 150, 100, 100, 230, 70, 100, 0, 100, 60, 60, 125, 125};

  public void setFoco() {
    jtxtFolio.requestFocus();
  }

  public KeyEvent getEventoTeclado() {
    return this.eventoTeclado;
  }

  public void setEventoTeclado(KeyEvent evento) {
    this.eventoTeclado = null;
  }

  // <editor-fold defaultstate="collapsed" desc="lookups">
  private TmsTarjetasViajeFacadeRemote lookupTmsTarjetasViajeFacade() {
    try {
      Context c = new InitialContext();
      return (TmsTarjetasViajeFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }
  
  // <editor-fold defaultstate="collapsed" desc="lookups">
  private generalFacadeRemote lookupGeneralFacadeRemote() {
    try {
      Context c = new InitialContext();
      return (generalFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.generalFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }  

  private TmsUsuariosFacadeRemote lookupTmsUsuariosFacade() {
    try {
      Context c = new InitialContext();
      return (TmsUsuariosFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsUsuariosFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }

  private TmsBaseDatosFacadeRemote lookupTmsBasedeDatosFacade() {
    try {
      Context c = new InitialContext();
      return (TmsBaseDatosFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsBaseDatosFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }

  private TmsSesionFacadeRemote lookupTmsSesionFacade() {
    try {
      Context c = new InitialContext();
      return (TmsSesionFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsSesionFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }
// </editor-fold>

  public JdlgConfirmacionViajes(Vector datosIniciales) {
    this(datosIniciales, "");
  }
  public JdlgConfirmacionViajes(Vector datosIniciales, String folio_tarjeta) {
    this(datosIniciales, "","");
  }

  /** Creates new form JIFConfirmacionViajes */
  public JdlgConfirmacionViajes(Vector datosIniciales, String folio_tarjeta, String terminal) {
    this.datosIniciales = datosIniciales;
    this.USUARIO_ID = new Long(datosIniciales.get(0).toString());
    this.USUARIO_NUMERO = datosIniciales.get(1).toString();
    this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
    this.SESION_ID = new Long(datosIniciales.get(3).toString());
    this.MENU_ID = new Long(datosIniciales.get(4).toString());
    this.folio_tarjeta = folio_tarjeta;
//    System.out.println("Datos Recibidos: ");
//    System.out.println("Folio: "+this.folio_tarjeta);
//    System.out.println("Terminal: "+terminal);
    validateSession();
    setCurrentUser();
    initComponents();
    this.jtxtFolio.setText(folio_tarjeta);
    jtpSearch.setMnemonicAt(0, KeyEvent.VK_F);
    jtpSearch.setMnemonicAt(1, KeyEvent.VK_O);

    tarjetasViajeFacade = lookupTmsTarjetasViajeFacade();
    generalfacadeRemote = lookupGeneralFacadeRemote();
    ArrayList<TmsTarjetasViaje> tjs = new ArrayList<TmsTarjetasViaje>();
    tableModel = new EntityDataModel(TmsTarjetasViaje.class, tjs);
    jtblTarjetas.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    jtblTarjetas.setDefaultRenderer(Timestamp.class, new CustomTableCellRenderer());
    jtblTarjetas.setDefaultRenderer(BigInteger.class, new CustomTableCellRenderer());
    jtxtFolio.requestFocus();
    jtblTarjetas.setModel(tableModel);
    setTerminalesComboBoxes();
    for(int i=0; i<jcmbOrigenFolio.getItemCount(); i++)
    {
        TmsBaseDatos origen = (TmsBaseDatos) jcmbOrigenFolio.getItemAt(i);
        if(origen.getNombreTerminal().equals(terminal))
        {
            origen_id = origen.getTerminalId();
            jcmbOrigenFolio.setSelectedItem(origen);
        }
    }
    System.out.println("TimeZone(Antes): " + TimeZone.getDefault());
    TimeZone.setDefault(generalfacadeRemote.getTimeZone());
    System.out.println("TimeZone(Despues): " + TimeZone.getDefault());

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
    getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$

      public void actionPerformed(ActionEvent e) {
        JdlgPreguntaSN salir = new JdlgPreguntaSN("Confirmacion de salida", "¿Seguro que desea salir de Confirmacion?");
        salir.setVisible(true);
        if (salir.isRespuestaSN()) {
          dispose();
        }
      }
    });

    setColumnsWidths();
    buscarTarjetas();
    this.setModal(true);
  }
/*
  @Override
  public void setVisible(boolean b) {
    System.out.println("Trae el ID8: "+origen_id);
    if (b) {
      if (this.folio_tarjeta.length() > 0) {
        if (this.terminal.length() > 0) {
        }
        buscarTarjeta(this.folio_tarjeta, this.terminal);
      } else {
        buscarTarjeta(this.folio_tarjeta);
      }
    }
    System.out.println("Trae el ID9: "+origen_id);
    super.setVisible(b);
    System.out.println("Trae el ID10: "+origen_id);
  }
*/
  private void setTerminalesComboBoxes() {
    basesDeDatosFacade = lookupTmsBasedeDatosFacade();
    List<TmsBaseDatos> dbs = basesDeDatosFacade.findAll();
    if (dbs != null) {
      dbs.add(0, new TmsBaseDatos(BigDecimal.ZERO, "", "", null, null));
      jcmbOrigenFolio.setModel(new DefaultComboBoxModel(dbs.toArray()));
      jcmbOrigenOperador.setModel(new DefaultComboBoxModel(dbs.toArray()));
    }
  }

  private void setCurrentUser() {
    tmsUsuariosFacade = lookupTmsUsuariosFacade();
    currentUser = tmsUsuariosFacade.findUsuario(new BigDecimal(USUARIO_ID));
    if (currentUser == null) {
      new JdlgError("¡Usuario invalido!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
      System.exit(0);
    }

  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpSearch = new javax.swing.JTabbedPane();
        jpnlByFolio = new javax.swing.JPanel();
        jlblFolio = new javax.swing.JLabel();
        jtxtFolio = new tms_TextFields.JTextTextField();
        jlblOrigenFolio = new javax.swing.JLabel();
        jcmbOrigenFolio = new javax.swing.JComboBox();
        jpnlByOperador = new javax.swing.JPanel();
        jlblOperador = new javax.swing.JLabel();
        jntxtOperador = new tms_TextFields.JNumberTextField();
        jlblOrigenOperador = new javax.swing.JLabel();
        jcmbOrigenOperador = new javax.swing.JComboBox();
        jscpTarjetas = new javax.swing.JScrollPane();
        jtblTarjetas = new javax.swing.JTable();
        jcmdSearch = new javax.swing.JButton();
        jcmdConfirm = new javax.swing.JButton();
        jlbl_barraEstado = new javax.swing.JLabel();
        jcmdOperador = new javax.swing.JButton();
        jcmdVentaManual = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Confirmación de Viaje");

        jlblFolio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlblFolio.setLabelFor(jtxtFolio);
        jlblFolio.setText("Folio:");

        jtxtFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformedBuscarTajeras(evt);
            }
        });

        jlblOrigenFolio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlblOrigenFolio.setText("Origen");

        jcmbOrigenFolio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout jpnlByFolioLayout = new org.jdesktop.layout.GroupLayout(jpnlByFolio);
        jpnlByFolio.setLayout(jpnlByFolioLayout);
        jpnlByFolioLayout.setHorizontalGroup(
            jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlByFolioLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblFolio)
                    .add(jlblOrigenFolio))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jcmbOrigenFolio, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jtxtFolio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlByFolioLayout.setVerticalGroup(
            jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlByFolioLayout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblFolio)
                    .add(jtxtFolio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jpnlByFolioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblOrigenFolio)
                    .add(jcmbOrigenFolio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jtpSearch.addTab("Por Folio", jpnlByFolio);

        jlblOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlblOperador.setText("Operador:");

        jntxtOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformedBuscarTajeras(evt);
            }
        });

        jlblOrigenOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlblOrigenOperador.setText("Origen");

        jcmbOrigenOperador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout jpnlByOperadorLayout = new org.jdesktop.layout.GroupLayout(jpnlByOperador);
        jpnlByOperador.setLayout(jpnlByOperadorLayout);
        jpnlByOperadorLayout.setHorizontalGroup(
            jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlByOperadorLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblOperador)
                    .add(jlblOrigenOperador))
                .add(18, 18, 18)
                .add(jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jcmbOrigenOperador, 0, 280, Short.MAX_VALUE)
                    .add(jntxtOperador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlByOperadorLayout.setVerticalGroup(
            jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlByOperadorLayout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblOperador)
                    .add(jntxtOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jpnlByOperadorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblOrigenOperador)
                    .add(jcmbOrigenOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jtpSearch.addTab("Por Operador", jpnlByOperador);

        jscpTarjetas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jtblTarjetas.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtblTarjetas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblTarjetas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtblTarjetas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblTarjetasKeyPressed(evt);
            }
        });
        jscpTarjetas.setViewportView(jtblTarjetas);

        jcmdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/estrellaroja/confirmacionviajes/forms/images/find.png"))); // NOI18N
        jcmdSearch.setMnemonic('B');
        jcmdSearch.setText("Buscar");
        jcmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformedBuscarTajeras(evt);
            }
        });

        jcmdConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/estrellaroja/confirmacionviajes/forms/images/accept.png"))); // NOI18N
        jcmdConfirm.setMnemonic('C');
        jcmdConfirm.setText("Confirmar");
        jcmdConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdConfirmActionPerformed(evt);
            }
        });

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_barraEstado.setText("  ESC: Termina     ENTER: Selecciona     F5: Reimprmir Ticket");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jcmdOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/estrellaroja/confirmacionviajes/forms/images/find.png"))); // NOI18N
        jcmdOperador.setMnemonic('M');
        jcmdOperador.setText("Modificar Datos");
        jcmdOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOperadoractionPerformedBuscarTajeras(evt);
            }
        });

        jcmdVentaManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/estrellaroja/confirmacionviajes/forms/images/accept.png"))); // NOI18N
        jcmdVentaManual.setMnemonic('V');
        jcmdVentaManual.setText("Registrar Venta Manual");
        jcmdVentaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdVentaManualActionPerformed(evt);
            }
        });

        jLabel1.setText("Rev. 15072014");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jscpTarjetas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jtpSearch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jcmdSearch)
                                        .add(18, 18, 18)
                                        .add(jcmdConfirm))
                                    .add(jcmdOperador)
                                    .add(jcmdVentaManual))
                                .add(68, 68, 68))
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jtpSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .add(17, 17, 17)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jcmdSearch)
                            .add(jcmdConfirm))
                        .add(18, 18, 18)
                        .add(jcmdOperador)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jcmdVentaManual)))
                .add(22, 22, 22)
                .add(jscpTarjetas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void jcmdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdConfirmActionPerformed
    confirmarTarjeta();
  }//GEN-LAST:event_jcmdConfirmActionPerformed

  private void actionPerformedBuscarTajeras(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionPerformedBuscarTajeras
    buscarTarjetas();
  }//GEN-LAST:event_actionPerformedBuscarTajeras

  private void jcmdOperadoractionPerformedBuscarTajeras(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOperadoractionPerformedBuscarTajeras
    cambiarOperador();
  }//GEN-LAST:event_jcmdOperadoractionPerformedBuscarTajeras

  private void jcmdVentaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdVentaManualActionPerformed
    cambiarVentaManual();
  }//GEN-LAST:event_jcmdVentaManualActionPerformed

  private void jtblTarjetasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblTarjetasKeyPressed
    switch(evt.getKeyCode()){
            case KeyEvent.VK_F5:
                reimprimirTicket();
                break;
    }
  }//GEN-LAST:event_jtblTarjetasKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jcmbOrigenFolio;
    private javax.swing.JComboBox jcmbOrigenOperador;
    private javax.swing.JButton jcmdConfirm;
    private javax.swing.JButton jcmdOperador;
    private javax.swing.JButton jcmdSearch;
    private javax.swing.JButton jcmdVentaManual;
    private javax.swing.JLabel jlblFolio;
    private javax.swing.JLabel jlblOperador;
    private javax.swing.JLabel jlblOrigenFolio;
    private javax.swing.JLabel jlblOrigenOperador;
    private javax.swing.JLabel jlbl_barraEstado;
    private tms_TextFields.JNumberTextField jntxtOperador;
    private javax.swing.JPanel jpnlByFolio;
    private javax.swing.JPanel jpnlByOperador;
    private javax.swing.JScrollPane jscpTarjetas;
    private javax.swing.JTable jtblTarjetas;
    private javax.swing.JTabbedPane jtpSearch;
    private tms_TextFields.JTextTextField jtxtFolio;
    // End of variables declaration//GEN-END:variables
  private EntityDataModel tableModel;
  private static TmsTarjetasViajeFacadeRemote tarjetasViajeFacade;
  private static generalFacadeRemote generalfacadeRemote;

  private void confirmarTarjeta() {
    int row = jtblTarjetas.getSelectedRow();
    if (row >= 0) {
      //int row2 = jtblTarjetas.convertRowIndexToModel(row);
        int row2 = jtblTarjetas.getSelectedRow();
      TmsTarjetasViaje tarjeta = (TmsTarjetasViaje) tableModel.getRow(row2);
      if (tarjeta.getEstadoTarjeta().equalsIgnoreCase("Abierta")) {
        if (!tarjeta.getRecaudacionAutomatica().equalsIgnoreCase("S")) {
          String result = tarjetasViajeFacade.confirmarTarjeta(tarjeta.getTarjetaViajeId(), USUARIO_ID, origen_id, tarjeta.getClaveOperador(), tarjeta.getAutobus(), tarjeta.getBoletosVentaManual(), tarjeta.getImporteVentaManual());
          if (result.equalsIgnoreCase("")) {
            new JdlgMessage(JRootPane.QUESTION_DIALOG, "Tarjeta Confirmada", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
            tarjeta.setEstadoTarjeta("CONFIRMADA");
            jtblTarjetas.updateUI();
          } else {
            new JdlgError("Tarjeta No Confirmada", result, "Confirmacion de Tarjetas de Viaje").setVisible(true);
          }
        } else {
          if (JdlgVistaPagoImpresion.showTicket(tarjeta, this.USUARIO_NUMERO + " - " + this.USUARIO_NOMBRE, false)) {
            String result = tarjetasViajeFacade.confirmarTarjeta(tarjeta.getTarjetaViajeId(), USUARIO_ID, origen_id, tarjeta.getClaveOperador(), tarjeta.getAutobus(), tarjeta.getBoletosVentaManual(), tarjeta.getImporteVentaManual());
            if (result.equalsIgnoreCase("")) {
              new JdlgMessage(JRootPane.QUESTION_DIALOG, "Tarjeta Confirmada", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
              tarjeta.setEstadoTarjeta("CONFIRMADA");
              jtblTarjetas.updateUI();
            } else {
              new JdlgError("Tarjeta No Confirmada", result, "Confirmacion de Tarjetas de Viaje").setVisible(true);
            }
          }
        }
      } else {
        new JdlgAdvertencia("No se puede confirmar la tarjeta seleccionada", "La tarjeta no esta en estado Abierta ", "Confirmacion de Tarjetas de Viaje").setVisible(true);
      }
    } else {
      new JdlgMessage(JRootPane.WARNING_DIALOG, "No ha seleccionado ninguna tarjeta", "Seleccione una tarjeta para confirmar", "Confirmacion de Tarjetas de Viaje").setVisible(true);
    }
  }

  private void reimprimirTicket(){
    System.out.println("Entra a reimprimir Ticket...");
    int row = jtblTarjetas.getSelectedRow();
    if (row >= 0) {
      //int row2 = jtblTarjetas.convertRowIndexToModel(row);
        int row2 = jtblTarjetas.getSelectedRow();
      TmsTarjetasViaje tarjeta = (TmsTarjetasViaje) tableModel.getRow(row2);
      if (tarjeta.getEstadoTarjeta().equalsIgnoreCase("CONFIRMADA")) {
        if (tarjeta.getRecaudacionAutomatica().equalsIgnoreCase("S")) {
            if(JdlgVistaPagoImpresion.showTicket(tarjeta, this.USUARIO_NUMERO + " - " + this.USUARIO_NOMBRE,true))
                new JdlgMessage(JRootPane.QUESTION_DIALOG, "El Ticket se reimprimió correctamente", "", "Reimpresion de Ticket").setVisible(true);
        }
        else
            new JdlgMessage(JRootPane.WARNING_DIALOG, "La tarjeta seleccionada no Recauda Automaticamente", "", "Reimpresion de Ticket").setVisible(true);
      }
        else
            new JdlgMessage(JRootPane.WARNING_DIALOG, "La Tarjeta debe estar CONFIRMADA", "", "Reimpresion de Ticket").setVisible(true);
    }
  }

  public void buscarTarjetas() {
    int idx = jtpSearch.getSelectedIndex();
    List<TmsTarjetasViaje> tarjetas = null;
    if (idx == 0) {
      String folio = jtxtFolio.getText();
      if (folio.length() > 4) {
        TmsBaseDatos origen = (TmsBaseDatos) jcmbOrigenFolio.getSelectedItem();
        if (origen.getBdconfigId() == BigDecimal.ZERO) {
          String prefijo = folio.substring(0, 4);
          List<TmsBaseDatos> terminales = basesDeDatosFacade.findByPrefijo(prefijo);
          if (terminales.size() == 1) {
            origen_id = terminales.get(0).getTerminalId();
            tarjetas = generalfacadeRemote.findByFolio(folio, origen_id);
          } else if (terminales.isEmpty()) {
            origen_id = BigInteger.ZERO;
            new JdlgAdvertencia("No se pudo determinar la terminal origen", "Revise el folio de la tarjeta de viaje", "Confirmacion de Tarjetas de Viaje").setVisible(true);
            jtxtFolio.requestFocus();
          } else {
            origen_id = BigInteger.ZERO;
            new JdlgAdvertencia("No se pudo determinar la terminal origen", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
            jcmbOrigenFolio.requestFocus();
          }
        } else {
          origen_id = origen.getTerminalId();
          tarjetas = generalfacadeRemote.findByFolio(folio, origen.getTerminalId());
        }
      } else {
        new JdlgAdvertencia("Demaciado corto para ser un folio", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
        jtxtFolio.requestFocus();
      }
    } else {
      String clave_operador = jntxtOperador.getText();
      if (clave_operador.length() > 0) {
        TmsBaseDatos origen = (TmsBaseDatos) jcmbOrigenOperador.getSelectedItem();
        if (origen.getBdconfigId() == BigDecimal.ZERO) {
          origen_id = BigInteger.ZERO;
          new JdlgAdvertencia("No selecciono la terminal origen", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
          jcmbOrigenOperador.requestFocus();
        } else {
          origen_id = origen.getTerminalId();
          //tarjetas = tarjetasViajeFacade.findByOperador(clave_operador, origen.getTerminalId());
          tarjetas = generalfacadeRemote.findByOperador(clave_operador, origen.getTerminalId());

        }
      } else {
        jntxtOperador.requestFocus();
      }
    }
    if (tarjetas != null ) {

      tableModel.updateData(tarjetas);
      if (tarjetas.size() == 0) {
        new JdlgAdvertencia("No se encontraron tarjetas", "", "Confirmacion de Tarjetas de Viaje").setVisible(true);
        if (idx == 0) {
          jtxtFolio.requestFocus();
        } else {
          jntxtOperador.requestFocus();
        }
      } else {
        jtblTarjetas.requestFocus();
        jtblTarjetas.changeSelection(0, 0, false, false);
        jtblTarjetas.setRowSelectionInterval(0, 0);
      }
    }
  }

  private void validateSession() {
    TmsSesionFacadeRemote facade = lookupTmsSesionFacade();
    TmsSesion session = facade.find(BigDecimal.valueOf(this.SESION_ID));
    if (session == null || session.getEstadoSesion().equalsIgnoreCase("CERRADA")) {
      new JdlgError("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
      System.exit(0);
    }
  }

  private void cambiarOperador() {
    int row = jtblTarjetas.getSelectedRow();
    if (row >= 0) {
      //int row2 = jtblTarjetas.convertRowIndexToModel(row);
      int row2 = jtblTarjetas.getSelectedRow();
      TmsTarjetasViaje tarjeta = (TmsTarjetasViaje) tableModel.getRow(row2);
      if (tarjeta.getEstadoTarjeta().equalsIgnoreCase("Abierta")) {
        new JdlgCambiarOperador(tarjeta).setVisible(true);
        jtblTarjetas.updateUI();
      } else {
        new JdlgAdvertencia("No se puede editar la tarjeta seleccionada", "La tarjeta no esta en estado Abierta ", "Confirmacion de Tarjetas de Viaje").setVisible(true);
      }
    } else {
      new JdlgMessage(JRootPane.WARNING_DIALOG, "No ha seleccionado ninguna tarjeta", "Seleccione una tarjeta para confirmar", "Confirmacion de Tarjetas de Viaje").setVisible(true);
    }
  }

  private void cambiarVentaManual() {
    int row = jtblTarjetas.getSelectedRow();
    if (row >= 0) {
      //int row2 = jtblTarjetas.convertRowIndexToModel(row);
      int row2 = jtblTarjetas.getSelectedRow();
      TmsTarjetasViaje tarjeta = (TmsTarjetasViaje) tableModel.getRow(row2);
      if (tarjeta.getEstadoTarjeta().equalsIgnoreCase("Abierta")) {
        new JdlgCambiarVentaManual(tarjeta).setVisible(true);
        jtblTarjetas.updateUI();
      } else {
        new JdlgAdvertencia("No se puede editar la tarjeta seleccionada", "La tarjeta no esta en estado Abierta ", "Confirmacion de Tarjetas de Viaje").setVisible(true);
      }
    } else {
      new JdlgMessage(JRootPane.WARNING_DIALOG, "No ha seleccionado ninguna tarjeta", "Seleccione una tarjeta para confirmar", "Confirmacion de Tarjetas de Viaje").setVisible(true);
    }
  }

  private void setColumnsWidths() {
    TableColumn columinv;
    int total = 0;
    for (int i = 0; i < anchoColumnas.length; i++) {
      int w = anchoColumnas[i];
      total += w;
      columinv = this.jtblTarjetas.getColumnModel().getColumn(i);
      columinv.setMinWidth(w);
//      columinv.setMaxWidth(w);
      columinv.setPreferredWidth(w);
    }
    jtblTarjetas.setSize(total, jtblTarjetas.getSize().height);
  }



  private void buscarTarjeta(String folio) {
    if (folio.length() > 4) {
      TmsBaseDatos origen = (TmsBaseDatos) jcmbOrigenFolio.getSelectedItem();
      if (origen.getBdconfigId() == BigDecimal.ZERO) {
        String prefijo = folio.substring(0, 4);
        buscarTarjeta(folio,prefijo);
      }
    }
  }

  private void buscarTarjeta(String folio, String prefijo) {
    List<TmsBaseDatos> terminales = basesDeDatosFacade.findByPrefijo(prefijo);
    List<TmsTarjetasViaje> tarjetas = null;
    if (terminales.size() == 1) {
      origen_id = terminales.get(0).getTerminalId();
      tarjetas = generalfacadeRemote.findByFolio(folio, origen_id);
      if (tarjetas != null) {
        tableModel.updateData(tarjetas);
      }
    } else if (terminales.isEmpty()) {
      origen_id = BigInteger.ZERO;
      jtxtFolio.requestFocus();
    } else {
      origen_id = BigInteger.ZERO;
      jcmbOrigenFolio.requestFocus();
    }
  }
}
