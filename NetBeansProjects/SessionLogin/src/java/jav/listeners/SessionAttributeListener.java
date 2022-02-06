/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jav.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Web application lifecycle listener.
 *
 * @author Jav
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
     System.out.println("agregado");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    System.out.println("removido");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    System.out.println("reemplazado");
    }
}
