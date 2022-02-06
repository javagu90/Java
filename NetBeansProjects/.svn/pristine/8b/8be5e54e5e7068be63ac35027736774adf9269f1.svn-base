/*
 * jpnl_RolCategoria2.java
 *
 * Created on 18 de noviembre de 2007, 10:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import tmsroles.entidad.TmsAutobusesTbl;
import tmsroles.entidad.TmsFlotillasTbl;
import tmsroles.entidad.TmsOfertasServicioTbl;
import tmsroles.entidad.TmsRolesBaseLineasTbl;
import tmsroles.entidad.TmsRolesBaseTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author  vgonzalez
 */
public class jpnl_RolCategoria2 extends JPanel {//javax.swing.JDialog {

   // private TmsRolesManagedBean busquedas;
    private long idrservicio;
    private long usuarioId;
    private Vector excluir;
    private Vector vHorarios;
    private boolean haycorridas = false;    
    private int nhor =0;
    private String[][] datos;
    private int sobranHorarios=0;
    private Vector indiceCorridas = new Vector();
    private Vector idCorridas =  new Vector();
    private Vector listos= new Vector();
    private int itsRow =300;
    private int itsColumn = 300;
    private int itsRowSelect =300;
    private int itsColumnSelect = 300;
    private boolean arrastrar = false;
    private int columnaorigen = 100; 
    private int renglonorigen = 100;
    private int columnadestino = 100; 
    private int renglondestino = 100;
    private String valororigen = "";
    private String valordestino = "";
    private boolean respuestaSN = true;   
    private int numcat = 0;
    private jdlg_RolesManual principal;
    private Vector horariosRutas;
    private TmsRolesManagedBean busquedas;
    private int insertados;
    private DefaultListModel modelolist;
    private int nautobuses;
    private TmsFlotillasTbl flotillaSeleccionada;
    private JDialog dbuses;
    private boolean asignados = false;
    private JComboBox jcmbx_flotillas;
    private Object selected[];
    private JList lista;
    private Timestamp fecha_servidor;
    private Vector vautobuses;
    private Vector vautobus;
    private Vector vOferModif = new Vector();
    private boolean horarioEliminado = true;
    private String nomcat;
    
    /**
     * Creates new form jpnl_RolCategoria2
     */
    public jpnl_RolCategoria2(TmsRolesManagedBean pbusquedas, Vector phorariosRutas, jdlg_RolesManual ppricipal,int pnumcat,Vector pindiceCorridas, Vector pidCorridas, TmsFlotillasTbl pflotillaSeleccionada, String pnomcat){ 
        initComponents();
        this.horariosRutas  = phorariosRutas;
        this.numcat = pnumcat;
        this.nomcat = pnomcat;
        this.principal = ppricipal;
        this.indiceCorridas = pindiceCorridas;
        this.idCorridas = pidCorridas;
        this.busquedas = pbusquedas;
        this.flotillaSeleccionada = pflotillaSeleccionada;
        System.out.println("Numero de categoia: "+numcat);
        jtbl_rolmanual.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        Object[][] renglon = new Object[1][27];
        renglon[0][0] = "";renglon[0][1] = "1";renglon[0][2] = "";renglon[0][3] = "";renglon[0][4] = "";renglon[0][5] = "";
        renglon[0][6] = "";renglon[0][7] = "";renglon[0][8] = "";renglon[0][9] = "";renglon[0][10] = "";renglon[0][11] = "";
        renglon[0][12] = "";renglon[0][13] = "";renglon[0][14] = "";renglon[0][15] = "";renglon[0][16] = "";renglon[0][17] = "";
        renglon[0][18] = "";renglon[0][19] = "";renglon[0][20] = "";renglon[0][21] = "";renglon[0][22] = "";renglon[0][23] = "";
        renglon[0][24] = "";renglon[0][25] = "";renglon[0][26] = "";
        modeloRolManual.setDataVector(renglon,encabezadoRolManual);
        jtbl_rolmanual.setModel(modeloRolManual);
        jtbl_rolmanual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist_horarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist_guardias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeloguardia.addElement("Guardias");
        jlist_guardias.setModel(modeloguardia);
//        Vector horariosRutas = (Vector)vhorCategorias.get(0);
        System.out.println("hay "+horariosRutas.size()+" guardados en el subrol"+nomcat);
        sobranHorarios = horariosRutas.size();
        for(int i=0; i<horariosRutas.size(); i++)
            modelolistahorarios.addElement(horariosRutas.get(i).toString());
        resizeColumnas();
       jtbl_rolmanual.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
       jtbl_rolmanual.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
        
        jlist_horarios.setModel(modelolistahorarios);
        MyMouseAdapter aMouseAda = new MyMouseAdapter();
        jtbl_rolmanual.addMouseMotionListener(aMouseAda);
        jtbl_rolmanual.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        jtbl_rolmanual_mousePressed(e);
                    }

                    public void mouseReleased(MouseEvent e) {
                        jtbl_rolmanual_mouseReleased(e);
                    }

                    public void mouseClicked(MouseEvent e) {
                        jtbl_rolmanual_mouseClicked(e);
                    }
                });
