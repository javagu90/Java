/*
 * JTxtFieldUpperCase.java
 *
 * Created on 26 de abril de 2007, 07:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * Clase que solo acepta que transforma lo escrito en puras Mayusculas
 *
 * @author imorales
 */

 public class JTxtFieldUpperCase extends JTextField {
 
    /**
     * Metodo que Combierte todo lo que se escribe en el 
     * Textfield lo combierte en Mayusculas
     */
     public JTxtFieldUpperCase() {
        super();
        setDocument(new javax.swing.text.PlainDocument(){
           public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws 
         javax.swing.text.BadLocationException{
              if (str == null) {
                 return;
             }
             char[] upper = str.toCharArray();
             for (int i = 0; i < upper.length; i++) {
                 //aqui realiza la conversion a mayusculas
                 upper[i] = Character.toUpperCase(upper[i]);
             }
             super.insertString(offs, new String(upper), a); 
        
           }});
     }
 }
