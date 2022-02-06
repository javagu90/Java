/*
 * JNumberTextFieldMaxDig.java
 *
 * Created on 27 de abril de 2007, 01:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package DialogosExeRrp;

import java.awt.Toolkit;
import javax.swing.JTextField;

/**
 * subclase de JTextField que acepta entradas de numeros y '.'  
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido*
 * @author vgonzalez
 */

public class JNumberTextFieldMaxDig extends JTextField {
    private int maxLength;
    private boolean beep_on_error = false;
    private  char c;
    /**
     * Creates a new instance of JNumberTextFieldMaxDig
     */
    public JNumberTextFieldMaxDig(int pMaxLength) {
        super();
        this.maxLength=pMaxLength;
        initializeForCuantity();
    }
    
    private void initializeForCuantity() {
        setDocument(new javax.swing.text.PlainDocument() {
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws    javax.swing.text.BadLocationException {
                StringBuffer buf = new StringBuffer(str);
                int size = buf.length();
                if (size>1) {
                    //es un setText() y no se valida nada solo se agrega el texto al campo
                    super.insertString(offs, buf.toString(), a);
                } else {
                    if ( offs < maxLength){ // maxLength
                        for (int i = 0; i < size; i++) {
                            c = buf.charAt(i);
                            // permite solo numeros y '.'
                            if (!Character.isDigit(c)) {
                                // beep de error
                                setBeepOnError(true);
                                if (beep_on_error){
                                    Toolkit.getDefaultToolkit().beep();
                                }
                                buf.deleteCharAt(i);
                            }
                        }//for
                        super.insertString(offs, buf.toString(), a);
                    }
                }//else
            }
        });
    }
    
    public void setBeepOnError(boolean beep) {
        beep_on_error = beep;
    }
}