//        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Rol Manual | <font color=FF0000>ENTER/DobleClick</font> Agregar Horario | <font color=FF0000> F2</font> Agregar Dia | <font color=FF0000>F3</font> Eliminar dia de Guardia | <font color=FF0000>Mouse</font> Intercambiar Horarios  | <font color=FF0000>F10</font> Guardar Rol</html>");                
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Rol Manual | <font color=FF0000> F2</font> Agregar Dia | <font color=FF0000>F3</font> Eliminar dia Vacio o de Guardia | <font color=FF0000> F5</font> Asignar Autobuses <font color=FF0000>Mouse</font> Intercambiar Horarios  | <font color=FF0000>F10</font> Guardar Rol  | <font color=FF0000>F11</font> Ver enrolamiento</html>");                
    }
    
    private void resizeColumnas(){
        TableColumn columinv;
        if(numcat>0)
        {
            columinv = jtbl_rolmanual.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        }
        else
        {
            columinv = jtbl_rolmanual.getColumnModel().getColumn(0); columinv.setMinWidth( 60 );columinv.setMaxWidth( 60 );columinv.setPreferredWidth(60);
        }
        columinv = jtbl_rolmanual.getColumnModel().getColumn(1);columinv.setMinWidth( 60 );columinv.setMaxWidth( 60 );columinv.setPreferredWidth(60);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(2);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(3);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(4);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(5);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(6);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(7);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(8);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(9);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(10);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(11);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(12);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(13);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(14);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(15);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(16);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(17);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(18);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(19);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(20);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(21);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(22);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(23);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(24);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(25);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
        columinv = jtbl_rolmanual.getColumnModel().getColumn(26);columinv.setMinWidth( 100 );columinv.setMaxWidth( 100 );columinv.setPreferredWidth(100);        
      }    

   
    
    private void jtbl_rolmanual_mousePressed(MouseEvent e) {
        int fila = jtbl_rolmanual.rowAtPoint(e.getPoint());
        int columna = jtbl_rolmanual.columnAtPoint(e.getPoint());
         itsRowSelect = jtbl_rolmanual.rowAtPoint(e.getPoint());
         itsColumnSelect = jtbl_rolmanual.columnAtPoint(e.getPoint());
         jtbl_rolmanual.repaint();
    //System.out.println("Presiona en  ==> columna="+ jtbl_rolmanual.getSelectedColumn()+"-"+columna+" Renglon="+ jtbl_rolmanual.getSelectedRow()+"-"+fila);
    if(jtbl_rolmanual.getSelectedColumn()<=1)
    {
            new jdlg_advertencia("¡El contenido de esta celda no se puede mover!","","Valor fijo").setVisible(true);
            return;
    }
      columnaorigen = jtbl_rolmanual.getSelectedColumn();
    renglonorigen = jtbl_rolmanual.getSelectedRow();
    if(jtbl_rolmanual.getValueAt(renglonorigen, columnaorigen)==null || ((String)jtbl_rolmanual.getValueAt(renglonorigen, columnaorigen)).equals(""))
     {arrastrar=false;valororigen="";}
    else
       {
           arrastrar = true;
           valororigen = ""+jtbl_rolmanual.getValueAt(renglonorigen, columnaorigen);
       }
    }

    private void jtbl_rolmanual_mouseReleased(MouseEvent e){
       if(valororigen.equals(""))
        {valororigen="";}
       else
       {
        //System.out.println("Suelta en  ==> columna="+ jtbl_rolmanual.getSelectedColumn()+" Renglon="+ jtbl_rolmanual.getSelectedRow());
         if(jtbl_rolmanual.getSelectedColumn()<=1)
          {
                new jdlg_advertencia("¡El contenido de esta celda no se puede reemplazar!","","Valor fijo").setVisible(true);
                return;
          }
        
        columnadestino = jtbl_rolmanual.getSelectedColumn();
        renglondestino = jtbl_rolmanual.getSelectedRow();
         itsRowSelect = jtbl_rolmanual.getSelectedRow();
         itsColumnSelect = jtbl_rolmanual.getSelectedColumn();
         jtbl_rolmanual.repaint();
        if(true)//((String)jtbl_rolmanual.getColumnName(columnaorigen) ).equals(((String)jtbl_rolmanual.getColumnName(columnadestino))))
        {
        if(columnadestino==columnaorigen && renglondestino==renglonorigen) 
             valororigen="";
        else
         {
          if(jtbl_rolmanual.getValueAt(renglonorigen, columnaorigen).toString().equals("Guardia"))
          {
              new jdlg_advertencia("¡No puedes intercambiar las guardias!","","Dia de Guardia").setVisible(true);
              return;
          }
          if(jtbl_rolmanual.getValueAt(renglondestino, columnadestino)==null || ((String)jtbl_rolmanual.getValueAt(renglondestino, columnadestino)).equals(""))
             {
               //System.out.println("Esta vacia la celda");
                 jtbl_rolmanual.setValueAt(valororigen,renglondestino,columnadestino); 
                 jtbl_rolmanual.setValueAt("",renglonorigen,columnaorigen); 
                 int index = vOferModif.indexOf(""+renglonorigen+","+columnaorigen);
                 if(index>=0)
                 {
                     vOferModif.remove(index);
                     vOferModif.add(""+renglondestino+","+columnadestino);
                 }
                 valororigen = "";
             }
          else
             {
              //System.out.println("Esta llena la celda");
              valordestino=(String)jtbl_rolmanual.getValueAt(renglondestino,columnadestino); 
              if(valordestino.equals("Guardia"))
               {
                   new jdlg_advertencia("¡No puedes intercambiar las guardias!","","Dia de Guardia").setVisible(true);
                   return;
               }

                    jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Casilla con datos", "Esta casilla tiene un horario. ¿Desea intercambiar los datos?");
                    psn.setVisible(true);
                    if(respuestaSN)
                     {
                         jtbl_rolmanual.setValueAt(valororigen,renglondestino,columnadestino); 
                         jtbl_rolmanual.setValueAt(valordestino,renglonorigen,columnaorigen); 
                         System.out.println("Busca Origen => "+renglonorigen+","+columnaorigen);
                         System.out.println("En => "+vOferModif);
                         int index = vOferModif.indexOf(""+renglonorigen+","+columnaorigen);
                         if(index>=0)
                         {
                             vOferModif.remove(index);
                             vOferModif.add(""+renglondestino+","+columnadestino);
                         }
                         else
                         {
                             System.out.println("Busca Destino => "+renglondestino+","+columnadestino);
                             System.out.println("En => "+vOferModif);
                             int indexd = vOferModif.indexOf(""+renglondestino+","+columnadestino);
                             if(indexd>=0)
                             {
                                 vOferModif.remove(indexd);
                                 vOferModif.add(""+renglonorigen+","+columnaorigen);
                             }
                         }
                         
                         valororigen = "";
                         valordestino = "";
                      }//Si "reemplazar datos"
                     else 
                      {
                       valororigen ="";
                       valordestino = "";
                      }//No "reemplazar datos"
                  //  this.setAlwaysOnTop(true);
              }
            }//else("es la misma celda")
         }//if("La misma columna")
         else
         { 
//          this.setAlwaysOnTop(false);
          JOptionPane.showMessageDialog(this,"Solo se pueden intercambiar horarios entre\n                 Origen o Destino","¡Diferentes columnas!", 2); 
 //         this.setAlwaysOnTop(true);
         }
         
       }//else("")
        itsRow =3000;
        itsColumn = 300;

     }

    private void jtbl_rolmanual_mouseClicked(MouseEvent e) {
         itsRow = jtbl_rolmanual.rowAtPoint(e.getPoint());
         itsColumn = jtbl_rolmanual.columnAtPoint(e.getPoint());
         itsRowSelect = jtbl_rolmanual.rowAtPoint(e.getPoint());
         itsColumnSelect = jtbl_rolmanual.columnAtPoint(e.getPoint());
         jtbl_rolmanual.repaint();
         
    }    
    
    public boolean cargarRolBaseBD(TmsRolesMaestroTbl rm, String string) {
        SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
        String nombreRol = string;
        boolean estado = true;
        setHorarioEliminado(true);
        List<TmsRolesBaseTbl> listaRolBase = busquedas.rolesBaseTblFacadeRemote.buscaRolBase(nombreRol,rm.getRolMaestroId().longValue());
        if(listaRolBase.size()==0)
            estado = false;
        else
        {
            System.out.println("El nombre del rol a cargar es: "+nombreRol);
            TmsRolesBaseTbl rolBase = listaRolBase.get(0);
              List<TmsRolesBaseLineasTbl> listalineas = busquedas.rolesBaseLineasTblFacadeRemote.buscaLineasPorRolId(rolBase.getRolBaseId().longValue());
              Object[][] datos = new Object[listalineas.size()][28];
//              Object[][] datos = new Object[rolBase.getTmsRolesBaseLineasTblCollection().size()][28];
//               Collection<TmsRolesBaseLineasTbl> coleccion = rolBase.getTmsRolesBaseLineasTblCollection();
//               Iterator iprs = coleccion.iterator();
               int renglon = 0; 
               int index =0;
               //while(iprs.hasNext()){
                for(int i=0; i<listalineas.size();i++){
                   //TmsRolesBaseLineasTbl linea = (TmsRolesBaseLineasTbl)iprs.next();
                   TmsRolesBaseLineasTbl linea = (TmsRolesBaseLineasTbl)listalineas.get(i);
                   datos[renglon][0] = ""+linea.getAutobusId().getNumeroEconomico();
                   datos[renglon][1] = ""+linea.getNumeroCuadro().longValue();      
                   if(linea.getCuadroGuardia().equals("S"))
                   {
                       datos[renglon][2] = "Guardia";datos[renglon][3] = "Guardia";datos[renglon][4] = "Guardia";datos[renglon][5] = "Guardia";datos[renglon][26] = "Guardia";
                       datos[renglon][6] = "Guardia";datos[renglon][7] = "Guardia";datos[renglon][8] = "Guardia";datos[renglon][9] = "Guardia";datos[renglon][10] = "Guardia";
                       datos[renglon][11] = "Guardia";datos[renglon][12] = "Guardia";datos[renglon][13] = "Guardia";datos[renglon][14] = "Guardia";datos[renglon][15] = "Guardia";
                       datos[renglon][16] = "Guardia";datos[renglon][17] = "Guardia";datos[renglon][18] = "Guardia";datos[renglon][19] = "Guardia";datos[renglon][20] = "Guardia";
                       datos[renglon][21] = "Guardia";datos[renglon][22] = "Guardia";datos[renglon][23] = "Guardia";datos[renglon][24] = "Guardia";datos[renglon][25] = "Guardia";
                   }  
                   else
                   {   
                       if(linea.getOfertaCorrida1Id()==null)
                         datos[renglon][2] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida1Id().longValue());
                        if(index<0)
                        {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida1Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][2] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida1Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida1()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",2");
                        }
                       }
                       
                       if(linea.getOfertaCorrida2Id()==null)
                         datos[renglon][3] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida2Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida2Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][3] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida2Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida2()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",3");
                        }
                       }

                       if(linea.getOfertaCorrida3Id()==null)
                         datos[renglon][4] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida3Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida3Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][4] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida3Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida3()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",4");
                        }
                       }

                       if(linea.getOfertaCorrida4Id()==null)
                         datos[renglon][5] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida4Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida4Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][5] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida4Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida4()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",5");
                        }
                       }

                       if(linea.getOfertaCorrida5Id()==null)
                         datos[renglon][6] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida5Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida5Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][6] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida5Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida5()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",6");
                        }
                       }

                       if(linea.getOfertaCorrida6Id()==null)
                         datos[renglon][7] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida6Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida6Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][7] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida6Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida6()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",7");
                        }
                       }

                       if(linea.getOfertaCorrida7Id()==null)
                         datos[renglon][8] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida7Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida7Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][8] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida7Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida7()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",8");
                        }
                       }

                       if(linea.getOfertaCorrida8Id()==null)
                         datos[renglon][9] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida8Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida8Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][9] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida8Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida8()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",9");
                        }
                       }

                       if(linea.getOfertaCorrida9Id()==null)
                         datos[renglon][10] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida9Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida9Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][10] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida9Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida9()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",10");
                        }
                       }

                       if(linea.getOfertaCorrida10Id()==null)
                         datos[renglon][11] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida10Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida10Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][11] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida10Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida10()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",11");
                        }
                       }

                       if(linea.getOfertaCorrida11Id()==null)
                         datos[renglon][12] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida11Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida11Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][12] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida11Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida11()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",12");
                        }
                       }

                       if(linea.getOfertaCorrida12Id()==null)
                         datos[renglon][13] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida12Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida12Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][13] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida12Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida12()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",13");
                        }
                       }

                       if(linea.getOfertaCorrida13Id()==null)
                         datos[renglon][14] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida13Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida13Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][14] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida13Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida13()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",14");
                        }
                       }

                       if(linea.getOfertaCorrida14Id()==null)
                         datos[renglon][15] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida14Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida14Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][15] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida14Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida14()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",15");
                        }
                       }

                       if(linea.getOfertaCorrida15Id()==null)
                         datos[renglon][16] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida15Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida15Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][16] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida15Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida15()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",16");
                        }
                       }

                       if(linea.getOfertaCorrida16Id()==null)
                         datos[renglon][17] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida16Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida16Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][17] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida16Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida16()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",17");
                        }
                       }

                       if(linea.getOfertaCorrida17Id()==null)
                         datos[renglon][18] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida17Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida17Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][18] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida17Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida17()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",18");
                        }
                       }

                       if(linea.getOfertaCorrida18Id()==null)
                         datos[renglon][19] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida18Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida18Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][19] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida18Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida18()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",19");
                        }
                       }

                       if(linea.getOfertaCorrida19Id()==null)
                         datos[renglon][20] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida19Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida19Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][20] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida19Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida19()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",20");
                        }
                       }

                       if(linea.getOfertaCorrida20Id()==null)
                         datos[renglon][21] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida20Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida20Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][21] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida20Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida20()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",21");
                        }
                       }

                       if(linea.getOfertaCorrida21Id()==null)
                         datos[renglon][22] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida21Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida21Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][22] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida21Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida21()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",22");
                        }
                       }

                       if(linea.getOfertaCorrida22Id()==null)
                         datos[renglon][23] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida22Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida22Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][23] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida22Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida22()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",23");
                        }
                       }

                       if(linea.getOfertaCorrida23Id()==null)
                         datos[renglon][24] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida23Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida23Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {
                        datos[renglon][24] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida23Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida23()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",24");
                        }
                       }

                       if(linea.getOfertaCorrida24Id()==null)
                         datos[renglon][25] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida24Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida24Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {

                        datos[renglon][25] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida24Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida24()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",25");
                        }
                       }

                       if(linea.getOfertaCorrida25Id()==null)
                         datos[renglon][26] = "";  
                       else
                       {  
                        index = idCorridas.indexOf(""+linea.getOfertaCorrida25Id().longValue());
                        if(index<0)
                                                    {
                            setHorarioEliminado(false);
                            return false;
                        }
                        String diasOferta = obtenerDiasOferta(linea.getOfertaCorrida25Id().longValue());
                        if(!diasOferta.equals(nombreRol))
                        {
                            modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                            datos[renglon][2] = ""; 
                        }
                        else
                        {

                        datos[renglon][26] = indiceCorridas.get(index);
                        modelolistahorarios.removeElement(indiceCorridas.get(index).toString());
                        if(sobranHorarios==0){
                            setHorarioEliminado(false);
                            return false;}
                        sobranHorarios--;

                        Vector vNorigen =(Vector)busquedas.variosFacadeRemote.buscaEdoPorId(linea.getOrigenCorrida25Id().longValue());
                        String norigen = (String)vNorigen.get(0);
                        String hora = formath.format(linea.getHorarioCorrida25()); 
                        if(indiceCorridas.get(index).toString().indexOf(norigen)==-1 || indiceCorridas.get(index).toString().indexOf(hora)==-1)
                           vOferModif.add(""+renglon+",26");
                        }
                       }
                   }//elseif(linea.getCuadroGuardia())         
                    renglon++;
                }//while
                modeloRolManual.setDataVector(datos,encabezadoRolManual);
                resizeColumnas();
        }
        return estado;
    }    
    
    private String obtenerDiasOferta(long ofertaId){
        String dias = "";
        Vector vDias =(Vector)busquedas.variosFacadeRemote.buscaDiasOfertaPorId(ofertaId);
           // System.out.println("Los dias de la oferta son: "+vDias);
        if(vDias.get(0).toString().equals("S"))
           dias = "Lun";
        if(vDias.get(1).toString().equals("S"))
           dias = dias+ "-Mar";
        if(vDias.get(2).toString().equals("S"))
           dias = dias+ "-Mie";
        if(vDias.get(3).toString().equals("S"))
           dias = dias+ "-Jue";
        if(vDias.get(4).toString().equals("S"))
           dias = dias+ "-Vie";
        if(vDias.get(5).toString().equals("S"))
           dias = dias+ "-Sab";
        if(vDias.get(6).toString().equals("S"))
           dias = dias+ "-Dom";
        //System.out.println("dias.substring(0,0)=>"+dias.substring(0,1));
        if(dias.substring(0,1).equals("-"))
              dias = dias.substring(1);
       //System.out.println("El nombre de los dias de la oferta son: "+dias);     
       return dias;
    }
    
    public void guardarBD(TmsRolesMaestroTbl prm, String pnombre){
        TmsRolesMaestroTbl rm = prm;
        System.out.println("Se guarda el rol "+numcat);
            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                 String nombre = pnombre;
                 System.out.println("Rol Base Categoria: "+nombre);
                 System.out.println("   No Autobuses: "+jtbl_rolmanual.getRowCount());
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
                   rolbase.setRolMaestroId(rm);
                   rolbase.setRolBaseCategoria(nombre);
                   rolbase.setCantidadAutobuses(BigInteger.valueOf(Long.valueOf(""+jtbl_rolmanual.getRowCount())));
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
              for(int i=0; i<jtbl_rolmanual.getRowCount(); i++){  
                     TmsRolesBaseLineasTbl rolLinea = new TmsRolesBaseLineasTbl();
                     rolLinea.setRolBaseId(rolbaseNuevo);
                     System.out.println(i+"- Busca el autobus "+jtbl_rolmanual.getValueAt(i,0).toString());
                     List<TmsAutobusesTbl> listaAbus = busquedas.autobusesTblFacadeRemote.buscaBusPorNumeroEconomico(jtbl_rolmanual.getValueAt(i,0).toString());
                     TmsAutobusesTbl abus = listaAbus.get(0);
                     rolLinea.setAutobusId(abus);
                     rolLinea.setNumeroCuadro(BigInteger.valueOf(Long.valueOf(jtbl_rolmanual.getValueAt(i,1).toString())));
                     rolLinea.setCuadroGuardia("N");
                     rolLinea.setCuadroQuedada("N");
                     rolLinea.setCuadroCondicionExtra("N");
                     int nsali =0;
                     boolean guardia = false;
                  for(int j=2; j<jtbl_rolmanual.getColumnCount();j++)
                  {
                     
                     if(jtbl_rolmanual.getValueAt(i,j).toString().trim().equals("Guardia"))
                     {
                         rolLinea.setCantidadSalidas(BigInteger.valueOf(Long.valueOf(""+0)));
                         rolLinea.setCuadroGuardia("S");
                         rolLinea.setCreadoPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setFechaCreacion(new Date(fecha_servidor.getTime()));
                         rolLinea.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                         busquedas.rolesBaseLineasTblFacadeRemote.create(rolLinea);
                         guardia = true;
                         break;
                     }
                     else
                        guardia = false;   
                    boolean vacio = false;
                    if(jtbl_rolmanual.getValueAt(i,j).toString().equals(""))
                        vacio = true;
                    else
                        nsali++;
                    if(!vacio)
                    {
                     if(!guardia)
                     {
                         String scorrida = jtbl_rolmanual.getValueAt(i,j).toString();
                         System.out.println("   Salida:  "+scorrida);
                         int index = indiceCorridas.indexOf(scorrida);
                         BigDecimal oferId =BigDecimal.valueOf (Long.valueOf(idCorridas.get(index).toString()));
                         TmsOfertasServicioTbl oferta = busquedas.variosFacadeRemote.findOferServ(oferId);
                        StringTokenizer sc = new StringTokenizer(scorrida,"-");
                        String vh = sc.nextToken();
                        String vo = sc.nextToken();
                        String vd = sc.nextToken();
                        System.out.println("   Corrida No:  "+j);
                        System.out.println("   Ruta  : "+oferta.getRutaId().getRutaId().toBigInteger());
                        System.out.println("   Origen: "+vo);
                        System.out.println("   Hora: "+vh);
                        rolLinea.setCuadroGuardia("N");

                            if(j==2){
                             rolLinea.setRutaCorrida1Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida1Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida1(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida1Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida1Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                           if(j==3){
                             rolLinea.setRutaCorrida2Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida2Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida2(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida2Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida2Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==4){
                             rolLinea.setRutaCorrida3Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida3Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida3(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida3Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida3Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==5){
                             rolLinea.setRutaCorrida4Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida4Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida4(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida4Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida4Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==6){
                             rolLinea.setRutaCorrida5Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida5Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida5(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida5Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida5Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==7){
                             rolLinea.setRutaCorrida6Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida6Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida6(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida6Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida6Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==8){
                             rolLinea.setRutaCorrida7Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida7Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida7(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida7Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida7Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==9){
                             rolLinea.setRutaCorrida8Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida8Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida8(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida8Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida8Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==10){
                             rolLinea.setRutaCorrida9Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida9Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida9(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida9Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida9Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==11){
                             rolLinea.setRutaCorrida10Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida10Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida10(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida10Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida10Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==12){
                             rolLinea.setRutaCorrida11Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida11Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida11(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida11Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida11Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==13){
                             rolLinea.setRutaCorrida12Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida12Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida12(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida12Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida12Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==14){
                             rolLinea.setRutaCorrida13Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida13Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida13(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida13Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                            }
                            if(j==15){
                             rolLinea.setRutaCorrida14Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida14Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida14(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida14Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                            }
                            if(j==16){
                             rolLinea.setRutaCorrida15Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida15Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida15(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida15Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                            }
                            if(j==17){
                             rolLinea.setRutaCorrida16Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida16Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida16(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida16Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida16Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==18){
                             rolLinea.setRutaCorrida17Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida17Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida17(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida17Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida17Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==19){
                             rolLinea.setRutaCorrida18Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida18Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida18(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida18Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida18Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==20){
                             rolLinea.setRutaCorrida19Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida19Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida19(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida19Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida19Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==21){
                             rolLinea.setRutaCorrida20Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida20Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida20(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida20Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida20Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==22){
                             rolLinea.setRutaCorrida21Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida21Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida21(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida21Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida21Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==23){
                             rolLinea.setRutaCorrida22Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida22Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida22(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida22Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida22Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==24){
                             rolLinea.setRutaCorrida23Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida23Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida23(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida23Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida23Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==25){
                             rolLinea.setRutaCorrida24Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida24Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida24(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida24Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida24Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                            if(j==26){
                             rolLinea.setRutaCorrida25Id(oferta.getRutaId().getRutaId().toBigInteger());  
                             Vector vEdoId = (Vector)busquedas.variosFacadeRemote.buscaIdEdoPorNombre(vo);
                             rolLinea.setOrigenCorrida25Id(BigInteger.valueOf(Long.valueOf(vEdoId.get(0).toString())));
                             Timestamp fhc = null;
                             fhc = fhc.valueOf("2004-12-10 "+vh+":00");
                             rolLinea.setHorarioCorrida25(new Date(fhc.getTime()));
                             rolLinea.setEmpresaCorrida25Id(oferta.getEmpresaId().getEmpresaId().toBigInteger());
                             rolLinea.setOfertaCorrida25Id(oferta.getOfertaServicioId().toBigInteger());
                            }
                        }
                  }
                  }
                    if(!guardia)
                    {
                         System.out.println("   cantidad de Salidas  : "+nsali);
                         rolLinea.setCantidadSalidas(BigInteger.valueOf(Long.valueOf(""+nsali)));
                         rolLinea.setCreadoPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setFechaCreacion(new Date(fecha_servidor.getTime()));
                         rolLinea.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                         rolLinea.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                         busquedas.rolesBaseLineasTblFacadeRemote.create(rolLinea);
                    }
              }
                  
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_rolmanual = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlist_horarios = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlist_guardias = new javax.swing.JList();

//        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rol Manual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_rolmanual.setFont(new java.awt.Font("Tahoma", 0, 10));
        jtbl_rolmanual.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_rolmanual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_rolmanualKeyPressed(evt);
            }
            
                    public void keyReleased(KeyEvent e) {
                        jtbl_rolmanual_keyReleased(e);
                    }
            
        });

        jScrollPane1.setViewportView(jtbl_rolmanual);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 10));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Horarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jlist_horarios.setBackground(new java.awt.Color(231, 231, 231));
        jlist_horarios.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlist_horarios.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "08:00-SMA-CAPU" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlist_horarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlist_horariosKeyPressed(evt);
            }
        });
        jlist_horarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlist_horariosMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jlist_horarios);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guardias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jlist_guardias.setBackground(new java.awt.Color(231, 231, 231));
        jlist_guardias.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Guardia" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlist_guardias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlist_guardiasKeyPressed(evt);
            }
        });
        jlist_guardias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlist_guardiasMouseClicked(evt);
            }
        });

        jScrollPane3.setViewportView(jlist_guardias);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
 //       pack();
    }// </editor-fold>                        

    private void jtbl_rolmanualKeyPressed(java.awt.event.KeyEvent evt) {                                          
         if(evt.getKeyCode() == evt.VK_F2){
             agregarDia();
         }
         
         if(evt.getKeyCode() == evt.VK_ESCAPE)
           principal.salirAplicacion();
         

         if(evt.getKeyCode() == evt.VK_F3)
            eliminarGuardia();

         if(evt.getKeyCode() == evt.VK_F5)
         {
             if(numcat==0)
               agregarAutobuses();
         }
         
         if(evt.getKeyCode() == evt.VK_F10)
             guardarRol(true);

         if(evt.getKeyCode() == evt.VK_F11)
             verificarEnrolamiento();
     
    }                                         
   
    private void jtbl_rolmanual_keyReleased(KeyEvent e) {
            if(e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_ENTER) {
             itsRowSelect = jtbl_rolmanual.getSelectedRow();
             itsColumnSelect = jtbl_rolmanual.getSelectedColumn();
             jtbl_rolmanual.repaint();
                
            }
            
        }    
    
    private void jlist_guardiasMouseClicked(java.awt.event.MouseEvent evt) {                                            
        if (evt.getClickCount() == 2) // Se detecta si es doble click
        {
             int pos = jlist_guardias.locationToIndex(evt.getPoint());
             pasarguardia((String)jlist_guardias.getModel().getElementAt(pos), pos, false);

         }//doble Click    
    
    }                                           

    private void jlist_guardiasKeyPressed(java.awt.event.KeyEvent evt) {                                          
//          if(evt.getKeyCode() == evt.VK_ENTER)
//         {
//          String selected = (String)jlist_horarios.getSelectedValue();
//           pasarguardia(selected, 0, true);
//         }

         if(evt.getKeyCode() == evt.VK_F2){
             agregarDia();
         }

         if(evt.getKeyCode() == evt.VK_F5)
         {
             if(numcat==0)
               agregarAutobuses();
         }

          if(evt.getKeyCode() == evt.VK_F10)
             guardarRol(true);

          if(evt.getKeyCode() == evt.VK_F11)
             verificarEnrolamiento();
          
         if(evt.getKeyCode() == evt.VK_ESCAPE)
           principal.salirAplicacion();
          
         
    }                                         

    private void jlist_horariosMouseClicked(java.awt.event.MouseEvent evt) {                                            
        if (evt.getClickCount() == 2) // Se detecta si es doble click
        {
             int pos = jlist_horarios.locationToIndex(evt.getPoint());
             if(pos!=-1)
               pasarhorario((String)jlist_horarios.getModel().getElementAt(pos), pos, false);

         }//doble Click    
    }                                           

    private void jlist_horariosKeyPressed(java.awt.event.KeyEvent evt) {                                          
//        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
//         {
//          String selected = (String)jlist_horarios.getSelectedValue();
//          pasarhorario(selected, 0, true);
//         }
         if(evt.getKeyCode() == evt.VK_F2){
             agregarDia();
         }

         if(evt.getKeyCode() == evt.VK_F5)
         {
             if(numcat==0)
               agregarAutobuses();
         }
         
          if(evt.getKeyCode() == evt.VK_F10)
             guardarRol(true);
        
         if(evt.getKeyCode() == evt.VK_F11)
             verificarEnrolamiento();

        if(evt.getKeyCode() == evt.VK_ESCAPE)
            principal.salirAplicacion();
    }                                         
    
    
    
    
    
  public void pasarhorario(String horario, int posicion, boolean e_enter){
    if(jtbl_rolmanual.getSelectedRow()>=0)
     {
        int  renglondestino = jtbl_rolmanual.getSelectedRow();
        if(jtbl_rolmanual.getValueAt(renglondestino,2).equals("Guardia"))
        {
            new jdlg_error("¡El dia seleccionado es una guardia¡","No puedes poner salidas en este dia","Dia de Guardia").setVisible(true);
            return;
        }
         //int  columnadestino = jtbl_rolmanual.getSelectedColumn();
         int columnadestino =0;
         for(int i=2;i<jtbl_rolmanual.getColumnCount(); i++){
              if(jtbl_rolmanual.getValueAt(renglondestino,i).equals(""))
              {
                  columnadestino = i;
                  break;
              }
         }
         
         String salida = horario;
         if(jtbl_rolmanual.getValueAt(renglondestino,columnadestino).equals(""))
         {             
            jtbl_rolmanual.setValueAt(salida,renglondestino,columnadestino);
             if(e_enter)
                modelolistahorarios.removeElement(horario);
              else
                modelolistahorarios.removeElementAt(posicion);
            sobranHorarios--;
            if(sobranHorarios==0)
            {
                jlist_horarios.setVisible(false);
                new jdlg_informacion("Se han agregado todos los horarios","","No hay mas horarios").setVisible(true);
                principal.setListos(numcat);
            }
         }
         else
           new jdlg_error("¡La casilla tiene un valor, elige una casilla vacia!","","Casilla ocupada").setVisible(true);

      }
    else
      new jdlg_error("Debes seleccionar una celda","","No hay casilla seleccionada").setVisible(true);
  }//pasarhorario()     
  
  
  public void pasarguardia(String horario, int posicion, boolean e_enter){
     if(jtbl_rolmanual.getSelectedRow()>=0)
     {
         int  renglondestino = jtbl_rolmanual.getSelectedRow();
         String salida = horario;
         boolean vacio = true;
         for(int i=2; i<jtbl_rolmanual.getColumnCount(); i++)
         {
             if(!jtbl_rolmanual.getValueAt(renglondestino,i).equals(""))
            {
                vacio = false;
                new jdlg_error("¡La casilla tiene un valor, elige una casilla vacia!","","Casilla ocupada").setVisible(true);
                return;
            }
         }
            for(int i=2; i<jtbl_rolmanual.getColumnCount(); i++)
                jtbl_rolmanual.setValueAt("Guardia",renglondestino,i);

      }
    else
      new jdlg_error("Debes seleccionar un dia","","No hay casilla seleccionada").setVisible(true);
      

      
  }//pasarGuardia()     

  
  private void agregarDia(){
        Object[] dia = new Object[27];
        dia[0] = "";dia[1] = ""+(jtbl_rolmanual.getRowCount() + 1);dia[2] = "";dia[3] = "";dia[4] = "";dia[5] = "";
        dia[6] = "";dia[7] = "";dia[8] = "";dia[9] = "";dia[10] = "";dia[11] = "";
        dia[12] = "";dia[13] = "";dia[14] = "";dia[15] = "";dia[16] = "";dia[17] = "";
        dia[18] = "";dia[19] = "";dia[20] = "";dia[21] = "";dia[22] = "";dia[23] = "";
        dia[24] = "";dia[25] = "";dia[26] = "";
       modeloRolManual.addRow(dia); 
  }
  
  private void eliminarGuardia(){
    if(jtbl_rolmanual.getSelectedRow()>=0)
     {
        int  renglondestino = jtbl_rolmanual.getSelectedRow();
        boolean vacio = true;
        for(int i=2; i<=25; i++)
        {
            if(!jtbl_rolmanual.getValueAt(renglondestino,i).equals(""))
                vacio = false;
        }
        if(jtbl_rolmanual.getValueAt(renglondestino,2).equals("Guardia") || vacio)
        {
            modeloRolManual.removeRow(renglondestino);
            for(int i=0; i<jtbl_rolmanual.getRowCount(); i++)
                jtbl_rolmanual.setValueAt(""+(i+1),i,1);
        }
        else{
            new jdlg_error("¡El dia a eliminar no es una guardia o un día vacio¡","No puedes eliminar este dia","Dia de Salidas").setVisible(true);
            return;
        }
 
      }
    else
      new jdlg_error("Debes seleccionar una celda","","No hay casilla seleccionada").setVisible(true);     
 }
 
  public boolean guardarRol(boolean pespera){
    principal.muestrabusesPrueba();
       
       boolean espera = pespera;
       boolean vacio = true;
        for(int j=0; j<jtbl_rolmanual.getRowCount();j++)
        {
            vacio = true;
            for(int i=2; i<=25; i++)
             {
                if(!jtbl_rolmanual.getValueAt(j,i).equals(""))
                {
                    vacio = false;
                    break;
                }
             }
            if(vacio)
                break;  
        }
        if(vacio)
        {
          new jdlg_advertencia("¡No puede haber dias vacios!","","Existen horarios vacios").setVisible(true);
          return false;
        }
        boolean busesVacios = false;
        for(int j=0; j<jtbl_rolmanual.getRowCount();j++)
        {
            busesVacios = false;
                if(jtbl_rolmanual.getValueAt(j,0).equals(""))
                {
                    busesVacios = true;
                    break;
                }
            if(busesVacios)
                break;  
        }
        if(busesVacios)
        {
          new jdlg_advertencia("¡Debes asignar autobus a cada uno de los cuadros!","","No hay autobuses Asignados").setVisible(true);
          return false;
        }
     if(espera){
        boolean regresa = principal.verificarAntesGuardarRol(numcat);
        System.out.println("Regresa = "+regresa);
        if(regresa)
        {
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de guardado", "¿Seguro que desea guardar el rol actual?");
          psn.setVisible(true);
          if(respuestaSN)
           principal.guardarRoles();
        }
     }
      return true;
   }
   
  private void verificarEnrolamiento(){
       principal.verificaEnrolamientos(nomcat);
   }
   
   public void verEnrolamiento(){
      if(sobranHorarios>0){
          new jdlg_advertencia("¡Debes agregar todos los horarios del rol de "+nomcat+"!","","Faltan horarios por agregar").setVisible(true);
          return;
      }
      System.out.println("sobranHorarios= "+sobranHorarios);
      if(sobranHorarios==0)
            principal.setListos(numcat);
      
        boolean vacio = true;
        for(int j=0; j<jtbl_rolmanual.getRowCount();j++)
        {
            vacio = true;
            for(int i=2; i<=25; i++)
             {
                if(!jtbl_rolmanual.getValueAt(j,i).equals(""))
                {
                    vacio = false;
                    break;
                }
             }
            if(vacio)
                break;  
        }
        System.out.println("paso a verificar si esta vacio...");
        if(vacio)
        {
          new jdlg_advertencia("¡No puede haber dias vacios!","","Existen horarios vacios").setVisible(true);
          return;
        }            
       int numbuses = 0;
       boolean vacios = true;
       for(int k=0; k<jtbl_rolmanual.getRowCount(); k++){
           for(int i=2;i<jtbl_rolmanual.getColumnCount(); i++){
                if(!jtbl_rolmanual.getValueAt(k,i).equals(""))
                {
                    numbuses++;
                    break;
                } 
           }
       }
       Vector buses = new Vector();
       for(int k=0; k<jtbl_rolmanual.getRowCount(); k++){
           if(!jtbl_rolmanual.getValueAt(k,0).equals(""))
               buses.add(""+jtbl_rolmanual.getValueAt(k,0));
       }
        System.out.println("paso a verificar los autobuses: "+buses.size());
       
       if(buses.size()==0)
       {
           new jdlg_advertencia("¡No hay autobuses asignados!","","").setVisible(true);
           return;
       }
       else
           principal.setAutobuses(numcat,buses);
       principal.setNumDias(numcat,numbuses);
       principal.agregarListo(nomcat);
       principal.setHorariosRol(numcat,modeloRolManual.getDataVector());
       
        System.out.println("paso a verificar si estan listos...");
       if(principal.getListos())
       {
          System.out.println("si estan listos (principal.getListos())..");
          if(principal.getCorrecto())
          {
            System.out.println("si estan correctos (principal.getCorrecto())..");
              if(principal.getBusesCorrecto())
                  principal.completo();
              else
              {
                System.out.println("no estan los buses correctos (principal.getBusesCorrecto())..");
                  if(principal.todosListos())
                  {
                      new jdlg_advertencia("¡Los autobuses de los cuadros de los roles debe ser el mismo!","","Faltan dias por completar").setVisible(true);
                      principal.limpiarodosListos();
                  }
                      
              }              
          }
          else
          {
            System.out.println("no estan correctos (principal.getCorrecto())..");              
            if(principal.todosListos())
            {
              System.out.println("no estan todos listos (principal.todosListos())..");  
              new jdlg_advertencia("¡El numero de dias de los roles debe ser el mismo!","","Faltan dias por completar").setVisible(true);
              principal.limpiarodosListos();
            }
              
          }              
       }
      // else
      //    new jdlg_advertencia("¡Aun no se han completado los roles de los demas dias!","","Faltan dias por completar").setVisible(true);
  }
  
   
   public void agregarBusATabla(Object bus, int fila, int column){
       if(jtbl_rolmanual.getRowCount()>=busesAsiganos.size())
          jtbl_rolmanual.setValueAt(bus,fila,column);
   }
   
private void agregarAutobuses() {
//            for(int i=0; i<jtbl_rolmanual.getRowCount(); i++)
//                jtbl_rolmanual.setValueAt("",i,0);
            principal.resetBuses();
            insertados=0;
            nautobuses = jtbl_rolmanual.getRowCount();
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
            jcmbx_flotillas.setSelectedItem( flotillaSeleccionada.getNombreFlotilla());
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
                           jtbl_rolmanual.setValueAt(lista.getModel().getElementAt(posicion),insertados-1,0);
                           principal.agregarAutobusATodos(lista.getModel().getElementAt(posicion).toString(),numcat);
                           principal.agregarAutobusATabla(lista.getModel().getElementAt(posicion),insertados-1,0,numcat);
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
                                  jtbl_rolmanual.setValueAt(selected[i],insertados-1,0);
                                  principal.agregarAutobusATodos(selected[i].toString(),numcat);
                                  principal.agregarAutobusATabla(selected[i],insertados-1,0,numcat);
                                  modelolist.removeElement(selected[i]);
                                 }
                                if(insertados==nautobuses)
                                {
                                    asignados = true;
                                    dbuses.dispose();
                                }
                               }
                              else
                              {
                                   dbuses.setAlwaysOnTop(false);
                                   JOptionPane.showMessageDialog(new JDialog(),"La seleccion debe de ser menor o igual a "+(nautobuses-insertados)+" Autobuses","Demasiados Autobuses seleccionados", 1);
                                   dbuses.setAlwaysOnTop(true);
                              }
                                   
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
             dbuses.setSize( 152, 450 ); 
             dbuses.setVisible( true );
             dbuses.setResizable(false);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension DilaogSize = this.getSize();
                if (DilaogSize.height > screenSize.height) {
                    DilaogSize.height = screenSize.height;
                }
                if (DilaogSize.width > screenSize.width) {
                    DilaogSize.width = screenSize.width;
                }
                dbuses.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
             dbuses.setAlwaysOnTop(true);
             if(listadoFlotillas.size()>0)
             {
                    lista.setSelectedIndex(0);
                    lista.requestFocus();
             }
                 
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
                for(int j=0; j<jtbl_rolmanual.getRowCount(); j++)
                {
                    if(jtbl_rolmanual.getValueAt(j,0)!=null &&  jtbl_rolmanual.getValueAt(j,0).toString().equals(vautobus.get(0).toString()))
                        agregar = false;
                }
                if(agregar)
                  modelolist.addElement(vautobus.get(0).toString());//unbus.getNumeroEconomico());
            }
   }
    
    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JList jlist_guardias;
    private javax.swing.JList jlist_horarios;
    private javax.swing.JTable jtbl_rolmanual;
    // End of variables declaration                   
    private DefaultListModel modelolistahorarios= new DefaultListModel();
    private DefaultListModel modeloguardia= new DefaultListModel();
     private Object[] encabezadoRolManual = {"Autobus","Cuadro","Salida1","Salida2","Salida3","Salida4","Salida5","Salida6","Salida7","Salida8","Salida9","Salida10","Salida11","Salida12","Salida13","Salida14","Salida15","Salida16","Salida17","Salida18","Salida19","Salida20","Salida21","Salida22","Salida23","Salida24","Salida25"};
    private DefaultTableModel modeloRolManual = new DefaultTableModel(null,encabezadoRolManual){
    public boolean isCellEditable (int row, int column)
        {
            if (column == 30)
               return true;
            return false;
        }   
    };
    private Vector busesAsiganos = new Vector();
    

    
    public void resetBusesAsiganos() {
        this.busesAsiganos = new Vector();
        for(int i=0; i<jtbl_rolmanual.getRowCount(); i++)
            jtbl_rolmanual.setValueAt("",i,0);
    }

    public Vector getBusesAsiganos() {
        return busesAsiganos;
    }

    public void setBusesAsiganos(Vector pbusesAsiganos) {
        this.busesAsiganos = new Vector();
        this.busesAsiganos = pbusesAsiganos;
    }
    
    public void agregarBus(String pbus) {
        this.busesAsiganos.add(pbus);
    }

    
    public boolean getHaycorridas() {
        return haycorridas;
    }    




///////////////////// CLASES ////////////////////////////
   class   MyMouseAdapter extends MouseMotionAdapter{

           public void mouseDragged(MouseEvent e)
           {
                if(arrastrar)
                {
                 itsRow = jtbl_rolmanual.rowAtPoint(e.getPoint());
                 itsColumn = jtbl_rolmanual.columnAtPoint(e.getPoint());
                 jtbl_rolmanual.repaint();
               }
           }
        
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
   
   
class AttributiveCellRenderer extends JLabel  implements TableCellRenderer {
        public AttributiveCellRenderer() {
         setOpaque(true);
        }
      
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value==null || value.equals(""))
              setText(null);
            else
              setText(String.valueOf(value));
           this.setFont(new java.awt.Font("Tahoma", 0, 10));
            if(row == itsRowSelect && column == itsColumnSelect)
                this.setBackground(new Color(184, 207, 229));
            else
                this.setBackground(Color.white);
           
           if(vOferModif.indexOf(""+row+","+column)>=0)
                this.setForeground(new Color(255, 64, 64));
            else
                  this.setForeground(new Color(51, 51, 51));
          return this;
        }}   

    public boolean isHorarioEliminado() {
        return horarioEliminado;
    }

    public void setHorarioEliminado(boolean horarioEliminado) {
        this.horarioEliminado = horarioEliminado;
    }

}
