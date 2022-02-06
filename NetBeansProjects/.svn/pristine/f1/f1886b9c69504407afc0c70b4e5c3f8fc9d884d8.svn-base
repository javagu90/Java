/*
 * jdlg_RolMaestro.java
 *
 * Created on 19 de noviembre de 2007, 02:36 PM
 */

package tmsroles;

import header_tables.ColumnGroup;
import header_tables.GroupableTableHeader;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import tmsroles.entidad.TmsAutobusesTbl;
import tmsroles.entidad.TmsCorridasTbl;
import tmsroles.entidad.TmsCorridasVentaTbl;
import tmsroles.entidad.TmsFlotillasTbl;
import tmsroles.entidad.TmsOfertasServicioTbl;
import tmsroles.entidad.TmsRolesBaseLineasTbl;
import tmsroles.entidad.TmsRolesBaseTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;
import tmsroles.entidad.TmsServiciosTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_RolMaestro extends javax.swing.JDialog {
    
    /** Creates new form jdlg_RolMaestro */
    public jdlg_RolMaestro(TmsRolesManagedBean pbusquedas,Vector pnomCategorias,String pnombrerolmaestro, Timestamp pfechai, Timestamp pfechaf, int pnumdias, Vector phorRoles, long pidrservicio, Vector pindiceCorridas,Vector pidCorridas,long pusuarioId,TmsFlotillasTbl pflotillaSeleccionada, String pcoris, String pcdes, String pEmp, Vector pautobusesAsignados, Vector pvrutes) {
        this.setTitle("Rol Maestro");
        this.nomCategorias = pnomCategorias;
        this.nombrerolmaestro = pnombrerolmaestro;
        this.fechai = pfechai;
        this.fechaf = pfechaf;
        this.numdias = pnumdias;
        this.horRoles = phorRoles;
        this.busquedas =pbusquedas;
        this.idrservicio = pidrservicio;
        this.indiceCorridas = pindiceCorridas;
        this.idCorridas = pidCorridas;
        this.usuarioId = pusuarioId;
        this.flotillaSeleccionada = pflotillaSeleccionada;
        this.coris = pcoris;
        this.cdes = pcdes;
        this.cemp = pEmp;
        this.vrutes = pvrutes;
        System.out.println("Las Rutas en Rol Maestro son: "+vrutes);
        this.autobusesAsignados = pautobusesAsignados;
        initComponents();
        //this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        //this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
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
//        jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Cerrar Rol Maestro | <font color=FF0000>F5 </font> Asignar Autobuses | <font color=FF0000> F6 </font> Cambiar Autobuses | <font color=FF0000> F7 </font> Rolar Autobuses | <font color=FF0000> F9 </font> Guardar Rol Maestro | <font color=FF0000> F10 </font> Generar Corridas  </html>"); 
        jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Cerrar Rol Maestro | <font color=FF0000> F9 </font> Exportar a Excel | <font color=FF0000> F10 </font> Generar Corridas  </html>"); 
/////////// ********************* CREACION DEL ROL MAESTRO ********************* ////       
        UIManager.put(GroupableTableHeader.uiClassID, "header_tables.GroupableTableHeaderUI");        
        long tn=fechai.getTime();
        long tn2=fechaf.getTime();
        diasduracion=(tn2-tn)/86400000;

    num = nomCategorias.size();
    tbf = new DefaultTableModel(){
     public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 10)
               return true;
            return false;
        }};
   tbnf = new DefaultTableModel(){
     public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 370)//columnedit)
               return true;
            return false;
        }};
   mFixed = new JTable(tbf){
        public Component prepareRenderer(TableCellRenderer renderer,
                                         int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText((String)getValueAt(rowIndex, vColIndex));
            }
            return c;
        }
    };
   mTable = new JTable(tbnf);
 tbf.addColumn("Dia");
 for(int i=0; i<nomCategorias.size(); i++)      
   tbf.addColumn(nomCategorias.get(i).toString());////System.out.println(""+nombresroles[i][0]+" "+nombresroles[i][1] +" "+nombresroles[i][2]);
 ColumnGroup g_name2 = null;
 fechas = new ColumnGroup[360];        
  cm2 = mTable.getColumnModel();
  header2 = new GroupableTableHeader(mTable.getColumnModel());
 GroupableTableHeader header = new GroupableTableHeader(mFixed.getColumnModel());
 TableColumnModel cm = mFixed.getColumnModel();
 ColumnGroup g_name = new ColumnGroup(nombrerolmaestro);
 for(int i=1; i<=num ;i++)
  g_name.add(cm.getColumn(i));
 header.addGroup(g_name);
 mFixed.setTableHeader(header);
 mTable.setTableHeader(header2);
 TableColumn fixed = mFixed.getColumnModel().getColumn(0);
 fixed.setResizable(false);
 int n = 35;
 fixed.setMinWidth( n );
 fixed.setMaxWidth( n );
 fixed.setPreferredWidth( n );
 fixed.setResizable( false );
 JTextField celda = new JTextField();
 celda.setFont(new Font("Dialog", 0, 6));

 for(int h=1; h<=num; h++)
 {
  fixed = mFixed.getColumnModel().getColumn(h);
  fixed.setCellEditor(new DefaultCellEditor(celda));
  fixed.setResizable(false);
  n = 80;
  fixed.setMinWidth( n );
  fixed.setMaxWidth( n );
  fixed.setPreferredWidth( n );
  //fixed.setResizable( false );
 }
 SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
 SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
 long tt=fechai.getTime();
 tbnf.addColumn(formatd.format(tt));
            System.out.println("                 Esta es la fecha que inicia = "+ formatd.format(tt));

 fechas[0] = new ColumnGroup(formatf.format(tt));
 fechas[0].add(cm2.getColumn(0));
 header2.addGroup(fechas[0]);
 int pos;
 //nautobuses = maestronum;
 for(int k=1; k<=numdias; k++) 
 {
 String[] r= new String[90];
  r[0]=""+k;
     for(int j=0; j<num; j++)
    {
      Vector rol = (Vector)horRoles.get(j);
      Vector dia = (Vector)rol.get(k-1);
         if(Integer.parseInt(dia.get(1).toString())==k) 
          {
            int ni=0;
            for(int v=2; v<dia.size();v++)
            {
                if(!dia.get(v).toString().equals(""))
                {
                    ni=v;
                    break;
                }
            }
             r[j+1]=dia.get(ni).toString();
           }
    }
   System.out.println("renglon="+r[0]+" "+r[1]+" "+r[2]+" "+r[3]+" "+r[4]+" "+r[5]+" "+r[6]+" "+r[7]);
   tbf.addRow(r);
   //mFixed.setValueAt((String)""+(i+1),i,0);
   String[] r2= new String[90];
   r2[0]=""+autobusesAsignados.get(k - 1);
   tbnf.addRow(r2);
 }
 System.out.println("los Autobuses Asignado: "+autobusesAsignados);
 mFixed.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 mTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 mFixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 mFixed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
               mFixed_finalKeyPressed(evt);
            }
        });
 mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 mTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
               mTable_finalKeyPressed(evt);
            }
        });
        
        
            jScrollPane1 = new JScrollPane(mTable);
            jScrollPane1.setBounds(new Rectangle(5, 10, 785, 500));
            //------this.getContentPane().add(jScrollPane1, null);
             this.add(jScrollPane1, null);
            viewport.setView(mFixed);
            viewport.setPreferredSize(mFixed.getPreferredSize());
            jScrollPane1.setRowHeaderView(viewport);
            jScrollPane1.setCorner(JScrollPane.UPPER_LEFT_CORNER, mFixed.getTableHeader());

            //mFixed.getSelectionModel().addListSelectionListener(this);
            mFixed.setCellSelectionEnabled(true);
            //mTable.getSelectionModel().addListSelectionListener(this);
             mTable.setCellSelectionEnabled(true);    
            mTable.addMouseListener(new MouseAdapter() 
               {
                 public void mouseClicked(MouseEvent e) 
                  {
                   if (e.getClickCount() == 2)
                   {
                    if(false)//mTable.getValueAt(mTable.getSelectedRow(),mTable.getSelectedColumn())==null)
                        JOptionPane.showMessageDialog(new JDialog(),"Debes de asignar autobuses primero","¡No se a asignado ningun autobus!", 0);
                    else
                     {
                        if(diaEncontrado)
                        {
                           if(mFixed.getValueAt(mFixed.getSelectedRow(),mFixed.getSelectedColumn()).equals("Guardia"))
                                JOptionPane.showMessageDialog(new JDialog(),"       Esta fecha es una \n            G U A R D I A",mTable.getColumnName(mTable.getSelectedColumn())+" "+ fechas[mTable.getSelectedColumn()].getName(), 1);
                           else
                           {
                           //List<TmsRolesBaseRolMaestroV> salidas = Rolesbase.queryTmsRolesBaseRolMaestroVFindByClave(idrolmaestro,Long.parseLong((nombresroles[(mFixed.getSelectedColumn())-1][0])),mFixed.getValueAt(mFixed.getSelectedRow(),mFixed.getSelectedColumn())); //5000,Long.parseLong((nombresroles[(mFixed.getSelectedColumn())-1][0])),mFixed.getValueAt(mFixed.getSelectedRow(),mFixed.getSelectedColumn()));
                           Vector rol = (Vector) horRoles.get(mFixed.getSelectedColumn()-1);
                           Vector salidas = (Vector)rol.get(mFixed.getSelectedRow());
                           dh = new JDialog(new JDialog(),mTable.getColumnName(mTable.getSelectedColumn())+" "+ fechas[mTable.getSelectedColumn()].getName(),true);
                           dh.setLocation(300,200);
                           nsali =0;
                           DefaultTableModel model = new DefaultTableModel(){
                                     public boolean isCellEditable (int row, int column)
                                        {if (column == 3)return true;return false;}};
                           model.addColumn("Salidas");
                           //model.addColumn("Hora");
                           String[] datos_tabla = new String[2];
                           for(int i=2;i<salidas.size(); i++) 
                           {
                            //TmsRolesBaseRolMaestroV cadauna = salidas.get(i);
                            //datos_tabla[0]= cadauna.getClaveOrigen();
                            //datos_tabla[1]= cadauna.getHora();
                             if(!salidas.get(i).toString().equals(""))
                               {
                                   datos_tabla[0]= salidas.get(i).toString();
                                   model.addRow(datos_tabla);
                                   nsali++;
                               }  

                           }
                           dh.setResizable(false);
                           JTable table = new JTable(model);
                           JScrollPane scrollPane = new JScrollPane(table);
                           dh.getContentPane().add(scrollPane);
                           if(salidas.size()==1)
                            dh.setSize(new Dimension(200, 80)); 
                           else
                            dh.setSize(new Dimension(200, 60+(20*(nsali)))); 
                           dh.setVisible( true );

                           }
                        }
                       //System.out.println("Se ha producido un doble click");
                    }
                   }
                   else{ 
                     diaEncontrado = false;
                     String columnDia ="";
                     int fila = mTable.rowAtPoint(e.getPoint());
                     int columna = mTable.columnAtPoint(e.getPoint());
                     if ((fila >= 0) && (columna >= 0))
                        {
                         columnDia= mTable.getColumnName(mTable.getSelectedColumn());
                         if(columnDia.equals("lunes"))columnDia= "Lun";
                         if(columnDia.equals("martes"))columnDia= "Mar";
                         if(columnDia.equals("miércoles"))columnDia= "Mie";
                         if(columnDia.equals("jueves"))columnDia= "Jue";
                         if(columnDia.equals("viernes"))columnDia= "Vie";
                         if(columnDia.equals("sábado"))columnDia= "Sab";
                         if(columnDia.equals("domingo"))columnDia= "Dom";
                         for(int i=0; i<num; i++) {
                            String nombre = nomCategorias.get(i).toString();
                           if(nombre.indexOf(columnDia)!=-1) 
                            {
                                mFixed.setRowSelectionInterval(fila, fila);
                                mFixed.setColumnSelectionInterval(i+1,i+1);//columna, columna);
                                diaEncontrado = true;
                            }
                         }
                        }
                     if(!diaEncontrado)
                         new jdlg_advertencia("¡Este dia no tiene salidas programadas!","","Dia sin salidas").setVisible(true);
                   }
               }});    
