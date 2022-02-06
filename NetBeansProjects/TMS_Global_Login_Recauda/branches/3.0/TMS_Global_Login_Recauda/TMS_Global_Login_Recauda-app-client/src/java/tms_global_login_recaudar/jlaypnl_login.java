
package tms_global_login_recaudar;

import eausuariofuncionr.AppPrincipal;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.ToolTipManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;
import tms_TextFields.JNumberTextField;
import tmsgloballoginr.entidad.TmsAuditoriaTbl;
import tmsgloballoginr.entidad.TmsSesionesGlobalTbl;

public class jlaypnl_login extends JLayeredPane {
    private JButton jbtn_salir = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JNumberTextField jtxt_usuario = new JNumberTextField();
    private ImageIcon imagenfondo = new ImageIcon(jlaypnl_login.class.getResource("splashlogin6.jpg"));
    private JLabel jLabel2 = new JLabel();
    private JPasswordField jtxt_contraseña = new JPasswordField(){
    public Point getToolTipLocation(MouseEvent event) {
        return new Point(0, getHeight());
    }
};
    private JButton jbtn_ingresar = new JButton();
    private JLabel jLabel3 = new JLabel();

    private TMSLoginGlobalManagedBean ventaLoginCnx;
    private PcInfo estaPC = new PcInfo();
    private login_main principal;
    private long id_menu;
    private long id_usuario;
    private String numero_usuario;
    private String nombre_usuario;
    private String contraseña_usuario;
    private String nombre_perfil;
    private long id_sesionglobal;
    private JList lista;
    private boolean valorsesion = false;
    private ToolTipManager manager;
    private ImageIcon imgCaps = new ImageIcon(getClass().getResource("/ico/CapsLockOn.png"));

    
    
    
    public jlaypnl_login(login_main p) {

        principal = p;
        JDialog jdl = new JDialog();
        jdl.setDefaultLookAndFeelDecorated(true);
        jdl.dispose();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        iniciarConexion();
        principal.setTitle("TMS Recauda v2.7.6 "+ventaLoginCnx.getNombreTerminal()+" - Inicio de Sesión");
        manager = ToolTipManager.sharedInstance();
        manager.setInitialDelay(0);        
        jtxt_contraseña.setToolTipText("");
        jtxt_contraseña.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) { }
        });        
    }

       private void jbInit() throws Exception {
        
        this.setLayout( null );
        this.setSize(new Dimension(560, 300));
        jbtn_salir.setText("Salir");
        jbtn_salir.setBounds(new Rectangle(300, 210, 80, 25));
        jbtn_salir.setFont(new Font("Dialog", 1, 11));
        jbtn_salir.setForeground(new Color(0, 77, 145));
        jbtn_salir.setMnemonic('s');
        jbtn_salir.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jbtn_salir_keyPressed(e);
                    }
                });
        jbtn_salir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtn_salir_actionPerformed(e);
                    }
                });
        jLabel1.setText("jLabel1");
        jLabel1.setBounds(new Rectangle(0, 0, 555, 310));
        jLabel1.setIcon(imagenfondo);
        jLabel1.setSize(new Dimension(560, 300));
        jtxt_usuario.setBounds(new Rectangle(270, 130, 90, 20));
        jtxt_usuario.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jtxt_usuario_keyPressed(e);
                    }
                });
        jtxt_usuario.addFocusListener(new FocusAdapter() {
                    public void focusGained(FocusEvent e) {
                        jtxt_usuario_focusGained(e);
                    }
                });
        jLabel2.setText("USUARIO:");
        jLabel2.setBounds(new Rectangle(200, 130, 60, 20));
        jLabel2.setForeground(Color.white);
        jLabel2.setFont(new Font("Dialog", 1, 12));
        jtxt_contraseña.setBounds(new Rectangle(270, 160, 90, 20));
        jtxt_contraseña.setToolTipText("");
        jtxt_contraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_contraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_contraseñaFocusLost(evt);
            }
        });
        jtxt_contraseña.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jtxt_contraseña_keyPressed(e);
                    }
                    public void keyReleased(KeyEvent e) {
                        jtxt_contraseña_keyReleased(e);
                    }
                });
        jbtn_ingresar.setText("Ingresar");
        jbtn_ingresar.setBounds(new Rectangle(205, 210, 85, 25));
        jbtn_ingresar.setFont(new Font("Dialog", 1, 11));
        jbtn_ingresar.setForeground(new Color(0, 77, 145));
        jbtn_ingresar.setMnemonic('i');
        jbtn_ingresar.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jbtn_ingresar_keyPressed(e);
                    }
                });
        jbtn_ingresar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtn_ingresar_actionPerformed(e);
                    }
                });
        jLabel3.setText("CONTRASEÑA:");
        jLabel3.setBounds(new Rectangle(175, 165, 85, 15));
        jLabel3.setFont(new Font("Dialog", 1, 12));
        jLabel3.setForeground(Color.white);
        //this.add(jLabel3, PALETTE_LAYER);
        this.add(jbtn_ingresar, PALETTE_LAYER);
        this.add(jtxt_contraseña, PALETTE_LAYER);
        //this.add(jLabel2, PALETTE_LAYER);
        this.add(jtxt_usuario, PALETTE_LAYER );
        this.add(jbtn_salir,PALETTE_LAYER );
        this.add(jLabel1, PALETTE_LAYER );
    }
       
       
        //Accion de las teclas
    private void jtxt_usuario_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER) {
            if((new String(jtxt_contraseña.getPassword())).equals("") || jtxt_usuario.getText().equals(""))
               jtxt_contraseña.requestFocus();
            else
               {
                   jbtn_ingresar.requestFocus();
                   jbtn_ingresar.doClick();
               }
        }
        if(e.getKeyCode() == e.VK_UP)
          jbtn_salir.requestFocus();
        if(e.getKeyCode() == e.VK_DOWN)
          jtxt_contraseña.requestFocus();
    }

    private void jtxt_contraseña_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER) {
            if(jtxt_usuario.getText().equals("") || (new String(jtxt_contraseña.getPassword())).equals(""))
               jbtn_ingresar.requestFocus();
            else
               {
                 jbtn_ingresar.requestFocus();
                 jbtn_ingresar.doClick();
               }
        }
        if(e.getKeyCode() == e.VK_UP)
          jtxt_usuario.requestFocus();
        if(e.getKeyCode() == e.VK_DOWN)
          jbtn_ingresar.requestFocus();
    }

