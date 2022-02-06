/*
 * ServiciosComboBoxModel.java
 *
 * Created on 30 de mayo de 2008, 09:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ejecutarreportes.modelo;

import java.util.List;
import tms_exerpt.entidad.TmsEmpresasTbl;
import tms_exerpt.entidad.TmsServiciosTbl;

/**
 *
 * @author imunoz
 */
public class EmpresasComboBoxModel extends ObjListComboBoxModel {
    
    public EmpresasComboBoxModel(List servicios){
        super(servicios);
    }
                                                
    public Object getElementAt(int i) {         
        return ((TmsEmpresasTbl) objetos.get(i)).getEmpresaNombre();
    }
    
    public TmsEmpresasTbl getEmpresa(int i) {         
        return ((TmsEmpresasTbl) objetos.get(i));
    }
}
