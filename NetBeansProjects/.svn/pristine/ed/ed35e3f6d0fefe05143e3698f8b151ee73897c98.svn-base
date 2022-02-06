package tmstraficomain;

import com.sun.jndi.ldap.ManageReferralControl;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.JDialog;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tms_desktop.MDIDesktopPane;

public class login_main extends JFrame {
    private Image iml = Toolkit.getDefaultToolkit().getImage(login_main.class.getResource( "logo_ventana.gif"));    
    private static MDIDesktopPane dskPrincipal;
    boolean ejecutar = true;  
    public login_main()
        {
        JDialog jdl = new JDialog();
        jdl.setDefaultLookAndFeelDecorated(true);
        jdl.dispose();
          Runnable nuevoCliente = new CD_PingThread(); 
          Thread hilo = new Thread(nuevoCliente);
          hilo.start();  
        //this.setAlwaysOnTop(true);
        this.setTitle("TMS - Inicio de Sesión");
        this.setResizable(false);
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

      public static void main(String Args[] ) throws IOException, MalformedURLException, ClassNotFoundException
        { 
        login_main v = new login_main();
        }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(565, 335));
        this.setIconImage(iml);
        jlaypnl_login jpanel_login =  new jlaypnl_login(this);
        getContentPane().add(jpanel_login,BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        this.setVisible(true);
        //this.hacerVisible();
        
        }


 public class CD_PingThread extends  Thread {
  public void run() {
      
        try {
            new minimoCliente();
        } catch (IOException ex) {
            ex.printStackTrace();
        }      
    while(ejecutar) {
      System.out.print("ping");
    }
  }
}

    
}
