package reportesrecaudajasper;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import tms_TextFields.JNumberTextField;
import tms_calendario.IDateEditor;
import tms_calendario.JDateChooser;

public class jdlg_ParametrosReportes_RecGral extends JDialog
{
  private ButtonGroup grupo;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JPanel jPanel1;
  private JCheckBox jchkbx_recaudan;
  private JComboBox jcmb_empresa;
  private JComboBox jcmb_servicio;
  private JLabel jlbl_barraEstado;
  private JRadioButton jrbtn_canceladas;
  private JRadioButton jrbtn_despachadas;
  
  private JRadioButton jrbtn_recuadadas;
  private JDateChooser jtxt_fechaFinal;
  private JDateChooser jtxt_fechaInicial;
  private JNumberTextField jtxt_usuario;
  private String fechaDesde = "nada";
  private String fechaHasta = "nada";
  private String recaudan = "nada";
  private String estado = "nada";
  private String servicio = "nada";
  private String empresa = "nada";
  private long usuario = 0L;
  private boolean pvisibleUsuario = false;

  public jdlg_ParametrosReportes_RecGral(Frame parent, boolean modal, boolean visbleRecuada, boolean visibleFechaFin, boolean visibleUsuario, boolean visibleRadioBotones, String des, String rec, String can, Vector pvs, Vector pve)
  {
    super(parent, modal);

    setModal(true);
    setDefaultLookAndFeelDecorated(true);
    setUndecorated(true);
    setAlwaysOnTop(true);
    initComponents();
    setDefaultCloseOperation(0);
    setResizable(false);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension DilaogSize = getSize();
    if (DilaogSize.height > screenSize.height) {
      DilaogSize.height = screenSize.height;
    }
    if (DilaogSize.width > screenSize.width) {
      DilaogSize.width = screenSize.width;
    }
    setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2); setDefaultLookAndFeelDecorated(true);
    getRootPane().setWindowDecorationStyle(3);
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESCAPE</font> Cancelar | <font color=FF0000>ENTER</font> Generar Reporte | <font color=FF0000> « »  </font> Moverse entre Campos");
    this.jtxt_fechaInicial.dateEditor.addKeyListener1(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jtxt_fechaInicialKeyPressed(evt);
      }
    });
    this.jtxt_fechaFinal.dateEditor.addKeyListener1(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jtxt_fechaFinalKeyPressed(evt);
      }
    });
    this.grupo.add(this.jrbtn_despachadas);
    this.grupo.add(this.jrbtn_recuadadas);
    if (!can.equals(""))
      this.grupo.add(this.jrbtn_canceladas);
    this.pvisibleUsuario = visibleUsuario;
    this.jtxt_fechaInicial.setVisible(!visibleUsuario);
    this.jtxt_usuario.setVisible(visibleUsuario);
    this.jchkbx_recaudan.setVisible(visbleRecuada);
    this.jtxt_fechaFinal.setVisible(visibleFechaFin);
    this.jLabel3.setVisible(visibleFechaFin);
    this.jrbtn_despachadas.setText(des);
    this.jrbtn_despachadas.setSelected(true);
    this.jrbtn_despachadas.setVisible(visibleRadioBotones);


    this.jrbtn_recuadadas.setText(rec);
    this.jrbtn_recuadadas.setVisible(visibleRadioBotones);
    this.jrbtn_canceladas.setText(can);
    this.jcmb_servicio.removeAllItems();
    this.jcmb_servicio.addItem("Todos");
    for (int i = 0; i < pvs.size(); i++)
    {
      Vector vs = (Vector)pvs.get(i);
      this.jcmb_servicio.addItem(vs.get(0));
    }

    this.jcmb_empresa.removeAllItems();
    this.jcmb_empresa.addItem("Todas");
    for (int i = 0; i < pve.size(); i++)
    {
      Vector ve = (Vector)pve.get(i);
      this.jcmb_empresa.addItem(ve.get(0));
    }

    if ((des.equals("Por Recaudación")) || (des.equals("General")))
      this.jrbtn_canceladas.setVisible(false);
    else
      this.jrbtn_canceladas.setVisible(visibleRadioBotones);
    if (!visibleFechaFin)
      this.jLabel2.setText("Fecha:");
    if (visibleUsuario)
      this.jLabel2.setText("Usuario:");
    this.jtxt_fechaInicial.dateEditor.setFoco();
    if (visibleUsuario)
      this.jtxt_usuario.requestFocus();
  }

  private void jtxt_fechaInicialKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      if (!this.jtxt_fechaFinal.isVisible())
        this.jtxt_fechaFinal.dateEditor.setTexto(this.jtxt_fechaInicial.dateEditor.getTexto());
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }

      if ((this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime()) && (this.jtxt_fechaFinal.isVisible()))
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());
      dispose();
    }

    if (evt.getKeyCode() == 39) {
      if (this.jtxt_fechaFinal.isVisible())
      {
        this.jtxt_fechaFinal.getDateEditor().setSeleccionaTexto();
        this.jtxt_fechaFinal.dateEditor.setFoco();
      }
      else {
        this.jtxt_fechaFinal.dateEditor.setTexto(this.jtxt_fechaInicial.dateEditor.getTexto());
      }
    }
    if (evt.getKeyCode() == 37) {
      if (this.jrbtn_canceladas.isVisible()) {
        this.jrbtn_canceladas.requestFocus();
      }
      else if (this.jtxt_fechaFinal.isVisible())
      {
        this.jtxt_fechaFinal.getDateEditor().setSeleccionaTexto();
        this.jtxt_fechaFinal.dateEditor.setFoco();
      }

    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");
      dispose();
    }
  }

  private void jtxt_fechaFinalKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jtxt_fechaInicial.getDateEditor().setSeleccionaTexto();
      this.jtxt_fechaInicial.dateEditor.setFoco();
    }

    if (evt.getKeyCode() == 39) {
      if (this.jchkbx_recaudan.isVisible()) {
        this.jchkbx_recaudan.requestFocus();
      }
      else {
        this.jtxt_fechaInicial.getDateEditor().setSeleccionaTexto();
        this.jtxt_fechaInicial.dateEditor.setFoco();
      }
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");
      dispose();
    }
  }

  private void initComponents()
  {
    this.grupo = new ButtonGroup();
    this.jPanel1 = new JPanel();
    this.jtxt_fechaInicial = new JDateChooser();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jtxt_fechaFinal = new JDateChooser();
    this.jchkbx_recaudan = new JCheckBox();
    this.jLabel1 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jtxt_usuario = new JNumberTextField();
    this.jrbtn_despachadas = new JRadioButton();
    this.jrbtn_recuadadas = new JRadioButton();
    this.jrbtn_canceladas = new JRadioButton();
    this.jLabel5 = new JLabel();
    this.jcmb_empresa = new JComboBox();
    this.jLabel6 = new JLabel();
    this.jcmb_servicio = new JComboBox();
    this.jlbl_barraEstado = new JLabel();

    setDefaultCloseOperation(2);
    addComponentListener(new ComponentAdapter() {
      public void componentShown(ComponentEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.formComponentShown(evt);
      }
    });
    this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Parametros del Reporte", 2, 0, new Font("Tahoma", 1, 12)));

    this.jLabel2.setFont(new Font("Tahoma", 1, 12));
    this.jLabel2.setText("Fecha desde:");

    this.jLabel3.setFont(new Font("Tahoma", 1, 12));
    this.jLabel3.setText("Fecha hasta:");

    this.jchkbx_recaudan.setFont(new Font("Tahoma", 1, 12));
    this.jchkbx_recaudan.setSelected(true);
    this.jchkbx_recaudan.setText("Empresas que recaudan");
    this.jchkbx_recaudan.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jchkbx_recaudan.setMargin(new Insets(0, 0, 0, 0));
    this.jchkbx_recaudan.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jchkbx_recaudanKeyPressed(evt);
      }
    });
    this.jLabel1.setText("   ");

    this.jLabel4.setText("  ");

    this.jtxt_usuario.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jtxt_usuarioKeyPressed(evt);
      }
      public void keyReleased(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jtxt_usuarioKeyReleased(evt);
      }
    });
    this.jrbtn_despachadas.setFont(new Font("Tahoma", 1, 12));
    this.jrbtn_despachadas.setText("Despachas");
    this.jrbtn_despachadas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jrbtn_despachadas.setMargin(new Insets(0, 0, 0, 0));
    this.jrbtn_despachadas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jrbtn_despachadasActionPerformed(evt);
      }
       });
    this.jrbtn_despachadas.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jrbtn_despachadasKeyPressed(evt);
      }
    });





    this.jrbtn_recuadadas.setFont(new Font("Tahoma", 1, 12));
    this.jrbtn_recuadadas.setText("Recaudadas");
    this.jrbtn_recuadadas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jrbtn_recuadadas.setMargin(new Insets(0, 0, 0, 0));
    this.jrbtn_recuadadas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jrbtn_recuadadasActionPerformed(evt);
      }
    });
    this.jrbtn_recuadadas.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jrbtn_recuadadasKeyPressed(evt);
      }
    });
    this.jrbtn_canceladas.setFont(new Font("Tahoma", 1, 12));
    this.jrbtn_canceladas.setText("Canceladas");
    this.jrbtn_canceladas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jrbtn_canceladas.setMargin(new Insets(0, 0, 0, 0));
    this.jrbtn_canceladas.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jrbtn_canceladasKeyPressed(evt);
      }
    });
    this.jLabel5.setFont(new Font("Tahoma", 1, 12));
    this.jLabel5.setHorizontalAlignment(4);
    this.jLabel5.setText("Empresa:");

    this.jcmb_empresa.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    this.jcmb_empresa.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jcmb_empresaKeyPressed(evt);
      }
    });
    this.jLabel6.setFont(new Font("Tahoma", 1, 12));
    this.jLabel6.setHorizontalAlignment(4);
    this.jLabel6.setText("Servicio:");

    this.jcmb_servicio.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    this.jcmb_servicio.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        jdlg_ParametrosReportes_RecGral.this.jcmb_servicioKeyPressed(evt);
      }
    });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createSequentialGroup().add(this.jLabel2, -2, 78, -2).add(116, 116, 116)).add(jPanel1Layout.createSequentialGroup().add(this.jtxt_fechaInicial, -2, 106, -2).addPreferredGap(0))).add(this.jtxt_usuario, -2, 79, -2).addPreferredGap(0).add(this.jLabel3, -1, 76, 32767).add(1, 1, 1).add(this.jtxt_fechaFinal, -2, 102, -2).add(71, 71, 71).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(216, 216, 216).add(this.jLabel4, -1, -1, 32767).addContainerGap()).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1, -1, 95, 32767).add(137, 137, 137)))).add(jPanel1Layout.createSequentialGroup().add(this.jchkbx_recaudan).addContainerGap(279, 32767)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2, false).add(1, this.jLabel6, -1, -1, 32767).add(1, this.jLabel5, -1, 69, 32767)).add(12, 12, 12).add(jPanel1Layout.createParallelGroup(1, false).add(this.jcmb_servicio, 0, -1, 32767).add(this.jcmb_empresa, 0, 254, 32767)).add(24, 24, 24)))).add(jPanel1Layout.createSequentialGroup().add(44, 44, 44).add(this.jrbtn_despachadas, -2, 145, -2).addPreferredGap(0).add(this.jrbtn_recuadadas, -2, 132, -2).addPreferredGap(0).add(this.jrbtn_canceladas, -2, 115, -2).addContainerGap(321, 32767)));
   
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createParallelGroup(1).add(this.jLabel4).add(2, this.jLabel1)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jtxt_fechaFinal, -2, -1, -2)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(2, jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel2).add(this.jtxt_fechaInicial, -1, -1, 32767)).add(29, 29, 29)).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.jtxt_usuario, -2, -1, -2))).addPreferredGap(0).add(this.jchkbx_recaudan, -1, 21, 32767))).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jrbtn_canceladas).add(this.jrbtn_despachadas).add(this.jrbtn_recuadadas, -2, 15, -2)).add(34, 34, 34).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel5).add(this.jcmb_empresa, -2, -1, -2)).add(15, 15, 15).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel6).add(this.jcmb_servicio, -2, -1, -2)).addContainerGap(14, 32767)));
   
    this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 12));
    this.jlbl_barraEstado.setText("jLabel1");
    this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jlbl_barraEstado, -1, 494, 32767).add(layout.createSequentialGroup().add(10, 10, 10).add(this.jPanel1, -2, 466, -2).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(this.jPanel1, -2, 250, -2).addPreferredGap(0, -1, 32767).add(this.jlbl_barraEstado, -2, 27, -2)));

    pack();
  }

  private void jcmb_servicioKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 39) {
      this.jcmb_servicio.requestFocus();
    }
    if (evt.getKeyCode() == 37) {
      this.jtxt_fechaInicial.dateEditor.setFoco();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  private void jcmb_empresaKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 39) {
      this.jcmb_servicio.requestFocus();
    }
    if (evt.getKeyCode() == 37) {
      this.jrbtn_canceladas.requestFocus();
    }
    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  private void jrbtn_recuadadasKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 39) {
      this.jrbtn_canceladas.requestFocus();
    }

    if (evt.getKeyCode() == 37) {
      this.jrbtn_despachadas.requestFocus();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  private void jrbtn_recuadadasActionPerformed(ActionEvent evt)
  {
  }

  private void jrbtn_canceladasKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 39) {
      this.jtxt_fechaInicial.getDateEditor().setSeleccionaTexto();
      this.jtxt_fechaInicial.dateEditor.setFoco();
    }

    if (evt.getKeyCode() == 37) {
      this.jrbtn_recuadadas.requestFocus();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  private void jrbtn_despachadasKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 39) {
      this.jrbtn_recuadadas.requestFocus();
    }

    if (evt.getKeyCode() == 37) {
      this.jchkbx_recaudan.requestFocus();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
       if (this.jrbtn_despachadas.isSelected())
        setEstado("COMFIRMADA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }



   private void jrbtn_confirmadasKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 39) {
      this.jrbtn_recuadadas.requestFocus();
    }

    if (evt.getKeyCode() == 37) {
      this.jchkbx_recaudan.requestFocus();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
       if (this.jrbtn_despachadas.isSelected())
        setEstado("COMFIRMADA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }


  private void jrbtn_despachadasActionPerformed(ActionEvent evt)
  {
  }
   private void jrbtn_confirmadasActionPerformed(ActionEvent evt)
  {
  }

  private void formComponentShown(ComponentEvent evt)
  {
    this.jtxt_fechaInicial.dateEditor.setFoco();
    if (this.pvisibleUsuario)
      this.jtxt_usuario.requestFocusInWindow();
  }

  private void jtxt_usuarioKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      if (this.jtxt_usuario.getText().equals(""))
      {
        new jdlg_error("¡Debes introducir un número de usuario!", "", "Error de datos").setVisible(true);
        return;
      }
      setFechaDesde("");
      setFechaHasta("");
      setRecaudan("S");
      setUsuario(Long.valueOf(this.jtxt_usuario.getText()).longValue());
      dispose();
    }
    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  private void jtxt_usuarioKeyReleased(KeyEvent evt)
  {
  }

  private void jchkbx_recaudanKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 39) {
      this.jrbtn_despachadas.requestFocus();
    }

    if (evt.getKeyCode() == 37) {
      this.jtxt_fechaFinal.getDateEditor().setSeleccionaTexto();
      this.jtxt_fechaFinal.dateEditor.setFoco();
    }

    if (evt.getKeyCode() == 10)
    {
      if ((this.jtxt_fechaFinal.getDateEditor().getTexto().length() < 10) || (this.jtxt_fechaInicial.getDateEditor().getTexto().length() < 10))
      {
        new jdlg_error("¡Debes introducir fechas validas!", "", "Error de formato de fecha").setVisible(true);
        return;
      }
      if (this.jtxt_fechaFinal.getDate().getTime() < this.jtxt_fechaInicial.getDate().getTime())
      {
        new jdlg_error("¡La fecha desde debe ser mayor o igual a la fecha hasta!", "", "Error de fechas").setVisible(true);
        return;
      }
      setFechaDesde(this.jtxt_fechaInicial.dateEditor.getTexto());
      setFechaHasta(this.jtxt_fechaFinal.dateEditor.getTexto());
      if (this.jchkbx_recaudan.isSelected())
        setRecaudan("S");
      else
        setRecaudan("N");
      if (this.jrbtn_despachadas.isSelected())
        setEstado("ABIERTA");
      if (this.jrbtn_recuadadas.isSelected())
        setEstado("RECAUDADA");
      if (this.jrbtn_canceladas.isSelected())
        setEstado("CANCELADA");
      setServicio(this.jcmb_servicio.getSelectedItem().toString());
      setEmpresa(this.jcmb_empresa.getSelectedItem().toString());

      dispose();
    }

    if (evt.getKeyCode() == 27)
    {
      setFechaDesde("nada");
      setFechaHasta("nada");
      setRecaudan("nada");
      setEstado("nada");
      setServicio("nada");
      setEmpresa("nada");

      dispose();
    }
  }

  public String getFechaDesde()
  {
    return this.fechaDesde;
  }

  public void setFechaDesde(String fechaDesde) {
    this.fechaDesde = fechaDesde;
  }

  public String getFechaHasta() {
    return this.fechaHasta;
  }

  public String getFechaCorte() {
    SimpleDateFormat formatfcn = new SimpleDateFormat("yyyy-MM-dd");
    return formatfcn.format(this.jtxt_fechaFinal.dateEditor.getDate());
  }

  public void setFechaHasta(String fechaHasta) {
    this.fechaHasta = fechaHasta;
  }

  public String getRecaudan() {
    return this.recaudan;
  }

  public void setRecaudan(String recaudan) {
    this.recaudan = recaudan;
  }

  public long getUsuario() {
    return this.usuario;
  }

  public void setUsuario(long usuario) {
    this.usuario = usuario;
  }

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getServicio() {
    return this.servicio;
  }

  public void setServicio(String servicio) {
    this.servicio = servicio;
  }

  public String getEmpresa() {
    return this.empresa;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }
}