/*
 * JIFPSD.java
 *
 * Created on 15 de agosto de 2007, 12:16 PM
 */

package tms_psd;

import java.awt.Color;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.InternalFrameEvent;
import tms_asigsust.JPnlAutobus;
import tms_psd.util.BarraMensajes;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.JDlgSiNo;

/**
 *
 * @author  ocruz
 */
public class JIFPSD extends JInternalFrame{
    
    /**
     * Creates new form JIFPSD
     */
    public JIFPSD(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        sesionVenta = new SesionVenta(this.USUARIO_ID, this.USUARIO_NUMERO,
                datosIniciales.get(5).toString(), Integer.valueOf(datosIniciales.get(6).toString()));
        int inicio = sesionVenta.procesoInicial();
        System.out.println("TimeZone(Antes): " + TimeZone.getDefault());
        TimeZone.setDefault(this.sesionVenta.getSesionPSDFacate().getTimeZone());
        System.out.println("TimeZone(Despues): " + TimeZone.getDefault());
        System.out.println("Inicio1: "+inicio);
        if(inicio==0) inicio = sesionVenta.procesoSecundario();
       System.out.println("Inicio2: "+inicio); 
        
        switch(inicio){
            case -1: DialogoAceptar = new JDlgAceptar("¡No existe conexion con la base de datos!", "Contacte al administrador del sistema.", Color.RED); break;
            case 1: DialogoAceptar = new JDlgAceptar("¡Configuracion de rutas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 2: DialogoAceptar = new JDlgAceptar("¡Configuracion de empresas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 3: DialogoAceptar = new JDlgAceptar("¡Configuracion de servicios incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 4: DialogoAceptar = new JDlgAceptar("¡Configuracion de origenes/destinos incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case -5: DialogoAceptar = new JDlgAceptar("¡Configuracion de parametros incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 5: DialogoAceptar = new JDlgAceptar("¡Configuracion de terminal local incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 10: DialogoAceptar = new JDlgAceptar("¡Configuracion de DBLink Central incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 6: DialogoAceptar = new JDlgAceptar("¡Configuracion de flotillas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 7: DialogoAceptar = new JDlgAceptar("¡Configuracion de estados de corrida incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 8: case 9: DialogoAceptar = new JDlgAceptar("¡Configuracion de Rutas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 13: DialogoAceptar = new JDlgAceptar("¡Configuracion de acciones de operador incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 15: DialogoAceptar = new JDlgAceptar("¡Configuracion de operadores incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 16: DialogoAceptar = new JDlgAceptar("¡Configuracion de terminales incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 17: DialogoAceptar = new JDlgAceptar("¡Configuracion de plantillas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 18: DialogoAceptar = new JDlgAceptar("¡Configuracion de servicios/empresas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
        }
        if(inicio!=0) {
            this.inicioGral=false;
            return;
        }
        this.inicioGral=true;
        jPnlPsdX = new JPnlPsdX(sesionVenta, this, this.datosIniciales);
        jPnlAutobus = new JPnlAutobus(sesionVenta);
        initComponents();
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jPnlPsdX.setComponentes(this);
        jPnlAutobus.setComponentes(this);
    }

    public boolean getinicioGral(){ return this.inicioGral; }
    public void setMensaje(String msg){
        jLblBarraEstado.setText(msg);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblBarraEstado = new javax.swing.JLabel();
        jTbpPrincipal = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setTitle("PSD Rev.13.08.14");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLblBarraEstado.setText("<html><font color=ff0000>&#171; &#187;</font> Seleccionar Panel | <font color=ff0000>ENTER</font> Buscar Corridas | <font color=ff0000>F1</font> Nueva oferta | <font color=ff0000>F4</font> Cerrar aplicacion</html>");

        jTbpPrincipal.add("Corridas", jPnlPsdX);
        //jTbpPrincipal.add("Autobuses", jPnlAutobus);
        jTbpPrincipal.setFocusTraversalKeysEnabled(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
            .add(jTbpPrincipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jTbpPrincipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        jLblBarraEstado.setText(barraMensajes.getMsgComun(0));
        jTbpPrincipal.setSelectedIndex(0);
        jPnlPsdX.setFoco();
    }//GEN-LAST:event_formComponentShown
    
    public void setTab(int tab){
        switch(tab){
            case 0: jTbpPrincipal.setSelectedIndex(0); break;
            //case 1: jTbpPrincipal.setSelectedIndex(1); break;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JTabbedPane jTbpPrincipal;
    // End of variables declaration//GEN-END:variables
    private Vector datosIniciales = new Vector();
    private long MENU_ID;
    private long USUARIO_ID;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID;
    
    private SesionVenta sesionVenta=null;
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    
    public JPnlPsdX jPnlPsdX;
    private JPnlAutobus jPnlAutobus;
    
    private BarraMensajes barraMensajes = new BarraMensajes();

    private boolean inicioGral;
}