private void jtxt_contraseña_keyReleased(java.awt.event.KeyEvent evt) {                                               
    if (evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
        verificaCapsLock();
        Caret caret = jtxt_contraseña.getCaret();
        Point point = caret.getMagicCaretPosition();
        showToolTip(point);
    }        
} 

private void jtxt_contraseñaFocusLost(java.awt.event.FocusEvent evt) {                                             
        jtxt_contraseña.setToolTipText("");
        Caret caret = jtxt_contraseña.getCaret();
        Point point = caret.getMagicCaretPosition();
        showToolTip(point);
    }                                            

private void jtxt_contraseñaFocusGained(java.awt.event.FocusEvent evt) {                                               
        verificaCapsLock();
        showToolTip(null);
        if((new String(jtxt_contraseña.getPassword())).equals(""))
        ;
        else
         jtxt_contraseña.selectAll();
    }
    
    private void jbtn_ingresar_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER)
           jbtn_ingresar.doClick();
        if(e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_UP)  
           jtxt_contraseña.requestFocus();
        if(e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_DOWN)  
           jbtn_salir.requestFocus();
    }

    private void jbtn_salir_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER)
            jbtn_salir.doClick();
        if(e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_UP)
            jbtn_ingresar.requestFocus();
        if(e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_DOWN)  
            jtxt_usuario.requestFocus();
                    
    }

        //Accion de los botones
    private void jbtn_ingresar_actionPerformed(ActionEvent e) {
     if((new String(jtxt_contraseña.getPassword())).equals("") || jtxt_usuario.getText().equals(""))
      {
        JOptionPane.showMessageDialog(this,"¡Debes introducir el numero de usuario y la contraseña!","Datos incompletos", JOptionPane.WARNING_MESSAGE);
        jtxt_usuario.requestFocus();
      }
     else 
      {
                //Validar Usuario
        String usuarioNumero = jtxt_usuario.getText();
        String pwdUsuario = new String(jtxt_contraseña.getPassword());
//        iniciarConexion();
        System.out.println("Entra avalidar usuario...");
        if (validarUsuario(usuarioNumero,pwdUsuario)){
                System.out.println("El Usuario si es valido...");
                id_usuario = ventaLoginCnx.getUsuarioId();
                nombre_usuario = ventaLoginCnx.getUsuarioNombre();
                numero_usuario = ventaLoginCnx.getUsuarioNumero();
                contraseña_usuario = ventaLoginCnx.getUsuarioContrasena();
                boolean sesion = true;
                sesion = sesionglobal();
                if(sesion) 
                {
                    System.out.println(" Validando Perfiles...");
                         if( ventaLoginCnx.getNumUsuarioPerfiles()>0)
                         {
                            System.out.println(" Tiene "+ventaLoginCnx.getNumUsuarioPerfiles()+" Perfiles...");
                              if(valorsesion)
                                registroAuditoria("EXITO", "Ingreso correctamente al sistema cerrando una sesion abierta");
                              else
                                registroAuditoria("EXITO", "Ingreso correctamente al sistema");
                              //System.out.println("Se abre la siguiente aplicacion con los parametros Sesion_id="+id_sesionglobal+"    Usuario_id="+id_usuario+"   This(la aplicacion Principal)");
                               new AppPrincipal(id_sesionglobal, id_usuario, nombre_usuario, numero_usuario,contraseña_usuario,principal,ventaLoginCnx.getNombreTerminal(),  ventaLoginCnx).setVisible(true);
                         }
                         else
                         {
                           JOptionPane.showMessageDialog(this,"¡Este usuario no tiene ningun perfil relacionado! \n Favor de contactar al administrador de red", "Usuario mal configurado", JOptionPane.ERROR_MESSAGE);
                           jtxt_usuario.requestFocus();
                         }
          }//if(sesion)
        }
     }
    }
    
    
   
    private void jbtn_salir_actionPerformed(ActionEvent e) {
        Jdlg_Pregunta_Salir jdps = new Jdlg_Pregunta_Salir("Confirmacion de salida");
        jdps.setVisible(true);
    }


        //Foco en los campos
    private void jtxt_usuario_focusGained(FocusEvent e) {
      if(jtxt_usuario.getText().equals(""))
      ;
      else
       jtxt_usuario.selectAll();
    }

    
    ///M E T O D O S
    private void iniciarConexion(){
        ventaLoginCnx = new TMSLoginGlobalManagedBean();
    }
    
    private void terminarConexion(){
        //limpiarCampos();
        ventaLoginCnx = null;
        jtxt_contraseña.setText("");
        jtxt_usuario.setText("");
        jtxt_contraseña.requestFocusInWindow();
        
    }
    
    private void registroAuditoria(String estado, String descripcion){
           //Agrega un registro en la tabla de auditoria
           //TmsFuncionesTbl funcion = ventaLoginCnx.funcionesFacadeRemote.getFuncionPorNumero("1000");
           TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
           auditoria.setUsuarioId(Long.valueOf(""+ventaLoginCnx.getUsuarioId()));
           auditoria.setNombreEquipo(estaPC.getHostName().toUpperCase());
           auditoria.setFuncionNumero("1000");
           auditoria.setFecha(ventaLoginCnx.FechaServidor());
           auditoria.setEstadoProceso(estado);
           auditoria.setDescripcionProceso(descripcion);
           ventaLoginCnx.getSesionesGlobalFacadeRemote().createAuditoria(auditoria,ventaLoginCnx.getIdPrefijoTerminal());
    }
    
    private boolean validarUsuario(String usuarioNumero,String pwdUsuario ){
        boolean r=false;
        int resp=ventaLoginCnx.esPwdValido(usuarioNumero,pwdUsuario);
        if(resp==0 || resp==7) {
            r = true;
            if(ventaLoginCnx.cambiarContrasena()){
                //principal.setAlwaysOnTop(false);
                jdlgActualizarPwd actualizarDlg = new jdlgActualizarPwd();//this, true);
                actualizarDlg.setVentaLoginCnx(ventaLoginCnx);
                actualizarDlg.setVisible(true);
                
                r = false;
            }
        }
        else{
            switch(resp){
                case 1: JOptionPane.showMessageDialog(null,"CNX-001: Contacte al Administrador del Sistema.","¡No existe Conexion a la Red!",JOptionPane.ERROR_MESSAGE);
                break;
                case 2: JOptionPane.showMessageDialog(this,"La contraseña no es valida.\nFavor de intentar de nuevo.","Inicio de Sesión",JOptionPane.ERROR_MESSAGE);
                break;
                case 3: JOptionPane.showMessageDialog(this,"El usuario no esta dado de alta en la Base de datos.\nFavor de intentar de nuevo","Inicio de Sesión",JOptionPane.ERROR_MESSAGE);
                break;
                case 4: JOptionPane.showMessageDialog(new JDialog(),"¡La cuenta esta suspendida!\nFavor de  contactar al Administrador del Sistema","Usuario Suspendido",JOptionPane.ERROR_MESSAGE);
                break;
                case 5: JOptionPane.showMessageDialog(new JDialog(),"¡El usuario esta dado de baja del sistema!\nFavor de  contactar al Administrador del Sistema","Usuario dado de Baja",JOptionPane.ERROR_MESSAGE);
                break;
                case 6: JOptionPane.showMessageDialog(new JDialog(),"¡El usuario no es valido en esta terminal!\nFavor de  contactar al Administrador del Sistema","Acceso invalido",JOptionPane.ERROR_MESSAGE);
                break;
            }
            //limpiarFormulario();
            r = false;
        }
        this.jtxt_usuario.setText("");
        this.jtxt_contraseña.setText("");
        this.jtxt_usuario.requestFocus();
        return r;
    }    
    
    private boolean sesionglobal()
    {
      boolean valor = false;  
      System.out.println("sesionGlobal:"+id_usuario);
       List<TmsSesionesGlobalTbl> sesion = ventaLoginCnx.getSesionesGlobalFacadeRemote().getSesionPorUsuario("ABIERTA",id_usuario);
       System.out.println(sesion);
       if(sesion.size()==0)//si no encuentra ninguna sesion abierta se registra con una nueva sesion
       {
           TmsSesionesGlobalTbl sesionnueva = new TmsSesionesGlobalTbl();
           sesionnueva.setEstadoSesion("ABIERTA");
           sesionnueva.setUsuarioId(id_usuario);
           sesionnueva.setFechaInicio(ventaLoginCnx.FechaServidor());
           sesionnueva.setNombreEquipo(estaPC.getHostName().toUpperCase());
           sesionnueva.setDireccionIp(estaPC.getHostAddress());
           id_sesionglobal =  ventaLoginCnx.getSesionesGlobalFacadeRemote().createSesion(sesionnueva, ventaLoginCnx.getIdPrefijoTerminal());
           valor = true;
       }
       else
       {
           TmsSesionesGlobalTbl ses = sesion.get(0);
         System.out.println(sesion.get(0).getUsuarioId());
         Jdlg_Pregunta_Cerrar_Sesion jdlsesion = new  Jdlg_Pregunta_Cerrar_Sesion("¡Sesion Abierta!", ses.getNombreEquipo());
         jdlsesion.setVisible(true);
         if(valorsesion)
         {
               ventaLoginCnx.getSesionesGlobalFacadeRemote().UpdateSesion(sesion.get(0).getNumeroSesion(), ventaLoginCnx.FechaServidor());
               TmsSesionesGlobalTbl sesionnueva = new TmsSesionesGlobalTbl();
               sesionnueva.setEstadoSesion("ABIERTA");
               sesionnueva.setUsuarioId(id_usuario);
               sesionnueva.setFechaInicio(ventaLoginCnx.FechaServidor());
               sesionnueva.setNombreEquipo(estaPC.getHostName().toUpperCase());
               sesionnueva.setDireccionIp(estaPC.getHostAddress());
               id_sesionglobal = ventaLoginCnx.getSesionesGlobalFacadeRemote().createSesion(sesionnueva, ventaLoginCnx.getIdPrefijoTerminal());
               valor = true;
         }
       }
       return valor;
    }
    

    public void showToolTip(Point point) {
    MouseEvent e;
    if (point != null) {
        e = new MouseEvent(jtxt_contraseña, MouseEvent.MOUSE_MOVED, 0, 0, (int)point.getX(), (int)point.getY(), 0, false);
    }
    else {
        e = new MouseEvent(jtxt_contraseña, MouseEvent.MOUSE_MOVED, 0, 0, 0, 0, 0, false);
    }                
    manager.mouseMoved(e);
}

    private void verificaCapsLock() {
        boolean caps = this.getToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (caps == true) {
            int dismissDelay = Integer.MAX_VALUE;
           ToolTipManager.sharedInstance().setDismissDelay(dismissDelay);
           jtxt_contraseña.setToolTipText("<html><img src=\"" + imgCaps + "\"></html>");
        }
        else {
           int dismissDelay = ToolTipManager.sharedInstance().getDismissDelay();
           jtxt_contraseña.setToolTipText("");
        }
    }

    
    
    //C L A S E S 
     class Jdlg_Pregunta_Salir extends JDialog {
             private JLabel jLabel1 = new JLabel();
             private JLabel jLabel2 = new JLabel();
             private JLabel jLabel3 = new JLabel();
             private ImageIcon imagen_pregunta = new ImageIcon(Jdlg_Pregunta_Salir.class.getResource("pregunta.gif"));
             private JButton jbtn_respuesta_si = new JButton();
             private JButton jbtn_respuesta_no = new JButton();
                          
             public Jdlg_Pregunta_Salir(String title) {
                 this.setTitle(title);
                 this.setModal(true);
                 this.setDefaultLookAndFeelDecorated(true);
                 this.setUndecorated(true);
                 this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
                 this.setAlwaysOnTop(true);
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
                 //jLabel1.setText("     TMS Recaudacion sin cerrar sesion?");
                 //jLabel1.setBounds(new Rectangle(45, 35, 265, 15));
                 //jLabel1.setFont(new Font("Arial", 1, 12));
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
                 jLabel3.setText(" ¿Seguro que desea salir de la Aplicacion?");
                 this.getContentPane().add(jLabel3, null);
                 this.add(jbtn_respuesta_no, null);
                 this.add(jbtn_respuesta_si, null);
                 this.add(jLabel2, null);
                 //this.add(jLabel1, null);
             }

             private void jbtn_respuesta_si_actionPerformed(ActionEvent e) {
                 System.exit(0);
             }

             private void jbtn_respuesta_no_actionPerformed(ActionEvent e) {
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
         }    
     
    class Jdlg_Pregunta_Cerrar_Sesion extends JDialog {
        private JLabel jLabel1 = new JLabel();
        private JLabel jLabel2 = new JLabel();
        private JLabel jLabel3 = new JLabel();
        private ImageIcon imagen_pregunta = new ImageIcon(Jdlg_Pregunta_Cerrar_Sesion.class.getResource("pregunta.gif"));
        private JButton jbtn_respuesta_si = new JButton();
        private JButton jbtn_respuesta_no = new JButton();
        private String pc ="";
       
        public Jdlg_Pregunta_Cerrar_Sesion(String title, String pc1) {
            this.setTitle(title);
            this.pc = pc1;
            this.setModal(true);
            this.setDefaultLookAndFeelDecorated(true);
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            this.setAlwaysOnTop(true);
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
            jLabel1.setText(pc+" ¿Desea cerra esa sesion?");
            jLabel1.setBounds(new Rectangle(45, 35, 275, 15));
            jLabel1.setFont(new Font("Arial", 1, 12));
            jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
            jLabel2.setSize(new Dimension(35, 45));
            jLabel2.setIcon(imagen_pregunta);
            jbtn_respuesta_si.setText("Si");
            jbtn_respuesta_si.setBounds(new Rectangle(115, 60, 50, 25));
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
            jbtn_respuesta_no.setBounds(new Rectangle(175, 60, 50, 25));
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
            jLabel3.setBounds(new Rectangle(45, 10, 285, 15));
            jLabel3.setFont(new Font("Arial", 1, 12));
            jLabel3.setText("Este usuario tiene una sesion abierta en");
            this.getContentPane().add(jLabel3, null);
            this.add(jbtn_respuesta_no, null);
            this.add(jbtn_respuesta_si, null);
            this.add(jLabel2, null);
            this.add(jLabel1, null);
        }

        private void jbtn_respuesta_si_actionPerformed(ActionEvent e) {
            valorsesion= true;
            this.dispose();
        }

        private void jbtn_respuesta_no_actionPerformed(ActionEvent e) {
            valorsesion= false;
            this.dispose();
            //System.exit(0);
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
    }         
}
