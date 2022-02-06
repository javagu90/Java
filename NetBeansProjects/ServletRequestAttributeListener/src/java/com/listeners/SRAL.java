/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.listeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.swing.JOptionPane;

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SRAL implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    
        JOptionPane.showMessageDialog(null,"attributo agregado");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    
        JOptionPane.showMessageDialog(null,"atributo removido");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    
        JOptionPane.showMessageDialog(null,"atributo remplazado "+ srae.toString());
    }
}
