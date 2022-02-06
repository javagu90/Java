/*
 * ObjListComboBoxModel.java
 *
 * Created on 30 de mayo de 2008, 09:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ejecutarreportes.modelo;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author imunoz
 */
public abstract class ObjListComboBoxModel  <Elemento> extends AbstractListModel implements ComboBoxModel {
    private Object selectedObject = null;
    protected List<Elemento> objetos;
                
    public ObjListComboBoxModel(List<Elemento> obj){
        this.objetos = obj;
    }
                
    public Elemento getSelectedObject(int indice){
        return objetos.get(indice);
    }
                
    public void setSelectedItem(Object item) {
        selectedObject = item;
        fireContentsChanged(this, -1, -1);
    }
                
    public Object getSelectedItem() { 
        return selectedObject;
    }
                                                
    public int getSize() {
        return objetos.size();
    }
    
}