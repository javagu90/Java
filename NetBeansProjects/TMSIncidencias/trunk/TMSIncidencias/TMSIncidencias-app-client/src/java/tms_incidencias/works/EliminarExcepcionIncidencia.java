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
//import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import tms_excepciones_incidencias.exceptions.ControlException;
import tms_incidencias.SesionVenta;

/**
 *Worker para guardar una excepcion de incidencia
 * en la base de datos
 * @author Osvaldo Sanchez
 */
public class EliminarExcepcionIncidencia extends SwingWorker<Integer, Integer> {

    private Short idExcepcion;
    private SesionVenta sesionVenta;
    private JDialog dialogoEspera;
    private JTable jTableExcepciones;

    /**
     * Constructor
     * @param excepcion
     * @param sesionVenta
     * @param dialogoEspera
     */
    public EliminarExcepcionIncidencia(Short idExcepcion, SesionVenta sesionVenta, JDialog dialogoEspera,JTable jTableExcepciones) {
        this.idExcepcion = idExcepcion;
        this.sesionVenta = sesionVenta;
        this.dialogoEspera = dialogoEspera;
        this.jTableExcepciones=jTableExcepciones;
    }

    @Override
    protected Integer doInBackground() {
        Integer resultado = -1;

        try {
            //Se elimina la excepcion y se cachan las excepciones
            sesionVenta.eliminarExcepcionIncidencia(idExcepcion);
            resultado = 1;
        } catch (ControlException e1) {
            System.err.println(e1);
            resultado = -1;
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
        //Si se elimino con exito en la base de datos
        //Se muestra el mensaje en pantalla
        if (resultado == 1) {
            new JDlgAceptar("¡Excepcion eliminada!", "Se elimino exitosamente la Excepcion.", Color.BLUE);
             CargarExcepcionesIncidencia c = new CargarExcepcionesIncidencia((DefaultTableModel) jTableExcepciones.getModel(), dialogoEspera, sesionVenta);
            c.execute();
        }

        //Si ocurrio un error al momento de eliminar se muestra un mensaje de error
        else if(resultado==-1){
            new JDlgAceptar("¡ERROR!", "Ocurrio un error al momento de eliminar.", Color.RED);
        }

    }
}

