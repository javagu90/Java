/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.swing.JOptionPane;

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SCAL implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
    
        JOptionPane.showMessageDialog(null, "Attributo agregado");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
       JOptionPane.showMessageDialog(null, "Attributo removido");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
       JOptionPane.showMessageDialog(null, "Attributo remplazado");
    }
}
