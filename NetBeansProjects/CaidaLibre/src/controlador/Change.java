/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import javax.swing.event.*;
import static vista.Frame.*;

/**
 *
 * @author Javy
 */

public class Change implements ChangeListener {
    
   
    		   
    public void stateChanged(ChangeEvent e){
		   	   	  
        String x=""+barra.getValue();
        texto1.setText(x);
        etiqueta4.setText("y="+x+"m");
    }
                           
    
}
