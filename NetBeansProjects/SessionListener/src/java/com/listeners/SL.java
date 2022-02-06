/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.swing.JOptionPane;

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SL implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        JOptionPane.showMessageDialog(null, "sesion iniciada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
      
    
        JOptionPane.showMessageDialog(null, "sesion terminada");
    }
}
