package tms_plantillas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JIFPlantilla extends JInternalFrame {
    private long MENU_ID;
    private long USUARIO_ID;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID;
    private JFormattedTextField tf;
    private cssPlantilla y;
    private cssPlantilla x;
    private int iX;
    private int iZ;
    private JPanel jpnlConf = new JPanel();
    private JNumberTextField jtxtCapacidad = new JNumberTextField();
    private JPanel jpnlPlantilla = new JPanel();
    private JPanel jpnlCompo = new JPanel();
    private JPanel jpnlBotones = new JPanel();
    private JLabel jlblCapacidad = new JLabel();
    private JCuantityTextField jtxtLargoBus = new JCuantityTextField();
    private JLabel jlblLargoBus = new JLabel();
    private JLabel jlblAnchoBus = new JLabel();
    private JLabel jlblAsientosFila = new JLabel();
    private JSpinner jspnAsientosFila;
    private JLabel jlblCI = new JLabel();
    private JNumberTextField jtxtCI = new JNumberTextField();
    private JTextField jtxtCD = new JTextField();
    private JLabel jlblCD = new JLabel();
    private JCanvasPlantilla jcnvPlantilla = new JCanvasPlantilla();
    private JCuantityTextField jtxtLargoAsiento = new JCuantityTextField();
    private JLabel jlblLargoAsiento = new JLabel();
    private JCuantityTextField jtxtAnchoAsiento = new JCuantityTextField();
    private JLabel jlblAnchoAsiento = new JLabel();
    private JCheckBox jckbAlinFilas = new JCheckBox();
    private JPanel jpnlConfAdicional = new JPanel();
    private JRadioButton jrbtI = new JRadioButton();
    private JRadioButton jrbtD = new JRadioButton();
    private boolean bAlineacionFilas=false;
    private int iAlineacionUFila=0;
    private boolean bDAsientos=true;
    private JCuantityTextField jtxtAnchoBus = new JCuantityTextField();
    private JLabel jlblUFila = new JLabel();
    private JCheckBox jckbDistribucion = new JCheckBox();
    private JScrollPane jscpCompo = new JScrollPane();
    private cssConsultas query;
    private JButton jbtnAgregar = new JButton();
    private JButton jbtnEliminar = new JButton();
    private JList jlstCompo = new JList();
    private JScrollPane jscpComponentes = new JScrollPane();
    private String[] encabezado = {"Componente","Símbolo","Ubicación"};
    private String[] encabezado2 = {"Numero Asiento Original","Numero Asiento Nuevo"};
    private String[][] datos;
    private JTable jtblComponentes = new JTable();
    private DefaultTableModel xTabla, yTabla;
    private JButton jbtnAnterior = new JButton();
    private JButton jbtnSiguiente = new JButton();
    private String strPosAnt;
    private JLabel jlblAEFila = new JLabel();
    private JButton jbtnAECI = new JButton();
    private JButton jbtnAECD = new JButton();
    private JButton jbtnVista = new JButton();
    private JButton jbtnTerminar = new JButton();
    private JLabel jlblNota = new JLabel();
    private JLabel jlblNota1 = new JLabel();
    private int iEntrar=0;
    private String[] tiposNumeracion={
        "Invertir Numeración de Columna Izquierda",
        "Numeración Básica 1,2 --- 3,4",
        "Invertir Numeración de Columna Derecha",
        "Numeración Básica 3,4 --- 1,2",
        "Numeración Generica",
        "Numeración Personalizada"
    };
    private JPanel jpnlNumeros = new JPanel();
    private JScrollPane jscpNumeros = new JScrollPane();
    private JList jlstNumeros = new JList(tiposNumeracion);
    private JButton jbtnLimpiar = new JButton();
    private JPanel jpnlGuardar = new JPanel();
    private JLabel jlblNombreCorto = new JLabel();
    private JLabel jlblDesc = new JLabel();
    private JTextTextField jtxtNombreCorto = new JTextTextField();
    private JScrollPane jscpDesc = new JScrollPane();
    private JTextArea jtxaDesc = new JTextArea();
    private JButton jbtnAplicar = new JButton();
    private JScrollPane jscpNumPersonal = new JScrollPane();
    private JTable jtblNumPersonal = new JTable();
    private JButton jbtnAceptar = new JButton();
    private JLabel jlblCapacidad1 = new JLabel();
    private JLabel jlbFCI = new JLabel();
    private JLabel jlbFCD = new JLabel();
    private int iTipoNumeracion;

    public JIFPlantilla(Vector datosIniciales) {
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        try {
            jbInit();
            centrarJIF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize( new Dimension(800, 600) );
        this.setTitle("Diseño de Plantilla: Paso 1 de 4 - Creación de Plantilla");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setClosable(true);
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        jpnlConf.setLayout(null);
        jpnlConf.setBorder(BorderFactory.createTitledBorder("Configuración"));
        jpnlConf.setFont(new Font("Dialog", 1, 12));
        jpnlConf.setBounds(new Rectangle(10, 5, 470, 470));
        jpnlConf.setVisible(true);
        jtxtCapacidad.setBounds(new Rectangle(170, 70, 45, 22));
        jtxtCapacidad.setText("49");
        jtxtCapacidad.setFont(new Font("Dialog", 1, 11));
        jtxtCapacidad.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jtxtCapacidad_keyReleased(e);
                    }
                });
        jtxtCapacidad.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtCapacidad_focusLost(e);
                    }
                });
        jpnlPlantilla.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jpnlPlantilla.setFont(new Font("Dialog", 1, 12));
        jpnlPlantilla.setLayout(null);
        jpnlPlantilla.setBounds(new Rectangle(490, 10, 295, 550));
        jpnlCompo.setLayout(null);
        jpnlCompo.setBorder(BorderFactory.createTitledBorder("Componentes"));
        jpnlCompo.setFont(new Font("Dialog", 1, 12));
        jpnlCompo.setBounds(new Rectangle(10, 5, 470, 470));
        jpnlCompo.setVisible(false);
        jpnlBotones.setLayout(null);
        jpnlBotones.setBounds(new Rectangle(10, 490, 470, 70));
        jpnlBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jlblCapacidad.setText("Capacidad (Pasajeros):");
        jlblCapacidad.setBounds(new Rectangle(30, 70, 130, 22));
        jlblCapacidad.setFont(new Font("Dialog", 1, 11));
        jlblCapacidad.setLabelFor(jtxtCapacidad);
        jlblCapacidad.setHorizontalAlignment(SwingConstants.RIGHT);
        jtxtLargoBus.setBounds(new Rectangle(170, 110, 45, 22));
        jtxtLargoBus.setText("14");
        jtxtLargoBus.setFont(new Font("Dialog", 1, 11));
        jtxtLargoBus.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtLargoBus_focusLost(e);
                    }
                });
        jtxtLargoBus.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jtxtLargoBus_keyReleased(e);
                    }
                });
        jlblLargoBus.setText("Autobús (Largo):");
        jlblLargoBus.setBounds(new Rectangle(65, 110, 95, 22));
        jlblLargoBus.setSize(new Dimension(95, 22));
        jlblLargoBus.setFont(new Font("Dialog", 1, 11));
        jlblLargoBus.setLabelFor(jtxtLargoBus);
        jlblLargoBus.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblAnchoBus.setText("Autobús (Ancho):");
        jlblAnchoBus.setBounds(new Rectangle(250, 110, 100, 20));
        jlblAnchoBus.setFont(new Font("Dialog", 1, 11));
        jlblAnchoBus.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblAsientosFila.setText("Asientos Por Fila:");
        jlblAsientosFila.setBounds(new Rectangle(60, 190, 100, 20));
        jlblAsientosFila.setFont(new Font("Dialog", 1, 11));
        jlblAsientosFila.setLabelFor(jspnAsientosFila);
        jlblAsientosFila.setHorizontalAlignment(SwingConstants.RIGHT);
        iX=jpnlPlantilla.getWidth()-24; iZ=jpnlPlantilla.getHeight()-32;
        y = new cssPlantilla();
        SpinnerModel spnModel = new SpinnerNumberModel(4, 2, y.imaxASIENTOSFILA, 1);
        jspnAsientosFila = new JSpinner(spnModel);
        tf =((JSpinner.DefaultEditor)jspnAsientosFila.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        jspnAsientosFila.setBounds(new Rectangle(170, 190, 45, 22));
        jspnAsientosFila.setFont(new Font("Dialog", 1, 11));
        jspnAsientosFila.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        jspnAsientosFila_stateChanged(e);
                    }
                });
        jlblCI.setText("Columna Izquierda:");
        jlblCI.setBounds(new Rectangle(50, 230, 110, 20));
        jlblCI.setFont(new Font("Dialog", 1, 11));
        jlblCI.setLabelFor(jtxtCI);
        jlblCI.setHorizontalAlignment(SwingConstants.RIGHT);
        jcnvPlantilla.setBounds(new Rectangle(12, 18, iX, iZ));
        jcnvPlantilla.setSize(new Dimension(iX, iZ));
        jcnvPlantilla.setBackground(Color.white);
        jtxtLargoAsiento.setBounds(new Rectangle(170, 150, 45, 22));
        jtxtLargoAsiento.setText("0.81");
        jtxtLargoAsiento.setFont(new Font("Dialog", 1, 11));
        jtxtLargoAsiento.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jtxtLargoAsiento_keyReleased(e);
                    }
                });
        jtxtLargoAsiento.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtLargoAsiento_focusLost(e);
                    }
                });
        jlblLargoAsiento.setText("Asiento (Largo):");
        jlblLargoAsiento.setBounds(new Rectangle(65, 150, 95, 22));
        jlblLargoAsiento.setSize(new Dimension(95, 22));
        jlblLargoAsiento.setFont(new Font("Dialog", 1, 11));
        jlblLargoAsiento.setLabelFor(jtxtLargoAsiento);
        jlblLargoAsiento.setHorizontalAlignment(SwingConstants.RIGHT);
        jtxtAnchoAsiento.setBounds(new Rectangle(360, 150, 45, 22));
        jtxtAnchoAsiento.setText("0.51");
        jtxtAnchoAsiento.setFont(new Font("Dialog", 1, 11));
        jtxtAnchoAsiento.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jtxtAnchoAsiento_keyReleased(e);
                    }
                });
        jtxtAnchoAsiento.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtAnchoAsiento_focusLost(e);
                    }
                });
        jlblAnchoAsiento.setText("Asiento (Ancho):");
        jlblAnchoAsiento.setBounds(new Rectangle(255, 150, 95, 22));
        jlblAnchoAsiento.setSize(new Dimension(95, 22));
        jlblAnchoAsiento.setFont(new Font("Dialog", 1, 11));
        jlblAnchoAsiento.setLabelFor(jtxtAnchoAsiento);
        jlblAnchoAsiento.setHorizontalAlignment(SwingConstants.RIGHT);
        jckbAlinFilas.setText("Alinear Filas de Ambas Columnas");
        jckbAlinFilas.setBounds(new Rectangle(30, 60, 215, 20));
        jckbAlinFilas.setFont(new Font("Dialog", 1, 11));
        jckbAlinFilas.setMnemonic('A');
        jckbAlinFilas.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        jckbAlinFilas_itemStateChanged(e);
                    }
                });
        jpnlConfAdicional.setBounds(new Rectangle(15, 275, 440, 180));
        jpnlConfAdicional.setBorder(BorderFactory.createTitledBorder("Adicional"));
        jpnlConfAdicional.setLayout(null);
        jpnlConfAdicional.setVisible(false);
        jrbtI.setText("Izquierda");
        jrbtI.setBounds(new Rectangle(175, 90, 84, 19));
        jrbtI.setFont(new Font("Dialog", 1, 11));
        jrbtI.setMnemonic('z');
        jrbtI.setSelected(true);
        jrbtI.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jrbtI_actionPerformed(e);
                    }
                });
        jrbtD.setText("Derecha");
        jrbtD.setBounds(new Rectangle(295, 90, 85, 20));
        jrbtD.setFont(new Font("Dialog", 1, 11));
        jrbtD.setMnemonic('D');
        jrbtD.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jrbtD_actionPerformed(e);
                    }
                });
        jtxtAnchoBus.setBounds(new Rectangle(360, 110, 45, 20));
        jtxtAnchoBus.setText("2.60");
        jtxtAnchoBus.setFont(new Font("Dialog", 1, 11));
        jtxtAnchoBus.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jtxtAnchoBus_keyReleased(e);
                    }
                });
        jtxtAnchoBus.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtAnchoBus_focusLost(e);
                    }
                });
        jlblUFila.setText("Alineación Ultima Fila:");
        jlblUFila.setBounds(new Rectangle(30, 90, 125, 15));
        jlblUFila.setFont(new Font("Dialog", 1, 11));
        jckbDistribucion.setText("Distribucion de Asientos");
        jckbDistribucion.setBounds(new Rectangle(30, 25, 215, 20));
        jckbDistribucion.setFont(new Font("Dialog", 1, 11));
        jckbDistribucion.setMnemonic('i');
        jckbDistribucion.setSelected(true);
        jckbDistribucion.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        jckbDistribucion_itemStateChanged(e);
                    }
                });
        jscpCompo.setBounds(new Rectangle(15, 25, 210, 200));
        ButtonGroup jrbtGrupo = new ButtonGroup();
        jrbtGrupo.add(jrbtI);
        jrbtGrupo.add(jrbtD);
        jpnlPlantilla.add(jcnvPlantilla, null);
        jpnlBotones.add(jbtnLimpiar, null);
        jpnlBotones.add(jbtnTerminar, null);
        jpnlBotones.add(jbtnSiguiente, null);
        jpnlBotones.add(jbtnAnterior, null);
        jbtnAgregar.setText("Insertar");
        jbtnAgregar.setBounds(new Rectangle(300, 75, 155, 22));
        jbtnAgregar.setFont(new Font("Dialog", 1, 11));
        jbtnAgregar.setMnemonic('I');
        jbtnAgregar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAgregar_actionPerformed(e);
                    }
                });
        jbtnEliminar.setText("Borrar Componentes");
        jbtnEliminar.setBounds(new Rectangle(300, 145, 155, 22));
        jbtnEliminar.setFont(new Font("Dialog", 1, 11));
        jbtnEliminar.setMnemonic('B');
        jbtnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnEliminar_actionPerformed(e);
                    }
                });
        query = new cssConsultas();
        datos=query.obtenerComponentes();
        String[] strLista=new String[datos.length];
        for(int i=0; i<datos.length; i++)
            strLista[i]=datos[i][1]+" ("+datos[i][0]+")";
        jlstCompo.setListData(strLista);
        jlstCompo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlstCompo.setVisibleRowCount(-1);
        jlstCompo.setFont(new Font("Dialog", 1, 11));
        jscpComponentes.setBounds(new Rectangle(15, 250, 440, 200));
        jtblComponentes.setFont(new Font("Dialog", 1, 11));
        xTabla = new DefaultTableModel(null,encabezado){
            public boolean isCellEditable(int row, int col){ return false; }};
        jtblComponentes.setModel(xTabla);
        jbtnAnterior.setBounds(new Rectangle(130, 25, 100, 22));
        jbtnAnterior.setFont(new Font("Dialog", 1, 11));
        jbtnAnterior.setText("Anterior");
        jbtnAnterior.setMnemonic('A');
        jbtnAnterior.setEnabled(false);
        jbtnAnterior.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAnterior_actionPerformed(e);
                    }
                });
        jbtnSiguiente.setText("Siguiente");
        jbtnSiguiente.setBounds(new Rectangle(240, 25, 100, 22));
        jbtnSiguiente.setFont(new Font("Dialog", 1, 11));
        jbtnSiguiente.setMnemonic('S');
        jbtnSiguiente.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnSiguiente_actionPerformed(e);
                    }
                });
        jtblComponentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblComponentes.getTableHeader().setReorderingAllowed(false);
        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.setBounds(new Rectangle(340, 165, 115, 22));
        jbtnAceptar.setMnemonic('e');
        jbtnAceptar.setFont(new Font("Dialog", 1, 11));
        jbtnAceptar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAceptar_actionPerformed(e);
                    }
                });
        jscpNumPersonal.setBounds(new Rectangle(15, 165, 310, 290));        
        jbtnAplicar.setText("Aplicar");
        jbtnAplicar.setBounds(new Rectangle(340, 25, 115, 22));
        jbtnAplicar.setMnemonic('p');
        jbtnAplicar.setFont(new Font("Dialog", 1, 11));
        jbtnAplicar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAplicar_actionPerformed(e);
                    }
                });
        jscpDesc.setBounds(new Rectangle(110, 60, 330, 176));
        jtxtNombreCorto.setBounds(new Rectangle(110, 22, 115, 22));
        jlblDesc.setText("Descripción:");
        jlblDesc.setBounds(new Rectangle(10, 55, 90, 15));
        jlblDesc.setFont(new Font("Dialog", 1, 11));
        jlblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblNombreCorto.setText("Nombre:");
        jlblNombreCorto.setBounds(new Rectangle(15, 25, 85, 15));
        jlblNombreCorto.setFont(new Font("Dialog", 1, 11));
        jlblNombreCorto.setHorizontalAlignment(SwingConstants.RIGHT);
        jpnlGuardar.setBounds(new Rectangle(10, 5, 470, 470));
        jpnlGuardar.setLayout(null);
        jpnlGuardar.setBorder(BorderFactory.createTitledBorder("Plantilla"));
        jpnlGuardar.setVisible(false);
        jbtnLimpiar.setText("Limpiar");
        jbtnLimpiar.setBounds(new Rectangle(10, 25, 100, 22));
        jbtnLimpiar.setFont(new Font("Dialog", 1, 11));
        jbtnLimpiar.setMnemonic('L');
        jbtnLimpiar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnLimpiar_actionPerformed(e);
                    }
                });
        jscpNumeros.setBounds(new Rectangle(15, 25, 310, 125));
        jpnlNumeros.setBounds(new Rectangle(10, 5, 470, 470));
        jpnlNumeros.setLayout(null);
        jpnlNumeros.setBorder(BorderFactory.createTitledBorder("Numeración de Asientos"));
        jpnlNumeros.setVisible(false);
        jlblNota1.setText("se expresan en metros.");
        jlblNota1.setBounds(new Rectangle(25, 35, 150, 15));
        jlblNota1.setFont(new Font("Dialog", 1, 11));
        jlblNota.setText("Las dimensiones del Autobus y de los Asientos");
        jlblNota.setBounds(new Rectangle(25, 20, 280, 15));
        jlblNota.setFont(new Font("Dialog", 1, 11));
        jbtnTerminar.setText("Terminar");
        jbtnTerminar.setBounds(new Rectangle(360, 25, 100, 22));
        jbtnTerminar.setFont(new Font("Dialog", 1, 11));
        jbtnTerminar.setMnemonic('T');
        jbtnTerminar.setEnabled(false);
        jbtnTerminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnTerminar_actionPerformed(e);
                    }
                });
        jbtnVista.setText("Vista Previa");
        jbtnVista.setMnemonic('V');
        jbtnVista.setFont(new Font("Dialog", 1, 11));
        jbtnVista.setBounds(new Rectangle(290, 70, 115, 22));
        jbtnVista.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnVista_actionPerformed(e);
                    }
                });
        jbtnAECD.setText("Col. Derecha");
        jbtnAECD.setBounds(new Rectangle(295, 120, 110, 22));
        jbtnAECD.setFont(new Font("Dialog", 1, 11));
        jbtnAECD.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAECD_actionPerformed(e);
                    }
                });
        jbtnAECI.setText("Col. Izquierda");
        jbtnAECI.setBounds(new Rectangle(175, 120, 110, 22));
        jbtnAECI.setFont(new Font("Dialog", 1, 11));
        jbtnAECI.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtnAECI_actionPerformed(e);
                    }
                });
        jlblAEFila.setText("Agregar Fila a:");
        jlblAEFila.setBounds(new Rectangle(70, 120, 90, 15));
        jlblAEFila.setFont(new Font("Dialog", 1, 11));
        TableColumn columna;
        int anchoContenedor = jscpComponentes.getWidth();
        for(int i=0; i<jtblComponentes.getColumnCount(); i++){
            columna = jtblComponentes.getColumnModel().getColumn(i);
            switch(i){
                case 0: columna.setPreferredWidth((int)(anchoContenedor*0.5)); break;
                case 1: columna.setPreferredWidth((int)(anchoContenedor*0.25)); break;
                case 2: columna.setPreferredWidth((int)(anchoContenedor*0.25)); break;
            }
            columna.setResizable(false);
        }
        jscpComponentes.getViewport().add(jtblComponentes, null);
        jpnlCompo.add(jscpComponentes, null);
        jpnlCompo.add(jbtnEliminar, null);
        jpnlCompo.add(jbtnAgregar, null);
        jscpCompo.getViewport().add(jlstCompo, null);
        jpnlCompo.add(jscpCompo, null);
        jpnlConfAdicional.add(jlbFCD, null);
        jpnlConfAdicional.add(jlbFCI, null);
        jpnlConfAdicional.add(jbtnAECD, null);
        jpnlConfAdicional.add(jbtnAECI, null);
        jpnlConfAdicional.add(jlblAEFila, null);
        jpnlConfAdicional.add(jckbDistribucion, null);
        jpnlConfAdicional.add(jlblUFila, null);
        jpnlConfAdicional.add(jrbtD, null);
        jpnlConfAdicional.add(jrbtI, null);
        jpnlConfAdicional.add(jckbAlinFilas, null);
        jpnlConf.add(jlblNota1, null);
        jpnlConf.add(jlblNota, null);
        jpnlConf.add(jbtnVista, null);
        jpnlConf.add(jtxtAnchoBus, null);
        jpnlConf.add(jpnlConfAdicional, null);
        jpnlConf.add(jlblAnchoAsiento, null);
        jpnlConf.add(jtxtAnchoAsiento, null);
        jpnlConf.add(jlblLargoAsiento, null);
        jpnlConf.add(jtxtLargoAsiento, null);
        jpnlConf.add(jlblCD, null);
        jpnlConf.add(jtxtCD, null);
        jpnlConf.add(jtxtCI, null);
        jpnlConf.add(jlblCI, null);
        jpnlConf.add(jspnAsientosFila, null);
        jpnlConf.add(jlblAsientosFila, null);
        jpnlConf.add(jlblAnchoBus, null);
        jpnlConf.add(jlblLargoBus, null);
        jpnlConf.add(jtxtLargoBus, null);
        jpnlConf.add(jlblCapacidad, null);
        jpnlConf.add(jtxtCapacidad, null);
        jtxtCI.setBounds(new Rectangle(170, 230, 45, 22));
        jtxtCI.setFont(new Font("Dialog", 1, 11));
        jtxtCI.setText("2");
        jtxtCI.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jtxtCI_actionPerformed(e);
                    }
                });
        jtxtCI.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        jtxtCI_focusLost(e);
                    }
                });
        jtxtCD.setBounds(new Rectangle(360, 230, 45, 22));
        jtxtCD.setFont(new Font("Dialog", 1, 11));
        jtxtCD.setText("2");
        jtxtCD.setEnabled(false);
        jlblCD.setText("Columna Derecha:");
        jlblCD.setBounds(new Rectangle(245, 230, 105, 20));
        jlblCD.setFont(new Font("Dialog", 1, 11));
        jlblCD.setLabelFor(jtxtCD);
        jlblCD.setHorizontalAlignment(SwingConstants.RIGHT);
        jlstNumeros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jscpNumeros.getViewport().add(jlstNumeros, null);
        yTabla = new DefaultTableModel(null,encabezado2){
            public boolean isCellEditable(int row, int col){ if(col==1) return true;
            return false; }};
        jtblNumPersonal.setModel(yTabla);
        jtblNumPersonal.setRowSelectionAllowed(false);
        jtblNumPersonal.getTableHeader().setReorderingAllowed(false);
        jlblCapacidad1.setText("Capacidad:");
        jlblCapacidad1.setBounds(new Rectangle(65, 70, 95, 22));
        jlblCapacidad1.setSize(new Dimension(95, 22));
        jlblCapacidad1.setFont(new Font("Dialog", 1, 11));
        jlblCapacidad1.setLabelFor(jtxtCapacidad);
        jlblCapacidad1.setHorizontalAlignment(SwingConstants.RIGHT);
        jlbFCI.setText("100 Filas");
        jlbFCI.setBounds(new Rectangle(190, 150, 85, 15));
        jlbFCI.setFont(new Font("Dialog", 1, 11));
        jlbFCI.setHorizontalAlignment(SwingConstants.CENTER);
        jlbFCD.setText("100 Filas");
        jlbFCD.setBounds(new Rectangle(305, 150, 85, 15));
        jlbFCD.setFont(new Font("Dialog", 1, 11));
        jlbFCD.setHorizontalAlignment(SwingConstants.CENTER);
        anchoContenedor = jscpNumPersonal.getWidth();
        for(int i=0; i<jtblNumPersonal.getColumnCount(); i++){
            columna = jtblNumPersonal.getColumnModel().getColumn(i);
            switch(i){
                case 0: columna.setPreferredWidth((int)(anchoContenedor*0.5)); break;
                case 1: columna.setPreferredWidth((int)(anchoContenedor*0.5)); break;
            }
            columna.setResizable(false);
        }
        jscpNumPersonal.getViewport().add(jtblNumPersonal, null);
        jpnlNumeros.add(jbtnAceptar, null);
        jpnlNumeros.add(jscpNumPersonal, null);
        jscpNumPersonal.setVisible(false);
        jtblNumPersonal.setFont(new Font("Dialog", 1, 11));
        jbtnAceptar.setVisible(false);
        jpnlNumeros.add(jbtnAplicar, null);
        jpnlNumeros.add(jscpNumeros, null);
        jscpDesc.getViewport().add(jtxaDesc, null);
        jpnlGuardar.add(jscpDesc, null);
        jpnlGuardar.add(jtxtNombreCorto, null);
        jpnlGuardar.add(jlblDesc, null);
        jpnlGuardar.add(jlblNombreCorto, null);
        this.getContentPane().add(jpnlGuardar, null);
        this.getContentPane().add(jpnlNumeros, null);
        this.getContentPane().add(jpnlBotones, null);
        this.getContentPane().add(jpnlCompo, null);
        this.getContentPane().add(jpnlPlantilla, null);
        this.getContentPane().add(jpnlConf, null);
    }
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {
    // TODO add your handling code here:
        this.jtxtCapacidad.requestFocusInWindow();
    }

    private void jspnAsientosFila_stateChanged(ChangeEvent e) {
        int iVal0 = Integer.valueOf(jspnAsientosFila.getValue().toString());
        int iVal1 = (iVal0/2)+(iVal0%2);
        jtxtCI.setText(String.valueOf(iVal1));
        jtxtCD.setText(String.valueOf(iVal0-iVal1));
    }

    private void jtxtCI_actionPerformed(ActionEvent e) {
        int iVal0 = Integer.valueOf(jtxtCI.getText());
        int iVal1 = Integer.valueOf(jspnAsientosFila.getValue().toString());
        if(iVal0<iVal1 && iVal0>=1) jtxtCD.setText(String.valueOf(iVal1-iVal0));
        else{
            iVal0 = Integer.valueOf(jtxtCD.getText());
            jtxtCI.setText(String.valueOf(iVal1-iVal0));
        }
    }

    private void jbtnVista_actionPerformed(ActionEvent e) {
        if(x!=null){
            if(!x.vCompoLocal.isEmpty() || !x.vCompoAereo.isEmpty()) xTabla.setDataVector(null,encabezado);
            if(!x.vCompoLocal.isEmpty()) x.vCompoLocal.clear();
            if(!x.vCompoAereo.isEmpty()) x.vCompoAereo.clear();
        }
        int i0,i1,i2,i3;
        float i4,i5, i6, i7;
        i4=Float.valueOf(jtxtLargoBus.getText());
        i5=Float.valueOf(jtxtAnchoBus.getText());
        i6=Float.valueOf(jtxtLargoAsiento.getText());
        i7=Float.valueOf(jtxtAnchoAsiento.getText());
        i0=Integer.valueOf(jtxtCapacidad.getText());
        i1=Integer.valueOf(jspnAsientosFila.getValue().toString());
        i2=Integer.valueOf(jtxtCI.getText());
        i3=Integer.valueOf(jtxtCD.getText());
        x = new cssPlantilla(iX,iZ);
        x.setPasajeros(i0);
        x.setAsientosFila(i1);
        x.setColIzquierda(i2);
        x.setColDerecha(i3);
        x.setBusZ(i4);
        x.setBusX(i5);
        x.setAsientoZ(i6);
        x.setAsientoX(i7);
        x.setAlineacionZ(iAlineacionUFila);
        x.setDistribucion(bDAsientos);
        
        if(!x.coordenadasDemo()){
            JOptionPane.showMessageDialog(this,x.getError(),"¡Error al crear la plantilla!",JOptionPane.INFORMATION_MESSAGE);
            jtxtLargoBus.setText(String.valueOf(i4));
            jtxtAnchoBus.setText(String.valueOf(i5));
            jtxtLargoAsiento.setText(String.valueOf(i6));
            jtxtAnchoAsiento.setText(String.valueOf(i7));
            jtxtCapacidad.setText(String.valueOf(i0));
            jspnAsientosFila.setValue(String.valueOf(i1));
            jtxtCI.setText(String.valueOf(i2));
            jtxtCD.setText(String.valueOf(i3));
        }
        else{
        //REPINTADO DE PLANTILLA
            jcnvPlantilla.setDatos(x.mAI,x.mAD,x.mAR, x.getAsientoXR(),
                        x.getAsientoZR(),x.getColIzquierda(),x.getColDerecha());
            jcnvPlantilla.repaint();
            if(!jpnlConfAdicional.isVisible())
                jpnlConfAdicional.setVisible(true);
            jlbFCI.setText(x.iFI+" Filas");
            jlbFCD.setText(x.iFD+" Filas");
        }
    }
    
    private void centrarJIF(){
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
    
    private void jtxtCI_focusLost(FocusEvent e) {
        int iVal0 = Integer.valueOf(jtxtCI.getText());
        int iVal1 = Integer.valueOf(jspnAsientosFila.getValue().toString());
        if(iVal0<iVal1 && iVal0>=1) jtxtCD.setText(String.valueOf(iVal1-iVal0));
        else{
            iVal0 = Integer.valueOf(jtxtCD.getText());
            jtxtCI.setText(String.valueOf(iVal1-iVal0));
        }
    }

    private void jckbAlinFilas_itemStateChanged(ItemEvent e) {
      try{
        boolean bAlinear;
        if(e.getStateChange()==ItemEvent.SELECTED){ bAlineacionFilas=true;
            bAlinear=x.AlinearColumnas();
        }
        else{ bAlineacionFilas=false;
            if(iEntrar==2) return;
            bAlinear=x.cargarRespaldo();
        }
        if(!bAlinear){
            iEntrar=2; jckbAlinFilas.setSelected(false); iEntrar=0;
            JOptionPane.showMessageDialog(this,x.getError(),"Imposible alinear filas de asientos.",JOptionPane.INFORMATION_MESSAGE);            
            return;
        }
        jcnvPlantilla.setDatos(x.mAI,x.mAD,x.mAR,
                               x.getAsientoXR(),x.getAsientoZR(),x.getColIzquierda(),x.getColDerecha());
        jcnvPlantilla.repaint();
      }catch(NullPointerException npe){
          return;
      }
    }

    private void jrbtI_actionPerformed(ActionEvent e) { iAlineacionUFila=0; if(x!=null) jbtnVista.doClick(); }

    private void jrbtD_actionPerformed(ActionEvent e) { iAlineacionUFila=2; if(x!=null) jbtnVista.doClick(); }

    private void jckbDistribucion_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED) bDAsientos=true;
        else bDAsientos=false;
        if(x!=null) jbtnVista.doClick();
    }

    private void jbtnAgregar_actionPerformed(ActionEvent e) {
        try{
          int iCompo = jlstCompo.getSelectedIndex();
          if(iCompo>-1){
              int iTipo=Integer.valueOf(datos[iCompo][2].toString()), j=0;
              Object[] ubicaciones = new Object[x.getPasajeros()+2];
              String strMsg="Arriba de Asiento: (Ubicación Central <<0>>)";
              if(iTipo==0){
                  strMsg="Después del Asiento:";
                  if(x.getAsientosRestantes()==1 && bDAsientos) j=x.getAsientosFila()+1;
                  if(x.getAsientosRestantes()==1 && !bDAsientos) j=1;
                  if(x.getAsientosRestantes()!=1 &&
                    (x.getAsientosRestantes()!=x.getColIzquierda() && x.getAsientosRestantes()!=x.getColDerecha()))
                        j=x.getAsientosRestantes();
                  ubicaciones = new Object[x.getPasajeros()-j]; j=1;
              }
              for(int i=0; i<ubicaciones.length; i++)
                  ubicaciones[i]=String.valueOf(i+j);
              ubicaciones[ubicaciones.length-1]="99";
              String ubicacion=(String)JOptionPane.showInputDialog(this,strMsg,"Ubicar Componente",JOptionPane.QUESTION_MESSAGE,null,ubicaciones,ubicaciones[0]);
              if(ubicacion!=null){
                int iAsiento=Integer.valueOf(ubicacion);
                if(x.insertarComponente(iTipo, datos[iCompo][0], iAsiento)){
                    String[] xDatos = new String[3];
                    xDatos[0]=datos[iCompo][1]; xDatos[1]=datos[iCompo][0]; xDatos[2]=ubicacion;
                    xTabla.addRow(xDatos);
                    if(iTipo==1) jcnvPlantilla.setCompoAero(x.vCompoAereo,xDatos[1]);
                    else jcnvPlantilla.setCompoLocal(x.vCompoLocal);
                    jcnvPlantilla.repaint();
                }else JOptionPane.showMessageDialog(this,x.getError(),"Imposible agregar: "+datos[iCompo][1],JOptionPane.INFORMATION_MESSAGE);
              }
              else JOptionPane.showMessageDialog(this,"No agrego ningun componente","Accion Cancelada",JOptionPane.INFORMATION_MESSAGE);
          }
          else JOptionPane.showMessageDialog(this,"No ha Seleccionado ningún componente","Imposible agregar componente",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(NullPointerException npe){
          JOptionPane.showMessageDialog(this,"No ha creado una plantilla de Asientos","Accion no Válida",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnEliminar_actionPerformed(ActionEvent e) {
        if(!x.vCompoLocal.isEmpty() || !x.vCompoAereo.isEmpty()){
            if(JOptionPane.showConfirmDialog(this,"¿Realmente Desea Eliminar los Componentes Insertados?","¡Aviso Importante!",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                xTabla.setDataVector(null,encabezado);
                x.vCompoLocal.clear(); x.vCompoAereo.clear();
                jbtnVista.doClick();
            }
        }
        else JOptionPane.showMessageDialog(this,"No ha Insertado Componentes","¡Imposible Borrar!",JOptionPane.INFORMATION_MESSAGE);
    }

    private void jbtnAECI_actionPerformed(ActionEvent e) {
        if(x==null){
            JOptionPane.showMessageDialog(this,"No ha creado una plantilla de Asientos","Accion no Válida",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!x.agregarFilaDeAsientoI()){ JOptionPane.showMessageDialog(this,x.getError(),"Operación no Permitida",JOptionPane.INFORMATION_MESSAGE); }
        else{
            jlbFCI.setText(x.iFI+" Filas");
            jlbFCD.setText(x.iFD+" Filas");
            jcnvPlantilla.setDatos(x.mAI,x.mAD,x.mAR,
                                   x.getAsientoXR(),x.getAsientoZR(),x.getColIzquierda(),x.getColDerecha());
            jcnvPlantilla.repaint();
        }
    }

    private void jbtnAECD_actionPerformed(ActionEvent e) {
        if(x==null){
            JOptionPane.showMessageDialog(this,"No ha creado una plantilla de Asientos","Accion no Válida",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!x.agregarFilaDeAsientoD()){ JOptionPane.showMessageDialog(this,x.getError(),"Operación no Permitida",JOptionPane.INFORMATION_MESSAGE); }
        else{
            jlbFCI.setText(x.iFI+" Filas");
            jlbFCD.setText(x.iFD+" Filas");
            jcnvPlantilla.setDatos(x.mAI,x.mAD,x.mAR,
                                   x.getAsientoXR(),x.getAsientoZR(),x.getColIzquierda(),x.getColDerecha());
            jcnvPlantilla.repaint();
        }
    }

    private void jbtnSiguiente_actionPerformed(ActionEvent e) {
        if(jpnlConf.isVisible()){
            if(x==null){
                JOptionPane.showMessageDialog(this,"No ha creado una plantilla de Asientos","Accion no Válida",JOptionPane.ERROR_MESSAGE);
                return;
            }
            jpnlConf.setVisible(false);
            jbtnLimpiar.setVisible(false);
            jpnlCompo.setVisible(true);
            this.setTitle("Diseño de Plantilla: Paso 2 de 4 - Añadir Componentes");
            jbtnAnterior.setEnabled(true);
        }
        else{
            if(jpnlCompo.isVisible()){
                jpnlCompo.setVisible(false);
                jpnlNumeros.setVisible(true);
                this.setTitle("Diseño de Plantilla: Paso 3 de 4 - Numeración de Asientos");
            }
            else{
                if(jpnlNumeros.isVisible()){
                    jpnlNumeros.setVisible(false);
                    jpnlGuardar.setVisible(true);
                    this.setTitle("Diseño de Plantilla: Paso 4 de 4 - Guardar Plantilla");
                    jbtnSiguiente.setEnabled(false);
                    jbtnTerminar.setEnabled(true);
                }
            }
        }
    }

    private void jbtnAnterior_actionPerformed(ActionEvent e) {
        if(jpnlGuardar.isVisible()){
            jpnlGuardar.setVisible(false);
            jpnlNumeros.setVisible(true);
            this.setTitle("Diseño de Plantilla: Paso 3 de 4 - Numeración de Asientos");
            jbtnTerminar.setEnabled(false);
            jbtnSiguiente.setEnabled(true);
        }
        else{
            if(jpnlNumeros.isVisible()){
                jpnlNumeros.setVisible(false);
                jpnlCompo.setVisible(true);
                this.setTitle("Diseño de Plantilla: Paso 2 de 4 - Añadir Componentes");
            }
            else{
                if(jpnlCompo.isVisible()){
                    if(JOptionPane.showConfirmDialog(this,"Si Modifica la Plantilla \n"+
                    "se Eliminarán los Componentes que ha Insertado",
                    "¡Aviso Importante!",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                        jpnlCompo.setVisible(false);
                        jpnlConf.setVisible(true);
                        jbtnLimpiar.setVisible(true);
                        this.setTitle("Diseño de Plantilla: Paso 1 de 4 - Creación de Plantilla");
                        jbtnAnterior.setEnabled(false);
                        jbtnLimpiar.doClick();
                        jtxtCapacidad.requestFocusInWindow();
                    }
                }
            }
        }
    }

    private void jbtnTerminar_actionPerformed(ActionEvent e) {
        if(jtxtNombreCorto.getText().equals("") || jtxaDesc.getText().equals("")){
            JOptionPane.showMessageDialog(this,
            "Para Terminar es Necesario Ingresar"+"\n"+"un Nombre y Descripción de Plantilla",
            "¡Aviso Importante!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        x.crearVectorFinal(datos);
        long tipoNum=this.iTipoNumeracion;
        int iAlineacionFilas=0;
        if(bAlineacionFilas) iAlineacionFilas=1;
        int iDAsientos=0;
        if(bDAsientos) iDAsientos=1;        
        x.insertarPlantilla(iAlineacionUFila,iAlineacionFilas,x.getAsientoXR(),
                x.getAsientoX(),x.getBusX(),x.getBusCanvasX(),x.getColDerecha(),
                x.getColIzquierda(),x.getPasajeros(),jtxaDesc.getText(),iDAsientos,
                x.getAsientoZR(),x.getAsientoZ(),x.getBusZ(),x.getBusCanvasZ(),
                jtxtNombreCorto.getText(),x.iFD,x.iFI,x.iFDReal,x.iFIReal,tipoNum, this.USUARIO_ID);
        JOptionPane.showMessageDialog(this,"La plantilla se guardo correctamente.","¡Plantilla guardada!",JOptionPane.INFORMATION_MESSAGE);
        // INICIO
        jpnlGuardar.setVisible(false);
        jpnlCompo.setVisible(false);
        jpnlNumeros.setVisible(false);
        jpnlConf.setVisible(true);
        jbtnLimpiar.setVisible(true);
        jbtnTerminar.setEnabled(false);
        jbtnSiguiente.setEnabled(true);
        jbtnAnterior.setEnabled(false);
        jtxtNombreCorto.setText("");
        jtxaDesc.setText("");
        this.setTitle("Diseño de Plantilla: Paso 1 de 4 - Creación de Plantilla");
        jbtnLimpiar.doClick();
        jtxtCapacidad.requestFocusInWindow();
    }

    private void jbtnLimpiar_actionPerformed(ActionEvent e) {
        if(x==null){
            JOptionPane.showMessageDialog(this,"No ha creado una plantilla de Asientos","¡Acción no Válida!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if(!x.vCompoLocal.isEmpty() || !x.vCompoAereo.isEmpty()) xTabla.setDataVector(null,encabezado);
            if(!x.vCompoLocal.isEmpty()) x.vCompoLocal.clear();
            if(!x.vCompoAereo.isEmpty()) x.vCompoAereo.clear();
            x=null;
        }
        jtxtCapacidad.setText("49");
        jtxtLargoBus.setText("14");
        jtxtLargoAsiento.setText("0.81");
        jtxtAnchoAsiento.setText("0.51");
        jckbAlinFilas.setSelected(false);
        jrbtI.setSelected(true);
        jckbDistribucion.setSelected(true);
        jtxtAnchoBus.setText("2.60");
        jspnAsientosFila.setValue(4);
        jpnlConfAdicional.setVisible(false);
        jcnvPlantilla.Limpiar();
        jcnvPlantilla.repaint();
    }

    private void jbtnAplicar_actionPerformed(ActionEvent e) {
        if(x==null) return;
        int iVar=jlstNumeros.getSelectedIndex();
        if(iVar<0){
            JOptionPane.showMessageDialog(this,"No ha Seleccionado un Tipo de Numeración","¡Imposible Aplicar Numeración!",JOptionPane.INFORMATION_MESSAGE);
            return; }
        this.iTipoNumeracion=iVar;
        /*if(iVar==5 && x.iFIReal==x.iFDReal && x.vCompoLocal.isEmpty()){
            JOptionPane.showMessageDialog(this,
            "Este Tipo de Numeración solo se Aplica a"+"\n"+
            "Plantilla con Componentes y con Columnas Diferentes en Numero de Filas",
            "¡Imposible Aplicar Numeración!",JOptionPane.INFORMATION_MESSAGE); return;
        }*/
        if(iVar!=5){
            x.numeracionDeAsientos(iVar);
            jcnvPlantilla.setDato(x.mAI,x.mAD,x.mAR);
            jcnvPlantilla.repaint();
        }
        else{ // NUMERACION PERSONALIZADA
            jbtnAplicar.setEnabled(false);
            jlstNumeros.setEnabled(false);
            Object[][] iDatos=x.EstablecerNumPersonal();
            yTabla.setDataVector(iDatos,encabezado2);
            jscpNumPersonal.setVisible(true);
            jbtnAceptar.setVisible(true);
            // JNumberTextField
            //JNumberTextField jcmbY = new JNumberTextField();
            TableColumn columna = jtblNumPersonal.getColumnModel().getColumn(1);
            columna.setCellEditor(new IntegerEditor(1,49));
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText("Clic para Reenumerar");
            columna.setCellRenderer(renderer);
        }
    }

    private void jbtnAceptar_actionPerformed(ActionEvent e) {
        Object[] iDatos=new Object[x.getPasajeros()];
        for(int i=0; i<x.getPasajeros(); i++)
            iDatos[i]=yTabla.getValueAt(i,1);
        x.AceptarNumPersonal(iDatos);
        jscpNumPersonal.setVisible(false);
        jbtnAceptar.setVisible(false);
        jbtnAplicar.setEnabled(true);
        jlstNumeros.setEnabled(true);
        jcnvPlantilla.setDato(x.mAI,x.mAD,x.mAR);
        jcnvPlantilla.repaint();
    }
       
    private void jtxtLargoBus_focusLost(FocusEvent e) {
        this.ValDimBusLargo(jtxtLargoBus.getText());
    }
    
    private void jtxtLargoBus_keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            this.ValDimBusLargo(jtxtLargoBus.getText());
    }

    private void jtxtAnchoBus_keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            this.ValDimBusAncho(jtxtAnchoBus.getText());
    }

    private void jtxtAnchoBus_focusLost(FocusEvent e) {
        this.ValDimBusAncho(jtxtAnchoBus.getText());
    }

    private void jtxtLargoAsiento_keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            this.ValDimAsientoLargo(jtxtLargoAsiento.getText());
    }

    private void jtxtLargoAsiento_focusLost(FocusEvent e) {
        this.ValDimAsientoLargo(jtxtLargoAsiento.getText());
    }
    
    private void jtxtAnchoAsiento_keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            this.ValDimAsientoAncho(jtxtAnchoAsiento.getText());
    }

    private void jtxtAnchoAsiento_focusLost(FocusEvent e) {
        this.ValDimAsientoAncho(jtxtAnchoAsiento.getText());
    }
    
    private void ValDimBusLargo(String strTexto){
        float iVar=-1;
        if(!strTexto.equals("")) iVar=Float.valueOf(strTexto);
        if(iVar>y.fmaxBUSZ || iVar<y.fminBUSZ || strTexto.equals("")){
            jtxtLargoBus.setText("14");
            JOptionPane.showMessageDialog(this,"Largo Mínimo: "+ y.fminBUSZ+
                                        " metros, Largo Máximo: "+y.fmaxBUSZ+" metros.",
                                        "Valor <<"+strTexto+" metros>> no Válido para Largo de Autobús",JOptionPane.ERROR_MESSAGE);
            jtxtLargoBus.requestFocus();
            return;
        }
    }

    private void ValDimBusAncho(String strTexto){
        float iVar=-1;
        if(!strTexto.equals("")) iVar=Float.valueOf(strTexto);
        if(iVar>y.fmaxBUSX || iVar<y.fminBUSX || strTexto.equals("")){
            jtxtAnchoBus.setText("2.60");
            JOptionPane.showMessageDialog(this,"Ancho Mínimo: "+ y.fminBUSX+
                                        " metros, Ancho Máximo: "+y.fmaxBUSX+" metros.",
                                        "Valor <<"+strTexto+" metros>> no Válido para Ancho de Autobús",JOptionPane.ERROR_MESSAGE);
            jtxtAnchoBus.requestFocus();
            return;
        }
    }

    private void ValDimAsientoLargo(String strTexto){
        float iVar=-1;
        if(!strTexto.equals("")) iVar=Float.valueOf(strTexto);
        if(iVar>y.fmaxASIENTOZ || iVar<y.fminASIENTOZ || strTexto.equals("")){
            jtxtLargoAsiento.setText("0.81");
            JOptionPane.showMessageDialog(this,"Largo Mínimo: "+ y.fminASIENTOZ+
                                        " metros, Largo Máximo: "+y.fmaxASIENTOZ+" metros.",
                                        "Valor <<"+strTexto+" metros>> no Válido para Largo de Asiento",JOptionPane.ERROR_MESSAGE);
            jtxtLargoAsiento.requestFocus();
            return;
        }
    }

    private void ValDimAsientoAncho(String strTexto){
        float iVar=-1;
        if(!strTexto.equals("")) iVar=Float.valueOf(strTexto);
        if(iVar>y.fmaxASIENTOX || iVar<y.fminASIENTOX || strTexto.equals("")){
            jtxtAnchoAsiento.setText("0.51");
            JOptionPane.showMessageDialog(this,"Ancho Mínimo: "+ y.fminASIENTOX+
                                        " metros, Ancho Máximo: "+y.fmaxASIENTOX+" metros.",
                                        "Valor <<"+strTexto+" metros>> no Válido para Ancho de Asiento",JOptionPane.ERROR_MESSAGE);
            jtxtAnchoAsiento.requestFocus();
            return;
        }
    }

    private void jtxtCapacidad_keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            this.ValCapacidad(jtxtCapacidad.getText());
    }

    private void jtxtCapacidad_focusLost(FocusEvent e) {
        this.ValCapacidad(jtxtCapacidad.getText());
    }

    private void ValCapacidad(String strTexto){
        int iVar=0;
        if(!strTexto.equals("")) iVar=Integer.valueOf(strTexto);
        if(iVar>100 || iVar<1 || strTexto.equals("")){
            jtxtCapacidad.setText("49");
            JOptionPane.showMessageDialog(this,"Capacidad Mínima: 1 Pasajero, Capacidad Máxima: 100 Pasajeros.",
                                        "Valor <<"+strTexto+" pasajeros>> no Válido para Capacidad",JOptionPane.ERROR_MESSAGE);
            jtxtCapacidad.requestFocus();
            return;
        }
    }

    private void this_windowClosing(WindowEvent e) {
        if(JOptionPane.showConfirmDialog(this,"¿Realmente desea Salir de la Aplicación?","¡No ha Terminado de Crear una Plantilla!",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
