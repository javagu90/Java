
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SAL implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
    
        JOptionPane.showMessageDialog(null, "attributo agregado");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    
        JOptionPane.showMessageDialog(null, "attributo removido");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    
        JOptionPane.showMessageDialog(null, "atributo remplazado");
    }
}
