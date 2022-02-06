/*
 * jdlg_RolesManual.java
 *
 * Created on 18 de noviembre de 2007, 10:02 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import tmsroles.entidad.TmsFlotillasTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;
import tmsroles.entidad.TmsServiciosTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_RolesManual extends javax.swing.JDialog {
    private TmsRolesManagedBean busquedas;
    private long idrservicio;
    private long usuarioId;
    private Vector excluir;
    private Vector vHorarios;
    private boolean haycorridas = false;    
    private int nhor =0;
    private String[][] datos;
    private int sobranHorarios=0;
    private    Vector catA = new Vector();
    private    Vector catB = new Vector();
    private    Vector catC = new Vector();
    private    Vector catD = new Vector();
    private    Vector catE = new Vector();
    private    Vector catF = new Vector();
    private    Vector catG = new Vector();
    private Vector indiceCorridas = new Vector();
    private Vector idCorridas =  new Vector();
    private Vector nomCategorias = new Vector();
    private Vector vhorCategorias = new Vector();
    private Vector horRoles = new Vector();
    private Vector numDias= new Vector();
    private Vector autobuses= new Vector();
    private Vector listos= new Vector();
    private int ncategorias = 0;
    private int itsRow =300;
    private int itsColumn = 300;
    private boolean arrastrar = false;
    private int columnaorigen = 100; 
    private int renglonorigen = 100;
    private int columnadestino = 100; 
    private int renglondestino = 100;
    private String valororigen = "";
    private String valordestino = "";
    private boolean respuestaSN = true;    
    private Timestamp fecha_servidor;
    private Timestamp ff;

    private Timestamp fi;

    private String nombreRolMaestro;
    private TmsFlotillasTbl flotillaSeleccionada;
    private String cdes;
    private String coris;
    private String cemp;
    private boolean guardarRolMaestro = false;
    private TmsRolesMaestroTbl rm = null;
    private String nombreOferta;
    private boolean abierto;
    private boolean salida = false;
    private Vector vrutes;
    private Vector verificadas = new Vector();

    private Vector todoListos = new Vector();
    
    /** Creates new form jdlg_RolesManual */
    public jdlg_RolesManual(TmsRolesManagedBean pbusquedas,long idSer,TmsFlotillasTbl pflotillaSeleccionada , String oferta, Vector pexcluir, long pusuarioId, String pnombreRolMaestro, Timestamp pfi, Timestamp pff, String pnombreOferta, TmsRolesMaestroTbl prolMaestroAbierto, boolean pabierto, Vector pvrutes ){
        this.setTitle("Horarios de la Oferta para generar Corridas");
        this.busquedas = pbusquedas;
        //this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        this.idrservicio = idSer;
        this.flotillaSeleccionada = pflotillaSeleccionada;
        this.excluir = pexcluir;
        this.usuarioId = pusuarioId;
        this.nombreRolMaestro = pnombreRolMaestro;
        this.fi = pfi;
        this.ff = pff;
        this.nombreOferta = pnombreOferta;
        this.vrutes = pvrutes;
        System.out.println("Las Rutas en Roles Manuales son: "+vrutes);
        this.abierto = pabierto;
        if(abierto){
            rm = prolMaestroAbierto;
            guardarRolMaestro = true;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );    
        vHorarios = (Vector)busquedas.variosFacadeRemote.buscaHorariosDeLaOfertaRolManual(idSer,oferta);
        System.out.println("Los horarios encontrados: "+vHorarios);
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        numDias.add("-1"); listos.add("-1");horRoles.add("");autobuses.add(new Vector());
        coris = "";
        cdes = "";
        cemp = "";
        Vector vhorariosSolos = new Vector();
        for(int i=0; i<vHorarios.size();i++){
           String[] unHorario = new String[12];
            Vector  horario = (Vector)vHorarios.get(i);
            boolean ex = false;
            for(int j=0; j<excluir.size(); j++)
            {
              if(horario.get(11).toString().equals(excluir.get(j).toString()))
              {
                  ex = true;
                  break;
              }
                  
            }
            if(!ex)
            {
               String tor = horario.get(1).toString();
               String tde = horario.get(2).toString();
               String temp = horario.get(12).toString();
               if(coris.indexOf(tor)==-1)
               {
                   if(coris.equals(""))
                       coris=coris+"'"+tor+"'";
                   else
                       coris=coris+",'"+tor+"'";
               }
               if(cdes.indexOf(tde)==-1)
               {
                   if(cdes.equals(""))
                       cdes=cdes+"'"+tde+"'";
                   else
                       cdes=cdes+",'"+tde+"'";
               }
               if(cemp.indexOf(temp)==-1)
               {
                   if(cemp.equals(""))
                       cemp=cemp+"'"+temp+"'";
                   else
                       cemp=cemp+",'"+temp+"'";
               }
               unHorario[0] = horario.get(0).toString();
                unHorario[1] = horario.get(1).toString();
                unHorario[2] = horario.get(2).toString();
                unHorario[3] = horario.get(3).toString();
                unHorario[4] = horario.get(4).toString();
                unHorario[5] = horario.get(5).toString();
                unHorario[6] = horario.get(6).toString();
                unHorario[7] = horario.get(7).toString();
                unHorario[8] = horario.get(8).toString();
                unHorario[9] = horario.get(9).toString();
                unHorario[10] = horario.get(10).toString();
                unHorario[11] = horario.get(11).toString();
                vhorariosSolos.add(unHorario);
                nhor++;
                haycorridas = true;
            }            
        }
       System.out.println("Cadena de Origenes: "+coris);
       System.out.println("Cadena de Destinos: "+cdes);
        if(haycorridas){
 //           System.out.println("entra a meter los horarios... "+vhorariosSolos);
            datos = new String[vhorariosSolos.size()][12];
            for(int i=0; i<vhorariosSolos.size();i++){
               String[] unHorario = new String[12];
               unHorario = (String[])vhorariosSolos.get(i);
               datos[i][0] = unHorario[0];
               datos[i][1] = unHorario[1];
               datos[i][2] = unHorario[2];
               datos[i][3] = unHorario[3];
               datos[i][4] = unHorario[4];
               datos[i][5] = unHorario[5];
               datos[i][6] = unHorario[6];
               datos[i][7] = unHorario[7];
               datos[i][8] = unHorario[8];
               datos[i][9] = unHorario[9];
               datos[i][10] = unHorario[10];
               datos[i][11] = unHorario[11];
            }    
        }
        categorias();
        ncategorias = nomCategorias.size();
       for(int i=0; i<nomCategorias.size(); i++) 
            jtabpnl_categorias.addTab(""+nomCategorias.get(i).toString(), new jpnl_RolCategoria2(busquedas,(Vector)vhorCategorias.get(i),this,i,indiceCorridas,idCorridas,flotillaSeleccionada, nomCategorias.get(i).toString()));
        //Vector horariosRutas = (Vector)vhorCategorias.get(0);
        if(abierto) 
            cargarRoles();
    }
    
    private void categorias(){

        for(int i=0; i<nhor; i++)
        {
            if(datos[i][4].equals("S"))
                catA.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][5].equals("S"))
                catB.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][6].equals("S"))
                catC.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][7].equals("S"))
                catD.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][8].equals("S"))
                catE.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][9].equals("S"))
                catF.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
            if(datos[i][10].equals("S"))
                catG.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
        indiceCorridas.add(datos[i][0]+"-"+datos[i][1]+"-"+datos[i][2]);
        idCorridas.add(datos[i][3]);
        }
        System.out.println("Categoria A: "+catA);
        System.out.println("Categoria B: "+catB);
        System.out.println("Categoria C: "+catC);
        System.out.println("Categoria D: "+catD);
        System.out.println("Categoria E: "+catE);
        System.out.println("Categoria F: "+catF);
        System.out.println("Categoria G: "+catG);
        if(catA.equals(catB) && catA.size()>0)
        {
          String nombre = "Lun-Mar";  
           if(catB.equals(catC) && catB.size()>0)
           {
              nombre = nombre + "-Mie";
              if(catC.equals(catD) && catC.size()>0)
              {
                  nombre = nombre + "-Jue";              
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    nombre = nombre + "-Vie"; 
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                          else
                          {
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catF);
                              if(catG.size()>0) 
                              {
                                nomCategorias.add("Dom");
                                vhorCategorias.add(catF);
                              }
                           
                          }
                      }else
                      {
                             nomCategorias.add(nombre);
                             vhorCategorias.add(catE);
                             if(catF.equals(catG) && catF.size()>0) 
                              {
                                nomCategorias.add("Sab-Dom");
                                vhorCategorias.add(catG);
                              }
                              else
                              {
                                  if(catF.size()>0) 
                                  {
                                    nomCategorias.add("Sab");
                                    vhorCategorias.add(catF);
                                  }
                                  if(catG.size()>0) 
                                  {
                                    nomCategorias.add("Dom");
                                    vhorCategorias.add(catG);
                                  }
                              }                        
                      }
                 }else{
                      nomCategorias.add(nombre);
                      vhorCategorias.add(catD);
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "Vie-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                          else
                          {
                             nomCategorias.add(nombre);
                             vhorCategorias.add(catF);
                              if(catG.size()>0) 
                              {
                                nombre = "Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }
                           
                          }
                      }else{
                              if(catF.equals(catG) && catF.size()>0) 
                              {
                                nombre = "Sab-Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }
                              else
                              {
                                  if(catF.size()>0) 
                                  {
                                    nomCategorias.add("Sab");
                                    vhorCategorias.add(catF);
                                  }
                                  if(catG.size()>0) 
                                  {
                                    nomCategorias.add("Dom");
                                    vhorCategorias.add(catG);
                                  }

                              }
                      }                      
                 }
                  
              }
              else  
              {
                    nomCategorias.add(nombre);
                    vhorCategorias.add(catC);
                     if(catD.equals(catE) && catD.size()>0)
                     {
                        nombre = "Jue-Vie"; 
                          if(catE.equals(catF) && catE.size()>0)
                          {
                           nombre = nombre + "-Sab"; 
                              if(catF.equals(catG) && catF.size()>0) 
                              {
                                nombre = nombre + "-Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }
                              else
                              {
                                 nomCategorias.add(nombre);
                                 vhorCategorias.add(catF);
                                  if(catF.size()>0) 
                                  {
                                    nomCategorias.add("Sab");
                                    vhorCategorias.add(catF);
                                  }
                                  if(catG.size()>0) 
                                  {
                                    nomCategorias.add("Dom");
                                    vhorCategorias.add(catG);
                                  }
                              }
                          }else{
                                  nomCategorias.add(nombre);
                                  vhorCategorias.add(catE);
                                  if(catF.equals(catG) && catF.size()>0) 
                                  {
                                    nomCategorias.add("Sab-Dom");
                                    vhorCategorias.add(catG);
                                  }
                                  else
                                  {
                                      if(catF.size()>0) 
                                      {
                                        nomCategorias.add("Sab");
                                        vhorCategorias.add(catF);
                                      }
                                      if(catG.size()>0) 
                                      {
                                        nomCategorias.add("Dom");
                                        vhorCategorias.add(catG);
                                      }
                                  }                            
                          }
                     }else{
                           if(catD.size()>0)
                           {
                               nomCategorias.add("Jue");
                               vhorCategorias.add(catD);
                           }
                           if(catE.equals(catF) && catE.size()>0)
                              {
                               nombre = "Vie-Sab"; 
                                  if(catF.equals(catG) && catF.size()>0) 
                                  {
                                    nombre = nombre + "-Dom";
                                    nomCategorias.add(nombre);
                                    vhorCategorias.add(catG);
                                  }
                                  else
                                  {
                                     nomCategorias.add(nombre);
                                     vhorCategorias.add(catF);
                                      if(catF.size()>0) 
                                      {
                                        nomCategorias.add("Sab");
                                        vhorCategorias.add(catF);
                                      }
                                      if(catG.size()>0) 
                                      {
                                        nomCategorias.add("Dom");
                                        vhorCategorias.add(catG);
                                      }
                                  }
                              }
                           else{
                              if(catE.size()>0) 
                              {
                                nomCategorias.add("Vie");
                                vhorCategorias.add(catE);
                              }
                                if(catF.equals(catG) && catF.size()>0) 
                                  {
                                    nombre =  "Sab-Dom";
                                    nomCategorias.add(nombre);
                                    vhorCategorias.add(catG);
                                  }
                                  else
                                  {
                                      if(catF.size()>0) 
                                      {
                                        nomCategorias.add("Sab");
                                        vhorCategorias.add(catF);
                                      }
                                      if(catG.size()>0) 
                                      {
                                        nomCategorias.add("Dom");
                                        vhorCategorias.add(catG);
                                      }
                                  }                              
                           }
                     }                  
              }
           }
           else{
              nomCategorias.add(nombre);
              vhorCategorias.add(catB);
              if(catC.equals(catD) && catC.size()>0)
              {
                  nombre = "Jue";              
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    nombre = nombre + "-Vie"; 
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                      }
                 }
              }
              else{
                     if(catD.equals(catE) && catD.size()>0)
                     {
                        nombre = nombre + "-Vie"; 
                          if(catE.equals(catF) && catE.size()>0)
                          {
                           nombre = nombre + "Sab"; 
                              if(catF.equals(catG) && catF.size()>0) 
                              {
                                nombre = nombre + "-Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }
                          }
                     }
                     else{
                          if(catE.equals(catF) && catE.size()>0)
                          {
                           nombre = nombre + "Sab"; 
                              if(catF.equals(catG) && catF.size()>0) 
                              {
                                nombre = nombre + "-Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }
                              else
                              {
                                   if(catF.size()>0)
                                   {
                                    nomCategorias.add("Sab");
                                    vhorCategorias.add(catF);
                                   }
                                   if(catG.size()>0)
                                   {
                                    nomCategorias.add("Dom");
                                    vhorCategorias.add(catG);
                                   }
                              }
                           
                          }
                          else
                              if(catF.equals(catG) && catF.size()>0) 
                              {
                                nombre = "Sab-Dom";
                                nomCategorias.add(nombre);
                                vhorCategorias.add(catG);
                              }else{nomCategorias.add("Dom");vhorCategorias.add(catG);}
                     }
              }
           }
        }
        else
        {
           if(catA.size()>0)
           {
               nomCategorias.add("Lun");
               vhorCategorias.add(catA);
           } 
            System.out.println("A y B No son iguales");
           if(catB.equals(catC) && catB.size()>0)
           {
              String nombre = "Mar-Mie";  
              System.out.println("B y C son iguales");
              if(catC.equals(catD) && catC.size()>0)
              {
                  nombre = nombre + "-Jue";              
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    nombre = nombre + "-Vie"; 
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                          else
                          {
                               if(catF.size()>0)
                               {
                                nomCategorias.add("Sab");
                                vhorCategorias.add(catF);
                               }
                               if(catG.size()>0)
                               {
                                nomCategorias.add("Dom");
                                vhorCategorias.add(catG);
                               }
                          }
                       
                      }
                 }
                  //modificacion 25/01/2008
                 else
                 {
                    if(catD.size()>0)
                     {
                       nomCategorias.add(nombre);
                       vhorCategorias.add(catD);
                     }                    
                    System.out.println("D y E No son iguales");
                   if(catE.equals(catF) && catE.size()>0)
                      {
                        nombre = "Vie-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                      }
                    else
                    {
                        if(catE.size()>0)
                         {
                           nomCategorias.add("Vie");
                           vhorCategorias.add(catE);
                         }                    
                       System.out.println("E y F No son iguales");
                       if(catF.equals(catG) && catF.size()>0) 
                          {
                             nombre = "Sab-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }else{
                            if(catF.size()>0)
                             {
                               nomCategorias.add("Sab");
                               vhorCategorias.add(catF);
                             }
                            if(catG.size()>0)
                             {
                               nomCategorias.add("Dom");
                               vhorCategorias.add(catG);
                             }                    
                          }
                    }
                 }
                  //hasta aui la modificacion 25/01/2008
              }
              else{
                  nomCategorias.add(nombre);
                  vhorCategorias.add(catC);
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    nombre = "Jue-Vie"; 
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                          else
                          {
                               if(catF.size()>0)
                               {
                                nomCategorias.add("Sab");
                                vhorCategorias.add(catF);
                               }
                               if(catG.size()>0)
                               {
                                nomCategorias.add("Dom");
                                vhorCategorias.add(catG);
                               }
                          }
                      }
                 }
                  //modificacion 25/01/2008 ->2
                 else
                 {
                    if(catD.size()>0)
                     {
                       nomCategorias.add("Jue");
                       vhorCategorias.add(catD);
                     }                    
                    System.out.println("D y E No son iguales");
                   if(catE.equals(catF) && catE.size()>0)
                      {
                        nombre = "Vie-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                      }
                    else
                    {
                        if(catE.size()>0)
                         {
                           nomCategorias.add("Vie");
                           vhorCategorias.add(catE);
                         }                    
                       System.out.println("E y F No son iguales");
                       if(catF.equals(catG) && catF.size()>0) 
                          {
                             nombre = "Sab-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }else{
                            if(catF.size()>0)
                             {
                               nomCategorias.add("Sab");
                               vhorCategorias.add(catF);
                             }
                            if(catG.size()>0)
                             {
                               nomCategorias.add("Dom");
                               vhorCategorias.add(catG);
                             }                    
                          }
                    }
                 }
                  //hasta aqui la modificacion 25/01/2008 ->2
              }
           } 
           else
           {
            if(catB.size()>0)
             {
               nomCategorias.add("Mar");
               vhorCategorias.add(catB);
             }                 
             System.out.println("B y C No son iguales");
             if(catC.equals(catD) && catC.size()>0)
              {
                  String nombre = "Mie-Jue";  
                  System.out.println("C y D son iguales zczcd");
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    nombre = nombre + "-Vie"; 
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                      }
                 }
              }
             else
             {
                if(catC.size()>0)
                 {
                   nomCategorias.add("Mie");
                   vhorCategorias.add(catC);
                 }                System.out.println("C y D No son iguales");
                 if(catD.equals(catE) && catD.size()>0)
                 {
                    String nombre = "Jue-Vie";
                    System.out.println("D y E son iguales");
                      if(catE.equals(catF) && catE.size()>0)
                      {
                       nombre = nombre + "-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                           nomCategorias.add(nombre);
                           vhorCategorias.add(catG);
                          }else{
                            if(catF.size()>0)
                             {
                               nomCategorias.add("Sab");
                               vhorCategorias.add(catF);
                             }
                            if(catG.size()>0)
                             {
                               nomCategorias.add("Dom");
                               vhorCategorias.add(catG);
                             }                    
                          }
                          
                      }
                      else{
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catE);
                           if(catF.equals(catG) && catF.size()>0) 
                           {
                            nomCategorias.add("Sab-Dom");
                            vhorCategorias.add(catG);
                           }else{
                             if(catF.size()>0)
                              {
                                nomCategorias.add("Sab");
                                vhorCategorias.add(catF);
                              }
                             if(catG.size()>0)
                              {
                                nomCategorias.add("Dom");
                                vhorCategorias.add(catG);
                              }                    
                           }                      
                      }

                 } 
                 else
                 {
                    if(catD.size()>0)
                     {
                       nomCategorias.add("Jue");
                       vhorCategorias.add(catD);
                     }                    
                    System.out.println("D y E No son iguales");
                   if(catE.equals(catF) && catE.size()>0)
                      {
                       String nombre = "Vie-Sab"; 
                          if(catF.equals(catG) && catF.size()>0) 
                          {
                            nombre = nombre + "-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }
                      }
                    else
                    {
                        if(catE.size()>0)
                         {
                           nomCategorias.add("Vie");
                           vhorCategorias.add(catE);
                         }                    
                       System.out.println("E y F No son iguales");
                       if(catF.equals(catG) && catF.size()>0) 
                          {
                            String nombre = "Sab-Dom";
                            nomCategorias.add(nombre);
                            vhorCategorias.add(catG);
                          }else{
                            if(catF.size()>0)
                             {
                               nomCategorias.add("Sab");
                               vhorCategorias.add(catF);
                             }
                            if(catG.size()>0)
                             {
                               nomCategorias.add("Dom");
                               vhorCategorias.add(catG);
                             }                    
                          }
                    }//else(E==F)
                 }//else(D==E)
             }//else(C==D)
           }//else(B==C)
           
        }//else(A==B)
        
        System.out.println("Las categorias son: "+nomCategorias);
        System.out.println("Los horarios de las categorias son: "+vhorCategorias);
    }
    
    public void salirAplicacion(){
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir del Rol Manual?");
          psn.setVisible(true);
          if(respuestaSN)
              this.dispose();
        }
    
    public void setListos(int index){
        this.listos.set(index,"0");
    }
    
    public void setNumDias(int index, int dias){
        this.numDias.set(index,""+dias);
    }
    
    public void setAutobuses(int index, Vector buses){
        this.autobuses.set(index,buses);
    }
    
    public boolean getListos(){
        System.out.println("entra averificar si estan listos");
        boolean listo = true;
        for(int i=0; i<ncategorias;i++){
             System.out.println("listos.get("+i+").toString().equals(-1) = "+listos.get(i).toString().equals("-1"));
            if(listos.get(i).toString().equals("-1"))
                listo = false;
        }
        for(int i=0; i<ncategorias;i++){
            System.out.println("numDias.get("+i+").toString().equals(-1) = "+numDias.get(i).toString().equals("-1"));
            if(numDias.get(i).toString().equals("-1"))
                listo = false;
        }
        for(int i=0; i<ncategorias;i++){
            Vector vb = (Vector)autobuses.get(i);
            System.out.println("autobuses.get("+i+") = "+vb.size());
            if(vb.size()==0)
                listo = false;
        }
        return listo;
    }
    
    public void setHorariosRol(int index,Vector horariosRol)
    {
        horRoles.set(index,horariosRol);
    }
    
    public boolean verificarAntesGuardarRol(int cat){
        boolean regresa = true;
        for(int i=0; i<nomCategorias.size(); i++) 
           {        
             System.out.println("Manda a verificar todos antes de guardar = "+i+" ==> "+nomCategorias.get(i).toString());
             if(i!=cat)
             {
               jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
               if(!pn.guardarRol(false))
                  regresa = false;
             }
           }
     return regresa;
    }
    
    public void guardarRoles(){
        if(!guardarRolMaestro)
        {
            guardarRolMaestro = true;
            //Se crea el Rol Maestro
            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
           System.out.println("Se guarda el Rol Maestro: ");
           System.out.println("     Nombre    : "+nombreRolMaestro);
           System.out.println("     Servicio  : "+idrservicio);
           System.out.println("     Flotilla  : "+flotillaSeleccionada.getFlotillaId());
           System.out.println("     F. inicial: "+fi);
           System.out.println("     F. final  : "+ff);
           System.out.println("\nSe guardan los Roles Base: ");
           TmsRolesMaestroTbl rolMaestro = new TmsRolesMaestroTbl();
           rolMaestro.setRolMaestroNombre(nombreRolMaestro);
           TmsServiciosTbl serv = busquedas.serviciosTblFacadeRemote.find(BigDecimal.valueOf(idrservicio));
           rolMaestro.setServicioId(serv);
           rolMaestro.setFlotillaId(flotillaSeleccionada);
           rolMaestro.setOfertaServicioNombre(nombreOferta);
           rolMaestro.setFechaInicial(new Date(fi.getTime()));
           rolMaestro.setFechaFinal(new Date(ff.getTime()));
           rolMaestro.setHabilitado("S");
           rolMaestro.setCreadoPor(BigInteger.valueOf(usuarioId));
           rolMaestro.setFechaCreacion(new Date(fecha_servidor.getTime()));
           rolMaestro.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
           rolMaestro.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           rm = busquedas.rolesMaestroTblFacadeRemote.create(rolMaestro);
        }
        
        Vector vIdsRB=(Vector)busquedas.variosFacadeRemote.buscaRolesBase(rm.getRolMaestroId().longValue());
        if(vIdsRB.size()>0){
            String rolesBase = "";
            for(int i=0; i<vIdsRB.size(); i++)
            {
                if(i==0)
                {
                    Vector val = (Vector)vIdsRB.get(i);
                    rolesBase = rolesBase + val.get(0).toString();
                }
                else
                {
                    Vector val = (Vector)vIdsRB.get(i);
                    rolesBase = rolesBase + ","+val.get(0).toString();
                }
            }
         busquedas.variosFacadeRemote.eliminarRolesBaseLineas(rolesBase);
         busquedas.variosFacadeRemote.eliminarRolesBase(rm.getRolMaestroId().longValue());
        }
            
        
   for(int i=0; i<nomCategorias.size(); i++) 
   {        
     System.out.println("Manda a guardar = "+i+" ==> "+nomCategorias.get(i).toString());
     jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
     pn.guardarBD(rm,nomCategorias.get(i).toString());
   }
        new jdlg_informacion("¡El rol se guardó satisfactoriamente!","","Rol guardado").setVisible(true);
        
    }
    
    public void agregarAutobusATodos(String pbus, int ncat){
       for(int i=0; i<nomCategorias.size(); i++) 
       {        
         //System.out.println("Manda a guardar = "+i+" ==> "+nomCategorias.get(i).toString());
            jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
            pn.agregarBus(pbus);
       }
    }
    
   public void agregarAutobusATabla(Object bus, int fila, int column, int ncat){
       for(int i=0; i<nomCategorias.size(); i++) 
       {        
          if(i!=ncat)
          {
              jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
              pn.agregarBusATabla(bus, fila,  column);
          }
            
       }
    }
      
  public void muestrabusesPrueba(){    
      for(int i=0; i<nomCategorias.size(); i++) 
       {        
            jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
            System.out.println("Autobuses "+i+" ==> "+nomCategorias.get(i).toString()+" con: " +pn.getBusesAsiganos());

       }
  }
    public void resetBuses(){
       for(int i=0; i<nomCategorias.size(); i++) 
       {        
         jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
         pn.resetBusesAsiganos();
       }
    }
    
    
    public void cargarRoles(){
        salida=false;
       for(int i=0; i<nomCategorias.size(); i++) 
       {        
         System.out.println("Manda a cargar = "+nomCategorias.get(i).toString());
         jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
         Vector catGuar = new Vector();
         Vector vIdsRB=(Vector)busquedas.variosFacadeRemote.buscaNombreRolesBase(rm.getRolMaestroId().longValue());
            for(int j=0; j<vIdsRB.size(); j++)
            {
                    Vector val = (Vector)vIdsRB.get(j);
                    catGuar.add(val.get(0).toString());
            }
         System.out.println("catGuar: "+ catGuar);
         System.out.println("nomCategorias: "+ nomCategorias);
         boolean iguales = true;
         for(int k=0;  k<catGuar.size(); k++)
         {
             if(nomCategorias.indexOf(catGuar.get(k).toString())<0)
                 iguales = false;
         }
         for(int k=0;  k<nomCategorias.size(); k++)
         {
             if(catGuar.indexOf(nomCategorias.get(k).toString())<0)
                 iguales = false;
         }

        if(!iguales)
         {
             new jdlg_error("¡Los roles base seleccionados no coinciden con los guardados en la Base de Datos! ","El sistema no puede seguir cargando el Rol ","Error de datos").setVisible(true); 
             salida=true;
             return;
         }
         
         boolean estado = pn.cargarRolBaseBD(rm,nomCategorias.get(i).toString());
         if(!estado)
         {
           if (!pn.isHorarioEliminado())
            new jdlg_error("¡Uno varios horarios guardados previamente fueron eliminados de la oferta de servicio o no se estan seleccionando! ","El sistema no puede seguir cargando el Rol ","Error de datos").setVisible(true);               
           else
             new jdlg_error("¡El rol base "+nomCategorias.get(i).toString()+" no se encuentra registrado en la Base de Datos! ","El sistema no puede seguir cargando el Rol ","Error de datos").setVisible(true); 
           salida=true;
           break;
         }
       }
    }
    
    public void salir(){
        this.dispose();
    }
    
    public void completo(){
        this.dispose(); 
        System.out.println("------------------- cemp"+cemp);
        
        new jdlg_RolMaestro(busquedas,nomCategorias,nombreRolMaestro, fi, ff, Integer.valueOf(numDias.get(0).toString()),horRoles, idrservicio, indiceCorridas, idCorridas,usuarioId,flotillaSeleccionada, coris, cdes, cemp,(Vector)autobuses.get(0),vrutes).setVisible(true);
    }
    
    public boolean getCorrecto(){
        boolean correct = true;
        for(int i=1; i<ncategorias;i++){
            if(!numDias.get(i).toString().equals(numDias.get(i-1).toString()))
                correct = false;
        }
        return correct;
    }
    
    public void limpiarodosListos(){
      todoListos = new Vector();  
    }
    
    public void agregarListo(String pnomcat){
        todoListos.add(pnomcat);
    }
    
    public boolean todosListos(){
        boolean listo = true;
        for(int i=0; i<nomCategorias.size();i++)
        {
            if(todoListos.indexOf(nomCategorias.get(i).toString())==-1)
                listo = false;
        }
        return listo;
    }
            
    public boolean getBusesCorrecto(){
        boolean correct = true;
        for(int i=1; i<ncategorias;i++){
            Vector vb1 = (Vector)autobuses.get(i);
            Vector vb2 = (Vector)autobuses.get(i - 1);
            if(!vb1.equals(vb2))
                correct = false;
        }
        return correct;
    }

    public void verificaEnrolamientos(String nomcat){
     System.out.println("se presiono F11");
        verificadas = new Vector();
//     for(int i=0; i<nomCategorias.size(); i++) 
//      {
//        System.out.println("verificadas.indexOf("+nomcat+") = "+verificadas.indexOf(nomcat));
//         if(verificadas.indexOf(nomcat)==-1)
//         {
//           jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
//           pn.verEnrolamiento();
//         }
//      }
//
//     if(verificadas.indexOf(nomcat)==-1)
//         verificadas.add(nomcat);
     for(int i=0; i<nomCategorias.size(); i++) 
      {
        System.out.println("verificadas.indexOf("+nomcat+") = "+verificadas.indexOf(nomCategorias.get(i).toString()));
         if(verificadas.indexOf(nomCategorias.get(i).toString())==-1)
         {
           jpnl_RolCategoria2 pn = (jpnl_RolCategoria2)jtabpnl_categorias.getComponentAt(i);
           pn.verEnrolamiento();
           verificadas.add(nomCategorias.get(i).toString());
         }
         //if(verificadas.indexOf(nomcat)==-1)
          //verificadas.add(nomcat);
      }

        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jtabpnl_categorias = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jtabpnl_categorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtabpnl_categoriasKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jtabpnl_categorias, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jtabpnl_categorias, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtabpnl_categoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtabpnl_categoriasKeyPressed
     if(evt.getKeyCode() == evt.VK_ESCAPE)
           salirAplicacion();
    }//GEN-LAST:event_jtabpnl_categoriasKeyPressed
    

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jtabpnl_categorias;
    // End of variables declaration//GEN-END:variables
    
    public boolean getHaycorridas() {
        return haycorridas;
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

    public boolean isGuardarRolMaestro() {
        return guardarRolMaestro;
    }

    public boolean isSalida() {
        return salida;
    }
}
