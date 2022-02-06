/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.swing.JOptionPane;

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SRL implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        JOptionPane.showMessageDialog(null,"peticion destruida");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    
        JOptionPane.showMessageDialog(null,"peticion iniciada");}
}
