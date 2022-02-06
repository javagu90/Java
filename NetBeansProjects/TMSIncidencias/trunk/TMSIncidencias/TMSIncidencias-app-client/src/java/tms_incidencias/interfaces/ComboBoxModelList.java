/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.interfaces;

/**
 *
 * @author OsvaldoSan
 */
import java.util.List;
import javax.swing.ComboBoxModel;

/**
 * Implementacion {@code ComboBoxModel} que permite agregar elementos
 * de una lista
 */
public interface ComboBoxModelList<T> extends ComboBoxModel {

    void add(List<T> elementsToAdd);
    void removeAllElements();
    List<T> getElements();

}