//         SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
//         SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
         Timestamp t = fechai;
         long tt2=(t.getTime())+86400000;
         for(int i=1; i<=diasduracion; i++)
          {
           tbnf.addColumn(formatd.format(tt2));
           tt2=tt2+86400000;
          }
         t = fechai;
         tt2=t.getTime();
         for(int i=0; i<mTable.getColumnCount(); i++)
           {
               fechas[i] = new ColumnGroup(formatf.format(tt2));
               fechas[i].add(cm2.getColumn(i));
               header2.addGroup(fechas[i]);
               tt2=tt2+86400000;
               int nrows = mTable.getRowCount();
               if(i>0)
               {
                for (int j=0; j<nrows; j++)
                {
                    if(j==0)
                      mTable.setValueAt(mTable.getValueAt(nrows-1,i-1) ,j,i);
                    else
                      mTable.setValueAt(mTable.getValueAt(j-1,i-1) ,j,i);
                }
               }
           }
         enrolados = true;    
    }
    
    
    private void mTable_finalKeyPressed(java.awt.event.KeyEvent evt) {                                       
/*        if(evt.getKeyCode() == evt.VK_F5)
           agregarAutobuses();
        
        if(evt.getKeyCode() == evt.VK_F6)
           cambiarAutobuses();     
        
        if(evt.getKeyCode() == evt.VK_F7)
           enrolarAutobuses();

        if(evt.getKeyCode() == evt.VK_F9)
           guardarRolMaestro();
*/
        
        if(evt.getKeyCode() == evt.VK_F9)
            exportarExcel();
        
        if(evt.getKeyCode() == evt.VK_F10)
        {
            if(enrolados)
            {
              jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de generar corridas", "¿Esta seguro que quiere generar corridas?");
              psn.setVisible(true);
              if(respuestaSN)
                     new jdlg_fechasGenerarCorridasDentro().setVisible(true); //generarCorridas();
            }
            else
                new jdlg_error("¡Los autobuses aun no estan enrolados para poder generar corridas!","","Faltan pasos previos").setVisible(true);
        }
           
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            salir();
    }
        
     private void mFixed_finalKeyPressed(java.awt.event.KeyEvent evt) {                                       
/*        if(evt.getKeyCode() == evt.VK_F5)
          agregarAutobuses();

        if(evt.getKeyCode() == evt.VK_F6)
           cambiarAutobuses();
        
        if(evt.getKeyCode() == evt.VK_F7)
           enrolarAutobuses();
        
        if(evt.getKeyCode() == evt.VK_F9)
           guardarRolMaestro();
*/
        if(evt.getKeyCode() == evt.VK_F9)
            exportarExcel();
         
        if(evt.getKeyCode() == evt.VK_F10)
        {
            if(enrolados)
            {
             jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de generar carridas", "¿Esta seguro que quiere generar corridas?");
              psn.setVisible(true);
              if(respuestaSN)
                 new jdlg_fechasGenerarCorridasDentro().setVisible(true); //generarCorridas();
            }
            else
                new jdlg_error("¡Los autobuses aun no estan enrolados para poder generar corridas!","","Faltan pasos previos").setVisible(true);
        }
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            salir();
    }
        
     
     private void exportarExcel(){
            File b = null;
            String nombreArchivoExcel = "";
            jdl_nombreArchivoExcel dnom = new jdl_nombreArchivoExcel(new JDialog(), true);
            dnom.setVisible(true);
            if(dnom.getNombre().equals("salida"))
              return;
            else
             nombreArchivoExcel = dnom.getNombre();
             
             b = new File("C:\\"+nombreArchivoExcel+".xls");
             if(b.exists())
             {
                 new jdlg_error("¡El archivo existe!"," Favor de introducir otro nombre"," Archivo existente").setVisible(true);
                 System.out.println("El archivo existe...");
                 return;
             }
             else
             {  
                     jProgressBar1.setValue( 0 );
                     jProgressBar1.setMinimum( 0 );
                     jProgressBar1.setMaximum(mFixed.getRowCount() - 1);
                 String  contenidoArchivoExcel = ""; 
                 contenidoArchivoExcel = contenidoArchivoExcel +  "\t";
                 contenidoArchivoExcel = contenidoArchivoExcel + nombrerolmaestro+ "\t";
                 for(int t1=1; t1<nomCategorias.size();t1++)
                     contenidoArchivoExcel = contenidoArchivoExcel +  "\t";
                 for(int t3=0; t3<mTable.getColumnCount(); t3++)
                    contenidoArchivoExcel = contenidoArchivoExcel +  fechas[t3].getName()+"\t";                
                 contenidoArchivoExcel = contenidoArchivoExcel +"\n";
                 contenidoArchivoExcel = contenidoArchivoExcel +  "Día\t";
                 for(int t4=0; t4<nomCategorias.size();t4++)
                     contenidoArchivoExcel = contenidoArchivoExcel + nomCategorias.get(t4).toString()+ "\t";
                 for(int t2=0; t2<mTable.getColumnCount(); t2++)
                    contenidoArchivoExcel = contenidoArchivoExcel + mTable.getColumnName(t2)+"\t";
                 contenidoArchivoExcel = contenidoArchivoExcel +"\n";

                 for(int i=0; i<mFixed.getRowCount(); i++){ 
                 for(int j=0; j<mFixed.getColumnCount(); j++)
                     contenidoArchivoExcel = contenidoArchivoExcel + mFixed.getValueAt(i,j).toString()+"\t";
                 for(int k=0; k<mTable.getColumnCount(); k++)
                     contenidoArchivoExcel = contenidoArchivoExcel + mTable.getValueAt(i,k).toString()+"\t";

                    contenidoArchivoExcel = contenidoArchivoExcel +"\n";
                           jProgressBar1.setValue(i);
                           jProgressBar1.setStringPainted(true);
                           jProgressBar1.paint(jProgressBar1.getGraphics());
                }
                    FileOutputStream os = null;
                        try {
                             os = new FileOutputStream("C:\\"+nombreArchivoExcel+".xls");
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        PrintStream ps = new PrintStream(os); 
                        ps.print(contenidoArchivoExcel);
                        try {
                            os.close(); 
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } 


              jdlg_pregunta_SN psn2 =  new jdlg_pregunta_SN("Exportacion completada", "Los datos se exportaron correctamente ¿Desea ver el archivo?");
              psn2.setVisible(true);
               if(respuestaSN)
               {
                    try
                       {

                         Process p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL C://"+nombreArchivoExcel+".xls"); //prueba.xls");
                        }
                        catch (Throwable exc4)
                        {
                          System.out.println("No se puede abrir el archivo de Excel.\n");
                          exc4.printStackTrace();
                        }
                }   
                     jProgressBar1.setValue( 0 );
                     jProgressBar1.setMinimum( 0 );
                     jProgressBar1.setMaximum(0);
             }
     }
     
     
    private void agregarAutobuses() {
        if(!asignados)
        {
            insertados=0;
            nautobuses = mFixed.getRowCount();
            dbuses = new JDialog(new JDialog(),"Autobuses");
            dbuses.setLayout(null);
            dbuses.setAlwaysOnTop(true);
            JButton botonBus = new JButton("Buscar");
            botonBus.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    botonBusActionPerformed(evt);
                }
            });
            modelolist = new DefaultListModel();
            lista = new JList(modelolist);
            jcmbx_flotillas = new JComboBox();
            Vector listadoFlotillas = (Vector)busquedas.variosFacadeRemote.buscaFlotillas(); 
            jcmbx_flotillas.removeAllItems();
            for(int i=0; i<listadoFlotillas.size(); i++)
            {
                Vector flo = (Vector)listadoFlotillas.get(i);
                jcmbx_flotillas.addItem(flo.get(0).toString());
            }
            jcmbx_flotillas.setSelectedItem(flotillaSeleccionada.getNombreFlotilla());
            buscaBuses(); 
            MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) 
                {
                    if (e.getClickCount() == 2) // Se checa si es doble click
                    {
                         if(insertados<=nautobuses)
                         {
                           int posicion = lista.locationToIndex(e.getPoint());
                           //System.out.println("La posicion es " + posicion +" -- "+lista.getModel().getElementAt(posicion));//lista.getSelectedValue());
                           insertados++;
                           mTable.setValueAt(lista.getModel().getElementAt(posicion),insertados-1,0);
                           modelolist.removeElementAt(posicion);
                           //System.out.println("insertados="+insertados+"   nautobuses="+nautobuses);
                           if(insertados==nautobuses)
                           {
                               asignados = true;
                               dbuses.dispose();
                           }
                         }
                     }
                }
            };
             lista.addMouseListener(mouseListener);
             lista.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                              {
                               selected = lista.getSelectedValues();
                               if(selected.length<=nautobuses && selected.length<=(nautobuses-insertados))
                                {
                                for(int i=0; i<selected.length; i++)
                                 {
                                  //System.out.println("La posicion es " + selected[i]);
                                  insertados++;
                                  mTable.setValueAt(selected[i],insertados-1,0);
                                  modelolist.removeElement(selected[i]);
                                 }
                                if(insertados==nautobuses)
                                {
                                    asignados = true;
                                    dbuses.dispose();
                                }
                               }
                              else
                                  JOptionPane.showMessageDialog(new JDialog(),"La seleccion debe de ser menor o igual a "+(nautobuses-insertados)+" Autobuses","Demasiados Autobuses seleccionados", 1);
                             }
                        }  
                    });
             jcmbx_flotillas.setBounds(0,0,146,25);
             botonBus.setBounds(35,26,80,25);
             JScrollPane scroll = new JScrollPane(lista);
             scroll.setBounds(0,53,145,345);
             dbuses.getContentPane().add(jcmbx_flotillas);
             dbuses.getContentPane().add(botonBus);
             dbuses.getContentPane().add(scroll);
             dbuses.setLocation(200,200);
             dbuses.setSize( 152, 400 ); 
             dbuses.setVisible( true );
             dbuses.setResizable(false);
        }
        else
            new jdlg_advertencia("¡Los autobuses ya estan asignados!","","").setVisible(true);
    }    
    
    private void cambiarAutobuses(){
        int ncolumnas = mTable.getColumnCount();
        for(int i=0; i<ncolumnas; i++)
        {
         TableColumn column = mTable.getColumnModel().getColumn(0);
         mTable.removeColumn(column);
        }
        tbnf.setColumnCount(0);
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
        long tt=fechai.getTime();
        tbnf.addColumn(formatd.format(tt));
        fechas[0] = new ColumnGroup(formatf.format(tt));
        fechas[0].add(cm2.getColumn(0));
        header2.addGroup(fechas[0]);
        enrolados = false;
        asignados = false;
    }   
    
    private void enrolarAutobuses(){
      if(asignados)
      {
        if(!enrolados)
        {
         SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
         SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
         Timestamp t = fechai;
         long tt=(t.getTime())+86400000;
         for(int i=1; i<=diasduracion; i++)
          {
           tbnf.addColumn(formatd.format(tt));
           tt=tt+86400000;
          }
         t = fechai;
         tt=t.getTime();
         for(int i=0; i<mTable.getColumnCount(); i++)
           {
               fechas[i] = new ColumnGroup(formatf.format(tt));
               fechas[i].add(cm2.getColumn(i));
               header2.addGroup(fechas[i]);
               tt=tt+86400000;
               int nrows = mTable.getRowCount();
               if(i>0)
               {
                for (int j=0; j<nrows; j++)
                {
                    if(j==0)
                      mTable.setValueAt(mTable.getValueAt(nrows-1,i-1) ,j,i);
                    else
                      mTable.setValueAt(mTable.getValueAt(j-1,i-1) ,j,i);
                }
               }
           }
         enrolados = true;
       }
        else
            new jdlg_advertencia("¡Los autobuses ya estan enrolados!","","Autobuses enrolados").setVisible(true);
      }
      else
           new jdlg_advertencia("¡Los autobuses aun no estan asignados!","","Autobuses enrolados").setVisible(true);
    }
    
   private void botonBusActionPerformed(java.awt.event.ActionEvent evt) {
    buscaBuses(); 
   }

   private void buscaBuses(){
            modelolist.removeAllElements();
            vautobuses = (Vector)busquedas.variosFacadeRemote.buscaAutobusesPorFlotilla(jcmbx_flotillas.getSelectedItem().toString());
            for(int i=0; i<vautobuses.size(); i++)
            {
                vautobus = (Vector)vautobuses.get(i);
                boolean agregar = true;
                for(int j=0; j<mTable.getRowCount(); j++)
                {
                    if(mTable.getValueAt(j,0)!=null &&  mTable.getValueAt(j,0).toString().equals(vautobus.get(0).toString()))
                        agregar = false;
                }
                if(agregar)
                  modelolist.addElement(vautobus.get(0).toString());//unbus.getNumeroEconomico());
            }
   }
   
   public void salir(){
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir del Rol Maestro?");
          psn.setVisible(true);
          if(respuestaSN)
              this.dispose();
        }  
    
    public void generarCorridas(String sfcorridasi, String sfcorridasf, Timestamp fcori, Timestamp fcorf, Vector idRutasInfo){
            ////////  Borra las corridas que ya existan en la bade de datos entre las fechas >=sfcorridasi <= sfcorridasf 
//           Vector TER = (Vector)busquedas.variosFacadeRemote.queryBuscaNombreTerminal();
//            nombreTerminal = TER.get(0).toString();
//        System.out.println("Inicio: "+fcori);
//        System.out.println("Final: "+fcorf);
            Vector TER = (Vector)busquedas.variosFacadeRemote.queryBuscaIdTerminal();
            idTerminal = TER.get(0).toString();
            String te = idTerminal;
            if(idTerminal.length()<3)
            {
               for(int i=idTerminal.length(); i<3;i++)
                   te = te+"0";
            }
            if(idTerminal.length()>=3)
             te = idTerminal.substring(0,2);
                
            idTerminal = te;
            Vector vest =(Vector) busquedas.variosFacadeRemote.queryBuscaIdEstadoCorriaAbierta();
            if(vest.size()==0)
                new jdlg_error("¡Los estados de la corrida no estan configurados! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
            else
            {
                Vector edo = (Vector)vest.get(0);
                estadoAbiertaId = Long.valueOf(edo.get(0).toString());
            }
            Vector vestc =(Vector) busquedas.variosFacadeRemote.queryBuscaIdEstadoCorridaCerrada();
            if(vestc.size()==0)
                new jdlg_error("¡Los estados de la corrida no estan configurados! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
            else
            {
                Vector edoc = (Vector)vestc.get(0);
                estadoCerradaId = Long.valueOf(edoc.get(0).toString());
            }
            TmsServiciosTbl service = busquedas.serviciosTblFacadeRemote.find(BigDecimal.valueOf(idrservicio));
//            Vector vplan = (Vector)busquedas.variosFacadeRemote.buscaPlantllaefaultPorServicio(service.getServicioNombre());
//            if(vplan.size()==0)
//                new jdlg_error("¡La plantilla por defecto del servicio no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
//            else
//            {
//                Vector plan = (Vector)vplan.get(0);
//                plantillaDefaultId = Long.valueOf(plan.get(0).toString());
//            }
            Vector vplan = (Vector)busquedas.variosFacadeRemote.buscaPlantllasDefaultPorRuta();
            boolean noesta = true;
            String rutcadena;
            rutcadena = vrutes.toString();
            rutcadena = rutcadena.replace('[','(');
            rutcadena = rutcadena.replace(']',')');
            rutcadena = rutcadena.trim();
            System.out.println("Los valores de las Rutas son: "+vplan);
            if(vplan.size()==0)
            {
                new jdlg_error("¡La plantilla por defecto de las rutas no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                return;
            }
            else
            {
                for(int i=0; i<vplan.size(); i++)
                {
                    Vector vr = (Vector) vplan.get(i);
                    rutasId.add(vr.get(0).toString());
                    plantillaRuta.add(vr.get(1).toString());
                }
                for(int j=0; j<vrutes.size(); j++)
                {
                    if(rutasId.indexOf(vrutes.get(j).toString())==-1)
                        noesta = false;
                }
                    
                if(!noesta)
                {
                    new jdlg_error("¡La plantilla por defecto de las rutas no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                    return;
                }
            }

            
                List<TmsCorridasTbl> lcorrinbor = new ArrayList<TmsCorridasTbl>();
                 Vector indicecorrinbor = new Vector();
                 System.out.println("para el Query CR = "+sfcorridasi+"  "+sfcorridasf+"  "+idrservicio+"  "+coris+"  "+ cdes+"  "+cemp+ " "+rutcadena);
                 Vector lcexbd = (Vector)busquedas.variosFacadeRemote.queryBuscaCR(sfcorridasi,sfcorridasf,idrservicio,coris,cdes,cemp,rutcadena);  //queryTmsCorridasTblFindRangoFechas(fcorridasi,fcorridasf,"","");
                 System.out.println("Entre "+sfcorridasi+" y "+sfcorridasf+" existen "+ lcexbd.size()+" corridas en la BD");       
                 jProgressBar1.setValue( 0 );
                 jProgressBar1.setMinimum( 0 );
                 if(lcexbd.size()>0)
                 {
                  //if(lcexbd.size()>0)
                    jProgressBar1.setMaximum(lcexbd.size() - 1);
                 //else
                   //  jProgressBar1.setMaximum(0);
                 for(int i=0; i<lcexbd.size(); i++)
                 {
                   BigDecimal ic =(BigDecimal)((Vector)(lcexbd.get(i))).get(0);
                   long idcorrida = Long.valueOf(ic.toString());
                   List<TmsCorridasTbl> lcid= busquedas.variosFacadeRemote.queryTmsCorridasTblFindById(ic);
                   TmsCorridasTbl corridab = lcid.get(0);
                   //List<TmsBoletosNoDisponibleTbl> lbnd= consultaRolesbase.queryTmsBoletosNoDisponibleTblFindByIdCorrida(idcorrida);
                   //Vector vReservados = (Vector) busquedas.variosFacadeRemote.queryBuscarBoletosReservados(idcorrida);
                   //Vector vVendidos = (Vector) busquedas.variosFacadeRemote.queryBuscarBoletosVendidos(idcorrida);
                   Vector vVPasajeros = (Vector) busquedas.variosFacadeRemote.queryBuscarPasajeros(idcorrida);
                   Vector vPasajeros = (Vector)vVPasajeros.get(0);
                   long pasajeros = Long.valueOf(vPasajeros.get(0).toString());
                   //if(vVendidos.size()>0 || vReservados.size()>0)
                   if(pasajeros>0)
                   {
                      lcorrinbor.add(corridab); 
                      String fcor = formatfc.format((corridab.getFechaHoraCorrida().getTime()));
                      String hcor = formathc.format((corridab.getFechaHoraCorrida().getTime()));
                      String or = ""+corridab.getOrigenId();
                      String des = ""+corridab.getDestinoId();
                      String indicecb = fcor+"-"+hcor+"-"+or+"-"+des;
                      indicecorrinbor.add(indicecb);
                      System.out.println("La corrida "+idcorrida+" no se puede eliminar");
                   }
                   else
                   {
                       busquedas.variosFacadeRemote.removeTmsCorridasTbl(corridab);
                       busquedas.variosFacadeRemote.removeTmsCorridasVentaTbl(corridab.getCorridaId());
                   }
                   jProgressBar1.setValue(i);
                   jProgressBar1.setStringPainted(true);
                   jProgressBar1.paint(jProgressBar1.getGraphics());
                 }
                 for(int i=0; i<lcorrinbor.size(); i++)
                 {
                     TmsCorridasTbl corrida = lcorrinbor.get(i);
                     System.out.println("id: "+corrida.getCorridaId()+"     Fecha: "+formatfc.format(corrida.getFechaHoraCorrida().getTime())+"    Hora:"+formathc.format(corrida.getFechaHoraCorrida()) + "    Servicio id: "+corrida.getServicioId());
                 }
                }//if(lcexbd.size()>0) 

         System.out.println("El servicio de las corridas nuevas es: "+idrservicio);
         System.out.println(" Creando corridas nuevas...");     
    int numcolinial = 0;
    int numcolfinal = 0;
     Timestamp t = fcori;//fcorridasi;
     long tt=t.getTime();//+86400000;         
    for(int i=0; i<mTable.getColumnCount(); i++)
    {
        if(fechas[i].getName().equals(sfcorridasi))
        {
            numcolinial = i;
            break;
        }
        tt = tt+ 86400000;
    }
    for(int i=0; i<mTable.getColumnCount(); i++)
    {
        if(fechas[i].getName().equals(sfcorridasf))
        {
            numcolfinal = i;
            break;
        }
    }
    
    System.out.println("col inicial para generar las corridas: "+numcolinial);
    System.out.println("col final para generar las corridas: "+numcolfinal);
         
         
    ///Inicio de corridas nuevas     
         jProgressBar1.setValue(0);
         //System.exit(0);
        jProgressBar1.setValue( 0 );
        jProgressBar1.setMinimum( 0 );
        jProgressBar1.setMaximum((numcolfinal - numcolinial) + 1 );
        int corridasact = 0;
        int nprogress = 0;
        Vector NSC = new Vector();
        String ServicioComercial = "";
        boolean serv = true;
         SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
         SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
         SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
         //Timestamp t = fcori;//fcorridasi;
         //long tt=(t.getTime())+86400000;
        // long tt=t.getTime();
         //long diasduracion=(fcorridasf.getTime()-fcorridasi.getTime())/86400000;
        // long diasduracion2=(fcorf.getTime()-fcori.getTime())/86400000;
         //int ni = Integer.valueOf(""+diasduracion2);
        //jProgressBar1.setMaximum(Integer.valueOf(""+diasduracion2) );//numcolfinal - numcolinial);
        int nsali2 = 0;
        for(int i=numcolinial; i<=numcolfinal; i++)
        {
            boolean diaEncontrado = false;
            Vector rol = null;
            int columna=0;
                 String columnDia= mTable.getColumnName(i);                 
                 if(columnDia.equals("lunes"))columnDia= "Lun";
                 if(columnDia.equals("martes"))columnDia= "Mar";
                 if(columnDia.equals("miércoles"))columnDia= "Mie";
                 if(columnDia.equals("jueves"))columnDia= "Jue";
                 if(columnDia.equals("viernes"))columnDia= "Vie";
                 if(columnDia.equals("sábado"))columnDia= "Sab";
                 if(columnDia.equals("domingo"))columnDia= "Dom";
            for(int col=0; col<num; col++)
            {
                //String nomcol = mTable.getColumnName(i);
                String nombre = nomCategorias.get(col).toString();
                if(nombre.indexOf(columnDia)!=-1) 
                {
                 columna =  col;
                 diaEncontrado = true;
                 //System.out.println("Columna mFixed "+nomCategorias.get(columna).toString()+"=="+mTable.getColumnName(i));
                 rol = (Vector) horRoles.get(columna);
                 System.out.println("Rol: "+rol);
                 break;
                }
            }
           if(diaEncontrado)
           {
            for(int j=0; j<mTable.getRowCount(); j++)
            {            
                //System.out.println("Vector horRoles: "+horRoles+" indice= "+columna);  
                //System.out.println("Valor = "+mFixed.getValueAt(j,columna+1).toString());
                //System.out.println("Bus   = "+mTable.getValueAt(j,i).toString());
                String bus = mTable.getValueAt(j,i).toString();
                Vector vdatosBus = (Vector)busquedas.variosFacadeRemote.buscarOperBusesporNumEcon(mTable.getValueAt(j,i).toString());
                Vector datosBus = (Vector)vdatosBus.get(0);
                String valor = (String)mFixed.getValueAt(j,columna+1);
                 if(!valor.equals("Guardia"))
                 {
                           Vector salidas = (Vector)rol.get(j);
                           for(int k=2;k<salidas.size(); k++) 
                           {
                             if(!salidas.get(k).toString().equals(""))
                               {
                                   String scorrida = salidas.get(k).toString();
                                    System.out.println("Salida = "+ scorrida);
                                    int index = indiceCorridas.indexOf(scorrida);
                                    BigDecimal oferId =BigDecimal.valueOf (Long.valueOf(idCorridas.get(index).toString()));
                                    TmsOfertasServicioTbl oferta = busquedas.variosFacadeRemote.findOferServ(oferId);
                                    StringTokenizer sc = new StringTokenizer(scorrida,"-");
                                    String vh = sc.nextToken();
                                    String vo = sc.nextToken();
                                    String vd = sc.nextToken();
                                    //System.out.println("Hora:"+vh+"    Origen:"+vo+"   Destino="+vd );
                                    
                                    TmsCorridasTbl corrida = new TmsCorridasTbl();
                                    TmsCorridasVentaTbl corridaVenta = new TmsCorridasVentaTbl();
                                    //buscarOperBusesporNumEcon
                                     StringTokenizer ht = new StringTokenizer(vh,":");
                                     String hora = ht.nextToken();
                                     hora = hora+ht.nextToken();
                                     String oclv = "";
                                     if(vo.length()>=4)
                                      oclv = vo.substring(0,4);
                                     if(vo.length()<4)
                                     {
                                         oclv = vo;
                                         for(int jk=vo.length(); jk<4;jk++)
                                            oclv = oclv+"_";
                                     }
                                    corrida.setClaveCorrida(oclv+service.getServicioClave()+hora+"N");
                                    corrida.setServicioId(BigInteger.valueOf(idrservicio));
                                    corrida.setEmpresaId(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    corrida.setRutaId(oferta.getRutaId().getRutaId().toBigInteger());
                                    Timestamp fechaHora= new Timestamp(tt + milis(vh));
                                    corrida.setAutobusId(BigInteger.valueOf(Long.valueOf(datosBus.get(0).toString())));
                                    corrida.setAutobusOriginalId(BigInteger.valueOf(Long.valueOf(datosBus.get(0).toString())));
                                    if(datosBus.get(1)!=null)
                                    {
                                        corrida.setOperadorId(BigInteger.valueOf(Long.valueOf(datosBus.get(1).toString())));
                                        corrida.setOperadorOriginalId(BigInteger.valueOf(Long.valueOf(datosBus.get(1).toString())));
                                    }
                                    corrida.setFechaHoraCorrida(new Date(fechaHora.getTime()));
                                    corrida.setOrigenId(oferta.getTramoOrigenId());
                                    corrida.setDestinoId(oferta.getTramoDestinoId());
                                    corrida.setTipoCorrida("N");
                                    if(idRutasInfo.indexOf(oferta.getRutaId().getRutaId().toString())>=0)// if(abiertas)
                                        corrida.setEstadoCorridaId(BigInteger.valueOf(estadoAbiertaId));
                                    else
                                        corrida.setEstadoCorridaId(BigInteger.valueOf(estadoCerradaId));
                                    int indexrid = rutasId.indexOf(oferta.getRutaId().getRutaId().toString());
                                    plantillaDefaultId = Long.valueOf(plantillaRuta.get(indexrid).toString());
                                    corrida.setPlantillaId(BigInteger.valueOf(plantillaDefaultId));
                                    corrida.setAdicional4("ROL");
                                    corrida.setCreadoPor(BigInteger.valueOf(usuarioId));
                                    corrida.setFechaCreacion(new Date(fecha_servidor.getTime()));
                                    corrida.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                                    corrida.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                                    corrida.setReplicacionEstado("P");
                                    corrida.setReplicacionOrigen("PCENTRAL");

                                    
                                    corridaVenta.setEmpresa(oferta.getEmpresaId().getEmpresaNombre());
                                    corridaVenta.setServicio(service.getServicioNombre());
                                    corridaVenta.setFechaHoraCorrida(corrida.getFechaHoraCorrida());
                                    corridaVenta.setOrigen(vo);
                                    corridaVenta.setDestino(vd);
                                    corridaVenta.setTipoCorrida("N");
                                    if(idRutasInfo.indexOf(oferta.getRutaId().getRutaId().toString())>=0)//if(abiertas)
                                        corridaVenta.setEstadoCorrida("A");
                                    else
                                        corridaVenta.setEstadoCorrida("E");
                                    corridaVenta.setAutobus(bus);
                                    corridaVenta.setAutobusOriginal(bus);
                                    if(datosBus.get(2)!=null)
                                    {
                                        corridaVenta.setOperador(datosBus.get(2).toString());
                                        corridaVenta.setOperadorOriginal(datosBus.get(2).toString());
                                    }
                                    if(datosBus.get(4)!=null)
                                      corridaVenta.setOperadorAdicional(datosBus.get(4).toString());
                                    corridaVenta.setPlantillaId(BigInteger.valueOf(plantillaDefaultId));
                                    corridaVenta.setAsiento1("D");corridaVenta.setAsiento2("D");corridaVenta.setAsiento3("D");corridaVenta.setAsiento4("D");corridaVenta.setAsiento5("D");
                                    corridaVenta.setAsiento6("D");corridaVenta.setAsiento7("D");corridaVenta.setAsiento8("D");corridaVenta.setAsiento9("D");corridaVenta.setAsiento10("D");
                                    corridaVenta.setAsiento11("D");corridaVenta.setAsiento12("D");corridaVenta.setAsiento13("D");corridaVenta.setAsiento14("D");corridaVenta.setAsiento15("D");
                                    corridaVenta.setAsiento16("D");corridaVenta.setAsiento17("D");corridaVenta.setAsiento18("D");corridaVenta.setAsiento19("D");corridaVenta.setAsiento20("D");
                                    corridaVenta.setAsiento21("D");corridaVenta.setAsiento22("D");corridaVenta.setAsiento23("D");corridaVenta.setAsiento24("D");corridaVenta.setAsiento25("D");
                                    corridaVenta.setAsiento26("D");corridaVenta.setAsiento27("D");corridaVenta.setAsiento28("D");corridaVenta.setAsiento29("D");corridaVenta.setAsiento30("D");
                                    corridaVenta.setAsiento31("D");corridaVenta.setAsiento32("D");corridaVenta.setAsiento33("D");corridaVenta.setAsiento34("D");corridaVenta.setAsiento35("D");
                                    corridaVenta.setAsiento36("D");corridaVenta.setAsiento37("D");corridaVenta.setAsiento38("D");corridaVenta.setAsiento39("D");corridaVenta.setAsiento40("D");
                                    corridaVenta.setAsiento41("D");corridaVenta.setAsiento42("D");corridaVenta.setAsiento43("D");corridaVenta.setAsiento44("D");corridaVenta.setAsiento45("D");
                                    corridaVenta.setAsiento46("D");corridaVenta.setAsiento47("D");corridaVenta.setAsiento48("D");corridaVenta.setAsiento49("D");corridaVenta.setAsiento50("D");
                                    corridaVenta.setAsiento51("D");corridaVenta.setAsiento52("D");corridaVenta.setAsiento53("D");corridaVenta.setAsiento54("D");corridaVenta.setAsiento55("D");
                                    corridaVenta.setAsiento56("D");corridaVenta.setAsiento57("D");corridaVenta.setAsiento58("D");corridaVenta.setAsiento59("D");corridaVenta.setAsiento60("D");
                                    corridaVenta.setAsiento61("D");corridaVenta.setAsiento62("D");corridaVenta.setAsiento63("D");corridaVenta.setAsiento64("D");corridaVenta.setAsiento65("D");
                                    corridaVenta.setAsiento66("D");corridaVenta.setAsiento67("D");corridaVenta.setAsiento68("D");corridaVenta.setAsiento69("D");corridaVenta.setAsiento70("D");
                                    corridaVenta.setAsiento71("D");corridaVenta.setAsiento72("D");corridaVenta.setAsiento73("D");corridaVenta.setAsiento74("D");corridaVenta.setAsiento75("D");
                                    corridaVenta.setAsiento76("D");corridaVenta.setAsiento77("D");corridaVenta.setAsiento78("D");corridaVenta.setAsiento79("D");corridaVenta.setAsiento80("D");
                                    corridaVenta.setAsiento81("D");corridaVenta.setAsiento82("D");corridaVenta.setAsiento83("D");corridaVenta.setAsiento84("D");corridaVenta.setAsiento85("D");
                                    corridaVenta.setMenoresCorrida(oferta.getMenoresCorrida());
                                    corridaVenta.setSenectudCorrida(oferta.getSenectudCorrida());
                                    corridaVenta.setEstudiantesCorrida(oferta.getEstudiantesCorrida());
                                    corridaVenta.setProfesoresCorrida(oferta.getProfesoresCorrida());
                                    corridaVenta.setCortesiasCorrida(oferta.getCortesiasCorrida());
                                    corridaVenta.setAdicional2(""+oferta.getSenectudCorrida()+"-"+oferta.getProfesoresCorrida()+"-"+oferta.getEstudiantesCorrida()+"-"+oferta.getCortesiasCorrida());//S-P-E-Cor                                    
                                    corridaVenta.setReplicacionEstado("P");
                                    corridaVenta.setReplicacionOrigen("PCENTRAL");
                                    TmsCorridasTbl corridaNueva;

                                  String fcor = formatf.format((corrida.getFechaHoraCorrida().getTime()));
                                  String hcor = formath.format((corrida.getFechaHoraCorrida().getTime()));
                                  String or = ""+corrida.getOrigenId();
                                  String des = ""+corrida.getDestinoId();
                                  String indicecb = fcor+"-"+hcor+"-"+or+"-"+des;
                                 if(indicecorrinbor.indexOf(indicecb)!=-1)
                                 {
                                   TmsCorridasTbl corridaupdate = lcorrinbor.get(indicecorrinbor.indexOf(indicecb));
                                   busquedas.variosFacadeRemote.updateCorrida(corrida,corridaupdate.getCorridaId(),corridaVenta); 
                                   corridasact++;
                                   System.out.println("*********** Se actualiza la corrida: "+indicecb);
                                 }
                                 else
                                 {
                                       corridaNueva = busquedas.corridasTblFacadeRemote.create(corrida,idTerminal);
                                       corridaVenta.setCorridaId(corridaNueva.getCorridaId());
                                       corridaVenta.setClaveCorrida(corridaNueva.getClaveCorrida());
                                       busquedas.corridasVentaTblFacadeRemote.create(corridaVenta);
                                       nsali2++;
                                 }                                    
                               }  

                           }
                 }
            }
          }
          nprogress++;  
          jProgressBar1.setValue(nprogress);
          //System.out.println("aumenta la barra de estado a: "+i);
          jProgressBar1.setStringPainted(true);
          jProgressBar1.paint(jProgressBar1.getGraphics());   
          tt = tt+ 86400000;

        }//i==diasduracion2
            //SimpleDateFormat formatd = new SimpleDateFormat ("dd/MM/yyyy");
            if(corridasact==0)
                new jdlg_informacion("Se han generado "+nsali2 +" corridas exitosamente  ","del  "+ sfcorridasi +"  al  "+ sfcorridasf,"¡Corridas Generadas!").setVisible(true);
                //JOptionPane.showMessageDialog(this,"Se han generado "+nsali2 +" corridas exitosamente \n              del  "+ sfcorridasi +"  al  "+ sfcorridasf +"","¡Corridas Generadas!", 1);
            else
                new jdlg_informacion("Se han generado "+nsali2 +" nuevas corridas ","y actualizado "+corridasact+" corridas exitosamente \n       del  "+ sfcorridasi +"  al  "+ sfcorridasf,"¡Corridas Generadas!").setVisible(true);
               // JOptionPane.showMessageDialog(this,"Se han generado "+nsali2 +" nuevas corridas \ny actualizado "+corridasact+" corridas exitosamente \n       del  "+ sfcorridasi +"  al  "+ sfcorridasf +"","¡Corridas Generadas!", 1);

            jProgressBar1.setValue(0);
    ///termina la generacion de corridas  
        }  
   
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jlbl_barraEstado = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel jlbl_barraEstado;
    // End of variables declaration//GEN-END:variables
    private JTable mFixed = null;
    private JTable mTable = null;
    private DefaultTableModel tbf = null;//new DefaultTableModel();
    private DefaultTableModel tbnf =null;
    private ColumnGroup[] fechas = null;
    private TableColumnModel cm2 = null;
    private GroupableTableHeader header2 = null;
    private JScrollPane jScrollPane1;// =  new JScrollPane();
    private JViewport viewport = new JViewport();
    private int num = 0;
    private Timestamp fechai;
    private Timestamp fechaf;
    private int nautobuses=0;
    private int insertados = 0;
    private JDialog dbuses;
    private JList lista;
    private Object selected[];
    private JComboBox jcmbx_flotillas;
    private JDialog dh;

            
    private Vector nomCategorias;
    private String nombrerolmaestro;
    private int numdias;
    private Vector horRoles;
    private DefaultListModel modelolist;
    private TmsRolesManagedBean busquedas;
    private Vector vautobuses;
    private Vector vautobus;
    private boolean diaEncontrado = false;
    private int nsali = 0;
    private long diasduracion;
    private boolean enrolados = false;
    private boolean asignados = false;
    private boolean respuestaSN = true;  
    private Timestamp fecha_servidor;
    private SimpleDateFormat formatfc = new SimpleDateFormat ("dd/MM/yyyy");
    private SimpleDateFormat formathc = new SimpleDateFormat ("HH:mm");
    private Timestamp fci = null;
    private Timestamp fcf = null;
    private String sfechai;
    private String sfechaf;
    private long idrservicio;
    private Vector idCorridas;
    private Vector indiceCorridas;
    private long estadoAbiertaId = 1;
    private long estadoCerradaId = 1;
    private long plantillaDefaultId = 0;
    private Vector rutasId = new Vector();
    private Vector plantillaRuta = new Vector();
    private long usuarioId;
    private String idTerminal;
    private TmsFlotillasTbl flotillaSeleccionada;
    private String cdes;
    private String coris;
    private String cemp;
    private boolean rolguardado = false;
    private Vector autobusesAsignados;
    private Vector vrutes;
    private String rutcadena ="()";
    


    
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
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
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
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
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

    private long milis(String hora){
        long m= 0;
        String[] h = hora.split(":");
        if(h[0].equals("00") || h[0].equals("0"))
         m= Integer.parseInt(h[1]) * 60000;
        else 
          m = (Integer.parseInt(h[0]) * 3600000) + (Integer.parseInt(h[1]) * 60000);
        return m;
       }

    private void guardarRolMaestro() {
        if(rolguardado)
        {
            new jdlg_advertencia("El Rol Maestro ya esta guardado","","Rol Maestro Guardado").setVisible(true);
            return;
        }
        
        if(asignados)
        {
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de Guardar Rol Maestro", "¿Esta seguro que desea guardar el Rol Maestro?");
          psn.setVisible(true);
          if(respuestaSN)
          {
            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
           System.out.println("Se guarda el Rol Maestro: ");
           System.out.println("     Nombre    : "+nombrerolmaestro);
           System.out.println("     Servicio  : "+idrservicio);
           System.out.println("     Flotilla  : "+flotillaSeleccionada.getFlotillaId());
           System.out.println("     F. inicial: "+fechai);
           System.out.println("     F. final  : "+fechaf);
           System.out.println("\nSe guardan los Roles Base: ");
           TmsRolesMaestroTbl rolMaestro = new TmsRolesMaestroTbl();
           rolMaestro.setRolMaestroNombre(nombrerolmaestro);
           TmsServiciosTbl serv = busquedas.serviciosTblFacadeRemote.find(BigDecimal.valueOf(idrservicio));
           rolMaestro.setServicioId(serv);
           rolMaestro.setFlotillaId(flotillaSeleccionada);
           rolMaestro.setFechaInicial(new Date(fechai.getTime()));
           rolMaestro.setFechaFinal(new Date(fechaf.getTime()));
           rolMaestro.setHabilitado("S");
           rolMaestro.setCreadoPor(BigInteger.valueOf(usuarioId));
           rolMaestro.setFechaCreacion(new Date(fecha_servidor.getTime()));
           rolMaestro.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
           rolMaestro.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           TmsRolesMaestroTbl rolMaestroNuevo = busquedas.rolesMaestroTblFacadeRemote.create(rolMaestro);

         for(int col=0; col<num; col++)
            {
                 Vector rol = null;
                 String nombre = nomCategorias.get(col).toString();
                 System.out.println("Rol Base Categoria: "+nombre);
                 System.out.println("   No Autobuses: "+mFixed.getRowCount());
                 String lun = "N";String mar = "N";String mie = "N";String jue = "N";
                 String vie = "N";String sab = "N";String dom = "N";
                 if(nombre.indexOf("Lun")>=0)
                     lun = "S";
                 if(nombre.indexOf("Mar")>=0)
                     mar = "S";
                 if(nombre.indexOf("Mie")>=0)
                     mie = "S";
                 if(nombre.indexOf("Jue")>=0)
                     jue = "S";
                 if(nombre.indexOf("Vie")>=0)
                     vie = "S";
                 if(nombre.indexOf("Sab")>=0)
                     sab = "S";
                 if(nombre.indexOf("Dom")>=0)
                     dom = "S";
                 System.out.println("   Aplica Lunes    : "+lun);
                 System.out.println("   Aplica Martes   : "+mar);
                 System.out.println("   Aplica Miercoles: "+mie);
                 System.out.println("   Aplica Jueves   : "+jue);
                 System.out.println("   Aplica Viernes  : "+vie);
                 System.out.println("   Aplica Sabado   : "+sab);
                 System.out.println("   Aplica Domingo  : "+dom);
                   TmsRolesBaseTbl rolbase = new TmsRolesBaseTbl();
                   rolbase.setRolMaestroId(rolMaestroNuevo);
                   rolbase.setRolBaseCategoria(nombre);
                   rolbase.setCantidadAutobuses(BigInteger.valueOf(Long.valueOf(""+mFixed.getRowCount())));
                   rolbase.setAplicaLunes(lun);
                   rolbase.setAplicaMartes(mar);
                   rolbase.setAplicaMiercoles(mie);
                   rolbase.setAplicaJueves(jue);
                   rolbase.setAplicaViernes(vie);
                   rolbase.setAplicaSabado(sab);
                   rolbase.setAplicaDomingo(dom);
                   rolbase.setCreadoPor(BigInteger.valueOf(usuarioId));
                   rolbase.setFechaCreacion(new Date(fecha_servidor.getTime()));
                   rolbase.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                   rolbase.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                   TmsRolesBaseTbl rolbaseNuevo = busquedas.rolesBaseTblFacadeRemote.create(rolbase);
                 rol = (Vector) horRoles.get(col);
                 System.out.println("Rol: "+rol);
                 
                 jProgressBar1.setValue(0);
                 jProgressBar1.setValue( 0 );
                 jProgressBar1.setMinimum( 0 );
                 jProgressBar1.setMaximum(mFixed.getRowCount() - 1);

                 for(int ni =0; ni<mFixed.getRowCount(); ni++)
                 {
                     System.out.println("   Numero Cuadro        : "+mFixed.getValueAt(ni,0));
                    String bus = mTable.getValueAt(ni,col).toString();
                    Vector vdatosBus = (Vector)busquedas.variosFacadeRemote.buscarOperBusesporNumEcon(mTable.getValueAt(ni,col).toString());
                    Vector datosBus = (Vector)vdatosBus.get(0);
                    System.out.println("    Autobus              :"+BigInteger.valueOf(Long.valueOf(datosBus.get(0).toString())));
                     int nsali = 0;
                     boolean guardia = false;
                     System.out.println(" valor del renglon ****************** " +mFixed.getValueAt(ni,col + 1).toString());
                     TmsRolesBaseLineasTbl rolLinea = new TmsRolesBaseLineasTbl();
                     rolLinea.setRolBaseId(rolbaseNuevo);
                     TmsAutobusesTbl abus = busquedas.autobusesTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(datosBus.get(0).toString())));
                     rolLinea.setAutobusId(abus);
                     rolLinea.setNumeroCuadro(BigInteger.valueOf(Long.valueOf(""+mFixed.getValueAt(ni,0))));
                     rolLinea.setCuadroGuardia("N");
                     rolLinea.setCuadroQuedada("N");
                     rolLinea.setCuadroCondicionExtra("N");
                     if(mFixed.getValueAt(ni,col + 1).toString().trim().equals("Guardia"))
                     {
                         System.out.println("   cantidad de Salidas  : "+nsali);
                         System.out.println("   Guardia ");
                         guardia = true;
                         rolLinea.setCuadroGuardia("S");
                         rolLinea.setCreadoPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setFechaCreacion(new Date(fecha_servidor.getTime()));
                         rolLinea.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                         busquedas.rolesBaseLineasTblFacadeRemote.create(rolLinea);
                     }
                     if(!guardia)
                     {
                          Vector salidas = (Vector)rol.get(ni);
                          for(int i=1;i<salidas.size(); i++) 
                          {
                           if(!salidas.get(i).toString().equals(""))
                             {
                                    String scorrida = salidas.get(i).toString();
                                    //System.out.println("Salida = "+ scorrida);
                                    int index = indiceCorridas.indexOf(scorrida);
                                    BigDecimal oferId =BigDecimal.valueOf (Long.valueOf(idCorridas.get(index).toString()));
                                    TmsOfertasServicioTbl oferta = busquedas.variosFacadeRemote.findOferServ(oferId);
                                    StringTokenizer sc = new StringTokenizer(scorrida,"-");
                                    String vh = sc.nextToken();
                                    String vo = sc.nextToken();
                                    String vd = sc.nextToken();
                                    System.out.println("   Corrida No:  "+i);
                                    System.out.println("   Ruta  : "+oferta.getRutaId().getRutaId().toBigInteger());
                                    System.out.println("   Origen: "+vo);
                                    System.out.println("   Origen: "+vh);
                                    rolLinea.setCuadroGuardia("N");
                                    if(i==1){
                                     rolLinea.setRutaCorrida1Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida1Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida1(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida1Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==2){
                                     rolLinea.setRutaCorrida2Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida2Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida2(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida2Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==3){
                                     rolLinea.setRutaCorrida3Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida3Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida3(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida3Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==4){
                                     rolLinea.setRutaCorrida4Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida4Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida4(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida4Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==5){
                                     rolLinea.setRutaCorrida5Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida5Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida5(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida5Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==6){
                                     rolLinea.setRutaCorrida6Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida6Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida6(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida6Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==7){
                                     rolLinea.setRutaCorrida7Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida7Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida7(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida7Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==8){
                                     rolLinea.setRutaCorrida8Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida8Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida8(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida8Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==9){
                                     rolLinea.setRutaCorrida9Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida9Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida9(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida9Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==10){
                                     rolLinea.setRutaCorrida10Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida10Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida10(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida10Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==11){
                                     rolLinea.setRutaCorrida11Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida11Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida11(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida11Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==12){
                                     rolLinea.setRutaCorrida12Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida12Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida12(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida12Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==13){
                                     rolLinea.setRutaCorrida13Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida13Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida13(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida13Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==14){
                                     rolLinea.setRutaCorrida14Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida14Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida14(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida14Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==15){
                                     rolLinea.setRutaCorrida15Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida15Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida15(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida15Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==16){
                                     rolLinea.setRutaCorrida16Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida16Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida16(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida16Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==17){
                                     rolLinea.setRutaCorrida17Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida17Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida17(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida17Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==18){
                                     rolLinea.setRutaCorrida18Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida18Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida18(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida18Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==19){
                                     rolLinea.setRutaCorrida19Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida19Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida19(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida19Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==20){
                                     rolLinea.setRutaCorrida20Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida20Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida20(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida20Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==21){
                                     rolLinea.setRutaCorrida21Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida21Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida21(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida21Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==22){
                                     rolLinea.setRutaCorrida22Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida22Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida22(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida22Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==23){
                                     rolLinea.setRutaCorrida23Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida23Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida23(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida23Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==24){
                                     rolLinea.setRutaCorrida24Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida24Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida24(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida24Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                                    if(i==25){
                                     rolLinea.setRutaCorrida25Id(oferta.getRutaId().getRutaId().toBigInteger());  
                                     Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                                     rolLinea.setOrigenCorrida25Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                                     Timestamp fhc = null;
                                     fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                                     rolLinea.setHorarioCorrida25(new Date(fhc.getTime()));
                                     rolLinea.setEmpresaCorrida25Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                                    }
                               System.out.println("     "+salidas.get(i).toString());
                               nsali++;
                             }  
                          }
                           System.out.println("   cantidad de Salidas  : "+nsali);
                                     rolLinea.setCantidadSalidas(BigInteger.valueOf(Long.valueOf(""+nsali)));
                                     rolLinea.setCreadoPor(BigInteger.valueOf(usuarioId));
                                     rolLinea.setFechaCreacion(new Date(fecha_servidor.getTime()));
                                     rolLinea.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                                     rolLinea.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                                     busquedas.rolesBaseLineasTblFacadeRemote.create(rolLinea);
                     }
                      jProgressBar1.setValue(ni);
                      jProgressBar1.setStringPainted(true);
                      jProgressBar1.paint(jProgressBar1.getGraphics());   
                 }
            }
           new jdlg_informacion("¡Se guardo satisfactoriamente el Rol Maestro! ",""," Rol MAestro Guardado").setVisible(true);
           rolguardado = true;
            jProgressBar1.setValue( 0 );
            jProgressBar1.setMinimum( 0 );
            jProgressBar1.setMaximum(0);
           
          }
        }
        else
            new jdlg_error("¡Los autobuses aun no estan asignadps! "," No se puede guardar el Rol Maestro","Autobuses no asignados").setVisible(true);
    }
 
class jdlg_fechasGenerarCorridasDentro extends javax.swing.JDialog {
    
    /** Creates new form jdlg_fechasGenerarCorridas */
    public jdlg_fechasGenerarCorridasDentro(){
        this.setTitle("Fechas para la Generacion de Corridas");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
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
            this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        jtbl_rutas.setFocusTraversalKeysEnabled(false);
        jtbl_rutas.setBackground(new java.awt.Color(238, 238, 239));
        jtbl_rutas.setGridColor(new java.awt.Color(238, 238, 239));
        jtbl_rutas.setCellSelectionEnabled(false);
        jtbl_rutas.setModel(modeloRutas);
        
        jtxt_fechaicorridas.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechaicorridas_inicialKeyPressed(evt);
            }
        });
        jtxt_fechafcorridas.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechafcorridas_inicialKeyPressed(evt);
            }
        });
            System.out.println("Las Rutas son: "+vrutes);
            rutcadena = vrutes.toString();
            rutcadena = rutcadena.replace('[','(');
            rutcadena = rutcadena.replace(']',')');
            rutcadena = rutcadena.trim();
            Vector infoRutas = (Vector)busquedas.variosFacadeRemote.buscarDatosRutas(rutcadena);
            Object[][] datosInfo = new Object[infoRutas.size()][5];
            for(int i=0; i<infoRutas.size();i++)
            {
                Vector unaInfo= (Vector)infoRutas.get(i);
                datosInfo[i][0] = unaInfo.get(0).toString();
                datosInfo[i][1] = unaInfo.get(1).toString();
                datosInfo[i][2] = unaInfo.get(2).toString();
                datosInfo[i][3] = (boolean)true;
            }
            modeloRutas.setDataVector(datosInfo,encabezadosRutas);
            System.out.println("Encabezado rutas: "+encabezadosRutas);
            System.out.println("Info rutas: "+infoRutas);
        resizeColumnasrutes();
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE: </font> Cancelar | <font color=FF0000>ENTER: </font> Aceptar </html>");
        jtxt_fechaicorridas.dateEditor.setFoco();
    }
    
    private void jtxt_fechaicorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() ==  evt.VK_LEFT)
         //jcbx_abiertas.requestFocus();
            
        if(evt.getKeyCode() ==  evt.VK_RIGHT){
                jtxt_fechafcorridas.getDateEditor().setSeleccionaTexto();
                jtxt_fechafcorridas.requestFocus();
        }

        
        if(evt.getKeyCode() == evt.VK_ENTER)
            verificar();
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    
    private void jtxt_fechafcorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() ==  evt.VK_LEFT)
        {
                jtxt_fechaicorridas.getDateEditor().setSeleccionaTexto();
                jtxt_fechaicorridas.requestFocus();
        }            

        if(evt.getKeyCode() ==  evt.VK_RIGHT){
           // jcbx_abiertas.requestFocus();
            
        }
        if(evt.getKeyCode() == evt.VK_ENTER)
            verificar();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
 
    private void jtbl_rutasKeyPressed(java.awt.event.KeyEvent evt) {                                      
     if(evt.getKeyCode() == evt.VK_ENTER) {
            verificar();
        }
        
        if(evt.getKeyCode() ==  evt.VK_LEFT)
        {
                jtxt_fechafcorridas.getDateEditor().setSeleccionaTexto();
                jtxt_fechafcorridas.requestFocus();
        }            

        if(evt.getKeyCode() ==  evt.VK_RIGHT){
                jtxt_fechaicorridas.getDateEditor().setSeleccionaTexto();
                jtxt_fechaicorridas.requestFocus();
            
        }
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();    
    }                                     
        
    

 private void verificar(){
          if(jtxt_fechafcorridas.getDateEditor().getTexto().length()<10 || jtxt_fechaicorridas.getDateEditor().getTexto().length()<10)
           JOptionPane.showMessageDialog(this,"¡Debes introducir fechas validas!","Error de formato de fecha",JOptionPane.ERROR_MESSAGE);   
          else
          {
            sfechai = formatfc.format(fechai.getTime());              
            sfechaf = formatfc.format(fechaf.getTime());// jtxt_fechaenrolamiento.getDateEditor().getTexto();
            StringTokenizer tokenfic = new StringTokenizer(sfechai,"/");
            String dic= tokenfic.nextToken();
            String mic= tokenfic.nextToken();
            String aic= tokenfic.nextToken();
            StringTokenizer tokenffc = new StringTokenizer(sfechaf,"/");  
            String dfc= tokenffc.nextToken();
            String mfc= tokenffc.nextToken(); 
            String afc= tokenffc.nextToken();        
            fci = null;
            fci = fci.valueOf(aic+"-"+mic+"-"+dic+" 00:00:00");
            fcf = null;
            fcf = fcf.valueOf(afc+"-"+mfc+"-"+dfc+" 00:00:00");
           String sfcorridasi = jtxt_fechaicorridas.getDateEditor().getTexto();
           String sfcorridasf = jtxt_fechafcorridas.getDateEditor().getTexto();
           StringTokenizer tokenfi = new StringTokenizer(sfcorridasi,"/");  
           String di= tokenfi.nextToken();
           String mi= tokenfi.nextToken(); 
           String ai= tokenfi.nextToken();        
           StringTokenizer tokenff = new StringTokenizer(sfcorridasf,"/");  
           String df= tokenff.nextToken();
           String mf= tokenff.nextToken(); 
           String af= tokenff.nextToken();        
           Timestamp fcorridasi = null;
           fcorridasi = fcorridasi.valueOf(ai+"-"+mi+"-"+di+" 00:00:00");
           Timestamp fcorridasf = null;
           fcorridasf = fcorridasf.valueOf(af+"-"+mf+"-"+df+" 00:00:00");
           //List<TmsFechahoraV> lfecha = Rolesbase.queryTmsFechahoraVFindAll();
            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
           //fecha_servidor = lfecha.get(0).getFechahorasys();
           String sfhoy = formatfc.format(fecha_servidor.getTime());
           StringTokenizer tokenfh = new StringTokenizer(sfhoy,"/");  
           String dh= tokenfh.nextToken();
           String mh= tokenfh.nextToken(); 
           String ah= tokenfh.nextToken();    
           Timestamp fh = null;
           
           fh = fh.valueOf(ah+"-"+mh+"-"+dh+" 00:00:00");
           if(fcorridasi.getTime() < fci.getTime() || fcorridasi.getTime() > fcf.getTime() || fcorridasi.getTime()<fh.getTime())
            JOptionPane.showMessageDialog(this,"¡La fecha inicial de generacion de corridas debe ser mayor o \nigual a la fecha de inicio de enrolamiento o a la fecha actual y \nmenor o igual a la fecha final de enrolamiento!","Error de formato de fecha",JOptionPane.ERROR_MESSAGE);   
           else
           {
            if(fcorridasf.getTime() < fcorridasi.getTime() || fcorridasf.getTime() > fcf.getTime())
             JOptionPane.showMessageDialog(this,"¡La fecha final de generacion de corridas debe ser \nmayor o igual a la fecha de inicio de generacion decorridas y\n menor o igual a la fecha final de enrolamiento!","Error de formato de fecha",JOptionPane.ERROR_MESSAGE);   
            else
            {
                this.dispose();
                boolean selected= false;
                Vector idRutasInfo = new Vector();
                for(int i=0; i<jtbl_rutas.getRowCount(); i++)
                    if(jtbl_rutas.getValueAt(i,3).toString().equals("true"))
                     idRutasInfo.add(jtbl_rutas.getValueAt(i,0).toString());
                System.out.println("Rutas Abiertas: "+idRutasInfo);
                generarCorridas(jtxt_fechaicorridas.getDateEditor().getTexto(), jtxt_fechafcorridas.getDateEditor().getTexto(), fci, fcf,idRutasInfo);
            }
           }
            
         }        
    }

    private void resizeColumnasrutes(){
        TableColumn columinv = jtbl_rutas.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        columinv = jtbl_rutas.getColumnModel().getColumn(1); columinv.setMinWidth(90);columinv.setMaxWidth(90);columinv.setPreferredWidth(90);
        columinv = jtbl_rutas.getColumnModel().getColumn(2); columinv.setMinWidth(190);columinv.setMaxWidth(190);columinv.setPreferredWidth(190);
        columinv = jtbl_rutas.getColumnModel().getColumn(3); columinv.setMinWidth(60);columinv.setMaxWidth(60);columinv.setPreferredWidth(60);
      }      
    
    
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_fechaicorridas = new tms_calendario.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jtxt_fechafcorridas = new tms_calendario.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_rutas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas de Generaci\u00f3n de Corridas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Fecha desde:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("hasta:");

        jtbl_rutas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_rutas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_rutasKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_rutas);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Rutas");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_fechaicorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(jLabel2)
                .add(20, 20, 20)
                .add(jtxt_fechafcorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(21, 21, 21)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 337, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .add(jLabel3)
                .add(280, 280, 280))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(53, 53, 53)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jtxt_fechafcorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel1)
                                    .add(jtxt_fechaicorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel3");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>     
    

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_rutas;
    private tms_calendario.JDateChooser jtxt_fechafcorridas;
    private tms_calendario.JDateChooser jtxt_fechaicorridas;
    private Object[] encabezadosRutas = {"ruta_id","Numero","Nombre","Abierta"};
    private DefaultTableModel modeloRutas = new DefaultTableModel(null,encabezadosRutas){
            public boolean isCellEditable(int row, int column) {
            if (column == 3  )
               return true;
            return false;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };
    // End of variables declaration                   

    
}

}
