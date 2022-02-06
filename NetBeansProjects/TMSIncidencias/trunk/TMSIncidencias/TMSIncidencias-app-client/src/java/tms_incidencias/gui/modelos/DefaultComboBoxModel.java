/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.gui.modelos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import tms_incidencias.interfaces.ComboBoxModelList;
/**
 *
 * @author OsvaldoSan
 */
public class DefaultComboBoxModel<T> extends AbstractListModel implements ComboBoxModelList<T> {

    List<T> objects;
    T selectedObject;

   /**
    * Constructor vacio
    */
    public DefaultComboBoxModel() {
        objects = new ArrayList<T>();
    }

    /**
     * Constructor DefaultCustomComboBoxModel inicializado
     *con un array o lista de objetos
     * @param items  an array of Object objects
     */
    public DefaultComboBoxModel(final T items[]) {
        objects = new ArrayList<T>();

        int i, c;
        for (i = 0, c = items.length; i < c; i++) {
            objects.add(items[i]);
        }

        if (getSize() > 0) {
            selectedObject = objects.get(0);
        }
    }

    // implementa javax.swing.ComboBoxModel
    /**
     * Establece valor del valor seleccionado puede ser nulo
     *{@code anObject} instancia de  T
     * ClassCastException
     * <p>
     * @param anObject The combo box value or null for no selection.
     */
    //@Override
    public void setSelectedItem(Object anObject) {
        if ((selectedObject != null && !selectedObject.equals(anObject))
                || selectedObject == null && anObject != null) {
            selectedObject = (T) anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    // implementa javax.swing.ComboBoxModel
    //@Override
    public T getSelectedItem() {
        return selectedObject;
    }

    // implementa javax.swing.ListModel
    //@Override
    public int getSize() {
        return objects.size();
    }

    // implementa javax.swing.ListModel
    //@Override
    public T getElementAt(int index) {
        if (index >= 0 && index < objects.size()) {
            return objects.get(index);
        } else {
            return null;
        }
    }

    /**
     * Regresa un valor entera de la posicion del objeto seleccionado
     *
     * @param anObject
     * @return entero donde 0 es la primera posicion
     */
    public int getIndexOf(T anObject) {
        return objects.indexOf(anObject);
    }

    // implementa javax.swing.MutableComboBoxModel
    public void addElement(T anObject) {
        objects.add(anObject);
        fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
        if (objects.size() == 1 && selectedObject == null && anObject != null) {
            setSelectedItem(anObject);
        }
    }

    // implementa javax.swing.MutableComboBoxModel
    public void insertElementAt(T anObject, int index) {
        objects.add(index, anObject);
        fireIntervalAdded(this, index, index);
    }

    // implementa javax.swing.MutableComboBoxModel
    public void removeElementAt(int index) {
        if (getElementAt(index) == selectedObject) {
            if (index == 0) {
                setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
            } else {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        objects.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    // implementa javax.swing.MutableComboBoxModel
    public void removeElement(T anObject) {
        int index = objects.indexOf(anObject);
        if (index != -1) {
            removeElementAt(index);
        }
    }

   /**
    * Vacia la lista
    */
    public void removeAllElements() {
        if (objects.size() > 0) {
            int firstIndex = 0;
            int lastIndex = objects.size() - 1;
            objects.clear();
            selectedObject = null;
            fireIntervalRemoved(this, firstIndex, lastIndex);
        } else {
            selectedObject = null;
        }
    }

    ////@Override
    public void add(List<T> elementsToAdd) {
        objects.addAll(elementsToAdd);
        fireContentsChanged(this, -1, -1);

    }

    //@Override
    public List<T> getElements() {
        return objects;
    }
}