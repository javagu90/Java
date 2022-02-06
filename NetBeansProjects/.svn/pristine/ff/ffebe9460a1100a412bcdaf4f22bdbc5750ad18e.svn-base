/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.works;

import DialogosIncidencias.JDlgAceptar;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JDialog;
//import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import tms_excepciones_incidencias.exceptions.ControlException;
import tms_incidencias.SesionVenta;
import tms_incidencias.modelo.ExcepcionIncidencia;

/**
 *
 * @author OsvaldoSan
 */
public class CargarExcepcionesIncidencia extends SwingWorker<Integer, ExcepcionIncidencia> {

    private final DefaultTableModel modelo;
    private SesionVenta sesionVenta;
    private JDialog dialogoEspera;

    /**
     * Constructor
     * @param modelo1
     * @param progreso1
     * @param panel
     * @param sesionVenta
     */
    public CargarExcepcionesIncidencia(DefaultTableModel modelo1, JDialog dialogoEspera, SesionVenta sesionVenta) {
        this.modelo = modelo1;
        this.dialogoEspera = dialogoEspera;
        this.sesionVenta = sesionVenta;
    }

    //<snip>Use SwingWorker to asynchronously load the data
    // background task let a parser do its stuff 
    @Override
    public Integer doInBackground() {
        Integer i = 0;

        //Se eliminan los registros previos de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        try {
            List<ExcepcionIncidencia> resultado = null;           
            resultado=sesionVenta.getAllExcepcionesIncidencias();

            if (resultado != null && resultado.size() > 0) {
                i = resultado.size();
                for (ExcepcionIncidencia excepcion : resultado) {
                    publish(excepcion);
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                    }

                }
            }

        } catch(ControlException e){
            System.err.println(e);
            i = -1;
        }

        return i;
    }
    //        </snip> 

    protected void process(List<ExcepcionIncidencia> registros) {
        if (registros != null && registros.size() > 0) {
            //Se crea el array para agregarse a la tabla         
            Object[] array = new Object[5];
            for (ExcepcionIncidencia excepcion : registros) {
                array[0] = excepcion.getId();
                array[1] = excepcion.getIncidencia1();
                array[2] = excepcion.getIncidencia2();
                array[3] = excepcion.getUsuario();
                array[4] = excepcion.getFechaCreacion();
                modelo.addRow(array);
            }
        }
    }

    @Override
    protected void done() {
        dialogoEspera.setVisible(false);
//Si no se encontraron registros se muestra un mensaje
        try {
            Integer op = get();
            if (op == 0) {
                /* JOptionPane.showMessageDialog(panel,
                "No se encontraron registros, vuelva a interntarlo",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);*/
            }
 else{
                if (op == -1) {
                    new JDlgAceptar("¡ERROR!", "Ocurrio un error al momento de buscar excepciones.", Color.RED);
                /* JOptionPane.showMessageDialog(panel,
                "No se encontraron registros, vuelva a interntarlo",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);*/
            }
 }
        } catch (InterruptedException e) {
        } catch (ExecutionException e1) {
        }
    }
}
