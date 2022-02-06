/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.works;

import DialogosIncidencias.JDlgAceptar;
import java.awt.Color;
import java.util.concurrent.ExecutionException;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingUtilities;// SwingWorker;
import javax.swing.table.DefaultTableModel;
import tms_excepciones_incidencias.exceptions.ControlException;
import tms_excepciones_incidencias.exceptions.ExisteExcepcionIncidenciaException;
import tms_incidencias.SesionVenta;
import tms_incidencias.entidad.TmsExcepcionIncidenciaTbl;

/**
 *Worker para guardar una excepcion de incidencia
 * en la base de datos
 * @author Osvaldo Sanchez
 */
public class GuardarExcepcionIncidencia extends SwingWorker<Integer, Integer> {

    private TmsExcepcionIncidenciaTbl excepcion;
    private SesionVenta sesionVenta;
    private JDialog dialogoEspera;
    private JTable jTableExcepciones;
    /**
     * Constructor
     * @param excepcion
     * @param sesionVenta
     * @param dialogoEspera
     */
    public GuardarExcepcionIncidencia(TmsExcepcionIncidenciaTbl excepcion, SesionVenta sesionVenta, JDialog dialogoEspera,JTable jTableExcepciones) {
        this.excepcion = excepcion;
        this.sesionVenta = sesionVenta;
        this.dialogoEspera = dialogoEspera;
        this.jTableExcepciones=jTableExcepciones;
    }

    @Override
    protected Integer doInBackground() {
        Integer resultado = -1;

        try {
            //Se guarda la excepcion y se cachan las excepciones
            sesionVenta.guardarExcepcionIncidencia(excepcion);
            resultado = 1;
        } catch(ControlException e){
            System.err.println(e);
            resultado = -1;
        }
        catch (ExisteExcepcionIncidenciaException e1) {
            System.err.println(e1.getMensaje());
            resultado = 2;
        }
        return resultado;
    }

    @Override
    protected void process(java.util.List<Integer> c) {
    }

    @Override
    protected void done() {
        Integer resultado = 0;
        try {
            resultado = get();
        } catch (InterruptedException e) {
        } catch (ExecutionException ex) {
        }
        //Se oculta el dialog de espera
        dialogoEspera.setVisible(false);
        //Si se guardo con exito en la base de datos
        //Se muestra el mensaje en pantalla
        if (resultado == 1) {
            new JDlgAceptar("¡Excepcion guardada!", "Se guardo exitosamente la Excepcion.", Color.BLUE);
             CargarExcepcionesIncidencia c = new CargarExcepcionesIncidencia((DefaultTableModel) jTableExcepciones.getModel(), dialogoEspera, sesionVenta);
            c.execute();
        } 
        //Si ya existe la excepcion se muestra el mensaje de error
        else if(resultado==2){
            new JDlgAceptar("¡Excepcion ya existe!", "La Excepcion ya se encuentra registrada.", Color.RED);
        }
        //Si ocurrio un error al momento de guardar se muestra un mensaje de error
        else if(resultado==-1){
            new JDlgAceptar("¡ERROR!", "Ocurrio un error al momento de guardar.", Color.RED);
        }
        
    }
}
